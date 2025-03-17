/*    */ package com.bioxx.tfc.TileEntities;
/*    */ 
/*    */ import com.bioxx.tfc.TerraFirmaCraft;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TEVessel
/*    */   extends TEBarrel
/*    */   implements IInventory
/*    */ {
/*    */   public int getTechLevel() {
/* 22 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMaxLiquid() {
/* 28 */     return 5000;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int func_70302_i_() {
/* 34 */     return 9;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public AxisAlignedBB getRenderBoundingBox() {
/* 41 */     return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 1), (this.field_145849_e + 1));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void switchTab(EntityPlayer player, int tab) {
/* 47 */     if (player != null)
/* 48 */       if (tab == 0) {
/* 49 */         player.openGui(TerraFirmaCraft.instance, 46, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 50 */       } else if (tab == 1) {
/* 51 */         player.openGui(TerraFirmaCraft.instance, 47, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*    */       }  
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEVessel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */