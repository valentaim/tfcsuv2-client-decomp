package com.bioxx.tfc.GUI;

import com.bioxx.tfc.Containers.ContainerFoodPrep;
import com.bioxx.tfc.Core.Player.PlayerInventory;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.TileEntities.TEFoodPrep;
import com.bioxx.tfc.api.TFCItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class GuiFoodPrep
  extends GuiContainerTFC
{
  private static final ResourceLocation TEXTURE = new ResourceLocation("terrafirmacraft", "textures/gui/gui_foodprep.png");

  private TEFoodPrep foodPrepTE;
  private int guiTab;

  public GuiFoodPrep(InventoryPlayer inventoryplayer, TEFoodPrep te, World world, int i, int j, int k, int tab) {
    super((Container)new ContainerFoodPrep(inventoryplayer, te, world, i, j, k, tab), 176, 85);
    this.foodPrepTE = te;
    this.guiTab = tab;
  }






  protected void func_146976_a(float f, int i, int j) {
    bindTexture(TEXTURE);
    this.field_147003_i = (this.field_146294_l - this.field_146999_f) / 2;
    this.field_147009_r = (this.field_146295_m - this.field_147000_g) / 2;
    if (this.guiTab == 0) {

      func_73729_b(this.field_147003_i, this.field_147009_r, 0, 0, this.field_146999_f, getShiftedYSize());
    }
    else if (this.guiTab == 1) {

      func_73729_b(this.field_147003_i, this.field_147009_r, 0, 86, this.field_146999_f, getShiftedYSize());
    }

    PlayerInventory.drawInventory(this, this.field_146294_l, this.field_146295_m, getShiftedYSize());
  }



  public void func_73866_w_() {
    super.func_73866_w_();
    this.field_146292_n.clear();
    if (this.guiTab == 0) {

      this.field_146292_n.add(new GuiButton(0, this.field_147003_i + 74, this.field_147009_r + 44, 50, 20, TFC_Core.translate("gui.FoodPrep.CreateMeal")));
      this.field_146292_n.add((new GuiFoodPrepTabButton(2, this.field_147003_i + 36, this.field_147009_r - 21, 31, 21, this, new ItemStack(TFCItems.salad), TFC_Core.translate("gui.FoodPrep.Salad"))).setButtonCoord(31, 172));
      this.field_146292_n.add(new GuiFoodPrepTabButton(1, this.field_147003_i + 5, this.field_147009_r - 21, 31, 21, this, new ItemStack(TFCItems.sandwich), TFC_Core.translate("gui.FoodPrep.Sandwich")));
    }
    else if (this.guiTab == 1) {

      this.field_146292_n.add(new GuiButton(0, this.field_147003_i + 74, this.field_147009_r + 44, 50, 20, TFC_Core.translate("gui.FoodPrep.CreateMeal")));
      this.field_146292_n.add(new GuiFoodPrepTabButton(2, this.field_147003_i + 36, this.field_147009_r - 21, 31, 21, this, new ItemStack(TFCItems.salad), TFC_Core.translate("gui.FoodPrep.Salad")));
      this.field_146292_n.add((new GuiFoodPrepTabButton(1, this.field_147003_i + 5, this.field_147009_r - 21, 31, 21, this, new ItemStack(TFCItems.sandwich), TFC_Core.translate("gui.FoodPrep.Sandwich"))).setButtonCoord(31, 172));
    }
  }



  protected void func_146284_a(GuiButton guibutton) {
    if (guibutton.field_146127_k == 0) {
      this.foodPrepTE.actionCreate((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g);
    } else if (guibutton.field_146127_k == 1 && this.guiTab != 0) {
      this.foodPrepTE.actionSwitchTab(0, (EntityPlayer)(Minecraft.func_71410_x()).field_71439_g);
    } else if (guibutton.field_146127_k == 2 && this.guiTab != 1) {
      this.foodPrepTE.actionSwitchTab(1, (EntityPlayer)(Minecraft.func_71410_x()).field_71439_g);
    }
  }


  public void func_73876_c() {
    super.func_73876_c();
    if (this.guiTab == 0 && this.foodPrepTE.validateSandwich()) {
      ((GuiButton)this.field_146292_n.get(0)).field_146124_l = true;
    } else if (this.guiTab == 1 && this.foodPrepTE.validateSalad()) {
      ((GuiButton)this.field_146292_n.get(0)).field_146124_l = true;
    } else if (((GuiButton)this.field_146292_n.get(0)).field_146124_l) {
      ((GuiButton)this.field_146292_n.get(0)).field_146124_l = false;
    }
  }

  public class GuiFoodPrepTabButton
    extends GuiButton
  {
    private GuiFoodPrep screen;
    private ItemStack item;
    private int xPos;
    private int yPos = 172;
    private int xSize = 31;
    private int ySize = 24;


    public GuiFoodPrepTabButton(int index, int xPos, int yPos, int width, int height, GuiFoodPrep gui, ItemStack is, String s) {
      super(index, xPos, yPos, width, height, s);
      this.screen = gui;
      this.item = is;
    }


    public GuiFoodPrepTabButton(int index, int xPos, int yPos, int width, int height, GuiFoodPrep gui, String s, int xp, int yp, int xs, int ys) {
      super(index, xPos, yPos, width, height, s);
      this.screen = gui;
      this.xPos = xp;
      this.yPos = yp;
      this.xSize = xs;
      this.ySize = ys;
    }


    public GuiFoodPrepTabButton setButtonCoord(int x, int y) {
      this.xPos = x;
      this.yPos = y;
      return this;
    }



    public void func_146112_a(Minecraft mc, int x, int y) {
      if (this.field_146125_m) {



        TFC_Core.bindTexture(GuiFoodPrep.TEXTURE);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderHelper.func_74518_a();
        GL11.glDisable(2896);
        GL11.glDisable(2929);
        this.field_73735_i = 301.0F;
        func_73729_b(this.field_146128_h, this.field_146129_i, this.xPos, this.yPos, this.xSize, this.ySize);
        this.field_146123_n = (x >= this.field_146128_h && y >= this.field_146129_i && x < this.field_146128_h + this.field_146120_f && y < this.field_146129_i + this.field_146121_g);

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPushMatrix();
        renderInventorySlot(this.item, this.field_146128_h + 8, this.field_146129_i + 5);
        GL11.glPopMatrix();
        func_146119_b(mc, x, y);

        if (this.field_146123_n) {

          this.screen.drawTooltip(x, y, this.field_146126_j);
          GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        }
      }
    }


    protected void renderInventorySlot(ItemStack par1, int par2, int par3) {
      if (par1 != null)
      {
        RenderItem.getInstance().func_82406_b((Minecraft.func_71410_x()).field_71466_p, Minecraft.func_71410_x().func_110434_K(), par1, par2, par3);
      }
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\GUI\GuiFoodPrep.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */