package com.bioxx.tfc.api.Crafting;

import com.bioxx.tfc.api.HeatRegistry;
import com.bioxx.tfc.api.TFC_ItemHeat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;






public class ShapelessRecipesTFC
  implements IRecipe
{
  private final ItemStack recipeOutput;
  private final List recipeItems;

  public ShapelessRecipesTFC(ItemStack par1ItemStack, List par2List) {
    this.recipeOutput = par1ItemStack;
    this.recipeItems = par2List;
  }






  public ItemStack func_77572_b(InventoryCrafting par1InventoryCrafting) {
    return this.recipeOutput.func_77946_l();
  }



  public ItemStack func_77571_b() {
    return this.recipeOutput;
  }






  public int func_77570_a() {
    return this.recipeItems.size();
  }






  public boolean func_77569_a(InventoryCrafting par1InventoryCrafting, World world) {
    ArrayList var2 = new ArrayList(this.recipeItems);

    for (int var3 = 0; var3 < 5; var3++) {

      for (int var4 = 0; var4 < 5; var4++) {

        ItemStack inputIS = par1InventoryCrafting.func_70463_b(var4, var3);

        if (inputIS != null) {

          boolean var6 = false;
          Iterator<ItemStack> var7 = var2.iterator();

          while (var7.hasNext()) {

            ItemStack recipeIS = var7.next();

            if (inputIS.func_77973_b() == recipeIS.func_77973_b() && (recipeIS
              .func_77960_j() == 32767 || inputIS
              .func_77960_j() == recipeIS.func_77960_j()) &&
              tempMatch(recipeIS, inputIS)) {

              var6 = true;
              var2.remove(recipeIS);

              break;
            }
          }
          if (!var6)
          {
            return false;
          }
        }
      }
    }

    return var2.isEmpty();
  }


  private boolean tempMatch(ItemStack recipeIS, ItemStack inputIS) {
    NBTTagCompound rnbt = recipeIS.func_77978_p();
    NBTTagCompound inbt = inputIS.func_77978_p();

    if (rnbt != null && rnbt.func_74764_b("noTemp"))
    {

      return (inbt == null || !TFC_ItemHeat.hasTemp(inputIS));
    }

    if (rnbt != null && TFC_ItemHeat.hasTemp(recipeIS)) {

      if (inbt != null && TFC_ItemHeat.hasTemp(inputIS))
      {
        return HeatRegistry.getInstance().getIsLiquid(inputIS).booleanValue();
      }


      return false;
    }

    return true;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Crafting\ShapelessRecipesTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */