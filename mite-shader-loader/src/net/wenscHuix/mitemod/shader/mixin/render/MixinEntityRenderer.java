package net.wenscHuix.mitemod.shader.mixin.render;


import net.minecraft.*;
import net.minecraft.client.main.Main;
import net.wenscHuix.mitemod.shader.client.Shaders;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Project;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.nio.FloatBuffer;
import java.util.Random;

@Mixin(EntityRenderer.class)
public class MixinEntityRenderer {
//    @Redirect(at = @At(value = "INVOKE", target = "Lorg/lwjgl/util/glu/Project;gluPerspective(FFFF)V"), method = "b(FI)V")
//    private void redirectRenderHand0(float fovy, float aspect, float zNear, float zFar){
//        if(Shaders.isActiveShader) {
//            Shaders.applyHandDepth();
//            Project.gluPerspective(fovy, aspect, 0.05F, zFar);
//        } else {
//            Project.gluPerspective(fovy, aspect, zNear, zFar);
//        }
//    }

    @Inject(at = @At(value = "RETURN"), method = "a(D)V")
    private void injectDisableLightmap(CallbackInfo callbackInfo){
        if(Shaders.isActiveShader) {
            Shaders.disableLightmap();
        }
    }

    @Inject(at = @At(value = "RETURN"), method = "b(D)V")
    private void injectEnableLightmap(CallbackInfo callbackInfo){
        if(Shaders.isActiveShader) {
            Shaders.enableLightmap();
        }
    }

//    @Inject(at = @At(value = "HEAD"), method = "a(FJ)V")
//    private void injectRenderWorld0(float par1, long par2, CallbackInfo callbackInfo){
//        if(Shaders.isActiveShader) {
//            Shaders.beginRender(this.q, par1, par2);
//        }
//    }
//
//    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V", ordinal = 2, shift = At.Shift.BEFORE),  method = "a(FJ)V")
//    private void injectRenderWorld1(CallbackInfo callbackInfo){
//        if(Shaders.isActiveShader) {
//            Shaders.clearRenderBuffer();
//        }
//    }
//
//    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityRenderer;setupCameraTransform(FIZ)V", shift = At.Shift.AFTER),  method = "a(FJ)V")
//    private void injectRenderWorld2(float par1, long par2, CallbackInfo callbackInfo){
//        if(Shaders.isActiveShader) {
//            Shaders.setCamera(par1);
//        }
//    }
//
////    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/aul;getRenderDistance()I", shift = At.Shift.BEFORE),  method = "a(FJ)V")
////    private void injectRenderWorld3(float par1, long par2, CallbackInfo callbackInfo){
////        if (Shaders.isActiveShader && !Shaders.isShadowPass) {
////            Shaders.beginSky();
////            Shaders.endSky();
////        }
////    }
//
//    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/bfl;a(F)V", shift = At.Shift.BEFORE),  method = "a(FJ)V")
//    private void injectRenderWorld3(float par1, long par2, CallbackInfo callbackInfo){
//        if (Shaders.isActiveShader && !Shaders.isShadowPass) {
//            Shaders.beginSky();
//        }
//    }
//
//    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/bfl;a(F)V", shift = At.Shift.AFTER),  method = "a(FJ)V")
//    private void injectRenderWorld4(float par1, long par2, CallbackInfo callbackInfo){
//        if (Shaders.isActiveShader && !Shaders.isShadowPass) {
//            Shaders.endSky();
//        }
//    }
//
//    @Redirect(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glViewport(IIII)V"), method = "a(FJ)V")
//    private void redirectRenderWorld0(int x, int y, int width, int height){
//        if(Shaders.isActiveShader) {
//            Shaders.setViewport(x, y, width, height);
//        } else {
//            GL11.glViewport(x, y, width, height);
//        }
//    }
//
//    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/aul;getRenderDistance()I"), method = "a(FJ)V")
//    private int redirectRenderWorld1(){
//        return Shaders.isActiveShader && !Shaders.isShadowPass ? 3 : this.q.u.getRenderDistance();
//    }


    @Overwrite
    public void b(float par1) {
        this.q.C.startSection("lightTex");
        if (this.ad) {
            this.h(par1);
        }

        this.q.C.endSection();
        boolean var2 = Display.isActive();
        if (var2 || !this.q.u.y || this.q.u.A && Mouse.isButtonDown(1)) {
            this.ab = Minecraft.F();
        } else if (Minecraft.F() - this.ab > 500L) {
            this.q.i();
        }

        this.q.C.startSection("mouse");
        int var18;
        if (this.q.A && var2) {
            this.q.w.c();
            float var3 = this.q.u.c * 0.6F + 0.2F;
            float var4 = var3 * var3 * var3 * 8.0F;
            if (this.q.h.zoomed) {
                var4 /= 4.0F;
            }

//            if (this.q.h.isPotionActive(MobEffectList.moveSlowdown)) {
//            }

            float overall_speed_modifier = this.q.h.getSpeedBoostVsSlowDown();
            if (overall_speed_modifier < 0.0F) {
                if (overall_speed_modifier < -0.8F) {
                    overall_speed_modifier = -0.8F;
                }

                var4 /= 1.0F - overall_speed_modifier * 15.0F;
            }

            float var5 = (float)this.q.w.a * var4;
            float var6 = (float)this.q.w.b * var4;
            var18 = 1;
            if (this.q.u.d) {
                var18 = -1;
            }

            if (this.q.u.af) {
                this.G += var5;
                this.H += var6;
                float var8 = par1 - this.K;
                this.K = par1;
                var5 = this.I * var8;
                var6 = this.J * var8;
                this.q.h.c(var5, var6 * (float)var18);
            } else {
                this.q.h.c(var5, var6 * (float)var18);
            }
        }

        this.q.C.endSection();
        if (!this.q.s) {
            a = this.q.u.g;
            awf var13 = new awf(this.q.u, this.q.d, this.q.e);
            int var14 = var13.a();
            int var15 = var13.b();
            int var16 = Mouse.getX() * var14 / this.q.d;
            int var17 = var15 - Mouse.getY() * var15 / this.q.e - 1;
            var18 = a(this.q.u.i);
            if (this.q.f != null && this.q.f.tick_has_passed) {
                this.q.C.startSection("level");
                if (this.q.A || !this.q.h.isGhost()) {
                    if (this.q.u.i == 0) {
                        this.a(par1, 0L);
                    } else if (this.q.u.i == 3) {
                        this.a(par1, this.ac + 16666666L);
                    } else {
                        this.a(par1, this.ac + (long)(1000000000 / var18));
                    }
                }

                this.ac = System.nanoTime();
                this.q.C.endStartSection("gui");
                this.q.r.a(par1, this.q.isGuiOpen(), var16, var17);
                this.q.C.endSection();
            } else {
                GL11.glViewport(0, 0, this.q.d, this.q.e);
                GL11.glMatrixMode(5889);
                GL11.glLoadIdentity();
                GL11.glMatrixMode(5888);
                GL11.glLoadIdentity();
                this.c();
                this.ac = System.nanoTime();
            }

            if (this.q.isGuiOpen()) {
                GL11.glClear(256);

                try {
                    if (this.q.n != null) {
                        this.q.n.a(var16, var17, par1);
                    }

                    if (this.q.isChatImposed()) {
                        this.q.imposed_gui_chat.a(var16, var17, par1);
                    }
                } catch (Throwable var12) {
                    CrashReport var10 = CrashReport.makeCrashReport(var12, "Rendering screen");
                    CrashReportSystemDetails var11 = var10.makeCategory("Screen render details");
                    var11.addCrashSectionCallable("Screen name", new bff(this));
                    var11.addCrashSectionCallable("Mouse location", new bfg(this, var16, var17));
                    var11.addCrashSectionCallable("Screen size", new bfh(this, var13));
                    throw new ReportedException(var10);
                }
            }
        }
    }


    @Overwrite
    private void b(float par1, int par2) {
        if (this.n <= 0) {
            GL11.glMatrixMode(5889);
            GL11.glLoadIdentity();
            float var3 = 0.07F;
            if (this.q.u.g) {
                GL11.glTranslatef((float) (-(par2 * 2 - 1)) * var3, 0.0F, 0.0F);
            }

            if (this.Y != 1.0) {
                GL11.glTranslatef((float) this.Z, (float) (-this.aa), 0.0F);
                GL11.glScaled(this.Y, this.Y, 1.0);
            }

            if (Shaders.isActiveShader) {
                Shaders.applyHandDepth();
            }

            Project.gluPerspective(this.a(par1, false), (float) this.q.d / (float) this.q.e, 0.05F, this.r * 2.0F);
            if (this.q.c.a()) {
                float var4 = 0.6666667F;
                GL11.glScalef(1.0F, var4, 1.0F);
            }

            GL11.glMatrixMode(5888);
            GL11.glLoadIdentity();
            if (this.q.u.g) {
                GL11.glTranslatef((float) (par2 * 2 - 1) * 0.1F, 0.0F, 0.0F);
            }

            if(Shaders.isActiveShader) {
                if (!Shaders.isCompositeRendered) {
                    this.e(par1);
                    if (this.q.u.f) {
                        this.f(par1);
                    }
                    if (this.q.u.aa == 0 && !this.q.i.inBed() && this.q.u.gui_mode != 2 && !this.q.c.a()) {
                        this.b(par1);
                        this.c.a(par1);
                        this.a(par1);
                    }
                    return;
                }
            } else {
                GL11.glPushMatrix();
                this.e(par1);
                if (this.q.u.f) {
                    this.f(par1);
                }

                if (this.q.u.aa == 0 && !this.q.i.inBed() && this.q.u.gui_mode != 2 && !this.q.c.a()) {
                    this.b(par1);
                    this.c.a(par1);
                    this.a(par1);
                }

                GL11.glPopMatrix();
            }


            if (this.q.u.aa == 0 && !this.q.i.inBed()) {
                this.c.b(par1);
                this.e(par1);
            }

            if (this.q.u.f) {
                this.f(par1);
            }

        }
    }

    @Shadow
    private long ac;
    @Shadow
    private long ab;
    @Shadow
    private float G;
    @Shadow
    private float H;
    @Shadow
    private float I;
    @Shadow
    private float J;
    @Shadow
    private float K;
    @Shadow
    public static boolean a;
    @Shadow
    private boolean ad;
    @Shadow
    private float r;
    @Shadow
    public bfj c;
    @Shadow
    private double aa;
    @Shadow
    public int n;
    @Shadow
    private Minecraft q;
    @Shadow
    private double Y;
    @Shadow
    private double Z;
    @Shadow
    public void c() {

    }
    @Shadow
    public void a(double par1) {

    }
    @Shadow
    private float a(float par1, boolean par2) {
        return 0;
    }
    @Shadow
    private void e(float par1) {

    }
    @Shadow
    public void b(double par1) {

    }
    @Shadow
    private void f(float par1) {

    }
    @Shadow
    public static int a(int par0) {
        return 0;
    }
    @Shadow
    private void h(float par1) {

    }
    @Shadow
    public void a(float par1, long par2) {

    }
}
