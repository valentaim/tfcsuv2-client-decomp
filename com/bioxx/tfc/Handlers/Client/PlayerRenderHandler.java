package com.bioxx.tfc.Handlers.Client;

import com.bioxx.tfc.Core.Player.InventoryPlayerTFC;
import com.bioxx.tfc.Render.RenderLargeItem;
import com.bioxx.tfc.Render.RenderQuiver;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderPlayerEvent;



public class PlayerRenderHandler
{
  public static final RenderQuiver RENDER_QUIVER = new RenderQuiver();
  public static final RenderLargeItem RENDER_LARGE = new RenderLargeItem();


  @SubscribeEvent
  @SideOnly(Side.CLIENT)
  public void onPlayerRenderTick(RenderPlayerEvent.Specials.Pre e) {
    EntityLivingBase el = e.entityLiving;
    if (el instanceof EntityPlayer &&
      ((EntityPlayer)el).field_71071_by instanceof InventoryPlayerTFC) {
      ItemStack[] equipables = ((InventoryPlayerTFC)((EntityPlayer)el).field_71071_by).extraEquipInventory;
      for (ItemStack i : equipables) {

        if (i != null && i.func_77973_b() instanceof com.bioxx.tfc.Items.ItemQuiver) {
          RENDER_QUIVER.render(e.entityLiving, i, e);
        }
        else if (i != null) {
          RENDER_LARGE.render(el, i, e);
        }
      }
    }
  }

  @SubscribeEvent
  public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent e) {}

  @SubscribeEvent
  @SideOnly(Side.CLIENT)
  public void onPlayerTick(TickEvent.PlayerTickEvent e) {}
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\Client\PlayerRenderHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */