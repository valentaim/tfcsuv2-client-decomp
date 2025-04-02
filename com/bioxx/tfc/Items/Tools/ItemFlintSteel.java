package com.bioxx.tfc.Items.Tools;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Items.ItemTerra;
import com.bioxx.tfc.TileEntities.TEPottery;
import com.bioxx.tfc.api.Enums.EnumItemReach;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import com.bioxx.tfc.api.Interfaces.ISize;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import fof.tfcsu.utils.ExpBonus;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;


public class ItemFlintSteel
  extends ItemFlintAndSteel
  implements ISize
{
  public ItemFlintSteel() {
    func_77637_a(TFCTabs.TFC_TOOLS);
  }



  public boolean func_77648_a(ItemStack itemstack, EntityPlayer entityplayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    if (!world.field_72995_K) {

      Block block = world.func_147439_a(x, y, z);
      boolean surroundSolids = TFC_Core.isSurroundedSolid(world, x, y, z);

      if ((block == TFCBlocks.charcoal && world.func_72805_g(x, y, z) > 6) || block == Blocks.field_150402_ci) {


        if (TFC_Core.isSurroundedStone(world, x, y, z) && surroundSolids) {

          triggerUse(itemstack, entityplayer, world, x, y, z);
          world.func_147465_d(x, y, z, TFCBlocks.forge, 1, 2);
          ExpBonus.SET_FIRE_TO.give(entityplayer);
          return true;
        }
      } else {

        if (block == TFCBlocks.pottery && surroundSolids) {

          TEPottery te = (TEPottery)world.func_147438_o(x, y, z);
          te.startPitFire();
          triggerUse(itemstack, entityplayer, world, x, y, z);
          return true;
        }

        if (side == 1 && TFC_Core.isTopFaceSolid(world, x, y, z) && world.func_147437_c(x, y + 1, z) && block
          .func_149688_o() != Material.field_151575_d && block.func_149688_o() != Material.field_151580_n) {

          List list = world.func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a(x, (y + 1), z, (x + 1), (y + 2), (z + 1)));
          int numsticks = 0;

          if (list != null && !list.isEmpty()) {

            for (Iterator<EntityItem> iterator = list.iterator(); iterator.hasNext(); ) {

              EntityItem entity = iterator.next();
              if (entity.func_92059_d().func_77973_b() == TFCItems.stick) {
                numsticks += (entity.func_92059_d()).field_77994_a;
              }
            }
            if (numsticks >= 3) {

              for (Iterator<EntityItem> iterator1 = list.iterator(); iterator1.hasNext(); ) {

                EntityItem entity = iterator1.next();
                if (entity.func_92059_d().func_77973_b() == TFCItems.stick || entity.func_92059_d().func_77973_b() == TFCItems.straw)
                  entity.func_70106_y();
              }
              triggerUse(itemstack, entityplayer, world, x, y, z);
              world.func_147465_d(x, y + 1, z, TFCBlocks.firepit, 1, 2);
              if (world.field_72995_K)
                world.func_147471_g(x, y + 1, z);
              return true;
            }
          }
        }
      }

      if (!block.func_149727_a(world, x, y, z, entityplayer, side, hitX, hitY, hitZ))
      {
        return super.func_77648_a(itemstack, entityplayer, world, x, y, z, side, hitX, hitY, hitZ);
      }
    }

    return false;
  }


  private void triggerUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z) {
    world.func_72908_a(x + 0.5D, y + 0.5D, z + 0.5D, "fire.ignite", 1.0F, field_77697_d.nextFloat() * 0.4F + 0.8F);
    is.func_77972_a(1, (EntityLivingBase)player);
  }




  public EnumSize getSize(ItemStack is) {
    return EnumSize.VERYSMALL;
  }



  public EnumWeight getWeight(ItemStack is) {
    return EnumWeight.LIGHT;
  }



  public boolean canStack() {
    return false;
  }



  public EnumItemReach getReach(ItemStack is) {
    return EnumItemReach.SHORT;
  }



  public void func_77624_a(ItemStack is, EntityPlayer player, List arraylist, boolean flag) {
    ItemTerra.addSizeInformation(is, arraylist);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Tools\ItemFlintSteel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */