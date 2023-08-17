package net.wenscHuix.mitemod.shader.mixin.render;


import net.minecraft.bfq;
import net.wenscHuix.mitemod.shader.client.Shaders;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.nio.IntBuffer;

@Mixin(bfq.class)
public class TessellatorMixin {

    @Shadow
    public boolean q;
    @Shadow
    public int y;
    public float normalX;
    public float normalY;
    public float normalZ;

    public float midTextureU;
    public float midTextureV;
    public float[] vertexPos;
    @Overwrite
    public final void b(float par1, float par2, float par3) {
        if(Shaders.isActiveShader) {
            this.q = true;
            this.normalX = par1;
            this.normalY = par2;
            this.normalZ = par3;
        } else {
            this.q = true;
            byte var4 = (byte)((int)(par1 * 127.0F));
            byte var5 = (byte)((int)(par2 * 127.0F));
            byte var6 = (byte)((int)(par3 * 127.0F));
            this.y = var4 & 255 | (var5 & 255) << 8 | (var6 & 255) << 16;
        }
    }
}
