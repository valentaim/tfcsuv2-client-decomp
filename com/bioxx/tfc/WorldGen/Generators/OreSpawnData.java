package com.bioxx.tfc.WorldGen.Generators;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.Enums.EnumOreGen;
import com.bioxx.tfc.api.TFCBlocks;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;

public class OreSpawnData {
  public EnumOreGen type;
  public int size;
  public int meta;
  public int rarity;
  public int min = 5; public int max = 128; public int rnd; public int SphereXSize; public int SphereYSize;
  public int SphereZSize;
  public int VeinWidth;
  public int VeinBaseHeight;

  public OreSpawnData(String t, String s, String blockName, int m, int r, String[] baseRocks) {
    this.block = Block.func_149684_b(blockName);

    if (this.block == null) {

      TerraFirmaCraft.LOG.error(TFC_Core.translate("error.error") + " " + TFC_Core.translate("error.OreCFG") + " " + blockName);
      throw new NullPointerException(TFC_Core.translate("error.OreCFG") + " " + blockName);
    }

    this.meta = m;
    this.rarity = r;
    if (EnumOreGen.Area.name().equalsIgnoreCase(t)) { this.type = EnumOreGen.Area; }
    else if (EnumOreGen.Vein.name().equalsIgnoreCase(t)) { this.type = EnumOreGen.Vein; }
    else if (EnumOreGen.Lens.name().equalsIgnoreCase(t)) { this.type = EnumOreGen.Lens; }
    else { this.type = EnumOreGen.Vein; }

    if ("small".equals(s)) { this.size = 0; }
    else if ("medium".equals(s)) { this.size = 1; }
    else { this.size = 2; }

    this.base = new HashMap<>();
    for (String name : baseRocks)
      getOre(name);
  }
  public int VeinDownFactor; public int AreaNumber;
  public int AreaMaxDistance;

  public OreSpawnData(String t, String s, String blockName, int m, int r, String[] baseRocks, int minHeight, int maxHeight, int rnd, int sxs, int sys, int szs, int vw, int vbh, int vdf, int an, int amd, int cs) {
    this(t, s, blockName, m, r, baseRocks);
    this.min = minHeight;
    this.max = maxHeight;
    this.rnd = rnd;
    this.SphereXSize = sxs;
    this.SphereYSize = sys;
    this.SphereZSize = szs;
    this.VeinWidth = vw;
    this.VeinBaseHeight = vbh;
    this.VeinDownFactor = vdf;
    this.AreaNumber = an;
    this.AreaMaxDistance = amd;
    this.CellSize = cs;
  }
  public int CellSize; public Block block; public Map<Block, List<Integer>> base;
  private void getOre(String name) {
    int i;
    for (i = 0; i < Global.STONE_IGIN.length; i++) {
      if (name.equalsIgnoreCase(Global.STONE_IGIN[i])) {

        List<Integer> metadata = this.base.containsKey(TFCBlocks.stoneIgIn) ? this.base.get(TFCBlocks.stoneIgIn) : new ArrayList<>();
        metadata.add(Integer.valueOf(i));
        this.base.put(TFCBlocks.stoneIgIn, metadata);

        return;
      }
    }
    for (i = 0; i < Global.STONE_IGEX.length; i++) {

      if (name.equalsIgnoreCase(Global.STONE_IGEX[i])) {

        List<Integer> metadata = this.base.containsKey(TFCBlocks.stoneIgEx) ? this.base.get(TFCBlocks.stoneIgEx) : new ArrayList<>();
        metadata.add(Integer.valueOf(i));
        this.base.put(TFCBlocks.stoneIgEx, metadata);

        return;
      }
    }
    for (i = 0; i < Global.STONE_SED.length; i++) {

      if (name.equalsIgnoreCase(Global.STONE_SED[i])) {

        List<Integer> metadata = this.base.containsKey(TFCBlocks.stoneSed) ? this.base.get(TFCBlocks.stoneSed) : new ArrayList<>();
        metadata.add(Integer.valueOf(i));
        this.base.put(TFCBlocks.stoneSed, metadata);

        return;
      }
    }
    for (i = 0; i < Global.STONE_MM.length; i++) {

      if (name.equalsIgnoreCase(Global.STONE_MM[i])) {

        List<Integer> metadata = this.base.containsKey(TFCBlocks.stoneMM) ? this.base.get(TFCBlocks.stoneMM) : new ArrayList<>();
        metadata.add(Integer.valueOf(i));
        this.base.put(TFCBlocks.stoneMM, metadata);

        return;
      }
    }
    if ("igneous intrusive".equalsIgnoreCase(name)) {

      List<Integer> metadata = this.base.containsKey(TFCBlocks.stoneIgIn) ? this.base.get(TFCBlocks.stoneIgIn) : new ArrayList<>();
      metadata.add(Integer.valueOf(-1));
      this.base.put(TFCBlocks.stoneIgIn, metadata);
      return;
    }
    if ("igneous extrusive".equalsIgnoreCase(name)) {

      List<Integer> metadata = this.base.containsKey(TFCBlocks.stoneIgEx) ? this.base.get(TFCBlocks.stoneIgEx) : new ArrayList<>();
      metadata.add(Integer.valueOf(-1));
      this.base.put(TFCBlocks.stoneIgEx, metadata);
      return;
    }
    if ("sedimentary".equalsIgnoreCase(name)) {

      List<Integer> metadata = this.base.containsKey(TFCBlocks.stoneSed) ? this.base.get(TFCBlocks.stoneSed) : new ArrayList<>();
      metadata.add(Integer.valueOf(-1));
      this.base.put(TFCBlocks.stoneSed, metadata);
      return;
    }
    if ("metamorphic".equalsIgnoreCase(name)) {

      List<Integer> metadata = this.base.containsKey(TFCBlocks.stoneMM) ? this.base.get(TFCBlocks.stoneMM) : new ArrayList<>();
      metadata.add(Integer.valueOf(-1));
      this.base.put(TFCBlocks.stoneMM, metadata);
      return;
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\OreSpawnData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */