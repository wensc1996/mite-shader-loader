package net.wenscHuix.mitemod.shader.client.dynamicLight.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraft.*;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Config {
    private static final Logger LOGGER = LogManager.getLogger();
    private static Minecraft minecraft = Minecraft.w();

    public static Minecraft getMinecraft() {
        return minecraft;
    }

    public static boolean isDynamicLights() {
        return true;
    }

    public static boolean isDynamicLightsFast() {
        return false;
    }

    public static boolean isDynamicHandLight() {
        return false;
    }

    public static boolean isClearWater() {
        return false;
    }

    public static String[] tokenize(String p_tokenize_0_, String p_tokenize_1_)
    {
        StringTokenizer stringtokenizer = new StringTokenizer(p_tokenize_0_, p_tokenize_1_);
        List list = new ArrayList();

        while (stringtokenizer.hasMoreTokens())
        {
            String s = stringtokenizer.nextToken();
            list.add(s);
        }

        String[] astring = (String[])((String[])list.toArray(new String[list.size()]));
        return astring;
    }

    public static int parseInt(String p_parseInt_0_, int p_parseInt_1_)
    {
        try
        {
            if (p_parseInt_0_ == null)
            {
                return p_parseInt_1_;
            }
            else
            {
                p_parseInt_0_ = p_parseInt_0_.trim();
                return Integer.parseInt(p_parseInt_0_);
            }
        }
        catch (NumberFormatException var3)
        {
            return p_parseInt_1_;
        }
    }

    public static int limit(int p_limit_0_, int p_limit_1_, int p_limit_2_) {
        return p_limit_0_ < p_limit_1_ ? p_limit_1_ : (p_limit_0_ > p_limit_2_ ? p_limit_2_ : p_limit_0_);
    }

    public static float limit(float p_limit_0_, float p_limit_1_, float p_limit_2_) {
        return p_limit_0_ < p_limit_1_ ? p_limit_1_ : (p_limit_0_ > p_limit_2_ ? p_limit_2_ : p_limit_0_);
    }

    public static double limit(double p_limit_0_, double p_limit_2_, double p_limit_4_) {
        return p_limit_0_ < p_limit_2_ ? p_limit_2_ : (p_limit_0_ > p_limit_4_ ? p_limit_4_ : p_limit_0_);
    }

    public static float limitTo1(float p_limitTo1_0_) {
        return p_limitTo1_0_ < 0.0F ? 0.0F : (p_limitTo1_0_ > 1.0F ? 1.0F : p_limitTo1_0_);
    }


    public static void dbg(String dbg) {
        LOGGER.info("[Shader] " + dbg);
    }

    public static void warn(String warn) {
        LOGGER.warn("[Shader] " + warn);
    }

    public static void error(String error) {
        LOGGER.error("[Shader] " + error);
    }
}
