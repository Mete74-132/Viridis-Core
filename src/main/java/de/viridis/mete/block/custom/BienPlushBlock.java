package de.viridis.mete.block.custom;

import de.viridis.mete.sound.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class BienPlushBlock extends Block {

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

    public BienPlushBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player,
                                 BlockHitResult hit) {
        world.playSound(player, pos, ModSounds.BIEN_PLUSH, SoundCategory.BLOCKS, 0.5f, 1f);
        return ActionResult.SUCCESS;
    }

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
}
