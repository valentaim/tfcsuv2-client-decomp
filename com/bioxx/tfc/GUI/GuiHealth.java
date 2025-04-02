package com.bioxx.tfc.GUI;

import com.bioxx.tfc.Containers.ContainerSkills;
import com.bioxx.tfc.Core.Player.FoodStatsTFC;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Textures;
import com.bioxx.tfc.api.TFCOptions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiHealth
  extends GuiContainerTFC {
  public static ResourceLocation texture = new ResourceLocation("terrafirmacraft", "textures/gui/gui_health.png");

  protected EntityPlayer player;

  public GuiHealth(EntityPlayer player) {
    super((Container)new ContainerSkills(player), 176, 104);
    setDrawInventory(false);
    this.player = player;
  }



  protected void func_146979_b(int par1, int par2) {
    this.field_146289_q.func_85187_a(TFC_Core.translate("gui.food.fruit"), 5, 13, 0, false);
    this.field_146289_q.func_85187_a(TFC_Core.translate("gui.food.vegetable"), 5, 23, 0, false);
    this.field_146289_q.func_85187_a(TFC_Core.translate("gui.food.grain"), 5, 33, 0, false);
    this.field_146289_q.func_85187_a(TFC_Core.translate("gui.food.protein"), 5, 43, 0, false);
    this.field_146289_q.func_85187_a(TFC_Core.translate("gui.food.dairy"), 5, 53, 0, false);
    if (TFCOptions.enableDebugMode) {

      FoodStatsTFC food = TFC_Core.getPlayerFoodStats(this.player);
      this.field_146289_q.func_85187_a(Float.toString(food.nutrFruit), 85, 13, 0, false);
      this.field_146289_q.func_85187_a(Float.toString(food.nutrVeg), 85, 23, 0, false);
      this.field_146289_q.func_85187_a(Float.toString(food.nutrGrain), 85, 33, 0, false);
      this.field_146289_q.func_85187_a(Float.toString(food.nutrProtein), 85, 43, 0, false);
      this.field_146289_q.func_85187_a(Float.toString(food.nutrDairy), 85, 53, 0, false);
    }
  }



  protected void func_146976_a(float f, int i, int j) {
    drawGui(texture);
  }



  protected void drawGui(ResourceLocation rl) {
    bindTexture(rl);
    this.field_147003_i = (this.field_146294_l - this.field_146999_f) / 2;
    this.field_147009_r = (this.field_146295_m - this.field_147000_g) / 2 - 34;
    func_73729_b(this.field_147003_i, this.field_147009_r, 0, 0, this.field_146999_f, this.field_147000_g);
    drawForeground(this.field_147003_i, this.field_147009_r);
  }



  protected void drawForeground(int guiLeft, int guiTop) {
    FoodStatsTFC food = TFC_Core.getPlayerFoodStats(this.player);

    func_73729_b(guiLeft + 55, guiTop + 14, 0, 106, (int)(food.nutrFruit * 24.0F), 6);
    func_73729_b(guiLeft + 55, guiTop + 24, 0, 106, (int)(food.nutrVeg * 24.0F), 6);
    func_73729_b(guiLeft + 55, guiTop + 34, 0, 106, (int)(food.nutrGrain * 24.0F), 6);
    func_73729_b(guiLeft + 55, guiTop + 44, 0, 106, (int)(food.nutrProtein * 24.0F), 6);
    func_73729_b(guiLeft + 55, guiTop + 54, 0, 106, (int)(food.nutrDairy * 24.0F), 6);
  }



  public void func_73866_w_() {
    super.func_73866_w_();
    this.field_147003_i = (this.field_146294_l - this.field_146999_f) / 2;
    this.field_147009_r = (this.field_146295_m - this.field_147000_g) / 2;
    this.field_146292_n.clear();
    this.field_146292_n.add(new GuiInventoryButton(0, this.field_147003_i + 176, this.field_147009_r - 31, 25, 20, 0, 86, 25, 20, TFC_Core.translate("gui.Inventory.Inventory"), TFC_Textures.guiInventory));
    this.field_146292_n.add(new GuiInventoryButton(1, this.field_147003_i + 176, this.field_147009_r - 12, 25, 20, 0, 86, 25, 20, TFC_Core.translate("gui.Inventory.Skills"), TFC_Textures.guiSkills));
    this.field_146292_n.add(new GuiInventoryButton(2, this.field_147003_i + 176, this.field_147009_r + 7, 25, 20, 0, 86, 25, 20, TFC_Core.translate("gui.Calendar.Calendar"), TFC_Textures.guiCalendar));
    this.field_146292_n.add(new GuiInventoryButton(3, this.field_147003_i + 176, this.field_147009_r + 26, 25, 20, 0, 86, 25, 20, TFC_Core.translate("gui.Inventory.Health"), TFC_Textures.guiHealth));
  }



  protected void func_146284_a(GuiButton guibutton) {
    if (guibutton.field_146127_k == 0) {
      Minecraft.func_71410_x().func_147108_a((GuiScreen)new GuiInventoryTFC((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g));
    } else if (guibutton.field_146127_k == 1) {
      Minecraft.func_71410_x().func_147108_a((GuiScreen)new GuiSkills((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g));
    } else if (guibutton.field_146127_k == 2) {
      Minecraft.func_71410_x().func_147108_a(new GuiCalendar((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g));
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\GUI\GuiHealth.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */