package com.bioxx.tfc.Entities.Mobs;

import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.WorldGen.TFCBiome;
import com.bioxx.tfc.api.Entities.IAnimal;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.Util.Helper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityPheasantTFC
  extends EntityChickenTFC
{
  private boolean wasRoped;

  public EntityPheasantTFC(World par1World) {
    super(par1World);
  }


  public EntityPheasantTFC(World world, double posX, double posY, double posZ, NBTTagCompound genes) {
    super(world, posX, posY, posZ, genes);
  }




  public void addAI() {}




  protected boolean func_70692_ba() {
    return !this.wasRoped;
  }




  public boolean func_70601_bi() {
    int i = MathHelper.func_76128_c(this.field_70165_t);
    int j = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
    int k = MathHelper.func_76128_c(this.field_70161_v);

    float temp = TFC_Climate.getHeightAdjustedTemp(this.field_70170_p, i, this.field_70170_p.func_72825_h(i, k), k);
    float rain = TFC_Climate.getRainfall(this.field_70170_p, i, 150, k);
    if ((temp > 0.0F && temp < 21.0F && rain > 250.0F) || (temp > -20.0F && temp <= 0.0F && rain > 250.0F) || TFC_Climate.isSwamp(this.field_70170_p, i, 150, k))
      return (TFCBiome.isGrass(this.field_70170_p.func_147439_a(i, j - 1, k)) && this.field_70170_p.func_72883_k(i, j, k) > 8 && TFCBiome.getCanSpawnHere((EntityLiving)this));
    return false;
  }



  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(50.0D);
  }







  public void func_70636_d() {
    this.field_70887_j = 10000;
    if (func_110167_bD() && !this.wasRoped)
      this.wasRoped = true;
    super.func_70636_d();
  }






  protected String func_70639_aQ() {
    return func_70631_g_() ? "terrafirmacraft:mob.pheasant.chick.say" : "terrafirmacraft:mob.pheasant.say";
  }






  protected String func_70621_aR() {
    return func_70631_g_() ? null : "terrafirmacraft:mob.pheasant.hurt";
  }






  protected String func_70673_aS() {
    return func_70631_g_() ? null : "terrafirmacraft:mob.pheasant.death";
  }






  public void roosterCrow() {}





  protected void func_70628_a(boolean par1, int par2) {
    float ageMod = TFC_Core.getPercentGrown(this);
    func_145779_a(Items.field_151008_G, (int)(ageMod * getSizeMod() * (5 + this.field_70146_Z.nextInt(10))));

    if (isAdult()) {

      float foodWeight = ageMod * getSizeMod() * 40.0F;
      TFC_Core.animalDropMeat((Entity)this, TFCItems.chickenRaw, foodWeight);
      func_145779_a(Items.field_151103_aS, this.field_70146_Z.nextInt(2) + 1);
    }
  }



  public boolean canMateWith(IAnimal animal) {
    return false;
  }



  public EntityAgeable createChildTFC(EntityAgeable entityageable) {
    if (entityageable instanceof IAnimal) {

      IAnimal animal = (IAnimal)entityageable;
      NBTTagCompound nbt = new NBTTagCompound();
      nbt.func_74776_a("m_size", animal.getSizeMod());
      nbt.func_74776_a("f_size", animal.getSizeMod());
      return (EntityAgeable)new EntityPheasantTFC(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, nbt);
    }

    return null;
  }



  public void func_70037_a(NBTTagCompound nbt) {
    super.func_70037_a(nbt);
    this.wasRoped = nbt.func_74767_n("wasRoped");
  }



  public void func_70014_b(NBTTagCompound nbt) {
    super.func_70014_b(nbt);
    nbt.func_74757_a("wasRoped", this.wasRoped);
  }



  public int getAnimalTypeID() {
    return Helper.stringToInt("pheasant");
  }


  public boolean trySetName(String name, EntityPlayer player) {
    if (checkFamiliarity(IAnimal.InteractionEnum.NAME, player)) {

      func_94058_c(name);
      return true;
    }
    func_85030_a("terrafirmacraft:mob.pheasant.say", 6.0F, this.field_70146_Z.nextFloat() / 2.0F + (func_70631_g_() ? 1.25F : 0.75F));
    return false;
  }


  public boolean checkFamiliarity(IAnimal.InteractionEnum interaction, EntityPlayer player) {
    boolean flag = false;
    switch (interaction) {
      case NAME:
        flag = (getFamiliarity() > 40);
        break;
    }

    if (!flag && !player.field_70170_p.field_72995_K) {
      TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("entity.notFamiliar", new Object[0]));
    }
    return flag;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\Mobs\EntityPheasantTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */