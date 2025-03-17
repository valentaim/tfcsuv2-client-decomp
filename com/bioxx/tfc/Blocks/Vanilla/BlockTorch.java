/*     */ package com.bioxx.tfc.Blocks.Vanilla;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.BlockTerraContainer;
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import com.bioxx.tfc.TileEntities.TELightEmitter;
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
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockTorch
/*     */   extends BlockTerraContainer
/*     */ {
/*     */   protected IIcon offIcon;
/*     */   
/*     */   public BlockTorch() {
/*  43 */     super(Material.field_151594_q);
/*  44 */     func_149675_a(true);
/*  45 */     func_149647_a(TFCTabs.TFC_DECORATION);
/*  46 */     func_149715_a(0.9375F);
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
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int side, int meta) {
/*  62 */     if (meta >= 8)
/*  63 */       return this.offIcon; 
/*  64 */     return this.field_149761_L;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister register) {
/*  71 */     super.func_149651_a(register);
/*  72 */     this.offIcon = register.func_94245_a("terrafirmacraft:torch_off");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
/*  78 */     if (!world.field_72995_K) {
/*     */       
/*  80 */       int meta = world.func_72805_g(x, y, z);
/*  81 */       ItemStack is = player.field_71071_by.func_70448_g();
/*  82 */       Item item = (is != null) ? is.func_77973_b() : null;
/*     */ 
/*     */       
/*  85 */       if (meta < 8 && item == TFCItems.stick)
/*     */       {
/*  87 */         player.field_71071_by.func_146026_a(TFCItems.stick);
/*  88 */         TFC_Core.giveItemToPlayer(new ItemStack(TFCBlocks.torch), player);
/*     */       
/*     */       }
/*  91 */       else if (item == Item.func_150898_a(TFCBlocks.torch))
/*     */       {
/*  93 */         TELightEmitter te = (TELightEmitter)world.func_147438_o(x, y, z);
/*  94 */         te.hourPlaced = (int)TFC_Time.getTotalHours();
/*  95 */         if (meta >= 8)
/*     */         {
/*  97 */           world.func_72921_c(x, y, z, meta - 8, 3);
/*     */         
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 103 */         world.func_147465_d(x, y, z, TFCBlocks.torchOff, meta, 3);
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 108 */     else if (TFCOptions.enableDebugMode) {
/*     */       
/* 110 */       int metadata = world.func_72805_g(x, y, z);
/* 111 */       TerraFirmaCraft.LOG.info("Meta = " + func_149739_a() + ":" + metadata);
/*     */     } 
/*     */     
/* 114 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
/* 120 */     ArrayList<ItemStack> ret = new ArrayList<>();
/*     */ 
/*     */     
/* 123 */     if (metadata >= 8) {
/* 124 */       return ret;
/*     */     }
/* 126 */     Item item = func_149650_a(metadata, world.field_73012_v, fortune);
/* 127 */     if (item != null)
/*     */     {
/* 129 */       ret.add(new ItemStack(item, 1, func_149692_a(metadata)));
/*     */     }
/* 131 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity func_149915_a(World var1, int var2) {
/* 137 */     return (TileEntity)new TELightEmitter();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
/* 147 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149662_c() {
/* 157 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149686_d() {
/* 166 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149645_b() {
/* 175 */     return TFCBlocks.torchRenderId;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean canSupportTorch(World world, int x, int y, int z) {
/* 180 */     if (World.func_147466_a((IBlockAccess)world, x, y, z))
/*     */     {
/* 182 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 186 */     Block block = world.func_147439_a(x, y, z);
/* 187 */     return block.canPlaceTorchOnTop(world, x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149742_c(World world, int x, int y, int z) {
/* 197 */     return (world.isSideSolid(x - 1, y, z, ForgeDirection.EAST, true) || world
/* 198 */       .isSideSolid(x + 1, y, z, ForgeDirection.WEST, true) || world
/* 199 */       .isSideSolid(x, y, z - 1, ForgeDirection.SOUTH, true) || world
/* 200 */       .isSideSolid(x, y, z + 1, ForgeDirection.NORTH, true) || 
/* 201 */       canSupportTorch(world, x, y - 1, z));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149660_a(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
/* 210 */     int j1 = meta;
/*     */     
/* 212 */     if (side == 1 && canSupportTorch(world, x, y - 1, z))
/*     */     {
/* 214 */       j1 = 5;
/*     */     }
/*     */     
/* 217 */     if (side == 2 && world.isSideSolid(x, y, z + 1, ForgeDirection.NORTH, true))
/*     */     {
/* 219 */       j1 = 4;
/*     */     }
/*     */     
/* 222 */     if (side == 3 && world.isSideSolid(x, y, z - 1, ForgeDirection.SOUTH, true))
/*     */     {
/* 224 */       j1 = 3;
/*     */     }
/*     */     
/* 227 */     if (side == 4 && world.isSideSolid(x + 1, y, z, ForgeDirection.WEST, true))
/*     */     {
/* 229 */       j1 = 2;
/*     */     }
/*     */     
/* 232 */     if (side == 5 && world.isSideSolid(x - 1, y, z, ForgeDirection.EAST, true))
/*     */     {
/* 234 */       j1 = 1;
/*     */     }
/*     */     
/* 237 */     return j1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149674_a(World world, int x, int y, int z, Random rand) {
/* 246 */     super.func_149674_a(world, x, y, z, rand);
/* 247 */     int meta = world.func_72805_g(x, y, z);
/*     */     
/* 249 */     if (meta == 0)
/*     */     {
/* 251 */       func_149726_b(world, x, y, z);
/*     */     }
/* 253 */     if (!world.field_72995_K)
/*     */     {
/* 255 */       if (TFCOptions.torchBurnTime != 0 && world.func_147438_o(x, y, z) instanceof TELightEmitter) {
/*     */ 
/*     */         
/* 258 */         TELightEmitter te = (TELightEmitter)world.func_147438_o(x, y, z);
/* 259 */         if (TFC_Time.getTotalHours() > (te.hourPlaced + TFCOptions.torchBurnTime) || 
/* 260 */           TFC_Core.isExposedToRain(world, x, y, z))
/*     */         {
/* 262 */           world.func_147465_d(x, y, z, TFCBlocks.torchOff, meta, 3);
/*     */         }
/*     */       }
/* 265 */       else if (meta >= 8) {
/*     */         
/* 267 */         world.func_147465_d(x, y, z, TFCBlocks.torchOff, meta - 8, 3);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149726_b(World world, int x, int y, int z) {
/* 278 */     if (world.func_72805_g(x, y, z) == 0)
/*     */     {
/* 280 */       if (world.isSideSolid(x - 1, y, z, ForgeDirection.EAST, true)) {
/*     */         
/* 282 */         world.func_72921_c(x, y, z, 1, 3);
/*     */       }
/* 284 */       else if (world.isSideSolid(x + 1, y, z, ForgeDirection.WEST, true)) {
/*     */         
/* 286 */         world.func_72921_c(x, y, z, 2, 3);
/*     */       }
/* 288 */       else if (world.isSideSolid(x, y, z - 1, ForgeDirection.SOUTH, true)) {
/*     */         
/* 290 */         world.func_72921_c(x, y, z, 3, 3);
/*     */       }
/* 292 */       else if (world.isSideSolid(x, y, z + 1, ForgeDirection.NORTH, true)) {
/*     */         
/* 294 */         world.func_72921_c(x, y, z, 4, 3);
/*     */       }
/* 296 */       else if (canSupportTorch(world, x, y - 1, z)) {
/*     */         
/* 298 */         world.func_72921_c(x, y, z, 5, 3);
/*     */       } 
/*     */     }
/* 301 */     ((TELightEmitter)world.func_147438_o(x, y, z)).create();
/*     */     
/* 303 */     tryPlace(world, x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149695_a(World world, int x, int y, int z, Block b) {
/* 313 */     checkValidity(world, x, y, z, b);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReplaceable(IBlockAccess world, int x, int y, int z) {
/* 319 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean checkValidity(World world, int x, int y, int z, Block b) {
/* 324 */     if (tryPlace(world, x, y, z)) {
/*     */       
/* 326 */       int l = world.func_72805_g(x, y, z);
/* 327 */       boolean flag = false;
/*     */       
/* 329 */       if (!world.isSideSolid(x - 1, y, z, ForgeDirection.EAST, true) && l == 1)
/*     */       {
/* 331 */         flag = true;
/*     */       }
/*     */       
/* 334 */       if (!world.isSideSolid(x + 1, y, z, ForgeDirection.WEST, true) && l == 2)
/*     */       {
/* 336 */         flag = true;
/*     */       }
/*     */       
/* 339 */       if (!world.isSideSolid(x, y, z - 1, ForgeDirection.SOUTH, true) && l == 3)
/*     */       {
/* 341 */         flag = true;
/*     */       }
/*     */       
/* 344 */       if (!world.isSideSolid(x, y, z + 1, ForgeDirection.NORTH, true) && l == 4)
/*     */       {
/* 346 */         flag = true;
/*     */       }
/*     */       
/* 349 */       if (!canSupportTorch(world, x, y - 1, z) && l == 5)
/*     */       {
/* 351 */         flag = true;
/*     */       }
/*     */       
/* 354 */       if (flag) {
/*     */         
/* 356 */         func_149697_b(world, x, y, z, world.func_72805_g(x, y, z), 0);
/* 357 */         world.func_147468_f(x, y, z);
/* 358 */         return true;
/*     */       } 
/*     */ 
/*     */       
/* 362 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 367 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean tryPlace(World world, int x, int y, int z) {
/* 373 */     if (!func_149742_c(world, x, y, z)) {
/*     */       
/* 375 */       if (world.func_147439_a(x, y, z) == this) {
/*     */         
/* 377 */         func_149697_b(world, x, y, z, world.func_72805_g(x, y, z), 0);
/* 378 */         world.func_147468_f(x, y, z);
/*     */       } 
/*     */       
/* 381 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 385 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MovingObjectPosition func_149731_a(World world, int x, int y, int z, Vec3 startVec, Vec3 endVec) {
/* 396 */     int l = world.func_72805_g(x, y, z) & 0x7;
/* 397 */     float f = 0.15F;
/*     */     
/* 399 */     if (l == 1) {
/*     */       
/* 401 */       func_149676_a(0.0F, 0.2F, 0.5F - f, f * 2.0F, 0.8F, 0.5F + f);
/*     */     }
/* 403 */     else if (l == 2) {
/*     */       
/* 405 */       func_149676_a(1.0F - f * 2.0F, 0.2F, 0.5F - f, 1.0F, 0.8F, 0.5F + f);
/*     */     }
/* 407 */     else if (l == 3) {
/*     */       
/* 409 */       func_149676_a(0.5F - f, 0.2F, 0.0F, 0.5F + f, 0.8F, f * 2.0F);
/*     */     }
/* 411 */     else if (l == 4) {
/*     */       
/* 413 */       func_149676_a(0.5F - f, 0.2F, 1.0F - f * 2.0F, 0.5F + f, 0.8F, 1.0F);
/*     */     }
/*     */     else {
/*     */       
/* 417 */       f = 0.1F;
/* 418 */       func_149676_a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.6F, 0.5F + f);
/*     */     } 
/*     */     
/* 421 */     return super.func_149731_a(world, x, y, z, startVec, endVec);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World world, int x, int y, int z, Random rand) {
/* 431 */     int meta = world.func_72805_g(x, y, z);
/* 432 */     if (meta >= 8) {
/*     */       return;
/*     */     }
/*     */     
/* 436 */     double centerX = (x + 0.5F);
/* 437 */     double centerY = (y + 0.7F);
/* 438 */     double centerZ = (z + 0.5F);
/* 439 */     double d3 = 0.22D;
/* 440 */     double d4 = 0.27D;
/*     */     
/* 442 */     if ((meta & 0x7) == 1) {
/*     */       
/* 444 */       world.func_72869_a("smoke", centerX - d4, centerY + d3, centerZ, 0.0D, 0.0D, 0.0D);
/* 445 */       world.func_72869_a("flame", centerX - d4, centerY + d3, centerZ, 0.0D, 0.0D, 0.0D);
/*     */     }
/* 447 */     else if ((meta & 0x7) == 2) {
/*     */       
/* 449 */       world.func_72869_a("smoke", centerX + d4, centerY + d3, centerZ, 0.0D, 0.0D, 0.0D);
/* 450 */       world.func_72869_a("flame", centerX + d4, centerY + d3, centerZ, 0.0D, 0.0D, 0.0D);
/*     */     }
/* 452 */     else if ((meta & 0x7) == 3) {
/*     */       
/* 454 */       world.func_72869_a("smoke", centerX, centerY + d3, centerZ - d4, 0.0D, 0.0D, 0.0D);
/* 455 */       world.func_72869_a("flame", centerX, centerY + d3, centerZ - d4, 0.0D, 0.0D, 0.0D);
/*     */     }
/* 457 */     else if ((meta & 0x7) == 4) {
/*     */       
/* 459 */       world.func_72869_a("smoke", centerX, centerY + d3, centerZ + d4, 0.0D, 0.0D, 0.0D);
/* 460 */       world.func_72869_a("flame", centerX, centerY + d3, centerZ + d4, 0.0D, 0.0D, 0.0D);
/*     */     }
/*     */     else {
/*     */       
/* 464 */       world.func_72869_a("smoke", centerX, centerY, centerZ, 0.0D, 0.0D, 0.0D);
/* 465 */       world.func_72869_a("flame", centerX, centerY, centerZ, 0.0D, 0.0D, 0.0D);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Vanilla\BlockTorch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */