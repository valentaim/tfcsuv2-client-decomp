/*    */ package com.bioxx.tfc.Food;
/*    */ 
/*    */ import com.bioxx.tfc.Core.TFC_Core;
/*    */ import com.bioxx.tfc.Items.ItemTerra;
/*    */ import com.bioxx.tfc.api.Enums.EnumFoodGroup;
/*    */ import com.bioxx.tfc.api.Food;
/*    */ import com.bioxx.tfc.api.Interfaces.IFood;
/*    */ import java.util.List;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.EnumChatFormatting;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ public class ItemEgg
/*    */   extends ItemFoodTFC
/*    */   implements IFood
/*    */ {
/*    */   public ItemEgg() {
/* 22 */     super(EnumFoodGroup.Protein, 0, 0, 0, 0, 0, false, false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> list) {
/* 28 */     list.add(createTag(new ItemStack((Item)this, 1), 2.0F));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_77624_a(ItemStack is, EntityPlayer player, List<String> arraylist, boolean flag) {
/* 34 */     ItemTerra.addSizeInformation(is, arraylist);
/* 35 */     arraylist.add(getFoodGroupName(getFoodGroup()));
/* 36 */     addFoodHeatInformation(is, arraylist);
/*    */     
/* 38 */     if (is.func_77942_o()) {
/*    */       
/* 40 */       if (is.func_77978_p().func_74764_b("Fertilized")) {
/* 41 */         arraylist.add(EnumChatFormatting.GOLD + TFC_Core.translate("gui.fertilized"));
/*    */       } else {
/* 43 */         addFoodInformation(is, player, arraylist);
/*    */       } 
/*    */     } else {
/*    */       
/* 47 */       arraylist.add(TFC_Core.translate("gui.badnbt"));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onUpdate(ItemStack is, World world, int x, int y, int z) {
/* 57 */     if (is.func_77942_o()) {
/*    */       
/* 59 */       if (is.func_77978_p().func_74764_b("Fertilized")) {
/*    */         
/* 61 */         is.field_77990_d.func_82580_o("Fertilized");
/* 62 */         is.field_77990_d.func_82580_o("Genes");
/*    */       } 
/* 64 */       if (is.func_77978_p().func_74764_b("Fertilized"))
/*    */       {
/* 66 */         return true;
/*    */       }
/*    */     } 
/* 69 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float getDecayRate(ItemStack is) {
/* 75 */     if (Food.isPickled(is))
/* 76 */       return 0.3F; 
/* 77 */     return 0.5F;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Food\ItemEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */