package de.viridis.mete.sound;

import de.viridis.mete.ViridisCore;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent LACHSI_PLUSH = registerSoundEvent("lachsi_plush");
    public static final SoundEvent METE_PLUSH = registerSoundEvent("mete_plush");
    public static final SoundEvent DOK_PLUSH = registerSoundEvent("dok_plush");
    public static final SoundEvent BIEN_PLUSH = registerSoundEvent("bien_plush");
    public static final SoundEvent MATTI_PLUSH = registerSoundEvent("matti_plush");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(ViridisCore.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        ViridisCore.LOGGER.info("Registering Mod Sounds for " + ViridisCore.MOD_ID);
    }
}
