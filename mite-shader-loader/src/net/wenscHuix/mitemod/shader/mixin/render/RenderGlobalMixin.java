package net.wenscHuix.mitemod.shader.mixin.render;

import net.minecraft.*;
import net.wenscHuix.mitemod.shader.client.Shaders;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
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
