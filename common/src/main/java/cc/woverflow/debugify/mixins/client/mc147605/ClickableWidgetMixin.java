package cc.woverflow.debugify.mixins.client.mc147605;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.widget.ClickableWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClickableWidget.class)
public class ClickableWidgetMixin {

    @Inject(method = "setFocused", at = @At("HEAD"), cancellable = true)
    private void setScreenFocus(boolean focused, CallbackInfo ci) {
        if (focused && MinecraftClient.getInstance().currentScreen != null) {
            MinecraftClient.getInstance().currentScreen.setFocused((Element) this);
            ci.cancel();
        }
    }

    @Inject(method = "isFocused", at = @At("HEAD"), cancellable = true)
    private void getScreenFocus(CallbackInfoReturnable<Boolean> cir) {
        if (MinecraftClient.getInstance().currentScreen != null) {
            cir.setReturnValue(MinecraftClient.getInstance().currentScreen.getFocused().equals(this));
        }
    }
}
