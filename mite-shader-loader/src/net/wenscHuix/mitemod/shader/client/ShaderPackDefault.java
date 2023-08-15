package net.wenscHuix.mitemod.shader.client;

import java.io.InputStream;

public class ShaderPackDefault implements IShaderPack {
    public ShaderPackDefault() {
    }

    public void close() {
    }

    public InputStream getResourceAsStream(String resName) {
        return ShaderPackDefault.class.getResourceAsStream(resName);
    }
}
