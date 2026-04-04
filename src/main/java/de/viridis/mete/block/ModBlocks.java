package de.viridis.mete.block;

import de.viridis.mete.ViridisCore;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.world.BlockView;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class ModBlocks {

    // Expanded outline (added 0.5 pixels on each side = 0.03125 normalized)
    // Original: [3.5, 0, 5] to [12.5, 15, 14]
    // Expanded: [3, 0, 4.5] to [13, 15, 14.5]
    private static final VoxelShape SHAPE_NORTH = VoxelShapes.cuboid(
            3f/16, 0f/16, 4.5f/16,
            13f/16, 15.5f/16, 14.5f/16
    );

    private static final VoxelShape SHAPE_SOUTH = VoxelShapes.cuboid(
            3f/16, 0f/16, 1.5f/16,
            13f/16, 15.5f/16, 11.5f/16
    );

    private static final VoxelShape SHAPE_EAST = VoxelShapes.cuboid(
            1.5f/16, 0f/16, 3f/16,
            11.5f/16, 15.5f/16, 13f/16
    );

    private static final VoxelShape SHAPE_WEST = VoxelShapes.cuboid(
            4.5f/16, 0f/16, 3f/16,
            14.5f/16, 15.5f/16, 13f/16
    );

    public static final Block METE_PLUSH = registerBlock("mete_plush",
            new Block(AbstractBlock.Settings.create().strength(4f).sounds(BlockSoundGroup.WOOL)) {
                @Override
                protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
                    builder.add(Properties.HORIZONTAL_FACING);
                }

                @Override
                public BlockState getPlacementState(ItemPlacementContext ctx) {
                    return this.getDefaultState().with(Properties.HORIZONTAL_FACING, ctx.getHorizontalPlayerFacing().getOpposite());
                }

                @Override
                public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
                    return getShapeForDirection(state.get(Properties.HORIZONTAL_FACING));
                }

                @Override
                public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
                    return getOutlineShape(state, world, pos, context);
                }

                private VoxelShape getShapeForDirection(Direction direction) {
                    return switch (direction) {
                        case SOUTH -> SHAPE_SOUTH;
                        case EAST -> SHAPE_EAST;
                        case WEST -> SHAPE_WEST;
                        default -> SHAPE_NORTH;
                    };
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