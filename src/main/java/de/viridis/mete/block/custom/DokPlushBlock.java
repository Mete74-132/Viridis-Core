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
public class DokPlushBlock extends AbstractPlushBlock {

    public DokPlushBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    //Bester Plush ever nicht wahr Mete? Jajajaja super duper

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player,
                                 BlockHitResult hit) {
        world.playSound(player, pos, ModSounds.DOK_PLUSH, SoundCategory.BLOCKS, 0.5f, 1f);
        return ActionResult.SUCCESS;
    }

    @Override
    protected net.minecraft.sound.SoundEvent getPlushSound() {
        return ModSounds.DOK_PLUSH;
    }
}
