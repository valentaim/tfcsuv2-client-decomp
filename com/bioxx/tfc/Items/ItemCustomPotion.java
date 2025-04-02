package com.bioxx.tfc.Items;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.TFCItems;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;








public class ItemCustomPotion
  extends ItemPotion
{
  public ItemCustomPotion() {
    func_77637_a(TFCTabs.TFC_FOODS);
  }



  public ItemStack func_77654_b(ItemStack is, World world, EntityPlayer player) {
    if (!player.field_71075_bZ.field_75098_d)
    {
      is.field_77994_a--;
    }

    if (!world.field_72995_K) {

      List var4 = func_77832_l(is);

      if (var4 != null) {

        Iterator<PotionEffect> var5 = var4.iterator();

        while (var5.hasNext())
        {
          PotionEffect var6 = var5.next();
          player.func_70690_d(new PotionEffect(var6));
        }

      } else {

        TFC_Core.getPlayerFoodStats(player).restoreWater(player, 12000);
      }
    }

    if (!player.field_71075_bZ.field_75098_d) {

      boolean broken = false;
      if (world.field_73012_v.nextInt(50) == 0) {

        player.func_85030_a("random.glass", 0.7F, player.field_70170_p.field_73012_v.nextFloat() * 0.2F + 0.8F);
        broken = true;
      }

      if (!broken && is.field_77994_a <= 0)
      {
        return new ItemStack(TFCItems.glassBottle);
      }
    }

    return is;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemCustomPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */