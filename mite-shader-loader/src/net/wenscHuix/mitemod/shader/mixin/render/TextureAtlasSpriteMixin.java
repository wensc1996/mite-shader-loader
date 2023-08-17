package net.wenscHuix.mitemod.shader.mixin.render;


import net.minecraft.bil;
import net.minecraft.bip;
import net.minecraft.bjp;
import net.wenscHuix.mitemod.shader.client.Shaders;
import net.wenscHuix.mitemod.shader.client.ShadersTex;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.awt.image.BufferedImage;
import java.util.List;

@Mixin(bil.class)
public class TextureAtlasSpriteMixin {

    @Shadow
    protected List a;
    @Shadow
    protected boolean b;
    @Shadow
    protected int c;
    @Shadow
    protected int d;
    @Shadow
    protected int e;
    @Shadow
    protected int f;

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/bip;a([IIIIIZZ)V"), method = "j")
    private void redirectRenderLivingLabel0(int[] var0, int var1, int var2, int var3, int var4, boolean var5, boolean var6) {
        if(Shaders.isActiveShader) {
            ShadersTex.updateSubImage(((int[])this.a.get(var3)), this.e, this.f, this.c, this.d, false, false);
        } else {
            bip.a(((int[])this.a.get(var3)), this.e, this.f, this.c, this.d, false, false);
        }
    }

    @Inject(locals = LocalCapture.PRINT, at = @At(value = "INVOKE", target = "Ljava/awt/image/BufferedImage;getRGB(IIII[III)[I", shift = At.Shift.BEFORE),
            method = "a(Lnet/minecraft/bjn;)V")
    private void injectLoadSprite0(CallbackInfo callbackInfo){
        if(Shaders.isActiveShader) {
            ShadersTex.loadAtlasSprite(var4, 0, 0, this.width, this.height, var5, 0, this.width);
        }
    }


}
