/*     */ package com.bioxx.tfc.Blocks.Flora;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.BlockTerra;
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockFlora
/*     */   extends BlockTerra
/*     */ {
/*     */   private IIcon[] icons;
/*     */   private String[] metaNames;
/*     */   
/*     */   public BlockFlora() {
/*  27 */     super(Material.field_151585_k);
/*  28 */     this.metaNames = new String[] { "Golden Rod", "Cat Tails" };
/*  29 */     this.icons = new IIcon[this.metaNames.length];
/*  30 */     func_149676_a(0.3F, 0.0F, 0.3F, 0.7F, 0.7F, 0.7F);
/*  31 */     func_149647_a(TFCTabs.TFC_DECORATION);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4) {
/*  37 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149662_c() {
/*  43 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149686_d() {
/*  49 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149645_b() {
/*  55 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister par1IconRegister) {
/*  62 */     for (int i = 0; i < this.icons.length; i++) {
/*  63 */       this.icons[i] = par1IconRegister.func_94245_a("terrafirmacraft:plants/" + this.metaNames[i]);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int par1, int par2) {
/*  70 */     if (par2 >= this.icons.length)
/*  71 */       par2 = 0; 
/*  72 */     return this.icons[par2];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149718_j(World world, int x, int y, int z) {
/*  78 */     return ((world.func_72883_k(x, y, z) >= 8 || world
/*  79 */       .func_72937_j(x, y, z)) && 
/*  80 */       canThisPlantGrowOnThisBlock(world.func_147439_a(x, y - 1, z)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149695_a(World world, int i, int j, int k, Block par5) {
/*  86 */     super.func_149695_a(world, i, j, k, par5);
/*  87 */     if (!func_149718_j(world, i, j, k)) {
/*  88 */       world.func_147468_f(i, j, k);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_149742_c(World world, int x, int y, int z) {
/*  94 */     Block block = world.func_147439_a(x, y, z);
/*  95 */     return ((world.func_147437_c(x, y, z) || block.func_149688_o().func_76222_j()) && canThisPlantGrowOnThisBlock(world.func_147439_a(x, y - 1, z)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean canThisPlantGrowOnThisBlock(Block block) {
/* 100 */     return (TFC_Core.isSoil(block) || TFC_Core.isFarmland(block));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Item func_149650_a(int par1, Random par2Random, int par3) {
/* 106 */     return Item.func_150898_a((Block)this);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Flora\BlockFlora.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */