/*     */ package com.bioxx.tfc.Blocks.Liquids;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.Vanilla.BlockCustomLilyPad;
/*     */ import com.bioxx.tfc.Core.TFC_Climate;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.WorldGen.DataLayer;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockDynamicLiquid;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.EnumPlantType;
/*     */ import net.minecraftforge.common.IPlantable;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import net.minecraftforge.fluids.Fluid;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fluids.IFluidBlock;
/*     */ 
/*     */ 
/*     */ public abstract class BlockCustomLiquid
/*     */   extends BlockDynamicLiquid
/*     */   implements IFluidBlock
/*     */ {
/*     */   protected Fluid fluidType;
/*     */   protected IIcon[] icons;
/*     */   protected int sourceBlockCount;
/*  36 */   protected boolean[] canFlowDirections = new boolean[4];
/*  37 */   protected int[] flowPriorities = new int[4];
/*     */ 
/*     */   
/*     */   public BlockCustomLiquid(Fluid fluid, Material mat) {
/*  41 */     super(mat);
/*  42 */     float f = 0.0F;
/*  43 */     float f1 = 0.0F;
/*  44 */     func_149676_a(0.0F + f1, 0.0F + f, 0.0F + f1, 1.0F + f1, 1.0F + f, 1.0F + f1);
/*  45 */     func_149675_a(true);
/*  46 */     this.fluidType = fluid;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149646_a(IBlockAccess world, int x, int y, int z, int side) {
/*  53 */     Block block = world.func_147439_a(x, y, z);
/*  54 */     if (block.func_149688_o() == func_149688_o())
/*     */     {
/*  56 */       return false;
/*     */     }
/*     */     
/*  59 */     return super.func_149646_a(world, x, y, z, side);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149670_a(World world, int x, int y, int z, Entity e) {
/*  65 */     if (this.field_149764_J == Material.field_151587_i)
/*     */     {
/*  67 */       if (e instanceof net.minecraft.entity.item.EntityItem)
/*     */       {
/*  69 */         e.func_70015_d(15);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_149720_d(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/*  82 */     if (this.field_149764_J != Material.field_151586_h) {
/*  83 */       return 16777215;
/*     */     }
/*  85 */     return 3493173;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149726_b(World world, int x, int y, int z) {
/*  95 */     if (world.func_147439_a(x, y, z) == this)
/*     */     {
/*  97 */       world.func_147464_a(x, y, z, (Block)this, func_149738_a(world));
/*     */     }
/*  99 */     checkForHarden(world, x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149695_a(World world, int x, int y, int z, Block block) {
/* 110 */     checkForHarden(world, x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void checkForHarden(World world, int x, int y, int z) {
/* 118 */     if (world.func_147439_a(x, y, z) == this && 
/* 119 */       this.field_149764_J == Material.field_151587_i) {
/*     */       
/* 121 */       boolean flag = false;
/*     */       
/* 123 */       if (flag || world.func_147439_a(x, y, z - 1).func_149688_o() == Material.field_151586_h) {
/* 124 */         flag = true;
/*     */       }
/* 126 */       if (flag || world.func_147439_a(x, y, z + 1).func_149688_o() == Material.field_151586_h) {
/* 127 */         flag = true;
/*     */       }
/* 129 */       if (flag || world.func_147439_a(x - 1, y, z).func_149688_o() == Material.field_151586_h) {
/* 130 */         flag = true;
/*     */       }
/* 132 */       if (flag || world.func_147439_a(x + 1, y, z).func_149688_o() == Material.field_151586_h) {
/* 133 */         flag = true;
/*     */       }
/* 135 */       if (flag || world.func_147439_a(x, y + 1, z).func_149688_o() == Material.field_151586_h) {
/* 136 */         flag = true;
/*     */       }
/* 138 */       if (flag) {
/*     */         
/* 140 */         int l = world.func_72805_g(x, y, z);
/*     */         
/* 142 */         if (l == 0) {
/* 143 */           setBlockforLava(world, x, y, z, 0);
/* 144 */         } else if (l <= 4) {
/* 145 */           setBlockforLava(world, x, y, z, 1);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable) {
/*     */     boolean isBeach, hasWater;
/* 174 */     Block plant = plantable.getPlant(world, x, y + 1, z);
/* 175 */     if (plant == TFCBlocks.cactus && this == TFCBlocks.cactus)
/*     */     {
/* 177 */       return true;
/*     */     }
/* 179 */     if (plant == TFCBlocks.reeds && this == TFCBlocks.reeds)
/*     */     {
/* 181 */       return true;
/*     */     }
/*     */     
/* 184 */     int meta = world.func_72805_g(x, y, z);
/* 185 */     if (plant instanceof BlockCustomLilyPad && ((BlockCustomLilyPad)plant).canThisPlantGrowOnThisBlock((Block)this, meta))
/*     */     {
/* 187 */       return true;
/*     */     }
/*     */     
/* 190 */     EnumPlantType plantType = plantable.getPlantType(world, x, y + 1, z);
/* 191 */     switch (plantType) {
/*     */       case Desert:
/* 193 */         return TFC_Core.isSand((Block)this);
/* 194 */       case Nether: return (this == Blocks.field_150425_aM);
/* 195 */       case Crop: return TFC_Core.isFarmland((Block)this);
/* 196 */       case Cave: return isSideSolid(world, x, y, z, ForgeDirection.UP);
/* 197 */       case Plains: return (this == TFCBlocks.grass || this == TFCBlocks.grass2 || this == TFCBlocks.dirt || this == TFCBlocks.dirt2);
/* 198 */       case Water: return (world.func_147439_a(x, y, z).func_149688_o() == Material.field_151586_h && world.func_72805_g(x, y, z) == 0);
/*     */       case Beach:
/* 200 */         isBeach = TFC_Core.isGround((Block)this);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 205 */         hasWater = (world.func_147439_a(x - 1, y, z).func_149688_o() == Material.field_151586_h || world.func_147439_a(x + 1, y, z).func_149688_o() == Material.field_151586_h || world.func_147439_a(x, y, z - 1).func_149688_o() == Material.field_151586_h || world.func_147439_a(x, y, z + 1).func_149688_o() == Material.field_151586_h);
/* 206 */         return (isBeach && hasWater);
/*     */     } 
/*     */     
/* 209 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockforLava(World world, int x, int y, int z, int typeOfLava) {
/* 214 */     DataLayer rockLayer3 = TFC_Climate.getCacheManager(world).getRockLayerAt(x, y, 2);
/*     */     
/* 216 */     int meta = rockLayer3.data2;
/* 217 */     Random rand = new Random();
/* 218 */     boolean felsicLava = true;
/*     */     
/* 220 */     if (this == TFCBlocks.stoneIgIn && (meta == 2 || meta == 1)) {
/* 221 */       felsicLava = false;
/* 222 */     } else if (this == TFCBlocks.stoneIgEx && (meta == 1 || meta == 2)) {
/* 223 */       felsicLava = false;
/* 224 */     }  if (typeOfLava == 0 || typeOfLava == 2) {
/*     */       
/* 226 */       if (felsicLava) {
/*     */         
/* 228 */         if (rand.nextInt(10) == 0 && typeOfLava == 0) {
/* 229 */           world.func_147449_b(x, y, z, Blocks.field_150343_Z);
/*     */         } else {
/*     */           
/* 232 */           world.func_147449_b(x, y, z, TFCBlocks.stoneIgEx);
/* 233 */           world.func_72921_c(x, y, z, 0, 0);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 238 */         world.func_147449_b(x, y, z, TFCBlocks.stoneIgEx);
/* 239 */         world.func_72921_c(x, y, z, 1, 0);
/*     */       }
/*     */     
/* 242 */     } else if (typeOfLava == 1) {
/*     */       
/* 244 */       world.func_147449_b(x, y, z, TFCBlocks.stoneIgExCobble);
/* 245 */       if (felsicLava) {
/* 246 */         world.func_72921_c(x, y, z, 0, 0);
/*     */       } else {
/* 248 */         world.func_72921_c(x, y, z, 1, 0);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149738_a(World world) {
/* 255 */     if (func_149688_o() == Material.field_151587_i)
/* 256 */       return 10; 
/* 257 */     return 3;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149651_a(IIconRegister register) {
/* 263 */     if (this.field_149764_J == Material.field_151587_i) {
/*     */       
/* 265 */       getFluid().setIcons(register.func_94245_a("lava_still"), register.func_94245_a("lava_flow"));
/*     */     }
/*     */     else {
/*     */       
/* 269 */       getFluid().setIcons(register.func_94245_a("water_still"), register.func_94245_a("water_flow"));
/*     */     } 
/* 271 */     this.icons = new IIcon[] { getFluid().getStillIcon(), getFluid().getFlowingIcon() };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int side, int meta) {
/* 278 */     return (side != 0 && side != 1) ? this.icons[1] : this.icons[0];
/*     */   }
/*     */   public Fluid getFluid() {
/* 281 */     return this.fluidType;
/*     */   }
/*     */   
/*     */   public FluidStack drain(World world, int x, int y, int z, boolean doDrain) {
/* 285 */     return null;
/*     */   }
/*     */   
/*     */   public boolean canDrain(World world, int x, int y, int z) {
/* 289 */     return false;
/*     */   }
/*     */   
/*     */   public float getFilledPercentage(World world, int x, int y, int z) {
/* 293 */     return 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canStay(World world, int x, int y, int z) {
/* 298 */     Block block = world.func_147439_a(x, y, z);
/* 299 */     String name = block.getClass().getSimpleName().toLowerCase();
/* 300 */     if (name.equals("blocktablestorage") || name.equals("blockpreptable")) return true; 
/* 301 */     if (block == TFCBlocks.thatch)
/*     */     {
/* 303 */       return false;
/*     */     }
/* 305 */     return func_149807_p(world, x, y, z);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getLiquidHeight(World world, int x, int y, int z, int count) {
/* 310 */     int liquidHeight = getMetaData(world, x, y, z);
/*     */ 
/*     */     
/* 313 */     if (liquidHeight < 0)
/*     */     {
/* 315 */       return count;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 320 */     if (liquidHeight == 0) {
/* 321 */       this.sourceBlockCount++;
/* 322 */     } else if (liquidHeight >= 8) {
/* 323 */       liquidHeight = 0;
/*     */     } 
/* 325 */     return (count >= 0 && liquidHeight >= count) ? count : liquidHeight;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149674_a(World world, int x, int y, int z, Random rand) {
/* 332 */     int meta = getMetaData(world, x, y, z);
/* 333 */     byte b0 = 1;
/*     */     
/* 335 */     if (this.field_149764_J == Material.field_151587_i && !world.field_73011_w.field_76575_d)
/*     */     {
/* 337 */       b0 = 2;
/*     */     }
/*     */     
/* 340 */     boolean flag = true;
/* 341 */     int tickRate = func_149738_a(world);
/*     */ 
/*     */     
/* 344 */     if (meta > 0) {
/*     */       
/* 346 */       this.sourceBlockCount = 0;
/* 347 */       int liquidHeight = getLiquidHeight(world, x - 1, y, z, -100);
/* 348 */       liquidHeight = getLiquidHeight(world, x + 1, y, z, liquidHeight);
/* 349 */       liquidHeight = getLiquidHeight(world, x, y, z - 1, liquidHeight);
/* 350 */       liquidHeight = getLiquidHeight(world, x, y, z + 1, liquidHeight);
/* 351 */       int newHeight = liquidHeight + b0;
/*     */       
/* 353 */       if (newHeight >= 8 || liquidHeight < 0)
/*     */       {
/* 355 */         newHeight = -1;
/*     */       }
/*     */ 
/*     */       
/* 359 */       if (getMetaData(world, x, y + 1, z) >= 0) {
/*     */         
/* 361 */         int metaAbove = getMetaData(world, x, y + 1, z);
/*     */         
/* 363 */         if (metaAbove >= 8) {
/*     */           
/* 365 */           newHeight = metaAbove;
/*     */         }
/*     */         else {
/*     */           
/* 369 */           newHeight = metaAbove + 8;
/*     */         } 
/*     */       } 
/*     */       
/* 373 */       if (this.sourceBlockCount >= 2 && this.field_149764_J == Material.field_151586_h && !TFCOptions.enableFiniteWater)
/*     */       {
/*     */         
/* 376 */         if (world.func_147439_a(x, y - 1, z).func_149688_o().func_76220_a() || (world
/* 377 */           .func_147439_a(x, y - 1, z).func_149688_o() == this.field_149764_J && world.func_72805_g(x, y - 1, z) == 0))
/*     */         {
/* 379 */           newHeight = 0;
/*     */         }
/*     */       }
/*     */       
/* 383 */       if (this.field_149764_J == Material.field_151587_i && meta < 8 && newHeight < 8 && newHeight > meta && rand.nextInt(4) != 0)
/*     */       {
/* 385 */         tickRate *= 4;
/*     */       }
/*     */       
/* 388 */       if (newHeight == meta) {
/*     */         
/* 390 */         if (flag)
/*     */         {
/* 392 */           convertFlowingToSource(world, x, y, z);
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 397 */         meta = newHeight;
/*     */         
/* 399 */         if (newHeight < 0)
/*     */         {
/* 401 */           world.func_147468_f(x, y, z);
/*     */         }
/*     */         else
/*     */         {
/* 405 */           world.func_72921_c(x, y, z, newHeight, 2);
/* 406 */           world.func_147464_a(x, y, z, (Block)this, tickRate);
/* 407 */           world.func_147459_d(x, y, z, (Block)this);
/*     */         }
/*     */       
/*     */       } 
/*     */     } else {
/*     */       
/* 413 */       convertFlowingToSource(world, x, y, z);
/*     */     } 
/*     */     
/* 416 */     if (canReplace(world, x, y - 1, z)) {
/*     */       
/* 418 */       if (this.field_149764_J == Material.field_151587_i && world.func_147439_a(x, y - 1, z).func_149688_o() == Material.field_151586_h) {
/*     */         
/* 420 */         setBlockforLava(world, x, y - 1, z, 2);
/* 421 */         func_149799_m(world, x, y - 1, z);
/*     */         
/*     */         return;
/*     */       } 
/* 425 */       if (meta >= 8)
/*     */       {
/* 427 */         flow(world, x, y - 1, z, meta);
/*     */       }
/*     */       else
/*     */       {
/* 431 */         flow(world, x, y - 1, z, meta + 8);
/*     */       }
/*     */     
/* 434 */     } else if (meta >= 0 && (meta == 0 || canStay(world, x, y - 1, z))) {
/*     */       
/* 436 */       int newHeight = meta + b0;
/*     */       
/* 438 */       if (meta >= 8)
/*     */       {
/* 440 */         newHeight = 1;
/*     */       }
/*     */       
/* 443 */       if (newHeight >= 8) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 448 */       boolean[] flowDirections = getFlowDirections(world, x, y, z);
/* 449 */       if (flowDirections[0])
/*     */       {
/* 451 */         flow(world, x - 1, y, z, newHeight);
/*     */       }
/*     */       
/* 454 */       if (flowDirections[1])
/*     */       {
/* 456 */         flow(world, x + 1, y, z, newHeight);
/*     */       }
/*     */       
/* 459 */       if (flowDirections[2])
/*     */       {
/* 461 */         flow(world, x, y, z - 1, newHeight);
/*     */       }
/*     */       
/* 464 */       if (flowDirections[3])
/*     */       {
/* 466 */         flow(world, x, y, z + 1, newHeight);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void flow(World world, int x, int y, int z, int meta) {
/* 473 */     if (canReplace(world, x, y, z)) {
/*     */       
/* 475 */       Block block = world.func_147439_a(x, y, z);
/*     */       
/* 477 */       if (this.field_149764_J == Material.field_151587_i) {
/*     */         
/* 479 */         setBlockforLava(world, x, y, z, 0);
/* 480 */         func_149799_m(world, x, y - 1, z);
/*     */       }
/*     */       else {
/*     */         
/* 484 */         block.func_149697_b(world, x, y, z, world.func_72805_g(x, y, z), 0);
/*     */       } 
/*     */       
/* 487 */       world.func_147465_d(x, y, z, (Block)this, meta, 3);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean canReplace(World world, int x, int y, int z) {
/* 493 */     Block b = world.func_147439_a(x, y, z);
/* 494 */     Material material = b.func_149688_o();
/* 495 */     if (b == Blocks.field_150448_aq || b == Blocks.field_150408_cc || b == Blocks.field_150319_E || b == Blocks.field_150318_D) return false; 
/* 496 */     if (material == this.field_149764_J || material == Material.field_151587_i) {
/* 497 */       return false;
/*     */     }
/* 499 */     return !canStay(world, x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean[] getFlowDirections(World world, int x, int y, int z) {
/*     */     int side;
/* 507 */     for (side = 0; side < 4; side++) {
/*     */       
/* 509 */       this.flowPriorities[side] = 1000;
/* 510 */       int i = x;
/* 511 */       int newZ = z;
/*     */       
/* 513 */       if (side == 0) {
/* 514 */         i = x - 1;
/* 515 */       } else if (side == 1) {
/* 516 */         i++;
/* 517 */       } else if (side == 2) {
/* 518 */         newZ = z - 1;
/* 519 */       } else if (side == 3) {
/* 520 */         newZ++;
/*     */       } 
/* 522 */       if (!canStay(world, i, y, newZ) && (world.func_147439_a(i, y, newZ).func_149688_o() != this.field_149764_J || world.func_72805_g(i, y, newZ) != 0))
/*     */       {
/* 524 */         if (canStay(world, i, y - 1, newZ)) {
/*     */           
/* 526 */           this.flowPriorities[side] = getFlowPriorities(world, i, y, newZ, 1, side);
/*     */         }
/*     */         else {
/*     */           
/* 530 */           this.flowPriorities[side] = 0;
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 535 */     side = this.flowPriorities[0];
/*     */     int newX;
/* 537 */     for (newX = 1; newX < 4; newX++) {
/*     */       
/* 539 */       if (this.flowPriorities[newX] < side)
/*     */       {
/* 541 */         side = this.flowPriorities[newX];
/*     */       }
/*     */     } 
/*     */     
/* 545 */     for (newX = 0; newX < 4; newX++)
/*     */     {
/* 547 */       this.canFlowDirections[newX] = (this.flowPriorities[newX] == side);
/*     */     }
/*     */     
/* 550 */     return this.canFlowDirections;
/*     */   }
/*     */ 
/*     */   
/*     */   private void convertFlowingToSource(World world, int x, int y, int z) {
/* 555 */     int meta = world.func_72805_g(x, y, z);
/* 556 */     world.func_147465_d(x, y, z, Block.func_149729_e(Block.func_149682_b((Block)this) + 1), meta, 2);
/*     */   }
/*     */ 
/*     */   
/*     */   private int getFlowPriorities(World world, int x, int y, int z, int distance, int side) {
/* 561 */     int priority = 1000;
/*     */     
/* 563 */     for (int side2 = 0; side2 < 4; side2++) {
/*     */       
/* 565 */       if ((side2 != 0 || side != 1) && (side2 != 1 || side != 0) && (side2 != 2 || side != 3) && (side2 != 3 || side != 2)) {
/*     */         
/* 567 */         int xCoord = x;
/* 568 */         int zCoord = z;
/*     */         
/* 570 */         if (side2 == 0) {
/* 571 */           xCoord = x - 1;
/* 572 */         } else if (side2 == 1) {
/* 573 */           xCoord++;
/* 574 */         } else if (side2 == 2) {
/* 575 */           zCoord = z - 1;
/* 576 */         } else if (side2 == 3) {
/* 577 */           zCoord++;
/*     */         } 
/* 579 */         if (!canStay(world, xCoord, y, zCoord) && (world.func_147439_a(xCoord, y, zCoord).func_149688_o() != this.field_149764_J || world.func_72805_g(xCoord, y, zCoord) != 0)) {
/*     */           
/* 581 */           if (!canStay(world, xCoord, y - 1, zCoord))
/*     */           {
/* 583 */             return distance;
/*     */           }
/*     */           
/* 586 */           if (distance < 4) {
/*     */             
/* 588 */             int newDistance = getFlowPriorities(world, xCoord, y, zCoord, distance + 1, side2);
/*     */             
/* 590 */             if (newDistance < priority)
/*     */             {
/* 592 */               priority = newDistance;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 599 */     return priority;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getMetaData(World world, int x, int y, int z) {
/* 604 */     return (world.func_147439_a(x, y, z).func_149688_o() == this.field_149764_J) ? world.func_72805_g(x, y, z) : -1;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Liquids\BlockCustomLiquid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */