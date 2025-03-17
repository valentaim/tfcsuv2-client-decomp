/*     */ package com.bioxx.tfc.Blocks;
/*     */ 
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.Explosion;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockCharcoal
/*     */   extends BlockTerra
/*     */ {
/*     */   public BlockCharcoal() {
/*  26 */     super(Material.field_151578_c);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IIcon func_149691_a(int side, int meta) {
/*  32 */     return this.field_149761_L;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149651_a(IIconRegister iconRegisterer) {
/*  38 */     this.field_149761_L = iconRegisterer.func_94245_a("terrafirmacraft:devices/Charcoal");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeReplacedByLeaves(IBlockAccess w, int x, int y, int z) {
/*  44 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149662_c() {
/*  50 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149686_d() {
/*  56 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149681_a(World world, int x, int y, int z, int side, EntityPlayer entityplayer) {
/*  62 */     if (!world.field_72995_K)
/*     */     {
/*  64 */       if (entityplayer.field_71075_bZ.field_75098_d) {
/*     */         
/*  66 */         world.func_147468_f(x, y, z);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/*  71 */         boolean isShovel = false;
/*  72 */         ItemStack equip = entityplayer.func_71045_bC();
/*  73 */         if (equip != null)
/*     */         {
/*  75 */           if (equip.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemCustomShovel) {
/*  76 */             isShovel = true;
/*     */           }
/*     */         }
/*  79 */         if (isShovel) {
/*     */           
/*  81 */           int top = 0;
/*  82 */           for (; world.func_147439_a(x, y + top + 1, z) == this; top++);
/*     */           
/*  84 */           func_149642_a(world, x, y, z, new ItemStack(TFCItems.coal, 1, 1));
/*  85 */           if (side - 1 > 0) {
/*     */             
/*  87 */             if (world.func_147439_a(x, y + 1, z) == this) {
/*     */               
/*  89 */               int m1 = world.func_72805_g(x, y + top, z);
/*  90 */               if (m1 - 1 > 0) {
/*  91 */                 world.func_72921_c(x, y + top, z, m1 - 1, 2);
/*     */               } else {
/*  93 */                 world.func_147468_f(x, y + top, z);
/*     */               } 
/*  95 */               world.func_147465_d(x, y, z, this, 8, 2);
/*     */             }
/*     */             else {
/*     */               
/*  99 */               world.func_147465_d(x, y, z, this, side - 1, 2);
/*     */             } 
/*     */             
/* 102 */             world.func_147471_g(x, y, z);
/* 103 */             world.func_147471_g(x, y + top, z);
/*     */           } else {
/*     */             
/* 106 */             world.func_147465_d(x, y, z, Blocks.field_150350_a, 0, 2);
/*     */           } 
/*     */         } else {
/*     */           
/* 110 */           world.func_147465_d(x, y, z, this, side, 2);
/*     */         } 
/*     */         
/* 113 */         if (side == 0) {
/* 114 */           world.func_147468_f(x, y, z);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z) {
/* 122 */     if (world.func_72805_g(x, y, z) > 0)
/* 123 */       return false; 
/* 124 */     return world.func_147468_f(x, y, z);
/*     */   }
/*     */ 
/*     */   
/*     */   public void combineCharcoalDown(World world, int x, int y, int z) {
/* 129 */     int meta = world.func_72805_g(x, y, z);
/* 130 */     int bottomMeta = world.func_72805_g(x, y - 1, z);
/*     */     
/* 132 */     if (bottomMeta < 8) {
/*     */       
/* 134 */       bottomMeta += meta;
/* 135 */       int m2 = 0;
/* 136 */       if (bottomMeta > 8) {
/*     */         
/* 138 */         m2 = bottomMeta - 8;
/* 139 */         bottomMeta = 8;
/*     */       } 
/*     */       
/* 142 */       world.func_147465_d(x, y - 1, z, this, bottomMeta, 2);
/*     */       
/* 144 */       if (m2 > 0) {
/*     */         
/* 146 */         world.func_147465_d(x, y, z, this, m2, 2);
/* 147 */         world.func_147460_e(x, y + 1, z, this);
/*     */       } else {
/*     */         
/* 150 */         world.func_147468_f(x, y, z);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void combineCharcoalUp(World world, int x, int y, int z) {
/* 156 */     int meta = world.func_72805_g(x, y + 1, z);
/* 157 */     int bottomMeta = world.func_72805_g(x, y, z);
/*     */     
/* 159 */     if (bottomMeta < 8) {
/*     */       
/* 161 */       bottomMeta += meta;
/* 162 */       int m2 = 0;
/* 163 */       if (bottomMeta > 8) {
/*     */         
/* 165 */         m2 = bottomMeta - 8;
/* 166 */         bottomMeta = 8;
/*     */       } 
/*     */       
/* 169 */       world.func_147465_d(x, y, z, this, bottomMeta, 2);
/*     */       
/* 171 */       if (m2 > 0) {
/*     */         
/* 173 */         world.func_147465_d(x, y + 1, z, this, m2, 2);
/* 174 */         world.func_147460_e(x, y + 2, z, this);
/*     */       } else {
/*     */         
/* 177 */         world.func_147468_f(x, y + 1, z);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_149695_a(World world, int x, int y, int z, Block block) {
/* 184 */     if (!world.field_72995_K)
/*     */     {
/* 186 */       if (world.func_147437_c(x, y - 1, z)) {
/*     */         
/* 188 */         int meta = world.func_72805_g(x, y, z);
/* 189 */         world.func_147465_d(x, y - 1, z, this, meta, 2);
/* 190 */         world.func_147468_f(x, y, z);
/*     */       }
/*     */       else {
/*     */         
/* 194 */         if (world.func_147439_a(x, y - 1, z) == this) {
/* 195 */           combineCharcoalDown(world, x, y, z);
/*     */         }
/* 197 */         if (world.func_147439_a(x, y + 1, z) == this) {
/* 198 */           combineCharcoalUp(world, x, y, z);
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
/*     */   public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
/* 210 */     int md = world.func_72805_g(x, y, z);
/*     */     
/* 212 */     if (md == 8) {
/* 213 */       return AxisAlignedBB.func_72330_a(x, y, z, (x + 1), (y + 1), (z + 1));
/*     */     }
/* 215 */     return AxisAlignedBB.func_72330_a(x, y, z, (x + 1), (y + 0.125F * md), (z + 1));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149719_a(IBlockAccess bAccess, int x, int y, int z) {
/* 221 */     int meta = bAccess.func_72805_g(x, y, z);
/* 222 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F * meta, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149659_a(Explosion ex) {
/* 228 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBlockExploded(World world, int x, int y, int z, Explosion ex) {
/* 234 */     if (!world.field_72995_K) {
/*     */       
/* 236 */       int amount = world.func_72805_g(x, y, z);
/* 237 */       if (amount > 0) {
/*     */         
/* 239 */         Random rand = new Random();
/*     */         
/* 241 */         amount = rand.nextInt(amount + 1) + amount / 2;
/* 242 */         func_149642_a(world, x, y, z, new ItemStack(TFCItems.coal, amount, 1));
/*     */       } 
/*     */     } 
/*     */     
/* 246 */     super.onBlockExploded(world, x, y, z, ex);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149723_a(World world, int x, int y, int z, Explosion ex) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
/* 257 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockCharcoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */