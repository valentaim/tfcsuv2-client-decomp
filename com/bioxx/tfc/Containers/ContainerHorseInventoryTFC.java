/*     */ package com.bioxx.tfc.Containers;
/*     */ 
/*     */ import com.bioxx.tfc.Containers.Slots.ContainerHorseInventorySlotArmor;
/*     */ import com.bioxx.tfc.Containers.Slots.ContainerHorseInventorySlotSaddle;
/*     */ import com.bioxx.tfc.Containers.Slots.SlotAnimalChest;
/*     */ import com.bioxx.tfc.Core.Player.PlayerInventory;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntityHorseTFC;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.passive.EntityHorse;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ 
/*     */ public class ContainerHorseInventoryTFC
/*     */   extends ContainerTFC {
/*     */   private IInventory horseInv;
/*     */   private EntityHorseTFC horse;
/*     */   
/*     */   public ContainerHorseInventoryTFC(InventoryPlayer invPlayer, IInventory invHorse, EntityHorseTFC entityHorse) {
/*  22 */     this.horseInv = invHorse;
/*  23 */     this.horse = entityHorse;
/*  24 */     invHorse.func_70295_k_();
/*  25 */     func_75146_a((Slot)new ContainerHorseInventorySlotSaddle(this, invHorse, 0, 8, 18));
/*  26 */     func_75146_a((Slot)new ContainerHorseInventorySlotArmor(this, this.horseInv, 1, 8, 36, (EntityHorse)this.horse));
/*     */     
/*  28 */     if (entityHorse.func_110261_ca())
/*     */     {
/*     */       
/*  31 */       for (int j = 0; j < 3; j++) {
/*     */         
/*  33 */         for (int k = 0; k < 5; k++)
/*     */         {
/*     */           
/*  36 */           func_75146_a((Slot)(new SlotAnimalChest(invHorse, 2 + k + j * 5, 80 + k * 18, 18 + j * 18)).addItemException(ContainerChestTFC.getExceptions()));
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*  41 */     PlayerInventory.buildInventoryLayout(this, invPlayer, 8, 90, false, true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_75145_c(EntityPlayer player) {
/*  47 */     return (this.horseInv.func_70300_a(player) && this.horse.func_70089_S() && this.horse.func_70032_d((Entity)player) < 8.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack transferStackInSlotTFC(EntityPlayer player, int slotNum) {
/*  56 */     ItemStack origStack = null;
/*  57 */     Slot slot = this.field_75151_b.get(slotNum);
/*     */     
/*  59 */     if (slot != null && slot.func_75216_d()) {
/*     */       
/*  61 */       ItemStack slotStack = slot.func_75211_c();
/*  62 */       origStack = slotStack.func_77946_l();
/*  63 */       int inventorySize = this.horseInv.func_70302_i_();
/*     */       
/*  65 */       if (slotNum < inventorySize) {
/*     */         
/*  67 */         if (!func_75135_a(slotStack, inventorySize, this.field_75151_b.size(), true))
/*     */         {
/*  69 */           return null;
/*     */         }
/*     */       }
/*  72 */       else if (func_75139_a(1).func_75214_a(slotStack) && !func_75139_a(1).func_75216_d()) {
/*     */         
/*  74 */         if (!func_75135_a(slotStack, 1, 2, false))
/*     */         {
/*  76 */           return null;
/*     */         }
/*     */       }
/*  79 */       else if (func_75139_a(0).func_75214_a(slotStack)) {
/*     */         
/*  81 */         if (!func_75135_a(slotStack, 0, 1, false))
/*     */         {
/*  83 */           return null;
/*     */         }
/*     */       }
/*  86 */       else if (inventorySize <= 2 || !func_75135_a(slotStack, 2, inventorySize, false)) {
/*     */         
/*  88 */         return null;
/*     */       } 
/*     */       
/*  91 */       if (slotStack.field_77994_a <= 0) {
/*  92 */         slot.func_75215_d(null);
/*     */       } else {
/*  94 */         slot.func_75218_e();
/*     */       } 
/*  96 */       if (slotStack.field_77994_a == origStack.field_77994_a) {
/*  97 */         return null;
/*     */       }
/*  99 */       slot.func_82870_a(player, slotStack);
/*     */     } 
/*     */     
/* 102 */     return origStack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75134_a(EntityPlayer player) {
/* 111 */     super.func_75134_a(player);
/* 112 */     this.horseInv.func_70305_f();
/* 113 */     this.horse.updateChestSaddle();
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\ContainerHorseInventoryTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */