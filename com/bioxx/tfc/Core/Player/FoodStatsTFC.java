package com.bioxx.tfc.Core.Player;

import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.Render.EntityRendererTFC;
import com.bioxx.tfc.api.Enums.EnumFoodGroup;
import com.bioxx.tfc.api.Food;
import com.bioxx.tfc.api.FoodRegistry;
import com.bioxx.tfc.api.Interfaces.IFood;
import com.bioxx.tfc.api.TFCOptions;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;


public class FoodStatsTFC
{
  private boolean updateStats = !TFCOptions.enableDebugMode;


  public float stomachLevel = 24.0F;
  private float stomachMax = 24.0F;
  private float prevFoodLevel = 24.0F;

  private ResourceLocation drunkBlur = new ResourceLocation("shaders/post/blur.json");
  private ResourceLocation wastedBlur = new ResourceLocation("shaders/post/blur.json");

  public float nutrFruit = 1.0F;
  public float nutrVeg = 1.0F;
  public float nutrGrain = 1.0F;
  public float nutrDairy = 1.0F;
  public float nutrProtein = 1.0F;


  private boolean sendUpdate = true;


  public long soberTime;

  private float satisfaction;

  private float foodExhaustionLevel;

  public long foodTimer;

  public long foodHealTimer;

  public float waterLevel = 48000.0F;

  public long waterTimer;
  public EntityPlayer player;
  private long nameSeed = Long.MIN_VALUE;

  private boolean satFruit;
  private boolean satVeg;
  private boolean satGrain;
  private boolean satProtein;
  private boolean satDairy;

  public FoodStatsTFC(EntityPlayer player) {
    this.player = player;
    this.waterTimer = Math.max(TFC_Time.getTotalTicks(), TFC_Time.startTime);
    this.foodTimer = Math.max(TFC_Time.getTotalTicks(), TFC_Time.startTime);
    this.foodHealTimer = Math.max(TFC_Time.getTotalTicks(), TFC_Time.startTime);
  }





  public void onUpdate(EntityPlayer player) {
    if (!player.field_70170_p.field_72995_K) {

      BodyTempStats bodyTemp = TFC_Core.getBodyTempStats(player);
      float temp = TFC_Climate.getHeightAdjustedTemp(player.field_70170_p, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v);

      float tempWaterMod = temp;
      if (tempWaterMod >= 25.0F) {
        tempWaterMod = (tempWaterMod - 25.0F) * 0.2F;
      } else {
        tempWaterMod = 0.0F;
      }



      if (this.foodTimer < TFC_Time.startTime) {

        this.foodTimer = TFC_Time.startTime;
        this.foodHealTimer = TFC_Time.startTime;
        this.waterTimer = TFC_Time.startTime;
      }

      if (TFC_Time.getTotalTicks() - this.foodTimer >= 1000L && !player.field_71075_bZ.field_75098_d && this.updateStats) {

        this.foodTimer += 1000L;
        float drainMult = 1.0F;
        if (player.func_70608_bn())
        {
          drainMult = 0.5F;
        }

        if (player.func_70051_ag())
          this.waterLevel -= 5.0F + tempWaterMod;
        if (!player.field_71075_bZ.field_75098_d && this.updateStats) {
          this.waterLevel -= bodyTemp.getExtraWater() * drainMult;
        }

        float hunger = (1.0F + this.foodExhaustionLevel + bodyTemp.getExtraFood()) * drainMult;
        if (this.satisfaction >= hunger) {

          this.satisfaction -= hunger;
          hunger = 0.0F;
          this.foodExhaustionLevel = 0.0F;
        }
        else {

          hunger -= this.satisfaction;
          this.satisfaction = 0.0F;
          this.foodExhaustionLevel = 0.0F;
        }
        this.stomachLevel = Math.max(this.stomachLevel - hunger, 0.0F);

        if (this.satisfaction == 0.0F) {

          this.satProtein = false; this.satFruit = false; this.satVeg = false; this.satDairy = false; this.satGrain = false;
        }

        if (this.stomachLevel <= 0.0F) {

          reduceNutrition(0.0024F);
        }
        else if (this.satisfaction <= 0.0F) {

          reduceNutrition(8.0E-4F);
        }
        else {

          if (this.satProtein)
            addNutrition(EnumFoodGroup.Protein, this.satisfaction * (1.0F - this.nutrProtein) / 100.0F, false);
          if (this.satGrain)
            addNutrition(EnumFoodGroup.Grain, this.satisfaction * (1.0F - this.nutrGrain) / 100.0F, false);
          if (this.satVeg)
            addNutrition(EnumFoodGroup.Vegetable, this.satisfaction * (1.0F - this.nutrVeg) / 100.0F, false);
          if (this.satFruit)
            addNutrition(EnumFoodGroup.Fruit, this.satisfaction * (1.0F - this.nutrFruit) / 100.0F, false);
          if (this.satDairy)
            addNutrition(EnumFoodGroup.Dairy, this.satisfaction * (1.0F - this.nutrDairy) / 100.0F, false);
        }
        this.sendUpdate = true;
      }


      if (TFC_Time.getTotalTicks() - this.foodHealTimer >= 500L) {

        this.foodHealTimer += 500L;

        if (this.stomachLevel >= getMaxStomach(player) / 4.0F && player.func_70996_bM())
        {

          player.func_70691_i((int)(player.func_110138_aP() * 0.01F));
        }
      }







      if (!player.field_71075_bZ.field_75098_d && this.updateStats)
      {
        for (; this.waterTimer < TFC_Time.getTotalTicks(); this.waterTimer++) {


          this.waterLevel -= 1.0F + tempWaterMod / 2.0F;
          if (this.waterLevel < 0.0F)
            this.waterLevel = 0.0F;
          if (!TFC_Core.isPlayerInDebugMode(player) && this.waterLevel == 0.0F && temp > 35.0F) {
            player.func_70097_a((new DamageSource("heatStroke")).func_76348_h().func_151518_m(), 2.0F);
          }
        }
      }
    }
  }

  public void clientUpdate() {
    if ((Minecraft.func_71410_x()).field_71460_t instanceof EntityRendererTFC) {

      EntityRendererTFC erTFC = (EntityRendererTFC)(Minecraft.func_71410_x()).field_71460_t;
      if ((erTFC.getCurrentShaderLocation() == null || !erTFC.getCurrentShaderLocation().equals(this.wastedBlur)) && this.soberTime > TFC_Time.getTotalTicks() + 8000L) {

        erTFC.setManualShader(this.wastedBlur);
      }
      else if ((erTFC.getCurrentShaderLocation() == null || !erTFC.getCurrentShaderLocation().equals(this.drunkBlur)) && this.soberTime > TFC_Time.getTotalTicks() + 4000L && this.soberTime <= TFC_Time.getTotalTicks() + 8000L) {

        erTFC.setManualShader(this.drunkBlur);
      }
      else if (erTFC.getManualShaderBeingUsed() && this.soberTime <= TFC_Time.getTotalTicks() + 4000L) {

        erTFC.deactivateManualShader();
      }
    }
  }


  protected void reduceNutrition(float amount) {
    this.nutrFruit = Math.max(this.nutrFruit - amount + this.foodExhaustionLevel, 0.0F);
    this.nutrVeg = Math.max(this.nutrVeg - amount + this.foodExhaustionLevel, 0.0F);
    this.nutrGrain = Math.max(this.nutrGrain - amount + this.foodExhaustionLevel, 0.0F);
    this.nutrProtein = Math.max(this.nutrProtein - amount + this.foodExhaustionLevel, 0.0F);
    this.nutrDairy = Math.max(this.nutrDairy - amount + this.foodExhaustionLevel, 0.0F);

    this.sendUpdate = true;
  }


  public int getMaxWater(EntityPlayer player) {
    return 48000 + 200 * player.field_71068_ca;
  }


  public float getMaxStomach(EntityPlayer player) {
    return this.stomachMax;
  }





  public float getFoodLevel() {
    return this.stomachLevel;
  }


  @SideOnly(Side.CLIENT)
  public float getPrevFoodLevel() {
    return this.prevFoodLevel;
  }





  public boolean needFood() {
    return (this.stomachLevel < getMaxStomach(this.player) && (getMaxStomach(this.player) - this.stomachLevel) > 0.1D);
  }


  public boolean needDrink() {
    return (this.waterLevel < (getMaxWater(this.player) - 500));
  }





  public void readNBT(NBTTagCompound par1NBTTagCompound) {
    if (par1NBTTagCompound.func_74764_b("foodCompound")) {

      NBTTagCompound foodCompound = par1NBTTagCompound.func_74775_l("foodCompound");
      this.waterLevel = foodCompound.func_74760_g("waterLevel");
      this.stomachLevel = foodCompound.func_74760_g("foodLevel");
      this.foodTimer = foodCompound.func_74763_f("foodTickTimer");
      this.foodHealTimer = foodCompound.func_74763_f("foodHealTimer");
      this.waterTimer = foodCompound.func_74763_f("waterTimer");
      this.soberTime = foodCompound.func_74763_f("soberTime");
      this.satisfaction = foodCompound.func_74760_g("foodSaturationLevel");
      this.foodExhaustionLevel = foodCompound.func_74760_g("foodExhaustionLevel");
      this.nutrFruit = foodCompound.func_74760_g("nutrFruit");
      this.nutrVeg = foodCompound.func_74760_g("nutrVeg");
      this.nutrGrain = foodCompound.func_74760_g("nutrGrain");
      this.nutrProtein = foodCompound.func_74760_g("nutrProtein");
      this.nutrDairy = foodCompound.func_74760_g("nutrDairy");
      this.sendUpdate = foodCompound.func_74767_n("shouldSendUpdate");
      this.satFruit = foodCompound.func_74767_n("satFruit");
      this.satVeg = foodCompound.func_74767_n("satVeg");
      this.satGrain = foodCompound.func_74767_n("satGrain");
      this.satProtein = foodCompound.func_74767_n("satProtein");
      this.satDairy = foodCompound.func_74767_n("satDairy");
    }
  }





  public void writeNBT(NBTTagCompound nbt) {
    NBTTagCompound foodNBT = new NBTTagCompound();
    foodNBT.func_74776_a("waterLevel", this.waterLevel);
    foodNBT.func_74776_a("foodLevel", this.stomachLevel);
    foodNBT.func_74772_a("foodTickTimer", this.foodTimer);
    foodNBT.func_74772_a("foodHealTimer", this.foodHealTimer);
    foodNBT.func_74772_a("waterTimer", this.waterTimer);
    foodNBT.func_74772_a("soberTime", this.soberTime);
    foodNBT.func_74776_a("foodSaturationLevel", this.satisfaction);
    foodNBT.func_74776_a("foodExhaustionLevel", this.foodExhaustionLevel);
    foodNBT.func_74776_a("nutrFruit", this.nutrFruit);
    foodNBT.func_74776_a("nutrVeg", this.nutrVeg);
    foodNBT.func_74776_a("nutrGrain", this.nutrGrain);
    foodNBT.func_74776_a("nutrProtein", this.nutrProtein);
    foodNBT.func_74776_a("nutrDairy", this.nutrDairy);
    foodNBT.func_74757_a("shouldSendUpdate", this.sendUpdate);
    foodNBT.func_74757_a("satFruit", this.satFruit);
    foodNBT.func_74757_a("satVeg", this.satVeg);
    foodNBT.func_74757_a("satGrain", this.satGrain);
    foodNBT.func_74757_a("satProtein", this.satProtein);
    foodNBT.func_74757_a("satDairy", this.satDairy);
    nbt.func_74782_a("foodCompound", (NBTBase)foodNBT);
  }


  public void addFoodExhaustion(float par1) {
    this.foodExhaustionLevel = par1;
  }







  public float getSatisfaction() {
    return this.satisfaction;
  }


  public void setFoodLevel(float par1) {
    if (par1 != this.stomachLevel)
      this.sendUpdate = true;
    this.stomachLevel = par1;
  }



  public void setSatisfaction(float par1, int[] fg) {
    this.satisfaction = Math.min(par1, 10.0F);
    for (int i = 0; i < fg.length; i++) {

      if (fg[i] != -1) {

        EnumFoodGroup efg = FoodRegistry.getInstance().getFoodGroup(fg[i]);
        switch (efg) {
          case Protein:
            this.satProtein = true; break;
          case Grain: this.satGrain = true; break;
          case Fruit: this.satFruit = true; break;
          case Vegetable: this.satVeg = true; break;
          case Dairy: this.satDairy = true;
            break;
        }
      }
    }
  }



  public long getPlayerFoodSeed() {
    if (this.nameSeed == Long.MIN_VALUE) {

      long seed = 0L;
      byte[] nameBytes = this.player.func_70005_c_().getBytes();
      for (byte b : nameBytes)
        seed += b;
      this.nameSeed = seed + this.player.field_70170_p.func_72905_C();
    }
    return this.nameSeed;
  }





  public int[] getPrefTaste() {
    Random r = new Random(getPlayerFoodSeed());
    return new int[] { 20 + r
        .nextInt(70), 20 + r.nextInt(70), 20 + r.nextInt(70), 20 + r.nextInt(70), 20 + r.nextInt(70) };
  }



  public float getTasteFactor(ItemStack food) {
    float tasteFactor = 0.85F;
    int[] tastePref = getPrefTaste();

    tasteFactor += getTasteDistanceFactor(tastePref[0], ((IFood)food.func_77973_b()).getTasteSweet(food));
    tasteFactor += getTasteDistanceFactor(tastePref[1], ((IFood)food.func_77973_b()).getTasteSour(food));
    tasteFactor += getTasteDistanceFactor(tastePref[2], ((IFood)food.func_77973_b()).getTasteSalty(food));
    tasteFactor += getTasteDistanceFactor(tastePref[3], ((IFood)food.func_77973_b()).getTasteBitter(food));
    tasteFactor += getTasteDistanceFactor(tastePref[4], ((IFood)food.func_77973_b()).getTasteSavory(food));

    return tasteFactor;
  }


  public float getTasteDistanceFactor(int pref, int val) {
    int abs = Math.abs(pref - val);
    if (abs < 11)
      return (10 - abs) * 0.01F;
    return 0.0F;
  }


  public float getNutritionHealthModifier() {
    float nMod = 0.0F;
    nMod += 0.2F * this.nutrFruit;
    nMod += 0.2F * this.nutrVeg;
    nMod += 0.2F * this.nutrGrain;
    nMod += 0.2F * this.nutrProtein;
    nMod += 0.2F * this.nutrDairy;
    return Math.max(nMod, 0.05F);
  }


  public static int getMaxHealth(EntityPlayer player) {
    return
      (int)(Math.min(1000 + player.field_71068_ca * TFCOptions.healthGainRate, TFCOptions.healthGainCap) * TFC_Core.getPlayerFoodStats(player).getNutritionHealthModifier());
  }






  public static boolean reduceFood(ItemStack is, float amount) {
    if (is.func_77942_o()) {

      float weight = Food.getWeight(is);
      float decay = Food.getDecay(is);
      if (decay >= 0.0F && weight - decay - amount <= 0.0F)
        return true;
      if (decay <= 0.0F && weight - amount <= 0.0F) {
        return true;
      }

      Food.setWeight(is, weight - amount);
    }

    return false;
  }


  public void addNutrition(EnumFoodGroup fg, float foodAmt) {
    addNutrition(fg, foodAmt, true);
  }


  public void addNutrition(EnumFoodGroup fg, float foodAmt, boolean shouldDoMath) {
    float amount = foodAmt;
    if (shouldDoMath)
      amount = foodAmt / 5.0F / 50.0F;
    switch (fg) {

      case Dairy:
        this.nutrDairy = Math.min(this.nutrDairy + amount, 1.0F);
        break;
      case Fruit:
        this.nutrFruit = Math.min(this.nutrFruit + amount, 1.0F);
        break;
      case Grain:
        this.nutrGrain = Math.min(this.nutrGrain + amount, 1.0F);
        break;
      case Protein:
        this.nutrProtein = Math.min(this.nutrProtein + amount, 1.0F);
        break;
      case Vegetable:
        this.nutrVeg = Math.min(this.nutrVeg + amount, 1.0F);
        break;
    }
  }




  public boolean shouldSendUpdate() {
    return this.sendUpdate;
  }


  public void restoreWater(EntityPlayer player, int w) {
    this.waterLevel = Math.min(this.waterLevel + w, getMaxWater(player));
    this.sendUpdate = true;
    writeNBT(player.getEntityData());
  }


  public void resetTimers() {
    this.waterTimer = TFC_Time.getTotalTicks();
    this.foodTimer = TFC_Time.getTotalTicks();
    this.foodHealTimer = TFC_Time.getTotalTicks();
  }



  public void consumeAlcohol() {
    if (this.soberTime <= TFC_Time.getTotalTicks()) {
      this.soberTime = TFC_Time.getTotalTicks() + this.player.field_70170_p.field_73012_v.nextInt(1000) + 400L;
    } else {
      this.soberTime += (this.player.field_70170_p.field_73012_v.nextInt(1000) + 400);
    }  this.sendUpdate = true;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\Player\FoodStatsTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */