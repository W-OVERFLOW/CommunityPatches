package dev.isxander.debugify.client.mixins.basic.mc111516;

import dev.isxander.debugify.fixes.BugFix;
import dev.isxander.debugify.fixes.FixCategory;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

/**
 * Taken from <a href="https://github.com/TheRandomLabs/RandomPatches">RandomPatches</a>
 * under MIT license
 * <br>
 * Adapted to work in newer versions and a multi-loader environment and have better mixin compatibility
 *
 * @author TheRandomLabs
 */
@BugFix(id = "MC-111516", category = FixCategory.BASIC, env = BugFix.Env.CLIENT)
@Mixin(PlayerRenderer.class)
public final class PlayerRendererMixin {
    @ModifyArg(method = "extractFlightData", at = @At(value = "INVOKE", target = "java/lang/Math.acos(D)D"))
    private static double clampAcos(double a) {
        return Math.min(a, 1.0);
    }
}
