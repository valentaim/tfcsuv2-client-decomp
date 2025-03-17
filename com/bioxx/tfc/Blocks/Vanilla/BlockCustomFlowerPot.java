/*     */ package com.bioxx.tfc.Blocks.Vanilla;
/*     */ 
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockFlowerPot;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityFlowerPot;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockCustomFlowerPot
/*     */   extends BlockFlowerPot
/*     */ {
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
/*  36 */     ItemStack itemstack = player.field_71071_by.func_70448_g();
/*     */     
/*  38 */     if (itemstack != null) {
/*     */       
/*  40 */       TileEntityFlowerPot teFlowerPot = getTileEntity(world, x, y, z);
/*     */       
/*  42 */       if (teFlowerPot != null) {
/*     */         
/*  44 */         if (teFlowerPot.func_145965_a() != null)
/*     */         {
/*  46 */           return false;
/*     */         }
/*     */         
/*  49 */         Item item = itemstack.func_77973_b();
/*  50 */         int meta = itemstack.func_77960_j();
/*     */         
/*  52 */         if (validItem(item, meta)) {
/*     */           
/*  54 */           teFlowerPot.func_145964_a(item, meta);
/*  55 */           teFlowerPot.func_70296_d();
/*     */           
/*  57 */           if (!world.func_72921_c(x, y, z, meta, 2))
/*     */           {
/*  59 */             world.func_147471_g(x, y, z);
/*     */           }
/*     */           
/*  62 */           if (!player.field_71075_bZ.field_75098_d && --itemstack.field_77994_a <= 0)
/*     */           {
/*  64 */             player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, (ItemStack)null);
/*     */           }
/*     */           
/*  67 */           return true;
/*     */         } 
/*     */ 
/*     */         
/*  71 */         return false;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*  76 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  81 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean validItem(Item item, int meta) {
/*  87 */     if (item instanceof net.minecraft.item.ItemBlock) {
/*     */       
/*  89 */       Block block = Block.func_149634_a(item);
/*     */ 
/*     */       
/*  92 */       if (block == TFCBlocks.cactus || block == TFCBlocks.flora || block == TFCBlocks.flowers || block == TFCBlocks.flowers2 || block == TFCBlocks.fungi || block == TFCBlocks.sapling || block == TFCBlocks.sapling2 || (block == TFCBlocks.tallGrass && meta == 1))
/*     */       {
/*  94 */         return true;
/*     */       }
/*     */       
/*  97 */       if (block == Blocks.field_150327_N || block == Blocks.field_150328_O || block == Blocks.field_150434_aF || block == Blocks.field_150338_P || block == Blocks.field_150337_Q || block == Blocks.field_150345_g || block == Blocks.field_150330_I || (block == Blocks.field_150329_H && meta == 2))
/*     */       {
/*  99 */         return true;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 104 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private TileEntityFlowerPot getTileEntity(World world, int x, int y, int z) {
/* 109 */     TileEntity tileentity = world.func_147438_o(x, y, z);
/* 110 */     return (tileentity != null && tileentity instanceof TileEntityFlowerPot) ? (TileEntityFlowerPot)tileentity : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public String func_149702_O() {
/* 120 */     return "terrafirmacraft:flower_pot";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Item func_149650_a(int i, Random rand, int j) {
/* 126 */     return Item.func_150898_a(TFCBlocks.flowerPot);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
/* 132 */     TileEntityFlowerPot teFlowerPot = getTileEntity(world, x, y, z);
/*     */     
/* 134 */     if (teFlowerPot != null)
/*     */     {
/* 136 */       return new ItemStack(teFlowerPot.func_145965_a(), 1, teFlowerPot.func_145966_b());
/*     */     }
/* 138 */     return new ItemStack(TFCBlocks.flowerPot);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149645_b() {
/* 147 */     return TFCBlocks.flowerPotRenderId;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Vanilla\BlockCustomFlowerPot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */