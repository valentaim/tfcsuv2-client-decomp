/*     */ package com.bioxx.tfc.Containers;
/*     */ 
/*     */ import com.bioxx.tfc.Containers.Slots.SlotChest;
/*     */ import com.bioxx.tfc.Core.Player.PlayerInventory;
/*     */ import com.bioxx.tfc.TileEntities.TEChest;
/*     */ import com.bioxx.tfc.TileEntities.TEIngotPile;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import cpw.mods.fml.common.registry.GameData;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.InventoryLargeChest;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContainerChestTFC
/*     */   extends ContainerTFC
/*     */ {
/*     */   private IInventory lowerChestInventory;
/*     */   private int numRows;
/*     */   
/*     */   public ContainerChestTFC(IInventory playerInv, IInventory chestInv, World world, int x, int y, int z) {
/*  30 */     TEChest chest = (TEChest)chestInv;
/*  31 */     this.lowerChestInventory = chestInv;
/*     */     
/*  33 */     if (chest.field_145991_k != null) {
/*  34 */       this.lowerChestInventory = (IInventory)new InventoryLargeChest("Large chest", (IInventory)chest.field_145991_k, chestInv);
/*     */     }
/*  36 */     if (chest.field_145990_j != null) {
/*  37 */       this.lowerChestInventory = (IInventory)new InventoryLargeChest("Large chest", chestInv, (IInventory)chest.field_145990_j);
/*     */     }
/*  39 */     if (chest.field_145992_i != null) {
/*  40 */       this.lowerChestInventory = (IInventory)new InventoryLargeChest("Large chest", (IInventory)chest.field_145992_i, chestInv);
/*     */     }
/*  42 */     if (chest.field_145988_l != null) {
/*  43 */       this.lowerChestInventory = (IInventory)new InventoryLargeChest("Large chest", chestInv, (IInventory)chest.field_145988_l);
/*     */     }
/*  45 */     this.numRows = this.lowerChestInventory.func_70302_i_() / 9;
/*  46 */     this.lowerChestInventory.func_70295_k_();
/*  47 */     int var3 = (this.numRows - 4) * 18;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  53 */     for (int var4 = 0; var4 < this.numRows; var4++) {
/*     */       
/*  55 */       for (int var5 = 0; var5 < 9; var5++)
/*     */       {
/*  57 */         func_75146_a((Slot)(new SlotChest(this.lowerChestInventory, var5 + var4 * 9, 8 + var5 * 18, 18 + var4 * 18)).addItemException(getExceptions()));
/*     */       }
/*     */     } 
/*     */     
/*  61 */     PlayerInventory.buildInventoryLayout(this, (InventoryPlayer)playerInv, 8, var3 + 109, false, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<Item> getExceptions() {
/*  66 */     List<Item> exceptions = new ArrayList<>();
/*  67 */     for (Item ingot : TEIngotPile.getIngots())
/*     */     {
/*  69 */       exceptions.add(ingot);
/*     */     }
/*  71 */     exceptions.add(TFCItems.logs);
/*  72 */     exceptions.add(Item.func_150898_a(TFCBlocks.barrel));
/*  73 */     exceptions.add(Item.func_150898_a(TFCBlocks.vessel));
/*     */     
/*  75 */     Item fromcook = (Item)GameData.getItemRegistry().func_82594_a("cookingwithtfc:item.Log");
/*  76 */     if (fromcook != null) exceptions.add(fromcook); 
/*  77 */     return exceptions;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_75145_c(EntityPlayer par1EntityPlayer) {
/*  83 */     return this.lowerChestInventory.func_70300_a(par1EntityPlayer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack transferStackInSlotTFC(EntityPlayer player, int slotNum) {
/*  92 */     ItemStack origStack = null;
/*  93 */     Slot slot = this.field_75151_b.get(slotNum);
/*     */     
/*  95 */     if (slot != null && slot.func_75216_d()) {
/*     */       
/*  97 */       ItemStack slotStack = slot.func_75211_c();
/*  98 */       origStack = slotStack.func_77946_l();
/*  99 */       int chestSlotCount = this.numRows * 9;
/*     */ 
/*     */       
/* 102 */       if (slotNum < chestSlotCount) {
/*     */         
/* 104 */         if (!func_75135_a(slotStack, chestSlotCount, this.field_75151_b.size(), true)) {
/* 105 */           return null;
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 110 */       else if (!func_75135_a(slotStack, 0, chestSlotCount, false)) {
/* 111 */         return null;
/*     */       } 
/*     */       
/* 114 */       if (slotStack.field_77994_a <= 0) {
/* 115 */         slot.func_75215_d(null);
/*     */       } else {
/* 117 */         slot.func_75218_e();
/*     */       } 
/* 119 */       if (slotStack.field_77994_a == origStack.field_77994_a) {
/* 120 */         return null;
/*     */       }
/* 122 */       slot.func_82870_a(player, slotStack);
/*     */     } 
/*     */     
/* 125 */     return origStack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75134_a(EntityPlayer par1EntityPlayer) {
/* 134 */     super.func_75134_a(par1EntityPlayer);
/* 135 */     this.lowerChestInventory.func_70305_f();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IInventory getLowerChestInventory() {
/* 143 */     return this.lowerChestInventory;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\ContainerChestTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */