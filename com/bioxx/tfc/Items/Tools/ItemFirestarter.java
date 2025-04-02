package com.bioxx.tfc.Items.Tools;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Items.ItemTerra;
import com.bioxx.tfc.TileEntities.TEPottery;
import com.bioxx.tfc.api.Enums.EnumItemReach;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import fof.tfcsu.utils.ExpBonus;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;





public class ItemFirestarter
  extends ItemTerra
{
  private boolean canBeUsed;
  private boolean isCoal;
  private boolean isPottery;

  public ItemFirestarter() {
    func_77656_e(8);
    this.field_77787_bX = false;
    func_77637_a(TFCTabs.TFC_TOOLS);
  }



  public EnumSize getSize(ItemStack is) {
    return EnumSize.SMALL;
  }


  public int getPlacedBlockMetadata(int i) {
    return i;
  }



  public boolean canStack() {
    return false;
  }



  public EnumAction func_77661_b(ItemStack is) {
    return EnumAction.bow;
  }



  public int func_77626_a(ItemStack is) {
    return 20;
  }



  public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
    setFlags(player);
    MovingObjectPosition mop = func_77621_a(player.field_70170_p, player, true);
    if (mop != null && mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {

      World world = player.field_70170_p;
      int x = mop.field_72311_b;
      int y = mop.field_72312_c;
      int z = mop.field_72309_d;
      double hitX = mop.field_72307_f.field_72450_a;
      double hitY = mop.field_72307_f.field_72448_b;
      double hitZ = mop.field_72307_f.field_72449_c;
      int chance = world.field_73012_v.nextInt(100);

      if (world.func_147439_a(x, y + 1, z) == TFCBlocks.firepit) {
        player.func_71034_by();
      }
      if (count > 0 && world.field_72995_K) {

        Boolean genSmoke = Boolean.valueOf((this.canBeUsed || this.isCoal || this.isPottery));

        if (genSmoke.booleanValue() && chance > 70) {
          world.func_72869_a("smoke", hitX, hitY, hitZ, 0.0D, 0.10000000149011612D, 0.0D);
        }
        if (count < 4 && chance > 70) {
          world.func_72869_a("flame", hitX, hitY, hitZ, 0.0D, 0.0D, 0.0D);
        }
        if (count < func_77626_a((ItemStack)null) - 4 && count % 3 == 1) {
          player.func_85030_a("terrafirmacraft:item.firestarter", 0.5F, 0.05F);
        }
      } else if (!world.field_72995_K && count == 1) {

        if (this.canBeUsed) {

          List list = world.func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a(x, (y + 1), z, (x + 1), (y + 2), (z + 1)));
          int numsticks = 0;
          int hasStraw = 0;

          if (list != null && !list.isEmpty()) {

            for (Iterator<EntityItem> iterator = list.iterator(); iterator.hasNext(); ) {

              EntityItem entity = iterator.next();
              if (entity.func_92059_d().func_77973_b() == TFCItems.straw) {
                hasStraw = 40; continue;
              }  if (entity.func_92059_d().func_77973_b() == TFCItems.stick) {
                numsticks += (entity.func_92059_d()).field_77994_a;
              }
            }
            if (chance > 70 - hasStraw && numsticks >= 3) {

              for (Iterator<EntityItem> iterator1 = list.iterator(); iterator1.hasNext(); ) {

                EntityItem entity = iterator1.next();
                if (entity.func_92059_d().func_77973_b() == TFCItems.stick || entity.func_92059_d().func_77973_b() == TFCItems.straw)
                  entity.func_70106_y();
              }
              world.func_147465_d(x, y + 1, z, TFCBlocks.firepit, 1, 2);
              ExpBonus.SET_FIRE_TO.give(player);
            }
          }

          stack.func_77972_a(1, (EntityLivingBase)player);
          if (stack.func_77960_j() >= stack.func_77958_k()) {
            stack.field_77994_a = 0;
          }
        } else if (this.isCoal) {

          if (chance > 70) {
            world.func_147465_d(x, y, z, TFCBlocks.forge, 1, 2);
            ExpBonus.SET_FIRE_TO.give(player);
          }
          stack.func_77972_a(1, (EntityLivingBase)player);
        }
        else if (this.isPottery) {

          if (chance > 70) {

            TEPottery te = (TEPottery)world.func_147438_o(x, y, z);
            te.startPitFire();
            ExpBonus.SET_FIRE_TO.give(player);
          }
          stack.func_77972_a(1, (EntityLivingBase)player);
        }
      }
    }
  }



  public ItemStack func_77659_a(ItemStack is, World world, EntityPlayer player) {
    if (this.canBeUsed || this.isCoal || this.isPottery)
      player.func_71008_a(is, func_77626_a(is));
    return is;
  }



  public boolean onItemUseFirst(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    setFlags(player);
    return false;
  }



  public EnumItemReach getReach(ItemStack is) {
    return EnumItemReach.SHORT;
  }


  public void setFlags(EntityPlayer player) {
    MovingObjectPosition mop = func_77621_a(player.field_70170_p, player, true);
    if (mop != null && mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {

      World world = player.field_70170_p;
      int x = mop.field_72311_b;
      int y = mop.field_72312_c;
      int z = mop.field_72309_d;
      int side = mop.field_72310_e;

      Block block = world.func_147439_a(x, y, z);
      boolean surroundSolids = TFC_Core.isSurroundedSolid(world, x, y, z);
      boolean surroundRock = TFC_Core.isSurroundedStone(world, x, y, z);
      this



        .canBeUsed = (side == 1 && TFC_Core.isTopFaceSolid(world, x, y, z) && block.func_149688_o() != Material.field_151575_d && block.func_149688_o() != Material.field_151580_n && world.func_147437_c(x, y + 1, z) && block != TFCBlocks.charcoal && block != Blocks.field_150402_ci && block != TFCBlocks.pottery);



      this.isCoal = (((block == TFCBlocks.charcoal && world.func_72805_g(x, y, z) > 6) || block == Blocks.field_150402_ci) && surroundRock && surroundSolids);
      this.isPottery = (block == TFCBlocks.pottery && surroundSolids);
      if (this.isPottery) {

        TEPottery te = (TEPottery)world.func_147438_o(x, y, z);
        this.isPottery = (!te.isLit() && te.wood == 8);
      }
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Tools\ItemFirestarter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */