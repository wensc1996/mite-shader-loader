package net.wenscHuix.mitemod.shader.util;

import net.minecraft.Minecraft;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Utils {
    /***
     * 获取私有成员变量的值
     *
     */
    public static <F> F get(Object instance, String name, Class<F> f) {
        try {
            Field field = instance.getClass().getDeclaredField(name);
            field.setAccessible(true); // 参数值为true，禁止访问控制检查

            return (F) field.get(instance);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            Minecraft.setErrorMessage("获取${instance.toString()}私有变量$name 失败 ${e.getMessage()}");
        }
        return null;
    }

    /***
     * 设置私有成员变量的值
     *
     */
    static void set(Object instance, String fileName, Object value) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

        Field field = instance.getClass().getDeclaredField(fileName);
        field.setAccessible(true);
        field.set(instance, value);
    }

    /***
     * 访问私有方法
     *
     */
    static <F> F call(Object instance, String name, Class<F> r, Class[] parameterTypes, Object[] params) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = instance.getClass().getDeclaredMethod(name, parameterTypes);
        method.setAccessible(true);
        return (F) method.invoke(instance, params);
    }

    /**
     * 获取static变量
     * @param c
     * @param name
     * @param f
     * @return
     */
    static <F> F get(Class c, String name, Class<F> f) {
        Field[] fields = c.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getType() == f && field.getName().equals(name))
                    return (F) field.get(c);
            }
        } catch (Exception e) {
            Minecraft.setErrorMessage("获取${c.name}私有变量$name 失败 ${e.getMessage()}");
        }
        return null;
    }

    /**
     * 设置static变量
     * @param c
     * @param name
     * @param f
     */
    static void set(Class c, String name, Object f) throws IllegalAccessException {
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getName().equals(name))
                field.set(c, f);
        }
    }

    /**
     * 调用私有static方法
     * @param c
     * @param name
     * @param r
     * @param parameterTypes
     * @param params
     * @return
     */
    static <F> Object call(Class c, String name, Class<F> r, Class[] parameterTypes, Object[] params) {
        try {
            Method method = c.getMethod(name, parameterTypes);
            method.setAccessible(true);
            return (F) method.invoke(null, params);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            Minecraft.setErrorMessage("执行${c.name}私有方法$name 失败,参数 ${e.getMessage()}");
        }
        return null;
    }
}
