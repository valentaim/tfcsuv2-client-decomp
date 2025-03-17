/*     */ package com.bioxx.tfc.TileEntities;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.Devices.BlockSluice;
/*     */ import com.bioxx.tfc.Blocks.Terrain.BlockOre;
/*     */ import com.bioxx.tfc.Chunkdata.ChunkData;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ public class TESluice
/*     */   extends TileEntity implements IInventory {
/*     */   public int soilAmount;
/*     */   public long lastUpdateTicks;
/*     */   public int processTimeRemaining;
/*     */   private ItemStack[] sluiceItemStacks;
/*     */   public boolean waterInput;
/*     */   public boolean waterOutput;
/*     */   public byte soilType;
/*     */   private boolean initialized;
/*  44 */   private Random random = new Random();
/*  45 */   private Set<Integer> coreSampleTypes = new TreeSet<>();
/*  46 */   private List<ItemStack> coreSampleStacks = new ArrayList<>();
/*     */ 
/*     */   
/*     */   public TESluice() {
/*  50 */     this.sluiceItemStacks = new ItemStack[9];
/*  51 */     this.soilAmount = 0;
/*  52 */     this.lastUpdateTicks = 0L;
/*  53 */     this.processTimeRemaining = 0;
/*  54 */     this.waterInput = false;
/*  55 */     this.waterOutput = false;
/*  56 */     this.soilType = 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addToInventory(ItemStack is) {
/*  61 */     for (int i = 0; i < func_70302_i_(); i++) {
/*     */       
/*  63 */       ItemStack stackInSlot = func_70301_a(i);
/*  64 */       if (stackInSlot == null) {
/*     */ 
/*     */         
/*  67 */         func_70299_a(i, is);
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/*  73 */       if (stackInSlot == is && stackInSlot.func_77960_j() == is.func_77960_j())
/*     */       {
/*     */         
/*  76 */         if (stackInSlot.field_77994_a + is.field_77994_a > func_70297_j_()) {
/*     */ 
/*     */           
/*  79 */           int size = func_70297_j_() - stackInSlot.field_77994_a;
/*  80 */           stackInSlot.field_77994_a += size;
/*  81 */           is.field_77994_a -= size;
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */           
/*  87 */           stackInSlot.field_77994_a += is.field_77994_a;
/*     */ 
/*     */ 
/*     */           
/*     */           return;
/*     */         } 
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  98 */     ejectItem(is);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70305_f() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70298_a(int i, int j) {
/* 109 */     if (this.sluiceItemStacks[i] != null) {
/*     */       
/* 111 */       if ((this.sluiceItemStacks[i]).field_77994_a <= j) {
/*     */         
/* 113 */         ItemStack itemstack = this.sluiceItemStacks[i];
/* 114 */         this.sluiceItemStacks[i] = null;
/* 115 */         return itemstack;
/*     */       } 
/* 117 */       ItemStack itemstack1 = this.sluiceItemStacks[i].func_77979_a(j);
/* 118 */       if ((this.sluiceItemStacks[i]).field_77994_a == 0)
/* 119 */         this.sluiceItemStacks[i] = null; 
/* 120 */       return itemstack1;
/*     */     } 
/*     */ 
/*     */     
/* 124 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void ejectItem(ItemStack is) {
/* 130 */     float f = this.random.nextFloat() * 0.8F + 0.1F;
/* 131 */     float f1 = this.random.nextFloat() * 2.0F + 0.4F;
/* 132 */     float f2 = this.random.nextFloat() * 0.8F + 0.1F;
/* 133 */     EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), new ItemStack(is.func_77973_b(), is.field_77994_a, is.func_77960_j()));
/* 134 */     float f3 = 0.05F;
/* 135 */     entityitem.field_70159_w = ((float)this.random.nextGaussian() * f3);
/* 136 */     entityitem.field_70181_x = ((float)this.random.nextGaussian() * f3 + 0.2F);
/* 137 */     entityitem.field_70179_y = ((float)this.random.nextGaussian() * f3);
/* 138 */     this.field_145850_b.func_72838_d((Entity)entityitem);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getFirstFreeSlot() {
/* 143 */     for (int i = 0; i < func_70302_i_(); i++) {
/*     */       
/* 145 */       if (func_70301_a(i) == null)
/* 146 */         return i; 
/*     */     } 
/* 148 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70297_j_() {
/* 154 */     return 64;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String func_145825_b() {
/* 160 */     return "Sluice";
/*     */   }
/*     */ 
/*     */   
/*     */   public int getProcessScaled(int i) {
/* 165 */     return this.processTimeRemaining * i / 100;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70302_i_() {
/* 171 */     return this.sluiceItemStacks.length;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70301_a(int i) {
/* 177 */     return this.sluiceItemStacks[i];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70304_b(int var1) {
/* 183 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70300_a(EntityPlayer entityplayer) {
/* 189 */     if (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this)
/* 190 */       return false; 
/* 191 */     return (entityplayer.func_70092_e(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D) <= 64.0D);
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
/* 202 */     this.sluiceItemStacks[i] = itemstack;
/* 203 */     if (itemstack != null && itemstack.field_77994_a > func_70297_j_()) {
/* 204 */       itemstack.field_77994_a = func_70297_j_();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145845_h() {
/* 211 */     int meta = func_145832_p();
/* 212 */     boolean isFoot = BlockSluice.isBlockFootOfBed(meta);
/* 213 */     if (isFoot || this.soilAmount == -1) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 219 */     if (!this.field_145850_b.field_72995_K) {
/*     */       
/* 221 */       if (!this.initialized && this.field_145850_b.func_72977_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, 10.0D) != null) {
/*     */         
/* 223 */         for (int x = -100; x <= 100; x += 2) {
/*     */           
/* 225 */           for (int z = -100; z <= 100; z += 2) {
/*     */             
/* 227 */             for (int y = this.field_145848_d; y > this.field_145848_d - 50; y--) {
/*     */               
/* 229 */               if (this.field_145850_b.func_147439_a(x + this.field_145851_c, y, z + this.field_145849_e) == TFCBlocks.ore) {
/*     */                 
/* 231 */                 int m = this.field_145850_b.func_72805_g(x + this.field_145851_c, y, z + this.field_145849_e);
/* 232 */                 if (m != 14 && m != 15)
/*     */                 {
/* 234 */                   if (!this.coreSampleTypes.contains(Integer.valueOf(m))) {
/*     */                     
/* 236 */                     this.coreSampleTypes.add(Integer.valueOf(m));
/* 237 */                     this.coreSampleStacks.add(new ItemStack(BlockOre.getDroppedItem(m), 1, m));
/*     */                   } 
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/* 244 */         this.initialized = true;
/*     */       } 
/*     */       
/* 247 */       List<EntityItem> list = this.field_145850_b.func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 1.1F), (this.field_145849_e + 1)));
/*     */ 
/*     */ 
/*     */       
/* 251 */       for (Iterator<EntityItem> iterator = list.iterator(); iterator.hasNext(); ) {
/*     */         
/* 253 */         EntityItem entity = iterator.next();
/* 254 */         Item item = entity.func_92059_d().func_77973_b();
/* 255 */         if (item == Item.func_150898_a(TFCBlocks.gravel) || item == Item.func_150898_a(TFCBlocks.gravel2) || item == 
/* 256 */           Item.func_150898_a(TFCBlocks.sand) || item == Item.func_150898_a(TFCBlocks.sand2)) {
/*     */           
/* 258 */           int stackSize = (entity.func_92059_d()).field_77994_a;
/* 259 */           int accept = (69 - this.soilAmount) / 20;
/* 260 */           if (stackSize <= accept) {
/*     */             
/* 262 */             this.soilAmount += 20 * stackSize;
/* 263 */             entity.func_70106_y();
/* 264 */             if (this.soilAmount > 50)
/* 265 */               this.soilAmount = 50; 
/* 266 */             if (item == Item.func_150898_a(TFCBlocks.gravel) || item == Item.func_150898_a(TFCBlocks.gravel2)) {
/* 267 */               this.soilType = 2; continue;
/*     */             } 
/* 269 */             this.soilType = 1;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 275 */       long tickDiff = TFC_Time.getTotalTicks() - this.lastUpdateTicks;
/* 276 */       if (this.lastUpdateTicks == 0L)
/*     */       {
/*     */         
/* 279 */         tickDiff = 0L;
/*     */       }
/* 281 */       this.lastUpdateTicks = TFC_Time.getTotalTicks();
/*     */ 
/*     */       
/* 284 */       if (this.soilAmount > 0 && this.waterInput && this.waterOutput) {
/*     */ 
/*     */ 
/*     */         
/* 288 */         this.processTimeRemaining = (int)(this.processTimeRemaining + tickDiff);
/* 289 */         if (this.processTimeRemaining < 0)
/*     */         {
/*     */           
/* 292 */           this.processTimeRemaining = 0;
/*     */         }
/*     */         
/* 295 */         ChunkData cd = TFC_Core.getCDM(this.field_145850_b).getData(this.field_145851_c >> 4, this.field_145849_e >> 4);
/*     */         
/* 297 */         if (TFCOptions.enableOverworkingChunks && cd.sluicedAmount > TFCOptions.sluiceLimit) {
/*     */           
/* 299 */           this.processTimeRemaining = 0;
/* 300 */           this.soilAmount = -1;
/*     */           
/*     */           return;
/*     */         } 
/* 304 */         while (this.processTimeRemaining > 100 && this.soilAmount > 0) {
/*     */           
/* 306 */           float gemMod = 1.0F;
/* 307 */           float oreMod = 1.0F;
/* 308 */           if (this.soilType == 1) {
/* 309 */             gemMod = 0.65F;
/* 310 */           } else if (this.soilType == 2) {
/* 311 */             oreMod = 0.6F;
/*     */           } 
/* 313 */           ArrayList<ItemStack> items = new ArrayList<>();
/* 314 */           if (this.random.nextInt((int)(200.0F * oreMod)) == 0 && !this.coreSampleStacks.isEmpty()) {
/* 315 */             addToInventory(((ItemStack)this.coreSampleStacks.get(this.random.nextInt(this.coreSampleStacks.size()))).func_77946_l());
/* 316 */           } else if (this.random.nextInt((int)(400.0F * gemMod)) == 0) {
/*     */             
/* 318 */             items.add(new ItemStack(TFCItems.gemAgate, 1, 0));
/* 319 */             items.add(new ItemStack(TFCItems.gemAmethyst, 1, 0));
/* 320 */             items.add(new ItemStack(TFCItems.gemBeryl, 1, 0));
/* 321 */             items.add(new ItemStack(TFCItems.gemEmerald, 1, 0));
/* 322 */             items.add(new ItemStack(TFCItems.gemGarnet, 1, 0));
/* 323 */             items.add(new ItemStack(TFCItems.gemJade, 1, 0));
/* 324 */             items.add(new ItemStack(TFCItems.gemJasper, 1, 0));
/* 325 */             items.add(new ItemStack(TFCItems.gemOpal, 1, 0));
/* 326 */             items.add(new ItemStack(TFCItems.gemRuby, 1, 0));
/* 327 */             items.add(new ItemStack(TFCItems.gemSapphire, 1, 0));
/* 328 */             items.add(new ItemStack(TFCItems.gemTourmaline, 1, 0));
/* 329 */             items.add(new ItemStack(TFCItems.gemTopaz, 1, 0));
/* 330 */             addToInventory((ItemStack)items.toArray()[this.random.nextInt((items.toArray()).length)]);
/*     */           }
/* 332 */           else if (this.random.nextInt((int)(800.0F * gemMod)) == 0) {
/*     */             
/* 334 */             items.add(new ItemStack(TFCItems.gemAgate, 1, 1));
/* 335 */             items.add(new ItemStack(TFCItems.gemAmethyst, 1, 1));
/* 336 */             items.add(new ItemStack(TFCItems.gemBeryl, 1, 1));
/* 337 */             items.add(new ItemStack(TFCItems.gemEmerald, 1, 1));
/* 338 */             items.add(new ItemStack(TFCItems.gemGarnet, 1, 1));
/* 339 */             items.add(new ItemStack(TFCItems.gemJade, 1, 1));
/* 340 */             items.add(new ItemStack(TFCItems.gemJasper, 1, 1));
/* 341 */             items.add(new ItemStack(TFCItems.gemOpal, 1, 1));
/* 342 */             items.add(new ItemStack(TFCItems.gemRuby, 1, 1));
/* 343 */             items.add(new ItemStack(TFCItems.gemSapphire, 1, 1));
/* 344 */             items.add(new ItemStack(TFCItems.gemTourmaline, 1, 1));
/* 345 */             items.add(new ItemStack(TFCItems.gemTopaz, 1, 1));
/* 346 */             addToInventory((ItemStack)items.toArray()[this.random.nextInt((items.toArray()).length)]);
/*     */           }
/* 348 */           else if (this.random.nextInt((int)(1600.0F * gemMod)) == 0) {
/*     */             
/* 350 */             items.add(new ItemStack(TFCItems.gemAgate, 1, 2));
/* 351 */             items.add(new ItemStack(TFCItems.gemAmethyst, 1, 2));
/* 352 */             items.add(new ItemStack(TFCItems.gemBeryl, 1, 2));
/* 353 */             items.add(new ItemStack(TFCItems.gemEmerald, 1, 2));
/* 354 */             items.add(new ItemStack(TFCItems.gemGarnet, 1, 2));
/* 355 */             items.add(new ItemStack(TFCItems.gemJade, 1, 2));
/* 356 */             items.add(new ItemStack(TFCItems.gemJasper, 1, 2));
/* 357 */             items.add(new ItemStack(TFCItems.gemOpal, 1, 2));
/* 358 */             items.add(new ItemStack(TFCItems.gemRuby, 1, 2));
/* 359 */             items.add(new ItemStack(TFCItems.gemSapphire, 1, 2));
/* 360 */             items.add(new ItemStack(TFCItems.gemTourmaline, 1, 2));
/* 361 */             items.add(new ItemStack(TFCItems.gemTopaz, 1, 2));
/* 362 */             addToInventory((ItemStack)items.toArray()[this.random.nextInt((items.toArray()).length)]);
/*     */           }
/* 364 */           else if (this.random.nextInt((int)(3200.0F * gemMod)) == 0) {
/*     */             
/* 366 */             items.add(new ItemStack(TFCItems.gemAgate, 1, 3));
/* 367 */             items.add(new ItemStack(TFCItems.gemAmethyst, 1, 3));
/* 368 */             items.add(new ItemStack(TFCItems.gemBeryl, 1, 3));
/* 369 */             items.add(new ItemStack(TFCItems.gemEmerald, 1, 3));
/* 370 */             items.add(new ItemStack(TFCItems.gemGarnet, 1, 3));
/* 371 */             items.add(new ItemStack(TFCItems.gemJade, 1, 3));
/* 372 */             items.add(new ItemStack(TFCItems.gemJasper, 1, 3));
/* 373 */             items.add(new ItemStack(TFCItems.gemOpal, 1, 3));
/* 374 */             items.add(new ItemStack(TFCItems.gemRuby, 1, 3));
/* 375 */             items.add(new ItemStack(TFCItems.gemSapphire, 1, 3));
/* 376 */             items.add(new ItemStack(TFCItems.gemTourmaline, 1, 3));
/* 377 */             items.add(new ItemStack(TFCItems.gemTopaz, 1, 3));
/* 378 */             addToInventory((ItemStack)items.toArray()[this.random.nextInt((items.toArray()).length)]);
/*     */           }
/* 380 */           else if (this.random.nextInt((int)(6400.0F * gemMod)) == 0) {
/*     */             
/* 382 */             items.add(new ItemStack(TFCItems.gemAgate, 1, 4));
/* 383 */             items.add(new ItemStack(TFCItems.gemAmethyst, 1, 4));
/* 384 */             items.add(new ItemStack(TFCItems.gemBeryl, 1, 4));
/* 385 */             items.add(new ItemStack(TFCItems.gemEmerald, 1, 4));
/* 386 */             items.add(new ItemStack(TFCItems.gemGarnet, 1, 4));
/* 387 */             items.add(new ItemStack(TFCItems.gemJade, 1, 4));
/* 388 */             items.add(new ItemStack(TFCItems.gemJasper, 1, 4));
/* 389 */             items.add(new ItemStack(TFCItems.gemOpal, 1, 4));
/* 390 */             items.add(new ItemStack(TFCItems.gemRuby, 1, 4));
/* 391 */             items.add(new ItemStack(TFCItems.gemSapphire, 1, 4));
/* 392 */             items.add(new ItemStack(TFCItems.gemTourmaline, 1, 4));
/* 393 */             items.add(new ItemStack(TFCItems.gemTopaz, 1, 4));
/* 394 */             addToInventory((ItemStack)items.toArray()[this.random.nextInt((items.toArray()).length)]);
/*     */           }
/* 396 */           else if (this.random.nextInt((int)(12800.0F * gemMod)) == 0) {
/*     */             
/* 398 */             int r = this.random.nextInt(50);
/* 399 */             if (r == 0) {
/* 400 */               addToInventory(new ItemStack(TFCItems.gemDiamond, 1, 3));
/* 401 */             } else if (r < 15) {
/* 402 */               addToInventory(new ItemStack(TFCItems.gemDiamond, 1, 2));
/* 403 */             } else if (r < 25) {
/* 404 */               addToInventory(new ItemStack(TFCItems.gemDiamond, 1, 1));
/* 405 */             } else if (r < 50) {
/* 406 */               addToInventory(new ItemStack(TFCItems.gemDiamond, 1, 0));
/*     */             } 
/* 408 */           }  cd.sluicedAmount++;
/* 409 */           this.processTimeRemaining -= 100;
/* 410 */           this.soilAmount--;
/*     */         } 
/*     */       } 
/* 413 */       if (this.soilAmount == 0) {
/* 414 */         this.processTimeRemaining = 0;
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 420 */     if ((meta & 0x3) == 0) {
/*     */       
/* 422 */       this.waterInput = TFC_Core.isWater(this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e - 1));
/* 423 */       this
/* 424 */         .waterOutput = (TFC_Core.isWater(this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e + 2)) || TFC_Core.isWater(this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e + 2)));
/*     */     } 
/* 426 */     if ((meta & 0x3) == 1) {
/*     */       
/* 428 */       this.waterInput = TFC_Core.isWater(this.field_145850_b.func_147439_a(this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e));
/* 429 */       this
/* 430 */         .waterOutput = (TFC_Core.isWater(this.field_145850_b.func_147439_a(this.field_145851_c - 2, this.field_145848_d - 1, this.field_145849_e)) || TFC_Core.isWater(this.field_145850_b.func_147439_a(this.field_145851_c - 2, this.field_145848_d - 1, this.field_145849_e)));
/*     */     } 
/* 432 */     if ((meta & 0x3) == 2) {
/*     */       
/* 434 */       this.waterInput = TFC_Core.isWater(this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e + 1));
/* 435 */       this
/* 436 */         .waterOutput = (TFC_Core.isWater(this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e - 2)) || TFC_Core.isWater(this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e - 2)));
/*     */     } 
/* 438 */     if ((meta & 0x3) == 3) {
/*     */       
/* 440 */       this.waterInput = TFC_Core.isWater(this.field_145850_b.func_147439_a(this.field_145851_c - 1, this.field_145848_d + 1, this.field_145849_e));
/* 441 */       this
/* 442 */         .waterOutput = (TFC_Core.isWater(this.field_145850_b.func_147439_a(this.field_145851_c + 2, this.field_145848_d - 1, this.field_145849_e)) || TFC_Core.isWater(this.field_145850_b.func_147439_a(this.field_145851_c + 2, this.field_145848_d - 1, this.field_145849_e)));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 448 */     boolean isFlowing = ((meta & 0x4) == 4);
/* 449 */     ForgeDirection dir = getDir(meta & 0x3);
/* 450 */     Block water = this.field_145850_b.func_147439_a(this.field_145851_c + dir.offsetX, this.field_145848_d + 1, this.field_145849_e + dir.offsetZ);
/* 451 */     boolean isInputWater = TFC_Core.isWater(water);
/* 452 */     boolean isOutputAir = this.field_145850_b.func_147437_c(this.field_145851_c + (dir.getOpposite()).offsetX * 2, this.field_145848_d - 1, this.field_145849_e + (dir.getOpposite()).offsetZ * 2);
/* 453 */     boolean isOutputWater = TFC_Core.isWater(this.field_145850_b.func_147439_a(this.field_145851_c + (dir.getOpposite()).offsetX * 2, this.field_145848_d - 1, this.field_145849_e + (dir.getOpposite()).offsetZ * 2));
/* 454 */     boolean isWaterDepth7 = (this.field_145850_b.func_72805_g(this.field_145851_c + dir.offsetX, this.field_145848_d + 1, this.field_145849_e + dir.offsetZ) == 7);
/* 455 */     int meta2 = this.field_145850_b.func_72805_g(this.field_145851_c + (dir.getOpposite()).offsetX, this.field_145848_d, this.field_145849_e + (dir.getOpposite()).offsetZ);
/* 456 */     if (isInputWater && isWaterDepth7 && !isFlowing && (isOutputAir || isOutputWater)) {
/*     */ 
/*     */       
/* 459 */       this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, meta + 4, 3);
/*     */       
/* 461 */       if ((this.field_145850_b.func_72805_g(this.field_145851_c + (dir.getOpposite()).offsetX, this.field_145848_d, this.field_145849_e + (dir.getOpposite()).offsetZ) & 0x4) == 0)
/*     */       {
/*     */         
/* 464 */         this.field_145850_b.func_72921_c(this.field_145851_c + (dir.getOpposite()).offsetX, this.field_145848_d, this.field_145849_e + (dir.getOpposite()).offsetZ, meta2 + 4, 3);
/*     */       }
/*     */ 
/*     */       
/* 468 */       this.field_145850_b.func_147449_b(this.field_145851_c + (dir.getOpposite()).offsetX * 2, this.field_145848_d - 1, this.field_145849_e + (dir.getOpposite()).offsetZ * 2, water);
/*     */     } 
/* 470 */     if ((!isInputWater || !isWaterDepth7 || (!isOutputAir && !isOutputWater)) && isFlowing) {
/*     */ 
/*     */       
/* 473 */       this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, meta - 4, 3);
/* 474 */       if ((meta2 & 0x4) != 0)
/*     */       {
/*     */         
/* 477 */         this.field_145850_b.func_72921_c(this.field_145851_c + (dir.getOpposite()).offsetX, this.field_145848_d, this.field_145849_e + (dir.getOpposite()).offsetZ, meta2 - 4, 3);
/*     */       }
/*     */       
/* 480 */       if (!isOutputAir && isOutputWater) {
/* 481 */         this.field_145850_b.func_147468_f(this.field_145851_c + (dir.getOpposite()).offsetX * 2, this.field_145848_d - 1, this.field_145849_e + (dir.getOpposite()).offsetZ * 2);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private ForgeDirection getDir(int r) {
/* 487 */     if (r == 0)
/*     */     {
/* 489 */       return ForgeDirection.NORTH;
/*     */     }
/* 491 */     if (r == 1)
/*     */     {
/* 493 */       return ForgeDirection.EAST;
/*     */     }
/* 495 */     if (r == 2)
/*     */     {
/* 497 */       return ForgeDirection.SOUTH;
/*     */     }
/* 499 */     if (r == 3)
/*     */     {
/* 501 */       return ForgeDirection.WEST;
/*     */     }
/*     */     
/* 504 */     return ForgeDirection.UNKNOWN;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_145818_k_() {
/* 510 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_94041_b(int i, ItemStack itemstack) {
/* 516 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbttagcompound) {
/* 522 */     super.func_145839_a(nbttagcompound);
/* 523 */     NBTTagList nbttaglist = nbttagcompound.func_150295_c("Items", 10);
/* 524 */     this.sluiceItemStacks = new ItemStack[func_70302_i_()];
/* 525 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*     */       
/* 527 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 528 */       byte byte0 = nbttagcompound1.func_74771_c("Slot");
/* 529 */       if (byte0 >= 0 && byte0 < this.sluiceItemStacks.length)
/* 530 */         this.sluiceItemStacks[byte0] = ItemStack.func_77949_a(nbttagcompound1); 
/*     */     } 
/* 532 */     this.soilType = nbttagcompound.func_74771_c("soilType");
/* 533 */     this.soilAmount = nbttagcompound.func_74762_e("soilAmount");
/* 534 */     this.processTimeRemaining = nbttagcompound.func_74762_e("processTimeRemaining");
/* 535 */     this.lastUpdateTicks = nbttagcompound.func_74763_f("lastUpdateTicks");
/* 536 */     this.waterInput = nbttagcompound.func_74767_n("waterInput");
/* 537 */     this.waterOutput = nbttagcompound.func_74767_n("waterOutput");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbttagcompound) {
/* 543 */     super.func_145841_b(nbttagcompound);
/* 544 */     nbttagcompound.func_74774_a("soilType", this.soilType);
/* 545 */     nbttagcompound.func_74768_a("soilAmount", this.soilAmount);
/* 546 */     nbttagcompound.func_74768_a("processTimeRemaining", this.processTimeRemaining);
/* 547 */     nbttagcompound.func_74772_a("lastUpdateTicks", this.lastUpdateTicks);
/* 548 */     nbttagcompound.func_74757_a("waterInput", this.waterInput);
/* 549 */     nbttagcompound.func_74757_a("waterOutput", this.waterOutput);
/* 550 */     NBTTagList nbttaglist = new NBTTagList();
/* 551 */     for (int i = 0; i < this.sluiceItemStacks.length; i++) {
/*     */       
/* 553 */       if (this.sluiceItemStacks[i] != null) {
/*     */         
/* 555 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 556 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 557 */         this.sluiceItemStacks[i].func_77955_b(nbttagcompound1);
/* 558 */         nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
/*     */       } 
/*     */     } 
/* 561 */     nbttagcompound.func_74782_a("Items", (NBTBase)nbttaglist);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Packet func_145844_m() {
/* 567 */     NBTTagCompound nbt = new NBTTagCompound();
/* 568 */     func_145841_b(nbt);
/* 569 */     return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbt);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
/* 575 */     func_145839_a(pkt.func_148857_g());
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TESluice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */