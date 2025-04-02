package com.bioxx.tfc.Core.Player;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.Tools.ChiselManager;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;






public class PlayerInfo
{
  public String playerName;
  public UUID playerUUID;
  public byte chiselMode;
  public int hoeMode;
  public int lockX = -9999999;
  public int lockY = -9999999;
  public int lockZ = -9999999;

  public ItemStack specialCraftingType;

  public ItemStack specialCraftingTypeAlternate;
  private long lastChange;
  public short moldTransferTimer = 1000;

  public boolean guishowFoodRestoreAmount;

  public float guiFoodRestoreAmount;

  public boolean[] knappingInterface;
  public SkillStats tempSkills;
  public ItemStack[] tempEquipment = new ItemStack[TFC_Core.getExtraEquipInventorySize()];


  public PlayerInfo(String name, UUID uuid) {
    this.playerName = name;
    this.playerUUID = uuid;
    this.chiselMode = 0;
    this.specialCraftingType = null;
    this.specialCraftingTypeAlternate = null;
    this.lastChange = 0L;
    this.hoeMode = 0;
    this.knappingInterface = new boolean[25];
  }



  public void switchHoeMode(EntityPlayer player) {
    int MODE_NUTRIENT = 1;

    SkillStats.SkillRank agRank = TFC_Core.getSkillStats(player).getSkillRank("skill.agriculture");


    if (this.lastChange + 3L < TFC_Time.getTotalTicks()) {

      boolean isMetalHoe = true;

      if (player.func_71045_bC() != null && (player
        .func_71045_bC().func_77973_b() == TFCItems.igInHoe || player
        .func_71045_bC().func_77973_b() == TFCItems.igExHoe || player
        .func_71045_bC().func_77973_b() == TFCItems.sedHoe || player
        .func_71045_bC().func_77973_b() == TFCItems.mMHoe))
      {
        isMetalHoe = false;
      }

      this.hoeMode = (this.hoeMode == 3) ? 0 : ++this.hoeMode;
      if (this.hoeMode == 1 && (!isMetalHoe || (isMetalHoe && agRank != SkillStats.SkillRank.Expert && agRank != SkillStats.SkillRank.Master))) {
        this.hoeMode++;
      }
      this.lastChange = TFC_Time.getTotalTicks();
    }
  }


  public void switchChiselMode() {
    if (this.lastChange + 3L < TFC_Time.getTotalTicks()) {



      if (this.chiselMode == ChiselManager.getInstance().getSize() - 1) {

        this.chiselMode = 0;
      }
      else {

        this.chiselMode = (byte)(this.chiselMode + 1);
      }
      this.lastChange = TFC_Time.getTotalTicks();
    }
  }


  public void setChiselMode(byte mode) {
    this.chiselMode = mode;
  }


  public boolean lockMatches(int x, int y, int z) {
    return ((this.lockX == -9999999 || this.lockX == x) && (this.lockY == -9999999 || this.lockY == y) && (this.lockZ == -9999999 || this.lockZ == z));
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\Player\PlayerInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */