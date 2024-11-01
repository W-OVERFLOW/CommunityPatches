package dev.isxander.debugify.client.mixins.basic.mc108948;

import dev.isxander.debugify.fixes.BugFix;
import dev.isxander.debugify.fixes.FixCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.AbstractBoat;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.Level;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@BugFix(id = "MC-108948", category = FixCategory.BASIC, env = BugFix.Env.CLIENT)
@Mixin(AbstractBoat.class)
public abstract class BoatMixin extends Entity {
    public BoatMixin(EntityType<?> type, Level world) {
        super(type, world);
    }

    /**
     * when the boat is being handled by client, and the player isn't controlling it,
     * it's movement is not calculated. creating de-sync between client and server.
     */
    @ModifyExpressionValue(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/vehicle/AbstractBoat;isControlledByLocalInstance()Z"))
    private boolean shouldUpdateVelocity(boolean isLogicalSideForUpdatingMovement) {
        return true;
    }

    /**
     * still limit packets and stuff like that if not controlled because
     * there is no need to send packets if the player isn't controlling it.
     */
    @ModifyExpressionValue(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/Level;isClientSide:Z", ordinal = 1, opcode = Opcodes.GETFIELD))
    private boolean updatePaddles(boolean isClient) {
        return isClient && isControlledByLocalInstance();
    }
}
