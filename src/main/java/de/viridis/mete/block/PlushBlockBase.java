package de.viridis.mete.block;

import de.viridis.mete.block.Interface.PlushBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
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

public class PlushBlockBase extends Block implements PlushBlock {

    private static final VoxelShape SHAPE_NORTH = VoxelShapes.cuboid(
            3f / 16, 0f / 16, 4.5f / 16,
            13f / 16, 15.5f / 16, 14.5f / 16);
    private static final VoxelShape SHAPE_SOUTH = VoxelShapes.cuboid(
            3f / 16, 0f / 16, 1.5f / 16,
            13f / 16, 15.5f / 16, 11.5f / 16);
    private static final VoxelShape SHAPE_EAST = VoxelShapes.cuboid(
            1.5f / 16, 0f / 16, 3f / 16,
            11.5f / 16, 15.5f / 16, 13f / 16);
    private static final VoxelShape SHAPE_WEST = VoxelShapes.cuboid(
            4.5f / 16, 0f / 16, 3f / 16,
            14.5f / 16, 15.5f / 16, 13f / 16);

    private final SoundEvent plushSound;

    public PlushBlockBase(AbstractBlock.Settings settings, SoundEvent plushSound) {
        super(settings);
        this.plushSound = plushSound;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(Properties.HORIZONTAL_FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    private VoxelShape shapeFor(Direction dir) {
        return switch (dir) {
            case SOUTH -> SHAPE_SOUTH;
            case EAST  -> SHAPE_EAST;
            case WEST  -> SHAPE_WEST;
            default    -> SHAPE_NORTH;
        };
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world,
                                      BlockPos pos, ShapeContext ctx) {
        return shapeFor(state.get(Properties.HORIZONTAL_FACING));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world,
                                        BlockPos pos, ShapeContext ctx) {
        return shapeFor(state.get(Properties.HORIZONTAL_FACING));
    }

    @Override
    public boolean canEntitySpawn(BlockState state, BlockView world,
                                  BlockPos pos, EntityType<?> entityType) {
        return false;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient) {
            world.playSound(
                    null,
                    pos,
                    this.plushSound,
                    SoundCategory.BLOCKS,
                    0.5f,
                    1.0f);
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world,
                              BlockPos pos, java.util.Random random) {
        // No default behavior.
    }
}
