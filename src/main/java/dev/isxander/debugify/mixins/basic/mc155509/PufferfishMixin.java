package dev.isxander.debugify.mixins.basic.mc155509;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.isxander.debugify.fixes.BugFix;
import dev.isxander.debugify.fixes.FixCategory;
import net.minecraft.world.entity.animal.Pufferfish;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@BugFix(id = "MC-155509", category = FixCategory.BASIC, env = BugFix.Env.SERVER)
@Mixin(Pufferfish.class)
public class PufferfishMixin {
    @ModifyExpressionValue(method = "playerTouch", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;hurtServer(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)Z"))
    private boolean shouldStingPlayer(boolean damaged) {
        return damaged && ((Pufferfish)(Object) this).isAlive();
    }
}
