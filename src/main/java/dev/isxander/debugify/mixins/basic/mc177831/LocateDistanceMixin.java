package dev.isxander.debugify.mixins.basic.mc177831;

import dev.isxander.debugify.fixes.BugFix;
import dev.isxander.debugify.fixes.FixCategory;
import net.minecraft.server.commands.LocateCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@BugFix(id = "MC-177831", category = FixCategory.BASIC, env = BugFix.Env.SERVER)
@Mixin(LocateCommand.class)
public class LocateDistanceMixin {
    /**
     * @author MaxenceDC
     * @reason Fix MC-177831
     */
    @Overwrite
    private static float dist(int pos1x, int pos1z, int pos2x, int pos2z) {
        double delta_x = pos2x - pos1x;
        double delta_y = pos2z - pos1z;
        return (float) Math.hypot(delta_x, delta_y); // Math.hypot accounts for integer overflow, with error upto 1ulp
    }
}
