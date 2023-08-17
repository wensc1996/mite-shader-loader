package net.wenscHuix.mitemod.shader.util.fix;

import net.minecraft.EntityRenderer;
import org.lwjgl.input.Mouse;

import java.util.concurrent.Callable;

public class CallableMouseLocationFix implements Callable {
    private final int value;

    private final int value_;

    private final EntityRenderer theEntityRenderer;

    public CallableMouseLocationFix(EntityRenderer par1EntityRenderer, int par2, int par3) {
        this.theEntityRenderer = par1EntityRenderer;
        this.value = par2;
        this.value_ = par3;
    }

    @Override
    public Object call(){
        return String.format("Scaled: (%d, %d). Absolute: (%d, %d)", this.value, this.value_, Mouse.getX(), Mouse.getY());
    }
}
