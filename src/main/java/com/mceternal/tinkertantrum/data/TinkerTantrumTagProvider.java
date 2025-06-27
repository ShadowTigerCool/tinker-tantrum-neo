package com.mceternal.tinkertantrum.data;

import com.google.common.collect.ImmutableList;
import com.mceternal.tinkertantrum.TinkerTantrum;
import com.mceternal.tinkertantrum.common.TinkerTantrumTags;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class TinkerTantrumTagProvider extends TagsProvider<Item> {

    protected TinkerTantrumTagProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, ExistingFileHelper fileHelper) {
        super(pOutput, Registries.ITEM, pLookupProvider, TinkerTantrum.MODID, fileHelper);
    }

    public static void gatherData(GatherDataEvent event) {
        event.getGenerator().addProvider(event.includeServer(), (Factory<TinkerTantrumTagProvider>) output -> new TinkerTantrumTagProvider(
                output,
                event.getLookupProvider(),
                event.getExistingFileHelper())
        );
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        //provider.lookup(Registries.ITEM).get().listElements()
        //        .filter(this::isByVazkii)
        //        .forEach(this::addToQuarkyTag);
    }
/*
    public boolean isByVazkii(Holder.Reference<Item> item) {
        return VAZKII_MODS.contains(item.key().location().getNamespace());
    }

    public void addToQuarkyTag(Holder.Reference<Item> item) {
        TinkerTantrum.LOGGER.info("applying Quarky tag to {}", item.key().location());
        this.tag(TinkerTantrumTags.Items.QUARKY).addOptional(item.key().location());
    }
 */
}
