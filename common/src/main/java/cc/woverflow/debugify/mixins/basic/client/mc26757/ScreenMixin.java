package cc.woverflow.debugify.mixins.basic.client.mc26757;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

// TODO: make this better as it's clearly quite scuffed
//@Mixin(Screen.class)
public class ScreenMixin {
    /* cursed field because you can't modify localcaptures */
    private int debugify$modifiedX = -1;

    //@Inject(method = "renderTooltipFromComponents", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;push()V", ordinal = 0), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void centerTooltip(MatrixStack matrices, List<TooltipComponent> components, int mouseX, int mouseY, CallbackInfo ci, int width, int height, int x) {
        if (x < 0) {
            debugify$modifiedX = mouseX - width / 2;
        }
    }

    /**
     * cursed modifyvariable because you can't modify localcaptures
     */
    //@ModifyVariable(method = "renderTooltipFromComponents", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;push()V", ordinal = 0), ordinal = 4)
    private int modifyX(int x) {
        return debugify$modifiedX;
    }
}
