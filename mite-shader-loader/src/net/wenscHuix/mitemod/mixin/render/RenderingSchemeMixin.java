package net.wenscHuix.mitemod.mixin.render;

import net.minecraft.Minecraft;
import net.minecraft.RenderingScheme;
import net.minecraft.TessellatorMITE;
import net.minecraft.bfq;
import net.wenscHuix.mitemod.shader.util.TessellatorExtra;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(RenderingScheme.class)
public class RenderingSchemeMixin {

    @Overwrite
    public static void setCurrent(int scheme_index) {
        if (getSchemeDescriptor(scheme_index) == null) {
            if (Minecraft.O != null) {
                Minecraft.O.getLogAgent().logWarning("Invalid rendering scheme (" + scheme_index + "), reverting to " + getSchemeDescriptor(1) + " (" + 1 + ")");
            }

            scheme_index = 1;
        } else {
            Minecraft.O.getLogAgent().logInfo("Rendering scheme: " + getSchemeDescriptor(scheme_index));
        }

//        if(Shaders.isActiveShader) {
            current = 0;
//        } else {
//            current = scheme_index;
//        }
        bfq.a = current == 0 ? new TessellatorExtra(2097152) : new TessellatorMITE();
    }
    @Shadow
    public static int current;
    @Shadow
    public static String getSchemeDescriptor(int scheme_index) {
        return null;
    }
}
