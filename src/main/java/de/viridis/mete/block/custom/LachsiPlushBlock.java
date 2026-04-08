package de.viridis.mete.block.custom;

import de.viridis.mete.block.AbstractPlushBlock;
import de.viridis.mete.sound.ModSounds;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Bien plush – plays the "bien_plush" sound.
 */
public class LachsiPlushBlock extends AbstractPlushBlock {

    public LachsiPlushBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player,
                                 BlockHitResult hit) {
        world.playSound(player, pos, ModSounds.LACHSI_PLUSH, SoundCategory.BLOCKS, 0.5f, 1f);
        return ActionResult.SUCCESS;
    }

    @Override
    protected net.minecraft.sound.SoundEvent getPlushSound() {
        return ModSounds.LACHSI_PLUSH;
    }
}
