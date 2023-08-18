package net.wenscHuix.mitemod.shader.mixin.render;

import net.minecraft.*;
import net.wenscHuix.mitemod.shader.client.Shaders;
import net.wenscHuix.mitemod.shader.client.ShadersTex;
import org.apache.commons.io.IOUtils;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

@Mixin(bif.class)
public class SimpleTextureMixin extends bia {
    @Shadow
    @Final
    private bjo b;

//    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/bip;a(ILjava/awt/image/BufferedImage;ZZ)I"), method = "a")
//    private int redirectLoadTexture(int var0, BufferedImage var1, boolean var2, boolean var3, bjp par1ResourceManager) {
//            if(Shaders.isActiveShader) {
//        //ShadersTex.loadSimpleTexture(this.b(), var4, var5, var6, par1ResourceManager, this.b, this.getMultiTexID());
//    } else {
//        bip.a(this.b(), var4, var5, var6);
//    }
//    }

    @Overwrite
    public void a(bjp par1ResourceManager) throws IOException {
        InputStream var2 = null;

        try {
            bjn var3 = par1ResourceManager.a(this.b);
            var2 = var3.b();
            if (this.b.generate_encoded_file && !Minecraft.inDevMode()) {
                this.b.generate_encoded_file = false;
                Minecraft.setErrorMessage("SimpleTexture: Error for " + this.b.a());
            }

            byte[] bytes;
            if (this.b.a().endsWith(".enc")) {
                bytes = IOUtils.toByteArray((InputStream)var2);
                this.rB(bytes);
                bytes[1] = 80;
                bytes[2] = 78;
                bytes[3] = 71;
                var2 = new ByteArrayInputStream(bytes);
            } else if (this.b.generate_encoded_file) {
                bytes = IOUtils.toByteArray((InputStream)var2);
                byte[] copy_of_bytes = new byte[bytes.length];

                System.arraycopy(bytes, 0, copy_of_bytes, 0, bytes.length);

                copy_of_bytes[1] = 0;
                copy_of_bytes[2] = 0;
                copy_of_bytes[3] = 0;
                this.rB(copy_of_bytes);
                String resource_path = this.b.a();
                if (resource_path.endsWith(".png")) {
                    resource_path = resource_path.substring(0, resource_path.length() - 4);
                }

                String output_path = "resourcepacks/MITE Resource Pack 1.6.4/assets/minecraft/" + resource_path + ".enc";
                System.out.print("Attempting to create encoded file (" + output_path + ")...");

                try {
                    FileOutputStream fos = new FileOutputStream(new File(output_path));
                    fos.write(copy_of_bytes, 0, copy_of_bytes.length);
                    fos.flush();
                    fos.close();
                    System.out.println("succeeded");
                } catch (Exception var14) {
                    System.out.println("failed");
                }

                var2 = new ByteArrayInputStream(bytes);
            }

            BufferedImage var4 = ImageIO.read(var2);
            boolean var5 = false;
            boolean var6 = false;
            if (var3.c()) {
                try {
                    bkw var7 = (bkw)var3.a("texture");
                    if (var7 != null) {
                        var5 = var7.a();
                        var6 = var7.b();
                    }
                } catch (RuntimeException var13) {
                    Minecraft.w().getLogAgent().logWarningException("Failed reading metadata of: " + this.b, var13);
                }
            }

            if(Shaders.isActiveShader) {
                ShadersTex.loadSimpleTexture(this.b(), var4, var5, var6, par1ResourceManager, this.b, this.getMultiTexID());
            } else {
                bip.a(this.b(), var4, var5, var6);
            }
        } finally {
            if (var2 != null) {
                var2.close();
            }

        }

    }


    @Shadow
    private void rB(byte[] bytes) {

    }
}
