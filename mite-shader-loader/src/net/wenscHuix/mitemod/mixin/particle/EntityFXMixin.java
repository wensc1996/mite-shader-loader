package net.wenscHuix.mitemod.mixin.particle;

import net.minecraft.beg;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(beg.class)
public class EntityFXMixin {

    @Shadow
    double[] motionX;
    @Shadow
    double[] motionY;
    @Shadow
    double[] motionZ;

    public double[] getMotionX() {
        return motionX;
    }

    public void setMotionX(double[] motionX) {
        this.motionX = motionX;
    }

    public double[] getMotionY() {
        return motionY;
    }

    public void setMotionY(double[] motionY) {
        this.motionY = motionY;
    }

    public double[] getMotionZ() {
        return motionZ;
    }

    public void setMotionZ(double[] motionZ) {
        this.motionZ = motionZ;
    }
}
