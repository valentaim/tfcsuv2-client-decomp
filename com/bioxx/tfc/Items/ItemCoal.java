/*    */ package com.bioxx.tfc.Items;
/*    */ 
/*    */ import com.bioxx.tfc.Core.TFCTabs;
/*    */ import com.bioxx.tfc.api.Enums.EnumSize;
/*    */ import com.bioxx.tfc.api.Enums.EnumWeight;
/*    */ import com.bioxx.tfc.api.TFCBlocks;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.List;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemCoal
/*    */   extends ItemTerra
/*    */ {
/*    */   private int[][] map;
/*    */   
/*    */   public ItemCoal() {
/* 31 */     this.map = new int[][] { { 0, -1, 0 }, { 0, 1, 0 }, { 0, 0, -1 }, { 0, 0, 1 }, { -1, 0, 0 }, { 1, 0, 0 } };
/*    */     func_77627_a(true);
/*    */     func_77656_e(0);
/*    */     func_77637_a(TFCTabs.TFC_MATERIALS);
/*    */     this.metaNames = new String[] { "coal", "charcoal" };
/*    */     setWeight(EnumWeight.LIGHT);
/*    */     setSize(EnumSize.TINY);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_150895_a(Item item, CreativeTabs tabs, List<ItemStack> list) {
/* 44 */     list.add(new ItemStack(item, 1, 0));
/* 45 */     list.add(new ItemStack(item, 1, 1));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_77648_a(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
/* 51 */     if (is.func_77960_j() == 1 && !world.field_72995_K) {
/*    */       
/* 53 */       if (world.func_147439_a(x, y, z) == TFCBlocks.charcoal) {
/*    */         
/* 55 */         int meta = world.func_72805_g(x, y, z);
/* 56 */         if (meta < 8) {
/*    */           
/* 58 */           world.func_72921_c(x, y, z, meta + 1, 3);
/* 59 */           is.field_77994_a--;
/* 60 */           return true;
/*    */         } 
/* 62 */         if (side == 1 && world.func_147437_c(x + this.map[side][0], y + this.map[side][1], z + this.map[side][2])) {
/*    */           
/* 64 */           world.func_147465_d(x + this.map[side][0], y + this.map[side][1], z + this.map[side][2], TFCBlocks.charcoal, 1, 2);
/* 65 */           is.field_77994_a--;
/* 66 */           return true;
/*    */         } 
/*    */       } 
/*    */       
/* 70 */       if (world.func_147439_a(x + this.map[side][0], y + this.map[side][1], z + this.map[side][2]) == TFCBlocks.charcoal) {
/*    */         
/* 72 */         int meta = world.func_72805_g(x + this.map[side][0], y + this.map[side][1], z + this.map[side][2]);
/* 73 */         if (meta < 8) {
/*    */           
/* 75 */           world.func_72921_c(x + this.map[side][0], y + this.map[side][1], z + this.map[side][2], meta + 1, 3);
/* 76 */           is.field_77994_a--;
/* 77 */           return true;
/*    */         } 
/*    */       } 
/*    */       
/* 81 */       if (world.func_147437_c(x + this.map[side][0], y + this.map[side][1], z + this.map[side][2])) {
/*    */         
/* 83 */         world.func_147465_d(x + this.map[side][0], y + this.map[side][1], z + this.map[side][2], TFCBlocks.charcoal, 1, 2);
/* 84 */         is.field_77994_a--;
/* 85 */         TFCBlocks.charcoal.func_149695_a(world, x + this.map[side][0], y + this.map[side][1], z + this.map[side][2], world.func_147439_a(x + this.map[side][0], y + this.map[side][1], z + this.map[side][2]));
/*    */       } 
/* 87 */       return true;
/*    */     } 
/* 89 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemCoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */