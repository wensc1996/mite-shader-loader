package net.wenscHuix.mitemod.optimize.gui;

import net.minecraft.*;
import net.wenscHuix.mitemod.MITEShaderLoader;
import net.wenscHuix.mitemod.shader.client.GuiShaders;

public class GuiPlusVideo extends awe {

    private awe parentGuiScreen;
    protected String screenTitle = "Plus Video Settings";
    private aul guiGameSettings;

    public GuiPlusVideo(awe par1GuiScreen, aul par2GameSettings) {
        this.parentGuiScreen = par1GuiScreen;
        this.guiGameSettings = par2GameSettings;
    }

    @Override
    public void A_() {
        this.screenTitle = bkb.a("Plus Video Settings");
        this.i.add(new aut(200, this.g / 2 - 100, this.h / 6 + 168, bkb.a("gui.done")));
        this.i.add(new aut(100, this.g / 2 - 155, this.h / 7, 300, 20, bkb.a("光影...")));
        this.i.add(new aut(101, this.g / 2 - 155, this.h / 7 + 25, 300, 20, bkb.a("粒子效果")));
        this.i.add(new aut(102, this.g / 2 - 155, this.h / 7 + 50, 150, 20, "动态光源: " + Config.dynamicLights));
    }


    @Override
    public void a(aut par1GuiButton) {
        if (par1GuiButton.h) {
            if (par1GuiButton.g == 100) {
                this.f.u.b();
                this.f.a(new GuiShaders(this));
            } else if (par1GuiButton.g == 101) {
                this.f.u.b();
                this.f.a(new GuiParticle(this, this.guiGameSettings));
            } else if (par1GuiButton.g == 102) {
                Config.dynamicLights = !Config.dynamicLights;
                par1GuiButton.f = "动态光源: " + Config.dynamicLights;
            } else if (par1GuiButton.g == 200) {
                this.f.u.b();
                this.f.a(this.parentGuiScreen);

                try {
                    Config.storeConfig();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void a(int par1, int par2, float par3) {
        this.e();
        this.a(this.o, "高级视频设置", this.g / 2, 16, 16777215);
        this.a(this.o, MITEShaderLoader.VERSION + "(by wensc,Huix)", this.g - 40, 10, 8421504);
        super.a(par1, par2, par3);
    }

}
