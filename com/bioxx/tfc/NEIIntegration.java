package com.bioxx.tfc;

import codechicken.nei.api.API;
import codechicken.nei.guihook.GuiContainerManager;
import codechicken.nei.guihook.IContainerTooltipHandler;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.TFCOptions;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;










public class NEIIntegration
{
  public static void hideNEIItems() {
    GuiContainerManager.addTooltipHandler(new IContainerTooltipHandler()
        {

          public List<String> handleTooltip(GuiContainer gui, int mousex, int mousey, List<String> currenttip)
          {
            return currenttip;
          }



          public List<String> handleItemDisplayName(GuiContainer gui, ItemStack itemstack, List<String> currenttip) {
            return currenttip;
          }



          public List<String> handleItemTooltip(GuiContainer gui, ItemStack itemstack, int mousex, int mousey, List<String> currenttip) {
            if (gui instanceof com.bioxx.tfc.GUI.GuiLargeVessel || gui instanceof com.bioxx.tfc.GUI.GuiBarrel) {

              Slot slot = gui.func_146975_c(mousex, mousey);
              if (slot != null && !slot.func_111238_b()) currenttip.clear();
            }
            return currenttip;
          }
        });

    if (TFCOptions.enableNEIHiding) {

      API.hideItem(new ItemStack(TFCBlocks.bloom));

      API.hideItem(new ItemStack(TFCBlocks.charcoal));

      API.hideItem(new ItemStack(TFCBlocks.crops));
      API.hideItem(new ItemStack(TFCBlocks.detailed));
      API.hideItem(new ItemStack(TFCBlocks.worldItem));
      for (Block door : TFCBlocks.doors)
      {
        API.hideItem(new ItemStack(door));
      }

      API.hideItem(new ItemStack(TFCBlocks.wattleDoor));
      API.hideItem(new ItemStack(TFCBlocks.firepit));
      API.hideItem(new ItemStack(TFCItems.flatClay, 1, 32767));
      API.hideItem(new ItemStack(TFCItems.flatLeather));
      API.hideItem(new ItemStack(TFCItems.flatRock, 1, 32767));
      API.hideItem(new ItemStack(TFCBlocks.foodPrep));
      API.hideItem(new ItemStack(TFCBlocks.forge));
      API.hideItem(new ItemStack(TFCBlocks.fruitTreeLeaves, 1, 32767));
      API.hideItem(new ItemStack(TFCBlocks.fruitTreeLeaves2, 1, 32767));
      API.hideItem(new ItemStack(TFCBlocks.fruitTreeWood, 1, 32767));

      API.hideItem(new ItemStack(TFCBlocks.ingotPile));
      API.hideItem(new ItemStack(TFCBlocks.leatherRack));
      API.hideItem(new ItemStack(TFCBlocks.leaves, 1, 32767));
      API.hideItem(new ItemStack(TFCBlocks.leaves2, 1, 32767));
      API.hideItem(new ItemStack(TFCBlocks.logNatural, 1, 32767));
      API.hideItem(new ItemStack(TFCBlocks.logNatural2, 1, 32767));
      API.hideItem(new ItemStack(TFCBlocks.logPile));
      API.hideItem(new ItemStack(TFCBlocks.woodConstruct));
      API.hideItem(new ItemStack(TFCBlocks.metalSheet));
      API.hideItem(new ItemStack(TFCBlocks.molten));
      API.hideItem(new ItemStack(TFCBlocks.moss));

      API.hideItem(new ItemStack(TFCBlocks.ore));
      API.hideItem(new ItemStack(TFCBlocks.ore2));
      API.hideItem(new ItemStack(TFCBlocks.ore3));
      API.hideItem(new ItemStack(TFCBlocks.pottery));

      API.hideItem(new ItemStack(TFCBlocks.reeds));
      API.hideItem(new ItemStack(TFCItems.salad, 1, 32767));
      API.hideItem(new ItemStack(TFCItems.sandwich, 1, 32767));
      API.hideItem(new ItemStack(TFCBlocks.sluice));
      API.hideItem(new ItemStack(TFCBlocks.smoke));
      API.hideItem(new ItemStack(TFCBlocks.smokeRack));

      API.hideItem(new ItemStack(TFCBlocks.stoneSlabs));
      API.hideItem(new ItemStack(TFCBlocks.stoneStairs));
      API.hideItem(new ItemStack(TFCBlocks.stoneStalac));
      API.hideItem(new ItemStack(TFCBlocks.strawHideBed));
      API.hideItem(new ItemStack(TFCBlocks.sulfur));
      API.hideItem(new ItemStack(TFCBlocks.torchOff));
      API.hideItem(new ItemStack(TFCBlocks.waterPlant));
      API.hideItem(new ItemStack(TFCBlocks.woodHoriz, 1, 32767));
      API.hideItem(new ItemStack(TFCBlocks.woodHoriz2, 1, 32767));
      API.hideItem(new ItemStack(TFCBlocks.woodHoriz3, 1, 32767));
      API.hideItem(new ItemStack(TFCBlocks.woodHoriz4, 1, 32767));
      API.hideItem(new ItemStack(TFCBlocks.woodSupportH, 1, 32767));
      API.hideItem(new ItemStack(TFCBlocks.woodSupportH2, 1, 32767));
      API.hideItem(new ItemStack(TFCBlocks.woodVert, 1, 32767));
      API.hideItem(new ItemStack(TFCBlocks.woodVert2, 1, 32767));














      API.hideItem(new ItemStack((Block)Blocks.field_150373_bw, 1, 32767));
      API.hideItem(new ItemStack((Block)Blocks.field_150376_bx, 1, 32767));
      API.hideItem(new ItemStack(Blocks.field_150476_ad, 1, 32767));
      API.hideItem(new ItemStack(Blocks.field_150400_ck, 1, 32767));
      API.hideItem(new ItemStack(Blocks.field_150401_cl, 1, 32767));
      API.hideItem(new ItemStack(Blocks.field_150485_bF, 1, 32767));
      API.hideItem(new ItemStack(Blocks.field_150487_bG, 1, 32767));
      API.hideItem(new ItemStack(Blocks.field_150481_bH, 1, 32767));
      API.hideItem(new ItemStack(Blocks.field_150392_bi, 1, 32767));
      API.hideItem(new ItemStack((Block)Blocks.field_150329_H, 1, 32767));
      API.hideItem(new ItemStack((Block)Blocks.field_150327_N, 1, 32767));
      API.hideItem(new ItemStack((Block)Blocks.field_150328_O, 1, 32767));
      API.hideItem(new ItemStack((Block)Blocks.field_150338_P, 1, 32767));
      API.hideItem(new ItemStack((Block)Blocks.field_150337_Q, 1, 32767));
      API.hideItem(new ItemStack(Blocks.field_150342_X, 1, 32767));
      API.hideItem(new ItemStack(Blocks.field_150478_aa, 1, 32767));
      API.hideItem(new ItemStack((Block)Blocks.field_150486_ae, 1, 32767));
      API.hideItem(new ItemStack(Blocks.field_150344_f, 1, 32767));
      API.hideItem(new ItemStack(Blocks.field_150462_ai, 1, 32767));
      API.hideItem(new ItemStack(Blocks.field_150434_aF, 1, 32767));
      API.hideItem(new ItemStack(Blocks.field_150436_aH, 1, 32767));
      API.hideItem(new ItemStack(Blocks.field_150423_aK, 1, 32767));
      API.hideItem(new ItemStack(Blocks.field_150428_aP, 1, 32767));
      API.hideItem(new ItemStack(Blocks.field_150471_bO, 1, 32767));
      API.hideItem(new ItemStack(Blocks.field_150432_aD, 1, 32767));
      API.hideItem(new ItemStack(Blocks.field_150395_bd, 1, 32767));
      API.hideItem(new ItemStack(Blocks.field_150457_bL, 1, 32767));
      API.hideItem(new ItemStack(Items.field_151162_bE, 1, 32767));
      API.hideItem(new ItemStack(Items.field_151120_aE, 1, 32767));
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\NEIIntegration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */