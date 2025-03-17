/*     */ package com.bioxx.tfc.Containers;
/*     */ 
/*     */ import com.bioxx.tfc.Containers.Slots.SlotArmorTFC;
/*     */ import com.bioxx.tfc.Core.Player.PlayerInventory;
/*     */ import com.bioxx.tfc.Handlers.CraftingHandler;
/*     */ import com.bioxx.tfc.Handlers.FoodCraftingHandler;
/*     */ import com.bioxx.tfc.Items.ItemTFCArmor;
/*     */ import com.bioxx.tfc.api.Interfaces.IEquipable;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.ContainerPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.InventoryCrafting;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.inventory.SlotCrafting;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContainerPlayerTFC
/*     */   extends ContainerPlayer
/*     */ {
/*     */   private final EntityPlayer thePlayer;
/*     */   
/*     */   public ContainerPlayerTFC(InventoryPlayer playerInv, boolean par2, EntityPlayer player) {
/*  31 */     super(playerInv, par2, player);
/*  32 */     this.field_75181_e = new InventoryCrafting((Container)this, 3, 3);
/*  33 */     this.field_75151_b.clear();
/*  34 */     this.field_75153_a.clear();
/*  35 */     this.thePlayer = player;
/*  36 */     func_75146_a((Slot)new SlotCrafting(player, (IInventory)this.field_75181_e, this.field_75179_f, 0, 152, 36));
/*     */     
/*     */     int x;
/*     */     
/*  40 */     for (x = 0; x < 2; x++) {
/*     */       
/*  42 */       for (int y = 0; y < 2; y++) {
/*  43 */         func_75146_a(new Slot((IInventory)this.field_75181_e, y + x * 3, 82 + y * 18, 18 + x * 18));
/*     */       }
/*     */     } 
/*  46 */     for (x = 0; x < playerInv.field_70460_b.length; x++) {
/*     */       
/*  48 */       int index = playerInv.func_70302_i_() - 3 - x;
/*     */       
/*  50 */       func_75146_a((Slot)new SlotArmorTFC(this, (IInventory)playerInv, index, 8, 8 + x * 18, x));
/*     */     } 
/*  52 */     PlayerInventory.buildInventoryLayout((Container)this, playerInv, 8, 90, false, true);
/*     */ 
/*     */     
/*  55 */     if (player.getEntityData().func_74764_b("craftingTable") || !player.field_70170_p.field_72995_K) {
/*     */       
/*  57 */       x = 2; int y = 0; func_75146_a(new Slot((IInventory)this.field_75181_e, y + x * 3, 82 + y * 18, 18 + x * 18));
/*  58 */       x = 2; y = 1; func_75146_a(new Slot((IInventory)this.field_75181_e, y + x * 3, 82 + y * 18, 18 + x * 18));
/*  59 */       x = 0; y = 2; func_75146_a(new Slot((IInventory)this.field_75181_e, y + x * 3, 82 + y * 18, 18 + x * 18));
/*  60 */       x = 1; y = 2; func_75146_a(new Slot((IInventory)this.field_75181_e, y + x * 3, 82 + y * 18, 18 + x * 18));
/*  61 */       x = 2; y = 2; func_75146_a(new Slot((IInventory)this.field_75181_e, y + x * 3, 82 + y * 18, 18 + x * 18));
/*     */     
/*     */     }
/*     */     else {
/*     */       
/*  66 */       x = 2; int y = 0; func_75146_a(new Slot((IInventory)this.field_75181_e, y + x * 3, 82 + y * 18 - 50000, 18 + x * 18));
/*  67 */       x = 2; y = 1; func_75146_a(new Slot((IInventory)this.field_75181_e, y + x * 3, 82 + y * 18 - 50000, 18 + x * 18));
/*  68 */       x = 0; y = 2; func_75146_a(new Slot((IInventory)this.field_75181_e, y + x * 3, 82 + y * 18 - 50000, 18 + x * 18));
/*  69 */       x = 1; y = 2; func_75146_a(new Slot((IInventory)this.field_75181_e, y + x * 3, 82 + y * 18 - 50000, 18 + x * 18));
/*  70 */       x = 2; y = 2; func_75146_a(new Slot((IInventory)this.field_75181_e, y + x * 3, 82 + y * 18 - 50000, 18 + x * 18));
/*     */     } 
/*  72 */     PlayerInventory.addExtraEquipables((Container)this, playerInv, 8, 90, false);
/*  73 */     func_75130_a((IInventory)this.field_75181_e);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75130_a(IInventory iinventory) {
/*  82 */     super.func_75130_a(iinventory);
/*     */     
/*  84 */     Slot craftOut = this.field_75151_b.get(0);
/*  85 */     if (craftOut != null && craftOut.func_75216_d()) {
/*     */       
/*  87 */       ItemStack craftResult = craftOut.func_75211_c();
/*  88 */       if (craftResult != null)
/*     */       {
/*  90 */         if (craftResult.func_77973_b() instanceof com.bioxx.tfc.Food.ItemFoodTFC) {
/*  91 */           FoodCraftingHandler.updateOutput(this.thePlayer, craftResult, (IInventory)this.field_75181_e);
/*     */         } else {
/*  93 */           CraftingHandler.transferNBT(false, this.thePlayer, craftResult, (IInventory)this.field_75181_e);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75134_a(EntityPlayer player) {
/* 101 */     if (!player.field_70170_p.field_72995_K) {
/*     */       
/* 103 */       super.func_75134_a(player);
/*     */       
/* 105 */       for (int i = 0; i < 9; i++) {
/*     */         
/* 107 */         ItemStack itemstack = this.field_75181_e.func_70304_b(i);
/* 108 */         if (itemstack != null) {
/* 109 */           player.func_71019_a(itemstack, false);
/*     */         }
/*     */       } 
/* 112 */       this.field_75179_f.func_70299_a(0, (ItemStack)null);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_82846_b(EntityPlayer player, int slotNum) {
/* 119 */     ItemStack origStack = null;
/* 120 */     Slot slot = this.field_75151_b.get(slotNum);
/* 121 */     Slot equipmentSlot = this.field_75151_b.get(50);
/*     */     
/* 123 */     if (slot != null && slot.func_75216_d()) {
/*     */       
/* 125 */       ItemStack slotStack = slot.func_75211_c();
/* 126 */       origStack = slotStack.func_77946_l();
/*     */ 
/*     */       
/* 129 */       if (slotNum == 0) {
/*     */         
/* 131 */         FoodCraftingHandler.preCraft(player, slotStack, (IInventory)this.field_75181_e);
/* 132 */         CraftingHandler.preCraft(player, slotStack, (IInventory)this.field_75181_e);
/*     */         
/* 134 */         if (!mergeItemStack(slotStack, 9, 45, true, true)) {
/* 135 */           return null;
/*     */         }
/* 137 */         slot.func_75220_a(slotStack, origStack);
/*     */       
/*     */       }
/* 140 */       else if ((slotNum >= 1 && slotNum < 5) || (player.getEntityData().func_74764_b("craftingTable") && slotNum >= 45 && slotNum < 50)) {
/*     */         
/* 142 */         if (!mergeItemStack(slotStack, 9, 45, true, false)) {
/* 143 */           return null;
/*     */         }
/*     */       }
/* 146 */       else if ((slotNum >= 5 && slotNum < 9) || slotNum == 50) {
/*     */         
/* 148 */         if (!mergeItemStack(slotStack, 9, 45, true, false)) {
/* 149 */           return null;
/*     */         }
/*     */       }
/* 152 */       else if (origStack.func_77973_b() instanceof ItemArmor) {
/*     */         
/* 154 */         int armorSlotNum = 5 + ((ItemArmor)origStack.func_77973_b()).field_77881_a;
/* 155 */         if (origStack.func_77973_b() instanceof ItemTFCArmor) {
/*     */           
/* 157 */           armorSlotNum = 5 + ((ItemTFCArmor)origStack.func_77973_b()).getUnadjustedArmorType();
/*     */           
/* 159 */           if (!((Slot)this.field_75151_b.get(armorSlotNum)).func_75216_d())
/*     */           {
/* 161 */             if (!mergeItemStack(slotStack, armorSlotNum, armorSlotNum + 1, false, false)) {
/* 162 */               return null;
/*     */             }
/*     */           }
/* 165 */         } else if (!((Slot)this.field_75151_b.get(armorSlotNum)).func_75216_d()) {
/*     */           
/* 167 */           if (!mergeItemStack(slotStack, armorSlotNum, armorSlotNum + 1, false, false)) {
/* 168 */             return null;
/*     */           }
/*     */         }
/*     */       
/* 172 */       } else if (!equipmentSlot.func_75216_d() && origStack.func_77973_b() instanceof IEquipable) {
/*     */         
/* 174 */         IEquipable equipment = (IEquipable)origStack.func_77973_b();
/* 175 */         if (equipment.getEquipType(origStack) == IEquipable.EquipType.BACK && (equipment == TFCItems.quiver || equipment.getTooHeavyToCarry(origStack)))
/*     */         {
/* 177 */           ItemStack backStack = slotStack.func_77946_l();
/* 178 */           backStack.field_77994_a = 1;
/* 179 */           equipmentSlot.func_75215_d(backStack);
/* 180 */           slotStack.field_77994_a--;
/*     */         }
/*     */       
/*     */       }
/* 184 */       else if (slotNum >= 9 && slotNum < 45 && origStack.func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood && !(origStack.func_77973_b() instanceof com.bioxx.tfc.Food.ItemMeal) && !isCraftingGridFull()) {
/*     */         
/* 186 */         if (!mergeItemStack(slotStack, 1, 5, false, false) && slotStack.field_77994_a == 0)
/* 187 */           return null; 
/* 188 */         if (slotStack.field_77994_a > 0 && player.getEntityData().func_74764_b("craftingTable") && !func_75135_a(slotStack, 45, 50, false))
/* 189 */           return null; 
/* 190 */         if (slotStack.field_77994_a > 0 && slotNum >= 9 && slotNum < 36) {
/*     */           
/* 192 */           if (!mergeItemStack(slotStack, 36, 45, false, false)) {
/* 193 */             return null;
/*     */           }
/* 195 */         } else if (slotStack.field_77994_a > 0 && slotNum >= 36 && slotNum < 45) {
/*     */           
/* 197 */           if (!mergeItemStack(slotStack, 9, 36, false, false)) {
/* 198 */             return null;
/*     */           }
/*     */         }
/*     */       
/* 202 */       } else if (slotNum >= 9 && slotNum < 36) {
/*     */         
/* 204 */         if (!mergeItemStack(slotStack, 36, 45, false, false)) {
/* 205 */           return null;
/*     */         }
/*     */       }
/* 208 */       else if (slotNum >= 36 && slotNum < 45) {
/*     */         
/* 210 */         if (!mergeItemStack(slotStack, 9, 36, false, false)) {
/* 211 */           return null;
/*     */         }
/*     */       } 
/* 214 */       if (slotStack.field_77994_a <= 0) {
/* 215 */         slot.func_75215_d(null);
/*     */       } else {
/* 217 */         slot.func_75218_e();
/*     */       } 
/* 219 */       if (slotStack.field_77994_a == origStack.field_77994_a) {
/* 220 */         return null;
/*     */       }
/* 222 */       slot.func_82870_a(player, slotStack);
/*     */     } 
/*     */     
/* 225 */     return origStack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_75144_a(int sourceSlotID, int destSlotID, int clickType, EntityPlayer p) {
/* 232 */     if (sourceSlotID >= 0 && sourceSlotID < this.field_75151_b.size()) {
/*     */       
/* 234 */       Slot sourceSlot = this.field_75151_b.get(sourceSlotID);
/* 235 */       ItemStack slotStack = sourceSlot.func_75211_c();
/*     */ 
/*     */       
/* 238 */       if (clickType == 2 && sourceSlotID == 0 && slotStack != null) {
/*     */         
/* 240 */         CraftingHandler.preCraft(p, slotStack, (IInventory)this.field_75181_e);
/*     */       
/*     */       }
/* 243 */       else if (clickType == 7 && sourceSlotID >= 9 && sourceSlotID < 45) {
/*     */         
/* 245 */         if (sourceSlot.func_82869_a(p))
/*     */         {
/* 247 */           Slot destSlot = this.field_75151_b.get(destSlotID);
/* 248 */           destSlot.func_75215_d(slotStack);
/* 249 */           sourceSlot.func_75215_d(null);
/* 250 */           return null;
/*     */         }
/*     */       
/*     */       }
/* 254 */       else if (clickType == 1 && sourceSlotID == 0 && isInventoryFull() && slotStack != null && slotStack.func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood) {
/* 255 */         return null;
/*     */       } 
/* 257 */     }  return super.func_75144_a(sourceSlotID, destSlotID, clickType, p);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isCraftingGridFull() {
/* 262 */     for (int i = 0; i < this.field_75181_e.func_70302_i_(); i++) {
/*     */       
/* 264 */       if (this.field_75181_e.func_70301_a(i) == null)
/* 265 */         return false; 
/*     */     } 
/* 267 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isInventoryFull() {
/* 273 */     for (int i = 9; i < 45; i++) {
/*     */       
/* 275 */       if (((Slot)this.field_75151_b.get(i)).func_75211_c() == null)
/* 276 */         return false; 
/*     */     } 
/* 278 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityPlayer getPlayer() {
/* 283 */     return this.thePlayer;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean mergeItemStack(ItemStack is, int slotStart, int slotFinish, boolean reverseOrder, boolean craftingOutput) {
/* 288 */     boolean merged = false;
/* 289 */     int slotIndex = slotStart;
/*     */     
/* 291 */     if (reverseOrder) {
/* 292 */       slotIndex = slotFinish - 1;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 297 */     if (is.func_77985_e())
/*     */     {
/* 299 */       while (is.field_77994_a > 0 && ((!reverseOrder && slotIndex < slotFinish) || (reverseOrder && slotIndex >= slotStart))) {
/*     */         
/* 301 */         Slot slot = this.field_75151_b.get(slotIndex);
/* 302 */         ItemStack slotstack = slot.func_75211_c();
/*     */         
/* 304 */         if (slotstack != null && slotstack.func_77973_b() == is.func_77973_b() && is
/*     */           
/* 306 */           .func_77960_j() == slotstack.func_77960_j() && ItemStack.func_77970_a(is, slotstack) && slotstack.field_77994_a < slot.func_75219_a()) {
/*     */           
/* 308 */           int mergedStackSize = is.field_77994_a + getSmaller(slotstack.field_77994_a, slot.func_75219_a());
/*     */ 
/*     */           
/* 311 */           if (mergedStackSize <= is.func_77976_d() && mergedStackSize <= slot.func_75219_a()) {
/*     */             
/* 313 */             is.field_77994_a = 0;
/* 314 */             slotstack.field_77994_a = mergedStackSize;
/* 315 */             slot.func_75218_e();
/* 316 */             merged = true;
/*     */           
/*     */           }
/* 319 */           else if (!craftingOutput && slotstack.field_77994_a < is.func_77976_d() && slotstack.field_77994_a < slot.func_75219_a()) {
/*     */ 
/*     */             
/* 322 */             if (slot.func_75219_a() >= is.func_77976_d()) {
/*     */               
/* 324 */               is.field_77994_a -= is.func_77976_d() - slotstack.field_77994_a;
/* 325 */               slotstack.field_77994_a = is.func_77976_d();
/* 326 */               slot.func_75218_e();
/* 327 */               merged = true;
/*     */             
/*     */             }
/* 330 */             else if (slot.func_75219_a() < is.func_77976_d()) {
/*     */               
/* 332 */               is.field_77994_a -= slot.func_75219_a() - slotstack.field_77994_a;
/* 333 */               slotstack.field_77994_a = slot.func_75219_a();
/* 334 */               slot.func_75218_e();
/* 335 */               merged = true;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 340 */         if (reverseOrder) {
/* 341 */           slotIndex--; continue;
/*     */         } 
/* 343 */         slotIndex++;
/*     */       } 
/*     */     }
/*     */     
/* 347 */     if (is.field_77994_a > 0) {
/*     */       
/* 349 */       if (reverseOrder) {
/* 350 */         slotIndex = slotFinish - 1;
/*     */       } else {
/* 352 */         slotIndex = slotStart;
/*     */       } 
/* 354 */       while ((!reverseOrder && slotIndex < slotFinish) || (reverseOrder && slotIndex >= slotStart)) {
/*     */         
/* 356 */         Slot slot = this.field_75151_b.get(slotIndex);
/* 357 */         ItemStack slotstack = slot.func_75211_c();
/* 358 */         if (slotstack == null && slot.func_75214_a(is) && slot.func_75219_a() < is.field_77994_a) {
/*     */           
/* 360 */           ItemStack copy = is.func_77946_l();
/* 361 */           copy.field_77994_a = slot.func_75219_a();
/* 362 */           is.field_77994_a -= slot.func_75219_a();
/* 363 */           slot.func_75215_d(copy);
/* 364 */           slot.func_75218_e();
/* 365 */           merged = true;
/*     */           
/*     */           break;
/*     */         } 
/* 369 */         if (slotstack == null && slot.func_75214_a(is)) {
/*     */           
/* 371 */           slot.func_75215_d(is.func_77946_l());
/* 372 */           slot.func_75218_e();
/* 373 */           is.field_77994_a = 0;
/* 374 */           merged = true;
/*     */           
/*     */           break;
/*     */         } 
/* 378 */         if (reverseOrder) {
/* 379 */           slotIndex--; continue;
/*     */         } 
/* 381 */         slotIndex++;
/*     */       } 
/*     */     } 
/*     */     
/* 385 */     return merged;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getSmaller(int i, int j) {
/* 390 */     if (i < j) {
/* 391 */       return i;
/*     */     }
/* 393 */     return j;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\ContainerPlayerTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */