package com.mceternal.tinkertantrum;

import com.mceternal.tinkertantrum.common.modifiers.TinkerTantrumModifiers;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TinkerTantrum.MODID)
public class TinkerTantrum
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "tinkertantrum";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation resource(String path) {
        return new ResourceLocation(MODID, path);
    }

    public TinkerTantrum(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        //modEventBus.addListener(this::addOptionalPacks);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        TinkerTantrumModifiers.register(modEventBus);
        //modEventBus.addListener(TinkerTantrumTagProvider::gatherData);
        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        context.registerConfig(ModConfig.Type.SERVER, TinkerTantrumConfig.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {

    }

    private void addOptionalPacks(AddPackFindersEvent event) {
        /*
        LOGGER.info("Fired AddPackFindersEvent for {}", event.getPackType());
        if(event.getPackType() == PackType.SERVER_DATA) {
            IModFile modFile = ModList.get().getModFileById(MODID).getFile();
            Path resourcePath = modFile.findResource(MODID, "resources", "tinkertantrum-ferromagnetic_iron");
            PathPackResources pack = new PathPackResources(modFile.getFileName() +":ferromagnetic_iron", resourcePath, true);
            PackMetadataSection packMetadata;
            try{
                packMetadata = pack.getMetadataSection(PackMetadataSection.TYPE);
                LOGGER.info("{}", packMetadata);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            LOGGER.info("resourcePath: {}, pack: {}", resourcePath, pack);
            if(packMetadata != null) {
                LOGGER.info("trying to add Ferromagnetic Iron pack...");
                event.addRepositorySource(c -> c.accept(Pack.create("tinkertantrum:ferromagnetic_iron",
                        Component.literal("Tinker Tantrum - Ferromagnetic Iron"),
                        false,
                        s -> pack,
                        new Pack.Info(packMetadata.getDescription(), packMetadata.getPackFormat(event.getPackType()), FeatureFlagSet.of()),
                        event.getPackType(),
                        Pack.Position.BOTTOM,
                        false,
                        PackSource.BUILT_IN
                )));
            }
        }
        */
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }
    }
}
