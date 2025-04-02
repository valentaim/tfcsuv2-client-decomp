package com.bioxx.tfc.GUI;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Textures;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.api.TFCOptions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;



public class GuiCalendar
  extends GuiScreen
{
  private World world;
  private EntityPlayer player;
  private static final ResourceLocation TEXTURE = new ResourceLocation("terrafirmacraft:textures/gui/gui_calendar.png");


  protected int xSize = 176;


  protected int ySize = 184;



  protected int guiLeft;



  protected int guiTop;



  public GuiCalendar(EntityPlayer p) {
    this.world = p.field_70170_p;
    this.guiLeft = (this.field_146294_l - this.xSize) / 2;
    this.guiTop = (this.field_146295_m - this.ySize) / 2;
    this.player = p;
  }









  public void func_73866_w_() {
    super.func_73866_w_();
    this.field_146292_n.clear();
    this.guiLeft = (this.field_146294_l - this.xSize) / 2;
    this.guiTop = (this.field_146295_m - this.ySize) / 2;
    if (TFCOptions.enableDebugMode) {

      this.field_146292_n.add(new GuiButton(0, this.guiLeft + 20, this.guiTop + 118, 66, 20, TFC_Core.translate("gui.Calendar.1Hour")));
      this.field_146292_n.add(new GuiButton(1, this.guiLeft + 20, this.guiTop + 137, 66, 20, TFC_Core.translate("gui.Calendar.1Day")));
      this.field_146292_n.add(new GuiButton(2, this.guiLeft + 85, this.guiTop + 118, 66, 20, TFC_Core.translate("gui.Calendar.1Month")));
      this.field_146292_n.add(new GuiButton(3, this.guiLeft + 85, this.guiTop + 137, 66, 20, TFC_Core.translate("gui.Calendar.1Year")));
    }

    this.field_146292_n.add(new GuiInventoryButton(4, this.guiLeft + 176, this.guiTop + 9, 25, 20, 0, 86, 25, 20,
          TFC_Core.translate("gui.Inventory.Inventory"), TFC_Textures.guiInventory));
    this.field_146292_n.add(new GuiInventoryButton(5, this.guiLeft + 176, this.guiTop + 28, 25, 20, 0, 86, 25, 20,
          TFC_Core.translate("gui.Inventory.Skills"), TFC_Textures.guiSkills));
    this.field_146292_n.add(new GuiInventoryButton(6, this.guiLeft + 176, this.guiTop + 47, 25, 20, 0, 86, 25, 20,
          TFC_Core.translate("gui.Calendar.Calendar"), TFC_Textures.guiCalendar));
    this.field_146292_n.add(new GuiInventoryButton(7, this.guiLeft + 176, this.guiTop + 66, 25, 20, 0, 86, 25, 20,
          TFC_Core.translate("gui.Inventory.Health"), TFC_Textures.guiHealth));
  }




  public void func_73863_a(int par1, int par2, float par3) {
    func_146270_b(0);

    TFC_Core.bindTexture(TEXTURE);




    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    int l = (this.field_146294_l - this.xSize) / 2;
    int i1 = (this.field_146295_m - this.ySize) / 2;
    func_73729_b(l, i1 + 6, 0, 0, this.xSize, this.ySize);

    func_73732_a(this.field_146289_q, TFC_Core.translate("gui.Calendar.Calendar"), l + 87, i1 + 16, 16777215);
    func_73732_a(this.field_146289_q, TFC_Core.translate("gui.Calendar.Season") + " : " + TFC_Time.SEASONS[TFC_Time.getSeasonAdjustedMonth((int)this.player.field_70161_v)], l + 87, i1 + 26, 0);

    int dom = TFC_Time.getDayOfMonth();
    int month = TFC_Time.currentMonth;
    String day = TFC_Time.DAYS[TFC_Time.getDayOfWeek()];

    if (month == 3 && dom == 18) {
      day = TFC_Core.translate("gui.Calendar.DateKitty");
    } else if (month == 4 && dom == 7) {
      day = TFC_Core.translate("gui.Calendar.DateBioxx");
    } else if (month == 8 && dom == 2) {
      day = TFC_Core.translate("gui.Calendar.DateDunk");
    }
    func_73732_a(this.field_146289_q, TFC_Core.translate("gui.Calendar.Day") + " : " + day, l + 87, i1 + 36, 0);

    int year = 1000 + TFC_Time.getYear();

    if (month >= 10) {
      year++;
    }
    func_73732_a(this.field_146289_q, TFC_Core.translate("gui.Calendar.Date") + " : " + dom + " " + TFC_Time.MONTHS[month] + ", " + year, l + 87, i1 + 46, 0);







    long h = TFC_Time.getHour();
    String hour = "";
    if (h == 0L) {
      hour = TFC_Core.translate("gui.Calendar.WitchHour");
    } else {
      hour = hour + h;
    }  func_73732_a(this.field_146289_q, TFC_Core.translate("gui.Calendar.Hour") + " : " + hour, l + 87, i1 + 56, 0);





    for (int var6 = 0; var6 < this.field_146292_n.size(); var6++) {

      GuiButton var7 = this.field_146292_n.get(var6);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      var7.func_146112_a(this.field_146297_k, par1, par2);
    }
  }



  public boolean func_73868_f() {
    return false;
  }



  protected void func_73869_a(char par1, int par2) {
    if (par2 == 1 || par2 == this.field_146297_k.field_71474_y.field_151445_Q.func_151463_i()) {
      this.field_146297_k.field_71439_g.func_71053_j();
    }
  }


  public void func_73732_a(FontRenderer fontrenderer, String s, int i, int j, int k) {
    fontrenderer.func_78276_b(s, i - fontrenderer.func_78256_a(s) / 2, j, k);
  }



  protected void func_146284_a(GuiButton guibutton) {
    if (this.world.field_72995_K)
      if (guibutton.field_146127_k == 0) {
        this.field_146297_k.field_71439_g.func_71165_d("/time add 1000");
      } else if (guibutton.field_146127_k == 1) {
        this.field_146297_k.field_71439_g.func_71165_d("/time add 24000");
      } else if (guibutton.field_146127_k == 2) {
        this.field_146297_k.field_71439_g.func_71165_d("/time add " + (24000 * TFC_Time.daysInMonth));
      } else if (guibutton.field_146127_k == 3) {
        this.field_146297_k.field_71439_g.func_71165_d("/time add " + (24000 * TFC_Time.daysInYear));
      } else if (guibutton.field_146127_k == 4) {
        this.field_146297_k.func_147108_a((GuiScreen)new GuiInventoryTFC((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g));
      } else if (guibutton.field_146127_k == 5) {
        Minecraft.func_71410_x().func_147108_a((GuiScreen)new GuiSkills((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g));
      } else if (guibutton.field_146127_k == 7) {
        Minecraft.func_71410_x().func_147108_a((GuiScreen)new GuiHealth((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g));
      }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\GUI\GuiCalendar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */