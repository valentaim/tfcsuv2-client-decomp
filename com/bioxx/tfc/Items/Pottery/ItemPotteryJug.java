package com.bioxx.tfc.Items.Pottery;

import com.bioxx.tfc.Core.Player.FoodStatsTFC;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;






public class ItemPotteryJug
  extends ItemPotteryBase
{
  private IIcon waterIcon;

  public ItemPotteryJug() {
    this.metaNames = new String[] { "Clay Jug", "Ceramic Jug", "Water Jug" };
    this.stackable = false;

    setWeight(EnumWeight.LIGHT);
    setSize(EnumSize.SMALL);
  }



  public ItemStack func_77654_b(ItemStack is, World world, EntityPlayer player) {
    if (!world.field_72995_K) {

      if (is.func_77960_j() == 2)
      {
        TFC_Core.getPlayerFoodStats(player).restoreWater(player, 24000);
      }

      if (is.func_77960_j() > 1 && !player.field_71075_bZ.field_75098_d)
      {
        if (world.field_73012_v.nextInt(50) == 0) {

          world.func_72956_a((Entity)player, "terrafirmacraft:item.ceramicbreak", 0.7F, player.field_70170_p.field_73012_v.nextFloat() * 0.2F + 0.8F);
          is.field_77994_a--;
        }
        else {

          is.func_77964_b(1);
        }
      }
    }
    return is;
  }






  public int func_77626_a(ItemStack is) {
    return 32;
  }






  public EnumAction func_77661_b(ItemStack is) {
    return EnumAction.drink;
  }






  public ItemStack func_77659_a(ItemStack is, World world, EntityPlayer entity) {
    MovingObjectPosition mop = func_77621_a(world, entity, true);
    FoodStatsTFC fs = TFC_Core.getPlayerFoodStats(entity);
    Boolean canDrink = Boolean.valueOf(((fs.getMaxWater(entity) - 500) > fs.waterLevel));

    boolean playNote = false;
    float lookAngle = 0.0F;
    if (mop == null) {

      if (is.func_77960_j() > 1 && canDrink.booleanValue()) {

        entity.func_71008_a(is, func_77626_a(is));
      }
      else if (is.func_77960_j() == 1) {
        Vec3 lookVec = entity.func_70040_Z();
        lookAngle = (float)(lookVec.field_72448_b / 2.0D);
        if (!is.func_77942_o()) {
          playNote = true;
          is.field_77990_d = new NBTTagCompound();
          is.field_77990_d.func_74772_a("blowTime", TFC_Time.getTotalTicks());
        }
        else if (is.field_77990_d.func_74764_b("blowTime") && is.field_77990_d
          .func_74763_f("blowTime") + 10L < TFC_Time.getTotalTicks()) {

          playNote = true;
          is.field_77990_d.func_74772_a("blowTime", TFC_Time.getTotalTicks());
        }
        else if (!is.field_77990_d.func_74764_b("blowTime")) {
          playNote = true;
          is.field_77990_d.func_74772_a("blowTime", TFC_Time.getTotalTicks());
        }

      }

    }
    else if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {

      int x = mop.field_72311_b;
      int y = mop.field_72312_c;
      int z = mop.field_72309_d;


      int flowX = x;
      int flowY = y;
      int flowZ = z;
      switch (mop.field_72310_e) {

        case 0:
          flowY = y - 1;
          break;
        case 1:
          flowY = y + 1;
          break;
        case 2:
          flowZ = z - 1;
          break;
        case 3:
          flowZ = z + 1;
          break;
        case 4:
          flowX = x - 1;
          break;
        case 5:
          flowX = x + 1;
          break;
      }

      if ((!entity.func_70093_af() && !world.field_72995_K &&
        TFC_Core.isFreshWater(world.func_147439_a(x, y, z))) || TFC_Core.isFreshWater(world.func_147439_a(flowX, flowY, flowZ))) {

        if (is.func_77960_j() == 1)
        {
          is.func_77964_b(2);
          playNote = false;


        }
        else if (canDrink.booleanValue())
        {
          entity.func_71008_a(is, func_77626_a(is));

        }


      }
      else if (is.func_77960_j() == 2 && canDrink.booleanValue()) {

        entity.func_71008_a(is, func_77626_a(is));
      }
      else if (is.func_77960_j() == 1) {
        Vec3 lookVec = entity.func_70040_Z();
        lookAngle = (float)(lookVec.field_72448_b / 2.0D);
        if (!is.func_77942_o()) {
          is.field_77990_d = new NBTTagCompound();
          is.field_77990_d.func_74772_a("blowTime", TFC_Time.getTotalTicks());
        }
        else if (is.field_77990_d.func_74764_b("blowTime") && is.field_77990_d
          .func_74763_f("blowTime") + 10L < TFC_Time.getTotalTicks()) {

          is.field_77990_d.func_74772_a("blowTime", TFC_Time.getTotalTicks());
        }
        else if (!is.field_77990_d.func_74764_b("blowTime")) {
          playNote = true;
          is.field_77990_d.func_74772_a("blowTime", TFC_Time.getTotalTicks());
        }
      }


      if (!world.func_72962_a(entity, x, y, z))
      {
        return is;
      }

      if (!entity.func_82247_a(x, y, z, mop.field_72310_e, is))
      {
        return is;
      }
    }

    if (playNote) {
      entity.func_85030_a("terrafirmacraft:item.jug.blow", 1.0F, 1.0F + lookAngle);
    }
    return is;
  }



  public IIcon func_77617_a(int damage) {
    if (damage == 0)
      return this.clayIcon;
    if (damage == 1)
      return this.ceramicIcon;
    if (damage == 2) {
      return this.waterIcon;
    }

    return this.waterIcon;
  }



  public void func_94581_a(IIconRegister registerer) {
    super.func_94581_a(registerer);
    this.waterIcon = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + "Water Jug");
  }



  public void func_77624_a(ItemStack is, EntityPlayer player, List<String> arraylist, boolean flag) {
    super.func_77624_a(is, player, arraylist, flag);
    if (is.func_77942_o() && is.field_77990_d.func_74764_b("LiquidType"))
    {
      arraylist.add(is.field_77990_d.func_74779_i("LiquidType"));
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Pottery\ItemPotteryJug.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */