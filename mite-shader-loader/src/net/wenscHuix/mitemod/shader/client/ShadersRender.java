package net.wenscHuix.mitemod.shader.client;


import net.minecraft.bfj;
import net.minecraft.bfl;
import net.minecraft.bfv;
import org.lwjgl.opengl.GL11;

public class ShadersRender {
    public ShadersRender() {
    }

    public static void setFrustrumPosition(bfv frustrum, double x, double y, double z) {
        frustrum.setPosition(x, y, z);
    }

    public static void clipRenderersByFrustrum(bfl renderGlobal, bfv frustrum, float par2) {
        Shaders.checkGLError("pre clip");
        if (!Shaders.isShadowPass || Shaders.configShadowClipFrustrum) {
            renderGlobal.clipRenderersByFrustum(frustrum, par2);
            Shaders.checkGLError("clip");
        }

    }

    public static void renderItemFP(bfj itemRenderer, float par1) {
        GL11.glDepthFunc(518);
        GL11.glPushMatrix();
        itemRenderer.renderItemInFirstPerson(par1);
        GL11.glPopMatrix();
        GL11.glDepthFunc(515);
        itemRenderer.renderItemInFirstPerson(par1);
    }
}
