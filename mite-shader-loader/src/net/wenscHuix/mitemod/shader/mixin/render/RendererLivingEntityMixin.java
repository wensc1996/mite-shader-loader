package net.wenscHuix.mitemod.shader.mixin.render;

import net.minecraft.*;
import net.wenscHuix.mitemod.shader.client.Shaders;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(bhb.class)
public abstract class RendererLivingEntityMixin extends bgm {


    @Redirect(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V", ordinal = 2), method = "a(Lnet/minecraft/EntityLiving;Ljava/lang/String;DDDI)V")
    private void redirectRenderLivingLabel0(int cap){
        if(Shaders.isActiveShader) {
            Shaders.sglDisableT2D(cap);
        } else {
            GL11.glDisable(cap);
        }
    }

    @Redirect(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V", ordinal = 1), method = "a(Lnet/minecraft/EntityLiving;Ljava/lang/String;DDDI)V")
    private void redirectRenderLivingLabel1(int cap){
        if(Shaders.isActiveShader) {
            Shaders.sglEnableT2D(cap);
        } else {
            GL11.glEnable(cap);
        }
    }

    //doRenderLiving
    @Overwrite
    public void a(EntityLiving par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9) {
        if (Shaders.useEntityHurtFlash) {
            Shaders.setEntityHurtFlash(par1EntityLivingBase.hurtTime <= 0 && par1EntityLivingBase.deathTime <= 0 ? 0 : 102, this.a(par1EntityLivingBase, par1EntityLivingBase.getBrightness(par9), par9));
        }
        GL11.glPushMatrix();
        if (par1EntityLivingBase.drawBackFaces()) {
            GL11.glDisable(2884);
        } else {
            GL11.glEnable(2884);
        }

        this.i.p = this.d(par1EntityLivingBase, par9);
        if (this.j != null) {
            this.j.p = this.i.p;
        }

        this.i.q = par1EntityLivingBase.isRiding();
        if (this.j != null) {
            this.j.q = this.i.q;
        }

        this.i.s = par1EntityLivingBase.isChild();
        if (this.j != null) {
            this.j.s = this.i.s;
        }

        try {
            float var10 = this.a(par1EntityLivingBase.prevRenderYawOffset, par1EntityLivingBase.renderYawOffset, par9);
            float var11 = this.a(par1EntityLivingBase.prevRotationYawHead, par1EntityLivingBase.rotationYawHead, par9);
            if (par1EntityLivingBase.isPotionActive(MobEffectList.confusion)) {
                var11 = (float)((double)var11 - Math.cos((float)par1EntityLivingBase.ticksExisted * 0.15F) * 3.0);
            }

            float var13;
            if (par1EntityLivingBase.isRiding() && par1EntityLivingBase.ridingEntity instanceof EntityLiving) {
                EntityLiving var12 = (EntityLiving)par1EntityLivingBase.ridingEntity;
                var10 = this.a(var12.prevRenderYawOffset, var12.renderYawOffset, par9);
                var13 = MathHelper.wrapAngleTo180_float(var11 - var10);
                if (var13 < -85.0F) {
                    var13 = -85.0F;
                }

                if (var13 >= 85.0F) {
                    var13 = 85.0F;
                }

                var10 = var11 - var13;
                if (var13 * var13 > 2500.0F) {
                    var10 += var13 * 0.2F;
                }
            }

            float var26 = par1EntityLivingBase.prevRotationPitch + (par1EntityLivingBase.rotationPitch - par1EntityLivingBase.prevRotationPitch) * par9;
            if (par1EntityLivingBase.isPotionActive(MobEffectList.confusion)) {
                var26 = (float)((double)var26 + Math.sin((float)par1EntityLivingBase.ticksExisted * 0.15F) * 6.0);
            }

            this.a(par1EntityLivingBase, par2, par4, par6);
            var13 = this.b(par1EntityLivingBase, par9);
            this.a(par1EntityLivingBase, var13, var10, par9);
            float var14 = 0.0625F;
            GL11.glEnable(32826);
            GL11.glScalef(-1.0F, -1.0F, 1.0F);
            this.a(par1EntityLivingBase, par9);
            GL11.glTranslatef(0.0F, -24.0F * var14 - 0.0078125F, 0.0F);
            float var15 = par1EntityLivingBase.prevLimbSwingAmount + (par1EntityLivingBase.limbSwingAmount - par1EntityLivingBase.prevLimbSwingAmount) * par9;
            float var16 = par1EntityLivingBase.limbSwing - par1EntityLivingBase.limbSwingAmount * (1.0F - par9);
            if (par1EntityLivingBase.isChild()) {
                var16 *= 3.0F;
            }

            if (var15 > 1.0F) {
                var15 = 1.0F;
            }

            GL11.glEnable(3008);
            this.i.a(par1EntityLivingBase, var16, var15, par9);
            this.a(par1EntityLivingBase, var16, var15, var13, var11 - var10, var26, var14);

            float var19;
            int var18;
            float var20;
            float var22;
            int var28;
            for(int var17 = 0; var17 < 4; ++var17) {
                var18 = this.a(par1EntityLivingBase, var17, par9);
                if (var18 > 0) {
                    this.j.a(par1EntityLivingBase, var16, var15, par9);
                    this.j.a(par1EntityLivingBase, var16, var15, var13, var11 - var10, var26, var14);
                    if ((var18 & 240) == 16) {
//                        this.c(par1EntityLivingBase, var17, par9);
                        this.j.a(par1EntityLivingBase, var16, var15, var13, var11 - var10, var26, var14);
                    }

                    if ((var18 & 15) == 15) {
                        var19 = (float)par1EntityLivingBase.ticksExisted + par9;
                        this.a(a);
                        GL11.glEnable(3042);
                        var20 = 0.5F;
                        GL11.glColor4f(var20, var20, var20, 1.0F);
                        GL11.glDepthFunc(514);
                        GL11.glDepthMask(false);

                        for(var28 = 0; var28 < 2; ++var28) {
                            GL11.glDisable(2896);
                            var22 = 0.76F;
                            GL11.glColor4f(0.5F * var22, 0.25F * var22, 0.8F * var22, 1.0F);
                            GL11.glBlendFunc(768, 1);
                            GL11.glMatrixMode(5890);
                            GL11.glLoadIdentity();
                            float var23 = var19 * (0.001F + (float)var28 * 0.003F) * 20.0F;
                            float var24 = 0.33333334F;
                            GL11.glScalef(var24, var24, var24);
                            GL11.glRotatef(30.0F - (float)var28 * 60.0F, 0.0F, 0.0F, 1.0F);
                            GL11.glTranslatef(0.0F, var23, 0.0F);
                            GL11.glMatrixMode(5888);
                            this.j.a(par1EntityLivingBase, var16, var15, var13, var11 - var10, var26, var14);
                        }

                        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                        GL11.glMatrixMode(5890);
                        GL11.glDepthMask(true);
                        GL11.glLoadIdentity();
                        GL11.glMatrixMode(5888);
                        GL11.glEnable(2896);
                        GL11.glDisable(3042);
                        GL11.glDepthFunc(515);
                    }

                    GL11.glDisable(3042);
                    GL11.glEnable(3008);
                }
            }

            GL11.glDepthMask(true);
            if(Shaders.isActiveShader) {
                Shaders.resetEntityHurtFlash();
            }
//            this.c(par1EntityLivingBase, par9);
            if (Shaders.isActiveShader ? !Shaders.useEntityHurtFlash : true) {
                float var27 = par1EntityLivingBase.getBrightness(par9);
                var18 = this.a(par1EntityLivingBase, var27, par9);
                bma.a(bma.b);
                GL11.glDisable(3553);
                bma.a(bma.a);
                if(Shaders.isActiveShader) {
                    Shaders.disableLightmap();
                }
                if ((var18 >> 24 & 255) > 0 || par1EntityLivingBase.hurtTime > 0 || par1EntityLivingBase.deathTime > 0 || par1EntityLivingBase.tagged) {
                    GL11.glDisable(3553);
                    GL11.glDisable(3008);
                    GL11.glEnable(3042);
                    GL11.glBlendFunc(770, 771);
                    GL11.glDepthFunc(514);
                    if (par1EntityLivingBase.tagged) {
                        GL11.glPushAttrib(2929);
                        GL11.glDisable(2929);
                        GL11.glColor4f(var27, 0.0F, 0.0F, 0.4F);
                        this.i.a(par1EntityLivingBase, var16, var15, var13, var11 - var10, var26, var14);
                        GL11.glPopAttrib();
                    }

                    if(Shaders.isActiveShader) {
                        Shaders.beginLivingDamage();
                    }

                    if (par1EntityLivingBase.hurtTime > 0 || par1EntityLivingBase.deathTime > 0) {
                        GL11.glColor4f(var27, 0.0F, 0.0F, 0.4F);
                        this.i.a(par1EntityLivingBase, var16, var15, var13, var11 - var10, var26, var14);

                        for (var28 = 0; var28 < 4; ++var28) {
                            if (this.b(par1EntityLivingBase, var28, par9) >= 0) {
                                GL11.glColor4f(var27, 0.0F, 0.0F, 0.4F);
                                this.j.a(par1EntityLivingBase, var16, var15, var13, var11 - var10, var26, var14);
                            }
                        }
                    }

                    if ((var18 >> 24 & 255) > 0) {
                        var19 = (float) (var18 >> 16 & 255) / 255.0F;
                        var20 = (float) (var18 >> 8 & 255) / 255.0F;
                        float var30 = (float) (var18 & 255) / 255.0F;
                        var22 = (float) (var18 >> 24 & 255) / 255.0F;
                        GL11.glColor4f(var19, var20, var30, var22);
                        this.i.a(par1EntityLivingBase, var16, var15, var13, var11 - var10, var26, var14);

                        for (int var29 = 0; var29 < 4; ++var29) {
                            if (this.b(par1EntityLivingBase, var29, par9) >= 0) {
                                GL11.glColor4f(var19, var20, var30, var22);
                                this.j.a(par1EntityLivingBase, var16, var15, var13, var11 - var10, var26, var14);
                            }
                        }
                    }

                    GL11.glDepthFunc(515);
                    if(Shaders.isActiveShader) {
                        Shaders.endLivingDamage();
                    }
                    GL11.glDisable(3042);
                    GL11.glEnable(3008);
                    GL11.glEnable(3553);
                }

                GL11.glDisable(32826);
            }
        } catch (Exception var25) {
            var25.printStackTrace();
        }

        bma.a(bma.b);
        GL11.glEnable(3553);
        bma.a(bma.a);
        GL11.glEnable(2884);
        GL11.glPopMatrix();
        this.b(par1EntityLivingBase, par2, par4, par6);
    }
    @Shadow
    @Final
    private static bjo a;
    @Shadow
    protected bbo i;
    @Shadow
    protected bbo j;
    @Shadow
    public void a(bbo par1ModelBase) {
        this.j = par1ModelBase;
    }
    @Shadow
    private float a(float par1, float par2, float par3) {
        return 0f;
    }
    @Shadow
    protected void a(EntityLiving par1EntityLivingBase, float par2, float par3, float par4, float par5, float par6, float par7) {}
    @Shadow
    protected void a(EntityLiving par1EntityLivingBase, double par2, double par4, double par6) {}
    @Shadow
    protected void a(EntityLiving par1EntityLivingBase, float par2, float par3, float par4) {}
    @Shadow
    protected float d(EntityLiving par1EntityLivingBase, float par2){return 0f;}
    @Shadow
    protected float b(EntityLiving par1EntityLivingBase, float par2) {return 0f;}
    @Shadow
    protected void c(EntityLiving par1EntityLivingBase, float par2) {}
    @Shadow
    protected int b(EntityLiving par1EntityLivingBase, int par2, float par3) {return 0;}
    @Shadow
    protected int a(EntityLiving par1EntityLivingBase, int par2, float par3) {
        return -1;
    }
    @Shadow
    protected int a(EntityLiving par1EntityLivingBase, float par2, float par3) {
        return 0;
    }
    @Shadow
    protected void a(EntityLiving par1EntityLivingBase, float par2) {
    }
    @Shadow
    protected void b(EntityLiving par1EntityLivingBase, double par2, double par4, double par6) {}
}
