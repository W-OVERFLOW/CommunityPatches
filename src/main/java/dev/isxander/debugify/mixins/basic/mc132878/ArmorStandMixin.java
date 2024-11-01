package dev.isxander.debugify.mixins.basic.mc132878;

import dev.isxander.debugify.fixes.BugFix;
import dev.isxander.debugify.fixes.FixCategory;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.decoration.ArmorStand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@BugFix(id = "MC-132878", category = FixCategory.BASIC, env = BugFix.Env.SERVER)
@Mixin(ArmorStand.class)
public abstract class ArmorStandMixin {
    @Shadow protected abstract void showBreakingParticles();

    @Inject(
            method = "hurtServer",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/decoration/ArmorStand;brokenByAnything(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;)V",
                    ordinal = 0
            )
    )
    private void breakParticlesExplosion(ServerLevel serverLevel, DamageSource damageSource, float f, CallbackInfoReturnable<Boolean> cir) {
        this.showBreakingParticles();
    }

    @Inject(
            method = "causeDamage",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/decoration/ArmorStand;brokenByAnything(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;)V",
                    ordinal = 0
            )
    )
    private void breakParticlesFire(CallbackInfo ci) {
        this.showBreakingParticles();
    }
}
