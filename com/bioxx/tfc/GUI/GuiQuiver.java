package com.bioxx.tfc.GUI;

import com.bioxx.tfc.Containers.ContainerQuiver;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiQuiver
  extends GuiContainerTFC
{
  public static ResourceLocation texture = new ResourceLocation("terrafirmacraft", "textures/gui/gui_quiver.png");


  public GuiQuiver(InventoryPlayer inventoryplayer, World world, int i, int j, int k) {
    super((Container)new ContainerQuiver(inventoryplayer, world, i, j, k), 176, 49);
  }



  protected void func_146976_a(float f, int i, int j) {
    drawGui(texture);
  }







  protected boolean func_146983_a(int par1) {
    if (this.field_146297_k.field_71439_g.field_71071_by.field_70461_c != par1 - 2) {

      super.func_146983_a(par1);
      return true;
    }

    return false;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\GUI\GuiQuiver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */