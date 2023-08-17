package net.wenscHuix.mitemod.shader.mixin.gui;

import net.minecraft.*;
import net.minecraft.client.main.Main;
import net.wenscHuix.mitemod.shader.client.GuiShaders;
import net.wenscHuix.mitemod.shader.client.Shaders;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(avw.class)
public class GuiOptionsMixin extends awe {

    @Overwrite
    public void A_() {
        int var1 = 0;
        this.a = bkb.a("options.title");
        aun[] var2 = b;

        for (aun var5 : var2) {
            if (var5.a()) {
                this.i.add(new awk(var5.c(), this.g / 2 - 155 + var1 % 2 * 160, this.h / 6 - 12 + 24 * (var1 >> 1), var5, this.d.c(var5), this.d.a(var5)));
            } else {
                awl var6 = new awl(var5.c(), this.g / 2 - 155 + var1 % 2 * 160, this.h / 6 - 12 + 24 * (var1 >> 1), var5, this.d.c(var5));
                if (var5 == aun.l && this.f.f != null && this.f.f.getWorldInfo().isHardcoreModeEnabled()) {
                    var6.h = false;
                    var6.f = bkb.a("options.difficulty") + ": " + bkb.a("options.difficulty.hardcore");
                }

                if (var5 == aun.l) {
                    var6.h = false;
                }

                this.i.add(var6);
            }

            ++var1;
        }

        aut button_video_settings = new aut(101, this.g / 2 - 152, this.h / 6 + 96 - 6, 150, 20, bkb.a("options.video"));
        button_video_settings.h = !Main.is_MITE_DS;
        this.i.add(button_video_settings);
        this.i.add(new aut(100, this.g / 2 + 2, this.h / 6 + 96 - 6, 150, 20, bkb.a("options.controls")));
        this.i.add(new aut(102, this.g / 2 - 152, this.h / 6 + 120 - 6, 150 - 20 - 57, 20, bkb.a("options.language")));
        this.i.add(new aut(103, this.g / 2 + 2, this.h / 6 + 120 - 6, 150, 20, bkb.a("options.multiplayer.title")));
        this.i.add(new aut(105, this.g / 2 - 152, this.h / 6 + 144 - 6, 150, 20, bkb.a("options.resourcepack")));
        this.i.add(new aut(104, this.g / 2 + 2, this.h / 6 + 144 - 6, 150, 20, bkb.a("options.snooper.view")));
        this.i.add(new aut(200, this.g / 2 - 100, this.h / 6 + 168, bkb.a("gui.done")));

        this.i.add(new aut(190, this.g / 2 - 152 + 77, this.h / 6 + 120 - 6, 73, 20, bkb.a("光影...")));
    }

    @Inject(at = @At(value = "HEAD"), method = "a(Lnet/minecraft/aut;)V")
    private void injectActionPerformed(aut par1GuiButton, CallbackInfo callbackInfo){
        if (par1GuiButton.h) {
            if (par1GuiButton.g == 190 && !Main.is_MITE_DS) {
                this.f.u.b();
                this.f.a(new GuiShaders(ReflectHelper.dyCast(this), this.d));
            }
        }
    }

    @Shadow
    @Final
    private aul d;
    @Shadow
    protected String a;
    @Shadow
    @Final
    private static aun[] b;
}
