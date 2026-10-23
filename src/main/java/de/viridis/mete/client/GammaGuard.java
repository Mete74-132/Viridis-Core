package de.viridis.mete.client;

import dev.doctor4t.wathe.cca.GameWorldComponent;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;

public final class GammaGuard {
    private static final double VANILLA_MAX_GAMMA = 1.0;
    private static final boolean PRESENT = FabricLoader.getInstance().isModLoaded("gammautils");

    private static boolean failed;
    @Nullable
    private static Object gamma;
    @Nullable
    private static Object nightVision;

    // gesicherte Spieler-Einstellungen (null = nichts gesichert)
    @Nullable
    private static Double savedGamma;
    @Nullable
    private static Boolean savedGammaDynamic;
    @Nullable
    private static Boolean savedNightVision;
    @Nullable
    private static Boolean savedNightVisionDynamic;

    private GammaGuard() {
    }

    public static void tick(MinecraftClient client) {
        if (!PRESENT || failed) return;
        try {
            if (gamma == null) {
                Object config = Class.forName("io.github.sjouwer.gammautils.GammaUtils").getMethod("getConfig").invoke(null);
                gamma = config.getClass().getField("gamma").get(config);
                nightVision = config.getClass().getField("nightVision").get(config);
            }
            boolean inRound = client.world != null && client.player != null
                    && GameWorldComponent.KEY.get(client.world).getGameStatus() == GameWorldComponent.GameStatus.ACTIVE;
            if (inRound) enforce();
            else restore();
        } catch (ReflectiveOperationException | LinkageError | RuntimeException e) {
            failed = true;
        }
    }

    public static void onDisconnect() {
        if (!PRESENT || failed || gamma == null) return;
        try {
            restore();
        } catch (ReflectiveOperationException | RuntimeException e) {
            failed = true;
        }
    }

    private static void enforce() throws ReflectiveOperationException {
        if (bool(gamma, "isDynamicEnabled")) {
            if (savedGammaDynamic == null) savedGammaDynamic = true;
            call(gamma, "setDynamicStatus", false);
        }
        double value = (double) gamma.getClass().getMethod("getValue").invoke(gamma);
        if (value > VANILLA_MAX_GAMMA) {
            if (savedGamma == null) savedGamma = value;
            gamma.getClass().getMethod("setValue", double.class).invoke(gamma, VANILLA_MAX_GAMMA);
        }
        if (bool(nightVision, "isDynamicEnabled")) {
            if (savedNightVisionDynamic == null) savedNightVisionDynamic = true;
            call(nightVision, "setDynamicStatus", false);
        }
        if (bool(nightVision, "isEnabled")) {
            if (savedNightVision == null) savedNightVision = true;
            call(nightVision, "setStatus", false);
        }
    }

    private static void restore() throws ReflectiveOperationException {
        if (savedGamma != null) {
            gamma.getClass().getMethod("setValue", double.class).invoke(gamma, savedGamma);
            savedGamma = null;
        }
        if (savedGammaDynamic != null) {
            call(gamma, "setDynamicStatus", true);
            savedGammaDynamic = null;
        }
        if (savedNightVision != null) {
            call(nightVision, "setStatus", true);
            savedNightVision = null;
        }
        if (savedNightVisionDynamic != null) {
            call(nightVision, "setDynamicStatus", true);
            savedNightVisionDynamic = null;
        }
    }

    private static boolean bool(Object target, String method) throws ReflectiveOperationException {
        Method m = target.getClass().getMethod(method);
        return (boolean) m.invoke(target);
    }

    private static void call(Object target, String method, boolean value) throws ReflectiveOperationException {
        target.getClass().getMethod(method, boolean.class).invoke(target, value);
    }
}
