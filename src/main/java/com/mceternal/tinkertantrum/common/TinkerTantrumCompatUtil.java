package com.mceternal.tinkertantrum.common;

import com.mceternal.tinkertantrum.TinkerTantrum;
import com.mceternal.tinkertantrum.common.modifiers.TinkerTantrumModifiers;
import net.minecraft.world.item.ItemStack;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

public class TinkerTantrumCompatUtil {

    /**
     * - Alex's Caves Compat </p>
     * If a Modifiable Item is Ferromagnetic
     * @param stack Itemstack to test
     * @param minLevel Minimum Level required to pass. Used to make the Galena Gauntlet only respond to Ferromagnetic II+
     * @return If this ItemStack is {@link IModifiable}, and has the modifier {@code tinkertantrum:ferromagnetic}
     */
    public static boolean isModifiableAndFerromagnetic(ItemStack stack, int minLevel) {
        if(!(stack.getItem() instanceof IModifiable))
            return false;
        ModifierEntry ferromagnetic = ToolStack.from(stack).getModifier(TinkerTantrumModifiers.FERROMAGNETIC.get());
        return ferromagnetic != ModifierEntry.EMPTY
                && ferromagnetic.getLevel() >= minLevel;
                    //|| tool.getMaterials().getList().stream()
                    //.anyMatch(material -> MaterialRegistry.getInstance().isInTag(material.getId(), TinkerTantrumTags.Materials.FERROMAGNETIC_MATERIALS));
        //TinkerTantrum.LOGGER.info("stack {} is not Ferromagnetic", stack.getDisplayName().getString());
    }
}
