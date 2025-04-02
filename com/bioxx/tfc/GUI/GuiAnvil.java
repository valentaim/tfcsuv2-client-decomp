package com.bioxx.tfc.GUI;

import com.bioxx.tfc.Containers.ContainerAnvil;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Textures;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.TileEntities.TEAnvil;
import com.bioxx.tfc.api.Crafting.AnvilManager;
import com.bioxx.tfc.api.Crafting.PlanRecipe;
import com.bioxx.tfc.api.Enums.RuleEnum;
import com.bioxx.tfc.api.TFCOptions;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;



public class GuiAnvil
  extends GuiContainerTFC
{
  public TEAnvil anvilTE;
  public static ResourceLocation texture = new ResourceLocation("terrafirmacraft", "textures/gui/gui_anvil.png");
  private EntityPlayer player;
  private int x;
  private String plan = "";

  private int y;
  private int z;

  public GuiAnvil(InventoryPlayer inventoryplayer, TEAnvil te, World world, int x, int y, int z) {
    super((Container)new ContainerAnvil(inventoryplayer, te, world, x, y, z), 208, 117);
    this.anvilTE = te;
    this.player = inventoryplayer.field_70458_d;
    this.x = x;
    this.y = y;
    this.z = z;
  }
  private ItemStack input;
  private ItemStack input2;

  public void func_73866_w_() {
    super.func_73866_w_();

    this.field_146292_n.clear();
    this.field_146292_n.add(new GuiAnvilButton(7, this.field_147003_i + 123, this.field_147009_r + 82, 16, 16, TFC_Textures.anvilShrink, 208, 17, 16, 16, this, TFC_Core.translate("gui.Anvil.Shrink")));
    this.field_146292_n.add(new GuiAnvilButton(6, this.field_147003_i + 105, this.field_147009_r + 82, 16, 16, TFC_Textures.anvilUpset, 208, 17, 16, 16, this, TFC_Core.translate("gui.Anvil.Upset")));
    this.field_146292_n.add(new GuiAnvilButton(5, this.field_147003_i + 123, this.field_147009_r + 64, 16, 16, TFC_Textures.anvilBend, 208, 17, 16, 16, this, TFC_Core.translate("gui.Anvil.Bend")));
    this.field_146292_n.add(new GuiAnvilButton(4, this.field_147003_i + 105, this.field_147009_r + 64, 16, 16, TFC_Textures.anvilPunch, 208, 17, 16, 16, this, TFC_Core.translate("gui.Anvil.Punch")));
    this.field_146292_n.add(new GuiAnvilButton(3, this.field_147003_i + 87, this.field_147009_r + 82, 16, 16, TFC_Textures.anvilDraw, 208, 33, 16, 16, this, TFC_Core.translate("gui.Anvil.Draw")));
    this.field_146292_n.add(new GuiAnvilButton(2, this.field_147003_i + 69, this.field_147009_r + 82, 16, 16, TFC_Textures.anvilHitHeavy, 208, 33, 16, 16, this, TFC_Core.translate("gui.Anvil.HeavyHit")));
    this.field_146292_n.add(new GuiAnvilButton(1, this.field_147003_i + 87, this.field_147009_r + 64, 16, 16, TFC_Textures.anvilHitMedium, 208, 33, 16, 16, this, TFC_Core.translate("gui.Anvil.MediumHit")));
    this.field_146292_n.add(new GuiAnvilButton(0, this.field_147003_i + 69, this.field_147009_r + 64, 16, 16, TFC_Textures.anvilHitLight, 208, 33, 16, 16, this, TFC_Core.translate("gui.Anvil.LightHit")));
    this.field_146292_n.add(new GuiButton(8, this.field_147003_i + 13, this.field_147009_r + 53, 36, 20, TFC_Core.translate("gui.Anvil.Weld")));
    this.field_146292_n.add(new GuiAnvilButton(9, this.field_147003_i + 113, this.field_147009_r + 7, 19, 21, 208, 49, 19, 21, this, 2, TFCOptions.anvilRuleColor2[0], TFCOptions.anvilRuleColor2[1], TFCOptions.anvilRuleColor2[2]));
    this.field_146292_n.add(new GuiAnvilButton(10, this.field_147003_i + 94, this.field_147009_r + 7, 19, 21, 208, 49, 19, 21, this, 1, TFCOptions.anvilRuleColor1[0], TFCOptions.anvilRuleColor1[1], TFCOptions.anvilRuleColor1[2]));
    this.field_146292_n.add(new GuiAnvilButton(11, this.field_147003_i + 75, this.field_147009_r + 7, 19, 21, 208, 49, 19, 21, this, 0, TFCOptions.anvilRuleColor0[0], TFCOptions.anvilRuleColor0[1], TFCOptions.anvilRuleColor0[2]));
    this.field_146292_n.add(new GuiAnvilPlanButton(12, this.field_147003_i + 122, this.field_147009_r + 45, 18, 18, this, TFC_Core.translate("gui.Anvil.Plans")));
  }



  public void func_73876_c() {
    super.func_73876_c();
    if (this.anvilTE != null) {

      String craftingPlan = this.anvilTE.craftingPlan;
      ItemStack stack1 = this.anvilTE.anvilItemStacks[1];
      ItemStack stack2 = this.anvilTE.anvilItemStacks[5];

      if ((craftingPlan != null && craftingPlan != this.plan) || (stack1 != null && stack1 != this.input) || (stack2 != null && stack2 != this.input2)) {



        this.plan = this.anvilTE.craftingPlan;
        this.anvilTE.updateRecipe();
        this.input = this.anvilTE.anvilItemStacks[1];
        this.input2 = this.anvilTE.anvilItemStacks[5];
      }
    }
  }



  protected void func_146284_a(GuiButton guibutton) {
    if (guibutton.field_146127_k == 0) {
      this.anvilTE.actionLightHammer();
    } else if (guibutton.field_146127_k == 2) {
      this.anvilTE.actionHeavyHammer();
    } else if (guibutton.field_146127_k == 1) {
      this.anvilTE.actionHammer();
    } else if (guibutton.field_146127_k == 3) {
      this.anvilTE.actionDraw();
    } else if (guibutton.field_146127_k == 4) {
      this.anvilTE.actionPunch();
    } else if (guibutton.field_146127_k == 5) {
      this.anvilTE.actionBend();
    } else if (guibutton.field_146127_k == 6) {
      this.anvilTE.actionUpset();
    } else if (guibutton.field_146127_k == 7) {
      this.anvilTE.actionShrink();
    } else if (guibutton.field_146127_k == 8) {
      this.anvilTE.actionWeld();
    } else if (guibutton.field_146127_k == 12 && this.anvilTE.anvilItemStacks[1] != null) {
      this.player.openGui(TerraFirmaCraft.instance, 24, this.player.field_70170_p, this.x, this.y, this.z);
    }  this.field_147002_h.func_75142_b();
  }



  protected void func_146976_a(float f, int i, int j) {
    drawGui(texture);
  }



  protected void drawForeground(int guiLeft, int guiTop) {
    if (this.anvilTE != null) {

      int i1 = this.anvilTE.getCraftingValue();
      func_73729_b(guiLeft + 27 + i1, guiTop + 103, 213, 10, 5, 5);

      i1 = this.anvilTE.getItemCraftingValue();
      func_73729_b(guiLeft + 27 + i1, guiTop + 108, 208, 10, 5, 6);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);

      if (this.anvilTE.workRecipe != null) {


        int s0 = (int)(this.anvilTE.workRecipe.getSkillMult(this.player) * 1000.0F);
        float s1 = s0 / 10.0F;
        this.field_146289_q.func_78276_b("Bonus: ", guiLeft + 150, guiTop + 8, 16736256);
        this.field_146289_q.func_78276_b("   " + s1 + "%", guiLeft + 150, guiTop + 20, 16736256);
      }

      drawItemRulesImages(guiLeft, guiTop);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
      drawRulesImages(guiLeft, guiTop);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
    }
  }



  public void drawTooltip(int mx, int my, String text) {
    List<String> list = new ArrayList<>();
    list.add(text);
    drawHoveringText(list, mx, my + 15, this.field_146289_q);
    RenderHelper.func_74518_a();
    GL11.glDisable(2896);
    GL11.glDisable(2929);
  }


  public void drawItemRulesImages(int w, int h) {
    if (this.anvilTE != null && this.anvilTE.itemCraftingRules != null) {

      PlanRecipe p = AnvilManager.getInstance().getPlan(this.anvilTE.craftingPlan);
      if (p == null)
        return;
      RuleEnum[] rules = (this.anvilTE.workRecipe != null) ? p.rules : null;
      int[] itemRules = this.anvilTE.getItemRules();

      this.field_146297_k.func_110434_K().func_110577_a(TextureMap.field_110575_b);
      func_94065_a(w + 80, h + 31, getIconFromRule(itemRules[0]), 10, 10);
      func_94065_a(w + 99, h + 31, getIconFromRule(itemRules[1]), 10, 10);
      func_94065_a(w + 118, h + 31, getIconFromRule(itemRules[2]), 10, 10);

      this.field_146297_k.func_110434_K().func_110577_a(texture);

      if (rules != null && rules[0].matches(itemRules, 0))
        GL11.glColor4f(0.0F, 1.0F, 0.0F, 1.0F);
      func_73729_b(w + 77, h + 28, 210, 115, 15, 15);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

      if (rules != null && rules[1].matches(itemRules, 1))
        GL11.glColor4f(0.0F, 1.0F, 0.0F, 1.0F);
      func_73729_b(w + 96, h + 28, 210, 115, 15, 15);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

      if (rules != null && rules[2].matches(itemRules, 2))
        GL11.glColor4f(0.0F, 1.0F, 0.0F, 1.0F);
      func_73729_b(w + 115, h + 28, 210, 115, 15, 15);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }
  }





  public void drawRulesImages(int w, int h) {
    if (this.anvilTE.workRecipe != null) {

      PlanRecipe p = AnvilManager.getInstance().getPlan(this.anvilTE.craftingPlan);
      if (p == null)
        return;
      RuleEnum[] rules = p.rules;


      TFC_Core.bindTexture(TextureMap.field_110575_b);
      func_94065_a(w + 80, h + 10, getIconFromRule((rules[0]).Action), 10, 10);
      func_94065_a(w + 99, h + 10, getIconFromRule((rules[1]).Action), 10, 10);
      func_94065_a(w + 118, h + 10, getIconFromRule((rules[2]).Action), 10, 10);

      TFC_Core.bindTexture(texture);

      GL11.glColor4ub(TFCOptions.anvilRuleColor0[0], TFCOptions.anvilRuleColor0[1], TFCOptions.anvilRuleColor0[2], (byte)-1);
      if ((rules[0]).Min == 0)
        func_73729_b(w + 75, h + 26, 228, 68, 19, 3);
      if ((rules[0]).Max > 0 && ((rules[0]).Min <= 1 || (rules[0]).Max == 1))
        func_73729_b(w + 94, h + 26, 228, 68, 19, 3);
      if ((rules[0]).Max > 1 && ((rules[0]).Min <= 2 || (rules[0]).Max == 2)) {
        func_73729_b(w + 113, h + 26, 228, 68, 19, 3);
      }
      GL11.glColor4ub(TFCOptions.anvilRuleColor1[0], TFCOptions.anvilRuleColor1[1], TFCOptions.anvilRuleColor1[2], (byte)-1);
      if ((rules[1]).Min == 0)
        func_73729_b(w + 75, h + 24, 228, 68, 19, 3);
      if ((rules[1]).Max > 0 && ((rules[1]).Min <= 1 || (rules[1]).Max == 1))
        func_73729_b(w + 94, h + 24, 228, 68, 19, 3);
      if ((rules[1]).Max > 1 && ((rules[1]).Min <= 2 || (rules[1]).Max == 2)) {
        func_73729_b(w + 113, h + 24, 228, 68, 19, 3);
      }
      GL11.glColor4ub(TFCOptions.anvilRuleColor2[0], TFCOptions.anvilRuleColor2[1], TFCOptions.anvilRuleColor2[2], (byte)-1);
      if ((rules[2]).Min == 0)
        func_73729_b(w + 75, h + 22, 228, 68, 19, 3);
      if ((rules[2]).Max > 0 && ((rules[2]).Min <= 1 || (rules[2]).Max == 1))
        func_73729_b(w + 94, h + 22, 228, 68, 19, 3);
      if ((rules[2]).Max > 1 && ((rules[2]).Min <= 2 || (rules[2]).Max == 2)) {
        func_73729_b(w + 113, h + 22, 228, 68, 19, 3);
      }
    }
  }




  public IIcon getIconFromRule(int action) {
    switch (action) {

      case 0:
        return TFC_Textures.anvilHit;
      case 1:
        return TFC_Textures.anvilDraw;
      case 3:
        return TFC_Textures.anvilPunch;
      case 4:
        return TFC_Textures.anvilBend;
      case 5:
        return TFC_Textures.anvilUpset;
      case 6:
        return TFC_Textures.anvilShrink;
    }
    return TFC_Textures.invisibleTexture;
  }




  protected boolean func_146978_c(int slotX, int slotY, int sizeX, int sizeY, int clickX, int clickY) {
    int k1 = this.field_147003_i;
    int l1 = this.field_147009_r;
    clickX -= k1;
    clickY -= l1;
    return (clickX >= slotX - 1 && clickX < slotX + sizeX + 1 && clickY >= slotY - 1 && clickY < slotY + sizeY + 1);
  }





  public void drawTexturedModalRect(int drawX, int drawY, int drawWidth, int drawHeight, int u, int v, int width, int height) {
    float f = 0.00390625F;
    float f1 = 0.00390625F;
    Tessellator tessellator = Tessellator.field_78398_a;
    tessellator.func_78382_b();
    tessellator.func_78374_a((drawX + 0), (drawY + drawHeight), this.field_73735_i, ((u + 0) * f), ((v + height) * f1));
    tessellator.func_78374_a((drawX + drawWidth), (drawY + drawHeight), this.field_73735_i, ((u + width) * f), ((v + height) * f1));
    tessellator.func_78374_a((drawX + drawWidth), (drawY + 0), this.field_73735_i, ((u + width) * f), ((v + 0) * f1));
    tessellator.func_78374_a((drawX + 0), (drawY + 0), this.field_73735_i, ((u + 0) * f), ((v + 0) * f1));
    tessellator.func_78381_a();
  }



  public void func_94065_a(int x, int y, IIcon par3Icon, int width, int height) {
    Tessellator tessellator = Tessellator.field_78398_a;
    tessellator.func_78382_b();
    tessellator.func_78374_a((x + 0), (y + height), this.field_73735_i, par3Icon.func_94209_e(), par3Icon.func_94210_h());
    tessellator.func_78374_a((x + width), (y + height), this.field_73735_i, par3Icon.func_94212_f(), par3Icon.func_94210_h());
    tessellator.func_78374_a((x + width), (y + 0), this.field_73735_i, par3Icon.func_94212_f(), par3Icon.func_94206_g());
    tessellator.func_78374_a((x + 0), (y + 0), this.field_73735_i, par3Icon.func_94209_e(), par3Icon.func_94206_g());
    tessellator.func_78381_a();
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\GUI\GuiAnvil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */