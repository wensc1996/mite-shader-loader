package net.wenscHuix.mitemod.shader.client;


import net.minecraft.bia;
import net.minecraft.bjp;

public class DefaultTexture extends bia {
    public DefaultTexture() {
        this.loadTexture((bjp)null);
    }

    public void loadTexture(bjp resourcemanager) {
        int[] aint = ShadersTex.createAIntImage(1, -1);
        //ShadersTex.setupTexture(this.getMultiTexID(), aint, 1, 1, false, false);
    }

    @Override
    public void a(bjp bjp) {

    }
}
