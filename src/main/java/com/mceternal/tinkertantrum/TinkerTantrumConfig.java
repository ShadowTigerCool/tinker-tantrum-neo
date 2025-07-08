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
    public static int deflectingWindow;
    public static int deflectingBaseCooldown;


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

    public static final ForgeConfigSpec.IntValue OVERMENDING_RECHARGE_FREQUENCY = BUILDER
            .comment("Delay in Ticks between attempts by items with the Overmending modifier to drain charge and gain Overslime.")
            .defineInRange("overmendingRechargeFrequency", 200, 1, 1200);

    public static final ForgeConfigSpec.IntValue DEFLECTING_WINDOW = BUILDER
            .comment("Window of time after attacking in which the Deflecting modifier can be used.")
            .defineInRange("deflectingWindow", 20, 5, 100);

    public static final ForgeConfigSpec.IntValue DEFLECTING_BASE_COOLDOWN = BUILDER
            .comment("Base Cooldown after using the Deflecting modifier's Block.")
            .comment("This value is divided by the tool's Attack Speed to produce the applied cooldown.")
            .defineInRange("deflectingBaseCooldown", 30, 5, 200);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        opQuarky = OP_QUARKY.get();
        quarkyEffectPerStack = QUARKY_EFFECT_PER_STACK.get();
        overchargingHitsPerCharge = OVERCHARGING_HITS_PER_CHARGE.get();
        overmendingRechargeFrequency = OVERMENDING_RECHARGE_FREQUENCY.get();
        deflectingWindow = DEFLECTING_WINDOW.get();
        deflectingBaseCooldown = DEFLECTING_BASE_COOLDOWN.get();
    }
}
