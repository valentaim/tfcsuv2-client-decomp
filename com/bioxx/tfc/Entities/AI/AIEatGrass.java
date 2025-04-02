package com.bioxx.tfc.Entities.AI;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.Entities.IAnimal;
import com.bioxx.tfc.api.TFCBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;



public class AIEatGrass
  extends EntityAIBase
{
  private EntityLiving theEntity;
  private World theWorld;
  private int eatGrassTick;
  
  public AIEatGrass(IAnimal animal) {
    this.theEntity = (EntityLiving)animal;
    this.theWorld = this.theEntity.field_70170_p;
    func_75248_a(7);
  }


  
  public boolean func_75250_a() {
    IAnimal animal = (IAnimal)this.theEntity;
    if (animal.getHunger() < 144000 && this.theWorld.field_73012_v.nextInt(10) == 0) {
      
      int i = MathHelper.func_76128_c(this.theEntity.field_70165_t);
      int j = MathHelper.func_76128_c(this.theEntity.field_70163_u);
      int k = MathHelper.func_76128_c(this.theEntity.field_70161_v);
      
      if (isBush(i, j, k)) return false;
      
      boolean isGrass = TFC_Core.isLushGrass(this.theWorld.func_147439_a(i, j - 1, k));
      boolean isTallGrass = (this.theWorld.func_147439_a(i, j, k) == TFCBlocks.tallGrass && this.theWorld.func_72805_g(i, j, k) == 1);
      return (isGrass || isTallGrass);
    } 
    return false;
  }
  
  private boolean isBush(int x, int y, int z) {
    Block block = this.theWorld.func_147439_a(x, y, z);
    if (!block.func_149739_a().toLowerCase().contains("bush")) {
      block = this.theWorld.func_147439_a(x, y + 1, z);
      if (!block.func_149739_a().toLowerCase().contains("bush")) {
        block = this.theWorld.func_147439_a(x, y - 1, z);
        if (!block.func_149739_a().toLowerCase().contains("bush")) return false; 
      } 
    } 
    return true;
  }





  
  public void func_75249_e() {
    this.eatGrassTick = 40;
    this.theWorld.func_72960_a((Entity)this.theEntity, (byte)10);
    this.theEntity.func_70661_as().func_75499_g();
  }





  
  public void func_75251_c() {
    this.eatGrassTick = 0;
  }





  
  public boolean func_75253_b() {
    return (this.eatGrassTick > 0);
  }

  
  public int getEatGrassTick() {
    return this.eatGrassTick;
  }





  
  public void func_75246_d() {
    this.eatGrassTick = Math.max(0, this.eatGrassTick - 1);
    
    if (this.eatGrassTick == 1) {
      
      int i = MathHelper.func_76128_c(this.theEntity.field_70165_t);
      int j = MathHelper.func_76128_c(this.theEntity.field_70163_u);
      int k = MathHelper.func_76128_c(this.theEntity.field_70161_v);
      
      if (isBush(i, j, k))
        return; 
      Block grass = this.theWorld.func_147439_a(i, j - 1, k);
      
      if (this.theWorld.func_147439_a(i, j, k) == TFCBlocks.tallGrass) {
        
        this.theWorld.func_147480_a(i, j, k, false);
        this.theEntity.func_70615_aA();
      }
      else if (TFC_Core.isLushGrass(grass)) {
        
        this.theWorld.func_72926_e(2001, i, j - 1, k, Block.func_149682_b((Block)Blocks.field_150349_c));
        TFC_Core.convertGrassToDirt(this.theWorld, i, j - 1, k);
        this.theEntity.func_70615_aA();
      } 
    } 
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\AI\AIEatGrass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */