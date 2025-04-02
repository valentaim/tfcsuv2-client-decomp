package com.bioxx.tfc.GUI;

import com.bioxx.tfc.Core.TFC_Core;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;



public class GuiInventoryButton
  extends GuiButton
{
  private static ResourceLocation texture = new ResourceLocation("terrafirmacraft:textures/gui/inventory.png");

  public IIcon icon;

  public int bX;
  public int bY;
  public int bW;
  public int bH;

  public GuiInventoryButton(int index, int xPos, int yPos, int width, int height, int buttonX, int buttonY, int buttonW, int buttonH, String s, IIcon ico) {
    super(index, xPos, yPos, width, height, s);
    this.bX = buttonX;
    this.bY = buttonY;
    this.bW = buttonW;
    this.bH = buttonH;
    this.icon = ico;
  }




  public void func_146112_a(Minecraft mc, int x, int y) {
    if (this.field_146125_m) {

      TFC_Core.bindTexture(texture);
      func_73729_b(this.field_146128_h, this.field_146129_i, this.bX, this.bY, this.bW, this.bH);

      this.field_146123_n = (x >= this.field_146128_h && y >= this.field_146129_i && x < this.field_146128_h + this.field_146120_f && y < this.field_146129_i + this.field_146121_g);

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      if (this.icon != null) {

        TFC_Core.bindTexture(TextureMap.field_110575_b);
        func_94065_a(this.field_146128_h + 4, this.field_146129_i + 2, this.icon, 16, 16);
      }

      func_146119_b(mc, x, y);
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\GUI\GuiInventoryButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */