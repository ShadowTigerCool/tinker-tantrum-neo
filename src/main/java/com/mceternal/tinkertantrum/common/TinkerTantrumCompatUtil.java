package com.mceternal.tinkertantrum.common;

import com.mceternal.tinkertantrum.TinkerTantrum;
import com.mceternal.tinkertantrum.common.modifiers.TinkerTantrumModifiers;
import net.minecraft.world.item.ItemStack;
import slimeknights.tconstruct.library.materials.MaterialRegistry;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

public class TinkerTantrumCompatUtil {

    /**
     * - Alex's Caves Compat </p>
     * Test if an {@link ItemStack}'s {@link net.minecraft.world.item.Item Item} is {@link IModifiable}, and the
     * @param stack Itemstack to test
     * @return If this ItemStack is {@link IModifiable}, and is Ferromagnetic by {@link TinkerTantrumModifiers#FERROMAGNETIC Ferromagnetic Modifier}, or a Tool part of a Material tagged as {@code tinkertantrum:compat/alexscaves/ferromagnetic_materials}
     */
    public static boolean isModifiableAndFerromagnetic(ItemStack stack) {
        if(stack.getItem() instanceof IModifiable) {
            ToolStack tool = ToolStack.from(stack);
            return tool.getModifier(TinkerTantrumModifiers.FERROMAGNETIC.get()) != ModifierEntry.EMPTY
                    || tool.getMaterials().getList().stream()
                    .anyMatch(material -> MaterialRegistry.getInstance().isInTag(material.getId(), TinkerTantrumTags.Materials.FERROMAGNETIC_MATERIALS));
        }
        //TinkerTantrum.LOGGER.info("stack {} is not Ferromagnetic", stack.getDisplayName().getString());
        return false;
    }
}
