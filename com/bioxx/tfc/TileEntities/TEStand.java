/*     */ package com.bioxx.tfc.TileEntities;
/*     */ 
/*     */ import com.bioxx.tfc.Entities.EntityStand;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TEStand
/*     */   extends TileEntity
/*     */   implements IInventory
/*     */ {
/*  32 */   public ItemStack[] items = new ItemStack[5];
/*  33 */   public int highlightedSlot = -1;
/*     */ 
/*     */   
/*     */   public EntityStand entity;
/*     */   
/*     */   public float yaw;
/*     */   
/*     */   public boolean isTop;
/*     */   
/*     */   private boolean hasEntity;
/*     */ 
/*     */   
/*     */   public void func_70295_k_() {}
/*     */ 
/*     */   
/*     */   public void func_70305_f() {}
/*     */ 
/*     */   
/*     */   public void destroy() {
/*  52 */     if (!this.isTop && this.entity != null) {
/*  53 */       this.entity.func_70106_y();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack func_70298_a(int i, int j) {
/*  59 */     if (this.items[i] != null && !this.isTop) {
/*     */       
/*  61 */       if ((this.items[i]).field_77994_a <= j) {
/*     */         
/*  63 */         ItemStack itemstack2 = this.items[i];
/*  64 */         this.items[i] = null;
/*  65 */         return itemstack2;
/*     */       } 
/*  67 */       ItemStack itemstack1 = this.items[i].func_77979_a(j);
/*  68 */       if ((this.items[i]).field_77994_a == 0)
/*  69 */         this.items[i] = null; 
/*  70 */       return itemstack1;
/*     */     } 
/*     */ 
/*     */     
/*  74 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void ejectContents() {
/*  80 */     if (!this.isTop) {
/*     */       
/*  82 */       float f3 = 0.05F;
/*     */       
/*  84 */       Random rand = new Random();
/*  85 */       float f = rand.nextFloat() * 0.8F + 0.1F;
/*  86 */       float f1 = rand.nextFloat() * 2.0F + 0.4F;
/*  87 */       float f2 = rand.nextFloat() * 0.8F + 0.1F;
/*     */       
/*  89 */       for (int i = 0; i < func_70302_i_(); i++) {
/*     */         
/*  91 */         if (this.items[i] != null) {
/*     */           
/*  93 */           EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), this.items[i]);
/*  94 */           entityitem.field_70159_w = ((float)rand.nextGaussian() * f3);
/*  95 */           entityitem.field_70181_x = ((float)rand.nextGaussian() * f3 + 0.2F);
/*  96 */           entityitem.field_70179_y = ((float)rand.nextGaussian() * f3);
/*  97 */           this.field_145850_b.func_72838_d((Entity)entityitem);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70297_j_() {
/* 106 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String func_145825_b() {
/* 112 */     return "Stand";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70302_i_() {
/* 118 */     return 5;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70301_a(int i) {
/* 124 */     if (!this.isTop)
/* 125 */       return this.items[i]; 
/* 126 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70304_b(int var1) {
/* 132 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70300_a(EntityPlayer entityplayer) {
/* 138 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70299_a(int i, ItemStack itemstack) {
/* 144 */     if (!this.isTop) {
/*     */       
/* 146 */       if (this.items[i] == null || itemstack == null) {
/* 147 */         this.items[i] = itemstack;
/*     */       }
/* 149 */       if (this.items[i] != null && (this.items[i]).field_77994_a > func_70297_j_()) {
/* 150 */         (this.items[i]).field_77994_a = func_70297_j_();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145845_h() {
/* 158 */     if (!this.field_145850_b.field_72995_K && !this.isTop) {
/*     */       
/* 160 */       if (func_145830_o() && !this.hasEntity) {
/*     */ 
/*     */         
/* 163 */         this.entity.func_70012_b(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.yaw, 0.0F);
/* 164 */         this.field_145850_b.func_72838_d((Entity)this.entity);
/*     */         
/* 166 */         this.hasEntity = true;
/*     */       } 
/*     */       
/* 169 */       if (this.hasEntity && this.entity == null) {
/*     */         
/* 171 */         List<EntityStand> list = this.field_145850_b.func_72872_a(EntityStand.class, AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 2), (this.field_145849_e + 1)));
/*     */ 
/*     */ 
/*     */         
/* 175 */         if (!list.isEmpty()) {
/* 176 */           this.entity = list.get(0);
/*     */         } else {
/* 178 */           this.hasEntity = false;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_94041_b(int i, ItemStack itemstack) {
/* 186 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_145818_k_() {
/* 192 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbttagcompound) {
/* 198 */     super.func_145841_b(nbttagcompound);
/* 199 */     NBTTagList nbttaglist = new NBTTagList();
/* 200 */     nbttagcompound.func_74757_a("hasEntity", this.hasEntity);
/* 201 */     nbttagcompound.func_74776_a("yaw", this.yaw);
/* 202 */     nbttagcompound.func_74757_a("isTop", this.isTop);
/* 203 */     for (int i = 0; i < this.items.length; i++) {
/*     */       
/* 205 */       if (this.items[i] != null) {
/*     */         
/* 207 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 208 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 209 */         this.items[i].func_77955_b(nbttagcompound1);
/* 210 */         nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
/*     */       } 
/*     */     } 
/* 213 */     nbttagcompound.func_74782_a("Items", (NBTBase)nbttaglist);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbttagcompound) {
/* 219 */     super.func_145839_a(nbttagcompound);
/* 220 */     this.hasEntity = nbttagcompound.func_74767_n("hasEntity");
/* 221 */     this.yaw = nbttagcompound.func_74760_g("yaw");
/* 222 */     this.isTop = nbttagcompound.func_74767_n("isTop");
/* 223 */     this.items = new ItemStack[func_70302_i_()];
/* 224 */     NBTTagList nbttaglist = nbttagcompound.func_150295_c("Items", 10);
/* 225 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*     */       
/* 227 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 228 */       byte byte0 = nbttagcompound1.func_74771_c("Slot");
/* 229 */       if (byte0 >= 0 && byte0 < this.items.length) {
/* 230 */         this.items[byte0] = ItemStack.func_77949_a(nbttagcompound1);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet func_145844_m() {
/* 237 */     NBTTagCompound nbt = new NBTTagCompound();
/* 238 */     func_145841_b(nbt);
/* 239 */     return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbt);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
/* 245 */     func_145839_a(pkt.func_148857_g());
/*     */   }
/*     */   
/*     */   public void updateGui() {}
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */