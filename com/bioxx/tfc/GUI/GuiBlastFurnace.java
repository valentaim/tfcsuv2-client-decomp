package com.bioxx.tfc.GUI;

import com.bioxx.tfc.Containers.ContainerBlastFurnace;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.TileEntities.TEBlastFurnace;
import com.bioxx.tfc.api.TFCOptions;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiBlastFurnace
  extends GuiContainerTFC
{
  public static ResourceLocation texture = new ResourceLocation("terrafirmacraft", "textures/gui/gui_blastfurnace.png");

  private TEBlastFurnace blastFurnaceTE;


  public GuiBlastFurnace(InventoryPlayer inventoryplayer, TEBlastFurnace te, World world, int x, int y, int z) {
    super((Container)new ContainerBlastFurnace(inventoryplayer, te, world, x, y, z), 176, 85);
    this.blastFurnaceTE = te;
  }



  protected void func_146976_a(float f, int i, int j) {
    drawGui(texture);
  }



  protected void drawForeground(int guiLeft, int guiTop) {
    int scale = 0;

    scale = this.blastFurnaceTE.getTemperatureScaled(49);
    func_73729_b(guiLeft + 8, guiTop + 65 - scale, 185, 31, 15, 6);

    scale = this.blastFurnaceTE.getOreCountScaled(80);
    func_73729_b(guiLeft + 40, guiTop + 25, 176, 0, scale + 1, 8);

    scale = this.blastFurnaceTE.getCharcoalCountScaled(80);
    func_73729_b(guiLeft + 40, guiTop + 43, 176, 0, scale + 1, 8);
  }



  protected void func_146979_b(int i, int j) {
    this.field_146289_q.func_78276_b(TFC_Core.translate("gui.Bloomery.Ore"), 40, 17, 0);
    this.field_146289_q.func_78276_b(TFC_Core.translate("gui.Bloomery.Charcoal"), 40, 35, 0);

    if (TFCOptions.enableDebugMode)
    {
      this.field_146289_q.func_78276_b("Temp : " + this.blastFurnaceTE.fireTemp, 40, 71, 0);
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\GUI\GuiBlastFurnace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */