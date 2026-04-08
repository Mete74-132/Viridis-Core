package de.viridis.mete.block;

import de.viridis.mete.ViridisCore;
import de.viridis.mete.sound.ModSounds;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

/**
 * Central class for registering all custom blocks (and their block‑items) for
 * the ViridisCore mod.
 */
public final class ModBlocks {

    /* --------------------------------------------------------------------- */
    /*  BLOCK DEFINITIONS – ONE LINE PER PLUSH                               */
    /* --------------------------------------------------------------------- */
    public static final Block METE_PLUSH = registerBlock(
            "mete_plush",
            new PlushBlockBase(AbstractBlock.Settings.create()
                    .strength(0.5f)
                    .sounds(BlockSoundGroup.WOOL),
                    ModSounds.METE_PLUSH));

    public static final Block DOK_PLUSH = registerBlock(
            "dok_plush",
            new PlushBlockBase(AbstractBlock.Settings.create()
                    .strength(0.5f)
                    .sounds(BlockSoundGroup.WOOL),
                    ModSounds.DOK_PLUSH));

    public static final Block BIEN_PLUSH = registerBlock(
            "bien_plush",
            new PlushBlockBase(AbstractBlock.Settings.create()
                    .strength(0.5f)
                    .sounds(BlockSoundGroup.WOOL),
                    ModSounds.BIEN_PLUSH));

    public static final Block LACHSI_PLUSH = registerBlock(
            "lachsi_plush",
            new PlushBlockBase(AbstractBlock.Settings.create()
                    .strength(0.5f)
                    .sounds(BlockSoundGroup.WOOL),
                    ModSounds.LACHSI_PLUSH));

    public static final Block MATTI_PLUSH = registerBlock(
            "matti_plush",
            new PlushBlockBase(AbstractBlock.Settings.create()
                    .strength(0.5f)
                    .sounds(BlockSoundGroup.WOOL),
                    ModSounds.MATTI_PLUSH));

    /* --------------------------------------------------------------------- */
    /*  INTERNAL REGISTRATION HELPERS                                        */
    /* --------------------------------------------------------------------- */
    private static Block registerBlock(String name, Block block) {
        // Register the BlockItem first – Fabric tolerates this order during init.
        registerBlockItem(name, block);

        // Register the block itself.
        return Registry.register(
                Registries.BLOCK,
                Identifier.of(ViridisCore.MOD_ID, name),
                block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(
                Registries.ITEM,
                Identifier.of(ViridisCore.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    /* --------------------------------------------------------------------- */
    /*  PUBLIC REGISTRATION ENTRYPOINT                                        */
    /* --------------------------------------------------------------------- */
    /** Called from {@link ViridisCore#onInitialize()}. */
    public static void registerModBlocks() {
        // Example: add the blocks to a custom creative tab if you have one.
        // ItemGroupEvents.modifyEntriesEvent(ModItemGroup.MY_TAB).register(content -> {
        //     content.add(METE_PLUSH);
        //     content.add(DOK_PLUSH);
        //     content.add(BIEN_PLUSH);
        //     content.add(LACHSI_PLUSH);
        //     content.add(MATTI_PLUSH);
        // });
        ViridisCore.LOGGER.info("Registered {} plush blocks", 5);
    }
}
