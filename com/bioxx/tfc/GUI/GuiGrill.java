package com.bioxx.tfc.GUI;

import com.bioxx.tfc.Containers.ContainerGrill;
import com.bioxx.tfc.TileEntities.TEGrill;
import com.bioxx.tfc.api.TileEntities.TEFireEntity;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiGrill
  extends GuiContainerTFC
{
  public static ResourceLocation texture = new ResourceLocation("terrafirmacraft", "textures/gui/gui_grill.png");


  private TEFireEntity fireTE;


  public GuiGrill(InventoryPlayer inventoryplayer, TEGrill te, World world, int x, int y, int z) {
    super((Container)new ContainerGrill(inventoryplayer, te, world, x, y, z), 176, 85);

    if (world.func_147438_o(x, y - 1, z) instanceof TEFireEntity) {
      this.fireTE = (TEFireEntity)world.func_147438_o(x, y - 1, z);
    }
  }


  protected void func_146976_a(float f, int i, int j) {
    drawGui(texture);
  }



  protected void drawForeground(int guiLeft, int guiTop) {
    int scale = 0;
    if (this.fireTE != null) {
      scale = this.fireTE.getTemperatureScaled(49);
    }
    func_73729_b(guiLeft + 7, guiTop + 65 - scale, 0, 86, 15, 6);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\GUI\GuiGrill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */