/*     */ package com.bioxx.tfc.Containers;
/*     */ 
/*     */ import com.bioxx.tfc.Containers.Slots.SlotSluice;
/*     */ import com.bioxx.tfc.Core.Player.PlayerInventory;
/*     */ import com.bioxx.tfc.TileEntities.TESluice;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.ICrafting;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ContainerSluice
/*     */   extends ContainerTFC
/*     */ {
/*     */   private TESluice sluice;
/*     */   private EntityPlayer player;
/*     */   private int soilamt;
/*     */   private int progress;
/*     */   
/*     */   public ContainerSluice(InventoryPlayer inventoryplayer, TESluice tileentitysluice, World world, int x, int y, int z) {
/*  23 */     this.sluice = tileentitysluice;
/*  24 */     this.player = inventoryplayer.field_70458_d;
/*  25 */     func_75146_a((Slot)new SlotSluice(this.player, (IInventory)this.sluice, 0, 116, 16));
/*  26 */     func_75146_a((Slot)new SlotSluice(this.player, (IInventory)this.sluice, 1, 134, 16));
/*  27 */     func_75146_a((Slot)new SlotSluice(this.player, (IInventory)this.sluice, 2, 152, 16));
/*  28 */     func_75146_a((Slot)new SlotSluice(this.player, (IInventory)this.sluice, 3, 116, 34));
/*  29 */     func_75146_a((Slot)new SlotSluice(this.player, (IInventory)this.sluice, 4, 134, 34));
/*  30 */     func_75146_a((Slot)new SlotSluice(this.player, (IInventory)this.sluice, 5, 152, 34));
/*  31 */     func_75146_a((Slot)new SlotSluice(this.player, (IInventory)this.sluice, 6, 116, 52));
/*  32 */     func_75146_a((Slot)new SlotSluice(this.player, (IInventory)this.sluice, 7, 134, 52));
/*  33 */     func_75146_a((Slot)new SlotSluice(this.player, (IInventory)this.sluice, 8, 152, 52));
/*  34 */     PlayerInventory.buildInventoryLayout(this, inventoryplayer, 8, 90, false, true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_75145_c(EntityPlayer entityplayer) {
/*  40 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack transferStackInSlotTFC(EntityPlayer player, int slotNum) {
/*  46 */     ItemStack origStack = null;
/*  47 */     Slot slot = this.field_75151_b.get(slotNum);
/*     */     
/*  49 */     if (slot != null && slot.func_75216_d()) {
/*     */       
/*  51 */       ItemStack slotStack = slot.func_75211_c();
/*  52 */       origStack = slotStack.func_77946_l();
/*     */ 
/*     */       
/*  55 */       if (slotNum < 9)
/*     */       {
/*  57 */         if (!func_75135_a(slotStack, 9, this.field_75151_b.size(), true)) {
/*  58 */           return null;
/*     */         }
/*     */       }
/*  61 */       if (slotStack.field_77994_a <= 0) {
/*  62 */         slot.func_75215_d(null);
/*     */       } else {
/*  64 */         slot.func_75218_e();
/*     */       } 
/*  66 */       if (slotStack.field_77994_a == origStack.field_77994_a) {
/*  67 */         return null;
/*     */       }
/*  69 */       slot.func_82870_a(player, slotStack);
/*     */     } 
/*     */     
/*  72 */     return origStack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75142_b() {
/*  79 */     super.func_75142_b(); int var1;
/*  80 */     for (var1 = 0; var1 < this.field_75151_b.size(); var1++) {
/*     */       
/*  82 */       ItemStack var2 = ((Slot)this.field_75151_b.get(var1)).func_75211_c();
/*  83 */       ItemStack var3 = this.field_75153_a.get(var1);
/*     */       
/*  85 */       if (!ItemStack.func_77989_b(var3, var2)) {
/*     */         
/*  87 */         var3 = (var2 == null) ? null : var2.func_77946_l();
/*  88 */         this.field_75153_a.set(var1, var3);
/*     */         
/*  90 */         for (int var4 = 0; var4 < this.field_75149_d.size(); var4++)
/*  91 */           ((ICrafting)this.field_75149_d.get(var4)).func_71111_a(this, var1, var3); 
/*     */       } 
/*     */     } 
/*  94 */     for (var1 = 0; var1 < this.field_75149_d.size(); var1++) {
/*     */       
/*  96 */       ICrafting var2 = this.field_75149_d.get(var1);
/*  97 */       if (this.soilamt != this.sluice.soilAmount)
/*  98 */         var2.func_71112_a(this, 0, this.sluice.soilAmount); 
/*  99 */       if (this.progress != this.sluice.processTimeRemaining) {
/* 100 */         var2.func_71112_a(this, 1, this.sluice.processTimeRemaining);
/*     */       }
/*     */     } 
/* 103 */     this.soilamt = this.sluice.soilAmount;
/* 104 */     this.progress = this.sluice.processTimeRemaining;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75137_b(int par1, int par2) {
/* 110 */     if (par1 == 0)
/* 111 */       this.sluice.soilAmount = par2; 
/* 112 */     if (par1 == 1)
/* 113 */       this.sluice.processTimeRemaining = par2; 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\ContainerSluice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */