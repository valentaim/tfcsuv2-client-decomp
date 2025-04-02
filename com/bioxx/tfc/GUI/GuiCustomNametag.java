package com.bioxx.tfc.GUI;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Handlers.Network.AbstractPacket;
import com.bioxx.tfc.Handlers.Network.ItemRenamePacket;
import com.bioxx.tfc.TerraFirmaCraft;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;







public class GuiCustomNametag
  extends GuiScreen
{
  private GuiTextField theGuiTextField;
  private World world;
  private EntityPlayer player;
  protected int xSize = 220;


  protected int ySize = 104;



  protected int guiLeft;



  protected int guiTop;



  public GuiCustomNametag(EntityPlayer p, World world, int i, int j, int k) {
    this.world = world;
    this.guiLeft = (this.field_146294_l - this.xSize) / 2;
    this.guiTop = (this.field_146295_m - this.ySize) / 2;


    this.player = p;
  }



  public void func_73876_c() {
    this.theGuiTextField.func_146178_a();
  }



  public void func_146281_b() {
    Keyboard.enableRepeatEvents(false);
  }



  public void func_73866_w_() {
    super.func_73866_w_();

    this.guiTop = (this.field_146295_m - this.ySize) / 2;

    Keyboard.enableRepeatEvents(true);
    this.field_146292_n.clear();
    this.field_146292_n.add(new GuiButton(0, this.field_146294_l / 2 - 100, this.guiTop + 57, TFC_Core.translate("gui.done")));


    this.theGuiTextField = new GuiTextField(this.field_146289_q, this.field_146294_l / 2 - 90, this.guiTop + 27, 180, 20);
    this.theGuiTextField.func_146195_b(true);
    this.theGuiTextField.func_146180_a("");
  }

  public static RenderItem itemRenderer = new RenderItem();



  protected void func_73864_a(int par1, int par2, int par3) {
    super.func_73864_a(par1, par2, par3);
    this.theGuiTextField.func_146192_a(par1, par2, par3);
  }



  protected void func_73869_a(char par1, int par2) {
    this.theGuiTextField.func_146201_a(par1, par2);
    ((GuiButton)this.field_146292_n.get(0)).field_146124_l = (this.theGuiTextField.func_146179_b().trim().length() > 0);
    if (par1 == '\r') {
      func_146284_a(this.field_146292_n.get(0));
    }
  }


  public boolean func_73868_f() {
    return false;
  }



  public void func_73732_a(FontRenderer fontrenderer, String s, int i, int j, int k) {
    fontrenderer.func_78276_b(s, i - fontrenderer.func_78256_a(s) / 2, j, k);
  }



  protected void func_146284_a(GuiButton guibutton) {
    if (guibutton.field_146127_k == 0 && this.world.field_72995_K) {

      ItemStack stack = this.player.field_71071_by.func_70448_g();
      stack.field_77990_d.func_74778_a("Name", this.theGuiTextField.func_146179_b());

      ItemRenamePacket itemRenamePacket = new ItemRenamePacket(this.theGuiTextField.func_146179_b());

      TerraFirmaCraft.PACKET_PIPELINE.sendToServer((AbstractPacket)itemRenamePacket);

      Minecraft.func_71410_x().func_147108_a(null);
    }
    else if (guibutton.field_146127_k == 1 && this.world.field_72995_K) {

      Minecraft.func_71410_x().func_147108_a(null);
    }
  }



  public void func_73863_a(int par1, int par2, float par3) {
    TFC_Core.bindTexture(new ResourceLocation("terrafirmacraft", "textures/gui/gui_nametag.png"));

    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    int l = (this.field_146294_l - this.xSize) / 2;
    int i1 = (this.field_146295_m - this.ySize) / 2;
    func_73729_b(l, i1, 0, 0, this.xSize, this.ySize);

    func_73732_a(this.field_146289_q, TFC_Core.translate("gui.Nametag"), this.field_146294_l / 2, i1 + 8, 0);
    this.theGuiTextField.func_146194_f();

    super.func_73863_a(par1, par2, par3);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\GUI\GuiCustomNametag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */