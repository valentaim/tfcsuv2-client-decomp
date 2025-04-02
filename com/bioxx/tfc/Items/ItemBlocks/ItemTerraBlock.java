package com.bioxx.tfc.Items.ItemBlocks;

import com.bioxx.tfc.Items.ItemTerra;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.api.Enums.EnumItemReach;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import com.bioxx.tfc.api.Interfaces.ISize;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.TFC_ItemHeat;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;


public class ItemTerraBlock
  extends ItemBlock
  implements ISize
{
  public String[] metaNames;
  public IIcon[] icons;
  public String folder;

  public ItemTerraBlock(Block b) {
    super(b);
    func_77627_a(true);
    this.folder = "";
  }


  public ItemTerraBlock setFolder(String f) {
    this.folder = f;
    return this;
  }




  public String func_77667_c(ItemStack is) {
    try {
      if (this.metaNames != null && is.func_77960_j() < this.metaNames.length) {
        return func_77658_a().concat("." + this.metaNames[is.func_77960_j()]);
      }
    } catch (Exception ex) {

      TerraFirmaCraft.LOG.error(ex.getLocalizedMessage());
    }

    return super.func_77667_c(is);
  }






  public boolean onUpdate(ItemStack is, World world, int x, int y, int z) {
    return false;
  }



  public int func_77647_b(int i) {
    return i;
  }




  public void func_77624_a(ItemStack is, EntityPlayer player, List<String> arraylist, boolean flag) {
    ItemTerra.addSizeInformation(is, arraylist);

    if (is.func_77942_o())
    {
      if (TFC_ItemHeat.hasTemp(is)) {

        float temp = TFC_ItemHeat.getTemp(is);
        float meltTemp = TFC_ItemHeat.isCookable(is);

        if (meltTemp != -1.0F)
        {
          if (is.func_77973_b() == TFCItems.stick) {
            arraylist.add(TFC_ItemHeat.getHeatColorTorch(temp, meltTemp));
          } else {
            arraylist.add(TFC_ItemHeat.getHeatColor(temp, meltTemp));
          }
        }
      }
    }
  }


  public boolean func_77651_p() {
    return true;
  }



  public int getItemStackLimit(ItemStack is) {
    if (canStack()) {
      return (getSize((ItemStack)null)).stackSize * (getWeight((ItemStack)null)).multiplier;
    }
    return 1;
  }



  public EnumSize getSize(ItemStack is) {
    return EnumSize.VERYSMALL;
  }



  public boolean canStack() {
    return true;
  }



  public EnumWeight getWeight(ItemStack is) {
    return EnumWeight.HEAVY;
  }




  public void func_94581_a(IIconRegister registerer) {}



  public EnumItemReach getReach(ItemStack is) {
    return EnumItemReach.SHORT;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemBlocks\ItemTerraBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */