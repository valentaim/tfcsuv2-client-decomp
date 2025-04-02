package com.bioxx.tfc.Core.Metal;

import com.bioxx.tfc.api.Metal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.item.Item;



public class MetalRegistry
{
  public static MetalRegistry instance = new MetalRegistry();





  private Map<String, Metal> hash = new HashMap<>();




  public boolean addMetal(Metal m, Alloy.EnumTier soloTier) {
    if (this.hash.containsKey(m.name)) {
      return false;
    }
    this.hash.put(m.name, m);

    Alloy alloy = new Alloy(m, soloTier);
    alloy.addIngred(m, 99.0F, 100.0F);
    AlloyManager.INSTANCE.addAlloy(alloy);

    return true;
  }


  public Metal getMetalFromItem(Item i) {
    Iterator<Metal> iter = this.hash.values().iterator();
    while (iter.hasNext()) {

      Metal m = iter.next();
      if (m.ingot == i || m.meltedItem == i) {
        return m;
      }
    }
    return null;
  }


  public Metal getMetalFromString(String s) {
    if (this.hash.containsKey(s))
      return this.hash.get(s);
    return null;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\Metal\MetalRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */