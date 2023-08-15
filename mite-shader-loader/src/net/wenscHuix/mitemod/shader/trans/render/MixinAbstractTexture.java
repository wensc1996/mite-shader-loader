package net.wenscHuix.mitemod.shader.trans.render;

import net.minecraft.bia;
import net.wenscHuix.mitemod.shader.client.MultiTexID;
import net.wenscHuix.mitemod.shader.client.ShadersTex;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(bia.class)
public class MixinAbstractTexture {
    public MultiTexID multiTex;
    public MultiTexID getMultiTexID() {
        return ShadersTex.getMultiTexID(this);
    }
}
