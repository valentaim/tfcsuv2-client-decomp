package com.bioxx.tfc.Handlers.Client;
import com.bioxx.tfc.Core.Player.PlayerInfo;
import com.bioxx.tfc.Entities.Mobs.EntityHorseTFC;
import com.bioxx.tfc.GUI.GuiAnvil;
import com.bioxx.tfc.GUI.GuiBarrel;
import com.bioxx.tfc.GUI.GuiChestTFC;
import com.bioxx.tfc.GUI.GuiFoodPrep;
import com.bioxx.tfc.GUI.GuiInventoryTFC;
import com.bioxx.tfc.GUI.GuiLargeVessel;
import com.bioxx.tfc.GUI.GuiWorkbench;
import com.bioxx.tfc.TileEntities.TEAnvil;
import com.bioxx.tfc.TileEntities.TEBarrel;
import com.bioxx.tfc.TileEntities.TEFirepit;
import com.bioxx.tfc.TileEntities.TEFoodPrep;
import com.bioxx.tfc.TileEntities.TEForge;
import com.bioxx.tfc.TileEntities.TEVessel;
import cuchaz.ships.EntityShip;
import cuchaz.ships.ShipWorld;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.event.GuiOpenEvent;

public class GuiHandler extends GuiHandler {
  public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
    ShipWorld shipWorld;
    TileEntity te;
    PlayerInfo pi;
    try {
      if (TerraFirmaCraft.instance.ShipsExist) {

        EntityShip ship = ShipLocator.getFromPlayerLook(player);
        if (ship != null) shipWorld = ship.getShipWorld();
      }
      te = shipWorld.func_147438_o(x, y, z);
    }
    catch (Exception e) {

      te = null;
    }

    switch (id) {

      case 0:
        return new GuiLogPile(player.field_71071_by, (TELogPile)te, (World)shipWorld, x, y, z);
      case 1:
        return new GuiWorkbench(player.field_71071_by, (TEWorkbench)te, (World)shipWorld, x, y, z);
      case 19:
        return new GuiVesselLiquid(player.field_71071_by, (World)shipWorld, x, y, z);
      case 20:
        return new GuiFirepit(player.field_71071_by, (TEFirepit)te, (World)shipWorld, x, y, z);
      case 21:
        return new GuiAnvil(player.field_71071_by, (TEAnvil)te, (World)shipWorld, x, y, z);
      case 22:
        return null;
      case 23:
        return new GuiForge(player.field_71071_by, (TEForge)te, (World)shipWorld, x, y, z);
      case 24:
        return new GuiPlanSelection(player, (TEAnvil)te, (World)shipWorld, x, y, z);
      case 25:
        return new GuiSluice(player.field_71071_by, (TESluice)te, (World)shipWorld, x, y, z);
      case 26:
        return new GuiBlastFurnace(player.field_71071_by, (TEBlastFurnace)te, (World)shipWorld, x, y, z);
      case 27:
        return new GuiCalendar(player);
      case 28:
        pi = PlayerManagerTFC.getInstance().getPlayerInfoFromPlayer(player);
        return new GuiKnapping(player.field_71071_by, (pi.specialCraftingTypeAlternate == null) ? pi.specialCraftingType : null, (World)shipWorld, x, y, z);
      case 29:
        return new GuiChestTFC((IInventory)player.field_71071_by, (IInventory)te, (World)shipWorld, x, y, z);
      case 31:
        return new GuiInventoryTFC(player);

      case 32:
      case 33:
        return new GuiQuern(player.field_71071_by, (TEQuern)te, (World)shipWorld, x, y, z);
      case 34:
        return new GuiBlueprint(player, (World)shipWorld, x, y, z);
      case 35:
        return new GuiBarrel(player.field_71071_by, (TEBarrel)te, (World)shipWorld, x, y, z, 0);
      case 36:
        return new GuiBarrel(player.field_71071_by, (TEBarrel)te, (World)shipWorld, x, y, z, 1);
      case 37:
        return new GuiCrucible(player.field_71071_by, (TECrucible)te, (World)shipWorld, x, y, z);
      case 38:
        return new GuiMold(player.field_71071_by, (World)shipWorld, x, y, z);
      case 39:
        return new GuiVessel(player.field_71071_by, (World)shipWorld, x, y, z);
      case 40:
        return new GuiQuiver(player.field_71071_by, (World)shipWorld, x, y, z);
      case 41:
        return new GuiNestBox(player.field_71071_by, (TENestBox)te, (World)shipWorld, x, y, z);

      case 42:
        if (player.field_70154_o instanceof EntityHorseTFC) {

          EntityHorseTFC horse = (EntityHorseTFC)player.field_70154_o;
          horse.updateChestSaddle();
          return new GuiScreenHorseInventoryTFC(player.field_71071_by, (IInventory)horse.getHorseChest(), horse);
        }

        return null;

      case 43:
        return new GuiGrill(player.field_71071_by, (TEGrill)te, (World)shipWorld, x, y, z);
      case 44:
        return new GuiFoodPrep(player.field_71071_by, (TEFoodPrep)te, (World)shipWorld, x, y, z, 0);
      case 45:
        return new GuiFoodPrep(player.field_71071_by, (TEFoodPrep)te, (World)shipWorld, x, y, z, 1);
      case 46:
        return new GuiLargeVessel(player.field_71071_by, (TEVessel)te, (World)shipWorld, x, y, z, 0);
      case 47:
        return new GuiLargeVessel(player.field_71071_by, (TEVessel)te, (World)shipWorld, x, y, z, 1);
      case 48:
        return new GuiCustomNametag(player, (World)shipWorld, x, y, z);

      case 49:
        return new GuiHopper(player.field_71071_by, (TEHopper)te, (World)shipWorld, x, y, z);
    }

    return null;
  }



  @SubscribeEvent
  public void openGuiHandler(GuiOpenEvent event) {
    if (event.gui instanceof net.minecraft.client.gui.inventory.GuiInventory && !(event.gui instanceof GuiInventoryTFC))
      event.gui = (GuiScreen)new GuiInventoryTFC((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\Client\GuiHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */