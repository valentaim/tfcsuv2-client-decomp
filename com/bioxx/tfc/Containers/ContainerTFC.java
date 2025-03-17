/*     */ package com.bioxx.tfc.Containers;
/*     */ 
/*     */ import com.bioxx.tfc.api.TFC_ItemHeat;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.ICrafting;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContainerTFC
/*     */   extends Container
/*     */ {
/*     */   public int bagsSlotNum;
/*     */   public EntityPlayer player;
/*     */   protected boolean isLoading;
/*     */   protected boolean doItemSaving;
/*     */   
/*     */   public boolean func_75145_c(EntityPlayer var1) {
/*  23 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveContents(ItemStack is) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack loadContents(int slot) {
/*  39 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_75144_a(int par1, int par2, int par3, EntityPlayer par4EntityPlayer) {
/*  45 */     ItemStack is = super.func_75144_a(par1, par2, par3, par4EntityPlayer);
/*  46 */     saveContents(is);
/*  47 */     return is;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean func_75135_a(ItemStack is, int slotStart, int slotFinish, boolean par4) {
/*  53 */     boolean merged = false;
/*  54 */     int slotIndex = slotStart;
/*     */     
/*  56 */     if (par4) {
/*  57 */       slotIndex = slotFinish - 1;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  62 */     if (is.func_77985_e())
/*     */     {
/*  64 */       while (is.field_77994_a > 0 && ((!par4 && slotIndex < slotFinish) || (par4 && slotIndex >= slotStart))) {
/*     */         
/*  66 */         Slot slot = this.field_75151_b.get(slotIndex);
/*  67 */         ItemStack slotstack = slot.func_75211_c();
/*     */         
/*  69 */         if (slotstack != null && slotstack
/*  70 */           .func_77973_b() == is.func_77973_b() && is
/*     */           
/*  72 */           .func_77960_j() == slotstack.func_77960_j() && 
/*  73 */           ItemStack.func_77970_a(is, slotstack) && slotstack.field_77994_a < slot
/*  74 */           .func_75219_a()) {
/*     */           
/*  76 */           int mergedStackSize = is.field_77994_a + getSmaller(slotstack.field_77994_a, slot.func_75219_a());
/*     */ 
/*     */           
/*  79 */           if (mergedStackSize <= is.func_77976_d() && mergedStackSize <= slot.func_75219_a()) {
/*     */             
/*  81 */             is.field_77994_a = 0;
/*  82 */             slotstack.field_77994_a = mergedStackSize;
/*  83 */             slot.func_75218_e();
/*  84 */             merged = true;
/*     */           }
/*  86 */           else if (slotstack.field_77994_a < is.func_77976_d() && slotstack.field_77994_a < slot.func_75219_a()) {
/*     */ 
/*     */             
/*  89 */             if (slot.func_75219_a() >= is.func_77976_d()) {
/*     */               
/*  91 */               is.field_77994_a -= is.func_77976_d() - slotstack.field_77994_a;
/*  92 */               slotstack.field_77994_a = is.func_77976_d();
/*  93 */               slot.func_75218_e();
/*  94 */               merged = true;
/*     */             
/*     */             }
/*  97 */             else if (slot.func_75219_a() < is.func_77976_d()) {
/*     */               
/*  99 */               is.field_77994_a -= slot.func_75219_a() - slotstack.field_77994_a;
/* 100 */               slotstack.field_77994_a = slot.func_75219_a();
/* 101 */               slot.func_75218_e();
/* 102 */               merged = true;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 107 */         if (par4) {
/* 108 */           slotIndex--; continue;
/*     */         } 
/* 110 */         slotIndex++;
/*     */       } 
/*     */     }
/*     */     
/* 114 */     if (is.field_77994_a > 0) {
/*     */       
/* 116 */       if (par4) {
/* 117 */         slotIndex = slotFinish - 1;
/*     */       } else {
/* 119 */         slotIndex = slotStart;
/*     */       } 
/* 121 */       while ((!par4 && slotIndex < slotFinish) || (par4 && slotIndex >= slotStart)) {
/*     */         
/* 123 */         Slot slot = this.field_75151_b.get(slotIndex);
/* 124 */         ItemStack slotstack = slot.func_75211_c();
/* 125 */         if (slotstack == null && slot.func_75214_a(is) && slot.func_75219_a() < is.field_77994_a) {
/*     */           
/* 127 */           ItemStack copy = is.func_77946_l();
/* 128 */           copy.field_77994_a = slot.func_75219_a();
/* 129 */           is.field_77994_a -= slot.func_75219_a();
/* 130 */           slot.func_75215_d(copy);
/* 131 */           slot.func_75218_e();
/* 132 */           merged = true;
/*     */           
/*     */           break;
/*     */         } 
/* 136 */         if (slotstack == null && slot.func_75214_a(is)) {
/*     */           
/* 138 */           slot.func_75215_d(is.func_77946_l());
/* 139 */           slot.func_75218_e();
/* 140 */           is.field_77994_a = 0;
/* 141 */           merged = true;
/*     */           
/*     */           break;
/*     */         } 
/* 145 */         if (par4) {
/* 146 */           slotIndex--; continue;
/*     */         } 
/* 148 */         slotIndex++;
/*     */       } 
/*     */     } 
/*     */     
/* 152 */     return merged;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getSmaller(int i, int j) {
/* 157 */     if (i < j) {
/* 158 */       return i;
/*     */     }
/* 160 */     return j;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75142_b() {
/* 167 */     boolean shouldSave = false;
/* 168 */     boolean shouldReload = false;
/*     */     int i;
/* 170 */     for (i = 0; i < this.field_75151_b.size(); i++) {
/*     */       
/* 172 */       ItemStack itemstack = ((Slot)this.field_75151_b.get(i)).func_75211_c();
/* 173 */       ItemStack itemstack1 = this.field_75153_a.get(i);
/*     */       
/* 175 */       if (!areItemStacksEqual(itemstack1, itemstack)) {
/*     */         
/* 177 */         if (this.doItemSaving && i < this.field_75153_a.size() - 36 && !this.isLoading) {
/* 178 */           shouldSave = true;
/*     */         }
/* 180 */         itemstack1 = (itemstack == null) ? null : itemstack.func_77946_l();
/* 181 */         if (itemstack1 != null && itemstack1.field_77994_a == 0)
/* 182 */           itemstack1 = null; 
/* 183 */         this.field_75153_a.set(i, itemstack1);
/*     */         
/* 185 */         if (shouldSave) {
/*     */           
/* 187 */           int slotNum = this.bagsSlotNum + this.field_75153_a.size() - 36;
/* 188 */           saveContents(this.field_75153_a.get(slotNum));
/* 189 */           this.player.field_71071_by.func_70299_a(this.bagsSlotNum, this.field_75153_a.get(slotNum));
/* 190 */           for (int k = 0; k < this.field_75149_d.size(); k++) {
/* 191 */             ((ICrafting)this.field_75149_d.get(k)).func_71111_a(this, slotNum, this.field_75153_a.get(slotNum));
/*     */           }
/*     */         } 
/* 194 */         for (int j = 0; j < this.field_75149_d.size(); j++) {
/* 195 */           ((ICrafting)this.field_75149_d.get(j)).func_71111_a(this, i, itemstack1);
/*     */         }
/*     */       } 
/*     */     } 
/* 199 */     for (i = 0; i < this.field_75151_b.size() - 36; i++)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 206 */       shouldReload = true;
/*     */     }
/*     */ 
/*     */     
/* 210 */     if (shouldReload && !this.isLoading) {
/* 211 */       reloadContainer();
/*     */     }
/* 213 */     this.isLoading = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reloadContainer() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean areItemStacksEqual(ItemStack is1, ItemStack is2) {
/* 226 */     return (is1 == null && is2 == null) ? true : ((is1 != null && is2 != null) ? isItemStackEqual(is1, is2) : false);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isItemStackEqual(ItemStack is1, ItemStack is2) {
/* 231 */     return (is1.field_77994_a != is2.field_77994_a) ? false : (
/* 232 */       (is1.func_77973_b() != is2.func_77973_b()) ? false : (
/* 233 */       (is1.func_77960_j() != is2.func_77960_j()) ? false : ((is1.field_77990_d == null && is2.field_77990_d != null) ? false : ((is1.field_77990_d == null || 
/*     */       
/* 235 */       areCompoundsEqual(is1, is2))))));
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean areCompoundsEqual(ItemStack is1, ItemStack is2) {
/* 240 */     ItemStack is3 = is1.func_77946_l();
/* 241 */     ItemStack is4 = is2.func_77946_l();
/* 242 */     NBTTagCompound is3Tags = is3.func_77978_p();
/* 243 */     NBTTagCompound is4Tags = is4.func_77978_p();
/*     */     
/* 245 */     if (is3Tags == null) {
/* 246 */       return (is4Tags == null || is4Tags.func_82582_d());
/*     */     }
/* 248 */     if (is4Tags == null) {
/* 249 */       return is3Tags.func_82582_d();
/*     */     }
/* 251 */     float temp3 = TFC_ItemHeat.getTemp(is1);
/* 252 */     float temp4 = TFC_ItemHeat.getTemp(is2);
/* 253 */     is3Tags.func_82580_o("temp");
/* 254 */     is4Tags.func_82580_o("temp");
/*     */     
/* 256 */     return (is3Tags.equals(is4Tags) && Math.abs(temp3 - temp4) < 5.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack transferStackInSlotTFC(EntityPlayer entityplayer, int slotNum) {
/* 261 */     return super.func_82846_b(entityplayer, slotNum);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final ItemStack func_82846_b(EntityPlayer entityplayer, int slotNum) {
/* 267 */     Slot slot = this.field_75151_b.get(slotNum);
/* 268 */     ItemStack is = transferStackInSlotTFC(entityplayer, slotNum);
/*     */ 
/*     */     
/* 271 */     if (!slot.func_75216_d() && entityplayer instanceof EntityPlayerMP && !entityplayer.field_70170_p.field_72995_K) {
/*     */       
/* 273 */       EntityPlayerMP mp = (EntityPlayerMP)entityplayer;
/* 274 */       mp.func_71111_a(this, slot.field_75222_d, slot.func_75211_c());
/*     */     } 
/*     */     
/* 277 */     return is;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\ContainerTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */