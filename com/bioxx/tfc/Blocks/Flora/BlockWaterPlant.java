/*     */ package com.bioxx.tfc.Blocks.Flora;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.Terrain.BlockSand;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Food.ItemFoodTFC;
/*     */ import com.bioxx.tfc.TileEntities.TEWaterPlant;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import com.bioxx.tfc.api.Util.Helper;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.ITileEntityProvider;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockWaterPlant
/*     */   extends BlockSand
/*     */   implements ITileEntityProvider
/*     */ {
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon pondWeed;
/*     */   private IIcon seaWeed;
/*     */   private IIcon catTails;
/*  38 */   private static String seaweed = "terrafirmacraft:plants/seaweed";
/*  39 */   private static String pondweed = "terrafirmacraft:plants/pondweed";
/*  40 */   private static String cattails = "terrafirmacraft:plants/Cat Tails";
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockWaterPlant(int texOff) {
/*  45 */     super(texOff);
/*  46 */     float var3 = 0.5F;
/*  47 */     func_149676_a(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 1.0F, 0.5F + var3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149666_a(Item item, CreativeTabs tabs, List list) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149645_b() {
/*  60 */     return TFCBlocks.waterPlantRenderId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149679_a(int i, Random r) {
/*  66 */     return 1 + r.nextInt(i * 2 + 1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
/*  72 */     if (!TFC_Core.isSaltWater(world.func_147439_a(x, y + 1, z))) {
/*  73 */       return new ArrayList<>();
/*     */     }
/*  75 */     ArrayList<ItemStack> ret = new ArrayList<>();
/*  76 */     ret.add(getSeaWeed(world.field_73012_v));
/*  77 */     TEWaterPlant te = (TEWaterPlant)world.func_147438_o(x, y, z);
/*  78 */     if (te != null)
/*  79 */       ret.add(new ItemStack(te.getBlockFromType(), 1, metadata)); 
/*  80 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149699_a(World world, int x, int y, int z, EntityPlayer entityplayer) {
/*  87 */     if (!world.field_72995_K)
/*     */     {
/*  89 */       if (TFC_Core.isSaltWater(world.func_147439_a(x, y + 1, z)) && entityplayer.field_71071_by.func_70448_g() != null && entityplayer.field_71071_by
/*  90 */         .func_70448_g().func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemKnife) {
/*     */         
/*  92 */         func_149642_a(world, x, y, z, getSeaWeed(world.field_73012_v));
/*  93 */         doBeforeFall(world, x, y, z);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float hitX, float hitY, float hitZ) {
/* 102 */     if (!world.field_72995_K)
/*     */     {
/* 104 */       if (TFC_Core.isSaltWater(world.func_147439_a(x, y + 1, z)) && entityplayer.field_71071_by.func_70448_g() != null && entityplayer.field_71071_by
/* 105 */         .func_70448_g().func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemKnife) {
/*     */         
/* 107 */         func_149642_a(world, x, y, z, getSeaWeed(world.field_73012_v));
/* 108 */         doBeforeFall(world, x, y, z);
/* 109 */         return true;
/*     */       } 
/*     */     }
/* 112 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private ItemStack getSeaWeed(Random r) {
/* 117 */     return ItemFoodTFC.createTag(new ItemStack(TFCItems.seaWeed, 1, 0), Helper.roundNumber(2.0F + r.nextFloat() * 5.0F, 10.0F));
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean canThisPlantGrowUnderThisBlock(Block par1) {
/* 122 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149678_a(int par1, boolean par2) {
/* 128 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149695_a(World world, int x, int y, int z, Block block) {
/* 134 */     if (!world.field_72995_K) {
/*     */       
/* 136 */       if (world.func_147439_a(x, y, z).func_149688_o() != Material.field_151586_h)
/*     */       {
/* 138 */         doBeforeFall(world, x, y, z);
/*     */       }
/*     */     } else {
/* 141 */       super.func_149695_a(world, x, y, z, block);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void doBeforeFall(World world, int x, int y, int z) {
/* 146 */     TileEntity te = world.func_147438_o(x, y, z);
/* 147 */     if (te instanceof TEWaterPlant) {
/* 148 */       Block block = ((TEWaterPlant)te).getBlockFromType();
/* 149 */       int meta = world.func_72805_g(x, y, z);
/* 150 */       if (block != null) {
/* 151 */         world.func_147465_d(x, y, z, block, meta, 0);
/*     */       } else {
/*     */         
/* 154 */         world.func_147468_f(x, y, z);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister registerer) {
/* 163 */     this.seaWeed = registerer.func_94245_a(seaweed);
/* 164 */     this.pondWeed = registerer.func_94245_a(pondweed);
/* 165 */     this.catTails = registerer.func_94245_a(cattails);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int par1, int par2) {
/* 172 */     switch (par2) { case 0:
/* 173 */         return this.seaWeed;
/* 174 */       case 1: return this.pondWeed;
/* 175 */       case 2: return this.catTails; }
/* 176 */      return this.catTails;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity func_149915_a(World world, int var2) {
/* 183 */     return (TileEntity)new TEWaterPlant();
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Flora\BlockWaterPlant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */