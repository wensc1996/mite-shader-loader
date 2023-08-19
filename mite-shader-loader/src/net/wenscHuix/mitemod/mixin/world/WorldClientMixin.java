//package net.wenscHuix.mitemod.mixin.world;
//
//import net.minecraft.*;
//import net.wenscHuix.mitemod.shader.client.dynamicLight.DynamicLights;
//import net.wenscHuix.mitemod.shader.util.BlockPos;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//
//@Mixin(bdd.class)
//public class WorldClientMixin extends World {
//
//    public WorldClientMixin(IDataManager par1ISaveHandler, String par2Str, WorldProvider par3WorldProvider, WorldSettings par4WorldSettings, MethodProfiler par5Profiler, IConsoleLogManager par6ILogAgent, long world_creation_time, long total_world_time) {
//        super(par1ISaveHandler, par2Str, par3WorldProvider, par4WorldSettings, par5Profiler, par6ILogAgent, world_creation_time, total_world_time);
//    }
//
//
//
//    public float i(int par1, int par2, int par3, int par4) {
//        float i1 = super.i(par1, par2, par3, par4);
//
//        return DynamicLights.getCombinedLight(new BlockPos(par1, par2, par3), (int) i1);
//    }
//
//    @Shadow
//    public Entity getEntityByID(int i) {
//        return null;
//    }
//    @Shadow
//    protected IChunkProvider createChunkProvider() {
//        return null;
//    }
//}
