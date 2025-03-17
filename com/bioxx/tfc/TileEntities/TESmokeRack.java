/*     */ package com.bioxx.tfc.TileEntities;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Climate;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.api.Food;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ 
/*     */ public class TESmokeRack
/*     */   extends NetworkTileEntity
/*     */   implements IInventory {
/*  18 */   public ItemStack[] storage = new ItemStack[2];
/*  19 */   public int[] driedCounter = new int[] { 0, 0 };
/*     */ 
/*     */ 
/*     */   
/*     */   private int dryTimer;
/*     */ 
/*     */ 
/*     */   
/*     */   public int lastSmokedTime;
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145845_h() {
/*  32 */     float env = 1.0F;
/*  33 */     float base = 1.0F;
/*     */     
/*  35 */     if (TFC_Climate.getRainfall(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e) < 500.0F) {
/*     */       
/*  37 */       env = 0.75F; base = 0.75F;
/*     */     } 
/*     */     
/*  40 */     this.dryTimer++;
/*  41 */     if (this.dryTimer > 1000) {
/*     */       
/*  43 */       this.dryTimer = 0;
/*  44 */       dryFoods();
/*     */     } 
/*     */     
/*  47 */     if (!TFC_Core.isExposedToRain(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e) && TFC_Time.getTotalHours() > (this.lastSmokedTime + 1)) {
/*  48 */       TFC_Core.handleItemTicking(this, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, env, base);
/*  49 */     } else if (TFC_Climate.getHeightAdjustedTemp(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e) > 0.0F) {
/*  50 */       TFC_Core.handleItemTicking(this, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, env * 2.0F, base * 2.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox() {
/*  57 */     return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 1), (this.field_145849_e + 1));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbt) {
/*  63 */     super.func_145839_a(nbt);
/*  64 */     TFC_Core.readInventoryFromNBT(nbt, this.storage);
/*  65 */     this.driedCounter = nbt.func_74759_k("driedCounter");
/*  66 */     if (this.driedCounter.length == 0) {
/*  67 */       this.driedCounter = new int[] { 0, 0 };
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbt) {
/*  73 */     super.func_145841_b(nbt);
/*  74 */     TFC_Core.writeInventoryToNBT(nbt, this.storage);
/*  75 */     nbt.func_74783_a("driedCounter", this.driedCounter);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70302_i_() {
/*  81 */     return this.storage.length;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70301_a(int i) {
/*  87 */     return this.storage[i];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70298_a(int i, int j) {
/*  93 */     (this.storage[i]).field_77994_a -= j;
/*  94 */     return this.storage[i];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70304_b(int i) {
/* 100 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70299_a(int i, ItemStack itemstack) {
/* 106 */     boolean flag = false;
/* 107 */     if (!TFC_Core.areItemsEqual(this.storage[i], itemstack))
/*     */     {
/* 109 */       flag = true;
/*     */     }
/*     */     
/* 112 */     if (itemstack != null && !ItemStack.func_77989_b(itemstack, this.storage[i])) {
/*     */       
/* 114 */       if (Food.getDried(itemstack) > 0) {
/* 115 */         this.driedCounter[i] = (int)(TFC_Time.getTotalHours() - Food.getDried(itemstack));
/*     */       } else {
/* 117 */         this.driedCounter[i] = (int)TFC_Time.getTotalHours();
/* 118 */       }  flag = true;
/*     */     } 
/* 120 */     if (flag) {
/*     */       
/* 122 */       this.storage[i] = itemstack;
/* 123 */       broadcastPacketInRange();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack removeStackInSlot(int i) {
/* 129 */     ItemStack is = func_70301_a(i).func_77946_l();
/* 130 */     Food.setDried(is, (int)TFC_Time.getTotalHours() - this.driedCounter[i]);
/* 131 */     func_70299_a(i, (ItemStack)null);
/* 132 */     return is;
/*     */   }
/*     */ 
/*     */   
/*     */   public void dryFoods() {
/* 137 */     for (int i = 0; i < this.storage.length; i++) {
/*     */       
/* 139 */       if (func_70301_a(i) != null) {
/*     */         
/* 141 */         ItemStack is = func_70301_a(i);
/* 142 */         Food.setDried(is, (int)TFC_Time.getTotalHours() - this.driedCounter[i]);
/* 143 */         this.driedCounter[i] = (int)(TFC_Time.getTotalHours() - Food.getDried(is));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String func_145825_b() {
/* 152 */     return "";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_145818_k_() {
/* 158 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70297_j_() {
/* 164 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70300_a(EntityPlayer entityplayer) {
/* 170 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70295_k_() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70305_f() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_94041_b(int i, ItemStack itemstack) {
/* 186 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleInitPacket(NBTTagCompound nbt) {
/* 192 */     TFC_Core.readInventoryFromNBT(nbt, this.storage);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleDataPacket(NBTTagCompound nbt) {
/* 198 */     this.storage = new ItemStack[this.storage.length];
/* 199 */     TFC_Core.readInventoryFromNBT(nbt, this.storage);
/* 200 */     this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void createDataNBT(NBTTagCompound nbt) {
/* 206 */     TFC_Core.writeInventoryToNBT(nbt, this.storage);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void createInitNBT(NBTTagCompound nbt) {
/* 212 */     TFC_Core.writeInventoryToNBT(nbt, this.storage);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TESmokeRack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */