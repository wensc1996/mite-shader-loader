package net.wenscHuix.mitemod.shader.client;

import net.minecraft.src.Tessellator;
import net.minecraft.src.gui.GuiSlot;

import java.util.ArrayList;

public class GuiSlotShaders extends GuiSlot {
    public ArrayList shaderslist;
    private int scrollBarX;
    final GuiShaders shadersGui;

    public GuiSlotShaders(GuiShaders par1GuiShaders) {
        super(par1GuiShaders.getMc(), par1GuiShaders.width / 2 + 20, par1GuiShaders.height, 40, par1GuiShaders.height - 70, 16);
        this.scrollBarX = par1GuiShaders.width / 2 + 14;
        this.shadersGui = par1GuiShaders;
        this.shaderslist = Shaders.listofShaders();
    }

    public void updateList() {
        this.shaderslist = Shaders.listofShaders();
    }

    public int getSize() {
        return this.shaderslist.size();
    }

    public void elementClicked(int par1, boolean par2) {
        Shaders.setShaderPack((String)this.shaderslist.get(par1));
        this.shadersGui.needReinit = false;
        Shaders.loadShaderPack();
        Shaders.uninit();
    }

    public boolean isSelected(int par1) {
        return ((String)this.shaderslist.get(par1)).equals(Shaders.currentshadername);
    }

    public int getScrollBarX() {
        return this.scrollBarX;
    }

    public int getContentHeight() {
        return this.getSize() * 18;
    }

    public void drawBackground() {
        this.shadersGui.drawDefaultBackground();
    }

    public void overlayBackground(int par1, int par2, int par3, int par4) {
    }

    public void drawContainerBackground(Tessellator tess) {
    }

    public void drawSlot(int par1, int par2, int par3, int par4, Tessellator par5Tessellator) {
        this.shadersGui.drawCenteredString((String)this.shaderslist.get(par1), this.scrollBarX / 2, par3 + 1, 16777215);
    }
}
