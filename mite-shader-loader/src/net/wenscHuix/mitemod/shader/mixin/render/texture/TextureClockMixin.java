package net.wenscHuix.mitemod.shader.mixin.render.texture;


import net.minecraft.bil;
import net.minecraft.bip;
import net.minecraft.bis;
import net.wenscHuix.mitemod.shader.client.Shaders;
import net.wenscHuix.mitemod.shader.client.ShadersTex;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(bis.class)
public class TextureClockMixin extends bil {
    protected TextureClockMixin(String par1Str) {
        super(par1Str);
    }

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/bip;a([IIIIIZZ)V"), method = "j")
    private void redirectUpdateAnimation(int[] var0, int var1, int var2, int var3, int var4, boolean var5, boolean var6) {
//        if(Shaders.isActiveShader) {
            ShadersTex.updateSubImage((int[])this.a.get(this.g), this.e, this.f, this.c, this.d,false, false);
//        } else {
//            bip.a((int[])this.a.get(this.g), this.e, this.f, this.c, this.d, false, false);
//        }
    }
}
