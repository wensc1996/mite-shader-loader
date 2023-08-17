package net.wenscHuix.mitemod.shader.mixin.render;

import net.minecraft.*;
import net.wenscHuix.mitemod.shader.client.Shaders;
import net.wenscHuix.mitemod.shader.client.ShadersTex;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.awt.image.BufferedImage;

@Mixin(bif.class)
public class SimpleTextureMixin extends bia {
    @Shadow
    @Final
    private bjo b;

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/bip;a(ILjava/awt/image/BufferedImage;ZZ)I"), method = "a")
    private int redirectRenderLivingLabel0(int var0, BufferedImage var1, boolean var2, boolean var3, bjp par1ResourceManager) {
        return 0;
//        if(Shaders.isActiveShader) {
//            return ShadersTex.loadSimpleTexture(var0, var1, var2, var3, par1ResourceManager, this.b, this.getMultiTexID());
//        } else {
//            return bip.a(var0, var1, var2, var3);
//        }
    }

    @Shadow
    public void a(bjp bjp) {

    }
}
