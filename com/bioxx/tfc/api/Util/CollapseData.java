package com.bioxx.tfc.api.Util;

import com.bioxx.tfc.api.Enums.TFCDirection;


public class CollapseData
{
  public ByteCoord coords;
  public float collapseChance;
  public TFCDirection direction;

  public CollapseData(ByteCoord c, float chance, TFCDirection d) {
    this.coords = c;
    this.collapseChance = chance;
    this.direction = d;
  }



  public boolean equals(Object o) {
    return (o instanceof CollapseData && ((CollapseData)o).coords.equals(this.coords));
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Util\CollapseData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */