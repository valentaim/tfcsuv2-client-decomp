/*     */ package com.bioxx.tfc.TileEntities;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntityCowTFC;
/*     */ import com.bioxx.tfc.Food.ItemFoodTFC;
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import com.bioxx.tfc.api.Crafting.QuernManager;
/*     */ import com.bioxx.tfc.api.Crafting.QuernRecipe;
/*     */ import com.bioxx.tfc.api.Food;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ 
/*     */ public class TEQuern
/*     */   extends NetworkTileEntity
/*     */   implements IInventory {
/*  30 */   public ItemStack[] storage = new ItemStack[3];
/*     */   
/*     */   public int rotation;
/*     */   
/*     */   public boolean shouldRotate;
/*     */   public int rotatetimer;
/*     */   public boolean hasQuern;
/*     */   
/*     */   public void func_145845_h() {
/*  39 */     if (!this.field_145850_b.field_72995_K) {
/*  40 */       TFC_Core.handleItemTicking(this, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/*  42 */     this.hasQuern = (this.storage[2] != null);
/*     */     
/*  44 */     if (this.shouldRotate) {
/*     */       
/*  46 */       this.rotatetimer++;
/*  47 */       if (this.rotatetimer == 90) {
/*     */         
/*  49 */         this.rotatetimer = 0;
/*  50 */         this.shouldRotate = false;
/*  51 */         if (!this.field_145850_b.field_72995_K)
/*     */         {
/*  53 */           if (processItem() && this.storage[2] != null) {
/*  54 */             damageStackInSlot(2);
/*     */           }
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox() {
/*  64 */     return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 1), (this.field_145849_e + 1));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean processItem() {
/*  69 */     if (this.storage[0] != null) {
/*     */       
/*  71 */       QuernRecipe qr = QuernManager.getInstance().findMatchingRecipe(this.storage[0]);
/*  72 */       if (qr == null) {
/*     */         
/*  74 */         TerraFirmaCraft.LOG.warn("QUERN RECIPE NOT FOUND! This is a BUG! -- " + this.storage[0].func_77973_b().func_77658_a());
/*  75 */         return false;
/*     */       } 
/*     */ 
/*     */       
/*  79 */       if (this.storage[1] != null && (this.storage[1].func_77973_b() != qr.getResult().func_77973_b() || this.storage[1].func_77960_j() != qr.getResult().func_77960_j())) {
/*     */         
/*  81 */         ejectItem(this.storage[1]);
/*  82 */         this.storage[1] = null;
/*     */       } 
/*     */       
/*  85 */       if (qr.getInItem().func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood) {
/*     */         
/*  87 */         if (this.storage[1] != null) {
/*     */           
/*  89 */           float slot0Weight = Food.getWeight(this.storage[0]);
/*  90 */           float slot1Weight = Food.getWeight(this.storage[1]);
/*  91 */           float newWeight = slot0Weight + slot1Weight;
/*     */           
/*  93 */           if (newWeight > 160.0F) {
/*     */             
/*  95 */             Food.setWeight(this.storage[1], newWeight - 160.0F);
/*     */             
/*  97 */             ItemStack tossStack = this.storage[1].func_77946_l();
/*  98 */             Food.setWeight(tossStack, 160.0F);
/*  99 */             ejectItem(tossStack);
/*     */           }
/*     */           else {
/*     */             
/* 103 */             Food.setWeight(this.storage[1], newWeight);
/*     */           } 
/* 105 */           this.storage[0] = null;
/* 106 */           this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 107 */           return true;
/*     */         } 
/*     */         
/* 110 */         if (this.storage[1] == null)
/*     */         {
/* 112 */           this.storage[1] = qr.getResult().func_77946_l();
/* 113 */           float flourWeight = Food.getWeight(this.storage[0]);
/* 114 */           float flourDecay = Food.getDecay(this.storage[0]);
/* 115 */           ItemFoodTFC.createTag(this.storage[1], flourWeight, flourDecay);
/* 116 */           this.storage[0] = null;
/* 117 */           this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 118 */           return true;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 123 */         if ((this.storage[0]).field_77994_a == (qr.getInItem()).field_77994_a) {
/*     */           
/* 125 */           this.storage[0] = null;
/* 126 */           this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */         } else {
/*     */           
/* 129 */           (this.storage[0]).field_77994_a -= (qr.getInItem()).field_77994_a;
/*     */         } 
/* 131 */         if (this.storage[1] == null) {
/* 132 */           this.storage[1] = qr.getResult().func_77946_l();
/* 133 */         } else if ((this.storage[1]).field_77994_a < this.storage[1].func_77976_d()) {
/*     */           
/* 135 */           if ((qr.getResult()).field_77994_a + (this.storage[1]).field_77994_a > this.storage[1].func_77976_d()) {
/*     */             
/* 137 */             int amountleft = (qr.getResult()).field_77994_a - this.storage[1].func_77976_d() - (this.storage[1]).field_77994_a;
/* 138 */             ItemStack tossStack = qr.getResult().func_77946_l();
/* 139 */             tossStack.field_77994_a = tossStack.func_77976_d();
/* 140 */             ejectItem(tossStack);
/* 141 */             ItemStack remainStack = qr.getResult().func_77946_l();
/* 142 */             remainStack.field_77994_a = amountleft;
/* 143 */             this.storage[1] = remainStack;
/*     */           } else {
/*     */             
/* 146 */             (this.storage[1]).field_77994_a += (qr.getResult()).field_77994_a;
/*     */           } 
/*     */         } else {
/*     */           
/* 150 */           ejectItem(this.storage[1]);
/* 151 */           this.storage[1] = qr.getResult().func_77946_l();
/*     */         } 
/* 153 */         return true;
/*     */       } 
/*     */     } 
/* 156 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void damageStackInSlot(int slot) {
/* 161 */     if (this.storage[slot] != null) {
/*     */ 
/*     */       
/* 164 */       this.storage[slot].func_77972_a(slot, (EntityLivingBase)new EntityCowTFC(this.field_145850_b));
/* 165 */       if ((this.storage[slot]).field_77994_a == 0 || this.storage[slot].func_77960_j() == this.storage[slot].func_77958_k()) {
/*     */         
/* 167 */         func_70299_a(slot, (ItemStack)null);
/* 168 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70298_a(int slot, int amount) {
/* 176 */     if (this.storage[slot] != null) {
/*     */       
/* 178 */       if ((this.storage[slot]).field_77994_a <= amount) {
/*     */         
/* 180 */         ItemStack itemstack = this.storage[slot];
/* 181 */         func_70299_a(slot, (ItemStack)null);
/* 182 */         return itemstack;
/*     */       } 
/* 184 */       ItemStack itemstack1 = this.storage[slot].func_77979_a(amount);
/* 185 */       if ((this.storage[slot]).field_77994_a == 0)
/* 186 */         func_70299_a(slot, (ItemStack)null); 
/* 187 */       return itemstack1;
/*     */     } 
/*     */     
/* 190 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void ejectContents() {
/* 195 */     float f3 = 0.05F;
/*     */     
/* 197 */     Random rand = new Random();
/* 198 */     float f = rand.nextFloat() * 0.8F + 0.1F;
/* 199 */     float f1 = rand.nextFloat() * 2.0F + 0.4F;
/* 200 */     float f2 = rand.nextFloat() * 0.8F + 0.1F;
/*     */     
/* 202 */     for (int i = 0; i < func_70302_i_(); i++) {
/*     */       
/* 204 */       if (this.storage[i] != null) {
/*     */         
/* 206 */         EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), this.storage[i]);
/* 207 */         entityitem.field_70159_w = ((float)rand.nextGaussian() * f3);
/* 208 */         entityitem.field_70181_x = ((float)rand.nextGaussian() * f3 + 0.2F);
/* 209 */         entityitem.field_70179_y = ((float)rand.nextGaussian() * f3);
/* 210 */         this.field_145850_b.func_72838_d((Entity)entityitem);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void ejectItem(ItemStack is) {
/* 217 */     float f3 = 0.05F;
/*     */     
/* 219 */     Random rand = new Random();
/* 220 */     float f = rand.nextFloat() * 0.8F + 0.1F;
/* 221 */     float f1 = rand.nextFloat() * 2.0F + 0.4F;
/* 222 */     float f2 = rand.nextFloat() * 0.8F + 0.1F;
/*     */     
/* 224 */     if (this.storage[1] != null) {
/*     */       
/* 226 */       EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), is);
/* 227 */       entityitem.field_70159_w = ((float)rand.nextGaussian() * f3);
/* 228 */       entityitem.field_70181_x = ((float)rand.nextGaussian() * f3 + 0.05F);
/* 229 */       entityitem.field_70179_y = ((float)rand.nextGaussian() * f3);
/* 230 */       this.field_145850_b.func_72838_d((Entity)entityitem);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70302_i_() {
/* 237 */     return this.storage.length;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70301_a(int slot) {
/* 243 */     return this.storage[slot];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70299_a(int slot, ItemStack is) {
/* 249 */     this.storage[slot] = is;
/* 250 */     if (is != null && is.field_77994_a > func_70297_j_()) {
/* 251 */       is.field_77994_a = func_70297_j_();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_145825_b() {
/* 257 */     return "Quern";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70297_j_() {
/* 263 */     return 64;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70300_a(EntityPlayer player) {
/* 269 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70295_k_() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70305_f() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70304_b(int slot) {
/* 285 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_145818_k_() {
/* 291 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_94041_b(int slot, ItemStack is) {
/* 297 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbttagcompound) {
/* 303 */     super.func_145839_a(nbttagcompound);
/* 304 */     NBTTagList nbttaglist = nbttagcompound.func_150295_c("Items", 10);
/* 305 */     this.storage = new ItemStack[func_70302_i_()];
/* 306 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*     */       
/* 308 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 309 */       byte byte0 = nbttagcompound1.func_74771_c("Slot");
/* 310 */       if (byte0 >= 0 && byte0 < this.storage.length)
/* 311 */         this.storage[byte0] = ItemStack.func_77949_a(nbttagcompound1); 
/*     */     } 
/* 313 */     this.hasQuern = nbttagcompound.func_74767_n("hasQuern");
/* 314 */     this.shouldRotate = nbttagcompound.func_74767_n("shouldRotate");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbttagcompound) {
/* 320 */     super.func_145841_b(nbttagcompound);
/* 321 */     NBTTagList nbttaglist = new NBTTagList();
/* 322 */     for (int i = 0; i < this.storage.length; i++) {
/*     */       
/* 324 */       if (this.storage[i] != null) {
/*     */         
/* 326 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 327 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 328 */         this.storage[i].func_77955_b(nbttagcompound1);
/* 329 */         nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
/*     */       } 
/*     */     } 
/* 332 */     nbttagcompound.func_74782_a("Items", (NBTBase)nbttaglist);
/* 333 */     nbttagcompound.func_74757_a("hasQuern", this.hasQuern);
/* 334 */     nbttagcompound.func_74757_a("shouldRotate", this.shouldRotate);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Packet func_145844_m() {
/* 340 */     NBTTagCompound nbt = new NBTTagCompound();
/* 341 */     func_145841_b(nbt);
/* 342 */     return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbt);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
/* 348 */     func_145839_a(pkt.func_148857_g());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleInitPacket(NBTTagCompound nbt) {
/* 354 */     this.hasQuern = nbt.func_74767_n("hasQuern");
/* 355 */     this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleDataPacket(NBTTagCompound nbt) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createDataNBT(NBTTagCompound nbt) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createInitNBT(NBTTagCompound nbt) {
/* 373 */     nbt.func_74757_a("hasQuern", this.hasQuern);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEQuern.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */