/*     */ package com.bioxx.tfc.Blocks.Terrain;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.api.Constant.Global;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockMushroom;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.feature.WorldGenBigMushroom;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockFungi
/*     */   extends BlockMushroom
/*     */ {
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon[] icons;
/*     */   
/*     */   public BlockFungi() {
/*  32 */     float var3 = 0.2F;
/*  33 */     func_149676_a(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var3 * 2.0F, 0.5F + var3);
/*  34 */     func_149675_a(true);
/*  35 */     func_149647_a(TFCTabs.TFC_DECORATION);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int par1, int par2) {
/*  45 */     if (par2 >= this.icons.length) par2 = 0; 
/*  46 */     return this.icons[par2];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister register) {
/*  53 */     this.icons = new IIcon[Global.FUNGI_META_NAMES.length];
/*  54 */     for (int i = 0; i < this.icons.length; i++)
/*     */     {
/*     */       
/*  57 */       this.icons[i] = register.func_94245_a(((this.icons.length > 2) ? "terrafirmacraft:plants/" : "") + Global.FUNGI_META_NAMES[i]);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149692_a(int par1) {
/*  67 */     return par1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item item, CreativeTabs tab, List<ItemStack> list) {
/*  77 */     for (int i = 0; i < this.icons.length; i++)
/*     */     {
/*  79 */       list.add(new ItemStack(item, 1, i));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149718_j(World world, int x, int y, int z) {
/*  89 */     if (y >= 0 && y < 256) {
/*     */       
/*  91 */       Block var5 = world.func_147439_a(x, y - 1, z);
/*  92 */       return (var5 == Blocks.field_150391_bh || (world.func_72883_k(x, y, z) < 13 && canThisPlantGrowOnThisBlock(var5)));
/*     */     } 
/*     */ 
/*     */     
/*  96 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canThisPlantGrowOnThisBlock(Block block) {
/* 102 */     return (TFC_Core.isSoil(block) || TFC_Core.isFarmland(block));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149742_c(World world, int x, int y, int z) {
/* 111 */     Block var5 = world.func_147439_a(x, y, z);
/* 112 */     return ((world.func_147437_c(x, y, z) || var5.func_149688_o().func_76222_j()) && 
/* 113 */       canThisPlantGrowOnThisBlock(var5) && 
/* 114 */       func_149718_j(world, x, y, z));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149884_c(World world, int x, int y, int z, Random rnd) {
/* 123 */     Block block = world.func_147439_a(x, y, z);
/* 124 */     int meta = world.func_72805_g(x, y, z);
/* 125 */     WorldGenBigMushroom bigGen = null;
/*     */     
/* 127 */     world.func_147468_f(x, y, z);
/*     */     
/* 129 */     if (this == block && meta == 0) {
/* 130 */       bigGen = new WorldGenBigMushroom(0);
/* 131 */     } else if (this == block && meta == 1) {
/* 132 */       bigGen = new WorldGenBigMushroom(1);
/*     */     } 
/* 134 */     if (bigGen != null && bigGen.func_76484_a(world, rnd, x, y, z))
/*     */     {
/* 136 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 140 */     world.func_147465_d(x, y, z, (Block)this, meta, 3);
/* 141 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149674_a(World world, int x, int y, int z, Random rnd) {
/* 151 */     int meta = world.func_72805_g(x, y, z);
/*     */     
/* 153 */     if (rnd.nextInt(25) == 0) {
/*     */       
/* 155 */       byte var6 = 4;
/* 156 */       int var7 = 5;
/*     */ 
/*     */       
/*     */       int i;
/*     */ 
/*     */       
/* 162 */       for (i = x - var6; i <= x + var6; i++) {
/*     */         
/* 164 */         for (int m = z - var6; m <= z + var6; m++) {
/*     */           
/* 166 */           for (int n = y - 1; n <= y + 1; n++) {
/*     */             
/* 168 */             if (world.func_72899_e(i, n, m) && world.func_147439_a(i, n, m) == this) {
/*     */               
/* 170 */               var7--;
/* 171 */               if (var7 <= 0) {
/*     */                 return;
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 178 */       i = x + rnd.nextInt(3) - 1;
/* 179 */       int j = y + rnd.nextInt(2) - rnd.nextInt(2);
/* 180 */       int k = z + rnd.nextInt(3) - 1;
/*     */       
/* 182 */       for (int var11 = 0; var11 < 4; var11++) {
/*     */         
/* 184 */         if (world.func_72899_e(i, j, k) && world.func_147437_c(i, j, k) && func_149718_j(world, i, j, k)) {
/*     */           
/* 186 */           x = i;
/* 187 */           y = j;
/* 188 */           z = k;
/*     */         } 
/*     */         
/* 191 */         i = x + rnd.nextInt(3) - 1;
/* 192 */         j = y + rnd.nextInt(2) - rnd.nextInt(2);
/* 193 */         k = z + rnd.nextInt(3) - 1;
/*     */       } 
/*     */       
/* 196 */       if (world.func_72899_e(i, j, k) && world.func_147437_c(i, j, k) && func_149718_j(world, i, j, k))
/* 197 */         world.func_147465_d(i, j, k, (Block)this, meta, 2); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Terrain\BlockFungi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */