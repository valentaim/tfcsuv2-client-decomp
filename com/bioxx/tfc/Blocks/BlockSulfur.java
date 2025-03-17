/*     */ package com.bioxx.tfc.Blocks;
/*     */ 
/*     */ import com.bioxx.tfc.Core.CollisionRayTraceStandard;
/*     */ import com.bioxx.tfc.api.Constant.Global;
/*     */ import com.bioxx.tfc.api.Interfaces.ICustomCollision;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ public class BlockSulfur
/*     */   extends BlockTerra
/*     */   implements ICustomCollision
/*     */ {
/*  31 */   private int itemMeta = Arrays.<String>asList(Global.POWDER).indexOf("Sulfur Powder");
/*  32 */   private IIcon[] icons = new IIcon[4];
/*     */ 
/*     */   
/*     */   public BlockSulfur(Material material) {
/*  36 */     super(material);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IIcon func_149691_a(int i, int j) {
/*  42 */     return this.icons[j];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
/*  48 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149692_a(int dmg) {
/*  54 */     return this.itemMeta;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149651_a(IIconRegister registerer) {
/*  60 */     for (int i = 0; i < 4; i++) {
/*  61 */       this.icons[i] = registerer.func_94245_a("terrafirmacraft:ores/Sulfur" + i);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149645_b() {
/*  67 */     return TFCBlocks.sulfurRenderId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149636_a(World world, EntityPlayer entityplayer, int i, int j, int k, int l) {
/*  74 */     func_149642_a(world, i, j, k, new ItemStack(TFCItems.powder, func_149745_a(new Random()), this.itemMeta));
/*     */   }
/*     */ 
/*     */   
/*     */   public Item func_149650_a(int par1, Random par2Random, int par3) {
/*  79 */     return TFCItems.powder;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149637_q() {
/*  85 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149662_c() {
/*  91 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149686_d() {
/*  97 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149695_a(World world, int i, int j, int k, Block l) {
/* 103 */     int num = 0;
/* 104 */     if (world.func_147439_a(i, j, k + 1).isSideSolid((IBlockAccess)world, i, j, k + 1, ForgeDirection.NORTH))
/* 105 */       num++; 
/* 106 */     if (world.func_147439_a(i, j, k - 1).isSideSolid((IBlockAccess)world, i, j, k - 1, ForgeDirection.SOUTH))
/* 107 */       num++; 
/* 108 */     if (world.func_147439_a(i + 1, j, k).isSideSolid((IBlockAccess)world, i + 1, j, k, ForgeDirection.WEST))
/* 109 */       num++; 
/* 110 */     if (world.func_147439_a(i - 1, j, k).isSideSolid((IBlockAccess)world, i - 1, j, k, ForgeDirection.EAST))
/* 111 */       num++; 
/* 112 */     if (world.func_147439_a(i, j + 1, k).isSideSolid((IBlockAccess)world, i, j + 1, k, ForgeDirection.DOWN))
/* 113 */       num++; 
/* 114 */     if (world.func_147439_a(i, j - 1, k).isSideSolid((IBlockAccess)world, i, j - 1, k, ForgeDirection.UP))
/* 115 */       num++; 
/* 116 */     if (num == 0) {
/*     */       
/* 118 */       world.func_147468_f(i, j, k);
/* 119 */       func_149642_a(world, i, j, k, new ItemStack(TFCItems.powder, func_149745_a(new Random()), this.itemMeta));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149745_a(Random random) {
/* 128 */     return 1 + random.nextInt(2);
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
/*     */   public void func_149743_a(World world, int i, int j, int k, AxisAlignedBB aabb, List<AxisAlignedBB> list, Entity entity) {
/* 175 */     ArrayList<Object[]> alist = new ArrayList();
/* 176 */     addCollisionBoxesToList(world, i, j, k, alist);
/* 177 */     for (Object[] obj : alist) {
/*     */       
/* 179 */       AxisAlignedBB plankAABB = (AxisAlignedBB)obj[0];
/* 180 */       plankAABB.field_72340_a += i; plankAABB.field_72336_d += i;
/* 181 */       plankAABB.field_72338_b += j; plankAABB.field_72337_e += j;
/* 182 */       plankAABB.field_72339_c += k; plankAABB.field_72334_f += k;
/* 183 */       if (aabb.func_72326_a(plankAABB))
/*     */       {
/* 185 */         list.add(plankAABB);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MovingObjectPosition func_149731_a(World world, int x, int y, int z, Vec3 player, Vec3 view) {
/* 193 */     return CollisionRayTraceStandard.collisionRayTrace(this, world, x, y, z, player, view);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addCollisionBoxesToList(World world, int x, int y, int z, List<Object[]> list) {
/* 200 */     if (world.func_147439_a(x, y, z + 1).isSideSolid((IBlockAccess)world, x, y, z + 1, ForgeDirection.NORTH))
/*     */     {
/* 202 */       list.add(new Object[] { AxisAlignedBB.func_72330_a(0.0D, 0.0D, 0.9900000095367432D, 1.0D, 1.0D, 1.0D) });
/*     */     }
/* 204 */     if (world.func_147439_a(x, y, z - 1).isSideSolid((IBlockAccess)world, x, y, z - 1, ForgeDirection.SOUTH))
/*     */     {
/* 206 */       list.add(new Object[] { AxisAlignedBB.func_72330_a(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.009999999776482582D) });
/*     */     }
/* 208 */     if (world.func_147439_a(x + 1, y, z).isSideSolid((IBlockAccess)world, x + 1, y, z, ForgeDirection.EAST))
/*     */     {
/* 210 */       list.add(new Object[] { AxisAlignedBB.func_72330_a(0.9900000095367432D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D) });
/*     */     }
/* 212 */     if (world.func_147439_a(x - 1, y, z).isSideSolid((IBlockAccess)world, x - 1, y, z, ForgeDirection.WEST))
/*     */     {
/* 214 */       list.add(new Object[] { AxisAlignedBB.func_72330_a(0.0D, 0.0D, 0.0D, 0.009999999776482582D, 1.0D, 1.0D) });
/*     */     }
/* 216 */     if (world.func_147439_a(x, y + 1, z).isSideSolid((IBlockAccess)world, x, y + 1, z, ForgeDirection.DOWN))
/*     */     {
/* 218 */       list.add(new Object[] { AxisAlignedBB.func_72330_a(0.0D, 0.9900000095367432D, 0.0D, 1.0D, 1.0D, 1.0D) });
/*     */     }
/* 220 */     if (world.func_147439_a(x, y - 1, z).isSideSolid((IBlockAccess)world, x, y - 1, z, ForgeDirection.UP))
/*     */     {
/* 222 */       list.add(new Object[] { AxisAlignedBB.func_72330_a(0.0D, 0.0D, 0.0D, 1.0D, 0.009999999776482582D, 1.0D) });
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockSulfur.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */