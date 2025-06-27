package com.mceternal.tinkertantrum.common;

import com.mceternal.tinkertantrum.TinkerTantrum;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class TinkerTantrumTags {

    public static class Items {

        //public static final TagKey<Item> QUARKY = modTag("quarky");

        public static TagKey<Item> modTag(String path) {
            return modTagForRegistry(Registries.ITEM, path);
        }

    }


    public static <T> TagKey<T> modTagForRegistry(ResourceKey<? extends Registry<T>> registryKey, String path) {
        return TagKey.create(registryKey, modResource(path));
    }


    public static ResourceLocation modResource(String path) {
        return new ResourceLocation(TinkerTantrum.MODID, path);
    }
}
