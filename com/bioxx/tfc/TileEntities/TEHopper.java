/*     */ package com.bioxx.tfc.TileEntities;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Handlers.Network.AbstractPacket;
/*     */ import com.bioxx.tfc.api.Food;
/*     */ import com.bioxx.tfc.api.TFCFluids;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import fof.tfcsu.utils.ExpBonus;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.tileentity.IHopper;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ 
/*     */ 
/*     */ public class TEHopper
/*     */   extends NetworkTileEntity
/*     */   implements IHopper
/*     */ {
/*  27 */   private ItemStack[] storage = new ItemStack[5];
/*     */   private String customName;
/*  29 */   private int cooldown = -1;
/*     */ 
/*     */   
/*     */   public int pressCooldown;
/*     */ 
/*     */   
/*     */   public ItemStack pressBlock;
/*     */ 
/*     */   
/*     */   public float startedweight;
/*     */ 
/*     */   
/*     */   private boolean giveexp = false;
/*     */ 
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbt) {
/*  45 */     super.func_145839_a(nbt);
/*  46 */     NBTTagList nbttaglist = nbt.func_150295_c("Items", 10);
/*  47 */     this.storage = new ItemStack[func_70302_i_()];
/*     */     
/*  49 */     if (nbt.func_150297_b("CustomName", 8))
/*     */     {
/*  51 */       this.customName = nbt.func_74779_i("CustomName");
/*     */     }
/*     */     
/*  54 */     this.cooldown = nbt.func_74762_e("TransferCooldown");
/*     */     
/*  56 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*     */       
/*  58 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/*  59 */       byte b0 = nbttagcompound1.func_74771_c("Slot");
/*     */       
/*  61 */       if (b0 >= 0 && b0 < this.storage.length)
/*     */       {
/*  63 */         this.storage[b0] = ItemStack.func_77949_a(nbttagcompound1);
/*     */       }
/*     */     } 
/*     */     
/*  67 */     this.pressCooldown = nbt.func_74762_e("pressCooldown");
/*  68 */     this.pressBlock = ItemStack.func_77949_a(nbt.func_74775_l("pressBlock"));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbt) {
/*  74 */     super.func_145841_b(nbt);
/*  75 */     NBTTagList nbttaglist = new NBTTagList();
/*     */     
/*  77 */     for (int i = 0; i < this.storage.length; i++) {
/*     */       
/*  79 */       if (this.storage[i] != null) {
/*     */         
/*  81 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*  82 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/*  83 */         this.storage[i].func_77955_b(nbttagcompound1);
/*  84 */         nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
/*     */       } 
/*     */     } 
/*     */     
/*  88 */     nbt.func_74782_a("Items", (NBTBase)nbttaglist);
/*  89 */     nbt.func_74768_a("TransferCooldown", this.cooldown);
/*     */     
/*  91 */     if (func_145818_k_())
/*     */     {
/*  93 */       nbt.func_74778_a("CustomName", this.customName);
/*     */     }
/*     */     
/*  96 */     nbt.func_74768_a("pressCooldown", this.pressCooldown);
/*     */     
/*  98 */     if (this.pressBlock != null) {
/*     */       
/* 100 */       NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 101 */       this.pressBlock.func_77955_b(nbttagcompound1);
/* 102 */       nbt.func_74782_a("pressBlock", (NBTBase)nbttagcompound1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox() {
/* 110 */     return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 2), (this.field_145849_e + 1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70302_i_() {
/* 119 */     return this.storage.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70301_a(int i) {
/* 128 */     return this.storage[i];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70298_a(int i, int amount) {
/* 138 */     if (this.storage[i] != null) {
/*     */ 
/*     */ 
/*     */       
/* 142 */       if ((this.storage[i]).field_77994_a <= amount) {
/*     */         
/* 144 */         ItemStack itemStack = this.storage[i];
/* 145 */         this.storage[i] = null;
/* 146 */         return itemStack;
/*     */       } 
/*     */ 
/*     */       
/* 150 */       ItemStack itemstack = this.storage[i].func_77979_a(amount);
/*     */       
/* 152 */       if ((this.storage[i]).field_77994_a == 0)
/*     */       {
/* 154 */         this.storage[i] = null;
/*     */       }
/*     */       
/* 157 */       return itemstack;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 162 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70304_b(int i) {
/* 173 */     if (this.storage[i] != null) {
/*     */       
/* 175 */       ItemStack itemstack = this.storage[i];
/* 176 */       this.storage[i] = null;
/* 177 */       return itemstack;
/*     */     } 
/*     */ 
/*     */     
/* 181 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70299_a(int i, ItemStack is) {
/* 191 */     this.storage[i] = is;
/*     */     
/* 193 */     if (is != null && is.field_77994_a > func_70297_j_())
/*     */     {
/* 195 */       is.field_77994_a = func_70297_j_();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String func_145825_b() {
/* 205 */     return func_145818_k_() ? this.customName : "container.hopper";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_145818_k_() {
/* 214 */     return (this.customName != null && this.customName.length() > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCustomName(String s) {
/* 219 */     this.customName = s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70297_j_() {
/* 228 */     return 64;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70300_a(EntityPlayer p) {
/* 237 */     return (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this) ? false : ((p.func_70092_e(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D) <= 64.0D));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70295_k_() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70305_f() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_94041_b(int i, ItemStack is) {
/* 252 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145845_h() {
/* 258 */     if (this.field_145850_b.field_72995_K) {
/*     */       
/* 260 */       if (this.pressCooldown > 0) {
/* 261 */         this.pressCooldown--;
/*     */       } else {
/* 263 */         this.pressBlock = null;
/*     */       } 
/* 265 */     } else if (this.field_145850_b != null && !this.field_145850_b.field_72995_K) {
/*     */       
/* 267 */       this.cooldown--;
/*     */       
/* 269 */       TFC_Core.handleItemTicking((IInventory)this, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 274 */       if (getPressableItem() == -1) this.pressCooldown = 0;
/*     */       
/* 276 */       if (this.pressCooldown > 0) {
/*     */         
/* 278 */         this.pressCooldown--;
/* 279 */         if (this.pressCooldown % 20 == 0) press();
/*     */       
/* 281 */       } else if (this.pressCooldown == 0 && this.pressBlock != null) {
/*     */         
/* 283 */         this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, Block.func_149634_a(this.pressBlock.func_77973_b()), this.pressBlock.func_77960_j(), 2);
/* 284 */         this.pressBlock = null;
/* 285 */         if (this.giveexp) { ExpBonus.PRESS.spawnExp(this.field_145850_b, func_96107_aA(), func_96109_aB(), func_96108_aC()); this.giveexp = false; }
/*     */       
/*     */       } 
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
/*     */ 
/*     */       
/* 313 */       if (!isCoolingDown())
/*     */       {
/* 315 */         setCooldown(0);
/*     */       }
/*     */       
/* 318 */       Block blockAbove = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
/* 319 */       if (blockAbove != null && hasPressableItem() > 0)
/*     */       {
/* 321 */         if (this.pressBlock != null && !(blockAbove instanceof com.bioxx.tfc.Blocks.Terrain.BlockCobble) && !(blockAbove instanceof com.bioxx.tfc.Blocks.Terrain.BlockGravel) && !(blockAbove instanceof com.bioxx.tfc.Blocks.Terrain.BlockSand) && !(blockAbove instanceof com.bioxx.tfc.Blocks.Terrain.BlockDirt)) {
/*     */           
/* 323 */           TFC_Core.setBlockToAirWithDrops(this.field_145850_b, this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
/*     */         }
/* 325 */         else if (blockAbove instanceof com.bioxx.tfc.Blocks.Terrain.BlockSmooth) {
/*     */           
/* 327 */           this.pressBlock = new ItemStack(blockAbove, 1, this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e));
/* 328 */           this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
/* 329 */           sendPressPacket();
/* 330 */           beginPressing();
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void press() {
/* 338 */     TEBarrel barrel = null;
/* 339 */     if (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) instanceof TEBarrel) {
/* 340 */       barrel = (TEBarrel)this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
/*     */     }
/*     */     
/* 343 */     int num = getPressableItem();
/* 344 */     ItemStack item = null;
/* 345 */     if (num != -1) item = this.storage[num];
/*     */     
/* 347 */     if (item != null) {
/*     */       
/* 349 */       if (item.func_77973_b() == TFCItems.honeycomb && this.startedweight == 0.0F) this.startedweight = Food.getWeight(item);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 357 */       if (item.func_77973_b() == TFCItems.honeycomb) {
/*     */         
/* 359 */         if (item.field_77994_a > 0) Food.setWeight(item, Food.getWeight(item) - 0.98F);
/*     */         
/* 361 */         if (item.field_77994_a == 0 || Food.getWeight(item) < 0.98F) {
/*     */           
/* 363 */           if (this.startedweight > 150.0F) this.storage[num] = new ItemStack(TFCItems.paraffin); 
/* 364 */           this.startedweight = 0.0F;
/*     */         } 
/* 366 */         if (barrel != null && barrel.canAcceptLiquids() && !barrel.getSealed()) barrel.addLiquid(new FluidStack(TFCFluids.HONEY, 1));
/*     */       
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void beginPressing() {
/* 374 */     int pressWeight = hasPressableItem();
/* 375 */     if (pressWeight > 0) {
/*     */ 
/*     */       
/* 378 */       this.pressCooldown = (int)(this.pressCooldown + pressWeight / 0.98F * 20.0F);
/* 379 */       sendCooldownPacket();
/* 380 */       if (pressWeight > 60) { this.giveexp = true; } else { this.giveexp = false; }
/*     */     
/*     */     } 
/*     */   }
/*     */   
/*     */   public int hasPressableItem() {
/* 386 */     int amount = 0;
/* 387 */     for (int i = 0; i < this.storage.length; i++) {
/*     */       
/* 389 */       if (this.storage[i] != null && this.storage[i].func_77973_b() == TFCItems.honeycomb)
/*     */       {
/* 391 */         amount = (int)(amount + Math.floor(Food.getWeight(this.storage[i])));
/*     */       }
/*     */     } 
/* 394 */     return amount;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPressableItem() {
/* 399 */     for (int i = 0; i < this.storage.length; i++) {
/*     */       
/* 401 */       if (this.storage[i] != null && this.storage[i].func_77973_b() == TFCItems.honeycomb)
/*     */       {
/* 403 */         return i;
/*     */       }
/*     */     } 
/* 406 */     return -1;
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
/*     */   public void setCooldown(int time) {
/* 445 */     this.cooldown = time;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCoolingDown() {
/* 450 */     return (this.cooldown > 0);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleInitPacket(NBTTagCompound nbt) {
/* 848 */     if (nbt.func_74764_b("pressBlock"))
/*     */     {
/* 850 */       this.pressBlock = ItemStack.func_77949_a(nbt.func_74775_l("pressBlock"));
/*     */     }
/* 852 */     this.pressCooldown = nbt.func_74762_e("pressCooldown");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void createInitNBT(NBTTagCompound nbt) {
/* 858 */     if (this.pressBlock != null) {
/*     */       
/* 860 */       NBTTagCompound pb = new NBTTagCompound();
/* 861 */       this.pressBlock.func_77955_b(pb);
/* 862 */       nbt.func_74782_a("pressBlock", (NBTBase)pb);
/*     */     } 
/* 864 */     nbt.func_74768_a("pressCooldown", this.pressCooldown);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleDataPacket(NBTTagCompound nbt) {
/* 870 */     if (nbt.func_74764_b("pressBlock"))
/*     */     {
/* 872 */       this.pressBlock = ItemStack.func_77949_a(nbt.func_74775_l("pressBlock"));
/*     */     }
/* 874 */     if (nbt.func_74764_b("pressCooldown"))
/*     */     {
/* 876 */       this.pressCooldown = nbt.func_74762_e("pressCooldown");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void sendPressPacket() {
/* 882 */     NBTTagCompound nbt = new NBTTagCompound();
/* 883 */     if (this.pressBlock != null) {
/*     */       
/* 885 */       NBTTagCompound pb = new NBTTagCompound();
/* 886 */       this.pressBlock.func_77955_b(pb);
/* 887 */       nbt.func_74782_a("pressBlock", (NBTBase)pb);
/*     */     } 
/* 889 */     broadcastPacketInRange((AbstractPacket)createDataPacket(nbt));
/*     */   }
/*     */ 
/*     */   
/*     */   private void sendCooldownPacket() {
/* 894 */     NBTTagCompound nbt = new NBTTagCompound();
/* 895 */     nbt.func_74768_a("pressCooldown", this.pressCooldown);
/* 896 */     broadcastPacketInRange((AbstractPacket)createDataPacket(nbt));
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_96107_aA() {
/* 901 */     return this.field_145851_c;
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_96109_aB() {
/* 906 */     return this.field_145848_d;
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_96108_aC() {
/* 911 */     return this.field_145849_e;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEHopper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */