package com.bioxx.tfc.Entities.Mobs;

import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.Entities.AI.EntityAIMateTFC;
import com.bioxx.tfc.Entities.AI.EntityAITargetNonTamedTFC;
import com.bioxx.tfc.Food.ItemFoodTFC;
import com.bioxx.tfc.WorldGen.TFCBiome;
import com.bioxx.tfc.api.Entities.IAnimal;
import com.bioxx.tfc.api.Enums.EnumDamageType;
import com.bioxx.tfc.api.Interfaces.ICausesDamage;
import com.bioxx.tfc.api.Interfaces.IInnateArmor;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.TFCOptions;
import com.bioxx.tfc.api.Util.Helper;
import fof.tfcsu.Entity.EntityBoar;
import fof.tfcsu.Entity.Sheep.EntityAnimaniaSheep;
import fof.tfcsu.Register.tfcsuItems;
import fof.tfcsu.utils.ExpBonus;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
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
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;





public class EntityBear
  extends EntityTameable
  implements ICausesDamage, IAnimal, IInnateArmor
{
  private static final float GESTATION_PERIOD = 7.0F;
  private static final float DIMORPHISM = 0.2182F;
  private static final int DEGREE_OF_DIVERSION = 4;
  private static final int FAMILIARITY_CAP = 80;
  private final Random rand = new Random();

  private float moveSpeed;

  private boolean isWet;
  private int sex;
  private int hunger;
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

  protected EntityAIAttackOnCollide attackAI;

  protected EntityAILeapAtTarget leapAI;

  protected EntityAITargetNonTamedTFC targetSheep;
  protected EntityAITargetNonTamedTFC targetDeer;
  protected EntityAITargetNonTamedTFC targetPig;
  protected EntityAITargetNonTamedTFC targetHorse;
  protected EntityAITargetNonTamedTFC targetPlayer;
  protected EntityAIHurtByTarget hurtAI;
  protected boolean isPeacefulAI;
  private boolean wasRoped;
  private int familiarity;
  private long lastFamiliarityUpdate;
  private boolean familiarizedToday;

  public EntityBear(World par1World) {
    super(par1World);
    func_70105_a(1.2F, 1.2F);
    this.moveSpeed = 0.4F;
    func_70661_as().func_75491_a(true);
    this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIMateTFC(this, this.field_70170_p, 0.5F));
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 0.24000000953674316D, TFCItems.fishRaw, false));
    this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIFollowParent((EntityAnimal)this, 0.2800000011920929D));

    this.sizeMod = (float)Math.sqrt((((this.rand.nextInt(this.rand.nextInt(50) + 1) * (this.rand.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F) * (1.0F - 0.2182F * this.sex)));

    this.strengthMod = (float)Math.sqrt(((this.rand.nextInt(this.rand.nextInt(40) + 1) * (this.rand.nextBoolean() ? 1 : -1)) * 0.01F + this.sizeMod));
    this.aggressionMod = (float)Math.sqrt(((this.rand.nextInt(this.rand.nextInt(40) + 1) * (this.rand.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F));
    this.obedienceMod = (float)Math.sqrt(((this.rand.nextInt(this.rand.nextInt(40) + 1) * (this.rand.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F / this.aggressionMod));



    this.sex = this.rand.nextInt(2);

    this.field_70714_bg.func_75776_a(7, (EntityAIBase)new EntityAIWander((EntityCreature)this, this.moveSpeed));
    this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
    this.field_70714_bg.func_75776_a(9, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
    this.attackAI = new EntityAIAttackOnCollide((EntityCreature)this, (this.moveSpeed * 1.5F), true);
    this.leapAI = new EntityAILeapAtTarget((EntityLiving)this, 0.4F);



    this.targetSheep = new EntityAITargetNonTamedTFC(this, EntityAnimaniaSheep.class, 200, false);
    this.targetDeer = new EntityAITargetNonTamedTFC(this, EntityDeer.class, 200, false);

    this.targetPig = new EntityAITargetNonTamedTFC(this, EntityBoar.class, 200, false);
    this.targetHorse = new EntityAITargetNonTamedTFC(this, EntityHorseTFC.class, 200, false);
    this.targetPlayer = new EntityAITargetNonTamedTFC(this, EntityPlayer.class, 20, false);
    this.hurtAI = new EntityAIHurtByTarget((EntityCreature)this, true);

    if (par1World.field_73013_u != EnumDifficulty.PEACEFUL) {

      this.isPeacefulAI = false;
      this.field_70714_bg.func_75776_a(4, (EntityAIBase)this.attackAI);
      this.field_70714_bg.func_75776_a(3, (EntityAIBase)this.leapAI);
      this.field_70715_bh.func_75776_a(4, (EntityAIBase)this.targetSheep);
      this.field_70715_bh.func_75776_a(4, (EntityAIBase)this.targetDeer);
      this.field_70715_bh.func_75776_a(4, (EntityAIBase)this.targetPig);
      this.field_70715_bh.func_75776_a(4, (EntityAIBase)this.targetHorse);
      this.field_70715_bh.func_75776_a(4, (EntityAIBase)this.targetPlayer);
      this.field_70715_bh.func_75776_a(3, (EntityAIBase)this.hurtAI);
    } else {

      this.isPeacefulAI = true;
    }

    this.pregnancyRequiredTime = (int)(TFCOptions.animalTimeMultiplier * 7.0F * (float)TFC_Time.ticksInMonth);





    setAge(TFC_Time.getTotalDays() - getNumberOfDaysToAdult());
  }



  public EntityBear(World par1World, IAnimal mother, List<Float> data) {
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
    float invSizeRatio = 0.5612302F;
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
    float evt = 0.0F;
    if (TFC_Climate.getCacheManager(this.field_70170_p) != null && TFC_Climate.getCacheManager(this.field_70170_p).getEVTLayerAt(i, k) != null)
      evt = (TFC_Climate.getCacheManager(this.field_70170_p).getEVTLayerAt(i, k)).floatdata1;
    TFCBiome biome = (TFCBiome)this.field_70170_p.func_72959_q().func_76935_a(i, k);
    boolean isMountainous = (biome == TFCBiome.MOUNTAINS || biome == TFCBiome.HIGH_HILLS);
    if ((isMountainous && temp < 25.0F && temp > -10.0F && rain > 250.0F && evt < 0.75D) || (temp > 0.0F && temp < 21.0F && rain > 250.0F) || (temp > -20.0F && temp <= 0.0F && rain > 250.0F))
      return (TFCBiome.isGrass(this.field_70170_p.func_147439_a(i, j - 1, k)) && this.field_70170_p.func_72883_k(i, j, k) > 8 && TFCBiome.getCanSpawnHere((EntityLiving)this));
    return false;
  }




  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(3000.0D);
  }



  public boolean func_70652_k(Entity par1Entity) {
    int dam = (int)(275.0F * getStrengthMod() * getAggressionMod() * (getSizeMod() / 2.0F + 0.5F));
    return par1Entity.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), dam);
  }






  protected boolean func_70692_ba() {
    return !this.wasRoped;
  }




  public boolean canFamiliarize() {
    return (!isAdult() || (isAdult() && this.familiarity <= 80));
  }














  public boolean canMateWith(IAnimal animal) {
    return (animal.getGender() != getGender() && isAdult() && animal.isAdult() && animal instanceof EntityBear);
  }








  protected boolean func_70041_e_() {
    return true;
  }



  public boolean checkFamiliarity(IAnimal.InteractionEnum interaction, EntityPlayer player) {
    boolean flag = false;
    switch (interaction) { case MOUNT:
        flag = (this.familiarity > 15); break;
      case BREED: flag = (this.familiarity > 20); break;
      case NAME: flag = (this.familiarity > 70); break;
      case TOLERATEPLAYER: flag = (this.familiarity > 75);
        break; }

    if (!flag && player != null && !player.field_70170_p.field_72995_K) {
      TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("entity.notFamiliar", new Object[0]));
    }
    return flag;
  }



  public EntityAgeable func_90011_a(EntityAgeable entityageable) {
    return createChildTFC(entityageable);
  }



  public EntityAgeable createChildTFC(EntityAgeable eAgeable) {
    ArrayList<Float> data = new ArrayList<>();
    data.add(Float.valueOf(eAgeable.getEntityData().func_74760_g("MateSize")));
    data.add(Float.valueOf(eAgeable.getEntityData().func_74760_g("MateStrength")));
    data.add(Float.valueOf(eAgeable.getEntityData().func_74760_g("MateAggro")));
    data.add(Float.valueOf(eAgeable.getEntityData().func_74760_g("MateObed")));
    return (EntityAgeable)new EntityBear(this.field_70170_p, this, data);
  }



  protected void func_70628_a(boolean par1, int par2) {
    float ageMod = TFC_Core.getPercentGrown(this);

    func_70099_a(new ItemStack(TFCItems.hide, 1, Math.max(0, Math.min(2, (int)(ageMod * 3.0F - 1.0F)))), 0.0F);
    func_145779_a(Items.field_151103_aS, (int)((this.rand.nextInt(6) + 2) * ageMod));
    float foodWeight = ageMod * getSizeMod() * 1500.0F;
    TFC_Core.animalDropMeat((Entity)this, tfcsuItems.bearRaw, foodWeight);
  }




  protected void func_70088_a() {
    super.func_70088_a();
    this.field_70180_af.func_75682_a(18, Float.valueOf(func_110143_aJ()));
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
      ExpBonus.FEED.give(ep);
    }
  }



  public float getAggressionMod() {
    return this.aggressionMod;
  }




  public int getAnimalTypeID() {
    return Helper.stringToInt("bear");
  }



  public Vec3 getAttackedVec() {
    return null;
  }



  public int getBirthDay() {
    return this.field_70180_af.func_75679_c(15);
  }



  public int getCrushArmor() {
    return 0;
  }



  public EnumDamageType getDamageType() {
    return EnumDamageType.SLASHING;
  }






  protected String func_70673_aS() {
    if (!func_70631_g_()) {
      return "terrafirmacraft:mob.bear.death";
    }
    return "terrafirmacraft:mob.bear.cub.cry";
  }







  protected Item func_146068_u() {
    return Item.func_150899_d(0);
  }



  public int getDueDay() {
    return TFC_Time.getDayFromTotalHours((this.timeOfConception + this.pregnancyRequiredTime) / 1000L);
  }



  public EntityLiving getEntity() {
    return (EntityLiving)this;
  }



  public float func_70047_e() {
    return this.field_70131_O * 0.8F;
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






  protected String func_70621_aR() {
    if (!func_70631_g_()) {
      return "terrafirmacraft:mob.bear.hurt";
    }
    return "terrafirmacraft:mob.bear.cub.cry";
  }



  public boolean getInLove() {
    return this.inLove;
  }


  public long getLastFamiliarityUpdate() {
    return this.lastFamiliarityUpdate;
  }






  protected String func_70639_aQ() {
    if (isAdult() && this.field_70170_p.field_73012_v.nextInt(100) < 5)
      return "terrafirmacraft:mob.bear.cry";
    if (func_70631_g_() && this.field_70170_p.field_73012_v.nextInt(100) < 5) {
      return "terrafirmacraft:mob.bear.cub.cry";
    }
    return func_70631_g_() ? null : "terrafirmacraft:mob.bear.say";
  }






  public int func_70641_bl() {
    return 2;
  }


  public float getMoveSpeed() {
    return this.moveSpeed;
  }



  public int getNumberOfDaysToAdult() {
    return TFC_Time.daysInMonth * 60;
  }



  public float getObedienceMod() {
    return this.obedienceMod;
  }



  public int getPierceArmor() {
    return -335;
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



  public int getSlashArmor() {
    return 0;
  }






  protected float func_70599_aP() {
    return 0.4F;
  }



  public float getStrengthMod() {
    return this.strengthMod;
  }


  public long getTimeOfConception() {
    return this.timeOfConception;
  }


  public void handleFamiliarityUpdate() {
    int totalDays = TFC_Time.getTotalDays();
    if (this.lastFamiliarityUpdate < totalDays) {
      if (this.familiarizedToday && this.familiarity < 100) {
        this.lastFamiliarityUpdate = totalDays;
        this.familiarizedToday = false;
        float familiarityChange = 3.0F * this.obedienceMod / this.aggressionMod;
        if (isAdult() && this.familiarity <= 80) {

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


  public void func_70103_a(byte par1) {
    if (par1 == 8) {

      this.isWet = true;
    }
    else {

      super.func_70103_a(par1);
    }
  }


  public boolean isBreedingItemTFC(ItemStack item) {
    return (!this.pregnant && isFood(item));
  }



  public boolean func_70085_c(EntityPlayer player) {
    if (!this.field_70170_p.field_72995_K) {

      if (player.func_70093_af() && !this.familiarizedToday && canFamiliarize()) {

        familiarize(player);
        return true;
      }
      TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation((getGender() == IAnimal.GenderEnum.FEMALE) ? "entity.female" : "entity.male", new Object[0]));
      if (getGender() == IAnimal.GenderEnum.FEMALE && this.pregnant)
      {
        TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("entity.pregnant", new Object[0]));
      }
    }
    ItemStack itemstack = player.func_70694_bm();
    if (itemstack != null && isBreedingItemTFC(itemstack) && checkFamiliarity(IAnimal.InteractionEnum.BREED, player) && func_70874_b() == 0 && !func_70880_s() && (this.familiarizedToday || !canFamiliarize())) {

      if (!player.field_71075_bZ.field_75098_d) {
        player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, ((ItemFoodTFC)itemstack.func_77973_b()).onConsumedByEntity(player.func_70694_bm(), this.field_70170_p, (EntityLivingBase)this));
      }
      this.hunger += 24000;
      func_146082_f(player);
      return true;
    }  if (itemstack != null && itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.ItemCustomNameTag && itemstack.func_77942_o() && itemstack.field_77990_d.func_74764_b("ItemName")) {

      if (trySetName(itemstack.field_77990_d.func_74779_i("ItemName"), player))
      {
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



  public boolean func_70631_g_() {
    return !isAdult();
  }


  public boolean isFood(ItemStack item) {
    return (item != null && item.func_77973_b().equals(TFCItems.fishRaw));
  }



  public boolean isPregnant() {
    return this.pregnant;
  }


  public boolean isWasRoped() {
    return this.wasRoped;
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
    this.mateSizeMod = otherAnimal.getSizeMod();
    this.mateStrengthMod = otherAnimal.getStrengthMod();
    this.mateAggroMod = otherAnimal.getAggressionMod();
    this.mateObedMod = otherAnimal.getObedienceMod();
  }







  public void func_70636_d() {
    TFC_Core.preventEntityDataUpdate = true;
    super.func_70636_d();
    TFC_Core.preventEntityDataUpdate = false;


    if (this.hunger > 168000) this.hunger = 168000;
    if (this.hunger > 0) this.hunger--;

    if (func_70880_s()) {

      func_70875_t();
      setInLove(true);
    }

    handleFamiliarityUpdate();
    syncData();
    if (isAdult()) { func_70873_a(0); }
    else { func_70873_a(-1); }

    if (!this.field_70170_p.field_72995_K) {

      if (!this.isWet && !func_70781_l() && this.field_70122_E) {

        this.isWet = true;
        this.field_70170_p.func_72960_a((Entity)this, (byte)8);
      }

      if (isPregnant())
      {
        if (TFC_Time.getTotalTicks() >= this.timeOfConception + this.pregnancyRequiredTime) {

          int i = this.rand.nextInt(3) + 1;
          for (int x = 0; x < i; x++) {

            EntityBear baby = (EntityBear)createChildTFC((EntityAgeable)this);
            baby.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0F, 0.0F);
            baby.field_70759_as = baby.field_70177_z;
            baby.field_70761_aq = baby.field_70177_z;
            this.field_70170_p.func_72838_d((Entity)baby);
            baby.setAge(TFC_Time.getTotalDays());
          }

          this.pregnant = false;
        }
      }
    }

    if (func_110167_bD() && !this.wasRoped) {
      this.wasRoped = true;
    }
  }






  public void func_70071_h_() {
    super.func_70071_h_();
    if (!this.field_70170_p.field_72995_K)
    {
      if (!this.isPeacefulAI && this.field_70170_p.field_73013_u == EnumDifficulty.PEACEFUL) {

        this.isPeacefulAI = true;
        this.field_70714_bg.func_85156_a((EntityAIBase)this.attackAI);
        this.field_70714_bg.func_85156_a((EntityAIBase)this.leapAI);
        this.field_70715_bh.func_85156_a((EntityAIBase)this.targetSheep);
        this.field_70715_bh.func_85156_a((EntityAIBase)this.targetDeer);
        this.field_70715_bh.func_85156_a((EntityAIBase)this.targetPig);
        this.field_70715_bh.func_85156_a((EntityAIBase)this.targetHorse);
        this.field_70715_bh.func_85156_a((EntityAIBase)this.targetPlayer);
        this.field_70715_bh.func_85156_a((EntityAIBase)this.hurtAI);
      }
      else if (this.isPeacefulAI && this.field_70170_p.field_73013_u != EnumDifficulty.PEACEFUL) {

        this.isPeacefulAI = false;
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)this.attackAI);
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)this.leapAI);
        this.field_70715_bh.func_75776_a(4, (EntityAIBase)this.targetSheep);
        this.field_70715_bh.func_75776_a(4, (EntityAIBase)this.targetDeer);
        this.field_70715_bh.func_75776_a(4, (EntityAIBase)this.targetPig);
        this.field_70715_bh.func_75776_a(4, (EntityAIBase)this.targetHorse);
        this.field_70715_bh.func_75776_a(4, (EntityAIBase)this.targetPlayer);
        this.field_70715_bh.func_75776_a(3, (EntityAIBase)this.hurtAI);
      }
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

    this.wasRoped = nbt.func_74767_n("wasRoped");

    this.hunger = nbt.func_74762_e("Hunger");
    this.pregnant = nbt.func_74767_n("Pregnant");
    this.mateSizeMod = nbt.func_74760_g("MateSize");
    this.mateStrengthMod = nbt.func_74760_g("MateStrength");
    this.mateAggroMod = nbt.func_74760_g("MateAggro");
    this.mateObedMod = nbt.func_74760_g("MateObed");
    this.timeOfConception = nbt.func_74763_f("ConceptionTime");
    this.field_70180_af.func_75692_b(15, Integer.valueOf(nbt.func_74762_e("Age")));
  }




  public void setAge(int par1) {
    this.field_70180_af.func_75692_b(15, Integer.valueOf(par1));
  }



  public void setAggressionMod(float aggression) {
    this.aggressionMod = aggression;
  }




  public void setAttackedVec(Vec3 attackedVec) {}



  public void setBirthDay(int day) {
    this.field_70180_af.func_75692_b(15, Integer.valueOf(day));
  }



  public void setFamiliarity(int f) {
    this.familiarity = f;
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


  public void setMoveSpeed(float moveSpeed) {
    this.moveSpeed = moveSpeed;
  }



  public void setObedienceMod(float obedience) {
    this.obedienceMod = obedience;
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



  public void setSizeMod(float size) {
    this.sizeMod = size;
  }



  public void setStrengthMod(float strength) {
    this.strengthMod = strength;
  }


  public void setTimeOfConception(long timeOfConception) {
    this.timeOfConception = timeOfConception;
  }


  public void setWasRoped(boolean wasRoped) {
    this.wasRoped = wasRoped;
  }


  public void syncData() {
    if (this.field_70180_af != null)
    {
      if (!this.field_70170_p.field_72995_K) {

        this.field_70180_af.func_75692_b(13, Integer.valueOf(this.sex));





        byte[] values = { TFC_Core.getByteFromSmallFloat(this.sizeMod), TFC_Core.getByteFromSmallFloat(this.strengthMod), TFC_Core.getByteFromSmallFloat(this.aggressionMod), TFC_Core.getByteFromSmallFloat(this.obedienceMod), (byte)this.familiarity, (byte)(this.familiarizedToday ? 1 : 0), (byte)(this.pregnant ? 1 : 0), 0 };





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


        try {
          this.timeOfConception = Long.parseLong(this.field_70180_af.func_75681_e(24));
        } catch (NumberFormatException numberFormatException) {}
      }
    }
  }


  public boolean trySetName(String name, EntityPlayer player) {
    if (checkFamiliarity(IAnimal.InteractionEnum.NAME, player)) {

      func_94058_c(name);
      return true;
    }
    func_85030_a(func_70631_g_() ? "terrafirmacraft:mob.bear.cub.cry" : "terrafirmacraft:mob.bear.cry", 6.0F, this.rand.nextFloat() / 2.0F + 0.75F);
    return false;
  }






  protected void func_70629_bd() {
    this.field_70180_af.func_75692_b(18, Float.valueOf(func_110143_aJ()));
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

    nbt.func_74757_a("wasRoped", this.wasRoped);

    nbt.func_74768_a("Hunger", this.hunger);
    nbt.func_74757_a("Pregnant", this.pregnant);
    nbt.func_74776_a("MateSize", this.mateSizeMod);
    nbt.func_74776_a("MateStrength", this.mateStrengthMod);
    nbt.func_74776_a("MateAggro", this.mateAggroMod);
    nbt.func_74776_a("MateObed", this.mateObedMod);
    nbt.func_74772_a("ConceptionTime", this.timeOfConception);
    nbt.func_74768_a("Age", getBirthDay());
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\Mobs\EntityBear.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */