package de.viridis.mete.block;

import com.mojang.serialization.MapCodec;
import de.viridis.mete.ViridisCore;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block METE_PLUSH = registerBlock("mete_plush",
            new HorizontalFacingBlock(AbstractBlock.Settings.create().strength(4f).sounds(BlockSoundGroup.WOOL)) {
                @Override
                protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
                    return null;
                }
            });

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(ViridisCore.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(ViridisCore.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        ViridisCore.LOGGER.info("Registering Mod Blocks for " + ViridisCore.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries ->
                entries.add(ModBlocks.METE_PLUSH));
    }
}