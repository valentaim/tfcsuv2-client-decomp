/*     */ package com.bioxx.tfc.Blocks.Devices;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.BlockTerraContainer;
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Core.TFC_Textures;
/*     */ import com.bioxx.tfc.Entities.EntityBarrel;
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemBarrels;
/*     */ import com.bioxx.tfc.Items.Tools.ItemCustomBucketMilk;
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import com.bioxx.tfc.TileEntities.TEBarrel;
/*     */ import com.bioxx.tfc.api.Constant.Global;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.particle.EffectRenderer;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.Explosion;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fluids.FluidContainerRegistry;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fluids.IFluidContainerItem;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockBarrel
/*     */   extends BlockTerraContainer
/*     */ {
/*     */   private String[] woodNames;
/*     */   
/*     */   public BlockBarrel() {
/*  57 */     super(Material.field_151575_d);
/*  58 */     func_149647_a(TFCTabs.TFC_DEVICES);
/*  59 */     func_149676_a(0.1F, 0.0F, 0.1F, 0.9F, 1.0F, 0.9F);
/*  60 */     this.woodNames = Global.WOOD_ALL;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149651_a(IIconRegister iconRegisterer) {
/*  66 */     this.field_149761_L = iconRegisterer.func_94245_a("terrafirmacraft:wood/BarrelHoop");
/*  67 */     TFC_Textures.guiSolidStorage = iconRegisterer.func_94245_a("terrafirmacraft:button_barrel_solid");
/*  68 */     TFC_Textures.guiLiquidStorage = iconRegisterer.func_94245_a("terrafirmacraft:button_barrel_liquid");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IIcon func_149691_a(int side, int meta) {
/*  75 */     if (side >= 10) {
/*     */       
/*  77 */       side -= 10;
/*  78 */       if (side == 0 || side == 1) {
/*  79 */         return TFC_Textures.invisibleTexture;
/*     */       }
/*  81 */       return this.field_149761_L;
/*     */     } 
/*  83 */     if (meta < 16)
/*  84 */       return TFCBlocks.planks.func_149691_a(side, meta); 
/*  85 */     return TFCBlocks.planks2.func_149691_a(side, meta - 16);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IIcon func_149673_e(IBlockAccess access, int x, int y, int z, int side) {
/*  93 */     if (side == 0 || side == 1) {
/*  94 */       return TFC_Textures.invisibleTexture;
/*     */     }
/*  96 */     return this.field_149761_L;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> par3List) {
/* 104 */     for (int i = 0; i < this.woodNames.length; i++) {
/* 105 */       par3List.add(new ItemStack((Block)this, 1, i));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149692_a(int dmg) {
/* 111 */     return dmg;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149662_c() {
/* 117 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149686_d() {
/* 123 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149645_b() {
/* 129 */     return TFCBlocks.barrelRenderId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149725_f(World world, int x, int y, int z, int meta) {
/* 135 */     if (!world.field_72995_K) {
/*     */       
/* 137 */       TEBarrel te = (TEBarrel)world.func_147438_o(x, y, z);
/* 138 */       if (te != null && te.shouldDropItem && world.func_82736_K().func_82766_b("doTileDrops"))
/*     */       {
/* 140 */         if (te.getSealed()) {
/*     */           
/* 142 */           ItemStack is = new ItemStack(Item.func_150898_a((Block)this), 1, te.barrelType);
/* 143 */           NBTTagCompound nbt = writeBarrelToNBT(te);
/* 144 */           is.func_77982_d(nbt);
/* 145 */           EntityItem ei = new EntityItem(world, x, y, z, is);
/* 146 */           world.func_72838_d((Entity)ei);
/*     */           
/* 148 */           te.fluid = null;
/*     */           
/* 150 */           for (int s = 0; s < te.func_70302_i_(); s++) {
/* 151 */             te.func_70299_a(s, null);
/*     */           }
/*     */         } else {
/*     */           
/* 155 */           ItemStack is = new ItemStack(Item.func_150898_a((Block)this), 1, te.barrelType);
/* 156 */           EntityItem ei = new EntityItem(world, x, y, z, is);
/* 157 */           world.func_72838_d((Entity)ei);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBlockExploded(World world, int x, int y, int z, Explosion explosion) {
/* 167 */     func_149723_a(world, x, y, z, explosion);
/* 168 */     world.func_147468_f(x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149723_a(World world, int x, int y, int z, Explosion exp) {
/* 174 */     if (world.func_147438_o(x, y, z) instanceof TEBarrel) {
/*     */       
/* 176 */       TEBarrel te = (TEBarrel)world.func_147438_o(x, y, z);
/*     */       
/* 178 */       if (this == TFCBlocks.barrel && te != null && te.getGunPowderCount() >= 12 && te.getSealed()) {
/*     */         
/* 180 */         spawnPowderKeg(world, x, y, z, te, true);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 185 */     super.func_149723_a(world, x, y, z, exp);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149689_a(World world, int i, int j, int k, EntityLivingBase player, ItemStack is) {
/* 194 */     super.func_149689_a(world, i, j, k, player, is);
/* 195 */     TEBarrel teb = null;
/* 196 */     TileEntity te = world.func_147438_o(i, j, k);
/* 197 */     if (te != null && is.func_77942_o() && te instanceof TEBarrel) {
/*     */       
/* 199 */       teb = (TEBarrel)te;
/* 200 */       teb.readFromItemNBT(is.func_77978_p());
/* 201 */       world.func_147471_g(i, j, k);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149742_c(World par1World, int par2, int par3, int par4) {
/* 211 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149659_a(Explosion exp) {
/* 218 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected ItemStack func_149644_j(int par1) {
/* 224 */     int j = 0;
/* 225 */     String s = func_149739_a();
/* 226 */     for (int i = 0; i < this.woodNames.length; i++)
/* 227 */       j = (s.substring(s.indexOf('l', s.length())) == ((ItemBarrels)TFCItems.barrel).metaNames[i]) ? i : 0; 
/* 228 */     return new ItemStack(TFCItems.barrel, 1, j);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149695_a(World world, int x, int y, int z, Block block) {
/* 234 */     if (world.func_147438_o(x, y, z) instanceof TEBarrel) {
/*     */       
/* 236 */       TEBarrel te = (TEBarrel)world.func_147438_o(x, y, z);
/*     */       
/* 238 */       if (this == TFCBlocks.barrel && te != null && te.getGunPowderCount() >= 12 && te.getSealed()) {
/*     */         
/* 240 */         boolean fireNearby = false;
/* 241 */         if (world.func_147439_a(x - 1, y, z) instanceof net.minecraft.block.BlockFire)
/* 242 */           fireNearby = true; 
/* 243 */         if (world.func_147439_a(x + 1, y, z) instanceof net.minecraft.block.BlockFire)
/* 244 */           fireNearby = true; 
/* 245 */         if (world.func_147439_a(x, y, z - 1) instanceof net.minecraft.block.BlockFire)
/* 246 */           fireNearby = true; 
/* 247 */         if (world.func_147439_a(x, y, z + 1) instanceof net.minecraft.block.BlockFire) {
/* 248 */           fireNearby = true;
/*     */         }
/* 250 */         if (fireNearby || world.func_72864_z(x, y, z)) {
/*     */           
/* 252 */           spawnPowderKeg(world, x, y, z, te, false);
/* 253 */           world.func_147468_f(x, y, z);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_149642_a(World par1World, int par2, int par3, int par4, ItemStack par5ItemStack) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound writeBarrelToNBT(TEBarrel te) {
/* 266 */     NBTTagCompound nbt = new NBTTagCompound();
/*     */     
/* 268 */     NBTTagCompound fluidNBT = new NBTTagCompound();
/* 269 */     if (te.fluid != null)
/* 270 */       te.fluid.writeToNBT(fluidNBT); 
/* 271 */     nbt.func_74782_a("fluidNBT", (NBTBase)fluidNBT);
/* 272 */     nbt.func_74768_a("barrelType", te.barrelType);
/* 273 */     nbt.func_74768_a("SealTime", te.sealtime);
/* 274 */     nbt.func_74757_a("Sealed", te.getSealed());
/*     */     
/* 276 */     NBTTagList nbttaglist = new NBTTagList();
/* 277 */     for (int i = 0; i < te.storage.length; i++) {
/*     */       
/* 279 */       if (te.storage[i] != null) {
/*     */         
/* 281 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 282 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 283 */         te.storage[i].func_77955_b(nbttagcompound1);
/* 284 */         nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
/*     */       } 
/*     */     } 
/* 287 */     if (nbttaglist.func_74745_c() > 0) {
/* 288 */       nbt.func_74782_a("Items", (NBTBase)nbttaglist);
/*     */     }
/* 290 */     return nbt;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
/* 296 */     if (world.field_72995_K) {
/*     */       
/* 298 */       world.func_147471_g(x, y, z);
/* 299 */       return true;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 304 */     if (player.func_70093_af())
/*     */     {
/* 306 */       return false;
/*     */     }
/*     */     
/* 309 */     if (world.func_147438_o(x, y, z) instanceof TEBarrel) {
/*     */       
/* 311 */       TEBarrel te = (TEBarrel)world.func_147438_o(x, y, z);
/*     */       
/* 313 */       if (this == TFCBlocks.barrel && te.getSealed() && te.getGunPowderCount() >= 12 && player
/* 314 */         .func_71045_bC() != null && player.func_71045_bC().func_77973_b() instanceof net.minecraft.item.ItemFlintAndSteel) {
/*     */         
/* 316 */         spawnPowderKeg(world, x, y, z, te, false);
/* 317 */         world.func_147468_f(x, y, z);
/* 318 */         return true;
/*     */       } 
/*     */       
/* 321 */       if (!handleInteraction(player, te)) {
/*     */         
/* 323 */         if (te.getFluidLevel() > 0 || te.getInvCount() == 0) {
/* 324 */           player.openGui(TerraFirmaCraft.instance, 35, world, x, y, z);
/*     */         } else {
/* 326 */           player.openGui(TerraFirmaCraft.instance, 36, world, x, y, z);
/* 327 */         }  return true;
/*     */       } 
/* 329 */       return true;
/*     */     } 
/*     */     
/* 332 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149749_a(World world, int x, int y, int z, Block p_149749_5_, int p_149749_6_) {
/* 339 */     TileEntity tileEntity = world.func_147438_o(x, y, z);
/* 340 */     if (tileEntity != null && tileEntity instanceof TEBarrel) {
/* 341 */       TEBarrel barrel = (TEBarrel)tileEntity;
/* 342 */       barrel.ejectContents();
/* 343 */       barrel.clearInventory();
/* 344 */       barrel.updateGui();
/*     */     } 
/* 346 */     super.func_149749_a(world, x, y, z, p_149749_5_, p_149749_6_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean handleInteraction(EntityPlayer player, TEBarrel te) {
/* 353 */     if (!te.getSealed() && te.getInvCount() <= 1 && !(te.func_145831_w()).field_72995_K) {
/*     */       
/* 355 */       ItemStack equippedItem = player.func_71045_bC();
/* 356 */       if (equippedItem == null) {
/* 357 */         return false;
/*     */       }
/* 359 */       if ((FluidContainerRegistry.isFilledContainer(equippedItem) || (equippedItem
/* 360 */         .func_77973_b() instanceof IFluidContainerItem && ((IFluidContainerItem)equippedItem.func_77973_b()).getFluid(equippedItem) != null)) && 
/* 361 */         !te.getSealed()) {
/*     */         
/* 363 */         ItemStack tmp = equippedItem.func_77946_l();
/* 364 */         tmp.field_77994_a = 1;
/* 365 */         ItemStack is = te.addLiquid(tmp);
/*     */ 
/*     */         
/* 368 */         if (ItemStack.func_77989_b(equippedItem, is))
/*     */         {
/* 370 */           return false;
/*     */         }
/*     */         
/* 373 */         equippedItem.field_77994_a--;
/*     */         
/* 375 */         if (equippedItem.field_77994_a == 0) {
/* 376 */           player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
/*     */         }
/* 378 */         if (equippedItem.field_77994_a == 0 && (is.func_77976_d() == 1 || !player.field_71071_by.func_70431_c(is))) {
/* 379 */           player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, is);
/*     */         
/*     */         }
/* 382 */         else if (!player.field_71071_by.func_70441_a(is)) {
/* 383 */           player.func_71019_a(is, false);
/*     */         } 
/*     */         
/* 386 */         if (player.field_71069_bz != null)
/*     */         {
/*     */           
/* 389 */           player.field_71069_bz.func_75142_b();
/*     */         }
/*     */         
/* 392 */         return true;
/*     */       } 
/* 394 */       if (FluidContainerRegistry.isEmptyContainer(equippedItem) || equippedItem.func_77973_b() instanceof IFluidContainerItem) {
/*     */         
/* 396 */         ItemStack tmp = equippedItem.func_77946_l();
/* 397 */         tmp.field_77994_a = 1;
/* 398 */         ItemStack is = te.removeLiquid(tmp);
/*     */ 
/*     */         
/* 401 */         if (ItemStack.func_77989_b(equippedItem, is))
/*     */         {
/* 403 */           return false;
/*     */         }
/*     */         
/* 406 */         if (is.func_77973_b() == TFCItems.woodenBucketMilk)
/*     */         {
/* 408 */           ItemCustomBucketMilk.createTag(is, 20.0F);
/*     */         }
/*     */         
/* 411 */         equippedItem.field_77994_a--;
/*     */         
/* 413 */         if (equippedItem.field_77994_a == 0) {
/* 414 */           player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
/*     */         }
/* 416 */         if (equippedItem.field_77994_a == 0 && (is.func_77976_d() == 1 || !player.field_71071_by.func_70431_c(is))) {
/* 417 */           player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, is);
/*     */         
/*     */         }
/* 420 */         else if (!player.field_71071_by.func_70441_a(is)) {
/* 421 */           player.func_71019_a(is, false);
/*     */         } 
/*     */         
/* 424 */         if (player.field_71069_bz != null)
/*     */         {
/*     */           
/* 427 */           player.field_71069_bz.func_75142_b();
/*     */         }
/*     */         
/* 430 */         return true;
/*     */       } 
/* 432 */       if (equippedItem.func_77973_b() instanceof ItemBarrels || equippedItem.func_77973_b() instanceof com.bioxx.tfc.Items.ItemBlocks.ItemLargeVessel) {
/*     */         
/* 434 */         ItemStack is = equippedItem.func_77946_l();
/* 435 */         is.field_77994_a = 1;
/* 436 */         if (equippedItem.func_77942_o()) {
/*     */           
/* 438 */           if (equippedItem.func_77978_p().func_74764_b("fluidNBT") && !equippedItem.func_77978_p().func_74764_b("Items") && te.getFluidLevel() < te.getMaxLiquid())
/*     */           {
/* 440 */             FluidStack fs = FluidStack.loadFluidStackFromNBT(equippedItem.func_77978_p().func_74775_l("fluidNBT"));
/* 441 */             if (te.addLiquid(fs))
/*     */             {
/* 443 */               if (fs.amount == 0) {
/*     */                 
/* 445 */                 equippedItem.func_77978_p().func_82580_o("Sealed");
/* 446 */                 equippedItem.func_77978_p().func_82580_o("fluidNBT");
/* 447 */                 if (equippedItem.func_77978_p().func_82582_d()) {
/* 448 */                   equippedItem.func_77982_d(null);
/*     */                 }
/*     */               } else {
/*     */                 
/* 452 */                 fs.writeToNBT(equippedItem.func_77978_p().func_74775_l("fluidNBT"));
/*     */               } 
/* 454 */               return true;
/*     */             }
/*     */           
/*     */           }
/*     */         
/*     */         }
/* 460 */         else if (te.getFluidStack() != null) {
/*     */           
/* 462 */           NBTTagCompound nbt = new NBTTagCompound();
/* 463 */           if (is.func_77973_b() instanceof ItemBarrels) {
/*     */             
/* 465 */             nbt.func_74782_a("fluidNBT", (NBTBase)te.getFluidStack().writeToNBT(new NBTTagCompound()));
/* 466 */             nbt.func_74757_a("Sealed", true);
/* 467 */             is.func_77982_d(nbt);
/* 468 */             te.actionEmpty();
/* 469 */             equippedItem.field_77994_a--;
/* 470 */             TFC_Core.giveItemToPlayer(is, player);
/*     */           }
/* 472 */           else if (is.func_77973_b() instanceof com.bioxx.tfc.Items.ItemBlocks.ItemLargeVessel) {
/*     */             
/* 474 */             if (is.func_77960_j() == 0) {
/* 475 */               return false;
/*     */             }
/* 477 */             FluidStack fs = te.getFluidStack().copy();
/* 478 */             if (fs.amount > 5000) {
/*     */               
/* 480 */               fs.amount = 5000;
/* 481 */               te.drainLiquid(5000);
/*     */             }
/*     */             else {
/*     */               
/* 485 */               te.actionEmpty();
/*     */             } 
/* 487 */             nbt.func_74782_a("fluidNBT", (NBTBase)fs.writeToNBT(new NBTTagCompound()));
/* 488 */             nbt.func_74757_a("Sealed", true);
/* 489 */             is.func_77982_d(nbt);
/* 490 */             equippedItem.field_77994_a--;
/* 491 */             TFC_Core.giveItemToPlayer(is, player);
/*     */           } 
/* 493 */           return true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 498 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_149646_a(IBlockAccess par1iBlockAccess, int par2, int par3, int par4, int par5) {
/* 505 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity func_149915_a(World var1, int var2) {
/* 511 */     return (TileEntity)new TEBarrel();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer) {
/* 518 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer) {
/* 525 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149643_k(World world, int x, int y, int z) {
/* 534 */     TileEntity te = world.func_147438_o(x, y, z);
/* 535 */     if (te instanceof TEBarrel)
/* 536 */       return ((TEBarrel)te).barrelType; 
/* 537 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
/* 546 */     ArrayList<ItemStack> ret = new ArrayList<>();
/*     */     
/* 548 */     int damageValue = func_149643_k(world, x, y, z);
/* 549 */     ret.add(new ItemStack((Block)this, 1, damageValue));
/*     */     
/* 551 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   public void spawnPowderKeg(World world, int x, int y, int z, TEBarrel te, boolean shortFuse) {
/* 556 */     if (TFCOptions.enablePowderKegs) {
/*     */       
/* 558 */       ItemStack is = new ItemStack((Block)this, 1, te.barrelType);
/* 559 */       NBTTagCompound nbt = writeBarrelToNBT(te);
/* 560 */       is.func_77982_d(nbt);
/* 561 */       EntityBarrel entity = new EntityBarrel(world, x, y, z, is, te.getGunPowderCount());
/* 562 */       te.clearInventory();
/* 563 */       te.shouldDropItem = false;
/* 564 */       if (shortFuse) {
/*     */         
/* 566 */         entity.setFuse(1);
/* 567 */         world.func_72838_d((Entity)entity);
/*     */       }
/*     */       else {
/*     */         
/* 571 */         world.func_72838_d((Entity)entity);
/* 572 */         world.func_72956_a((Entity)entity, "game.tnt.primed", 1.0F, 1.0F);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Devices\BlockBarrel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */