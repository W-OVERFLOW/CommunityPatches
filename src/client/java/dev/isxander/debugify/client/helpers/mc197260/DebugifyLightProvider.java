package dev.isxander.debugify.client.helpers.mc197260;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public interface DebugifyLightProvider<S extends LivingEntityRenderState> {
    default int modifyLightLevel(int lightLevel, S livingEntity, PoseStack matrixStack, MultiBufferSource vertexConsumerProvider) {
        return lightLevel;
    }
}
