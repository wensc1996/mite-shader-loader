package net.wenscHuix.mitemod.shader.mixin.render.texture;


import net.minecraft.bie;
import net.minecraft.bip;
import net.minecraft.bjp;
import net.wenscHuix.mitemod.shader.client.Shaders;
import net.wenscHuix.mitemod.shader.client.ShadersTex;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.awt.image.BufferedImage;
import java.util.List;

@Mixin(bie.class)
public class LayeredTextureMixin {

    @Shadow
    @Final
    public List b;

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/bip;a(ILjava/awt/image/BufferedImage;)I"), method = "a")
    private int redirectLoadTexture(int var0, BufferedImage var1, bjp par1ResourceManager){
        if(Shaders.isActiveShader) {
            ShadersTex.loadLayeredTexture(ReflectHelper.dyCast(this), par1ResourceManager, this.b);
        }
        return bip.a(var0, var1);
    }
}
