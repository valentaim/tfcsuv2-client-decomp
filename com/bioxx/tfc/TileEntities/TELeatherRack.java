/*    */ package com.bioxx.tfc.TileEntities;
/*    */ 
/*    */ import com.bioxx.tfc.api.TFCItems;
/*    */ import fof.tfcsu.utils.ExpBonus;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.NBTBase;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ 
/*    */ public class TELeatherRack
/*    */   extends NetworkTileEntity
/*    */ {
/*    */   public short workedArea;
/*    */   public ItemStack leatherItem;
/*    */   
/*    */   public void setLeather(ItemStack is) {
/* 16 */     this.leatherItem = is.func_77946_l();
/* 17 */     this.leatherItem.field_77994_a = 1;
/* 18 */     is.field_77994_a--;
/*    */   }
/*    */ 
/*    */   
/*    */   public void workArea(int coord) {
/* 23 */     this.workedArea = (short)(this.workedArea | 1 << coord);
/* 24 */     if (this.workedArea == -1 && this.leatherItem != null) {
/*    */       
/* 26 */       int meta = this.leatherItem.func_77960_j();
/* 27 */       this.leatherItem = new ItemStack(TFCItems.scrapedHide, 1, meta);
/* 28 */       ExpBonus.LEATHER_RACK.spawnExp(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_145841_b(NBTTagCompound nbt) {
/* 35 */     super.func_145841_b(nbt);
/* 36 */     NBTTagCompound item = new NBTTagCompound();
/* 37 */     if (this.leatherItem != null) {
/*    */       
/* 39 */       this.leatherItem.func_77955_b(item);
/* 40 */       nbt.func_74782_a("leatherItem", (NBTBase)item);
/*    */     } 
/* 42 */     nbt.func_74777_a("workedArea", this.workedArea);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_145839_a(NBTTagCompound nbt) {
/* 48 */     super.func_145839_a(nbt);
/* 49 */     if (nbt.func_74764_b("leatherItem"))
/* 50 */       this.leatherItem = ItemStack.func_77949_a((NBTTagCompound)nbt.func_74781_a("leatherItem")); 
/* 51 */     this.workedArea = nbt.func_74765_d("workedArea");
/*    */   }
/*    */ 
/*    */   
/*    */   public void handleInitPacket(NBTTagCompound nbt) {
/* 56 */     if (nbt.func_74764_b("leatherItem"))
/* 57 */       this.leatherItem = ItemStack.func_77949_a((NBTTagCompound)nbt.func_74781_a("leatherItem")); 
/* 58 */     this.workedArea = nbt.func_74765_d("workedArea");
/*    */   }
/*    */ 
/*    */   
/*    */   public void createInitNBT(NBTTagCompound nbt) {
/* 63 */     NBTTagCompound item = new NBTTagCompound();
/* 64 */     if (this.leatherItem != null) {
/*    */       
/* 66 */       this.leatherItem.func_77955_b(item);
/* 67 */       nbt.func_74782_a("leatherItem", (NBTBase)item);
/*    */     } 
/* 69 */     nbt.func_74777_a("workedArea", this.workedArea);
/*    */   }
/*    */ 
/*    */   
/*    */   public void handleDataPacket(NBTTagCompound nbt) {
/* 74 */     this.workedArea = nbt.func_74765_d("workedArea");
/* 75 */     this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TELeatherRack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */