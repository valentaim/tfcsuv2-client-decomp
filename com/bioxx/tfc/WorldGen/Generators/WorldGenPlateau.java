/*    */ package com.bioxx.tfc.WorldGen.Generators;
/*    */ 
/*    */ import java.util.Random;
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
/*    */ 
/*    */ public class WorldGenPlateau
/*    */ {
/*    */   public boolean generate(World par1World, Random rand, int x, int y, int z, int radiusStart, int radiusTop, int height, int centers, int radiusJitter, int taperChance) {
/* 29 */     int radius = radiusStart;
/*    */     
/* 31 */     y = par1World.func_72825_h(x, z);
/* 32 */     for (int centerRun = 0; centerRun <= centers; centerRun++) {
/*    */       
/* 34 */       radius = radiusStart;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 41 */       for (int run = 0; run <= height; run++) {
/*    */ 
/*    */         
/* 44 */         if (radiusTop <= radius && rand.nextInt(taperChance) == 1)
/* 45 */           radius--; 
/*    */       } 
/*    */     } 
/* 48 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\WorldGenPlateau.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */