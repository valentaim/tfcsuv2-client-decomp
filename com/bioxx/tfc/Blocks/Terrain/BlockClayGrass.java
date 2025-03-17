/*    */ package com.bioxx.tfc.Blocks.Terrain;
/*    */ 
/*    */ import com.bioxx.tfc.Core.TFC_Core;
/*    */ import com.bioxx.tfc.api.TFCBlocks;
/*    */ import com.bioxx.tfc.api.TFCItems;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.util.ForgeDirection;
/*    */ 
/*    */ 
/*    */ public class BlockClayGrass
/*    */   extends BlockGrass
/*    */ {
/*    */   public BlockClayGrass(int texOff) {
/* 20 */     super(texOff);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int func_149645_b() {
/* 26 */     return TFCBlocks.clayGrassRenderId;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int func_149692_a(int dmg) {
/* 32 */     return dmg;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Item func_149650_a(int metadata, Random rand, int fortune) {
/* 38 */     return TFCItems.clayBall;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int func_149745_a(Random rand) {
/* 44 */     return 1 + rand.nextInt(3);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
/* 54 */     ArrayList<ItemStack> ret = new ArrayList<>();
/* 55 */     int count = func_149745_a(world.field_73012_v);
/* 56 */     Item item = func_149650_a(metadata, world.field_73012_v, fortune);
/* 57 */     for (int i = 0; i < count; i++)
/* 58 */       ret.add(new ItemStack(item, 1, 0)); 
/* 59 */     return ret;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_149695_a(World world, int x, int y, int z, Block b) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_149674_a(World world, int x, int y, int z, Random rand) {
/* 71 */     if (world.func_147439_a(x, y + 1, z).isSideSolid((IBlockAccess)world, x, y + 1, z, ForgeDirection.DOWN)) {
/* 72 */       world.func_147465_d(x, y, z, TFC_Core.getTypeForClay(world.func_72805_g(x, y, z) + this.textureOffset), world.func_72805_g(x, y, z), 2);
/* 73 */     } else if (world.func_72937_j(x, y + 1, z)) {
/*    */       
/* 75 */       spreadGrass(world, x, y, z, rand);
/*    */     } 
/*    */     
/* 78 */     world.func_147471_g(x, y, z);
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Terrain\BlockClayGrass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */