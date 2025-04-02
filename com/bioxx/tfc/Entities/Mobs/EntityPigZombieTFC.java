package com.bioxx.tfc.Entities.Mobs;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;









public class EntityPigZombieTFC
  extends EntityZombieTFC
{
  public EntityPigZombieTFC(World par1World) {
    super(par1World);
  }
















  protected void func_70628_a(boolean par1, int par2) {
    int var3 = this.field_70146_Z.nextInt(2 + par2);
    int var4;
    for (var4 = 0; var4 < var3; var4++) {
      func_145779_a(Items.field_151078_bh, 1);
    }
    var3 = this.field_70146_Z.nextInt(2 + par2);
    for (var4 = 0; var4 < var3; var4++) {
      func_145779_a(Items.field_151074_bl, 1);
    }
  }


  protected void func_70600_l(int par1) {
    func_145779_a(Items.field_151043_k, 1);
  }






  protected Item func_146068_u() {
    return Items.field_151078_bh;
  }



  protected void func_82164_bB() {
    func_70062_b(0, new ItemStack(Items.field_151010_B));
  }



  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(200.0D);
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(4000.0D);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\Mobs\EntityPigZombieTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */