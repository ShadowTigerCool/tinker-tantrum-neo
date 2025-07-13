package com.mceternal.tinkertantrum.common.mixin.alexscaves;

import com.github.alexmodguy.alexscaves.server.entity.util.MagnetUtil;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mceternal.tinkertantrum.TinkerTantrum;
import com.mceternal.tinkertantrum.common.TinkerTantrumCompatUtil;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MagnetUtil.class)
public abstract class MagnetUtilMixin {

    @WrapOperation(method = "isDynamicallyMagnetic", //remap = false,
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/tags/TagKey;)Z"))
    private static boolean tinkertantrum_isDynamicallyMagnetic_isModifiableAndFerromagnetic(ItemStack stack, TagKey<Item> tag, Operation<Boolean> original) {
        //TinkerTantrum.LOGGER.info("resolving MagnetUtilMixin#isDynamicallyMagnetic_isModifiableAndFerromagnetic");
        return original.call(stack, tag) || TinkerTantrumCompatUtil.isModifiableAndFerromagnetic(stack, 1);
    }

    @WrapOperation(method = "isPulledByMagnets", //remap = false,
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/tags/TagKey;)Z"))
    private static boolean tinkertantrum_isPulledByMagnets(ItemStack stack, TagKey<Item> tag, Operation<Boolean> original) {
        //TinkerTantrum.LOGGER.info("resolving MagnetUtilMixin#isPulledByMagnets_isModifiableAndFerromagnetic");
        return original.call(stack, tag) || TinkerTantrumCompatUtil.isModifiableAndFerromagnetic(stack, 1);
    }
}
