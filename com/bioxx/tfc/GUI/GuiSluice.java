package com.bioxx.tfc.GUI;

import com.bioxx.tfc.Containers.ContainerSluice;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.TileEntities.TESluice;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiSluice
  extends GuiContainerTFC
{
  public static ResourceLocation texture = new ResourceLocation("terrafirmacraft", "textures/gui/gui_sluice.png");
  
  private TESluice sluiceTE;

  
  public GuiSluice(InventoryPlayer inventoryplayer, TESluice te, World world, int x, int y, int z) {
    super((Container)new ContainerSluice(inventoryplayer, te, world, x, y, z), 176, 85);
    this.sluiceTE = te;
  }


  
  protected void func_146976_a(float f, int i, int j) {
    drawGui(texture);
  }


  
  protected void drawForeground(int guiLeft, int guiTop) {
    if (this.sluiceTE.waterInput && this.sluiceTE.waterOutput) {
      
      int l = 12;
      func_73729_b(guiLeft + 80, guiTop + 36 + 12 - 19 - l, 176, 12 - l, 14, l + 2);
    } 
    int scale = this.sluiceTE.getProcessScaled(24);
    func_73729_b(guiLeft + 76, guiTop + 34, 176, 14, scale + 1, 16);
  }


  
  protected void func_146979_b(int par1, int par2) {
    if (this.sluiceTE.soilAmount != -1) {
      this.field_146289_q.func_78276_b(TFC_Core.translate("gui.Sluice.Soil") + ": " + this.sluiceTE.soilAmount + "/50", 15, 39, 4210752);
    } else {
      this.field_146289_q.func_78276_b(TFC_Core.translate("gui.Sluice.Overworked"), 10, 39, 4210752);
    } 
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\GUI\GuiSluice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */