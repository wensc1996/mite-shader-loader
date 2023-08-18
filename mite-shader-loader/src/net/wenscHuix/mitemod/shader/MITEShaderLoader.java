package net.wenscHuix.mitemod.shader;


import net.wenscHuix.mitemod.shader.mixin.MinecraftMixin;
import net.xiaoyu233.fml.AbstractMod;
import net.xiaoyu233.fml.classloading.Mod;
import net.xiaoyu233.fml.config.InjectionConfig;
import org.spongepowered.asm.mixin.MixinEnvironment;

@Mod(MixinEnvironment.Side.CLIENT)
public class MITEShaderLoader extends AbstractMod {
    public static final String VERSION = "v0.0.3";

    public MITEShaderLoader() {
    }

    public void preInit() {
    }


    @Override
    public InjectionConfig getInjectionConfig() {
        return InjectionConfig.Builder.of("mite-shader-loader", MinecraftMixin.class.getPackage(), MixinEnvironment.Phase.INIT).setRequired().build();
    }


    public void postInit() {
//        super.postInit();
    }


    @Override
    public String modId() {
        return "mite-shader-loader";
    }

    @Override
    public int modVerNum() {
        return 3;
    }

    @Override
    public String modVerStr() {
        return VERSION;
    }
}
