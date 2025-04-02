package com.bioxx.tfc.GUI;

import com.bioxx.tfc.Core.Player.PlayerInventory;
import com.bioxx.tfc.Core.TFC_Core;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;





public class GuiContainerTFC
  extends GuiContainer
{
  protected boolean drawInventory = true;
  protected Slot activeSlot;

  public GuiContainerTFC(Container container, int xsize, int ysize) {
    super(container);
    this.field_146999_f = xsize;
    this.field_147000_g = ysize + PlayerInventory.invYSize;
  }


  protected void setDrawInventory(boolean b) {
    if (!this.drawInventory && b) {
      this.field_147000_g += PlayerInventory.invYSize;
    } else if (this.drawInventory && !b) {
      this.field_147000_g -= PlayerInventory.invYSize;
    }  this.drawInventory = b;
  }



  public void func_73863_a(int par1, int par2, float par3) {
    try {
      super.func_73863_a(par1, par2, par3);
      for (int j1 = 0; j1 < this.field_147002_h.field_75151_b.size(); j1++) {

        Slot slot = this.field_147002_h.field_75151_b.get(j1);
        if (func_146981_a(slot, par1, par2) && slot.func_111238_b())
          this.activeSlot = slot;
      }
    } catch (NullPointerException e) {
      this.field_146297_k.field_71439_g.func_71053_j();
    }
  }


  protected boolean func_146981_a(Slot par1Slot, int par2, int par3) {
    return func_146978_c(par1Slot.field_75223_e, par1Slot.field_75221_f, 16, 16, par2, par3);
  }



  protected void func_146976_a(float f, int i, int j) {
    drawGui((ResourceLocation)null);
  }


  protected void drawGui(ResourceLocation rl) {
    if (rl != null) {

      bindTexture(rl);
      this.field_147003_i = (this.field_146294_l - this.field_146999_f) / 2;
      this.field_147009_r = (this.field_146295_m - this.field_147000_g) / 2;
      int height = this.drawInventory ? getShiftedYSize() : this.field_147000_g;

      func_73729_b(this.field_147003_i, this.field_147009_r, 0, 0, this.field_146999_f, height);

      drawForeground(this.field_147003_i, this.field_147009_r);
    }
    if (this.drawInventory) {
      PlayerInventory.drawInventory(this, this.field_146294_l, this.field_146295_m, getShiftedYSize());
    }
  }





  protected void drawForeground(int guiLeft, int guiTop) {}




  protected boolean mouseInRegion(int x, int y, int width, int height, int mouseX, int mouseY) {
    mouseX -= this.field_147003_i;
    mouseY -= this.field_147009_r;
    return (mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height);
  }


  protected void bindTexture(ResourceLocation rl) {
    TFC_Core.bindTexture(rl);
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
  }


  public void drawTooltip(int mx, int my, String text) {
    List<String> list = new ArrayList<>();
    list.add(text);
    drawHoveringText(list, mx, my + 15, this.field_146289_q);
    RenderHelper.func_74518_a();
    GL11.glDisable(2896);
  }



  protected void drawHoveringTextZLevel(List<String> par1List, int par2, int par3, FontRenderer font, float z) {
    if (!par1List.isEmpty()) {

      GL11.glDisable(32826);
      RenderHelper.func_74518_a();
      GL11.glDisable(2896);

      int k = 0;
      Iterator<String> iterator = par1List.iterator();

      while (iterator.hasNext()) {

        String s = iterator.next();
        int l = font.func_78256_a(s);
        if (l > k) {
          k = l;
        }
      }
      int i1 = par2 + 12;
      int j1 = par3 - 12;
      int k1 = 8;

      if (par1List.size() > 1) {
        k1 += 2 + (par1List.size() - 1) * 10;
      }
      if (i1 + k > this.field_146294_l) {
        i1 -= 28 + k;
      }
      if (j1 + k1 + 6 > this.field_146295_m) {
        j1 = this.field_146295_m - k1 - 6;
      }
      this.field_73735_i = z;
      field_146296_j.field_77023_b = 300.0F;
      int l1 = -267386864;
      func_73733_a(i1 - 3, j1 - 4, i1 + k + 3, j1 - 3, l1, l1);
      func_73733_a(i1 - 3, j1 + k1 + 3, i1 + k + 3, j1 + k1 + 4, l1, l1);
      func_73733_a(i1 - 3, j1 - 3, i1 + k + 3, j1 + k1 + 3, l1, l1);
      func_73733_a(i1 - 4, j1 - 3, i1 - 3, j1 + k1 + 3, l1, l1);
      func_73733_a(i1 + k + 3, j1 - 3, i1 + k + 4, j1 + k1 + 3, l1, l1);
      int i2 = 1347420415;
      int j2 = (i2 & 0xFEFEFE) >> 1 | i2 & 0xFF000000;
      func_73733_a(i1 - 3, j1 - 3 + 1, i1 - 3 + 1, j1 + k1 + 3 - 1, i2, j2);
      func_73733_a(i1 + k + 2, j1 - 3 + 1, i1 + k + 3, j1 + k1 + 3 - 1, i2, j2);
      func_73733_a(i1 - 3, j1 - 3, i1 + k + 3, j1 - 3 + 1, i2, i2);
      func_73733_a(i1 - 3, j1 + k1 + 2, i1 + k + 3, j1 + k1 + 3, j2, j2);

      for (int k2 = 0; k2 < par1List.size(); k2++) {

        String s1 = par1List.get(k2);
        font.func_78261_a(s1, i1, j1, -1);

        if (k2 == 0)
          j1 += 2;
        j1 += 10;
      }

      this.field_73735_i = 0.0F;
      field_146296_j.field_77023_b = 0.0F;
      GL11.glEnable(2896);
      RenderHelper.func_74519_b();
      GL11.glEnable(32826);
    }
  }


  protected int getShiftedYSize() {
    return this.field_147000_g - PlayerInventory.invYSize;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\GUI\GuiContainerTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */