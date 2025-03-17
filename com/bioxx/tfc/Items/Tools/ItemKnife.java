/*    */ package com.bioxx.tfc.Items.Tools;
/*    */ 
/*    */ import com.bioxx.tfc.Core.TFC_Core;
/*    */ import com.bioxx.tfc.TileEntities.TEFoodPrep;
/*    */ import com.bioxx.tfc.api.Enums.EnumDamageType;
/*    */ import com.bioxx.tfc.api.Enums.EnumItemReach;
/*    */ import com.bioxx.tfc.api.Enums.EnumSize;
/*    */ import com.bioxx.tfc.api.TFCBlocks;
/*    */ import com.bioxx.tfc.api.TFCItems;
/*    */ import com.bioxx.tfc.api.Tools.IKnife;
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.util.ForgeDirection;
/*    */ 
/*    */ public class ItemKnife
/*    */   extends ItemWeapon
/*    */   implements IKnife {
/*    */   public ItemKnife(Item.ToolMaterial e, float damage) {
/* 25 */     super(e, damage);
/* 26 */     func_77656_e(e.func_77997_a());
/* 27 */     this.damageType = EnumDamageType.PIERCING;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public EnumSize getSize(ItemStack is) {
/* 33 */     return EnumSize.SMALL;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canStack() {
/* 39 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_77648_a(ItemStack itemstack, EntityPlayer entityplayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
/* 45 */     Block id = world.func_147439_a(x, y, z);
/* 46 */     if (!world.field_72995_K && id != TFCBlocks.toolRack) {
/*    */       
/* 48 */       int hasBowl = -1;
/*    */       
/* 50 */       for (int i = 0; i < 36 && hasBowl == -1; i++) {
/*    */         
/* 52 */         if (entityplayer.field_71071_by.field_70462_a[i] != null && entityplayer.field_71071_by.field_70462_a[i].func_77973_b() == TFCItems.potteryBowl && entityplayer.field_71071_by.field_70462_a[i].func_77960_j() == 1) {
/* 53 */           hasBowl = i;
/*    */         }
/*    */       } 
/* 56 */       Material mat = world.func_147439_a(x, y, z).func_149688_o();
/*    */       
/* 58 */       if (side == 1 && id.isSideSolid((IBlockAccess)world, x, y, z, ForgeDirection.UP) && !TFC_Core.isSoil(id) && !TFC_Core.isWater(id) && world.func_147437_c(x, y + 1, z) && (mat == Material.field_151575_d || mat == Material.field_151576_e || mat == Material.field_151573_f)) {
/*    */ 
/*    */         
/* 61 */         world.func_147449_b(x, y + 1, z, TFCBlocks.foodPrep);
/* 62 */         TEFoodPrep te = (TEFoodPrep)world.func_147438_o(x, y + 1, z);
/* 63 */         if (hasBowl != -1 && te != null) {
/*    */           
/* 65 */           te.func_70299_a(7, entityplayer.field_71071_by.field_70462_a[hasBowl]);
/* 66 */           entityplayer.field_71071_by.field_70462_a[hasBowl] = null;
/*    */         } 
/* 68 */         return true;
/*    */       } 
/*    */     } 
/* 71 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void addExtraInformation(ItemStack is, EntityPlayer player, List<String> arraylist) {
/* 77 */     if (TFC_Core.showShiftInformation()) {
/*    */       
/* 79 */       arraylist.add(TFC_Core.translate("gui.Help"));
/* 80 */       arraylist.add(TFC_Core.translate("gui.Knife.Inst0"));
/* 81 */       arraylist.add(TFC_Core.translate("gui.Knife.Inst1"));
/*    */     
/*    */     }
/*    */     else {
/*    */       
/* 86 */       arraylist.add(TFC_Core.translate("gui.ShowHelp"));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumItemReach getReach(ItemStack is) {
/* 92 */     return EnumItemReach.SHORT;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Tools\ItemKnife.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */