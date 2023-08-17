package net.wenscHuix.mitemod.shader.mixin.render.texture;

import net.minecraft.Minecraft;
import net.minecraft.bjo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(bjo.class)
public class ResourceLocationMixin {
    @Overwrite
    public void verifyExistence() {

    }
}
