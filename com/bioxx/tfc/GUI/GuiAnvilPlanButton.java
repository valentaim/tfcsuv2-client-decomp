package com.bioxx.tfc.GUI;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.TFCItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;


public class GuiAnvilPlanButton
  extends GuiButton
{
  private GuiAnvil screen;
  protected static final RenderItem ITEM_RENDERER = new RenderItem();


  public GuiAnvilPlanButton(int index, int xPos, int yPos, int width, int height, GuiAnvil gui, String s) {
    super(index, xPos, yPos, width, height, s);
    this.screen = gui;
  }



  public void func_146112_a(Minecraft mc, int x, int y) {
    if (this.field_146125_m) {

      int k = func_146114_a(this.field_146123_n) - 1;

      TFC_Core.bindTexture(GuiAnvil.texture);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.field_73735_i = 300.0F;
      func_73729_b(this.field_146128_h, this.field_146129_i, 0, 205 + k * 18, 18, 18);
      this.field_146123_n = (x >= this.field_146128_h && y >= this.field_146129_i && x < this.field_146128_h + this.field_146120_f && y < this.field_146129_i + this.field_146121_g);

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);


      if (this.screen.anvilTE != null && !this.screen.anvilTE.craftingPlan.equals("") && this.screen.anvilTE.workRecipe != null) {
        renderInventorySlot(this.screen.anvilTE.workRecipe.getCraftingResult(), this.field_146128_h + 1, this.field_146129_i + 1);
      } else {
        renderInventorySlot(new ItemStack(TFCItems.blueprint), this.field_146128_h + 1, this.field_146129_i + 1);
      }
      this.field_73735_i = 0.0F;
      func_146119_b(mc, x, y);

      if (this.field_146123_n) {

        this.screen.drawTooltip(x, y, this.field_146126_j);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      }
    }
  }


  protected void renderInventorySlot(ItemStack is, int x, int y) {
    if (is != null)
    {
      ITEM_RENDERER.func_82406_b((Minecraft.func_71410_x()).field_71466_p, Minecraft.func_71410_x().func_110434_K(), is, x, y);
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\GUI\GuiAnvilPlanButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */