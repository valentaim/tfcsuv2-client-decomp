package com.bioxx.tfc.TileEntities;

import net.minecraft.block.material.Material;
import net.minecraft.nbt.NBTTagCompound;

public class TEPartial
  extends NetworkTileEntity {
  public short typeID = -1;


  public byte metaID;


  public byte material;

  public long extraData;


  public boolean canUpdate() {
    return false;
  }


  public Material getMaterial() {
    switch (this.material) {

      case 1:
        return Material.field_151578_c;
      case 2:
        return Material.field_151575_d;
      case 3:
        return Material.field_151576_e;
      case 4:
        return Material.field_151573_f;
      case 5:
        return Material.field_151586_h;
      case 6:
        return Material.field_151587_i;
      case 7:
        return Material.field_151584_j;
      case 8:
        return Material.field_151585_k;
      case 9:
        return Material.field_151582_l;
      case 10:
        return Material.field_151583_m;
      case 11:
        return Material.field_151580_n;
      case 12:
        return Material.field_151581_o;
      case 13:
        return Material.field_151595_p;
      case 14:
        return Material.field_151594_q;
      case 15:
        return Material.field_151592_s;
      case 16:
        return Material.field_151591_t;
      case 17:
        return Material.field_151590_u;
      case 19:
        return Material.field_151588_w;
      case 20:
        return Material.field_151597_y;
      case 21:
        return Material.field_151596_z;
      case 22:
        return Material.field_151570_A;
      case 23:
        return Material.field_151571_B;
      case 24:
        return Material.field_151572_C;
      case 25:
        return Material.field_151566_D;
      case 26:
        return Material.field_151567_E;
      case 27:
        return Material.field_151568_F;
      case 28:
        return Material.field_151569_G;
      case 29:
        return Material.field_76233_E;
    }
    return Material.field_151577_b;
  }



  public void setMaterial(Material mat) {
    if (mat == Material.field_151578_c) { this.material = 1; }
    else if (mat == Material.field_151575_d)

    { this.material = 2; }

    else if (mat == Material.field_151576_e)

    { this.material = 3; }

    else if (mat == Material.field_151573_f)

    { this.material = 4; }

    else if (mat == Material.field_151586_h)

    { this.material = 5; }

    else if (mat == Material.field_151587_i)

    { this.material = 6; }

    else if (mat == Material.field_151584_j)

    { this.material = 7; }

    else if (mat == Material.field_151585_k)

    { this.material = 8; }

    else if (mat == Material.field_151582_l)

    { this.material = 9; }

    else if (mat == Material.field_151583_m)

    { this.material = 10; }

    else if (mat == Material.field_151580_n)

    { this.material = 11; }

    else if (mat == Material.field_151581_o)

    { this.material = 12; }

    else if (mat == Material.field_151595_p)

    { this.material = 13; }

    else if (mat == Material.field_151594_q)

    { this.material = 14; }

    else if (mat == Material.field_151592_s)

    { this.material = 15; }

    else if (mat == Material.field_151591_t)

    { this.material = 16; }

    else if (mat == Material.field_151590_u)

    { this.material = 17; }

    else if (mat == Material.field_151588_w)

    { this.material = 19; }

    else if (mat == Material.field_151597_y)

    { this.material = 20; }

    else if (mat == Material.field_151596_z)

    { this.material = 21; }

    else if (mat == Material.field_151570_A)

    { this.material = 22; }

    else if (mat == Material.field_151571_B)

    { this.material = 23; }

    else if (mat == Material.field_151572_C)

    { this.material = 24; }

    else if (mat == Material.field_151566_D)

    { this.material = 25; }

    else if (mat == Material.field_151567_E)

    { this.material = 26; }

    else if (mat == Material.field_151568_F)

    { this.material = 27; }

    else if (mat == Material.field_151569_G)

    { this.material = 28; }

    else if (mat == Material.field_76233_E)

    { this.material = 29; }

    else if (mat == Material.field_151577_b)

    { this.material = 0; }

  }






  public void func_145839_a(NBTTagCompound par1NBTTagCompound) {
    super.func_145839_a(par1NBTTagCompound);
    this.metaID = par1NBTTagCompound.func_74771_c("metaID");
    this.typeID = par1NBTTagCompound.func_74765_d("typeID");
    this.material = par1NBTTagCompound.func_74771_c("material");
    this.extraData = par1NBTTagCompound.func_74763_f("extraData");
  }






  public void func_145841_b(NBTTagCompound par1NBTTagCompound) {
    super.func_145841_b(par1NBTTagCompound);
    par1NBTTagCompound.func_74777_a("typeID", this.typeID);
    par1NBTTagCompound.func_74774_a("metaID", this.metaID);
    par1NBTTagCompound.func_74774_a("material", this.material);
    par1NBTTagCompound.func_74772_a("extraData", this.extraData);
  }



  public void handleInitPacket(NBTTagCompound nbt) {
    this.metaID = nbt.func_74771_c("metaID");
    this.typeID = nbt.func_74765_d("typeID");
    this.material = nbt.func_74771_c("material");
    this.extraData = nbt.func_74763_f("extraData");
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }



  public void handleDataPacket(NBTTagCompound nbt) {
    this.extraData = nbt.func_74763_f("extraData");
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }



  public void createDataNBT(NBTTagCompound nbt) {
    nbt.func_74772_a("extraData", this.extraData);
  }



  public void createInitNBT(NBTTagCompound nbt) {
    nbt.func_74777_a("typeID", this.typeID);
    nbt.func_74774_a("metaID", this.metaID);
    nbt.func_74774_a("material", this.material);
    nbt.func_74772_a("extraData", this.extraData);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEPartial.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */