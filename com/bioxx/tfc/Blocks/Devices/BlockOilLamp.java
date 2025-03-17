/*     */ package com.bioxx.tfc.Blocks.Devices;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.BlockTerraContainer;
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Core.TFC_Textures;
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemOilLamp;
/*     */ import com.bioxx.tfc.TileEntities.TEOilLamp;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ 
/*     */ 
/*     */ public class BlockOilLamp
/*     */   extends BlockTerraContainer
/*     */ {
/*     */   private IIcon[] icons;
/*     */   
/*     */   public BlockOilLamp() {
/*  43 */     super(Material.field_151594_q);
/*  44 */     func_149675_a(true);
/*  45 */     func_149647_a(TFCTabs.TFC_DECORATION);
/*  46 */     func_149715_a(1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLightValue(IBlockAccess world, int x, int y, int z) {
/*  52 */     int meta = world.func_72805_g(x, y, z);
/*  53 */     if (meta >= 8)
/*  54 */       return 0; 
/*  55 */     return func_149750_m();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> list) {
/*  63 */     for (int i = 0; i < 6; i++)
/*     */     {
/*  65 */       list.add(ItemOilLamp.getFullLamp(i));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int side, int meta) {
/*  73 */     int m = meta & 0x7;
/*  74 */     if (side == 0 || side == 1) {
/*     */       
/*  76 */       if (m == 0)
/*  77 */         return TFC_Textures.sheetGold; 
/*  78 */       if (m == 1)
/*  79 */         return TFC_Textures.sheetPlatinum; 
/*  80 */       if (m == 2)
/*  81 */         return TFC_Textures.sheetRoseGold; 
/*  82 */       if (m == 3)
/*  83 */         return TFC_Textures.sheetSilver; 
/*  84 */       if (m == 4)
/*  85 */         return TFC_Textures.sheetSterlingSilver; 
/*  86 */       if (m == 5)
/*  87 */         return TFC_Textures.sheetBlueSteel; 
/*  88 */       return TFC_Textures.sheetGold;
/*     */     } 
/*  90 */     return this.icons[m];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149673_e(IBlockAccess access, int i, int j, int k, int side) {
/*  97 */     return func_149691_a(side, access.func_72805_g(i, j, k));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister registerer) {
/* 104 */     this.icons = new IIcon[6];
/* 105 */     this.icons[0] = registerer.func_94245_a("terrafirmacraft:metal/GoldLamp");
/* 106 */     this.icons[1] = registerer.func_94245_a("terrafirmacraft:metal/PlatinumLamp");
/* 107 */     this.icons[2] = registerer.func_94245_a("terrafirmacraft:metal/RoseGoldLamp");
/* 108 */     this.icons[3] = registerer.func_94245_a("terrafirmacraft:metal/SilverLamp");
/* 109 */     this.icons[4] = registerer.func_94245_a("terrafirmacraft:metal/SterlingSilverLamp");
/* 110 */     this.icons[5] = registerer.func_94245_a("terrafirmacraft:metal/BlueSteelLamp");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
/* 116 */     if (!world.field_72995_K) {
/*     */       
/* 118 */       int meta = world.func_72805_g(x, y, z);
/* 119 */       if (!isLampLit(meta)) {
/*     */         
/* 121 */         TEOilLamp te = (TEOilLamp)world.func_147438_o(x, y, z);
/* 122 */         if (te != null) {
/*     */           
/* 124 */           te.updateLampFuel(Boolean.valueOf(false));
/* 125 */           if (te.isFuelValid() && 
/* 126 */             te.getFuelTimeLeft() > 0) {
/* 127 */             world.func_72921_c(x, y, z, meta - 8, 3);
/*     */           }
/*     */         } 
/*     */       } else {
/*     */         
/* 132 */         TEOilLamp te = (TEOilLamp)world.func_147438_o(x, y, z);
/* 133 */         if (te != null)
/*     */         {
/* 135 */           te.updateLampFuel(Boolean.valueOf(true));
/*     */         }
/* 137 */         world.func_72921_c(x, y, z, meta + 8, 3);
/*     */       } 
/*     */     } 
/* 140 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isLampLit(int meta) {
/* 145 */     return ((meta & 0x8) <= 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
/* 151 */     return new ArrayList<>();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity func_149915_a(World var1, int var2) {
/* 157 */     return (TileEntity)new TEOilLamp();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
/* 167 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149662_c() {
/* 177 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149686_d() {
/* 186 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149645_b() {
/* 195 */     return TFCBlocks.oilLampRenderId;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean canSupportTorch(World world, int x, int y, int z) {
/* 200 */     if (World.func_147466_a((IBlockAccess)world, x, y, z))
/*     */     {
/* 202 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 206 */     Block block = world.func_147439_a(x, y, z);
/* 207 */     return block.canPlaceTorchOnTop(world, x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149742_c(World world, int x, int y, int z) {
/* 217 */     return canSupportTorch(world, x, y - 1, z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149674_a(World world, int x, int y, int z, Random rand) {
/* 226 */     super.func_149674_a(world, x, y, z, rand);
/* 227 */     int meta = world.func_72805_g(x, y, z);
/* 228 */     if (meta < 8) {
/*     */       
/* 230 */       TEOilLamp te = (TEOilLamp)world.func_147438_o(x, y, z);
/* 231 */       if (te != null) {
/*     */         
/* 233 */         te.updateLampFuel(Boolean.valueOf(true));
/* 234 */         if (te.getFuelTimeLeft() == 0) {
/* 235 */           world.func_72921_c(x, y, z, meta + 8, 3);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149738_a(World world) {
/* 243 */     return 20;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149726_b(World world, int x, int y, int z) {
/* 252 */     tryPlace(world, x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack is) {
/* 258 */     TileEntity te = world.func_147438_o(x, y, z);
/* 259 */     if (te instanceof TEOilLamp) {
/*     */       
/* 261 */       ((TEOilLamp)te).create();
/* 262 */       FluidStack fs = FluidStack.loadFluidStackFromNBT(is.func_77978_p());
/* 263 */       if (fs != null)
/*     */       {
/* 265 */         ((TEOilLamp)te).setFuelFromStack(fs);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 271 */       ((TEOilLamp)te).hourPlaced = (int)TFC_Time.getTotalHours();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149695_a(World world, int x, int y, int z, Block b) {
/* 283 */     if (!World.func_147466_a((IBlockAccess)world, x, y - 1, z)) {
/* 284 */       TFC_Core.setBlockToAirWithDrops(world, x, y, z);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isReplaceable(IBlockAccess world, int x, int y, int z) {
/* 290 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean tryPlace(World world, int x, int y, int z) {
/* 295 */     if (!func_149742_c(world, x, y, z)) {
/*     */       
/* 297 */       if (world.func_147439_a(x, y, z) == this)
/*     */       {
/*     */         
/* 300 */         world.func_147468_f(x, y, z);
/*     */       }
/*     */       
/* 303 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 307 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149725_f(World world, int x, int y, int z, int meta) {
/* 314 */     if (!world.field_72995_K) {
/*     */       
/* 316 */       TEOilLamp te = (TEOilLamp)world.func_147438_o(x, y, z);
/* 317 */       if ((meta & 0x8) != 0)
/* 318 */         meta -= 8; 
/* 319 */       if (te != null)
/*     */       {
/* 321 */         if (te.getFuel() != null) {
/*     */           
/* 323 */           ItemStack is = new ItemStack((Block)this, 1, meta);
/* 324 */           NBTTagCompound nbt = te.getFuel().writeToNBT(new NBTTagCompound());
/* 325 */           is.func_77982_d(nbt);
/* 326 */           EntityItem ei = new EntityItem(world, x, y, z, is);
/* 327 */           world.func_72838_d((Entity)ei);
/*     */         }
/*     */         else {
/*     */           
/* 331 */           ItemStack is = new ItemStack((Block)this, 1, meta);
/* 332 */           EntityItem ei = new EntityItem(world, x, y, z, is);
/* 333 */           world.func_72838_d((Entity)ei);
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
/*     */   
/*     */   public MovingObjectPosition func_149731_a(World world, int x, int y, int z, Vec3 startVec, Vec3 endVec) {
/* 346 */     func_149676_a(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
/* 347 */     return super.func_149731_a(world, x, y, z, startVec, endVec);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World world, int x, int y, int z, Random rand) {
/* 357 */     int meta = world.func_72805_g(x, y, z);
/* 358 */     if (meta >= 8) {
/*     */       return;
/*     */     }
/*     */     
/* 362 */     double centerX = (x + 0.5F);
/* 363 */     double centerY = (y + 0.6F);
/* 364 */     double centerZ = (z + 0.5F);
/*     */ 
/*     */ 
/*     */     
/* 368 */     world.func_72869_a("smoke", centerX, centerY, centerZ, 0.0D, 0.0D, 0.0D);
/* 369 */     world.func_72869_a("flame", centerX, centerY, centerZ, 0.0D, 0.0D, 0.0D);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Devices\BlockOilLamp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */