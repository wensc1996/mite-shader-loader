//package net.wenscHuix.mitemod.mixin.particle;
//
//import net.minecraft.*;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Overwrite;
//import org.spongepowered.asm.mixin.Shadow;
//
//@Mixin(beh.class)
//public class EffectRendererMixin {
//
//    @Shadow
//    protected World a;
//    @Shadow
//    public void a(beg par1EntityFX) {
//
//    }
//    @Overwrite
//    public void addBlockDestroyEffects(int x, int y, int z, int block_id, int metadata, int aux_data, AxisAlignedBB bounds_of_exclusion) {
//        if (block_id != 0) {
//            Block block = Block.getBlock(block_id);
//            long visible_on_tick = this.a.getTotalWorldTime() + 1L;
//            boolean was_not_legal = BitHelper.isBitSet(aux_data, bfl.SFX_2001_WAS_NOT_LEGAL);
//            block.setBlockBoundsBasedOnStateAndNeighbors(this.a, x, y, z);
//            int index = Minecraft.getThreadIndex();
//            double min_x = MathHelper.clamp_double(block.getBlockBoundsMinX(index), 0.125, 0.875);
//            double max_x = MathHelper.clamp_double(block.getBlockBoundsMaxX(index), 0.125, 0.875);
//            double min_y = MathHelper.clamp_double(block.getBlockBoundsMinY(index), 0.125, 0.875);
//            double max_y = MathHelper.clamp_double(block.getBlockBoundsMaxY(index), 0.125, 0.875);
//            double min_z = MathHelper.clamp_double(block.getBlockBoundsMinZ(index), 0.125, 0.875);
//            double max_z = MathHelper.clamp_double(block.getBlockBoundsMaxZ(index), 0.125, 0.875);
//            double range_x = max_x - min_x;
//            double range_y = max_y - min_y;
//            double range_z = max_z - min_z;
//            int num_divisions_x = 2 + MathHelper.ceiling_double_int(range_x * 2.0);
//            int num_divisions_y = 2 + MathHelper.ceiling_double_int(range_y * 2.0);
//            int num_divisions_z = 2 + MathHelper.ceiling_double_int(range_z * 2.0);
//
//            for(int var8 = 0; var8 < num_divisions_x; ++var8) {
//                for(int var9 = 0; var9 < num_divisions_y; ++var9) {
//                    for(int var10 = 0; var10 < num_divisions_z; ++var10) {
//                        double var11 = (double)x + min_x + range_x * (double)var8 / (double)(num_divisions_x - 1);
//                        double var13 = (double)y + min_y + range_y * (double)var9 / (double)(num_divisions_y - 1);
//                        double var15 = (double)z + min_z + range_z * (double)var10 / (double)(num_divisions_z - 1);
//                        if (bounds_of_exclusion == null || !(var11 >= bounds_of_exclusion.minX) || !(var11 < bounds_of_exclusion.maxX) || !(var13 >= bounds_of_exclusion.minY) || !(var13 < bounds_of_exclusion.maxY) || !(var15 >= bounds_of_exclusion.minZ) || !(var15 < bounds_of_exclusion.maxZ)) {
//                            double motion_x = var11 - (double)x - (max_x + min_x) * 0.5;
//                            double motion_y = var13 - (double)y - (max_y + min_y) * 0.5;
//                            double motion_z = var15 - (double)z - (max_z + min_z) * 0.5;
//                            double scaler_x = 1.0 / MathHelper.clamp_double(block.getBlockBoundsMaxX(index) - block.getBlockBoundsMinX(index), 0.8, 1.0);
//                            double scaler_z = 1.0 / MathHelper.clamp_double(block.getBlockBoundsMaxZ(index) - block.getBlockBoundsMinZ(index), 0.8, 1.0);
//                            beg fx = (new bes(this.a, var11, var13, var15, motion_x, motion_y, motion_z, block, metadata)).a(x, y, z).setVisibleOnTick(visible_on_tick);
//                            if (was_not_legal) {
////                                fx.motionX *= 0.699999988079071;
////                                fx.motionZ *= 0.699999988079071;
////                                fx.motionY *= 0.30000001192092896;
//                            }
//
////                            fx.motionX *= scaler_x;
////                            fx.motionZ *= scaler_z;
//                            this.a((beg)fx);
//                        }
//                    }
//                }
//            }
//
//        }
//    }
//}
