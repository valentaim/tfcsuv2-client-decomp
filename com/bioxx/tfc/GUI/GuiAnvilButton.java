package com.bioxx.tfc.GUI;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.Crafting.AnvilManager;
import com.bioxx.tfc.api.Crafting.PlanRecipe;
import com.bioxx.tfc.api.Enums.RuleEnum;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import org.lwjgl.opengl.GL11;



public class GuiAnvilButton
  extends GuiButton
{
  public IIcon icon;
  public int bX;
  public int bY;
  public int bW;
  public int bH;
  public int ruleIndex;
  private GuiAnvil screen;
  private byte red = -1;
  private byte blue = -1;
  private byte green = -1;



  public GuiAnvilButton(int index, int xPos, int yPos, int width, int height, IIcon ico, int buttonX, int buttonY, int buttonW, int buttonH, GuiAnvil gui, String s) {
    super(index, xPos, yPos, width, height, s);
    this.icon = ico;
    this.bX = buttonX;
    this.bY = buttonY;
    this.bW = buttonW;
    this.bH = buttonH;
    this.screen = gui;
  }



  public GuiAnvilButton(int index, int xPos, int yPos, int width, int height, int buttonX, int buttonY, int buttonW, int buttonH, GuiAnvil gui, int i, byte r, byte g, byte b) {
    super(index, xPos, yPos, width, height, "");
    this.bX = buttonX;
    this.bY = buttonY;
    this.bW = buttonW;
    this.bH = buttonH;
    this.screen = gui;
    this.ruleIndex = i;
    this.red = r;
    this.green = g;
    this.blue = b;
  }




  public void func_146112_a(Minecraft mc, int x, int y) {
    if (this.field_146125_m) {

      int k = func_146114_a(this.field_146123_n) - 1;
      if (this.icon == null) {

        k = 0;
        if (this.screen.anvilTE != null && this.screen.anvilTE.workRecipe != null) {

          PlanRecipe p = AnvilManager.getInstance().getPlan(this.screen.anvilTE.craftingPlan);
          if (p == null)
            return;  RuleEnum[] rules = p.rules;

          this.field_146126_j = TFC_Core.translate((rules[this.ruleIndex]).Name);
        }
      }

      TFC_Core.bindTexture(GuiAnvil.texture);
      GL11.glColor4ub(this.red, this.green, this.blue, (byte)-1);
      func_73729_b(this.field_146128_h, this.field_146129_i, this.bX + k * 16, this.bY + this.ruleIndex * 22, this.bW, this.bH);

      this.field_146123_n = (x >= this.field_146128_h && y >= this.field_146129_i && x < this.field_146128_h + this.field_146120_f && y < this.field_146129_i + this.field_146121_g);

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      if (this.icon != null) {

        TFC_Core.bindTexture(TextureMap.field_110575_b);
        func_94065_a(this.field_146128_h, this.field_146129_i, this.icon, this.field_146120_f, this.field_146121_g);
      }

      func_146119_b(mc, x, y);

      if (this.field_146123_n) {

        this.screen.drawTooltip(x, y, this.field_146126_j);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      }
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\GUI\GuiAnvilButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */