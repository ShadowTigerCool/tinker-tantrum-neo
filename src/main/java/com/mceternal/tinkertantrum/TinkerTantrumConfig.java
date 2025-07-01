package com.mceternal.tinkertantrum;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = TinkerTantrum.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TinkerTantrumConfig {

    public static boolean opQuarky;
    public static double quarkyEffectPerStack;
    public static int overchargingHitsPerCharge;
    public static int overmendingRechargeFrequency;


    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.BooleanValue OP_QUARKY = BUILDER
            .comment("If the Quarky modifier's multiplication is applied to the currently calculated Damage rather than the Base Damage.")
            .define("opQuarky", false);

    public static final ForgeConfigSpec.DoubleValue QUARKY_EFFECT_PER_STACK = BUILDER
            .comment("How much the Quarky modifier increases Damage per valid stack.")
            .defineInRange("quarkyEffectPerStack", 0.05, 0.001, 1.0);

    public static final ForgeConfigSpec.IntValue OVERCHARGING_HITS_PER_CHARGE = BUILDER
            .comment("How many hits are required for Overcharging to add Overslime to all worn armor that accepts it.")
            .defineInRange("overchargingHits", 5, 1, 100);

    public static final ForgeConfigSpec.IntValue OVERMENDING_RECHARGE_FREQUENCY = BUILDER.comment()
    .defineInRange("overmendingRechargeFrequency", 100, 1, 1200);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        opQuarky = OP_QUARKY.get();
        quarkyEffectPerStack = QUARKY_EFFECT_PER_STACK.get();
        overchargingHitsPerCharge = OVERCHARGING_HITS_PER_CHARGE.get();
        overmendingRechargeFrequency = OVERMENDING_RECHARGE_FREQUENCY.get();
    }
}
