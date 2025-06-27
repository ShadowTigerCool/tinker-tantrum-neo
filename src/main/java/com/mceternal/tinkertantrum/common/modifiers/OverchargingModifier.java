package com.mceternal.tinkertantrum.common.modifiers;

import com.mceternal.tinkertantrum.TinkerTantrum;
import com.mceternal.tinkertantrum.TinkerTantrumConfig;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.item.armor.ModifiableArmorItem;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.modifiers.slotless.OverslimeModifier;

public class OverchargingModifier extends NoLevelsModifier implements MeleeHitModifierHook {

    public static final ResourceLocation KEY_HITS = TinkerTantrum.resource("overcharging_hits");

    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        LivingEntity attacker = context.getAttacker();
        int hits = getHits(tool);
        //TinkerTantrum.LOGGER.info("fired OverchargingModifier#afterMeleeHit");
        if(hits >= TinkerTantrumConfig.overchargingHitsPerCharge) {
            setHits(tool, 0);
            for (EquipmentSlot slot : EquipmentSlot.values())
                if (slot.isArmor()) {
                    ItemStack armor = attacker.getItemBySlot(slot);
                    //TinkerTantrum.LOGGER.info("armor slot {}: item {}", slot, armor.getDisplayName().getString());
                    if (armor.getItemHolder().get() instanceof ModifiableArmorItem) {
                        //TinkerTantrum.LOGGER.info("item in slot {} is ModifiableArmorItem", slot);
                        ToolStack stack = ToolStack.from(armor);
                        ModifierEntry overslime = stack.getModifier(TinkerModifiers.overslime.getId());
                        if (overslime == ModifierEntry.EMPTY) continue;
                        //TinkerTantrum.LOGGER.info("adding Overslime to item in slot {}", slot);
                        ((OverslimeModifier) overslime.getModifier()).addOverslime(stack, overslime, 1);
                    }
                }
        } else
            setHits(tool, hits + 1);
    }

    public int getHits(IToolStackView tool) {
        ModDataNBT modNBT = tool.getPersistentData();
        return modNBT.contains(KEY_HITS, Tag.TAG_INT)
                ? modNBT.getInt(KEY_HITS)
                : 0;
    }

    public void setHits(IToolStackView tool, int amount) {
        ModDataNBT modNBT = tool.getPersistentData();
        modNBT.putInt(KEY_HITS, amount);
    }

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_HIT);
    }
}
