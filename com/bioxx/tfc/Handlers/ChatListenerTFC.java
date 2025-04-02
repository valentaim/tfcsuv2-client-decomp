package com.bioxx.tfc.Handlers;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.event.ServerChatEvent;





public class ChatListenerTFC
{
  @SubscribeEvent
  public void onServerChatEvent(ServerChatEvent event) {
    String msg = event.message;
    long soberTime = (TFC_Core.getPlayerFoodStats((EntityPlayer)event.player)).soberTime;
    if (soberTime > TFC_Time.getTotalTicks()) {


      String s = "аеиоуыврсфлзкбнмАЕИОУЫВРСФЛЗКБНМ";
      Random rand = new Random();
      soberTime -= TFC_Time.getTotalTicks();
      for (int i = 0; i < event.message.length() - 1; i++) {

        String start = event.message.substring(0, i);
        String s2 = event.message.substring(i, i + 1);
        String end = event.message.substring(i + 1);

        if (event.message.charAt(0) != '/') {

          int chance = Math.max(1, 11 - (int)(soberTime / 1000L));
          if (s.indexOf(s2) != -1 && rand.nextInt(chance) == 0) {

            int n = rand.nextInt(2);
            int m = 0;
            msg = start + s2;
            for (int j = 0; j < n; j++)
            {
              msg = msg + (end.substring(0, 1).toUpperCase().equals(end.substring(0, 1)) ? s2 : s2.toLowerCase());
            }
            if (("С".equals(s2) || "с".equals(s2)) && !"С".equals(end.substring(0, 1)) && !"с".equals(end.substring(0, 1))) {


              msg = msg + (s2.toUpperCase().equals(s2) ? (end.substring(0, 1).toUpperCase().equals(end.substring(0, 1)) ? "Х" : "х".toLowerCase()) : "х");

              m++;
            }
            msg = msg + end;
            i += m;
          }
        }
      }



      event.component = new ChatComponentTranslation("chat.type.text", new Object[] { event.username, msg });
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\ChatListenerTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */