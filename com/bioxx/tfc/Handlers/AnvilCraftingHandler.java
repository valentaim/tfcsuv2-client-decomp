/*    */ package com.bioxx.tfc.Handlers;
/*    */ 
/*    */ import com.bioxx.tfc.api.Events.AnvilCraftEvent;
/*    */ import com.bioxx.tfc.api.Events.ItemCookEvent;
/*    */ import com.bioxx.tfc.api.TFCItems;
/*    */ import com.bioxx.tfc.api.TFC_ItemHeat;
/*    */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AnvilCraftingHandler
/*    */ {
/*    */   @SubscribeEvent
/*    */   public void onAnvilCraft(AnvilCraftEvent event) {
/* 20 */     if (event.input1.func_77973_b() == TFCItems.bloom && event.input1.func_77960_j() > 100) {
/*    */       
/* 22 */       TileEntity te = event.anvilTE;
/* 23 */       World world = te.func_145831_w();
/* 24 */       int dam = event.input1.func_77960_j();
/* 25 */       float temp = (event.input1.func_77978_p() != null) ? TFC_ItemHeat.getTemp(event.input1) : 0.0F;
/* 26 */       int count = dam / 100;
/* 27 */       int rem = dam % 100;
/* 28 */       while (count > 0) {
/*    */         
/* 30 */         ItemStack out1 = new ItemStack(TFCItems.bloom, 1, 100);
/* 31 */         TFC_ItemHeat.setTemp(out1, temp);
/*    */         
/* 33 */         EntityItem ei = new EntityItem(world, te.field_145851_c + 0.5D, te.field_145848_d + 1.5D, te.field_145849_e + 0.5D, out1);
/* 34 */         ei.field_70159_w = 0.0D; ei.field_70181_x = 0.0D; ei.field_70179_y = 0.0D; ei.field_145804_b = 0;
/* 35 */         world.func_72838_d((Entity)ei);
/*    */         
/* 37 */         count--;
/*    */       } 
/* 39 */       if (rem > 0) {
/*    */         
/* 41 */         ItemStack out2 = new ItemStack(TFCItems.bloom, 1, rem);
/* 42 */         TFC_ItemHeat.setTemp(out2, temp);
/*    */         
/* 44 */         EntityItem ei = new EntityItem(world, te.field_145851_c + 0.5D, te.field_145848_d + 1.5D, te.field_145849_e + 0.5D, out2);
/* 45 */         ei.field_70159_w = 0.0D; ei.field_70181_x = 0.0D; ei.field_70179_y = 0.0D; ei.field_145804_b = 0;
/* 46 */         world.func_72838_d((Entity)ei);
/*    */       } 
/* 48 */       event.result = null;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onItemMelt(ItemCookEvent event) {
/* 62 */     if (event.input1 != null)
/*    */     {
/* 64 */       if ((event.input1.func_77973_b() == TFCItems.bloom || event.input1.func_77973_b() == TFCItems.rawBloom) && event.result.func_77960_j() > 100) {
/*    */         
/* 66 */         event.result = event.input1;
/* 67 */         event.result.func_77964_b(event.result.func_77960_j() - 1);
/*    */       }
/* 69 */       else if ((event.input1.func_77973_b() == TFCItems.bloom || event.input1.func_77973_b() == TFCItems.rawBloom) && event.result.func_77960_j() <= 100) {
/*    */         
/* 71 */         event.result.func_77964_b(100 - event.input1.func_77960_j());
/*    */       }
/* 73 */       else if (event.result != null && event.result.func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood) {
/*    */         
/* 75 */         event.result.field_77990_d = event.input1.field_77990_d;
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\AnvilCraftingHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */