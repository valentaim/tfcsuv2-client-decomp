package com.bioxx.tfc.Items.Tools;

import com.bioxx.tfc.Core.Player.InventoryPlayerTFC;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Entities.EntityProjectileTFC;
import com.bioxx.tfc.Items.ItemQuiver;
import com.bioxx.tfc.Items.ItemTerra;
import com.bioxx.tfc.api.Enums.EnumAmmo;
import com.bioxx.tfc.api.Enums.EnumItemReach;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import com.bioxx.tfc.api.Interfaces.ISize;
import com.bioxx.tfc.api.TFCItems;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class ItemCustomBow
  extends ItemBow implements ISize {
  private String[] bowPullIconNameArray = new String[] { "pulling_0", "pulling_1", "pulling_2", "pulling_3" };
  
  private IIcon[] iconArray;

  
  public ItemCustomBow() {
    this.field_77777_bU = 1;
    func_77656_e(384);
    func_77637_a(TFCTabs.TFC_WEAPONS);
    setNoRepair();
  }

  
  public boolean consumeArrowInQuiver(EntityPlayer player, boolean shouldConsume) {
    ItemStack quiver = ((InventoryPlayerTFC)player.field_71071_by).extraEquipInventory[0];









    
    if (quiver != null && quiver.func_77973_b() instanceof ItemQuiver && (
      (ItemQuiver)quiver.func_77973_b()).consumeAmmo(quiver, EnumAmmo.ARROW, shouldConsume) != null) {
      return true;
    }
    return false;
  }


  
  public ItemStack func_77659_a(ItemStack is, World world, EntityPlayer player) {
    ArrowNockEvent event = new ArrowNockEvent(player, is);
    MinecraftForge.EVENT_BUS.post((Event)event);
    if (event.isCanceled()) {
      return event.result;
    }
    if (player.field_71075_bZ.field_75098_d || player.field_71071_by.func_146028_b(TFCItems.arrow) || consumeArrowInQuiver(player, false)) {
      player.func_71008_a(is, func_77626_a(is));
    }
    return is;
  }


  
  public void func_77615_a(ItemStack is, World world, EntityPlayer player, int inUseCount) {
    int j = func_77626_a(is) - inUseCount;
    
    ArrowLooseEvent event = new ArrowLooseEvent(player, is, j);
    MinecraftForge.EVENT_BUS.post((Event)event);
    if (event.isCanceled())
      return; 
    j = event.charge;
    
    boolean flag = (player.field_71075_bZ.field_75098_d || EnchantmentHelper.func_77506_a(Enchantment.field_77342_w.field_77352_x, is) > 0);

    
    boolean hasAmmo = (flag || player.field_71071_by.func_146028_b(TFCItems.arrow));
    boolean hasAmmoInQuiver = false;
    
    if (!hasAmmo) {
      hasAmmoInQuiver = consumeArrowInQuiver(player, false);
    }
    if (hasAmmo || hasAmmoInQuiver) {
      
      float forceMult = j / getUseSpeed(player);

      
      if (forceMult < 0.25D) {
        return;
      }
      if (forceMult > 1.25F) {
        forceMult = 1.25F;
      }
      EntityProjectileTFC entityarrow = new EntityProjectileTFC(world, (EntityLivingBase)player, forceMult * 2.0F);
      entityarrow.func_70239_b(forceMult * 150.0D);
      if (forceMult == 1.25F) {
        entityarrow.func_70243_d(true);
      }
      int k = EnchantmentHelper.func_77506_a(Enchantment.field_77345_t.field_77352_x, is);
      
      if (k > 0) {
        entityarrow.func_70239_b(entityarrow.func_70242_d() + k * 0.5D + 0.5D);
      }
      int l = EnchantmentHelper.func_77506_a(Enchantment.field_77344_u.field_77352_x, is);
      
      if (l > 0) {
        entityarrow.func_70240_a(l);
      }
      if (EnchantmentHelper.func_77506_a(Enchantment.field_77343_v.field_77352_x, is) > 0) {
        entityarrow.func_70015_d(100);
      }
      is.func_77972_a(1, (EntityLivingBase)player);
      world.func_72956_a((Entity)player, "random.bow", 1.0F, 1.0F / (field_77697_d.nextFloat() * 0.4F + 1.2F) + forceMult * 0.5F);
      
      if (flag) {
        entityarrow.field_70251_a = 2;
      } else if (hasAmmo) {
        player.field_71071_by.func_146026_a(TFCItems.arrow);
      } else if (hasAmmoInQuiver) {
        consumeArrowInQuiver(player, true);
      } 
      if (!world.field_72995_K) {
        world.func_72838_d((Entity)entityarrow);
      }
    } 
  }
  
  public static float getUseSpeed(EntityPlayer player) {
    float speed = 60.0F;
    ItemStack[] armor = player.field_71071_by.field_70460_b;
    if (armor[0] != null && armor[0].func_77973_b() instanceof ISize)
      speed += 20.0F / (((ISize)armor[0].func_77973_b()).getWeight(armor[0])).multiplier; 
    if (armor[1] != null && armor[1].func_77973_b() instanceof ISize)
      speed += 20.0F / (((ISize)armor[1].func_77973_b()).getWeight(armor[1])).multiplier; 
    if (armor[2] != null && armor[2].func_77973_b() instanceof ISize)
      speed += 20.0F / (((ISize)armor[2].func_77973_b()).getWeight(armor[2])).multiplier; 
    if (armor[3] != null && armor[3].func_77973_b() instanceof ISize) {
      speed += 20.0F / (((ISize)armor[3].func_77973_b()).getWeight(armor[3])).multiplier;
    }
    return speed;
  }


  
  public void func_77624_a(ItemStack is, EntityPlayer player, List arraylist, boolean flag) {
    ItemTerra.addSizeInformation(is, arraylist);
  }


  
  public EnumSize getSize(ItemStack is) {
    return EnumSize.LARGE;
  }


  
  public EnumWeight getWeight(ItemStack is) {
    return EnumWeight.LIGHT;
  }


  
  public boolean canStack() {
    return false;
  }


  
  @SideOnly(Side.CLIENT)
  public void func_94581_a(IIconRegister par1IconRegister) {
    this.field_77791_bV = par1IconRegister.func_94245_a("terrafirmacraft:" + func_111208_A() + "_standby");
    this.iconArray = new IIcon[this.bowPullIconNameArray.length];
    
    for (int i = 0; i < this.iconArray.length; i++) {
      this.iconArray[i] = par1IconRegister.func_94245_a("terrafirmacraft:" + func_111208_A() + "_" + this.bowPullIconNameArray[i]);
    }
  }


  
  @SideOnly(Side.CLIENT)
  public IIcon func_94599_c(int par1) {
    return this.iconArray[par1];
  }


  
  public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
    if (usingItem != null && usingItem.func_77973_b() == this) {
      
      int j = usingItem.func_77988_m() - useRemaining;
      float force = j / getUseSpeed(player);
      
      if (force >= 1.25D)
      {
        return func_94599_c(3);
      }
      if (force > 0.75D)
      {
        return func_94599_c(2);
      }
      if (force > 0.25D)
      {
        return func_94599_c(1);
      }
      if (force > 0.0F)
      {
        return func_94599_c(0);
      }
    } 
    return getIcon(stack, renderPass);
  }

  
  public EnumItemReach getReach(ItemStack is) {
    return EnumItemReach.SHORT;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Tools\ItemCustomBow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */