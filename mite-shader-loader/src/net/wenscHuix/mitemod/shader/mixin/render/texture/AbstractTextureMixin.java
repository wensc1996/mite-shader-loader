package net.wenscHuix.mitemod.shader.mixin.render.texture;

import net.minecraft.bia;
import net.wenscHuix.mitemod.shader.client.MultiTexID;
import net.wenscHuix.mitemod.shader.client.ShadersTex;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(bia.class)
public class AbstractTextureMixin {
    @Shadow
    protected int a;

    public int getGlTextureId() {
        return a;
    }

    public void setGlTextureId(int a) {
        this.a = a;
    }

    public MultiTexID multiTex;
    public MultiTexID getMultiTexID() {
        return ShadersTex.getMultiTexID(ReflectHelper.dyCast(this));
    }
}
