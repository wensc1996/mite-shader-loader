package net.wenscHuix.mitemod.mixin.render;

import net.minecraft.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EntityRenderer.class)
public interface EntityRendererAccessor {
    @Accessor("b")
    public static void setAnaglyphField(int val) {
        throw new AssertionError();
    }
    @Accessor("b")
    public static int getAnaglyphField() {
        throw new AssertionError();
    }
}
