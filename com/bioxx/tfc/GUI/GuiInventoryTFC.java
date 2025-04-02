package com.bioxx.tfc.GUI;

import com.bioxx.tfc.Core.Player.PlayerInventory;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Textures;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.api.Food;
import java.util.Collection;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;






public class GuiInventoryTFC
  extends InventoryEffectRenderer
{
  private float xSizeLow;
  private float ySizeLow;
  private boolean hasEffect;
  protected static final ResourceLocation UPPER_TEXTURE = new ResourceLocation("terrafirmacraft:textures/gui/inventory.png");
  protected static final ResourceLocation UPPER_TEXTURE_2X2 = new ResourceLocation("terrafirmacraft:textures/gui/gui_inventory2x2.png");
  protected static final ResourceLocation EFFECTS_TEXTURE = new ResourceLocation("terrafirmacraft:textures/gui/inv_effects.png");
  protected EntityPlayer player;
  protected Slot activeSlot;
  private long spamTimer;

  public GuiInventoryTFC(EntityPlayer player) {
    super(player.field_71069_bz);
    this.field_146291_p = true;
    player.func_71064_a((StatBase)AchievementList.field_76004_f, 1);
    this.field_146999_f = 176;
    this.field_147000_g = 85 + PlayerInventory.invYSize;
    this.player = player;
  }



  protected void func_146976_a(float par1, int par2, int par3) {
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    if (this.player.getEntityData().func_74764_b("craftingTable")) {
      TFC_Core.bindTexture(UPPER_TEXTURE);
    } else {
      TFC_Core.bindTexture(UPPER_TEXTURE_2X2);
    }  int k = this.field_147003_i;
    int l = this.field_147009_r;
    func_73729_b(k, l, 0, 0, this.field_146999_f, 86);

    drawPlayerModel(k + 51, l + 75, 30, (k + 51) - this.xSizeLow, (l + 75 - 50) - this.ySizeLow, (EntityLivingBase)this.field_146297_k.field_71439_g);

    PlayerInventory.drawInventory((GuiContainer)this, this.field_146294_l, this.field_146295_m, this.field_147000_g - PlayerInventory.invYSize);
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
  }



  public void func_94065_a(int i, int j, IIcon icon, int w, int h) {
    Tessellator tessellator = Tessellator.field_78398_a;
    GL11.glEnable(3042);
    GL11.glEnable(2929);
    tessellator.func_78382_b();
    tessellator.func_78374_a((i + 0), (j + h), this.field_73735_i, icon.func_94209_e(), icon.func_94210_h());
    tessellator.func_78374_a((i + w), (j + h), this.field_73735_i, icon.func_94212_f(), icon.func_94210_h());
    tessellator.func_78374_a((i + w), (j + 0), this.field_73735_i, icon.func_94212_f(), icon.func_94206_g());
    tessellator.func_78374_a((i + 0), (j + 0), this.field_73735_i, icon.func_94209_e(), icon.func_94206_g());
    tessellator.func_78381_a();
    GL11.glDisable(3042);
  }


  public static void drawPlayerModel(int par0, int par1, int par2, float par3, float par4, EntityLivingBase par5EntityLivingBase) {
    GL11.glEnable(2903);
    GL11.glPushMatrix();
    GL11.glTranslatef(par0, par1, 50.0F);
    GL11.glScalef(-par2, par2, par2);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    float f2 = par5EntityLivingBase.field_70761_aq;
    float f3 = par5EntityLivingBase.field_70177_z;
    float f4 = par5EntityLivingBase.field_70125_A;
    float f5 = par5EntityLivingBase.field_70758_at;
    float f6 = par5EntityLivingBase.field_70759_as;
    GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
    RenderHelper.func_74519_b();
    GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(-((float)Math.atan((par4 / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
    par5EntityLivingBase.field_70761_aq = (float)Math.atan((par3 / 40.0F)) * 20.0F;
    par5EntityLivingBase.field_70177_z = (float)Math.atan((par3 / 40.0F)) * 40.0F;
    par5EntityLivingBase.field_70125_A = -((float)Math.atan((par4 / 40.0F))) * 20.0F;
    par5EntityLivingBase.field_70759_as = par5EntityLivingBase.field_70177_z;
    par5EntityLivingBase.field_70758_at = par5EntityLivingBase.field_70177_z;
    GL11.glTranslatef(0.0F, par5EntityLivingBase.field_70129_M, 0.0F);
    RenderManager.field_78727_a.field_78735_i = 180.0F;
    RenderManager.field_78727_a.func_147940_a((Entity)par5EntityLivingBase, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
    par5EntityLivingBase.field_70761_aq = f2;
    par5EntityLivingBase.field_70177_z = f3;
    par5EntityLivingBase.field_70125_A = f4;
    par5EntityLivingBase.field_70758_at = f5;
    par5EntityLivingBase.field_70759_as = f6;
    GL11.glPopMatrix();
    RenderHelper.func_74518_a();
    GL11.glDisable(32826);
    OpenGlHelper.func_77473_a(OpenGlHelper.field_77476_b);
    GL11.glDisable(3553);
    OpenGlHelper.func_77473_a(OpenGlHelper.field_77478_a);
  }






  protected void func_146979_b(int par1, int par2) {}





  public void func_73876_c() {
    if (this.field_146297_k.field_71442_b.func_78758_h()) {
      this.field_146297_k.func_147108_a((GuiScreen)new GuiContainerCreativeTFC(this.player));
    }
  }


  public void func_73866_w_() {
    this.field_146292_n.clear();

    if (this.field_146297_k.field_71442_b.func_78758_h()) {
      this.field_146297_k.func_147108_a((GuiScreen)new GuiContainerCreativeTFC((EntityPlayer)this.field_146297_k.field_71439_g));
    } else {
      super.func_73866_w_();
    }
    if (!this.field_146297_k.field_71439_g.func_70651_bq().isEmpty()) {


      this.field_147003_i = (this.field_146294_l - this.field_146999_f) / 2;
      this.hasEffect = true;
    }

    this.field_146292_n.clear();
    this.field_146292_n.add(new GuiInventoryButton(0, this.field_147003_i + 176, this.field_147009_r + 3, 25, 20, 0, 86, 25, 20,
          TFC_Core.translate("gui.Inventory.Inventory"), TFC_Textures.guiInventory));
    this.field_146292_n.add(new GuiInventoryButton(1, this.field_147003_i + 176, this.field_147009_r + 22, 25, 20, 0, 86, 25, 20,
          TFC_Core.translate("gui.Inventory.Skills"), TFC_Textures.guiSkills));
    this.field_146292_n.add(new GuiInventoryButton(2, this.field_147003_i + 176, this.field_147009_r + 41, 25, 20, 0, 86, 25, 20,
          TFC_Core.translate("gui.Calendar.Calendar"), TFC_Textures.guiCalendar));
    this.field_146292_n.add(new GuiInventoryButton(3, this.field_147003_i + 176, this.field_147009_r + 60, 25, 20, 0, 86, 25, 20,
          TFC_Core.translate("gui.Inventory.Health"), TFC_Textures.guiHealth));
  }



  protected void func_146284_a(GuiButton guibutton) {
    if (guibutton.field_146127_k == 1) {
      Minecraft.func_71410_x().func_147108_a((GuiScreen)new GuiSkills(this.player));
    } else if (guibutton.field_146127_k == 2) {
      Minecraft.func_71410_x().func_147108_a(new GuiCalendar(this.player));
    } else if (guibutton.field_146127_k == 3) {
      Minecraft.func_71410_x().func_147108_a((GuiScreen)new GuiHealth(this.player));
    }
  }


  public void func_73863_a(int par1, int par2, float par3) {
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    super.func_73863_a(par1, par2, par3);
    this.xSizeLow = par1;
    this.ySizeLow = par2;
    if (this.hasEffect) {
      displayDebuffEffects();
    }
    for (int j1 = 0; j1 < this.field_147002_h.field_75151_b.size(); j1++) {

      Slot slot = this.field_147002_h.field_75151_b.get(j1);
      if (func_146981_a(slot, par1, par2) && slot.func_111238_b()) {
        this.activeSlot = slot;
      }
    }
  }

  protected boolean func_146981_a(Slot par1Slot, int par2, int par3) {
    return func_146978_c(par1Slot.field_75223_e, par1Slot.field_75221_f, 16, 16, par2, par3);
  }





  private void displayDebuffEffects() {
    int var1 = this.field_147003_i - 124;
    int var2 = this.field_147009_r;
    Collection var4 = this.field_146297_k.field_71439_g.func_70651_bq();

    if (!var4.isEmpty()) {

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glDisable(2896);
      int var6 = 33;

      if (var4.size() > 5) {
        var6 = 132 / (var4.size() - 1);
      }
      for (Iterator<PotionEffect> var7 = this.field_146297_k.field_71439_g.func_70651_bq().iterator(); var7.hasNext(); var2 += var6) {

        PotionEffect var8 = var7.next();


        Potion var9 = (Potion.field_76425_a[var8.func_76456_a()] instanceof com.bioxx.tfc.Food.TFCPotion) ? Potion.field_76425_a[var8.func_76456_a()] : Potion.field_76425_a[var8.func_76456_a()];
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        TFC_Core.bindTexture(EFFECTS_TEXTURE);
        func_73729_b(var1, var2, 0, 166, 140, 32);

        if (var9.func_76400_d()) {

          int var10 = var9.func_76392_e();
          func_73729_b(var1 + 6, var2 + 7, 0 + var10 % 8 * 18, 198 + var10 / 8 * 18, 18, 18);
        }

        String var12 = TFC_Core.translate(var9.func_76393_a());

        if (var8.func_76458_c() == 1) {
          var12 = var12 + " II";
        } else if (var8.func_76458_c() == 2) {
          var12 = var12 + " III";
        } else if (var8.func_76458_c() == 3) {
          var12 = var12 + " IV";
        }
        this.field_146289_q.func_78261_a(var12, var1 + 10 + 18, var2 + 6, 16777215);
        String var11 = Potion.func_76389_a(var8);
        this.field_146289_q.func_78261_a(var11, var1 + 10 + 18, var2 + 6 + 10, 8355711);
      }
    }
  }



  private boolean isCooking(ItemStack is) {
    String itype = is.func_77973_b().func_77658_a().toLowerCase();
    return (itype.contains("salad") || itype.contains("soup") || itype.contains("sandwich"));
  }




  protected boolean func_146983_a(int keycode) {
    boolean cooking = false;
    if (this.activeSlot != null && this.activeSlot.field_75222_d == 0 && this.activeSlot.func_75216_d() && this.activeSlot
      .func_75211_c().func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood) {
      return false;
    }
    if (this.activeSlot != null && this.activeSlot.func_75216_d() &&
      isCooking(this.activeSlot.func_75211_c())) cooking = true;

    if (!cooking && keycode == 31 && this.activeSlot != null && this.activeSlot.func_82869_a(this.player) && this.activeSlot.func_75216_d() && this.activeSlot
      .func_75211_c() != null && this.activeSlot.func_75211_c().func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood && TFC_Time.getTotalTicks() > this.spamTimer + 5L) {

      this.spamTimer = TFC_Time.getTotalTicks();
      Item iType = this.activeSlot.func_75211_c().func_77973_b();
      ItemStack activeIS = this.activeSlot.func_75211_c();
      for (int i = 9; i < 45 && getEmptyCraftSlot() != -1; i++) {

        ItemStack is = this.field_147002_h.func_75139_a(i).func_75211_c();
        if (is != null && is.func_77973_b() == iType && Food.areEqual(activeIS, is) && Food.getWeight(is) < 160.0F) {
          func_146984_a(this.field_147002_h.func_75139_a(i), i, getEmptyCraftSlot(), 7);
        }
      }
      if (this.field_147002_h.func_75139_a(0).func_75211_c() != null)
      {
        func_146984_a(this.field_147002_h.func_75139_a(0), 0, 0, 1);
      }
      return true;
    }
    if (!cooking && keycode == 32 && TFC_Time.getTotalTicks() > this.spamTimer + 5L) {

      this.spamTimer = TFC_Time.getTotalTicks();
      int knifeSlot = -1;
      if (!getCraftingHasKnife())
      {
        for (int j = 9; j < 45 && getEmptyCraftSlot() != -1; j++) {

          ItemStack is = this.field_147002_h.func_75139_a(j).func_75211_c();
          if (is != null && is.func_77973_b() instanceof com.bioxx.tfc.api.Tools.IKnife) {

            knifeSlot = j;
            break;
          }
        }
      }
      for (int i = 9; i < 45 && getEmptyCraftSlot() != -1 && knifeSlot != -1 && this.field_147002_h.func_75139_a(knifeSlot).func_75211_c() != null; i++) {

        ItemStack is = this.field_147002_h.func_75139_a(i).func_75211_c();
        int knifeDamage = this.field_147002_h.func_75139_a(knifeSlot).func_75211_c().func_77960_j();
        if (knifeDamage >= this.field_147002_h.func_75139_a(knifeSlot).func_75211_c().func_77958_k()) {
          break;
        }
        if (is != null && !isCooking(is) &&
          is != null && !(is.func_77973_b() instanceof com.bioxx.tfc.Food.ItemMeal) && is.func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood && Food.getDecay(is) > 0.0F &&
          Food.getDecayTimer(is) >= TFC_Time.getTotalHours()) {

          func_146984_a(this.field_147002_h.func_75139_a(i), i, getEmptyCraftSlot(), 7);
          func_146984_a(this.field_147002_h.func_75139_a(0), 0, 0, 1);
        }
      }
      return true;
    }
    return super.func_146983_a(keycode);
  }


  private int getEmptyCraftSlot() {
    if (this.field_147002_h.func_75139_a(4).func_75211_c() == null)
      return 4;
    if (this.field_147002_h.func_75139_a(1).func_75211_c() == null)
      return 1;
    if (this.field_147002_h.func_75139_a(2).func_75211_c() == null)
      return 2;
    if (this.field_147002_h.func_75139_a(3).func_75211_c() == null)
      return 3;
    if (this.player.getEntityData().func_74764_b("craftingTable")) {

      if (this.field_147002_h.func_75139_a(45).func_75211_c() == null)
        return 45;
      if (this.field_147002_h.func_75139_a(46).func_75211_c() == null)
        return 46;
      if (this.field_147002_h.func_75139_a(47).func_75211_c() == null)
        return 47;
      if (this.field_147002_h.func_75139_a(48).func_75211_c() == null)
        return 48;
      if (this.field_147002_h.func_75139_a(49).func_75211_c() == null) {
        return 49;
      }
    }
    return -1;
  }


  private boolean getCraftingHasKnife() {
    if (this.field_147002_h.func_75139_a(4).func_75211_c() != null && this.field_147002_h.func_75139_a(4).func_75211_c().func_77973_b() instanceof com.bioxx.tfc.api.Tools.IKnife)
      return true;
    if (this.field_147002_h.func_75139_a(1).func_75211_c() != null && this.field_147002_h.func_75139_a(1).func_75211_c().func_77973_b() instanceof com.bioxx.tfc.api.Tools.IKnife)
      return true;
    if (this.field_147002_h.func_75139_a(2).func_75211_c() != null && this.field_147002_h.func_75139_a(2).func_75211_c().func_77973_b() instanceof com.bioxx.tfc.api.Tools.IKnife)
      return true;
    if (this.field_147002_h.func_75139_a(3).func_75211_c() != null && this.field_147002_h.func_75139_a(3).func_75211_c().func_77973_b() instanceof com.bioxx.tfc.api.Tools.IKnife)
      return true;
    if (this.player.getEntityData().func_74764_b("craftingTable")) {

      if (this.field_147002_h.func_75139_a(45).func_75211_c() != null && this.field_147002_h.func_75139_a(45).func_75211_c().func_77973_b() instanceof com.bioxx.tfc.api.Tools.IKnife)
        return true;
      if (this.field_147002_h.func_75139_a(46).func_75211_c() != null && this.field_147002_h.func_75139_a(46).func_75211_c().func_77973_b() instanceof com.bioxx.tfc.api.Tools.IKnife)
        return true;
      if (this.field_147002_h.func_75139_a(47).func_75211_c() != null && this.field_147002_h.func_75139_a(47).func_75211_c().func_77973_b() instanceof com.bioxx.tfc.api.Tools.IKnife)
        return true;
      if (this.field_147002_h.func_75139_a(48).func_75211_c() != null && this.field_147002_h.func_75139_a(48).func_75211_c().func_77973_b() instanceof com.bioxx.tfc.api.Tools.IKnife)
        return true;
      if (this.field_147002_h.func_75139_a(49).func_75211_c() != null && this.field_147002_h.func_75139_a(49).func_75211_c().func_77973_b() instanceof com.bioxx.tfc.api.Tools.IKnife) {
        return true;
      }
    }
    return false;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\GUI\GuiInventoryTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */