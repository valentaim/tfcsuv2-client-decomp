/*     */ package com.bioxx.tfc.Blocks;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockThatch
/*     */   extends BlockTerra
/*     */ {
/*     */   public BlockThatch() {
/*  25 */     super(Material.field_151577_b);
/*  26 */     func_149647_a(TFCTabs.TFC_BUILDING);
/*  27 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*  28 */     this.field_149786_r = 255;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149651_a(IIconRegister iconRegisterer) {
/*  34 */     this.field_149761_L = iconRegisterer.func_94245_a("terrafirmacraft:plants/Thatch");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149636_a(World world, EntityPlayer entityplayer, int i, int j, int k, int l) {
/*  44 */     func_149642_a(world, i, j, k, new ItemStack(this, 1, l));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149695_a(World world, int x, int y, int z, Block block) {
/*  50 */     if (!world.field_72995_K) {
/*     */       
/*  52 */       Block b = world.func_147439_a(x, y + 1, z);
/*  53 */       if (TFC_Core.isSoilOrGravel(b) || b instanceof com.bioxx.tfc.Blocks.Terrain.BlockCobble || b instanceof com.bioxx.tfc.Blocks.Terrain.BlockSand)
/*     */       {
/*  55 */         TFC_Core.setBlockToAirWithDrops(world, x, y, z);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149655_b(IBlockAccess bAccess, int x, int y, int z) {
/*  63 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149670_a(World world, int x, int y, int z, Entity entity) {
/*  69 */     entity.field_70159_w *= 0.1D;
/*  70 */     entity.field_70179_y *= 0.1D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
/*  76 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_149646_a(IBlockAccess world, int x, int y, int z, int side) {
/*  83 */     Block block = world.func_147439_a(x, y, z);
/*  84 */     if (side == 0 && this.field_149760_C > 0.0D)
/*  85 */       return true; 
/*  86 */     if (side == 1 && this.field_149756_F < 1.0D)
/*  87 */       return true; 
/*  88 */     if (side == 2 && this.field_149754_D > 0.0D)
/*  89 */       return true; 
/*  90 */     if (side == 3 && this.field_149757_G < 1.0D)
/*  91 */       return true; 
/*  92 */     if (side == 4 && this.field_149759_B > 0.0D)
/*  93 */       return true; 
/*  94 */     if (side == 5 && this.field_149755_E < 1.0D) {
/*  95 */       return true;
/*     */     }
/*  97 */     return !block.func_149662_c();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149742_c(World world, int x, int y, int z) {
/* 103 */     return (func_149718_j(world, x, y, z) && super.func_149742_c(world, x, y, z));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149662_c() {
/* 109 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149686_d() {
/* 115 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockThatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */