/*     */ package com.bioxx.tfc.Containers;
/*     */ 
/*     */ import com.bioxx.tfc.Containers.Slots.SlotSpecialCraftingOutput;
/*     */ import com.bioxx.tfc.Core.Player.PlayerInfo;
/*     */ import com.bioxx.tfc.Core.Player.PlayerInventory;
/*     */ import com.bioxx.tfc.Core.Player.PlayerManagerTFC;
/*     */ import com.bioxx.tfc.Core.TFC_Achievements;
/*     */ import com.bioxx.tfc.Items.Tools.ItemMiscToolHead;
/*     */ import com.bioxx.tfc.api.Crafting.CraftingManagerTFC;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import fof.tfcsu.utils.ExpBonus;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.InventoryCraftResult;
/*     */ import net.minecraft.inventory.InventoryCrafting;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.stats.StatBase;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ public class ContainerSpecialCrafting
/*     */   extends ContainerTFC
/*     */ {
/*  28 */   public InventoryCrafting craftMatrix = new InventoryCrafting(this, 5, 5);
/*     */   
/*     */   private SlotSpecialCraftingOutput outputSlot;
/*     */   
/*     */   private boolean decreasedStack;
/*     */   
/*     */   private boolean gived = false;
/*  35 */   public IInventory craftResult = (IInventory)new InventoryCraftResult();
/*     */   private World worldObj;
/*     */   private InventoryPlayer invPlayer;
/*     */   private boolean isConstructing;
/*     */   
/*     */   public ContainerSpecialCrafting(InventoryPlayer inventoryplayer, ItemStack is, World world, int x, int y, int z) {
/*  41 */     this.invPlayer = inventoryplayer;
/*  42 */     this.worldObj = world;
/*  43 */     this.decreasedStack = false;
/*  44 */     this.isConstructing = true;
/*  45 */     this.bagsSlotNum = inventoryplayer.field_70461_c;
/*  46 */     for (int j1 = 0; j1 < 25; j1++) {
/*     */       
/*  48 */       if (is != null) {
/*  49 */         this.craftMatrix.func_70299_a(j1, is.func_77946_l());
/*     */       }
/*     */     } 
/*  52 */     this.outputSlot = new SlotSpecialCraftingOutput(this, inventoryplayer.field_70458_d, (IInventory)this.craftMatrix, this.craftResult, 0, 128, 44);
/*  53 */     func_75146_a((Slot)this.outputSlot);
/*     */     
/*  55 */     PlayerInventory.buildInventoryLayout(this, inventoryplayer, 8, 108, true, true);
/*     */     
/*  57 */     func_75130_a((IInventory)this.craftMatrix);
/*  58 */     this.isConstructing = false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75134_a(EntityPlayer player) {
/*  64 */     super.func_75134_a(player);
/*  65 */     if (!this.worldObj.field_72995_K) {
/*     */       
/*  67 */       ItemStack is = this.craftResult.func_70304_b(0);
/*  68 */       if (is != null) {
/*  69 */         player.func_70099_a(is, 0.0F);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75130_a(IInventory ii) {
/*  79 */     ItemStack result = CraftingManagerTFC.getInstance().findMatchingRecipe(this.craftMatrix, this.worldObj);
/*     */ 
/*     */     
/*  82 */     if (!this.decreasedStack && !this.isConstructing) {
/*     */       
/*  84 */       PlayerInfo pi = PlayerManagerTFC.getInstance().getPlayerInfoFromPlayer(this.invPlayer.field_70458_d);
/*     */ 
/*     */       
/*  87 */       if (pi.specialCraftingType.func_77973_b() == TFCItems.flatClay) {
/*     */         
/*  89 */         if (result != null) {
/*     */           
/*  91 */           setDecreasedStack(Boolean.valueOf(true));
/*  92 */           if (!this.worldObj.field_72995_K && (this.invPlayer.func_70448_g()).field_77994_a >= 5) {
/*  93 */             this.invPlayer.func_70298_a(this.invPlayer.field_70461_c, 5);
/*     */           } else {
/*     */             
/*  96 */             setDecreasedStack(Boolean.valueOf(false));
/*     */ 
/*     */             
/*     */             return;
/*     */           } 
/*     */         } 
/* 102 */       } else if (hasPieceBeenRemoved(pi)) {
/*     */         
/* 104 */         setDecreasedStack(Boolean.valueOf(true));
/* 105 */         if (!this.worldObj.field_72995_K) {
/* 106 */           this.invPlayer.func_146026_a(this.invPlayer.func_70448_g().func_77973_b());
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 111 */     if (this.decreasedStack) {
/*     */       
/* 113 */       this.craftResult.func_70299_a(0, result);
/*     */ 
/*     */       
/* 116 */       if (result != null && this.invPlayer.field_70458_d != null) {
/*     */         
/* 118 */         Item item = result.func_77973_b();
/* 119 */         if (!this.gived && !item.func_77658_a().toLowerCase().contains("stone")) { ExpBonus.KNAPPING.give(this.invPlayer.field_70458_d); this.gived = true; }
/* 120 */          if (item instanceof ItemMiscToolHead && ((ItemMiscToolHead)item).getMaterial() != null && (((ItemMiscToolHead)item)
/* 121 */           .getMaterial() == TFCItems.igInToolMaterial || ((ItemMiscToolHead)item)
/* 122 */           .getMaterial() == TFCItems.sedToolMaterial || ((ItemMiscToolHead)item)
/* 123 */           .getMaterial() == TFCItems.igExToolMaterial || ((ItemMiscToolHead)item)
/* 124 */           .getMaterial() == TFCItems.mMToolMaterial)) {
/*     */           
/* 126 */           this.invPlayer.field_70458_d.func_71029_a((StatBase)TFC_Achievements.achStoneAge);
/* 127 */           if (item == TFCItems.stoneKnifeHead && result.field_77994_a == 2) {
/* 128 */             this.invPlayer.field_70458_d.func_71029_a((StatBase)TFC_Achievements.achTwoKnives);
/*     */           }
/* 130 */         } else if (item == Item.func_150898_a(TFCBlocks.crucible)) {
/* 131 */           this.invPlayer.field_70458_d.func_71029_a((StatBase)TFC_Achievements.achCrucible);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack transferStackInSlotTFC(EntityPlayer player, int slotNum) {
/* 143 */     ItemStack origStack = null;
/* 144 */     Slot slot = this.field_75151_b.get(slotNum);
/*     */     
/* 146 */     if (slot != null && slot instanceof SlotSpecialCraftingOutput && slot.func_75216_d()) {
/*     */       
/* 148 */       ItemStack slotStack = slot.func_75211_c();
/* 149 */       origStack = slotStack.func_77946_l();
/*     */       
/* 151 */       if (slotNum < 1 && !func_75135_a(slotStack, 1, this.field_75151_b.size(), true)) {
/* 152 */         return null;
/*     */       }
/* 154 */       if (slotStack.field_77994_a <= 0) {
/* 155 */         slot.func_75215_d(null);
/*     */       } else {
/* 157 */         slot.func_75218_e();
/*     */       } 
/* 159 */       if (slotStack.field_77994_a == origStack.field_77994_a) {
/* 160 */         return null;
/*     */       }
/* 162 */       slot.func_82870_a(player, slotStack);
/*     */     } 
/*     */     
/* 165 */     return origStack;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_75145_c(EntityPlayer player) {
/* 171 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasPieceBeenRemoved(PlayerInfo pi) {
/* 177 */     for (int i = 0; i < this.craftMatrix.func_70302_i_(); i++) {
/*     */       
/* 179 */       if (this.craftMatrix.func_70301_a(i) == null) {
/* 180 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 184 */     setDecreasedStack(Boolean.valueOf(false));
/* 185 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDecreasedStack(Boolean b) {
/* 190 */     this.decreasedStack = b.booleanValue();
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\ContainerSpecialCrafting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */