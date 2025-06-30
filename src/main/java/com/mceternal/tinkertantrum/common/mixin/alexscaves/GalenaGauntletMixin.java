package com.mceternal.tinkertantrum.common.mixin.alexscaves;

import com.github.alexmodguy.alexscaves.server.item.GalenaGauntletItem;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mceternal.tinkertantrum.common.TinkerTantrumCompatUtil;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GalenaGauntletItem.class)
public abstract class GalenaGauntletMixin {

    @WrapOperation(method = "use", remap = false,
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/tags/TagKey;)Z"))
    public boolean tinkertantrum_use_isModifiableAndFerromagnetic(ItemStack stack, TagKey<Item> pTag, Operation<Boolean> original) {
        //TinkerTantrum.LOGGER.info("resolving GalenaGauntletMixin#use_isModifiableAndFerromagnetic");
        return original.call(stack, pTag) || TinkerTantrumCompatUtil.isModifiableAndFerromagnetic(stack);
    }

    @WrapOperation(method  = "onUseTick", remap = false,
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/tags/TagKey;)Z"))
    public boolean tinkertantrum_onUseTick_isModifiableAndFerromagnetic(ItemStack stack, TagKey<Item> pTag, Operation<Boolean> original) {
        //TinkerTantrum.LOGGER.info("resolving GalenaGauntletMixin#onUseTick_isModifiableAndFerromagnetic");
        return original.call(stack, pTag) || TinkerTantrumCompatUtil.isModifiableAndFerromagnetic(stack);
    }
}
