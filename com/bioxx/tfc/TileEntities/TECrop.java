package com.bioxx.tfc.TileEntities;

import com.bioxx.tfc.Blocks.BlockFarmland;
import com.bioxx.tfc.Core.TFC_Achievements;
import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.Food.CropIndex;
import com.bioxx.tfc.Food.CropManager;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCOptions;
import growthcraft.rice.common.block.BlockPaddy;
import growthcraft.rice.util.RiceBlockCheck;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;













public class TECrop
  extends NetworkTileEntity
{
  public float growth = 0.1F;
  private long plantedTime = TFC_Time.getTotalTicks();
  private long growthTimer = TFC_Time.getTotalTicks();
  private long finishedTime = 0L;
  private byte sunLevel = 1;
  public int cropId;
  public int tendingLevel;
  private int killLevel;
  private boolean finished;
  
  public void func_145845_h() {
    Random r = new Random();
    if (!this.field_145850_b.field_72995_K) {
      
      float timeMultiplier = 360.0F / TFC_Time.daysInYear;
      CropIndex crop = CropManager.getInstance().getCropFromId(this.cropId);
      
      if (crop.cropId == 6 && 
        !this.finished && this.growth >= crop.numGrowthStages) {
        
        Block paddyBlock = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
        if (RiceBlockCheck.isPaddy(paddyBlock))
        {
          ((BlockPaddy)paddyBlock).drainPaddy(this.field_145850_b, this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
        }
        this.finished = true;
      } 
      
      if ((int)Math.min(this.growth / crop.numGrowthStages * 100.0F, 100.0F) >= 100 && 
        this.finishedTime == 0L) this.finishedTime = TFC_Time.getTotalMonths();

      
      long time = TFC_Time.getTotalTicks();
      
      if (crop != null && this.growthTimer < time && this.sunLevel > 0) {
        
        this.sunLevel = (byte)(this.sunLevel - 1);
        if (crop.needsSunlight && hasSunlight(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e)) {
          
          this.sunLevel = (byte)(this.sunLevel + 1);
          if (this.sunLevel > 30) {
            this.sunLevel = 30;
          }
        } 
        TEFarmland tef = null;
        TileEntity te = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
        if (te instanceof TEFarmland) {
          tef = (TEFarmland)te;
        }
        float ambientTemp = TFC_Climate.getHeightAdjustedTempSpecificDay(this.field_145850_b, TFC_Time.getDayOfYearFromTick(this.growthTimer), this.field_145851_c, this.field_145848_d, this.field_145849_e);
        float tempAdded = 0.0F;
        boolean isDormant = false;




















        
        if (!crop.dormantInFrost && ambientTemp < crop.minGrowthTemp) {
          tempAdded = -0.03F * (crop.minGrowthTemp - ambientTemp);
        } else if (crop.dormantInFrost && ambientTemp < crop.minGrowthTemp) {
          
          if (this.growth > 1.0F)
            tempAdded = -0.03F * (crop.minGrowthTemp - ambientTemp); 
          isDormant = true;
        }
        else if (ambientTemp < 28.0F) {
          tempAdded = ambientTemp * 3.5E-4F;
        } else if (ambientTemp < 37.0F) {
          tempAdded = (28.0F - ambientTemp - 28.0F) * 3.0E-4F;
        } 
        if (!crop.dormantInFrost && ambientTemp < crop.minAliveTemp) {
          
          int baseKillChance = 6;
          if (this.field_145850_b.field_73012_v.nextInt(baseKillChance - this.killLevel) == 0) {
            killCrop(crop);
          
          }
          else if (this.killLevel < baseKillChance - 1) {
            this.killLevel++;
          }
        
        } else if (crop.dormantInFrost && ambientTemp < crop.minAliveTemp) {
          
          if (this.growth > 1.0F) {
            
            int baseKillChance = 6;
            if (this.field_145850_b.field_73012_v.nextInt(baseKillChance - this.killLevel) == 0) {
              killCrop(crop);
            
            }
            else if (this.killLevel < baseKillChance - 1) {
              this.killLevel++;
            }
          
          } 
        } else {
          
          this.killLevel = 0;
        } 
        
        int nutriType = crop.cycleType;
        int nutri = (tef != null) ? tef.nutrients[nutriType] : 18000;
        int fert = (tef != null) ? tef.nutrients[3] : 0;
        int soilMax = (tef != null) ? tef.getSoilMax() : 18000;
        
        float waterBoost = BlockFarmland.isFreshWaterNearby(this.field_145850_b, this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) ? 0.1F : 0.0F;

        
        nutri = Math.min(nutri + fert, (int)(soilMax * 1.25F));
        
        float nutriMult = 0.2F + nutri / soilMax * 0.5F + waterBoost;
        
        if (tef != null && !isDormant) {
          
          if (tef.nutrients[nutriType] > 0) {
            tef.drainNutrients(nutriType, crop.nutrientUsageMult);
          }
          if (tef.nutrients[3] > 0) {
            tef.drainNutrients(3, crop.nutrientUsageMult);
          }
        } 
        float growthRate = Math.max(0.0F, (crop.numGrowthStages / crop.growthTime * TFCOptions.yearLength / 120.0F + tempAdded) * nutriMult * timeMultiplier * TFCOptions.cropGrowthMultiplier);
        if (tef != null && tef.isInfested)
          growthRate /= 2.0F; 
        int oldGrowth = (int)Math.floor(this.growth);
        
        if (!isDormant) {
          this.growth += growthRate;
        }
        if (oldGrowth < (int)Math.floor(this.growth))
        {
          
          this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }

        
        if (this.finishedTime > 0L && TFC_Time.getTotalMonths() - this.finishedTime > 4L) killCrop(crop);
        
        if (((this.finished || TFCOptions.enableCropsDie || !TFC_Core.isFarmland(this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e))) && crop.maxLifespan == -1 && this.growth > crop.numGrowthStages + crop.numGrowthStages / 2.0F) || this.growth < 0.0F)
        {
          
          killCrop(crop);
        }
        
        this.growthTimer += (r.nextInt(2) + 23) * 1000L;
      
      }
      else if (crop != null && crop.needsSunlight && this.sunLevel <= 0) {
        
        killCrop(crop);
      } 

      
      if (TFC_Core.isExposedToRain(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e) && TFC_Climate.getHeightAdjustedTemp(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e) < 0.0F)
      {
        if ((crop != null && !crop.dormantInFrost) || this.growth > 1.0F)
        {
          killCrop(crop);
        }
      }
    } 
  }

  
  public static boolean hasSunlight(World world, int x, int y, int z) {
    Chunk chunk = world.func_72938_d(x, z);
    int skylight = chunk.func_76614_a(EnumSkyBlock.Sky, x & 0xF, y, z & 0xF);
    boolean sky = world.func_72937_j(x, y + 1, z);
    return (sky || skylight > 13);
  }

  
  public float getEstimatedGrowth(CropIndex crop) {
    return crop.numGrowthStages / (float)(this.growthTimer - this.plantedTime / 24000L) * 1.5F;
  }

  
  public void onHarvest(World world, EntityPlayer player, boolean isBreaking) {
    if (!world.field_72995_K) {
      
      CropIndex crop = CropManager.getInstance().getCropFromId(this.cropId);
      
      if (crop != null && this.growth >= (crop.numGrowthStages - 1)) {
        
        ItemStack is1 = crop.getOutput1(this);
        ItemStack is2 = crop.getOutput2(this);
        ItemStack seedStack = crop.getSeed();
        
        if (is1 != null) {
          world.func_72838_d((Entity)new EntityItem(world, this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, is1));
        }
        if (is2 != null) {
          world.func_72838_d((Entity)new EntityItem(world, this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, is2));
        }
        int skill = 20 - (int)(20.0F * TFC_Core.getSkillStats(player).getSkillMultiplier("skill.agriculture"));
        
        seedStack.field_77994_a = 1 + ((world.field_73012_v.nextInt(1 + skill) == 0) ? 1 : 0);
        if (isBreaking) {
          world.func_72838_d((Entity)new EntityItem(world, this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, seedStack));
        }
        TFC_Core.getSkillStats(player).increaseSkill("skill.agriculture", 1);
        
        if (TFC_Core.isSoil(world.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e))) {
          player.func_71064_a((StatBase)TFC_Achievements.achWildVegetable, 1);
        }
      } else if (crop != null) {
        
        ItemStack is = crop.getSeed();
        is.field_77994_a = 1;
        world.func_72838_d((Entity)new EntityItem(world, this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, is));
      } 
    } 
  }

  
  public void killCrop(CropIndex crop) {
    ItemStack is = crop.getSeed();
    is.field_77994_a = 1;
    if (TFC_Core.isFarmland(this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e)) && TFCOptions.enableSeedDrops) {
      
      if (this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d, this.field_145849_e, TFCBlocks.worldItem)) {
        
        TEWorldItem te = (TEWorldItem)this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        if (te != null) {
          te.storage[0] = is;
          this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
      
      } 
    } else {
      
      this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    } 
  }





  
  public void func_145839_a(NBTTagCompound nbt) {
    super.func_145839_a(nbt);
    this.growth = nbt.func_74760_g("growth");
    this.cropId = nbt.func_74762_e("cropId");
    this.growthTimer = nbt.func_74763_f("growthTimer");
    this.plantedTime = nbt.func_74763_f("plantedTime");
    this.killLevel = nbt.func_74762_e("killLevel");
    this.sunLevel = nbt.func_74771_c("sunLevel");
    this.finished = nbt.func_74767_n("finished");
    this.finishedTime = nbt.func_74763_f("finishedTime");
  }





  
  public void func_145841_b(NBTTagCompound nbt) {
    super.func_145841_b(nbt);
    nbt.func_74776_a("growth", this.growth);
    nbt.func_74768_a("cropId", this.cropId);
    nbt.func_74772_a("growthTimer", this.growthTimer);
    nbt.func_74772_a("plantedTime", this.plantedTime);
    nbt.func_74768_a("killLevel", this.killLevel);
    nbt.func_74774_a("sunLevel", this.sunLevel);
    nbt.func_74757_a("finished", this.finished);
    nbt.func_74772_a("finishedTime", this.finishedTime);
  }

  
  public void handleInitPacket(NBTTagCompound nbt) {
    this.growth = nbt.func_74760_g("growth");
    this.cropId = nbt.func_74762_e("cropId");
    this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }


  
  public void handleDataPacket(NBTTagCompound nbt) {
    this.growth = nbt.func_74760_g("growth");
    this.cropId = nbt.func_74762_e("cropId");
    this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }

  
  public void createDataNBT(NBTTagCompound nbt) {
    nbt.func_74776_a("growth", this.growth);
    nbt.func_74768_a("cropId", this.cropId);
  }

  
  public void createInitNBT(NBTTagCompound nbt) {
    nbt.func_74776_a("growth", this.growth);
    nbt.func_74768_a("cropId", this.cropId);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TECrop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */