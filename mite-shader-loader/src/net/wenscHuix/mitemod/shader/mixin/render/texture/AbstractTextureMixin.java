package net.wenscHuix.mitemod.shader.mixin.render.texture;

import net.minecraft.bia;
import net.wenscHuix.mitemod.shader.client.MultiTexID;
import net.wenscHuix.mitemod.shader.client.ShadersTex;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(bia.class)
public class AbstractTextureMixin {
    public MultiTexID multiTex;
    public MultiTexID getMultiTexID() {
        return ShadersTex.getMultiTexID(ReflectHelper.dyCast(this));
    }
}
