package com.bioxx.tfc.GUI;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Handlers.Network.AbstractPacket;
import com.bioxx.tfc.Handlers.Network.ItemNBTPacket;
import com.bioxx.tfc.TerraFirmaCraft;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;









public class GuiBlueprint
  extends GuiScreen
{
  private World world;
  private EntityPlayer player;
  private ItemStack stack;
  private GuiTextField nameTextField;
  private static final int X_MINUS_BUTTON = 0;
  private static final int X_PLUS_BUTTON = 1;
  private static final int Y_MINUS_BUTTON = 2;
  private static final int Y_PLUS_BUTTON = 3;
  private static final int Z_MINUS_BUTTON = 4;
  private static final int Z_PLUS_BUTTON = 5;
  private static final int DONE_BUTTON = 6;
  private static final int CANCEL_BUTTON = 7;
  private static final String DONE_NAME = "gui.done";
  private static final String CANCEL_NAME = "gui.cancel";
  private int xAngle;
  private int yAngle;
  private int zAngle;
  protected int xSize = 200;


  protected int ySize = 172;


  protected int guiLeft() {
    return (this.field_146294_l - this.xSize) / 2;
  }


  protected int guiTop() {
    return (this.field_146295_m - this.ySize) / 2;
  }


  public GuiBlueprint(EntityPlayer p, World world, int i, int j, int k) {
    this.world = world;
    this.player = p;
    this.stack = this.player.field_71071_by.func_70448_g();
    if (this.stack.func_77942_o()) {

      this.xAngle = this.stack.field_77990_d.func_74762_e("xAngle");
      this.yAngle = this.stack.field_77990_d.func_74762_e("yAngle");
      this.zAngle = this.stack.field_77990_d.func_74762_e("zAngle");
    }
  }



  public void func_73876_c() {
    this.nameTextField.func_146178_a();
  }



  public void func_146281_b() {
    Keyboard.enableRepeatEvents(false);
  }



  public void func_73866_w_() {
    super.func_73866_w_();

    int nameTop = guiTop() + 10 + this.field_146289_q.field_78288_b + 4;
    this.nameTextField = new GuiTextField(this.field_146289_q, guiLeft() + 14, nameTop, 176, 20);
    this.nameTextField.func_146195_b(true);
    this.nameTextField.func_146205_d(false);
    if (!this.stack.func_77942_o() || this.stack.field_77990_d
      .func_74779_i("ItemName").isEmpty()) {

      this.nameTextField.func_146180_a("name_it");
      this.nameTextField.func_146190_e(0);
      this.nameTextField.func_146199_i(this.nameTextField.func_146179_b().length());
    }
    else {

      this.nameTextField.func_146184_c(false);
      this.nameTextField.func_146195_b(false);
      this.nameTextField.func_146180_a(this.stack.field_77990_d.func_74779_i("ItemName"));
    }

    Keyboard.enableRepeatEvents(true);
    int buttonsA = 20;
    int buttonsTop = nameTop + 20 + 4;
    int buttonsLeftF = guiLeft() + 10 + this.field_146289_q.func_78256_a("X:") + 4;
    int buttonsLeftS = buttonsLeftF + buttonsA + 4 + this.field_146289_q.func_78256_a("360") + 4;

    this.field_146292_n.clear();
    this.field_146293_o.clear();


    this.field_146292_n.add(new GuiButton(0, buttonsLeftF, buttonsTop, buttonsA, buttonsA, "<"));
    this.field_146292_n.add(new GuiButton(1, buttonsLeftS, buttonsTop, buttonsA, buttonsA, ">"));


    buttonsTop += buttonsA + 4;
    this.field_146292_n.add(new GuiButton(2, buttonsLeftF, buttonsTop, buttonsA, buttonsA, "<"));
    this.field_146292_n.add(new GuiButton(3, buttonsLeftS, buttonsTop, buttonsA, buttonsA, ">"));


    buttonsTop += buttonsA + 4;
    this.field_146292_n.add(new GuiButton(4, buttonsLeftF, buttonsTop, buttonsA, buttonsA, "<"));
    this.field_146292_n.add(new GuiButton(5, buttonsLeftS, buttonsTop, buttonsA, buttonsA, ">"));


    int doneWidth = this.field_146289_q.func_78256_a(TFC_Core.translate("gui.done")) + 20;
    this.field_146292_n.add(new GuiButton(6, (this.field_146294_l + this.xSize) / 2 - 10 - doneWidth, (this.field_146295_m + this.ySize) / 2 - 10 - buttonsA, doneWidth, buttonsA,



          TFC_Core.translate("gui.done")));



    int cancelWidth = this.field_146289_q.func_78256_a(TFC_Core.translate("gui.cancel")) + 20;
    this.field_146292_n.add(new GuiButton(7, (this.field_146294_l + this.xSize) / 2 - 10 - doneWidth - cancelWidth - 4, (this.field_146295_m + this.ySize) / 2 - 10 - buttonsA, cancelWidth, buttonsA,



          TFC_Core.translate("gui.cancel")));
  }


  public static RenderItem itemRenderer = new RenderItem();



  protected void func_73864_a(int par1, int par2, int par3) {
    super.func_73864_a(par1, par2, par3);
    this.nameTextField.func_146192_a(par1, par2, par3);
  }



  protected void func_73869_a(char par1, int par2) {
    this.nameTextField.func_146201_a(par1, par2);
    ((GuiButton)this.field_146292_n.get(6)).field_146124_l = (this.nameTextField.func_146179_b().trim().length() > 0);
    if (par1 == '\r') {
      func_146284_a(this.field_146292_n.get(6));
    }
  }


  public boolean func_73868_f() {
    return false;
  }



  public void func_73732_a(FontRenderer fontrenderer, String s, int i, int j, int k) {
    fontrenderer.func_78276_b(s, i - fontrenderer.func_78256_a(s) / 2, j, k);
  }



  protected void func_146284_a(GuiButton guibutton) {
    if (!this.world.field_72995_K) {
      return;
    }
    if (guibutton.field_146127_k == 0) {
      this.xAngle = (this.xAngle == 0) ? 270 : (this.xAngle - 90);
    } else if (guibutton.field_146127_k == 1) {
      this.xAngle = (this.xAngle == 270) ? 0 : (this.xAngle + 90);
    } else if (guibutton.field_146127_k == 2) {
      this.yAngle = (this.yAngle == 0) ? 270 : (this.yAngle - 90);
    } else if (guibutton.field_146127_k == 3) {
      this.yAngle = (this.yAngle == 270) ? 0 : (this.yAngle + 90);
    } else if (guibutton.field_146127_k == 4) {
      this.zAngle = (this.zAngle == 0) ? 270 : (this.zAngle - 90);
    } else if (guibutton.field_146127_k == 5) {
      this.zAngle = (this.zAngle == 270) ? 0 : (this.zAngle + 90);
    } else if (guibutton.field_146127_k == 6) {
      this.stack.field_77990_d.func_74757_a("Completed", true);
      this.stack.field_77990_d.func_74778_a("ItemName", this.nameTextField.func_146179_b());
      this.stack.field_77990_d.func_74768_a("xAngle", this.xAngle);
      this.stack.field_77990_d.func_74768_a("yAngle", this.yAngle);
      this.stack.field_77990_d.func_74768_a("zAngle", this.zAngle);

      ItemNBTPacket itemNBTPacket = new ItemNBTPacket(this.stack.field_77990_d);
      itemNBTPacket
        .addAcceptedTag("Completed")
        .addAcceptedTag("ItemName")
        .addAcceptedTag("xAngle")
        .addAcceptedTag("yAngle")
        .addAcceptedTag("zAngle");

      TerraFirmaCraft.PACKET_PIPELINE.sendToServer((AbstractPacket)itemNBTPacket);

      Minecraft.func_71410_x().func_147108_a(null);
    }
    else if (guibutton.field_146127_k == 7) {
      if (!this.stack.field_77990_d.func_74767_n("Completed")) {
        this.stack.func_77982_d(new NBTTagCompound());
        ItemNBTPacket itemNBTPacket = new ItemNBTPacket(this.stack.field_77990_d);
        itemNBTPacket
          .addRemoveTag("Completed")
          .addRemoveTag("ItemName")
          .addRemoveTag("Data")
          .addRemoveTag("xAngle")
          .addRemoveTag("yAngle")
          .addRemoveTag("zAngle");
        TerraFirmaCraft.PACKET_PIPELINE.sendToServer((AbstractPacket)itemNBTPacket);
      }

      Minecraft.func_71410_x().func_147108_a(null);
    }
  }



  public void func_73863_a(int par1, int par2, float par3) {
    TFC_Core.bindTexture(new ResourceLocation("terrafirmacraft", "textures/gui/gui_blueprint.png"));

    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    int left = guiLeft();
    int top = guiTop();
    func_73729_b(left, top, 0, 0, this.xSize, this.ySize);

    if (!this.stack.func_77942_o() || this.stack.field_77990_d.func_74779_i("ItemName").isEmpty()) {
      func_73732_a(this.field_146289_q, TFC_Core.translate("gui.Blueprint"), this.field_146294_l / 2, top + 10, 0);
    } else {
      func_73732_a(this.field_146289_q, TFC_Core.translate("gui.Rotate"), this.field_146294_l / 2, top + 10, 0);
    }
    int axesNameLeft = left + 10;
    int axesAngleLeft = axesNameLeft + this.field_146289_q.func_78256_a("X: ") + 4 + 20 + this.field_146289_q.func_78256_a("360") / 2;
    int topShift = (20 - this.field_146289_q.field_78288_b) / 2;

    top += 10 + this.field_146289_q.field_78288_b + 4;
    this.nameTextField.func_146194_f();


    top += 24;
    this.field_146289_q.func_78276_b("X:", axesNameLeft, top + topShift, 0);
    func_73732_a(this.field_146289_q, String.valueOf(this.xAngle), axesAngleLeft, top + topShift, 0);


    top += 24;
    this.field_146289_q.func_78276_b("Y:", axesNameLeft, top + topShift, 0);
    func_73732_a(this.field_146289_q, String.valueOf(this.yAngle), axesAngleLeft, top + topShift, 0);


    top += 24;
    this.field_146289_q.func_78276_b("Z:", axesNameLeft, top + topShift, 0);
    func_73732_a(this.field_146289_q, String.valueOf(this.zAngle), axesAngleLeft, top + topShift, 0);

    super.func_73863_a(par1, par2, par3);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\GUI\GuiBlueprint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */