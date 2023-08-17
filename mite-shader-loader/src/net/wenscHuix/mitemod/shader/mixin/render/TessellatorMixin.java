package net.wenscHuix.mitemod.shader.mixin.render;


import net.minecraft.atu;
import net.minecraft.bfq;
import net.wenscHuix.mitemod.shader.client.Shaders;
import net.wenscHuix.mitemod.shader.client.ShadersTess;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.lwjgl.opengl.ARBVertexBufferObject;
import org.lwjgl.opengl.GLContext;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

@Mixin(bfq.class)
public class TessellatorMixin {
    public ShadersTess shadersTess;
    public boolean defaultTexture;
    public int rawBufferSize;
    public int textureID;
    public float normalX;
    public float normalY;
    public float normalZ;
    public float midTextureU;
    public float midTextureV;
    public float[] vertexPos;

    public boolean isUseVBO() {
        return A;
    }

    public boolean setUseVBO(boolean is) {
        return A = is;
    }

    public int getVboCount() {
        return D;
    }

    public boolean tryVBO() {
        return c;
    }


    @Overwrite
    public void a(double par1, double par3, double par5) {
        ShadersTess.addVertex(ReflectHelper.dyCast(this), par1, par3, par5);
    }

    @Inject(at = @At(value = "RETURN"), method = "<init>")
    private void injectInit(CallbackInfo callbackInfo){
        this.defaultTexture = false;
        this.rawBufferSize = 0;
        this.textureID = 0;
        this.setShadersTess(65536);
    }


    public void setShadersTess(int par1){
        this.defaultTexture = false;
        this.rawBufferSize = 0;
        this.textureID = 0;
        this.d = atu.c(par1 * 4);
        this.e = this.d.asIntBuffer();
        this.f = this.d.asFloatBuffer();
        this.g = this.d.asShortBuffer();
        this.h = new int[par1];
        this.setUseVBO(tryVBO() && GLContext.getCapabilities().GL_ARB_vertex_buffer_object);
        if (this.isUseVBO()) {
            this.B = atu.f(this.getVboCount());
            ARBVertexBufferObject.glGenBuffersARB(this.B);
        }

        this.shadersTess = new ShadersTess();
        this.vertexPos = new float[par1];
    }

    @Overwrite
    public final void b(float par1, float par2, float par3) {
        if(Shaders.isActiveShader) {
            this.q = true;
            this.normalX = par1;
            this.normalY = par2;
            this.normalZ = par3;
        } else {
            this.q = true;
            byte var4 = (byte)((int)(par1 * 127.0F));
            byte var5 = (byte)((int)(par2 * 127.0F));
            byte var6 = (byte)((int)(par3 * 127.0F));
            this.y = var4 & 255 | (var5 & 255) << 8 | (var6 & 255) << 16;
        }
    }

//    public static void setBufferSize(int bufferSize) {
//        E = bufferSize;
//    }
//
//    @Shadow
//    @Final
//    @Mutable
//    public static int E;
    @Shadow
    public IntBuffer B;
    @Shadow
    public ByteBuffer d;
    @Shadow
    public IntBuffer e;
    @Shadow
    public FloatBuffer f;
    @Shadow
    public ShortBuffer g;
    @Shadow
    public int[] h;
    @Shadow
    public int i;
    @Shadow
    public double j;
    @Shadow
    public double k;
    @Shadow
    public int l;
    @Shadow
    public int m;
    @Shadow
    public boolean q;
    @Shadow
    public int y;
    @Shadow
    private boolean A;
    @Shadow
    private int D;
    @Shadow
    private static boolean c;
}
