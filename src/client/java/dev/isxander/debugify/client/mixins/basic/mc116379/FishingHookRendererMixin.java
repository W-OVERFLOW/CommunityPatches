package dev.isxander.debugify.client.mixins.basic.mc116379;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.isxander.debugify.fixes.BugFix;
import dev.isxander.debugify.fixes.FixCategory;
import net.minecraft.client.renderer.entity.FishingHookRenderer;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@BugFix(id = "MC-116379", category = FixCategory.BASIC, env = BugFix.Env.CLIENT)
@Mixin(FishingHookRenderer.class)
public class FishingHookRendererMixin {
    @ModifyExpressionValue(method = "extractRenderState(Lnet/minecraft/world/entity/projectile/FishingHook;Lnet/minecraft/client/renderer/entity/state/FishingHookRenderState;F)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;getAttackAnim(F)F"))
    private float modifyHandSwingProgress(float handSwingProgress, FishingHook bobber) {
        Player player = bobber.getPlayerOwner();
        int j = player.getMainArm() == HumanoidArm.RIGHT ? 1 : -1;
        int j2 = j;
        ItemStack itemStack = player.getMainHandItem();
        if (!itemStack.is(Items.FISHING_ROD)) {
            j = -j;
        }

        return j == j2 ? handSwingProgress : 0;
    }
}
