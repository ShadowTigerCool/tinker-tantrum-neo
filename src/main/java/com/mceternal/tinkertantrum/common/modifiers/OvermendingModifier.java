package com.mceternal.tinkertantrum.common.modifiers;

import com.mceternal.tinkertantrum.TinkerTantrum;
import com.mceternal.tinkertantrum.TinkerTantrumConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.modifiers.slotless.OverslimeModifier;

public class OvermendingModifier extends Modifier implements InventoryTickModifierHook {

    public static final ResourceLocation KEY_OVERMENDING_CHARGE = TinkerTantrum.resource("overmending_charge");

    public OvermendingModifier() {
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, this::experiencePickup);
    }


    @Override
    public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level level, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        //TinkerTantrum.LOGGER.info("OvermendingModifier#onInventoryTick");
        if(!level.isClientSide
                && level.getGameTime() % TinkerTantrumConfig.overmendingRechargeFrequency == 0
                && getPendingCharge(tool) > 0
                && tool.getModifier(TinkerModifiers.overslime.get()) != ModifierEntry.EMPTY
                && getOverslime(tool) < tool.getStats().get(OverslimeModifier.OVERSLIME_STAT).intValue())
            chargeOverslime(tool, modifier.getLevel());
    }

    public int getMaxOverslime(IToolStackView tool) {
        return tool.getStats().get(OverslimeModifier.OVERSLIME_STAT).intValue();
    }

    public int getOverslime(IToolStackView tool) {
        return tool.getPersistentData().getInt(OverslimeModifier.OVERSLIME_STAT.getName());
    }

    public int getMaxCharge(int level) {
            return 30 * level;
    }

    public void chargeOverslime(IToolStackView tool, int amount) {
        ModifierEntry overslime = tool.getModifier(TinkerModifiers.overslime.get());
        if(overslime == ModifierEntry.EMPTY)
            return;
        int value = Math.min(Math.min(amount, getPendingCharge(tool)), getMaxOverslime(tool) - getOverslime(tool));
        //TinkerTantrum.LOGGER.info("Charging {} Overslime for tool with {} charge", value, getPendingCharge(tool));
        removePendingCharge(tool, value);
        TinkerModifiers.overslime.get().addOverslime(tool, overslime, value);
    }

    public void addPendingCharge(IToolStackView tool, int amount) {
        //TinkerTantrum.LOGGER.info("adding Overmending {} charge", amount);
        setPendingCharge(tool, getPendingCharge(tool) + amount);
    }

    public void removePendingCharge(IToolStackView tool, int amount) {
        //TinkerTantrum.LOGGER.info("removing Overmending {} charge", amount);
        setPendingCharge(tool, getPendingCharge(tool) - amount);
    }

    public void setPendingCharge(IToolStackView tool, int amount) {
        //TinkerTantrum.LOGGER.info("setting Overmending charge to {}", amount);
        tool.getPersistentData().putInt(KEY_OVERMENDING_CHARGE, amount);
    }

    public int getPendingCharge(IToolStackView tool) {
        ModDataNBT data = tool.getPersistentData();
        //TinkerTantrum.LOGGER.info("got Overmending charge {}", charge);
        return tool.getModifier(this) != ModifierEntry.EMPTY
                && data.contains(KEY_OVERMENDING_CHARGE, 3)
                ? data.getInt(KEY_OVERMENDING_CHARGE)
                : 0;
    }

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.INVENTORY_TICK);
    }

    /*
    @Override
    public List<Component> getDescriptionList(IToolStackView tool, ModifierEntry entry) {
        List<Component> result = super.getDescriptionList(tool, entry);
        result.add(Component.translatable("description.tinkertantrum.overmending.charge", getPendingCharge(tool), getMaxCharge(entry)));
        return result;
    }
     */

    @SubscribeEvent
    public void experiencePickup(PlayerXpEvent.PickupXp event) {
        //TinkerTantrum.LOGGER.info("caught PlayerXpEvent.PickupXp");
        int xpValue = event.getOrb().getValue();
        //TinkerTantrum.LOGGER.info("XP Orb starting value: {}", xpValue);
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            ItemStack equipmentStack = event.getEntity().getItemBySlot(slot);

            if(!(equipmentStack.getItem() instanceof IModifiable)) continue;
            ToolStack tool = ToolStack.from(equipmentStack);
            ModifierEntry overmend = tool.getModifier(this);
            if(overmend == ModifierEntry.EMPTY) continue;

            int availableCharge = Math.max(0, getMaxCharge(overmend.getLevel()) - getPendingCharge(tool));
            //TinkerTantrum.LOGGER.info("xpValue: {}, availableCharge: {}, getMaxCharge: {}, getPendingCharge: {}", xpValue, availableCharge, getMaxCharge(overmend), getPendingCharge(tool));
            int toAdd = Math.min((int) (xpValue * 2f), availableCharge);
            //TinkerTantrum.LOGGER.info("toAdd: {}", toAdd);
            if(toAdd == 0)
                continue;
            addPendingCharge(tool, toAdd);
            xpValue -= (int) Math.ceil(toAdd / 2f);
            if(xpValue <= 0)
                break;
        }
        //TinkerTantrum.LOGGER.info("XP Orb was reduced to {} by Overmending", xpValue);
        event.getOrb().value = Math.max(xpValue, 0);
    }
}
