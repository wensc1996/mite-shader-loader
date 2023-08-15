package net.wenscHuix.mitemod.shader.util;

import net.minecraft.EntityRenderer;
import net.minecraft.awf;

import java.util.concurrent.Callable;

public class CallableScreenSizeFix implements Callable {
    final awf theScaledResolution;

    final EntityRenderer theEntityRenderer;

    public CallableScreenSizeFix(EntityRenderer par1EntityRenderer, awf par2ScaledResolution) {
        this.theEntityRenderer = par1EntityRenderer;
        this.theScaledResolution = par2ScaledResolution;
    }

    @Override
    public Object call() {
        return String.format("Scaled: (%d, %d). Absolute: (%d, %d). Scale factor of %d", this.theScaledResolution.a(), this.theScaledResolution.b(),
                "null", "null", this.theScaledResolution.e());
    }
}
