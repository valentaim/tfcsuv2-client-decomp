package com.bioxx.tfc.Handlers;

import com.bioxx.tfc.api.Events.AnvilCraftEvent;
import com.bioxx.tfc.api.Events.ItemCookEvent;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.TFC_ItemHeat;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;



public class AnvilCraftingHandler
{
  @SubscribeEvent
  public void onAnvilCraft(AnvilCraftEvent event) {
    if (event.input1.func_77973_b() == TFCItems.bloom && event.input1.func_77960_j() > 100) {

      TileEntity te = event.anvilTE;
      World world = te.func_145831_w();
      int dam = event.input1.func_77960_j();
      float temp = (event.input1.func_77978_p() != null) ? TFC_ItemHeat.getTemp(event.input1) : 0.0F;
      int count = dam / 100;
      int rem = dam % 100;
      while (count > 0) {

        ItemStack out1 = new ItemStack(TFCItems.bloom, 1, 100);
        TFC_ItemHeat.setTemp(out1, temp);

        EntityItem ei = new EntityItem(world, te.field_145851_c + 0.5D, te.field_145848_d + 1.5D, te.field_145849_e + 0.5D, out1);
        ei.field_70159_w = 0.0D; ei.field_70181_x = 0.0D; ei.field_70179_y = 0.0D; ei.field_145804_b = 0;
        world.func_72838_d((Entity)ei);

        count--;
      }
      if (rem > 0) {

        ItemStack out2 = new ItemStack(TFCItems.bloom, 1, rem);
        TFC_ItemHeat.setTemp(out2, temp);

        EntityItem ei = new EntityItem(world, te.field_145851_c + 0.5D, te.field_145848_d + 1.5D, te.field_145849_e + 0.5D, out2);
        ei.field_70159_w = 0.0D; ei.field_70181_x = 0.0D; ei.field_70179_y = 0.0D; ei.field_145804_b = 0;
        world.func_72838_d((Entity)ei);
      }
      event.result = null;
    }
  }









  @SubscribeEvent
  public void onItemMelt(ItemCookEvent event) {
    if (event.input1 != null)
    {
      if ((event.input1.func_77973_b() == TFCItems.bloom || event.input1.func_77973_b() == TFCItems.rawBloom) && event.result.func_77960_j() > 100) {

        event.result = event.input1;
        event.result.func_77964_b(event.result.func_77960_j() - 1);
      }
      else if ((event.input1.func_77973_b() == TFCItems.bloom || event.input1.func_77973_b() == TFCItems.rawBloom) && event.result.func_77960_j() <= 100) {

        event.result.func_77964_b(100 - event.input1.func_77960_j());
      }
      else if (event.result != null && event.result.func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood) {

        event.result.field_77990_d = event.input1.field_77990_d;
      }
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\AnvilCraftingHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */