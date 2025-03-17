/*    */ package com.bioxx.tfc.Items;
/*    */ 
/*    */ import com.bioxx.tfc.Core.TFCTabs;
/*    */ import com.bioxx.tfc.api.TFCBlocks;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.util.ForgeDirection;
/*    */ 
/*    */ public class ItemYarn
/*    */   extends ItemTerra
/*    */ {
/* 15 */   protected final int[][] sidesMap = new int[][] { { 0, -1, 0 }, { 0, 1, 0 }, { 0, 0, -1 }, { 0, 0, 1 }, { -1, 0, 0 }, { 1, 0, 0 } };
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemYarn() {
/* 20 */     this.field_77787_bX = false;
/* 21 */     func_77656_e(0);
/* 22 */     func_77637_a(TFCTabs.TFC_MATERIALS);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_77648_a(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
/* 28 */     if (!world.field_72995_K && side > 1 && !world.func_147439_a(x, y, z).equals(TFCBlocks.loom)) {
/*    */       
/* 30 */       int length = 0;
/* 31 */       int[] map = this.sidesMap[side];
/* 32 */       ForgeDirection opp = ForgeDirection.VALID_DIRECTIONS[side].getOpposite(); int i;
/* 33 */       for (i = 1; i < 6; i++) {
/*    */         
/* 35 */         int xCoord = x + map[0] * i;
/* 36 */         int yCoord = y + map[1] * i;
/* 37 */         int zCoord = z + map[2] * i;
/* 38 */         Block b = world.func_147439_a(xCoord, yCoord, zCoord);
/*    */         
/* 40 */         if (b.func_149688_o().func_76222_j()) {
/*    */           
/* 42 */           length++;
/*    */         } else {
/* 44 */           if (!b.isSideSolid((IBlockAccess)world, xCoord, yCoord, zCoord, opp) && !(b instanceof com.bioxx.tfc.Blocks.BlockWoodSupport))
/*    */           {
/* 46 */             return false;
/*    */           }
/*    */           
/*    */           break;
/*    */         } 
/*    */       } 
/*    */       
/* 53 */       if (length == 5) {
/*    */         
/* 55 */         int xCoord = x + map[0] * 6;
/* 56 */         int yCoord = y + map[1] * 6;
/* 57 */         int zCoord = z + map[2] * 6;
/* 58 */         Block b = world.func_147439_a(xCoord, yCoord, zCoord);
/* 59 */         if (!b.isSideSolid((IBlockAccess)world, xCoord, yCoord, zCoord, opp) && !(b instanceof com.bioxx.tfc.Blocks.BlockWoodSupport))
/*    */         {
/* 61 */           return false;
/*    */         }
/*    */       } 
/*    */       
/* 65 */       if (is.field_77994_a < length) {
/* 66 */         return false;
/*    */       }
/* 68 */       for (i = 1; i <= length; i++) {
/*    */         
/* 70 */         int xCoord = x + map[0] * i;
/* 71 */         int yCoord = y + map[1] * i;
/* 72 */         int zCoord = z + map[2] * i;
/* 73 */         int meta = (side & 0x4) >> 2;
/* 74 */         world.func_147465_d(xCoord, yCoord, zCoord, TFCBlocks.smokeRack, meta, 2);
/* 75 */         is.field_77994_a--;
/*    */       } 
/*    */       
/* 78 */       return true;
/*    */     } 
/* 80 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemYarn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */