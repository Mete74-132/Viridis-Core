package de.viridis.mete.mixin;

import de.viridis.mete.game.RoomKeys;
import dev.doctor4t.wathe.game.mapeffect.HarpyExpressTrainMapEffect;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = HarpyExpressTrainMapEffect.class, remap = false)
public class HarpyExpressTrainMapEffectMixin {
    @Inject(method = "initializeMapEffects", at = @At("HEAD"))
    private void roomkeys$scan(ServerWorld world, List<ServerPlayerEntity> players, CallbackInfo ci) {
        RoomKeys.scan(world);
    }

    @ModifyConstant(method = "initializeMapEffects", constant = @Constant(intValue = 7))
    private int roomkeys$allRooms(int original) {
        return RoomKeys.roomCount(original);
    }
}
