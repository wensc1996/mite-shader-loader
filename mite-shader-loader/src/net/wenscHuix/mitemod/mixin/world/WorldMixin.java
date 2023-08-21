package net.wenscHuix.mitemod.mixin.world;

import net.minecraft.Block;
import net.minecraft.EnumSkyBlock;
import net.minecraft.World;
import net.wenscHuix.mitemod.shader.client.dynamicLight.DynamicLights;
import net.wenscHuix.mitemod.shader.client.dynamicLight.config.ShaderConfig;
import net.wenscHuix.mitemod.shader.util.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(World.class)
public abstract class WorldMixin {
    @Shadow public abstract Block getBlock(int[] coords);

    @Overwrite
    public final int h(int par1, int par2, int par3, int par4) {
        int var5 = this.a(EnumSkyBlock.Sky, par1, par2, par3);
        int var6 = this.a(EnumSkyBlock.Block, par1, par2, par3);
        if (var6 < par4) {
            var6 = par4;
        }

        int cout = var5 << 20 | var6 << 4;
        if(ShaderConfig.isDynamicLights()){
            cout = DynamicLights.getCombinedLight(new BlockPos(par1, par2, par3), cout);
        }
        return cout;
    }

    @Final
    public Block getBlock(BlockPos pos){
        return this.getBlock(pos.x, pos.y, pos.z);
    }

    @Shadow
    @Final
    public Block getBlock(int x, int y, int z) {
        return null;
    }
    @Shadow
    public final int a(EnumSkyBlock par1EnumSkyBlock, int par2, int par3, int par4) {
        return 0;
    }
}
