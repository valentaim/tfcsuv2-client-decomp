/*     */ package com.bioxx.tfc.Blocks;
/*     */ 
/*     */ import com.bioxx.tfc.TileEntities.TEPartial;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockSlab
/*     */   extends BlockPartial
/*     */ {
/*     */   public BlockSlab() {
/*  23 */     super(Material.field_151576_e);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149645_b() {
/*  29 */     return TFCBlocks.slabRenderId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149651_a(IIconRegister iconRegisterer) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLadder(IBlockAccess world, int x, int y, int z, EntityLivingBase entity) {
/*  40 */     TEPartial te = (TEPartial)world.func_147438_o(x, y, z);
/*  41 */     if (8 - getTopChiselLevel(te.extraData) + getBottomChiselLevel(te.extraData) < 3)
/*     */     {
/*  43 */       if (8 - getSouthChiselLevel(te.extraData) + getNorthChiselLevel(te.extraData) < 3 || 8 - 
/*  44 */         getEastChiselLevel(te.extraData) + getWestChiselLevel(te.extraData) < 3)
/*     */       {
/*  46 */         return true;
/*     */       }
/*     */     }
/*  49 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float func_149712_f(World world, int x, int y, int z) {
/*  55 */     TEPartial te = (TEPartial)world.func_147438_o(x, y, z);
/*  56 */     if (te != null)
/*  57 */       return Block.func_149729_e(te.typeID).func_149712_f(world, x, y, z); 
/*  58 */     return this.field_149782_v;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getTopChiselLevel(long data) {
/*  63 */     return (int)(data >> 16L & 0xFL);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getBottomChiselLevel(long data) {
/*  68 */     return (int)(data >> 4L & 0xFL);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getEastChiselLevel(long data) {
/*  73 */     return (int)(data >> 12L & 0xFL);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getWestChiselLevel(long data) {
/*  78 */     return (int)(data & 0xFL);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getNorthChiselLevel(long data) {
/*  83 */     return (int)(data >> 8L & 0xFL);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getSouthChiselLevel(long data) {
/*  88 */     return (int)(data >> 20L & 0xFL);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
/*  98 */     TEPartial te = (TEPartial)world.func_147438_o(i, j, k);
/*     */     
/* 100 */     if (te != null) {
/*     */       
/* 102 */       short type = te.typeID;
/*     */       
/* 104 */       if (type <= 0) {
/* 105 */         return super.func_149668_a(world, i, j, k);
/*     */       }
/*     */       
/* 108 */       byte extraX = (byte)(int)(te.extraData & 0xFL);
/* 109 */       byte extraY = (byte)(int)(te.extraData >> 4L & 0xFL);
/* 110 */       byte extraZ = (byte)(int)(te.extraData >> 8L & 0xFL);
/* 111 */       byte extraX2 = (byte)(int)(te.extraData >> 12L & 0xFL);
/* 112 */       byte extraY2 = (byte)(int)(te.extraData >> 16L & 0xFL);
/* 113 */       byte extraZ2 = (byte)(int)(te.extraData >> 20L & 0xFL);
/*     */       
/* 115 */       float div = 0.125F;
/*     */       
/* 117 */       return AxisAlignedBB.func_72330_a((i + div * extraX), (j + div * extraY), (k + div * extraZ), (i + 1.0F - div * extraX2), (j + 1.0F - div * extraY2), (k + 1.0F - div * extraZ2));
/*     */     } 
/* 119 */     return AxisAlignedBB.func_72330_a(i, j, k, (i + 1), (j + 1), (k + 1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_149633_g(World world, int i, int j, int k) {
/* 129 */     return func_149668_a(world, i, j, k);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149719_a(IBlockAccess bAccess, int i, int j, int k) {
/* 135 */     TEPartial te = (TEPartial)bAccess.func_147438_o(i, j, k);
/*     */     
/* 137 */     long extraX = te.extraData & 0xFL;
/* 138 */     long extraY = te.extraData >> 4L & 0xFL;
/* 139 */     long extraZ = te.extraData >> 8L & 0xFL;
/* 140 */     long extraX2 = te.extraData >> 12L & 0xFL;
/* 141 */     long extraY2 = te.extraData >> 16L & 0xFL;
/* 142 */     long extraZ2 = te.extraData >> 20L & 0xFL;
/*     */     
/* 144 */     float div = 0.125F;
/*     */     
/* 146 */     func_149676_a(0.0F + div * (float)extraX, 0.0F + div * (float)extraY, 0.0F + div * (float)extraZ, 1.0F - div * (float)extraX2, 1.0F - div * (float)extraY2, 1.0F - div * (float)extraZ2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBlockDestroyedByExplosion(World world, int i, int j, int k) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149636_a(World world, EntityPlayer entityplayer, int i, int j, int k, int l) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149655_b(IBlockAccess par1IBlockAccess, int i, int j, int k) {
/* 162 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
/* 168 */     TEPartial te = null;
/*     */     
/* 170 */     if (world.func_147438_o(x, y, z) instanceof TEPartial) {
/* 171 */       te = (TEPartial)world.func_147438_o(x, y, z);
/*     */     }
/* 173 */     if (te == null) {
/* 174 */       return false;
/*     */     }
/* 176 */     long data = te.extraData;
/*     */     
/* 178 */     switch (side) {
/*     */       
/*     */       case DOWN:
/* 181 */         return (getBottomChiselLevel(data) == 0 && getNorthChiselLevel(data) == 0 && 
/* 182 */           getSouthChiselLevel(data) == 0 && getEastChiselLevel(data) == 0 && getWestChiselLevel(data) == 0);
/*     */       case UP:
/* 184 */         return (getTopChiselLevel(data) == 0 && getNorthChiselLevel(data) == 0 && 
/* 185 */           getSouthChiselLevel(data) == 0 && getEastChiselLevel(data) == 0 && getWestChiselLevel(data) == 0);
/*     */       case NORTH:
/* 187 */         return (getNorthChiselLevel(data) == 0 && getEastChiselLevel(data) == 0 && getWestChiselLevel(data) == 0 && 
/* 188 */           getTopChiselLevel(data) == 0 && getBottomChiselLevel(data) == 0);
/*     */       case SOUTH:
/* 190 */         return (getSouthChiselLevel(data) == 0 && getEastChiselLevel(data) == 0 && getWestChiselLevel(data) == 0 && 
/* 191 */           getTopChiselLevel(data) == 0 && getBottomChiselLevel(data) == 0);
/*     */       case EAST:
/* 193 */         return (getEastChiselLevel(data) == 0 && getNorthChiselLevel(data) == 0 && getSouthChiselLevel(data) == 0 && 
/* 194 */           getTopChiselLevel(data) == 0 && getBottomChiselLevel(data) == 0);
/*     */       case WEST:
/* 196 */         return (getWestChiselLevel(data) == 0 && getNorthChiselLevel(data) == 0 && getSouthChiselLevel(data) == 0 && 
/* 197 */           getTopChiselLevel(data) == 0 && getBottomChiselLevel(data) == 0);
/*     */     } 
/* 199 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item func_149650_a(int metadata, Random rand, int fortune) {
/* 206 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockSlab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */