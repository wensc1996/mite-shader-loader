package net.wenscHuix.mitemod.shader.mixin.render;

import net.minecraft.*;
import net.wenscHuix.mitemod.shader.client.Shaders;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(bfr.class)
public class RenderBlocksMixin {

    @ModifyConstant(constant = @Constant(floatValue = 0.5F, ordinal = 0), method = {"u", "o", "a(Lnet/minecraft/Block;Lnet/minecraft/World;IIII)V",
            "d(Lnet/minecraft/Block;IIIFFF)Z", "e(Lnet/minecraft/Block;IIIFFF)Z", "t"})
    private static float injectedRenderBlockBed0(float value) {
        return Shaders.blockLightLevel05;
    }

    @ModifyConstant(constant = @Constant(floatValue = 0.8F, ordinal = 0), method = {"u", "o", "a(Lnet/minecraft/Block;Lnet/minecraft/World;IIII)V",
            "d(Lnet/minecraft/Block;IIIFFF)Z", "e(Lnet/minecraft/Block;IIIFFF)Z", "t"})
    private static float injectedRenderBlockBed1(float value) {
        return Shaders.blockLightLevel08;
    }

    @ModifyConstant(constant = @Constant(floatValue = 0.6F, ordinal = 0), method = {"u", "o", "a(Lnet/minecraft/Block;Lnet/minecraft/World;IIII)V",
            "d(Lnet/minecraft/Block;IIIFFF)Z", "e(Lnet/minecraft/Block;IIIFFF)Z", "t"})
    private static float injectedRenderBlockBed2(float value) {
        return Shaders.blockLightLevel06;
    }

    @ModifyConstant(constant = @Constant(floatValue = 0.5F), method = {"c(Lnet/minecraft/Block;IIIZ)Z", "a(Lnet/minecraft/Block;IIIFFF)Z",
            "b(Lnet/minecraft/Block;IIIFFF)Z"})
    private static float injectedRenderPistonExtension0(float value) {
        return Shaders.blockLightLevel05;
    }

    @ModifyConstant(constant = @Constant(floatValue = 0.8F), method = {"c(Lnet/minecraft/Block;IIIZ)Z", "a(Lnet/minecraft/Block;IIIFFF)Z",
            "b(Lnet/minecraft/Block;IIIFFF)Z"})
    private static float injectedRenderPistonExtension1(float value) {
        return Shaders.blockLightLevel08;
    }

    @ModifyConstant(constant = @Constant(floatValue = 0.6F), method = {"c(Lnet/minecraft/Block;IIIZ)Z", "a(Lnet/minecraft/Block;IIIFFF)Z",
            "b(Lnet/minecraft/Block;IIIFFF)Z"})
    private static float injectedRenderPistonExtension2(float value) {
        return Shaders.blockLightLevel06;
    }



    @Overwrite
    public boolean b(Block par1Block, int par2, int par3, int par4) {
        if(Shaders.isActiveShader) {
            Shaders.pushEntity(par1Block);
        }

        int renderType = par1Block.getRenderType();
        if (renderType == -1) {
            return false;
        } else {
            if (this.d != null && renderType == 22) {
                renderType = 0;
            }

            if (par1Block.isAlwaysStandardFormCube()) {
                this.setRenderBoundsForStandardFormBlock();
            } else {
                par1Block.setBlockBoundsBasedOnStateAndNeighbors(this.a, par2, par3, par4);
                this.setRenderBoundsForNonStandardFormBlock(par1Block);
            }

            boolean var10000;
            if (renderType == 0){
                var10000 = this.p(par1Block, par2, par3, par4);
                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                }
                return var10000;
            } else if (renderType == 1) {
                var10000 = this.k(par1Block, par2, par3, par4);
                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                }
                return var10000;
            } else if (renderType == 2) {
                var10000 = this.c(par1Block, par2, par3, par4);
                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                }
                return var10000;
            } else if (renderType == 3) {
                var10000 = this.a((BlockFire)par1Block, par2, par3, par4);
                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                }
                return var10000;
            } else if (renderType == 4) {
                var10000 = this.o(par1Block, par2, par3, par4);
                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                }
                return var10000;
            } else if (renderType == 5) {
                var10000 = this.h(par1Block, par2, par3, par4);
                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                }
                return var10000;
            } else if (renderType == 6) {
                var10000 = this.m(par1Block, par2, par3, par4);
                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                }
                return var10000;
            } else if (renderType == 7) {
                var10000 = this.t(par1Block, par2, par3, par4);
                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                }
                return var10000;
            } else if (renderType == 8) {
                var10000 = this.i(par1Block, par2, par3, par4);
                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                }
                return var10000;
            } else if (renderType == 9) {
                var10000 = this.a((BlockMinecartTrackAbstract)par1Block, par2, par3, par4);
                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                }
                return var10000;
            } else if (renderType == 10) {
                var10000 = this.a((BlockStairs)par1Block, par2, par3, par4);
                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                }
                return var10000;
            } else if (renderType == 11) {
                var10000 = this.a((BlockFence)par1Block, par2, par3, par4);
                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                }
                return var10000;
            }  else if (renderType == 12) {
                var10000 = this.e(par1Block, par2, par3, par4);
                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                }
                return var10000;
            } else if (renderType == 13) {
                var10000 = this.s(par1Block, par2, par3, par4);
                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                }
                return var10000;
            } else if (renderType == 14) {
                var10000 = this.u(par1Block, par2, par3, par4);
                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                }
                return var10000;
            } else if (renderType == 15) {
                var10000 = this.a((BlockRepeater)par1Block, par2, par3, par4);
                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                }
                return var10000;
            } else if (renderType == 16) {
                var10000 = this.b(par1Block, par2, par3, par4, false);
                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                }
                return var10000;
            } else if (renderType == 17) {
                var10000 = this.c(par1Block, par2, par3, par4, true);
                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                }

                return var10000;
            } else if (renderType == 18) {
                var10000 = this.a((BlockThinFence)par1Block, par2, par3, par4);
                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                }
                return var10000;
            } else if (renderType == 19) {
                var10000 = this.l(par1Block, par2, par3, par4);
                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                }
                return var10000;
            } else if (renderType == 20) {
                var10000 = this.j(par1Block, par2, par3, par4);
                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                }
                return var10000;
            } else if (renderType == 21) {
                var10000 = this.a((BlockFenceGate)par1Block, par2, par3, par4);
                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                }
                return var10000;
            } else if (renderType == 23) {
                var10000 = this.n(par1Block, par2, par3, par4);
                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                }
                return var10000;
            } else if (renderType == 24) {
                var10000 = this.a((BlockCauldron)par1Block, par2, par3, par4);
                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                }
                return var10000;
            } else if (renderType == 25) {
                var10000 = this.a((BlockBrewingStand)par1Block, par2, par3, par4);
                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                }
                return var10000;
            } else if (renderType == 26) {
                var10000 = this.a((BlockEnderPortalFrame)par1Block, par2, par3, par4);
                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                }
                return var10000;
            } else if (renderType == 27) {

                var10000 = this.a((BlockDragonEgg)par1Block, par2, par3, par4);
                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                }
                return var10000;
            } else if (renderType == 28) {
                var10000 = this.a((BlockCocoa)par1Block, par2, par3, par4);
                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                }
                return var10000;
            } else if (renderType == 29) {
                var10000 = this.f(par1Block, par2, par3, par4);
                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                }
                return var10000;
            } else if (renderType == 30) {
                var10000 =this.g(par1Block, par2, par3, par4);
                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                }
                return var10000;
            } else if (renderType == 31) {
                var10000 = this.q(par1Block, par2, par3, par4);
                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                }
                return var10000;
            } else if (renderType == 32) {
                var10000 = this.a((BlockCobbleWall)par1Block, par2, par3, par4);
                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                }
                return var10000;
            } else if (renderType == 33) {
                var10000 = this.a((BlockFlowerPot)par1Block, par2, par3, par4);
                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                }
                return var10000;
            } else if (renderType == 34) {
                var10000 = this.a((BlockBeacon)par1Block, par2, par3, par4);
                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                }
                return var10000;
            } else if (renderType == 35) {
                var10000 = this.a((BlockAnvil)par1Block, par2, par3, par4);
                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                }
                return var10000;
            } else if (renderType == 36) {
                var10000 =this.a((BlockDiodeAbstract)par1Block, par2, par3, par4);
                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                }
                return var10000;
            } else if (renderType == 37) {
                var10000 = this.a((BlockRedstoneComparator)par1Block, par2, par3, par4);
                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                }
                return var10000;
            } else if (renderType == 38) {
                var10000 = this.a((BlockHopper)par1Block, par2, par3, par4);
                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                }
                return var10000;
            } else if (renderType == 39) {
                var10000 = this.r(par1Block, par2, par3, par4);
                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                }
                return var10000;
            }
            if(Shaders.isActiveShader) {
                Shaders.popEntity();
            }

            return false;
        }
    }

    @Shadow
    private boolean f;
    @Shadow
    public IBlockAccess a;
    @Shadow
    private IIcon d;
    @Shadow
    private boolean a(BlockBeacon par1BlockBeacon, int par2, int par3, int par4) {
        return false;
    }
    @Shadow
    private boolean a(BlockCocoa par1BlockCocoa, int par2, int par3, int par4) {
        return false;
    }
    @Shadow
    private boolean a(BlockEnderPortalFrame par1BlockEndPortalFrame, int par2, int par3, int par4) {
        return false;
    }
    @Shadow
    private boolean a(BlockBrewingStand par1BlockBrewingStand, int par2, int par3, int par4) {
        return false;
    }
    @Shadow
    private boolean a(BlockAnvil par1BlockAnvil, int par2, int par3, int par4) {
        return false;
    }
    @Shadow
    private boolean a(BlockFlowerPot par1BlockFlowerPot, int par2, int par3, int par4) {
        return false;
    }
    @Shadow
    private boolean a(BlockCauldron par1BlockCauldron, int par2, int par3, int par4) {
        return false;
    }
    @Shadow
    public boolean a(BlockFenceGate par1BlockFenceGate, int par2, int par3, int par4) {
        return false;
    }
    @Shadow
    public boolean a(BlockThinFence par1BlockPane, int par2, int par3, int par4) {
        return false;
    }
    @Shadow
    private boolean c(Block par1Block, int par2, int par3, int par4, boolean par5) {
        return false;
    }
    @Shadow
    private boolean b(Block par1Block, int par2, int par3, int par4, boolean par5) {
        return false;
    }
    @Shadow
    private boolean a(BlockDiodeAbstract par1BlockRedstoneLogic, int par2, int par3, int par4) {
        return false;
    }
    @Shadow
    private boolean a(BlockRepeater par1BlockRedstoneRepeater, int par2, int par3, int par4) {
        return false;
    }
    @Shadow
    private boolean u(Block par1Block, int par2, int par3, int par4) {
        return false;
    }
    @Shadow
    public boolean g(Block par1Block, int par2, int par3, int par4) {
        return false;
    }
    @Shadow
    public boolean f(Block par1Block, int par2, int par3, int par4) {
        return false;
    }
    @Shadow
    public boolean e(Block par1Block, int par2, int par3, int par4) {
        return false;
    }
    @Shadow
    public boolean a(BlockCobbleWall par1BlockWall, int par2, int par3, int par4) {
        return false;
    }
    @Shadow
    public boolean a(BlockDragonEgg par1BlockDragonEgg, int par2, int par3, int par4) {
        return false;
    }
    @Shadow
    public boolean a(BlockStairs par1BlockStairs, int par2, int par3, int par4) {
        return false;
    }
    @Shadow
    public boolean t(Block par1Block, int par2, int par3, int par4) {
        return false;
    }
    @Shadow
    public boolean i(Block par1Block, int par2, int par3, int par4) {
        return false;
    }
    @Shadow
    public boolean a(BlockFire par1BlockFire, int par2, int par3, int par4) {
        return false;
    }
    @Shadow
    public boolean m(Block par1Block, int par2, int par3, int par4) {
        return false;
    }
    @Shadow
    public boolean n(Block par1Block, int par2, int par3, int par4) {
        return false;
    }
    @Shadow
    public boolean a(BlockMinecartTrackAbstract par1BlockRailBase, int par2, int par3, int par4) {
        return false;
    }
    @Shadow
    public boolean l(Block par1Block, int par2, int par3, int par4) {
        return false;
    }
    @Shadow
    public boolean s(Block par1Block, int par2, int par3, int par4) {
        return false;
    }
    @Shadow
    public boolean h(Block par1Block, int par2, int par3, int par4) {
        return false;
    }
    @Shadow
    public void setRenderBoundsForStandardFormBlock() {

    }
    @Shadow
    public void setRenderBoundsForNonStandardFormBlock(Block block) {

    }
    @Shadow
    public boolean r(Block par1Block, int par2, int par3, int par4) {
        return false;
    }
    @Shadow
    private boolean a(BlockHopper par1BlockHopper, int par2, int par3, int par4) {
        return false;
    }
    @Shadow
    public boolean c(Block par1Block, int par2, int par3, int par4) {
        return false;
    }
    @Shadow
    public boolean a(BlockFence par1BlockFence, int par2, int par3, int par4) {
        return false;
    }
    @Shadow
    public boolean k(Block par1Block, int par2, int par3, int par4) {
        return false;
    }
    @Shadow
    public boolean j(Block par1Block, int par2, int par3, int par4) {
        return false;
    }
    @Shadow
    public boolean q(Block par1Block, int par2, int par3, int par4) {
        return false;
    }
    @Shadow
    public boolean p(Block par1Block, int par2, int par3, int par4) {
        return false;
    }
    @Shadow
    public boolean o(Block par1Block, int par2, int par3, int par4) {
        return false;
    }
}
