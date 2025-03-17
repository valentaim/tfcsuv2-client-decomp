/*     */ package com.bioxx.tfc.Containers;
/*     */ 
/*     */ import com.bioxx.tfc.Containers.Slots.SlotChest;
/*     */ import com.bioxx.tfc.Containers.Slots.SlotForShowOnly;
/*     */ import com.bioxx.tfc.Core.Player.PlayerInventory;
/*     */ import com.bioxx.tfc.TileEntities.TEBarrel;
/*     */ import com.bioxx.tfc.api.Enums.EnumSize;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import cpw.mods.fml.common.FMLLog;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.ICrafting;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ 
/*     */ 
/*     */ public class ContainerBarrel
/*     */   extends ContainerTFC
/*     */ {
/*     */   public TEBarrel barrel;
/*     */   public float liquidLevel;
/*     */   public int liquidID;
/*  29 */   public int sealedTime = -1;
/*     */   
/*     */   public int guiTab;
/*     */   
/*     */   public ContainerBarrel(InventoryPlayer inventoryplayer, TEBarrel tileentitybarrel, World world, int x, int y, int z, int tab) {
/*  34 */     this.barrel = tileentitybarrel;
/*  35 */     this.liquidLevel = 0.0F;
/*  36 */     this.liquidID = -1;
/*  37 */     this.guiTab = tab;
/*     */     
/*  39 */     buildLayout();
/*     */     
/*  41 */     PlayerInventory.buildInventoryLayout(this, inventoryplayer, 8, 90, false, true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<Item> getExceptions() {
/*  47 */     List<Item> exceptions = new ArrayList<>();
/*  48 */     exceptions.add(Item.func_150898_a(TFCBlocks.barrel));
/*  49 */     exceptions.add(Item.func_150898_a(TFCBlocks.vessel));
/*  50 */     return exceptions;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void buildLayout() {
/*  55 */     if (this.guiTab == 0) {
/*     */ 
/*     */       
/*  58 */       if (!this.barrel.getSealed()) {
/*  59 */         func_75146_a((Slot)(new SlotChest((IInventory)this.barrel, 0, 80, 29)).setSize(EnumSize.LARGE).addItemException(getExceptions()));
/*     */       } else {
/*  61 */         func_75146_a((Slot)new SlotForShowOnly((IInventory)this.barrel, 0, 80, 29));
/*     */       } 
/*  63 */     } else if (this.guiTab == 1) {
/*     */       
/*  65 */       for (int i = 0; i < 4; i++) {
/*     */         
/*  67 */         for (int k = 0; k < 3; k++) {
/*     */           
/*  69 */           if (!this.barrel.getSealed()) {
/*  70 */             func_75146_a((Slot)(new SlotChest((IInventory)this.barrel, k + i * 3, 53 + i * 18, 17 + k * 18)).setSize(EnumSize.LARGE).addItemException(ContainerChestTFC.getExceptions()));
/*     */           } else {
/*  72 */             func_75146_a((Slot)new SlotForShowOnly((IInventory)this.barrel, k + i * 3, 53 + i * 18, 17 + k * 18));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_75145_c(EntityPlayer entityplayer) {
/*  81 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack transferStackInSlotTFC(EntityPlayer player, int slotNum) {
/*  87 */     if (this.barrel.func_145831_w() instanceof cuchaz.ships.ShipWorld) {
/*  88 */       if (!player.field_70170_p.field_72995_K) FMLLog.getLogger().warn("Ship Duper found: " + player.getDisplayName()); 
/*  89 */       return null;
/*     */     } 
/*  91 */     ItemStack origStack = null;
/*  92 */     Slot slot = this.field_75151_b.get(slotNum);
/*     */     
/*  94 */     if (!this.barrel.getSealed() && slot != null && slot.func_75216_d()) {
/*     */       
/*  96 */       ItemStack slotStack = slot.func_75211_c();
/*  97 */       origStack = slotStack.func_77946_l();
/*     */ 
/*     */       
/* 100 */       if (slotNum < 1 && this.guiTab == 0) {
/*     */         
/* 102 */         if (!func_75135_a(slotStack, 1, this.field_75151_b.size(), true)) {
/* 103 */           return null;
/*     */         }
/*     */       }
/* 106 */       else if (slotNum < 12 && this.guiTab == 1) {
/*     */         
/* 108 */         if (!func_75135_a(slotStack, 12, this.field_75151_b.size(), true)) {
/* 109 */           return null;
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 114 */       else if (this.guiTab == 1) {
/*     */         
/* 116 */         if (!func_75135_a(slotStack, 0, 12, false)) {
/* 117 */           return null;
/*     */         }
/*     */       }
/* 120 */       else if (this.guiTab == 0) {
/*     */         
/* 122 */         if (!func_75135_a(slotStack, 0, 1, false)) {
/* 123 */           return null;
/*     */         }
/*     */       } 
/*     */       
/* 127 */       if (slotStack.field_77994_a <= 0) {
/* 128 */         slot.func_75215_d(null);
/*     */       } else {
/* 130 */         slot.func_75218_e();
/*     */       } 
/* 132 */       if (slotStack.field_77994_a == origStack.field_77994_a) {
/* 133 */         return null;
/*     */       }
/* 135 */       slot.func_82870_a(player, slotStack);
/*     */     } 
/*     */     
/* 138 */     return origStack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75142_b() {
/* 145 */     super.func_75142_b();
/*     */     
/* 147 */     for (int var1 = 0; var1 < this.field_75149_d.size() && this.guiTab == 0; var1++) {
/*     */       
/* 149 */       ICrafting var2 = this.field_75149_d.get(var1);
/*     */       
/* 151 */       if (this.barrel.getFluidStack() != null && this.liquidID != this.barrel.getFluidStack().getFluidID()) {
/*     */         
/* 153 */         this.liquidID = this.barrel.getFluidStack().getFluidID();
/* 154 */         var2.func_71112_a(this, 0, this.barrel.getFluidStack().getFluidID());
/*     */       } 
/* 156 */       if (this.liquidLevel != this.barrel.getFluidLevel()) {
/*     */         
/* 158 */         this.liquidLevel = this.barrel.getFluidLevel();
/* 159 */         var2.func_71112_a(this, 1, this.barrel.getFluidLevel());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75137_b(int id, int val) {
/* 168 */     if (id == 0) {
/*     */       
/* 170 */       if (this.barrel.fluid != null) {
/*     */         
/* 172 */         this.barrel.fluid = new FluidStack(val, this.barrel.fluid.amount);
/*     */       }
/*     */       else {
/*     */         
/* 176 */         this.barrel.fluid = new FluidStack(val, 1000);
/*     */       } 
/* 178 */       this.barrel.processItems();
/*     */     }
/* 180 */     else if (id == 1) {
/*     */       
/* 182 */       if (this.barrel.fluid != null)
/* 183 */         this.barrel.fluid.amount = val; 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\ContainerBarrel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */