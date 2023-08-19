package net.wenscHuix.mitemod.shader.client.dynamicLight.config;

import net.minecraft.*;

public class ItemLocator implements IObjectLocator {

    @Override
    public Object getObject(bjo var1) {
        for (Item item : Item.itemsList) {
            if (item.getUnlocalizedName().equals(var1.toString())) {
                return item;
            }
        }

        return null;
    }
}
