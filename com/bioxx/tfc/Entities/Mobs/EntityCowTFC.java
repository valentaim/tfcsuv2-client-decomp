package com.bioxx.tfc.Entities.Mobs;

import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.Entities.AI.AIEatGrass;
import com.bioxx.tfc.Entities.AI.EntityAIAvoidEntityTFC;
import com.bioxx.tfc.Entities.AI.EntityAIMateTFC;
import com.bioxx.tfc.Food.ItemFoodTFC;
import com.bioxx.tfc.Items.Tools.ItemCustomBucketMilk;
import com.bioxx.tfc.WorldGen.TFCBiome;
import com.bioxx.tfc.api.Entities.IAnimal;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.TFCOptions;
import com.bioxx.tfc.api.Util.Helper;
import fof.tfcsu.utils.ExpBonus;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;



public class EntityCowTFC
  extends EntityCow
  implements IAnimal
{
  private static final float GESTATION_PERIOD = 9.0F;
  private static final float DIMORPHISM = 0.1822F;
  private static final int DEGREE_OF_DIVERSION = 1;
  private static final int FAMILIARITY_CAP = 35;
  protected final AIEatGrass aiEatGrass = new AIEatGrass(this);

  private long animalID;
  private int sex;
  private int hunger;
  private long hasMilkTime;
  private boolean canMilk;
  private boolean pregnant;
  private int pregnancyRequiredTime;
  private long timeOfConception;
  private float mateSizeMod;
  private float mateStrengthMod;
  private float mateAggroMod;
  private float mateObedMod;
  private float sizeMod;
  private float strengthMod;
  private float aggressionMod = 1.0F;
  private float obedienceMod = 1.0F;

  private boolean inLove;

  private int familiarity;
  private long lastFamiliarityUpdate;
  private boolean familiarizedToday;

  public EntityCowTFC(World par1World) {
    super(par1World);
    this.animalID = TFC_Time.getTotalTicks() + func_145782_y();
    this.hunger = 168000;
    this.pregnant = false;
    this.pregnancyRequiredTime = (int)(TFCOptions.animalTimeMultiplier * 9.0F * (float)TFC_Time.ticksInMonth);
    this.timeOfConception = 0L;
    this.mateSizeMod = 0.0F;
    this.sex = this.field_70146_Z.nextInt(2);

    this.sizeMod = (float)Math.sqrt((((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(20) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F) * (1.0F - 0.1822F * this.sex)));
    this.strengthMod = (float)Math.sqrt(((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(10) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + this.sizeMod));
    this.aggressionMod = (float)Math.sqrt(((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(10) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F));
    this.obedienceMod = (float)Math.sqrt(((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(10) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F / this.aggressionMod));
    func_70105_a(0.9F, 1.3F);
    func_70661_as().func_75491_a(true);
    this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIMateTFC(this, this.field_70170_p, 1.0F));
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.wheatGrain, false));
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.ryeGrain, false));
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.riceGrain, false));
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.barleyGrain, false));
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.oatGrain, false));
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.maizeEar, false));
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAvoidEntityTFC((EntityCreature)this, EntityWolfTFC.class, 8.0F, 0.5D, 0.699999988079071D));
    this.field_70714_bg.func_75776_a(6, (EntityAIBase)this.aiEatGrass);





    setAge(TFC_Time.getTotalDays() - getNumberOfDaysToAdult());
  }




  public EntityCowTFC(World par1World, IAnimal mother, List<Float> data) {
    this(par1World);
    float fatherSize = 1.0F;
    float fatherStr = 1.0F;
    float fatherAggro = 1.0F;
    float fatherObed = 1.0F;
    for (int i = 0; i < data.size(); i++) {
      switch (i) { case 0:
          fatherSize = ((Float)data.get(i)).floatValue(); break;
        case 1: fatherStr = ((Float)data.get(i)).floatValue(); break;
        case 2: fatherAggro = ((Float)data.get(i)).floatValue(); break;
        case 3: fatherObed = ((Float)data.get(i)).floatValue();
          break; }

    }
    this.field_70165_t = ((EntityLivingBase)mother).field_70165_t;
    this.field_70163_u = ((EntityLivingBase)mother).field_70163_u;
    this.field_70161_v = ((EntityLivingBase)mother).field_70161_v;
    float invSizeRatio = 0.5501155F;
    this.sizeMod = (float)Math.sqrt((this.sizeMod * this.sizeMod * (float)Math.sqrt(((mother.getSizeMod() + fatherSize) * invSizeRatio))));
    this.strengthMod = (float)Math.sqrt((this.strengthMod * this.strengthMod * (float)Math.sqrt(((mother.getStrengthMod() + fatherStr) * 0.5F))));
    this.aggressionMod = (float)Math.sqrt((this.aggressionMod * this.aggressionMod * (float)Math.sqrt(((mother.getAggressionMod() + fatherAggro) * 0.5F))));
    this.obedienceMod = (float)Math.sqrt((this.obedienceMod * this.obedienceMod * (float)Math.sqrt(((mother.getObedienceMod() + fatherObed) * 0.5F))));

    this.familiarity = (int)((mother.getFamiliarity() < 90) ? (mother.getFamiliarity() / 2) : (mother.getFamiliarity() * 0.9F));


    setAge(TFC_Time.getTotalDays());
  }




  public boolean func_70601_bi() {
    int i = MathHelper.func_76128_c(this.field_70165_t);
    int j = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
    int k = MathHelper.func_76128_c(this.field_70161_v);
    float temp = TFC_Climate.getHeightAdjustedTemp(this.field_70170_p, i, this.field_70170_p.func_72825_h(i, k), k);
    float rain = TFC_Climate.getRainfall(this.field_70170_p, i, 150, k);
    TFCBiome biome = (TFCBiome)this.field_70170_p.func_72959_q().func_76935_a(i, k);
    boolean isMountainous = (biome == TFCBiome.MOUNTAINS || biome == TFCBiome.HIGH_HILLS);
    if (!isMountainous && temp > 0.0F && temp < 30.0F && rain > 100.0F && rain <= 500.0F)
      return (TFCBiome.isGrass(this.field_70170_p.func_147439_a(i, j - 1, k)) && this.field_70170_p.func_72883_k(i, j, k) > 8 && TFCBiome.getCanSpawnHere((EntityLiving)this));
    return false;
  }



  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(500.0D);
  }



  public boolean canFamiliarize() {
    return (!isAdult() || (isAdult() && this.familiarity <= 35));
  }



  public boolean canMateWith(IAnimal animal) {
    return (animal.getGender() != getGender() && isAdult() && animal.isAdult() && animal instanceof EntityCowTFC);
  }




  public boolean checkFamiliarity(IAnimal.InteractionEnum interaction, EntityPlayer player) {
    boolean flag = false;
    switch (interaction) {

      case BREED:
        flag = (this.familiarity > 20);
        break;
      case MILK:
        flag = (this.familiarity > 15);
        break;
      case NAME:
        flag = (this.familiarity > 40);
        break;
    }


    if (!flag && player != null && !player.field_70170_p.field_72995_K)
    {
      TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("entity.notFamiliar", new Object[0]));
    }
    return flag;
  }



  public EntityCow func_90011_a(EntityAgeable entityageable) {
    return (EntityCow)createChildTFC(entityageable);
  }



  public EntityAgeable createChildTFC(EntityAgeable eAgeable) {
    ArrayList<Float> data = new ArrayList<>();
    data.add(Float.valueOf(eAgeable.getEntityData().func_74760_g("MateSize")));
    data.add(Float.valueOf(eAgeable.getEntityData().func_74760_g("MateStrength")));
    data.add(Float.valueOf(eAgeable.getEntityData().func_74760_g("MateAggro")));
    data.add(Float.valueOf(eAgeable.getEntityData().func_74760_g("MateObed")));
    return (EntityAgeable)new EntityCowTFC(this.field_70170_p, this, data);
  }






  protected void func_70628_a(boolean par1, int par2) {
    float ageMod = TFC_Core.getPercentGrown(this);

    func_70099_a(new ItemStack(TFCItems.hide, 1, Math.max(0, Math.min(2, (int)(ageMod * 3.0F - 1.0F)))), 0.0F);
    func_145779_a(Items.field_151103_aS, (int)((this.field_70146_Z.nextInt(6) + 3) * ageMod));

    float foodWeight = ageMod * this.sizeMod * 4000.0F;

    TFC_Core.animalDropMeat((Entity)this, TFCItems.beefRaw, foodWeight);
  }



  public void func_70615_aA() {
    this.hunger += 24000;
  }



  protected void func_70088_a() {
    super.func_70088_a();
    this.field_70180_af.func_75682_a(13, Integer.valueOf(0));
    this.field_70180_af.func_75682_a(15, Integer.valueOf(0));

    this.field_70180_af.func_75682_a(22, Integer.valueOf(0));
    this.field_70180_af.func_75682_a(23, Integer.valueOf(0));
    this.field_70180_af.func_75682_a(24, String.valueOf("0"));
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
    }
  }



  public float getAggressionMod() {
    return this.aggressionMod;
  }


  public long getAnimalID() {
    return this.animalID;
  }



  public int getAnimalTypeID() {
    return Helper.stringToInt("cow");
  }



  public Vec3 getAttackedVec() {
    return null;
  }



  public int getBirthDay() {
    return this.field_70180_af.func_75679_c(15);
  }






  protected Item func_146068_u() {
    return Items.field_151116_aA;
  }



  public int getDueDay() {
    return TFC_Time.getDayFromTotalHours((this.timeOfConception + this.pregnancyRequiredTime) / 1000L);
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


  public long getHasMilkTime() {
    return this.hasMilkTime;
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



  public int getNumberOfDaysToAdult() {
    return (int)(TFCOptions.animalTimeMultiplier * TFC_Time.daysInMonth * 36.0F);
  }



  public float getObedienceMod() {
    return this.obedienceMod;
  }


  public int getPregnancyRequiredTime() {
    return this.pregnancyRequiredTime;
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


  public long getTimeOfConception() {
    return this.timeOfConception;
  }



  public void handleFamiliarityUpdate() {
    int totalDays = TFC_Time.getTotalDays();
    if (this.lastFamiliarityUpdate < totalDays)
    {
      if (this.familiarizedToday && this.familiarity < 100) {

        this.lastFamiliarityUpdate = totalDays;
        this.familiarizedToday = false;
        float familiarityChange = 6.0F * this.obedienceMod / this.aggressionMod;
        if (isAdult() && this.familiarity <= 35)
        {
          this.familiarity = (int)(this.familiarity + familiarityChange);
        }
        else if (!isAdult())
        {
          float ageMod = 2.0F / (1.0F + TFC_Core.getPercentGrown(this));
          this.familiarity = (int)(this.familiarity + ageMod * familiarityChange);
          if (this.familiarity > 70)
          {
            this.obedienceMod *= 1.01F;
          }
        }

      } else if (this.familiarity < 30) {

        this.familiarity = (int)(this.familiarity - 2L * (TFC_Time.getTotalDays() - this.lastFamiliarityUpdate));
        this.lastFamiliarityUpdate = totalDays;
      }
    }
    if (this.familiarity > 100)
      this.familiarity = 100;
    if (this.familiarity < 0) {
      this.familiarity = 0;
    }
  }





  public boolean func_70085_c(EntityPlayer player) {
    if (!this.field_70170_p.field_72995_K) {

      if (player.func_70093_af() && !this.familiarizedToday && canFamiliarize()) {

        familiarize(player);
        return true;
      }

      if (getGender() == IAnimal.GenderEnum.FEMALE && this.pregnant)
      {
        TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("entity.pregnant", new Object[0]));
      }

      if (getGender() == IAnimal.GenderEnum.FEMALE && isAdult() && this.hasMilkTime < TFC_Time.getTotalTicks() && checkFamiliarity(IAnimal.InteractionEnum.MILK, player)) {

        ItemStack heldItem = player.field_71071_by.func_70448_g();
        if (heldItem != null && heldItem.func_77973_b() == TFCItems.woodenBucketEmpty) {

          if (!this.familiarizedToday && this.familiarity <= 35) {

            this.familiarizedToday = true;
            func_70671_ap().func_75651_a((Entity)player, 0.0F, 0.0F);
            func_70642_aH();
          }

          ItemStack milkBucket = new ItemStack(TFCItems.woodenBucketMilk);
          ItemCustomBucketMilk.createTag(milkBucket, 20.0F);
          player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, milkBucket);
          this.hasMilkTime = TFC_Time.getTotalTicks() + 24000L;
          ExpBonus.MILK.give(player);
          return true;
        }
      }
    }

    ItemStack itemstack = player.field_71071_by.func_70448_g();
    if (itemstack != null && isBreedingItemTFC(itemstack) && checkFamiliarity(IAnimal.InteractionEnum.BREED, player) && func_70874_b() == 0 && !func_70880_s() && (this.familiarizedToday ||
      !canFamiliarize())) {

      if (!player.field_71075_bZ.field_75098_d)
      {
        player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, ((ItemFoodTFC)itemstack.func_77973_b()).onConsumedByEntity(player.func_70694_bm(), this.field_70170_p, (EntityLivingBase)this));
      }
      this.hunger += 24000;
      func_146082_f(player);
      return true;
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



  public boolean func_70877_b(ItemStack par1ItemStack) {
    return false;
  }


  public boolean isBreedingItemTFC(ItemStack item) {
    return (!this.pregnant && isFood(item));
  }



  public boolean func_70631_g_() {
    return !isAdult();
  }



  public boolean isFood(ItemStack item) {
    return (item != null && (item.func_77973_b() == TFCItems.wheatGrain || item.func_77973_b() == TFCItems.oatGrain || item.func_77973_b() == TFCItems.riceGrain || item
      .func_77973_b() == TFCItems.barleyGrain || item.func_77973_b() == TFCItems.ryeGrain || item.func_77973_b() == TFCItems.maizeEar));
  }


  public boolean isMilkable() {
    return this.canMilk;
  }



  public boolean isPregnant() {
    return this.pregnant;
  }



  public void mate(IAnimal otherAnimal) {
    if (getGender() == IAnimal.GenderEnum.MALE) {

      otherAnimal.mate(this);
      return;
    }
    this.timeOfConception = TFC_Time.getTotalTicks();
    this.pregnant = true;
    func_70875_t();
    otherAnimal.setInLove(false);
    this.mateAggroMod = otherAnimal.getAggressionMod();
    this.mateObedMod = otherAnimal.getObedienceMod();
    this.mateSizeMod = otherAnimal.getSizeMod();
    this.mateStrengthMod = otherAnimal.getStrengthMod();
  }




  public void func_70636_d() {
    if (this.hunger > 168000)
    {
      this.hunger = 168000;
    }
    if (this.hunger > 0)
    {
      this.hunger--;
    }

    if (func_70880_s()) {

      func_70875_t();
      setInLove(true);
    }

    handleFamiliarityUpdate();

    if (getGender() == IAnimal.GenderEnum.FEMALE && isAdult() && this.hasMilkTime < TFC_Time.getTotalTicks() && checkFamiliarity(IAnimal.InteractionEnum.MILK, (EntityPlayer)null)) {
      this.canMilk = true;
    } else {
      this.canMilk = false;
    }
    syncData();
    if (isAdult()) {

      func_70873_a(0);
    }
    else {

      func_70873_a(-1);
    }
    if (!this.field_70170_p.field_72995_K && isPregnant())
    {
      if (TFC_Time.getTotalTicks() >= this.timeOfConception + this.pregnancyRequiredTime) {

        EntityCowTFC baby = (EntityCowTFC)createChildTFC((EntityAgeable)this);
        baby.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0F, 0.0F);
        baby.field_70759_as = baby.field_70177_z;
        baby.field_70761_aq = baby.field_70177_z;
        this.field_70170_p.func_72838_d((Entity)baby);
        baby.setAge(TFC_Time.getTotalDays());
        this.pregnant = false;
      }
    }




    TFC_Core.preventEntityDataUpdate = true;
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
    this.animalID = nbt.func_74763_f("Animal ID");
    this.sex = nbt.func_74762_e("Sex");
    this.sizeMod = nbt.func_74760_g("Size Modifier");

    this.familiarity = nbt.func_74762_e("Familiarity");
    this.lastFamiliarityUpdate = nbt.func_74763_f("lastFamUpdate");
    this.familiarizedToday = nbt.func_74767_n("Familiarized Today");

    this.strengthMod = nbt.func_74760_g("Strength Modifier");
    this.aggressionMod = nbt.func_74760_g("Aggression Modifier");
    this.obedienceMod = nbt.func_74760_g("Obedience Modifier");

    this.hunger = nbt.func_74762_e("Hunger");
    this.pregnant = nbt.func_74767_n("Pregnant");
    this.mateSizeMod = nbt.func_74760_g("MateSize");
    this.mateStrengthMod = nbt.func_74760_g("MateStrength");
    this.mateAggroMod = nbt.func_74760_g("MateAggro");
    this.mateObedMod = nbt.func_74760_g("MateObed");
    this.timeOfConception = nbt.func_74763_f("ConceptionTime");
    this.hasMilkTime = nbt.func_74763_f("HasMilkTime");
    this.canMilk = nbt.func_74767_n("CanMilk");
    setAge(nbt.func_74762_e("Age"));
  }




  public void setAge(int par1) {
    this.field_70180_af.func_75692_b(15, Integer.valueOf(par1));
  }




  public void setAggressionMod(float aggressionMod) {
    this.aggressionMod = aggressionMod;
  }


  public void setAnimalID(long animalID) {
    this.animalID = animalID;
  }




  public void setAttackedVec(Vec3 attackedVec) {}




  public void setBirthDay(int day) {
    this.field_70180_af.func_75692_b(15, Integer.valueOf(day));
  }


  public void setCanMilk(boolean canMilk) {
    this.canMilk = canMilk;
  }



  public void setFamiliarity(int familiarity) {
    this.familiarity = familiarity;
  }


  public void setFamiliarizedToday(boolean familiarizedToday) {
    this.familiarizedToday = familiarizedToday;
  }




  public void setFearSource(Entity fearSource) {}




  public void func_70873_a(int par1) {
    if (!TFC_Core.preventEntityDataUpdate)
    {
      this.field_70180_af.func_75692_b(12, Integer.valueOf(par1));
    }
  }


  public void setHasMilkTime(long hasMilkTime) {
    this.hasMilkTime = hasMilkTime;
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



  public void setObedienceMod(float obedienceMod) {
    this.obedienceMod = obedienceMod;
  }


  public void setPregnancyRequiredTime(int pregnancyRequiredTime) {
    this.pregnancyRequiredTime = pregnancyRequiredTime;
  }


  public void setPregnant(boolean pregnant) {
    this.pregnant = pregnant;
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


  public void setTimeOfConception(long timeOfConception) {
    this.timeOfConception = timeOfConception;
  }


  public void syncData() {
    if (this.field_70180_af != null)
    {
      if (!this.field_70170_p.field_72995_K) {

        this.field_70180_af.func_75692_b(13, Integer.valueOf(this.sex));

        byte[] values = { TFC_Core.getByteFromSmallFloat(this.sizeMod), TFC_Core.getByteFromSmallFloat(this.strengthMod), TFC_Core.getByteFromSmallFloat(this.aggressionMod), TFC_Core.getByteFromSmallFloat(this.obedienceMod), (byte)this.familiarity, (byte)(this.familiarizedToday ? 1 : 0), (byte)(this.pregnant ? 1 : 0), (byte)(this.canMilk ? 1 : 0) };


        ByteBuffer buf = ByteBuffer.wrap(values);
        this.field_70180_af.func_75692_b(22, Integer.valueOf(buf.getInt()));
        this.field_70180_af.func_75692_b(23, Integer.valueOf(buf.getInt()));
        this.field_70180_af.func_75692_b(24, String.valueOf(this.timeOfConception));
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
        this.pregnant = (values[6] == 1);
        this.canMilk = (values[7] == 1);


        try {
          this.timeOfConception = Long.parseLong(this.field_70180_af.func_75681_e(24));
        } catch (NumberFormatException numberFormatException) {}
      }
    }
  }




  public boolean trySetName(String name, EntityPlayer player) {
    if (checkFamiliarity(IAnimal.InteractionEnum.NAME, player)) {

      func_94058_c(name);
      func_94061_f(true);
      return true;
    }
    func_85030_a(func_70621_aR(), 6.0F, this.field_70146_Z.nextFloat() / 2.0F + (func_70631_g_() ? 1.25F : 0.75F));
    return false;
  }



  public void func_70014_b(NBTTagCompound nbt) {
    super.func_70014_b(nbt);
    nbt.func_74768_a("Sex", this.sex);
    nbt.func_74772_a("Animal ID", this.animalID);
    nbt.func_74776_a("Size Modifier", this.sizeMod);

    nbt.func_74768_a("Familiarity", this.familiarity);
    nbt.func_74772_a("lastFamUpdate", this.lastFamiliarityUpdate);
    nbt.func_74757_a("Familiarized Today", this.familiarizedToday);

    NBTTagCompound nbt2 = nbt;
    nbt2.func_74776_a("Strength Modifier", this.strengthMod);
    nbt2.func_74776_a("Aggression Modifier", this.aggressionMod);
    nbt2.func_74776_a("Obedience Modifier", this.obedienceMod);

    nbt.func_74768_a("Hunger", this.hunger);
    nbt.func_74757_a("Pregnant", this.pregnant);
    nbt.func_74776_a("MateSize", this.mateSizeMod);
    nbt.func_74776_a("MateStrength", this.mateStrengthMod);
    nbt.func_74776_a("MateAggro", this.mateAggroMod);
    nbt.func_74776_a("MateObed", this.mateObedMod);
    nbt.func_74772_a("ConceptionTime", this.timeOfConception);
    nbt.func_74768_a("Age", getBirthDay());
    nbt.func_74772_a("HasMilkTime", this.hasMilkTime);
    nbt.func_74757_a("CanMilk", this.canMilk);
  }



  protected boolean func_70692_ba() {
    return true;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\Mobs\EntityCowTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */