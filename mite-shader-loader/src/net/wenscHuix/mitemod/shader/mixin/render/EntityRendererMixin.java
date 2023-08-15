package net.wenscHuix.mitemod.shader.mixin.render;


import net.minecraft.*;
import net.wenscHuix.mitemod.shader.client.Shaders;
import net.wenscHuix.mitemod.shader.client.ShadersRender;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.Project;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.nio.FloatBuffer;
import java.util.Random;

@Mixin(EntityRenderer.class)
public class EntityRendererMixin {

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
//    @Redirect(at = @At(value = "INVOKE", target = "Lorg/lwjgl/util/glu/Project;gluPerspective(FFFF)V"), method = "b(FI)V")
//    private void redirectRenderHand0(float fovy, float aspect, float zNear, float zFar){
//        if(Shaders.isActiveShader) {
//            Shaders.applyHandDepth();
//            Project.gluPerspective(fovy, aspect, 0.05F, zFar);
//        } else {
//            Project.gluPerspective(fovy, aspect, zNear, zFar);
//        }
//    }
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

    @Inject(at = @At(value = "HEAD"), method = "a(FFFF)Ljava/nio/FloatBuffer;")
    private void injectSetFogColorBuffer(float par1, float par2, float par3, float par4, CallbackInfo callbackInfo){
        if(Shaders.isActiveShader) {
            Shaders.setFogColor(par1, par2, par3);
        }
    }

    @Overwrite
    private void a(int par1, float par2) {
        EntityLiving var3 = this.q.i;
        boolean var4 = disable_fog;

        if (par1 == 999) {
            GL11.glFog(2918, this.a(0.0F, 0.0F, 0.0F, 1.0F));
            if(Shaders.isActiveShader) {
                Shaders.sglFogi(2917, 9729);
            }
            GL11.glFogi(2917, 9729);
            GL11.glFogf(2915, 0.0F);
            GL11.glFogf(2916, 8.0F);

            if(Shaders.isActiveShader) {
                if (GLContext.getCapabilities().GL_NV_fog_distance) {
                    Shaders.sglFogi(34138, 34139);
                }
            } else {
                if (capability_gl_nv_fog_distance) {
                    GL11.glFogi(34138, 34139);
                }
            }

            GL11.glFogf(2915, 0.0F);
        } else {
            GL11.glFog(2918, this.a(this.k, this.l, this.m, 1.0F));
            GL11.glNormal3f(0.0F, -1.0F, 0.0F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            int var5 = atp.a(this.q.f, var3, par2);
            float var6;
            if (var3.isPotionActive(MobEffectList.blindness)) {
                var6 = 5.0F;
                int var7 = var3.getActivePotionEffect(MobEffectList.blindness).getDuration();
                if (var7 < 20) {
                    var6 = 5.0F + (this.r - 5.0F) * (1.0F - (float)var7 / 20.0F);
                }

                if(Shaders.isActiveShader) {
                    Shaders.sglFogi(GL11.GL_FOG_MODE, GL11.GL_LINEAR);
                } else {
                    GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_LINEAR);
                }

                if (par1 < 0) {
                    GL11.glFogf(2915, 0.0F);
                    GL11.glFogf(2916, var6 * 0.8F);
                } else {
                    GL11.glFogf(2915, var6 * 0.25F);
                    GL11.glFogf(2916, var6);
                }

                if(Shaders.isActiveShader) {
                    if (GLContext.getCapabilities().GL_NV_fog_distance) {
                        Shaders.sglFogi(34138, 34139);
                    }
                } else {
                    if (capability_gl_nv_fog_distance)
                    {
                        GL11.glFogi(34138, 34139);
                    }
                }
            } else if (this.X) {
                if(Shaders.isActiveShader) {
                    Shaders.sglFogi(2917, 2048);
                } else {
                    GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_EXP);
                }
                GL11.glFogf(2914, 0.1F);
            } else if (var5 > 0 && Block.blocksList[var5].blockMaterial == Material.water) {

                if(Shaders.isActiveShader) {
                    Shaders.sglFogi(2917, 2048);
                } else {
                    GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_EXP);
                }

                if (var3.isPotionActive(MobEffectList.waterBreathing)) {
                    GL11.glFogf(2914, 0.05F);
                } else {
                    GL11.glFogf(2914, 0.1F - (float)EnchantmentManager.getRespiration(var3) * 0.03F);
                }
            } else if (var5 > 0 && Block.blocksList[var5].blockMaterial == Material.lava) {
                if(Shaders.isActiveShader) {
                    Shaders.sglFogi(2917, 2048);
                } else {
                    GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_EXP);
                }
                GL11.glFogf(2914, 2.0F);
            } else {
                var6 = this.r;
                if (!var4) {
                    if (this.q.f.provider.getWorldHasVoidFog()) {
                        Vec3D eye_pos = var3.getEyePos();
                        int skylight_brightness_at_eye_pos = this.q.f.a(EnumSkyBlock.Sky, eye_pos.getBlockX(), eye_pos.getBlockY(), eye_pos.getBlockZ());
                        int effective_fps = Math.max(Minecraft.last_fps, 1);

                        float delta;
                        float max_change;
                        if (this.skylight_brightness_used_for_fog < (float)skylight_brightness_at_eye_pos) {
                            delta = (float)skylight_brightness_at_eye_pos - this.skylight_brightness_used_for_fog;
                            max_change = !(delta < 12.0F) && eye_pos.getBlockY() >= 0 ? delta : 0.6F / (float)effective_fps;
                            this.skylight_brightness_used_for_fog += Math.min(delta, max_change);
                        } else if (this.skylight_brightness_used_for_fog > (float)skylight_brightness_at_eye_pos) {
                            delta = this.skylight_brightness_used_for_fog - (float)skylight_brightness_at_eye_pos;
                            max_change = !(delta < 12.0F) && eye_pos.getBlockY() >= 0 ? delta : 0.6F / (float)effective_fps;
                            this.skylight_brightness_used_for_fog -= Math.min(delta, max_change);
                        }

                        double interpolated_pos_y = MathHelper.getInterpolatedValue(var3.lastTickPosY, var3.posY, par2);
                        double var10 = (double)this.skylight_brightness_used_for_fog / 16.0 + (interpolated_pos_y + 4.0) / 20.0;
                        if (var10 < 0.0) {
                            var10 = 0.0;
                        }

                        double power = 1.0 + (16.0 - interpolated_pos_y) / 2.0;
                        if (power > 1.0) {
                            var10 = Math.pow(var10, power);
                        }

                        float var9 = 100.0F * (float)var10;
                        if (var9 < 5.0F) {
                            var9 = 5.0F;
                        }

                        if (var6 > var9) {
                            var6 = var9;
                        }

                        if (var6 < this.r) {
                            var6 = (float)((double)var6 / Math.max(Math.sqrt((double)getProximityToNearestFogPost(var3)), 0.009999999776482582));
                        }

                        if (var6 < 24.0F) {
                            var6 = 24.0F;
                        }

                        if (var6 > 96.0F && this.q.f.isOverworld()) {
                            long shifted_total_world_time = this.q.f.getTotalWorldTime() - 12000L;
                            int shifted_day_of_world = World.getDayOfWorld(shifted_total_world_time);
                            if (shifted_day_of_world > 7) {
                                random_for_fog_events.setSeed((shifted_day_of_world * 365024131L) * this.q.f.getWorldCreationTime() * 672784657L);
                                random_for_fog_events.nextInt();
                                if (random_for_fog_events.nextInt(7) == 0) {
                                    float fog_max_strength = 96.0F + random_for_fog_events.nextFloat() * (var6 - 96.0F) * 0.75F;
                                    long ticks_from_midnight = this.q.f.getAdjustedTimeOfDay();
                                    boolean is_dusk = false;
                                    if (ticks_from_midnight > 12000L) {
                                        ticks_from_midnight = 24000L - ticks_from_midnight;
                                        is_dusk = true;
                                    }

                                    float day_cycle_factor = MathHelper.clamp_float((float)(8000L - ticks_from_midnight) / (is_dusk ? 4000.0F : 2000.0F), 0.0F, 1.0F);
                                    if (day_cycle_factor > 0.0F) {
                                        if (distance_from_biome_that_can_be_foggy_last_viewer_world != this.q.f || distance_from_biome_that_can_be_foggy_tick != this.q.f.getTotalWorldTime()) {
                                            boolean player_moved = distance_from_biome_that_can_be_foggy_last_viewer_world != this.q.f || distance_from_biome_that_can_be_foggy_last_viewer_pos_x != var3.posX || distance_from_biome_that_can_be_foggy_last_viewer_pos_z != var3.posZ;
                                            if (player_moved) {
                                                distance_from_biome_that_can_be_foggy = this.getDistanceToNearestBiomeThatCanBeFoggy(var3.posX, var3.posZ);
                                                distance_from_biome_that_can_be_foggy_last_viewer_pos_x = var3.posX;
                                                distance_from_biome_that_can_be_foggy_last_viewer_pos_z = var3.posZ;
                                            }

                                            distance_from_biome_that_can_be_foggy_tick = this.q.f.getTotalWorldTime();
                                        }

                                        float distance_from_biome_that_can_be_foggy_factor = Math.max(1.0F - (float)(distance_from_biome_that_can_be_foggy / 32.0), 0.0F);
                                        float final_factor = day_cycle_factor * distance_from_biome_that_can_be_foggy_factor;
                                        if (final_factor > 0.0F) {
                                            float fog_strength = fog_max_strength * final_factor + var6 * (1.0F - final_factor);
                                            if (var6 > fog_strength) {
                                                var6 = fog_strength;
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        if (var6 > this.r) {
                            var6 = this.r;
                        }
                    } else if (this.q.f.isUnderworld()) {
                        var6 = 128.0F;
                    }

                    if(Shaders.isActiveShader) {
                        Shaders.sglFogi(2917, 9729);
                    }

                }

                GL11.glFogi(2917, 9729);
                if (par1 < 0) {
                    GL11.glFogf(2915, 0.0F);
                    GL11.glFogf(2916, var6 * 0.8F);
                } else {
                    GL11.glFogf(2915, var6 * 0.25F);
                    GL11.glFogf(2916, var6);
                }

                if(Shaders.isActiveShader) {
                    if (GLContext.getCapabilities().GL_NV_fog_distance) {
                        Shaders.sglFogi(34138, 34139);
                    }
                } else {
                    if (capability_gl_nv_fog_distance) {
                        GL11.glFogi(34138, 34139);
                    }
                }

                if (this.q.f.provider.doesXZShowFog(var3.getBlockPosX(), var3.getEyeBlockPosY(), var3.getBlockPosZ())) {
                    GL11.glFogf(2915, var6 * 0.05F);
                    GL11.glFogf(2916, Math.min(var6, 192.0F) * 0.5F);
                }
            }

            GL11.glEnable(2903);
            GL11.glColorMaterial(1028, 4608);
        }

    }

    @Overwrite
    private void a(bfl par1RenderGlobal, float par2) {
        if (Shaders.isActiveShader ? Shaders.shouldRenderClouds(this.q.u) : this.q.u.d()) {
            this.q.C.endStartSection("clouds");
            GL11.glPushMatrix();
            this.a(0, par2);
            GL11.glEnable(2912);

            if(Shaders.isActiveShader) {
                Shaders.beginClouds();
            }

            par1RenderGlobal.b(par2);

            if(Shaders.isActiveShader) {
                Shaders.endClouds();
            }

            GL11.glDisable(2912);
            this.a(1, par2);
            GL11.glPopMatrix();
        }

    }

    @Overwrite
    public void a(float par1, long par2) {
        if(Shaders.isActiveShader) {
            Shaders.beginRender(this.q, par1, par2);
        }

        this.q.C.startSection("lightTex");
        if (this.ad) {
            this.h(par1);
        }

        GL11.glEnable(2884);
        GL11.glEnable(2929);
        if (this.q.i == null) {
            this.q.i = this.q.h;
        }

        this.q.C.endStartSection("pick");
        this.a(par1);
        EntityLiving var4 = this.q.i;
        if (var4 instanceof EntityPlayer) {
            EntityPlayer entity_player = (EntityPlayer)var4;
            if (entity_player.inBed()) {
                entity_player.setPositionAndRotationInBed();
            }
        }

        bfl var5 = this.q.g;
        beh var6 = this.q.k;
        double var7 = var4.lastTickPosX + (var4.posX - var4.lastTickPosX) * (double)par1;
        double var9 = var4.lastTickPosY + (var4.posY - var4.lastTickPosY) * (double)par1;
        double var11 = var4.lastTickPosZ + (var4.posZ - var4.lastTickPosZ) * (double)par1;
        this.q.C.endStartSection("center");

        for(int var13 = 0; var13 < 2; ++var13) {
            if (this.q.u.g) {
                b = var13;
                if (b == 0) {
                    GL11.glColorMask(false, true, true, false);
                } else {
                    GL11.glColorMask(true, false, false, false);
                }
            }

            this.q.C.endStartSection("clear");

            if(Shaders.isActiveShader) {
                Shaders.setViewport(0, 0, this.q.d, this.q.e);
            } else {
                GL11.glViewport(0, 0, this.q.d, this.q.e);
            }


            this.i(par1);
            GL11.glClear(16640);

            if(Shaders.isActiveShader) {
                Shaders.clearRenderBuffer();
            }

            GL11.glEnable(2884);
            this.q.C.endStartSection("camera");
            this.setupCameraTransform(par1, var13, false);

            if(Shaders.isActiveShader) {
                Shaders.setCamera(par1);
            }

            if (this.last_vsync_nanotime != -1L) {
                long milliseconds_since_last_vsync = (System.nanoTime() - this.last_vsync_nanotime) / 1000000L;
                this.q.downtimeProcessing(16L - milliseconds_since_last_vsync);
            }

            atp.a(this.q.h, this.q.u.aa == 2);
            this.last_vsync_nanotime = System.nanoTime();
            if (this.last_vsync_nanotime > this.fps_start_time + 1000000000L) {
                this.fps_start_time = this.last_vsync_nanotime;
                Minecraft.last_fps = this.fps_counter;
                this.fps_counter = 0;
            } else {
                ++this.fps_counter;
            }

            if (this.last_vsync_nanotime > this.fp10s_start_time + 10000000000L) {
                this.fp10s_start_time = this.last_vsync_nanotime;
                Minecraft.last_fp10s = this.fp10s_counter;
                this.fp10s_counter = 0;
            } else {
                ++this.fp10s_counter;
            }

            this.q.C.endStartSection("frustrum");
            bfu.a();
            if (Shaders.isActiveShader ? !Shaders.isShadowPass : this.q.u.getRenderDistance() < 2) {
                this.a(-1, par1);

                if(Shaders.isActiveShader) {
                    Shaders.beginSky();
                }

                this.q.C.endStartSection("sky");
                var5.a(par1);

                if(Shaders.isActiveShader) {
                    Shaders.endSky();
                }
            }

            GL11.glEnable(2912);
            this.a(1, par1);
            if (this.q.u.k != 0) {
                GL11.glShadeModel(7425);
            }

            this.q.C.endStartSection("culling");
            bfv frustrum = new bfv();

            if(Shaders.isActiveShader) {
                ShadersRender.setFrustrumPosition(frustrum, var7, var9, var11);
                ShadersRender.clipRenderersByFrustrum(this.q.g, frustrum, par1);
            } else {
                frustrum.a(var7, var9, var11);
                this.q.g.a(frustrum, par1);
            }

            if (var13 == 0) {

                if(Shaders.isActiveShader) {
                    Shaders.beginUpdateChunks();
                }

                this.q.C.endStartSection("updatechunks");

                while(!this.q.g.a(var4, false) && par2 != 0L) {
                    long var15 = par2 - System.nanoTime();
                    if (this.q.u.i == 3) {
                        if (var15 < 1000000L || var15 > 1000000000L) {
                            break;
                        }
                    } else if (var15 < 0L || var15 > 1000000000L) {
                        break;
                    }
                }

                if(Shaders.isActiveShader) {
                    Shaders.endUpdateChunks();
                }
            }

            if (var4.posY < 128.0) {
                this.a(var5, par1);
            }

            this.q.C.endStartSection("prepareterrain");
            this.a(0, par1);
            GL11.glEnable(2912);
            this.q.J().a(bik.b);
            att.a();
            this.q.C.endStartSection("terrain");

            if(Shaders.isActiveShader) {
                Shaders.beginTerrain();
                var5.a(var4, 0, par1);
                Shaders.endTerrain();
            } else {
                var5.a(var4, 0, par1);
            }

            GL11.glShadeModel(7424);
            EntityPlayer var17;
            if (this.n == 0) {
                att.b();
                this.q.C.endStartSection("entities");
                var5.a(var4.l(par1), frustrum, par1);
                this.b((double)par1);
                this.q.C.endStartSection("litParticles");

                if(Shaders.isActiveShader) {
                    Shaders.beginLitParticles();
                }

                var6.b(var4, par1);
                att.a();
                this.a(0, par1);

                if(Shaders.isActiveShader) {
                    Shaders.beginParticles();
                }

                this.q.C.endStartSection("particles");
                var6.a(var4, par1);

                if(Shaders.isActiveShader) {
                    Shaders.endParticles();
                }

                this.a(par1);
                if (this.q.t != null && var4.isInsideOfMaterial(Material.water) && var4 instanceof EntityPlayer && this.q.u.gui_mode == 0) {
                    var17 = (EntityPlayer)var4;
                    GL11.glDisable(3008);
                    this.q.C.endStartSection("outline");
                    var5.drawSelectionBox(var17, this.q.t, 0, par1);
                    GL11.glEnable(3008);
                }
            }

            GL11.glDisable(3042);
            GL11.glEnable(2884);
            GL11.glBlendFunc(770, 771);
            GL11.glDepthMask(true);
            if(Shaders.isActiveShader) {
                Shaders.beginHand();
                this.a(0, par1);
                Shaders.endHand();
                Shaders.preWater();
            }

            GL11.glEnable(3042);
            GL11.glDisable(2884);
            this.q.J().a(bik.b);
            if (this.q.u.isFancyGraphicsEnabled()) {
                this.q.C.endStartSection("water");
                if (this.q.u.k != 0) {
                    GL11.glShadeModel(7425);
                }

                GL11.glColorMask(false, false, false, false);

                if(Shaders.isActiveShader) {
                    Shaders.beginWaterFancy();
                }
                int var18 = var5.a(var4, 1, par1);
                if (this.q.u.g) {
                    if (b == 0) {
                        GL11.glColorMask(false, true, true, true);
                    } else {
                        GL11.glColorMask(true, false, false, true);
                    }
                } else {
                    GL11.glColorMask(true, true, true, true);
                }

                if (var18 > 0) {
                    if(Shaders.isActiveShader) {
                        Shaders.midWaterFancy();
                    }
                    var5.a(1, par1);
                }
                if(Shaders.isActiveShader){
                    Shaders.endWater();
                }
                GL11.glShadeModel(7424);
            } else {
                this.q.C.endStartSection("water");
                if(Shaders.isActiveShader) {
                    Shaders.beginWater();
                    var5.a(var4, 1, par1);
                    Shaders.endWater();
                } else {
                    var5.a(var4, 1, par1);
                }

            }

            GL11.glDepthMask(true);
            GL11.glEnable(2884);
            GL11.glDisable(3042);

            if(Shaders.isActiveShader) {
                if (Shaders.isShadowPass) {
                    return;
                }
                Shaders.readCenterDepth();
            }

            if (this.Y == 1.0 && var4 instanceof EntityPlayer && this.q.u.gui_mode == 0 && this.q.t != null && !var4.isInsideOfMaterial(Material.water)) {
                var17 = (EntityPlayer)var4;
                GL11.glDisable(3008);
                this.q.C.endStartSection("outline");
                var5.drawSelectionBox(var17, this.q.t, 0, par1);
                GL11.glEnable(3008);
            }

            this.q.C.endStartSection("destroyProgress");
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 1);
            var5.a(bfq.a, (EntityPlayer)var4, par1);
            GL11.glDisable(3042);
            this.q.C.endStartSection("weather");
            if(Shaders.isActiveShader) {
                Shaders.beginWeather();
            }
            this.d(par1);
            if(Shaders.isActiveShader) {
                Shaders.endWeather();
            }
            GL11.glDisable(2912);
            if(Shaders.isActiveShader) {
                Shaders.disableFog();
            }
            if (var4.posY >= 128.0) {
                this.a(var5, par1);
            }

            this.q.C.endStartSection("hand");
            if(Shaders.isActiveShader) {
                Shaders.renderCompositeFinal();
            }
            if (this.Y == 1.0) {
                GL11.glClear(256);
                if(Shaders.isActiveShader) {
                    Shaders.beginFPOverlay();
                }
                this.b(par1, var13);
                if(Shaders.isActiveShader) {
                    Shaders.endFPOverlay();
                }
            }

            if(Shaders.isActiveShader) {
                Shaders.endRender();
            }

            if (!this.q.u.g) {
                this.q.C.endSection();
                return;
            }
        }

        GL11.glColorMask(true, true, true, false);
        this.q.C.endSection();
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
    private boolean X;
    @Shadow
    float k;
    @Shadow
    float l;
    @Shadow
    float m;
    @Shadow
    private static double distance_from_biome_that_can_be_foggy;
    @Shadow
    private static long distance_from_biome_that_can_be_foggy_tick;
    @Shadow
    private static World distance_from_biome_that_can_be_foggy_last_viewer_world;
    @Shadow
    private static double distance_from_biome_that_can_be_foggy_last_viewer_pos_x;
    @Shadow
    private static double distance_from_biome_that_can_be_foggy_last_viewer_pos_z;
    @Shadow
    private static Random random_for_fog_events;
    @Shadow
    public static int b;
    @Shadow
    private long last_vsync_nanotime;
    @Shadow
    private long fps_start_time;
    @Shadow
    private int fps_counter;
    @Shadow
    private long fp10s_start_time;
    @Shadow
    private int fp10s_counter;
    @Shadow
    private long ac;
    @Shadow
    private long ab;
    @Shadow
    public static boolean disable_fog;
    @Shadow
    @Final
    private static boolean capability_gl_nv_fog_distance;
    @Shadow
    private float skylight_brightness_used_for_fog;
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
    private void i(float par1) {

    }
    @Shadow
    protected void d(float par1) {

    }
    @Shadow
    public void setupCameraTransform(float par1, int par2, boolean extend_far_clipping_plane) {

    }
    @Shadow
    public double getDistanceToNearestBiomeThatCanBeFoggy(double pos_x, double pos_z) {
        return 0;
    }
    @Shadow
    private FloatBuffer a(float par1, float par2, float par3, float par4) {
        return null;
    }
    @Shadow
    public static float getProximityToNearestFogPost(EntityLiving viewer) {
        return 0;
    }

}
