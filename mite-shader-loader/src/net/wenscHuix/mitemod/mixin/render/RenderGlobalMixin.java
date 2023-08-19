package net.wenscHuix.mitemod.mixin.render;

import net.minecraft.*;
import net.wenscHuix.mitemod.optimize.gui.Config;
import net.wenscHuix.mitemod.shader.client.Shaders;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(bfl.class)
public class RenderGlobalMixin implements IWorldAccess {

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/MethodProfiler;endStartSection(Ljava/lang/String;)V", ordinal = 1,
            shift = At.Shift.AFTER), method = "a(Lnet/minecraft/Vec3D;Lnet/minecraft/bft;F)V")
    private void injectRenderEntities0(CallbackInfo callbackInfo){
        Shaders.beginEntities();
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/MethodProfiler;endStartSection(Ljava/lang/String;)V", ordinal = 2,
            shift = At.Shift.AFTER), method = "a(Lnet/minecraft/Vec3D;Lnet/minecraft/bft;F)V")
    private void injectRenderEntities1(CallbackInfo callbackInfo){
        Shaders.endEntities();
        Shaders.beginTileEntities();
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityRenderer;a(D)V", shift = At.Shift.AFTER),
            method = "a(Lnet/minecraft/Vec3D;Lnet/minecraft/bft;F)V")
    private void injectRenderEntities2(CallbackInfo callbackInfo){
        Shaders.endTileEntities();
        
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V", ordinal = 0, shift = At.Shift.AFTER),
            method = "a(Lnet/minecraft/EntityLiving;ID)I")
    private void injectSortAndRender0(CallbackInfoReturnable<Integer> callbackInfoReturnable){
        Shaders.disableTexture2D();

    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V", ordinal = 3, shift = At.Shift.AFTER),
            method = "a(Lnet/minecraft/EntityLiving;ID)I")
    private void injectSortAndRender1(CallbackInfoReturnable<Integer> callbackInfoReturnable){
        Shaders.disableFog();
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V", ordinal = 0, shift = At.Shift.AFTER),
            method = "a(Lnet/minecraft/EntityLiving;ID)I")
    private void injectSortAndRender2(CallbackInfoReturnable<Integer> callbackInfoReturnable){
        Shaders.enableTexture2D();
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V", ordinal = 2, shift = At.Shift.AFTER),
            method = "a(Lnet/minecraft/EntityLiving;ID)I")
    private void injectSortAndRender3(CallbackInfoReturnable<Integer> callbackInfoReturnable){
        Shaders.enableFog();

    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V", ordinal = 1, shift = At.Shift.AFTER),
            method = "a(F)V")
    private void injectRenderSky0(CallbackInfo callbackInfo){
        Shaders.disableFog();
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V", ordinal = 1, shift = At.Shift.AFTER),
            method = "a(F)V")
    private void injectRenderSky1(CallbackInfo callbackInfo){
        Shaders.enableTexture2D();
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V", ordinal = 3, shift = At.Shift.AFTER),
            method = "a(F)V")
    private void injectRenderSky2(CallbackInfo callbackInfo){
        Shaders.disableTexture2D();
    }

    @Inject(locals = LocalCapture.CAPTURE_FAILHARD, at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/bdd;a(Lnet/minecraft/Entity;F)Lnet/minecraft/Vec3D;"),
            method = "a(F)V")
    private void injectRenderSky3(float par1, CallbackInfo ci, Vec3D var2){
        Shaders.setSkyColor(var2);
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V", ordinal = 3, shift = At.Shift.AFTER),
            method = "a(F)V")
    private void injectRenderSky4(CallbackInfo callbackInfo){
        Shaders.enableFog();

    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glColor3f(FFF)V", ordinal = 1, shift = At.Shift.AFTER),
            method = "a(F)V")
    private void injectRenderSky5(CallbackInfo callbackInfo){
        Shaders.preSkyList();
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V", ordinal = 4, shift = At.Shift.AFTER),
            method = "a(F)V")
    private void injectRenderSky6(CallbackInfo callbackInfo){
        Shaders.disableFog();
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V", ordinal = 6, shift = At.Shift.AFTER),
            method = "a(F)V")
    private void injectRenderSky7(CallbackInfo callbackInfo){
        Shaders.disableTexture2D();
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V", ordinal = 5, shift = At.Shift.AFTER),
            method = "a(F)V")
    private void injectRenderSky8(CallbackInfo callbackInfo){
        Shaders.enableTexture2D();
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glRotatef(FFFF)V", ordinal = 8, shift = At.Shift.AFTER),
            method = "a(F)V")
    private void injectRenderSky9(CallbackInfo callbackInfo){
        Shaders.preCelestialRotate();
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glRotatef(FFFF)V", ordinal = 9, shift = At.Shift.AFTER),
            method = "a(F)V")
    private void injectRenderSky10(CallbackInfo callbackInfo){
        Shaders.postCelestialRotate();
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V", ordinal = 7, shift = At.Shift.AFTER),
            method = "a(F)V")
    private void injectRenderSky11(CallbackInfo callbackInfo){
        Shaders.disableTexture2D();
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V", ordinal = 7, shift = At.Shift.AFTER),
            method = "a(F)V")
    private void injectRenderSky12(CallbackInfo callbackInfo){
        Shaders.enableFog();
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V", ordinal = 9, shift = At.Shift.AFTER),
            method = "a(F)V")
    private void injectRenderSky13(CallbackInfo callbackInfo){
        Shaders.disableTexture2D();
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V", ordinal = 8, shift = At.Shift.AFTER),
            method = "a(F)V")
    private void injectRenderSky14(CallbackInfo callbackInfo){
        Shaders.enableTexture2D();
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V", ordinal = 0, shift = At.Shift.AFTER),
            method = "compileCloudsFancy")
    private void injectCompileCloudsFancy0(CallbackInfo callbackInfo){
        Shaders.disableFog();

    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V", ordinal = 2, shift = At.Shift.AFTER),
            method = "compileCloudsFancy")
    private void injectCompileCloudsFancy1(CallbackInfo callbackInfo){
        Shaders.disableTexture2D();
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V", ordinal = 1, shift = At.Shift.AFTER),
            method = "compileCloudsFancy")
    private void injectCompileCloudsFancy2(CallbackInfo callbackInfo){
        Shaders.enableFog();
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V", ordinal = 3, shift = At.Shift.AFTER),
            method = "compileCloudsFancy")
    private void injectCompileCloudsFancy3(CallbackInfo callbackInfo){
        Shaders.enableTexture2D();
    }

//    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/beh;addBlockDestroyEffects(IIIIII)V"), method = "playAuxSFX")
//    private void redirectPlayAuxSFX(){
////        if(!Config.blockDestroyEffects){
////            this.t.k.addBlockDestroyEffects(x, y, z, block_id, metadata, aux_data);
////        }
//    }
    @Overwrite
    public void b(float par1) {
        if (this.t.f.provider.isSurfaceWorld()) {
            boolean force_fancy_clouds = true;
            if (!force_fancy_clouds && !this.t.u.isFancyGraphicsEnabled()) {
                GL11.glEnable(2884);
                float var2 = (float)(this.t.i.lastTickPosY + (this.t.i.posY - this.t.i.lastTickPosY) * (double)par1);
                byte var3 = 32;
                int var4 = 256 / var3;
                bfq var5 = bfq.a;
                this.l.a(i);
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 771);
                Vec3D var6 = this.k.e(par1);
                float var7 = (float)var6.xCoord;
                float var8 = (float)var6.yCoord;
                float var9 = (float)var6.zCoord;
                float var10;
                if (this.t.u.g) {
                    var10 = (var7 * 30.0F + var8 * 59.0F + var9 * 11.0F) / 100.0F;
                    float var11 = (var7 * 30.0F + var8 * 70.0F) / 100.0F;
                    float var12 = (var7 * 30.0F + var9 * 70.0F) / 100.0F;
                    var7 = var10;
                    var8 = var11;
                    var9 = var12;
                }

                var10 = 4.8828125E-4F;
                double var24 = (float)this.x + par1;
                double var13 = this.t.i.prevPosX + (this.t.i.posX - this.t.i.prevPosX) * (double)par1 + var24 * 0.029999999329447746;
                double var15 = this.t.i.prevPosZ + (this.t.i.posZ - this.t.i.prevPosZ) * (double)par1;
                int var17 = MathHelper.floor_double(var13 / 2048.0);
                int var18 = MathHelper.floor_double(var15 / 2048.0);
                var13 -= var17 * 2048;
                var15 -= var18 * 2048;
                float var19 = this.k.provider.f() - var2 + 0.33F;
                float var20 = (float)(var13 * (double)var10);
                float var21 = (float)(var15 * (double)var10);
                boolean player_can_see_cloud_bottoms = var19 > -0.0F;
                GL11.glCullFace(player_can_see_cloud_bottoms ? 1028 : 1029);
                var5.b();
                GL11.glColor4f(var7, var8, var9, 0.8F);
                var5.o = true;
                int[] rawBuffer = var5.h;
                int y0 = Float.floatToRawIntBits(var19);

                for(int var22 = -var3 * var4; var22 < var3 * var4; var22 += var3) {
                    int u0 = Float.floatToRawIntBits((float)var22 * var10 + var20);
                    int u1 = Float.floatToRawIntBits((float)(var22 + var3) * var10 + var20);
                    int x0 = Float.floatToRawIntBits((float)var22);
                    int x1 = Float.floatToRawIntBits((float)(var22 + var3));

                    for(int var23 = -var3 * var4; var23 < var3 * var4; var23 += var3) {
                        if (RenderingScheme.current == 0) {
                            var5.a(var22 + 0, var19, (double)(var23 + var3), (double)((float)(var22 + 0) * var10 + var20), (double)((float)(var23 + var3) * var10 + var21));
                            var5.a(var22 + var3, var19, (double)(var23 + var3), (double)((float)(var22 + var3) * var10 + var20), (double)((float)(var23 + var3) * var10 + var21));
                            var5.a(var22 + var3, (double)var19, (double)(var23 + 0), (float)(var22 + var3) * var10 + var20, (double)((float)(var23 + 0) * var10 + var21));
                            var5.a(var22 + 0, var19, (double)(var23 + 0), (float)(var22 + 0) * var10 + var20, (double)((float)(var23 + 0) * var10 + var21));
                        } else {
                            int v0 = Float.floatToRawIntBits((float)(var23 + var3) * var10 + var21);
                            int v2 = Float.floatToRawIntBits((float)var23 * var10 + var21);
                            int z0 = Float.floatToRawIntBits((float)(var23 + var3));
                            int z1 = Float.floatToRawIntBits((float)var23);
                            rawBuffer[var5.r + 3] = u0;
                            rawBuffer[var5.r + 11] = u1;
                            rawBuffer[var5.r + 19] = u1;
                            rawBuffer[var5.r + 27] = u0;
                            rawBuffer[var5.r + 4] = v0;
                            rawBuffer[var5.r + 12] = v0;
                            rawBuffer[var5.r + 20] = v2;
                            rawBuffer[var5.r + 28] = v2;
                            rawBuffer[var5.r + 0] = x0;
                            rawBuffer[var5.r + 8] = x1;
                            rawBuffer[var5.r + 16] = x1;
                            rawBuffer[var5.r + 24] = x0;
                            rawBuffer[var5.r + 1] = y0;
                            rawBuffer[var5.r + 9] = y0;
                            rawBuffer[var5.r + 17] = y0;
                            rawBuffer[var5.r + 25] = y0;
                            rawBuffer[var5.r + 2] = z0;
                            rawBuffer[var5.r + 10] = z0;
                            rawBuffer[var5.r + 18] = z1;
                            rawBuffer[var5.r + 26] = z1;
                            var5.r += 32;
                            var5.s += 4;
                            var5.i += 4;
                            if (var5.r >= 2097120) {
                                var5.a();
                                var5.z = true;
                            }
                        }
                    }
                }

                var5.a();
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glDisable(3042);
                GL11.glCullFace(1029);
            } else if (this.t.u.g) {
                this.c(par1);
            } else {
                this.c(par1);
            }
        }

    }

    public bdd getClientWorld(){
        return k;
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glBlendFunc(II)V", shift = At.Shift.AFTER),
            method = "a(Lnet/minecraft/bfq;Lnet/minecraft/EntityPlayer;F)V")
    private void injectDrawBlockDamageTexture0(CallbackInfo callbackInfo){
        Shaders.beginBlockDestroyProgress();
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glPopMatrix()V", shift = At.Shift.AFTER),
            method = "a(Lnet/minecraft/bfq;Lnet/minecraft/EntityPlayer;F)V")
    private void injectDrawBlockDamageTexture1(CallbackInfo callbackInfo){
        Shaders.endBlockDestroyProgress();
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V", ordinal = 0, shift = At.Shift.AFTER),
            method = "drawSelectionBox")
    private void injectDrawSelectionBox0(CallbackInfo callbackInfo){
        Shaders.disableTexture2D();
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V", ordinal = 1, shift = At.Shift.AFTER),
            method = "drawSelectionBox")
    private void injectDrawSelectionBox1(CallbackInfo callbackInfo){
        Shaders.enableTexture2D();
    }


    @Shadow
    private bdd k;
    @Shadow
    @Final
    private bim l;
    @Shadow
    @Final
    private static bjo i;
    @Shadow
    private int x;
    @Shadow
    private Minecraft t;
    @Shadow
    public void c(float par1) {

    }
    @Shadow
    public void renderCloudsFancy_MITE(float par1) {

    }
    @Shadow
    public void markBlockForUpdate(int i, int i1, int i2) {

    }
    @Shadow
    public void markBlockForRenderUpdate(int i, int i1, int i2) {

    }

    @Shadow
    public void markBlockRangeForRenderUpdate(int i, int i1, int i2, int i3, int i4, int i5) {

    }

    @Shadow
    public void playSound(String s, double v, double v1, double v2, float v3, float v4) {

    }

    @Shadow
    public void playLongDistanceSound(String s, double v, double v1, double v2, float v3, float v4) {

    }

    @Shadow
    public void playSoundToNearExcept(EntityPlayer entityPlayer, String s, double v, double v1, double v2, float v3, float v4) {

    }

    @Shadow
    public void spawnParticle(EnumParticle enumParticle, double v, double v1, double v2, double v3, double v4, double v5) {

    }

    @Shadow
    public void spawnParticleEx(EnumParticle enumParticle, int i, int i1, double v, double v1, double v2, double v3, double v4, double v5) {

    }

    @Shadow
    public void onEntityCreate(Entity entity) {

    }

    @Shadow
    public void onEntityDestroy(Entity entity) {

    }

    @Shadow
    public void playRecord(String s, int i, int i1, int i2) {

    }

    @Shadow
    public void broadcastSound(int i, int i1, int i2, int i3, int i4) {

    }

    @Shadow
    public void playAuxSFX(EntityPlayer entityPlayer, int i, int i1, int i2, int i3, int i4) {

    }

    @Shadow
    public void destroyBlockPartially(int i, int i1, int i2, int i3, int i4) {

    }
}
