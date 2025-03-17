/*     */ package com.bioxx.tfc.TileEntities;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.Devices.BlockBlastFurnace;
/*     */ import com.bioxx.tfc.Core.Metal.MetalRegistry;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.GUI.GuiBlastFurnace;
/*     */ import com.bioxx.tfc.api.Constant.Global;
/*     */ import com.bioxx.tfc.api.HeatIndex;
/*     */ import com.bioxx.tfc.api.HeatRegistry;
/*     */ import com.bioxx.tfc.api.Interfaces.ISmeltable;
/*     */ import com.bioxx.tfc.api.Metal;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import com.bioxx.tfc.api.TFC_ItemHeat;
/*     */ import com.bioxx.tfc.api.TileEntities.TEFireEntity;
/*     */ import cpw.mods.fml.client.FMLClientHandler;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Queue;
/*     */ import java.util.Random;
/*     */ import java.util.concurrent.ArrayBlockingQueue;
/*     */ import mods.railcraft.common.items.RailcraftToolItems;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TEBlastFurnace
/*     */   extends TEFireEntity
/*     */   implements IInventory
/*     */ {
/*     */   public boolean isValid = false;
/*  77 */   public ItemStack[] fireItemStacks = new ItemStack[20];
/*  78 */   public ItemStack[] outputItemStacks = new ItemStack[20];
/*  79 */   public ItemStack[] storage = new ItemStack[2];
/*     */ 
/*     */   
/*  82 */   public int charcoalCount = 0;
/*  83 */   public int oreCount = 0;
/*     */   public static final int ORE_SLOT1 = 0;
/*     */   public String oreType;
/*     */   public int slowCounter;
/*     */   
/*     */   public boolean canLight() {
/*  89 */     if (!this.field_145850_b.field_72995_K) {
/*     */       
/*  91 */       if (this.charcoalCount < this.oreCount) {
/*  92 */         return false;
/*     */       }
/*     */ 
/*     */       
/*  96 */       int meta = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*  97 */       if (this.charcoalCount >= 4 && this.fireTemp == 0.0F) {
/*     */         
/*  99 */         this.fireTemp = 1.0F;
/* 100 */         this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, meta + 4, 2);
/* 101 */         return true;
/*     */       } 
/*     */     } 
/* 104 */     return false;
/*     */   }
/*     */   private int outMetal1Count; private int cookDelay; private int maxValidStackSize; private int moltenCount;
/*     */   
/*     */   private Boolean checkValidity() {
/* 109 */     int y = this.field_145848_d + 1;
/* 110 */     if (isStackValid(this.field_145851_c, y, this.field_145849_e))
/* 111 */       return Boolean.valueOf(true); 
/* 112 */     return Boolean.valueOf(false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70305_f() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void cookItem(int i) {
/* 122 */     ItemStack cookingItemStack = this.fireItemStacks[i];
/*     */     
/* 124 */     TECrucible crucibleTE = (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) instanceof TECrucible) ? (TECrucible)this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) : null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 130 */     if (cookingItemStack != null && crucibleTE != null && crucibleTE.getTotalMetal() < 3000.0F && this.storage[1] != null && this.cookDelay == 0) {
/*     */ 
/*     */       
/* 133 */       Random r = new Random();
/* 134 */       HeatRegistry manager = HeatRegistry.getInstance();
/* 135 */       HeatIndex index = manager.findMatchingIndex(cookingItemStack);
/*     */       
/* 137 */       if (index != null && TFC_ItemHeat.getTemp(cookingItemStack) >= index.meltTemp) {
/*     */         
/* 139 */         int output = 0;
/* 140 */         Item cookingItem = cookingItemStack.func_77973_b();
/*     */         
/* 142 */         if (cookingItem instanceof ISmeltable) {
/*     */           
/* 144 */           output = ((ISmeltable)cookingItem).getMetalReturnAmount(cookingItemStack);
/*     */           
/* 146 */           if (!crucibleTE.addMetal(((ISmeltable)cookingItem).getMetalType(cookingItemStack), output)) {
/*     */             return;
/*     */           }
/*     */         } else {
/*     */           
/* 151 */           Metal m = MetalRegistry.instance.getMetalFromItem(cookingItem);
/* 152 */           output = index.getOutput(cookingItemStack, r).func_77960_j();
/* 153 */           if (m != null)
/*     */           {
/*     */             
/* 156 */             if (!crucibleTE.addMetal(m, (short)(100 - output))) {
/*     */               return;
/*     */             }
/*     */           }
/*     */         } 
/* 161 */         this.oreCount--;
/* 162 */         this.charcoalCount--;
/* 163 */         this.cookDelay = 100;
/* 164 */         this.fireItemStacks[i] = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 170 */         Queue<ItemStack> buffer = new ArrayBlockingQueue<>(this.fireItemStacks.length);
/* 171 */         for (ItemStack is : this.fireItemStacks) {
/*     */           
/* 173 */           if (is != null)
/*     */           {
/* 175 */             buffer.offer(is);
/*     */           }
/*     */         } 
/*     */         
/* 179 */         this.fireItemStacks = (ItemStack[])buffer.toArray((Object[])new ItemStack[this.fireItemStacks.length]);
/*     */ 
/*     */         
/* 182 */         this.storage[1].func_77964_b(this.storage[1].func_77960_j() + 1);
/* 183 */         if (this.storage[1] != null && this.storage[1].func_77960_j() == this.storage[1].func_77958_k())
/*     */         {
/* 185 */           func_70299_a(1, (ItemStack)null);
/*     */         }
/*     */ 
/*     */         
/* 189 */         crucibleTE.temperature = (int)this.fireTemp;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70298_a(int i, int j) {
/* 197 */     if (this.storage[i] != null) {
/*     */       
/* 199 */       if ((this.storage[i]).field_77994_a <= j) {
/*     */         
/* 201 */         ItemStack itemstack = this.storage[i];
/* 202 */         this.storage[i] = null;
/* 203 */         return itemstack;
/*     */       } 
/* 205 */       ItemStack itemstack1 = this.storage[i].func_77979_a(j);
/* 206 */       if ((this.storage[i]).field_77994_a == 0)
/*     */       {
/* 208 */         this.storage[i] = null;
/*     */       }
/* 210 */       return itemstack1;
/*     */     } 
/*     */ 
/*     */     
/* 214 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void ejectContents() {
/* 220 */     float f3 = 0.05F;
/*     */     
/* 222 */     Random rand = new Random();
/* 223 */     float f = rand.nextFloat() * 0.8F + 0.1F;
/* 224 */     float f1 = rand.nextFloat() * 2.0F + 0.4F;
/* 225 */     float f2 = rand.nextFloat() * 0.8F + 0.1F;
/*     */     
/* 227 */     for (int i = 0; i < func_70302_i_(); i++) {
/*     */       
/* 229 */       if (this.fireItemStacks[i] != null) {
/*     */         
/* 231 */         EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), this.fireItemStacks[i]);
/* 232 */         entityitem.field_70159_w = ((float)rand.nextGaussian() * f3);
/* 233 */         entityitem.field_70181_x = ((float)rand.nextGaussian() * f3 + 0.2F);
/* 234 */         entityitem.field_70179_y = ((float)rand.nextGaussian() * f3);
/* 235 */         this.field_145850_b.func_72838_d((Entity)entityitem);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 240 */     if (this.charcoalCount > 0) {
/*     */       
/* 242 */       EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), RailcraftToolItems.getCoalCoke());
/* 243 */       entityitem.field_70159_w = ((float)rand.nextGaussian() * f3);
/* 244 */       entityitem.field_70181_x = ((float)rand.nextGaussian() * f3 + 0.2F);
/* 245 */       entityitem.field_70179_y = ((float)rand.nextGaussian() * f3);
/* 246 */       this.field_145850_b.func_72838_d((Entity)entityitem);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70297_j_() {
/* 254 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String func_145825_b() {
/* 260 */     return "BlastFurnace";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70302_i_() {
/* 266 */     return this.storage.length;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70301_a(int i) {
/* 272 */     return this.storage[i];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70304_b(int i) {
/* 278 */     return this.storage[i];
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleTemperature() {
/* 283 */     int meta = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */ 
/*     */ 
/*     */     
/* 287 */     if (this.fuelTimeLeft > 0) {
/*     */       
/* 289 */       float desiredTemp = handleTemp();
/* 290 */       handleTempFlux(desiredTemp);
/*     */     }
/* 292 */     else if (this.fuelTimeLeft <= 0 && this.charcoalCount > 0 && (meta & 0x4) > 0) {
/*     */       
/* 294 */       this.charcoalCount--;
/*     */       
/* 296 */       this.fuelTimeLeft = 1875;
/* 297 */       this.fuelBurnTemp = 1400;
/*     */     }
/*     */     else {
/*     */       
/* 301 */       if ((meta & 0x4) > 0) {
/* 302 */         this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, meta & 0x3, 3);
/*     */       }
/* 304 */       this.fuelBurnTemp = 0;
/* 305 */       float desiredTemp = handleTemp();
/* 306 */       handleTempFlux(desiredTemp);
/*     */     } 
/*     */ 
/*     */     
/* 310 */     handleAirReduction();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void receiveAirFromBellows() {
/* 316 */     if (this.storage[1] != null) {
/* 317 */       super.receiveAirFromBellows();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isStackValid(int i, int j, int k) {
/* 322 */     Block yNegBlock = this.field_145850_b.func_147439_a(i, j - 1, k);
/* 323 */     if (yNegBlock != TFCBlocks.molten && this.field_145850_b
/* 324 */       .func_147439_a(i, j - 1, k).func_149688_o() != Material.field_151576_e && 
/* 325 */       !this.field_145850_b.func_147439_a(i, j - 1, k).func_149721_r() && yNegBlock != TFCBlocks.blastFurnace && 
/* 326 */       TFC_Core.isTopFaceSolid(this.field_145850_b, i, j - 1, k))
/*     */     {
/* 328 */       return false;
/*     */     }
/*     */     
/* 331 */     this.maxValidStackSize = 0;
/* 332 */     for (int num = 0; num < 5; num++) {
/*     */       
/* 334 */       if (!((BlockBlastFurnace)TFCBlocks.blastFurnace).checkStackAt(this.field_145850_b, i, j + num, k))
/*     */         break; 
/* 336 */       this.maxValidStackSize++;
/*     */     } 
/* 338 */     return (this.maxValidStackSize != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70300_a(EntityPlayer entityplayer) {
/* 344 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70295_k_() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addOreToFire(ItemStack is) {
/* 354 */     for (int i = 0; i < this.fireItemStacks.length; i++) {
/*     */       
/* 356 */       if (this.fireItemStacks[i] == null) {
/*     */         
/* 358 */         this.fireItemStacks[i] = is;
/* 359 */         return true;
/*     */       } 
/*     */     } 
/* 362 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70299_a(int i, ItemStack itemstack) {
/* 368 */     this.storage[i] = itemstack;
/* 369 */     if (itemstack != null && itemstack.field_77994_a > func_70297_j_()) {
/* 370 */       itemstack.field_77994_a = func_70297_j_();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createTuyereBlock() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTotalCount() {
/* 426 */     return this.charcoalCount + this.oreCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145845_h() {
/* 435 */     if (!this.field_145850_b.field_72995_K) {
/*     */       
/* 437 */       createTuyereBlock();
/*     */       
/* 439 */       if (this.oreCount < 0)
/* 440 */         this.oreCount = 0; 
/* 441 */       if (this.charcoalCount < 0) {
/* 442 */         this.charcoalCount = 0;
/*     */       }
/*     */       
/* 445 */       List<EntityItem> list = this.field_145850_b.func_72872_a(EntityItem.class, 
/* 446 */           AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + this.moltenCount) + 1.1D, (this.field_145849_e + 1)));
/*     */ 
/*     */       
/* 449 */       List<EntityItem> playerList = this.field_145850_b.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + this.moltenCount) + 1.1D, (this.field_145849_e + 1)));
/*     */       
/* 451 */       if (this.moltenCount == 0) {
/* 452 */         this.moltenCount = 1;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 457 */       if (list != null && !list.isEmpty() && ((BlockBlastFurnace)TFCBlocks.blastFurnace).checkStackAt(this.field_145850_b, this.field_145851_c, this.field_145848_d + this.moltenCount, this.field_145849_e) && (playerList == null || playerList.isEmpty()))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 463 */         for (Iterator<EntityItem> iterator = list.iterator(); iterator.hasNext(); ) {
/*     */           
/* 465 */           EntityItem entity = iterator.next();
/* 466 */           ItemStack itemstack = entity.func_92059_d();
/* 467 */           Item item = itemstack.func_77973_b();
/* 468 */           boolean isOre = TFC_Core.isOreIron(itemstack);
/* 469 */           HeatRegistry manager = HeatRegistry.getInstance();
/* 470 */           HeatIndex index = manager.findMatchingIndex(itemstack);
/*     */           
/* 472 */           if (item == RailcraftToolItems.getCoalCoke().func_77973_b()) {
/*     */ 
/*     */ 
/*     */             
/* 476 */             for (int c = 0; c < itemstack.field_77994_a; c++) {
/*     */               
/* 478 */               if (getTotalCount() < 40 && this.charcoalCount < this.maxValidStackSize * 4) {
/*     */                 
/* 480 */                 this.charcoalCount++;
/* 481 */                 itemstack.field_77994_a--;
/*     */               } 
/*     */             } 
/*     */             
/* 485 */             if (itemstack.field_77994_a == 0) {
/* 486 */               entity.func_70106_y();
/*     */             }
/*     */ 
/*     */             
/*     */             continue;
/*     */           } 
/*     */           
/* 493 */           if ((TFC_ItemHeat.isCookable(itemstack) != -1.0F && isOre) || (!isOre && item instanceof ISmeltable && ((ISmeltable)item)
/* 494 */             .getMetalType(itemstack) == Global.PIGIRON && index != null)) {
/*     */ 
/*     */             
/* 497 */             int c = itemstack.field_77994_a;
/* 498 */             int nonConsumedOre = 0;
/* 499 */             for (; c > 0; c--) {
/*     */               
/* 501 */               if (getTotalCount() < 40 && this.oreCount < this.maxValidStackSize * 4) {
/*     */                 
/* 503 */                 if (foundFlux(this.moltenCount) && addOreToFire(new ItemStack(item, 1, itemstack.func_77960_j()))) {
/* 504 */                   this.oreCount++;
/*     */                 } else {
/* 506 */                   nonConsumedOre++;
/*     */                 } 
/*     */               } else {
/*     */                 
/* 510 */                 nonConsumedOre++;
/*     */               } 
/*     */             } 
/*     */             
/* 514 */             if (c + nonConsumedOre == 0) {
/* 515 */               entity.func_70106_y();
/*     */               continue;
/*     */             } 
/* 518 */             itemstack.field_77994_a = c + nonConsumedOre;
/* 519 */             entity.func_92058_a(itemstack);
/*     */           } 
/*     */         } 
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 526 */       handleTemperature();
/*     */       
/* 528 */       if (this.cookDelay > 0) {
/* 529 */         this.cookDelay--;
/*     */       }
/* 531 */       for (int i = 0; i < this.fireItemStacks.length && this.isValid; i++) {
/*     */ 
/*     */         
/* 534 */         careForInventorySlot(this.fireItemStacks[i]);
/*     */         
/* 536 */         if (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) == TFCBlocks.crucible)
/*     */         {
/* 538 */           cookItem(i);
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 544 */       if (this.slowCounter > 100) {
/*     */ 
/*     */         
/* 547 */         this.isValid = checkValidity().booleanValue();
/* 548 */         this.moltenCount = updateMoltenBlocks();
/*     */       } 
/* 550 */       this.slowCounter++;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int updateMoltenBlocks() {
/* 559 */     int count = this.charcoalCount + this.oreCount;
/*     */     
/* 561 */     int moltenCount = 0;
/* 562 */     if (count > 0 && count <= 8) { moltenCount = 1; }
/* 563 */     else if (count > 8 && count <= 16) { moltenCount = 2; }
/* 564 */     else if (count > 16 && count <= 24) { moltenCount = 3; }
/* 565 */     else if (count > 24 && count <= 32) { moltenCount = 4; }
/* 566 */     else if (count > 32 && count <= 40) { moltenCount = 5; }
/*     */ 
/*     */     
/* 569 */     for (int i = 1; i <= 5; i++) {
/*     */ 
/*     */       
/* 572 */       if (this.field_145850_b.func_147437_c(this.field_145851_c, this.field_145848_d + i, this.field_145849_e) || this.field_145850_b
/* 573 */         .func_147439_a(this.field_145851_c, this.field_145848_d + i, this.field_145849_e) == TFCBlocks.molten)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 580 */         if (i <= moltenCount && i <= this.maxValidStackSize) {
/*     */           
/* 582 */           if (this.fireTemp > 100.0F)
/*     */           {
/* 584 */             int m = (count > 7) ? 7 : count;
/* 585 */             this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d + i, this.field_145849_e, TFCBlocks.molten, m + 8, 2);
/* 586 */             count -= 8;
/*     */           }
/*     */           else
/*     */           {
/* 590 */             int m = (count > 7) ? 7 : count;
/* 591 */             this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d + i, this.field_145849_e, TFCBlocks.molten, m, 2);
/* 592 */             count -= 8;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 597 */           this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d + i, this.field_145849_e);
/*     */         } 
/*     */       }
/*     */     } 
/* 601 */     return moltenCount;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean foundFlux(int moltenCount) {
/* 607 */     List<EntityItem> list = this.field_145850_b.func_72872_a(EntityItem.class, 
/* 608 */         AxisAlignedBB.func_72330_a(this.field_145851_c, (this.field_145848_d + moltenCount), this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + moltenCount) + 1.1D, (this.field_145849_e + 1)));
/* 609 */     boolean found = false;
/* 610 */     for (Iterator<EntityItem> iterator = list.iterator(); iterator.hasNext() && !found; ) {
/*     */       
/* 612 */       EntityItem entity = iterator.next();
/* 613 */       ItemStack is = entity.func_92059_d();
/* 614 */       if (!entity.field_70128_L && is.func_77960_j() == 0 && is.func_77973_b() == TFCItems.powder) {
/*     */         
/* 616 */         is.field_77994_a--;
/* 617 */         if (is.field_77994_a == 0) {
/* 618 */           entity.func_70106_y();
/*     */         } else {
/* 620 */           entity.func_92058_a(is);
/* 621 */         }  found = true;
/*     */       } 
/*     */     } 
/* 624 */     return found;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getOreCountScaled(int l) {
/* 629 */     return this.oreCount * l / 20;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCharcoalCountScaled(int l) {
/* 634 */     return this.charcoalCount * l / 20;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_94041_b(int i, ItemStack itemstack) {
/* 640 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_145818_k_() {
/* 646 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbt) {
/* 652 */     super.func_145841_b(nbt);
/* 653 */     nbt.func_74768_a("charcoalCount", this.charcoalCount);
/* 654 */     nbt.func_74768_a("outMetal1Count", this.outMetal1Count);
/* 655 */     nbt.func_74774_a("oreCount", (byte)this.oreCount);
/* 656 */     nbt.func_74768_a("maxValidStackSize", this.maxValidStackSize);
/*     */ 
/*     */     
/* 659 */     NBTTagList nbttaglist = new NBTTagList();
/* 660 */     for (int i = 0; i < this.fireItemStacks.length; i++) {
/*     */       
/* 662 */       if (this.fireItemStacks[i] != null) {
/*     */         
/* 664 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 665 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 666 */         this.fireItemStacks[i].func_77955_b(nbttagcompound1);
/* 667 */         nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
/*     */       } 
/*     */     } 
/* 670 */     nbt.func_74782_a("Items", (NBTBase)nbttaglist);
/*     */     
/* 672 */     NBTTagList nbttaglist2 = new NBTTagList();
/* 673 */     for (int j = 0; j < this.storage.length; j++) {
/*     */       
/* 675 */       if (this.storage[j] != null) {
/*     */         
/* 677 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 678 */         nbttagcompound1.func_74774_a("Slot", (byte)j);
/* 679 */         this.storage[j].func_77955_b(nbttagcompound1);
/* 680 */         nbttaglist2.func_74742_a((NBTBase)nbttagcompound1);
/*     */       } 
/*     */     } 
/* 683 */     nbt.func_74782_a("Input", (NBTBase)nbttaglist2);
/*     */     
/* 685 */     NBTTagList nbttaglist3 = new NBTTagList();
/* 686 */     for (int k = 0; k < this.outputItemStacks.length; k++) {
/*     */       
/* 688 */       if (this.outputItemStacks[k] != null) {
/*     */         
/* 690 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 691 */         nbttagcompound1.func_74774_a("Slot", (byte)k);
/* 692 */         this.outputItemStacks[k].func_77955_b(nbttagcompound1);
/* 693 */         nbttaglist3.func_74742_a((NBTBase)nbttagcompound1);
/*     */       } 
/*     */     } 
/* 696 */     nbt.func_74782_a("Output", (NBTBase)nbttaglist3);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbt) {
/* 702 */     super.func_145839_a(nbt);
/* 703 */     this.charcoalCount = nbt.func_74762_e("charcoalCount");
/* 704 */     this.outMetal1Count = nbt.func_74762_e("outMetal1Count");
/* 705 */     this.oreCount = nbt.func_74771_c("oreCount");
/* 706 */     this.maxValidStackSize = nbt.func_74762_e("maxValidStackSize");
/*     */     
/* 708 */     NBTTagList nbttaglist = nbt.func_150295_c("Items", 10);
/* 709 */     this.fireItemStacks = new ItemStack[20];
/* 710 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*     */       
/* 712 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 713 */       byte byte0 = nbttagcompound1.func_74771_c("Slot");
/* 714 */       if (byte0 >= 0 && byte0 < this.fireItemStacks.length) {
/* 715 */         this.fireItemStacks[byte0] = ItemStack.func_77949_a(nbttagcompound1);
/*     */       }
/*     */     } 
/* 718 */     NBTTagList nbttaglist2 = nbt.func_150295_c("Input", 10);
/* 719 */     this.storage = new ItemStack[2];
/* 720 */     for (int j = 0; j < nbttaglist2.func_74745_c(); j++) {
/*     */       
/* 722 */       NBTTagCompound nbttagcompound1 = nbttaglist2.func_150305_b(j);
/* 723 */       byte byte0 = nbttagcompound1.func_74771_c("Slot");
/* 724 */       if (byte0 >= 0 && byte0 < this.storage.length)
/*     */       {
/* 726 */         this.storage[byte0] = ItemStack.func_77949_a(nbttagcompound1);
/*     */       }
/*     */     } 
/*     */     
/* 730 */     NBTTagList nbttaglist3 = nbt.func_150295_c("Output", 10);
/* 731 */     this.outputItemStacks = new ItemStack[20];
/* 732 */     for (int k = 0; k < nbttaglist3.func_74745_c(); k++) {
/*     */       
/* 734 */       NBTTagCompound nbttagcompound1 = nbttaglist3.func_150305_b(k);
/* 735 */       byte byte0 = nbttagcompound1.func_74771_c("Slot");
/* 736 */       if (byte0 >= 0 && byte0 < this.outputItemStacks.length) {
/* 737 */         this.outputItemStacks[byte0] = ItemStack.func_77949_a(nbttagcompound1);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet func_145844_m() {
/* 744 */     NBTTagCompound nbt = new NBTTagCompound();
/* 745 */     func_145841_b(nbt);
/* 746 */     return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbt);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
/* 752 */     func_145839_a(pkt.func_148857_g());
/*     */     
/* 754 */     GuiScreen gui = (FMLClientHandler.instance().getClient()).field_71462_r;
/* 755 */     if (gui instanceof GuiBlastFurnace) {
/* 756 */       ((GuiBlastFurnace)gui).func_73876_c();
/*     */     }
/*     */   }
/*     */   
/*     */   public void updateGui() {
/* 761 */     this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEBlastFurnace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */