/*    */ package com.bioxx.tfc.Items.ItemBlocks;
/*    */ 
/*    */ import com.bioxx.tfc.api.TFCBlocks;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemColored;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemCustomLilyPad
/*    */   extends ItemColored
/*    */ {
/*    */   public ItemCustomLilyPad(Block block) {
/* 19 */     super(block, false);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
/* 28 */     MovingObjectPosition movingobjectposition = func_77621_a(par2World, par3EntityPlayer, true);
/*    */     
/* 30 */     if (movingobjectposition == null)
/*    */     {
/* 32 */       return par1ItemStack;
/*    */     }
/*    */ 
/*    */     
/* 36 */     if (movingobjectposition.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
/*    */       
/* 38 */       int i = movingobjectposition.field_72311_b;
/* 39 */       int j = movingobjectposition.field_72312_c;
/* 40 */       int k = movingobjectposition.field_72309_d;
/*    */       
/* 42 */       if (!par2World.func_72962_a(par3EntityPlayer, i, j, k)) {
/* 43 */         return par1ItemStack;
/*    */       }
/* 45 */       if (!par3EntityPlayer.func_82247_a(i, j, k, movingobjectposition.field_72310_e, par1ItemStack)) {
/* 46 */         return par1ItemStack;
/*    */       }
/* 48 */       if (TFCBlocks.lilyPad.func_149718_j(par2World, i, j + 1, k) && par2World.func_147437_c(i, j + 1, k)) {
/*    */         
/* 50 */         par2World.func_147449_b(i, j + 1, k, TFCBlocks.lilyPad);
/* 51 */         par2World.func_72869_a("splash", i, (j + 2), k, 0.0D, 0.0D, 0.0D);
/* 52 */         par2World.func_72908_a((i + 0.5F), (j + 0.5F), (k + 0.5F), "random.splash", 0.5F, TFCBlocks.lilyPad.field_149762_H.func_150494_d() * 0.8F);
/* 53 */         if (!par3EntityPlayer.field_71075_bZ.field_75098_d)
/* 54 */           par1ItemStack.field_77994_a--; 
/*    */       } 
/*    */     } 
/* 57 */     return par1ItemStack;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int func_82790_a(ItemStack par1ItemStack, int par2) {
/* 65 */     return TFCBlocks.lilyPad.func_149741_i(par1ItemStack.func_77960_j());
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemBlocks\ItemCustomLilyPad.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */