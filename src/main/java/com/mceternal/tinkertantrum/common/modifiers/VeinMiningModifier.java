package com.mceternal.tinkertantrum.common.modifiers;

import com.illusivesoulworks.veinmining.common.veinmining.VeinMiningEvents;
import com.illusivesoulworks.veinmining.common.veinmining.VeinMiningPlayers;
import com.illusivesoulworks.veinmining.common.veinmining.logic.VeinMiningLogic;
import net.minecraft.server.level.ServerPlayer;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.mining.BlockBreakModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class VeinMiningModifier extends NoLevelsModifier implements BlockBreakModifierHook {

    @Override
    public void afterBlockBreak(IToolStackView tool, ModifierEntry modifier, ToolHarvestContext context) {
        ServerPlayer player = context.getPlayer();
        if(player == null) return;
        //VeinMiningEvents.blockBreak(context.getPlayer(), context.getPos(), context.getState());
    }

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.BLOCK_BREAK);
    }
}
