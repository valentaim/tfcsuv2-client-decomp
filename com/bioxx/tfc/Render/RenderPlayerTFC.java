package com.bioxx.tfc.Render;

import com.bioxx.tfc.Entities.EntityStand;
import com.bioxx.tfc.api.TFCItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;
import org.lwjgl.opengl.GL11;





@SideOnly(Side.CLIENT)
public class RenderPlayerTFC
  extends RenderPlayer
{
  private ModelBiped modelArmorChestplate;
  private ModelBiped modelArmor;
  public static String[] armorFilenamePrefix = new String[] { "cloth", "chain", "iron", "diamond", "gold" };

  public static final float NAME_TAG_RANGE = 64.0F;

  public static final float NAME_TAG_RANGE_SNEAK = 32.0F;

  private ModelRenderer plume;
  private ModelRenderer plume2;
  private ModelRenderer hornR1;
  private ModelRenderer hornL1;
  private ModelRenderer hornR2;
  private ModelRenderer hornL2;

  public RenderPlayerTFC() {
    this.field_77109_a = (ModelBiped)this.field_77045_g;
    this.modelArmorChestplate = new ModelBiped(1.0F);
    this.modelArmor = new ModelBiped(0.5F);


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
  }



  protected int func_77032_a(EntityLivingBase par1EntityLivingBase, int par2, float par3) {
    if (par1EntityLivingBase instanceof EntityStand)
    {
      return setArmorModelTFC((EntityStand)par1EntityLivingBase, par2, par3);
    }
    return func_77032_a((AbstractClientPlayer)par1EntityLivingBase, par2, par3);
  }


  protected int setArmorModelTFC(EntityStand stand, int par2, float par3) {
    ItemStack itemstack = stand.func_71124_b(3 - par2);

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
































  protected int func_77032_a(AbstractClientPlayer par1AbstractClientPlayer, int slotIndex, float partialTick) {
    ItemStack itemstack = par1AbstractClientPlayer.field_71071_by.func_70440_f(3 - slotIndex);

    this.plume.field_78806_j = false;
    this.plume2.field_78806_j = false;
    this.hornR1.field_78806_j = false;
    this.hornL1.field_78806_j = false;
    if (itemstack != null) {

      Item item = itemstack.func_77973_b();

      if (item instanceof ItemArmor) {

        ItemArmor itemarmor = (ItemArmor)item;
        func_110776_a(RenderBiped.getArmorResource((Entity)par1AbstractClientPlayer, itemstack, slotIndex, null));
        ModelBiped modelbiped = (slotIndex == 2) ? this.modelArmor : this.modelArmorChestplate;
        modelbiped.field_78116_c.field_78806_j = (slotIndex == 0);
        this.plume.field_78806_j = false;
        this.plume2.field_78806_j = false;
        this.hornR1.field_78806_j = false;
        this.hornL1.field_78806_j = false;
        modelbiped.field_78114_d
          .field_78806_j = (slotIndex == 0 && itemstack.func_77973_b() != TFCItems.bronzeHelmet && itemstack.func_77973_b() != TFCItems.wroughtIronHelmet);
        modelbiped.field_78115_e.field_78806_j = (slotIndex == 1 || slotIndex == 2);
        modelbiped.field_78112_f.field_78806_j = (slotIndex == 1);
        modelbiped.field_78113_g.field_78806_j = (slotIndex == 1);
        modelbiped.field_78123_h.field_78806_j = (slotIndex == 2 || slotIndex == 3);
        modelbiped.field_78124_i.field_78806_j = (slotIndex == 2 || slotIndex == 3);
        func_77042_a((ModelBase)modelbiped);

        modelbiped.field_78095_p = this.field_77045_g.field_78095_p;
        modelbiped.field_78093_q = this.field_77045_g.field_78093_q;
        modelbiped.field_78091_s = this.field_77045_g.field_78091_s;

        float f1 = 1.0F;

        if (itemarmor.func_82812_d() == ItemArmor.ArmorMaterial.CLOTH) {

          int j = itemarmor.func_82814_b(itemstack);
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


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\RenderPlayerTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */