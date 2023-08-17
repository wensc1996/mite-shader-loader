package net.wenscHuix.mitemod.shader.util.fix;


import net.minecraft.EntityRenderer;
import net.minecraft.Minecraft;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.concurrent.Callable;

public class CallableScreenNameFix implements Callable {
    private final EntityRenderer entityRender;

    public CallableScreenNameFix(EntityRenderer par1EntityRenderer) {
        this.entityRender = par1EntityRenderer;
    }

    @Override
    public Object call() {
        return entityRender;
    }
}
