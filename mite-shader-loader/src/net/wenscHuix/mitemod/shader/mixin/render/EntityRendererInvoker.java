package net.wenscHuix.mitemod.shader.mixin.render;

import net.minecraft.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(EntityRenderer.class)
public interface EntityRendererInvoker {
    @Invoker("a")
    void renderWorld(float par1, long par2);
}
