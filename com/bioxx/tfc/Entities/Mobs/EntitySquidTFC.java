package com.bioxx.tfc.Entities.Mobs;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.TFCItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;



public class EntitySquidTFC
  extends EntitySquid
{
  public EntitySquidTFC(World par1World) {
    super(par1World);
    func_70105_a(0.75F, 0.75F);
  }






  public boolean func_70601_bi() {
    return (this.field_70163_u > 128.0D && this.field_70163_u <= 144.0D && this.field_70170_p.func_72855_b(this.field_70121_D));
  }


  public boolean func_70692_ba() {
    return !func_94056_bM();
  }



  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(400.0D);
  }


  public boolean func_70085_c(EntityPlayer player) {
    ItemStack itemstack = player.func_70694_bm();
    if (itemstack != null && itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.ItemCustomNameTag && itemstack.func_77942_o() && itemstack.field_77990_d.func_74764_b("ItemName")) {
      String name = itemstack.field_77990_d.func_74779_i("ItemName");

      func_94058_c(name);
      itemstack.field_77994_a--;

      return true;
    }
    return true;
  }







  protected void func_70628_a(boolean par1, int par2) {
    int j = this.field_70146_Z.nextInt(3 + par2) + 1;
    for (int k = 0; k < j; k++) {
      func_70099_a(new ItemStack(TFCItems.dye, 1, 0), 0.0F);
    }
    TFC_Core.animalDropMeat((Entity)this, TFCItems.calamariRaw, 50.0F + 50.0F * this.field_70146_Z.nextFloat());
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\Mobs\EntitySquidTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */