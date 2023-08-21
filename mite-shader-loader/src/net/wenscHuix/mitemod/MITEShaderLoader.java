package net.wenscHuix.mitemod;


import net.wenscHuix.mitemod.mixin.MinecraftMixin;
import net.xiaoyu233.fml.AbstractMod;
import net.xiaoyu233.fml.classloading.Mod;
import net.xiaoyu233.fml.config.InjectionConfig;
import org.spongepowered.asm.mixin.MixinEnvironment;

@Mod(MixinEnvironment.Side.CLIENT)
public class MITEShaderLoader extends AbstractMod {

    @Override
    public int modVerNum() {
        return 9;
    }
    public static final String VERSION = "v0.0.9";


    @Override
    public void preInit() {

    }

    @Override
    public InjectionConfig getInjectionConfig() {
        return InjectionConfig.Builder.of("mite-shader-loader", MinecraftMixin.class.getPackage(), MixinEnvironment.Phase.INIT).setRequired().build();
    }

    @Override
    public void postInit() {

    }

    @Override
    public String modId() {
        return "mite-shader-loader";
    }


    @Override
    public String modVerStr() {
        return VERSION;
    }
}
