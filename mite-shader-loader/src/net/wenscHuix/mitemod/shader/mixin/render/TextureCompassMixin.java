package net.wenscHuix.mitemod.shader.mixin.render;

import net.minecraft.bil;
import net.minecraft.bip;
import net.minecraft.bit;
import net.wenscHuix.mitemod.shader.client.Shaders;
import net.wenscHuix.mitemod.shader.client.ShadersTex;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(bit.class)
public class TextureCompassMixin extends bil {
    protected TextureCompassMixin(String par1Str) {
        super(par1Str);
    }

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/bip;a([IIIIIZZ)V"), method = "a")
    private void redirectUpdateCompass(int[] var0, int var1, int var2, int var3, int var4, boolean var5, boolean var6) {
        if(Shaders.isActiveShader) {
            ShadersTex.updateSubImage((int[])this.a.get(this.g), this.e, this.f, this.c, this.d, false, false);
        } else {
            bip.a((int[])this.a.get(this.g), this.e, this.f, this.c, this.d, false, false);
        }
    }

}
