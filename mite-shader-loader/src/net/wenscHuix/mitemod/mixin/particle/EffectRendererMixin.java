package net.wenscHuix.mitemod.mixin.particle;

import net.minecraft.*;
import net.wenscHuix.mitemod.optimize.gui.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(beh.class)
public class EffectRendererMixin {



    @Overwrite
    public void addBlockDestroyEffects(int x, int y, int z, int block_id, int metadata, int aux_data) {
        if(Config.blockDestroyEffects){
            this.addBlockDestroyEffects(x, y, z, block_id, metadata, aux_data, (AxisAlignedBB)null);
        }
    }

    @Shadow
    public void addBlockDestroyEffects(int x, int y, int z, int block_id, int metadata, int aux_data, AxisAlignedBB bounds_of_exclusion) {

    }

    @Shadow
    protected World a;
    @Shadow
    public void a(beg par1EntityFX) {

    }
}
