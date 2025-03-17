/*     */ package com.bioxx.tfc.TileEntities;
/*     */ 
/*     */ import com.bioxx.tfc.Handlers.Network.AbstractPacket;
/*     */ import com.bioxx.tfc.Render.Models.ModelLoom;
/*     */ import com.bioxx.tfc.api.Constant.Global;
/*     */ import com.bioxx.tfc.api.Crafting.LoomManager;
/*     */ import com.bioxx.tfc.api.Crafting.LoomRecipe;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import fof.tfcsu.utils.ExpBonus;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TELoom
/*     */   extends NetworkTileEntity
/*     */   implements IInventory
/*     */ {
/*     */   public byte rotation;
/*     */   public int loomType;
/*     */   private ItemStack[] storage;
/*     */   private boolean weaving;
/*     */   private boolean finished;
/*     */   private ModelLoom model;
/*     */   private int clothCompletionCount;
/*     */   public LoomRecipe recipe;
/*  41 */   private final ResourceLocation defaultTexture = new ResourceLocation("terrafirmacraft", "textures/blocks/String.png");
/*     */   
/*     */   long lasttick;
/*     */   
/*     */   public boolean canUpdate() {
/*  46 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70305_f() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox() {
/*  64 */     return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 1), (this.field_145849_e + 1));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70298_a(int i, int j) {
/*  70 */     if (this.storage[i] != null) {
/*     */       
/*  72 */       if ((this.storage[i]).field_77994_a <= j) {
/*     */         
/*  74 */         ItemStack is = this.storage[i];
/*  75 */         this.storage[i] = null;
/*  76 */         return is;
/*     */       } 
/*  78 */       ItemStack isSplit = this.storage[i].func_77979_a(j);
/*  79 */       if ((this.storage[i]).field_77994_a == 0)
/*  80 */         this.storage[i] = null; 
/*  81 */       return isSplit;
/*     */     } 
/*     */ 
/*     */     
/*  85 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFinished() {
/*  90 */     return this.finished;
/*     */   }
/*     */   public TELoom() {
/*  93 */     this.lasttick = 0L;
/*     */     this.storage = new ItemStack[2];
/*     */   } public ItemStack addString(ItemStack i) {
/*  96 */     if (System.currentTimeMillis() < this.lasttick + 300L) {
/*  97 */       this.lasttick = System.currentTimeMillis();
/*  98 */       return i;
/*     */     } 
/* 100 */     this.lasttick = System.currentTimeMillis();
/* 101 */     if (!isFinished() && i != null && !this.field_145850_b.field_72995_K) {
/*     */       
/* 103 */       this.recipe = LoomManager.getInstance().findPotentialRecipes(this.storage[0]);
/* 104 */       if (this.storage[0] != null) {
/*     */         
/* 106 */         LoomRecipe lr = LoomManager.getInstance().findPotentialRecipes(i);
/* 107 */         if (lr != null && lr.equals(this.recipe))
/*     */         {
/* 109 */           if (getStringCount() < this.recipe.getReqSize())
/*     */           {
/* 111 */             i.field_77994_a--;
/* 112 */             (this.storage[0]).field_77994_a++;
/* 113 */             updateLoom();
/*     */           }
/*     */         
/*     */         }
/* 117 */       } else if (LoomManager.getInstance().hasPotentialRecipes(i)) {
/* 118 */         i.field_77994_a--;
/* 119 */         ItemStack is = i.func_77946_l();
/* 120 */         is.field_77994_a = 1;
/* 121 */         func_70299_a(0, is);
/*     */       } 
/*     */     } 
/* 124 */     return i;
/*     */   }
/*     */   
/*     */   public ItemStack takeFinishedCloth() {
/* 128 */     if (this.finished) {
/*     */       
/* 130 */       this.finished = false;
/* 131 */       this.clothCompletionCount = 0;
/* 132 */       ItemStack is = this.storage[1].func_77946_l();
/* 133 */       this.storage[1] = null;
/* 134 */       updateLoom();
/* 135 */       Item i = is.func_77973_b();
/* 136 */       if (i.equals(TFCItems.burlapCloth)) { ExpBonus.LOOM_BURLAP.give(this.entityplayer); }
/* 137 */       else if (i.equals(TFCItems.woolCloth)) { ExpBonus.LOOM_WOOL.give(this.entityplayer); }
/* 138 */       else if (i.equals(TFCItems.silkCloth)) { ExpBonus.LOOM_SILK.give(this.entityplayer); }
/* 139 */        return is;
/*     */     } 
/* 141 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void ejectContents() {
/* 146 */     float f3 = 0.05F;
/*     */     
/* 148 */     Random rand = new Random();
/* 149 */     float f = rand.nextFloat() * 0.3F + 0.1F;
/* 150 */     float f1 = rand.nextFloat() * 2.0F + 0.4F;
/* 151 */     float f2 = rand.nextFloat() * 0.3F + 0.1F;
/*     */     
/* 153 */     for (int i = 0; i < func_70302_i_(); i++) {
/*     */       
/* 155 */       if (this.storage[i] != null) {
/*     */         
/* 157 */         EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), this.storage[i]);
/* 158 */         entityitem.field_70159_w = ((float)rand.nextGaussian() * f3);
/* 159 */         entityitem.field_70181_x = ((float)rand.nextGaussian() * f3 + 0.2F);
/* 160 */         entityitem.field_70179_y = ((float)rand.nextGaussian() * f3);
/* 161 */         this.field_145850_b.func_72838_d((Entity)entityitem);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70297_j_() {
/* 169 */     return getRequiredStringCount();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String func_145825_b() {
/* 175 */     return "Loom";
/*     */   }
/*     */   
/*     */   public ModelLoom getModel() {
/* 179 */     if (this.field_145850_b.field_72995_K) {
/* 180 */       return this.model;
/*     */     }
/* 182 */     return null;
/*     */   }
/*     */   
/*     */   public void setModel(ModelLoom loomModel) {
/* 186 */     if (this.field_145850_b.field_72995_K) {
/* 187 */       this.model = loomModel;
/* 188 */       this.model.cloth = this.clothCompletionCount;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70302_i_() {
/* 195 */     return 2;
/*     */   }
/*     */   
/*     */   public ResourceLocation getWoodResource() {
/* 199 */     return new ResourceLocation("terrafirmacraft", "textures/blocks/wood/WoodSheet/" + Global.WOOD_ALL[this.loomType] + ".png");
/*     */   }
/*     */   
/*     */   public ResourceLocation getStringResource() {
/* 203 */     LoomRecipe resource = null;
/*     */     
/* 205 */     if (this.storage[1] != null) {
/* 206 */       resource = LoomManager.getInstance().findMatchingResult(this.storage[1]);
/*     */     } else {
/* 208 */       resource = LoomManager.getInstance().findPotentialRecipes(this.storage[0]);
/*     */     } 
/* 210 */     ResourceLocation rl = LoomManager.getInstance().findMatchingTexture(resource);
/* 211 */     return (resource != null && rl != null) ? rl : this.defaultTexture;
/*     */   }
/*     */   
/*     */   public Item getStringType() {
/* 215 */     return (this.storage[0] != null) ? this.storage[0].func_77973_b() : null;
/*     */   }
/*     */   
/*     */   public int getStringCount() {
/* 219 */     return (this.storage[0] != null) ? (this.storage[0]).field_77994_a : 0;
/*     */   }
/*     */   
/*     */   public void setString(ItemStack is) {
/* 223 */     this.storage[0] = is;
/* 224 */     if (!this.field_145850_b.field_72995_K) updateLoom(); 
/*     */   }
/*     */   
/*     */   public void setStringCount(int count) {
/* 228 */     if (this.storage[0] != null) (this.storage[0]).field_77994_a = count; 
/* 229 */     if (!this.field_145850_b.field_72995_K) updateLoom();
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack func_70301_a(int i) {
/* 235 */     return this.storage[i];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70304_b(int i) {
/* 241 */     return this.storage[i];
/*     */   }
/*     */ 
/*     */   
/*     */   public int getInvCount() {
/* 246 */     int count = 0;
/* 247 */     for (ItemStack is : this.storage) {
/*     */       
/* 249 */       if (is != null)
/* 250 */         count++; 
/*     */     } 
/* 252 */     if (this.storage[0] != null && count == 1)
/* 253 */       return 0; 
/* 254 */     return count;
/*     */   }
/*     */   
/*     */   public boolean canWeave() {
/* 258 */     this.recipe = LoomManager.getInstance().findMatchingRecipe(this.storage[0]);
/* 259 */     return (this.recipe != null && !this.finished);
/*     */   }
/*     */   
/*     */   public void setIsWeaving(boolean isWeaving) {
/* 263 */     if (canWeave()) {
/* 264 */       this.weaving = isWeaving;
/* 265 */       if (!this.field_145850_b.field_72995_K) updateLoom(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean getIsWeaving() {
/* 270 */     return this.weaving;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70300_a(EntityPlayer entityplayer) {
/* 277 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70295_k_() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70299_a(int i, ItemStack is) {
/* 288 */     this.storage[i] = is;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getInputStack() {
/* 293 */     return this.storage[0];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_145818_k_() {
/* 299 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_94041_b(int i, ItemStack itemstack) {
/* 305 */     return false;
/*     */   }
/*     */   
/*     */   public int getRequiredStringCount() {
/* 309 */     if (this.storage[0] != null) {
/*     */       
/* 311 */       this.recipe = LoomManager.getInstance().findPotentialRecipes(this.storage[0]);
/* 312 */       if (this.recipe != null) {
/* 313 */         return this.recipe.getReqSize();
/*     */       }
/*     */     }
/* 316 */     else if (this.storage[1] != null) {
/*     */       
/* 318 */       this.recipe = LoomManager.getInstance().findMatchingResult(this.storage[1]);
/* 319 */       if (this.recipe != null)
/*     */       {
/* 321 */         return this.recipe.getReqSize();
/*     */       }
/*     */     } 
/* 324 */     return 16;
/*     */   }
/*     */   
/*     */   public void finishCloth() {
/* 328 */     if (!this.finished) {
/* 329 */       NBTTagCompound nbt = new NBTTagCompound();
/* 330 */       this.weaving = false;
/* 331 */       this.finished = true;
/* 332 */       this.recipe = LoomManager.getInstance().findMatchingRecipe(this.storage[0]);
/* 333 */       this.storage[1] = this.recipe.getResult(this.storage[0]);
/* 334 */       setString((ItemStack)null);
/* 335 */       func_145841_b(nbt);
/* 336 */       broadcastPacketInRange((AbstractPacket)createDataPacket(nbt));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void dropItem() {
/* 341 */     if (!this.field_145850_b.field_72995_K) {
/* 342 */       ejectContents();
/*     */     }
/*     */   }
/*     */   
/*     */   public void finishWeaveCycle() {
/* 347 */     NBTTagCompound nbt = new NBTTagCompound();
/* 348 */     this.weaving = false;
/* 349 */     this.clothCompletionCount++;
/* 350 */     func_145841_b(nbt);
/* 351 */     broadcastPacketInRange((AbstractPacket)createDataPacket(nbt));
/*     */   }
/*     */   
/*     */   public void updateLoom() {
/* 355 */     NBTTagCompound nbt = new NBTTagCompound();
/* 356 */     func_145841_b(nbt);
/* 357 */     broadcastPacketInRange((AbstractPacket)createDataPacket(nbt));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCloth() {
/* 362 */     return this.clothCompletionCount;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbt) {
/* 368 */     super.func_145841_b(nbt);
/* 369 */     nbt.func_74768_a("loomType", this.loomType);
/* 370 */     nbt.func_74774_a("rotation", this.rotation);
/* 371 */     nbt.func_74757_a("weaving", this.weaving);
/* 372 */     nbt.func_74757_a("finished", this.finished);
/* 373 */     nbt.func_74768_a("cloth", this.clothCompletionCount);
/* 374 */     NBTTagList nbttaglist = new NBTTagList();
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
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbt) {
/* 391 */     super.func_145839_a(nbt);
/* 392 */     this.loomType = nbt.func_74762_e("loomType");
/* 393 */     this.weaving = nbt.func_74767_n("weaving");
/* 394 */     this.rotation = nbt.func_74771_c("rotation");
/* 395 */     this.finished = nbt.func_74767_n("finished");
/* 396 */     this.clothCompletionCount = nbt.func_74762_e("cloth");
/* 397 */     NBTTagList nbttaglist = nbt.func_150295_c("Items", 10);
/* 398 */     this.storage = new ItemStack[func_70302_i_()];
/* 399 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*     */       
/* 401 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 402 */       byte byte0 = nbttagcompound1.func_74771_c("Slot");
/* 403 */       if (byte0 >= 0 && byte0 < this.storage.length) {
/* 404 */         this.storage[byte0] = ItemStack.func_77949_a(nbttagcompound1);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void readFromItemNBT(NBTTagCompound nbt) {
/* 411 */     this.loomType = nbt.func_74762_e("loomType");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateGui() {
/* 426 */     this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleInitPacket(NBTTagCompound nbt) {
/* 433 */     func_145839_a(nbt);
/* 434 */     this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleDataPacket(NBTTagCompound nbt) {
/* 441 */     func_145839_a(nbt);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void createInitNBT(NBTTagCompound nbt) {
/* 447 */     func_145841_b(nbt);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void registerRecipes() {
/* 453 */     LoomManager.getInstance().addRecipe(new LoomRecipe(new ItemStack(TFCItems.woolYarn, 16), new ItemStack(TFCItems.woolCloth, 1)), new ResourceLocation("terrafirmacraft", "textures/blocks/String.png"));
/* 454 */     LoomManager.getInstance().addRecipe(new LoomRecipe(new ItemStack(Items.field_151007_F, 24), new ItemStack(TFCItems.silkCloth, 1)), new ResourceLocation("terrafirmacraft", "textures/blocks/Silk.png"));
/* 455 */     LoomManager.getInstance().addRecipe(new LoomRecipe(new ItemStack(TFCItems.juteFiber, 12), new ItemStack(TFCItems.burlapCloth, 1)), new ResourceLocation("terrafirmacraft", "textures/blocks/Rope.png"));
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TELoom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */