package dev.isxander.debugify.client.mixins.basic.mc215531;

import dev.isxander.debugify.fixes.BugFix;
import dev.isxander.debugify.fixes.FixCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@BugFix(id = "MC-215531", category = FixCategory.BASIC, env = BugFix.Env.CLIENT)
@Mixin(Gui.class)
public class GuiMixin {
    @Shadow @Final private Minecraft minecraft;

    @ModifyExpressionValue(method = "renderCameraOverlays", at = @At(value = "INVOKE", target = "Ljava/util/Optional;isPresent()Z"))
    private boolean shouldRenderPumpkinOverlay(boolean pumpkinOnHead) {
        return pumpkinOnHead && !minecraft.player.isSpectator();
    }
}
