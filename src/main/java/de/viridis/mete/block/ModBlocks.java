package de.viridis.mete.block;

import de.viridis.mete.ViridisCore;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

/**
 * Central class for registering all custom blocks (and their block‑items) for the
 * ViridisCore mod.
 */
public final class ModBlocks {

    // ------------------------------------------------------------------------
    //  BLOCK DEFINITIONS
    // ------------------------------------------------------------------------

    public static final Block METE_PLUSH = registerBlock(
            "mete_plush",
            new MetePlushBlock(AbstractBlock.Settings.create()
                    .strength(4.0f)
                    .sounds(BlockSoundGroup.WOOL))
    );

    public static final Block DOK_PLUSH = registerBlock(
            "dok_plush",
            new MetePlushBlock(AbstractBlock.Settings.create()
                    .strength(4.0f)
                    .sounds(BlockSoundGroup.WOOL))
    );

    public static final Block BIEN_PLUSH = registerBlock(
            "bien_plush",
            new MetePlushBlock(AbstractBlock.Settings.create()
                    .strength(4.0f)
                    .sounds(BlockSoundGroup.WOOL))
    );

    public static final Block LACHSI_PLUSH = registerBlock(
            "lachsi_plush",
            new MetePlushBlock(AbstractBlock.Settings.create()
                    .strength(4.0f)
                    .sounds(BlockSoundGroup.WOOL))
    );

    // ------------------------------------------------------------------------
    //  INTERNAL REGISTRATION HELPERS
    // ------------------------------------------------------------------------

    /**
     * Registers the block *and* its corresponding {@link BlockItem}.
     *
     * @param name  registry name (without namespace)
     * @param block the block instance
     * @return the block that was registered (useful for static field init)
     */
    private static Block registerBlock(String name, Block block) {
        // Register the BlockItem first – the item registry expects the block to be
        // already in the block registry, but Fabric tolerates the order as long as
        // both calls happen during the same mod‑initialisation phase.
        registerBlockItem(name, block);

        // Register the block itself.
        return Registry.register(
                Registries.BLOCK,
                Identifier.of(ViridisCore.MOD_ID, name),
                block
        );
    }

    /**
     * Registers the {@link BlockItem} that represents {@code block} in the item
     * registry. By default the block‑item will **not** have a creative‑tab
     * group; we add it later via {@link ItemGroupEvents}.
     *
     * @param name  registry name (without namespace)
     * @param block the associated block
     */
    private static void registerBlockItem(String name, Block block) {
        Registry.register(
                Registries.ITEM,
                Identifier.of(ViridisCore.MOD_ID, name),
                new BlockItem(block, new Item.Settings())
        );
    }

    // ------------------------------------------------------------------------
    //  PUBLIC REGISTRATION ENTRYPOINT
    // ------------------------------------------------------------------------

    /**
     * Called from {@link ViridisCore#onInitialize()}.
     * <p>
     * Adds the custom blocks to the {@code Ingredients} creative tab.
     */
    public static void registerModBlocks() {
        ViridisCore.LOGGER.info("Registering Mod Blocks for {}", ViridisCore.MOD_ID);

        // The lambda **must** contain both add‑calls inside its body.
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
                .register(entries -> {
                    entries.add(METE_PLUSH);
                    entries.add(DOK_PLUSH);
                    entries.add(BIEN_PLUSH);
                    entries.add(LACHSI_PLUSH);
                });
    }

    // ------------------------------------------------------------------------
    //  PRIVATE CONSTRUCTOR – utility class
    // ------------------------------------------------------------------------

    private ModBlocks() {
        // Prevent instantiation
    }
}
