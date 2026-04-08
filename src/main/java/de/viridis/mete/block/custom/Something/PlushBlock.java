package de.viridis.mete.block.custom.Something;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public interface PlushBlock {
    boolean canEntitySpawn(BlockState state,
                           BlockView world,
                           BlockPos pos,
                           EntityType<?> entityType);

    ActionResult use(BlockState state, World world, BlockPos pos,
                     PlayerEntity player, BlockHitResult hit);
}
