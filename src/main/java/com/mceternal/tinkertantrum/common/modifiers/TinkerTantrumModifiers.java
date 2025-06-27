package com.mceternal.tinkertantrum.common.modifiers;

import com.mceternal.tinkertantrum.TinkerTantrum;
import net.minecraftforge.eventbus.api.IEventBus;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

public class TinkerTantrumModifiers {

    private static final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(TinkerTantrum.MODID);

    public static void register(IEventBus bus) {
        MODIFIERS.register(bus);
    }

    public static final StaticModifier<QuarkyModifier> QUARKY = MODIFIERS.register("quarky", QuarkyModifier::new);

    public static final StaticModifier<OverchargingModifier> OVERCHARGING = MODIFIERS.register("overcharging", OverchargingModifier::new);
}
