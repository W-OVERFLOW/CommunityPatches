package cc.woverflow.debugify.mixins.basic.server.mc129909;

import cc.woverflow.debugify.fixes.BugFix;
import cc.woverflow.debugify.fixes.FixCategory;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@BugFix(id = "MC-129909", category = FixCategory.BASIC, env = BugFix.Env.SERVER)
@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {
    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile profile) {
        super(world, pos, yaw, profile);
    }

    /**
     * Also fixes
     * MC-81773, MC-206705
     */
    @Inject(method = "changeGameMode", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;stopRiding()V"))
    private void onChangeToSpectator(GameMode gameMode, CallbackInfoReturnable<Boolean> cir) {
        stopUsingItem();
    }
}
