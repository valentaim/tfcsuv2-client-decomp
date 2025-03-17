/*     */ package com.bioxx.tfc.TileEntities;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.api.Enums.EnumFuelMaterial;
/*     */ import com.bioxx.tfc.api.Events.ItemCookEvent;
/*     */ import com.bioxx.tfc.api.Food;
/*     */ import com.bioxx.tfc.api.HeatIndex;
/*     */ import com.bioxx.tfc.api.HeatRegistry;
/*     */ import com.bioxx.tfc.api.Interfaces.ICookableFood;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import com.bioxx.tfc.api.TFC_ItemHeat;
/*     */ import com.bioxx.tfc.api.TileEntities.TEFireEntity;
/*     */ import cpw.mods.fml.common.eventhandler.Event;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TEFirepit
/*     */   extends TEFireEntity
/*     */   implements IInventory
/*     */ {
/*  45 */   public ItemStack[] fireItemStacks = new ItemStack[11];
/*     */   
/*     */   public boolean hasCookingPot = true;
/*     */   
/*     */   public int smokeTimer;
/*     */ 
/*     */   
/*     */   public void func_70305_f() {}
/*     */ 
/*     */   
/*     */   public void combineMetals(ItemStack inputItem, ItemStack destItem) {
/*  56 */     int d1 = 100 - inputItem.func_77960_j();
/*  57 */     int d2 = 100 - destItem.func_77960_j();
/*     */     
/*  59 */     destItem.func_77964_b(100 - d1 + d2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void cookItem() {
/*  64 */     HeatRegistry manager = HeatRegistry.getInstance();
/*  65 */     Random r = new Random();
/*  66 */     if (this.fireItemStacks[1] != null) {
/*     */       
/*  68 */       HeatIndex index = manager.findMatchingIndex(this.fireItemStacks[1]);
/*  69 */       if (index != null && TFC_ItemHeat.getTemp(this.fireItemStacks[1]) > index.meltTemp) {
/*     */         
/*  71 */         ItemStack output = index.getOutput(this.fireItemStacks[1], r);
/*  72 */         ItemCookEvent eventMelt = new ItemCookEvent(this.fireItemStacks[1], output, (TileEntity)this);
/*  73 */         MinecraftForge.EVENT_BUS.post((Event)eventMelt);
/*  74 */         output = eventMelt.result;
/*  75 */         int damage = 0;
/*  76 */         ItemStack mold = null;
/*  77 */         if (output != null) {
/*     */           
/*  79 */           damage = output.func_77960_j();
/*  80 */           if (output.func_77973_b() == this.fireItemStacks[1].func_77973_b()) {
/*  81 */             damage = this.fireItemStacks[1].func_77960_j();
/*     */           }
/*     */           
/*  84 */           if (this.fireItemStacks[1].func_77973_b() instanceof com.bioxx.tfc.Items.ItemMeltedMetal) {
/*     */ 
/*     */             
/*  87 */             if (this.fireItemStacks[7] == null && this.fireItemStacks[8] == null) {
/*     */               
/*  89 */               this.fireItemStacks[7] = this.fireItemStacks[1].func_77946_l();
/*  90 */               this.fireItemStacks[1] = null;
/*     */               
/*     */               return;
/*     */             } 
/*  94 */             if (this.fireItemStacks[7] != null && this.fireItemStacks[7].func_77973_b() != TFCItems.ceramicMold && (this.fireItemStacks[7]
/*  95 */               .func_77973_b() != this.fireItemStacks[1].func_77973_b() || this.fireItemStacks[7].func_77960_j() == 0))
/*     */             {
/*  97 */               if (this.fireItemStacks[8] == null) {
/*     */                 
/*  99 */                 this.fireItemStacks[8] = this.fireItemStacks[1].func_77946_l();
/* 100 */                 this.fireItemStacks[1] = null;
/*     */                 return;
/*     */               } 
/*     */             }
/* 104 */             mold = new ItemStack(TFCItems.ceramicMold, 1);
/* 105 */             mold.field_77994_a = 1;
/* 106 */             mold.func_77964_b(1);
/*     */           } 
/*     */         } 
/*     */         
/* 110 */         float temp = TFC_ItemHeat.getTemp(this.fireItemStacks[1]);
/* 111 */         this.fireItemStacks[1] = index.getMorph();
/* 112 */         if (this.fireItemStacks[1] != null && manager.findMatchingIndex(this.fireItemStacks[1]) != null)
/*     */         {
/*     */           
/* 115 */           TFC_ItemHeat.setTemp(this.fireItemStacks[1], temp);
/*     */         }
/*     */ 
/*     */         
/* 119 */         if (output != null && output.func_77973_b() instanceof com.bioxx.tfc.Items.ItemMeltedMetal) {
/*     */           
/* 121 */           int leftover = 0;
/* 122 */           boolean addLeftover = false;
/* 123 */           int fromSide = 0;
/* 124 */           if (this.fireItemStacks[7] != null && output.func_77973_b() == this.fireItemStacks[7].func_77973_b() && this.fireItemStacks[7].func_77960_j() > 0) {
/*     */             
/* 126 */             int amt1 = 100 - damage;
/* 127 */             int amt2 = 100 - this.fireItemStacks[7].func_77960_j();
/* 128 */             int amt3 = amt1 + amt2;
/* 129 */             leftover = amt3 - 100;
/* 130 */             if (leftover > 0)
/* 131 */               addLeftover = true; 
/* 132 */             int amt4 = 100 - amt3;
/* 133 */             if (amt4 < 0)
/* 134 */               amt4 = 0; 
/* 135 */             this.fireItemStacks[7] = output.func_77946_l();
/* 136 */             this.fireItemStacks[7].func_77964_b(amt4);
/*     */             
/* 138 */             TFC_ItemHeat.setTemp(this.fireItemStacks[7], temp);
/*     */             
/* 140 */             if (this.fireItemStacks[1] == null && mold != null) {
/* 141 */               this.fireItemStacks[1] = mold;
/*     */             }
/* 143 */           } else if (this.fireItemStacks[8] != null && output.func_77973_b() == this.fireItemStacks[8].func_77973_b() && this.fireItemStacks[8].func_77960_j() > 0) {
/*     */             
/* 145 */             int amt1 = 100 - damage;
/* 146 */             int amt2 = 100 - this.fireItemStacks[8].func_77960_j();
/* 147 */             int amt3 = amt1 + amt2;
/* 148 */             leftover = amt3 - 100;
/* 149 */             if (leftover > 0)
/* 150 */               addLeftover = true; 
/* 151 */             fromSide = 1;
/* 152 */             int amt4 = 100 - amt3;
/* 153 */             if (amt4 < 0)
/* 154 */               amt4 = 0; 
/* 155 */             this.fireItemStacks[8] = output.func_77946_l();
/* 156 */             this.fireItemStacks[8].func_77964_b(amt4);
/*     */             
/* 158 */             TFC_ItemHeat.setTemp(this.fireItemStacks[8], temp);
/*     */             
/* 160 */             if (this.fireItemStacks[1] == null && mold != null) {
/* 161 */               this.fireItemStacks[1] = mold;
/*     */             }
/* 163 */           } else if (this.fireItemStacks[7] != null && this.fireItemStacks[7].func_77973_b() == TFCItems.ceramicMold) {
/*     */             
/* 165 */             this.fireItemStacks[7] = output.func_77946_l();
/* 166 */             this.fireItemStacks[7].func_77964_b(damage);
/*     */             
/* 168 */             TFC_ItemHeat.setTemp(this.fireItemStacks[7], temp);
/*     */           }
/* 170 */           else if (this.fireItemStacks[8] != null && this.fireItemStacks[8].func_77973_b() == TFCItems.ceramicMold) {
/*     */             
/* 172 */             this.fireItemStacks[8] = output.func_77946_l();
/* 173 */             this.fireItemStacks[8].func_77964_b(damage);
/*     */             
/* 175 */             TFC_ItemHeat.setTemp(this.fireItemStacks[8], temp);
/*     */           } 
/*     */           
/* 178 */           if (addLeftover) {
/*     */             
/* 180 */             int dest = (fromSide == 1) ? 7 : 8;
/* 181 */             if (this.fireItemStacks[dest] != null && output.func_77973_b() == this.fireItemStacks[dest].func_77973_b() && this.fireItemStacks[dest].func_77960_j() > 0)
/*     */             {
/* 183 */               int amt1 = 100 - leftover;
/* 184 */               int amt2 = 100 - this.fireItemStacks[dest].func_77960_j();
/* 185 */               int amt3 = amt1 + amt2;
/* 186 */               int amt4 = 100 - amt3;
/* 187 */               if (amt4 < 0)
/* 188 */                 amt4 = 0; 
/* 189 */               this.fireItemStacks[dest] = output.func_77946_l();
/* 190 */               this.fireItemStacks[dest].func_77964_b(amt4);
/*     */               
/* 192 */               TFC_ItemHeat.setTemp(this.fireItemStacks[dest], temp);
/*     */             }
/* 194 */             else if (this.fireItemStacks[dest] != null && this.fireItemStacks[dest].func_77973_b() == TFCItems.ceramicMold)
/*     */             {
/* 196 */               this.fireItemStacks[dest] = output.func_77946_l();
/* 197 */               this.fireItemStacks[dest].func_77964_b(100 - leftover);
/* 198 */               TFC_ItemHeat.setTemp(this.fireItemStacks[dest], temp);
/*     */             }
/*     */           
/*     */           } 
/* 202 */         } else if (output != null) {
/*     */           
/* 204 */           if (this.fireItemStacks[7] != null && this.fireItemStacks[7]
/* 205 */             .func_77973_b() == output.func_77973_b() && (this.fireItemStacks[7]).field_77994_a + output.field_77994_a <= this.fireItemStacks[7]
/* 206 */             .func_77976_d()) {
/*     */             
/* 208 */             (this.fireItemStacks[7]).field_77994_a += output.field_77994_a;
/*     */           }
/* 210 */           else if (this.fireItemStacks[8] != null && this.fireItemStacks[8]
/* 211 */             .func_77973_b() == output.func_77973_b() && (this.fireItemStacks[8]).field_77994_a + output.field_77994_a <= this.fireItemStacks[8]
/* 212 */             .func_77976_d()) {
/*     */             
/* 214 */             (this.fireItemStacks[8]).field_77994_a += output.field_77994_a;
/*     */           }
/* 216 */           else if (this.fireItemStacks[7] == null) {
/*     */             
/* 218 */             this.fireItemStacks[7] = output.func_77946_l();
/*     */           }
/* 220 */           else if (this.fireItemStacks[8] == null) {
/*     */             
/* 222 */             this.fireItemStacks[8] = output.func_77946_l();
/*     */           }
/* 224 */           else if (((this.fireItemStacks[7]).field_77994_a == this.fireItemStacks[7].func_77976_d() && (this.fireItemStacks[8]).field_77994_a == this.fireItemStacks[8]
/* 225 */             .func_77976_d()) || (this.fireItemStacks[7]
/* 226 */             .func_77973_b() != output.func_77973_b() && this.fireItemStacks[8].func_77973_b() != output.func_77973_b()) || ((this.fireItemStacks[7]).field_77994_a == this.fireItemStacks[7]
/* 227 */             .func_77976_d() && this.fireItemStacks[8].func_77973_b() != output.func_77973_b()) || (this.fireItemStacks[7]
/* 228 */             .func_77973_b() != output.func_77973_b() && (this.fireItemStacks[8]).field_77994_a == this.fireItemStacks[8].func_77976_d())) {
/*     */             
/* 230 */             this.fireItemStacks[1] = output.func_77946_l();
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70298_a(int slot, int amount) {
/* 240 */     if (this.fireItemStacks[slot] != null) {
/*     */       
/* 242 */       if ((this.fireItemStacks[slot]).field_77994_a <= amount) {
/*     */         
/* 244 */         ItemStack itemstack = this.fireItemStacks[slot];
/* 245 */         this.fireItemStacks[slot] = null;
/* 246 */         return itemstack;
/*     */       } 
/* 248 */       ItemStack itemstack1 = this.fireItemStacks[slot].func_77979_a(amount);
/* 249 */       if ((this.fireItemStacks[slot]).field_77994_a == 0)
/* 250 */         this.fireItemStacks[slot] = null; 
/* 251 */       return itemstack1;
/*     */     } 
/*     */     
/* 254 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void ejectContents() {
/* 259 */     float f3 = 0.05F;
/*     */     
/* 261 */     Random rand = new Random();
/* 262 */     float f = rand.nextFloat() * 0.8F + 0.1F;
/* 263 */     float f1 = rand.nextFloat() * 0.8F + 0.3F;
/* 264 */     float f2 = rand.nextFloat() * 0.8F + 0.1F;
/*     */     
/* 266 */     for (int i = 0; i < func_70302_i_(); i++) {
/*     */       
/* 268 */       if (this.fireItemStacks[i] != null) {
/*     */         
/* 270 */         EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), this.fireItemStacks[i]);
/* 271 */         entityitem.field_70159_w = ((float)rand.nextGaussian() * f3);
/* 272 */         entityitem.field_70181_x = ((float)rand.nextGaussian() * f3 + 0.2F);
/* 273 */         entityitem.field_70179_y = ((float)rand.nextGaussian() * f3);
/* 274 */         this.field_145850_b.func_72838_d((Entity)entityitem);
/* 275 */         this.fireItemStacks[i] = null;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70297_j_() {
/* 283 */     return 64;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String func_145825_b() {
/* 289 */     return "Firepit";
/*     */   }
/*     */ 
/*     */   
/*     */   public float getOutput1Temp() {
/* 294 */     return TFC_ItemHeat.getTemp(this.fireItemStacks[7]);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getOutput2Temp() {
/* 299 */     return TFC_ItemHeat.getTemp(this.fireItemStacks[8]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70302_i_() {
/* 305 */     return this.fireItemStacks.length;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70301_a(int slot) {
/* 311 */     return this.fireItemStacks[slot];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70304_b(int slot) {
/* 317 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleFuelStack() {
/* 322 */     if (this.fireItemStacks[3] == null && this.fireItemStacks[0] != null) {
/*     */       
/* 324 */       this.fireItemStacks[3] = this.fireItemStacks[0];
/* 325 */       this.fireItemStacks[0] = null;
/*     */     } 
/* 327 */     if (this.fireItemStacks[4] == null && this.fireItemStacks[3] != null) {
/*     */       
/* 329 */       this.fireItemStacks[4] = this.fireItemStacks[3];
/* 330 */       this.fireItemStacks[3] = null;
/*     */     } 
/* 332 */     if (this.fireItemStacks[5] == null && this.fireItemStacks[4] != null) {
/*     */       
/* 334 */       this.fireItemStacks[5] = this.fireItemStacks[4];
/* 335 */       this.fireItemStacks[4] = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70300_a(EntityPlayer entityplayer) {
/* 342 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70295_k_() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70299_a(int slot, ItemStack is) {
/* 353 */     this.fireItemStacks[slot] = is;
/* 354 */     if (is != null && is.field_77994_a > func_70297_j_()) {
/* 355 */       is.field_77994_a = func_70297_j_();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void careForInventorySlot(ItemStack is) {
/* 361 */     if (is != null) {
/*     */       
/* 363 */       HeatRegistry manager = HeatRegistry.getInstance();
/* 364 */       HeatIndex index = manager.findMatchingIndex(is);
/*     */       
/* 366 */       if (index != null) {
/*     */         
/* 368 */         float temp = TFC_ItemHeat.getTemp(is);
/* 369 */         if (this.fuelTimeLeft > 0 && is.func_77973_b() instanceof ICookableFood) {
/*     */           
/* 371 */           float inc = Food.getCooked(is) + Math.min(this.fireTemp / 700.0F, 2.0F);
/* 372 */           Food.setCooked(is, inc);
/* 373 */           temp = inc;
/* 374 */           if (Food.isCooked(is))
/*     */           {
/* 376 */             int[] cookedTasteProfile = { 0, 0, 0, 0, 0 };
/*     */             
/* 378 */             Random r = new Random((((ICookableFood)is.func_77973_b()).getFoodID() + ((int)Food.getCooked(is) - 600) / 120));
/* 379 */             cookedTasteProfile[0] = r.nextInt(31) - 15;
/* 380 */             cookedTasteProfile[1] = r.nextInt(31) - 15;
/* 381 */             cookedTasteProfile[2] = r.nextInt(31) - 15;
/* 382 */             cookedTasteProfile[3] = r.nextInt(31) - 15;
/* 383 */             cookedTasteProfile[4] = r.nextInt(31) - 15;
/* 384 */             Food.setCookedProfile(is, cookedTasteProfile);
/* 385 */             Food.setFuelProfile(is, EnumFuelMaterial.getFuelProfile(this.fuelTasteProfile));
/*     */           }
/*     */         
/* 388 */         } else if (this.fireTemp > temp && index.hasOutput()) {
/*     */           
/* 390 */           temp += TFC_ItemHeat.getTempIncrease(is);
/*     */         } else {
/*     */           
/* 393 */           temp -= TFC_ItemHeat.getTempDecrease(is);
/* 394 */         }  TFC_ItemHeat.setTemp(is, temp);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145845_h() {
/* 403 */     if (!this.field_145850_b.field_72995_K) {
/*     */ 
/*     */       
/* 406 */       List<EntityItem> list = this.field_145850_b.func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), this.field_145848_d + 1.1D, (this.field_145849_e + 1)));
/*     */       
/* 408 */       if (list != null && !list.isEmpty() && this.fireItemStacks[0] == null)
/*     */       {
/*     */         
/* 411 */         for (Iterator<EntityItem> iterator = list.iterator(); iterator.hasNext(); ) {
/*     */           
/* 413 */           EntityItem entity = iterator.next();
/* 414 */           ItemStack is = entity.func_92059_d();
/* 415 */           Item item = is.func_77973_b();
/*     */           
/* 417 */           if (item == TFCItems.logs || item == Item.func_150898_a(TFCBlocks.peat)) {
/*     */             
/* 419 */             for (int c = 0; c < is.field_77994_a; c++) {
/*     */               
/* 421 */               if (this.fireItemStacks[0] == null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 427 */                 func_70299_a(0, new ItemStack(item, 1, is.func_77960_j()));
/* 428 */                 is.field_77994_a--;
/* 429 */                 handleFuelStack();
/*     */               } 
/*     */             } 
/*     */             
/* 433 */             if (is.field_77994_a == 0) {
/* 434 */               entity.func_70106_y();
/*     */             }
/*     */           } 
/*     */         } 
/*     */       }
/*     */       
/* 440 */       careForInventorySlot(this.fireItemStacks[1]);
/* 441 */       careForInventorySlot(this.fireItemStacks[7]);
/* 442 */       careForInventorySlot(this.fireItemStacks[8]);
/*     */       
/* 444 */       smokeFoods();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 450 */       cookItem();
/*     */ 
/*     */       
/* 453 */       handleFuelStack();
/*     */       
/* 455 */       if (this.fireTemp < 1.0F && this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e) != 0) {
/*     */         
/* 457 */         this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, 3);
/* 458 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */       }
/* 460 */       else if (this.fireTemp >= 1.0F && this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e) != 1) {
/*     */         
/* 462 */         this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, 3);
/* 463 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */       } 
/*     */ 
/*     */       
/* 467 */       if (this.fuelTimeLeft > 0 && this.fireTemp >= 1.0F) {
/*     */         
/* 469 */         if (this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e) != 2)
/*     */         {
/* 471 */           this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 2, 3);
/* 472 */           this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */         }
/*     */       
/* 475 */       } else if (this.fuelTimeLeft <= 0 && this.fireTemp >= 1.0F && this.fireItemStacks[5] != null && 
/* 476 */         !TFC_Core.isExposedToRain(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e)) {
/*     */         
/* 478 */         if (this.fireItemStacks[5] != null) {
/*     */           
/* 480 */           EnumFuelMaterial m = TFC_Core.getFuelMaterial(this.fireItemStacks[5]);
/* 481 */           this.fuelTasteProfile = m.ordinal();
/* 482 */           this.fireItemStacks[5] = null;
/* 483 */           this.fuelTimeLeft = m.burnTimeMax;
/* 484 */           this.fuelBurnTemp = m.burnTempMax;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 489 */       float desiredTemp = handleTemp();
/*     */       
/* 491 */       handleTempFlux(desiredTemp);
/*     */ 
/*     */       
/* 494 */       handleAirReduction();
/*     */ 
/*     */       
/* 497 */       if (this.fireItemStacks[7] != null)
/*     */       {
/* 499 */         if ((this.fireItemStacks[7]).field_77994_a <= 0) {
/* 500 */           (this.fireItemStacks[7]).field_77994_a = 1;
/*     */         }
/*     */       }
/* 503 */       if (this.fireItemStacks[8] != null)
/*     */       {
/* 505 */         if ((this.fireItemStacks[8]).field_77994_a <= 0) {
/* 506 */           (this.fireItemStacks[8]).field_77994_a = 1;
/*     */         }
/*     */       }
/* 509 */       if (this.fuelTimeLeft <= 0) {
/* 510 */         TFC_Core.handleItemTicking(this, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void smokeFoods() {
/* 516 */     if (this.fuelTimeLeft > 0) {
/*     */       
/* 518 */       this.smokeTimer++;
/* 519 */       if (this.smokeTimer > 1000) {
/*     */         
/* 521 */         this.smokeTimer = 0;
/* 522 */         smokeBlock(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
/* 523 */         smokeBlock(this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e);
/* 524 */         smokeBlock(this.field_145851_c - 1, this.field_145848_d + 1, this.field_145849_e);
/* 525 */         smokeBlock(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e + 1);
/* 526 */         smokeBlock(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e - 1);
/* 527 */         smokeBlock(this.field_145851_c, this.field_145848_d + 2, this.field_145849_e);
/* 528 */         smokeBlock(this.field_145851_c + 1, this.field_145848_d + 2, this.field_145849_e);
/* 529 */         smokeBlock(this.field_145851_c - 1, this.field_145848_d + 2, this.field_145849_e);
/* 530 */         smokeBlock(this.field_145851_c, this.field_145848_d + 2, this.field_145849_e + 1);
/* 531 */         smokeBlock(this.field_145851_c, this.field_145848_d + 2, this.field_145849_e - 1);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void smokeBlock(int x, int y, int z) {
/* 537 */     if (this.field_145850_b.func_72899_e(x, y, z) && this.field_145850_b.func_147439_a(x, y, z) == TFCBlocks.smokeRack && this.field_145850_b
/* 538 */       .func_147438_o(x, y, z) instanceof TESmokeRack) {
/*     */       
/* 540 */       boolean broadcast = false;
/* 541 */       TESmokeRack te = (TESmokeRack)this.field_145850_b.func_147438_o(x, y, z);
/* 542 */       te.lastSmokedTime = (int)TFC_Time.getTotalHours();
/*     */       
/* 544 */       for (int i = 0; i < te.storage.length; i++) {
/*     */         
/* 546 */         ItemStack is = te.func_70301_a(i);
/* 547 */         if (is != null)
/*     */         {
/* 549 */           if (Food.getSmokeCounter(is) < 12) {
/*     */ 
/*     */             
/* 552 */             Food.setSmokeCounter(is, Food.getSmokeCounter(is) + 1);
/*     */           }
/*     */           else {
/*     */             
/* 556 */             Food.setFuelProfile(is, EnumFuelMaterial.getFuelProfile(this.fuelTasteProfile));
/* 557 */             broadcast = true;
/*     */           } 
/*     */         }
/*     */       } 
/*     */       
/* 562 */       if (broadcast) {
/* 563 */         te.broadcastPacketInRange();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbttagcompound) {
/* 570 */     super.func_145839_a(nbttagcompound);
/* 571 */     NBTTagList nbttaglist = nbttagcompound.func_150295_c("Items", 10);
/* 572 */     this.fireItemStacks = new ItemStack[func_70302_i_()];
/* 573 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*     */       
/* 575 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 576 */       byte byte0 = nbttagcompound1.func_74771_c("Slot");
/* 577 */       if (byte0 >= 0 && byte0 < this.fireItemStacks.length) {
/* 578 */         this.fireItemStacks[byte0] = ItemStack.func_77949_a(nbttagcompound1);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbttagcompound) {
/* 585 */     super.func_145841_b(nbttagcompound);
/* 586 */     NBTTagList nbttaglist = new NBTTagList();
/* 587 */     for (int i = 0; i < this.fireItemStacks.length; i++) {
/*     */       
/* 589 */       if (this.fireItemStacks[i] != null) {
/*     */         
/* 591 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 592 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 593 */         this.fireItemStacks[i].func_77955_b(nbttagcompound1);
/* 594 */         nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
/*     */       } 
/*     */     } 
/* 597 */     nbttagcompound.func_74782_a("Items", (NBTBase)nbttaglist);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_145818_k_() {
/* 603 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_94041_b(int slot, ItemStack is) {
/* 609 */     return false;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void generateSmoke() {}
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEFirepit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */