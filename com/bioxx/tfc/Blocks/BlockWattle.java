/*     */ package com.bioxx.tfc.Blocks;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockWattle
/*     */   extends BlockTerra
/*     */ {
/*     */   public BlockWattle(Material m) {
/*  31 */     super(m);
/*  32 */     func_149647_a(TFCTabs.TFC_BUILDING);
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune) {
/*  37 */     ArrayList<ItemStack> drops = new ArrayList<>();
/*  38 */     Random rand = new Random();
/*  39 */     drops.add(new ItemStack(TFCItems.stick, rand.nextInt(2) + 1));
/*  40 */     return drops;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149689_a(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack is) {
/*  46 */     if (TFCOptions.enableDebugMode && world.field_72995_K) {
/*     */       
/*  48 */       int metadata = world.func_72805_g(i, j, k);
/*  49 */       TerraFirmaCraft.LOG.info("Meta=" + func_149739_a() + ":" + metadata);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
/*  56 */     if (TFCOptions.enableDebugMode && world.field_72995_K) {
/*     */       
/*  58 */       int metadata = world.func_72805_g(x, y, z);
/*  59 */       TerraFirmaCraft.LOG.info("Meta = " + func_149739_a() + ":" + metadata);
/*     */     } 
/*  61 */     if (!world.field_72995_K)
/*     */     {
/*  63 */       ItemStack itemStack = player.field_71071_by.func_70448_g();
/*     */     }
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
/*  79 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149686_d() {
/*  87 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_149655_b(IBlockAccess p_149655_1_, int p_149655_2_, int p_149655_3_, int p_149655_4_) {
/*  92 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149651_a(IIconRegister iconRegisterer) {
/*  98 */     this.field_149761_L = iconRegisterer.func_94245_a("terrafirmacraft:wattle");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149662_c() {
/* 107 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149645_b() {
/* 116 */     return TFCBlocks.wattleRenderId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
/* 122 */     func_149719_a((IBlockAccess)p_149668_1_, p_149668_2_, p_149668_3_, p_149668_4_);
/* 123 */     this.field_149756_F = 1.5D;
/* 124 */     return super.func_149668_a(p_149668_1_, p_149668_2_, p_149668_3_, p_149668_4_);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149719_a(IBlockAccess world, int x, int y, int z) {
/* 130 */     boolean flag0 = canConnectWallTo(world, x, y, z - 1);
/* 131 */     boolean flag1 = canConnectWallTo(world, x, y, z + 1);
/* 132 */     boolean flag2 = canConnectWallTo(world, x - 1, y, z);
/* 133 */     boolean flag3 = canConnectWallTo(world, x + 1, y, z);
/*     */     
/* 135 */     boolean flag0Up = canConnectWallTo(world, x, y, z - 1);
/* 136 */     boolean flag1Up = canConnectWallTo(world, x, y, z + 1);
/* 137 */     boolean flag2Up = canConnectWallTo(world, x - 1, y, z);
/* 138 */     boolean flag3Up = canConnectWallTo(world, x + 1, y, z);
/* 139 */     float f = 0.425F;
/* 140 */     float f1 = 0.575F;
/* 141 */     float f2 = 0.45F;
/* 142 */     float f3 = 0.575F;
/* 143 */     float f4 = 1.1F;
/*     */     
/* 145 */     if (flag0)
/*     */     {
/* 147 */       f2 = 0.0F;
/*     */     }
/*     */     
/* 150 */     if (flag1)
/*     */     {
/* 152 */       f3 = 1.0F;
/*     */     }
/*     */     
/* 155 */     if (flag2)
/*     */     {
/* 157 */       f = 0.0F;
/*     */     }
/*     */     
/* 160 */     if (flag3)
/*     */     {
/* 162 */       f1 = 1.0F;
/*     */     }
/*     */     
/* 165 */     if (flag0 && flag1 && !flag2 && !flag3) {
/*     */       
/* 167 */       if (!flag0Up || !flag1Up) {
/* 168 */         f4 = 1.1F;
/*     */       }
/* 170 */       f = 0.425F;
/* 171 */       f1 = 0.575F;
/*     */     }
/* 173 */     else if (!flag0 && !flag1 && flag2 && flag3) {
/*     */       
/* 175 */       if (!flag2Up || !flag3Up) {
/* 176 */         f4 = 1.1F;
/*     */       }
/* 178 */       f2 = 0.425F;
/* 179 */       f3 = 0.575F;
/*     */     } 
/* 181 */     f4 = 1.0F;
/* 182 */     func_149676_a(f, 0.0F, f2, f1, f4, f3);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canConnectWallTo(IBlockAccess access, int i, int j, int k) {
/* 188 */     Block block = access.func_147439_a(i, j, k);
/* 189 */     if (block != this && block != Blocks.field_150396_be && block != TFCBlocks.fenceGate && block != TFCBlocks.fenceGate2 && !(block instanceof com.bioxx.tfc.Blocks.Vanilla.BlockCustomWall)) {
/* 190 */       return (block != null && block.func_149688_o().func_76218_k() && block.func_149686_d()) ? ((block.func_149688_o() != Material.field_151572_C)) : false;
/*     */     }
/* 192 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_149646_a(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_) {
/* 202 */     return (p_149646_5_ == 0) ? super.func_149646_a(p_149646_1_, p_149646_2_, p_149646_3_, p_149646_4_, p_149646_5_) : true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlaceTorchOnTop(World world, int x, int y, int z) {
/* 207 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) {
/* 213 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockWattle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */