/*     */ package com.bioxx.tfc.TileEntities;
/*     */ 
/*     */ import com.bioxx.tfc.Containers.ContainerChestTFC;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFC_ItemHeat;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.InventoryLargeChest;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityChest;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ 
/*     */ 
/*     */ public class TEChest
/*     */   extends TileEntityChest
/*     */   implements IInventory
/*     */ {
/*  32 */   private ItemStack[] chestContents = new ItemStack[18];
/*     */   
/*     */   public int type;
/*     */   private int ticksSinceSync;
/*  36 */   public int cooldown = 5;
/*     */   
/*     */   private boolean typeSynced;
/*     */   public boolean isDoubleChest;
/*  40 */   private Boolean needCheck = null;
/*     */ 
/*     */   
/*     */   private boolean hasTemp = false;
/*     */ 
/*     */   
/*     */   public TEChest() {}
/*     */ 
/*     */   
/*     */   public TEChest(int i, boolean isDouble) {
/*  50 */     this.type = i;
/*  51 */     this.isDoubleChest = isDouble;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70302_i_() {
/*  57 */     return 18;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70301_a(int par1) {
/*  63 */     return this.chestContents[par1];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70298_a(int par1, int par2) {
/*  69 */     if (this.chestContents[par1] != null) {
/*     */ 
/*     */       
/*  72 */       if ((this.chestContents[par1]).field_77994_a <= par2) {
/*     */         
/*  74 */         ItemStack itemStack = this.chestContents[par1];
/*  75 */         this.chestContents[par1] = null;
/*  76 */         func_70296_d();
/*  77 */         return itemStack;
/*     */       } 
/*     */ 
/*     */       
/*  81 */       ItemStack var3 = this.chestContents[par1].func_77979_a(par2);
/*  82 */       if ((this.chestContents[par1]).field_77994_a == 0)
/*  83 */         this.chestContents[par1] = null; 
/*  84 */       func_70296_d();
/*  85 */       return var3;
/*     */     } 
/*     */ 
/*     */     
/*  89 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70304_b(int par1) {
/*  95 */     if (this.chestContents[par1] != null) {
/*     */       
/*  97 */       ItemStack var2 = this.chestContents[par1];
/*  98 */       this.chestContents[par1] = null;
/*  99 */       return var2;
/*     */     } 
/*     */     
/* 102 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70299_a(int par1, ItemStack par2ItemStack) {
/* 108 */     boolean check = true;
/* 109 */     if (this.chestContents[par1] != null && par2ItemStack != null && 
/* 110 */       this.chestContents[par1].func_77973_b().equals(par2ItemStack.func_77973_b()) && TFC_ItemHeat.hasTemp(this.chestContents[par1]) == TFC_ItemHeat.hasTemp(par2ItemStack)) check = false; 
/* 111 */     this.chestContents[par1] = par2ItemStack;
/* 112 */     if (par2ItemStack != null && par2ItemStack.field_77994_a > func_70297_j_())
/* 113 */       par2ItemStack.field_77994_a = func_70297_j_(); 
/* 114 */     func_70296_d();
/* 115 */     if (check)
/*     */     {
/* 117 */       checkItems();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String func_145825_b() {
/* 125 */     return "container.chest";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbt) {
/* 131 */     super.func_145839_a(nbt);
/* 132 */     NBTTagList var2 = nbt.func_150295_c("Items", 10);
/* 133 */     this.chestContents = new ItemStack[func_70302_i_()];
/* 134 */     this.type = nbt.func_74762_e("woodtype");
/* 135 */     for (int var3 = 0; var3 < var2.func_74745_c(); var3++) {
/*     */       
/* 137 */       NBTTagCompound var4 = var2.func_150305_b(var3);
/* 138 */       int var5 = var4.func_74771_c("Slot") & 0xFF;
/* 139 */       if (var5 >= 0 && var5 < this.chestContents.length) {
/* 140 */         this.chestContents[var5] = ItemStack.func_77949_a(var4);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbt) {
/* 147 */     super.func_145841_b(nbt);
/* 148 */     NBTTagList var2 = new NBTTagList();
/*     */     
/* 150 */     for (int var3 = 0; var3 < this.chestContents.length; var3++) {
/*     */       
/* 152 */       if (this.chestContents[var3] != null) {
/*     */         
/* 154 */         NBTTagCompound var4 = new NBTTagCompound();
/* 155 */         var4.func_74774_a("Slot", (byte)var3);
/* 156 */         this.chestContents[var3].func_77955_b(var4);
/* 157 */         var2.func_74742_a((NBTBase)var4);
/*     */       } 
/*     */     } 
/*     */     
/* 161 */     nbt.func_74782_a("Items", (NBTBase)var2);
/* 162 */     nbt.func_74768_a("woodtype", this.type);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Packet func_145844_m() {
/* 168 */     NBTTagCompound nbt = new NBTTagCompound();
/* 169 */     nbt.func_74768_a("woodtype", this.type);
/* 170 */     return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbt);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
/* 176 */     this.type = pkt.func_148857_g().func_74762_e("woodtype");
/*     */     
/* 178 */     this.field_145984_a = false;
/* 179 */     this.typeSynced = true;
/* 180 */     this.cooldown = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70297_j_() {
/* 186 */     return 64;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70300_a(EntityPlayer par1EntityPlayer) {
/* 192 */     return (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this) ? false : ((par1EntityPlayer.func_70092_e(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D) <= 64.0D));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean checkType(IBlockAccess access, int x, int y, int z) {
/* 197 */     TileEntity te = access.func_147438_o(x, y, z);
/* 198 */     if (te instanceof TEChest) {
/*     */       
/* 200 */       TEChest chest = (TEChest)access.func_147438_o(x, y, z);
/* 201 */       if (chest.type == this.type && this.cooldown == 0 && chest.cooldown == 0)
/* 202 */         return true; 
/*     */     } 
/* 204 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145979_i() {
/* 210 */     if (!this.field_145984_a) {
/*     */       
/* 212 */       this.field_145984_a = true;
/* 213 */       this.field_145992_i = null;
/* 214 */       this.field_145990_j = null;
/* 215 */       this.field_145991_k = null;
/* 216 */       this.field_145988_l = null;
/*     */       
/* 218 */       if (isChest(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e)) {
/* 219 */         this.field_145991_k = (TEChest)this.field_145850_b.func_147438_o(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e);
/*     */       }
/* 221 */       if (isChest(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e)) {
/* 222 */         this.field_145990_j = (TEChest)this.field_145850_b.func_147438_o(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e);
/*     */       }
/* 224 */       if (isChest(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1)) {
/* 225 */         this.field_145992_i = (TEChest)this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1);
/*     */       }
/* 227 */       if (isChest(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1)) {
/* 228 */         this.field_145988_l = (TEChest)this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 234 */       if (this.field_145992_i != null)
/*     */       {
/* 236 */         ((TEChest)this.field_145992_i).updateSide(this, 0);
/*     */       }
/*     */       
/* 239 */       if (this.field_145988_l != null)
/*     */       {
/* 241 */         ((TEChest)this.field_145988_l).updateSide(this, 2);
/*     */       }
/*     */       
/* 244 */       if (this.field_145990_j != null)
/*     */       {
/* 246 */         ((TEChest)this.field_145990_j).updateSide(this, 1);
/*     */       }
/*     */       
/* 249 */       if (this.field_145991_k != null)
/*     */       {
/* 251 */         ((TEChest)this.field_145991_k).updateSide(this, 3);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isChest(int x, int y, int z) {
/* 258 */     return (this.field_145850_b.func_147439_a(x, y, z) == TFCBlocks.chest && checkType((IBlockAccess)this.field_145850_b, x, y, z));
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateSide(TileEntityChest teChest, int side) {
/* 263 */     if (teChest.func_145837_r()) {
/*     */       
/* 265 */       this.field_145984_a = false;
/*     */     }
/* 267 */     else if (this.field_145984_a) {
/*     */       
/* 269 */       switch (side) {
/*     */         
/*     */         case 0:
/* 272 */           if (this.field_145988_l != teChest)
/*     */           {
/* 274 */             this.field_145984_a = false;
/*     */           }
/*     */           break;
/*     */         
/*     */         case 1:
/* 279 */           if (this.field_145991_k != teChest)
/*     */           {
/* 281 */             this.field_145984_a = false;
/*     */           }
/*     */           break;
/*     */         
/*     */         case 2:
/* 286 */           if (this.field_145992_i != teChest)
/*     */           {
/* 288 */             this.field_145984_a = false;
/*     */           }
/*     */           break;
/*     */         
/*     */         case 3:
/* 293 */           if (this.field_145990_j != teChest)
/*     */           {
/* 295 */             this.field_145984_a = false;
/*     */           }
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145845_h() {
/* 307 */     if (this.needCheck == null) { checkItems(); }
/* 308 */     else if (this.needCheck.booleanValue()) { TFC_Core.handleItemTicking(this, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e); }
/* 309 */      if (this.hasTemp && this.field_145850_b.func_82737_E() % 600L == 0L) checkItems(); 
/* 310 */     if (this.needCheck.booleanValue() && this.field_145850_b.func_82737_E() % 1200L == 0L) checkItems();
/*     */     
/* 312 */     if (this.type == 0 && !this.typeSynced) {
/*     */       
/* 314 */       if (this.cooldown == 0)
/*     */       {
/* 316 */         this.field_145984_a = false;
/* 317 */         this.isDoubleChest = false;
/* 318 */         this.typeSynced = true;
/*     */       }
/* 320 */       else if (this.cooldown > 0)
/*     */       {
/* 322 */         this.cooldown--;
/*     */       }
/*     */     
/* 325 */     } else if (this.cooldown != 0) {
/*     */       
/* 327 */       this.cooldown = 0;
/*     */     } 
/*     */     
/* 330 */     func_145979_i();
/* 331 */     this.ticksSinceSync++;
/*     */     
/* 333 */     if (!this.field_145850_b.field_72995_K && this.field_145987_o != 0 && (this.ticksSinceSync + this.field_145851_c + this.field_145848_d + this.field_145849_e) % 200 == 0) {
/*     */       
/* 335 */       this.field_145987_o = 0;
/* 336 */       float f = 5.0F;
/* 337 */       List<EntityPlayer> list = this.field_145850_b.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a((this.field_145851_c - f), (this.field_145848_d - f), (this.field_145849_e - f), ((this.field_145851_c + 1) + f), ((this.field_145848_d + 1) + f), ((this.field_145849_e + 1) + f)));
/* 338 */       Iterator<EntityPlayer> iterator = list.iterator();
/*     */       
/* 340 */       while (iterator.hasNext()) {
/*     */         
/* 342 */         EntityPlayer entityplayer = iterator.next();
/*     */         
/* 344 */         if (entityplayer.field_71070_bA instanceof ContainerChestTFC) {
/*     */           
/* 346 */           IInventory iinventory = ((ContainerChestTFC)entityplayer.field_71070_bA).getLowerChestInventory();
/*     */           
/* 348 */           if (iinventory == this || (iinventory instanceof InventoryLargeChest && ((InventoryLargeChest)iinventory).func_90010_a(this)))
/*     */           {
/* 350 */             this.field_145987_o++;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 356 */     this.field_145986_n = this.field_145989_m;
/* 357 */     float var1 = 0.1F;
/*     */ 
/*     */     
/* 360 */     if (this.field_145987_o > 0 && this.field_145989_m == 0.0F && this.field_145992_i == null && this.field_145991_k == null) {
/*     */       
/* 362 */       double var2 = this.field_145851_c + 0.5D;
/* 363 */       double var4 = this.field_145849_e + 0.5D;
/* 364 */       if (this.field_145988_l != null)
/* 365 */         var4 += 0.5D; 
/* 366 */       if (this.field_145990_j != null)
/* 367 */         var2 += 0.5D; 
/* 368 */       this.field_145850_b.func_72908_a(var2, this.field_145848_d + 0.5D, var4, "random.chestopen", 0.5F, this.field_145850_b.field_73012_v.nextFloat() * 0.1F + 0.9F);
/*     */     } 
/*     */     
/* 371 */     if ((this.field_145987_o == 0 && this.field_145989_m > 0.0F) || (this.field_145987_o > 0 && this.field_145989_m < 1.0F)) {
/*     */       
/* 373 */       if (this.field_145987_o > 0) {
/* 374 */         this.field_145989_m += var1;
/*     */       } else {
/* 376 */         this.field_145989_m -= var1;
/*     */       } 
/* 378 */       if (this.field_145989_m > 1.0F) {
/* 379 */         this.field_145989_m = 1.0F;
/*     */       }
/* 381 */       float var8 = this.field_145989_m;
/* 382 */       float var3 = 0.5F;
/* 383 */       if (this.field_145989_m < var3 && var8 >= var3 && this.field_145992_i == null && this.field_145991_k == null) {
/*     */         
/* 385 */         double var4 = this.field_145851_c + 0.5D;
/* 386 */         double var6 = this.field_145849_e + 0.5D;
/* 387 */         if (this.field_145988_l != null)
/* 388 */           var6 += 0.5D; 
/* 389 */         if (this.field_145990_j != null)
/* 390 */           var4 += 0.5D; 
/* 391 */         this.field_145850_b.func_72908_a(var4, this.field_145848_d + 0.5D, var6, "random.chestclosed", 0.5F, this.field_145850_b.field_73012_v.nextFloat() * 0.1F + 0.9F);
/*     */       } 
/*     */       
/* 394 */       if (this.field_145989_m < 0.0F) {
/* 395 */         this.field_145989_m = 0.0F;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void checkItems() {
/* 401 */     for (int i = 0; i < func_70302_i_(); i++) {
/* 402 */       ItemStack is = func_70301_a(i);
/* 403 */       if (is != null) {
/* 404 */         if (TFC_ItemHeat.hasTemp(is)) {
/* 405 */           this.needCheck = Boolean.valueOf(true);
/* 406 */           this.hasTemp = true;
/*     */           
/*     */           return;
/*     */         } 
/* 410 */         Item item = is.func_77973_b();
/* 411 */         if (item instanceof com.bioxx.tfc.api.Interfaces.IFood || item instanceof com.bioxx.tfc.Food.ItemFoodTFC) {
/* 412 */           this.needCheck = Boolean.valueOf(true);
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 419 */     this.hasTemp = false;
/* 420 */     this.needCheck = Boolean.valueOf(false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox() {
/* 427 */     return AxisAlignedBB.func_72330_a((this.field_145851_c - 1), this.field_145848_d, (this.field_145849_e - 1), (this.field_145851_c + 2), (this.field_145848_d + 2), (this.field_145849_e + 2));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70295_k_() {
/* 433 */     this.field_145987_o++;
/* 434 */     this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, TFCBlocks.chest, 1, this.field_145987_o);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70305_f() {
/* 440 */     this.field_145987_o--;
/* 441 */     this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, TFCBlocks.chest, 1, this.field_145987_o);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_145818_k_() {
/* 447 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_94041_b(int i, ItemStack itemstack) {
/* 453 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */