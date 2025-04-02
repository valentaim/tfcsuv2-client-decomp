package com.bioxx.tfc.Handlers;

import com.bioxx.tfc.Core.Player.FoodStatsTFC;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.Util.Helper;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;



public class PlayerInteractHandler
{
  private Map<UUID, Long> lastDrink = new HashMap<>();


  @SubscribeEvent
  public void onPlayerInteract(PlayerInteractEvent event) {
    if (event.entityPlayer.field_70170_p.field_72995_K) {
      return;
    }
    ItemStack itemInHand = event.entityPlayer.func_71045_bC();

    boolean validAction = (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK || event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR);

    if (validAction && event.getResult() != Event.Result.DENY && itemInHand == null)
    {
      handleDrinkingWater(event.entityPlayer);
    }
  }


  private void handleDrinkingWater(EntityPlayer entityPlayer) {
    Long lastCheck = this.lastDrink.get(entityPlayer.func_110124_au());
    if (lastCheck != null && lastCheck.longValue() + 20L > TFC_Time.getTotalTicks())
      return;
    this.lastDrink.put(entityPlayer.func_110124_au(), Long.valueOf(TFC_Time.getTotalTicks()));
    World world = entityPlayer.field_70170_p;
    MovingObjectPosition mop = Helper.getMovingObjectPositionFromPlayer(world, (EntityLivingBase)entityPlayer, true);
    FoodStatsTFC fs = TFC_Core.getPlayerFoodStats(entityPlayer);
    Boolean canDrink = Boolean.valueOf(((fs.getMaxWater(entityPlayer) - 500) > fs.waterLevel));


    if (mop != null && canDrink.booleanValue() && mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK)
    {
      if (TFC_Core.isFreshWater(world.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d))) {


        fs.restoreWater(entityPlayer, 2000);


        world.func_72956_a((Entity)entityPlayer, "random.drink", 0.2F, world.field_73012_v.nextFloat() * 0.1F + 0.9F);
      }
    }
  }







  @SubscribeEvent
  public void onItemPickup(EntityItemPickupEvent event) {
    EntityItem item = event.item;
    ItemStack is = item.func_92059_d();
    EntityPlayer player = event.entityPlayer;

    if (is.func_77973_b() == Items.field_151055_y) {

      int count = is.field_77994_a;
      item.field_145804_b = 100;
      item.func_70106_y();
      item.func_82142_c(true);
      Random rand = player.field_70170_p.field_73012_v;
      player.field_70170_p.func_72956_a((Entity)player, "random.pop", 0.2F, ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
      ItemStack tfcSticks = new ItemStack(TFCItems.stick, count);
      player.field_71071_by.func_70441_a(tfcSticks);
    }
    else if (is.func_77973_b() == Item.func_150898_a(Blocks.field_150344_f) && is.func_77960_j() == 0) {

      int count = is.field_77994_a;
      item.field_145804_b = 100;
      item.func_70106_y();
      item.func_82142_c(true);
      Random rand = player.field_70170_p.field_73012_v;
      player.field_70170_p.func_72956_a((Entity)player, "random.pop", 0.2F, ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
      ItemStack tfcPlanks = new ItemStack(TFCBlocks.planks, count);
      player.field_71071_by.func_70441_a(tfcPlanks);
    }
    else if (is.func_77973_b() == Item.func_150898_a(Blocks.field_150428_aP)) {

      int count = is.field_77994_a;
      item.field_145804_b = 100;
      item.func_70106_y();
      item.func_82142_c(true);
      Random rand = player.field_70170_p.field_73012_v;
      player.field_70170_p.func_72956_a((Entity)player, "random.pop", 0.2F, ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
      ItemStack jackOLanternTFC = new ItemStack(TFCBlocks.litPumpkin, count);
      player.field_71071_by.func_70441_a(jackOLanternTFC);
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\PlayerInteractHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */