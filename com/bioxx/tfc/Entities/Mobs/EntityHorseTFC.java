package com.bioxx.tfc.Entities.Mobs;
import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.Entities.AI.AIEatGrass;
import com.bioxx.tfc.Entities.AI.EntityAIAvoidEntityTFC;
import com.bioxx.tfc.Entities.AI.EntityAIMateTFC;
import com.bioxx.tfc.Entities.AI.EntityAIPanicTFC;
import com.bioxx.tfc.Food.ItemFoodTFC;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.WorldGen.TFCBiome;
import com.bioxx.tfc.api.Entities.IAnimal;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.TFCOptions;
import com.bioxx.tfc.api.Util.Helper;
import fof.tfcsu.Entity.EntityBear3D;
import fof.tfcsu.Weight.InventoryWeight;
import fof.tfcsu.utils.ExpBonus;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIRunAroundLikeCrazy;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.inventory.AnimalChest;
import net.minecraft.inventory.IInvBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S1BPacketEntityAttach;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityHorseTFC extends EntityHorse implements IInvBasic, IAnimal {
  private static final IEntitySelector HORSE_BREEDING_SELECTOR = new EntityHorseBredSelector();
  private static final IAttribute HORSE_JUMP_STRENGTH = (IAttribute)(new RangedAttribute("horse.jumpStrengthTFC", 0.7D, 0.0D, 2.0D)).func_111117_a("Jump StrengthTFC").func_111112_a(true);

  private static final float GESTATION_PERIOD = 11.17F;

  private static final float DIMORPHISM = 0.0813F;

  private static final int DEGREE_OF_DIVERSION = 2;

  private static final int FAMILIARITY_CAP = 35;

  protected final AIEatGrass aiEatGrass = new AIEatGrass(this);

  private long animalID;
  private int sex;
  private int hunger;
  private boolean pregnant;
  private int pregnancyRequiredTime;
  private long timeOfConception;
  private float sizeMod;
  private float strengthMod;
  private float aggressionMod = 1.0F;
  private float obedienceMod = 1.0F;

  private float mateSizeMod;
  private float mateStrengthMod;
  private float mateAggroMod;
  private float mateObedMod;
  private int mateType;
  private int mateVariant;
  private double mateMaxHealth = calcMaxHealth();
  private double mateJumpStrength = calcJumpStrength();
  private double mateMoveSpeed = calcMoveSpeed();

  private boolean inLove;

  private Vec3 attackedVec;
  private Entity fearSource;
  private int familiarity;
  private long lastFamiliarityUpdate;
  private boolean familiarizedToday;

  public EntityHorseTFC(World par1World) {
    super(par1World);
    this.animalID = TFC_Time.getTotalTicks() + func_145782_y();
    this.hunger = 168000;
    this.pregnant = false;
    this.pregnancyRequiredTime = (int)(TFCOptions.animalTimeMultiplier * 11.17F * (float)TFC_Time.ticksInMonth);
    this.timeOfConception = 0L;
    this.sex = this.field_70146_Z.nextInt(2);
    this.sizeMod = (float)Math.sqrt((((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(30) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F) * (1.0F - 0.0813F * this.sex)));
    this.strengthMod = (float)Math.sqrt(((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(20) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + this.sizeMod));
    this.aggressionMod = (float)Math.sqrt(((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(20) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F));
    this.obedienceMod = (float)Math.sqrt(((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(20) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F / this.aggressionMod));
    func_70105_a(1.4F, 1.6F);
    func_70661_as().func_75491_a(true);
    this.field_70714_bg.field_75782_a.clear();
    this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAIRunAroundLikeCrazy(this, 1.2D));
    this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIFollowParent((EntityAnimal)this, 1.0D));
    this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIWander((EntityCreature)this, 0.7D));
    this.field_70714_bg.func_75776_a(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0F));
    this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
    this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIMateTFC(this, this.field_70170_p, 1.0F));
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAvoidEntityTFC((EntityCreature)this, EntityWolfTFC.class, 8.0F, 0.5D, 0.699999988079071D));
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAvoidEntityTFC((EntityCreature)this, EntityBear3D.class, 16.0F, 0.25D, 0.30000001192092896D));
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.wheatGrain, false));
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.ryeGrain, false));
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.riceGrain, false));
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.barleyGrain, false));
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.oatGrain, false));
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.maizeEar, false));
    this.field_70714_bg.func_75776_a(6, (EntityAIBase)this.aiEatGrass);
    this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAIPanicTFC((EntityCreature)this, 1.2D, true));
    updateChestSaddle();





    setAge(TFC_Time.getTotalDays() - getNumberOfDaysToAdult());
  }




  public EntityHorseTFC(World par1World, IAnimal mother, List<Float> data, int type, int variant) {
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
    float invSizeRatio = 0.52118623F;
    this.sizeMod = (float)Math.sqrt((this.sizeMod * this.sizeMod * (float)Math.sqrt(((mother.getSizeMod() + fatherSize) * invSizeRatio))));
    this.strengthMod = (float)Math.sqrt((this.strengthMod * this.strengthMod * (float)Math.sqrt(((mother.getStrengthMod() + fatherStr) * 0.5F))));
    this.aggressionMod = (float)Math.sqrt((this.aggressionMod * this.aggressionMod * (float)Math.sqrt(((mother.getAggressionMod() + fatherAggro) * 0.5F))));
    this.obedienceMod = (float)Math.sqrt((this.obedienceMod * this.obedienceMod * (float)Math.sqrt(((mother.getObedienceMod() + fatherObed) * 0.5F))));

    this.familiarity = (int)((mother.getFamiliarity() < 90) ? (mother.getFamiliarity() / 2) : (mother.getFamiliarity() * 0.9F));


    setAge(TFC_Time.getTotalDays());
    func_110214_p(type);
    func_110235_q(variant);
  }




  public boolean func_70601_bi() {
    int i = MathHelper.func_76128_c(this.field_70165_t);
    int j = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
    int k = MathHelper.func_76128_c(this.field_70161_v);
    float temp = TFC_Climate.getHeightAdjustedTemp(this.field_70170_p, i, this.field_70170_p.func_72825_h(i, k), k);
    float rain = TFC_Climate.getRainfall(this.field_70170_p, i, 150, k);
    TFCBiome biome = (TFCBiome)this.field_70170_p.func_72959_q().func_76935_a(i, k);
    boolean isMountainous = (biome == TFCBiome.MOUNTAINS || biome == TFCBiome.HIGH_HILLS);
    if (!isMountainous && temp > 0.0F && temp < 30.0F && rain > 100.0F && rain <= 500.0F) {

      func_110262_ch();
      return (TFCBiome.isGrass(this.field_70170_p.func_147439_a(i, j - 1, k)) && this.field_70170_p.func_72883_k(i, j, k) > 8 && TFCBiome.getCanSpawnHere((EntityLiving)this));
    }
    return false;
  }



  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110140_aT().func_111150_b(HORSE_JUMP_STRENGTH);
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a((1250.0F * this.sizeMod * this.strengthMod));
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.22499999403953552D * this.strengthMod * this.obedienceMod / (this.sizeMod * this.sizeMod));
    func_70606_j(func_110138_aP());
  }


  private double calcJumpStrength() {
    return 0.4000000059604645D + this.field_70146_Z.nextDouble() * 0.2D + this.field_70146_Z.nextDouble() * 0.2D + this.field_70146_Z.nextDouble() * 0.2D;
  }


  private float calcMaxHealth() {
    return 1000.0F + this.field_70146_Z.nextInt(101) + this.field_70146_Z.nextInt(151);
  }


  private double calcMoveSpeed() {
    return (0.44999998807907104D + this.field_70146_Z.nextDouble() * 0.3D + this.field_70146_Z.nextDouble() * 0.3D + this.field_70146_Z.nextDouble() * 0.3D) * 0.25D;
  }



  public boolean canFamiliarize() {
    return (!isAdult() || (isAdult() && this.familiarity <= 35));
  }






  public boolean func_70878_b(EntityAnimal animal) {
    if (animal == this)
    {
      return false;
    }
    if (animal.getClass() != getClass())
    {
      return false;
    }


    EntityHorseTFC entityhorse = (EntityHorseTFC)animal;

    if (getBreedable() && entityhorse.getBreedable()) {

      int i = func_110265_bP();
      int j = entityhorse.func_110265_bP();
      return (i == j || (i == 0 && j == 1) || (i == 1 && j == 0));
    }


    return false;
  }





  public boolean canMateWith(IAnimal animal) {
    return (animal.getGender() != getGender() && isAdult() && animal.isAdult() && animal instanceof EntityHorseTFC);
  }



  public boolean checkFamiliarity(IAnimal.InteractionEnum interaction, EntityPlayer player) {
    boolean flag = false;
    switch (interaction) { case MOUNT:
        flag = (this.familiarity > 15); break;
      case BREED: flag = (this.familiarity > 20); break;
      case NAME: flag = (this.familiarity > 40);
        break; }

    if (!flag && player != null && !player.field_70170_p.field_72995_K) {
      TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("entity.notFamiliar", new Object[0]));
    }
    return flag;
  }



  public void func_110160_i(boolean par1, boolean par2) {
    Entity entity = func_110166_bE();
    if (entity instanceof EntityPlayer) {


      if (entity.func_70093_af()) {
        super.func_110160_i(par1, true);
      }
    } else {

      super.func_110160_i(par1, true);
    }
  }



  public EntityAgeable func_90011_a(EntityAgeable eAgeable) {
    return createChildTFC(eAgeable);
  }



  public EntityAgeable createChildTFC(EntityAgeable eAgeable) {
    ArrayList<Float> data = new ArrayList<>();
    data.add(Float.valueOf(this.mateSizeMod));
    data.add(Float.valueOf(this.mateStrengthMod));
    data.add(Float.valueOf(this.mateAggroMod));
    data.add(Float.valueOf(this.mateObedMod));

    int momType = func_110265_bP();
    int dadType = this.mateType;
    int babyType = 0;
    int babyVariant = 0;

    if (momType == dadType) {

      babyType = momType;
    }
    else if ((momType == 0 && dadType == 1) || (momType == 1 && dadType == 0)) {

      babyType = 2;
    }

    if (babyType == 0) {

      int l = this.field_70146_Z.nextInt(9);

      if (l < 4) { babyVariant = func_110202_bQ() & 0xFF; }
      else if (l < 8) { babyVariant = this.mateVariant & 0xFF; }
      else { babyVariant = this.field_70146_Z.nextInt(7); }

      int j1 = this.field_70146_Z.nextInt(5);

      if (j1 < 4) { babyVariant |= func_110202_bQ() & 0xFF00; }
      else if (j1 < 8) { babyVariant |= this.mateVariant & 0xFF00; }
      else { babyVariant |= this.field_70146_Z.nextInt(5) << 8 & 0xFF00; }

    }
    EntityHorseTFC baby = new EntityHorseTFC(this.field_70170_p, this, data, babyType, babyVariant);





    double health = formula(func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b(), this.mateMaxHealth);
    baby.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a((health > 1262.0D) ? 1252.0D : health);





    double jumpstr = formula(func_110148_a(HORSE_JUMP_STRENGTH).func_111125_b(), this.mateJumpStrength);
    baby.func_110148_a(HORSE_JUMP_STRENGTH).func_111128_a((jumpstr > 1.0D) ? 1.0D : jumpstr);





    double speed = formula(func_110148_a(SharedMonsterAttributes.field_111263_d).func_111125_b(), this.mateMoveSpeed);
    baby.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a((speed > 0.3374999970197678D) ? 0.3374999970197678D : speed);


    baby.func_70606_j((float)baby.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b());
    return (EntityAgeable)baby;
  }


  private double formula(double par1, double par2) {
    double kk = this.field_70170_p.field_73012_v.nextDouble() / 2.0D + 0.05D;
    return (par1 + par2) / 2.0D * (0.7D + kk);
  }





  public void func_110224_ci() {
    if (!this.field_70170_p.field_72995_K && func_110261_ca())
    {
      func_110207_m(false);
    }
  }



  protected void func_70628_a(boolean par1, int par2) {
    float ageMod = TFC_Core.getPercentGrown(this);

    func_70099_a(new ItemStack(TFCItems.hide, 1, Math.max(0, Math.min(2, (int)(ageMod * 3.0F - 1.0F)))), 0.0F);
    func_145779_a(Items.field_151103_aS, (int)((this.field_70146_Z.nextInt(8) + 3) * ageMod));
    if (func_110167_bD()) {
      func_70099_a(new ItemStack(TFCItems.rope), 0.0F);
    }

    float foodWeight = ageMod * this.sizeMod * 4000.0F / 2.0F;

    TFC_Core.animalDropMeat((Entity)this, TFCItems.horseMeatRaw, foodWeight);
  }



  protected void func_70088_a() {
    super.func_70088_a();
    this.field_70180_af.func_75682_a(13, Integer.valueOf(0));
    this.field_70180_af.func_75682_a(15, Integer.valueOf(0));

    this.field_70180_af.func_75682_a(26, Integer.valueOf(0));
    this.field_70180_af.func_75682_a(24, Integer.valueOf(0));
    this.field_70180_af.func_75682_a(25, String.valueOf("0"));
  }



  public void familiarize(EntityPlayer ep) {
    ItemStack stack = ep.func_70694_bm();
    if ((this.field_70153_n == null || !this.field_70153_n.equals(ep)) && !this.familiarizedToday && stack != null && isFood(stack) && canFamiliarize()) {

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
    if (this.field_70153_n != null && this.field_70153_n.equals(ep) && isAdult()) {

      this.familiarizedToday = true;
      func_70671_ap().func_75651_a((Entity)ep, 0.0F, 0.0F);
      func_70642_aH();
    }
  }



  public float getAggressionMod() {
    return this.aggressionMod;
  }



  public int getAnimalTypeID() {
    return Helper.stringToInt("horse");
  }



  public Vec3 getAttackedVec() {
    return this.attackedVec;
  }



  public int getBirthDay() {
    return this.field_70180_af.func_75679_c(15);
  }


  private boolean getBreedable() {
    return (this.field_70153_n == null && this.field_70154_o == null && func_110248_bS() && func_110228_bR() &&
      !func_110222_cv() && func_110143_aJ() >= func_110138_aP());
  }




  protected EntityHorseTFC getClosestHorse(Entity entity, double range) {
    double maxDistance = Double.MAX_VALUE;
    EntityHorseTFC closestHorse = null;
    List<EntityHorseTFC> list = this.field_70170_p.func_94576_a(entity, entity.field_70121_D.func_72321_a(range, range, range), HORSE_BREEDING_SELECTOR);
    Iterator<EntityHorseTFC> iterator = list.iterator();

    while (iterator.hasNext()) {

      EntityHorseTFC nearbyHorse = iterator.next();
      double distance = nearbyHorse.func_70092_e(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v);
      if (distance < maxDistance) {

        closestHorse = nearbyHorse;
        maxDistance = distance;
      }
    }

    return closestHorse;
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
    return this.fearSource;
  }



  public IAnimal.GenderEnum getGender() {
    return IAnimal.GenderEnum.GENDERS[this.field_70180_af.func_75679_c(13)];
  }


  public AnimalChest getHorseChest() {
    return this.field_110296_bG;
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




  public boolean func_110167_bD() {
    if (super.func_110167_bD() && func_110166_bE() instanceof EntityPlayer && ((EntityPlayer)
      func_110166_bE()).field_71071_by.func_70448_g() == null && func_110174_bM() != -1.0F)
    {
      return false;
    }
    return super.func_110167_bD();
  }



  public int func_110218_cm() {
    return (int)(500.0F * this.aggressionMod);
  }






  public double func_70042_X() {
    float offset = this.sizeMod * this.field_70131_O * 0.75F;
    int type = func_110265_bP();

    if (type == 1) {
      offset *= 0.87F;
    } else if (type == 2) {
      offset *= 0.92F;
    }
    return offset;
  }



  public int getNumberOfDaysToAdult() {
    return (int)(TFCOptions.animalTimeMultiplier * TFC_Time.daysInMonth * 30.0F);
  }


  private int getNumChestSlots() {
    int i = func_110265_bP();
    return (func_110261_ca() && (i == 1 || i == 2)) ? 17 : 2;
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
    if (this.lastFamiliarityUpdate < totalDays) {
      if (this.familiarizedToday && this.familiarity < 100) {
        this.lastFamiliarityUpdate = totalDays;
        this.familiarizedToday = false;
        float familiarityChange = 6.0F * this.obedienceMod / this.aggressionMod;
        if (isAdult() && this.familiarity <= 35) {

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


  public int func_110198_t(int temper) {
    temper *= 5;
    int j = MathHelper.func_76125_a(func_110252_cg() + (int)(temper * this.obedienceMod * 1.0F / this.aggressionMod), 0, func_110218_cm());
    func_110238_s(j);
    return j;
  }






  public boolean func_70085_c(EntityPlayer player) {
    ItemStack itemstack = player.field_71071_by.func_70448_g();
    if (!this.field_70170_p.field_72995_K) {

      if (player.func_70093_af() && !this.familiarizedToday && itemstack != null && canFamiliarize()) {

        familiarize(player);
        return true;
      }
      TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation((getGender() == IAnimal.GenderEnum.FEMALE) ? "entity.female" : "entity.male", new Object[0]));
      if (getGender() == IAnimal.GenderEnum.FEMALE && this.pregnant) {
        TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("entity.pregnant", new Object[0]));
      }
    }

    if (itemstack != null && isBreedingItemTFC(itemstack) && checkFamiliarity(IAnimal.InteractionEnum.BREED, player) && func_70874_b() == 0 && !func_70880_s() && (this.familiarizedToday ||
      !canFamiliarize())) {

      if (!player.field_71075_bZ.field_75098_d)
        player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, ((ItemFoodTFC)itemstack.func_77973_b()).onConsumedByEntity(player.func_70694_bm(), this.field_70170_p, (EntityLivingBase)this));
      this.hunger += 24000;
      setInLove(true);
      return true;
    }
    if (itemstack != null && itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.ItemCustomNameTag && itemstack.func_77942_o() && itemstack.field_77990_d.func_74764_b("ItemName")) {
      if (trySetName(itemstack.field_77990_d.func_74779_i("ItemName"), player)) {
        itemstack.field_77994_a--;
      }
      return true;
    }
    if (itemstack != null && itemstack.func_77973_b() == Items.field_151063_bx)
    {
      return super.func_70085_c(player);
    }
    if (func_110248_bS() && func_110228_bR() && player.func_70093_af() && !func_110167_bD()) {

      func_110199_f(player);
      return true;
    }
    if (func_110248_bS() && func_110228_bR() && player.func_70093_af() && func_110167_bD()) {

      func_110160_i(true, true);
      return true;
    }
    if (func_110228_bR() && this.field_70153_n != null)
    {
      return super.func_70085_c(player);
    }


    if (itemstack != null) {

      if (!func_110248_bS()) {

        if (itemstack.func_111282_a(player, (EntityLivingBase)this))
        {
          return true;
        }

        func_110231_cz();
      }

      boolean flag = false;

      if (func_110229_cs() && !func_110261_ca() && itemstack.func_77973_b() == Item.func_150898_a(TFCBlocks.chest)) {

        func_110207_m(true);
        func_85030_a("mob.chickenplop", 1.0F, (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 1.0F);
        flag = true;
        updateChestSaddle();
      }

      if (flag) {

        if (!player.field_71075_bZ.field_75098_d && --itemstack.field_77994_a == 0)
        {
          player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, (ItemStack)null);
        }

        return true;
      }
    }
    if (func_110228_bR() && this.field_70153_n == null) {

      if (itemstack != null && itemstack.func_111282_a(player, (EntityLivingBase)this))
      {
        return true;
      }


      if (func_110166_bE() instanceof EntityPlayer && func_110166_bE() == player)
      {
        mountHorse(player);
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



  public boolean isPregnant() {
    return this.pregnant;
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
    this.mateSizeMod = otherAnimal.getSizeMod();
    this.mateStrengthMod = otherAnimal.getStrengthMod();
    this.mateAggroMod = otherAnimal.getAggressionMod();
    this.mateObedMod = otherAnimal.getObedienceMod();
    this.mateType = ((EntityHorse)otherAnimal).func_110265_bP();
    this.mateVariant = ((EntityHorse)otherAnimal).func_110202_bQ();
    this.mateMaxHealth = ((EntityLivingBase)otherAnimal).func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b();
    this.mateJumpStrength = ((EntityLivingBase)otherAnimal).func_110148_a(HORSE_JUMP_STRENGTH).func_111125_b();
    this.mateMoveSpeed = ((EntityLivingBase)otherAnimal).func_110148_a(SharedMonsterAttributes.field_111263_d).func_111125_b();
  }


  private void mountHorse(EntityPlayer player) {
    player.field_70177_z = this.field_70177_z;
    player.field_70125_A = this.field_70125_A;
    func_110227_p(false);
    func_110219_q(false);

    if (!this.field_70170_p.field_72995_K && checkFamiliarity(IAnimal.InteractionEnum.MOUNT, player)) {
      player.func_70078_a((Entity)this);
    }
  }


  public void func_70636_d() {
    if (func_110215_cj() == 0.7D) func_110148_a(HORSE_JUMP_STRENGTH).func_111128_a(super.func_110215_cj());

    if (this.hunger > 168000)
      this.hunger = 168000;
    if (this.hunger > 0) {
      this.hunger--;
    }
    if (this.field_70153_n != null && this.field_70153_n instanceof EntityPlayer && this.field_70146_Z.nextInt(600) == 0 && !this.familiarizedToday && canFamiliarize())
    {
      familiarize((EntityPlayer)this.field_70153_n);
    }

    syncData();
    if (isAdult()) {
      func_70873_a(0);
    } else {
      func_70873_a(-1);
    }
    handleFamiliarityUpdate();
    if (!this.field_70170_p.field_72995_K && isPregnant())
    {
      if (TFC_Time.getTotalTicks() >= this.timeOfConception + this.pregnancyRequiredTime) {

        EntityHorseTFC baby = (EntityHorseTFC)createChildTFC((EntityAgeable)this);
        baby.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0F, 0.0F);
        baby.field_70759_as = baby.field_70177_z;
        baby.field_70761_aq = baby.field_70177_z;
        this.field_70170_p.func_72838_d((Entity)baby);
        baby.setAge(TFC_Time.getTotalDays());
        this.pregnant = false;
      }
    }

    if (this.field_70170_p.func_82737_E() % 20L == 0L &&
      isOverweight()) {
      func_110244_cA();
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



  public IEntityLivingData func_110161_a(IEntityLivingData livingData) {
    IEntityLivingData data = super.func_110161_a(livingData);
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1250.0D);
    func_110148_a(HORSE_JUMP_STRENGTH).func_111128_a(super.func_110215_cj());
    func_70691_i(1250.0F);
    return data;
  }




  public void func_110199_f(EntityPlayer player) {
    Entity temp = player.field_70154_o;

    if (temp == null) {

      player.field_70154_o = (Entity)this;
      if (player instanceof EntityPlayerMP) {

        EntityPlayerMP playerMP = (EntityPlayerMP)player;
        playerMP.field_71135_a.func_147359_a((Packet)new S1BPacketEntityAttach(0, (Entity)playerMP, (Entity)this));
        openHorseContainer(player);
        playerMP.field_71135_a.func_147359_a((Packet)new S1BPacketEntityAttach(0, (Entity)playerMP, temp));
      }
      else {

        openHorseContainer(player);
      }
      player.field_70154_o = null;
    }

    if (temp != null && temp == this)
    {
      openHorseContainer(player);
    }
  }


  private void openHorseContainer(EntityPlayer player) {
    if (!this.field_70170_p.field_72995_K && (this.field_70153_n == null || this.field_70153_n == player) && func_110248_bS())
    {
      player.openGui(TerraFirmaCraft.instance, 42, player.field_70170_p, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v);
    }
  }






  public void func_70037_a(NBTTagCompound nbttc) {
    super.func_70037_a(nbttc);
    NBTTagCompound nbt = nbttc;
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
    this.mateType = nbt.func_74762_e("MateType");
    this.mateVariant = nbt.func_74762_e("MateVariant");
    this.mateMaxHealth = nbt.func_74769_h("MateHealth");
    this.mateJumpStrength = nbt.func_74769_h("MateJump");
    this.mateMoveSpeed = nbt.func_74769_h("MateSpeed");
    this.timeOfConception = nbt.func_74763_f("ConceptionTime");
    setAge(nbt.func_74762_e("Age"));

    if (func_110261_ca()) {

      NBTTagList nbttaglist = nbttc.func_150295_c("Items", 10);
      updateChestSaddle();

      for (int i = 0; i < nbttaglist.func_74745_c(); i++) {

        NBTTagCompound nbt1 = nbttaglist.func_150305_b(i);
        int j = nbt1.func_74771_c("Slot") & 0xFF;

        if (j >= 2 && j < this.field_110296_bG.func_70302_i_())
        {
          this.field_110296_bG.func_70299_a(j, ItemStack.func_77949_a(nbt1));
        }
      }
    }



    if (nbttc.func_150297_b("ArmorItem", 10)) {

      ItemStack itemstack = ItemStack.func_77949_a(nbttc.func_74775_l("ArmorItem"));

      if (itemstack != null && EntityHorse.func_146085_a(itemstack.func_77973_b()))
      {
        this.field_110296_bG.func_70299_a(1, itemstack);
      }
    }

    if (nbttc.func_150297_b("SaddleItem", 10)) {

      ItemStack itemstack = ItemStack.func_77949_a(nbttc.func_74775_l("SaddleItem"));
      if (itemstack != null && itemstack.func_77973_b() == Items.field_151141_av)
      {
        this.field_110296_bG.func_70299_a(0, itemstack);
      }
    }
    else if (nbttc.func_74767_n("Saddle")) {

      this.field_110296_bG.func_70299_a(0, new ItemStack(Items.field_151141_av));
    }

    updateSaddle();
  }




  public void setAge(int par1) {
    this.field_70180_af.func_75692_b(15, Integer.valueOf(par1));
  }




  public void setAggressionMod(float aggressionMod) {
    this.aggressionMod = aggressionMod;
  }



  public void setAttackedVec(Vec3 attackedVec) {
    this.attackedVec = attackedVec;
  }



  public void setBirthDay(int day) {
    this.field_70180_af.func_75692_b(15, Integer.valueOf(day));
  }



  public void setFamiliarity(int familiarity) {
    this.familiarity = familiarity;
  }


  public void setFamiliarizedToday(boolean familiarizedToday) {
    this.familiarizedToday = familiarizedToday;
  }



  public void setFearSource(Entity fearSource) {
    this.fearSource = fearSource;
  }



  public void func_70873_a(int par1) {
    if (!TFC_Core.preventEntityDataUpdate)
    {
      this.field_70180_af.func_75692_b(12, Integer.valueOf(par1));
    }
  }



  public void setHunger(int h) {
    this.hunger = h;
  }



  public void setInLove(boolean b) {
    this.inLove = b;
    if (b) {

      this.field_70789_a = null;
      this.field_70170_p.func_72960_a((Entity)this, (byte)18);
    }
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





        byte[] values = { TFC_Core.getByteFromSmallFloat(this.sizeMod), TFC_Core.getByteFromSmallFloat(this.strengthMod), TFC_Core.getByteFromSmallFloat(this.aggressionMod), TFC_Core.getByteFromSmallFloat(this.obedienceMod), (byte)this.familiarity, (byte)(this.familiarizedToday ? 1 : 0), (byte)(this.pregnant ? 1 : 0), 0 };





        ByteBuffer buf = ByteBuffer.wrap(values);
        this.field_70180_af.func_75692_b(26, Integer.valueOf(buf.getInt()));
        this.field_70180_af.func_75692_b(24, Integer.valueOf(buf.getInt()));
        this.field_70180_af.func_75692_b(25, String.valueOf(this.timeOfConception));
      }
      else {

        this.sex = this.field_70180_af.func_75679_c(13);

        ByteBuffer buf = ByteBuffer.allocate(8);
        buf.putInt(this.field_70180_af.func_75679_c(26));
        buf.putInt(this.field_70180_af.func_75679_c(24));
        byte[] values = buf.array();

        this.sizeMod = TFC_Core.getSmallFloatFromByte(values[0]);
        this.strengthMod = TFC_Core.getSmallFloatFromByte(values[1]);
        this.aggressionMod = TFC_Core.getSmallFloatFromByte(values[2]);
        this.obedienceMod = TFC_Core.getSmallFloatFromByte(values[3]);

        this.familiarity = values[4];
        this.familiarizedToday = (values[5] == 1);
        this.pregnant = (values[6] == 1);


        try {
          this.timeOfConception = Long.parseLong(this.field_70180_af.func_75681_e(25));
        } catch (NumberFormatException numberFormatException) {}
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


  public void updateChestSaddle() {
    AnimalChest animalchest = this.field_110296_bG;
    this.field_110296_bG = new AnimalChest("HorseChest", getNumChestSlots());

    if (animalchest != null) {

      animalchest.func_110132_b(this);
      int i = Math.min(animalchest.func_70302_i_(), this.field_110296_bG.func_70302_i_());
      for (int j = 0; j < i; j++) {

        ItemStack itemstack = animalchest.func_70301_a(j);
        if (itemstack != null)
          this.field_110296_bG.func_70299_a(j, itemstack.func_77946_l());
      }
      animalchest = null;
    }

    this.field_110296_bG.func_110134_a(this);
    updateSaddle();
  }


  private void updateSaddle() {
    if (!this.field_70170_p.field_72995_K) {

      func_110251_o((this.field_110296_bG.func_70301_a(0) != null));
      if (func_110265_bP() == 0) {
        func_146086_d(this.field_110296_bG.func_70301_a(1));
      }
    }
  }





  public void func_70014_b(NBTTagCompound nbttc) {
    super.func_70014_b(nbttc);
    nbttc.func_74768_a("Sex", this.sex);
    nbttc.func_74772_a("Animal ID", this.animalID);
    nbttc.func_74776_a("Size Modifier", this.sizeMod);

    nbttc.func_74768_a("Familiarity", this.familiarity);
    nbttc.func_74772_a("lastFamUpdate", this.lastFamiliarityUpdate);
    nbttc.func_74757_a("Familiarized Today", this.familiarizedToday);

    NBTTagCompound nbt = nbttc;
    nbt.func_74776_a("Strength Modifier", this.strengthMod);
    nbt.func_74776_a("Aggression Modifier", this.aggressionMod);
    nbt.func_74776_a("Obedience Modifier", this.obedienceMod);

    nbttc.func_74768_a("Hunger", this.hunger);
    nbttc.func_74757_a("Pregnant", this.pregnant);
    nbttc.func_74776_a("MateSize", this.mateSizeMod);
    nbttc.func_74776_a("MateStrength", this.mateStrengthMod);
    nbttc.func_74776_a("MateAggro", this.mateAggroMod);
    nbttc.func_74776_a("MateObed", this.mateObedMod);
    nbttc.func_74768_a("MateType", this.mateType);
    nbttc.func_74768_a("MateVariant", this.mateVariant);
    nbttc.func_74780_a("MateHealth", this.mateMaxHealth);
    nbttc.func_74780_a("MateJump", this.mateJumpStrength);
    nbttc.func_74780_a("MateSpeed", this.mateMoveSpeed);
    nbttc.func_74772_a("ConceptionTime", this.timeOfConception);
    nbttc.func_74768_a("Age", getBirthDay());

    if (func_110261_ca()) {

      NBTTagList nbttaglist = new NBTTagList();

      for (int i = 2; i < this.field_110296_bG.func_70302_i_(); i++) {

        ItemStack itemstack = this.field_110296_bG.func_70301_a(i);

        if (itemstack != null) {

          NBTTagCompound nbttagcompound1 = new NBTTagCompound();
          nbttagcompound1.func_74774_a("Slot", (byte)i);
          itemstack.func_77955_b(nbttagcompound1);
          nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
        }
      }

      nbttc.func_74782_a("Items", (NBTBase)nbttaglist);
    }

    if (this.field_110296_bG.func_70301_a(1) != null)
    {
      nbttc.func_74782_a("ArmorItem", (NBTBase)this.field_110296_bG.func_70301_a(1).func_77955_b(new NBTTagCompound()));
    }

    if (this.field_110296_bG.func_70301_a(0) != null)
    {
      nbttc.func_74782_a("SaddleItem", (NBTBase)this.field_110296_bG.func_70301_a(0).func_77955_b(new NBTTagCompound()));
    }
  }



  protected boolean func_70692_ba() {
    return !func_110248_bS();
  }


  public boolean isOverweight() {
    return (getWeight() > 80000);
  }

  public int getWeight() {
    ItemStack[] inv = new ItemStack[getHorseChest().func_70302_i_()];
    for (int i = 0; i < getHorseChest().func_70302_i_(); ) { inv[i] = getHorseChest().func_70301_a(i); i++; }
     InventoryWeight iv = new InventoryWeight(inv);
    return iv.getWeight();
  }



  public double func_110215_cj() {
    return func_110148_a(HORSE_JUMP_STRENGTH).func_111126_e();
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\Mobs\EntityHorseTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */