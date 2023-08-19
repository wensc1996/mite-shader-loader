package net.wenscHuix.mitemod.shader.client.dynamicLight;

import net.minecraft.*;
import net.wenscHuix.mitemod.shader.client.dynamicLight.config.Config;
import net.wenscHuix.mitemod.shader.util.BlockPos;

import java.util.HashSet;
import java.util.Set;

public class DynamicLight {
    private Entity entity;
    private double offsetY;
    private double lastPosX = -2.147483648E9D;
    private double lastPosY = -2.147483648E9D;
    private double lastPosZ = -2.147483648E9D;
    private int lastLightLevel = 0;
    private boolean underwater = false;
    private long timeCheckMs = 0L;
    private Set<BlockPos> setLitChunkPos = new HashSet();

    public DynamicLight(Entity entity) {
        this.entity = entity;
        this.offsetY = (double)entity.getEyeHeight();
    }

    public void update(bfl renderGlobal) {
        if (Config.isDynamicLightsFast()) {
            long i = System.currentTimeMillis();

            if (i < this.timeCheckMs + 500L) {
                return;
            }

            this.timeCheckMs = i;
        }

        double d6 = this.entity.posX - 0.5D;
        double d0 = this.entity.posY - 0.5D + this.offsetY;
        double d1 = this.entity.posZ - 0.5D;
        int j = DynamicLights.getLightLevel(this.entity);
        double d2 = d6 - this.lastPosX;
        double d3 = d0 - this.lastPosY;
        double d4 = d1 - this.lastPosZ;
        double d5 = 0.1D;

        if (Math.abs(d2) > d5 || Math.abs(d3) > d5 || Math.abs(d4) > d5 || this.lastLightLevel != j) {
            this.lastPosX = d6;
            this.lastPosY = d0;
            this.lastPosZ = d1;
            this.lastLightLevel = j;
            this.underwater = false;
            World world = renderGlobal.getClientWorld();

            if (world != null) {
                Block block = world.getBlock(MathHelper.floor_double(d6), MathHelper.floor_double(d0), MathHelper.floor_double(d1));
                this.underwater = block == Block.waterStill;
            }

            Set<BlockPos> set = new HashSet();

            if (j > 0) {
                for (bfa bfa : renderGlobal.o) {
                    this.updateChunkLight(bfa, this.setLitChunkPos, set);
                }
            }

            this.updateLitChunks(renderGlobal);
            this.setLitChunkPos = set;
        }
    }

//    private BlockPos getChunkPos(bfa renderChunk, BlockPos pos, EnumFacing facing) {
//        if (renderChunk != null){
//            return renderChunk.isRenderingCoords(pos.x, pos.y, pos.z) ? pos : pos.offset(facing, 16);
//        }
//        return pos.offset(facing, 16);
//    }

    private void updateChunkLight(bfa worldRenderer, Set<BlockPos> setPrevPos, Set<BlockPos> setNewPos) {
        if (worldRenderer != null) {
            worldRenderer.q = true;

            BlockPos blockpos = new BlockPos(worldRenderer.getX(), worldRenderer.getY(), worldRenderer.getZ());

            if (setPrevPos != null) {
                setPrevPos.remove(blockpos);
            }

            if (setNewPos != null) {
                setNewPos.add(blockpos);
            }
        }
    }

    public void updateLitChunks(bfl renderGlobal) {
        for (BlockPos blockpos : this.setLitChunkPos) {
            for (bfa bfa : renderGlobal.o) {
                this.updateChunkLight(bfa, null, null);
            }
        }
    }

    public Entity getEntity() {
        return this.entity;
    }

    public double getLastPosX() {
        return this.lastPosX;
    }

    public double getLastPosY() {
        return this.lastPosY;
    }

    public double getLastPosZ() {
        return this.lastPosZ;
    }

    public int getLastLightLevel() {
        return this.lastLightLevel;
    }

    public boolean isUnderwater() {
        return this.underwater;
    }

    public double getOffsetY() {
        return this.offsetY;
    }

    public String toString() {
        return "Entity: " + this.entity + ", offsetY: " + this.offsetY;
    }
}
