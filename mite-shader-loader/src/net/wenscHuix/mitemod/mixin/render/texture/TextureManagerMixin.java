package net.wenscHuix.mitemod.mixin.render.texture;


import net.minecraft.bif;
import net.minecraft.bim;
import net.minecraft.bio;
import net.minecraft.bjo;
import net.wenscHuix.mitemod.shader.client.ShadersTex;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

@Mixin(bim.class)
public class TextureManagerMixin {

    @Overwrite
    public void a(bjo par1ResourceLocation) {
        Object var2 = this.a.get(par1ResourceLocation);
        if (var2 == null) {
            var2 = new bif(par1ResourceLocation);
            this.a(par1ResourceLocation, (bio) var2);
        }
//        if (Shaders.isActiveShader) {
            ShadersTex.bindTexture((bio) var2);
//        } else {
//            TextureUtilExtra.bindTexture(((bio) var2).b());
//        }
    }

    @Shadow
    @Final
    private Map a;
    @Shadow
    public boolean a(bjo par1ResourceLocation, bio par2TextureObject) {
        return false;
    }
}
