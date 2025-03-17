/*     */ package com.bioxx.tfc.Core.Player;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ 
/*     */ public class InventoryPlayerTFC extends InventoryPlayer {
/*  15 */   public ItemStack[] extraEquipInventory = new ItemStack[TFC_Core.getExtraEquipInventorySize()];
/*     */   
/*     */   public InventoryPlayerTFC(EntityPlayer par1EntityPlayer) {
/*  18 */     super(par1EntityPlayer);
/*  19 */     this.field_70458_d = par1EntityPlayer;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70449_g(float par1) {
/*  25 */     par1 /= 4.0F;
/*  26 */     if (par1 < 1.0F) {
/*  27 */       par1 = 1.0F;
/*     */     }
/*  29 */     for (int i = 0; i < this.field_70460_b.length; i++) {
/*     */       
/*  31 */       if (this.field_70460_b[i] != null && this.field_70460_b[i].func_77973_b() instanceof net.minecraft.item.ItemArmor) {
/*     */         
/*  33 */         this.field_70460_b[i].func_77972_a((int)par1, (EntityLivingBase)this.field_70458_d);
/*  34 */         if ((this.field_70460_b[i]).field_77994_a == 0) {
/*  35 */           this.field_70460_b[i] = null;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_70302_i_() {
/*  43 */     return this.field_70462_a.length + this.field_70460_b.length + this.extraEquipInventory.length;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70443_b(NBTTagList par1NBTTagList) {
/*  49 */     super.func_70443_b(par1NBTTagList);
/*  50 */     this.extraEquipInventory = new ItemStack[TFC_Core.getExtraEquipInventorySize()];
/*     */     
/*  52 */     NBTTagList extraList = this.field_70458_d.getEntityData().func_150295_c("ExtraInventory", 10);
/*     */     
/*  54 */     for (int i = 0; i < extraList.func_74745_c(); i++) {
/*     */       
/*  56 */       NBTTagCompound nbttagcompound = extraList.func_150305_b(i);
/*  57 */       ItemStack itemstack = ItemStack.func_77949_a(nbttagcompound);
/*  58 */       if (itemstack != null)
/*     */       {
/*  60 */         this.extraEquipInventory[i] = itemstack;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70301_a(int par1) {
/*  71 */     ItemStack[] aitemstack = this.field_70462_a;
/*     */     
/*  73 */     if (par1 >= aitemstack.length + this.field_70460_b.length) {
/*     */       
/*  75 */       par1 -= aitemstack.length + this.field_70460_b.length;
/*  76 */       aitemstack = this.extraEquipInventory;
/*  77 */     } else if (par1 >= aitemstack.length) {
/*     */       
/*  79 */       par1 -= aitemstack.length;
/*  80 */       aitemstack = this.field_70460_b;
/*     */     } 
/*  82 */     return aitemstack[par1];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70304_b(int par1) {
/*  88 */     ItemStack[] aitemstack = this.field_70462_a;
/*     */     
/*  90 */     if (par1 >= aitemstack.length + this.field_70460_b.length) {
/*     */       
/*  92 */       par1 -= aitemstack.length + this.field_70460_b.length;
/*  93 */       aitemstack = this.extraEquipInventory;
/*  94 */     } else if (par1 >= aitemstack.length) {
/*     */       
/*  96 */       par1 -= aitemstack.length;
/*  97 */       aitemstack = this.field_70460_b;
/*     */     } 
/*  99 */     if (aitemstack[par1] != null) {
/*     */       
/* 101 */       ItemStack itemstack = aitemstack[par1];
/* 102 */       aitemstack[par1] = null;
/* 103 */       return itemstack;
/*     */     } 
/*     */ 
/*     */     
/* 107 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_146027_a(Item item, int meta) {
/* 114 */     for (int i = 0; i < this.extraEquipInventory.length; i++) {
/*     */       
/* 116 */       if (this.extraEquipInventory[i] != null && (item == null || this.extraEquipInventory[i].func_77973_b() == item) && (meta <= -1 || this.extraEquipInventory[i]
/* 117 */         .func_77960_j() == meta))
/*     */       {
/* 119 */         this.extraEquipInventory[i] = null;
/*     */       }
/*     */     } 
/* 122 */     return super.func_146027_a(item, meta);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70429_k() {
/* 128 */     for (int i = 0; i < this.extraEquipInventory.length; i++) {
/*     */       
/* 130 */       if (this.extraEquipInventory[i] != null)
/*     */       {
/* 132 */         this.extraEquipInventory[i].func_77945_a(this.field_70458_d.field_70170_p, (Entity)this.field_70458_d, i, (this.field_70461_c == i));
/*     */       }
/*     */     } 
/* 135 */     super.func_70429_k();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70298_a(int par1, int par2) {
/* 141 */     ItemStack[] aitemstack = this.field_70462_a;
/*     */     
/* 143 */     if (par1 >= aitemstack.length + this.field_70460_b.length) {
/*     */       
/* 145 */       par1 -= aitemstack.length + this.field_70460_b.length;
/* 146 */       aitemstack = this.extraEquipInventory;
/* 147 */     } else if (par1 >= aitemstack.length) {
/*     */       
/* 149 */       par1 -= aitemstack.length;
/* 150 */       aitemstack = this.field_70460_b;
/*     */     } 
/*     */ 
/*     */     
/* 154 */     if (aitemstack[par1] != null) {
/*     */ 
/*     */ 
/*     */       
/* 158 */       if ((aitemstack[par1]).field_77994_a <= par2) {
/*     */         
/* 160 */         ItemStack itemStack = aitemstack[par1];
/* 161 */         aitemstack[par1] = null;
/* 162 */         return itemStack;
/*     */       } 
/*     */ 
/*     */       
/* 166 */       ItemStack itemstack = aitemstack[par1].func_77979_a(par2);
/*     */       
/* 168 */       if ((aitemstack[par1]).field_77994_a == 0)
/*     */       {
/* 170 */         aitemstack[par1] = null;
/*     */       }
/*     */       
/* 173 */       return itemstack;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 178 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70436_m() {
/* 187 */     for (int i = 0; i < this.extraEquipInventory.length; i++) {
/*     */       
/* 189 */       if (this.extraEquipInventory[i] != null) {
/*     */         
/* 191 */         this.field_70458_d.func_146097_a(this.extraEquipInventory[i], true, false);
/* 192 */         this.extraEquipInventory[i] = null;
/*     */       } 
/*     */     } 
/* 195 */     super.func_70436_m();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70431_c(ItemStack par1ItemStack) {
/* 203 */     for (int i = 0; i < this.extraEquipInventory.length; i++) {
/*     */       
/* 205 */       if (this.extraEquipInventory[i] != null && this.extraEquipInventory[i].func_77969_a(par1ItemStack))
/*     */       {
/* 207 */         return true;
/*     */       }
/*     */     } 
/* 210 */     return super.func_70431_c(par1ItemStack);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70299_a(int par1, ItemStack par2ItemStack) {
/* 217 */     ItemStack[] aitemstack = this.field_70462_a;
/*     */     
/* 219 */     if (par1 >= aitemstack.length + this.field_70460_b.length) {
/*     */       
/* 221 */       par1 -= aitemstack.length + this.field_70460_b.length;
/* 222 */       aitemstack = this.extraEquipInventory;
/* 223 */     } else if (par1 >= aitemstack.length) {
/*     */       
/* 225 */       par1 -= aitemstack.length;
/* 226 */       aitemstack = this.field_70460_b;
/*     */     } 
/*     */     
/* 229 */     aitemstack[par1] = par2ItemStack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70455_b(InventoryPlayer par1InventoryPlayer) {
/* 242 */     if (par1InventoryPlayer instanceof InventoryPlayerTFC) {
/* 243 */       copyInventoryTFC((InventoryPlayerTFC)par1InventoryPlayer);
/*     */     } else {
/*     */       
/* 246 */       super.func_70455_b(par1InventoryPlayer);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void copyInventoryTFC(InventoryPlayerTFC par1InventoryPlayer) {
/* 254 */     for (int i = 0; i < this.extraEquipInventory.length; i++)
/*     */     {
/* 256 */       this.extraEquipInventory[i] = ItemStack.func_77944_b(par1InventoryPlayer.extraEquipInventory[i]);
/*     */     }
/* 258 */     super.func_70455_b(par1InventoryPlayer);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagList func_70442_a(NBTTagList par1NBTTagList) {
/* 264 */     super.func_70442_a(par1NBTTagList);
/*     */ 
/*     */ 
/*     */     
/* 268 */     NBTTagList tagList = new NBTTagList();
/* 269 */     for (int i = 0; i < this.extraEquipInventory.length; i++) {
/*     */       
/* 271 */       ItemStack is = this.extraEquipInventory[i];
/* 272 */       if (is != null) {
/*     */         
/* 274 */         NBTTagCompound nbt = new NBTTagCompound();
/* 275 */         nbt.func_74774_a("Slot", (byte)i);
/* 276 */         is.func_77955_b(nbt);
/* 277 */         tagList.func_74742_a((NBTBase)nbt);
/*     */       } 
/*     */     } 
/* 280 */     this.field_70458_d.getEntityData().func_74782_a("ExtraInventory", (NBTBase)tagList);
/* 281 */     return par1NBTTagList;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\Player\InventoryPlayerTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */