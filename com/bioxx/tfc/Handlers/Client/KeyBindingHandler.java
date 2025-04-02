package com.bioxx.tfc.Handlers.Client;

import com.bioxx.tfc.Blocks.BlockDetailed;
import com.bioxx.tfc.Core.Player.PlayerInfo;
import com.bioxx.tfc.Core.Player.PlayerManagerTFC;
import com.bioxx.tfc.Handlers.Network.AbstractPacket;
import com.bioxx.tfc.Handlers.Network.KeyPressPacket;
import com.bioxx.tfc.TerraFirmaCraft;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;







public class KeyBindingHandler
{
  public static KeyBinding keyToolMode = new KeyBinding("key.ToolMode", 50, "TerraFirmaCraft");
  public static KeyBinding keyLockTool = new KeyBinding("key.LockTool", 38, "TerraFirmaCraft");


  @SubscribeEvent
  public void onKeyInput(InputEvent.KeyInputEvent event) {
    PlayerInfo pi = PlayerManagerTFC.getInstance().getClientPlayer();
    EntityClientPlayerMP player = (FMLClientHandler.instance().getClient()).field_71439_g;

    if ((FMLClientHandler.instance().getClient()).field_71415_G &&
      (FMLClientHandler.instance().getClient()).field_71439_g.func_71045_bC() != null &&
      (FMLClientHandler.instance().getClient()).field_71462_r == null)
    {
      if (keyToolMode.func_151468_f()) {

        if (player.func_71045_bC().func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemChisel)
        {
          pi.switchChiselMode();


          KeyPressPacket keyPressPacket = new KeyPressPacket(pi.chiselMode);
          TerraFirmaCraft.PACKET_PIPELINE.sendToServer((AbstractPacket)keyPressPacket);
        }
        else if (player.func_71045_bC().func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemCustomHoe)
        {
          pi.switchHoeMode((EntityPlayer)player);
        }

      } else if (keyLockTool.func_151468_f() && pi != null) {

        if (pi.lockX == -9999999) {

          pi.lockX = BlockDetailed.lockX;
          pi.lockY = BlockDetailed.lockY;
          pi.lockZ = BlockDetailed.lockZ;
        }
        else {

          pi.lockX = -9999999;
          pi.lockY = -9999999;
          pi.lockZ = -9999999;
        }
      }
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\Client\KeyBindingHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */