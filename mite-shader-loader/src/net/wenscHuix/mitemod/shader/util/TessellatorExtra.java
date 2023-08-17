package net.wenscHuix.mitemod.shader.util;

import net.minecraft.atu;
import net.minecraft.bfq;
import net.wenscHuix.mitemod.shader.client.ShadersTess;
import org.lwjgl.opengl.ARBVertexBufferObject;
import org.lwjgl.opengl.GLContext;

public class TessellatorExtra extends bfq {
    public static int bufferSize = 2097152;
    public TessellatorExtra(int par1){
        this.setShadersTess(par1);
    }
}
