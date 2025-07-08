package com.mceternal.tinkertantrum.common.modifiers;

import com.mceternal.tinkertantrum.TinkerTantrumConfig;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.UseAnim;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.behavior.ToolActionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.EntityInteractionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.GeneralInteractionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InteractionSource;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DeflectingModifier extends NoLevelsModifier implements ToolActionModifierHook, GeneralInteractionModifierHook, EntityInteractionModifierHook, MeleeHitModifierHook {

    public final Map<UUID, Long> CAN_DEFLECT_UNTIL = new HashMap<>();

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.TOOL_ACTION, ModifierHooks.GENERAL_INTERACT, ModifierHooks.MELEE_HIT);
    }

    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        Player player = context.getPlayerAttacker();
        if(player != null)
            setCanDeflect(player);
    }

    @Override
    public InteractionResult beforeEntityUse(IToolStackView tool, ModifierEntry modifier, Player player, Entity target, InteractionHand hand, InteractionSource source) {
        if(source == InteractionSource.RIGHT_CLICK && canDeflect(player)) {
            GeneralInteractionModifierHook.startUsing(tool, modifier.getId(), player, hand);
            return InteractionResult.CONSUME;
        }
        return InteractionResult.PASS;
    }

    @Override
    public InteractionResult onToolUse(IToolStackView tool, ModifierEntry modifier, Player player, InteractionHand hand, InteractionSource source) {
        if(source == InteractionSource.RIGHT_CLICK && hand == InteractionHand.MAIN_HAND && canDeflect(player)) {
            GeneralInteractionModifierHook.startUsing(tool, modifier.getId(), player, hand);
            return InteractionResult.CONSUME;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void onFinishUsing(IToolStackView tool, ModifierEntry modifier, LivingEntity entity) {
        if(entity instanceof Player player)
            player.getCooldowns().addCooldown(tool.getItem(), (int) (30 / tool.getStats().get(ToolStats.ATTACK_SPEED)));
    }

    @Override
    public void onStoppedUsing(IToolStackView tool, ModifierEntry modifier, LivingEntity entity, int timeLeft) {
        if(entity instanceof Player player)
            player.getCooldowns().addCooldown(tool.getItem(), (int) (30 / tool.getStats().get(ToolStats.ATTACK_SPEED)));
    }

    @Override
    public UseAnim getUseAction(IToolStackView tool, ModifierEntry modifier) {
        return UseAnim.BLOCK;
    }

    @Override
    public int getUseDuration(IToolStackView tool, ModifierEntry modifier) {
        return 10;
    }

    @Override
    public boolean canPerformAction(IToolStackView tool, ModifierEntry modifier, ToolAction toolAction) {
        return toolAction == ToolActions.SHIELD_BLOCK;
    }

    public long getWorldTime(LivingEntity entity) {
        return entity.level().getGameTime();
    }

    public void setCanDeflect(Player player) {
        CAN_DEFLECT_UNTIL.put(player.getUUID(), getWorldTime(player) + TinkerTantrumConfig.deflectingWindow);
    }

    public boolean canDeflect(Player player) {
        return CAN_DEFLECT_UNTIL.getOrDefault(player.getUUID(), 0L) > getWorldTime(player);
    }
}
