package com.bioxx.tfc.Handlers.Client;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.Food;
import com.bioxx.tfc.api.FoodRegistry;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;





public class FMLClientEventHandler
{
  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void renderTick(TickEvent.RenderTickEvent event) {
    Minecraft mc = FMLClientHandler.instance().getClient();

    if (event.phase != TickEvent.Phase.START) {

      GuiScreen gui = mc.field_71462_r;
      if (gui instanceof GuiContainer && !GuiScreen.func_146272_n() && !Mouse.isGrabbed())
      {
        if (mc.field_71439_g.field_71071_by.func_70445_o() == null) {
          renderMealIngredInGui((GuiContainer)gui, (EntityPlayer)mc.field_71439_g);
        }
      }
    }
  }

  public void renderMealIngredInGui(GuiContainer gui, EntityPlayer player) {
    Minecraft mc = FMLClientHandler.instance().getClient();
    ScaledResolution var13 = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
    int scaledWidth = var13.func_78326_a();
    int scaledHeight = var13.func_78328_b();
    int mouseX = Mouse.getX() * scaledWidth / mc.field_71443_c;
    int mouseY = scaledHeight - Mouse.getY() * scaledHeight / mc.field_71440_d - 1;

    GL11.glPushMatrix();
    GL11.glPushAttrib(1048575);





    int shiftx = -8;
    int shifty = 0;

    Slot slot = gui.func_146975_c(mouseX, mouseY);
    if (slot != null && slot.func_111238_b()) {

      if (slot.func_75216_d() && slot.func_75211_c().func_77973_b() instanceof com.bioxx.tfc.Food.ItemMeal && slot.func_75211_c().func_77942_o()) {

        int[] fg = Food.getFoodGroups(slot.func_75211_c());
        TFC_Core.bindTexture(TextureMap.field_110576_c);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        for (int i = 0; i < fg.length; i++) {

          Item food = FoodRegistry.getInstance().getFood(fg[i]);
          if (food != null) {

            int x = mouseX + 19;
            int y = mouseY + 11;
            GL11.glDisable(2929);
            GL11.glPushMatrix();
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            GL11.glTranslated((x + shiftx - 2), (y + shifty), 0.0D);
            GL11.glScaled(1.0D, 1.0D, 0.0D);
            drawQuad(0, 0, 8, 8, 200.0D, food.func_77617_a(0));
            GL11.glDisable(3042);
            GL11.glPopMatrix();

            shiftx += 8;
          }
        }
      }  GL11.glEnable(2929);
    }

    GL11.glPopAttrib();

    GL11.glPopMatrix();
  }


  public static void drawQuad(int x, int y, int xSize, int ySize, double zLevel, IIcon ico) {
    Tessellator var9 = Tessellator.field_78398_a;
    var9.func_78382_b();
    var9.func_78374_a((x + 0), (y + ySize), zLevel, ico.func_94209_e(), ico.func_94210_h());
    var9.func_78374_a((x + xSize), (y + ySize), zLevel, ico.func_94212_f(), ico.func_94210_h());
    var9.func_78374_a((x + xSize), (y + 0), zLevel, ico.func_94212_f(), ico.func_94206_g());
    var9.func_78374_a((x + 0), (y + 0), zLevel, ico.func_94209_e(), ico.func_94206_g());
    var9.func_78381_a();
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\Client\FMLClientEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */