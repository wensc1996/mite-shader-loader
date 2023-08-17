package net.wenscHuix.mitemod.shader.mixin.render.texture;


import net.minecraft.*;
import net.wenscHuix.mitemod.shader.client.Shaders;
import net.wenscHuix.mitemod.shader.client.ShadersTex;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

@Mixin(bie.class)
public class LayeredTextureMixin extends bia {

    @Shadow
    @Final
    public List b;

//    @Redirect(locals = LocalCapture.CAPTURE_FAILHARD, at = @At(value = "INVOKE", target = "Lnet/minecraft/bip;a(ILjava/awt/image/BufferedImage;)I")
//            , method = "a")
//    private int redirectLoadTexture(int var0, BufferedImage var1, bjp par1ResourceManager){
//        if(Shaders.isActiveShader) {
//            ShadersTex.loadLayeredTexture(ReflectHelper.dyCast(this), par1ResourceManager, this.b);
//        } else {
//            return bip.a(var0, var1);
//        }
//    }

    @Overwrite
    public void a(bjp var1) {
        BufferedImage var2 = null;

        try {
            Iterator var3 = this.b.iterator();

            while(var3.hasNext()) {
                String var4 = (String)var3.next();
                if (var4 != null) {
                    InputStream var5 = var1.a(new bjo(var4)).b();
                    BufferedImage var6 = ImageIO.read(var5);
                    if (var2 == null) {
                        var2 = new BufferedImage(var6.getWidth(), var6.getHeight(), 2);
                    }

                    var2.getGraphics().drawImage(var6, 0, 0, (ImageObserver)null);
                }
            }
        } catch (IOException var7) {
            var7.printStackTrace();
            return;
        }

        if(Shaders.isActiveShader) {
            ShadersTex.loadLayeredTexture(ReflectHelper.dyCast(this), var1, this.b);
        } else {
            bip.a(this.b(), var2);
        }
    }
}
