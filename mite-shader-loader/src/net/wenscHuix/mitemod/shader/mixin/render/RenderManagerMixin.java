package net.wenscHuix.mitemod.shader.mixin.render;


import net.minecraft.avi;
import net.minecraft.bgl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

@Mixin(bgl.class)
public class RenderManagerMixin {
    @Shadow
    private avi r;
    @Shadow
    private Map q;

    public avi getFontRenderer() {
        return r;
    }

    public Map getEntityRenderMap() {
        return q;
    }
}
