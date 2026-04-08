package de.viridis.mete.block.Interface;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public interface PlushBlock {
    boolean canEntitySpawn(BlockState state, BlockView world,
                           BlockPos pos, EntityType<?> entityType);

    void scheduledTick(BlockState state, ServerWorld world,
                       BlockPos pos, java.util.Random random);
}
