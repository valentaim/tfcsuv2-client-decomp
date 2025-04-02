package com.bioxx.tfc.Render;

import com.bioxx.tfc.Entities.EntityStand;
import com.bioxx.tfc.Render.Models.ModelStand;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.RenderPlayerEvent;
import org.lwjgl.opengl.GL11;



@SideOnly(Side.CLIENT)
public class RenderEntityStand
  extends RenderBiped
{
  private ModelBiped modelArmorChestplate;
  private ModelBiped modelArmor;
  private static final ResourceLocation TEXTURE = new ResourceLocation("terrafirmacraft", "textures/mob/stand.png");

  private ModelRenderer plume;

  private ModelRenderer plume2;
  private ModelRenderer hornR1;
  private ModelRenderer hornL1;
  private ModelRenderer hornR2;
  private ModelRenderer hornL2;
  private RenderLargeItem standBlockRenderer = new RenderLargeItem();


  public RenderEntityStand() {
    super((ModelBiped)new ModelStand(), 0.5F);
    this.field_77071_a = (ModelBiped)this.field_77045_g;
    this.modelArmorChestplate = (ModelBiped)new ModelStand(1.0F);
    this.modelArmor = (ModelBiped)new ModelStand(0.5F);

    this.plume = new ModelRenderer((ModelBase)this.modelArmorChestplate, 40, 0);
    this.plume2 = new ModelRenderer((ModelBase)this.modelArmorChestplate, 40, 0);
    this.plume.func_78790_a(-1.0F, -6.0F, -10.0F, 2, 6, 10, 0.5F);
    this.plume2.func_78789_a(-1.0F, -6.0F, -10.0F, 2, 6, 10);
    this.plume.func_78793_a(0.0F, -8.0F, 2.0F);
    this.plume2.func_78793_a(0.0F, -2.0F, 4.0F);
    this.plume2.field_78795_f = -1.0471976F;

    this.hornR1 = new ModelRenderer((ModelBase)this.modelArmorChestplate, 40, 0);
    this.hornR1.func_78789_a(-6.0F, -1.5F, -1.5F, 3, 3, 6);
    this.hornL1 = new ModelRenderer((ModelBase)this.modelArmorChestplate, 40, 0);
    this.hornL1.func_78789_a(6.0F, -1.5F, -1.5F, 3, 3, 6);
    this.hornR1.func_78793_a(-6.0F, -6.0F, 5.0F);
    this.hornL1.func_78793_a(6.0F, -6.0F, 8.0F);
    this.hornR1.field_78796_g = -1.5707964F;
    this.hornR1.field_78795_f = -0.2617994F;
    this.hornL1.field_78796_g = 1.5707964F;
    this.hornL1.field_78795_f = -0.2617994F;
    this.hornR2 = new ModelRenderer((ModelBase)this.modelArmorChestplate, 40, 9);
    this.hornR2.func_78789_a(0.0F, 0.0F, -5.0F, 2, 2, 5);
    this.hornR2.func_78793_a(-6.0F, 0.0F, 2.0F);
    this.hornR2.field_78795_f = 1.5707964F;
    this.hornR2.field_78808_h = 0.5235988F;
    this.hornL2 = new ModelRenderer((ModelBase)this.modelArmorChestplate, 40, 9);
    this.hornL2.func_78789_a(0.0F, 0.0F, -5.0F, 2, 2, 5);
    this.hornL2.func_78793_a(7.0F, 0.0F, 2.0F);
    this.hornL2.field_78795_f = 1.5707964F;
    this.hornL2.field_78808_h = -0.5235988F;

    this.modelArmorChestplate.field_78116_c.func_78792_a(this.plume);
    this.modelArmorChestplate.field_78116_c.func_78792_a(this.plume2);
    this.modelArmorChestplate.field_78116_c.func_78792_a(this.hornR1);
    this.modelArmorChestplate.field_78116_c.func_78792_a(this.hornL1);
    this.hornR1.func_78792_a(this.hornR2);
    this.hornL1.func_78792_a(this.hornL2);
    this.hornR1.field_78806_j = false;
    this.hornL1.field_78806_j = false;
    this.plume.field_78806_j = false;
    this.plume2.field_78806_j = false;
  }



  protected int func_77032_a(EntityLivingBase entity, int pass, float delta) {
    return setArmorModelTFC((EntityStand)entity, pass, delta);
  }



  protected ResourceLocation func_110775_a(EntityLiving entity) {
    return TEXTURE;
  }


  public void func_76986_a(Entity e, double par2, double par3, double par4, float par5, float par6) {
    float rotation = (e instanceof EntityStand) ? ((EntityStand)e).getRotation() : 0.0F;
    GL11.glPushMatrix();

    super.func_76986_a(e, par2, par3, par4, rotation, 0.0F);
    GL11.glPopMatrix();
  }



  protected void func_77043_a(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4) {
    super.func_77043_a(par1EntityLivingBase, par2, par3, par4);
  }



  protected void func_77041_b(EntityLivingBase entity, float par2) {
    GL11.glScalef(1.0F, 0.95F, 1.0F);

    int l = 0;
    if (entity instanceof EntityStand) {
      l = ((EntityStand)entity).woodType;
    }
    this.standBlockRenderer.render(entity, new ItemStack(TFCBlocks.armorStand, 1, l), (RenderPlayerEvent.Specials.Pre)null);
  }


  protected int setArmorModelTFC(EntityStand stand, int par2, float par3) {
    ItemStack itemstack = stand.getArmorInSlot(3 - par2);

    if (itemstack != null) {

      Item item = itemstack.func_77973_b();

      if (item instanceof ItemArmor) {

        ItemArmor itemarmor = (ItemArmor)item;
        func_110776_a(RenderBiped.getArmorResource((Entity)stand, itemstack, par2, null));
        ModelBiped modelbiped = (par2 == 2) ? this.modelArmor : this.modelArmorChestplate;
        modelbiped.field_78116_c.field_78806_j = (par2 == 0);
        modelbiped.field_78114_d.field_78806_j = (par2 == 0);
        modelbiped.field_78115_e.field_78806_j = (par2 == 1 || par2 == 2);
        modelbiped.field_78112_f.field_78806_j = (par2 == 1);
        modelbiped.field_78113_g.field_78806_j = (par2 == 1);
        modelbiped.field_78123_h.field_78806_j = (par2 == 2 || par2 == 3);
        modelbiped.field_78124_i.field_78806_j = (par2 == 2 || par2 == 3);
        modelbiped = ForgeHooksClient.getArmorModel((EntityLivingBase)stand, itemstack, par2, modelbiped);
        func_77042_a((ModelBase)modelbiped);
        modelbiped.field_78095_p = this.field_77045_g.field_78095_p;
        modelbiped.field_78093_q = this.field_77045_g.field_78093_q;
        modelbiped.field_78091_s = this.field_77045_g.field_78091_s;
        float f1 = 1.0F;


        int j = itemarmor.func_82814_b(itemstack);
        if (j != -1) {

          float f2 = (j >> 16 & 0xFF) / 255.0F;
          float f3 = (j >> 8 & 0xFF) / 255.0F;
          float f4 = (j & 0xFF) / 255.0F;
          GL11.glColor3f(f1 * f2, f1 * f3, f1 * f4);

          if (itemstack.func_77948_v())
          {
            return 31;
          }

          return 16;
        }

        GL11.glColor3f(f1, f1, f1);

        if (itemstack.func_77948_v())
        {
          return 15;
        }

        return 1;
      }
    }

    return -1;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\RenderEntityStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */