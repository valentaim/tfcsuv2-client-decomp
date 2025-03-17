/*     */ package com.bioxx.tfc.TileEntities;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TEToolRack
/*     */   extends NetworkTileEntity
/*     */   implements IInventory
/*     */ {
/*  22 */   public ItemStack[] storage = new ItemStack[4];
/*  23 */   public byte woodType = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   public void addContents(int index, ItemStack is) {
/*  28 */     if (this.storage[index] == null) {
/*  29 */       this.storage[index] = is;
/*     */     }
/*     */   }
/*     */   
/*     */   public void clearContents() {
/*  34 */     this.storage[0] = null;
/*  35 */     this.storage[1] = null;
/*  36 */     this.storage[2] = null;
/*  37 */     this.storage[3] = null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox() {
/*  44 */     return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 1), (this.field_145849_e + 1));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contentsMatch(int index, ItemStack is) {
/*  49 */     return (this.storage[index] != null && this.storage[index]
/*  50 */       .func_77973_b() == is.func_77973_b() && this.storage[index]
/*  51 */       .func_77960_j() == is.func_77960_j() && (this.storage[index]).field_77994_a < this.storage[index]
/*  52 */       .func_77976_d());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70298_a(int i, int j) {
/*  58 */     if (this.storage[i] != null) {
/*     */       
/*  60 */       if ((this.storage[i]).field_77994_a <= j) {
/*     */         
/*  62 */         ItemStack itemstack = this.storage[i];
/*  63 */         this.storage[i] = null;
/*  64 */         return itemstack;
/*     */       } 
/*  66 */       ItemStack itemstack1 = this.storage[i].func_77979_a(j);
/*  67 */       if ((this.storage[i]).field_77994_a == 0)
/*  68 */         this.storage[i] = null; 
/*  69 */       return itemstack1;
/*     */     } 
/*     */ 
/*     */     
/*  73 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void ejectContents() {
/*  79 */     float f3 = 0.05F;
/*     */     
/*  81 */     Random rand = new Random();
/*  82 */     float f = rand.nextFloat() * 0.8F + 0.1F;
/*  83 */     float f1 = rand.nextFloat() * 0.8F + 0.4F;
/*  84 */     float f2 = rand.nextFloat() * 0.8F + 0.1F;
/*     */     
/*  86 */     for (int i = 0; i < func_70302_i_(); i++) {
/*     */       
/*  88 */       if (this.storage[i] != null) {
/*     */         
/*  90 */         EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), this.storage[i]);
/*  91 */         entityitem.field_70159_w = ((float)rand.nextGaussian() * f3);
/*  92 */         entityitem.field_70181_x = ((float)rand.nextGaussian() * f3 + 0.2F);
/*  93 */         entityitem.field_70179_y = ((float)rand.nextGaussian() * f3);
/*  94 */         this.field_145850_b.func_72838_d((Entity)entityitem);
/*  95 */         this.storage[i] = null;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void ejectItem(int index, int dir) {
/* 102 */     float f3 = 0.05F;
/*     */     
/* 104 */     Random rand = new Random();
/* 105 */     float f = rand.nextFloat() * 0.8F + 0.1F;
/* 106 */     float f1 = rand.nextFloat() * 0.2F + 0.1F;
/* 107 */     float f2 = rand.nextFloat() * 0.8F + 0.1F;
/*     */     
/* 109 */     if (this.storage[index] != null) {
/*     */       
/* 111 */       EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), this.storage[index]);
/* 112 */       entityitem.field_70159_w = ((float)rand.nextGaussian() * f3);
/* 113 */       entityitem.field_70181_x = 0.0D;
/* 114 */       entityitem.field_70179_y = ((float)rand.nextGaussian() * f3);
/* 115 */       this.field_145850_b.func_72838_d((Entity)entityitem);
/* 116 */       this.storage[index] = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70297_j_() {
/* 123 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70302_i_() {
/* 129 */     return this.storage.length;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70301_a(int i) {
/* 135 */     return this.storage[i];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70304_b(int var1) {
/* 141 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void injectContents(int index, int count) {
/* 146 */     if (this.storage[index] != null) {
/* 147 */       this.storage[index] = new ItemStack(this.storage[index].func_77973_b(), (this.storage[index]).field_77994_a + count, this.storage[index].func_77960_j());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_70300_a(EntityPlayer entityplayer) {
/* 153 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70299_a(int i, ItemStack itemstack) {
/* 159 */     this.storage[i] = itemstack;
/* 160 */     if (itemstack != null && itemstack.field_77994_a > func_70297_j_()) {
/* 161 */       itemstack.field_77994_a = func_70297_j_();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_94041_b(int i, ItemStack itemstack) {
/* 173 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String func_145825_b() {
/* 179 */     return "Tool Rack";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_145818_k_() {
/* 185 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70295_k_() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70305_f() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbttagcompound) {
/* 201 */     super.func_145839_a(nbttagcompound);
/* 202 */     this.woodType = nbttagcompound.func_74771_c("woodType");
/* 203 */     NBTTagList nbttaglist = nbttagcompound.func_150295_c("Items", 10);
/* 204 */     this.storage = new ItemStack[func_70302_i_()];
/* 205 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*     */       
/* 207 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 208 */       byte byte0 = nbttagcompound1.func_74771_c("Slot");
/* 209 */       if (byte0 >= 0 && byte0 < this.storage.length) {
/* 210 */         this.storage[byte0] = ItemStack.func_77949_a(nbttagcompound1);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbt) {
/* 217 */     super.func_145841_b(nbt);
/* 218 */     NBTTagList nbttaglist = new NBTTagList();
/* 219 */     for (int i = 0; i < this.storage.length; i++) {
/*     */       
/* 221 */       if (this.storage[i] != null) {
/*     */         
/* 223 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 224 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 225 */         this.storage[i].func_77955_b(nbttagcompound1);
/* 226 */         nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
/*     */       } 
/*     */     } 
/* 229 */     nbt.func_74782_a("Items", (NBTBase)nbttaglist);
/* 230 */     nbt.func_74774_a("woodType", this.woodType);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleInitPacket(NBTTagCompound nbt) {
/* 235 */     func_145839_a(nbt);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleDataPacket(NBTTagCompound nbt) {
/* 241 */     func_145839_a(nbt);
/*     */   }
/*     */ 
/*     */   
/*     */   public void createDataNBT(NBTTagCompound nbt) {
/* 246 */     func_145841_b(nbt);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void createInitNBT(NBTTagCompound nbt) {
/* 252 */     func_145841_b(nbt);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEToolRack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */