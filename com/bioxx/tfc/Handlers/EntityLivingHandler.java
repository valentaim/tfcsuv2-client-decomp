package com.bioxx.tfc.Handlers;

import com.bioxx.tfc.Core.Player.FoodStatsTFC;
import com.bioxx.tfc.Core.Player.InventoryPlayerTFC;
import com.bioxx.tfc.Core.Player.PlayerInfo;
import com.bioxx.tfc.Core.Player.PlayerInventory;
import com.bioxx.tfc.Core.Player.PlayerManagerTFC;
import com.bioxx.tfc.Core.Player.SkillStats;
import com.bioxx.tfc.Core.TFC_Achievements;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.Entities.EntityProjectileTFC;
import com.bioxx.tfc.Food.ItemFoodTFC;
import com.bioxx.tfc.Handlers.Network.AbstractPacket;
import com.bioxx.tfc.Handlers.Network.PlayerUpdatePacket;
import com.bioxx.tfc.Items.ItemQuiver;
import com.bioxx.tfc.Items.Tools.ItemCustomBow;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.api.Food;
import com.bioxx.tfc.api.Interfaces.IEquipable;
import com.bioxx.tfc.api.TFCAttributes;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.TFCOptions;
import com.bioxx.tfc.api.Util.Helper;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatBase;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;












public class EntityLivingHandler
{
  @SubscribeEvent
  public void onEntityLivingUpdate(LivingEvent.LivingUpdateEvent event) {
    if (event.entityLiving instanceof EntityPlayer) {

      EntityPlayer player = (EntityPlayer)event.entityLiving;

      float newMaxHealth = FoodStatsTFC.getMaxHealth(player);
      float oldMaxHealth = (float)player.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111126_e();
      if (oldMaxHealth != newMaxHealth)
      {
        player.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(newMaxHealth);
      }

      if (!player.field_70170_p.field_72995_K) {


        TFC_Core.handleItemTicking(player.field_71071_by.field_70462_a, player.field_70170_p, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v);








        player.func_71024_bL().func_75122_a(20 - player.func_71024_bL().func_75116_a(), 0.0F);

        FoodStatsTFC foodstats = TFC_Core.getPlayerFoodStats(player);
        foodstats.onUpdate(player);
        TFC_Core.setPlayerFoodStats(player, foodstats);

        if (foodstats.shouldSendUpdate()) {

          PlayerUpdatePacket playerUpdatePacket = new PlayerUpdatePacket(player, 0);
          TerraFirmaCraft.PACKET_PIPELINE.sendTo((AbstractPacket)playerUpdatePacket, (EntityPlayerMP)player);
        }
        if (foodstats.waterLevel / foodstats.getMaxWater(player) <= 0.25F) {

          setThirsty(player, true);
        }
        else if (foodstats.waterLevel / foodstats.getMaxWater(player) <= 0.5F) {

          if (player.func_70051_ag()) {
            player.func_70031_b(false);
          }
        } else {

          setThirsty(player, false);
        }
        if (foodstats.stomachLevel / foodstats.getMaxStomach(player) <= 0.25F) {

          player.func_70690_d(new PotionEffect(Potion.field_76419_f.field_76415_H, 20, 1));
          player.func_70690_d(new PotionEffect(Potion.field_76437_t.field_76415_H, 20, 1));
        }


        boolean isOverburdened = false;
        if (!player.field_71075_bZ.field_75098_d)
        {
          for (int i = 0; i < player.field_71071_by.field_70462_a.length; i++) {

            ItemStack is = player.field_71071_by.func_70301_a(i);
            if (is != null && is.func_77973_b() instanceof IEquipable) {

              isOverburdened = ((IEquipable)is.func_77973_b()).getTooHeavyToCarry(is);
              if (isOverburdened) {
                break;
              }
            }
          }
        }
        setOverburdened(player, isOverburdened);


        NBTTagCompound nbt = player.getEntityData();
        long spawnProtectionTimer = nbt.func_74764_b("spawnProtectionTimer") ? nbt.func_74763_f("spawnProtectionTimer") : (TFC_Time.getTotalTicks() + 1000L);
        if (spawnProtectionTimer < TFC_Time.getTotalTicks())
        {

          for (int i = -2; i < 3; i++) {

            for (int k = -2; k < 3; k++) {

              int lastChunkX = (int)Math.floor(player.field_70165_t) >> 4;
              int lastChunkZ = (int)Math.floor(player.field_70161_v) >> 4;
              TFC_Core.getCDM(player.field_70170_p).addProtection(lastChunkX + i, lastChunkZ + k, TFCOptions.protectionGain);
            }
          }

          spawnProtectionTimer += 1000L;
          nbt.func_74772_a("spawnProtectionTimer", spawnProtectionTimer);
        }

      } else {

        PlayerInfo pi = PlayerManagerTFC.getInstance().getClientPlayer();
        FoodStatsTFC foodstats = TFC_Core.getPlayerFoodStats(player);
        foodstats.clientUpdate();

        if (pi != null && pi.playerUUID.equals(player.func_110124_au())) {

          foodstats.onUpdate(player);
          if (player.field_71071_by.func_70448_g() != null) {

            if (player.field_71071_by.func_70448_g().func_77973_b() instanceof com.bioxx.tfc.Food.ItemMeal) {

              pi.guishowFoodRestoreAmount = true;
              pi.guiFoodRestoreAmount = Food.getWeight(player.field_71071_by.func_70448_g());
            }
            else if (player.field_71071_by.func_70448_g().func_77973_b() instanceof ItemFoodTFC) {

              pi.guishowFoodRestoreAmount = true;
              pi.guiFoodRestoreAmount = Food.getWeight(player.field_71071_by.func_70448_g());
            } else {

              pi.guishowFoodRestoreAmount = false;
            }
          } else {
            pi.guishowFoodRestoreAmount = false;
          }
        }
      }
    }
  }


  public void setThirsty(EntityPlayer player, boolean b) {
    IAttributeInstance iattributeinstance = player.func_110148_a(SharedMonsterAttributes.field_111263_d);

    if (iattributeinstance.func_111127_a(TFCAttributes.THIRSTY_UUID) != null)
    {
      iattributeinstance.func_111124_b(TFCAttributes.THIRSTY);
    }

    if (b)
    {
      iattributeinstance.func_111121_a(TFCAttributes.THIRSTY);
    }
  }


  public void setOverburdened(EntityPlayer player, boolean b) {
    IAttributeInstance iattributeinstance = player.func_110148_a(SharedMonsterAttributes.field_111263_d);

    if (iattributeinstance.func_111127_a(TFCAttributes.OVERBURDENED_UUID) != null)
    {
      iattributeinstance.func_111124_b(TFCAttributes.OVERBURDENED);
    }

    if (b)
    {
      iattributeinstance.func_111121_a(TFCAttributes.OVERBURDENED);
    }
  }


  @SubscribeEvent
  @SideOnly(Side.CLIENT)
  public void handleFOV(FOVUpdateEvent event) {
    EntityPlayerSP entityPlayerSP = event.entity;


    IAttributeInstance iattributeinstance = entityPlayerSP.func_110148_a(SharedMonsterAttributes.field_111263_d);
    if (iattributeinstance.func_111127_a(TFCAttributes.OVERBURDENED_UUID) != null) {

      event.newfov = 1.0F;

      return;
    }

    if (entityPlayerSP.func_71039_bw() && entityPlayerSP.func_71011_bu().func_77973_b() instanceof ItemCustomBow) {

      float fov = 1.0F;
      int duration = entityPlayerSP.func_71057_bx();
      float speed = ItemCustomBow.getUseSpeed((EntityPlayer)entityPlayerSP);
      float force = duration / speed;

      if (force > 1.0F) {

        force = 1.0F;
      }
      else {

        force *= force;
      }

      fov *= 1.0F - force * 0.15F;
      event.newfov = fov;
    }
  }


  @SubscribeEvent
  public void handleItemPickup(EntityItemPickupEvent event) {
    EntityPlayer player = event.entityPlayer;
    ItemStack item = event.item.func_92059_d();
    if (player.field_71071_by instanceof InventoryPlayerTFC) {

      ItemStack backItem = ((InventoryPlayerTFC)player.field_71071_by).extraEquipInventory[0];


      if (backItem == null && item.func_77973_b() instanceof IEquipable) {

        IEquipable equipment = (IEquipable)item.func_77973_b();
        if (equipment.getEquipType(item) == IEquipable.EquipType.BACK && (equipment == TFCItems.quiver || equipment.getTooHeavyToCarry(item)))
        {
          PlayerInventory.putItemToExtraSlot(player, item.func_77946_l());

          item.field_77994_a = 0;
          event.item.func_92058_a(item);
        }

      }
      else if (backItem != null && backItem.func_77973_b() instanceof ItemQuiver) {

        ItemQuiver quiver = (ItemQuiver)backItem.func_77973_b();


        if (item.func_77973_b() instanceof com.bioxx.tfc.Items.ItemArrow) {

          ItemStack is = quiver.addItem(backItem, item);
          if (is != null) {
            event.item.func_92058_a(is);
          } else {

            is = item;
            is.field_77994_a = 0;
            event.item.func_92058_a(is);
            event.setResult(Event.Result.DENY);
          }

        } else if (item.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemJavelin) {


          boolean foundJav = false;
          for (int i = 0; i < 9; i++) {

            if (player.field_71071_by.func_70301_a(i) != null && player.field_71071_by.func_70301_a(i).func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemJavelin) {
              foundJav = true;
            }
          }

          if (foundJav) {

            ItemStack is = quiver.addItem(backItem, item);
            if (is == null) {

              is = item;
              is.field_77994_a = 0;
              event.item.func_92058_a(is);
              event.setResult(Event.Result.DENY);
            }
          }
        }
      }
    }

    if (item.func_77973_b() == TFCItems.looseRock) {
      player.func_71029_a((StatBase)TFC_Achievements.achLooseRock);
    } else if (item.func_77973_b() instanceof com.bioxx.tfc.Items.ItemOreSmall) {
      player.func_71029_a((StatBase)TFC_Achievements.achSmallOre);
    } else if (item.func_77973_b() instanceof com.bioxx.tfc.Items.ItemBloom) {
      player.func_71029_a((StatBase)TFC_Achievements.achIronAge);
    } else if (item.func_77973_b().equals(TFCItems.gemDiamond)) {
      player.func_71029_a((StatBase)TFC_Achievements.achDiamond);
    } else if (item.func_77973_b().equals(TFCItems.onion) && TFCOptions.onionsAreGross) {
      player.func_71029_a((StatBase)TFC_Achievements.achRutabaga);
    } else if (item.func_77973_b().equals(TFCItems.oreChunk) && (item.func_77960_j() == 11 || item.func_77960_j() == 46 || item.func_77960_j() == 60)) {
      player.func_71029_a((StatBase)TFC_Achievements.achLimonite);
    }
  }

  @SubscribeEvent
  public void onEntityDeath(LivingDeathEvent event) {
    EntityLivingBase entity = event.entityLiving;

    if (entity instanceof EntityPlayer) {

      EntityPlayer player = (EntityPlayer)entity;

      if (player.getClass().getSimpleName().equals("NPCEntity"))
        return;
      SkillStats skills = TFC_Core.getSkillStats(player);
      PlayerInfo pi = PlayerManagerTFC.getInstance().getPlayerInfoFromPlayer(player);
      pi.tempSkills = skills;



      if (entity.field_70170_p.func_82736_K().func_82766_b("keepInventory") && player.field_71071_by instanceof InventoryPlayerTFC)
      {
        pi.tempEquipment = (ItemStack[])((InventoryPlayerTFC)player.field_71071_by).extraEquipInventory.clone();
      }
    }

    if (event.entity.field_71093_bK == 1) {
      event.entity.func_71027_c(0);
    }
  }

  @SubscribeEvent
  public void onLivingDrop(LivingDropsEvent event) {
    boolean processed = false;
    if (!event.entity.field_70170_p.field_72995_K && event.recentlyHit && !(event.entity instanceof EntityPlayer) && !(event.entity instanceof net.minecraft.entity.monster.EntityZombie))
    {
      if (event.source.func_76364_f() instanceof EntityPlayer || event.source.func_76352_a()) {

        boolean foundFood = false;
        processed = true;
        ArrayList<EntityItem> drop = new ArrayList<>();
        EntityPlayer p = null;
        if (event.source.func_76364_f() instanceof EntityPlayer) {
          p = (EntityPlayer)event.source.func_76364_f();
        } else if (event.source.func_76364_f() instanceof EntityProjectileTFC) {

          EntityProjectileTFC proj = (EntityProjectileTFC)event.source.func_76364_f();
          if (proj.field_70250_c instanceof EntityPlayer)
            p = (EntityPlayer)proj.field_70250_c;
        }
        for (EntityItem ei : event.drops) {

          ItemStack is = ei.func_92059_d();
          if (is.func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood) {

            if (p == null)
              continue;
            foundFood = true;

            int sweetMod = Food.getSweetMod(is);
            int sourMod = Food.getSourMod(is);
            int saltyMod = Food.getSaltyMod(is);
            int bitterMod = Food.getBitterMod(is);
            int umamiMod = Food.getSavoryMod(is);

            float oldWeight = Food.getWeight(is);
            Food.setWeight(is, 0.0F);

            float newWeight = oldWeight * 0.3F;
            if (is.func_77973_b() instanceof fof.tfcsu.Items.ItemFoodInsect) newWeight = oldWeight;




            while (newWeight >= 0.1F) {

              float fw = Helper.roundNumber(Math.min(160.0F, newWeight), 10.0F);
              if (fw < 160.0F)
                newWeight = 0.0F;
              newWeight -= fw;


              ItemStack result = ItemFoodTFC.createTag(new ItemStack(is.func_77973_b(), 1), fw);

              if (sweetMod != 0)
                Food.setSweetMod(result, sweetMod);
              if (sourMod != 0)
                Food.setSourMod(result, sourMod);
              if (saltyMod != 0)
                Food.setSaltyMod(result, saltyMod);
              if (bitterMod != 0)
                Food.setBitterMod(result, bitterMod);
              if (umamiMod != 0) {
                Food.setSavoryMod(result, umamiMod);
              }
              drop.add(new EntityItem(event.entity.field_70170_p, event.entity.field_70165_t, event.entity.field_70163_u, event.entity.field_70161_v, result));
            }

            continue;
          }
          drop.add(ei);
        }

        event.drops.clear();
        event.drops.addAll(drop);
        if (foundFood && p != null)
        {
          TFC_Core.getSkillStats(p).increaseSkill("skill.butchering", 1);
        }
      }
    }

    if (!processed && !(event.entity instanceof EntityPlayer) && !(event.entity instanceof net.minecraft.entity.monster.EntityZombie)) {

      ArrayList<EntityItem> drop = new ArrayList<>();
      for (EntityItem ei : event.drops) {

        if (!(ei.func_92059_d().func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood))
        {
          drop.add(ei);
        }
      }
      event.drops.clear();
      event.drops.addAll(drop);
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\EntityLivingHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */