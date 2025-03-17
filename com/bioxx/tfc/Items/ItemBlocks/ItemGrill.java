/*    */ package com.bioxx.tfc.Items.ItemBlocks;
/*    */ 
/*    */ import com.bioxx.tfc.Core.TFCTabs;
/*    */ import com.bioxx.tfc.TileEntities.TEGrill;
/*    */ import com.bioxx.tfc.api.Enums.EnumSize;
/*    */ import com.bioxx.tfc.api.TFCBlocks;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.util.ForgeDirection;
/*    */ 
/*    */ 
/*    */ public class ItemGrill
/*    */   extends ItemTerraBlock
/*    */ {
/*    */   public ItemGrill(Block par1) {
/* 20 */     super(par1);
/* 21 */     func_77637_a(TFCTabs.TFC_TOOLS);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_77648_a(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
/* 27 */     if (!world.field_72995_K)
/*    */     {
/* 29 */       if (side == 1 && world.func_147437_c(x, y + 1, z)) {
/*    */         
/* 31 */         int out = side;
/* 32 */         int hinge = 0;
/*    */         
/* 34 */         if (hitX < 0.2D) {
/* 35 */           hinge = 0;
/* 36 */         } else if (hitZ < 0.2D) {
/* 37 */           hinge = 1;
/* 38 */         } else if (hitX > 0.8D) {
/* 39 */           hinge = 2;
/* 40 */         } else if (hitZ > 0.8D) {
/* 41 */           hinge = 3;
/*    */         } else {
/* 43 */           hinge = 0;
/*    */         } 
/* 45 */         out += hinge << 4;
/*    */         
/* 47 */         TileEntity teFire = world.func_147438_o(x, y, z);
/* 48 */         if (teFire != null && teFire instanceof com.bioxx.tfc.api.TileEntities.TEFireEntity && checkSides(world, x, y, z)) {
/*    */           
/* 50 */           if (world.func_147465_d(x, y + 1, z, TFCBlocks.grill, itemstack.func_77960_j(), 2))
/*    */           {
/* 52 */             TEGrill teGrill = (TEGrill)world.func_147438_o(x, y + 1, z);
/* 53 */             teGrill.data = (byte)out;
/*    */           }
/*    */         
/*    */         }
/* 57 */         else if (world.func_147437_c(x, y + 2, z) && checkSides(world, x, y + 1, z)) {
/*    */           
/* 59 */           if (world.func_147465_d(x, y + 2, z, TFCBlocks.grill, itemstack.func_77960_j(), 2)) {
/*    */             
/* 61 */             TEGrill teGrill = (TEGrill)world.func_147438_o(x, y + 2, z);
/* 62 */             teGrill.data = (byte)out;
/*    */           } 
/*    */         } else {
/*    */           
/* 66 */           return false;
/* 67 */         }  (player.field_71071_by.field_70462_a[player.field_71071_by.field_70461_c]).field_77994_a--;
/* 68 */         return true;
/*    */       } 
/*    */     }
/* 71 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean checkSides(World world, int x, int y, int z) {
/* 76 */     int count = 0;
/* 77 */     if (world.func_147439_a(x - 1, y, z).isSideSolid((IBlockAccess)world, x - 1, y, z, ForgeDirection.WEST))
/*    */     {
/* 79 */       count++;
/*    */     }
/* 81 */     if (world.func_147439_a(x + 1, y, z).isSideSolid((IBlockAccess)world, x + 1, y, z, ForgeDirection.EAST))
/*    */     {
/* 83 */       count++;
/*    */     }
/* 85 */     if (world.func_147439_a(x, y, z - 1).isSideSolid((IBlockAccess)world, x, y, z - 1, ForgeDirection.SOUTH))
/*    */     {
/* 87 */       count++;
/*    */     }
/* 89 */     if (world.func_147439_a(x, y, z + 1).isSideSolid((IBlockAccess)world, x, y, z + 1, ForgeDirection.NORTH))
/*    */     {
/* 91 */       count++;
/*    */     }
/* 93 */     return (count >= 2);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumSize getSize(ItemStack is) {
/* 98 */     return EnumSize.HUGE;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemBlocks\ItemGrill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */