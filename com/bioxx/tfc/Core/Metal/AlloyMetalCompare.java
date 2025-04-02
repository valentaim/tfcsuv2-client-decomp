package com.bioxx.tfc.Core.Metal;

import com.bioxx.tfc.api.Metal;

public class AlloyMetalCompare
  extends AlloyMetal
{
  private float metalMin;
  private float metalMax;

  public AlloyMetalCompare(Metal e, float min) {
    super(e, min);
  }


  public AlloyMetalCompare(Metal e, float min, float max) {
    super(e, min);
    this.metalType = e;
    this.metalMin = min;
    this.metalMax = max;
  }







  public boolean compare(AlloyMetal b) {
    return (this.metalType == b.metalType && (b.metal == -1.0F || (
      Math.round(b.metal * 1000.0F) >= Math.round(this.metalMin * 1000.0F) && Math.round(b.metal * 1000.0F) <= Math.round(this.metalMax * 1000.0F))));
  }


  public float getMetalMin() {
    return this.metalMin;
  }


  public float getMetalMax() {
    return this.metalMax;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\Metal\AlloyMetalCompare.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */