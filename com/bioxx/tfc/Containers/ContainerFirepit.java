/*     */ package com.bioxx.tfc.Containers;
/*     */ 
/*     */ import com.bioxx.tfc.Containers.Slots.SlotFirepit;
/*     */ import com.bioxx.tfc.Containers.Slots.SlotFirepitFuel;
/*     */ import com.bioxx.tfc.Containers.Slots.SlotFirepitIn;
/*     */ import com.bioxx.tfc.Containers.Slots.SlotFirepitOut;
/*     */ import com.bioxx.tfc.Containers.Slots.SlotForShowOnly;
/*     */ import com.bioxx.tfc.Core.Player.PlayerInventory;
/*     */ import com.bioxx.tfc.TileEntities.TEFirepit;
/*     */ import com.bioxx.tfc.api.HeatRegistry;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.ICrafting;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ public class ContainerFirepit
/*     */   extends ContainerTFC
/*     */ {
/*     */   private TEFirepit firepit;
/*     */   private float firetemp;
/*     */   
/*     */   public ContainerFirepit(InventoryPlayer inventoryplayer, TEFirepit tileentityfirepit, World world, int x, int y, int z) {
/*  30 */     this.firepit = tileentityfirepit;
/*  31 */     this.firetemp = -1111.0F;
/*     */ 
/*     */     
/*  34 */     func_75146_a((Slot)new SlotFirepitIn(inventoryplayer.field_70458_d, (IInventory)tileentityfirepit, 1, 80, 20));
/*     */     
/*  36 */     func_75146_a((Slot)new SlotFirepitFuel(inventoryplayer.field_70458_d, (IInventory)tileentityfirepit, 0, 8, 8));
/*  37 */     func_75146_a((Slot)new SlotFirepit(inventoryplayer.field_70458_d, (IInventory)tileentityfirepit, 3, 8, 26));
/*  38 */     func_75146_a((Slot)new SlotFirepit(inventoryplayer.field_70458_d, (IInventory)tileentityfirepit, 4, 8, 44));
/*  39 */     func_75146_a((Slot)new SlotFirepit(inventoryplayer.field_70458_d, (IInventory)tileentityfirepit, 5, 8, 62));
/*     */ 
/*     */     
/*  42 */     func_75146_a((Slot)new SlotFirepitOut(inventoryplayer.field_70458_d, (IInventory)tileentityfirepit, 7, 71, 48));
/*  43 */     func_75146_a((Slot)new SlotFirepitOut(inventoryplayer.field_70458_d, (IInventory)tileentityfirepit, 8, 89, 48));
/*     */ 
/*     */     
/*  46 */     func_75146_a((Slot)new SlotForShowOnly((IInventory)tileentityfirepit, 2, -50000, 0));
/*  47 */     func_75146_a((Slot)new SlotForShowOnly((IInventory)tileentityfirepit, 6, -50000, 0));
/*  48 */     func_75146_a((Slot)new SlotForShowOnly((IInventory)tileentityfirepit, 9, -50000, 0));
/*  49 */     func_75146_a((Slot)new SlotForShowOnly((IInventory)tileentityfirepit, 10, -50000, 0));
/*     */     
/*  51 */     PlayerInventory.buildInventoryLayout(this, inventoryplayer, 8, 90, false, true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_75145_c(EntityPlayer entityplayer) {
/*  57 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack transferStackInSlotTFC(EntityPlayer player, int slotNum) {
/*  63 */     ItemStack origStack = null;
/*  64 */     Slot slot = this.field_75151_b.get(slotNum);
/*  65 */     Slot slotinput = this.field_75151_b.get(0);
/*  66 */     Slot[] slotfuel = { this.field_75151_b.get(1), this.field_75151_b.get(3), this.field_75151_b.get(4), this.field_75151_b.get(5) };
/*     */     
/*  68 */     if (slot != null && slot.func_75216_d()) {
/*     */       
/*  70 */       ItemStack slotStack = slot.func_75211_c();
/*  71 */       origStack = slotStack.func_77946_l();
/*     */ 
/*     */       
/*  74 */       if (slotNum < 11) {
/*     */         
/*  76 */         if (!func_75135_a(slotStack, 11, this.field_75151_b.size(), true)) {
/*  77 */           return null;
/*     */         }
/*     */       } else {
/*     */         
/*  81 */         HeatRegistry manager = HeatRegistry.getInstance();
/*     */ 
/*     */         
/*  84 */         if (slotStack.func_77973_b() == TFCItems.logs || slotStack.func_77973_b() == Item.func_150898_a(TFCBlocks.peat)) {
/*     */           
/*  86 */           if (slotfuel[0].func_75216_d())
/*  87 */             return null; 
/*  88 */           ItemStack stack = slotStack.func_77946_l();
/*  89 */           stack.field_77994_a = 1;
/*  90 */           slotfuel[0].func_75215_d(stack);
/*  91 */           slotStack.field_77994_a--;
/*     */         
/*     */         }
/*  94 */         else if (!(slotStack.func_77973_b() instanceof com.bioxx.tfc.Items.ItemOre) && manager.findMatchingIndex(slotStack) != null) {
/*     */           
/*  96 */           if (slotinput.func_75216_d())
/*  97 */             return null; 
/*  98 */           ItemStack stack = slotStack.func_77946_l();
/*  99 */           stack.field_77994_a = 1;
/* 100 */           slotinput.func_75215_d(stack);
/* 101 */           slotStack.field_77994_a--;
/*     */         } 
/*     */       } 
/*     */       
/* 105 */       if (slotStack.field_77994_a <= 0) {
/* 106 */         slot.func_75215_d(null);
/*     */       } else {
/* 108 */         slot.func_75218_e();
/*     */       } 
/* 110 */       if (slotStack.field_77994_a == origStack.field_77994_a) {
/* 111 */         return null;
/*     */       }
/* 113 */       slot.func_82870_a(player, slotStack);
/*     */     } 
/*     */     
/* 116 */     return origStack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75142_b() {
/* 123 */     super.func_75142_b(); int var1;
/* 124 */     for (var1 = 0; var1 < this.field_75151_b.size(); var1++) {
/*     */       
/* 126 */       ItemStack var2 = ((Slot)this.field_75151_b.get(var1)).func_75211_c();
/* 127 */       ItemStack var3 = this.field_75153_a.get(var1);
/*     */       
/* 129 */       if (!ItemStack.func_77989_b(var3, var2)) {
/*     */         
/* 131 */         var3 = (var2 == null) ? null : var2.func_77946_l();
/* 132 */         this.field_75153_a.set(var1, var3);
/*     */         
/* 134 */         for (int var4 = 0; var4 < this.field_75149_d.size(); var4++) {
/* 135 */           ((ICrafting)this.field_75149_d.get(var4)).func_71111_a(this, var1, var3);
/*     */         }
/*     */       } 
/*     */     } 
/* 139 */     for (var1 = 0; var1 < this.field_75149_d.size(); var1++) {
/*     */       
/* 141 */       ICrafting var2 = this.field_75149_d.get(var1);
/* 142 */       if (this.firetemp != this.firepit.fireTemp) {
/* 143 */         var2.func_71112_a(this, 0, (int)this.firepit.fireTemp);
/*     */       }
/*     */     } 
/* 146 */     this.firetemp = this.firepit.fireTemp;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75137_b(int par1, int par2) {
/* 152 */     if (par1 == 0)
/* 153 */       this.firepit.fireTemp = par2; 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\ContainerFirepit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */