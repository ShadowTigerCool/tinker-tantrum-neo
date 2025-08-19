package com.mceternal.tinkertantrum.common.modifiers;

import com.bobmowzie.mowziesmobs.server.ability.AbilityHandler;
import com.bobmowzie.mowziesmobs.server.capability.CapabilityHandler;
import com.bobmowzie.mowziesmobs.server.capability.PlayerCapability;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.interaction.GeneralInteractionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InteractionSource;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class WroughtSlamModifier extends NoLevelsModifier implements GeneralInteractionModifierHook {

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.GENERAL_INTERACT);
    }

    @Override
    public InteractionResult onToolUse(IToolStackView tool, ModifierEntry modifier, Player player, InteractionHand hand, InteractionSource source) {
        if(hand == InteractionHand.MAIN_HAND && player.getAttackStrengthScale(0.5f) == 1.0f) {
            PlayerCapability.IPlayerCapability playerCap = CapabilityHandler.getCapability(player, CapabilityHandler.PLAYER_CAPABILITY);
            if(playerCap != null
                    && playerCap.getUntilAxeSwing() <= 0
                    && player.isShiftKeyDown()
                    && player.onGround()) {

                AbilityHandler.INSTANCE.sendAbilityMessage(player, AbilityHandler.WROUGHT_AXE_SLAM_ABILITY);
                playerCap.setVerticalSwing(true);
                playerCap.setUntilAxeSwing(30);
                player.startUsingItem(hand);
                player.getItemInHand(hand).hurtAndBreak(2, player, p -> p.broadcastBreakEvent(hand));
            }

            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
