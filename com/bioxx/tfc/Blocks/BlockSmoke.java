/*     */ package com.bioxx.tfc.Blocks;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.MapColor;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockSmoke
/*     */   extends BlockTerra
/*     */ {
/*     */   public BlockSmoke() {
/*  22 */     super((new Material(MapColor.field_151666_j)).func_76231_i());
/*  23 */     func_149647_a(null);
/*  24 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*  25 */     func_149675_a(true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149651_a(IIconRegister iconRegisterer) {
/*  31 */     this.field_149761_L = iconRegisterer.func_94245_a("terrafirmacraft:Smoke");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_149720_d(IBlockAccess access, int x, int y, int z) {
/*  38 */     return 6710886;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149646_a(IBlockAccess access, int x, int y, int z, int side) {
/*  44 */     return (access.func_147439_a(x, y, z) != this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_149701_w() {
/*  51 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149662_c() {
/*  57 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149674_a(World world, int x, int y, int z, Random rand) {
/*  63 */     verify(world, x, y, z);
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
/*     */   public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
/*  81 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149703_v() {
/*  87 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149726_b(World world, int x, int y, int z) {
/*  93 */     if (!world.field_72995_K) {
/*     */       
/*  95 */       int meta = world.func_72805_g(x, y, z);
/*  96 */       boolean isOdd = ((meta & 0x1) > 0);
/*  97 */       if (meta < 15)
/*     */       {
/*  99 */         if ((isSurrounded(world, x, y + 1, z) || world.field_73012_v.nextInt(5) != 0 || meta < 8) && addSmoke(world, x, y + 1, z, meta)) {
/*     */           
/* 101 */           if (world.field_73012_v.nextInt((16 - meta) / 2) != 0) addSmoke(world, x, y + 1, z + 1, meta); 
/* 102 */           if (world.field_73012_v.nextInt((16 - meta) / 2) != 0) addSmoke(world, x, y + 1, z - 1, meta); 
/* 103 */           if (!isOdd) {
/*     */             
/* 105 */             if (world.field_73012_v.nextBoolean() && addSmoke(world, x + 1, y + 1, z, meta))
/*     */             {
/* 107 */               if (world.field_73012_v.nextInt((16 - meta) / 2) != 0) addSmoke(world, x + 1, y + 1, z + 1, meta); 
/* 108 */               if (world.field_73012_v.nextInt((16 - meta) / 2) != 0) addSmoke(world, x + 1, y + 1, z - 1, meta);
/*     */             
/*     */             }
/*     */           
/*     */           }
/* 113 */           else if (world.field_73012_v.nextBoolean() && addSmoke(world, x - 1, y + 1, z, meta)) {
/*     */             
/* 115 */             if (world.field_73012_v.nextInt((16 - meta) / 2) != 0) addSmoke(world, x - 1, y + 1, z + 1, meta); 
/* 116 */             if (world.field_73012_v.nextInt((16 - meta) / 2) != 0) addSmoke(world, x - 1, y + 1, z - 1, meta);
/*     */           
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isSurrounded(World world, int x, int y, int z) {
/* 127 */     return (world.isSideSolid(x, y, z + 1, ForgeDirection.NORTH) && world.isSideSolid(x, y, z - 1, ForgeDirection.SOUTH) && world
/* 128 */       .isSideSolid(x - 1, y, z, ForgeDirection.EAST) && world.isSideSolid(x + 1, y, z, ForgeDirection.WEST));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149695_a(World world, int x, int y, int z, Block neighborType) {
/* 134 */     verify(world, x, y, z);
/*     */   }
/*     */   
/*     */   private void verify(World world, int x, int y, int z) {
/* 138 */     if (!world.field_72995_K) {
/*     */       
/* 140 */       int thisMeta = world.func_72805_g(x, y, z);
/* 141 */       if (thisMeta == 0)
/*     */         return; 
/* 143 */       boolean hasBase = false;
/* 144 */       if (hasBase(world, x, y - 1, z, thisMeta - 1) || hasBase(world, x, y - 1, z - 1, thisMeta - 1) || 
/* 145 */         hasBase(world, x, y - 1, z + 1, thisMeta - 1) || hasBase(world, x - 1, y - 1, z, thisMeta - 1) || 
/* 146 */         hasBase(world, x - 1, y - 1, z - 1, thisMeta - 1) || hasBase(world, x - 1, y - 1, z + 1, thisMeta - 1) || 
/* 147 */         hasBase(world, x + 1, y - 1, z, thisMeta - 1) || hasBase(world, x + 1, y - 1, z - 1, thisMeta - 1) || 
/* 148 */         hasBase(world, x + 1, y - 1, z + 1, thisMeta - 1))
/*     */       {
/* 150 */         hasBase = true;
/*     */       }
/*     */       
/* 153 */       if (!hasBase) {
/* 154 */         world.func_147468_f(x, y, z);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean hasBase(World world, int x, int y, int z, int meta) {
/* 160 */     return (world.func_72899_e(x, y, z) && world.func_147439_a(x, y, z) == this && world.func_72805_g(x, y, z) == meta);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean addSmoke(World world, int x, int y, int z, int meta) {
/* 165 */     if (world.func_147437_c(x, y, z))
/*     */     {
/* 167 */       return world.func_147465_d(x, y, z, this, meta + 1, 2);
/*     */     }
/* 169 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockSmoke.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */