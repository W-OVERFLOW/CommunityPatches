package dev.isxander.debugify.client.mixins.basic.mc577;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.isxander.debugify.fixes.BugFix;
import dev.isxander.debugify.fixes.FixCategory;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@BugFix(id = "MC-577", category = FixCategory.BASIC, env = BugFix.Env.CLIENT)
@Mixin(AbstractContainerScreen.class)
public abstract class AbstractContainerScreenMixin extends Screen {
    @Shadow
    protected abstract void slotClicked(Slot slot, int slotId, int button, ClickType actionType);

    protected AbstractContainerScreenMixin(Component title) {
        super(title);
    }

    @ModifyExpressionValue(method = "mouseClicked", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/Screen;mouseClicked(DDI)Z"))
    private boolean shouldReturn(boolean parentMouseClicked, double mouseX, double mouseY, int button) {
        return parentMouseClicked || mouseInventoryClose(button);
    }

    @Inject(method = "mouseClicked", at = @At(value = "INVOKE", target = "Lnet/minecraft/Util;getMillis()J"), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private void dropWithMouse(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir, boolean isPickItem, Slot hoveredSlot) {
        if (minecraft.options.keyDrop.matchesMouse(button)) {
            if (hoveredSlot == null) return;
            slotClicked(hoveredSlot, hoveredSlot.index, hasControlDown() ? 1 : 0, ClickType.THROW);
            cir.setReturnValue(true);
        }
    }

    @Unique
    private boolean mouseInventoryClose(int button) {
        if (minecraft.options.keyInventory.matchesMouse(button)) {
            onClose();
            return true;
        }

        return false;
    }
}
