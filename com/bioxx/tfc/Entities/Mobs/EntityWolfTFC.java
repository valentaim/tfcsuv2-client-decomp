package com.bioxx.tfc.Entities.Mobs;

import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.Entities.AI.EntityAIMateTFC;
import com.bioxx.tfc.Entities.AI.EntityAISitTFC;
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
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fof.tfcsu.Entity.EntityBoar;
import fof.tfcsu.utils.ExpBonus;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.BlockColored;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;





public class EntityWolfTFC
  extends EntityWolf
  implements IAnimal, IInnateArmor, ICausesDamage
{
  private static final float GESTATION_PERIOD = 2.25F;
  private static final float DIMORPHISM = 0.0786F;
  private static final int DEGREE_OF_DIVERSION = 1;
  private static final int FAMILIARITY_CAP = 35;
  private long animalID;
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

  private int familiarity;

  private long lastFamiliarityUpdate;

  private boolean familiarizedToday;
  private int happyTicks;
  private boolean wasRoped;
  private static final int BREED_RANGE = 50;
  protected EntityAITargetNonTamedTFC targetChicken;
  protected EntityAITargetNonTamedTFC targetPheasant;
  protected EntityAITargetNonTamedTFC targetPig;
  protected EntityAITargetNonTamedTFC targetDeer;
  protected EntityAITargetNonTamedTFC targetHorse;
  private boolean peacefulAI;

  public EntityWolfTFC(World par1World) {
    super(par1World);
    this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIMateTFC(this, this.field_70170_p, 1.0F));
    this.field_70715_bh.func_85156_a((EntityAIBase)this.field_70911_d);
    this.field_70911_d = (EntityAISit)new EntityAISitTFC((EntityTameable)this);
    this.field_70714_bg.func_75776_a(2, (EntityAIBase)this.field_70911_d);


    this.targetChicken = new EntityAITargetNonTamedTFC((EntityTameable)this, EntityChickenTFC.class, 200, false);
    this.targetPheasant = new EntityAITargetNonTamedTFC((EntityTameable)this, EntityPheasantTFC.class, 200, false);

    this.targetPig = new EntityAITargetNonTamedTFC((EntityTameable)this, EntityBoar.class, 200, false);

    this.targetDeer = new EntityAITargetNonTamedTFC((EntityTameable)this, EntityDeer.class, 200, false);
    this.targetHorse = new EntityAITargetNonTamedTFC((EntityTameable)this, EntityHorseTFC.class, 200, false);
    if (this.field_70170_p.field_73013_u != EnumDifficulty.PEACEFUL) {

      this.peacefulAI = false;
      this.field_70715_bh.func_75776_a(7, (EntityAIBase)this.targetChicken);
      this.field_70715_bh.func_75776_a(7, (EntityAIBase)this.targetPheasant);
      this.field_70715_bh.func_75776_a(7, (EntityAIBase)this.targetPig);

      this.field_70715_bh.func_75776_a(7, (EntityAIBase)this.targetDeer);
      this.field_70715_bh.func_75776_a(7, (EntityAIBase)this.targetHorse);
    } else {

      this.peacefulAI = true;
    }
    this.hunger = 168000;
    this.animalID = TFC_Time.getTotalTicks() + func_145782_y();
    this.pregnant = false;
    this.pregnancyRequiredTime = (int)(TFCOptions.animalTimeMultiplier * 2.25F * (float)TFC_Time.ticksInMonth);
    this.timeOfConception = 0L;
    this.mateSizeMod = 1.0F;
    this.sex = this.field_70146_Z.nextInt(2);
    this.sizeMod = (float)Math.sqrt((((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(20) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F) * (1.0F - 0.0786F * this.sex)));
    this.strengthMod = (float)Math.sqrt(((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(10) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + this.sizeMod));
    this.aggressionMod = (float)Math.sqrt(((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(10) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F));
    this.obedienceMod = (float)Math.sqrt(((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(10) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F / this.aggressionMod));





    setAge(TFC_Time.getTotalDays() - getNumberOfDaysToAdult());
  }


  public EntityWolfTFC(World par1World, IAnimal mother, List<Float> data) {
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
    float invSizeRatio = 0.5204539F;
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
    if ((isMountainous && temp < 25.0F && temp > -10.0F && rain > 250.0F && evt < 0.75D) || (temp > 0.0F && temp < 21.0F && rain > 250.0F) || (temp > -20.0F && temp <= 0.0F && rain > 250.0F) || (temp > -20.0F && temp <= 0.0F && rain > 100.0F))
      return (TFCBiome.isGrass(this.field_70170_p.func_147439_a(i, j - 1, k)) && this.field_70170_p.func_72883_k(i, j, k) > 8 && TFCBiome.getCanSpawnHere((EntityLiving)this));
    return false;
  }


  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(2000.0D);
  }


  public boolean func_70652_k(Entity par1Entity) {
    int damage = (int)(200.0F * getStrengthMod() * getAggressionMod() * (getSizeMod() / 2.0F + 0.5F));

    return par1Entity.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), damage);
  }


  protected boolean func_70692_ba() {
    if (!isAdult())
      return false;
    if (func_70902_q() != null)
      return false;
    if (this.wasRoped) {
      return false;
    }
    return true;
  }


  public boolean canFamiliarize() {
    return (!isAdult() || (isAdult() && this.familiarity <= 35));
  }


  public boolean canMateWith(IAnimal animal) {
    return (animal.getGender() != getGender() && isAdult() && animal.isAdult() && animal instanceof EntityWolfTFC);
  }




  public boolean checkFamiliarity(IAnimal.InteractionEnum interaction, EntityPlayer player) {
    boolean flag = false;
    switch (interaction) {

      case BREED:
        flag = (this.familiarity > 20);
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



  public EntityWolf func_90011_a(EntityAgeable entityageable) {
    return (EntityWolf)createChildTFC(entityageable);
  }



  public EntityAgeable createChildTFC(EntityAgeable eAgeable) {
    ArrayList<Float> data = new ArrayList<>();
    data.add(Float.valueOf(eAgeable.getEntityData().func_74760_g("MateSize")));
    data.add(Float.valueOf(eAgeable.getEntityData().func_74760_g("MateStrength")));
    data.add(Float.valueOf(eAgeable.getEntityData().func_74760_g("MateAggro")));
    data.add(Float.valueOf(eAgeable.getEntityData().func_74760_g("MateObed")));
    return (EntityAgeable)new EntityWolfTFC(this.field_70170_p, this, data);
  }



  protected void func_70628_a(boolean par1, int par2) {
    float ageMod = TFC_Core.getPercentGrown(this);
    func_70099_a(new ItemStack(TFCItems.hide, 1, Math.max(0, Math.min(2, (int)((this.sizeMod * ageMod) * 0.9D)))), 0.0F);
    func_145779_a(Items.field_151103_aS, (int)((this.field_70146_Z.nextInt(3) + 1) * ageMod));
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
    if (this.happyTicks == 0 && this.familiarity >= 5 && !this.familiarizedToday && canFamiliarize()) {

      this.familiarizedToday = true;
      func_70671_ap().func_75651_a((Entity)ep, 0.0F, 0.0F);
      func_70642_aH();
      this.happyTicks = 40;
    }
    if (this.familiarity > 80 && func_70902_q() != null) {

      if (!func_70909_n()) ExpBonus.TAMED.give(ep);
      func_70903_f(true);
    }
  }


  public float getAggressionMod() {
    return this.aggressionMod;
  }


  public long getAnimalID() {
    return this.animalID;
  }



  public int getAnimalTypeID() {
    return Helper.stringToInt("wolf");
  }


  public Vec3 getAttackedVec() {
    return null;
  }


  public int getBirthDay() {
    return this.field_70180_af.func_75679_c(15);
  }


  public int getCrushArmor() {
    return 250;
  }



  public EnumDamageType getDamageType() {
    return EnumDamageType.SLASHING;
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



  public int getHappyTicks() {
    return this.happyTicks;
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
    return (int)(TFCOptions.animalTimeMultiplier * TFC_Time.daysInMonth * 9.0F);
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
    return 250;
  }



  public float getStrengthMod() {
    return this.strengthMod;
  }



  @SideOnly(Side.CLIENT)
  public float func_70920_v() {
    float scale = func_110138_aP() / 20.0F;
    if (func_70919_bu())
      return 1.5393804F;
    if (func_70902_q() != null) {
      return (0.55F - (func_110138_aP() - this.field_70180_af.func_111145_d(18)) / scale * 0.02F) * 3.1415927F;
    }
    return 0.62831855F;
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
        if (isAdult() && this.familiarity >= 5 && this.familiarity <= 35)
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
    if (this.familiarity < ((func_70902_q() != null) ? 5 : 0)) {
      this.familiarity = (func_70902_q() != null) ? 5 : 0;
    }
  }





  public boolean func_70085_c(EntityPlayer player) {
    if (!this.field_70170_p.field_72995_K) {

      if (player.func_70093_af() && func_70902_q() != null && canFamiliarize()) {

        familiarize(player);
        return true;
      }
      if (player.func_70694_bm() != null) {

        ItemStack is = player.func_70694_bm();
        if (isFood(is)) {

          Item item = is.func_77973_b();
          if (item instanceof ItemFoodTFC && this.hunger <= 160000) {

            player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, ((ItemFoodTFC)item).onConsumedByEntity(player.func_70694_bm(), this.field_70170_p, (EntityLivingBase)this));
            this.hunger += 24000;
            return true;
          }
        }
      }

      TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation((getGender() == IAnimal.GenderEnum.FEMALE) ? "entity.female" : "entity.male", new Object[0]));
      if (getGender() == IAnimal.GenderEnum.FEMALE && this.pregnant) {
        TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("entity.pregnant", new Object[0]));
      }
    }
    ItemStack itemstack = player.field_71071_by.func_70448_g();

    if (itemstack != null) {

      if (isBreedingItemTFC(itemstack) && checkFamiliarity(IAnimal.InteractionEnum.BREED, player) && func_70874_b() == 0 && !func_70880_s()) {

        if (!player.field_71075_bZ.field_75098_d)
        {
          player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, ((ItemFoodTFC)itemstack.func_77973_b()).onConsumedByEntity(player.func_70694_bm(), this.field_70170_p, (EntityLivingBase)this));
        }

        func_146082_f(player);
        return true;
      }
      if (itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.ItemCustomNameTag && itemstack.func_77942_o() && itemstack.field_77990_d.func_74764_b("ItemName")) {

        if (trySetName(itemstack.field_77990_d.func_74779_i("ItemName"), player))
        {
          itemstack.field_77994_a--;
        }
        return true;
      }
      if (itemstack.func_77973_b() == Items.field_151103_aS && !func_70919_bu()) {

        if (func_70902_q() == null) {

          if (!player.field_71075_bZ.field_75098_d)
          {
            itemstack.field_77994_a--;
          }

          if (itemstack.field_77994_a <= 0)
          {
            player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, (ItemStack)null);
          }

          if (!this.field_70170_p.field_72995_K)
          {
            if (this.field_70146_Z.nextInt(3) == 0) {

              func_70903_f(true);
              func_70778_a((PathEntity)null);
              func_70624_b((EntityLivingBase)null);
              func_152115_b(player.func_110124_au().toString());
              func_70908_e(true);
              this.field_70170_p.func_72960_a((Entity)this, (byte)7);
            }
            else {

              func_70908_e(false);
              this.field_70170_p.func_72960_a((Entity)this, (byte)6);
            }
          }
        }

        return true;
      }

      if (func_70909_n() && (itemstack.func_77973_b() == Items.field_151100_aR || itemstack.func_77973_b() == TFCItems.dye)) {

        int i = BlockColored.func_150032_b(itemstack.func_77960_j());

        if (i != func_82186_bH()) {

          func_82185_r(i);

          if (!player.field_71075_bZ.field_75098_d && --itemstack.field_77994_a <= 0)
          {
            player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, (ItemStack)null);
          }
        }

        return true;
      }
    }

    return super.func_70085_c(player);
  }



  public boolean isAdult() {
    return (getBirthDay() + getNumberOfDaysToAdult() <= TFC_Time.getTotalDays());
  }



  public boolean func_70877_b(ItemStack is) {
    return false;
  }


  public boolean isBreedingItemTFC(ItemStack item) {
    return (!this.pregnant && isFood(item));
  }



  public boolean func_70631_g_() {
    return !isAdult();
  }



  public boolean isFood(ItemStack item) {
    return (item != null && (item
      .func_77973_b() == TFCItems.beefRaw || item.func_77973_b() == TFCItems.chickenRaw || item.func_77973_b() == TFCItems.fishRaw || item
      .func_77973_b() == TFCItems.horseMeatRaw || item.func_77973_b() == TFCItems.muttonRaw || item.func_77973_b() == TFCItems.porkchopRaw || item
      .func_77973_b() == TFCItems.venisonRaw));
  }


  public boolean isPeacefulAI() {
    return this.peacefulAI;
  }



  public boolean isPregnant() {
    return this.pregnant;
  }


  public boolean hasBeenRoped() {
    return this.wasRoped;
  }



  public void mate(IAnimal otherAnimal) {
    if (getGender() == IAnimal.GenderEnum.MALE) {

      otherAnimal.mate(this);
      setInLove(false);
      func_70875_t();
      return;
    }
    this.timeOfConception = TFC_Time.getTotalTicks();
    this.pregnant = true;
    func_70875_t();
    setInLove(false);
    otherAnimal.setInLove(false);
    this.mateAggroMod = otherAnimal.getAggressionMod();
    this.mateObedMod = otherAnimal.getObedienceMod();
    this.mateSizeMod = otherAnimal.getSizeMod();
    this.mateStrengthMod = otherAnimal.getStrengthMod();
  }








  public void func_70636_d() {
    if (this.hunger > 168000)
      this.hunger = 168000;
    if (this.hunger > 0) {
      this.hunger--;
    }
    if (func_110167_bD()) {

      Entity leashedTo = func_110166_bE();

      if (leashedTo instanceof net.minecraft.entity.EntityLeashKnot && this.familiarity >= 5 && !func_70919_bu()) {

        this.field_70911_d.func_75270_a(true);
        func_70904_g(true);
        this.field_70703_bu = false;

        func_70778_a((PathEntity)null);
        func_70784_b((Entity)null);
        func_70624_b((EntityLivingBase)null);

      }
      else if (leashedTo instanceof EntityPlayer || (leashedTo instanceof net.minecraft.entity.EntityLeashKnot && func_70919_bu())) {

        this.field_70911_d.func_75270_a(false);
        func_70904_g(false);
      }

      if (!this.wasRoped) {
        this.wasRoped = true;
      }
    }
    else if (func_70919_bu() && func_70906_o()) {

      this.field_70911_d.func_75270_a(false);
      func_70904_g(false);
    }

    if (func_70880_s()) {
      setInLove(true);
    }
    if (isAdult()) {
      func_70873_a(0);
    } else {
      func_70873_a(-1);
    }
    handleFamiliarityUpdate();

    if (this.happyTicks > 0) {
      this.happyTicks--;
    }
    syncData();

    if (!this.field_70170_p.field_72995_K && isPregnant())
    {
      if (TFC_Time.getTotalTicks() >= this.timeOfConception + this.pregnancyRequiredTime) {

        List<?> around_wolfs = this.field_70170_p.func_72872_a(EntityWolfTFC.class, AxisAlignedBB.func_72330_a(this.field_70165_t - 50.0D, this.field_70163_u - 50.0D, this.field_70161_v - 50.0D, this.field_70165_t + 50.0D, this.field_70163_u + 50.0D, this.field_70161_v + 50.0D));

        if (around_wolfs.size() < 10) {

          int i = this.field_70146_Z.nextInt(2) + 1;
          for (int x = 0; x < i; x++) {

            ArrayList<Float> data = new ArrayList<>();
            data.add(Float.valueOf(this.mateSizeMod));
            EntityWolfTFC baby = (EntityWolfTFC)createChildTFC((EntityAgeable)this);
            baby.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0F, 0.0F);
            baby.field_70759_as = baby.field_70177_z;
            baby.field_70761_aq = baby.field_70177_z;
            baby.setAge(TFC_Time.getTotalDays());
            this.field_70170_p.func_72838_d((Entity)baby);
          }
        }
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


    if (func_110167_bD() && func_70919_bu() && func_110166_bE() == func_70902_q()) {

      func_70916_h(false);
      func_70778_a((PathEntity)null);
      func_70784_b((Entity)null);
      func_70624_b((EntityLivingBase)null);
    }
  }



  public void func_70071_h_() {
    super.func_70071_h_();
    if (!this.field_70170_p.field_72995_K)
    {
      if (!this.peacefulAI && this.field_70170_p.field_73013_u == EnumDifficulty.PEACEFUL) {

        this.peacefulAI = true;
        this.field_70715_bh.func_85156_a((EntityAIBase)this.targetChicken);
        this.field_70715_bh.func_85156_a((EntityAIBase)this.targetPheasant);
        this.field_70715_bh.func_85156_a((EntityAIBase)this.targetPig);

        this.field_70715_bh.func_85156_a((EntityAIBase)this.targetDeer);
        this.field_70715_bh.func_85156_a((EntityAIBase)this.targetHorse);
      }
      else if (this.peacefulAI && this.field_70170_p.field_73013_u != EnumDifficulty.PEACEFUL) {

        this.peacefulAI = false;
        this.field_70715_bh.func_75776_a(7, (EntityAIBase)this.targetChicken);
        this.field_70715_bh.func_75776_a(7, (EntityAIBase)this.targetPheasant);
        this.field_70715_bh.func_75776_a(7, (EntityAIBase)this.targetPig);

        this.field_70715_bh.func_75776_a(7, (EntityAIBase)this.targetDeer);
        this.field_70715_bh.func_75776_a(7, (EntityAIBase)this.targetHorse);
      }
    }
  }






  public void func_70037_a(NBTTagCompound nbt) {
    super.func_70037_a(nbt);
    func_70916_h(nbt.func_74767_n("Angry"));
    this.animalID = nbt.func_74763_f("Animal ID");
    this.sex = nbt.func_74762_e("Sex");
    this.sizeMod = nbt.func_74760_g("Size Modifier");

    this.familiarity = nbt.func_74762_e("Familiarity");
    this.lastFamiliarityUpdate = nbt.func_74763_f("lastFamUpdate");
    this.familiarizedToday = nbt.func_74767_n("Familiarized Today");

    this.strengthMod = nbt.func_74760_g("Strength Modifier");
    this.aggressionMod = nbt.func_74760_g("Aggression Modifier");
    this.obedienceMod = nbt.func_74760_g("Obedience Modifier");

    this.field_70180_af.func_75692_b(16, Byte.valueOf(nbt.func_74771_c("tamed")));
    this.happyTicks = nbt.func_74762_e("happy");

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

  public void setHappyTicks(int happyTicks) {
    this.happyTicks = happyTicks;
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


  public void setPeacefulAI(boolean isPeacefulAI) {
    this.peacefulAI = isPeacefulAI;
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



  public void func_70903_f(boolean par1) {
    if (this.familiarity > 80 && !func_70909_n()) {

      super.func_70903_f(par1);

      double healthRatio = func_110143_aJ() / func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b();

      func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(2000.0D);
      float h = (float)(healthRatio * func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b());
      func_70606_j(h);
    }
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

        byte[] values = { TFC_Core.getByteFromSmallFloat(this.sizeMod), TFC_Core.getByteFromSmallFloat(this.strengthMod), TFC_Core.getByteFromSmallFloat(this.aggressionMod), TFC_Core.getByteFromSmallFloat(this.obedienceMod), (byte)this.familiarity, (byte)(this.familiarizedToday ? 1 : 0), (byte)(this.pregnant ? 1 : 0), (byte)this.happyTicks };


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
        this.happyTicks = values[7];


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
    func_85030_a("mob.wolf.growl", 6.0F, this.field_70146_Z.nextFloat() / 2.0F + (func_70631_g_() ? 1.25F : 0.75F));
    return false;
  }






  public void func_70014_b(NBTTagCompound nbt) {
    super.func_70014_b(nbt);
    nbt.func_74757_a("Angry", func_70919_bu());
    nbt.func_74768_a("Familiarity", this.familiarity);
    nbt.func_74772_a("lastFamUpdate", this.lastFamiliarityUpdate);
    nbt.func_74757_a("Familiarized Today", this.familiarizedToday);
    nbt.func_74768_a("Sex", this.sex);
    nbt.func_74772_a("Animal ID", this.animalID);
    nbt.func_74776_a("Size Modifier", this.sizeMod);

    nbt.func_74774_a("tamed", this.field_70180_af.func_75683_a(16));
    nbt.func_74768_a("happy", this.happyTicks);

    nbt.func_74757_a("wasRoped", this.wasRoped);

    nbt.func_74776_a("Strength Modifier", getStrengthMod());
    nbt.func_74776_a("Aggression Modifier", getAggressionMod());
    nbt.func_74776_a("Obedience Modifier", this.obedienceMod);

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


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\Mobs\EntityWolfTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */