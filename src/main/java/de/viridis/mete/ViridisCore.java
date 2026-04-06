package de.viridis.mete;

import de.viridis.mete.block.ModBlocks;
import de.viridis.mete.item.ModItemGroups;
import de.viridis.mete.sound.ModSounds;
import net.fabricmc.api.ModInitializer;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ViridisCore implements ModInitializer {
	public static final String MOD_ID = "viridiscore";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModSounds.registerSounds();
		ModBlocks.registerModBlocks();
	}
}