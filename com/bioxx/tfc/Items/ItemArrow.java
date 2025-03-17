/*    */ package com.bioxx.tfc.Items;
/*    */ 
/*    */ import com.bioxx.tfc.api.Enums.EnumAmmo;
/*    */ import com.bioxx.tfc.api.Enums.EnumSize;
/*    */ import com.bioxx.tfc.api.Enums.EnumWeight;
/*    */ import com.bioxx.tfc.api.Interfaces.IQuiverAmmo;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ 
/*    */ 
/*    */ public class ItemArrow
/*    */   extends ItemTerra
/*    */   implements IQuiverAmmo
/*    */ {
/*    */   public ItemArrow() {
/* 15 */     setSize(EnumSize.LARGE);
/* 16 */     setWeight(EnumWeight.LIGHT);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_94581_a(IIconRegister registerer) {
/* 22 */     this.field_77791_bV = registerer.func_94245_a("minecraft:arrow");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public EnumAmmo getAmmoType() {
/* 28 */     return EnumAmmo.ARROW;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemArrow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */