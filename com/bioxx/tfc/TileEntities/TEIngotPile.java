/*     */ package com.bioxx.tfc.TileEntities;
/*     */ 
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ 
/*     */ public class TEIngotPile
/*     */   extends NetworkTileEntity implements IInventory {
/*     */   public ItemStack[] storage;
/*     */   public String type;
/*  23 */   public static final Item[] INGOTS = new Item[] { TFCItems.bismuthIngot, TFCItems.bismuthBronzeIngot, TFCItems.blackBronzeIngot, TFCItems.blackSteelIngot, TFCItems.blueSteelIngot, TFCItems.brassIngot, TFCItems.bronzeIngot, TFCItems.copperIngot, TFCItems.goldIngot, TFCItems.wroughtIronIngot, TFCItems.leadIngot, TFCItems.nickelIngot, TFCItems.pigIronIngot, TFCItems.platinumIngot, TFCItems.redSteelIngot, TFCItems.roseGoldIngot, TFCItems.silverIngot, TFCItems.steelIngot, TFCItems.sterlingSilverIngot, TFCItems.tinIngot, TFCItems.zincIngot, TFCItems.unknownIngot };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canUpdate() {
/*  33 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public TEIngotPile() {
/*  38 */     this.storage = new ItemStack[1];
/*  39 */     this.type = "Copper";
/*     */   }
/*     */   
/*     */   public static Item[] getIngots() {
/*  43 */     return (Item[])INGOTS.clone();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setType(String i) {
/*  48 */     this.type = i;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox() {
/*  55 */     return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 1), (this.field_145849_e + 1));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStack() {
/*  60 */     return (this.storage[0]).field_77994_a;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getType() {
/*  65 */     return this.type;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addContents(int index, ItemStack is) {
/*  70 */     if (this.storage[index] == null)
/*  71 */       this.storage[index] = is; 
/*  72 */     updateNeighbours();
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearContents() {
/*  77 */     this.storage[0] = null;
/*  78 */     updateNeighbours();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70305_f() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean contentsMatch(int index, ItemStack is) {
/*  88 */     if (this.storage[index] != null)
/*     */     {
/*  90 */       if ((this.storage[index]).field_77994_a == 0) {
/*  91 */         return true;
/*     */       }
/*     */     }
/*  94 */     return (this.storage[index].func_77973_b() == is.func_77973_b() && this.storage[index].func_77973_b() == is.func_77973_b() && (this.storage[index]).field_77994_a + 1 <= 
/*  95 */       func_70297_j_());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70298_a(int i, int j) {
/* 101 */     if (this.storage[i] != null) {
/*     */       
/* 103 */       if ((this.storage[i]).field_77994_a <= j) {
/*     */         
/* 105 */         ItemStack itemstack = this.storage[i];
/* 106 */         this.storage[i] = null;
/* 107 */         updateNeighbours();
/* 108 */         return itemstack;
/*     */       } 
/* 110 */       ItemStack itemstack1 = this.storage[i].func_77979_a(j);
/* 111 */       if ((this.storage[i]).field_77994_a == 0)
/* 112 */         this.storage[i] = null; 
/* 113 */       updateNeighbours();
/* 114 */       return itemstack1;
/*     */     } 
/*     */     
/* 117 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void ejectContents() {
/* 122 */     float f3 = 0.05F;
/*     */     
/* 124 */     Random rand = new Random();
/* 125 */     float f = rand.nextFloat() * 0.8F + 0.1F;
/* 126 */     float f1 = rand.nextFloat() * 2.0F + 0.4F;
/* 127 */     float f2 = rand.nextFloat() * 0.8F + 0.1F;
/*     */     
/* 129 */     for (int i = 0; i < func_70302_i_(); i++) {
/*     */       
/* 131 */       if (this.storage[i] != null) {
/*     */         
/* 133 */         EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), this.storage[i]);
/* 134 */         entityitem.field_70159_w = ((float)rand.nextGaussian() * f3);
/* 135 */         entityitem.field_70181_x = ((float)rand.nextGaussian() * f3 + 0.2F);
/* 136 */         entityitem.field_70179_y = ((float)rand.nextGaussian() * f3);
/* 137 */         this.field_145850_b.func_72838_d((Entity)entityitem);
/* 138 */         this.storage[i] = null;
/*     */       } 
/*     */     } 
/* 141 */     updateNeighbours();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70297_j_() {
/* 147 */     return 64;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String func_145825_b() {
/* 153 */     return "Ingot Pile";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70302_i_() {
/* 159 */     return this.storage.length;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70301_a(int i) {
/* 165 */     return this.storage[i];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70304_b(int var1) {
/* 171 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void injectContents(int index, int count) {
/* 176 */     if (this.storage[index] != null)
/*     */     {
/* 178 */       if ((this.storage[index]).field_77994_a > 0) {
/*     */         
/* 180 */         this.storage[index] = new ItemStack(this.storage[index].func_77973_b(), (this.storage[index]).field_77994_a + count, this.storage[index].func_77960_j());
/* 181 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */       } 
/*     */     }
/* 184 */     updateNeighbours();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70300_a(EntityPlayer entityplayer) {
/* 190 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70295_k_() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70299_a(int i, ItemStack itemstack) {
/* 201 */     this.storage[i] = itemstack;
/* 202 */     if (itemstack != null && itemstack.field_77994_a > func_70297_j_()) {
/* 203 */       itemstack.field_77994_a = func_70297_j_();
/*     */     }
/*     */   }
/*     */   
/*     */   public void updateNeighbours() {
/* 208 */     if (this.field_145850_b.func_72899_e(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) && !this.field_145850_b.func_147437_c(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e)) {
/* 209 */       this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e).func_149695_a(this.field_145850_b, this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, TFCBlocks.ingotPile);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_145818_k_() {
/* 215 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_94041_b(int i, ItemStack itemstack) {
/* 221 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbttagcompound) {
/* 227 */     super.func_145839_a(nbttagcompound);
/*     */     
/* 229 */     this.type = nbttagcompound.func_74779_i("type");
/* 230 */     NBTTagList nbttaglist = nbttagcompound.func_150295_c("Items", 10);
/* 231 */     this.storage = new ItemStack[func_70302_i_()];
/* 232 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*     */       
/* 234 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 235 */       byte byte0 = nbttagcompound1.func_74771_c("Slot");
/* 236 */       if (byte0 >= 0 && byte0 < this.storage.length) {
/* 237 */         this.storage[byte0] = ItemStack.func_77949_a(nbttagcompound1);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbttagcompound) {
/* 244 */     super.func_145841_b(nbttagcompound);
/* 245 */     nbttagcompound.func_74778_a("type", this.type);
/* 246 */     NBTTagList nbttaglist = new NBTTagList();
/* 247 */     for (int i = 0; i < this.storage.length; i++) {
/*     */       
/* 249 */       if (this.storage[i] != null) {
/*     */         
/* 251 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 252 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 253 */         this.storage[i].func_77955_b(nbttagcompound1);
/* 254 */         nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
/*     */       } 
/*     */     } 
/* 257 */     nbttagcompound.func_74782_a("Items", (NBTBase)nbttaglist);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleInitPacket(NBTTagCompound nbt) {
/* 263 */     this.type = nbt.func_74779_i("type");
/* 264 */     this.storage[0] = ItemStack.func_77949_a(nbt);
/* 265 */     updateNeighbours();
/* 266 */     this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleDataPacket(NBTTagCompound nbt) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void createDataNBT(NBTTagCompound nbt) {}
/*     */ 
/*     */   
/*     */   public void createInitNBT(NBTTagCompound nbt) {
/* 279 */     if (this.type != null)
/* 280 */       nbt.func_74778_a("type", this.type); 
/* 281 */     if (this.storage[0] != null) {
/*     */       
/* 283 */       ItemStack is = this.storage[0].func_77946_l();
/* 284 */       is.field_77990_d = null;
/* 285 */       is.func_77955_b(nbt);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEIngotPile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */