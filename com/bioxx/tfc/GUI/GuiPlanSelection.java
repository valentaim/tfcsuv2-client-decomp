package com.bioxx.tfc.GUI;

import com.bioxx.tfc.Containers.ContainerPlanSelection;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.TileEntities.TEAnvil;
import com.bioxx.tfc.api.Crafting.AnvilManager;
import com.bioxx.tfc.api.Crafting.AnvilRecipe;
import com.bioxx.tfc.api.Crafting.AnvilReq;
import com.bioxx.tfc.api.TFCItems;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;






public class GuiPlanSelection
  extends GuiContainerTFC
{
  private TEAnvil anvilTE;
  private List<Object[]> plans;
  public static ResourceLocation texture = new ResourceLocation("terrafirmacraft", "textures/gui/gui_plan.png");


  public GuiPlanSelection(EntityPlayer p, TEAnvil te, World w, int x, int y, int z) {
    super((Container)new ContainerPlanSelection(p, te, w, x, y, z), 176, 130);
    this.anvilTE = te;


    this.drawInventory = false;
  }






  public void func_73866_w_() {
    super.func_73866_w_();

    this.field_146292_n.clear();
    this.plans = getRecipes();
    this.field_147003_i = (this.field_146294_l - this.field_146999_f) / 2;
    this.field_147009_r = (this.field_146295_m - this.field_147000_g) / 2;
    int xOffset = 5;
    int yOffset = 14;
    int index = this.plans.size() - 1;
    for (Object[] o : this.plans) {

      String p = (String)o[0];
      AnvilRecipe a = (AnvilRecipe)o[1];
      this.field_146292_n.add(0, new GuiPlanButton(this.plans.size() - 1 - index, this.field_147003_i + xOffset, this.field_147009_r + yOffset, 16, 16, a.getCraftingResult(), this, TFC_Core.translate("gui.plans." + p)));
      index--;
      if (xOffset + 36 < this.field_146999_f) {
        xOffset += 18;
        continue;
      }
      xOffset = 5;
      yOffset += 18;
    }
  }




  protected void func_146284_a(GuiButton guibutton) {
    Object[] p = (Object[])this.plans.toArray()[guibutton.field_146127_k];
    this.anvilTE.setPlan((String)p[0]);
  }



  public void func_73732_a(FontRenderer fontrenderer, String s, int i, int j, int k) {
    fontrenderer.func_78276_b(s, i - fontrenderer.func_78256_a(s) / 2, j, k);
  }



  protected void func_146976_a(float f, int i, int j) {
    drawGui(texture);
    if (this.anvilTE.func_70301_a(1) != null) {
      func_73732_a(this.field_146289_q, "Plans: " + this.anvilTE.func_70301_a(1).func_82833_r(), this.field_147003_i + this.field_146999_f / 2, this.field_147009_r + 5, 0);
    }
  }

  private List<Object[]> getRecipes() {
    AnvilManager manager = AnvilManager.getInstance();
    Object[] plans = manager.getPlans().keySet().toArray();
    ArrayList<Object[]> planList = new ArrayList();
    for (Object p : plans) {

      AnvilRecipe ar = manager.findMatchingRecipe(new AnvilRecipe(this.anvilTE.anvilItemStacks[1], this.anvilTE.anvilItemStacks[5], (String)p, AnvilReq.getReqFromInt(this.anvilTE.anvilTier), null));

      ar = handleMatchingRecipe(ar);
      if (ar != null) {
        planList.add(new Object[] { p, ar });
      }
    }
    return planList;
  }



  public AnvilRecipe handleMatchingRecipe(AnvilRecipe ar) {
    if (ar != null &&
      this.anvilTE.anvilItemStacks[1] != null && this.anvilTE.anvilItemStacks[1].func_77973_b() == TFCItems.bloom && ar.getCraftingResult().func_77973_b() == TFCItems.bloom)
    {
      if (this.anvilTE.anvilItemStacks[1].func_77960_j() <= 100)
        return null;
    }
    return ar;
  }



  public void drawTooltip(int mx, int my, String text) {
    List<String> list = new ArrayList<>();
    list.add(text);
    drawHoveringTextZLevel(list, mx, my + 15, this.field_146289_q, 400.0F);
    RenderHelper.func_74518_a();
    GL11.glDisable(2896);
    GL11.glDisable(2929);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\GUI\GuiPlanSelection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */