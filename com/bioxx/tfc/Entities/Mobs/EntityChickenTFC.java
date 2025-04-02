package com.bioxx.tfc.Entities.Mobs;

import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.Entities.AI.EntityAIAvoidEntityTFC;
import com.bioxx.tfc.Entities.AI.EntityAIFindNest;
import com.bioxx.tfc.Food.ItemFoodTFC;
import com.bioxx.tfc.WorldGen.TFCBiome;
import com.bioxx.tfc.api.Entities.IAnimal;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.TFCOptions;
import com.bioxx.tfc.api.Util.Helper;
import fof.tfcsu.utils.ExpBonus;
import java.nio.ByteBuffer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIEatGrass;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;




public class EntityChickenTFC
  extends EntityChicken
  implements IAnimal
{
  private static final float DIMORPHISM = 0.0606F;
  private static final int DEGREE_OF_DIVERSION = 2;
  protected static final int FAMILIARITY_CAP = 45;
  private static final int EGG_TIME = 24000;
  private final EntityAIEatGrass aiEatGrass = new EntityAIEatGrass((EntityLiving)this);
  private int sex;
  private int hunger;
  private float sizeMod;
  private float strengthMod;
  private float aggressionMod = 1.0F;

  private float obedienceMod = 1.0F;

  private boolean inLove;

  private long nextEgg;

  private int familiarity;

  private long lastFamiliarityUpdate;

  private boolean familiarizedToday;

  public EntityChickenTFC(World par1World) {
    super(par1World);
    func_70105_a(0.3F, 0.7F);
    this.field_70887_j = 9999;
    this.nextEgg = TFC_Time.getTotalTicks() + 24000L;
    this.hunger = 168000;
    this.sex = this.field_70146_Z.nextInt(2);

    this.field_70714_bg.field_75782_a.clear();
    this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAIPanic((EntityCreature)this, 1.4D));
    this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIFollowParent((EntityAnimal)this, 1.1D));
    this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0D));
    this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityChickenTFC.class, 6.0F));
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAvoidEntityTFC((EntityCreature)this, EntityWolfTFC.class, 8.0F, 0.5D, 0.699999988079071D));
    this.field_70714_bg.func_75776_a(7, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
    this.field_70714_bg.func_75776_a(6, (EntityAIBase)this.aiEatGrass);
    addAI();

    this.sizeMod = (float)Math.sqrt((((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(30) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F) * (1.0F - 0.0606F * this.sex)));
    this.strengthMod = (float)Math.sqrt(((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(20) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + this.sizeMod));
    this.aggressionMod = (float)Math.sqrt(((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(20) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F));
    this.obedienceMod = (float)Math.sqrt(((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(20) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F / this.aggressionMod));





    setAge(TFC_Time.getTotalDays() - getNumberOfDaysToAdult());
  }





  public EntityChickenTFC(World world, double posX, double posY, double posZ, NBTTagCompound genes) {
    this(world);
    this.field_70165_t = posX;
    this.field_70163_u = posY;
    this.field_70161_v = posZ;
    float motherSize = genes.func_74760_g("m_size");
    float fatherSize = genes.func_74760_g("f_size");
    this.sizeMod = ((this.field_70146_Z.nextInt(3) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) / 10.0F + 1.0F) * (1.0F - 0.1F * this.sex) * (float)Math.sqrt(((motherSize + fatherSize) / 1.9F));


    setAge(TFC_Time.getTotalDays());
  }




  public boolean func_70601_bi() {
    int i = MathHelper.func_76128_c(this.field_70165_t);
    int j = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
    int k = MathHelper.func_76128_c(this.field_70161_v);

    float temp = TFC_Climate.getHeightAdjustedTemp(this.field_70170_p, i, this.field_70170_p.func_72825_h(i, k), k);
    float rain = TFC_Climate.getRainfall(this.field_70170_p, i, 150, k);
    if (temp >= 23.0F && temp < 44.0F && rain > 1500.0F)
      return (TFCBiome.isGrass(this.field_70170_p.func_147439_a(i, j - 1, k)) && this.field_70170_p.func_72883_k(i, j, k) > 8 && TFCBiome.getCanSpawnHere((EntityLiving)this));
    return false;
  }


  public void addAI() {
    if (this.sex == 0)
    {
      this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
    }
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.wheatGrain, false));
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.ryeGrain, false));
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.riceGrain, false));
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.barleyGrain, false));
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.oatGrain, false));
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.maizeEar, false));
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIFindNest((EntityAnimal)this, 1.2000000476837158D));
  }



  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(50.0D);
  }



  public boolean canFamiliarize() {
    return (!isAdult() || (isAdult() && this.familiarity <= 45));
  }



  public boolean canMateWith(IAnimal animal) {
    return (animal.getGender() != getGender() && isAdult() && animal.isAdult() && animal
      .getAnimalTypeID() == getAnimalTypeID());
  }


  public boolean checkFamiliarity(IAnimal.InteractionEnum interaction, EntityPlayer player) {
    boolean flag = false;
    switch (interaction) {
      case NAME:
        flag = (this.familiarity > 50);
        break;
    }

    if (!flag && player != null && !player.field_70170_p.field_72995_K) {
      TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("entity.notFamiliar", new Object[0]));
    }
    return flag;
  }



  public EntityChicken func_90011_a(EntityAgeable entityAgeable) {
    return (EntityChicken)createChildTFC(entityAgeable);
  }




  public EntityAgeable createChildTFC(EntityAgeable entityageable) {
    if (entityageable instanceof IAnimal) {

      IAnimal animal = (IAnimal)entityageable;
      NBTTagCompound nbt = new NBTTagCompound();
      nbt.func_74776_a("m_size", animal.getSizeMod());
      nbt.func_74776_a("f_size", animal.getSizeMod());
      return (EntityAgeable)new EntityChickenTFC(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, nbt);
    }

    return null;
  }






  protected void func_70628_a(boolean par1, int par2) {
    float ageMod = TFC_Core.getPercentGrown(this);
    func_145779_a(Items.field_151008_G, (int)(ageMod * this.sizeMod * (5 + this.field_70146_Z.nextInt(10))));

    if (isAdult()) {

      float foodWeight = ageMod * this.sizeMod * 40.0F;
      TFC_Core.animalDropMeat((Entity)this, TFCItems.chickenRaw, foodWeight);
      func_145779_a(Items.field_151103_aS, this.field_70146_Z.nextInt(2) + 1);
    }
  }



  protected void func_70088_a() {
    super.func_70088_a();
    this.field_70180_af.func_75682_a(13, Integer.valueOf(0));
    this.field_70180_af.func_75682_a(15, Integer.valueOf(0));

    this.field_70180_af.func_75682_a(22, Integer.valueOf(0));
    this.field_70180_af.func_75682_a(23, Integer.valueOf(0));
  }


  public void familiarize(EntityPlayer ep) {
    ItemStack stack = ep.func_70694_bm();
    if (stack != null && isFood(stack) && !this.familiarizedToday && canFamiliarize()) {

      if (!ep.field_71075_bZ.field_75098_d) {

        ep.field_71071_by.func_70299_a(ep.field_71071_by.field_70461_c, ((ItemFoodTFC)stack.func_77973_b()).onConsumedByEntity(ep.func_70694_bm(), this.field_70170_p, (EntityLivingBase)this));
      }
      else {

        this.field_70170_p.func_72956_a((Entity)this, "random.burp", 0.5F, this.field_70170_p.field_73012_v.nextFloat() * 0.1F + 0.9F);
      }
      this.hunger += 24000;
      this.familiarizedToday = true;
      func_70671_ap().func_75651_a((Entity)ep, 0.0F, 0.0F);
      func_70642_aH();
      ExpBonus.FEED.give(ep);
    }
  }



  public float getAggressionMod() {
    return this.aggressionMod;
  }



  public int getAnimalTypeID() {
    return Helper.stringToInt("chicken");
  }



  public Vec3 getAttackedVec() {
    return null;
  }



  public int getBirthDay() {
    return this.field_70180_af.func_75679_c(15);
  }






  protected Item func_146068_u() {
    return Items.field_151008_G;
  }



  public int getDueDay() {
    return 0;
  }


  public ItemStack getEggs() {
    if (TFC_Time.getTotalTicks() >= this.nextEgg) {

      this.nextEgg = TFC_Time.getTotalTicks() + 24000L;
      return new ItemStack(TFCItems.egg, 1);
    }
    return null;
  }



  public EntityLiving getEntity() {
    return (EntityLiving)this;
  }


  public int getFamiliarity() {
    return this.familiarity;
  }



  public boolean getFamiliarizedToday() {
    return this.familiarizedToday;
  }



  public Entity getFearSource() {
    return null;
  }



  public IAnimal.GenderEnum getGender() {
    return IAnimal.GenderEnum.GENDERS[this.field_70180_af.func_75679_c(13)];
  }



  public int getHunger() {
    return this.hunger;
  }



  public boolean getInLove() {
    return this.inLove;
  }


  public long getLastFamiliarityUpdate() {
    return this.lastFamiliarityUpdate;
  }


  public long getNextEgg() {
    return this.nextEgg;
  }



  public int getNumberOfDaysToAdult() {
    return (int)((TFCOptions.animalTimeMultiplier * TFC_Time.daysInMonth) * 4.14D);
  }



  public float getObedienceMod() {
    return this.obedienceMod;
  }


  public int getSex() {
    return this.sex;
  }



  public float getSizeMod() {
    return this.sizeMod;
  }



  public float getStrengthMod() {
    return this.strengthMod;
  }


  public void handleFamiliarityUpdate() {
    int totalDays = TFC_Time.getTotalDays();
    if (this.lastFamiliarityUpdate < totalDays) {
      if (this.familiarizedToday && this.familiarity < 100) {
        this.lastFamiliarityUpdate = totalDays;
        this.familiarizedToday = false;
        float familiarityChange = 6.0F * this.obedienceMod / this.aggressionMod;
        if (isAdult() && this.familiarity <= 45) {

          this.familiarity = (int)(this.familiarity + familiarityChange);
        }
        else if (!isAdult()) {
          float ageMod = 2.0F / (1.0F + TFC_Core.getPercentGrown(this));
          this.familiarity = (int)(this.familiarity + ageMod * familiarityChange);
          if (this.familiarity > 70) {
            this.obedienceMod *= 1.01F;
          }
        }

      } else if (this.familiarity < 30) {
        this.familiarity = (int)(this.familiarity - 2L * (TFC_Time.getTotalDays() - this.lastFamiliarityUpdate));
        this.lastFamiliarityUpdate = totalDays;
      }
    }
    if (this.familiarity > 100) this.familiarity = 100;
    if (this.familiarity < 0) this.familiarity = 0;

  }





  public boolean func_70085_c(EntityPlayer player) {
    ItemStack itemstack = player.func_70694_bm();

    if (!this.field_70170_p.field_72995_K) {

      if (isAdult() && player.func_70093_af() && !isFood(itemstack) && func_70097_a(DamageSource.field_76377_j, 5.0F)) {

        player.field_71071_by.func_70441_a(new ItemStack(Items.field_151008_G, 1));
        this.familiarity -= 4;
        return true;
      }

      if (player.func_70093_af() && !this.familiarizedToday && canFamiliarize()) {

        familiarize(player);
        return true;
      }
    }

    if (itemstack != null && itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.ItemCustomNameTag && itemstack.func_77942_o() && itemstack.field_77990_d.func_74764_b("ItemName")) {
      if (trySetName(itemstack.field_77990_d.func_74779_i("ItemName"), player)) {
        itemstack.field_77994_a--;
      }
      return true;
    }
    return super.func_70085_c(player);
  }



  public boolean isAdult() {
    return (getBirthDay() + getNumberOfDaysToAdult() <= TFC_Time.getTotalDays());
  }






  public boolean func_70650_aV() {
    return true;
  }







  public boolean func_70877_b(ItemStack par1ItemStack) {
    return false;
  }



  public boolean func_70631_g_() {
    return !isAdult();
  }


  public boolean isFood(ItemStack item) {
    return (item != null && (item.func_77973_b() == TFCItems.wheatGrain || item.func_77973_b() == TFCItems.oatGrain || item.func_77973_b() == TFCItems.riceGrain || item
      .func_77973_b() == TFCItems.barleyGrain || item.func_77973_b() == TFCItems.ryeGrain || item.func_77973_b() == TFCItems.maizeEar));
  }



  public boolean isPregnant() {
    return false;
  }






  public void mate(IAnimal otherAnimal) {}






  public void func_70636_d() {
    if (this.hunger > 168000)
    {
      this.hunger = 168000;
    }
    if (this.hunger > 0)
    {
      this.hunger--;
    }

    syncData();
    if (isAdult()) {

      func_70873_a(0);
    }
    else {

      func_70873_a(-1);
    }

    roosterCrow();
    handleFamiliarityUpdate();


    this.field_70887_j = 9999;



    TFC_Core.preventEntityDataUpdate = true;
    if (getGender() == IAnimal.GenderEnum.MALE)
    {
      this.nextEgg = 10000L;
    }

    super.func_70636_d();
    TFC_Core.preventEntityDataUpdate = false;

    if (this.hunger > 144000 && this.field_70146_Z.nextInt(100) == 0 && func_110143_aJ() < TFC_Core.getEntityMaxHealth((EntityLivingBase)this) && !this.field_70128_L) {

      func_70691_i(1.0F);
    }
    else if (this.hunger < 144000 && func_70880_s()) {
      setInLove(false);
    }
  }






  public void func_70037_a(NBTTagCompound nbt) {
    super.func_70037_a(nbt);
    this.sex = nbt.func_74762_e("Sex");
    this.sizeMod = nbt.func_74760_g("Size Modifier");

    this.familiarity = nbt.func_74762_e("Familiarity");
    this.lastFamiliarityUpdate = nbt.func_74763_f("lastFamUpdate");
    this.familiarizedToday = nbt.func_74767_n("Familiarized Today");

    this.strengthMod = nbt.func_74760_g("Strength Modifier");
    this.aggressionMod = nbt.func_74760_g("Aggression Modifier");
    this.obedienceMod = nbt.func_74760_g("Obedience Modifier");

    this.hunger = nbt.func_74762_e("Hunger");
    this.field_70180_af.func_75692_b(15, Integer.valueOf(nbt.func_74762_e("Age")));
    this.nextEgg = nbt.func_74763_f("nextEgg");
  }


  public void roosterCrow() {
    if ((TFC_Time.getTotalTicks() - 15L) % 24000L == 0L && getGender() == IAnimal.GenderEnum.MALE && isAdult() && this.field_70170_p.func_72937_j((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v)) {
      func_85030_a("terrafirmacraft:mob.rooster.cry", 6.0F, this.field_70146_Z.nextFloat() / 2.0F + 0.75F);
    }
  }



  public void setAge(int par1) {
    this.field_70180_af.func_75692_b(15, Integer.valueOf(par1));
  }



  public void setAggressionMod(float aggressionMod) {
    this.aggressionMod = aggressionMod;
  }




  public void setAttackedVec(Vec3 attackedVec) {}



  public void setBirthDay(int day) {
    this.field_70180_af.func_75692_b(15, Integer.valueOf(day));
  }



  public void setFamiliarity(int familiarity) {
    this.familiarity = familiarity;
  }


  public void setFamiliarizedToday(boolean familiarizedToday) {
    this.familiarizedToday = familiarizedToday;
  }




  public void setFearSource(Entity fearSource) {}



  public void func_70873_a(int par1) {
    if (!TFC_Core.preventEntityDataUpdate) {
      this.field_70180_af.func_75692_b(12, Integer.valueOf(par1));
    }
  }


  public void setHunger(int h) {
    this.hunger = h;
  }



  public void setInLove(boolean b) {
    this.inLove = b;
  }


  public void setLastFamiliarityUpdate(long lastFamiliarityUpdate) {
    this.lastFamiliarityUpdate = lastFamiliarityUpdate;
  }


  public void setNextEgg(long nextEgg) {
    this.nextEgg = nextEgg;
  }



  public void setObedienceMod(float obedienceMod) {
    this.obedienceMod = obedienceMod;
  }


  public void setSex(int sex) {
    this.sex = sex;
  }



  public void setSizeMod(float sizeMod) {
    this.sizeMod = sizeMod;
  }



  public void setStrengthMod(float strengthMod) {
    this.strengthMod = strengthMod;
  }


  public void syncData() {
    if (this.field_70180_af != null)
    {
      if (!this.field_70170_p.field_72995_K) {

        this.field_70180_af.func_75692_b(13, Integer.valueOf(this.sex));





        byte[] values = { TFC_Core.getByteFromSmallFloat(this.sizeMod), TFC_Core.getByteFromSmallFloat(this.strengthMod), TFC_Core.getByteFromSmallFloat(this.aggressionMod), TFC_Core.getByteFromSmallFloat(this.obedienceMod), (byte)this.familiarity, (byte)(this.familiarizedToday ? 1 : 0), 0, 0 };





        ByteBuffer buf = ByteBuffer.wrap(values);
        this.field_70180_af.func_75692_b(22, Integer.valueOf(buf.getInt()));
        this.field_70180_af.func_75692_b(23, Integer.valueOf(buf.getInt()));
      }
      else {

        this.sex = this.field_70180_af.func_75679_c(13);

        ByteBuffer buf = ByteBuffer.allocate(8);
        buf.putInt(this.field_70180_af.func_75679_c(22));
        buf.putInt(this.field_70180_af.func_75679_c(23));
        byte[] values = buf.array();

        this.sizeMod = TFC_Core.getSmallFloatFromByte(values[0]);
        this.strengthMod = TFC_Core.getSmallFloatFromByte(values[1]);
        this.aggressionMod = TFC_Core.getSmallFloatFromByte(values[2]);
        this.obedienceMod = TFC_Core.getSmallFloatFromByte(values[3]);

        this.familiarity = values[4];
        this.familiarizedToday = (values[5] == 1);
      }
    }
  }


  public boolean trySetName(String name, EntityPlayer player) {
    if (checkFamiliarity(IAnimal.InteractionEnum.NAME, player)) {

      func_94058_c(name);
      return true;
    }
    func_85030_a(func_70621_aR(), 6.0F, this.field_70146_Z.nextFloat() / 2.0F + (func_70631_g_() ? 1.25F : 0.75F));
    return false;
  }






  public void func_70014_b(NBTTagCompound nbt) {
    super.func_70014_b(nbt);
    nbt.func_74768_a("Sex", this.sex);
    nbt.func_74776_a("Size Modifier", this.sizeMod);

    nbt.func_74768_a("Familiarity", this.familiarity);
    nbt.func_74772_a("lastFamUpdate", this.lastFamiliarityUpdate);
    nbt.func_74757_a("Familiarized Today", this.familiarizedToday);

    nbt.func_74776_a("Strength Modifier", this.strengthMod);
    nbt.func_74776_a("Aggression Modifier", this.aggressionMod);
    nbt.func_74776_a("Obedience Modifier", this.obedienceMod);

    nbt.func_74768_a("Hunger", this.hunger);
    nbt.func_74768_a("Age", getBirthDay());
    nbt.func_74772_a("nextEgg", this.nextEgg);
  }



  protected boolean func_70692_ba() {
    return true;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\Mobs\EntityChickenTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */