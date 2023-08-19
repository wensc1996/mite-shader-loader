package net.wenscHuix.mitemod.shader.client.dynamicLight.config;

import net.minecraft.*;
import net.wenscHuix.mitemod.shader.util.EntityUtils;

public class EntityClassLocator implements IObjectLocator {


    public Object getObject(bjo loc) {
        Class oclass = EntityUtils.getEntityClassByName(loc.a());
        return oclass;
    }
}
