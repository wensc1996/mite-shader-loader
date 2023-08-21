package net.wenscHuix.mitemod.mixin.render;

import net.minecraft.*;
import net.wenscHuix.mitemod.shader.client.Shaders;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(bgk.class)
public class RenderEndermanMixin extends bhe {

    public RenderEndermanMixin(bbo par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }

    @Overwrite
    public void a(EntityEnderman par1EntityEnderman, double par2, double par4, double par6, float par8, float par9) {
        this.g.a = par1EntityEnderman.getCarried() > 0;
        this.g.b = par1EntityEnderman.isScreaming();
        if (par1EntityEnderman.isScreaming()) {
            double var10 = 0.02;
            par2 += this.h.nextGaussian() * var10;
            par6 += this.h.nextGaussian() * var10;
        }

        Shaders.beginMobEye();
        super.a(par1EntityEnderman, par2, par4, par6, par8, par9);
    }

    @Shadow
    private bbh g;
    @Shadow
    private Random h;
    @Shadow
    protected void setTextures() {

    }
    @Shadow
    protected bjo a(Entity entity) {
        return null;
    }
}
