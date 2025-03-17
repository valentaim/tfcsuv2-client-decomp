/*     */ package com.bioxx.tfc.TileEntities;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.api.Enums.EnumFuelMaterial;
/*     */ import com.bioxx.tfc.api.Events.ItemCookEvent;
/*     */ import com.bioxx.tfc.api.Food;
/*     */ import com.bioxx.tfc.api.HeatIndex;
/*     */ import com.bioxx.tfc.api.HeatRegistry;
/*     */ import com.bioxx.tfc.api.Interfaces.ICookableFood;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFC_ItemHeat;
/*     */ import com.bioxx.tfc.api.TileEntities.TEFireEntity;
/*     */ import cpw.mods.fml.common.eventhandler.Event;
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
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ 
/*     */ public class TEGrill
/*     */   extends NetworkTileEntity implements IInventory {
/*  31 */   public ItemStack[] storage = new ItemStack[6];
/*     */   
/*     */   public byte data;
/*     */ 
/*     */   
/*     */   public void func_145845_h() {
/*  37 */     TFC_Core.handleItemTicking(this, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     
/*  39 */     for (int i = 0; i < 6; i++) {
/*     */       
/*  41 */       careForInventorySlot(this.storage[i]);
/*  42 */       cookItem(i);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox() {
/*  50 */     return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 1), (this.field_145849_e + 1));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOven() {
/*  55 */     int wallCount = 0;
/*  56 */     if (TFC_Core.isWestFaceSolid(this.field_145850_b, this.field_145851_c - 1, this.field_145848_d, this.field_145849_e))
/*  57 */       wallCount++; 
/*  58 */     if (TFC_Core.isEastFaceSolid(this.field_145850_b, this.field_145851_c + 1, this.field_145848_d, this.field_145849_e))
/*  59 */       wallCount++; 
/*  60 */     if (TFC_Core.isNorthFaceSolid(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e + 1))
/*  61 */       wallCount++; 
/*  62 */     if (TFC_Core.isSouthFaceSolid(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e - 1)) {
/*  63 */       wallCount++;
/*     */     }
/*  65 */     if (TFC_Core.isBottomFaceSolid(this.field_145850_b, this.field_145851_c, this.field_145848_d + 1, this.field_145849_e)) {
/*  66 */       wallCount++;
/*     */     }
/*  68 */     if (this.field_145850_b.func_147439_a(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e) == TFCBlocks.metalTrapDoor) {
/*     */       
/*  70 */       TEMetalTrapDoor te = (TEMetalTrapDoor)this.field_145850_b.func_147438_o(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e);
/*  71 */       if (te.getSide() == 4) {
/*  72 */         wallCount++;
/*     */       }
/*  74 */     } else if (this.field_145850_b.func_147439_a(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e) == TFCBlocks.metalTrapDoor) {
/*     */       
/*  76 */       TEMetalTrapDoor te = (TEMetalTrapDoor)this.field_145850_b.func_147438_o(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e);
/*  77 */       if (te.getSide() == 5) {
/*  78 */         wallCount++;
/*     */       }
/*  80 */     } else if (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1) == TFCBlocks.metalTrapDoor) {
/*     */       
/*  82 */       TEMetalTrapDoor te = (TEMetalTrapDoor)this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1);
/*  83 */       if (te.getSide() == 2) {
/*  84 */         wallCount++;
/*     */       }
/*  86 */     } else if (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1) == TFCBlocks.metalTrapDoor) {
/*     */       
/*  88 */       TEMetalTrapDoor te = (TEMetalTrapDoor)this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1);
/*  89 */       if (te.getSide() == 3) {
/*  90 */         wallCount++;
/*     */       }
/*     */     } 
/*  93 */     return (wallCount >= 5);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDoor(int x, int y, int z) {
/*  99 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void careForInventorySlot(ItemStack is) {
/* 104 */     TileEntity te = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
/* 105 */     if (is != null && te instanceof TEFireEntity) {
/*     */       
/* 107 */       HeatRegistry manager = HeatRegistry.getInstance();
/* 108 */       HeatIndex index = manager.findMatchingIndex(is);
/*     */       
/* 110 */       if (index != null) {
/*     */         
/* 112 */         float temp = TFC_ItemHeat.getTemp(is);
/* 113 */         TEFireEntity fire = (TEFireEntity)te;
/* 114 */         if (fire.fuelTimeLeft > 0 && is.func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood) {
/*     */           
/* 116 */           float inc = Food.getCooked(is) + Math.min(fire.fireTemp / 700.0F, 2.0F);
/* 117 */           Food.setCooked(is, inc);
/* 118 */           temp = inc;
/*     */         }
/* 120 */         else if (fire.fireTemp > temp) {
/*     */           
/* 122 */           temp += TFC_ItemHeat.getTempIncrease(is);
/*     */         } 
/*     */         
/* 125 */         if (fire.fireTemp > temp) {
/* 126 */           temp += TFC_ItemHeat.getTempIncrease(is);
/*     */         } else {
/* 128 */           temp -= TFC_ItemHeat.getTempDecrease(is);
/* 129 */         }  TFC_ItemHeat.setTemp(is, temp);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void cookItem(int i) {
/* 136 */     HeatRegistry manager = HeatRegistry.getInstance();
/* 137 */     Random r = new Random();
/* 138 */     if (this.storage[i] != null) {
/*     */       
/* 140 */       HeatIndex index = manager.findMatchingIndex(this.storage[i]);
/* 141 */       if (index != null && Food.isCooked(this.storage[i])) {
/*     */ 
/*     */         
/* 144 */         int[] cookedTasteProfile = { 0, 0, 0, 0, 0 };
/* 145 */         r = new Random((((ICookableFood)this.storage[i].func_77973_b()).getFoodID() + ((int)Food.getCooked(this.storage[i]) - 600) / 120));
/* 146 */         cookedTasteProfile[0] = r.nextInt(31) - 15;
/* 147 */         cookedTasteProfile[1] = r.nextInt(31) - 15;
/* 148 */         cookedTasteProfile[2] = r.nextInt(31) - 15;
/* 149 */         cookedTasteProfile[3] = r.nextInt(31) - 15;
/* 150 */         cookedTasteProfile[4] = r.nextInt(31) - 15;
/* 151 */         Food.setCookedProfile(this.storage[i], cookedTasteProfile);
/* 152 */         TileEntity te = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
/* 153 */         if (te instanceof TEFireEntity) {
/*     */           
/* 155 */           TEFireEntity fire = (TEFireEntity)te;
/* 156 */           Food.setFuelProfile(this.storage[i], EnumFuelMaterial.getFuelProfile(fire.fuelTasteProfile));
/*     */         } 
/*     */       } 
/*     */       
/* 160 */       if (index != null && TFC_ItemHeat.getTemp(this.storage[i]) > index.meltTemp) {
/*     */         
/* 162 */         float temp = TFC_ItemHeat.getTemp(this.storage[i]);
/* 163 */         ItemStack output = index.getOutput(this.storage[i], r);
/*     */         
/* 165 */         ItemCookEvent eventMelt = new ItemCookEvent(this.storage[i], output, this);
/* 166 */         MinecraftForge.EVENT_BUS.post((Event)eventMelt);
/* 167 */         output = eventMelt.result;
/*     */ 
/*     */         
/* 170 */         this.storage[i] = output;
/* 171 */         if (this.storage[i] != null && manager.findMatchingIndex(this.storage[i]) != null)
/*     */         {
/*     */           
/* 174 */           TFC_ItemHeat.setTemp(this.storage[i], temp);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSide() {
/* 182 */     return this.data & 0x7;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 187 */     for (ItemStack is : this.storage) {
/*     */       
/* 189 */       if (is != null) {
/* 190 */         return false;
/*     */       }
/*     */     } 
/* 193 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbt) {
/* 199 */     super.func_145839_a(nbt);
/* 200 */     this.data = nbt.func_74771_c("data");
/* 201 */     NBTTagList nbttaglist = nbt.func_150295_c("Items", 10);
/* 202 */     this.storage = new ItemStack[func_70302_i_()];
/* 203 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*     */       
/* 205 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 206 */       byte byte0 = nbttagcompound1.func_74771_c("Slot");
/* 207 */       if (byte0 >= 0 && byte0 < this.storage.length) {
/* 208 */         this.storage[byte0] = ItemStack.func_77949_a(nbttagcompound1);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbt) {
/* 215 */     super.func_145841_b(nbt);
/* 216 */     nbt.func_74774_a("data", this.data);
/* 217 */     NBTTagList nbttaglist = new NBTTagList();
/* 218 */     for (int i = 0; i < this.storage.length; i++) {
/*     */       
/* 220 */       if (this.storage[i] != null) {
/*     */         
/* 222 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 223 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 224 */         this.storage[i].func_77955_b(nbttagcompound1);
/* 225 */         nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
/*     */       } 
/*     */     } 
/* 228 */     nbt.func_74782_a("Items", (NBTBase)nbttaglist);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70298_a(int i, int j) {
/* 234 */     if (this.storage[i] != null) {
/*     */       
/* 236 */       if ((this.storage[i]).field_77994_a <= j) {
/*     */         
/* 238 */         ItemStack itemstack = this.storage[i];
/* 239 */         this.storage[i] = null;
/* 240 */         return itemstack;
/*     */       } 
/* 242 */       ItemStack itemstack1 = this.storage[i].func_77979_a(j);
/* 243 */       if ((this.storage[i]).field_77994_a == 0)
/* 244 */         this.storage[i] = null; 
/* 245 */       return itemstack1;
/*     */     } 
/*     */     
/* 248 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void ejectContents() {
/* 256 */     Random rand = new Random();
/* 257 */     float f = rand.nextFloat() * 0.8F + 0.1F;
/* 258 */     float f1 = rand.nextFloat() * 2.0F + 0.4F;
/* 259 */     float f2 = rand.nextFloat() * 0.8F + 0.1F;
/*     */     
/* 261 */     for (int i = 0; i < func_70302_i_(); i++) {
/*     */       
/* 263 */       if (this.storage[i] != null) {
/*     */         
/* 265 */         EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), this.storage[i]);
/* 266 */         this.field_145850_b.func_72838_d((Entity)entityitem);
/* 267 */         this.storage[i] = null;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void ejectItem(int index) {
/* 276 */     Random rand = new Random();
/* 277 */     float f = rand.nextFloat() * 0.8F + 0.1F;
/* 278 */     float f1 = rand.nextFloat() * 2.0F + 0.4F;
/* 279 */     float f2 = rand.nextFloat() * 0.8F + 0.1F;
/*     */     
/* 281 */     if (this.storage[index] != null) {
/*     */       
/* 283 */       EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), this.storage[index]);
/* 284 */       this.field_145850_b.func_72838_d((Entity)entityitem);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70302_i_() {
/* 291 */     return this.storage.length;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70301_a(int i) {
/* 297 */     return this.storage[i];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70299_a(int i, ItemStack itemstack) {
/* 303 */     this.storage[i] = itemstack;
/* 304 */     this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String func_145825_b() {
/* 310 */     return "grill";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70297_j_() {
/* 316 */     return 64;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70300_a(EntityPlayer var1) {
/* 322 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70295_k_() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70305_f() {
/* 333 */     if (this.field_145850_b.field_72995_K)
/*     */     {
/* 335 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70304_b(int var1) {
/* 342 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_145818_k_() {
/* 348 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_94041_b(int i, ItemStack itemstack) {
/* 354 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleInitPacket(NBTTagCompound nbt) {
/* 359 */     NBTTagList nbttaglist = nbt.func_150295_c("Items", 10);
/* 360 */     this.data = nbt.func_74771_c("data");
/* 361 */     this.storage = new ItemStack[func_70302_i_()];
/* 362 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*     */       
/* 364 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 365 */       byte byte0 = nbttagcompound1.func_74771_c("Slot");
/* 366 */       if (byte0 >= 0 && byte0 < this.storage.length) {
/* 367 */         this.storage[byte0] = ItemStack.func_77949_a(nbttagcompound1);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void createInitNBT(NBTTagCompound nbt) {
/* 373 */     NBTTagList nbttaglist = new NBTTagList();
/* 374 */     nbt.func_74774_a("data", this.data);
/* 375 */     for (int i = 0; i < this.storage.length; i++) {
/*     */       
/* 377 */       if (this.storage[i] != null) {
/*     */         
/* 379 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 380 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 381 */         this.storage[i].func_77955_b(nbttagcompound1);
/* 382 */         nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
/*     */       } 
/*     */     } 
/* 385 */     nbt.func_74782_a("Items", (NBTBase)nbttaglist);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEGrill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */