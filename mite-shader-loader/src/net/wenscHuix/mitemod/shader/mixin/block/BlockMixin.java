package net.wenscHuix.mitemod.shader.mixin.block;


import net.minecraft.Block;
import net.minecraft.IBlockAccess;
import net.wenscHuix.mitemod.shader.client.Shaders;
import net.wenscHuix.mitemod.shader.util.Utils;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Block.class)
public class BlockMixin {
    @Shadow
    public static Block getBlock(int block_id) {
        return null;
    }
    @Shadow
    @Final
    private boolean is_tree_leaves;

    public boolean isTreeLeaves() {
        return is_tree_leaves;
    }

    @Overwrite
    public final float i(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {

        if(Shaders.isActiveShader) {
            return par1IBlockAccess.isBlockNormalCube(par2, par3, par4) ? Shaders.blockAoLight : 1.0F;
        } else {
            int var5 = par1IBlockAccess.getBlockId(par2, par3, par4);
            if(var5 == 0) {
                return 1.0f;
            } else {
                if(BlockAccessor.getis_normal_cube_lookup()[var5]){
                    return 0.2f;
                } else {
                    if(getBlock(var5) != null) {
                        Block block = getBlock(var5);
                        if(Utils.get(block, "is_tree_leaves", boolean.class)) {
                            return 0.4f;
                        } else {
                            return 1f;
                        }
                    } else {
                        return 1f;
                    }
                }
            }
        }
    }

}
