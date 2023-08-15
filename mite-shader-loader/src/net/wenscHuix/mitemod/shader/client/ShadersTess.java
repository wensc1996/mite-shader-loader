package net.wenscHuix.mitemod.shader.client;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//



import net.minecraft.bfq;
import org.lwjgl.opengl.ARBVertexBufferObject;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.Arrays;

public class ShadersTess {
    public static final int vertexStride = 16;

    public ShadersTess() {
    }

    public static int draw(bfq tess) {
        if (!tess.isDrawing) {
            throw new IllegalStateException("Not tesselating!");
        } else {
            tess.isDrawing = false;
            int offs = 0;

            int vtc;
            while(offs < tess.vertexCount) {
                int realDrawMode;
                if (tess.drawMode == 7 && bfq.convertQuadsToTriangles) {
                    vtc = Math.min(tess.vertexCount - offs, tess.byteBuffer.capacity() / 96);
                    realDrawMode = 4;
                } else {
                    vtc = Math.min(tess.vertexCount - offs, tess.byteBuffer.capacity() / 64);
                    realDrawMode = tess.drawMode;
                }

                tess.intBuffer.clear();
                tess.intBuffer.put(tess.rawBuffer, offs * 16, vtc * 16);
                tess.byteBuffer.position(0);
                tess.byteBuffer.limit(vtc * 64);
                offs += vtc;
                if (tess.useVBO) {
                    tess.vboIndex = (tess.vboIndex + 1) % bfq.vboCount;
                    ARBVertexBufferObject.glBindBufferARB(34962, bfq.vertexBuffers.get(tess.vboIndex));
                    ARBVertexBufferObject.glBufferDataARB(34962, tess.byteBuffer, 35040);
                    if (tess.hasTexture) {
                        GL11.glTexCoordPointer(2, 5126, 64, 12L);
                        GL11.glEnableClientState(32888);
                    }

                    if (tess.hasBrightness) {
                        OpenGlHelper.setClientActiveTexture(OpenGlHelper.lightmapTexUnit);
                        GL11.glTexCoordPointer(2, 5122, 64, 28L);
                        GL11.glEnableClientState(32888);
                        OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
                    }

                    if (tess.hasColor) {
                        GL11.glColorPointer(4, 5121, 64, 20L);
                        GL11.glEnableClientState(32886);
                    }

                    if (tess.hasNormals) {
                        GL11.glNormalPointer(5126, 64, 32L);
                        GL11.glEnableClientState(32885);
                    }

                    GL11.glVertexPointer(3, 5126, 64, 0L);
                    GL11.glEnableClientState(32884);
                    preDrawArrayVBO(tess);
                } else {
                    if (tess.hasTexture) {
                        tess.floatBuffer.position(3);
                        GL11.glTexCoordPointer(2, 64, tess.floatBuffer);
                        GL11.glEnableClientState(32888);
                    }

                    if (tess.hasBrightness) {
                        OpenGlHelper.setClientActiveTexture(OpenGlHelper.lightmapTexUnit);
                        tess.shortBuffer.position(14);
                        GL11.glTexCoordPointer(2, 64, tess.shortBuffer);
                        GL11.glEnableClientState(32888);
                        OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
                    }

                    if (tess.hasColor) {
                        tess.byteBuffer.position(20);
                        GL11.glColorPointer(4, true, 64, tess.byteBuffer);
                        GL11.glEnableClientState(32886);
                    }

                    if (tess.hasNormals) {
                        tess.floatBuffer.position(8);
                        GL11.glNormalPointer(64, tess.floatBuffer);
                        GL11.glEnableClientState(32885);
                    }

                    tess.floatBuffer.position(0);
                    GL11.glVertexPointer(3, 64, tess.floatBuffer);
                    preDrawArray(tess);
                }

                GL11.glEnableClientState(32884);
                GL11.glDrawArrays(realDrawMode, 0, vtc);
            }

            GL11.glDisableClientState(32884);
            postDrawArray(tess);
            if (tess.hasTexture) {
                GL11.glDisableClientState(32888);
            }

            if (tess.hasBrightness) {
                OpenGlHelper.setClientActiveTexture(OpenGlHelper.lightmapTexUnit);
                GL11.glDisableClientState(32888);
                OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
            }

            if (tess.hasColor) {
                GL11.glDisableClientState(32886);
            }

            if (tess.hasNormals) {
                GL11.glDisableClientState(32885);
            }

            vtc = tess.rawBufferIndex * 4;
            tess.reset();
            return vtc;
        }
    }

    public static void preDrawArray(bfq tess) {
        if (Shaders.useMultiTexCoord3Attrib && tess.hasTexture) {
            GL13.glClientActiveTexture(33987);
            GL11.glTexCoordPointer(2, 64, (FloatBuffer)tess.floatBuffer.position(11));
            GL11.glEnableClientState(32888);
            GL13.glClientActiveTexture(33984);
        }

        if (Shaders.useMidTexCoordAttrib && tess.hasTexture) {
            ARBVertexShader.glVertexAttribPointerARB(Shaders.midTexCoordAttrib, 2, false, 64, (FloatBuffer)tess.floatBuffer.position(11));
            ARBVertexShader.glEnableVertexAttribArrayARB(Shaders.midTexCoordAttrib);
        }

        if (Shaders.useEntityAttrib) {
            ARBVertexShader.glVertexAttribPointerARB(Shaders.entityAttrib, 2, false, false, 64, (ShortBuffer)tess.shortBuffer.position(12));
            ARBVertexShader.glEnableVertexAttribArrayARB(Shaders.entityAttrib);
        }

    }

    public static void preDrawArrayVBO(bfq tess) {
        if (Shaders.useMultiTexCoord3Attrib && tess.hasTexture) {
            GL13.glClientActiveTexture(33987);
            GL11.glTexCoordPointer(2, 5126, 64, 44L);
            GL11.glEnableClientState(32888);
            GL13.glClientActiveTexture(33984);
        }

        if (Shaders.useMidTexCoordAttrib && tess.hasTexture) {
            ARBVertexShader.glVertexAttribPointerARB(Shaders.midTexCoordAttrib, 2, 5126, false, 64, 44L);
            ARBVertexShader.glEnableVertexAttribArrayARB(Shaders.midTexCoordAttrib);
        }

        if (Shaders.useEntityAttrib) {
            ARBVertexShader.glVertexAttribPointerARB(Shaders.entityAttrib, 2, 5122, false, 64, 24L);
            ARBVertexShader.glEnableVertexAttribArrayARB(Shaders.entityAttrib);
        }

    }

    public static void postDrawArray(bfq tess) {
        if (Shaders.useEntityAttrib) {
            ARBVertexShader.glDisableVertexAttribArrayARB(Shaders.entityAttrib);
        }

        if (Shaders.useMidTexCoordAttrib && tess.hasTexture) {
            ARBVertexShader.glDisableVertexAttribArrayARB(Shaders.midTexCoordAttrib);
        }

        if (Shaders.useMultiTexCoord3Attrib && tess.hasTexture) {
            GL13.glClientActiveTexture(33987);
            GL11.glDisableClientState(32888);
            GL13.glClientActiveTexture(33984);
        }

    }

    public static void addVertex(bfq tess, double parx, double pary, double parz) {
        int[] rawBuffer = tess.rawBuffer;
        int rbi = tess.rawBufferIndex;
        float fx = (float)(parx + tess.xOffset);
        float fy = (float)(pary + tess.yOffset);
        float fz = (float)(parz + tess.zOffset);
        if (rbi >= tess.bufferSize - 64) {
            if (tess.bufferSize >= 16777216) {
                if (tess.addedVertices % 4 == 0) {
                    tess.draw();
                    tess.isDrawing = true;
                }
            } else if (tess.bufferSize > 0) {
                tess.bufferSize *= 2;
                tess.rawBuffer = rawBuffer = Arrays.copyOf(tess.rawBuffer, tess.bufferSize);
                System.out.format("Expand tesselator buffer %d\n", tess.bufferSize);
            } else {
                tess.bufferSize = 65536;
                tess.rawBuffer = rawBuffer = new int[tess.bufferSize];
            }
        }

        if (tess.drawMode == 7) {
            int i = tess.addedVertices % 4;
            float[] vertexPos = tess.vertexPos;
            vertexPos[i * 4 + 0] = fx;
            vertexPos[i * 4 + 1] = fy;
            vertexPos[i * 4 + 2] = fz;
            if (i == 3) {
                float x1 = vertexPos[8] - vertexPos[0];
                float y1 = vertexPos[9] - vertexPos[1];
                float z1 = vertexPos[10] - vertexPos[2];
                float x2 = vertexPos[12] - vertexPos[4];
                float y2 = vertexPos[13] - vertexPos[5];
                float z2 = vertexPos[14] - vertexPos[6];
                float vnx = y1 * z2 - y2 * z1;
                float vny = z1 * x2 - z2 * x1;
                float vnz = x1 * y2 - x2 * y1;
                float lensq = vnx * vnx + vny * vny + vnz * vnz;
                float mult = (double)lensq != 0.0 ? (float)(1.0 / Math.sqrt((double)lensq)) : 1.0F;
                rawBuffer[rbi + -40] = rawBuffer[rbi + -24] = rawBuffer[rbi + -8] = Float.floatToRawIntBits(tess.normalX = vnx * mult);
                rawBuffer[rbi + -39] = rawBuffer[rbi + -23] = rawBuffer[rbi + -7] = Float.floatToRawIntBits(tess.normalY = vny * mult);
                rawBuffer[rbi + -38] = rawBuffer[rbi + -22] = rawBuffer[rbi + -6] = Float.floatToRawIntBits(tess.normalZ = vnz * mult);
                tess.hasNormals = true;
                tess.midTextureU = (Float.intBitsToFloat(rawBuffer[rbi + -45]) + Float.intBitsToFloat(rawBuffer[rbi + -29]) + Float.intBitsToFloat(rawBuffer[rbi + -13]) + (float)tess.textureU) / 4.0F;
                tess.midTextureV = (Float.intBitsToFloat(rawBuffer[rbi + -44]) + Float.intBitsToFloat(rawBuffer[rbi + -28]) + Float.intBitsToFloat(rawBuffer[rbi + -12]) + (float)tess.textureV) / 4.0F;
                rawBuffer[rbi + -37] = rawBuffer[rbi + -21] = rawBuffer[rbi + -5] = Float.floatToRawIntBits(tess.midTextureU);
                rawBuffer[rbi + -36] = rawBuffer[rbi + -20] = rawBuffer[rbi + -4] = Float.floatToRawIntBits(tess.midTextureV);
                if (bfq.convertQuadsToTriangles) {
                    System.arraycopy(rawBuffer, rbi - 48, rawBuffer, rbi, 16);
                    System.arraycopy(rawBuffer, rbi - 16, rawBuffer, rbi + 16, 16);
                    rbi += 32;
                    tess.rawBufferIndex = rbi;
                    tess.vertexCount += 2;
                }
            }
        }

        ++tess.addedVertices;
        rawBuffer[rbi + 0] = Float.floatToRawIntBits(fx);
        rawBuffer[rbi + 1] = Float.floatToRawIntBits(fy);
        rawBuffer[rbi + 2] = Float.floatToRawIntBits(fz);
        rawBuffer[rbi + 3] = Float.floatToRawIntBits((float)tess.textureU);
        rawBuffer[rbi + 4] = Float.floatToRawIntBits((float)tess.textureV);
        rawBuffer[rbi + 5] = tess.color;
        rawBuffer[rbi + 6] = Shaders.getEntityData();
        rawBuffer[rbi + 7] = tess.brightness;
        rawBuffer[rbi + 8] = Float.floatToRawIntBits(tess.normalX);
        rawBuffer[rbi + 9] = Float.floatToRawIntBits(tess.normalY);
        rawBuffer[rbi + 10] = Float.floatToRawIntBits(tess.normalZ);
        rawBuffer[rbi + 11] = Float.floatToRawIntBits(tess.midTextureU);
        rawBuffer[rbi + 12] = Float.floatToRawIntBits(tess.midTextureV);
        rbi += 16;
        tess.rawBufferIndex = rbi;
        ++tess.vertexCount;
    }
}
