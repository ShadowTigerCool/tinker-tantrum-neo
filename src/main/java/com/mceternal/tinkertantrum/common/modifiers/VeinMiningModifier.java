package com.mceternal.tinkertantrum.common.modifiers;

import com.illusivesoulworks.veinmining.VeinMiningMod;
import com.illusivesoulworks.veinmining.common.config.VeinMiningConfig;
import net.minecraft.world.item.enchantment.Enchantment;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.behavior.EnchantmentModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class VeinMiningModifier extends Modifier implements EnchantmentModifierHook.SingleEnchantment {

    public int getVeinMiningLevel(IToolStackView tool) {
        return getVeinMiningLevel(tool.getModifierLevel(this));
    }

    public int getVeinMiningLevel(ModifierEntry modifier) {
        return getVeinMiningLevel(modifier.getLevel());
    }

    public int getVeinMiningLevel(int level) {
        return Math.min(level, VeinMiningConfig.COMMON.levels.get());
    }

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.ENCHANTMENTS);
    }

    @Override
    public int getEnchantmentLevel(IToolStackView tool, ModifierEntry modifier) {
        return getVeinMiningLevel(modifier);
    }

    @Override
    public Enchantment getEnchantment(IToolStackView tool, ModifierEntry modifier) {
        return getEnchantment();
    }

    public Enchantment getEnchantment() {
        return VeinMiningMod.ENCHANTMENT;
    }
}
