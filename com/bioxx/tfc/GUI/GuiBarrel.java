package com.bioxx.tfc.GUI;

import com.bioxx.tfc.Containers.ContainerBarrel;
import com.bioxx.tfc.Core.Player.PlayerInventory;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Textures;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.TileEntities.TEBarrel;
import com.bioxx.tfc.api.Crafting.BarrelManager;
import com.bioxx.tfc.api.Crafting.BarrelPreservativeRecipe;
import com.bioxx.tfc.api.Enums.EnumFoodGroup;
import com.bioxx.tfc.api.Food;
import com.bioxx.tfc.api.Interfaces.IFood;
import com.bioxx.tfc.api.TFCFluids;
import com.bioxx.tfc.api.TFCItems;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;





public class GuiBarrel
  extends GuiContainerTFC
{
  public static final ResourceLocation TEXTURE = new ResourceLocation("terrafirmacraft", "textures/gui/gui_barrel.png");

  protected TEBarrel barrelTE;
  protected EntityPlayer player;
  protected int guiTab;

  public GuiBarrel(InventoryPlayer inventoryplayer, TEBarrel te, World world, int x, int y, int z, int tab) {
    super((Container)new ContainerBarrel(inventoryplayer, te, world, x, y, z, tab), 176, 85);
    this.barrelTE = te;
    this.player = inventoryplayer.field_70458_d;
    this.field_147003_i = (this.field_146294_l - 208) / 2;
    this.field_147009_r = (this.field_146295_m - 198) / 2;
    this.guiTab = tab;
  }



  public void func_73876_c() {
    super.func_73876_c();
    if (this.barrelTE.getInvCount() > 0) {

      if (this.guiTab == 0) {
        ((GuiButton)this.field_146292_n.get(4)).field_146125_m = false;
      } else if (this.guiTab == 1) {
        ((GuiButton)this.field_146292_n.get(1)).field_146125_m = false;
      }

    }
    else if (this.guiTab == 0) {
      ((GuiButton)this.field_146292_n.get(4)).field_146125_m = true;
    } else if (this.guiTab == 1) {
      ((GuiButton)this.field_146292_n.get(1)).field_146125_m = true;
    }
    if (this.barrelTE.getFluidLevel() > 0) {

      if (this.guiTab == 0) {
        ((GuiButton)this.field_146292_n.get(3)).field_146125_m = false;
      } else if (this.guiTab == 1) {
        ((GuiButton)this.field_146292_n.get(0)).field_146125_m = false;
      }

    }
    else if (this.guiTab == 0) {
      ((GuiButton)this.field_146292_n.get(3)).field_146125_m = true;
    } else if (this.guiTab == 1) {
      ((GuiButton)this.field_146292_n.get(0)).field_146125_m = true;
    }

    if (this.barrelTE.getSealed() && this.guiTab == 0) {

      ((GuiButton)this.field_146292_n.get(0)).field_146126_j = TFC_Core.translate("gui.Barrel.Unseal");
      ((GuiButton)this.field_146292_n.get(1)).field_146124_l = false;
      ((GuiButton)this.field_146292_n.get(2)).field_146124_l = false;
    }
    else if (!this.barrelTE.getSealed() && this.guiTab == 0) {

      ((GuiButton)this.field_146292_n.get(0)).field_146126_j = TFC_Core.translate("gui.Barrel.Seal");
      ((GuiButton)this.field_146292_n.get(1)).field_146124_l = true;
      ((GuiButton)this.field_146292_n.get(2)).field_146124_l = true;
    }
  }



  public void func_73866_w_() {
    super.func_73866_w_();
    createButtons();
  }


  public void createButtons() {
    this.field_146292_n.clear();
    if (this.guiTab == 0) {

      if (!this.barrelTE.getSealed()) {
        this.field_146292_n.add(new GuiButton(0, this.field_147003_i + 38, this.field_147009_r + 50, 50, 20, TFC_Core.translate("gui.Barrel.Seal")));
      } else {
        this.field_146292_n.add(new GuiButton(0, this.field_147003_i + 38, this.field_147009_r + 50, 50, 20, TFC_Core.translate("gui.Barrel.Unseal")));
      }  this.field_146292_n.add(new GuiButton(1, this.field_147003_i + 88, this.field_147009_r + 50, 50, 20, TFC_Core.translate("gui.Barrel.Empty")));
      if (this.barrelTE.mode == 0) {
        this.field_146292_n.add(new GuiBarrelTabButton(2, this.field_147003_i + 39, this.field_147009_r + 29, 16, 16, this, TFC_Core.translate("gui.Barrel.ToggleOn"), 0, 204, 16, 16));
      } else if (this.barrelTE.mode == 1) {
        this.field_146292_n.add(new GuiBarrelTabButton(2, this.field_147003_i + 39, this.field_147009_r + 29, 16, 16, this, TFC_Core.translate("gui.Barrel.ToggleOff"), 0, 188, 16, 16));
      }  this.field_146292_n.add(new GuiBarrelTabButton(3, this.field_147003_i + 36, this.field_147009_r - 12, 31, 15, this, TFC_Textures.guiSolidStorage, TFC_Core.translate("gui.Barrel.Solid")));
      this.field_146292_n.add(new GuiBarrelTabButton(4, this.field_147003_i + 5, this.field_147009_r - 12, 31, 15, this, TFC_Textures.guiLiquidStorage, TFC_Core.translate("gui.Barrel.Liquid")));

    }
    else if (this.guiTab == 1) {

      this.field_146292_n.add(new GuiBarrelTabButton(0, this.field_147003_i + 36, this.field_147009_r - 12, 31, 15, this, TFC_Textures.guiSolidStorage, TFC_Core.translate("gui.Barrel.Solid")));
      this.field_146292_n.add(new GuiBarrelTabButton(1, this.field_147003_i + 5, this.field_147009_r - 12, 31, 15, this, TFC_Textures.guiLiquidStorage, TFC_Core.translate("gui.Barrel.Liquid")));

      if (!this.barrelTE.getSealed()) {
        this.field_146292_n.add(new GuiButton(2, this.field_147003_i + 6, this.field_147009_r + 33, 44, 20, TFC_Core.translate("gui.Barrel.Seal")));
      } else {
        this.field_146292_n.add(new GuiButton(2, this.field_147003_i + 6, this.field_147009_r + 33, 44, 20, TFC_Core.translate("gui.Barrel.Unseal")));
      }
    }
  }


  public void drawTooltip(int mx, int my, String text) {
    List<String> list = new ArrayList<>();
    list.add(text);
    drawHoveringText(list, mx, my + 15, this.field_146289_q);
    RenderHelper.func_74518_a();
    GL11.glDisable(2896);
  }

  public class GuiButtonMode
    extends GuiButton
  {
    public GuiButtonMode(int par1, int par2, int par3, String par4Str) {
      super(par1, par2, par3, 200, 20, par4Str);
    }


    public GuiButtonMode(int par1, int par2, int par3, int par4, int par5, String par6Str) {
      super(par1, par2, par3, par4, par5, par6Str);
    }



    public void func_146112_a(Minecraft par1Minecraft, int xPos, int yPos) {
      if (this.field_146125_m) {

        FontRenderer fontrenderer = par1Minecraft.field_71466_p;
        par1Minecraft.func_110434_K().func_110577_a(field_146122_a);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.field_146123_n = (xPos >= this.field_146128_h && yPos >= this.field_146129_i && xPos < this.field_146128_h + this.field_146120_f && yPos < this.field_146129_i + this.field_146121_g);
        int k = func_146114_a(this.field_146123_n);
        func_73729_b(this.field_146128_h, this.field_146129_i, 0, 46 + k * 20, this.field_146120_f / 2, this.field_146121_g);
        func_73729_b(this.field_146128_h + this.field_146120_f / 2, this.field_146129_i, 200 - this.field_146120_f / 2, 46 + k * 20, this.field_146120_f / 2, this.field_146121_g);
        func_146119_b(par1Minecraft, xPos, yPos);
        int l = 14737632;

        if (!this.field_146124_l) {

          l = -6250336;
        }
        else if (this.field_146123_n) {

          l = 16777120;
        }

        func_73732_a(fontrenderer, (GuiBarrel.this.barrelTE.mode == 0) ? TFC_Core.translate("gui.Barrel.ToggleOn") : TFC_Core.translate("gui.Barrel.ToggleOff"), this.field_146128_h + this.field_146120_f / 2, this.field_146129_i + (this.field_146121_g - 8) / 2, l);
      }
    }
  }

  public class GuiBarrelTabButton
    extends GuiButton
  {
    private GuiBarrel screen;
    private IIcon buttonicon;
    private int xPos;
    private int yPos = 172;
    private int xSize = 31;
    private int ySize = 15;


    public GuiBarrelTabButton(int index, int xPos, int yPos, int width, int height, GuiBarrel gui, IIcon icon, String s) {
      super(index, xPos, yPos, width, height, s);
      this.screen = gui;
      this.buttonicon = icon;
    }


    public GuiBarrelTabButton(int index, int xPos, int yPos, int width, int height, GuiBarrel gui, String s, int xp, int yp, int xs, int ys) {
      super(index, xPos, yPos, width, height, s);
      this.screen = gui;
      this.xPos = xp;
      this.yPos = yp;
      this.xSize = xs;
      this.ySize = ys;
    }



    public void func_146112_a(Minecraft mc, int x, int y) {
      if (this.field_146125_m) {

        TFC_Core.bindTexture(GuiBarrel.TEXTURE);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.field_73735_i = 301.0F;
        func_73729_b(this.field_146128_h, this.field_146129_i, this.xPos, this.yPos, this.xSize, this.ySize);
        this.field_146123_n = (x >= this.field_146128_h && y >= this.field_146129_i && x < this.field_146128_h + this.field_146120_f && y < this.field_146129_i + this.field_146121_g);

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        TFC_Core.bindTexture(TextureMap.field_110575_b);
        if (this.buttonicon != null) {
          func_94065_a(this.field_146128_h + 12, this.field_146129_i + 4, this.buttonicon, 8, 8);
        }
        this.field_73735_i = 0.0F;
        func_146119_b(mc, x, y);

        if (this.field_146123_n) {

          this.screen.drawTooltip(x, y, this.field_146126_j);
          GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        }
      }
    }
  }



  protected void func_146284_a(GuiButton guibutton) {
    if (this.guiTab == 0) {

      if (guibutton.field_146127_k == 0) {

        if (!this.barrelTE.getSealed()) {
          this.barrelTE.actionSeal(0, this.player);
        } else {
          this.barrelTE.actionUnSeal(0, this.player);
        }
      } else if (guibutton.field_146127_k == 1) {
        this.barrelTE.actionEmpty();
      } else if (guibutton.field_146127_k == 2) {

        this.barrelTE.actionMode();
        createButtons();
      }
      else if (guibutton.field_146127_k == 3 && this.barrelTE.getFluidLevel() == 0 && this.barrelTE.getInvCount() == 0) {
        this.barrelTE.actionSwitchTab(1, this.player);
      }
    } else if (this.guiTab == 1) {

      if (guibutton.field_146127_k == 1 && this.barrelTE.getInvCount() == 0) {
        this.barrelTE.actionSwitchTab(0, this.player);
      } else if (guibutton.field_146127_k == 2) {

        if (!this.barrelTE.getSealed()) {
          this.barrelTE.actionSeal(1, this.player);
        } else {
          this.barrelTE.actionUnSeal(1, this.player);
        }  createButtons();
      }
    }
  }






  protected void func_146976_a(float f, int mouseX, int mouseY) {
    bindTexture(TEXTURE);
    this.field_147003_i = (this.field_146294_l - this.field_146999_f) / 2;
    this.field_147009_r = (this.field_146295_m - this.field_147000_g) / 2;

    if (this.guiTab == 0) {

      func_73729_b(this.field_147003_i, this.field_147009_r, 0, 0, this.field_146999_f, getShiftedYSize());

      int scale = 0;
      if (this.barrelTE != null && this.barrelTE.fluid != null) {

        scale = this.barrelTE.getLiquidScaled(50);

        IIcon liquidIcon = this.barrelTE.fluid.getFluid().getIcon(this.barrelTE.fluid);
        TFC_Core.bindTexture(TextureMap.field_110575_b);
        int color = this.barrelTE.fluid.getFluid().getColor(this.barrelTE.fluid);
        GL11.glColor4ub((byte)(color >> 16 & 0xFF), (byte)(color >> 8 & 0xFF), (byte)(color & 0xFF), (byte)-86);
        int div = (int)Math.floor((scale / 8));
        int rem = scale - div * 8;
        func_94065_a(this.field_147003_i + 12, this.field_147009_r + 65 - scale, liquidIcon, 8, (div > 0) ? 8 : rem);
        for (int c = 0; div > 0 && c < div; c++)
        {
          func_94065_a(this.field_147003_i + 12, this.field_147009_r + 65 - 8 + c * 8, liquidIcon, 8, 8);
        }
        GL11.glColor3f(0.0F, 0.0F, 0.0F);
      }
      ItemStack inStack = this.barrelTE.func_70301_a(0);


      if (this.barrelTE.getFluidStack() != null) {
        func_73732_a(this.field_146289_q, this.barrelTE.fluid.getFluid().getLocalizedName(this.barrelTE.getFluidStack()), this.field_147003_i + 88, this.field_147009_r + 7, 5592405);
      }

      if (this.barrelTE.sealtime != 0)
      {
        func_73732_a(this.field_146289_q, TFC_Time.getDateStringFromHours(this.barrelTE.sealtime), this.field_147003_i + 88, this.field_147009_r + 17, 5592405);
      }


      if (this.barrelTE.recipe != null) {

        if (!(this.barrelTE.recipe instanceof com.bioxx.tfc.api.Crafting.BarrelBriningRecipe))
        {
          func_73732_a(this.field_146289_q, TFC_Core.translate("gui.Output") + ": " + this.barrelTE.recipe.getRecipeName(), this.field_147003_i + 88, this.field_147009_r + 72, 5592405);
        }
        else if (this.barrelTE.getSealed() && this.barrelTE.getFluidStack() != null && this.barrelTE.getFluidStack().getFluid() == TFCFluids.BRINE)
        {
          if (inStack != null && inStack.func_77973_b() instanceof IFood && (((IFood)inStack.func_77973_b()).getFoodGroup() == EnumFoodGroup.Fruit || ((IFood)inStack
            .func_77973_b()).getFoodGroup() == EnumFoodGroup.Vegetable || ((IFood)inStack.func_77973_b()).getFoodGroup() == EnumFoodGroup.Protein || (IFood)inStack
            .func_77973_b() == TFCItems.cheese) && !Food.isBrined(inStack))
          {
            func_73732_a(this.field_146289_q, TFC_Core.translate("gui.barrel.brining"), this.field_147003_i + 88, this.field_147009_r + 72, 5592405);
          }
        }

      } else if (this.barrelTE.recipe == null && this.barrelTE.getSealed() && this.barrelTE.getFluidStack() != null && inStack != null && inStack.func_77973_b() instanceof IFood && this.barrelTE
        .getFluidStack().getFluid() == TFCFluids.VINEGAR && !Food.isPickled(inStack) && Food.getWeight(inStack) / (this.barrelTE.getFluidStack()).amount <= 160.0F / this.barrelTE.getMaxLiquid()) {

        if ((((IFood)inStack.func_77973_b()).getFoodGroup() == EnumFoodGroup.Fruit || ((IFood)inStack.func_77973_b()).getFoodGroup() == EnumFoodGroup.Vegetable || ((IFood)inStack
          .func_77973_b()).getFoodGroup() == EnumFoodGroup.Protein || (IFood)inStack.func_77973_b() == TFCItems.cheese) &&
          Food.isBrined(inStack))
        {
          func_73732_a(this.field_146289_q, TFC_Core.translate("gui.barrel.pickling"), this.field_147003_i + 88, this.field_147009_r + 72, 5592405);
        }
      }
      else {

        BarrelPreservativeRecipe preservative = BarrelManager.getInstance().findMatchingPreservativeRepice(this.barrelTE, inStack, this.barrelTE.getFluidStack(), this.barrelTE.getSealed());
        if (preservative != null) {
          func_73732_a(this.field_146289_q, TFC_Core.translate(preservative.getPreservingString()), this.field_147003_i + 88, this.field_147009_r + 72, 5592405);
        }
      }

    }
    else if (this.guiTab == 1) {

      func_73729_b(this.field_147003_i, this.field_147009_r, 0, 86, this.field_146999_f, getShiftedYSize());
    }

    PlayerInventory.drawInventory(this, this.field_146294_l, this.field_146295_m, getShiftedYSize());
  }



  protected void func_146979_b(int mouseX, int mouseY) {
    if (this.guiTab == 0 && mouseInRegion(12, 15, 9, 50, mouseX, mouseY)) {

      ArrayList<String> list = new ArrayList<>();
      list.add(this.barrelTE.getFluidLevel() + "mB");
      drawHoveringText(list, mouseX - this.field_147003_i, mouseY - this.field_147009_r + 8, this.field_146289_q);
    }
  }



  public void func_73732_a(FontRenderer fontrenderer, String s, int i, int j, int k) {
    fontrenderer.func_78276_b(s, i - fontrenderer.func_78256_a(s) / 2, j, k);
  }



  public void func_73863_a(int x, int y, float par3) {
    super.func_73863_a(x, y, par3);
    if (this.barrelTE.getSealed()) {

      GL11.glPushMatrix();
      if (this.guiTab == 0) {

        Slot inputSlot = this.field_147002_h.func_75139_a(0);
        drawSlotOverlay(inputSlot);
      }
      else if (this.guiTab == 1) {

        for (int i = 0; i < this.barrelTE.storage.length; i++) {

          Slot slot = this.field_147002_h.func_75139_a(i);
          drawSlotOverlay(slot);
        }
      }

      GL11.glPopMatrix();
    }
  }


  private void drawSlotOverlay(Slot slot) {
    GL11.glDisable(2896);
    GL11.glDisable(2929);
    int xPos = slot.field_75223_e + this.field_147003_i - 1;
    int yPos = slot.field_75221_f + this.field_147009_r - 1;
    GL11.glColorMask(true, true, true, false);
    func_73733_a(xPos, yPos, xPos + 18, yPos + 18, 1979711487, 1979711487);
    GL11.glColorMask(true, true, true, true);
    GL11.glEnable(2896);
    GL11.glEnable(2929);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\GUI\GuiBarrel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */