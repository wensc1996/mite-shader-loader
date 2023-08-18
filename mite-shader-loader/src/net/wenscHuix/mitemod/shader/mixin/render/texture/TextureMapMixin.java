package net.wenscHuix.mitemod.shader.mixin.render.texture;

import com.google.common.collect.Maps;
import net.minecraft.*;
import net.wenscHuix.mitemod.shader.client.Shaders;
import net.wenscHuix.mitemod.shader.client.ShadersTex;
import net.wenscHuix.mitemod.shader.util.TextureUtilExtra;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Mixin(bik.class)
public class TextureMapMixin extends bia {

    public int atlasWidth;
    public int atlasHeight;

    @Overwrite
    public void b(bjp par1ResourceManager) {
        int var2 = Minecraft.y();
        big var3 = new big(var2, var2, true);
        this.f.clear();
        this.d.clear();
        Iterator var4 = this.e.entrySet().iterator();

        bil var17;
        while(var4.hasNext()) {
            Map.Entry var5 = (Map.Entry)var4.next();
            bjo var6 = new bjo((String)var5.getKey(), false);
            var17 = (bil)var5.getValue();
            bjo var8 = new bjo(var6.b(), String.format("%s/%s%s", this.h, var6.a(), ".png"), false);

            try {
                if(Shaders.isActiveShader) {
                    if (!var17.load(par1ResourceManager, var8)) {
                        continue;
                    }
                } else {
                    var17.a(par1ResourceManager.a(var8));
                }
            } catch (RuntimeException var14) {
                Minecraft.w().getLogAgent().logSevere(String.format("Unable to parse animation metadata from %s: %s", var8, var14.getMessage()));
                continue;
            } catch (IOException var15) {
                String error_message = "Missing resource: " + var8.a();
                Minecraft.w().getLogAgent().logSevere(error_message);
                Minecraft.setErrorMessage(error_message, false);
                continue;
            }

            var3.a(var17);
        }

        var3.a(this.i);

        try {
            var3.c();
        } catch (bij var13) {
            throw var13;
        }

        if(Shaders.isActiveShader) {
            ShadersTex.setupTextureMap(var3.a(), var3.b(), var3, ReflectHelper.dyCast(this));
        } else {
            bip.a(this.b(), var3.a(), var3.b());
        }

        HashMap var15 = Maps.newHashMap(this.e);
        Iterator var16 = var3.d().iterator();

        while(var16.hasNext()) {
            var17 = (bil)var16.next();
            String var18 = var17.g();
            var15.remove(var18);
            this.f.put(var18, var17);

            try {
                if(Shaders.isActiveShader) {
                    ShadersTex.updateTextureMap(var17.a(0), var17.a(), var17.b(), var17.h(), var17.i(), false, false);
                } else {
                    bip.a(var17.a(0), var17.a(), var17.b(), var17.h(), var17.i(), false, false);
                }
            } catch (Throwable var12) {
                CrashReport var9 = CrashReport.makeCrashReport(var12, "Stitching texture atlas");
                CrashReportSystemDetails var10 = var9.makeCategory("Texture being stitched together");
                var10.addCrashSection("Atlas path", this.h);
                var10.addCrashSection("Sprite", var17);
                throw new ReportedException(var9);
            }

            if (var17.m()) {
                this.d.add(var17);
            } else {
                var17.l();
            }
        }

        var16 = var15.values().iterator();

        while(var16.hasNext()) {
            var17 = (bil)var16.next();
            var17.a(this.i);
        }
    }


    @Overwrite
    public void c() {
        if(Shaders.isActiveShader) {
            ShadersTex.updatingTex = this.getMultiTexID();
        }

//        Utils.call(bip.class, "b", new Class[]{Integer.class}, new Object[]{this.b()});

        TextureUtilExtra.bindTexture(this.b());
        Iterator var1 = this.d.iterator();

        while(var1.hasNext()) {
            bil var2 = (bil)var1.next();
            var2.j();
        }

        if(Shaders.isActiveShader) {
            ShadersTex.updatingTex = null;
        }

    }

    @Shadow
    @Final
    public static bjo b;
    @Shadow
    @Final
    public static bjo c;
    @Shadow
    @Final
    private List d;
    @Shadow
    @Final
    private Map e;
    @Shadow
    @Final
    private Map f;
    @Shadow
    @Final
    private int g;
    @Shadow
    @Final
    private String h;
    @Shadow
    @Final
    private bil i;
    @Shadow
    public void a(bjp bjp) {

    }
}
