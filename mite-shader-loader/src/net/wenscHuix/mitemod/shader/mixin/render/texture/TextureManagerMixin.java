package net.wenscHuix.mitemod.shader.mixin.render.texture;


import net.minecraft.*;
import net.wenscHuix.mitemod.shader.client.Shaders;
import net.wenscHuix.mitemod.shader.client.ShadersTex;
import net.wenscHuix.mitemod.shader.util.TextureUtilExtra;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Map;

@Mixin(bim.class)
public class TextureManagerMixin {

//    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/bio;b()I"), method = "a(Lnet/minecraft/bjo;)V")
//    private void redirectUpdateCompass(int var0) {
//        if(Shaders.isActiveShader) {
//            ShadersTex.bindTexture((TextureObject)var2);
//        } else {
//            bip.b(var0);
//        }
//    }
    @Overwrite
    public void a(bjo par1ResourceLocation) {
        Object var2 = this.a.get(par1ResourceLocation);
        if (var2 == null) {
            var2 = new bif(par1ResourceLocation);
            this.a(par1ResourceLocation, (bio) var2);
        }
        if (Shaders.isActiveShader) {
            ShadersTex.bindTexture((bio) var2);
        } else {
            TextureUtilExtra.bindTexture(((bio) var2).b());
        }
    }

    @Shadow
    @Final
    private Map a;
    @Shadow
    public boolean a(bjo par1ResourceLocation, bio par2TextureObject) {
        return false;
    }
}
