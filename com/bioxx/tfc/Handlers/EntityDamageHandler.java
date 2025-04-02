package com.bioxx.tfc.Handlers;

import com.bioxx.tfc.Core.Player.FoodStatsTFC;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Entities.EntityJavelin;
import com.bioxx.tfc.Items.ItemTFCArmor;
import com.bioxx.tfc.Items.Tools.ItemWeapon;
import com.bioxx.tfc.api.Crafting.AnvilManager;
import com.bioxx.tfc.api.Enums.EnumDamageType;
import com.bioxx.tfc.api.Events.EntityArmorCalcEvent;
import com.bioxx.tfc.api.Interfaces.ICausesDamage;
import com.bioxx.tfc.api.Interfaces.IInnateArmor;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fof.tfcsu.Enchantment.EnchantmentWeapon;
import java.lang.reflect.Field;
import java.util.Random;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.smart.moving.IEntityPlayerMP;
import net.smart.moving.playerapi.SmartMoving;
import terramisc.items.tools.ItemCustomArmor;




public class EntityDamageHandler
{
  @SubscribeEvent
  public void onEntityHurt(LivingHurtEvent event) {
    EntityLivingBase entity = event.entityLiving;
    if (entity instanceof EntityPlayer) {
      float curMaxHealth = (float)((EntityPlayer)entity).func_110148_a(SharedMonsterAttributes.field_111267_a).func_111126_e();
      float newMaxHealth = FoodStatsTFC.getMaxHealth((EntityPlayer)entity);
      float h = ((EntityPlayer)entity).func_110143_aJ();
      if (newMaxHealth != curMaxHealth) ((EntityPlayer)entity).func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(newMaxHealth);
      if (newMaxHealth < h) ((EntityPlayer)entity).func_70606_j(newMaxHealth);

    }
    if (event.source == DamageSource.field_76370_b) { event.ammount = 50.0F; }
    else if (event.source == DamageSource.field_76379_h)
    { float healthMod = TFC_Core.getEntityMaxHealth(entity) / 1000.0F;
      event.ammount *= 80.0F * healthMod;

      if (event.entityLiving instanceof net.minecraft.entity.passive.EntityAnimal) event.ammount /= 10.0F;  }
    else if (event.source == DamageSource.field_76369_e) { event.ammount = 50.0F; }
    else if (event.source != DamageSource.field_76367_g)
    { if (event.source == DamageSource.field_76371_c) { event.ammount = 100.0F; }
      else if (event.source == DamageSource.field_76368_d) { event.ammount = 100.0F; }
      else if (event.source == DamageSource.field_82729_p) { event.ammount = 100.0F; }
      else if (event.source.func_94541_c())
      { event.ammount *= 30.0F;

        if (entity instanceof EntityPlayer) {
          IEntityPlayerMP sm_player = SmartMoving.getServerPlayerBase((EntityPlayer)event.entity);
          if ((sm_player.getMoving()).isCrawling) {
            event.ammount /= 2.0F;
          }
        }
         }

      else if (event.source == DamageSource.field_76376_m && entity.func_110143_aJ() > 25.0F) { event.ammount = 25.0F; }
      else if ("player".equals(event.source.field_76373_n) || "mob".equals(event.source.field_76373_n) || "arrow".equals(event.source.field_76373_n))

      { if (event.source.func_76346_g() != null && event.source.func_76346_g().getClass().getSimpleName().equals("NPCEntity")) event.ammount = getWeaponDamage(event.source.func_76346_g());

        double attacker_height = -1.0D;
        double target_height = -1.0D;

        if (!"arrow".equals(event.source.field_76373_n) && "mob".equals(event.source.field_76373_n) && event.entity instanceof EntityPlayer) {
          attacker_height = ((EntityLiving)event.source.func_76346_g()).field_70121_D.field_72337_e - ((EntityLiving)event.source.func_76346_g()).field_70121_D.field_72338_b;
          target_height = event.entityLiving.field_70121_D.field_72337_e - event.entityLiving.field_70121_D.field_72338_b;
        }
        event.ammount = applyArmorCalculations(entity, event.source, event.ammount, attacker_height, target_height);
        if ("arrow".equals(event.source.field_76373_n)) {
          Entity e = ((EntityDamageSourceIndirect)event.source).func_76364_f();
          if (e instanceof EntityJavelin) {
            ((EntityJavelin)e).setDamageTaken((short)(((EntityJavelin)e).damageTaken + 10));
            if (((EntityJavelin)e).damageTaken >= ((EntityJavelin)e).pickupItem.func_77612_l()) e.func_70106_y();

          }
        }  }
       }

  }

  public float getWeaponDamage(Entity e) {
    ItemStack is = ((EntityPlayer)e).field_71071_by.func_70448_g();
    float out = 0.0F; try {
      float base_damage;
      Field f = ItemWeapon.class.getDeclaredField("weaponBaseDamage");
      f.setAccessible(true);
      ItemWeapon iw = (ItemWeapon)is.func_77973_b();
      float damage = ((Float)f.get(iw)).floatValue();

      int level = EnchantmentWeapon.getEnchantmentLevel(is);
      if (level != 0) { base_damage = damage / (1 + level / 100); }
      else { base_damage = damage; }
       float end_damage = base_damage * (100 + level * 10) / 100.0F;
      out = (float)Math.floor((end_damage + end_damage * AnvilManager.getDamageBuff(is)));
    } catch (Exception exception) {}
    return (out < 50.0F) ? 50.0F : out;
  }


  protected int applyArmorCalculations(EntityLivingBase entity, DamageSource source, float originalDamage, double ah, double th) {
    ItemStack[] armor = entity.func_70035_c();
    int pierceRating = 0;
    int slashRating = 0;
    int crushRating = 0;

    EntityArmorCalcEvent eventPre = new EntityArmorCalcEvent(entity, originalDamage, EntityArmorCalcEvent.EventType.PRE);
    MinecraftForge.EVENT_BUS.post((Event)eventPre);
    float damage = eventPre.incomingDamage;

    if (!source.func_76363_c() && armor != null) {

      int location = getRandomSlot(entity.func_70681_au());
      if (ah != -1.0D && th != -1.0D) if (ah >= th) { location = getRandomSlotUpper(entity.func_70681_au()); }
        else if (th * 2.0D / 3.0D > ah) { location = getRandomSlotLower(entity.func_70681_au()); }


      if (armor[location] != null && (armor[location].func_77973_b() instanceof ItemTFCArmor || armor[location].func_77973_b() instanceof ItemCustomArmor)) {
        if (armor[location].func_77973_b() instanceof ItemTFCArmor) {
          pierceRating = ((ItemTFCArmor)armor[location].func_77973_b()).armorTypeTFC.getPiercingAR();
          slashRating = ((ItemTFCArmor)armor[location].func_77973_b()).armorTypeTFC.getSlashingAR();
          crushRating = ((ItemTFCArmor)armor[location].func_77973_b()).armorTypeTFC.getCrushingAR();
        } else if (armor[location].func_77973_b() instanceof ItemCustomArmor) {
          pierceRating = ((ItemCustomArmor)armor[location].func_77973_b()).ArmorType.getPiercingAR();
          slashRating = ((ItemCustomArmor)armor[location].func_77973_b()).ArmorType.getSlashingAR();
          crushRating = ((ItemCustomArmor)armor[location].func_77973_b()).ArmorType.getCrushingAR();
        }
        if (entity instanceof IInnateArmor) {
          pierceRating += ((IInnateArmor)entity).getPierceArmor();
          slashRating += ((IInnateArmor)entity).getSlashArmor();
          crushRating += ((IInnateArmor)entity).getCrushArmor();
        }


        float pierceMult = getDamageReduction(pierceRating);
        float slashMult = getDamageReduction(slashRating);
        float crushMult = getDamageReduction(crushRating);


        damage = processDamageSource(source, damage, pierceMult, slashMult, crushMult);


        if (damageItem(armor[location], (int)(processArmorDamage(armor[location], damage) / 2.5F), entity)) armor[location] = null;
      } else if (armor[location] == null || (armor[location] != null && !(armor[location].func_77973_b() instanceof ItemTFCArmor))) {
        if (entity instanceof IInnateArmor) {
          pierceRating += ((IInnateArmor)entity).getPierceArmor();
          slashRating += ((IInnateArmor)entity).getSlashArmor();
          crushRating += ((IInnateArmor)entity).getCrushArmor();
        }

        float pierceMult = getDamageReduction(pierceRating);
        float slashMult = getDamageReduction(slashRating);
        float crushMult = getDamageReduction(crushRating);

        damage = processDamageSource(source, damage, pierceMult, slashMult, crushMult);



        if (location == 3) { damage *= 1.75F; }
        else if (location == 0) { entity.func_70690_d(new PotionEffect(Potion.field_76421_d.func_76396_c(), 100, 1)); }

      }











      EntityArmorCalcEvent eventPost = new EntityArmorCalcEvent(entity, damage, EntityArmorCalcEvent.EventType.POST);
      MinecraftForge.EVENT_BUS.post((Event)eventPost);


      float hasHealth = entity.func_110143_aJ();
      entity.func_70606_j(entity.func_110143_aJ() - eventPost.incomingDamage);
      entity.func_110142_aN().func_94547_a(source, hasHealth, eventPost.incomingDamage);
    }
    return 0;
  }

  private float processDamageSource(DamageSource source, float damage, float pierceMult, float slashMult, float crushMult) {
    EnumDamageType damageType = getDamageType(source);

    switch (damageType) {
      case PIERCING:
        damage *= pierceMult;
        break;
      case SLASHING:
        damage *= slashMult;
        break;
      case CRUSHING:
        damage *= crushMult;
        break;
      case GENERIC:
        damage = (float)(damage * (((crushMult + slashMult + pierceMult) / 3.0F) - 0.25D)); break;
    }
    return Math.max(0.0F, damage);
  }


  private EnumDamageType getDamageType(DamageSource source) {
    if (source.func_76364_f() instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer)source.func_76364_f();
      if (player.func_71045_bC() != null && player
        .func_71045_bC().func_77973_b() instanceof ICausesDamage) return ((ICausesDamage)player.func_71045_bC().func_77973_b()).getDamageType();

    }
    if (source.func_76364_f() instanceof EntityLiving) {
      EntityLiving el = (EntityLiving)source.func_76364_f();
      if (el.func_70694_bm() != null && el.func_70694_bm().func_77973_b() instanceof ICausesDamage) return ((ICausesDamage)el.func_70694_bm().func_77973_b()).getDamageType();

    }
    if (source.func_76364_f() instanceof ICausesDamage) return ((ICausesDamage)source.func_76364_f()).getDamageType();

    return EnumDamageType.GENERIC;
  }



  private int getRandomSlot(Random rand) {
    int chance = rand.nextInt(100);
    if (chance < 30) return 3;
    if (chance < 45) return 0;
    if (chance < 75) return 2;
    return 1;
  }

  private int getRandomSlotUpper(Random rand) {
    int chance = rand.nextInt(100);
    if (chance < 10) return 0;
    if (chance < 30) return 1;
    if (chance < 60) return 2;
    return 3;
  }

  private int getRandomSlotLower(Random rand) {
    int chance = rand.nextInt(100);
    if (chance < 10) return 3;
    if (chance < 30) return 2;
    if (chance < 60) return 1;
    return 0;
  }

  private float processArmorDamage(ItemStack armor, float baseDamage) {
    if (armor.func_77942_o()) {
      NBTTagCompound nbt = armor.func_77978_p();
      if (nbt.func_74764_b("armorReductionBuff")) {
        float reductBuff = nbt.func_74771_c("armorReductionBuff") / 100.0F;
        return baseDamage - baseDamage * reductBuff;
      }
    }
    return baseDamage;
  }







  protected float getDamageReduction(int armorRating) {
    if (armorRating == -1000) armorRating = -999;
    return 1000.0F / (1000.0F + armorRating);
  }

  @SubscribeEvent
  public void onAttackEntity(AttackEntityEvent event) {
    if (event.entityLiving.field_70170_p.field_72995_K)
      return;
    EntityLivingBase attacker = event.entityLiving;
    EntityPlayer player = event.entityPlayer;
    Entity target = event.target;
    ItemStack stack = attacker.func_71124_b(0);
    if (stack != null && stack.func_77973_b().onLeftClickEntity(stack, player, target))
      return;
    if (target.func_70075_an() &&
      !target.func_85031_j(target)) {
      float damageAmount = 50.0F;
      if (stack != null) {
        damageAmount = (float)player.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();

        if (damageAmount == 1.0F) {
          damageAmount = 50.0F;
        }
      }



      if (player.func_70644_a(Potion.field_76420_g)) damageAmount += (3 << player.func_70660_b(Potion.field_76420_g).func_76458_c());

      if (player.func_70644_a(Potion.field_76437_t)) damageAmount -= (2 << player.func_70660_b(Potion.field_76437_t).func_76458_c());

      int knockback = 0;
      float enchantmentDamage = 0.0F;

      if (target instanceof EntityLiving) {
        enchantmentDamage = EnchantmentHelper.func_77512_a((EntityLivingBase)player, (EntityLivingBase)target);
        knockback += EnchantmentHelper.func_77507_b((EntityLivingBase)player, (EntityLivingBase)target);
      }

      if (player.func_70051_ag()) knockback++;

      if (damageAmount > 0.0F || enchantmentDamage > 0.0F) {
        boolean criticalHit = (player.field_70143_R > 0.0F && !player.field_70122_E && !player.func_70617_f_() && !player.func_70090_H() && !player.func_70644_a(Potion.field_76440_q) && player.field_70154_o == null && target instanceof EntityLiving);


        if (criticalHit && damageAmount > 0.0F) damageAmount += event.entity.field_70170_p.field_73012_v.nextInt((int)(damageAmount / 2.0F + 2.0F));

        damageAmount += enchantmentDamage;
        boolean onFire = false;
        int fireAspect = EnchantmentHelper.func_90036_a((EntityLivingBase)player);

        if (target instanceof EntityLiving && fireAspect > 0 && !target.func_70027_ad()) {
          onFire = true;
          target.func_70015_d(1);
        }

        boolean entityAttacked = target.func_70097_a(DamageSource.func_76365_a(player), damageAmount);

        if (entityAttacked) {
          if (knockback > 0) {
            target.func_70024_g((-MathHelper.func_76126_a(player.field_70177_z * 3.1415927F / 180.0F) * knockback * 0.5F), 0.1D, (
                MathHelper.func_76134_b(player.field_70177_z * 3.1415927F / 180.0F) * knockback * 0.5F));
            player.field_70159_w *= 0.6D;
            player.field_70179_y *= 0.6D;
            player.func_70031_b(false);
          }

          if (criticalHit) player.func_71009_b(target);

          if (enchantmentDamage > 0.0F) player.func_71047_c(target);

          if (damageAmount >= 18.0F) player.func_71029_a((StatBase)AchievementList.field_75999_E);

          player.func_130011_c(target);

          if (target instanceof EntityLiving) target.func_70097_a(DamageSource.func_92087_a((Entity)attacker), damageAmount);

        }
        ItemStack itemstack = player.func_71045_bC();
        Object object = target;

        if (target instanceof EntityDragonPart) {
          IEntityMultiPart ientitymultipart = ((EntityDragonPart)target).field_70259_a;
          if (ientitymultipart instanceof EntityLiving) object = ientitymultipart;

        }
        if (itemstack != null && object instanceof EntityLiving) {
          itemstack.func_77961_a((EntityLivingBase)object, player);
          if (itemstack.field_77994_a <= 0) player.func_71028_bD();

        }
        if (target instanceof EntityLivingBase) {
          player.func_71064_a(StatList.field_75951_w, Math.round(damageAmount * 10.0F));
          if (fireAspect > 0 && entityAttacked) { target.func_70015_d(fireAspect * 4); }
          else if (onFire) { target.func_70066_B(); }

        }
        player.func_71020_j(0.3F);
      }
    }

    event.setCanceled(true);
  }

  public boolean damageItem(ItemStack item, int p_77972_1_, EntityLivingBase p_77972_2_) {
    if ((!(p_77972_2_ instanceof EntityPlayer) || !((EntityPlayer)p_77972_2_).field_71075_bZ.field_75098_d) &&
      item.func_77984_f() &&
      item.func_96631_a(p_77972_1_, p_77972_2_.func_70681_au())) {
      p_77972_2_.func_70669_a(item);
      item.field_77994_a--;

      if (p_77972_2_ instanceof EntityPlayer) {
        EntityPlayer entityplayer = (EntityPlayer)p_77972_2_;
        entityplayer.func_71064_a(StatList.field_75930_F[Item.func_150891_b(item.func_77973_b())], 1);

        if (item.field_77994_a == 0)
          if (item.func_77973_b() instanceof net.minecraft.item.ItemBow) { entityplayer.func_71028_bD(); }
          else if (item.func_77973_b() instanceof net.minecraft.item.ItemArmor) { return true; }

      }
      if (item.field_77994_a < 0) item.field_77994_a = 0;
      item.func_77964_b(0);
    }


    return false;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\EntityDamageHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */