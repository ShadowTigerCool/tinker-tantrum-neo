package com.mceternal.tinkertantrum.common.modifiers;

import com.mceternal.tinkertantrum.TinkerTantrum;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

import java.util.function.Supplier;

public class TinkerTantrumModifiers {

    private static final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(TinkerTantrum.MODID);

    public static void register(IEventBus bus) {
        MODIFIERS.register(bus);
    }

    public static final StaticModifier<QuarkyModifier> QUARKY = MODIFIERS.register("quarky", QuarkyModifier::new);

    public static final StaticModifier<OverchargingModifier> OVERCHARGING = MODIFIERS.register("overcharging", OverchargingModifier::new);

    public static final StaticModifier<Modifier> FERROMAGNETIC = registerIfLoaded("ferromagnetic", () -> Modifier::new, "alexscaves");

    public static final StaticModifier<OvermendingModifier> OVERMENDING = MODIFIERS.register("overmending", OvermendingModifier::new);

    public static final StaticModifier<VeinMiningModifier> VEINMINING = registerIfLoaded("veinmining", () -> VeinMiningModifier::new, "veinmining");

    public static final StaticModifier<DeflectingModifier> DEFLECTING = MODIFIERS.register("deflecting", DeflectingModifier::new);


    private static <T extends Modifier> StaticModifier<T> registerIfLoaded(String id, Supplier<Supplier<T>> modifier, String mod) {
        return ModList.get().isLoaded(mod)
                ? MODIFIERS.register(id, modifier.get())
                : null;
    }
}
