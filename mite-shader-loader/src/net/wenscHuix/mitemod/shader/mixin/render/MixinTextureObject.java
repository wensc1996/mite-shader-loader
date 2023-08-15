package net.wenscHuix.mitemod.shader.mixin.render;

import net.minecraft.bio;
import net.wenscHuix.mitemod.shader.client.MultiTexID;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(bio.class)
public interface MixinTextureObject {
    MultiTexID getMultiTexID();
}
