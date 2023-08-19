package net.wenscHuix.mitemod.mixin.render;

import net.minecraft.bma;
import net.wenscHuix.mitemod.shader.util.OpenGlHelperExtra;
import org.lwjgl.opengl.ARBMultitexture;
import org.lwjgl.opengl.GL13;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(bma.class)
public class OpenGlHelperMixin {

    @Shadow
    private static boolean c;

    @Overwrite
    public static void a(int par0) {
        OpenGlHelperExtra.activeTexUnit = par0;
        if (c) {
            ARBMultitexture.glActiveTextureARB(par0);
        } else {
            GL13.glActiveTexture(par0);
        }
    }

}
