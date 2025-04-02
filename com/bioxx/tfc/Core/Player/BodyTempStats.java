package com.bioxx.tfc.Core.Player;

import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.api.Interfaces.IClothing;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TileEntities.TEFireEntity;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;



public class BodyTempStats
{
  private int temperatureLevel;
  private int prevTemperatureLevel;
  private int extraFoodConsumed;
  private int extraWaterConsumed;
  private int heatStorage;
  private long tempTimer;
  private Random rand = new Random();
  private ItemStack itemHead;
  private ItemStack itemChest;
  
  public void onUpdate(EntityPlayer player) {
    if (TFC_Time.getTotalTicks() - this.tempTimer >= 20L) {
      
      this.tempTimer += 20L;
      if (this.heatStorage > this.temperatureLevel) {
        this.heatStorage--;
      }
      if (!player.field_71075_bZ.field_75098_d)
        applyTemperature(player); 
    } 
  }
  private ItemStack itemLegs; private ItemStack itemFeet;
  
  private void applyTemperature(EntityPlayer player) {
    FoodStatsTFC food = TFC_Core.getPlayerFoodStats(player);

    
    this.prevTemperatureLevel = this.temperatureLevel;
    if (this.rand.nextInt(2000 - getBaseBodyTempMod(player)) < 100 && food.stomachLevel >= 500.0F)
      this.temperatureLevel++; 
    if ((player.func_70051_ag() || player.field_70733_aJ != 0.0F) && this.rand.nextInt(1000 - getBaseBodyTempMod(player) / 2) < 100) {
      this.temperatureLevel++;
    }

    
    this.temperatureLevel += applyTemperatureFromEnvironment(player);

    
    this.heatStorage = Math.max(Math.min(this.temperatureLevel + this.heatStorage, 14), 0);
    
    this.extraFoodConsumed = 0;
    this.extraWaterConsumed = 0;
    
    if (this.temperatureLevel != this.prevTemperatureLevel && (this.prevTemperatureLevel < -10 || this.prevTemperatureLevel > 10 || this.temperatureLevel < -10 || this.temperatureLevel > 10))
    {

      
      tellPlayerMessage(player);
    }
    this.prevTemperatureLevel = this.temperatureLevel;
    if (this.temperatureLevel >= -10 && this.temperatureLevel <= 10) {
      
      this.extraFoodConsumed = 0;
      this.extraWaterConsumed = 0;
    } 
  }

  
  public int applyTemperatureFromEnvironment(EntityPlayer player) {
    int x = (int)player.field_70165_t;
    int y = (int)player.field_70163_u;
    int z = (int)player.field_70161_v;
    float temperature = TFC_Climate.getHeightAdjustedTemp(player.field_70170_p, x, y, z);
    temperature += applyTemperatureFromHeatSources(player);

    
    if (temperature <= 10.0F) {
      
      int modifier = (int)((temperature - 10.0F) * 15.0F);
      if (this.rand.nextInt(1200 + modifier) < 10) {
        return -1;
      }
    }
    else if (temperature >= 30.0F) {
      
      int modifier = Math.min(1199, (int)((temperature - 30.0F) * 15.0F));
      if (this.rand.nextInt(1200 - modifier) < 10) {
        return 1;
      }
    } else if (temperature < 20.0F) {
      
      if (this.temperatureLevel <= -1) {
        
        if (this.rand.nextInt(1200 - Math.min(1199, (int)(temperature - 10.0F) * 15)) < 100) {
          return 1;
        }
      } else if (temperature >= 1.0F) {
        
        if (this.rand.nextInt(1200 - Math.min(1199, (int)(temperature - 10.0F) * 15)) < 100) {
          return -1;
        }
      } 
    } else if (temperature > 20.0F) {
      
      if (this.temperatureLevel <= 1) {
        
        if (this.rand.nextInt(1200 - Math.min(1199, (int)(temperature - 20.0F) * 10)) < 100) {
          return 1;
        }
      } else if (temperature > 1.0F) {
        
        if (this.rand.nextInt(1200 - Math.min(1199, (int)(temperature - 20.0F) * 10)) < 100)
          return -1; 
      } 
    } 
    return 0;
  }

  
  public static float applyTemperatureFromHeatSources(EntityPlayer player) {
    int x = (int)player.field_70165_t;
    int y = (int)player.field_70163_u;
    int z = (int)player.field_70161_v;
    float temperatureMod = 0.0F;
    
    for (int i = x - 7; i < x + 7; i++) {
      
      for (int j = y - 3; j < y + 3; j++) {
        
        for (int k = z - 7; k < z + 7; k++) {
          
          if (player.field_70170_p.func_72899_e(i, j, k)) {
            
            TileEntity te = player.field_70170_p.func_147438_o(i, j, k);
            if (player.field_70170_p.func_147439_a(i, j, k) == Blocks.field_150353_l || player.field_70170_p.func_147439_a(i, j, k) == TFCBlocks.lava || te instanceof TEFireEntity) {


              
              double tempValue = 950.0D;

              
              if (te instanceof TEFireEntity) {
                tempValue = ((TEFireEntity)te).fireTemp;
              }
              
              double distanceSq = player.func_70092_e(i, j, k) + 0.05D;



              
              tempValue *= 0.05D / distanceSq;
              temperatureMod = (float)(temperatureMod + tempValue);
            } 
          } 
        } 
      } 
    } 
    return temperatureMod;
  }

  
  public int getBaseBodyTempMod(EntityPlayer player) {
    this.itemHead = player.field_71071_by.func_70440_f(3);
    this.itemChest = player.field_71071_by.func_70440_f(2);
    this.itemLegs = player.field_71071_by.func_70440_f(1);
    this.itemFeet = player.field_71071_by.func_70440_f(0);
    int returnAmount = 0;
    if (this.itemHead != null && this.itemHead.func_77973_b() instanceof IClothing)
      returnAmount += ((IClothing)this.itemHead.func_77973_b()).getThermal(); 
    if (this.itemChest != null && this.itemChest.func_77973_b() instanceof IClothing)
      returnAmount += ((IClothing)this.itemChest.func_77973_b()).getThermal(); 
    if (this.itemLegs != null && this.itemLegs.func_77973_b() instanceof IClothing)
      returnAmount += ((IClothing)this.itemLegs.func_77973_b()).getThermal(); 
    if (this.itemFeet != null && this.itemFeet.func_77973_b() instanceof IClothing)
      returnAmount += ((IClothing)this.itemFeet.func_77973_b()).getThermal(); 
    return returnAmount;
  }











  
  private void tellPlayerMessage(EntityPlayer player) {}











  
  public void readNBT(NBTTagCompound nbt) {
    if (nbt.func_74764_b("tempCompound")) {
      
      NBTTagCompound tempCompound = nbt.func_74775_l("tempCompound");
      this.temperatureLevel = tempCompound.func_74762_e("tempLev");
      this.extraWaterConsumed = tempCompound.func_74762_e("waterExtra");
      this.extraFoodConsumed = tempCompound.func_74762_e("foodExtra");
      this.heatStorage = tempCompound.func_74762_e("heatStorage");
      this.tempTimer = tempCompound.func_74763_f("tempTimer");
    } 
  }




  
  public void writeNBT(NBTTagCompound nbt) {
    NBTTagCompound tempCompound = new NBTTagCompound();
    tempCompound.func_74768_a("tempLev", this.temperatureLevel);
    tempCompound.func_74768_a("waterExtra", this.extraWaterConsumed);
    tempCompound.func_74768_a("foodExtra", this.extraFoodConsumed);
    tempCompound.func_74768_a("heatStorage", this.heatStorage);
    tempCompound.func_74772_a("tempTimer", this.tempTimer);
    nbt.func_74782_a("tempCompound", (NBTBase)tempCompound);
  }

  
  public int getExtraFood() {
    return this.extraFoodConsumed;
  }

  
  public int getExtraWater() {
    return this.extraWaterConsumed;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\Player\BodyTempStats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */