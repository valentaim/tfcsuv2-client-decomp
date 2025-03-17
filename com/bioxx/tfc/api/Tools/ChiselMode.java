/*    */ package com.bioxx.tfc.api.Tools;
/*    */ 
/*    */ import com.bioxx.tfc.Core.Player.PlayerInfo;
/*    */ import com.bioxx.tfc.Core.Player.PlayerManagerTFC;
/*    */ import com.bioxx.tfc.api.TFCBlocks;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.ResourceLocation;
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
/*    */ public class ChiselMode
/*    */ {
/*    */   private ResourceLocation resourcelocation;
/*    */   private int textureU;
/*    */   private int textureV;
/*    */   private int divX;
/*    */   private int divY;
/*    */   private int divZ;
/*    */   
/*    */   public ResourceLocation getResourceLocation() {
/* 30 */     return this.resourcelocation;
/*    */   }
/*    */   
/*    */   public int getTextureU() {
/* 34 */     return this.textureU;
/*    */   }
/*    */   
/*    */   public int getTextureV() {
/* 38 */     return this.textureV;
/*    */   }
/*    */   
/*    */   public int getDivX(Block block) {
/* 42 */     return this.divX;
/*    */   }
/*    */   
/*    */   public int getDivY(Block block) {
/* 46 */     return this.divY;
/*    */   }
/*    */   
/*    */   public int getDivZ(Block block) {
/* 50 */     return this.divZ;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onUsedHandler(World world, EntityPlayer player, int x, int y, int z, Block id, int meta, int side, float hitX, float hitY, float hitZ) {
/* 55 */     return false;
/*    */   }
/*    */   
/*    */   public void setDivision(int hitSide) {}
/*    */   
/*    */   public boolean isChiselable(Block block) {
/* 61 */     boolean isChiselable = (block == TFCBlocks.planks || block instanceof com.bioxx.tfc.Blocks.Terrain.BlockCobble || block instanceof com.bioxx.tfc.Blocks.Terrain.BlockStone || block.getClass().getName().toLowerCase().contains("blockbas") || block instanceof com.bioxx.tfc.Blocks.Terrain.BlockSmooth);
/*    */     
/* 63 */     return isChiselable;
/*    */   }
/*    */   
/*    */   public int hasChisel(EntityPlayer player) {
/* 67 */     int hasChisel = -1;
/* 68 */     if (player.field_71071_by.field_70462_a[player.field_71071_by.field_70461_c] != null && player.field_71071_by.field_70462_a[player.field_71071_by.field_70461_c].func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemChisel) {
/* 69 */       hasChisel = player.field_71071_by.field_70461_c;
/*    */     }
/* 71 */     return hasChisel;
/*    */   }
/*    */   
/*    */   public static PlayerInfo playerInfo(World world, EntityPlayer player) {
/*    */     PlayerInfo pi;
/* 76 */     if (!world.field_72995_K) {
/*    */       
/* 78 */       pi = PlayerManagerTFC.getInstance().getPlayerInfoFromPlayer(player);
/*    */     }
/*    */     else {
/*    */       
/* 82 */       pi = PlayerManagerTFC.getInstance().getClientPlayer();
/*    */     } 
/* 84 */     return pi;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Tools\ChiselMode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */