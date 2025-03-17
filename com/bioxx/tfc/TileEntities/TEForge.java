/*     */ package com.bioxx.tfc.TileEntities;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.api.Enums.EnumFuelMaterial;
/*     */ import com.bioxx.tfc.api.HeatIndex;
/*     */ import com.bioxx.tfc.api.HeatRegistry;
/*     */ import com.bioxx.tfc.api.Interfaces.ISmeltable;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import com.bioxx.tfc.api.TFC_ItemHeat;
/*     */ import com.bioxx.tfc.api.TileEntities.TEFireEntity;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TEForge
/*     */   extends TEFireEntity
/*     */   implements IInventory
/*     */ {
/*     */   public boolean isSmokeStackValid = false;
/*  38 */   public ItemStack[] fireItemStacks = new ItemStack[14];
/*     */   
/*     */   private int smokeTimer;
/*     */ 
/*     */   
/*     */   private boolean validateSmokeStack() {
/*  44 */     if (!TFC_Core.isExposedToRain(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e))
/*  45 */       return true; 
/*  46 */     if (checkChimney(this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e))
/*  47 */       return true; 
/*  48 */     if (checkChimney(this.field_145851_c - 1, this.field_145848_d + 1, this.field_145849_e))
/*  49 */       return true; 
/*  50 */     if (checkChimney(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e + 1))
/*  51 */       return true; 
/*  52 */     if (checkChimney(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e - 1))
/*  53 */       return true; 
/*  54 */     if (notOpaque(this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e) && checkChimney(this.field_145851_c + 2, this.field_145848_d + 1, this.field_145849_e))
/*  55 */       return true; 
/*  56 */     if (notOpaque(this.field_145851_c - 1, this.field_145848_d + 1, this.field_145849_e) && checkChimney(this.field_145851_c - 2, this.field_145848_d + 1, this.field_145849_e))
/*  57 */       return true; 
/*  58 */     if (notOpaque(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e + 1) && checkChimney(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e + 2)) {
/*  59 */       return true;
/*     */     }
/*  61 */     return (notOpaque(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e - 1) && checkChimney(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e - 2));
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean checkChimney(int x, int y, int z) {
/*  66 */     return (notOpaque(x, y, z) && this.field_145850_b.func_72937_j(x, y, z));
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean notOpaque(int x, int y, int z) {
/*  71 */     return (this.field_145850_b.func_72899_e(x, y, z) && !this.field_145850_b.func_147439_a(x, y, z).func_149662_c());
/*     */   }
/*     */ 
/*     */   
/*     */   private void genSmokeRoot(int x, int y, int z) {
/*  76 */     if (this.fuelTimeLeft >= 0) {
/*     */       
/*  78 */       if (this.field_145850_b.func_147439_a(x, y, z) != TFCBlocks.smoke) {
/*  79 */         this.field_145850_b.func_147449_b(x, y, z, TFCBlocks.smoke);
/*     */       }
/*     */     } else {
/*     */       
/*  83 */       this.field_145850_b.func_147468_f(x, y, z);
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
/*     */   public void func_70305_f() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void combineMetals(ItemStack inputItem, ItemStack destItem) {
/* 103 */     int d1 = 100 - inputItem.func_77960_j();
/* 104 */     int d2 = 100 - destItem.func_77960_j();
/* 105 */     destItem.func_77964_b(100 - d1 + d2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void cookItem(int i) {
/* 110 */     HeatRegistry manager = HeatRegistry.getInstance();
/* 111 */     Random r = new Random();
/* 112 */     if (this.fireItemStacks[i] != null) {
/*     */       
/* 114 */       HeatIndex index = manager.findMatchingIndex(this.fireItemStacks[i]);
/* 115 */       ItemStack inputCopy = this.fireItemStacks[i].func_77946_l();
/*     */       
/* 117 */       if (index != null && TFC_ItemHeat.getTemp(this.fireItemStacks[i]) > index.meltTemp) {
/*     */         
/* 119 */         float temperature = TFC_ItemHeat.getTemp(this.fireItemStacks[i]);
/*     */ 
/*     */ 
/*     */         
/* 123 */         if (!(this.fireItemStacks[i].func_77973_b() instanceof com.bioxx.tfc.Items.ItemMeltedMetal)) {
/* 124 */           this.fireItemStacks[i] = index.getMorph();
/*     */         }
/*     */         
/* 127 */         if (this.fireItemStacks[i] != null) {
/*     */           
/* 129 */           HeatIndex morphIndex = manager.findMatchingIndex(this.fireItemStacks[i]);
/* 130 */           if (morphIndex != null)
/*     */           {
/*     */             
/* 133 */             TFC_ItemHeat.setTemp(this.fireItemStacks[i], temperature);
/*     */           }
/*     */         }
/* 136 */         else if (index.hasOutput()) {
/*     */           
/* 138 */           ItemStack output = index.getOutput(inputCopy, r);
/* 139 */           if (inputCopy.func_77973_b() instanceof ISmeltable) {
/*     */             ItemStack meltedItem;
/* 141 */             ISmeltable smelt = (ISmeltable)inputCopy.func_77973_b();
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 146 */             try { meltedItem = new ItemStack((smelt.getMetalType(inputCopy)).meltedItem); }
/* 147 */             catch (Exception e) { return; }
/* 148 */              TFC_ItemHeat.setTemp(meltedItem, temperature);
/*     */             
/* 150 */             int units = smelt.getMetalReturnAmount(inputCopy);
/*     */             
/* 152 */             if (inputCopy.func_77973_b() instanceof com.bioxx.tfc.Items.ItemBloom) {
/* 153 */               units = Math.min(100, units);
/*     */             }
/* 155 */             while (units > 0 && getMold() != null) {
/*     */               
/* 157 */               ItemStack moldIS = getMold();
/* 158 */               ItemStack outputCopy = meltedItem.func_77946_l();
/*     */               
/* 160 */               if (units > 100) {
/*     */                 
/* 162 */                 units -= 100;
/* 163 */                 moldIS.field_77994_a--;
/* 164 */                 if (!addToStorage(outputCopy.func_77946_l())) {
/*     */                   
/* 166 */                   EntityItem ei = new EntityItem(this.field_145850_b, this.field_145851_c + 0.5D, this.field_145848_d + 1.5D, this.field_145849_e + 0.5D, outputCopy);
/* 167 */                   ei.field_70159_w = 0.0D; ei.field_70181_x = 0.0D; ei.field_70179_y = 0.0D;
/* 168 */                   this.field_145850_b.func_72838_d((Entity)ei);
/*     */                 }  continue;
/*     */               } 
/* 171 */               if (units > 0)
/*     */               {
/* 173 */                 outputCopy.func_77964_b(100 - units);
/* 174 */                 units = 0;
/* 175 */                 moldIS.field_77994_a--;
/* 176 */                 this.fireItemStacks[i] = outputCopy.func_77946_l();
/*     */               }
/*     */             
/*     */             } 
/*     */           } else {
/*     */             
/* 182 */             this.fireItemStacks[i] = output;
/*     */           } 
/*     */ 
/*     */           
/* 186 */           if (TFC_ItemHeat.isCookable(this.fireItemStacks[i]) > -1.0F)
/*     */           {
/*     */             
/* 189 */             TFC_ItemHeat.setTemp(this.fireItemStacks[i], temperature);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addToStorage(ItemStack is) {
/* 198 */     if (func_70301_a(10) == null) {
/*     */       
/* 200 */       func_70299_a(10, is);
/* 201 */       return true;
/*     */     } 
/* 203 */     if (func_70301_a(11) == null) {
/*     */       
/* 205 */       func_70299_a(11, is);
/* 206 */       return true;
/*     */     } 
/* 208 */     if (func_70301_a(12) == null) {
/*     */       
/* 210 */       func_70299_a(12, is);
/* 211 */       return true;
/*     */     } 
/* 213 */     if (func_70301_a(13) == null) {
/*     */       
/* 215 */       func_70299_a(13, is);
/* 216 */       return true;
/*     */     } 
/* 218 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private ItemStack getMold() {
/* 223 */     if (this.fireItemStacks[10] != null && this.fireItemStacks[10].func_77973_b() == TFCItems.ceramicMold && (this.fireItemStacks[10]).field_77994_a > 0)
/*     */     {
/* 225 */       return this.fireItemStacks[10];
/*     */     }
/* 227 */     if (this.fireItemStacks[11] != null && this.fireItemStacks[11].func_77973_b() == TFCItems.ceramicMold && (this.fireItemStacks[11]).field_77994_a > 0)
/*     */     {
/* 229 */       return this.fireItemStacks[11];
/*     */     }
/* 231 */     if (this.fireItemStacks[12] != null && this.fireItemStacks[12].func_77973_b() == TFCItems.ceramicMold && (this.fireItemStacks[12]).field_77994_a > 0)
/*     */     {
/* 233 */       return this.fireItemStacks[12];
/*     */     }
/* 235 */     if (this.fireItemStacks[13] != null && this.fireItemStacks[13].func_77973_b() == TFCItems.ceramicMold && (this.fireItemStacks[13]).field_77994_a > 0)
/*     */     {
/* 237 */       return this.fireItemStacks[13];
/*     */     }
/* 239 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70298_a(int i, int j) {
/* 245 */     if (this.fireItemStacks[i] != null) {
/*     */       
/* 247 */       if ((this.fireItemStacks[i]).field_77994_a <= j) {
/*     */         
/* 249 */         ItemStack is = this.fireItemStacks[i];
/* 250 */         this.fireItemStacks[i] = null;
/* 251 */         return is;
/*     */       } 
/*     */       
/* 254 */       ItemStack isSplit = this.fireItemStacks[i].func_77979_a(j);
/* 255 */       if ((this.fireItemStacks[i]).field_77994_a == 0)
/* 256 */         this.fireItemStacks[i] = null; 
/* 257 */       return isSplit;
/*     */     } 
/*     */     
/* 260 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void ejectContents() {
/* 265 */     float f3 = 0.05F;
/*     */     
/* 267 */     Random rand = new Random();
/* 268 */     float f = rand.nextFloat() * 0.8F + 0.1F;
/* 269 */     float f1 = rand.nextFloat() * 0.8F + 0.4F;
/* 270 */     float f2 = rand.nextFloat() * 0.8F + 0.1F;
/*     */     
/* 272 */     for (int i = 0; i < func_70302_i_(); i++) {
/*     */       
/* 274 */       if (this.fireItemStacks[i] != null) {
/*     */         
/* 276 */         EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), this.fireItemStacks[i]);
/* 277 */         entityitem.field_70159_w = ((float)rand.nextGaussian() * f3);
/* 278 */         entityitem.field_70181_x = ((float)rand.nextGaussian() * f3 + 0.2F);
/* 279 */         entityitem.field_70179_y = ((float)rand.nextGaussian() * f3);
/* 280 */         this.field_145850_b.func_72838_d((Entity)entityitem);
/* 281 */         this.fireItemStacks[i] = null;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70297_j_() {
/* 289 */     return 64;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String func_145825_b() {
/* 295 */     return "Forge";
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMoldIndex() {
/* 300 */     if (this.fireItemStacks[10] != null && this.fireItemStacks[10].func_77973_b() == TFCItems.ceramicMold)
/* 301 */       return 10; 
/* 302 */     if (this.fireItemStacks[11] != null && this.fireItemStacks[11].func_77973_b() == TFCItems.ceramicMold)
/* 303 */       return 11; 
/* 304 */     if (this.fireItemStacks[12] != null && this.fireItemStacks[12].func_77973_b() == TFCItems.ceramicMold)
/* 305 */       return 12; 
/* 306 */     if (this.fireItemStacks[13] != null && this.fireItemStacks[13].func_77973_b() == TFCItems.ceramicMold)
/* 307 */       return 13; 
/* 308 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70302_i_() {
/* 314 */     return this.fireItemStacks.length;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70301_a(int i) {
/* 320 */     return this.fireItemStacks[i];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70304_b(int var1) {
/* 326 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleFuelStack() {
/* 331 */     Random random = new Random();
/* 332 */     if (this.fireItemStacks[7] == null)
/*     */     {
/* 334 */       if (random.nextBoolean() && this.fireItemStacks[6] != null) {
/*     */         
/* 336 */         this.fireItemStacks[7] = this.fireItemStacks[6];
/* 337 */         this.fireItemStacks[6] = null;
/*     */       }
/*     */       else {
/*     */         
/* 341 */         this.fireItemStacks[7] = this.fireItemStacks[8];
/* 342 */         this.fireItemStacks[8] = null;
/*     */       } 
/*     */     }
/*     */     
/* 346 */     if (this.fireItemStacks[6] == null)
/*     */     {
/* 348 */       if (this.fireItemStacks[5] != null) {
/*     */         
/* 350 */         this.fireItemStacks[6] = this.fireItemStacks[5];
/* 351 */         this.fireItemStacks[5] = null;
/*     */       } 
/*     */     }
/*     */     
/* 355 */     if (this.fireItemStacks[8] == null)
/*     */     {
/* 357 */       if (this.fireItemStacks[9] != null) {
/*     */         
/* 359 */         this.fireItemStacks[8] = this.fireItemStacks[9];
/* 360 */         this.fireItemStacks[9] = null;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70300_a(EntityPlayer entityplayer) {
/* 368 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70295_k_() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbt) {
/* 379 */     super.func_145839_a(nbt);
/* 380 */     this.isSmokeStackValid = nbt.func_74767_n("isValid");
/*     */     
/* 382 */     NBTTagList nbttaglist = nbt.func_150295_c("Items", 10);
/* 383 */     this.fireItemStacks = new ItemStack[func_70302_i_()];
/* 384 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*     */       
/* 386 */       NBTTagCompound nbt1 = nbttaglist.func_150305_b(i);
/* 387 */       byte byte0 = nbt1.func_74771_c("Slot");
/* 388 */       if (byte0 >= 0 && byte0 < this.fireItemStacks.length) {
/* 389 */         this.fireItemStacks[byte0] = ItemStack.func_77949_a(nbt1);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70299_a(int i, ItemStack itemstack) {
/* 396 */     this.fireItemStacks[i] = itemstack;
/* 397 */     if (itemstack != null && itemstack.field_77994_a > func_70297_j_()) {
/* 398 */       itemstack.field_77994_a = func_70297_j_();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145845_h() {
/* 405 */     this.isSmokeStackValid = validateSmokeStack();
/*     */     
/* 407 */     if (!this.field_145850_b.field_72995_K) {
/*     */ 
/*     */       
/* 410 */       careForInventorySlot(this.fireItemStacks[0]);
/* 411 */       careForInventorySlot(this.fireItemStacks[1]);
/* 412 */       careForInventorySlot(this.fireItemStacks[2]);
/* 413 */       careForInventorySlot(this.fireItemStacks[3]);
/* 414 */       careForInventorySlot(this.fireItemStacks[4]);
/*     */       
/* 416 */       ItemStack[] fuelStack = new ItemStack[9];
/* 417 */       fuelStack[0] = this.fireItemStacks[5];
/* 418 */       fuelStack[1] = this.fireItemStacks[6];
/* 419 */       fuelStack[2] = this.fireItemStacks[7];
/* 420 */       fuelStack[3] = this.fireItemStacks[8];
/* 421 */       fuelStack[4] = this.fireItemStacks[9];
/* 422 */       fuelStack[5] = this.fireItemStacks[10];
/* 423 */       fuelStack[6] = this.fireItemStacks[11];
/* 424 */       fuelStack[7] = this.fireItemStacks[12];
/* 425 */       fuelStack[8] = this.fireItemStacks[13];
/*     */ 
/*     */       
/* 428 */       cookItem(0);
/* 429 */       cookItem(1);
/* 430 */       cookItem(2);
/* 431 */       cookItem(3);
/* 432 */       cookItem(4);
/*     */ 
/*     */       
/* 435 */       handleFuelStack();
/*     */ 
/*     */       
/* 438 */       Random r = new Random();
/* 439 */       if (r.nextInt(10) == 0 && this.fireTemp > 20.0F) {
/* 440 */         this.field_145850_b.func_72908_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, "fire.fire", 0.4F + r.nextFloat() / 2.0F, 0.7F + r.nextFloat());
/*     */       }
/* 442 */       if (this.fireTemp >= 20.0F && this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e) != 1) {
/* 443 */         this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, 3);
/* 444 */       } else if (this.fireTemp < 20.0F && this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e) != 0) {
/* 445 */         this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, 3);
/*     */       } 
/*     */       
/* 448 */       if (this.fuelTimeLeft > 0 && this.fireTemp >= 1.0F && !TFC_Core.isExposedToRain(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e)) {
/*     */         
/* 450 */         float desiredTemp = handleTemp();
/* 451 */         handleTempFlux(desiredTemp);
/* 452 */         this.smokeTimer++;
/* 453 */         if (this.smokeTimer > 60) {
/*     */           
/* 455 */           this.smokeTimer = 0;
/* 456 */           createSmoke();
/*     */         } 
/* 458 */         if (TFCOptions.enableDebugMode) {
/*     */           
/* 460 */           this.fireTemp = 2000.0F;
/* 461 */           this.fuelTimeLeft = 9999;
/*     */         } 
/*     */         
/* 464 */         TFC_Core.handleItemTicking(fuelStack, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */       }
/* 466 */       else if (this.fuelTimeLeft <= 0 && this.fireTemp >= 1.0F && this.fireItemStacks[7] != null && this.isSmokeStackValid) {
/*     */ 
/*     */         
/* 469 */         EnumFuelMaterial m = TFC_Core.getFuelMaterial(this.fireItemStacks[7]);
/* 470 */         this.fuelTimeLeft = m.burnTimeMax;
/* 471 */         this.fuelBurnTemp = m.burnTempMax;
/* 472 */         this.fuelTasteProfile = m.ordinal();
/* 473 */         this.fireItemStacks[7] = null;
/*     */       }
/*     */       else {
/*     */         
/* 477 */         removeSmoke();
/*     */         
/* 479 */         handleTempFlux(0.0F);
/* 480 */         TFC_Core.handleItemTicking(this, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */       } 
/*     */ 
/*     */       
/* 484 */       handleAirReduction();
/*     */ 
/*     */       
/* 487 */       for (int c = 0; c < 5; c++) {
/*     */         
/* 489 */         if (this.fireItemStacks[c] != null)
/*     */         {
/* 491 */           if ((this.fireItemStacks[c]).field_77994_a <= 0) {
/* 492 */             (this.fireItemStacks[c]).field_77994_a = 1;
/*     */           }
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void createSmoke() {
/* 500 */     if (!TFCOptions.generateSmoke) {
/*     */       return;
/*     */     }
/* 503 */     if (checkChimney(this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e)) {
/* 504 */       genSmokeRoot(this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e);
/* 505 */     } else if (checkChimney(this.field_145851_c - 1, this.field_145848_d + 1, this.field_145849_e)) {
/* 506 */       genSmokeRoot(this.field_145851_c - 1, this.field_145848_d + 1, this.field_145849_e);
/* 507 */     } else if (checkChimney(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e + 1)) {
/* 508 */       genSmokeRoot(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e + 1);
/* 509 */     } else if (checkChimney(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e - 1)) {
/* 510 */       genSmokeRoot(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e - 1);
/* 511 */     } else if (notOpaque(this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e) && checkChimney(this.field_145851_c + 2, this.field_145848_d + 1, this.field_145849_e)) {
/* 512 */       genSmokeRoot(this.field_145851_c + 2, this.field_145848_d + 1, this.field_145849_e);
/* 513 */     } else if (notOpaque(this.field_145851_c - 1, this.field_145848_d + 1, this.field_145849_e) && checkChimney(this.field_145851_c - 2, this.field_145848_d + 1, this.field_145849_e)) {
/* 514 */       genSmokeRoot(this.field_145851_c - 2, this.field_145848_d + 1, this.field_145849_e);
/* 515 */     } else if (notOpaque(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e + 1) && checkChimney(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e + 2)) {
/* 516 */       genSmokeRoot(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e + 2);
/* 517 */     } else if (notOpaque(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e - 1) && checkChimney(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e - 2)) {
/* 518 */       genSmokeRoot(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e - 2);
/*     */     } 
/*     */   }
/*     */   public void removeSmoke() {
/* 522 */     if (isSmoke(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e)) {
/* 523 */       this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
/* 524 */     } else if (isSmoke(this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e)) {
/* 525 */       this.field_145850_b.func_147468_f(this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e);
/* 526 */     } else if (isSmoke(this.field_145851_c - 1, this.field_145848_d + 1, this.field_145849_e)) {
/* 527 */       this.field_145850_b.func_147468_f(this.field_145851_c - 1, this.field_145848_d + 1, this.field_145849_e);
/* 528 */     } else if (isSmoke(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e + 1)) {
/* 529 */       this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e + 1);
/* 530 */     } else if (isSmoke(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e - 1)) {
/* 531 */       this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e - 1);
/* 532 */     } else if (isSmoke(this.field_145851_c + 2, this.field_145848_d + 1, this.field_145849_e)) {
/* 533 */       this.field_145850_b.func_147468_f(this.field_145851_c + 2, this.field_145848_d + 1, this.field_145849_e);
/* 534 */     } else if (isSmoke(this.field_145851_c - 2, this.field_145848_d + 1, this.field_145849_e)) {
/* 535 */       this.field_145850_b.func_147468_f(this.field_145851_c - 2, this.field_145848_d + 1, this.field_145849_e);
/* 536 */     } else if (isSmoke(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e + 2)) {
/* 537 */       this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e + 2);
/* 538 */     } else if (isSmoke(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e - 2)) {
/* 539 */       this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e - 2);
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isSmoke(int x, int y, int z) {
/* 544 */     return (this.field_145850_b.func_72899_e(x, y, z) && this.field_145850_b.func_147439_a(x, y, z) == TFCBlocks.smoke);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_145818_k_() {
/* 550 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_94041_b(int i, ItemStack itemstack) {
/* 556 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbt) {
/* 562 */     super.func_145841_b(nbt);
/* 563 */     nbt.func_74757_a("isValid", this.isSmokeStackValid);
/*     */     
/* 565 */     NBTTagList nbttaglist = new NBTTagList();
/* 566 */     for (int i = 0; i < this.fireItemStacks.length; i++) {
/*     */       
/* 568 */       if (this.fireItemStacks[i] != null) {
/*     */         
/* 570 */         NBTTagCompound nbt1 = new NBTTagCompound();
/* 571 */         nbt1.func_74774_a("Slot", (byte)i);
/* 572 */         this.fireItemStacks[i].func_77955_b(nbt1);
/* 573 */         nbttaglist.func_74742_a((NBTBase)nbt1);
/*     */       } 
/*     */     } 
/* 576 */     nbt.func_74782_a("Items", (NBTBase)nbttaglist);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEForge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */