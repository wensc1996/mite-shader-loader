package net.wenscHuix.mitemod.shader.client;

import net.minecraft.src.Frustrum;
import net.minecraft.src.item.ItemRenderer;
import net.minecraft.src.render.RenderGlobal;
import org.lwjgl.opengl.GL11;

public class ShadersRender {
    public ShadersRender() {
    }

    public static void setFrustrumPosition(Frustrum frustrum, double x, double y, double z) {
        frustrum.setPosition(x, y, z);
    }

    public static void clipRenderersByFrustrum(RenderGlobal renderGlobal, Frustrum frustrum, float par2) {
        Shaders.checkGLError("pre clip");
        if (!Shaders.isShadowPass || Shaders.configShadowClipFrustrum) {
            renderGlobal.clipRenderersByFrustum(frustrum, par2);
            Shaders.checkGLError("clip");
        }

    }

    public static void renderItemFP(ItemRenderer itemRenderer, float par1) {
        GL11.glDepthFunc(518);
        GL11.glPushMatrix();
        itemRenderer.renderItemInFirstPerson(par1);
        GL11.glPopMatrix();
        GL11.glDepthFunc(515);
        itemRenderer.renderItemInFirstPerson(par1);
    }
}
