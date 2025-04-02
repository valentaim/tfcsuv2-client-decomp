package com.bioxx.tfc.GUI;

import com.bioxx.tfc.Containers.ContainerNestBox;
import com.bioxx.tfc.TileEntities.TENestBox;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiNestBox
  extends GuiContainerTFC
{
  public static ResourceLocation texture = new ResourceLocation("terrafirmacraft", "textures/gui/gui_nestbox.png");


  public GuiNestBox(InventoryPlayer inventoryplayer, TENestBox te, World world, int i, int j, int k) {
    super((Container)new ContainerNestBox(inventoryplayer, te, world, i, j, k), 176, 85);
  }



  protected void func_146976_a(float f, int i, int j) {
    drawGui(texture);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\GUI\GuiNestBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */