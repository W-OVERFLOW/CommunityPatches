package dev.isxander.debugify.client.mixins.basic.mc227169;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.isxander.debugify.fixes.BugFix;
import dev.isxander.debugify.fixes.FixCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.minecraft.world.InteractionHand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@BugFix(id = "MC-227169", category = FixCategory.BASIC, env = BugFix.Env.CLIENT)
@Mixin(PlayerRenderer.class)
public class PlayerRendererMixin {
    @ModifyExpressionValue(method = "extractHandState", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/CrossbowItem;isCharged(Lnet/minecraft/world/item/ItemStack;)Z"))
    private boolean shouldUseCrossbowPose(boolean crossbowCharged, AbstractClientPlayer player, PlayerRenderState.HandState handState, InteractionHand hand) {
        Minecraft client = Minecraft.getInstance();
        return crossbowCharged && (hand == InteractionHand.MAIN_HAND || client.cameraEntity != player || !client.options.getCameraType().isFirstPerson());
    }
}
