package net.wenscHuix.mitemod.shader.mixin.render;

import net.minecraft.bfq;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(bfq.class)
public interface TessellatorAccessor {
    @Accessor("A")
    public void setuseVBO(boolean val);

    @Accessor("A")
    public boolean getuseVBO();

    @Accessor("c")
    public static boolean gettryVBO() {
        throw new AssertionError();
    }
}
