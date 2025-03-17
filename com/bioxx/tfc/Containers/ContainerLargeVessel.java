/*     */ package com.bioxx.tfc.Containers;
/*     */ 
/*     */ import com.bioxx.tfc.Containers.Slots.SlotChest;
/*     */ import com.bioxx.tfc.Containers.Slots.SlotForShowOnly;
/*     */ import com.bioxx.tfc.TileEntities.TEBarrel;
/*     */ import com.bioxx.tfc.TileEntities.TEVessel;
/*     */ import com.bioxx.tfc.api.Enums.EnumSize;
/*     */ import cpw.mods.fml.common.FMLLog;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ContainerLargeVessel
/*     */   extends ContainerBarrel
/*     */ {
/*     */   TileEntity vessel;
/*     */   
/*     */   public ContainerLargeVessel(InventoryPlayer inventoryplayer, TEVessel tileentitybarrel, World world, int x, int y, int z, int tab) {
/*  23 */     super(inventoryplayer, (TEBarrel)tileentitybarrel, world, x, y, z, tab);
/*  24 */     this.vessel = (TileEntity)tileentitybarrel;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void buildLayout() {
/*  30 */     if (this.guiTab == 0) {
/*     */ 
/*     */       
/*  33 */       if (!this.barrel.getSealed()) {
/*  34 */         func_75146_a((Slot)(new SlotChest((IInventory)this.barrel, 0, 80, 29)).setSize(EnumSize.MEDIUM).addItemException(ContainerBarrel.getExceptions()));
/*     */       } else {
/*  36 */         func_75146_a((Slot)new SlotForShowOnly((IInventory)this.barrel, 0, 80, 29));
/*     */       } 
/*  38 */     } else if (this.guiTab == 1) {
/*     */       
/*  40 */       for (int i = 0; i < 3; i++) {
/*     */         
/*  42 */         for (int k = 0; k < 3; k++) {
/*     */           
/*  44 */           if (!this.barrel.getSealed()) {
/*  45 */             func_75146_a((Slot)(new SlotChest((IInventory)this.barrel, k + i * 3, 71 + i * 18, 17 + k * 18)).setSize(EnumSize.MEDIUM).addItemException(ContainerChestTFC.getExceptions()));
/*     */           } else {
/*  47 */             func_75146_a((Slot)new SlotForShowOnly((IInventory)this.barrel, k + i * 3, 71 + i * 18, 17 + k * 18));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack transferStackInSlotTFC(EntityPlayer player, int slotNum) {
/*  56 */     if (this.vessel.func_145831_w() instanceof cuchaz.ships.ShipWorld) {
/*  57 */       if (!player.field_70170_p.field_72995_K) FMLLog.getLogger().warn("Ship Duper found: " + player.getDisplayName()); 
/*  58 */       return null;
/*     */     } 
/*  60 */     ItemStack origStack = null;
/*  61 */     Slot slot = this.field_75151_b.get(slotNum);
/*     */     
/*  63 */     if (!this.barrel.getSealed() && slot != null && slot.func_75216_d()) {
/*     */       
/*  65 */       ItemStack slotStack = slot.func_75211_c();
/*  66 */       origStack = slotStack.func_77946_l();
/*     */ 
/*     */       
/*  69 */       if (slotNum < 1 && this.guiTab == 0) {
/*     */         
/*  71 */         if (!func_75135_a(slotStack, 1, this.field_75151_b.size(), true)) {
/*  72 */           return null;
/*     */         }
/*     */       }
/*  75 */       else if (slotNum < 9 && this.guiTab == 1) {
/*     */         
/*  77 */         if (!func_75135_a(slotStack, 9, this.field_75151_b.size(), true)) {
/*  78 */           return null;
/*     */         
/*     */         }
/*     */       
/*     */       }
/*  83 */       else if (this.guiTab == 1) {
/*     */         
/*  85 */         if (!func_75135_a(slotStack, 0, 9, false)) {
/*  86 */           return null;
/*     */         }
/*     */       }
/*  89 */       else if (this.guiTab == 0) {
/*     */         
/*  91 */         if (!func_75135_a(slotStack, 0, 1, false)) {
/*  92 */           return null;
/*     */         }
/*     */       } 
/*     */       
/*  96 */       if (slotStack.field_77994_a <= 0) {
/*  97 */         slot.func_75215_d(null);
/*     */       } else {
/*  99 */         slot.func_75218_e();
/*     */       } 
/* 101 */       if (slotStack.field_77994_a == origStack.field_77994_a) {
/* 102 */         return null;
/*     */       }
/* 104 */       slot.func_82870_a(player, slotStack);
/*     */     } 
/*     */     
/* 107 */     return origStack;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\ContainerLargeVessel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */