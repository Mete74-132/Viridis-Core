package de.viridis.mete.block;

import de.viridis.mete.block.custom.Something.PlushBlock;
import de.viridis.mete.sound.ModSounds;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
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
import net.minecraft.entity.EntityType;

/**
 * Base class for every plush block.
 *
 * <ul>
 *   <li>Directional VoxelShape (north/east/south/west).</li>
 *   <li>Solid collision → blocks players, mobs, items, projectiles, etc.</li>
 *   <li>Prevents natural mob spawns inside the block.</li>
 *   <li>Plays a custom sound when the player right‑clicks the block.</li>
 * </ul>
 */
public abstract class AbstractPlushBlock extends Block implements PlushBlock {

    /* --------------------------------------------------------------------- */
    /*  SHAPES – the same for all plush blocks                               */
    /* --------------------------------------------------------------------- */
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

    protected AbstractPlushBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    /* --------------------------------------------------------------------- */
    /*  ROTATION (facing)                                                   */
    /* --------------------------------------------------------------------- */
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        // The plush should face the player, therefore we store the *opposite* direction.
        return this.getDefaultState()
                .with(Properties.HORIZONTAL_FACING,
                        ctx.getHorizontalPlayerFacing().getOpposite());
    }

    /* --------------------------------------------------------------------- */
    /*  SHAPE HELPERS                                                       */
    /* --------------------------------------------------------------------- */
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
        // Collision shape = outline → solid obstacle.
        return shapeFor(state.get(Properties.HORIZONTAL_FACING));
    }

    /* --------------------------------------------------------------------- */
    /*  SPAWN BLOCKING (explicit)                                           */
    /* --------------------------------------------------------------------- */
    /** Prevent natural mob spawns from appearing inside the block. */
    @Override
    public boolean canEntitySpawn(BlockState state,
                                  BlockView world,
                                  BlockPos pos,
                                  EntityType<?> entityType) {
        return false;
    }

    /* --------------------------------------------------------------------- */
    /*  RIGHT‑CLICK – subclasses supply the specific SoundEvent               */
    /* --------------------------------------------------------------------- */
    /**
     * @return the {@link SoundEvent} that should be played when a player
     *         right‑clicks this plush block.
     */
    protected abstract SoundEvent getPlushSound();

    @Override
    public ActionResult use(BlockState state, World world, BlockPos pos,
                            PlayerEntity player, BlockHitResult hit) {
        // **This is the important part that plays the sound.**
        // It runs only on the server side; the client then receives the sound automatically.
        if (!world.isClient) {
            world.playSound(
                    null,                       // who hears it? null = all nearby players
                    pos,
                    this.getPlushSound(),        // sound supplied by the concrete subclass
                    SoundCategory.BLOCKS,
                    0.5f,                       // volume (0.0‑1.0 is fine)
                    1.0f);                      // pitch
        }
        return ActionResult.SUCCESS;
    }
}
