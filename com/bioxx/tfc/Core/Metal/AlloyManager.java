package com.bioxx.tfc.Core.Metal;

import com.bioxx.tfc.api.Metal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class AlloyManager
{
  public static final AlloyManager INSTANCE = new AlloyManager();






  public List<Alloy> alloys = new ArrayList<>();



  public void addAlloy(Alloy a) {
    this.alloys.add(a);
  }


  public boolean matches(List<AlloyMetal> ingred) {
    Iterator<Alloy> iter = this.alloys.iterator();
    Alloy match = null;
    while (iter.hasNext() && match == null) {

      match = iter.next();
      match = match.matches(ingred);
    }
    return (match != null);
  }


  public Metal matchesAlloy(List<AlloyMetal> ingred, Alloy.EnumTier furnaceTier) {
    Iterator<Alloy> iter = this.alloys.iterator();
    Alloy match = null;
    while (iter.hasNext()) {

      match = iter.next();
      if (furnaceTier.tier >= match.furnaceTier.tier) {
        match = match.matches(ingred);
      } else {
        match = null;
      }
      if (match != null)
        return match.outputType;
    }
    return null;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\Metal\AlloyManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */