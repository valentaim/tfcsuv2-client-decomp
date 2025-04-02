package com.bioxx.tfc.WorldGen.GenLayers;


public class GenLayerZoomTFC
  extends GenLayerTFC
{
  public GenLayerZoomTFC(long seed, GenLayerTFC par3GenLayer) {
    super(seed);
    this.field_75909_a = par3GenLayer;
  }







  public int[] func_75904_a(int xPos, int zPos, int xSize, int zSize) {
    int xCoord = xPos >> 1;
    int zCoord = zPos >> 1;
    int newXSize = (xSize >> 1) + 2;
    int newZSize = (zSize >> 1) + 2;
    int[] parentCache = this.field_75909_a.func_75904_a(xCoord, zCoord, newXSize, newZSize);
    int i2 = newXSize - 1 << 1;
    int j2 = newZSize - 1 << 1;
    int[] out = new int[i2 * j2];


    for (int z = 0; z < newZSize - 1; z++) {

      int l2 = (z << 1) * i2;
      int i3 = 0;
      int thisID = parentCache[i3 + 0 + (z + 0) * newXSize];

      for (int x = parentCache[i3 + 0 + (z + 1) * newXSize]; i3 < newXSize - 1; i3++) {

        func_75903_a((i3 + xCoord << 1), (z + zCoord << 1));
        int rightID = parentCache[i3 + 1 + (z + 0) * newXSize];
        int upRightID = parentCache[i3 + 1 + (z + 1) * newXSize];
        out[l2] = thisID;
        out[l2++ + i2] = func_151619_a(new int[] { thisID, x });
        out[l2] = func_151619_a(new int[] { thisID, rightID });
        out[l2++ + i2] = func_151617_b(thisID, rightID, x, upRightID);
        thisID = rightID;
        x = upRightID;
      }
    }

    int[] outCache = new int[xSize * zSize];

    for (int zoom = 0; zoom < zSize; zoom++) {

      int srcPos = (zoom + (zPos & 0x1)) * i2 + (xPos & 0x1);
      System.arraycopy(out, srcPos, outCache, zoom * xSize, xSize);
    }

    return outCache;
  }





  protected int choose(int par1, int par2) {
    return (func_75902_a(2) == 0) ? par1 : par2;
  }


  protected int choose4(int id0, int id1, int id2, int id3) {
    if (id1 == id2 && id2 == id3)
      return id1;
    if (id0 == id1 && id0 == id2)
      return id0;
    if (id0 == id1 && id0 == id3)
      return id0;
    if (id0 == id2 && id0 == id3)
      return id0;
    if (id0 == id1 && id2 != id3)
      return id0;
    if (id0 == id2 && id1 != id3)
      return id0;
    if (id0 == id3 && id1 != id2)
      return id0;
    if (id1 == id0 && id2 != id3)
      return id1;
    if (id1 == id2 && id0 != id3)
      return id1;
    if (id1 == id3 && id0 != id2)
      return id1;
    if (id2 == id0 && id1 != id3)
      return id2;
    if (id2 == id1 && id0 != id3)
      return id2;
    if (id2 == id3 && id0 != id1)
      return id2;
    if (id3 == id0 && id1 != id2)
      return id2;
    if (id3 == id1 && id0 != id2)
      return id2;
    if (id3 == id2 && id0 != id1) {
      return id2;
    }

    int rand = func_75902_a(4);
    return (rand == 0) ? id0 : ((rand == 1) ? id1 : ((rand == 2) ? id2 : id3));
  }



  public static GenLayerTFC magnify(long par0, GenLayerTFC par2GenLayer, int par3) {
    Object var4 = par2GenLayer;
    for (int var5 = 0; var5 < par3; var5++)
      var4 = new GenLayerZoomTFC(par0 + var5, (GenLayerTFC)var4);
    return (GenLayerTFC)var4;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\GenLayers\GenLayerZoomTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */