/*     */ package com.bioxx.tfc.TileEntities;
/*     */ 
/*     */ import com.bioxx.tfc.Core.Metal.Alloy;
/*     */ import com.bioxx.tfc.Core.Metal.AlloyManager;
/*     */ import com.bioxx.tfc.Core.Metal.AlloyMetal;
/*     */ import com.bioxx.tfc.Core.Metal.MetalPair;
/*     */ import com.bioxx.tfc.Core.Metal.MetalRegistry;
/*     */ import com.bioxx.tfc.Core.TFC_Climate;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Handlers.Network.AbstractPacket;
/*     */ import com.bioxx.tfc.api.Constant.Global;
/*     */ import com.bioxx.tfc.api.Interfaces.ISmeltable;
/*     */ import com.bioxx.tfc.api.Metal;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import com.bioxx.tfc.api.TFC_ItemHeat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ 
/*     */ public class TECrucible
/*     */   extends NetworkTileEntity
/*     */   implements IInventory {
/*  34 */   public Map<String, MetalPair> metals = new HashMap<>();
/*     */   
/*     */   public Alloy currentAlloy;
/*     */   public int temperature;
/*     */   public ItemStack[] storage;
/*     */   public byte inputTick;
/*     */   public byte outputTick;
/*     */   public byte tempTick;
/*     */   private int cookDelay;
/*     */   public static final int MAX_UNITS = 3000;
/*     */   
/*     */   public TECrucible() {
/*  46 */     this.storage = new ItemStack[2];
/*  47 */     this.broadcastRange = 5;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbt) {
/*  53 */     super.func_145841_b(nbt);
/*     */     
/*  55 */     nbt.func_74768_a("temp", this.temperature);
/*     */     
/*  57 */     NBTTagList nbttaglist = new NBTTagList();
/*  58 */     Iterator<MetalPair> iter = this.metals.values().iterator();
/*  59 */     while (iter.hasNext()) {
/*     */       
/*  61 */       MetalPair m = iter.next();
/*  62 */       if (m != null) {
/*     */         
/*  64 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*  65 */         nbttagcompound1.func_74768_a("ID", Item.func_150891_b(m.type.ingot));
/*  66 */         nbttagcompound1.func_74776_a("AmountF", m.amount);
/*  67 */         nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
/*     */       } 
/*     */     } 
/*  70 */     nbt.func_74782_a("Metals", (NBTBase)nbttaglist);
/*     */     
/*  72 */     nbttaglist = new NBTTagList();
/*  73 */     for (int i = 0; i < this.storage.length; i++) {
/*     */       
/*  75 */       if (this.storage[i] != null) {
/*     */         
/*  77 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*  78 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/*  79 */         this.storage[i].func_77955_b(nbttagcompound1);
/*  80 */         nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
/*     */       } 
/*     */     } 
/*  83 */     nbt.func_74782_a("Items", (NBTBase)nbttaglist);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbt) {
/*  89 */     super.func_145839_a(nbt);
/*  90 */     readFromItemNBT(nbt);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readFromItemNBT(NBTTagCompound nbt) {
/*  95 */     this.temperature = nbt.func_74762_e("temp");
/*  96 */     NBTTagList nbttaglist = nbt.func_150295_c("Metals", 10);
/*     */     int i;
/*  98 */     for (i = 0; i < nbttaglist.func_74745_c(); i++) {
/*     */       
/* 100 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 101 */       int id = nbttagcompound1.func_74762_e("ID");
/* 102 */       float amount = nbttagcompound1.func_74765_d("Amount");
/*     */       
/* 104 */       float amountF = amount + nbttagcompound1.func_74760_g("AmountF");
/* 105 */       addMetal(MetalRegistry.instance.getMetalFromItem(Item.func_150899_d(id)), amountF);
/*     */     } 
/*     */     
/* 108 */     nbttaglist = nbt.func_150295_c("Items", 10);
/* 109 */     this.storage = new ItemStack[func_70302_i_()];
/* 110 */     for (i = 0; i < nbttaglist.func_74745_c(); i++) {
/*     */       
/* 112 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 113 */       byte byte0 = nbttagcompound1.func_74771_c("Slot");
/* 114 */       if (byte0 >= 0 && byte0 < this.storage.length) {
/* 115 */         this.storage[byte0] = ItemStack.func_77949_a(nbttagcompound1);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_145845_h() {
/* 122 */     if (!this.field_145850_b.field_72995_K) {
/*     */       
/* 124 */       this.inputTick = (byte)(this.inputTick + 1);
/* 125 */       this.outputTick = (byte)(this.outputTick + 1);
/* 126 */       this.tempTick = (byte)(this.tempTick + 1);
/*     */       
/* 128 */       if (this.cookDelay > 0) {
/* 129 */         this.cookDelay--;
/*     */       }
/*     */       
/* 132 */       if (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) == TFCBlocks.forge) {
/*     */         
/* 134 */         TEForge te = (TEForge)this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
/* 135 */         if (te.fireTemp >= 1.0F && TFCOptions.enableDebugMode) {
/* 136 */           this.temperature = 2000;
/* 137 */         } else if (te.fireTemp > this.temperature) {
/* 138 */           this.temperature++;
/*     */         } 
/* 140 */       }  if (this.tempTick > 22) {
/*     */         
/* 142 */         this.tempTick = 0;
/* 143 */         if (this.temperature > TFC_Climate.getHeightAdjustedTemp(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e)) {
/* 144 */           this.temperature--;
/*     */         }
/*     */       } 
/* 147 */       ItemStack stackToSmelt = this.storage[0];
/* 148 */       if (stackToSmelt != null) {
/*     */         
/* 150 */         Item itemToSmelt = stackToSmelt.func_77973_b();
/* 151 */         int newDamage = stackToSmelt.func_77960_j() + 1;
/* 152 */         int maxDamage = stackToSmelt.func_77958_k() - 1;
/*     */         
/* 154 */         if (itemToSmelt instanceof com.bioxx.tfc.Items.ItemMeltedMetal && TFC_ItemHeat.getIsLiquid(this.storage[0]).booleanValue()) {
/*     */           
/* 156 */           if (this.inputTick > 10)
/*     */           {
/* 158 */             Metal inputMetal = MetalRegistry.instance.getMetalFromItem(itemToSmelt);
/*     */             
/* 160 */             if (this.currentAlloy != null && this.currentAlloy.outputType != null && itemToSmelt == this.currentAlloy.outputType.meltedItem) {
/*     */               
/* 162 */               addMetal(inputMetal, 1.0F);
/* 163 */               if (newDamage >= maxDamage)
/*     */               {
/* 165 */                 this.storage[0] = new ItemStack(TFCItems.ceramicMold, 1, 1);
/*     */               }
/*     */               else
/*     */               {
/* 169 */                 stackToSmelt.func_77964_b(newDamage);
/*     */               }
/*     */             
/*     */             } else {
/*     */               
/* 174 */               addMetal(inputMetal, 1.0F);
/* 175 */               if (newDamage >= maxDamage) {
/*     */                 
/* 177 */                 this.storage[0] = new ItemStack(TFCItems.ceramicMold, 1, 1);
/*     */               }
/*     */               else {
/*     */                 
/* 181 */                 stackToSmelt.func_77964_b(newDamage);
/*     */               } 
/*     */             } 
/* 184 */             this.inputTick = 0;
/* 185 */             updateGui((byte)0);
/*     */           }
/*     */         
/* 188 */         } else if (itemToSmelt instanceof ISmeltable && ((ISmeltable)itemToSmelt)
/* 189 */           .isSmeltable(stackToSmelt) && 
/* 190 */           !TFC_Core.isOreIron(stackToSmelt) && this.temperature >= 
/* 191 */           TFC_ItemHeat.isCookable(stackToSmelt) && this.cookDelay == 0) {
/*     */           
/* 193 */           Metal mType = ((ISmeltable)itemToSmelt).getMetalType(stackToSmelt);
/* 194 */           if (addMetal(mType, ((ISmeltable)itemToSmelt).getMetalReturnAmount(stackToSmelt))) {
/*     */             
/* 196 */             this.temperature = (int)(this.temperature * 0.9F);
/* 197 */             this.cookDelay = 40;
/* 198 */             if (stackToSmelt.field_77994_a <= 1) {
/* 199 */               this.storage[0] = null;
/*     */             } else {
/* 201 */               (this.storage[0]).field_77994_a--;
/* 202 */             }  updateGui((byte)0);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 207 */       if (this.currentAlloy != null && this.storage[1] != null && this.currentAlloy.outputType != null && this.outputTick >= 2 && this.temperature >= 
/*     */ 
/*     */ 
/*     */         
/* 211 */         TFC_ItemHeat.isCookable(this.currentAlloy.outputType)) {
/*     */         
/* 213 */         if (this.storage[1].func_77973_b() == TFCItems.ceramicMold) {
/*     */           
/* 215 */           this.storage[1] = new ItemStack(this.currentAlloy.outputType.meltedItem, 1, 99);
/* 216 */           TFC_ItemHeat.setTemp(this.storage[1], this.temperature);
/*     */           
/* 218 */           drainOutput(1.0F);
/* 219 */           updateGui((byte)1);
/*     */         }
/* 221 */         else if (this.storage[1].func_77973_b() == this.currentAlloy.outputType.meltedItem && this.storage[1].func_77960_j() > 0) {
/*     */           
/* 223 */           this.storage[1].func_77964_b(this.storage[1].func_77960_j() - 1);
/* 224 */           float inTemp = TFC_ItemHeat.getTemp(this.storage[1]);
/* 225 */           float temp = (this.temperature - inTemp) / 2.0F;
/* 226 */           TFC_ItemHeat.setTemp(this.storage[1], inTemp + temp);
/*     */           
/* 228 */           drainOutput(1.0F);
/* 229 */           (this.storage[1]).field_77994_a = 1;
/* 230 */           updateGui((byte)1);
/*     */         } 
/* 232 */         this.outputTick = 0;
/*     */       } 
/*     */       
/* 235 */       if (this.currentAlloy != null && getTotalMetal() < 1.0F) {
/*     */         
/* 237 */         this.metals = new HashMap<>();
/* 238 */         updateCurrentAlloy();
/* 239 */         updateGui((byte)2);
/* 240 */         this.currentAlloy = null;
/*     */       } 
/*     */       
/* 243 */       if (this.storage[1] != null && (this.storage[1]).field_77994_a <= 0)
/* 244 */         (this.storage[1]).field_77994_a = 1; 
/* 245 */       if (this.inputTick > 10)
/* 246 */         this.inputTick = 0; 
/* 247 */       if (this.outputTick >= 2) {
/* 248 */         this.outputTick = 0;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean drainOutput(float amount) {
/* 254 */     if (this.metals != null && this.metals.values().size() > 0) {
/*     */       
/* 256 */       for (MetalPair am : this.metals.values()) {
/*     */         
/* 258 */         float percent = this.currentAlloy.getPercentForMetal(am.type) / 100.0F;
/* 259 */         am.amount -= amount * percent;
/*     */       } 
/* 261 */       updateCurrentAlloy();
/*     */     } 
/* 263 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addMetal(Metal m, float amt) {
/* 268 */     if (getTotalMetal() + amt <= 3000.0F && m.name != null && !"Unknown".equals(m.name)) {
/*     */       
/* 270 */       if (this.metals.containsKey(m.name)) {
/* 271 */         ((MetalPair)this.metals.get(m.name)).amount += amt;
/*     */       } else {
/* 273 */         this.metals.put(m.name, new MetalPair(m, amt));
/*     */       } 
/* 275 */       updateCurrentAlloy();
/* 276 */       return true;
/*     */     } 
/* 278 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getTotalMetal() {
/* 283 */     Iterator<MetalPair> iter = this.metals.values().iterator();
/* 284 */     float totalAmount = 0.0F;
/* 285 */     while (iter.hasNext()) {
/*     */       
/* 287 */       MetalPair m = iter.next();
/* 288 */       if (m != null)
/* 289 */         totalAmount += m.amount; 
/*     */     } 
/* 291 */     return totalAmount;
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateCurrentAlloy() {
/* 296 */     List<AlloyMetal> a = new ArrayList<>();
/* 297 */     Iterator<MetalPair> iter = this.metals.values().iterator();
/* 298 */     float totalAmount = getTotalMetal();
/* 299 */     iter = this.metals.values().iterator();
/* 300 */     while (iter.hasNext()) {
/*     */       
/* 302 */       MetalPair m = iter.next();
/* 303 */       if (m != null) {
/* 304 */         a.add(new AlloyMetal(m.type, m.amount / totalAmount * 100.0F));
/*     */       }
/*     */     } 
/* 307 */     Metal match = AlloyManager.INSTANCE.matchesAlloy(a, Alloy.EnumTier.TierV);
/* 308 */     if (match != null) {
/*     */       
/* 310 */       this.currentAlloy = new Alloy(match, totalAmount);
/* 311 */       this.currentAlloy.alloyIngred = a;
/*     */     }
/*     */     else {
/*     */       
/* 315 */       this.currentAlloy = new Alloy(Global.UNKNOWN, totalAmount);
/* 316 */       this.currentAlloy.alloyIngred = a;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70302_i_() {
/* 323 */     return 2;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70301_a(int i) {
/* 329 */     return this.storage[i];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70298_a(int i, int j) {
/* 335 */     if (this.storage[i] != null) {
/*     */       
/* 337 */       if ((this.storage[i]).field_77994_a <= j) {
/*     */         
/* 339 */         ItemStack itemstack = this.storage[i];
/* 340 */         this.storage[i] = null;
/* 341 */         return itemstack;
/*     */       } 
/* 343 */       ItemStack itemstack1 = this.storage[i].func_77979_a(j);
/* 344 */       if ((this.storage[i]).field_77994_a == 0)
/* 345 */         this.storage[i] = null; 
/* 346 */       return itemstack1;
/*     */     } 
/*     */ 
/*     */     
/* 350 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70304_b(int i) {
/* 357 */     return this.storage[i];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70299_a(int i, ItemStack itemstack) {
/* 363 */     this.storage[i] = itemstack;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String func_145825_b() {
/* 369 */     return "Crucible";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_145818_k_() {
/* 375 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70297_j_() {
/* 381 */     return 64;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70300_a(EntityPlayer entityplayer) {
/* 387 */     return true;
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
/*     */   public boolean func_94041_b(int i, ItemStack itemstack) {
/* 403 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getOutCountScaled(int length) {
/* 408 */     if (this.currentAlloy != null) {
/* 409 */       return (int)this.currentAlloy.outputAmount * length / 3000;
/*     */     }
/* 411 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTemperatureScaled(int s) {
/* 416 */     return this.temperature * s / 2500;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateGui(byte action) {
/* 421 */     if (!this.field_145850_b.field_72995_K) {
/*     */       
/* 423 */       NBTTagCompound nbt = new NBTTagCompound();
/* 424 */       nbt.func_74774_a("action", action);
/* 425 */       if (this.currentAlloy != null) {
/* 426 */         if (action == 0) {
/* 427 */           this.currentAlloy.toNBT(nbt);
/*     */         }
/* 429 */         else if (action == 1 && this.currentAlloy != null) {
/* 430 */           nbt.func_74776_a("outputAmount", this.currentAlloy.outputAmount);
/*     */         } 
/*     */       }
/* 433 */       broadcastPacketInRange((AbstractPacket)createDataPacket(nbt));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleInitPacket(NBTTagCompound nbt) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleDataPacket(NBTTagCompound nbt) {
/* 444 */     byte action = nbt.func_74771_c("action");
/* 445 */     if (action == 0) {
/* 446 */       this.currentAlloy = (new Alloy()).fromNBT(nbt);
/* 447 */     } else if (action == 1 && this.currentAlloy != null) {
/*     */       
/* 449 */       this.currentAlloy.outputAmount = nbt.func_74760_g("outputAmount");
/*     */     }
/* 451 */     else if (action == 2) {
/* 452 */       this.currentAlloy = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void createInitNBT(NBTTagCompound nbt) {}
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TECrucible.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */