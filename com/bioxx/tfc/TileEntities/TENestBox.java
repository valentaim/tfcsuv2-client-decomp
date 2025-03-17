/*     */ package com.bioxx.tfc.TileEntities;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntityChickenTFC;
/*     */ import com.bioxx.tfc.Food.ItemFoodTFC;
/*     */ import com.bioxx.tfc.api.Entities.IAnimal;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import com.bioxx.tfc.api.Util.Helper;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.passive.EntityAnimal;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ 
/*     */ public class TENestBox
/*     */   extends TileEntity implements IInventory {
/*  25 */   public ItemStack[] inventory = new ItemStack[4];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasBird() {
/*  33 */     return (getBird() != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBirdsNum() {
/*  40 */     List<EntityChickenTFC> list = this.field_145850_b.func_72872_a(EntityChickenTFC.class, AxisAlignedBB.func_72330_a((this.field_145851_c - 20), (this.field_145848_d - 20), (this.field_145849_e - 20), (this.field_145851_c + 20), (this.field_145848_d + 20), (this.field_145849_e + 20)));
/*     */ 
/*     */     
/*  43 */     return list.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityAnimal getBird() {
/*  50 */     List<EntityChickenTFC> list = this.field_145850_b.func_72872_a(EntityChickenTFC.class, AxisAlignedBB.func_72330_a(this.field_145851_c + 0.1D, this.field_145848_d, this.field_145849_e + 0.1D, this.field_145851_c + 0.9D, this.field_145848_d + 1.1D, this.field_145849_e + 0.9D));
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
/*     */     
/*  64 */     if (!list.isEmpty())
/*     */     {
/*  66 */       for (EntityChickenTFC e : list) {
/*     */         
/*  68 */         if (((EntityChickenTFC)e).getGender() == IAnimal.GenderEnum.FEMALE && ((EntityChickenTFC)e).isAdult() && ((EntityChickenTFC)e)
/*  69 */           .getAnimalTypeID() == Helper.stringToInt("chicken"))
/*     */         {
/*  71 */           return (EntityAnimal)e;
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*  76 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityAnimal getRooster() {
/*  82 */     List<EntityChickenTFC> list = this.field_145850_b.func_72872_a(EntityChickenTFC.class, AxisAlignedBB.func_72330_a((this.field_145851_c - 5), this.field_145848_d, (this.field_145849_e - 5), (this.field_145851_c + 5), (this.field_145848_d + 2), (this.field_145849_e + 5)));
/*     */ 
/*     */ 
/*     */     
/*  86 */     if (!list.isEmpty())
/*     */     {
/*  88 */       for (EntityChickenTFC e : list) {
/*     */         
/*  90 */         if (((EntityChickenTFC)e).getGender() == IAnimal.GenderEnum.MALE && ((EntityChickenTFC)e).isAdult())
/*  91 */           return (EntityAnimal)e; 
/*     */       } 
/*     */     }
/*  94 */     return null;
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
/* 105 */     if (this.inventory[i] != null) {
/*     */       
/* 107 */       if ((this.inventory[i]).field_77994_a <= j) {
/*     */         
/* 109 */         ItemStack itemstack = this.inventory[i];
/* 110 */         this.inventory[i] = null;
/* 111 */         return itemstack;
/*     */       } 
/* 113 */       ItemStack itemstack1 = this.inventory[i].func_77979_a(j);
/* 114 */       if ((this.inventory[i]).field_77994_a == 0)
/* 115 */         this.inventory[i] = null; 
/* 116 */       return itemstack1;
/*     */     } 
/*     */ 
/*     */     
/* 120 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void ejectContents() {
/* 126 */     float f3 = 0.05F;
/*     */     
/* 128 */     Random rand = new Random();
/* 129 */     float f = rand.nextFloat() * 0.8F + 0.1F;
/* 130 */     float f1 = rand.nextFloat() * 2.0F + 0.4F;
/* 131 */     float f2 = rand.nextFloat() * 0.8F + 0.1F;
/*     */     
/* 133 */     for (int i = 0; i < func_70302_i_(); i++) {
/*     */       
/* 135 */       if (this.inventory != null) {
/*     */         
/* 137 */         EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), this.inventory[i]);
/* 138 */         entityitem.field_70159_w = ((float)rand.nextGaussian() * f3);
/* 139 */         entityitem.field_70181_x = ((float)rand.nextGaussian() * f3 + 0.2F);
/* 140 */         entityitem.field_70179_y = ((float)rand.nextGaussian() * f3);
/* 141 */         this.field_145850_b.func_72838_d((Entity)entityitem);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70297_j_() {
/* 149 */     return 64;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String func_145825_b() {
/* 155 */     return "NestBox";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70302_i_() {
/* 161 */     return 4;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70301_a(int i) {
/* 167 */     return this.inventory[i];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70304_b(int var1) {
/* 173 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70300_a(EntityPlayer entityplayer) {
/* 179 */     return true;
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
/* 190 */     this.inventory[i] = itemstack;
/* 191 */     if (itemstack != null && itemstack.field_77994_a > func_70297_j_()) {
/* 192 */       itemstack.field_77994_a = func_70297_j_();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_145845_h() {
/* 198 */     if (!this.field_145850_b.field_72995_K) {
/*     */       
/* 200 */       EntityAnimal bird = getBird();
/* 201 */       if (bird != null) {
/*     */         
/* 203 */         ItemStack item = ((EntityChickenTFC)bird).getEggs();
/*     */         
/* 205 */         EntityChickenTFC father = (EntityChickenTFC)getRooster();
/* 206 */         for (int j = 0; item != null && j < func_70302_i_(); j++) {
/*     */           
/* 208 */           if (this.inventory[j] == null) {
/*     */             
/* 210 */             ItemFoodTFC.createTag(item, 2.0F);
/* 211 */             if (father != null) {
/*     */               
/* 213 */               NBTTagCompound nbt = item.func_77978_p();
/* 214 */               nbt.func_74772_a("Fertilized", TFC_Time.getTotalTicks() + (long)(TFCOptions.animalTimeMultiplier * (float)TFC_Time.ticksInMonth * 0.75F));
/* 215 */               nbt.func_74782_a("Genes", (NBTBase)createGenes((EntityChickenTFC)bird, father));
/* 216 */               item.func_77982_d(nbt);
/*     */             } 
/* 218 */             this.field_145850_b.func_72956_a((Entity)bird, "mob.chicken.plop", 1.0F, (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.2F + 1.0F);
/* 219 */             func_70299_a(j, item);
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 226 */       for (int i = 0; i < func_70302_i_(); i++) {
/*     */         
/* 228 */         if (this.inventory[i] != null)
/*     */         {
/* 230 */           if (this.inventory[i].func_77978_p() != null && this.inventory[i].func_77978_p().func_74764_b("Fertilized")) {
/*     */             
/* 232 */             long time = this.inventory[i].func_77978_p().func_74763_f("Fertilized");
/* 233 */             if (time <= TFC_Time.getTotalTicks()) {
/*     */               
/* 235 */               if (getBirdsNum() < 20) {
/*     */                 
/* 237 */                 EntityChickenTFC chick = new EntityChickenTFC(this.field_145850_b, this.field_145851_c + 0.5D, (this.field_145848_d + 1), this.field_145849_e + 0.5D, (NBTTagCompound)this.inventory[i].func_77978_p().func_74781_a("Genes"));
/* 238 */                 chick.func_70012_b(this.field_145851_c + 0.5D, (this.field_145848_d + 1), this.field_145849_e + 0.5D, 0.0F, 0.0F);
/* 239 */                 chick.field_70759_as = chick.field_70177_z;
/* 240 */                 chick.field_70761_aq = chick.field_70177_z;
/* 241 */                 this.field_145850_b.func_72838_d((Entity)chick);
/* 242 */               }  this.inventory[i] = null;
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_145818_k_() {
/* 253 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_94041_b(int i, ItemStack itemstack) {
/* 259 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound createGenes(EntityChickenTFC mother, EntityChickenTFC father) {
/* 264 */     NBTTagCompound nbt = new NBTTagCompound();
/* 265 */     nbt.func_74776_a("m_size", mother.getSizeMod());
/* 266 */     nbt.func_74776_a("f_size", father.getSizeMod());
/* 267 */     return nbt;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbttagcompound) {
/* 273 */     super.func_145841_b(nbttagcompound);
/* 274 */     NBTTagList nbttaglist = new NBTTagList();
/* 275 */     for (int i = 0; i < this.inventory.length; i++) {
/*     */       
/* 277 */       if (this.inventory[i] != null) {
/*     */         
/* 279 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 280 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 281 */         this.inventory[i].func_77955_b(nbttagcompound1);
/* 282 */         nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
/*     */       } 
/*     */     } 
/* 285 */     nbttagcompound.func_74782_a("Items", (NBTBase)nbttaglist);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbttagcompound) {
/* 291 */     super.func_145839_a(nbttagcompound);
/* 292 */     NBTTagList nbttaglist = nbttagcompound.func_150295_c("Items", 10);
/* 293 */     this.inventory = new ItemStack[func_70302_i_()];
/* 294 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*     */       
/* 296 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 297 */       byte byte0 = nbttagcompound1.func_74771_c("Slot");
/* 298 */       if (byte0 >= 0 && byte0 < this.inventory.length)
/* 299 */         this.inventory[byte0] = ItemStack.func_77949_a(nbttagcompound1); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TENestBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */