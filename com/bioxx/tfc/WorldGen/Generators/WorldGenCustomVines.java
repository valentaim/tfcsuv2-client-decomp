package com.bioxx.tfc.WorldGen.Generators;

import com.bioxx.tfc.api.TFCBlocks;
import java.util.Random;
import net.minecraft.util.Direction;
import net.minecraft.util.Facing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;





public class WorldGenCustomVines
  extends WorldGenerator
{
  public boolean func_76484_a(World world, Random r, int x, int y, int z) {
    int var6 = x;
    int var7 = z;
    
    for (; y > 144; y--) {
      
      if (world.func_147437_c(x, y, z)) {
        
        for (int var8 = 2; var8 <= 5; var8++) {
          
          if (TFCBlocks.vine.func_149707_d(world, x, y, z, var8)) {
            
            world.func_147465_d(x, y, z, TFCBlocks.vine, 1 << Direction.field_71579_d[Facing.field_71588_a[var8]], 2);

            
            break;
          } 
        } 
      } else {
        x = var6 + r.nextInt(4) - r.nextInt(4);
        z = var7 + r.nextInt(4) - r.nextInt(4);
      } 
    } 
    return true;
  }

  
  public boolean generate2(World world, Random random, int x, int y, int z) {
    int oldX = x;
    
    for (int i1 = z; y < 256; y++) {
      
      if (world.func_147437_c(x, y, z)) {
        
        for (int side = 2; side <= 5; side++) {
          
          if (TFCBlocks.vine.func_149707_d(world, x, y, z, side)) {
            
            world.func_147465_d(x, y, z, TFCBlocks.vine, 1 << Direction.field_71579_d[Facing.field_71588_a[side]], 2);

            
            break;
          } 
        } 
      } else {
        x = oldX + random.nextInt(4) - random.nextInt(4);
        z = i1 + random.nextInt(4) - random.nextInt(4);
      } 
    } 
    
    return true;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\WorldGenCustomVines.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */