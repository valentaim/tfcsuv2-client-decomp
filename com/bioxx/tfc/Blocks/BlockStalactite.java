/*     */ package com.bioxx.tfc.Blocks;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Core.TFC_Textures;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.Explosion;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockStalactite
/*     */   extends BlockTerra
/*     */ {
/*     */   private Random r;
/*     */   
/*     */   public BlockStalactite() {
/*  29 */     func_149672_a(field_149769_e);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149734_b(World world, int i, int j, int k, Random random) {
/*  35 */     if (isStalactite(world.func_72805_g(i, j, k)) && random.nextInt(80) == 0) {
/*     */       
/*  37 */       AxisAlignedBB aabb = func_149668_a(world, i, j, k);
/*     */       
/*  39 */       double xRand = random.nextFloat() * (aabb.field_72336_d - aabb.field_72340_a) + aabb.field_72340_a;
/*  40 */       double zRand = random.nextFloat() * (aabb.field_72334_f - aabb.field_72339_c) + aabb.field_72339_c;
/*     */       
/*  42 */       world.func_72869_a("dripWater", xRand + 0.2D, aabb.field_72338_b + 0.9D, zRand + 0.2D, 0.0D, 0.0D, 0.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149719_a(IBlockAccess access, int i, int j, int k) {
/*  49 */     boolean isStalac = isStalactite(access.func_72805_g(i, j, k));
/*  50 */     boolean isStalag = isStalagmite(access.func_72805_g(i, j, k));
/*     */     
/*  52 */     float f = 0.125F;
/*  53 */     this.r = new Random((i + i * k));
/*  54 */     if (isStalac) {
/*     */       
/*  56 */       float height = TFC_Core.isRawStone(access.func_147439_a(i, j + 1, k)) ? 1.0F : (TFC_Core.isRawStone(access.func_147439_a(i, j + 2, k)) ? 2.0F : 3.0F);
/*  57 */       f = 0.1F + this.r.nextFloat() * 0.025F;
/*  58 */       float width = height * f;
/*  59 */       if (height == 3.0F)
/*  60 */       { height = 0.5F + this.r.nextFloat() * 0.5F; }
/*  61 */       else { height = 1.0F; }
/*  62 */        func_149676_a(width, 1.0F - height, width, 1.0F - width, 1.0F, 1.0F - width);
/*     */     }
/*  64 */     else if (isStalag) {
/*     */       
/*  66 */       float height = TFC_Core.isRawStone(access.func_147439_a(i, j - 1, k)) ? 1.0F : (TFC_Core.isRawStone(access.func_147439_a(i, j - 2, k)) ? 2.0F : 3.0F);
/*  67 */       f = 0.1F + this.r.nextFloat() * 0.025F;
/*  68 */       float width = height * f;
/*  69 */       if (height == 3.0F)
/*  70 */       { height = 0.5F + this.r.nextFloat() * 0.5F; }
/*  71 */       else { height = 1.0F; }
/*  72 */        func_149676_a(width, 0.0F, width, 1.0F - width, height, 1.0F - width);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
/*  79 */     boolean isStalac = isStalactite(world.func_72805_g(i, j, k));
/*  80 */     boolean isStalag = isStalagmite(world.func_72805_g(i, j, k));
/*     */     
/*  82 */     float f = 0.125F;
/*  83 */     this.r = new Random((i + i * k));
/*  84 */     if (isStalac) {
/*     */       
/*  86 */       float height = TFC_Core.isRawStone(world.func_147439_a(i, j + 1, k)) ? 1.0F : (TFC_Core.isRawStone(world.func_147439_a(i, j + 2, k)) ? 2.0F : 3.0F);
/*  87 */       f = 0.1F + this.r.nextFloat() * 0.025F;
/*  88 */       float width = height * f;
/*  89 */       if (height == 3.0F)
/*  90 */       { height = 0.5F + this.r.nextFloat() * 0.5F; }
/*  91 */       else { height = 1.0F; }
/*     */       
/*  93 */       return AxisAlignedBB.func_72330_a((i + width), (j - height), (k + width), ((i + 1) - width), (j + 1), ((k + 1) - width));
/*     */     } 
/*  95 */     if (isStalag) {
/*     */       
/*  97 */       float height = TFC_Core.isRawStone(world.func_147439_a(i, j - 1, k)) ? 1.0F : (TFC_Core.isRawStone(world.func_147439_a(i, j - 2, k)) ? 2.0F : 3.0F);
/*  98 */       f = 0.1F + this.r.nextFloat() * 0.025F;
/*  99 */       float width = height * f;
/* 100 */       if (height == 3.0F)
/* 101 */       { height = 0.5F + this.r.nextFloat() * 0.5F; }
/* 102 */       else { height = 1.0F; }
/* 103 */        return AxisAlignedBB.func_72330_a((i + width), j, (k + width), ((i + 1) - width), (j + height), ((k + 1) - width));
/*     */     } 
/*     */     
/* 106 */     return AxisAlignedBB.func_72330_a(i + this.field_149759_B, j + this.field_149760_C, k + this.field_149754_D, i + this.field_149755_E, j + this.field_149756_F, k + this.field_149757_G);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149673_e(IBlockAccess access, int i, int j, int k, int meta) {
/* 113 */     boolean isStalac = isStalactite(access.func_72805_g(i, j, k));
/* 114 */     boolean isStalag = isStalagmite(access.func_72805_g(i, j, k));
/* 115 */     if (isStalac) {
/*     */       
/* 117 */       if (TFC_Core.isRawStone(access.func_147439_a(i, j + 1, k)))
/* 118 */         return access.func_147439_a(i, j + 1, k).func_149691_a(0, access.func_72805_g(i, j + 1, k)); 
/* 119 */       if (TFC_Core.isRawStone(access.func_147439_a(i, j + 2, k)))
/* 120 */         return access.func_147439_a(i, j + 2, k).func_149691_a(0, access.func_72805_g(i, j + 2, k)); 
/* 121 */       if (TFC_Core.isRawStone(access.func_147439_a(i, j + 3, k))) {
/* 122 */         return access.func_147439_a(i, j + 3, k).func_149691_a(0, access.func_72805_g(i, j + 3, k));
/*     */       }
/* 124 */     } else if (isStalag) {
/*     */       
/* 126 */       if (TFC_Core.isRawStone(access.func_147439_a(i, j - 1, k)))
/* 127 */         return access.func_147439_a(i, j - 1, k).func_149691_a(0, access.func_72805_g(i, j - 1, k)); 
/* 128 */       if (TFC_Core.isRawStone(access.func_147439_a(i, j - 2, k)))
/* 129 */         return access.func_147439_a(i, j - 2, k).func_149691_a(0, access.func_72805_g(i, j - 2, k)); 
/* 130 */       if (TFC_Core.isRawStone(access.func_147439_a(i, j - 3, k)))
/* 131 */         return access.func_147439_a(i, j - 3, k).func_149691_a(0, access.func_72805_g(i, j - 3, k)); 
/*     */     } 
/* 133 */     return TFC_Textures.invisibleTexture;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int par1, int par2) {
/* 140 */     return TFC_Textures.invisibleTexture;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149695_a(World world, int i, int j, int k, Block b) {
/* 146 */     if (!world.field_72995_K)
/*     */     {
/* 148 */       if (!func_149718_j(world, i, j, k)) {
/*     */         
/* 150 */         world.func_147468_f(i, j, k);
/*     */         return;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149718_j(World world, int i, int j, int k) {
/* 159 */     boolean isStalac = isStalactite(world.func_72805_g(i, j, k));
/* 160 */     boolean isStalag = isStalagmite(world.func_72805_g(i, j, k));
/* 161 */     int h = 0;
/* 162 */     if (isStalac) {
/*     */       
/* 164 */       if (TFC_Core.isRawStone(world.func_147439_a(i, j + 1, k))) {
/* 165 */         h = 1;
/* 166 */       } else if (TFC_Core.isRawStone(world.func_147439_a(i, j + 2, k))) {
/* 167 */         h = 2;
/* 168 */       } else if (TFC_Core.isRawStone(world.func_147439_a(i, j + 3, k))) {
/* 169 */         h = 3;
/* 170 */       }  for (int y = h; y > 0; y--)
/*     */       {
/* 172 */         if (world.func_147437_c(i, j + y, k))
/*     */         {
/* 174 */           return false;
/*     */         }
/*     */       }
/*     */     
/* 178 */     } else if (isStalag) {
/*     */       
/* 180 */       if (TFC_Core.isRawStone(world.func_147439_a(i, j - 1, k))) {
/* 181 */         h = 1;
/* 182 */       } else if (TFC_Core.isRawStone(world.func_147439_a(i, j - 2, k))) {
/* 183 */         h = 2;
/* 184 */       } else if (TFC_Core.isRawStone(world.func_147439_a(i, j - 3, k))) {
/* 185 */         h = 3;
/* 186 */       }  for (int y = h; y > 0; y--) {
/*     */         
/* 188 */         if (world.func_147437_c(i, j - y, k))
/*     */         {
/* 190 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/* 194 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149705_a(World world, int x, int y, int z, int i, ItemStack is) {
/* 200 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAir(IBlockAccess world, int x, int y, int z) {
/* 206 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Item func_149650_a(int metadata, Random rand, int fortune) {
/* 212 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isStalagmite(int meta) {
/* 220 */     return ((meta & 0x8) > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isStalactite(int meta) {
/* 228 */     return ((meta & 0x8) == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149659_a(Explosion ex) {
/* 234 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
/* 240 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149662_c() {
/* 246 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149686_d() {
/* 252 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149651_a(IIconRegister registerer) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
/* 263 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockStalactite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */