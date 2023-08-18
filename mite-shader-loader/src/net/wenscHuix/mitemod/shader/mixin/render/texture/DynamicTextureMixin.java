package net.wenscHuix.mitemod.shader.mixin.render.texture;

import net.minecraft.bia;
import net.minecraft.bib;
import net.minecraft.bip;
import net.minecraft.bjp;
import net.wenscHuix.mitemod.shader.client.Shaders;
import net.wenscHuix.mitemod.shader.client.ShadersTex;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(bib.class)
public class DynamicTextureMixin extends bia {
    @Shadow
    @Final
    private int[] b;
    @Shadow
    @Final
    private int c;
    @Shadow
    @Final
    private int d;

    @Shadow
    public void a(bjp bjp) {
    }

    @ModifyVariable(at = @At(value = "FIELD" , shift = At.Shift.AFTER, ordinal = 1), method = "<init>(II)V", argsOnly = true, index = 1)
    private int injectInit(int par1){
        return par1 * 3;
    }


    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/bip;a(III)V"), method = "<init>(II)V")
    private void redirectInit(int id, int par1, int par2){
        //if(Shaders.isActiveShader) {
            ShadersTex.initDynamicTexture(id, par1 / 3, par2, ReflectHelper.dyCast(this));
//        } else {
//            bip.a(id, par1 / 3, par2);
//        }
    }

    @Overwrite
    public void a() {
        //if(Shaders.isActiveShader) {
            ShadersTex.updateDynamicTexture(this.b(), this.b, this.c, this.d, ReflectHelper.dyCast(this));
//        } else {
//            bip.a(this.b(), this.b, this.c, this.d);
//        }
    }

}
