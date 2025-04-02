package com.bioxx.tfc.Items;

import com.bioxx.tfc.Core.Player.FoodStatsTFC;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.api.TFCItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;





public class ItemAlcohol
  extends ItemTerra
{
  public ItemAlcohol() {
    setFolder("food/");
    func_77642_a(TFCItems.glassBottle);
    func_77625_d(16);
  }



  public EnumAction func_77661_b(ItemStack par1ItemStack) {
    return EnumAction.drink;
  }



  public int getItemStackLimit(ItemStack is) {
    return this.field_77777_bU;
  }




  public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
    par3EntityPlayer.func_71008_a(par1ItemStack, func_77626_a(par1ItemStack));
    return par1ItemStack;
  }





  @Deprecated
  public int func_77639_j() {
    return 4;
  }



  public int func_77626_a(ItemStack par1ItemStack) {
    return 32;
  }



  @SideOnly(Side.CLIENT)
  public void func_94581_a(IIconRegister registerer) {
    this.field_77791_bV = registerer.func_94245_a("terrafirmacraft:Glass Bottle Overlay");
  }



  @SideOnly(Side.CLIENT)
  public IIcon getIcon(ItemStack stack, int pass) {
    return (pass == 1) ? this.field_77791_bV : func_77668_q().getIcon(stack, pass);
  }



  @SideOnly(Side.CLIENT)
  public int func_82790_a(ItemStack is, int pass) {
    return (pass == 1) ? FluidContainerRegistry.getFluidForFilledItem(is).getFluid().getColor() : super.func_82790_a(is, pass);
  }



  @SideOnly(Side.CLIENT)
  public boolean func_77623_v() {
    return true;
  }




  public ItemStack func_77654_b(ItemStack is, World world, EntityPlayer player) {
    if (!player.field_71075_bZ.field_75098_d)
    {
      is.field_77994_a--;
    }

    if (!world.field_72995_K) {


      Random rand = new Random();
      FoodStatsTFC fs = TFC_Core.getPlayerFoodStats(player);
      fs.restoreWater(player, 800);
      int time = 400 + rand.nextInt(1000);
      fs.consumeAlcohol();
      if (rand.nextInt(100) == 0) {
        player.func_70690_d(new PotionEffect(8, time, 2));
      }
      if (rand.nextInt(100) == 0) {
        player.func_70690_d(new PotionEffect(5, time, 1));
      }
      if (rand.nextInt(100) == 0) {
        player.func_70690_d(new PotionEffect(8, time, 2));
      }
      if (rand.nextInt(200) == 0) {
        player.func_70690_d(new PotionEffect(10, time, 2));
      }
      if (rand.nextInt(150) == 0) {
        player.func_70690_d(new PotionEffect(12, time, 4));
      }
      if (rand.nextInt(180) == 0) {
        player.func_70690_d(new PotionEffect(13, time, 4));
      }
      int levelMod = 250 * player.field_71068_ca;
      if (fs.soberTime > TFC_Time.getTotalTicks() + 3000L + levelMod)
      {


        if (fs.soberTime > TFC_Time.getTotalTicks() + 5000L + levelMod) {
          if (rand.nextInt(4) == 0) {
            player.func_70690_d(new PotionEffect(18, time, 4));
          }
          if (fs.soberTime > TFC_Time.getTotalTicks() + 7000L + levelMod) {
            if (rand.nextInt(2) == 0) {
              player.func_70690_d(new PotionEffect(15, time, 4));
            }
            if (fs.soberTime > TFC_Time.getTotalTicks() + 8000L + levelMod &&
              rand.nextInt(10) == 0) {
              player.func_70690_d(new PotionEffect(20, time, 4));
            }

            if (fs.soberTime > TFC_Time.getTotalTicks() + 10000L + levelMod && !player.field_71075_bZ.field_75098_d) {
              fs.soberTime = 0L;

              player.func_70097_a((new DamageSource("alcohol")).func_76348_h().func_151518_m(), player.func_110138_aP());
            }
          }
        }
      }

      TFC_Core.setPlayerFoodStats(player, fs);
    }


    if (!player.field_71075_bZ.field_75098_d && !player.field_71071_by.func_70441_a(new ItemStack(TFCItems.glassBottle))) {


      if (is.field_77994_a == 0) {
        return new ItemStack(TFCItems.glassBottle);
      }

      player.func_71019_a(new ItemStack(TFCItems.glassBottle), false);
    }

    return is;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemAlcohol.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */