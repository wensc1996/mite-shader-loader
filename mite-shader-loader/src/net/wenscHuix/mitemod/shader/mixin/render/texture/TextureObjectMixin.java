package net.wenscHuix.mitemod.shader.mixin.render.texture;

import net.minecraft.bio;
import net.minecraft.bjp;
import net.wenscHuix.mitemod.shader.client.MultiTexID;
import org.spongepowered.asm.mixin.Mixin;

import java.io.IOException;

@Mixin(bio.class)
public interface TextureObjectMixin {
    void a(bjp var1) throws IOException;
    MultiTexID getMultiTexID();
}
