package net.wenscHuix.mitemod.mixin.render;

import net.minecraft.bfa;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(bfa.class)
public class WorldRendererMixin {
    public boolean isDynamicLight;
    private int c;
    private int d;
    private int e;

    public int getX() {
        return c;
    }

    public int getY() {
        return d;
    }

    public int getZ() {
        return e;
    }
}
