package com.bioxx.tfc.api;

import com.bioxx.tfc.api.Enums.EnumFoodGroup;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.item.Item;



public class FoodRegistry
{
  private static final FoodRegistry INSTANCE = new FoodRegistry();

  public static final FoodRegistry getInstance() {
    return INSTANCE;
  }

  private int proteinCount;
  private Map<Integer, Item> proteinMap;
  private int vegetableCount = 10000;
  private Map<Integer, Item> vegetableMap;
  private int fruitCount = 20000;
  private Map<Integer, Item> fruitMap;
  private int grainCount = 30000;
  private Map<Integer, Item> grainMap;
  private int dairyCount = 40000;

  private Map<Integer, Item> dairyMap;

  private FoodRegistry() {
    this.proteinMap = new HashMap<>();
    this.vegetableMap = new HashMap<>();
    this.fruitMap = new HashMap<>();
    this.grainMap = new HashMap<>();
    this.dairyMap = new HashMap<>();
  }


  public int registerFood(EnumFoodGroup efg, Item i) {
    switch (efg) {


      case Protein:
        this.proteinMap.put(Integer.valueOf(this.proteinCount), i);
        return this.proteinCount++;


      case Vegetable:
        this.vegetableMap.put(Integer.valueOf(this.vegetableCount), i);
        return this.vegetableCount++;


      case Fruit:
        this.fruitMap.put(Integer.valueOf(this.fruitCount), i);
        return this.fruitCount++;


      case Grain:
        this.grainMap.put(Integer.valueOf(this.grainCount), i);
        return this.grainCount++;


      case Dairy:
        this.dairyMap.put(Integer.valueOf(this.dairyCount), i);
        return this.dairyCount++;
    }


    return -1;
  }




  public Item getFood(int id) {
    if (this.proteinMap.containsKey(Integer.valueOf(id)))
      return this.proteinMap.get(Integer.valueOf(id));
    if (this.vegetableMap.containsKey(Integer.valueOf(id)))
      return this.vegetableMap.get(Integer.valueOf(id));
    if (this.fruitMap.containsKey(Integer.valueOf(id)))
      return this.fruitMap.get(Integer.valueOf(id));
    if (this.grainMap.containsKey(Integer.valueOf(id)))
      return this.grainMap.get(Integer.valueOf(id));
    if (this.dairyMap.containsKey(Integer.valueOf(id)))
      return this.dairyMap.get(Integer.valueOf(id));
    return null;
  }


  public EnumFoodGroup getFoodGroup(int id) {
    if (this.proteinMap.containsKey(Integer.valueOf(id)))
      return EnumFoodGroup.Protein;
    if (this.vegetableMap.containsKey(Integer.valueOf(id)))
      return EnumFoodGroup.Vegetable;
    if (this.fruitMap.containsKey(Integer.valueOf(id)))
      return EnumFoodGroup.Fruit;
    if (this.grainMap.containsKey(Integer.valueOf(id)))
      return EnumFoodGroup.Grain;
    if (this.dairyMap.containsKey(Integer.valueOf(id)))
      return EnumFoodGroup.Dairy;
    return EnumFoodGroup.None;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\FoodRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */