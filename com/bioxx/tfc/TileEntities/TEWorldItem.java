/*     */ package com.bioxx.tfc.TileEntities;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ 
/*     */ public class TEWorldItem extends NetworkTileEntity implements IInventory {
/*  15 */   public ItemStack[] storage = new ItemStack[1];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityItem renderItem;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canUpdate() {
/*  26 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbt) {
/*  32 */     super.func_145839_a(nbt);
/*  33 */     NBTTagList nbttaglist = nbt.func_150295_c("Items", 10);
/*  34 */     this.storage = new ItemStack[1];
/*  35 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*     */       
/*  37 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/*  38 */       byte byte0 = nbttagcompound1.func_74771_c("Slot");
/*  39 */       if (byte0 >= 0 && byte0 < this.storage.length) {
/*  40 */         this.storage[byte0] = ItemStack.func_77949_a(nbttagcompound1);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public double func_145833_n() {
/*  48 */     return 1024.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox() {
/*  55 */     return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), this.field_145848_d + 0.1D, (this.field_145849_e + 1));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbt) {
/*  61 */     super.func_145841_b(nbt);
/*  62 */     NBTTagList nbttaglist = new NBTTagList();
/*  63 */     for (int i = 0; i < this.storage.length; i++) {
/*     */       
/*  65 */       if (this.storage[i] != null) {
/*     */         
/*  67 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*  68 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/*  69 */         this.storage[i].func_77955_b(nbttagcompound1);
/*  70 */         nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
/*     */       } 
/*     */     } 
/*  73 */     nbt.func_74782_a("Items", (NBTBase)nbttaglist);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70302_i_() {
/*  79 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70301_a(int i) {
/*  85 */     return this.storage[i];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70298_a(int i, int j) {
/*  91 */     (this.storage[i]).field_77994_a -= j;
/*  92 */     return this.storage[i];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70304_b(int i) {
/*  98 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70299_a(int i, ItemStack itemstack) {
/* 104 */     this.storage[i] = itemstack;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String func_145825_b() {
/* 110 */     return "worldItem";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_145818_k_() {
/* 116 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70297_j_() {
/* 122 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70300_a(EntityPlayer entityplayer) {
/* 128 */     return false;
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
/*     */   public boolean func_94041_b(int i, ItemStack itemstack) {
/* 144 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleInitPacket(NBTTagCompound nbt) {
/* 149 */     if (nbt.func_74764_b("id")) {
/* 150 */       this.storage[0] = ItemStack.func_77949_a(nbt);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleDataPacket(NBTTagCompound nbt) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createDataNBT(NBTTagCompound nbt) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void createInitNBT(NBTTagCompound nbt) {
/* 167 */     if (this.storage[0] != null)
/* 168 */       this.storage[0].func_77955_b(nbt); 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEWorldItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */