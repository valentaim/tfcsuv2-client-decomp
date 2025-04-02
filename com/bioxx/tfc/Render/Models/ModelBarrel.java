package com.bioxx.tfc.Render.Models;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.AxisAlignedBB;














@SideOnly(Side.CLIENT)
public class ModelBarrel
  extends ModelBase
{
  public ModelRenderer barrel;
  public ModelRenderer barrel2;
  public ModelRenderer barrel3;
  public ModelRenderer chestBelow;
  public ModelRenderer chestKnob;

  public ModelBarrel(int type) {
    this.barrel = (new ModelRenderer(this, 0 + type * 56, 0)).func_78787_b(952, 76);
    this.barrel2 = (new ModelRenderer(this, 0 + type * 56, 44)).func_78787_b(952, 76);
    this.barrel3 = (new ModelRenderer(this, 0 + type * 56, 44)).func_78787_b(952, 76);

    this.barrel.func_78793_a(16.0F, 16.0F, 0.0F);
    this.barrel2.func_78793_a(0.0F, 0.0F, 0.0F);
    this.barrel3.func_78793_a(0.0F, 0.0F, 0.0F);


    this.barrel.func_78790_a(1.0F, 0.0F, 1.0F, 14, 16, 14, 0.0F);
    this.barrel.field_78808_h = -3.1415927F;
    this.barrel2.func_78790_a(2.0F, 0.0F, 2.0F, 12, 16, 0, 0.0F);
    this.barrel2.func_78790_a(2.0F, 0.0F, 2.0F, 0, 16, 12, 0.0F);
    this.barrel2.func_78790_a(2.0F, 0.0F, 14.0F, 12, 16, 0, 0.0F);
    this.barrel2.func_78790_a(14.0F, 0.0F, 2.0F, 0, 16, 12, 0.0F);
    this.barrel3.func_78790_a(2.0F, 2.0F, 2.0F, 12, 12, 12, 0.0F);
  }




















  public void drawBox(AxisAlignedBB par1AxisAlignedBB) {
    Tessellator var2 = Tessellator.field_78398_a;


    var2.func_78371_b(7);
    var2.func_78377_a(par1AxisAlignedBB.field_72340_a + 0.1D, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c + 0.1D);
    var2.func_78377_a(par1AxisAlignedBB.field_72336_d - 0.1D, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c + 0.1D);
    var2.func_78377_a(par1AxisAlignedBB.field_72336_d - 0.1D, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f - 0.1D);
    var2.func_78377_a(par1AxisAlignedBB.field_72340_a + 0.1D, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f - 0.1D);
    var2.func_78381_a();


    var2.func_78371_b(7);
    var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
    var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
    var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
    var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
    var2.func_78381_a();


    var2.func_78371_b(7);
    var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
    var2.func_78377_a(par1AxisAlignedBB.field_72340_a + 0.1D, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c + 0.1D);
    var2.func_78377_a(par1AxisAlignedBB.field_72340_a + 0.1D, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f - 0.1D);
    var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
    var2.func_78381_a();


    var2.func_78371_b(7);
    var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
    var2.func_78377_a(par1AxisAlignedBB.field_72336_d - 0.1D, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f - 0.1D);
    var2.func_78377_a(par1AxisAlignedBB.field_72336_d - 0.1D, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c + 0.1D);
    var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
    var2.func_78381_a();


    var2.func_78371_b(7);
    var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
    var2.func_78377_a(par1AxisAlignedBB.field_72340_a + 0.1D, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c + 0.1D);
    var2.func_78377_a(par1AxisAlignedBB.field_72336_d - 0.1D, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c + 0.1D);
    var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
    var2.func_78381_a();


    var2.func_78371_b(7);
    var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
    var2.func_78377_a(par1AxisAlignedBB.field_72336_d - 0.1D, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f - 0.1D);
    var2.func_78377_a(par1AxisAlignedBB.field_72340_a + 0.1D, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f - 0.1D);
    var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
    var2.func_78381_a();
  }







  public void renderBarrel() {
    this.barrel.func_78785_a(0.0625F);
    this.barrel2.func_78785_a(0.0625F);
    this.barrel3.func_78785_a(0.0625F);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Models\ModelBarrel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */