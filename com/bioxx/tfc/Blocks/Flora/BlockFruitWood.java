/*     */ package com.bioxx.tfc.Blocks.Flora;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.BlockTerraContainer;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.TileEntities.TEFruitTreeWood;
/*     */ import com.bioxx.tfc.api.Constant.Global;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockFruitWood
/*     */   extends BlockTerraContainer
/*     */ {
/*  29 */   private IIcon[] icons = new IIcon[Global.FRUIT_META_NAMES.length];
/*     */ 
/*     */   
/*     */   public BlockFruitWood() {
/*  33 */     super(Material.field_151575_d);
/*  34 */     func_149676_a(0.3F, 0.0F, 0.3F, 0.7F, 1.0F, 0.7F);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean checkOut(World world, int i, int j, int k, int l) {
/*  39 */     return (world.func_147439_a(i, j, k) == this && world.func_72805_g(i, j, k) == l);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149692_a(int j) {
/*  45 */     return j;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IIcon func_149691_a(int i, int j) {
/*  51 */     return this.icons[j];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149651_a(IIconRegister registerer) {
/*  57 */     for (int i = 0; i < 9; i++) {
/*  58 */       this.icons[i] = registerer.func_94245_a("terrafirmacraft:wood/fruit trees/" + Global.FRUIT_META_NAMES[i] + " Wood");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149636_a(World world, EntityPlayer entityplayer, int i, int j, int k, int l) {
/*  65 */     boolean isAxeorSaw = false;
/*  66 */     ItemStack equip = entityplayer.func_71045_bC();
/*  67 */     if (equip != null) {
/*     */       
/*  69 */       int[] equipIDs = OreDictionary.getOreIDs(equip);
/*  70 */       for (int id : equipIDs) {
/*     */         
/*  72 */         String name = OreDictionary.getOreName(id);
/*  73 */         if (name.startsWith("itemAxe") || name.startsWith("itemSaw")) {
/*     */           
/*  75 */           isAxeorSaw = true;
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*  80 */     if (isAxeorSaw) {
/*     */       
/*  82 */       int x = i;
/*  83 */       int y = 0;
/*  84 */       int z = k;
/*     */ 
/*     */       
/*  87 */       if (world.func_147439_a(i, j + 1, k) == this || world.func_147439_a(i, j - 1, k) == this) {
/*     */ 
/*     */         
/*  90 */         boolean[][][] checkArray = new boolean[11][50][11];
/*     */         
/*  92 */         if (TFC_Core.isGrass(world.func_147439_a(i, j + y - 1, k)) || TFC_Core.isDirt(world.func_147439_a(i, j + y - 1, k))) {
/*     */           
/*  94 */           boolean reachedTop = false;
/*  95 */           while (!reachedTop)
/*     */           {
/*  97 */             if (world.func_147437_c(x, j + y + 1, z))
/*     */             {
/*  99 */               reachedTop = true;
/*     */             }
/* 101 */             scanLogs(world, i, j + y, k, l, checkArray, 6, y, 6);
/* 102 */             y++;
/*     */           }
/*     */         
/*     */         } 
/* 106 */       } else if (world.func_147439_a(i + 1, j, k) == this || world
/* 107 */         .func_147439_a(i - 1, j, k) == this || world
/* 108 */         .func_147439_a(i, j, k + 1) == this || world
/* 109 */         .func_147439_a(i, j, k - 1) == this) {
/*     */         
/* 111 */         Random r = new Random();
/* 112 */         if (r.nextInt(100) > 50 && isAxeorSaw) {
/*     */           
/* 114 */           if (l < 8 && (world
/* 115 */             .func_147439_a(i + 1, j, k) == TFCBlocks.fruitTreeLeaves2 || world
/* 116 */             .func_147439_a(i - 1, j, k) == TFCBlocks.fruitTreeLeaves2 || world
/* 117 */             .func_147439_a(i, j, k + 1) == TFCBlocks.fruitTreeLeaves2 || world
/* 118 */             .func_147439_a(i, j, k - 1) == TFCBlocks.fruitTreeLeaves2 || world
/* 119 */             .func_147439_a(i, j + 1, k) == TFCBlocks.fruitTreeLeaves2 || world
/* 120 */             .func_147439_a(i, j - 1, k) == TFCBlocks.fruitTreeLeaves2))
/*     */           {
/* 122 */             l += 8;
/*     */           }
/* 124 */           func_149642_a(world, i, j, k, new ItemStack(TFCItems.fruitTreeSapling, 1, l));
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
/*     */   public Item func_149650_a(int i, Random random, int j) {
/* 137 */     return TFCItems.logs;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149695_a(World world, int i, int j, int k, Block block) {
/* 143 */     boolean check = false;
/* 144 */     for (int h = -1; h <= 1; h++) {
/*     */       
/* 146 */       for (int g = -1; g <= 1; g++) {
/*     */         
/* 148 */         for (int f = -1; f <= 1; f++) {
/*     */           
/* 150 */           if (world.func_147439_a(i + h, j + g, k + f) == this && world.func_72805_g(i + h, j + g, k + f) == world.func_72805_g(i, j, k))
/* 151 */             check = true; 
/*     */         } 
/*     */       } 
/*     */     } 
/* 155 */     if (!check) {
/* 156 */       world.func_147468_f(i, j, k);
/*     */     }
/*     */   }
/*     */   
/*     */   private void scanLogs(World world, int i, int j, int k, int l, boolean[][][] checkArray, int x, int y, int z) {
/* 161 */     if (y >= 0) {
/*     */       
/* 163 */       checkArray[x][y][z] = true;
/* 164 */       int offsetX = 0;
/* 165 */       int offsetY = 0;
/* 166 */       int offsetZ = 0;
/*     */       
/* 168 */       for (offsetY = 0; offsetY <= 1; offsetY++) {
/*     */         
/* 170 */         for (offsetX = -1; offsetX <= 1; offsetX++) {
/*     */           
/* 172 */           for (offsetZ = -1; offsetZ <= 1; offsetZ++) {
/*     */             
/* 174 */             if (x + offsetX < 11 && x + offsetX >= 0 && z + offsetZ < 11 && z + offsetZ >= 0 && y + offsetY < 50 && y + offsetY >= 0)
/*     */             {
/* 176 */               if (checkOut(world, i + offsetX, j + offsetY, k + offsetZ, l) && !checkArray[x + offsetX][y + offsetY][z + offsetZ])
/* 177 */                 scanLogs(world, i + offsetX, j + offsetY, k + offsetZ, l, checkArray, x + offsetX, y + offsetY, z + offsetZ); 
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/* 182 */       world.func_147468_f(i, j, k);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149645_b() {
/* 189 */     return TFCBlocks.woodFruitRenderId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149662_c() {
/* 195 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149686_d() {
/* 201 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149646_a(IBlockAccess par1iBlockAccess, int par2, int par3, int par4, int par5) {
/* 207 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
/* 213 */     if (world.func_147439_a(i, j - 1, k) == this || world.func_147439_a(i, j - 1, k).func_149662_c())
/* 214 */       return AxisAlignedBB.func_72330_a(i + 0.3D, j, k + 0.3D, i + 0.7D, (j + 1), k + 0.7D); 
/* 215 */     return AxisAlignedBB.func_72330_a(i, j + 0.4D, k, (i + 1), j + 0.6D, (k + 1));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_149633_g(World world, int i, int j, int k) {
/* 221 */     if (world.func_147439_a(i, j - 1, k) == this || world.func_147439_a(i, j - 1, k).func_149662_c())
/* 222 */       return AxisAlignedBB.func_72330_a(i + 0.3D, j, k + 0.3D, i + 0.7D, (j + 1), k + 0.7D); 
/* 223 */     return AxisAlignedBB.func_72330_a(i, j + 0.4D, k, (i + 1), j + 0.6D, (k + 1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149719_a(IBlockAccess world, int i, int j, int k) {
/* 232 */     if (world.func_147439_a(i, j - 1, k) == this || world.func_147439_a(i, j - 1, k).func_149662_c()) {
/* 233 */       func_149676_a(0.3F, 0.0F, 0.3F, 0.7F, 1.0F, 0.7F);
/*     */     } else {
/* 235 */       func_149676_a(0.0F, 0.4F, 0.0F, 1.0F, 0.6F, 1.0F);
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
/*     */ 
/*     */   
/*     */   public void func_149674_a(World world, int i, int j, int k, Random rand) {}
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
/*     */ 
/*     */   
/*     */   public static String getType(int meta) {
/* 376 */     switch (meta) {
/*     */       case 0:
/* 378 */         return Global.FRUIT_META_NAMES[0];
/* 379 */       case 1: return Global.FRUIT_META_NAMES[1];
/* 380 */       case 2: return Global.FRUIT_META_NAMES[2];
/* 381 */       case 3: return Global.FRUIT_META_NAMES[3];
/* 382 */       case 4: return Global.FRUIT_META_NAMES[4];
/* 383 */       case 5: return Global.FRUIT_META_NAMES[5];
/* 384 */       case 6: return Global.FRUIT_META_NAMES[6];
/* 385 */       case 7: return Global.FRUIT_META_NAMES[7];
/* 386 */       case 8: return Global.FRUIT_META_NAMES[8];
/*     */     } 
/* 388 */     return "";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity func_149915_a(World var1, int var2) {
/* 394 */     return (TileEntity)new TEFruitTreeWood();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149749_a(World world, int x, int y, int z, Block block, int metadata) {
/* 400 */     if (!world.field_72995_K && checkOut(world, x, y - 1, z, metadata) && world.func_147438_o(x, y - 1, z) != null)
/* 401 */       ((TEFruitTreeWood)world.func_147438_o(x, y - 1, z)).initBirth(); 
/* 402 */     super.func_149749_a(world, x, y, z, block, metadata);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
/* 408 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Flora\BlockFruitWood.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */