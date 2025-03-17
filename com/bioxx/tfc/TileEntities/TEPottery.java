/*     */ package com.bioxx.tfc.TileEntities;
/*     */ 
/*     */ import com.bioxx.tfc.Core.Metal.Alloy;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.Items.Pottery.ItemPotteryBase;
/*     */ import com.bioxx.tfc.api.Crafting.KilnCraftingManager;
/*     */ import com.bioxx.tfc.api.Crafting.KilnRecipe;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import com.bioxx.tfc.api.Util.Helper;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TEPottery
/*     */   extends NetworkTileEntity
/*     */   implements IInventory
/*     */ {
/*  39 */   public ItemStack[] inventory = new ItemStack[12];
/*     */   
/*     */   public boolean hasRack = false;
/*     */   public long burnStart;
/*     */   
/*     */   public boolean canAddItem(int slot) {
/*  45 */     if (this.inventory[0] != null && this.inventory[0].func_77973_b() instanceof net.minecraft.item.ItemBlock)
/*     */     {
/*  47 */       return false;
/*     */     }
/*     */     
/*  50 */     return (this.inventory[slot] == null);
/*     */   }
/*     */   public int straw;
/*     */   public int wood;
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox() {
/*  57 */     return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 1), (this.field_145849_e + 1));
/*     */   }
/*     */   
/*     */   private boolean isDone() {
/*  61 */     if (this.burnStart > 0L) {
/*  62 */       int hours = (int)(TFCOptions.pitKilnBurnTime - (float)(TFC_Time.getTotalHours() - this.burnStart / 1000L));
/*  63 */       float percent = Helper.roundNumber(100.0F - hours / TFCOptions.pitKilnBurnTime * 100.0F, 10.0F);
/*  64 */       return (percent >= 100.0F || hours < 0);
/*     */     } 
/*  66 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145845_h() {
/*  75 */     if (!this.field_145850_b.field_72995_K && this.straw > 0 && this.wood < 8)
/*     */     {
/*     */ 
/*     */       
/*  79 */       if (isFireNear().booleanValue()) {
/*     */         
/*  81 */         ejectContents();
/*  82 */         this.field_145850_b.func_147475_p(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*  83 */         this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d, this.field_145849_e, Blocks.field_150350_a);
/*  84 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */       } 
/*     */     }
/*     */     
/*  88 */     if (!this.field_145850_b.field_72995_K && this.wood == 8) {
/*     */       
/*  90 */       Block blockAbove = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
/*     */ 
/*     */       
/*  93 */       if (this.burnStart == 0L && isFireNear().booleanValue()) {
/*  94 */         startPitFire();
/*     */       }
/*     */       
/*  97 */       if (blockAbove != Blocks.field_150480_ab && (float)(TFC_Time.getTotalTicks() - this.burnStart) <= 1000.0F * TFCOptions.pitKilnBurnTime)
/*     */       {
/*  99 */         if ((blockAbove.isAir((IBlockAccess)this.field_145850_b, this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) || this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e).func_149688_o().func_76217_h()) && isValid()) {
/* 100 */           this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, (Block)Blocks.field_150480_ab);
/*     */         } else {
/*     */           
/* 103 */           this.wood = 0;
/* 104 */           this.inventory[4] = null; this.inventory[5] = null; this.inventory[6] = null; this.inventory[7] = null;
/* 105 */           this.inventory[8] = null; this.inventory[9] = null; this.inventory[10] = null; this.inventory[11] = null;
/* 106 */           this.straw = 0;
/* 107 */           this.burnStart = 0L;
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/* 112 */       if ((blockAbove == Blocks.field_150480_ab && (float)TFC_Time.getTotalTicks() >= (float)this.burnStart + TFCOptions.pitKilnBurnTime * 1000.0F) || isDone()) {
/*     */         
/* 114 */         this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
/* 115 */         if (this.inventory[0] != null) {
/*     */           
/* 117 */           this.inventory[0] = KilnCraftingManager.getInstance().findCompleteRecipe(new KilnRecipe(this.inventory[0], 0)).func_77946_l();
/* 118 */           if (this.inventory[0].func_77973_b() instanceof ItemPotteryBase)
/* 119 */             ((ItemPotteryBase)this.inventory[0].func_77973_b()).onDoneCooking(this.field_145850_b, this.inventory[0], Alloy.EnumTier.TierI); 
/*     */         } 
/* 121 */         if (this.inventory[1] != null) {
/*     */           
/* 123 */           this.inventory[1] = KilnCraftingManager.getInstance().findCompleteRecipe(new KilnRecipe(this.inventory[1], 0)).func_77946_l();
/* 124 */           if (this.inventory[1].func_77973_b() instanceof ItemPotteryBase)
/* 125 */             ((ItemPotteryBase)this.inventory[1].func_77973_b()).onDoneCooking(this.field_145850_b, this.inventory[1], Alloy.EnumTier.TierI); 
/*     */         } 
/* 127 */         if (this.inventory[2] != null) {
/*     */           
/* 129 */           this.inventory[2] = KilnCraftingManager.getInstance().findCompleteRecipe(new KilnRecipe(this.inventory[2], 0)).func_77946_l();
/* 130 */           if (this.inventory[2].func_77973_b() instanceof ItemPotteryBase)
/* 131 */             ((ItemPotteryBase)this.inventory[2].func_77973_b()).onDoneCooking(this.field_145850_b, this.inventory[2], Alloy.EnumTier.TierI); 
/*     */         } 
/* 133 */         if (this.inventory[3] != null) {
/*     */           
/* 135 */           this.inventory[3] = KilnCraftingManager.getInstance().findCompleteRecipe(new KilnRecipe(this.inventory[3], 0)).func_77946_l();
/* 136 */           if (this.inventory[3].func_77973_b() instanceof ItemPotteryBase) {
/* 137 */             ((ItemPotteryBase)this.inventory[3].func_77973_b()).onDoneCooking(this.field_145850_b, this.inventory[3], Alloy.EnumTier.TierI);
/*     */           }
/*     */         } 
/* 140 */         this.wood = 0;
/* 141 */         this.inventory[4] = null; this.inventory[5] = null; this.inventory[6] = null; this.inventory[7] = null;
/* 142 */         this.inventory[8] = null; this.inventory[9] = null; this.inventory[10] = null; this.inventory[11] = null;
/* 143 */         this.straw = 0;
/* 144 */         this.burnStart = 0L;
/* 145 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private Boolean isFireNear() {
/* 152 */     Boolean foundFire = Boolean.valueOf(false);
/*     */     
/* 154 */     for (int x = -1; x <= 1; x++) {
/*     */       
/* 156 */       for (int z = -1; z <= 1; z++) {
/*     */         
/* 158 */         if (this.field_145850_b.func_72899_e(this.field_145851_c + x, this.field_145848_d + 1, this.field_145849_e + z) && this.field_145850_b.func_147439_a(this.field_145851_c + x, this.field_145848_d + 1, this.field_145849_e + z) == Blocks.field_150480_ab) {
/* 159 */           foundFire = Boolean.valueOf(true);
/*     */         }
/*     */       } 
/*     */     } 
/* 163 */     return foundFire;
/*     */   }
/*     */ 
/*     */   
/*     */   public void startPitFire() {
/* 168 */     if (this.straw == 8 && this.wood == 8) {
/*     */       
/* 170 */       this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, (Block)Blocks.field_150480_ab);
/* 171 */       this.burnStart = TFC_Time.getTotalTicks();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addLog(ItemStack is, EntityPlayer player) {
/* 177 */     if (this.wood < 8)
/*     */     {
/* 179 */       if (!player.field_71075_bZ.field_75098_d) {
/*     */         
/* 181 */         for (int i = 4; i < 12; i++) {
/*     */           
/* 183 */           if (this.inventory[i] == null)
/*     */           {
/* 185 */             this.wood++;
/* 186 */             ItemStack itemStack = is.func_77946_l();
/* 187 */             is.field_77994_a--;
/* 188 */             itemStack.field_77994_a = 1;
/* 189 */             func_70299_a(i, itemStack);
/* 190 */             this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 191 */             return true;
/*     */           }
/*     */         
/*     */         } 
/*     */       } else {
/*     */         
/* 197 */         for (int i = 4; i < 12; i++) {
/*     */           
/* 199 */           if (this.inventory[i] == null) {
/*     */             
/* 201 */             this.wood++;
/* 202 */             ItemStack itemStack = is.func_77946_l();
/* 203 */             itemStack.field_77994_a = 1;
/* 204 */             func_70299_a(i, itemStack);
/*     */           } 
/*     */         } 
/* 207 */         this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 208 */         return true;
/*     */       } 
/*     */     }
/* 211 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addStraw(ItemStack is, EntityPlayer player) {
/* 216 */     if (this.straw < 8) {
/*     */       
/* 218 */       if (!player.field_71075_bZ.field_75098_d) {
/*     */         
/* 220 */         this.straw++;
/* 221 */         is.field_77994_a--;
/*     */       }
/*     */       else {
/*     */         
/* 225 */         this.straw = 8;
/*     */       } 
/* 227 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isValid() {
/* 236 */     boolean surroundSolids = (TFC_Core.isNorthFaceSolid(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e - 1) && TFC_Core.isSouthFaceSolid(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e + 1) && TFC_Core.isEastFaceSolid(this.field_145850_b, this.field_145851_c - 1, this.field_145848_d, this.field_145849_e) && TFC_Core.isWestFaceSolid(this.field_145850_b, this.field_145851_c + 1, this.field_145848_d, this.field_145849_e));
/* 237 */     return (surroundSolids && this.field_145850_b.isSideSolid(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e, ForgeDirection.UP));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLit() {
/* 242 */     return (TFC_Time.getTotalTicks() > this.burnStart && (float)(TFC_Time.getTotalTicks() - this.burnStart) < 1000.0F * TFCOptions.pitKilnBurnTime);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70299_a(int i, ItemStack itemstack) {
/* 248 */     this.inventory[i] = itemstack;
/* 249 */     if (itemstack != null && itemstack.field_77994_a > func_70297_j_()) {
/* 250 */       itemstack.field_77994_a = func_70297_j_();
/*     */     }
/*     */   }
/*     */   
/*     */   public void ejectContents() {
/* 255 */     float f3 = 0.05F;
/*     */     
/* 257 */     Random rand = new Random();
/* 258 */     float f = rand.nextFloat() * 0.8F;
/* 259 */     float f1 = rand.nextFloat() * 0.4F;
/* 260 */     float f2 = rand.nextFloat() * 0.8F;
/*     */     
/* 262 */     for (int i = 0; i < func_70302_i_(); i++) {
/*     */       
/* 264 */       if (this.inventory[i] != null) {
/*     */         
/* 266 */         EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), this.inventory[i]);
/* 267 */         entityitem.field_70159_w = ((float)rand.nextGaussian() * f3);
/* 268 */         entityitem.field_70181_x = ((float)rand.nextGaussian() * f3 + 0.2F);
/* 269 */         entityitem.field_70179_y = ((float)rand.nextGaussian() * f3);
/* 270 */         this.field_145850_b.func_72838_d((Entity)entityitem);
/* 271 */         this.inventory[i] = null;
/*     */       } 
/*     */     } 
/*     */     
/* 275 */     if (this.straw > 0) {
/*     */       
/* 277 */       EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), new ItemStack(TFCItems.straw, this.straw));
/* 278 */       entityitem.field_70159_w = ((float)rand.nextGaussian() * f3);
/* 279 */       entityitem.field_70181_x = ((float)rand.nextGaussian() * f3 + 0.2F);
/* 280 */       entityitem.field_70179_y = ((float)rand.nextGaussian() * f3);
/* 281 */       this.field_145850_b.func_72838_d((Entity)entityitem);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void ejectItem(int index) {
/* 290 */     if (this.inventory[index] != null) {
/*     */       
/* 292 */       EntityItem entityitem = new EntityItem(this.field_145850_b, this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, this.inventory[index]);
/* 293 */       entityitem.lifespan = 48000;
/* 294 */       this.field_145850_b.func_72838_d((Entity)entityitem);
/* 295 */       this.inventory[index] = null;
/*     */     } 
/*     */     
/* 298 */     if (this.inventory[0] == null && this.inventory[1] == null && this.inventory[2] == null && this.inventory[3] == null) {
/*     */ 
/*     */       
/* 301 */       int m = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 302 */       if (m > 0) {
/*     */         
/* 304 */         EntityItem entityitem = new EntityItem(this.field_145850_b, this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, new ItemStack(TFCItems.straw, m));
/* 305 */         entityitem.lifespan = 48000;
/* 306 */         this.field_145850_b.func_72838_d((Entity)entityitem);
/*     */       } 
/* 308 */       this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70300_a(EntityPlayer entityplayer) {
/* 315 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70295_k_() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70302_i_() {
/* 326 */     return this.inventory.length;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70301_a(int i) {
/* 332 */     return this.inventory[i];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70304_b(int var1) {
/* 338 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_145818_k_() {
/* 344 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_94041_b(int i, ItemStack itemstack) {
/* 350 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70298_a(int i, int j) {
/* 356 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String func_145825_b() {
/* 362 */     return "Pottery";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70297_j_() {
/* 368 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70305_f() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbttagcompound) {
/* 379 */     super.func_145841_b(nbttagcompound);
/* 380 */     NBTTagList nbttaglist = new NBTTagList();
/* 381 */     for (int i = 0; i < this.inventory.length; i++) {
/*     */       
/* 383 */       if (this.inventory[i] != null) {
/*     */         
/* 385 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 386 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 387 */         this.inventory[i].func_77955_b(nbttagcompound1);
/* 388 */         nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
/*     */       } 
/*     */     } 
/* 391 */     nbttagcompound.func_74782_a("Items", (NBTBase)nbttaglist);
/* 392 */     nbttagcompound.func_74772_a("burnStart", this.burnStart);
/* 393 */     nbttagcompound.func_74757_a("hasRack", this.hasRack);
/* 394 */     nbttagcompound.func_74768_a("wood", this.wood);
/* 395 */     nbttagcompound.func_74768_a("straw", this.straw);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbt) {
/* 401 */     super.func_145839_a(nbt);
/* 402 */     NBTTagList nbttaglist = nbt.func_150295_c("Items", 10);
/* 403 */     this.inventory = new ItemStack[func_70302_i_()];
/* 404 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*     */       
/* 406 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 407 */       byte byte0 = nbttagcompound1.func_74771_c("Slot");
/* 408 */       if (byte0 >= 0 && byte0 < this.inventory.length)
/* 409 */         this.inventory[byte0] = ItemStack.func_77949_a(nbttagcompound1); 
/*     */     } 
/* 411 */     this.burnStart = nbt.func_74763_f("burnStart");
/* 412 */     this.wood = nbt.func_74762_e("wood");
/* 413 */     this.hasRack = nbt.func_74767_n("hasRack");
/* 414 */     this.straw = nbt.func_74762_e("straw");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleInitPacket(NBTTagCompound nbt) {
/* 420 */     func_145839_a(nbt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleDataPacket(NBTTagCompound nbt) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createDataNBT(NBTTagCompound nbt) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createInitNBT(NBTTagCompound nbt) {
/* 440 */     func_145841_b(nbt);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEPottery.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */