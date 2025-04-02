package com.bioxx.tfc.Handlers;

import com.bioxx.tfc.Containers.ContainerAnvil;
import com.bioxx.tfc.Containers.ContainerBarrel;
import com.bioxx.tfc.Containers.ContainerBlastFurnace;
import com.bioxx.tfc.Containers.ContainerChestTFC;
import com.bioxx.tfc.Containers.ContainerCrucible;
import com.bioxx.tfc.Containers.ContainerFirepit;
import com.bioxx.tfc.Containers.ContainerFoodPrep;
import com.bioxx.tfc.Containers.ContainerForge;
import com.bioxx.tfc.Containers.ContainerGrill;
import com.bioxx.tfc.Containers.ContainerHopper;
import com.bioxx.tfc.Containers.ContainerHorseInventoryTFC;
import com.bioxx.tfc.Containers.ContainerLargeVessel;
import com.bioxx.tfc.Containers.ContainerLiquidVessel;
import com.bioxx.tfc.Containers.ContainerLogPile;
import com.bioxx.tfc.Containers.ContainerMold;
import com.bioxx.tfc.Containers.ContainerNestBox;
import com.bioxx.tfc.Containers.ContainerPlanSelection;
import com.bioxx.tfc.Containers.ContainerPlayerTFC;
import com.bioxx.tfc.Containers.ContainerQuern;
import com.bioxx.tfc.Containers.ContainerQuiver;
import com.bioxx.tfc.Containers.ContainerSluice;
import com.bioxx.tfc.Containers.ContainerSpecialCrafting;
import com.bioxx.tfc.Containers.ContainerVessel;
import com.bioxx.tfc.Containers.ContainerWorkbench;
import com.bioxx.tfc.Core.Player.PlayerInfo;
import com.bioxx.tfc.Core.Player.PlayerManagerTFC;
import com.bioxx.tfc.Entities.Mobs.EntityHorseTFC;
import com.bioxx.tfc.TileEntities.TEAnvil;
import com.bioxx.tfc.TileEntities.TEBarrel;
import com.bioxx.tfc.TileEntities.TEBlastFurnace;
import com.bioxx.tfc.TileEntities.TECrucible;
import com.bioxx.tfc.TileEntities.TEFirepit;
import com.bioxx.tfc.TileEntities.TEFoodPrep;
import com.bioxx.tfc.TileEntities.TEForge;
import com.bioxx.tfc.TileEntities.TEGrill;
import com.bioxx.tfc.TileEntities.TELogPile;
import com.bioxx.tfc.TileEntities.TENestBox;
import com.bioxx.tfc.TileEntities.TEQuern;
import com.bioxx.tfc.TileEntities.TESluice;
import com.bioxx.tfc.TileEntities.TEVessel;
import com.bioxx.tfc.TileEntities.TEWorkbench;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;




public class GuiHandler
  implements IGuiHandler
{
  public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
    PlayerInfo pi;
    TileEntity te = world.func_147438_o(x, y, z);

    switch (id) {


      case 0:
        return new ContainerLogPile(player.field_71071_by, (TELogPile)te, world, x, y, z);


      case 1:
        return new ContainerWorkbench(player.field_71071_by, (TEWorkbench)te, world, x, y, z);


      case 19:
        return new ContainerLiquidVessel(player.field_71071_by, world, x, y, z);


      case 20:
        return new ContainerFirepit(player.field_71071_by, (TEFirepit)te, world, x, y, z);


      case 21:
        return new ContainerAnvil(player.field_71071_by, (TEAnvil)te, world, x, y, z);


      case 22:
        return null;


      case 23:
        return new ContainerForge(player.field_71071_by, (TEForge)te, world, x, y, z);


      case 24:
        return new ContainerPlanSelection(player, (TEAnvil)te, world, x, y, z);


      case 25:
        return new ContainerSluice(player.field_71071_by, (TESluice)te, world, x, y, z);


      case 26:
        return new ContainerBlastFurnace(player.field_71071_by, (TEBlastFurnace)te, world, x, y, z);


      case 28:
        pi = PlayerManagerTFC.getInstance().getPlayerInfoFromPlayer(player);
        return new ContainerSpecialCrafting(player.field_71071_by, (pi.specialCraftingTypeAlternate == null) ? pi.specialCraftingType : null, world, x, y, z);


      case 29:
        return new ContainerChestTFC((IInventory)player.field_71071_by, (IInventory)te, world, x, y, z);


      case 31:
        return new ContainerPlayerTFC(player.field_71071_by, false, player);


      case 33:
        return new ContainerQuern(player.field_71071_by, (TEQuern)te, world, x, y, z);


      case 34:
        return null;


      case 35:
        return new ContainerBarrel(player.field_71071_by, (TEBarrel)te, world, x, y, z, 0);


      case 36:
        return new ContainerBarrel(player.field_71071_by, (TEBarrel)te, world, x, y, z, 1);


      case 37:
        return new ContainerCrucible(player.field_71071_by, (TECrucible)te, world, x, y, z);


      case 38:
        return new ContainerMold(player.field_71071_by, world, x, y, z);


      case 39:
        return new ContainerVessel(player.field_71071_by, world, x, y, z);


      case 40:
        return new ContainerQuiver(player.field_71071_by, world, x, y, z);


      case 41:
        return new ContainerNestBox(player.field_71071_by, (TENestBox)te, world, x, y, z);


      case 42:
        if (player.field_70154_o instanceof EntityHorseTFC) {

          EntityHorseTFC horse = (EntityHorseTFC)player.field_70154_o;
          return new ContainerHorseInventoryTFC(player.field_71071_by, (IInventory)horse.getHorseChest(), horse);
        }

        return null;


      case 43:
        return new ContainerGrill(player.field_71071_by, (TEGrill)te, world, x, y, z);

      case 44:
        return new ContainerFoodPrep(player.field_71071_by, (TEFoodPrep)te, world, x, y, z, 0);
      case 45:
        return new ContainerFoodPrep(player.field_71071_by, (TEFoodPrep)te, world, x, y, z, 1);
      case 46:
        return new ContainerLargeVessel(player.field_71071_by, (TEVessel)te, world, x, y, z, 0);
      case 47:
        return new ContainerLargeVessel(player.field_71071_by, (TEVessel)te, world, x, y, z, 1);

      case 48:
        return null;


      case 49:
        return new ContainerHopper(player.field_71071_by, (IInventory)te);
    }


    return null;
  }





  public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
    return null;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\GuiHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */