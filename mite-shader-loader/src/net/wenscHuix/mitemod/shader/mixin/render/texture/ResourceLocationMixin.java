package net.wenscHuix.mitemod.shader.mixin.render.texture;

import net.minecraft.bjo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(bjo.class)
public class ResourceLocationMixin {
    @Overwrite
    public void verifyExistence() {

    }
}
