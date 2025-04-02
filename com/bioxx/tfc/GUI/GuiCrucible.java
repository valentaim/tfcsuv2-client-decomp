package com.bioxx.tfc.GUI;

import com.bioxx.tfc.Containers.ContainerCrucible;
import com.bioxx.tfc.Core.Metal.AlloyMetal;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.TileEntities.TECrucible;
import com.bioxx.tfc.api.TFCOptions;
import java.util.Arrays;
import java.util.List;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiCrucible
  extends GuiContainerTFC
{
  public static ResourceLocation texture = new ResourceLocation("terrafirmacraft", "textures/gui/gui_crucible.png");

  private TECrucible crucibleTE;


  public GuiCrucible(InventoryPlayer inventoryplayer, TECrucible te, World world, int x, int y, int z) {
    super((Container)new ContainerCrucible(inventoryplayer, te, world, x, y, z), 176, 113);
    this.crucibleTE = te;
  }



  protected void func_146976_a(float f, int i, int j) {
    drawGui(texture);
  }



  protected void drawForeground(int guiLeft, int guiTop) {
    int scale = 0;

    scale = this.crucibleTE.getTemperatureScaled(49);
    func_73729_b(guiLeft + 153, guiTop + 80 - scale, 185, 0, 15, 6);

    scale = this.crucibleTE.getOutCountScaled(100);
    func_73729_b(guiLeft + 129, guiTop + 106 - scale, 177, 6, 8, scale);
  }



  protected void func_146979_b(int i, int j) {
    if (TFCOptions.enableDebugMode)
    {
      this.field_146289_q.func_78276_b("Temp: " + this.crucibleTE.temperature, 178, 8, 16777215);
    }

    if (this.crucibleTE.currentAlloy != null) {

      if (this.crucibleTE.currentAlloy.outputAmount == 0.0F) {

        this.field_146289_q.func_78276_b(EnumChatFormatting.UNDERLINE + TFC_Core.translate("gui.empty"), 7, 7, 0);
        return;
      }
      if (this.crucibleTE.currentAlloy.outputType != null) {

        this.field_146289_q.func_78276_b(EnumChatFormatting.UNDERLINE + TFC_Core.translate("gui.metal." + this.crucibleTE.currentAlloy.outputType.name.replace(" ", "")), 7, 7, 0);
      }
      else {

        this.field_146289_q.func_78276_b(EnumChatFormatting.UNDERLINE + TFC_Core.translate("gui.metal.Unknown"), 7, 7, 0);
      }

      for (int c = 0; c < this.crucibleTE.currentAlloy.alloyIngred.size(); c++) {

        double m = ((AlloyMetal)this.crucibleTE.currentAlloy.alloyIngred.get(c)).metal;
        m = Math.round(m * 100.0D) / 100.0D;
        if (((AlloyMetal)this.crucibleTE.currentAlloy.alloyIngred.get(c)).metalType != null)
        {
          this.field_146289_q.func_78276_b(EnumChatFormatting.DARK_GRAY + TFC_Core.translate("gui.metal." + ((AlloyMetal)this.crucibleTE.currentAlloy.alloyIngred.get(c)).metalType.name.replace(" ", "")) + ": " + EnumChatFormatting.DARK_GREEN + m + "%", 7, 18 + 10 * c, 0);
        }
      }
    }
  }



  public void func_73863_a(int par1, int par2, float par3) {
    super.func_73863_a(par1, par2, par3);
    if (this.crucibleTE.currentAlloy != null) {

      int w = (this.field_146294_l - this.field_146999_f) / 2;
      int h = (this.field_146295_m - this.field_147000_g) / 2;
      if (par1 >= 129 + w && par2 >= 6 + h && par1 <= 137 + w && par2 <= 106 + h) {


        String[] text = { String.format("%2.0f", new Object[] { Float.valueOf(this.crucibleTE.currentAlloy.outputAmount) }) };
        List<String> temp = Arrays.asList(text);
        drawHoveringText(temp, par1, par2, this.field_146289_q);
      }
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\GUI\GuiCrucible.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */