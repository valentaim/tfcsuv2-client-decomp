/*     */ package com.bioxx.tfc.Blocks;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Food.CropIndex;
/*     */ import com.bioxx.tfc.Food.CropManager;
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import com.bioxx.tfc.TileEntities.TECrop;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockContainer;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.particle.EffectRenderer;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.stats.StatList;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ 
/*     */ 
/*     */ public class BlockCrop
/*     */   extends BlockContainer
/*     */ {
/*  35 */   private IIcon[] iconsCarrots = new IIcon[5];
/*  36 */   private IIcon[] iconsGarlic = new IIcon[5];
/*  37 */   private IIcon[] iconsCorn = new IIcon[6];
/*  38 */   private IIcon[] iconsCabbage = new IIcon[6];
/*  39 */   private IIcon[] iconsTomato = new IIcon[8];
/*  40 */   private IIcon[] iconsPepperRed = new IIcon[7];
/*  41 */   private IIcon[] iconsPepperYellow = new IIcon[7];
/*  42 */   private IIcon[] iconsWheat = new IIcon[8];
/*  43 */   private IIcon[] iconsRye = new IIcon[8];
/*  44 */   private IIcon[] iconsBarley = new IIcon[8];
/*  45 */   private IIcon[] iconsOat = new IIcon[8];
/*  46 */   private IIcon[] iconsRice = new IIcon[8];
/*  47 */   private IIcon[] iconsGreenbean = new IIcon[7];
/*  48 */   private IIcon[] iconsOnion = new IIcon[7];
/*  49 */   private IIcon[] iconsPotato = new IIcon[7];
/*  50 */   private IIcon[] iconsSoybean = new IIcon[7];
/*  51 */   private IIcon[] iconsSquash = new IIcon[7];
/*  52 */   private IIcon[] iconsJute = new IIcon[6];
/*  53 */   private IIcon[] iconsSugarcane = new IIcon[8];
/*     */   
/*     */   public IIcon iconInfest;
/*     */ 
/*     */   
/*     */   public BlockCrop() {
/*  59 */     super(Material.field_151585_k);
/*  60 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.2F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149645_b() {
/*  66 */     return TFCBlocks.cropRenderId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_149651_a(IIconRegister register) {
/*     */     int i;
/*  72 */     for (i = 1; i < 6; i++) {
/*     */       
/*  74 */       this.iconsCarrots[i - 1] = register.func_94245_a("terrafirmacraft:plants/crops/Carrots (" + i + ")");
/*  75 */       this.iconsGarlic[i - 1] = register.func_94245_a("terrafirmacraft:plants/crops/Garlic (" + i + ")");
/*     */     } 
/*  77 */     for (i = 1; i < 7; i++) {
/*     */       
/*  79 */       this.iconsCorn[i - 1] = register.func_94245_a("terrafirmacraft:plants/crops/Corn (" + i + ")");
/*  80 */       this.iconsCabbage[i - 1] = register.func_94245_a("terrafirmacraft:plants/crops/Cabbage (" + i + ")");
/*  81 */       this.iconsJute[i - 1] = register.func_94245_a("terrafirmacraft:plants/crops/Jute (" + i + ")");
/*     */     } 
/*  83 */     for (i = 1; i < 8; i++) {
/*     */       
/*  85 */       this.iconsPepperRed[i - 1] = register.func_94245_a("terrafirmacraft:plants/crops/PepperRed (" + i + ")");
/*  86 */       this.iconsPepperYellow[i - 1] = register.func_94245_a("terrafirmacraft:plants/crops/PepperYellow (" + i + ")");
/*  87 */       this.iconsGreenbean[i - 1] = register.func_94245_a("terrafirmacraft:plants/crops/Greenbean (" + i + ")");
/*  88 */       this.iconsOnion[i - 1] = register.func_94245_a("terrafirmacraft:plants/crops/Onion (" + i + ")");
/*  89 */       this.iconsPotato[i - 1] = register.func_94245_a("terrafirmacraft:plants/crops/Potato (" + i + ")");
/*  90 */       this.iconsSquash[i - 1] = register.func_94245_a("terrafirmacraft:plants/crops/Squash (" + i + ")");
/*  91 */       this.iconsSoybean[i - 1] = register.func_94245_a("terrafirmacraft:plants/crops/Soybean (" + i + ")");
/*     */     } 
/*  93 */     for (i = 1; i < 9; i++) {
/*     */       
/*  95 */       this.iconsTomato[i - 1] = register.func_94245_a("terrafirmacraft:plants/crops/Tomato (" + i + ")");
/*  96 */       this.iconsWheat[i - 1] = register.func_94245_a("terrafirmacraft:plants/crops/Wheat (" + i + ")");
/*  97 */       this.iconsRye[i - 1] = register.func_94245_a("terrafirmacraft:plants/crops/Rye (" + i + ")");
/*  98 */       this.iconsBarley[i - 1] = register.func_94245_a("terrafirmacraft:plants/crops/Barley (" + i + ")");
/*  99 */       this.iconsOat[i - 1] = register.func_94245_a("terrafirmacraft:plants/crops/Oat (" + i + ")");
/* 100 */       this.iconsRice[i - 1] = register.func_94245_a("terrafirmacraft:plants/crops/Rice (" + i + ")");
/* 101 */       this.iconsSugarcane[i - 1] = register.func_94245_a("terrafirmacraft:plants/crops/Sugarcane (" + i + ")");
/*     */     } 
/*     */     
/* 104 */     this.iconInfest = register.func_94245_a("terrafirmacraft:bugs");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149673_e(IBlockAccess access, int x, int y, int z, int meta) {
/* 111 */     TECrop te = (TECrop)access.func_147438_o(x, y, z);
/* 112 */     CropIndex crop = CropManager.getInstance().getCropFromId(te.cropId);
/*     */     
/* 114 */     int stage = (int)Math.floor(te.growth);
/* 115 */     if (stage > crop.numGrowthStages) {
/* 116 */       stage = crop.numGrowthStages;
/*     */     }
/* 118 */     switch (te.cropId) {
/*     */       
/*     */       case 0:
/* 121 */         return this.iconsWheat[stage];
/*     */       case 1:
/* 123 */         return this.iconsCorn[stage];
/*     */       case 2:
/* 125 */         return this.iconsTomato[stage];
/*     */       case 3:
/* 127 */         return this.iconsBarley[stage];
/*     */       case 4:
/* 129 */         return this.iconsRye[stage];
/*     */       case 5:
/* 131 */         return this.iconsOat[stage];
/*     */       case 6:
/* 133 */         return this.iconsRice[stage];
/*     */       case 7:
/* 135 */         return this.iconsPotato[stage];
/*     */       case 8:
/* 137 */         return this.iconsOnion[stage];
/*     */       case 9:
/* 139 */         return this.iconsCabbage[stage];
/*     */       case 10:
/* 141 */         return this.iconsGarlic[stage];
/*     */       case 11:
/* 143 */         return this.iconsCarrots[stage];
/*     */       case 12:
/* 145 */         return this.iconsPepperYellow[stage];
/*     */       case 13:
/* 147 */         return this.iconsPepperRed[stage];
/*     */       case 14:
/* 149 */         return this.iconsSoybean[stage];
/*     */       case 15:
/* 151 */         return this.iconsGreenbean[stage];
/*     */       case 16:
/* 153 */         return this.iconsSquash[stage];
/*     */       case 17:
/* 155 */         return this.iconsJute[stage];
/*     */       case 18:
/* 157 */         return this.iconsSugarcane[stage];
/*     */     } 
/* 159 */     return this.iconsCorn[6];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149655_b(IBlockAccess bAccess, int x, int y, int z) {
/* 165 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float hitX, float hitY, float hitZ) {
/* 171 */     TECrop te = (TECrop)world.func_147438_o(x, y, z);
/* 172 */     CropIndex crop = CropManager.getInstance().getCropFromId(te.cropId);
/*     */     
/* 174 */     if (TFCOptions.enableDebugMode) {
/*     */       
/* 176 */       TerraFirmaCraft.LOG.info("Crop ID: " + te.cropId);
/* 177 */       TerraFirmaCraft.LOG.info("Growth: " + te.growth);
/* 178 */       TerraFirmaCraft.LOG.info("Est Growth: " + te.getEstimatedGrowth(crop));
/*     */     } 
/*     */     
/* 181 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149681_a(World world, int i, int j, int k, int l, EntityPlayer player) {
/* 187 */     TECrop te = (TECrop)world.func_147438_o(i, j, k);
/* 188 */     if (!world.field_72995_K) {
/*     */       
/* 190 */       ItemStack itemstack = player.field_71071_by.func_70448_g();
/* 191 */       int[] equipIDs = OreDictionary.getOreIDs(itemstack);
/*     */       
/* 193 */       for (int id : equipIDs) {
/*     */         
/* 195 */         String name = OreDictionary.getOreName(id);
/* 196 */         if (name.startsWith("itemScythe")) {
/*     */           
/* 198 */           for (int x = -1; x < 2; x++) {
/*     */             
/* 200 */             for (int z = -1; z < 2; z++) {
/*     */               
/* 202 */               if (world.func_147439_a(i + x, j, k + z) == this && player.field_71071_by.func_70301_a(player.field_71071_by.field_70461_c) != null) {
/*     */                 
/* 204 */                 player.func_71064_a(StatList.field_75934_C[func_149682_b((Block)this)], 1);
/* 205 */                 TECrop teX = (TECrop)world.func_147438_o(i + x, j, k + z);
/* 206 */                 teX.onHarvest(world, player, true);
/*     */                 
/* 208 */                 world.func_147468_f(i + x, j, k + z);
/*     */                 
/* 210 */                 itemstack.func_77972_a(1, (EntityLivingBase)player);
/* 211 */                 if (itemstack.field_77994_a == 0) {
/* 212 */                   player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 223 */     te.onHarvest(world, player, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
/* 233 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_149633_g(World world, int x, int y, int z) {
/* 239 */     return AxisAlignedBB.func_72330_a(x, y, z, (x + 1), y + 0.3D, (z + 1));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Item func_149650_a(int metadata, Random rand, int fortune) {
/* 245 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149662_c() {
/* 251 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149695_a(World world, int x, int y, int z, Block b) {
/* 257 */     super.func_149695_a(world, x, y, z, b);
/*     */     
/* 259 */     if (!func_149718_j(world, x, y, z)) {
/* 260 */       world.func_147468_f(x, y, z);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_149686_d() {
/* 266 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149718_j(World world, int x, int y, int z) {
/* 272 */     return (TFC_Core.isFarmland(world.func_147439_a(x, y - 1, z)) || TFC_Core.isSoil(world.func_147439_a(x, y - 1, z)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity func_149915_a(World world, int meta) {
/* 278 */     return (TileEntity)new TECrop();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer) {
/* 285 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer) {
/* 292 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
/* 298 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockCrop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */