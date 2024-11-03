package dev.isxander.debugify.client.mixins.basic.mc122477;

import com.mojang.blaze3d.TracyFrameCapture;
import dev.isxander.debugify.fixes.BugFix;
import dev.isxander.debugify.fixes.FixCategory;
import dev.isxander.debugify.client.helpers.mc122477.KeyboardPollCounter;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.isxander.debugify.fixes.OS;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@BugFix(id = "MC-122477", category = FixCategory.BASIC, env = BugFix.Env.CLIENT, os = OS.LINUX)
@Mixin(value = RenderSystem.class, priority = 1100, remap = false) // higher priority to improve compatibility with VulkanMod
public class RenderSystemMixin {
    @Inject(method = "flipFrame", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;pollEvents()V"))
    private static void onPollEvents(long l, TracyFrameCapture tracyFrameCapture, CallbackInfo ci) {
        KeyboardPollCounter.poll();
    }
}
