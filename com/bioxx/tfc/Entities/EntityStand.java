package com.bioxx.tfc.Entities;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Items.ItemTFCArmor;
import com.bioxx.tfc.api.TFCBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityStand extends EntityLiving {
  private static int defaultArmorLength = 4;
  private static int defaultEquipableLength = TFC_Core.getExtraEquipInventorySize();

  private ItemStack[] armor;
  private ItemStack[] equipable;
  private float rotation;
  public int woodType;

  public EntityStand(World par1World) {
    super(par1World);
    func_70105_a(1.0F, 2.0F);

    this.field_70158_ak = true;
    this.armor = new ItemStack[defaultArmorLength];
    this.equipable = new ItemStack[defaultEquipableLength];
  }



  public EntityStand(World par1World, float rotation, int type) {
    this(par1World);
    this.rotation = rotation;
    this.woodType = type;
  }



  protected void func_70619_bc() {
    this.field_70708_bq++;
  }


  public boolean func_85032_ar() {
    return false;
  }



  protected void func_70626_be() {
    this.field_70702_br = 0.0F;
    this.field_70701_bs = 0.0F;
    func_70623_bb();
  }



  protected void func_70088_a() {
    super.func_70088_a();
    int start = 20;
    int i;
    for (i = 0; i < defaultArmorLength; i++) {
      this.field_70180_af.func_82709_a(start + i, 5);
    }
    for (i = 0; i < defaultEquipableLength; i++) {
      this.field_70180_af.func_82709_a(start + i + defaultArmorLength, 5);
    }
    this.field_70180_af.func_75682_a(start + defaultEquipableLength + defaultArmorLength, new Float(1.0F));
    this.field_70180_af.func_75682_a(start + defaultEquipableLength + defaultArmorLength + 1, Integer.valueOf(0));
  }


  public boolean func_70104_M() {
    return true;
  }


  public boolean func_70692_ba() {
    return false;
  }


  public void func_70636_d() {
    super.func_70636_d();
    syncData();
  }


  public void syncData() {
    if (this.field_70180_af != null)
    {
      if (this.field_70170_p.field_72995_K) {

        int start = 20; int i;
        for (i = 0; i < defaultArmorLength; i++) {
          this.armor[i] = this.field_70180_af.func_82710_f(start + i);
        }
        for (i = 0; i < defaultEquipableLength; i++) {
          this.equipable[i] = this.field_70180_af.func_82710_f(start + i + defaultArmorLength);
        }
        this.rotation = this.field_70180_af.func_111145_d(start + defaultEquipableLength + defaultArmorLength);
        this.woodType = this.field_70180_af.func_75679_c(start + defaultEquipableLength + defaultArmorLength + 1);
      }
      else {

        int start = 20;
        int i;
        for (i = 0; i < defaultArmorLength; i++) {
          this.field_70180_af.func_75692_b(start + i, this.armor[i]);
        }
        for (i = 0; i < defaultEquipableLength; i++) {
          this.field_70180_af.func_75692_b(start + i + defaultArmorLength, this.equipable[i]);
        }

        this.field_70180_af.func_75692_b(start + defaultEquipableLength + defaultArmorLength, Float.valueOf(this.rotation));
        this.field_70180_af.func_75692_b(start + defaultEquipableLength + defaultArmorLength + 1, Integer.valueOf(this.woodType));
      }
    }
  }



  public void func_70071_h_() {
    if (this.field_70170_p.func_72872_a(getClass(), this.field_70121_D).size() > 1) {
      func_70106_y();
    }


    double tempX = this.field_70165_t;

    double tempZ = this.field_70161_v;
    super.func_70071_h_();
    if (this.field_70170_p.field_72995_K) {
      func_70105_a(0.125F, 2.0F);
    } else {
      func_70105_a(0.1F, 2.0F);
    }
    func_70012_b(tempX, this.field_70163_u, tempZ, this.rotation, 0.0F);

    func_70101_b(this.rotation, 0.0F);
    this.field_70761_aq = this.rotation;
    this.field_70741_aB = this.rotation;
    this.field_70764_aw = this.rotation;
    this.field_70123_F = false;
    this.field_70754_ba = 0.0F;
    this.field_70721_aZ = 0.0F;
    this.field_70712_bm = this.rotation;
    this.field_70733_aJ = 0.0F;
    this.field_110158_av = 0;
  }






  protected void func_70628_a(boolean par1, int par2) {
    if (!this.field_70170_p.field_72995_K) {
      int i; for (i = 0; i < this.armor.length; i++) {
        if (this.armor[i] != null) {
          ItemStack is = new ItemStack(this.armor[i].func_77973_b(), 1, this.armor[i].func_77960_j());
          func_70099_a(is, 0.0F);
        }
      }
      for (i = 0; i < this.equipable.length; i++) {
        if (this.equipable[i] != null) {
          ItemStack is = new ItemStack(this.equipable[i].func_77973_b(), 1, this.equipable[i].func_77960_j());
          func_70099_a(is, 0.0F);
        }
      }
      Block blockToDrop = (this.woodType < 16) ? TFCBlocks.armorStand : TFCBlocks.armorStand2;
      func_70099_a(new ItemStack(blockToDrop, 1, this.woodType % 16), 0.0F);
    }
  }










  private Vec3 getPlayerLook(EntityLivingBase entity, float mult) {
    if (mult == 1.0F) {

      float f7 = MathHelper.func_76134_b(-entity.field_70177_z * 0.017453292F - 3.1415927F);
      float f8 = MathHelper.func_76126_a(-entity.field_70177_z * 0.017453292F - 3.1415927F);
      float f9 = -MathHelper.func_76134_b(-entity.field_70125_A * 0.017453292F);
      float f10 = MathHelper.func_76126_a(-entity.field_70125_A * 0.017453292F);
      return Vec3.func_72443_a((f8 * f9), f10, (f7 * f9));
    }


    float f1 = entity.field_70127_C + (entity.field_70125_A - entity.field_70127_C) * mult;
    float f2 = entity.field_70126_B + (entity.field_70177_z - entity.field_70126_B) * mult;
    float f3 = MathHelper.func_76134_b(-f2 * 0.017453292F - 3.1415927F);
    float f4 = MathHelper.func_76126_a(-f2 * 0.017453292F - 3.1415927F);
    float f5 = -MathHelper.func_76134_b(-f1 * 0.017453292F);
    float f6 = MathHelper.func_76126_a(-f1 * 0.017453292F);
    return Vec3.func_72443_a((f4 * f5), f6, (f3 * f5));
  }



  public boolean func_70085_c(EntityPlayer ep) {
    if (!this.field_70170_p.field_72995_K) {
      ItemStack is = ep.func_71045_bC();
      if (is != null) {
        if (is.func_77973_b() instanceof ItemTFCArmor) {
          ItemTFCArmor tempArmor = (ItemTFCArmor)is.func_77973_b();
          int slot = tempArmor.getBodyPart();
          if (this.armor[slot] == null) {
            setArmor(slot, is);
            is.field_77994_a--;
          }
          ep.func_70062_b(0, is);
        }
      } else {

        Vec3 hitVec = getPlayerLook((EntityLivingBase)ep, 1.0F);
        double angleTan = hitVec.field_72448_b / Math.sqrt(hitVec.field_72450_a * hitVec.field_72450_a + hitVec.field_72449_c * hitVec.field_72449_c);

        double xzDist = Math.sqrt(Math.pow(ep.field_70165_t - this.field_70165_t, 2.0D) + Math.pow(ep.field_70161_v - this.field_70161_v, 2.0D));
        double yLevel = angleTan * xzDist + ep.eyeHeight + ep.field_70163_u;
        double y = yLevel - this.field_70163_u;

        int slot = -1;
        if (y >= 0.0D && y < 0.3D) {
          slot = 0;
        }
        else if (y >= 0.3D && y < 1.0D) {
          slot = 1;
        }
        else if (y >= 1.0D && y < 1.4D) {
          slot = 2;
        }
        else if (y >= 1.4D && y < 2.0D) {
          slot = 3;
        }


        if (slot != -1) {
          ItemStack i = this.armor[slot];
          this.armor[slot] = null;
          giveItemToPlayer(this.field_70170_p, ep, i);
        }
      }
    }
    return true;
  }

  public void giveItemToPlayer(World world, EntityPlayer ep, ItemStack is) {
    if (world != null && ep != null && is != null) {
      is.field_77994_a = 1;
      EntityItem ei = new EntityItem(world, ep.field_70165_t, ep.field_70163_u, ep.field_70161_v, is);
      world.func_72838_d((Entity)ei);
    }
  }



  public void func_70037_a(NBTTagCompound nbttagcompound) {
    super.func_70037_a(nbttagcompound);



    this.rotation = nbttagcompound.func_74760_g("Rotation");
    this.woodType = nbttagcompound.func_74762_e("Wood");




    if (nbttagcompound.func_150297_b("Armor", 9)) {

      NBTTagList nbttaglist = nbttagcompound.func_150295_c("Armor", 10);

      for (int i = 0; i < defaultArmorLength; i++)
      {
        this.armor[i] = ItemStack.func_77949_a(nbttaglist.func_150305_b(i));
      }
    }

    if (nbttagcompound.func_150297_b("Equipable", 9)) {

      NBTTagList nbttaglist = nbttagcompound.func_150295_c("Equipable", 10);

      for (int i = 0; i < defaultEquipableLength; i++)
      {
        this.equipable[i] = ItemStack.func_77949_a(nbttaglist.func_150305_b(i));
      }
    }
  }

  public float getRotation() {
    return this.rotation;
  }



  public void func_70014_b(NBTTagCompound nbttagcompound) {
    super.func_70014_b(nbttagcompound);




    nbttagcompound.func_74776_a("Rotation", this.rotation);
    nbttagcompound.func_74768_a("Wood", this.woodType);

    NBTTagList nbttaglist = new NBTTagList();

    int i;
    for (i = 0; i < defaultArmorLength; i++) {

      NBTTagCompound nbttagcompound1 = new NBTTagCompound();

      if (this.armor[i] != null)
      {
        this.armor[i].func_77955_b(nbttagcompound1);
      }

      nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
    }

    nbttagcompound.func_74782_a("Armor", (NBTBase)nbttaglist);

    nbttaglist = new NBTTagList();
    for (i = 0; i < defaultEquipableLength; i++) {

      NBTTagCompound nbttagcompound1 = new NBTTagCompound();

      if (this.equipable[i] != null)
      {
        this.equipable[i].func_77955_b(nbttagcompound1);
      }

      nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
    }

    nbttagcompound.func_74782_a("Equipable", (NBTBase)nbttaglist);
  }



  public ItemStack func_70694_bm() {
    return null;
  }



  public ItemStack getEquipableInSlot(int i) {
    if (this.equipable != null && defaultEquipableLength > i) {
      return this.equipable[i];
    }
    return null;
  }


  public ItemStack getArmorInSlot(int i) {
    if (this.armor != null && defaultArmorLength > i) {
      return this.armor[i];
    }
    return null;
  }


  public void setArmor(int i, ItemStack itemstack) {
    if (this.armor != null && defaultArmorLength > i) {
      this.armor[i] = itemstack;
    }
  }




  public void setEquipable(int i, ItemStack itemstack) {
    if (this.equipable != null && defaultEquipableLength > i)
      this.equipable[i] = itemstack;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\EntityStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */