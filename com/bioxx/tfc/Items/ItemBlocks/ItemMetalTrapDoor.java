package com.bioxx.tfc.Items.ItemBlocks;

import com.bioxx.tfc.TileEntities.TEMetalTrapDoor;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.TFCBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


public class ItemMetalTrapDoor
  extends ItemTerraBlock
{
  public ItemMetalTrapDoor(Block par1) {
    super(par1);
  }



  public boolean func_77648_a(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    if (!world.field_72995_K) {

      int out = side;
      int hinge = 0;
      switch (side) {


        case 0:
          y--;
          if (hitX < 0.2D)
          { hinge = 0; }
          else if (hitZ < 0.2D)
          { hinge = 1; }
          else if (hitX > 0.8D)
          { hinge = 2; }
          else if (hitZ > 0.8D)
          { hinge = 3; }
          else { return false; }

          out += hinge << 4;
          break;


        case 1:
          y++;
          if (hitX < 0.2D)
          { hinge = 0; }
          else if (hitZ < 0.2D)
          { hinge = 1; }
          else if (hitX > 0.8D)
          { hinge = 2; }
          else if (hitZ > 0.8D)
          { hinge = 3; }
          else { return false; }

          out += hinge << 4;
          break;


        case 2:
          z--;
          if (hitX < 0.2D)
          { hinge = 0; }
          else if (hitY < 0.2D)
          { hinge = 1; }
          else if (hitX > 0.8D)
          { hinge = 2; }
          else if (hitY > 0.8D)
          { hinge = 3; }
          else { return false; }

          out += hinge << 4;
          break;


        case 3:
          z++;
          if (hitX < 0.2D)
          { hinge = 0; }
          else if (hitY < 0.2D)
          { hinge = 1; }
          else if (hitX > 0.8D)
          { hinge = 2; }
          else if (hitY > 0.8D)
          { hinge = 3; }
          else { return false; }

          out += hinge << 4;
          break;

        case 4:
          x--;
          if (hitY < 0.2D)
          { hinge = 0; }
          else if (hitZ < 0.2D)
          { hinge = 1; }
          else if (hitY > 0.8D)
          { hinge = 2; }
          else if (hitZ > 0.8D)
          { hinge = 3; }
          else { return false; }

          out += hinge << 4;
          break;

        case 5:
          x++;
          if (hitY < 0.2D)
          { hinge = 0; }
          else if (hitZ < 0.2D)
          { hinge = 1; }
          else if (hitY > 0.8D)
          { hinge = 2; }
          else if (hitZ > 0.8D)
          { hinge = 3; }
          else { return false; }

          out += hinge << 4;
          break;
      }

      if (world.func_147439_a(x, y, z).func_149688_o().func_76222_j())
      {
        if (world.func_147449_b(x, y, z, TFCBlocks.metalTrapDoor)) {

          TEMetalTrapDoor te = (TEMetalTrapDoor)world.func_147438_o(x, y, z);
          te.sheetStack = itemstack.func_77946_l();
          te.sheetStack.field_77994_a = 1;
          te.data = (byte)out;
          itemstack.field_77994_a--;
          return true;
        }
      }
    }
    return false;
  }


  public EnumSize getSize(ItemStack is) {
    return EnumSize.HUGE;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemBlocks\ItemMetalTrapDoor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */