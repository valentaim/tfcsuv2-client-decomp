/*      */ package com.bioxx.tfc.TileEntities;
/*      */ 
/*      */ import com.bioxx.tfc.Core.TFC_Core;
/*      */ import com.bioxx.tfc.Core.TFC_Time;
/*      */ import com.bioxx.tfc.Food.ItemFoodTFC;
/*      */ import com.bioxx.tfc.Handlers.Network.AbstractPacket;
/*      */ import com.bioxx.tfc.Items.Tools.ItemCustomBucketMilk;
/*      */ import com.bioxx.tfc.TerraFirmaCraft;
/*      */ import com.bioxx.tfc.api.Crafting.BarrelAlcoholRecipe;
/*      */ import com.bioxx.tfc.api.Crafting.BarrelBriningRecipe;
/*      */ import com.bioxx.tfc.api.Crafting.BarrelLiquidToLiquidRecipe;
/*      */ import com.bioxx.tfc.api.Crafting.BarrelManager;
/*      */ import com.bioxx.tfc.api.Crafting.BarrelMultiItemRecipe;
/*      */ import com.bioxx.tfc.api.Crafting.BarrelPreservativeRecipe;
/*      */ import com.bioxx.tfc.api.Crafting.BarrelRecipe;
/*      */ import com.bioxx.tfc.api.Crafting.BarrelVinegarRecipe;
/*      */ import com.bioxx.tfc.api.Enums.EnumFoodGroup;
/*      */ import com.bioxx.tfc.api.Food;
/*      */ import com.bioxx.tfc.api.Interfaces.IFood;
/*      */ import com.bioxx.tfc.api.TFCBlocks;
/*      */ import com.bioxx.tfc.api.TFCFluids;
/*      */ import com.bioxx.tfc.api.TFCItems;
/*      */ import com.bioxx.tfc.api.TFC_ItemHeat;
/*      */ import com.zerren.extrafirma.items.ModItems;
/*      */ import cpw.mods.fml.relauncher.Side;
/*      */ import cpw.mods.fml.relauncher.SideOnly;
/*      */ import fof.tfcsu.utils.ExpBonus;
/*      */ import growthcraft.cellar.GrowthCraftCellar;
/*      */ import java.util.Random;
/*      */ import java.util.Stack;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.item.EntityItem;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.inventory.IInventory;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.NBTBase;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.nbt.NBTTagList;
/*      */ import net.minecraft.util.AxisAlignedBB;
/*      */ import net.minecraftforge.fluids.FluidContainerRegistry;
/*      */ import net.minecraftforge.fluids.FluidStack;
/*      */ import net.minecraftforge.fluids.IFluidContainerItem;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class TEBarrel
/*      */   extends NetworkTileEntity
/*      */   implements IInventory
/*      */ {
/*      */   public FluidStack fluid;
/*      */   public byte rotation;
/*      */   public int barrelType;
/*      */   public int mode;
/*      */   public ItemStack[] storage;
/*      */   private boolean sealed;
/*      */   public int sealtime;
/*      */   public int unsealtime;
/*      */   private int processTimer;
/*      */   public static final int MODE_IN = 0;
/*      */   public static final int MODE_OUT = 1;
/*      */   public static final int INPUT_SLOT = 0;
/*      */   public BarrelRecipe recipe;
/*      */   public boolean shouldDropItem = true;
/*      */   private Boolean needCheck;
/*      */   private boolean hasTemp;
/*      */   private boolean hasExp;
/*      */   
/*      */   public TEBarrel() {
/*   73 */     this.storage = new ItemStack[12];
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getSealed() {
/*   78 */     return this.sealed;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getTechLevel() {
/*   83 */     return 1;
/*      */   }
/*      */ 
/*      */   
/*      */   public void clearInventory() {
/*   88 */     this.storage = new ItemStack[12];
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   public AxisAlignedBB getRenderBoundingBox() {
/*   95 */     return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 1), (this.field_145849_e + 1));
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSealed() {
/*  100 */     this.sealed = true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setUnsealed(String reason) {
/*  105 */     if ("killing fuse".equals(reason)) {
/*  106 */       this.sealed = false;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_70305_f() {}
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack func_70298_a(int i, int j) {
/*  117 */     if (this.storage[i] != null) {
/*      */       
/*  119 */       if ((this.storage[i]).field_77994_a <= j) {
/*      */         
/*  121 */         ItemStack is = this.storage[i];
/*  122 */         this.storage[i] = null;
/*  123 */         return is;
/*      */       } 
/*  125 */       ItemStack isSplit = this.storage[i].func_77979_a(j);
/*  126 */       if ((this.storage[i]).field_77994_a == 0)
/*  127 */         this.storage[i] = null; 
/*  128 */       return isSplit;
/*      */     } 
/*      */ 
/*      */     
/*  132 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void ejectContents() {
/*  138 */     float f3 = 0.05F;
/*      */     
/*  140 */     Random rand = new Random();
/*  141 */     float f = rand.nextFloat() * 0.3F + 0.1F;
/*  142 */     float f1 = rand.nextFloat() * 2.0F + 0.4F;
/*  143 */     float f2 = rand.nextFloat() * 0.3F + 0.1F;
/*      */     
/*  145 */     for (int i = 0; i < func_70302_i_(); i++) {
/*      */       
/*  147 */       if (this.storage[i] != null) {
/*      */         
/*  149 */         EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), this.storage[i]);
/*  150 */         entityitem.field_70159_w = ((float)rand.nextGaussian() * f3);
/*  151 */         entityitem.field_70181_x = ((float)rand.nextGaussian() * f3 + 0.2F);
/*  152 */         entityitem.field_70179_y = ((float)rand.nextGaussian() * f3);
/*  153 */         this.field_145850_b.func_72838_d((Entity)entityitem);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int func_70297_j_() {
/*  161 */     return 64;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String func_145825_b() {
/*  167 */     return "Barrel";
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int func_70302_i_() {
/*  173 */     return 12;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack func_70301_a(int i) {
/*  179 */     return this.storage[i];
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack func_70304_b(int i) {
/*  185 */     return this.storage[i];
/*      */   }
/*      */ 
/*      */   
/*      */   public int getInvCount() {
/*  190 */     int count = 0;
/*  191 */     for (ItemStack is : this.storage) {
/*      */       
/*  193 */       if (is != null)
/*  194 */         count++; 
/*      */     } 
/*  196 */     if (this.storage[0] != null && count == 1)
/*  197 */       return 0; 
/*  198 */     return count;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getGunPowderCount() {
/*  203 */     int count = 0;
/*  204 */     for (ItemStack is : this.storage) {
/*      */       
/*  206 */       if (is != null && is.func_77973_b() == Items.field_151016_H)
/*  207 */         count += is.field_77994_a; 
/*      */     } 
/*  209 */     return count;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canAcceptLiquids() {
/*  214 */     return (getInvCount() == 0);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_70300_a(EntityPlayer entityplayer) {
/*  220 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_70295_k_() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_70299_a(int i, ItemStack is) {
/*  236 */     boolean check = true;
/*  237 */     if (this.storage[i] != null && is != null && 
/*  238 */       this.storage[i].func_77973_b().equals(is.func_77973_b()) && TFC_ItemHeat.hasTemp(this.storage[i]) == TFC_ItemHeat.hasTemp(is)) check = false;
/*      */     
/*  240 */     if (!ItemStack.func_77989_b(this.storage[i], is)) {
/*      */ 
/*      */       
/*  243 */       this.storage[i] = is;
/*  244 */       if (i == 0) {
/*      */         
/*  246 */         processItems();
/*  247 */         if (!getSealed())
/*  248 */           this.unsealtime = (int)TFC_Time.getTotalHours(); 
/*      */       } 
/*      */     } 
/*  251 */     if (check) checkItems();
/*      */   
/*      */   }
/*      */   
/*      */   public int getFluidLevel() {
/*  256 */     if (this.fluid != null)
/*  257 */       return this.fluid.amount; 
/*  258 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack getInputStack() {
/*  263 */     return this.storage[0];
/*      */   }
/*      */ 
/*      */   
/*      */   public FluidStack getFluidStack() {
/*  268 */     return this.fluid;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMaxLiquid() {
/*  273 */     return 10000;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean addLiquid(FluidStack inFS) {
/*  278 */     if (inFS != null) {
/*      */ 
/*      */       
/*  281 */       if (inFS.getFluid() != null && inFS.getFluid().getTemperature(inFS) > 385) {
/*  282 */         return false;
/*      */       }
/*  284 */       if (this.fluid == null) {
/*      */         
/*  286 */         this.fluid = inFS.copy();
/*  287 */         if (this.fluid.amount > getMaxLiquid())
/*      */         {
/*  289 */           this.fluid.amount = getMaxLiquid();
/*  290 */           inFS.amount -= getMaxLiquid();
/*      */         }
/*      */         else
/*      */         {
/*  294 */           inFS.amount = 0;
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  299 */         if (this.fluid.amount == getMaxLiquid() || !this.fluid.isFluidEqual(inFS)) {
/*  300 */           return false;
/*      */         }
/*  302 */         int a = this.fluid.amount + inFS.amount - getMaxLiquid();
/*  303 */         this.fluid.amount = Math.min(this.fluid.amount + inFS.amount, getMaxLiquid());
/*  304 */         if (a > 0) {
/*  305 */           inFS.amount = a;
/*      */         } else {
/*  307 */           inFS.amount = 0;
/*      */         } 
/*  309 */       }  this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*  310 */       return true;
/*      */     } 
/*      */     
/*  313 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack addLiquid(ItemStack is) {
/*  318 */     if (is == null || is.field_77994_a > 1)
/*  319 */       return is; 
/*  320 */     if (FluidContainerRegistry.isFilledContainer(is)) {
/*      */       
/*  322 */       FluidStack fs = FluidContainerRegistry.getFluidForFilledItem(is);
/*      */       
/*  324 */       if (is.func_77973_b().func_77658_a().toLowerCase().contains("soup") && 
/*  325 */         is.func_77973_b() instanceof straywolfe.cookingwithtfc.common.item.ItemTFCMealTransform) {
/*      */         
/*  327 */         float weight = Food.getWeight(is);
/*      */ 
/*      */         
/*  330 */         fs.amount = (int)(weight * fs.amount) / 10;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  335 */       if (addLiquid(fs))
/*      */       {
/*  337 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*  338 */         return FluidContainerRegistry.drainFluidContainer(is);
/*      */       }
/*      */     
/*  341 */     } else if (is.func_77973_b() instanceof IFluidContainerItem) {
/*      */       
/*  343 */       FluidStack isfs = ((IFluidContainerItem)is.func_77973_b()).getFluid(is);
/*      */       
/*  345 */       if (isfs != null && addLiquid(isfs)) {
/*      */         
/*  347 */         ((IFluidContainerItem)is.func_77973_b()).drain(is, is.func_77958_k(), true);
/*  348 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*      */       } 
/*      */     } 
/*  351 */     return is;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack removeLiquid(ItemStack is) {
/*  359 */     if (is == null || is.field_77994_a > 1)
/*  360 */       return is; 
/*  361 */     if (FluidContainerRegistry.isEmptyContainer(is)) {
/*      */       
/*  363 */       ItemStack out = FluidContainerRegistry.fillFluidContainer(this.fluid, is);
/*  364 */       if (out != null)
/*      */       {
/*  366 */         FluidStack fs = FluidContainerRegistry.getFluidForFilledItem(out);
/*  367 */         this.fluid.amount -= fs.amount;
/*  368 */         is = null;
/*  369 */         if (this.fluid.amount == 0)
/*      */         {
/*  371 */           this.fluid = null;
/*      */         }
/*  373 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*  374 */         return out;
/*      */       }
/*      */     
/*  377 */     } else if (this.fluid != null && is.func_77973_b() instanceof IFluidContainerItem) {
/*      */       
/*  379 */       FluidStack isfs = ((IFluidContainerItem)is.func_77973_b()).getFluid(is);
/*  380 */       if (isfs == null || this.fluid.isFluidEqual(isfs)) {
/*      */         
/*  382 */         this.fluid.amount -= ((IFluidContainerItem)is.func_77973_b()).fill(is, this.fluid, true);
/*  383 */         if (this.fluid.amount == 0)
/*  384 */           this.fluid = null; 
/*  385 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*      */       } 
/*      */     } 
/*  388 */     return is;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void drainLiquid(int amount) {
/*  396 */     if (!getSealed() && getFluidStack() != null) {
/*      */       
/*  398 */       (getFluidStack()).amount -= amount;
/*  399 */       if ((getFluidStack()).amount <= 0) {
/*  400 */         actionEmpty();
/*      */       } else {
/*  402 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public int getLiquidScaled(int i) {
/*  408 */     if (this.fluid != null)
/*  409 */       return this.fluid.amount * i / getMaxLiquid(); 
/*  410 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean actionSeal(int tab, EntityPlayer player) {
/*  415 */     NBTTagCompound nbt = new NBTTagCompound();
/*  416 */     nbt.func_74757_a("seal", true);
/*  417 */     nbt.func_74774_a("tab", (byte)tab);
/*  418 */     nbt.func_74778_a("player", player.func_70005_c_());
/*  419 */     broadcastPacketInRange((AbstractPacket)createDataPacket(nbt));
/*  420 */     this.sealed = true;
/*  421 */     this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*  422 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean actionUnSeal(int tab, EntityPlayer player) {
/*  427 */     NBTTagCompound nbt = new NBTTagCompound();
/*  428 */     nbt.func_74757_a("seal", false);
/*  429 */     nbt.func_74774_a("tab", (byte)tab);
/*  430 */     nbt.func_74778_a("player", player.func_70005_c_());
/*  431 */     broadcastPacketInRange((AbstractPacket)createDataPacket(nbt));
/*  432 */     this.sealed = false;
/*  433 */     this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*  434 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void actionEmpty() {
/*  439 */     this.fluid = null;
/*  440 */     NBTTagCompound nbt = new NBTTagCompound();
/*  441 */     nbt.func_74774_a("fluidID", (byte)-1);
/*  442 */     broadcastPacketInRange((AbstractPacket)createDataPacket(nbt));
/*      */   }
/*      */ 
/*      */   
/*      */   public void actionMode() {
/*  447 */     this.mode = (this.mode == 0) ? 1 : 0;
/*  448 */     NBTTagCompound nbt = new NBTTagCompound();
/*  449 */     nbt.func_74774_a("mode", (byte)this.mode);
/*  450 */     broadcastPacketInRange((AbstractPacket)createDataPacket(nbt));
/*      */   }
/*      */ 
/*      */   
/*      */   public void actionSwitchTab(int tab, EntityPlayer player) {
/*  455 */     NBTTagCompound nbt = new NBTTagCompound();
/*  456 */     nbt.func_74774_a("tab", (byte)tab);
/*  457 */     nbt.func_74778_a("player", player.func_70005_c_());
/*  458 */     broadcastPacketInRange((AbstractPacket)createDataPacket(nbt));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_145818_k_() {
/*  464 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_94041_b(int i, ItemStack itemstack) {
/*  470 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_145841_b(NBTTagCompound nbt) {
/*  476 */     super.func_145841_b(nbt);
/*  477 */     nbt.func_74757_a("hasExp", this.hasExp);
/*  478 */     nbt.func_74757_a("Sealed", this.sealed);
/*  479 */     nbt.func_74768_a("SealTime", this.sealtime);
/*  480 */     nbt.func_74768_a("barrelType", this.barrelType);
/*      */     
/*  482 */     NBTTagCompound fluidNBT = new NBTTagCompound();
/*  483 */     if (this.fluid != null)
/*  484 */       this.fluid.writeToNBT(fluidNBT); 
/*  485 */     nbt.func_74782_a("fluidNBT", (NBTBase)fluidNBT);
/*  486 */     nbt.func_74774_a("rotation", this.rotation);
/*  487 */     NBTTagList nbttaglist = new NBTTagList();
/*  488 */     for (int i = 0; i < this.storage.length; i++) {
/*      */       
/*  490 */       if (this.storage[i] != null) {
/*      */         
/*  492 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*  493 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/*  494 */         this.storage[i].func_77955_b(nbttagcompound1);
/*  495 */         nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
/*      */       } 
/*      */     } 
/*  498 */     nbt.func_74782_a("Items", (NBTBase)nbttaglist);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_145839_a(NBTTagCompound nbt) {
/*  504 */     super.func_145839_a(nbt);
/*  505 */     this.fluid = FluidStack.loadFluidStackFromNBT(nbt.func_74775_l("fluidNBT"));
/*  506 */     this.sealed = nbt.func_74767_n("Sealed");
/*  507 */     this.sealtime = nbt.func_74762_e("SealTime");
/*  508 */     this.barrelType = nbt.func_74762_e("barrelType");
/*      */     
/*  510 */     this.rotation = nbt.func_74771_c("rotation");
/*  511 */     NBTTagList nbttaglist = nbt.func_150295_c("Items", 10);
/*  512 */     this.storage = new ItemStack[func_70302_i_()];
/*  513 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*      */       
/*  515 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/*  516 */       byte byte0 = nbttagcompound1.func_74771_c("Slot");
/*  517 */       if (byte0 >= 0 && byte0 < this.storage.length) {
/*  518 */         this.storage[byte0] = ItemStack.func_77949_a(nbttagcompound1);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void readFromItemNBT(NBTTagCompound nbt) {
/*  525 */     this.barrelType = nbt.func_74762_e("barrelType");
/*  526 */     this.fluid = FluidStack.loadFluidStackFromNBT(nbt.func_74775_l("fluidNBT"));
/*  527 */     this.hasExp = nbt.func_74767_n("hasExp");
/*  528 */     this.sealed = nbt.func_74767_n("Sealed");
/*  529 */     this.sealtime = nbt.func_74762_e("SealTime");
/*  530 */     NBTTagList nbttaglist = nbt.func_150295_c("Items", 10);
/*  531 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*      */       
/*  533 */       NBTTagCompound nbt1 = nbttaglist.func_150305_b(i);
/*  534 */       byte byte0 = nbt1.func_74771_c("Slot");
/*  535 */       if (byte0 >= 0 && byte0 < this.storage.length) {
/*  536 */         func_70299_a(byte0, ItemStack.func_77949_a(nbt1));
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public void updateGui() {
/*  542 */     this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleInitPacket(NBTTagCompound nbt) {
/*  550 */     this.rotation = nbt.func_74771_c("rotation");
/*  551 */     this.sealed = nbt.func_74767_n("sealed");
/*  552 */     this.sealtime = nbt.func_74762_e("SealTime");
/*  553 */     this.barrelType = nbt.func_74762_e("barrelType");
/*  554 */     if (nbt.func_74762_e("fluid") != -1) {
/*      */       
/*  556 */       if (this.fluid != null) {
/*  557 */         this.fluid.amount = nbt.func_74762_e("fluidAmount");
/*      */       } else {
/*  559 */         this.fluid = new FluidStack(nbt.func_74762_e("fluid"), nbt.func_74762_e("fluidAmount"));
/*      */       } 
/*      */     } else {
/*      */       
/*  563 */       this.fluid = null;
/*      */     } 
/*  565 */     this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void createInitNBT(NBTTagCompound nbt) {
/*  571 */     nbt.func_74774_a("rotation", this.rotation);
/*  572 */     nbt.func_74757_a("sealed", this.sealed);
/*  573 */     nbt.func_74768_a("SealTime", this.sealtime);
/*  574 */     nbt.func_74768_a("fluid", (this.fluid != null) ? this.fluid.getFluidID() : -1);
/*  575 */     nbt.func_74768_a("fluidAmount", (this.fluid != null) ? this.fluid.amount : 0);
/*  576 */     nbt.func_74768_a("barrelType", this.barrelType);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleDataPacket(NBTTagCompound nbt) {
/*  582 */     if (nbt.func_74764_b("fluidID")) {
/*      */       
/*  584 */       if (nbt.func_74771_c("fluidID") == -1)
/*  585 */         this.fluid = null; 
/*  586 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*      */     } 
/*  588 */     if (!this.field_145850_b.field_72995_K) {
/*      */       
/*  590 */       if (nbt.func_74764_b("mode")) {
/*      */         
/*  592 */         this.mode = nbt.func_74771_c("mode");
/*      */       }
/*  594 */       else if (nbt.func_74764_b("seal")) {
/*      */         
/*  596 */         this.sealed = nbt.func_74767_n("seal");
/*  597 */         if (!this.sealed) {
/*      */           
/*  599 */           this.unsealtime = (int)TFC_Time.getTotalHours();
/*  600 */           this.sealtime = 0;
/*      */           
/*  602 */           if (this.hasExp) {
/*  603 */             ExpBonus.BARREL.give(this.entityplayer);
/*  604 */             this.hasExp = false;
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/*  609 */           this.sealtime = (int)TFC_Time.getTotalHours();
/*  610 */           this.unsealtime = 0;
/*      */         } 
/*      */ 
/*      */         
/*  614 */         NBTTagCompound timeTag = new NBTTagCompound();
/*  615 */         timeTag.func_74768_a("SealTime", this.sealtime);
/*  616 */         broadcastPacketInRange((AbstractPacket)createDataPacket(timeTag));
/*      */         
/*  618 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*      */       } 
/*      */       
/*  621 */       if (nbt.func_74764_b("tab"))
/*      */       {
/*  623 */         int tab = nbt.func_74771_c("tab");
/*  624 */         switchTab(this.field_145850_b.func_72924_a(nbt.func_74779_i("player")), tab);
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/*  630 */     else if (nbt.func_74764_b("SealTime")) {
/*  631 */       this.sealtime = nbt.func_74762_e("SealTime");
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void switchTab(EntityPlayer player, int tab) {
/*  637 */     if (player != null)
/*  638 */       if (tab == 0) {
/*  639 */         player.openGui(TerraFirmaCraft.instance, 35, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*  640 */       } else if (tab == 1) {
/*  641 */         player.openGui(TerraFirmaCraft.instance, 36, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*      */       }  
/*      */   }
/*      */   private void checkItems() {
/*  645 */     for (int i = 0; i < func_70302_i_(); i++) {
/*  646 */       ItemStack is = func_70301_a(i);
/*  647 */       if (is != null) {
/*  648 */         if (TFC_ItemHeat.hasTemp(is)) {
/*  649 */           this.needCheck = Boolean.valueOf(true);
/*  650 */           this.hasTemp = true;
/*      */           return;
/*      */         } 
/*  653 */         Item item = is.func_77973_b();
/*  654 */         if (item instanceof IFood || item instanceof ItemFoodTFC || item.equals(ModItems.leatherBag)) {
/*  655 */           this.needCheck = Boolean.valueOf(true);
/*      */           return;
/*      */         } 
/*      */       } 
/*      */     } 
/*  660 */     this.hasTemp = false;
/*  661 */     this.needCheck = Boolean.valueOf(false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_145845_h() {
/*  668 */     if (!this.field_145850_b.field_72995_K) {
/*      */       
/*  670 */       if (this.needCheck == null) checkItems(); 
/*  671 */       ItemStack itemstack = this.storage[0];
/*  672 */       if (itemstack != null && this.fluid != null && this.fluid.getFluid() == TFCFluids.FRESHWATER)
/*      */       {
/*  674 */         if (TFC_ItemHeat.hasTemp(itemstack)) {
/*      */           
/*  676 */           float temp = TFC_ItemHeat.getTemp(itemstack);
/*  677 */           if (this.fluid.amount >= 1 && temp > 1.0F) {
/*      */             
/*  679 */             temp -= 50.0F;
/*  680 */             this.fluid.amount--;
/*  681 */             TFC_ItemHeat.setTemp(itemstack, temp);
/*  682 */             TFC_ItemHeat.handleItemHeat(itemstack);
/*      */           } 
/*      */         } 
/*      */       }
/*  686 */       if (itemstack != null && this.fluid != null && itemstack.func_77973_b() instanceof IFood) {
/*      */         
/*  688 */         float w = Food.getWeight(itemstack);
/*  689 */         if (this.fluid.getFluid() == TFCFluids.VINEGAR)
/*      */         {
/*      */           
/*  692 */           if (Food.isBrined(itemstack) && !Food.isPickled(itemstack) && w / this.fluid.amount <= 160.0F / getMaxLiquid() && getSealed() && this.sealtime != 0 && 
/*  693 */             TFC_Time.getTotalHours() - this.sealtime >= 4L) {
/*      */             
/*  695 */             this.fluid.amount = (int)(this.fluid.amount - 1.0F * w);
/*  696 */             Food.setPickled(itemstack, true);
/*  697 */             this.hasExp = true;
/*      */           } 
/*      */         }
/*      */       } 
/*      */       
/*  702 */       BarrelPreservativeRecipe preservative = BarrelManager.getInstance().findMatchingPreservativeRepice(this, itemstack, this.fluid, this.sealed);
/*  703 */       if (preservative == null) {
/*      */ 
/*      */ 
/*      */         
/*  707 */         if (this.needCheck.booleanValue()) TFC_Core.handleItemTicking(this, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e); 
/*  708 */         if (this.hasTemp && this.field_145850_b.func_82737_E() % 600L == 0L) checkItems(); 
/*  709 */         if (!this.needCheck.booleanValue() && this.field_145850_b.func_82737_E() % 1200L == 0L) checkItems();
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  714 */         float env = preservative.getEnvironmentalDecayFactor();
/*  715 */         float base = preservative.getBaseDecayModifier();
/*  716 */         if (Float.isNaN(env) || env < 0.0D) {
/*      */           
/*  718 */           TFC_Core.handleItemTicking(this, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*      */         }
/*  720 */         else if (Float.isNaN(base) || base < 0.0D) {
/*      */           
/*  722 */           TFC_Core.handleItemTicking(this, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, env);
/*      */         }
/*      */         else {
/*      */           
/*  726 */           TFC_Core.handleItemTicking(this, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, env, base);
/*      */         } 
/*      */       } 
/*      */       
/*  730 */       boolean shipsworld = false;
/*  731 */       if (TerraFirmaCraft.instance.ShipsExist) shipsworld = this.field_145850_b instanceof cuchaz.ships.ShipWorld;
/*      */       
/*  733 */       if (!shipsworld && !getSealed()) {
/*  734 */         boolean isfillwater = (this.fluid != null && this.fluid.getFluid() == TFCFluids.FRESHWATER);
/*  735 */         if (this.fluid == null || (isfillwater && this.fluid.amount < getMaxLiquid()))
/*      */         {
/*  737 */           if (TFC_Core.isExposedToRain(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e)) {
/*      */             
/*  739 */             int count = getInvCount();
/*  740 */             if (count == 0 || (count == 1 && getInputStack() != null))
/*      */             {
/*  742 */               if (this.fluid == null) { this.fluid = new FluidStack(TFCFluids.FRESHWATER, 1); }
/*  743 */               else if (isfillwater)
/*  744 */               { this.fluid.amount = Math.min(this.fluid.amount + 1, getMaxLiquid()); }
/*      */             
/*      */             }
/*      */           } 
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  752 */       this.processTimer++;
/*  753 */       if (this.processTimer > 100) {
/*      */         
/*  755 */         processItems();
/*  756 */         this.processTimer = 0;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  762 */       if (getFluidLevel() > 0 && getInputStack() != null) {
/*      */         
/*  764 */         int count = 1;
/*  765 */         int max_stack_size = getInputStack().func_77976_d();
/*  766 */         while ((getInputStack()).field_77994_a > max_stack_size)
/*      */         {
/*  768 */           ItemStack is = getInputStack().func_77979_a(max_stack_size);
/*  769 */           if (count < this.storage.length && func_70301_a(count) == null) { func_70299_a(count, is); }
/*  770 */           else { this.field_145850_b.func_72838_d((Entity)new EntityItem(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, is)); }
/*  771 */            count++;
/*      */         
/*      */         }
/*      */       
/*      */       }
/*  776 */       else if (getFluidLevel() > 0 && getInputStack() == null && getInvCount() > 0) {
/*      */         
/*  778 */         for (int i = 0; i < this.storage.length; i++) {
/*      */           
/*  780 */           if (this.storage[i] != null) {
/*      */             
/*  782 */             this.storage[0] = this.storage[i].func_77946_l();
/*  783 */             this.storage[i] = null;
/*      */ 
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  791 */       if (this.fluid != null && this.fluid.amount == 0) {
/*  792 */         this.fluid = null;
/*      */       }
/*      */       
/*  795 */       if (this.mode == 0) {
/*      */         
/*  797 */         ItemStack container = getInputStack();
/*  798 */         FluidStack inLiquid = FluidContainerRegistry.getFluidForFilledItem(container);
/*      */         
/*  800 */         if (container != null && container.func_77973_b() instanceof IFluidContainerItem)
/*      */         
/*  802 */         { FluidStack isfs = ((IFluidContainerItem)container.func_77973_b()).getFluid(container);
/*  803 */           if (container.func_77973_b().func_77658_a().toLowerCase().contains("waterbag") && isfs != null)
/*      */           {
/*  805 */             int dosage = Math.min(isfs.amount, (GrowthCraftCellar.getConfig()).waterBagDosage);
/*  806 */             int amount = 0;
/*  807 */             if (this.fluid == null) { this.fluid = isfs.copy(); this.fluid.amount = dosage; }
/*  808 */             else if (this.fluid.getFluid() == isfs.getFluid())
/*      */             
/*  810 */             { int a = getFluidLevel() + dosage - getMaxLiquid();
/*  811 */               this.fluid.amount = Math.min(getFluidLevel() + dosage, getMaxLiquid());
/*  812 */               if (a > 0) amount = a;  }
/*      */             else
/*      */             { return; }
/*  815 */              ((IFluidContainerItem)container.func_77973_b()).drain(container, dosage - amount, true);
/*  816 */             this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*      */           }
/*  818 */           else if (isfs != null && addLiquid(isfs))
/*      */           {
/*  820 */             ((IFluidContainerItem)container.func_77973_b()).drain(container, ((IFluidContainerItem)container.func_77973_b()).getCapacity(container), true);
/*      */           }
/*      */            }
/*      */         
/*  824 */         else if (inLiquid != null && container != null && container.field_77994_a == 1 && 
/*  825 */           addLiquid(inLiquid)) { func_70299_a(0, FluidContainerRegistry.drainFluidContainer(container)); }
/*      */ 
/*      */       
/*  828 */       } else if (this.mode == 1) {
/*      */         
/*  830 */         ItemStack container = getInputStack();
/*      */         
/*  832 */         if (this.fluid != null && this.fluid.amount > 0) {
/*  833 */           int ln = checkLantern(container);
/*  834 */           if (ln > 1)
/*      */             return; 
/*      */         } 
/*  837 */         if (container != null && this.fluid != null && container.func_77973_b() instanceof IFluidContainerItem) {
/*      */           
/*  839 */           FluidStack isfs = ((IFluidContainerItem)container.func_77973_b()).getFluid(container);
/*  840 */           if (isfs == null || this.fluid.isFluidEqual(isfs)) {
/*      */             
/*  842 */             this.fluid.amount -= ((IFluidContainerItem)container.func_77973_b()).fill(container, this.fluid, true);
/*  843 */             if (this.fluid.amount == 0) this.fluid = null;
/*      */           
/*      */           } 
/*  846 */         } else if (FluidContainerRegistry.isEmptyContainer(container)) {
/*      */           
/*  848 */           ItemStack fullContainer = removeLiquid(getInputStack());
/*  849 */           if (fullContainer.func_77973_b() == TFCItems.woodenBucketMilk) ItemCustomBucketMilk.createTag(fullContainer, 20.0F); 
/*  850 */           func_70299_a(0, fullContainer);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private int checkLantern(ItemStack is) {
/*  857 */     if (is != null && (
/*  858 */       is.func_77977_a().toLowerCase().contains("lante") || is.func_77973_b().getClass().getSimpleName().toLowerCase().contains("lante"))) {
/*  859 */       return is.field_77994_a;
/*      */     }
/*  861 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void processItems() {
/*  897 */     if (getInvCount() == 0) {
/*      */ 
/*      */ 
/*      */       
/*  901 */       boolean isCheese = false;
/*      */       
/*  903 */       if (getFluidStack() != null && !isCheese)
/*      */       
/*  905 */       { this.recipe = BarrelManager.getInstance().findMatchingRecipe(getInputStack(), getFluidStack(), this.sealed, getTechLevel());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  913 */         if (this.recipe != null && !this.field_145850_b.field_72995_K) {
/*      */           
/*  915 */           int time = 0;
/*  916 */           if (this.sealtime > 0) {
/*  917 */             time = (int)TFC_Time.getTotalHours() - this.sealtime;
/*  918 */           } else if (this.unsealtime > 0) {
/*  919 */             time = (int)TFC_Time.getTotalHours() - this.unsealtime;
/*      */           } 
/*      */           
/*  922 */           if (this.recipe.isSealedRecipe() && time < this.recipe.sealTime) {
/*      */             return;
/*      */           }
/*  925 */           ItemStack origIS = (getInputStack() != null) ? getInputStack().func_77946_l() : null;
/*  926 */           FluidStack origFS = (getFluidStack() != null) ? getFluidStack().copy() : null;
/*  927 */           if (this.fluid.isFluidEqual(this.recipe.getResultFluid(origIS, origFS, time)) && this.recipe.removesLiquid) {
/*      */             
/*  929 */             this.hasExp = true;
/*  930 */             if (this.fluid.getFluid() == TFCFluids.BRINE && origIS != null && origIS.func_77973_b() instanceof IFood) {
/*  931 */               this.fluid.amount = (int)(this.fluid.amount - (this.recipe.getResultFluid(origIS, origFS, time)).amount * Food.getWeight(origIS));
/*      */             } else {
/*  933 */               this.fluid.amount -= (this.recipe.getResultFluid(origIS, origFS, time)).amount;
/*      */             } 
/*      */           } else {
/*      */             
/*  937 */             this.fluid = this.recipe.getResultFluid(origIS, origFS, time).copy();
/*  938 */             this.hasExp = true;
/*  939 */             if (this.fluid != null && !(this.recipe instanceof BarrelLiquidToLiquidRecipe) && origFS != null) {
/*  940 */               this.fluid.amount = origFS.amount;
/*      */             }
/*  942 */             this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*      */           } 
/*      */           
/*  945 */           if (origFS != null && origFS.getFluid() != TFCFluids.MILKCURDLED && this.fluid.getFluid() == TFCFluids.MILKCURDLED) {
/*  946 */             this.sealtime = (int)TFC_Time.getTotalHours();
/*      */           }
/*  948 */           Stack<ItemStack> resultStacks = this.recipe.getResult(origIS, origFS, time);
/*  949 */           if (!resultStacks.isEmpty())
/*      */           {
/*  951 */             ItemStack result = resultStacks.pop();
/*  952 */             if (this.fluid != null && this.fluid.getFluid() == TFCFluids.BRINE) {
/*      */               
/*  954 */               if (result == null && origIS != null)
/*  955 */                 result = origIS.func_77946_l(); 
/*  956 */               if (result != null && result.func_77973_b() instanceof IFood && (result.func_77973_b() == TFCItems.cheese || ((IFood)result.func_77973_b()).getFoodGroup() != EnumFoodGroup.Grain))
/*      */               {
/*  958 */                 if (!Food.isBrined(result)) {
/*  959 */                   Food.setBrined(result, true);
/*  960 */                   this.hasExp = true;
/*      */                 } 
/*      */               }
/*      */             } 
/*      */             
/*  965 */             this.storage[0] = result;
/*      */             
/*  967 */             for (int i = 1; i < this.storage.length; i++) {
/*      */               
/*  969 */               if (this.storage[i] == null && !resultStacks.isEmpty()) {
/*  970 */                 func_70299_a(i, resultStacks.pop());
/*      */               }
/*      */             } 
/*  973 */             while (!resultStacks.isEmpty()) {
/*  974 */               this.field_145850_b.func_72838_d((Entity)new EntityItem(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, resultStacks.pop()));
/*      */             }
/*  976 */             func_70299_a(0, result);
/*      */           }
/*      */         
/*      */         }  }
/*  980 */       else if (getFluidStack() == null && !isCheese) { this.recipe = null; }
/*      */     
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void registerRecipes() {
/* 1048 */     BarrelManager.getInstance().addRecipe((BarrelRecipe)new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.potato), 160.0F), new FluidStack(TFCFluids.FRESHWATER, 10000), null, new FluidStack(TFCFluids.VODKA, 10000)));
/*      */ 
/*      */     
/* 1051 */     BarrelManager.getInstance().addRecipe((BarrelRecipe)new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.wheatGround), 160.0F), new FluidStack(TFCFluids.FRESHWATER, 10000), null, new FluidStack(TFCFluids.WHISKEY, 10000)));
/* 1052 */     BarrelManager.getInstance().addRecipe((BarrelRecipe)new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.ryeGround), 160.0F), new FluidStack(TFCFluids.FRESHWATER, 10000), null, new FluidStack(TFCFluids.RYEWHISKEY, 10000)));
/*      */ 
/*      */     
/* 1055 */     BarrelManager.getInstance().addRecipe((BarrelRecipe)new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.sugar), 160.0F), new FluidStack(TFCFluids.FRESHWATER, 10000), null, new FluidStack(TFCFluids.RUM, 10000)));
/* 1056 */     BarrelManager.getInstance().addRecipe((BarrelRecipe)new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.cornmealGround), 160.0F), new FluidStack(TFCFluids.FRESHWATER, 10000), null, new FluidStack(TFCFluids.CORNWHISKEY, 10000)));
/*      */ 
/*      */     
/* 1059 */     BarrelManager.getInstance().addRecipe((new BarrelRecipe(new ItemStack(TFCItems.logs, 1, 0), new FluidStack(TFCFluids.FRESHWATER, 1000), null, new FluidStack(TFCFluids.TANNIN, 1000))).setMinTechLevel(0));
/* 1060 */     BarrelManager.getInstance().addRecipe((new BarrelRecipe(new ItemStack(TFCItems.logs, 1, 2), new FluidStack(TFCFluids.FRESHWATER, 1000), null, new FluidStack(TFCFluids.TANNIN, 1000))).setMinTechLevel(0));
/* 1061 */     BarrelManager.getInstance().addRecipe((new BarrelRecipe(new ItemStack(TFCItems.logs, 1, 3), new FluidStack(TFCFluids.FRESHWATER, 1000), null, new FluidStack(TFCFluids.TANNIN, 1000))).setMinTechLevel(0));
/* 1062 */     BarrelManager.getInstance().addRecipe((new BarrelRecipe(new ItemStack(TFCItems.logs, 1, 4), new FluidStack(TFCFluids.FRESHWATER, 1000), null, new FluidStack(TFCFluids.TANNIN, 1000))).setMinTechLevel(0));
/* 1063 */     BarrelManager.getInstance().addRecipe((new BarrelRecipe(new ItemStack(TFCItems.logs, 1, 5), new FluidStack(TFCFluids.FRESHWATER, 1000), null, new FluidStack(TFCFluids.TANNIN, 1000))).setMinTechLevel(0));
/* 1064 */     BarrelManager.getInstance().addRecipe((new BarrelRecipe(new ItemStack(TFCItems.logs, 1, 6), new FluidStack(TFCFluids.FRESHWATER, 1000), null, new FluidStack(TFCFluids.TANNIN, 1000))).setMinTechLevel(0));
/* 1065 */     BarrelManager.getInstance().addRecipe((new BarrelRecipe(new ItemStack(TFCItems.logs, 1, 9), new FluidStack(TFCFluids.FRESHWATER, 1000), null, new FluidStack(TFCFluids.TANNIN, 1000))).setMinTechLevel(0));
/* 1066 */     BarrelManager.getInstance().addRecipe((new BarrelRecipe(new ItemStack(TFCItems.powder, 1, 0), new FluidStack(TFCFluids.FRESHWATER, 500), null, new FluidStack(TFCFluids.LIMEWATER, 500), 0)).setMinTechLevel(0).setSealedRecipe(false).setRemovesLiquid(false).setAllowAnyStack(false));
/* 1067 */     BarrelManager.getInstance().addRecipe((new BarrelMultiItemRecipe(new ItemStack(TFCItems.scrapedHide, 1, 0), new FluidStack(TFCFluids.FRESHWATER, 300), new ItemStack(TFCItems.prepHide, 1, 0), new FluidStack(TFCFluids.FRESHWATER, 300))).setMinTechLevel(0));
/* 1068 */     BarrelManager.getInstance().addRecipe((new BarrelMultiItemRecipe(new ItemStack(TFCItems.scrapedHide, 1, 1), new FluidStack(TFCFluids.FRESHWATER, 400), new ItemStack(TFCItems.prepHide, 1, 1), new FluidStack(TFCFluids.FRESHWATER, 400))).setMinTechLevel(0));
/* 1069 */     BarrelManager.getInstance().addRecipe((new BarrelMultiItemRecipe(new ItemStack(TFCItems.scrapedHide, 1, 2), new FluidStack(TFCFluids.FRESHWATER, 500), new ItemStack(TFCItems.prepHide, 1, 2), new FluidStack(TFCFluids.FRESHWATER, 500))).setMinTechLevel(0));
/* 1070 */     BarrelManager.getInstance().addRecipe((new BarrelMultiItemRecipe(new ItemStack(TFCItems.hide, 1, 0), new FluidStack(TFCFluids.LIMEWATER, 300), new ItemStack(TFCItems.soakedHide, 1, 0), new FluidStack(TFCFluids.LIMEWATER, 300))).setMinTechLevel(0));
/* 1071 */     BarrelManager.getInstance().addRecipe((new BarrelMultiItemRecipe(new ItemStack(TFCItems.hide, 1, 1), new FluidStack(TFCFluids.LIMEWATER, 400), new ItemStack(TFCItems.soakedHide, 1, 1), new FluidStack(TFCFluids.LIMEWATER, 400))).setMinTechLevel(0));
/* 1072 */     BarrelManager.getInstance().addRecipe((new BarrelMultiItemRecipe(new ItemStack(TFCItems.hide, 1, 2), new FluidStack(TFCFluids.LIMEWATER, 500), new ItemStack(TFCItems.soakedHide, 1, 2), new FluidStack(TFCFluids.LIMEWATER, 500))).setMinTechLevel(0));
/* 1073 */     BarrelManager.getInstance().addRecipe((new BarrelMultiItemRecipe(new ItemStack(TFCItems.prepHide, 1, 0), new FluidStack(TFCFluids.TANNIN, 300), new ItemStack(TFCItems.leather, 1), new FluidStack(TFCFluids.TANNIN, 300))).setKeepStackSize(false).setMinTechLevel(0));
/* 1074 */     BarrelManager.getInstance().addRecipe((new BarrelMultiItemRecipe(new ItemStack(TFCItems.prepHide, 1, 1), new FluidStack(TFCFluids.TANNIN, 400), new ItemStack(TFCItems.leather, 2), new FluidStack(TFCFluids.TANNIN, 400))).setKeepStackSize(false).setMinTechLevel(0));
/* 1075 */     BarrelManager.getInstance().addRecipe((new BarrelMultiItemRecipe(new ItemStack(TFCItems.prepHide, 1, 2), new FluidStack(TFCFluids.TANNIN, 500), new ItemStack(TFCItems.leather, 3), new FluidStack(TFCFluids.TANNIN, 500))).setKeepStackSize(false).setMinTechLevel(0));
/* 1076 */     BarrelManager.getInstance().addRecipe((new BarrelRecipe(new ItemStack(TFCBlocks.sand, 1, 32767), new FluidStack(TFCFluids.LIMEWATER, 100), new ItemStack(TFCItems.mortar, 16), new FluidStack(TFCFluids.LIMEWATER, 100))).setMinTechLevel(0));
/* 1077 */     BarrelManager.getInstance().addRecipe((new BarrelRecipe(new ItemStack(TFCBlocks.sand2, 1, 32767), new FluidStack(TFCFluids.LIMEWATER, 100), new ItemStack(TFCItems.mortar, 16), new FluidStack(TFCFluids.LIMEWATER, 100))).setMinTechLevel(0));
/* 1078 */     BarrelManager.getInstance().addRecipe((BarrelRecipe)new BarrelVinegarRecipe(new FluidStack(TFCFluids.VODKA, 100), new FluidStack(TFCFluids.VINEGAR, 100)));
/* 1079 */     BarrelManager.getInstance().addRecipe((BarrelRecipe)new BarrelVinegarRecipe(new FluidStack(TFCFluids.CIDER, 100), new FluidStack(TFCFluids.VINEGAR, 100)));
/* 1080 */     BarrelManager.getInstance().addRecipe((BarrelRecipe)new BarrelVinegarRecipe(new FluidStack(TFCFluids.WHISKEY, 100), new FluidStack(TFCFluids.VINEGAR, 100)));
/* 1081 */     BarrelManager.getInstance().addRecipe((BarrelRecipe)new BarrelVinegarRecipe(new FluidStack(TFCFluids.RYEWHISKEY, 100), new FluidStack(TFCFluids.VINEGAR, 100)));
/*      */ 
/*      */     
/* 1084 */     BarrelManager.getInstance().addRecipe((BarrelRecipe)new BarrelVinegarRecipe(new FluidStack(TFCFluids.RUM, 100), new FluidStack(TFCFluids.VINEGAR, 100)));
/* 1085 */     BarrelManager.getInstance().addRecipe((BarrelRecipe)new BarrelVinegarRecipe(new FluidStack(TFCFluids.CORNWHISKEY, 100), new FluidStack(TFCFluids.VINEGAR, 100)));
/* 1086 */     BarrelManager.getInstance().addRecipe((new BarrelLiquidToLiquidRecipe(new FluidStack(TFCFluids.SALTWATER, 9000), new FluidStack(TFCFluids.VINEGAR, 1000), new FluidStack(TFCFluids.BRINE, 10000))).setSealedRecipe(false).setMinTechLevel(0).setRemovesLiquid(false));
/* 1087 */     BarrelManager.getInstance().addRecipe((new BarrelBriningRecipe(new FluidStack(TFCFluids.BRINE, 60), new FluidStack(TFCFluids.BRINE, 60))).setMinTechLevel(0));
/* 1088 */     BarrelManager.getInstance().addRecipe((new BarrelMultiItemRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.sugarcane), 1.0F), new FluidStack(TFCFluids.FRESHWATER, 60), ItemFoodTFC.createTag(new ItemStack(TFCItems.sugar), 0.1F), new FluidStack(TFCFluids.FRESHWATER, 60))).setMinTechLevel(0));
/* 1089 */     BarrelManager.getInstance().addRecipe((new BarrelMultiItemRecipe(new ItemStack(TFCItems.jute, 1, 0), new FluidStack(TFCFluids.FRESHWATER, 155), new ItemStack(TFCItems.juteFiber, 1, 0), new FluidStack(TFCFluids.FRESHWATER, 155))).setMinTechLevel(0));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1095 */     BarrelPreservativeRecipe picklePreservative = (new BarrelPreservativeRecipe(new FluidStack(TFCFluids.VINEGAR, 31), "gui.barrel.preserving")).setAllowGrains(false).setRequiresPickled(true).setEnvironmentalDecayFactor(0.25F).setBaseDecayModifier(0.1F).setRequiresSealed(true);
/* 1096 */     BarrelPreservativeRecipe brineInBrinePreservative = (new BarrelPreservativeRecipe(new FluidStack(TFCFluids.BRINE, 62), "gui.barrel.preserving")).setAllowGrains(false).setRequiresBrined(true).setEnvironmentalDecayFactor(0.75F).setRequiresSealed(true);
/* 1097 */     BarrelPreservativeRecipe brineInVinegarPreservative = (new BarrelPreservativeRecipe(new FluidStack(TFCFluids.VINEGAR, 62), "gui.barrel.preserving")).setAllowGrains(false).setRequiresBrined(true).setEnvironmentalDecayFactor(0.75F).setRequiresSealed(true);
/* 1098 */     BarrelManager.getInstance().addPreservative(picklePreservative);
/* 1099 */     BarrelManager.getInstance().addPreservative(brineInBrinePreservative);
/* 1100 */     BarrelManager.getInstance().addPreservative(brineInVinegarPreservative);
/*      */   }
/*      */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEBarrel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */