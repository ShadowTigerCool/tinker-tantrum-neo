package com.mceternal.tinkertantrum.common;

import com.mceternal.tinkertantrum.TinkerTantrum;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import slimeknights.tconstruct.library.materials.definition.IMaterial;
import slimeknights.tconstruct.library.materials.definition.MaterialManager;

public class TinkerTantrumTags {

    public static class Items {

        //public static final TagKey<Item> QUARKY = modTag("quarky");

        public static TagKey<Item> modTag(String path) {
            return modTagForRegistry(Registries.ITEM, path);
        }

    }

    public static class Entity {

        public static final TagKey<EntityType<?>> VAMPIRE_SLAYABLE = modTag("vampire_slayable");

        public static TagKey<EntityType<?>> modTag(String path) {
            return modTagForRegistry(Registries.ENTITY_TYPE, path);
        }
    }

    public static class Materials {

        public static final TagKey<IMaterial> FERROMAGNETIC_MATERIALS = modTag("compat/alexscaves/ferromagnetic_materials");

        public static TagKey<IMaterial> modTag(String path) {
            return modTagForRegistry(MaterialManager.REGISTRY_KEY, path);
        }
    }


    public static <T> TagKey<T> modTagForRegistry(ResourceKey<? extends Registry<T>> registryKey, String path) {
        return TagKey.create(registryKey, TinkerTantrum.resource(path));
    }
}
