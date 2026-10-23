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


public final class ModBlocks {

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

    public static final Block SODOG_PLUSH = registerBlock(
            "sodog_plush",
            new PlushBlockBase(AbstractBlock.Settings.create()
                    .strength(0.5f)
                    .sounds(BlockSoundGroup.WOOL),
                    ModSounds.SODOG_PLUSH));

    public static final Block COVER_PLUSH = registerBlock(
            "cover_plush",
            new PlushBlockBase(AbstractBlock.Settings.create()
                    .strength(0.5f)
                    .sounds(BlockSoundGroup.WOOL),
                    ModSounds.COVER_PLUSH));

    public static final Block FLOWERY_PLUSH = registerBlock(
            "flowery_plush",
            new PlushBlockBase(AbstractBlock.Settings.create()
                    .strength(0.5f)
                    .sounds(BlockSoundGroup.WOOL),
                    ModSounds.FLOWERY_PLUSH));

    public static final Block MADDIE_PLUSH = registerBlock(
            "maddie_plush",
            new PlushBlockBase(AbstractBlock.Settings.create()
                    .strength(0.5f)
                    .sounds(BlockSoundGroup.WOOL),
                    ModSounds.MADDIE_PLUSH));

    public static final Block TRS_PLUSH = registerBlock(
            "trs_plush",
            new PlushBlockBase(AbstractBlock.Settings.create()
                    .strength(0.5f)
                    .sounds(BlockSoundGroup.WOOL),
                    ModSounds.TRS_PLUSH));

    public static final Block VERITY_PLUSH = registerBlock(
            "verity_plush",
            new PlushBlockBase(AbstractBlock.Settings.create()
                    .strength(0.5f)
                    .sounds(BlockSoundGroup.WOOL),
                    ModSounds.VERITY_PLUSH));

    public static final Block ZOCKER_PLUSH = registerBlock(
            "zocker_plush",
            new PlushBlockBase(AbstractBlock.Settings.create()
                    .strength(0.5f)
                    .sounds(BlockSoundGroup.WOOL),
                    ModSounds.ZOCKER_PLUSH));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);

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

    public static void registerModBlocks() {

    }
}
