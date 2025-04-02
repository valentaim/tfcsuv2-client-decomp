package com.bioxx.tfc.Items.Tools;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.TileEntities.TEFoodPrep;
import com.bioxx.tfc.api.Enums.EnumDamageType;
import com.bioxx.tfc.api.Enums.EnumItemReach;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.Tools.IKnife;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ItemKnife
  extends ItemWeapon
  implements IKnife {
  public ItemKnife(Item.ToolMaterial e, float damage) {
    super(e, damage);
    func_77656_e(e.func_77997_a());
    this.damageType = EnumDamageType.PIERCING;
  }



  public EnumSize getSize(ItemStack is) {
    return EnumSize.SMALL;
  }



  public boolean canStack() {
    return false;
  }



  public boolean func_77648_a(ItemStack itemstack, EntityPlayer entityplayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    Block id = world.func_147439_a(x, y, z);
    if (!world.field_72995_K && id != TFCBlocks.toolRack) {

      int hasBowl = -1;

      for (int i = 0; i < 36 && hasBowl == -1; i++) {

        if (entityplayer.field_71071_by.field_70462_a[i] != null && entityplayer.field_71071_by.field_70462_a[i].func_77973_b() == TFCItems.potteryBowl && entityplayer.field_71071_by.field_70462_a[i].func_77960_j() == 1) {
          hasBowl = i;
        }
      }
      Material mat = world.func_147439_a(x, y, z).func_149688_o();

      if (side == 1 && id.isSideSolid((IBlockAccess)world, x, y, z, ForgeDirection.UP) && !TFC_Core.isSoil(id) && !TFC_Core.isWater(id) && world.func_147437_c(x, y + 1, z) && (mat == Material.field_151575_d || mat == Material.field_151576_e || mat == Material.field_151573_f)) {


        world.func_147449_b(x, y + 1, z, TFCBlocks.foodPrep);
        TEFoodPrep te = (TEFoodPrep)world.func_147438_o(x, y + 1, z);
        if (hasBowl != -1 && te != null) {

          te.func_70299_a(7, entityplayer.field_71071_by.field_70462_a[hasBowl]);
          entityplayer.field_71071_by.field_70462_a[hasBowl] = null;
        }
        return true;
      }
    }
    return false;
  }



  public void addExtraInformation(ItemStack is, EntityPlayer player, List<String> arraylist) {
    if (TFC_Core.showShiftInformation()) {

      arraylist.add(TFC_Core.translate("gui.Help"));
      arraylist.add(TFC_Core.translate("gui.Knife.Inst0"));
      arraylist.add(TFC_Core.translate("gui.Knife.Inst1"));

    }
    else {

      arraylist.add(TFC_Core.translate("gui.ShowHelp"));
    }
  }


  public EnumItemReach getReach(ItemStack is) {
    return EnumItemReach.SHORT;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Tools\ItemKnife.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */