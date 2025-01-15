package dev.isxander.debugify.mixins.basic.mc88371;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.isxander.debugify.fixes.BugFix;
import dev.isxander.debugify.fixes.FixCategory;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.boss.enderdragon.phases.DragonLandingPhase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@BugFix(id = "MC-88371", category = FixCategory.BASIC, env = BugFix.Env.SERVER)
@Mixin(DragonLandingPhase.class)
public class DragonLandingPhaseMixin {
    @ModifyExpressionValue(method = "doServerTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;getHeightmapPos(Lnet/minecraft/world/level/levelgen/Heightmap$Types;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/core/BlockPos;"))
    private BlockPos getLandingPos(BlockPos pos) {
        if (pos.getY() == 0) {
            // average height of portal
            return pos.atY(65);
        }
        return pos;
    }
}
