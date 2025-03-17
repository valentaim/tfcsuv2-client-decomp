/*     */ package com.bioxx.tfc.Blocks;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.TileEntities.TEFarmland;
/*     */ import com.bioxx.tfc.api.Constant.Global;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockContainer;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.EnumPlantType;
/*     */ import net.minecraftforge.common.IPlantable;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockFarmland
/*     */   extends BlockContainer
/*     */ {
/*     */   private Block dirtBlock;
/*     */   private IIcon[] dirtTexture;
/*     */   private int textureOffset;
/*     */   
/*     */   public BlockFarmland(Block block, int tex) {
/*  39 */     super(Material.field_151578_c);
/*  40 */     func_149675_a(true);
/*  41 */     this.dirtBlock = block;
/*  42 */     this.textureOffset = tex;
/*  43 */     func_149647_a(TFCTabs.TFC_BUILDING);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister registerer) {
/*  50 */     int count = (this.textureOffset == 0) ? 16 : (Global.STONE_ALL.length - 16);
/*  51 */     this.dirtTexture = new IIcon[count];
/*  52 */     for (int i = 0; i < count; i++) {
/*  53 */       this.dirtTexture[i] = registerer.func_94245_a("terrafirmacraft:farmland/Farmland " + Global.STONE_ALL[i + this.textureOffset]);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item item, CreativeTabs tabs, List<ItemStack> list) {
/*  65 */     Boolean addToCreative = Boolean.valueOf(true);
/*     */     
/*  67 */     if (addToCreative.booleanValue()) {
/*     */       int count;
/*     */       
/*  70 */       if (this.textureOffset == 0) { count = 16; }
/*  71 */       else { count = Global.STONE_ALL.length - 16; }
/*     */       
/*  73 */       for (int i = 0; i < count; i++) {
/*  74 */         list.add(new ItemStack(item, 1, i));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149673_e(IBlockAccess access, int x, int y, int z, int side) {
/*  82 */     int meta = access.func_72805_g(x, y, z);
/*  83 */     if (meta < 0 || meta >= this.dirtTexture.length)
/*  84 */       meta = 0; 
/*  85 */     if (side == 1) {
/*  86 */       return this.dirtTexture[meta];
/*     */     }
/*  88 */     return this.dirtBlock.func_149691_a(side, meta);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int side, int meta) {
/*  95 */     if (meta < 0 || meta >= this.dirtTexture.length)
/*  96 */       meta = 0; 
/*  97 */     if (side == ForgeDirection.UP.ordinal()) {
/*  98 */       return this.dirtTexture[meta];
/*     */     }
/* 100 */     return this.dirtBlock.func_149691_a(0, meta);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149692_a(int dmg) {
/* 106 */     return dmg;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
/* 112 */     return AxisAlignedBB.func_72330_a((x + 0), (y + 0), (z + 0), (x + 1), (y + 1), (z + 1));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Item func_149650_a(int metadata, Random rand, int fortune) {
/* 118 */     return Item.func_150899_d(0);
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
/*     */   public static boolean isFreshWaterNearby(World world, int i, int j, int k) {
/* 144 */     for (int x = i - 4; x <= i + 4; x++) {
/*     */       
/* 146 */       for (int y = j; y <= j + 1; y++) {
/*     */         
/* 148 */         for (int z = k - 4; z <= k + 4; z++) {
/*     */           
/* 150 */           if (world.func_72899_e(x, y, z)) {
/*     */             
/* 152 */             Block b = world.func_147439_a(x, y, z);
/* 153 */             if (TFC_Core.isFreshWater(b))
/* 154 */               return true; 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 159 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isSaltWaterNearby(World world, int i, int j, int k) {
/* 164 */     for (int x = i - 4; x <= i + 4; x++) {
/*     */       
/* 166 */       for (int y = j; y <= j + 1; y++) {
/*     */         
/* 168 */         for (int z = k - 4; z <= k + 4; z++) {
/*     */           
/* 170 */           Block b = world.func_147439_a(x, y, z);
/* 171 */           if (TFC_Core.isSaltWater(b))
/* 172 */             return true; 
/*     */         } 
/*     */       } 
/*     */     } 
/* 176 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149662_c() {
/* 182 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity func_149915_a(World world, int meta) {
/* 188 */     return (TileEntity)new TEFarmland();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable) {
/* 194 */     Block plant = plantable.getPlant(world, x, y + 1, z);
/* 195 */     if (plant == Blocks.field_150393_bb || plant == Blocks.field_150394_bc) {
/* 196 */       return false;
/*     */     }
/* 198 */     EnumPlantType plantType = plantable.getPlantType(world, x, y + 1, z);
/* 199 */     if (plantType == EnumPlantType.Crop) {
/* 200 */       return true;
/*     */     }
/* 202 */     return super.canSustainPlant(world, x, y, z, direction, plantable);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockFarmland.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */