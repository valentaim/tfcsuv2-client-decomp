package com.bioxx.tfc.Core.Player;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;


public class PlayerManagerTFC
{
  public List<PlayerInfo> players;
  private static final PlayerManagerTFC INSTANCE = new PlayerManagerTFC();


  public static final PlayerManagerTFC getInstance() {
    return INSTANCE;
  }


  private PlayerManagerTFC() {
    this.players = new ArrayList<>();
  }


  public PlayerInfo getPlayerInfoFromPlayer(EntityPlayer player) {
    for (PlayerInfo pi : this.players) {

      if (pi.playerName.equals(player.func_70005_c_()) && pi.playerUUID.equals(player.func_110124_au()))
        return pi;
    }
    return null;
  }


  public PlayerInfo getPlayerInfoFromName(String name) {
    for (PlayerInfo pi : this.players) {

      if (pi.playerName.equals(name))
        return pi;
    }
    return null;
  }


  public PlayerInfo getPlayerInfoFromUUID(String uuid) {
    for (PlayerInfo pi : this.players) {

      if (pi.playerUUID.toString().equals(uuid))
        return pi;
    }
    return null;
  }


  public PlayerInfo getClientPlayer() {
    if (!this.players.isEmpty())
      return this.players.get(0);
    return null;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\Player\PlayerManagerTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */