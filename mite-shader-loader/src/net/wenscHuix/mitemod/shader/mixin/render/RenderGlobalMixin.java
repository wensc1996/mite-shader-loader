package net.wenscHuix.mitemod.shader.mixin.render;

import net.minecraft.*;
import net.wenscHuix.mitemod.shader.client.Shaders;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(bfl.class)
public class RenderGlobalMixin implements IWorldAccess {

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/MethodProfiler;endStartSection(Ljava/lang/String;)V", ordinal = 1,
            shift = At.Shift.AFTER), method = "a(Lnet/minecraft/Vec3D;Lnet/minecraft/bft;F)V")
    private void injectRenderEntities0(CallbackInfo callbackInfo){
        if(Shaders.isActiveShader) {
            Shaders.beginEntities();
        }
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/MethodProfiler;endStartSection(Ljava/lang/String;)V", ordinal = 2,
            shift = At.Shift.AFTER), method = "a(Lnet/minecraft/Vec3D;Lnet/minecraft/bft;F)V")
    private void injectRenderEntities1(CallbackInfo callbackInfo){
        if(Shaders.isActiveShader) {
            Shaders.endEntities();
            Shaders.beginTileEntities();
        }
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityRenderer;a(D)V", shift = At.Shift.AFTER),
            method = "a(Lnet/minecraft/Vec3D;Lnet/minecraft/bft;F)V")
    private void injectRenderEntities2(CallbackInfo callbackInfo){
        if(Shaders.isActiveShader) {
            Shaders.endTileEntities();
        }
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V", ordinal = 0, shift = At.Shift.AFTER),
            method = "a(Lnet/minecraft/EntityLiving;ID)I")
    private void injectSortAndRender0(CallbackInfo callbackInfo){
        if(Shaders.isActiveShader) {
            Shaders.disableTexture2D();
        }
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V", ordinal = 3, shift = At.Shift.AFTER),
            method = "a(Lnet/minecraft/EntityLiving;ID)I")
    private void injectSortAndRender1(CallbackInfo callbackInfo){
        if(Shaders.isActiveShader) {
            Shaders.disableFog();
        }
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V", ordinal = 0, shift = At.Shift.AFTER),
            method = "a(Lnet/minecraft/EntityLiving;ID)I")
    private void injectSortAndRender2(CallbackInfo callbackInfo){
        if(Shaders.isActiveShader) {
            Shaders.enableTexture2D();
        }
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V", ordinal = 2, shift = At.Shift.AFTER),
            method = "a(Lnet/minecraft/EntityLiving;ID)I")
    private void injectSortAndRender3(CallbackInfo callbackInfo){
        if(Shaders.isActiveShader) {
            Shaders.enableFog();
        }
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V", ordinal = 1, shift = At.Shift.AFTER),
            method = "a(F)V")
    private void injectRenderSky0(CallbackInfo callbackInfo){
        if(Shaders.isActiveShader) {
            Shaders.disableFog();
        }
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V", ordinal = 1, shift = At.Shift.AFTER),
            method = "a(F)V")
    private void injectRenderSky1(CallbackInfo callbackInfo){
        if(Shaders.isActiveShader) {
            Shaders.enableTexture2D();
        }
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V", ordinal = 3, shift = At.Shift.AFTER),
            method = "a(F)V")
    private void injectRenderSky2(CallbackInfo callbackInfo){
        if(Shaders.isActiveShader) {
            Shaders.disableTexture2D();
        }
    }

    @Inject(at = @At(value = "FIELD", target = "Lnet/minecraft/bfl;k:Lnet/minecraft/bdd;", ordinal = 0, shift = At.Shift.AFTER),
            method = "a(F)V")
    private void injectRenderSky3(Vec3D par1, CallbackInfo callbackInfo){
        if(Shaders.isActiveShader) {
            Shaders.setSkyColor(par1);
        }
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V", ordinal = 3, shift = At.Shift.AFTER),
            method = "a(F)V")
    private void injectRenderSky4(CallbackInfo callbackInfo){
        if(Shaders.isActiveShader) {
            Shaders.enableFog();
        }
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glColor3f(FFF)V", ordinal = 1, shift = At.Shift.AFTER),
            method = "a(F)V")
    private void injectRenderSky5(CallbackInfo callbackInfo){
        if(Shaders.isActiveShader) {
            Shaders.preSkyList();
        }
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V", ordinal = 4, shift = At.Shift.AFTER),
            method = "a(F)V")
    private void injectRenderSky6(CallbackInfo callbackInfo){
        if(Shaders.isActiveShader) {
            Shaders.disableFog();
        }
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V", ordinal = 6, shift = At.Shift.AFTER),
            method = "a(F)V")
    private void injectRenderSky7(CallbackInfo callbackInfo){
        if(Shaders.isActiveShader) {
            Shaders.disableTexture2D();
        }
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V", ordinal = 5, shift = At.Shift.AFTER),
            method = "a(F)V")
    private void injectRenderSky8(CallbackInfo callbackInfo){
        if(Shaders.isActiveShader) {
            Shaders.enableTexture2D();
        }
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glRotatef(FFFF)V", ordinal = 8, shift = At.Shift.AFTER),
            method = "a(F)V")
    private void injectRenderSky9(CallbackInfo callbackInfo){
        if(Shaders.isActiveShader) {
            Shaders.preCelestialRotate();
        }
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glRotatef(FFFF)V", ordinal = 9, shift = At.Shift.AFTER),
            method = "a(F)V")
    private void injectRenderSky10(CallbackInfo callbackInfo){
        if(Shaders.isActiveShader) {
            Shaders.postCelestialRotate();
        }
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V", ordinal = 7, shift = At.Shift.AFTER),
            method = "a(F)V")
    private void injectRenderSky11(CallbackInfo callbackInfo){
        if(Shaders.isActiveShader) {
            Shaders.disableTexture2D();
        }
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V", ordinal = 7, shift = At.Shift.AFTER),
            method = "a(F)V")
    private void injectRenderSky12(CallbackInfo callbackInfo){
        if(Shaders.isActiveShader) {
            Shaders.enableFog();
        }
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V", ordinal = 9, shift = At.Shift.AFTER),
            method = "a(F)V")
    private void injectRenderSky13(CallbackInfo callbackInfo){
        if(Shaders.isActiveShader) {
            Shaders.disableTexture2D();
        }
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V", ordinal = 8, shift = At.Shift.AFTER),
            method = "a(F)V")
    private void injectRenderSky14(CallbackInfo callbackInfo){
        if(Shaders.isActiveShader) {
            Shaders.enableTexture2D();
        }
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V", ordinal = 0, shift = At.Shift.AFTER),
            method = "compileCloudsFancy")
    private void injectCompileCloudsFancy0(CallbackInfo callbackInfo){
        if(Shaders.isActiveShader) {
            Shaders.disableFog();
        }
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V", ordinal = 2, shift = At.Shift.AFTER),
            method = "compileCloudsFancy")
    private void injectCompileCloudsFancy1(CallbackInfo callbackInfo){
        if(Shaders.isActiveShader) {
            Shaders.disableTexture2D();
        }
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V", ordinal = 1, shift = At.Shift.AFTER),
            method = "compileCloudsFancy")
    private void injectCompileCloudsFancy2(CallbackInfo callbackInfo){
        if(Shaders.isActiveShader) {
            Shaders.enableFog();
        }
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V", ordinal = 3, shift = At.Shift.AFTER),
            method = "compileCloudsFancy")
    private void injectCompileCloudsFancy3(CallbackInfo callbackInfo){
        if(Shaders.isActiveShader) {
            Shaders.enableTexture2D();
        }
    }

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/bfl;renderCloudsFancy_MITE(F)V"), method = "b(F)V")
    private void redirectRenderClouds(float par1){
        if(Shaders.isActiveShader){
            this.c(par1);
        } else {
            this.renderCloudsFancy_MITE(par1);
        }
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glBlendFunc(II)V", shift = At.Shift.AFTER),
            method = "a(Lnet/minecraft/bfq;Lnet/minecraft/EntityPlayer;F)V")
    private void injectDrawBlockDamageTexture0(CallbackInfo callbackInfo){
        if(Shaders.isActiveShader) {
            Shaders.beginBlockDestroyProgress();
        }
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glPopMatrix()V", shift = At.Shift.AFTER),
            method = "a(Lnet/minecraft/bfq;Lnet/minecraft/EntityPlayer;F)V")
    private void injectDrawBlockDamageTexture1(CallbackInfo callbackInfo){
        if(Shaders.isActiveShader) {
            Shaders.endBlockDestroyProgress();
        }
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V", ordinal = 0, shift = At.Shift.AFTER),
            method = "drawSelectionBox")
    private void injectDrawSelectionBox0(CallbackInfo callbackInfo){
        if(Shaders.isActiveShader) {
            Shaders.disableTexture2D();
        }
    }

    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V", ordinal = 1, shift = At.Shift.AFTER),
            method = "drawSelectionBox")
    private void injectDrawSelectionBox1(CallbackInfo callbackInfo){
        if(Shaders.isActiveShader) {
            Shaders.enableTexture2D();
        }
    }


    @Shadow
    private bdd k;
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
