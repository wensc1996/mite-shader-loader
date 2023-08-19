package net.wenscHuix.mitemod.mixin.render;

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
//        if(Shaders.isActiveShader) {
            Shaders.pushEntity(par1Block);
//        }

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

            boolean value;
            if (renderType == 0){
                value = this.p(par1Block, par2, par3, par4);
//                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
//                }
                return value;
            } else if (renderType == 1) {
                value = this.k(par1Block, par2, par3, par4);
//                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
//                }
                return value;
            } else if (renderType == 2) {
                value = this.c(par1Block, par2, par3, par4);
                //if(Shaders.isActiveShader) {
                    Shaders.popEntity();
               // }
                return value;
            } else if (renderType == 3) {
                value = this.a((BlockFire)par1Block, par2, par3, par4);
                //if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                //}
                return value;
            } else if (renderType == 4) {
                value = this.o(par1Block, par2, par3, par4);
                //if(Shaders.isActiveShader) {
                    Shaders.popEntity();
              //  }
                return value;
            } else if (renderType == 5) {
                value = this.h(par1Block, par2, par3, par4);
                //if(Shaders.isActiveShader) {
                    Shaders.popEntity();
               // }
                return value;
            } else if (renderType == 6) {
                value = this.m(par1Block, par2, par3, par4);
               // if(Shaders.isActiveShader) {
                    Shaders.popEntity();
              //  }
                return value;
            } else if (renderType == 7) {
                value = this.t(par1Block, par2, par3, par4);
               // if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                //}
                return value;
            } else if (renderType == 8) {
                value = this.i(par1Block, par2, par3, par4);
               // if(Shaders.isActiveShader) {
                    Shaders.popEntity();
               // }
                return value;
            } else if (renderType == 9) {
                value = this.a((BlockMinecartTrackAbstract)par1Block, par2, par3, par4);
                //if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                //}
                return value;
            } else if (renderType == 10) {
                value = this.a((BlockStairs)par1Block, par2, par3, par4);
                //if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                //}
                return value;
            } else if (renderType == 11) {
                value = this.a((BlockFence)par1Block, par2, par3, par4);
                //if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                //}
                return value;
            }  else if (renderType == 12) {
                value = this.e(par1Block, par2, par3, par4);
                //if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                //}
                return value;
            } else if (renderType == 13) {
                value = this.s(par1Block, par2, par3, par4);
                //if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                //}
                return value;
            } else if (renderType == 14) {
                value = this.u(par1Block, par2, par3, par4);
                //if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                //}
                return value;
            } else if (renderType == 15) {
                value = this.a((BlockRepeater)par1Block, par2, par3, par4);
                //if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                //}
                return value;
            } else if (renderType == 16) {
                value = this.b(par1Block, par2, par3, par4, false);
                //if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                //}
                return value;
            } else if (renderType == 17) {
                value = this.c(par1Block, par2, par3, par4, true);
                //if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                //}

                return value;
            } else if (renderType == 18) {
                value = this.a((BlockThinFence)par1Block, par2, par3, par4);
                //if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                //}
                return value;
            } else if (renderType == 19) {
                value = this.l(par1Block, par2, par3, par4);
                //if(Shaders.isActiveShader) {
                    Shaders.popEntity();
               // }
                return value;
            } else if (renderType == 20) {
                value = this.j(par1Block, par2, par3, par4);
                //if(Shaders.isActiveShader) {
                    Shaders.popEntity();
               // }
                return value;
            } else if (renderType == 21) {
                value = this.a((BlockFenceGate)par1Block, par2, par3, par4);
                //if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                //}
                return value;
            } else if (renderType == 23) {
                value = this.n(par1Block, par2, par3, par4);
                //if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                //}
                return value;
            } else if (renderType == 24) {
                value = this.a((BlockCauldron)par1Block, par2, par3, par4);
                //if(Shaders.isActiveShader) {
                    Shaders.popEntity();
               // }
                return value;
            } else if (renderType == 25) {
                value = this.a((BlockBrewingStand)par1Block, par2, par3, par4);
               // if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                //}
                return value;
            } else if (renderType == 26) {
                value = this.a((BlockEnderPortalFrame)par1Block, par2, par3, par4);
                //if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                //}
                return value;
            } else if (renderType == 27) {

                value = this.a((BlockDragonEgg)par1Block, par2, par3, par4);
                //if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                //}
                return value;
            } else if (renderType == 28) {
                value = this.a((BlockCocoa)par1Block, par2, par3, par4);
                //if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                //}
                return value;
            } else if (renderType == 29) {
                value = this.f(par1Block, par2, par3, par4);
                //if(Shaders.isActiveShader) {
                    Shaders.popEntity();
               // }
                return value;
            } else if (renderType == 30) {
                value =this.g(par1Block, par2, par3, par4);
               // if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                //}
                return value;
            } else if (renderType == 31) {
                value = this.q(par1Block, par2, par3, par4);
               // if(Shaders.isActiveShader) {
                    Shaders.popEntity();
               // }
                return value;
            } else if (renderType == 32) {
                value = this.a((BlockCobbleWall)par1Block, par2, par3, par4);
                //if(Shaders.isActiveShader) {
                    Shaders.popEntity();
               // }
                return value;
            } else if (renderType == 33) {
                value = this.a((BlockFlowerPot)par1Block, par2, par3, par4);
               // if(Shaders.isActiveShader) {
                    Shaders.popEntity();
               // }
                return value;
            } else if (renderType == 34) {
                value = this.a((BlockBeacon)par1Block, par2, par3, par4);
                //if(Shaders.isActiveShader) {
                    Shaders.popEntity();
               // }
                return value;
            } else if (renderType == 35) {
                value = this.a((BlockAnvil)par1Block, par2, par3, par4);
                //if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                //}
                return value;
            } else if (renderType == 36) {
                value =this.a((BlockDiodeAbstract)par1Block, par2, par3, par4);
                //if(Shaders.isActiveShader) {
                    Shaders.popEntity();
                //}
                return value;
            } else if (renderType == 37) {
                value = this.a((BlockRedstoneComparator)par1Block, par2, par3, par4);
                //if(Shaders.isActiveShader) {
                    Shaders.popEntity();
               // }
                return value;
            } else if (renderType == 38) {
                value = this.a((BlockHopper)par1Block, par2, par3, par4);
                //if(Shaders.isActiveShader) {
                    Shaders.popEntity();
               // }
                return value;
            } else if (renderType == 39) {
                value = this.r(par1Block, par2, par3, par4);
//                if(Shaders.isActiveShader) {
                    Shaders.popEntity();
//                }
                return value;
            }
            //if(Shaders.isActiveShader) {
                Shaders.popEntity();
            //}

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
