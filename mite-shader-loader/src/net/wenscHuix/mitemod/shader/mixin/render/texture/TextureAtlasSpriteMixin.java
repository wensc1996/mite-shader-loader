package net.wenscHuix.mitemod.shader.mixin.render.texture;


import com.google.common.collect.Lists;
import net.minecraft.*;
import net.wenscHuix.mitemod.shader.client.Shaders;
import net.wenscHuix.mitemod.shader.client.ShadersTex;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.spongepowered.asm.mixin.injection.modify.LocalVariableDiscriminator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Mixin(bil.class)
public class TextureAtlasSpriteMixin {

    public IntBuffer[] frameBuffers;

    public boolean mipmapActive = false;

//    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/bip;a([IIIIIZZ)V"), method = "j")
//    private void redirectRenderLivingLabel0(int[] var0, int var1, int var2, int var3, int var4, boolean var5, boolean var6) {
//        if(Shaders.isActiveShader) {
//            ShadersTex.updateSubImage(((int[])this.a.get(var3)), this.e, this.f, this.c, this.d, false, false);
//        } else {
//            bip.a(((int[])this.a.get(var3)), this.e, this.f, this.c, this.d, false, false);
//        }
//    }


    public int getC() {
        return c;
    }

    public int getD() {
        return d;
    }

    @Overwrite
    public void j() {
        ++this.h;
        if (this.h >= this.j.a(this.g)) {
            int var1 = this.j.c(this.g);
            int var2 = this.j.c() == 0 ? this.a.size() : this.j.c();
            this.g = (this.g + 1) % var2;
            this.h = 0;
            int var3 = this.j.c(this.g);
            if (var1 != var3 && var3 >= 0 && var3 < this.a.size()) {
                if(Shaders.isActiveShader) {
                    ShadersTex.updateSubImage(((int[])this.a.get(var3)), this.e, this.f, this.c, this.d, false, false);
                } else {
                    bip.a(((int[])this.a.get(var3)), this.e, this.f, this.c, this.d, false, false);
                }
            }
        }

    }

//    @Inject(locals = LocalCapture.CAPTURE_FAILHARD, at = @At(value = "INVOKE", target = "Ljava/awt/image/BufferedImage;getRGB(IIII[III)[I", shift = At.Shift.BEFORE),
//            method = "a(Lnet/minecraft/bjn;)V")
//    private void injectLoadSprite0(CallbackInfo callbackInfo, BufferedImage var4, int[] var5){
//        if(Shaders.isActiveShader) {
//            ShadersTex.loadAtlasSprite(var4, 0, 0,this.e, this.f, var5, 0, this.e);
//        }
//    }

    public void uploadFrameMipmaps(int frameIndex, int xPos, int yPos) {


    }

    @Overwrite
    public final void a(bjn par1Resource) throws IOException {
        this.n();
        InputStream var2 = par1Resource.b();
        bko var3 = (bko)par1Resource.a("animation");
        BufferedImage var4 = ImageIO.read(var2);
        this.f = var4.getHeight();
        this.e = var4.getWidth();
        int[] var5 = new int[this.f * this.e * 3];
        if(Shaders.isActiveShader) {
            ShadersTex.loadAtlasSprite(var4, 0, 0,  this.e, this.f, var5, 0, this.e);
        }
        var4.getRGB(0, 0, this.e, this.f, var5, 0, this.e);
        if (var3 == null) {
            if (this.f != this.e) {
                throw new RuntimeException("broken aspect ratio and not an animation");
            }

            this.a.add(var5);
        } else {
            int var6 = this.f / this.e;
            int var7 = this.e;
            int var8 = this.e;
            this.f = this.e;
            int var10;
            if (var3.c() > 0) {

                for (Object o : var3.e()) {
                    var10 = (Integer) o;
                    if (var10 >= var6) {
                        throw new RuntimeException("invalid frameindex " + var10);
                    }

                    this.d(var10);
                    if(Shaders.isActiveShader) {
                        this.a.set(var10, ShadersTex.extractFrame(var5, var7, var8, var10));
                    } else{
                        this.a.set(var10, a(var5, var7, var8, var10));
                    }
                }

                this.j = var3;
            } else {
                ArrayList var11 = Lists.newArrayList();

                for(var10 = 0; var10 < var6; ++var10) {
                    if(Shaders.isActiveShader) {
                        this.a.add(ShadersTex.extractFrame(var5, var7, var8, var10));
                    } else {
                        this.a.add(a(var5, var7, var8, var10));
                    }
                    var11.add(new bkn(var10, -1));
                }

                this.j = new bko(var11, this.e, this.f, var3.d());
            }
        }
    }

    public boolean load(bjp manager, bjo location) throws IOException {
        this.a(ShadersTex.loadResource(manager, location));
        return true;
    }


    @Shadow
    private bko j;
    @Shadow
    protected List a;
    @Shadow
    protected boolean b;
    @Shadow
    protected int c;
    @Shadow
    protected int g;
    @Shadow
    protected int h;
    @Shadow
    protected int d;
    @Shadow
    protected int e;
    @Shadow
    protected int f;
    @Shadow
    @Final
    private void n() {

    }
    @Shadow
    @Final
    private void d(int par1) {

    }
    @Shadow
    @Final
    private static int[] a(int[] par0ArrayOfInteger, int par1, int par2, int par3) {
        return null;
    }

}