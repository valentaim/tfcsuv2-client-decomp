/*     */ package com.bioxx.tfc.TileEntities;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.Devices.BlockEarlyBloomery;
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.Items.ItemOre;
/*     */ import com.bioxx.tfc.api.Constant.Global;
/*     */ import com.bioxx.tfc.api.Interfaces.ISmeltable;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TEBloomery
/*     */   extends NetworkTileEntity
/*     */ {
/*     */   public boolean isFlipped;
/*     */   public boolean bloomeryLit;
/*  30 */   private int validationCheck = 60;
/*     */   
/*     */   public int charcoalCount;
/*     */   
/*     */   public long fuelTimeLeft;
/*     */   
/*     */   public int oreCount;
/*     */   public int outCount;
/*     */   
/*     */   public TEBloomery() {
/*  40 */     this.isFlipped = false;
/*  41 */     this.bloomeryLit = false;
/*     */     
/*  43 */     this.charcoalCount = 0;
/*  44 */     this.oreCount = 0;
/*  45 */     this.outCount = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void swapFlipped() {
/*  50 */     if (this.isFlipped) { this.isFlipped = false; }
/*  51 */     else { this.isFlipped = true; }
/*  52 */      if (!this.field_145850_b.field_72995_K) {
/*  53 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isStackValid(int i, int j, int k) {
/*  58 */     Block yNegBlock = this.field_145850_b.func_147439_a(i, j - 1, k);
/*  59 */     if (yNegBlock != TFCBlocks.molten && this.field_145850_b
/*  60 */       .func_147439_a(i, j - 1, k).func_149688_o() != Material.field_151576_e && 
/*  61 */       !this.field_145850_b.func_147439_a(i, j - 1, k).func_149721_r() && yNegBlock != TFCBlocks.charcoal)
/*     */     {
/*     */       
/*  64 */       return false;
/*     */     }
/*  66 */     return ((BlockEarlyBloomery)TFCBlocks.bloomery).checkStack(this.field_145850_b, this.field_145851_c, j, this.field_145849_e, this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e) & 0x3);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addOreToFire(ItemStack is) {
/*  71 */     if (((ISmeltable)is.func_77973_b()).getMetalType(is) == Global.PIGIRON || ((ISmeltable)is.func_77973_b()).getMetalType(is) == Global.WROUGHTIRON) {
/*     */       
/*  73 */       this.outCount += ((ISmeltable)is.func_77973_b()).getMetalReturnAmount(is);
/*  74 */       return true;
/*     */     } 
/*  76 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canLight() {
/*  81 */     if (!this.field_145850_b.field_72995_K) {
/*     */       
/*  83 */       if (this.charcoalCount < this.oreCount || this.oreCount == 0) {
/*  84 */         return false;
/*     */       }
/*     */       
/*  87 */       int meta = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*  88 */       int[] direction = BlockEarlyBloomery.BLOOMERY_TO_STACK_MAP[getCharcoalDir(meta)];
/*  89 */       int x = this.field_145851_c + direction[0];
/*  90 */       int z = this.field_145849_e + direction[1];
/*  91 */       Block bid = this.field_145850_b.func_147439_a(x, this.field_145848_d, z);
/*  92 */       if (bid == TFCBlocks.charcoal && this.field_145850_b
/*  93 */         .func_72805_g(x, this.field_145848_d, z) >= 7 && !this.bloomeryLit) {
/*     */         
/*  95 */         this.bloomeryLit = true;
/*  96 */         this.fuelTimeLeft = (long)((float)TFC_Time.getTotalTicks() + 1000.0F * TFCOptions.bloomeryBurnTime);
/*  97 */         if ((meta & 0x4) == 0)
/*  98 */           this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, meta + 4, 3); 
/*  99 */         return true;
/*     */       } 
/*     */     } 
/* 102 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private int getCharcoalDir(int meta) {
/* 107 */     return meta & 0x3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145845_h() {
/* 114 */     if (!this.field_145850_b.field_72995_K) {
/*     */ 
/*     */       
/* 117 */       int count = this.charcoalCount + this.oreCount;
/*     */       
/* 119 */       int moltenCount = (count > 0 && count < 8) ? 1 : (count / 8);
/* 120 */       int validCount = 0;
/* 121 */       int maxCount = 0;
/*     */ 
/*     */       
/* 124 */       int meta = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 125 */       int[] direction = BlockEarlyBloomery.BLOOMERY_TO_STACK_MAP[getCharcoalDir(meta)];
/*     */       
/* 127 */       int x = this.field_145851_c + direction[0];
/* 128 */       int z = this.field_145849_e + direction[1];
/*     */       
/* 130 */       if (this.field_145850_b.func_72899_e(x, this.field_145848_d, z)) {
/*     */         
/* 132 */         if (this.bloomeryLit && TFC_Time.getTotalTicks() > this.fuelTimeLeft) {
/*     */           
/* 134 */           if (this.field_145850_b.func_147439_a(x, this.field_145848_d, z) == TFCBlocks.molten)
/*     */           {
/* 136 */             if (this.field_145850_b.func_147449_b(x, this.field_145848_d, z, TFCBlocks.bloom)) {
/*     */               
/* 138 */               this.bloomeryLit = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 145 */               this.oreCount = 0;
/* 146 */               this.charcoalCount = 0;
/* 147 */               ((TEBloom)this.field_145850_b.func_147438_o(x, this.field_145848_d, z)).setSize(this.outCount);
/* 148 */               this.outCount = 0;
/*     */             } 
/*     */           }
/*     */           
/* 152 */           if ((meta & 0x4) != 0)
/*     */           {
/* 154 */             this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, meta & 0x3, 3);
/*     */           }
/*     */         } 
/*     */         
/* 158 */         if (this.outCount < 0)
/* 159 */           this.outCount = 0; 
/* 160 */         if (this.oreCount < 0)
/* 161 */           this.oreCount = 0; 
/* 162 */         if (this.charcoalCount < 0) {
/* 163 */           this.charcoalCount = 0;
/*     */         }
/*     */         
/* 166 */         if (isStackValid(x, this.field_145848_d + 1, z)) {
/*     */           
/* 168 */           maxCount = 8;
/*     */           
/* 170 */           if (isStackValid(x, this.field_145848_d + 2, z)) {
/*     */             
/* 172 */             maxCount = 16;
/*     */             
/* 174 */             if (isStackValid(x, this.field_145848_d + 3, z))
/*     */             {
/* 176 */               maxCount = 24;
/*     */             }
/*     */           } 
/*     */         } 
/*     */         
/* 181 */         int moltenHeight = Math.max(count / 2 - 1, 0);
/*     */         
/* 183 */         for (int i = this.bloomeryLit ? 0 : 1, j = this.bloomeryLit ? (moltenHeight + 7) : moltenHeight; j > 0; i++, j -= 8) {
/*     */           
/* 185 */           Block bid = this.field_145850_b.func_147439_a(x, this.field_145848_d + i, z);
/*     */           
/* 187 */           if ((bid.isAir((IBlockAccess)this.field_145850_b, x, this.field_145848_d + i, z) || bid == TFCBlocks.molten || bid == TFCBlocks.charcoal) && this.field_145850_b
/* 188 */             .func_147439_a(x, this.field_145848_d - 1, z).func_149688_o() == Material.field_151576_e) {
/*     */ 
/*     */             
/* 191 */             if (isStackValid(x, this.field_145848_d + i, z)) {
/* 192 */               validCount++;
/*     */             }
/* 194 */             if (i <= validCount) {
/*     */               
/* 196 */               int mMeta = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d + i, this.field_145849_e);
/* 197 */               int m = (j > 7) ? 7 : j;
/* 198 */               if (this.bloomeryLit) {
/*     */                 
/* 200 */                 if ((bid == TFCBlocks.molten && (mMeta & 0x8) == 0) || bid
/* 201 */                   .isAir((IBlockAccess)this.field_145850_b, x, this.field_145848_d + i, z) || bid == TFCBlocks.charcoal)
/*     */                 {
/*     */                   
/* 204 */                   m += 8;
/* 205 */                   this.field_145850_b.func_147465_d(x, this.field_145848_d + i, z, TFCBlocks.molten, m, 2);
/*     */                 
/*     */                 }
/*     */               
/*     */               }
/* 210 */               else if (count > 0) {
/* 211 */                 this.field_145850_b.func_147465_d(x, this.field_145848_d + i, z, TFCBlocks.molten, m, 2);
/*     */               } else {
/* 213 */                 this.field_145850_b.func_147468_f(x, this.field_145848_d + i, z);
/*     */               }
/*     */             
/*     */             } else {
/*     */               
/* 218 */               this.field_145850_b.func_147468_f(x, this.field_145848_d + i, z);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 223 */         if (!this.bloomeryLit && this.field_145850_b.func_147439_a(x, this.field_145848_d, z) == TFCBlocks.bloom) {
/*     */           
/* 225 */           if (isStackValid(x, this.field_145848_d + 3, z) && 
/* 226 */             isStackValid(x, this.field_145848_d + 2, z) && 
/* 227 */             isStackValid(x, this.field_145848_d + 1, z))
/*     */           {
/* 229 */             if (this.field_145850_b.func_147439_a(x, this.field_145848_d + 3, z) == TFCBlocks.molten) {
/* 230 */               this.field_145850_b.func_147468_f(x, this.field_145848_d + 3, z);
/*     */             }
/*     */           }
/* 233 */           if (isStackValid(x, this.field_145848_d + 2, z) && 
/* 234 */             isStackValid(x, this.field_145848_d + 1, z))
/*     */           {
/* 236 */             if (this.field_145850_b.func_147439_a(x, this.field_145848_d + 2, z) == TFCBlocks.molten) {
/* 237 */               this.field_145850_b.func_147468_f(x, this.field_145848_d + 2, z);
/*     */             }
/*     */           }
/* 240 */           if (isStackValid(x, this.field_145848_d + 1, z))
/*     */           {
/* 242 */             if (this.field_145850_b.func_147439_a(x, this.field_145848_d + 1, z) == TFCBlocks.molten) {
/* 243 */               this.field_145850_b.func_147468_f(x, this.field_145848_d + 1, z);
/*     */             }
/*     */           }
/*     */         } 
/* 247 */         if (moltenCount == 0) {
/* 248 */           moltenCount = 1;
/*     */         }
/*     */         
/* 251 */         List<EntityItem> list = this.field_145850_b.func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a(x, this.field_145848_d, z, (x + 1), (this.field_145848_d + maxCount / 8) + 1.1D, (z + 1)));
/*     */ 
/*     */         
/* 254 */         List<EntityItem> playerList = this.field_145850_b.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a(x, this.field_145848_d, z, (x + 1), (this.field_145848_d + maxCount / 8) + 1.1D, (z + 1)));
/*     */ 
/*     */         
/* 257 */         if (list != null && !list.isEmpty() && !this.bloomeryLit && (playerList == null || playerList.isEmpty()))
/*     */         {
/*     */           
/* 260 */           for (Iterator<EntityItem> iterator = list.iterator(); iterator.hasNext(); ) {
/*     */             
/* 262 */             EntityItem entity = iterator.next();
/* 263 */             if (entity.func_92059_d().func_77973_b() == TFCItems.coal && entity
/* 264 */               .func_92059_d().func_77960_j() == 1) {
/*     */               
/* 266 */               for (int c = 0; c < (entity.func_92059_d()).field_77994_a; c++) {
/*     */                 
/* 268 */                 if (this.charcoalCount + this.oreCount < 2 * maxCount && this.charcoalCount < maxCount) {
/*     */                   
/* 270 */                   this.charcoalCount++;
/* 271 */                   (entity.func_92059_d()).field_77994_a--;
/*     */                 } 
/*     */               } 
/* 274 */               if ((entity.func_92059_d()).field_77994_a == 0)
/* 275 */                 entity.func_70106_y(); 
/*     */               continue;
/*     */             } 
/* 278 */             if (entity.func_92059_d().func_77973_b() instanceof ItemOre && ((ItemOre)entity.func_92059_d().func_77973_b()).isSmeltable(entity.func_92059_d())) {
/*     */               
/* 280 */               int c = (entity.func_92059_d()).field_77994_a;
/* 281 */               while (c > 0) {
/*     */                 
/* 283 */                 if (this.charcoalCount + this.oreCount < 2 * maxCount && this.oreCount < maxCount && this.outCount < 1000)
/*     */                 {
/* 285 */                   if (addOreToFire(new ItemStack(entity.func_92059_d().func_77973_b(), 1, entity.func_92059_d().func_77960_j()))) {
/*     */                     
/* 287 */                     this.oreCount++;
/* 288 */                     c--;
/*     */                   } 
/*     */                 }
/*     */               } 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 296 */               if (c == 0) {
/* 297 */                 entity.func_70106_y(); continue;
/*     */               } 
/* 299 */               (entity.func_92059_d()).field_77994_a = c; continue;
/*     */             } 
/* 301 */             if (entity.func_92059_d().func_77973_b() instanceof ISmeltable && ((ISmeltable)entity
/* 302 */               .func_92059_d().func_77973_b()).isSmeltable(entity.func_92059_d())) {
/*     */               
/* 304 */               int c = (entity.func_92059_d()).field_77994_a;
/* 305 */               while (c > 0) {
/*     */                 
/* 307 */                 if (((ISmeltable)entity.func_92059_d().func_77973_b()).getMetalReturnAmount(entity.func_92059_d()) < 100 && this.oreCount < maxCount && this.outCount < 1000)
/*     */                 {
/* 309 */                   if (addOreToFire(new ItemStack(entity.func_92059_d().func_77973_b(), 1, entity.func_92059_d().func_77960_j()))) {
/*     */                     
/* 311 */                     this.oreCount++;
/* 312 */                     c--;
/*     */                   } 
/*     */                 }
/*     */               } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 321 */               if (c == 0) {
/* 322 */                 entity.func_70106_y(); continue;
/*     */               } 
/* 324 */               (entity.func_92059_d()).field_77994_a = c;
/*     */             } 
/*     */           } 
/*     */         }
/*     */         
/* 329 */         if (this.validationCheck <= 0) {
/*     */           
/* 331 */           if (((BlockEarlyBloomery)this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e)).func_149718_j(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e)) {
/* 332 */             this.validationCheck = 600;
/*     */           } else {
/*     */             
/* 335 */             this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 336 */             this.field_145850_b.func_72838_d((Entity)new EntityItem(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, new ItemStack(TFCBlocks.bloomery, 1)));
/*     */           } 
/*     */         } else {
/*     */           
/* 340 */           this.validationCheck--;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbttagcompound) {
/* 348 */     super.func_145841_b(nbttagcompound);
/* 349 */     nbttagcompound.func_74757_a("isFlipped", this.isFlipped);
/* 350 */     nbttagcompound.func_74772_a("fuelTimeLeft", this.fuelTimeLeft);
/* 351 */     nbttagcompound.func_74768_a("charcoalCount", this.charcoalCount);
/* 352 */     nbttagcompound.func_74768_a("outCount", this.outCount);
/* 353 */     nbttagcompound.func_74768_a("oreCount", this.oreCount);
/* 354 */     nbttagcompound.func_74757_a("isLit", this.bloomeryLit);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbttagcompound) {
/* 360 */     super.func_145839_a(nbttagcompound);
/* 361 */     this.isFlipped = nbttagcompound.func_74767_n("isFlipped");
/* 362 */     this.fuelTimeLeft = nbttagcompound.func_74763_f("fuelTimeLeft");
/* 363 */     this.charcoalCount = nbttagcompound.func_74762_e("charcoalCount");
/* 364 */     this.outCount = nbttagcompound.func_74762_e("outCount");
/* 365 */     this.oreCount = nbttagcompound.func_74762_e("oreCount");
/* 366 */     this.bloomeryLit = nbttagcompound.func_74767_n("isLit");
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleInitPacket(NBTTagCompound nbt) {
/* 371 */     this.isFlipped = nbt.func_74767_n("isFlipped");
/* 372 */     this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
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
/*     */   
/*     */   public void createInitNBT(NBTTagCompound nbt) {
/* 389 */     nbt.func_74757_a("isFlipped", this.isFlipped);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEBloomery.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */