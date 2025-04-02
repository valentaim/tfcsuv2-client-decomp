package com.bioxx.tfc.Entities;

import com.bioxx.tfc.api.TFCBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecartChest;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;


public class EntityCustomMinecart
  extends EntityMinecartChest
{
  public EntityCustomMinecart(World par1World) {
    super(par1World);
  }


  public EntityCustomMinecart(World par1, double par2, double par4, double par6) {
    super(par1, par2, par4, par6);
  }



  public void func_94095_a(DamageSource par1DamageSource) {
    super.func_94095_a(par1DamageSource);
    func_145778_a(Item.func_150898_a(TFCBlocks.chest), 1, 0.0F);
  }






  public int func_70302_i_() {
    return 18;
  }



  public int func_94087_l() {
    return 1;
  }



  public Block func_145817_o() {
    return TFCBlocks.chest;
  }



  public int func_94085_r() {
    return 8;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\EntityCustomMinecart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */