/*    */ package com.bioxx.tfc.Items.Tools;
/*    */ 
/*    */ import com.bioxx.tfc.Items.ItemTerra;
/*    */ import com.bioxx.tfc.api.Enums.EnumItemReach;
/*    */ import com.bioxx.tfc.api.Enums.EnumSize;
/*    */ import com.bioxx.tfc.api.Enums.EnumWeight;
/*    */ import com.bioxx.tfc.api.Interfaces.ISize;
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class ItemCustomSaw
/*    */   extends ItemCustomAxe
/*    */   implements ISize
/*    */ {
/*    */   public ItemCustomSaw(Item.ToolMaterial e) {
/* 20 */     super(e, 0.0F);
/* 21 */     func_77656_e((int)(e.func_77997_a() * 0.85D));
/* 22 */     this.field_77864_a = e.func_77998_b() * 1.35F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float func_150893_a(ItemStack par1ItemStack, Block par2Block) {
/* 28 */     return (par2Block != null && par2Block.func_149688_o() == Material.field_151575_d) ? (this.field_77864_a * 1.35F) : super.func_150893_a(par1ItemStack, par2Block);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_77624_a(ItemStack is, EntityPlayer player, List<String> arraylist, boolean flag) {
/* 34 */     ItemTerra.addSizeInformation(is, arraylist);
/* 35 */     ItemTerraTool.addSmithingBonusInformation(is, arraylist);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public EnumSize getSize(ItemStack is) {
/* 41 */     return EnumSize.MEDIUM;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canStack() {
/* 47 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public EnumWeight getWeight(ItemStack is) {
/* 53 */     return EnumWeight.MEDIUM;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumItemReach getReach(ItemStack is) {
/* 58 */     return EnumItemReach.SHORT;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Tools\ItemCustomSaw.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */