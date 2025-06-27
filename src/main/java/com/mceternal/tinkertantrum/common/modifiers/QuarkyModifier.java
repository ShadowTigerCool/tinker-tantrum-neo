package com.mceternal.tinkertantrum.common.modifiers;

import com.google.common.collect.ImmutableList;
import com.mceternal.tinkertantrum.TinkerTantrum;
import com.mceternal.tinkertantrum.TinkerTantrumConfig;
import com.mceternal.tinkertantrum.common.TinkerTantrumTags;
import com.mceternal.tinkertantrum.data.TinkerTantrumTagProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;
import java.util.Optional;

public class QuarkyModifier extends NoLevelsModifier implements MeleeDamageModifierHook {

    public static final List<String> VAZKII_MODS = ImmutableList.of("botania", "quark", "akashictome", "morphotool", "psi", "patchouli");

    @Override
    public float getMeleeDamage(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        TinkerTantrum.LOGGER.info("Fired QuarkyModifier#getMeleeDamage!");
        float sourceDamage = TinkerTantrumConfig.opQuarky ? damage : baseDamage;
        LivingEntity attacker = context.getAttacker();
        if(attacker instanceof Player player) {
            List<ItemStack> inventoryItems = player.getInventory().items.subList(0, 9);
            return damage + (sourceDamage * computeQuarkiness(inventoryItems));
        } else
            return damage;
    }

    public float computeQuarkiness(List<ItemStack> stacks) {
        float effect = 0.0f;
        for (ItemStack stack : stacks) {
            if(isQuarky(stack))
                effect += (float) (TinkerTantrumConfig.quarkyEffectPerStack * ((double) stack.getCount() / stack.getMaxStackSize()));
        }
        TinkerTantrum.LOGGER.info("QuarkyModifier bonus: {}", effect);
        return effect;
    }

    public boolean isQuarky(ItemStack stack) {
        Optional<ResourceKey<Item>> key = stack.getItemHolder().unwrapKey();
        return key.isPresent() && VAZKII_MODS.contains(key.get().location().getNamespace());
    }

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_DAMAGE);
    }
}
