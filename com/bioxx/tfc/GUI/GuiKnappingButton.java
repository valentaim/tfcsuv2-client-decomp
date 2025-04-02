package com.bioxx.tfc.GUI;

import com.bioxx.tfc.Core.Player.PlayerInfo;
import com.bioxx.tfc.Core.Player.PlayerManagerTFC;
import com.bioxx.tfc.Core.TFC_Core;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import org.lwjgl.opengl.GL11;



public class GuiKnappingButton
  extends GuiButton
{
  public GuiKnappingButton(int index, int xPos, int yPos, int width, int height) {
    super(index, xPos, yPos, width, height, "");
  }



  public void func_146112_a(Minecraft par1Minecraft, int xPos, int yPos) {
    if (this.field_146125_m) {

      PlayerInfo pi = PlayerManagerTFC.getInstance().getClientPlayer();
      IIcon icon = null;

      if (pi.specialCraftingType != null)
        icon = pi.specialCraftingType.func_77954_c();
      if (!this.field_146124_l && pi.specialCraftingTypeAlternate != null) {
        icon = pi.specialCraftingTypeAlternate.func_77954_c();
      }
      TFC_Core.bindTexture(TextureMap.field_110576_c);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.field_146123_n = (xPos >= this.field_146128_h && yPos >= this.field_146129_i && xPos < this.field_146128_h + this.field_146120_f && yPos < this.field_146129_i + this.field_146121_g);

      if (icon != null) {
        func_94065_a(this.field_146128_h, this.field_146129_i, icon, this.field_146120_f, this.field_146121_g);
      }
      func_146119_b(par1Minecraft, xPos, yPos);
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\GUI\GuiKnappingButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */