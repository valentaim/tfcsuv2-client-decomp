package com.bioxx.tfc.api.Tools;

import com.bioxx.tfc.Core.Player.PlayerInfo;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;






public class ChiselManager
{
  private static final ChiselManager INSTANCE = new ChiselManager(); public static final ChiselManager getInstance() {
    return INSTANCE;
  }

  private List<ChiselMode> chiselModes;

  private ChiselManager() {
    this.chiselModes = new ArrayList<>();
  }


  public void addChiselMode(ChiselMode mode) {
    this.chiselModes.add(mode);
  }


  public ResourceLocation getResourceLocation(int mode) {
    return ((ChiselMode)this.chiselModes.get(mode)).getResourceLocation();
  }

  public int getTextureU(int mode) {
    return ((ChiselMode)this.chiselModes.get(mode)).getTextureU();
  }

  public int getTextureV(int mode) {
    return ((ChiselMode)this.chiselModes.get(mode)).getTextureV();
  }
  public int getDivX(int mode, Block block) {
    return ((ChiselMode)this.chiselModes.get(mode)).getDivX(block);
  } public int getDivY(int mode, Block block) {
    return ((ChiselMode)this.chiselModes.get(mode)).getDivY(block);
  } public int getDivZ(int mode, Block block) {
    return ((ChiselMode)this.chiselModes.get(mode)).getDivZ(block);
  } public int getSize() {
    return this.chiselModes.size();
  } public void setDivision(int mode, int sideHit) {
    ((ChiselMode)this.chiselModes.get(mode)).setDivision(sideHit);
  }
  public boolean onUsedHandler(World world, EntityPlayer player, int x, int y, int z, Block id, int meta, int side, float hitX, float hitY, float hitZ) {
    int mode = -1;
    PlayerInfo pi = null;
    pi = ChiselMode.playerInfo(world, player);
    if (pi != null) mode = pi.chiselMode;
    return ((ChiselMode)this.chiselModes.get(mode)).onUsedHandler(world, player, x, y, z, id, meta, side, hitX, hitY, hitZ);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Tools\ChiselManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */