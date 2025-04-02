package com.bioxx.tfc.Render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;


public class RenderBlocksFixUV
  extends RenderBlocks
{
  public RenderBlocksFixUV(RenderBlocks old) {
    this.field_147845_a = old.field_147845_a;
  }


  public void update(RenderBlocks old) {
    this.field_147845_a = old.field_147845_a;
  }



  public void func_147761_c(Block par1Block, double par2, double par4, double par6, IIcon par8Icon) {
    Tessellator tessellator = Tessellator.field_78398_a;

    if (func_147744_b())
    {
      par8Icon = this.field_147840_d;
    }

    double d3 = par8Icon.func_94214_a(16.0D - this.field_147859_h * 16.0D);
    double d4 = par8Icon.func_94214_a(16.0D - this.field_147861_i * 16.0D);
    double d5 = par8Icon.func_94207_b(16.0D - this.field_147857_k * 16.0D);
    double d6 = par8Icon.func_94207_b(16.0D - this.field_147855_j * 16.0D);


    if (this.field_147842_e) {

      double d = d3;
      d3 = d4;
      d4 = d;
    }

    if (this.field_147859_h < 0.0D || this.field_147861_i > 1.0D) {

      d3 = par8Icon.func_94209_e();
      d4 = par8Icon.func_94212_f();
    }

    if (this.field_147855_j < 0.0D || this.field_147857_k > 1.0D) {

      d5 = par8Icon.func_94206_g();
      d6 = par8Icon.func_94210_h();
    }

    double d7 = d4;
    double d8 = d3;
    double d9 = d5;
    double d10 = d6;

    if (this.field_147875_q == 2) {

      d3 = par8Icon.func_94214_a(this.field_147855_j * 16.0D);
      d5 = par8Icon.func_94207_b(16.0D - this.field_147859_h * 16.0D);
      d4 = par8Icon.func_94214_a(this.field_147857_k * 16.0D);
      d6 = par8Icon.func_94207_b(16.0D - this.field_147861_i * 16.0D);
      d9 = d5;
      d10 = d6;
      d7 = d3;
      d8 = d4;
      d5 = d6;
      d6 = d9;
    }
    else if (this.field_147875_q == 1) {

      d3 = par8Icon.func_94214_a(16.0D - this.field_147857_k * 16.0D);
      d5 = par8Icon.func_94207_b(this.field_147861_i * 16.0D);
      d4 = par8Icon.func_94214_a(16.0D - this.field_147855_j * 16.0D);
      d6 = par8Icon.func_94207_b(this.field_147859_h * 16.0D);
      d7 = d4;
      d8 = d3;
      d3 = d4;
      d4 = d8;
      d9 = d6;
      d10 = d5;
    }
    else if (this.field_147875_q == 3) {

      d3 = par8Icon.func_94214_a(16.0D - this.field_147859_h * 16.0D);
      d4 = par8Icon.func_94214_a(16.0D - this.field_147861_i * 16.0D);
      d5 = par8Icon.func_94207_b(this.field_147857_k * 16.0D);
      d6 = par8Icon.func_94207_b(this.field_147855_j * 16.0D);
      d7 = d4;
      d8 = d3;
      d9 = d5;
      d10 = d6;
    }

    double d11 = par2 + this.field_147859_h;
    double d12 = par2 + this.field_147861_i;
    double d13 = par4 + this.field_147855_j;
    double d14 = par4 + this.field_147857_k;
    double d15 = par6 + this.field_147851_l;

    if (this.field_147863_w) {

      tessellator.func_78386_a(this.field_147872_ap, this.field_147846_at, this.field_147854_ax);
      tessellator.func_78380_c(this.field_147864_al);
      tessellator.func_78374_a(d11, d14, d15, d3, d5);
      tessellator.func_78386_a(this.field_147848_as, this.field_147856_aw, this.field_147833_aA);
      tessellator.func_78380_c(this.field_147870_ao);
      tessellator.func_78374_a(d12, d14, d15, d7, d9);
      tessellator.func_78386_a(this.field_147850_ar, this.field_147858_av, this.field_147839_az);
      tessellator.func_78380_c(this.field_147876_an);
      tessellator.func_78374_a(d12, d13, d15, d4, d6);
      tessellator.func_78386_a(this.field_147852_aq, this.field_147860_au, this.field_147841_ay);
      tessellator.func_78380_c(this.field_147874_am);
      tessellator.func_78374_a(d11, d13, d15, d8, d10);



    }
    else {



      tessellator.func_78374_a(d11, d14, d15, d3, d5);
      tessellator.func_78374_a(d12, d14, d15, d7, d9);
      tessellator.func_78374_a(d12, d13, d15, d4, d6);
      tessellator.func_78374_a(d11, d13, d15, d8, d10);
    }
  }



  public void func_147764_f(Block par1Block, double par2, double par4, double par6, IIcon par8Icon) {
    Tessellator tessellator = Tessellator.field_78398_a;

    if (func_147744_b())
    {
      par8Icon = this.field_147840_d;
    }

    double d3 = par8Icon.func_94214_a(16.0D - this.field_147851_l * 16.0D);
    double d4 = par8Icon.func_94214_a(16.0D - this.field_147853_m * 16.0D);
    double d5 = par8Icon.func_94207_b(16.0D - this.field_147857_k * 16.0D);
    double d6 = par8Icon.func_94207_b(16.0D - this.field_147855_j * 16.0D);


    if (this.field_147842_e) {

      double d = d3;
      d3 = d4;
      d4 = d;
    }

    if (this.field_147851_l < 0.0D || this.field_147853_m > 1.0D) {

      d3 = par8Icon.func_94209_e();
      d4 = par8Icon.func_94212_f();
    }

    if (this.field_147855_j < 0.0D || this.field_147857_k > 1.0D) {

      d5 = par8Icon.func_94206_g();
      d6 = par8Icon.func_94210_h();
    }

    double d7 = d4;
    double d8 = d3;
    double d9 = d5;
    double d10 = d6;

    if (this.field_147871_s == 2) {

      d3 = par8Icon.func_94214_a(this.field_147855_j * 16.0D);
      d5 = par8Icon.func_94207_b(16.0D - this.field_147851_l * 16.0D);
      d4 = par8Icon.func_94214_a(this.field_147857_k * 16.0D);
      d6 = par8Icon.func_94207_b(16.0D - this.field_147853_m * 16.0D);
      d9 = d5;
      d10 = d6;
      d7 = d3;
      d8 = d4;
      d5 = d6;
      d6 = d9;
    }
    else if (this.field_147871_s == 1) {

      d3 = par8Icon.func_94214_a(16.0D - this.field_147857_k * 16.0D);
      d5 = par8Icon.func_94207_b(this.field_147853_m * 16.0D);
      d4 = par8Icon.func_94214_a(16.0D - this.field_147855_j * 16.0D);
      d6 = par8Icon.func_94207_b(this.field_147851_l * 16.0D);
      d7 = d4;
      d8 = d3;
      d3 = d4;
      d4 = d8;
      d9 = d6;
      d10 = d5;
    }
    else if (this.field_147871_s == 3) {

      d3 = par8Icon.func_94214_a(16.0D - this.field_147851_l * 16.0D);
      d4 = par8Icon.func_94214_a(16.0D - this.field_147853_m * 16.0D);
      d5 = par8Icon.func_94207_b(this.field_147857_k * 16.0D);
      d6 = par8Icon.func_94207_b(this.field_147855_j * 16.0D);
      d7 = d4;
      d8 = d3;
      d9 = d5;
      d10 = d6;
    }

    double d11 = par2 + this.field_147861_i;
    double d12 = par4 + this.field_147855_j;
    double d13 = par4 + this.field_147857_k;
    double d14 = par6 + this.field_147851_l;
    double d15 = par6 + this.field_147853_m;

    if (this.field_147863_w) {

      tessellator.func_78386_a(this.field_147872_ap, this.field_147846_at, this.field_147854_ax);
      tessellator.func_78380_c(this.field_147864_al);
      tessellator.func_78374_a(d11, d13, d15, d7, d9);
      tessellator.func_78386_a(this.field_147848_as, this.field_147856_aw, this.field_147833_aA);
      tessellator.func_78380_c(this.field_147870_ao);
      tessellator.func_78374_a(d11, d12, d15, d4, d6);
      tessellator.func_78386_a(this.field_147850_ar, this.field_147858_av, this.field_147839_az);
      tessellator.func_78380_c(this.field_147876_an);
      tessellator.func_78374_a(d11, d12, d14, d8, d10);
      tessellator.func_78386_a(this.field_147852_aq, this.field_147860_au, this.field_147841_ay);
      tessellator.func_78380_c(this.field_147874_am);
      tessellator.func_78374_a(d11, d13, d14, d3, d5);
    }
    else {

      tessellator.func_78374_a(d11, d13, d15, d7, d9);
      tessellator.func_78374_a(d11, d12, d15, d4, d6);
      tessellator.func_78374_a(d11, d12, d14, d8, d10);
      tessellator.func_78374_a(d11, d13, d14, d3, d5);
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\RenderBlocksFixUV.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */