/*     */ package com.bioxx.tfc.Containers;
/*     */ 
/*     */ import com.bioxx.tfc.Containers.Slots.SlotBlocked;
/*     */ import com.bioxx.tfc.Containers.Slots.SlotMoldTool;
/*     */ import com.bioxx.tfc.Containers.Slots.SlotMoldTool2;
/*     */ import com.bioxx.tfc.Core.Player.PlayerInfo;
/*     */ import com.bioxx.tfc.Core.Player.PlayerInventory;
/*     */ import com.bioxx.tfc.Core.Player.PlayerManagerTFC;
/*     */ import com.bioxx.tfc.api.Crafting.CraftingManagerTFC;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import com.bioxx.tfc.api.TFC_ItemHeat;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.ICrafting;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.InventoryCraftResult;
/*     */ import net.minecraft.inventory.InventoryCrafting;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContainerMold
/*     */   extends ContainerTFC
/*     */ {
/*     */   private World world;
/*     */   private EntityPlayer player;
/*  32 */   public InventoryCrafting containerInv = new InventoryCrafting(this, 2, 1);
/*  33 */   public IInventory craftResult = (IInventory)new InventoryCraftResult();
/*     */ 
/*     */   
/*     */   public ContainerMold(InventoryPlayer playerinv, World world, int x, int y, int z) {
/*  37 */     this.player = playerinv.field_70458_d;
/*  38 */     this.world = world;
/*     */ 
/*     */ 
/*     */     
/*  42 */     layoutContainer((IInventory)playerinv, 0, 0);
/*  43 */     PlayerInventory.buildInventoryLayout(this, playerinv, 8, 90, false, true);
/*  44 */     PlayerInfo pi = PlayerManagerTFC.getInstance().getPlayerInfoFromPlayer(playerinv.field_70458_d);
/*  45 */     this.containerInv.func_70299_a(0, pi.specialCraftingType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75134_a(EntityPlayer player) {
/*  54 */     super.func_75134_a(player);
/*  55 */     if (!this.world.field_72995_K) {
/*     */       
/*  57 */       ItemStack itemstack = this.craftResult.func_70304_b(0);
/*  58 */       ItemStack itemstack2 = this.containerInv.func_70304_b(0);
/*  59 */       ItemStack itemstack3 = this.containerInv.func_70304_b(1);
/*  60 */       if (itemstack != null)
/*  61 */         player.func_70099_a(itemstack, 0.0F); 
/*  62 */       if (itemstack2 != null)
/*  63 */         player.func_70099_a(itemstack2, 0.0F); 
/*  64 */       if (itemstack3 != null) {
/*  65 */         player.func_70099_a(itemstack3, 0.0F);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_75145_c(EntityPlayer var1) {
/*  72 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void layoutContainer(IInventory playerInventory, int xSize, int ySize) {
/*  77 */     func_75146_a((Slot)new SlotMoldTool((IInventory)this.containerInv, 0, 41, 34));
/*  78 */     func_75146_a((Slot)new SlotMoldTool2((IInventory)this.containerInv, 1, 94, 34));
/*  79 */     func_75146_a((Slot)new SlotBlocked(this.craftResult, 0, 116, 34));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75137_b(int id, int value) {
/*  85 */     if (id == 0) {
/*     */       
/*  87 */       PlayerInfo pi = PlayerManagerTFC.getInstance().getPlayerInfoFromPlayer(this.player);
/*  88 */       pi.moldTransferTimer = (short)value;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75142_b() {
/*  95 */     super.func_75142_b();
/*  96 */     if (this.craftResult.func_70301_a(0) == null) {
/*     */       
/*  98 */       PlayerInfo pi = PlayerManagerTFC.getInstance().getPlayerInfoFromPlayer(this.player);
/*  99 */       short oldTransferTimer = pi.moldTransferTimer;
/* 100 */       ItemStack sourceStack = this.containerInv.func_70301_a(0);
/* 101 */       ItemStack inputStack = this.containerInv.func_70301_a(1);
/*     */       
/* 103 */       if (sourceStack != null && inputStack != null) {
/*     */         
/* 105 */         Item sourceItem = sourceStack.func_77973_b();
/* 106 */         Item inputItem = inputStack.func_77973_b();
/* 107 */         int inputDamage = inputStack.func_77960_j();
/*     */         
/* 109 */         if (sourceItem instanceof com.bioxx.tfc.Items.ItemMeltedMetal && inputItem == TFCItems.ceramicMold && inputDamage == 1 && TFC_ItemHeat.getIsLiquid(sourceStack).booleanValue()) {
/*     */           
/* 111 */           ItemStack is = sourceStack.func_77946_l();
/* 112 */           is.func_77964_b(100);
/* 113 */           this.containerInv.func_70299_a(1, is);
/* 114 */           pi.moldTransferTimer = 100;
/*     */         }
/* 116 */         else if (sourceItem instanceof com.bioxx.tfc.Items.ItemMeltedMetal && inputItem instanceof com.bioxx.tfc.Items.ItemMeltedMetal && sourceItem == inputItem && inputDamage != 0) {
/*     */           
/* 118 */           pi.moldTransferTimer = 100;
/*     */         } 
/*     */       } 
/*     */       
/* 122 */       if (inputStack != null && pi.moldTransferTimer < 100 && 
/* 123 */         CraftingManagerTFC.getInstance().findMatchingRecipe(this.containerInv, this.world) != null)
/*     */       {
/* 125 */         pi.moldTransferTimer = (short)(pi.moldTransferTimer + 1);
/*     */       }
/*     */       
/* 128 */       if (sourceStack != null && inputStack != null && pi.moldTransferTimer == 1000)
/*     */       {
/* 130 */         pi.moldTransferTimer = 0;
/*     */       }
/*     */       
/* 133 */       if (sourceStack == null || inputStack == null)
/*     */       {
/* 135 */         pi.moldTransferTimer = 1000;
/*     */       }
/*     */       
/* 138 */       if (sourceStack != null && inputStack != null && pi.moldTransferTimer == 100) {
/*     */         
/* 140 */         Item sourceItem = sourceStack.func_77973_b();
/* 141 */         Item inputItem = inputStack.func_77973_b();
/* 142 */         int newSourceDamage = sourceStack.func_77960_j() + 1;
/* 143 */         int inputDamage = inputStack.func_77960_j();
/* 144 */         ItemStack recipeOutput = CraftingManagerTFC.getInstance().findMatchingRecipe(this.containerInv, this.world);
/*     */         
/* 146 */         if (sourceItem instanceof com.bioxx.tfc.Items.ItemMeltedMetal && inputItem instanceof com.bioxx.tfc.Items.ItemMeltedMetal) {
/*     */           
/* 148 */           if (sourceItem == inputItem && inputDamage != 0)
/*     */           {
/* 150 */             sourceStack.func_77964_b(newSourceDamage);
/* 151 */             inputStack.func_77964_b(inputDamage - 1);
/* 152 */             if (newSourceDamage >= sourceStack.func_77958_k() - 1)
/*     */             {
/* 154 */               this.containerInv.func_70299_a(0, new ItemStack(TFCItems.ceramicMold, 1, 1));
/*     */             }
/*     */           }
/*     */         
/* 158 */         } else if (recipeOutput != null) {
/*     */           
/* 160 */           recipeOutput.func_77982_d(inputStack.field_77990_d);
/* 161 */           this.craftResult.func_70299_a(0, recipeOutput);
/* 162 */           this.containerInv.func_70299_a(1, null);
/* 163 */           this.containerInv.func_70299_a(1, new ItemStack(TFCItems.ceramicMold, 1, 1));
/* 164 */           this.containerInv.func_70299_a(0, null);
/*     */         } 
/*     */       } 
/*     */       
/* 168 */       for (int i = 0; i < this.field_75149_d.size(); i++) {
/*     */         
/* 170 */         ICrafting player = this.field_75149_d.get(i);
/* 171 */         if (pi.moldTransferTimer != oldTransferTimer) {
/* 172 */           player.func_71112_a(this, 0, pi.moldTransferTimer);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack transferStackInSlotTFC(EntityPlayer player, int slotNum) {
/* 180 */     ItemStack origStack = null;
/* 181 */     Slot slot = this.field_75151_b.get(slotNum);
/*     */     
/* 183 */     if (slot != null && slot.func_75216_d()) {
/*     */       
/* 185 */       ItemStack slotStack = slot.func_75211_c();
/* 186 */       origStack = slotStack.func_77946_l();
/*     */ 
/*     */       
/* 189 */       if (slotNum < 3) {
/*     */         
/* 191 */         if (!func_75135_a(slotStack, 3, this.field_75151_b.size(), true)) {
/* 192 */           return null;
/*     */         
/*     */         }
/*     */       }
/* 196 */       else if (!func_75135_a(slotStack, 0, 3, false)) {
/* 197 */         return null;
/*     */       } 
/*     */       
/* 200 */       if (slotStack.field_77994_a <= 0) {
/* 201 */         slot.func_75215_d(null);
/*     */       } else {
/* 203 */         slot.func_75218_e();
/*     */       } 
/* 205 */       if (slotStack.field_77994_a == origStack.field_77994_a) {
/* 206 */         return null;
/*     */       }
/* 208 */       slot.func_82870_a(player, slotStack);
/*     */     } 
/*     */     
/* 211 */     return origStack;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\ContainerMold.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */