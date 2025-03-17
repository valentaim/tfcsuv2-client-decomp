/*    */ package com.bioxx.tfc.Containers.Slots;
/*    */ 
/*    */ import com.bioxx.tfc.Containers.ContainerPlayerTFC;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.inventory.Slot;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemArmor;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IIcon;
/*    */ 
/*    */ public class SlotArmorTFC
/*    */   extends Slot {
/*    */   public final int armorType;
/*    */   private final ContainerPlayerTFC parent;
/*    */   
/*    */   public SlotArmorTFC(ContainerPlayerTFC cont, IInventory inv, int index, int x, int y, int armortype) {
/* 20 */     super(inv, index, x, y);
/* 21 */     this.parent = cont;
/* 22 */     this.armorType = armortype;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int func_75219_a() {
/* 33 */     return 1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_75214_a(ItemStack par1ItemStack) {
/* 42 */     Item item = (par1ItemStack == null) ? null : par1ItemStack.func_77973_b();
/* 43 */     return (item != null && item.isValidArmor(par1ItemStack, this.armorType, (Entity)this.parent.getPlayer()));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_75212_b() {
/* 50 */     return ItemArmor.func_94602_b(this.armorType);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public boolean func_111238_b() {
/* 57 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\Slots\SlotArmorTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */