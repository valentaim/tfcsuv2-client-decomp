package com.bioxx.tfc.GUI;

import com.bioxx.tfc.Containers.ContainerFirepit;
import com.bioxx.tfc.TileEntities.TEFirepit;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiFirepit
  extends GuiContainerTFC
{
  public static ResourceLocation texture = new ResourceLocation("terrafirmacraft", "textures/gui/gui_firepit.png");
  
  private TEFirepit firepitTE;

  
  public GuiFirepit(InventoryPlayer inventoryplayer, TEFirepit te, World world, int x, int y, int z) {
    super((Container)new ContainerFirepit(inventoryplayer, te, world, x, y, z), 176, 85);
    this.firepitTE = te;
  }


  
  protected void func_146976_a(float f, int i, int j) {
    drawGui(texture);
  }


  
  protected void drawForeground(int guiLeft, int guiTop) {
    if (this.firepitTE != null) {
      
      int scale = this.firepitTE.getTemperatureScaled(49);
      func_73729_b(guiLeft + 30, guiTop + 65 - scale, 185, 31, 15, 6);
    } 
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\GUI\GuiFirepit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */