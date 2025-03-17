/*     */ package com.bioxx.tfc.Blocks.Liquids;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Climate;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockLiquid;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import net.minecraftforge.fluids.Fluid;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fluids.IFluidBlock;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockLiquidStatic
/*     */   extends BlockLiquid
/*     */   implements IFluidBlock
/*     */ {
/*     */   private Block flowing;
/*     */   protected Fluid fluidType;
/*     */   protected IIcon[] icons;
/*     */   
/*     */   public BlockLiquidStatic(Fluid fluid, Material material, Block f) {
/*  35 */     super(material);
/*  36 */     this.flowing = f;
/*  37 */     this.fluidType = fluid;
/*  38 */     func_149675_a(true);
/*  39 */     fluid.setBlock((Block)this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Fluid getFluid() {
/*  45 */     return this.fluidType;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public FluidStack drain(World world, int x, int y, int z, boolean doDrain) {
/*  51 */     if (!isDrainableSourceBlock((IBlockAccess)world, x, y, z))
/*     */     {
/*  53 */       return null;
/*     */     }
/*     */     
/*  56 */     if (doDrain)
/*     */     {
/*  58 */       world.func_147449_b(x, y, z, Blocks.field_150350_a);
/*     */     }
/*     */     
/*  61 */     return new FluidStack(this.fluidType, 1000);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canDrain(World world, int x, int y, int z) {
/*  67 */     return isDrainableSourceBlock((IBlockAccess)world, x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getFilledPercentage(World world, int x, int y, int z) {
/*  73 */     return 1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_149720_d(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/*  80 */     if (this.field_149764_J != Material.field_151586_h) {
/*  81 */       return 16777215;
/*     */     }
/*  83 */     return 3493173;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149670_a(World world, int x, int y, int z, Entity e) {
/*  89 */     if (this.field_149764_J == Material.field_151587_i)
/*     */     {
/*  91 */       if (e instanceof net.minecraft.entity.item.EntityItem)
/*     */       {
/*  93 */         e.func_70015_d(15);
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
/*     */   public void func_149695_a(World world, int x, int y, int z, Block b) {
/* 105 */     super.func_149695_a(world, x, y, z, b);
/*     */     
/* 107 */     if (world.func_147439_a(x, y, z) == this)
/*     */     {
/* 109 */       setNotStationary(world, x, y, z);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149674_a(World world, int x, int y, int z, Random rand) {
/* 116 */     if (!world.field_72995_K) {
/*     */       
/* 118 */       if (world.func_147437_c(x, y + 1, z))
/*     */       {
/* 120 */         world.field_73011_w.canBlockFreeze(x, y, z, false);
/*     */       }
/*     */ 
/*     */       
/* 124 */       if (world.func_147439_a(x, y, z) == TFCBlocks.freshWaterStationary && world
/* 125 */         .func_147437_c(x, y + 1, z) && 
/* 126 */         TFC_Climate.getHeightAdjustedTemp(world, x, y + 1, z) > 2.0F)
/*     */       {
/* 128 */         if (rand.nextInt(100) < 25 && world.func_72957_l(x, y, z) < 7)
/*     */         {
/*     */           
/* 131 */           for (int x1 = x - 3; x1 < x + 3; x1++) {
/*     */             
/* 133 */             for (int z1 = z - 3; z1 < z + 3; z1++) {
/*     */               
/* 135 */               if (TFC_Core.isGrass(world.func_147439_a(x1, y, z1)) || world.func_147439_a(x1, y - 1, z1) == TFCBlocks.waterPlant) {
/*     */                 
/* 137 */                 float mod = rand.nextFloat();
/* 138 */                 world.func_72908_a(x, y, z, "terrafirmacraft:mob.frog", (mod < 0.55F) ? mod : 0.55F, (mod < 0.41F) ? (mod + 0.8F) : 0.8F);
/*     */                 
/*     */                 // Byte code: goto -> 228
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         }
/*     */       }
/* 146 */       if (func_149688_o() == Material.field_151587_i)
/*     */       {
/* 148 */         if (world.func_147439_a(x, y + 1, z).func_149688_o() == Material.field_151579_a) {
/*     */           
/* 150 */           int i = x - 2 + rand.nextInt(5);
/* 151 */           int j = y + 1 + rand.nextInt(4);
/* 152 */           int k = z - 2 + rand.nextInt(5);
/*     */           
/* 154 */           if (world.func_147439_a(i, j, k).func_149688_o() == Material.field_151579_a)
/*     */           {
/* 156 */             if (isFlammable(world, i - 1, j, k) || isFlammable(world, i + 1, j, k) || 
/* 157 */               isFlammable(world, i, j, k - 1) || isFlammable(world, i, j, k + 1) || 
/* 158 */               isFlammable(world, i, j - 1, k) || isFlammable(world, i, j + 1, k)) {
/*     */               
/* 160 */               world.func_147449_b(i, j, k, (Block)Blocks.field_150480_ab);
/*     */             }
/* 162 */             else if (world.isSideSolid(i, j + 1, k, ForgeDirection.DOWN) || world.isSideSolid(i, j - 1, k, ForgeDirection.UP) || world
/* 163 */               .isSideSolid(i - 1, j, k, ForgeDirection.EAST) || world.isSideSolid(i + 1, j, k, ForgeDirection.WEST) || world
/* 164 */               .isSideSolid(i, j, k + 1, ForgeDirection.NORTH) || world.isSideSolid(i, j, k - 1, ForgeDirection.SOUTH)) {
/*     */ 
/*     */               
/* 167 */               if (world.field_73012_v.nextFloat() < 0.2F) world.func_147465_d(i, j, k, TFCBlocks.sulfur, world.field_73012_v.nextInt(4), 3);
/*     */             
/*     */             } 
/*     */           }
/*     */           
/* 172 */           int distance = rand.nextInt(3);
/*     */           
/*     */           int xCoord;
/* 175 */           for (xCoord = 0; xCoord < distance; xCoord++) {
/*     */             
/* 177 */             x += rand.nextInt(3) - 1;
/* 178 */             y++;
/* 179 */             z += rand.nextInt(3) - 1;
/* 180 */             Block block = world.func_147439_a(x, y, z);
/*     */             
/* 182 */             if (block.func_149688_o() == Material.field_151579_a) {
/*     */               
/* 184 */               if (isFlammable(world, x - 1, y, z) || isFlammable(world, x + 1, y, z) || 
/* 185 */                 isFlammable(world, x, y, z - 1) || isFlammable(world, x, y, z + 1) || 
/* 186 */                 isFlammable(world, x, y - 1, z) || isFlammable(world, x, y + 1, z)) {
/*     */                 
/* 188 */                 world.func_147449_b(x, y, z, (Block)Blocks.field_150480_ab);
/*     */                 
/*     */                 return;
/*     */               } 
/* 192 */             } else if (block.func_149688_o().func_76230_c()) {
/*     */               return;
/*     */             } 
/*     */           } 
/*     */ 
/*     */           
/* 198 */           if (distance == 0) {
/*     */             
/* 200 */             xCoord = x;
/* 201 */             int zCoord = z;
/*     */             
/* 203 */             for (int c = 0; c < 3; c++) {
/*     */               
/* 205 */               x = xCoord + rand.nextInt(3) - 1;
/* 206 */               z = zCoord + rand.nextInt(3) - 1;
/*     */               
/* 208 */               if (world.func_147437_c(x, y + 1, z) && isFlammable(world, x, y, z))
/*     */               {
/* 210 */                 world.func_147449_b(x, y + 1, z, (Block)Blocks.field_150480_ab);
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 218 */     super.func_149674_a(world, x, y, z, rand);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isFlammable(World world, int x, int y, int z) {
/* 227 */     return (Blocks.field_150480_ab.getFlammability(world.func_147439_a(x, y, z)) > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setNotStationary(World world, int x, int y, int z) {
/* 235 */     int l = world.func_72805_g(x, y, z);
/* 236 */     world.func_147465_d(x, y, z, this.flowing, l, 2);
/* 237 */     world.func_147464_a(x, y, z, this.flowing, func_149738_a(world));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister registerer) {
/* 244 */     if (func_149688_o() == Material.field_151587_i) {
/* 245 */       this.icons = new IIcon[] { registerer.func_94245_a("lava_still"), registerer.func_94245_a("lava_flow") };
/*     */     } else {
/* 247 */       this.icons = new IIcon[] { registerer.func_94245_a("water_still"), registerer.func_94245_a("water_flow") };
/* 248 */     }  getFluid().setIcons(this.icons[0], this.icons[1]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int side, int meta) {
/* 255 */     return (side != 0 && side != 1) ? this.icons[1] : this.icons[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDrainableSourceBlock(IBlockAccess world, int x, int y, int z) {
/* 260 */     Block block = world.func_147439_a(x, y, z);
/* 261 */     return (!TFC_Core.isHotWater(block) && block == this && world.func_72805_g(x, y, z) == 0);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Liquids\BlockLiquidStatic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */