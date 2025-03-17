/*     */ package com.bioxx.tfc.Containers;
/*     */ 
/*     */ import com.bioxx.tfc.Containers.Slots.SlotLiquidVessel;
/*     */ import com.bioxx.tfc.Core.Player.PlayerInventory;
/*     */ import com.bioxx.tfc.TileEntities.TECrucible;
/*     */ import com.bioxx.tfc.api.Enums.EnumSize;
/*     */ import com.bioxx.tfc.api.Interfaces.ISize;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.ICrafting;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContainerCrucible
/*     */   extends ContainerTFC
/*     */ {
/*     */   private TECrucible te;
/*     */   private float firetemp;
/*     */   
/*     */   public ContainerCrucible(InventoryPlayer inventoryplayer, TECrucible tileentityforge, World world, int x, int y, int z) {
/*  27 */     this.te = tileentityforge;
/*  28 */     this.firetemp = 0.0F;
/*     */     
/*  30 */     func_75146_a(new Slot((IInventory)tileentityforge, 0, 152, 7)
/*     */         {
/*     */           
/*     */           public boolean func_75214_a(ItemStack itemstack)
/*     */           {
/*  35 */             if (itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.ItemBlocks.ItemAnvil) return true; 
/*  36 */             if (itemstack.func_77973_b() != TFCItems.bloom && (itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.Pottery.ItemPotterySmallVessel || (itemstack.func_77973_b() instanceof ISize && (((ISize)itemstack.func_77973_b()).getSize(itemstack)).stackSize < EnumSize.MEDIUM.stackSize))) return false; 
/*  37 */             return (itemstack.func_77973_b() != TFCItems.rawBloom && (itemstack.func_77973_b() != TFCItems.bloom || itemstack.func_77960_j() <= 100));
/*     */           }
/*     */         });
/*     */     
/*  41 */     func_75146_a((Slot)new SlotLiquidVessel((IInventory)tileentityforge, 1, 152, 90));
/*     */     
/*  43 */     PlayerInventory.buildInventoryLayout(this, inventoryplayer, 8, 118, false, true);
/*     */     
/*  45 */     this.te.updateGui((byte)0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_75145_c(EntityPlayer entityplayer) {
/*  51 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack transferStackInSlotTFC(EntityPlayer player, int slotNum) {
/*  57 */     ItemStack origStack = null;
/*  58 */     Slot slot = this.field_75151_b.get(slotNum);
/*     */     
/*  60 */     if (slot != null && slot.func_75216_d()) {
/*     */       
/*  62 */       ItemStack slotStack = slot.func_75211_c();
/*  63 */       if (!(slotStack.func_77973_b() instanceof com.bioxx.tfc.Items.ItemBlocks.ItemAnvil) && 
/*  64 */         slotStack.func_77973_b() != TFCItems.bloom && (slotStack.func_77973_b() instanceof com.bioxx.tfc.Items.Pottery.ItemPotterySmallVessel || (slotStack.func_77973_b() instanceof ISize && (((ISize)slotStack.func_77973_b()).getSize(slotStack)).stackSize < EnumSize.MEDIUM.stackSize))) return null;
/*     */       
/*  66 */       origStack = slotStack.func_77946_l();
/*     */ 
/*     */       
/*  69 */       if (slotNum < 2) {
/*     */         
/*  71 */         if (!func_75135_a(slotStack, 2, this.field_75151_b.size(), true)) {
/*  72 */           return null;
/*     */         }
/*     */       }
/*  75 */       else if ((slotStack.func_77973_b() == TFCItems.ceramicMold && slotStack.func_77960_j() == 1) || slotStack.func_77973_b() instanceof com.bioxx.tfc.Items.ItemMeltedMetal) {
/*     */         
/*  77 */         if (!func_75135_a(slotStack, 1, 2, true)) {
/*  78 */           return null;
/*     */         
/*     */         }
/*     */       
/*     */       }
/*  83 */       else if (!func_75135_a(slotStack, 0, 1, true)) {
/*  84 */         return null;
/*     */       } 
/*     */       
/*  87 */       if (slotStack.field_77994_a <= 0) {
/*  88 */         slot.func_75215_d(null);
/*     */       } else {
/*  90 */         slot.func_75218_e();
/*     */       } 
/*  92 */       if (slotStack.field_77994_a == origStack.field_77994_a) {
/*  93 */         return null;
/*     */       }
/*  95 */       slot.func_82870_a(player, slotStack);
/*     */     } 
/*     */     
/*  98 */     return origStack;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75142_b() {
/* 104 */     super.func_75142_b();
/*     */     
/* 106 */     for (int var1 = 0; var1 < this.field_75149_d.size(); var1++) {
/*     */       
/* 108 */       ICrafting var2 = this.field_75149_d.get(var1);
/* 109 */       if (this.firetemp != this.te.temperature) {
/* 110 */         var2.func_71112_a(this, 0, this.te.temperature);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75137_b(int id, int value) {
/* 117 */     if (id == 0)
/* 118 */       this.te.temperature = value; 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\ContainerCrucible.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */