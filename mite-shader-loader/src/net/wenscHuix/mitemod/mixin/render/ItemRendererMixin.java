package net.wenscHuix.mitemod.mixin.render;

import net.minecraft.*;
import net.wenscHuix.mitemod.shader.client.dynamicLight.DynamicLights;
import net.wenscHuix.mitemod.shader.client.dynamicLight.config.ShaderConfig;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(bfj.class)
public class ItemRendererMixin {
    @Overwrite
    public void a(float par1) {
        ClientPlayer player = this.e.h;
        if (this.f == null || !(this.f.getItem() instanceof ItemFishingRod) || !player.zoomed) {
            if (player.ticksExisted < 1) {
                player.i = player.g = player.rotationYaw;
                player.j = player.h = player.rotationPitch;
            }

            if (this.e.f.doesChunkAndAllNeighborsExist(player.getChunkPosX(), player.getChunkPosZ(), 0, false)) {
                float var2 = this.h + (this.g - this.h) * par1;
                ClientPlayer var3 = this.e.h;
                float var4 = var3.prevRotationPitch + (var3.rotationPitch - var3.prevRotationPitch) * par1;
                GL11.glPushMatrix();
                GL11.glRotatef(var4, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(var3.prevRotationYaw + (var3.rotationYaw - var3.prevRotationYaw) * par1, 0.0F, 1.0F, 0.0F);
                att.b();
                GL11.glPopMatrix();
                float var6 = var3.j + (var3.h - var3.j) * par1;
                float var7 = var3.i + (var3.g - var3.i) * par1;
                GL11.glRotatef((var3.rotationPitch - var6) * 0.1F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef((var3.rotationYaw - var7) * 0.1F, 0.0F, 1.0F, 0.0F);
                ItemStack var8 = this.f;
                float var9 = this.e.f.getLightBrightness(MathHelper.floor_double(var3.posX), MathHelper.floor_double(var3.posY), MathHelper.floor_double(var3.posZ));
                var9 = 1.0F;
                int var10 = this.e.f.h(MathHelper.floor_double(var3.posX), MathHelper.floor_double(var3.posY), MathHelper.floor_double(var3.posZ), 0);

                if(ShaderConfig.isDynamicLights()){
                    var10 = DynamicLights.getCombinedLight(this.e.i, var10);
                }

                int var11 = var10 % 65536;
                int var12 = var10 / 65536;
                bma.a(bma.b, (float)var11 / 1.0F, (float)var12 / 1.0F);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                float var13;
                float var20;
                float var22;
                if (var8 != null) {
                    var10 = Item.itemsList[var8.itemID].a(var8, 0);
                    var20 = (float)(var10 >> 16 & 255) / 255.0F;
                    var22 = (float)(var10 >> 8 & 255) / 255.0F;
                    var13 = (float)(var10 & 255) / 255.0F;
                    GL11.glColor4f(var9 * var20, var9 * var22, var9 * var13, 1.0F);
                } else {
                    GL11.glColor4f(var9, var9, var9, 1.0F);
                }

                float var14;
                float var15;
                float var16;
                float var21;
                bgm var27;
                bhj var26;
                if (var8 != null && var8.itemID == Item.map.itemID) {
                    GL11.glPushMatrix();
                    var21 = 0.8F;
                    var20 = var3.k(par1);
                    var22 = MathHelper.sin(var20 * 3.1415927F);
                    var13 = MathHelper.sin(MathHelper.sqrt_float(var20) * 3.1415927F);
                    GL11.glTranslatef(-var13 * 0.4F, MathHelper.sin(MathHelper.sqrt_float(var20) * 3.1415927F * 2.0F) * 0.2F, -var22 * 0.2F);
                    var20 = 1.0F - var4 / 45.0F + 0.1F;
                    if (var20 < 0.0F) {
                        var20 = 0.0F;
                    }

                    if (var20 > 1.0F) {
                        var20 = 1.0F;
                    }

                    var20 = -MathHelper.cos(var20 * 3.1415927F) * 0.5F + 0.5F;
                    GL11.glTranslatef(0.0F, 0.0F * var21 - (1.0F - var2) * 1.2F - var20 * 0.5F + 0.04F, -0.9F * var21);
                    GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(var20 * -85.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glEnable(32826);
                    this.e.J().a(var3.r());

                    for(var12 = 0; var12 < 2; ++var12) {
                        int var24 = var12 * 2 - 1;
                        GL11.glPushMatrix();
                        GL11.glTranslatef(-0.0F, -0.6F, 1.1F * (float)var24);
                        GL11.glRotatef((float)(-45 * var24), 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef(59.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glRotatef((float)(-65 * var24), 0.0F, 1.0F, 0.0F);
                        var27 = bgl.a.a(this.e.h);
                        var26 = (bhj)var27;
                        var16 = 1.0F;
                        GL11.glScalef(var16, var16, var16);
                        var26.a(this.e.h);
                        GL11.glPopMatrix();
                    }

                    var22 = var3.k(par1);
                    var13 = MathHelper.sin(var22 * var22 * 3.1415927F);
                    var14 = MathHelper.sin(MathHelper.sqrt_float(var22) * 3.1415927F);
                    GL11.glRotatef(-var13 * 20.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(-var14 * 20.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glRotatef(-var14 * 80.0F, 1.0F, 0.0F, 0.0F);
                    var15 = 0.38F;
                    GL11.glScalef(var15, var15, var15);
                    GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glTranslatef(-1.0F, -1.0F, 0.0F);
                    var16 = 0.015625F;
                    GL11.glScalef(var16, var16, var16);
                    this.e.J().a(c);
                    bfq var30 = bfq.a;
                    GL11.glNormal3f(0.0F, 0.0F, -1.0F);
                    var30.b();
                    byte var29 = 7;
                    var30.a((double)(0 - var29), (double)(128 + var29), 0.0, 0.0, 1.0);
                    var30.a((double)(128 + var29), (double)(128 + var29), 0.0, 1.0, 1.0);
                    var30.a((double)(128 + var29), (double)(0 - var29), 0.0, 1.0, 0.0);
                    var30.a((double)(0 - var29), (double)(0 - var29), 0.0, 0.0, 0.0);
                    var30.a();
                    ali var19 = Item.map.getMapData(var8, this.e.f);
                    if (var19 != null) {
                        this.a.a(this.e.h, this.e.J(), var19);
                    }

                    GL11.glPopMatrix();
                } else if (var8 != null) {
                    GL11.glPushMatrix();
                    var21 = 0.8F;
                    if (var3.bq() > 0) {
                        EnumItemInUseAction var23 = var8.getItemInUseAction(this.e.h);
                        if (var23 == EnumItemInUseAction.EAT || var23 == EnumItemInUseAction.DRINK) {
                            var22 = (float)var3.bq() - par1 + 1.0F;
                            var13 = 1.0F - var22 / (float)var8.getMaxItemUseDuration();
                            var14 = 1.0F - var13;
                            var14 = var14 * var14 * var14;
                            var14 = var14 * var14 * var14;
                            var14 = var14 * var14 * var14;
                            var15 = 1.0F - var14;
                            GL11.glTranslatef(0.0F, MathHelper.abs(MathHelper.cos(var22 / 4.0F * 3.1415927F) * 0.1F) * (float)((double)var13 > 0.2 ? 1 : 0), 0.0F);
                            GL11.glTranslatef(var15 * 0.6F, -var15 * 0.5F, 0.0F);
                            GL11.glRotatef(var15 * 90.0F, 0.0F, 1.0F, 0.0F);
                            GL11.glRotatef(var15 * 10.0F, 1.0F, 0.0F, 0.0F);
                            GL11.glRotatef(var15 * 30.0F, 0.0F, 0.0F, 1.0F);
                        }
                    } else {
                        var20 = var3.k(par1);
                        var22 = MathHelper.sin(var20 * 3.1415927F);
                        var13 = MathHelper.sin(MathHelper.sqrt_float(var20) * 3.1415927F);
                        GL11.glTranslatef(-var13 * 0.4F, MathHelper.sin(MathHelper.sqrt_float(var20) * 3.1415927F * 2.0F) * 0.2F, -var22 * 0.2F);
                    }

                    GL11.glTranslatef(0.7F * var21, -0.65F * var21 - (1.0F - var2) * 0.6F, -0.9F * var21);
                    GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glEnable(32826);
                    var20 = var3.k(par1);
                    var22 = MathHelper.sin(var20 * var20 * 3.1415927F);
                    var13 = MathHelper.sin(MathHelper.sqrt_float(var20) * 3.1415927F);
                    GL11.glRotatef(-var22 * 20.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(-var13 * 20.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glRotatef(-var13 * 80.0F, 1.0F, 0.0F, 0.0F);
                    var14 = 0.4F;
                    GL11.glScalef(var14, var14, var14);
                    float var18;
                    float var17;
                    if (var3.bq() > 0) {
                        EnumItemInUseAction var25 = var8.getItemInUseAction(this.e.h);
                        if (var25 == EnumItemInUseAction.BLOCK) {
                            GL11.glTranslatef(-0.5F, 0.2F, 0.0F);
                            GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
                            GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
                            GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
                        } else if (var25 == EnumItemInUseAction.BOW) {
                            GL11.glRotatef(-18.0F, 0.0F, 0.0F, 1.0F);
                            GL11.glRotatef(-12.0F, 0.0F, 1.0F, 0.0F);
                            GL11.glRotatef(-8.0F, 1.0F, 0.0F, 0.0F);
                            GL11.glTranslatef(-0.9F, 0.2F, 0.0F);
                            var16 = (float)var8.getMaxItemUseDuration() - ((float)var3.bq() - par1 + 1.0F);
                            var17 = var16 / (float)ItemBow.getTicksForMaxPull(var8);
                            var17 = (var17 * var17 + var17 * 2.0F) / 3.0F;
                            if (var17 > 1.0F) {
                                var17 = 1.0F;
                            }

                            if (var17 > 0.1F) {
                                GL11.glTranslatef(0.0F, MathHelper.sin((var16 - 0.1F) * 1.3F) * 0.01F * (var17 - 0.1F), 0.0F);
                            }

                            GL11.glTranslatef(0.0F, 0.0F, var17 * 0.1F);
                            GL11.glRotatef(-335.0F, 0.0F, 0.0F, 1.0F);
                            GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                            GL11.glTranslatef(0.0F, 0.5F, 0.0F);
                            var18 = 1.0F + var17 * 0.2F;
                            GL11.glScalef(1.0F, 1.0F, var18);
                            GL11.glTranslatef(0.0F, -0.5F, 0.0F);
                            GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
                            GL11.glRotatef(335.0F, 0.0F, 0.0F, 1.0F);
                        }
                    }

                    if (var8.getItem().o_()) {
                        GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                    }

                    if (var8.getItem().b()) {
                        this.a(var3, var8, 0);
                        int var28 = Item.itemsList[var8.itemID].a(var8, 1);
                        var16 = (float)(var28 >> 16 & 255) / 255.0F;
                        var17 = (float)(var28 >> 8 & 255) / 255.0F;
                        var18 = (float)(var28 & 255) / 255.0F;
                        GL11.glColor4f(var9 * var16, var9 * var17, var9 * var18, 1.0F);
                        this.a(var3, var8, 1);
                    } else {
                        this.a(var3, var8, 0);
                    }

                    GL11.glPopMatrix();
                } else if (!var3.isInvisible()) {
                    GL11.glPushMatrix();
                    var21 = 0.8F;
                    var20 = var3.k(par1);
                    var22 = MathHelper.sin(var20 * 3.1415927F);
                    var13 = MathHelper.sin(MathHelper.sqrt_float(var20) * 3.1415927F);
                    GL11.glTranslatef(-var13 * 0.3F, MathHelper.sin(MathHelper.sqrt_float(var20) * 3.1415927F * 2.0F) * 0.4F, -var22 * 0.4F);
                    GL11.glTranslatef(0.8F * var21, -0.75F * var21 - (1.0F - var2) * 0.6F, -0.9F * var21);
                    GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glEnable(32826);
                    var20 = var3.k(par1);
                    var22 = MathHelper.sin(var20 * var20 * 3.1415927F);
                    var13 = MathHelper.sin(MathHelper.sqrt_float(var20) * 3.1415927F);
                    GL11.glRotatef(var13 * 70.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(-var22 * 20.0F, 0.0F, 0.0F, 1.0F);
                    this.e.J().a(var3.r());
                    GL11.glTranslatef(-1.0F, 3.6F, 3.5F);
                    GL11.glRotatef(120.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glRotatef(200.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScalef(1.0F, 1.0F, 1.0F);
                    GL11.glTranslatef(5.6F, 0.0F, 0.0F);
                    var27 = bgl.a.a(this.e.h);
                    var26 = (bhj)var27;
                    var16 = 1.0F;
                    GL11.glScalef(var16, var16, var16);
                    var26.a(this.e.h);
                    GL11.glPopMatrix();
                }

                GL11.glDisable(32826);
                att.a();
            }
        }
    }

    @Shadow
    private Minecraft e;
    @Shadow
    private ItemStack f;
    @Shadow
    private float g;
    @Shadow
    private float h;
    @Shadow
    @Final
    public avv a;
    @Shadow
    @Final
    private static bjo c;
    @Shadow
    public void a(EntityLiving par1EntityLivingBase, ItemStack par2ItemStack, int par3) {

    }
}
