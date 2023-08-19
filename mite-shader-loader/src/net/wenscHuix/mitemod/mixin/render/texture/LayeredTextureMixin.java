package net.wenscHuix.mitemod.mixin.render.texture;


import net.minecraft.*;
import net.wenscHuix.mitemod.shader.client.ShadersTex;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

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

//        if(Shaders.isActiveShader) {
            ShadersTex.loadLayeredTexture(ReflectHelper.dyCast(this), var1, this.b);
//        } else {
//            bip.a(this.b(), var2);
//        }
    }
}
