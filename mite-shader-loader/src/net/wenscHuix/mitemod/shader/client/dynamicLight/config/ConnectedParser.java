//package net.wenscHuix.mitemod.shader.client.dynamicLight.config;
//
//import net.minecraft.*;
//
//import java.lang.reflect.Array;
//import java.util.*;
//
//public class ConnectedParser
//{
//    private String context ;
//
//    public ConnectedParser(String context)
//    {
//        this.context = context;
//    }
//
//    public String parseName(String path) {
//        String s = path;
//        int i = path.lastIndexOf(47);
//
//        if (i >= 0) {
//            s = path.substring(i + 1);
//        }
//
//        int j = s.lastIndexOf(46);
//
//        if (j >= 0) {
//            s = s.substring(0, j);
//        }
//
//        return s;
//    }
//
//    public String parseBasePath(String path)
//    {
//        int i = path.lastIndexOf(47);
//        return i < 0 ? "" : path.substring(0, i);
//    }
//
//
//
//    public boolean isFullBlockName(String[] parts) {
//        if (parts.length < 2) {
//            return false;
//        } else {
//            String s = parts[1];
//            return s.length() < 1 ? false : (this.startsWithDigit(s) ? false : !s.contains("="));
//        }
//    }
//
//    public boolean startsWithDigit(String str) {
//        if (str == null) {
//            return false;
//        } else if (str.length() < 1) {
//            return false;
//        } else {
//            char c0 = str.charAt(0);
//            return Character.isDigit(c0);
//        }
//    }
//
//
//    public static Comparable parseValue(String str, Class<?> cls) {
//        if (cls == String.class) {
//            return str;
//        }
//        if (cls == Boolean.class) {
//            return Boolean.valueOf(str);
//        }
//        if (cls == Float.class) {
//            return Float.valueOf(str);
//        }
//        if (cls == Double.class) {
//            return Double.valueOf(str);
//        }
//        if (cls == Integer.class) {
//            return Integer.valueOf(str);
//        }
//        if (cls == Long.class) {
//            return Long.valueOf(str);
//        }
//        return null;
//    }
//
//
//
//    public BiomeBase findBiome(String biomeName) {
//        biomeName = biomeName.toLowerCase();
//
//        if (biomeName.equals("nether")) {
//            return BiomeBase.hell;
//        } else {
//            BiomeBase[] abiomegenbase = BiomeBase.biomeList;
//
//            for (int i = 0; i < abiomegenbase.length; ++i) {
//                BiomeBase biomegenbase = abiomegenbase[i];
//
//                if (biomegenbase != null) {
//                    String s = biomegenbase.biomeName.replace(" ", "").toLowerCase();
//
//                    if (s.equals(biomeName)) {
//                        return biomegenbase;
//                    }
//                }
//            }
//
//            return null;
//        }
//    }
//
//    public int parseInt(String str, int defVal) {
//        if (str == null) {
//            return defVal;
//        } else {
//            str = str.trim();
//            int i = Config.parseInt(str, -1);
//
//            if (i < 0) {
//                this.warn("Invalid number: " + str);
//                return defVal;
//            } else {
//                return i;
//            }
//        }
//    }
//
//    public int[] parseIntList(String str) {
//        if (str == null) {
//            return null;
//        } else {
//            List<Integer> list = new ArrayList();
//            String[] astring = Config.tokenize(str, " ,");
//
//            for (int i = 0; i < astring.length; ++i)
//            {
//                String s = astring[i];
//
//                if (s.contains("-"))
//                {
//                    String[] astring1 = Config.tokenize(s, "-");
//
//                    if (astring1.length != 2)
//                    {
//                        this.warn("Invalid interval: " + s + ", when parsing: " + str);
//                    }
//                    else
//                    {
//                        int k = Config.parseInt(astring1[0], -1);
//                        int l = Config.parseInt(astring1[1], -1);
//
//                        if (k >= 0 && l >= 0 && k <= l)
//                        {
//                            for (int i1 = k; i1 <= l; ++i1)
//                            {
//                                list.add(Integer.valueOf(i1));
//                            }
//                        }
//                        else
//                        {
//                            this.warn("Invalid interval: " + s + ", when parsing: " + str);
//                        }
//                    }
//                }
//                else
//                {
//                    int j = Config.parseInt(s, -1);
//
//                    if (j < 0)
//                    {
//                        this.warn("Invalid number: " + s + ", when parsing: " + str);
//                    }
//                    else
//                    {
//                        list.add(Integer.valueOf(j));
//                    }
//                }
//            }
//
//            int[] aint = new int[list.size()];
//
//            for (int j1 = 0; j1 < aint.length; ++j1)
//            {
//                aint[j1] = ((Integer)list.get(j1)).intValue();
//            }
//
//            return aint;
//        }
//    }
//
//    public EnumFacing parseFace(String str)
//    {
//        str = str.toLowerCase();
//
//        if (!str.equals("bottom") && !str.equals("down"))
//        {
//            if (!str.equals("top") && !str.equals("up"))
//            {
//                if (str.equals("north"))
//                {
//                    return EnumFacing.NORTH;
//                }
//                else if (str.equals("south"))
//                {
//                    return EnumFacing.SOUTH;
//                }
//                else if (str.equals("east"))
//                {
//                    return EnumFacing.EAST;
//                }
//                else if (str.equals("west"))
//                {
//                    return EnumFacing.WEST;
//                }
//                else
//                {
//                    Config.warn("Unknown face: " + str);
//                    return null;
//                }
//            }
//            else
//            {
//                return EnumFacing.UP;
//            }
//        }
//        else
//        {
//            return EnumFacing.DOWN;
//        }
//    }
//
//    public void dbg(String str)
//    {
//        Config.dbg("" + this.context + ": " + str);
//    }
//
//    public void warn(String str)
//    {
//        Config.warn("" + this.context + ": " + str);
//    }
//
//
//
//
//
//    public boolean parseBoolean(String str, boolean defVal)
//    {
//        if (str == null)
//        {
//            return defVal;
//        }
//        else
//        {
//            String s = str.toLowerCase().trim();
//
//            if (s.equals("true"))
//            {
//                return true;
//            }
//            else if (s.equals("false"))
//            {
//                return false;
//            }
//            else
//            {
//                this.warn("Invalid boolean: " + str);
//                return defVal;
//            }
//        }
//    }
//
//    public Boolean parseBooleanObject(String str)
//    {
//        if (str == null)
//        {
//            return null;
//        }
//        else
//        {
//            String s = str.toLowerCase().trim();
//
//            if (s.equals("true"))
//            {
//                return Boolean.TRUE;
//            }
//            else if (s.equals("false"))
//            {
//                return Boolean.FALSE;
//            }
//            else
//            {
//                this.warn("Invalid boolean: " + str);
//                return null;
//            }
//        }
//    }
//
//    public static int parseColor(String str, int defVal) {
//        if (str == null) {
//            return defVal;
//        } else {
//            str = str.trim();
//
//            try {
//                return Integer.parseInt(str, 16) & 16777215;
//            }
//            catch (NumberFormatException var3)
//            {
//                return defVal;
//            }
//        }
//    }
//
//    public static int parseColor4(String str, int defVal) {
//        if (str == null) {
//            return defVal;
//        } else {
//            str = str.trim();
//
//            try {
//                return (int)(Long.parseLong(str, 16) & -1L);
//            } catch (NumberFormatException var3) {
//                return defVal;
//            }
//        }
//    }
//}
