package com.bioxx.tfc.Render.Item;

import com.bioxx.tfc.Food.ItemFoodTFC;
import com.bioxx.tfc.api.Food;
import com.bioxx.tfc.api.Interfaces.IFood;
import com.bioxx.tfc.api.TFC_ItemHeat;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;





public class FoodItemRenderer
  implements IItemRenderer
{
  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
    return (type == IItemRenderer.ItemRenderType.INVENTORY);
  }


  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
    return false;
  }



  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack is, Object... data) {
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    GL11.glPushAttrib(8192);
    GL11.glEnable(2929);
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    if (is.func_77973_b() instanceof IFood && is.func_77942_o()) {

      renderIcon(0, 0, is.func_77973_b().func_77650_f(is), 16, 16);
      float decayPerc = Math.max(Food.getDecay(is) / Food.getWeight(is), 0.0F);
      float cookPerc = Math.max(Math.min(Food.getCooked(is) / 600.0F, 1.0F), 0.0F);
      if (is.func_77973_b() instanceof ItemFoodTFC) {

        int color = Food.getCookedColorMultiplier(is);
        GL11.glColor4f(((color & 0xFF0000) >> 16) / 255.0F, ((color & 0xFF00) >> 8) / 255.0F, (color & 0xFF) / 255.0F, cookPerc);
        if (((ItemFoodTFC)is.func_77973_b()).cookedIcon != null) {
          renderIcon(0, 0, ((ItemFoodTFC)is.func_77973_b()).cookedIcon, 16, 16);
        } else {
          renderIcon(0, 0, is.func_77973_b().func_77650_f(is), 16, 16);
        }
      }  GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      float decayTop = decayPerc * 13.0F;

      if (type == IItemRenderer.ItemRenderType.INVENTORY) {

        if (TFC_ItemHeat.hasTemp(is)) {

          float meltTemp = TFC_ItemHeat.isCookable(is);
          float temp = TFC_ItemHeat.getTemp(is);
          if (temp > 0.0F && temp < meltTemp) {

            renderQuad(1.0D, 1.0D, 13.0D, 1.0D, 0);

            float tempValue = 13.0F / meltTemp * temp;
            if (tempValue < 0.0F) tempValue = 0.0F;
            if (tempValue > 13.0F) tempValue = 13.0F;

            if (temp < meltTemp * 0.1F) {
              renderQuad(1.0D, 1.0D, tempValue, 1.0D, 16777215);
            } else if (temp < meltTemp * 0.4F) {
              renderQuad(1.0D, 1.0D, tempValue, 1.0D, 16744448);
            } else if (temp < meltTemp * 0.8F) {
              renderQuad(1.0D, 1.0D, tempValue, 1.0D, 16736256);
            } else {
              renderQuad(1.0D, 1.0D, tempValue, 1.0D, 16711680);
            }
          }
        }
        float weightPerc = Food.getWeight(is) / ((IFood)is.func_77973_b()).getFoodMaxWeight(is);

        if (weightPerc <= 1.0F)
        {
          if (((IFood)is.func_77973_b()).renderDecay())
          {
            if (decayPerc < 0.1D) {

              decayTop *= 10.0F;
              renderQuad(1.0D, 13.0D, (13.0F - decayTop), 1.0D, 65280);
            } else {

              renderQuad(1.0D, 13.0D, (13.0F - decayTop), 1.0D, 16711680);
            }  }
          if (((IFood)is.func_77973_b()).renderWeight())
          {
            renderQuad(1.0D, 14.0D, 13.0D, 1.0D, 0);
            float weightTop = weightPerc * 13.0F;

            renderQuad(1.0D, 14.0D, weightTop, 1.0D, 16777215);
          }

        }

      }
    } else if (is.func_77973_b() instanceof IFood) {

      renderIcon(0, 0, is.func_77973_b().func_77650_f(is), 16, 16);
    }

    GL11.glPopAttrib();
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
  }


  public static void renderIcon(int x, int y, IIcon icon, int sizeX, int sizeY) {
    Tessellator tessellator = Tessellator.field_78398_a;
    tessellator.func_78382_b();
    tessellator.func_78374_a((x + 0), (y + sizeY), 0.0D, icon.func_94209_e(), icon.func_94210_h());
    tessellator.func_78374_a((x + sizeX), (y + sizeY), 0.0D, icon.func_94212_f(), icon.func_94210_h());
    tessellator.func_78374_a((x + sizeX), (y + 0), 0.0D, icon.func_94212_f(), icon.func_94206_g());
    tessellator.func_78374_a((x + 0), (y + 0), 0.0D, icon.func_94209_e(), icon.func_94206_g());
    tessellator.func_78381_a();
  }


  private static void renderQuad(double x, double y, double sizeX, double sizeY, int color) {
    GL11.glDisable(3553);
    Tessellator tess = Tessellator.field_78398_a;
    tess.func_78382_b();
    tess.func_78378_d(color);
    tess.func_78377_a(x + 0.0D, y + 0.0D, 0.0D);
    tess.func_78377_a(x + 0.0D, y + sizeY, 0.0D);
    tess.func_78377_a(x + sizeX, y + sizeY, 0.0D);
    tess.func_78377_a(x + sizeX, y + 0.0D, 0.0D);
    tess.func_78381_a();
    GL11.glEnable(3553);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Item\FoodItemRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */