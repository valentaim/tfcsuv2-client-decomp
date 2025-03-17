/*     */ package com.bioxx.tfc.TileEntities;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.Flora.BlockFruitLeaves;
/*     */ import com.bioxx.tfc.Blocks.Flora.BlockFruitWood;
/*     */ import com.bioxx.tfc.Core.TFC_Climate;
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.Food.FloraIndex;
/*     */ import com.bioxx.tfc.Food.FloraManager;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ 
/*     */ public class TEFruitTreeWood
/*     */   extends TileEntity
/*     */   implements IInventory {
/*     */   public boolean isTrunk;
/*     */   public int height;
/*     */   public long birthTimeWood;
/*     */   public long birthTimeLeaves;
/*     */   private static final long LEAF_GROWTH_RATE = 20L;
/*  28 */   private static final long TRUNK_GROW_TIME = (long)(1.5F * TFC_Time.daysInMonth);
/*  29 */   private static final long BRANCH_GROW_TIME = TFC_Time.daysInMonth;
/*     */   
/*     */   private static final long TEMP_TIMER_RATE = 10L;
/*     */   float temp;
/*  33 */   float temp_timer = 0.0F;
/*     */ 
/*     */ 
/*     */   
/*     */   public TEFruitTreeWood() {
/*  38 */     this.height = 0;
/*  39 */     this.isTrunk = false;
/*  40 */     this.birthTimeWood = 0L;
/*  41 */     this.birthTimeLeaves = 0L;
/*     */   }
/*     */ 
/*     */   
/*     */   public void initBirth() {
/*  46 */     this.birthTimeWood = TFC_Time.getTotalDays();
/*  47 */     this.birthTimeLeaves = TFC_Time.getTotalDays();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBirthWood(long t) {
/*  52 */     this.birthTimeWood = t;
/*     */   }
/*     */ 
/*     */   
/*     */   public void increaseBirthWood(long t) {
/*  57 */     this.birthTimeWood += t;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBirthLeaves(long t) {
/*  62 */     this.birthTimeLeaves = t;
/*     */   }
/*     */   
/*     */   public void increaseBirthLeaves(long t) {
/*  66 */     this.birthTimeLeaves += t;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTrunk(boolean b) {
/*  71 */     this.isTrunk = b;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeight(int h) {
/*  76 */     this.height = h;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setupBirth(boolean isTrunk, int h, long woodBirth, long leafBirth) {
/*  81 */     setTrunk(isTrunk);
/*  82 */     setHeight(h);
/*  83 */     initBirth();
/*  84 */     setBirthWood(woodBirth);
/*  85 */     setBirthLeaves(leafBirth);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145845_h() {
/*  91 */     if (!this.field_145850_b.field_72995_K) {
/*     */       
/*  93 */       FloraManager manager = FloraManager.getInstance();
/*  94 */       FloraIndex fi = manager.findMatchingIndex(BlockFruitWood.getType(this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e)));
/*     */       
/*  96 */       int meta = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */       
/*  98 */       this.temp_timer--;
/*  99 */       if (this.temp_timer <= 0.0F) {
/* 100 */         this.temp = TFC_Climate.getHeightAdjustedTemp(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 101 */         this.temp_timer = 10.0F;
/*     */       } 
/*     */       
/* 104 */       int month = TFC_Time.getSeasonAdjustedMonth(this.field_145849_e);
/* 105 */       if (month < 9 && fi != null && this.temp >= fi.minTemp && this.temp < fi.maxTemp) {
/*     */         
/* 107 */         int t = 1;
/* 108 */         if (month < 3) {
/* 109 */           t = 2;
/*     */         }
/*     */         
/* 112 */         if (this.birthTimeWood + TRUNK_GROW_TIME < TFC_Time.getTotalDays() && this.height < 3 && this.isTrunk && this.field_145850_b.field_73012_v.nextInt(16 / t) == 0 && (this.field_145850_b
/* 113 */           .func_147437_c(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) || this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) == TFCBlocks.fruitTreeLeaves || this.field_145850_b
/* 114 */           .func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) == TFCBlocks.fruitTreeLeaves2)) {
/*     */           
/* 116 */           this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, TFCBlocks.fruitTreeWood, meta, 2);
/* 117 */           if (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) instanceof TEFruitTreeWood)
/*     */           {
/* 119 */             TEFruitTreeWood trunkTE = (TEFruitTreeWood)this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
/* 120 */             trunkTE.setupBirth(true, this.height + 1, this.birthTimeWood + TRUNK_GROW_TIME, this.birthTimeLeaves);
/*     */             
/* 122 */             increaseBirthWood(TRUNK_GROW_TIME);
/* 123 */             this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */           }
/*     */         
/*     */         }
/* 127 */         else if (this.birthTimeWood + BRANCH_GROW_TIME < TFC_Time.getTotalDays() && this.height == 2 && this.isTrunk && this.field_145850_b.field_73012_v.nextInt(16 / t) == 0 && this.field_145850_b
/* 128 */           .func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) == TFCBlocks.fruitTreeWood) {
/*     */           
/* 130 */           int r = this.field_145850_b.field_73012_v.nextInt(4);
/* 131 */           if (r == 0 && this.field_145850_b.func_72899_e(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e) && (this.field_145850_b.func_147437_c(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e) || this.field_145850_b
/* 132 */             .func_147439_a(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e) == TFCBlocks.fruitTreeLeaves || this.field_145850_b.func_147439_a(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e) == TFCBlocks.fruitTreeLeaves2)) {
/*     */             
/* 134 */             this.field_145850_b.func_147465_d(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e, TFCBlocks.fruitTreeWood, meta, 2);
/* 135 */             if (this.field_145850_b.func_147438_o(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e) instanceof TEFruitTreeWood)
/*     */             {
/* 137 */               TEFruitTreeWood branchTE = (TEFruitTreeWood)this.field_145850_b.func_147438_o(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e);
/* 138 */               branchTE.setupBirth(false, this.height, this.birthTimeWood + BRANCH_GROW_TIME, this.birthTimeLeaves);
/*     */             }
/*     */           
/* 141 */           } else if (r == 1 && this.field_145850_b.func_72899_e(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1) && (this.field_145850_b.func_147437_c(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1) || this.field_145850_b
/* 142 */             .func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1) == TFCBlocks.fruitTreeLeaves || this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1) == TFCBlocks.fruitTreeLeaves2)) {
/*     */             
/* 144 */             this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1, TFCBlocks.fruitTreeWood, meta, 2);
/* 145 */             if (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1) instanceof TEFruitTreeWood)
/*     */             {
/* 147 */               TEFruitTreeWood branchTE = (TEFruitTreeWood)this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1);
/* 148 */               branchTE.setupBirth(false, this.height, this.birthTimeWood + BRANCH_GROW_TIME, this.birthTimeLeaves);
/*     */             }
/*     */           
/* 151 */           } else if (r == 2 && this.field_145850_b.func_72899_e(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e) && (this.field_145850_b.func_147437_c(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e) || this.field_145850_b
/* 152 */             .func_147439_a(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e) == TFCBlocks.fruitTreeLeaves || this.field_145850_b.func_147439_a(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e) == TFCBlocks.fruitTreeLeaves2)) {
/*     */             
/* 154 */             this.field_145850_b.func_147465_d(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e, TFCBlocks.fruitTreeWood, meta, 2);
/* 155 */             if (this.field_145850_b.func_147438_o(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e) instanceof TEFruitTreeWood)
/*     */             {
/* 157 */               TEFruitTreeWood branchTE = (TEFruitTreeWood)this.field_145850_b.func_147438_o(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e);
/* 158 */               branchTE.setupBirth(false, this.height, this.birthTimeWood + BRANCH_GROW_TIME, this.birthTimeLeaves);
/*     */             }
/*     */           
/* 161 */           } else if (r == 3 && this.field_145850_b.func_72899_e(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1) && (this.field_145850_b.func_147437_c(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1) || this.field_145850_b
/* 162 */             .func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1) == TFCBlocks.fruitTreeLeaves || this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1) == TFCBlocks.fruitTreeLeaves2)) {
/*     */             
/* 164 */             this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1, TFCBlocks.fruitTreeWood, meta, 2);
/* 165 */             if (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1) instanceof TEFruitTreeWood) {
/*     */               
/* 167 */               TEFruitTreeWood branchTE = (TEFruitTreeWood)this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1);
/* 168 */               branchTE.setupBirth(false, this.height, this.birthTimeWood + BRANCH_GROW_TIME, this.birthTimeLeaves);
/*     */             } 
/*     */           } 
/*     */           
/* 172 */           increaseBirthWood(BRANCH_GROW_TIME);
/* 173 */           this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */         } 
/*     */         
/* 176 */         if (this.birthTimeLeaves + 2L < TFC_Time.getTotalDays() && this.field_145850_b.field_73012_v.nextInt(20) == 0 && this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 2, this.field_145849_e) != TFCBlocks.fruitTreeWood) {
/*     */           
/* 178 */           int m = meta & 0x7;
/* 179 */           Block bid = (meta < 8) ? TFCBlocks.fruitTreeLeaves : TFCBlocks.fruitTreeLeaves2;
/*     */           
/* 181 */           if (checkLeaves(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e)) {
/*     */             
/* 183 */             this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, bid, m, 2);
/* 184 */             this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
/*     */           }
/* 186 */           else if (checkLeaves(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e)) {
/*     */             
/* 188 */             this.field_145850_b.func_147465_d(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e, bid, m, 2);
/* 189 */             this.field_145850_b.func_147471_g(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e);
/*     */           }
/* 191 */           else if (checkLeaves(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e)) {
/*     */             
/* 193 */             this.field_145850_b.func_147465_d(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e, bid, m, 2);
/* 194 */             this.field_145850_b.func_147471_g(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e);
/*     */           }
/* 196 */           else if (checkLeaves(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1)) {
/*     */             
/* 198 */             this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1, bid, m, 2);
/* 199 */             this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1);
/*     */           }
/* 201 */           else if (checkLeaves(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1)) {
/*     */             
/* 203 */             this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1, bid, m, 2);
/* 204 */             this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1);
/*     */           }
/* 206 */           else if (checkLeaves(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e - 1)) {
/*     */             
/* 208 */             this.field_145850_b.func_147465_d(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e - 1, bid, m, 2);
/* 209 */             this.field_145850_b.func_147471_g(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e - 1);
/*     */           }
/* 211 */           else if (checkLeaves(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e + 1)) {
/*     */             
/* 213 */             this.field_145850_b.func_147465_d(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e + 1, bid, m, 2);
/* 214 */             this.field_145850_b.func_147471_g(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e + 1);
/*     */           }
/* 216 */           else if (checkLeaves(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e - 1)) {
/*     */             
/* 218 */             this.field_145850_b.func_147465_d(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e - 1, bid, m, 2);
/* 219 */             this.field_145850_b.func_147471_g(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e - 1);
/*     */           }
/* 221 */           else if (checkLeaves(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e + 1)) {
/*     */             
/* 223 */             this.field_145850_b.func_147465_d(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e + 1, bid, m, 2);
/* 224 */             this.field_145850_b.func_147471_g(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e + 1);
/*     */           } 
/*     */           
/* 227 */           increaseBirthLeaves(2L);
/* 228 */           this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean checkLeaves(int xCoord, int yCoord, int zCoord) {
/* 236 */     return (this.field_145850_b.func_72899_e(xCoord, yCoord, zCoord) && this.field_145850_b.func_147437_c(xCoord, yCoord, zCoord) && this.field_145850_b
/* 237 */       .func_147437_c(xCoord, yCoord + 1, zCoord) && BlockFruitLeaves.canStay(this.field_145850_b, xCoord, yCoord, zCoord));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70305_f() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70297_j_() {
/* 248 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String func_145825_b() {
/* 254 */     return "Fruit Tree Wood";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70304_b(int var1) {
/* 260 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70300_a(EntityPlayer entityplayer) {
/* 266 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70295_k_() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbttagcompound) {
/* 277 */     super.func_145839_a(nbttagcompound);
/* 278 */     this.birthTimeWood = nbttagcompound.func_74763_f("birthTime");
/* 279 */     this.birthTimeLeaves = nbttagcompound.func_74763_f("birthTimeLeaves");
/* 280 */     this.isTrunk = nbttagcompound.func_74767_n("isTrunk");
/* 281 */     this.height = nbttagcompound.func_74762_e("height");
/*     */     
/* 283 */     this.temp = nbttagcompound.func_74760_g("temp");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbttagcompound) {
/* 289 */     super.func_145841_b(nbttagcompound);
/* 290 */     nbttagcompound.func_74772_a("birthTime", this.birthTimeWood);
/* 291 */     nbttagcompound.func_74772_a("birthTimeLeaves", this.birthTimeLeaves);
/* 292 */     nbttagcompound.func_74757_a("isTrunk", this.isTrunk);
/* 293 */     nbttagcompound.func_74768_a("height", this.height);
/* 294 */     nbttagcompound.func_74768_a("height", this.height);
/*     */     
/* 296 */     nbttagcompound.func_74776_a("temp", this.temp);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Packet func_145844_m() {
/* 302 */     NBTTagCompound nbt = new NBTTagCompound();
/* 303 */     func_145841_b(nbt);
/* 304 */     return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbt);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
/* 310 */     func_145839_a(pkt.func_148857_g());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70302_i_() {
/* 317 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70301_a(int var1) {
/* 323 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70298_a(int var1, int var2) {
/* 329 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70299_a(int var1, ItemStack var2) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_145818_k_() {
/* 340 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_94041_b(int i, ItemStack itemstack) {
/* 346 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEFruitTreeWood.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */