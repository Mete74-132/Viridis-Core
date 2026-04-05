package de.viridis.mete;

import de.viridis.mete.block.ModBlocks;
import de.viridis.mete.item.ModItemGroups;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ViridisCore implements ModInitializer {
	public static final String MOD_ID = "viridiscore";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();

		ModBlocks.registerModBlocks();
	}
}