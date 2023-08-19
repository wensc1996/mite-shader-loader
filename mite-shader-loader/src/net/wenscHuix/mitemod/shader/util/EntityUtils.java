package net.wenscHuix.mitemod.shader.util;

import net.minecraft.Entity;
import net.minecraft.EntityTypes;
import net.wenscHuix.mitemod.shader.client.dynamicLight.config.Config;

import java.util.HashMap;
import java.util.Map;

public class EntityUtils {
    private static final Map<String, Class> mapClassByName = new HashMap();

    public static Class getEntityClassByName(String name) {
        Class oclass = (Class)mapClassByName.get(name);
        return oclass;
    }

    static
    {
        for (int i = 0; i < 1000; ++i) {
            Class oclass = EntityTypes.getClassFromID(i);

            if (oclass != null) {
                String s = EntityTypes.getStringFromID(i);

                if (s != null) {

                    if (mapClassByName.containsKey(s)) {
                        Config.warn("Duplicate entity name: " + s + ", class1: " + mapClassByName.get(s) + ", class2: " + oclass);
                    }

                    mapClassByName.put(s, oclass);
                }
            }
        }
    }
}
