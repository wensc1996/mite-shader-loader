package net.wenscHuix.mitemod.shader.mixin.gui;

import net.minecraft.*;
import net.minecraft.client.main.Main;
import net.wenscHuix.mitemod.shader.client.GuiShaders;
import net.wenscHuix.mitemod.shader.client.Shaders;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(avw.class)
public class GuiOptionsMixin extends awe {
    @Shadow
    @Final
    private aul d;

    @Inject(at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 4, shift = At.Shift.AFTER), method = "A_")
    private void injectInit(CallbackInfo callbackInfo){
        this.i.add(new aut(190, this.g / 2 - 152 + 77, this.h / 6 + 120 - 6, 73, 20, bkb.a("光影")));
        this.i.add(new aut(102, this.g / 2 - 152, this.h / 6 + 120 - 6, 150 - 20 - 57, 20, bkb.a("光影")));
        this.buttonList.add(new GuiButton(, I18n.getString("options.language")));
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
}
