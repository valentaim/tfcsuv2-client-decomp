package com.bioxx.tfc.GUI;

import com.bioxx.tfc.Containers.ContainerMold;
import com.bioxx.tfc.Core.Player.PlayerInfo;
import com.bioxx.tfc.Core.Player.PlayerManagerTFC;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiMold
  extends GuiContainerTFC
{
  public static ResourceLocation texture = new ResourceLocation("terrafirmacraft", "textures/gui/gui_mold.png");

  private EntityPlayer player;

  public GuiMold(InventoryPlayer inventoryplayer, World world, int x, int y, int z) {
    super((Container)new ContainerMold(inventoryplayer, world, x, y, z), 176, 85);
    this.player = inventoryplayer.field_70458_d;
  }



  protected void func_146976_a(float f, int i, int j) {
    drawGui(texture);
  }



  protected void drawForeground(int guiLeft, int guiTop) {
    func_73729_b(guiLeft, guiTop, 0, 0, this.field_146999_f, getShiftedYSize());

    func_73729_b(guiLeft + 64, guiTop + 34, 176, 0, drawArrowScaled(22) + 1, 15);
  }


  private int drawArrowScaled(int scale) {
    PlayerInfo pi = PlayerManagerTFC.getInstance().getPlayerInfoFromPlayer(this.player);
    return ((pi.moldTransferTimer == 1000) ? 0 : (pi.moldTransferTimer * scale)) / 100;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\GUI\GuiMold.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */