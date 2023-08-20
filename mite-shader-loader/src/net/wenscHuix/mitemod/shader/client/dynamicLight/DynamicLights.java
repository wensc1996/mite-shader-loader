package net.wenscHuix.mitemod.shader.client.dynamicLight;

import net.minecraft.*;
import net.wenscHuix.mitemod.shader.client.dynamicLight.config.ShaderConfig;
import net.wenscHuix.mitemod.shader.util.BlockPos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicLights {
    private static DynamicLightsMap mapDynamicLights = new DynamicLightsMap();
    private static Map<Class, Integer> mapEntityLightLevels = new HashMap();
    private static Map<Item, Integer> mapItemLightLevels = new HashMap();
    private static long timeUpdateMs = 0L;
    private static boolean initialized;

    public static void entityAdded(Entity entityIn, bfl renderGlobal) {

    }

    public static void entityRemoved(Entity entityIn, bfl renderGlobal) {
        synchronized (mapDynamicLights) {
            DynamicLight dynamiclight = mapDynamicLights.remove(entityIn.entityId);

            if (dynamiclight != null) {
                dynamiclight.updateLitChunks(renderGlobal);
            }
        }
    }

    public static void update(bfl renderGlobal) {
        long i = System.currentTimeMillis();

        if (i >= timeUpdateMs + 50L) {
            timeUpdateMs = i;

            if (!initialized) {
                initialize();
            }

            synchronized (mapDynamicLights) {
                updateMapDynamicLights(renderGlobal);

//                System.out.println(mapDynamicLights.size());

                if (mapDynamicLights.size() > 0) {
                    List<DynamicLight> list = mapDynamicLights.valueList();


                    for (int j = 0; j < list.size(); ++j) {
                        DynamicLight dynamiclight = list.get(j);
                        dynamiclight.update(renderGlobal);
//                        System.out.println("renderGlobalif");
                    }
                }
            }
        }
    }

    private static void initialize() {
        initialized = true;
        mapEntityLightLevels.clear();
        mapItemLightLevels.clear();
//        String[] astring = ReflectorForge.getForgeModIds();
//
//        for (int i = 0; i < astring.length; ++i) {
//            String s = astring[i];
//
//            try {
//                ResourceLocation resourcelocation = new ResourceLocation(s, "optifine/dynamic_lights.properties");
//                InputStream inputstream = Config.getResourceStream(resourcelocation);
//                loadModConfiguration(inputstream, resourcelocation.toString(), s);
//            } catch (IOException var5) {
//                var5.printStackTrace();
//            }
//        }

        if (mapEntityLightLevels.size() > 0) {
            ShaderConfig.dbg("DynamicLights entities: " + mapEntityLightLevels.size());
        }

        if (mapItemLightLevels.size() > 0) {
            ShaderConfig.dbg("DynamicLights items: " + mapItemLightLevels.size());
        }
    }

//    private static void loadModConfiguration(InputStream in, String path, String modId) {
//        if (in != null) {
//            try {
//                Properties properties = new PropertiesOrdered();
//                properties.load(in);
//                in.close();
//                Config.dbg("DynamicLights: Parsing " + path);
//                ConnectedParser connectedparser = new ConnectedParser("DynamicLights");
//                loadModLightLevels(properties.getProperty("entities"), mapEntityLightLevels, new EntityClassLocator(), connectedparser, path, modId);
//                loadModLightLevels(properties.getProperty("items"), mapItemLightLevels, new ItemLocator(), connectedparser, path, modId);
//            }
//            catch (IOException var5)
//            {
//                Config.warn("DynamicLights: Error reading " + path);
//            }
//        }
//    }

//    private static void loadModLightLevels(String prop, Map mapLightLevels, IObjectLocator ol, ConnectedParser cp, String path, String modId) {
//        if (prop != null) {
//            String[] astring = Config.tokenize(prop, " ");
//
//            for (int i = 0; i < astring.length; ++i) {
//                String s = astring[i];
//                String[] astring1 = Config.tokenize(s, ":");
//
//                if (astring1.length != 2) {
//                    cp.warn("Invalid entry: " + s + ", in:" + path);
//                } else {
//                    String s1 = astring1[0];
//                    String s2 = astring1[1];
//                    String s3 = modId + ":" + s1;
//                    bjo resourceLocation = new bjo(s3);
//                    Object object = ol.getObject(resourceLocation);
//
//                    if (object == null) {
//                        cp.warn("Object not found: " + s3);
//                    } else {
//                        int j = cp.parseInt(s2, -1);
//
//                        if (j >= 0 && j <= 15) {
//                            mapLightLevels.put(object, new Integer(j));
//                        }
//                        else {
//                            cp.warn("Invalid light level: " + s);
//                        }
//                    }
//                }
//            }
//        }
//    }

    private static void updateMapDynamicLights(bfl renderGlobal) {
        World world = renderGlobal.getClientWorld();

        if (world != null) {
            for (Object list : world.loadedEntityList) {
                if (list instanceof Entity){
                    Entity entity = (Entity) list;
                    int i = getLightLevel(entity);
//                    System.out.println("updateMapDynamicLights： " + i);
                    if (i > 0) {
                        int j = entity.entityId;
                        DynamicLight dynamiclight = mapDynamicLights.get(j);

                        if (dynamiclight == null) {
                            dynamiclight = new DynamicLight(entity);
                            mapDynamicLights.put(j, dynamiclight);
                        }
                    } else {
                        int k = entity.entityId;
                        DynamicLight dynamiclight1 = mapDynamicLights.remove(k);

                        if (dynamiclight1 != null) {
                            dynamiclight1.updateLitChunks(renderGlobal);
                        }
                    }
                }
            }
        }
    }

    public static int getCombinedLight(BlockPos pos, int combinedLight) {
        double d0 = getLightLevel(pos);
        combinedLight = getCombinedLight(d0, combinedLight);
        return combinedLight;
    }

    public static int getCombinedLight(Entity entity, int combinedLight) {
        double d0 = getLightLevel(entity);
        combinedLight = getCombinedLight(d0, combinedLight);
        return combinedLight;
    }

    public static int getCombinedLight(double lightPlayer, int combinedLight) {
        if (lightPlayer > 0.0D) {
            int i = (int)(lightPlayer * 16.0D);
            int j = combinedLight & 255;

            if (i > j) {
                combinedLight = combinedLight & -256;
                combinedLight = combinedLight | i;
            }
        }

        return combinedLight;
    }

    public static double getLightLevel(BlockPos pos) {
        double d0 = 0.0D;

        synchronized (mapDynamicLights) {
            List<DynamicLight> list = mapDynamicLights.valueList();
            int i = list.size();

            for (int j = 0; j < i; ++j) {
                DynamicLight dynamiclight = (DynamicLight)list.get(j);
                int k = dynamiclight.getLastLightLevel();

                if (k > 0) {
                    double d1 = dynamiclight.getLastPosX();
                    double d2 = dynamiclight.getLastPosY();
                    double d3 = dynamiclight.getLastPosZ();
                    double d4 = (double)pos.x - d1;
                    double d5 = (double)pos.y - d2;
                    double d6 = (double)pos.z - d3;
                    double d7 = d4 * d4 + d5 * d5 + d6 * d6;

                    if (dynamiclight.isUnderwater() && !ShaderConfig.isClearWater()) {
                        k = ShaderConfig.limit(k - 2, 0, 15);
                        d7 *= 2.0D;
                    }

                    if (d7 <= 56.25D) {
                        double d8 = Math.sqrt(d7);
                        double d9 = 1.0D - d8 / 7.5D;
                        double d10 = d9 * (double)k;

                        if (d10 > d0)
                        {
                            d0 = d10;
                        }
                    }
                }
            }
        }

        return ShaderConfig.limit(d0, 0.0D, 15.0D);
    }

    public static int getLightLevel(ItemStack itemStack) {
        if (itemStack == null) {
            return 0;
        } else {
            Item item = itemStack.getItem();

            if (item instanceof ItemBlock) {
                ItemBlock itemblock = (ItemBlock) item;
                Block block = itemblock.getBlock();

                if (block != null) {
                    return block.getLightValue();
                }
            }

            if (item == Item.bucketAdamantiumLava || item == Item.bucketGoldLava || item == Item.bucketCopperLava || item == Item.bucketIronLava
                    || item == Item.bucketMithrilLava || item == Item.bucketAncientMetalLava || item == Item.bucketSilverLava) {
                return Block.lavaStill.getLightValue();
            } else if (item instanceof ItemCoin) {
                if (item == Item.coinAncientMetal){
                    return 10;
                } else if (item == Item.coinMithril){
                    return 12;
                } else if (item == Item.coinAdamantium){
                    return 14;
                }
                return 5;
            } else if (item != Item.blazeRod && item != Item.blazePowder) {
                if (item == Item.glowstone) {
                    return 8;
                } else if (item == Item.magmaCream) {
                    return 8;
                } else if (item == Item.netherStar) {
                    return Block.beacon.getLightValue() / 2;
                } else {
                    if (!mapItemLightLevels.isEmpty()) {
                        Integer integer = (Integer) mapItemLightLevels.get(item);

                        if (integer != null) {
                            return integer.intValue();
                        }
                    }

                    return 0;
                }
            } else {
                return 10;
            }
        }
    }

    public static int getLightLevel(Entity entity) {
        if (entity == ShaderConfig.getMinecraft().i && !ShaderConfig.isDynamicHandLight()) {
            return 0;
        } else {
//            if (entity instanceof EntityPlayer) {
//                EntityPlayer entityplayer = (EntityPlayer)entity;
//
//                if (entityplayer.isSpectator()) {
//                    return 0;
//                }
//            }

            if (entity.isBurning()) {
                return 15;
            } else {
                if (!mapEntityLightLevels.isEmpty()) {
                    Integer integer = mapEntityLightLevels.get(entity.getClass());

                    if (integer != null) {
                        return integer.intValue();
                    }
                }

                if (entity instanceof EntityFireball) {
                    return 15;
                } else if (entity instanceof EntityTNTPrimed) {
                    return 15;
                } else if (entity instanceof EntityBlaze) {
                    EntityBlaze entityblaze = (EntityBlaze)entity;
                    return entityblaze.func_70845_n() ? 15 : 10;
                } else if (entity instanceof EntityMagmaCube) {
                    EntityMagmaCube entitymagmacube = (EntityMagmaCube)entity;
                    return (double)entitymagmacube.squishFactor > 0.6D ? 13 : 8;
                } else  {
                    if (entity instanceof EntityCreeper)
                    {
                        EntityCreeper entitycreeper = (EntityCreeper)entity;

                        if ((double)entitycreeper.a(0.0F) > 0.001D)
                        {
                            return 15;
                        }
                    }

                    if (entity instanceof EntityLiving) {
                        EntityLiving entityLiving = (EntityLiving) entity;
                        ItemStack itemstack2 = entityLiving.getHeldItemStack();
                        int i = getLightLevel(itemstack2);
                        ItemStack itemstack1 = entityLiving.getEquipmentInSlot(4);
                        int j = getLightLevel(itemstack1);
                        return Math.max(i, j);
                    } else if (entity instanceof EntityItem) {
                        EntityItem entityitem = (EntityItem)entity;
                        ItemStack itemstack = getItemStack(entityitem);
                        return getLightLevel(itemstack);
                    } else {
                        return 0;
                    }
                }
            }
        }
    }

//    public static void removeLights(bfl renderGlobal) {
//        synchronized (mapDynamicLights) {
//            List<DynamicLight> list = mapDynamicLights.valueList();
//
//            for (int i = 0; i < list.size(); ++i) {
//                DynamicLight dynamiclight = (DynamicLight)list.get(i);
//                dynamiclight.updateLitChunks(renderGlobal);
//            }
//
//            mapDynamicLights.clear();
//        }
//    }

    public static void clear() {
        synchronized (mapDynamicLights) {
            mapDynamicLights.clear();
        }
    }

    public static int getCount() {
        synchronized (mapDynamicLights) {
            return mapDynamicLights.size();
        }
    }

    public static ItemStack getItemStack(EntityItem entityItem) {
        ItemStack itemstack = entityItem.getDataWatcher().getWatchableObjectItemStack(10);
        return itemstack;
    }
}
