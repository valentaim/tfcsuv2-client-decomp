package com.bioxx.tfc.Items.Tools;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Entities.Mobs.EntityCowTFC;
import com.bioxx.tfc.Items.ItemTerra;
import com.bioxx.tfc.api.Entities.IAnimal;
import com.bioxx.tfc.api.Enums.EnumItemReach;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.FillBucketEvent;





public class ItemCustomBucket
  extends ItemTerra
{
  private Block bucketContents;

  public ItemCustomBucket(Block contents) {
    this.bucketContents = contents;
    setFolder("tools/");
    setSize(EnumSize.MEDIUM);
  }


  public ItemCustomBucket(Block contents, Item container) {
    this(contents);
    func_77642_a(container);
  }



  public boolean canStack() {
    return false;
  }






  public ItemStack func_77659_a(ItemStack is, World world, EntityPlayer player) {
    boolean isEmpty = (this.bucketContents == Blocks.field_150350_a);
    MovingObjectPosition mop = func_77621_a(world, player, isEmpty);

    if (mop == null)
    {
      return is;
    }


    if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {

      int x = mop.field_72311_b;
      int y = mop.field_72312_c;
      int z = mop.field_72309_d;

      if (!world.func_72962_a(player, x, y, z)) {
        return is;
      }
      if (isEmpty) {

        if (!player.func_82247_a(x, y, z, mop.field_72310_e, is)) {
          return is;
        }
        FillBucketEvent event = new FillBucketEvent(player, is, world, mop);
        if (MinecraftForge.EVENT_BUS.post((Event)event) || event.isCanceled()) {
          return is;
        }
        if (event.getResult() == Event.Result.ALLOW) {
          return event.result;
        }
        if (TFC_Core.isFreshWater(world.func_147439_a(x, y, z))) {

          world.func_147468_f(x, y, z);
          if (player.field_71075_bZ.field_75098_d)
            return is;
          return new ItemStack(TFCItems.woodenBucketWater);
        }
        if (TFC_Core.isSaltWater(world.func_147439_a(x, y, z))) {

          world.func_147468_f(x, y, z);
          if (player.field_71075_bZ.field_75098_d)
            return is;
          return new ItemStack(TFCItems.woodenBucketSaltWater);
        }


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

        if (TFC_Core.isFreshWater(world.func_147439_a(flowX, flowY, flowZ))) {

          world.func_147468_f(flowX, flowY, flowZ);
          if (player.field_71075_bZ.field_75098_d)
            return is;
          return new ItemStack(TFCItems.woodenBucketWater);
        }
        if (TFC_Core.isSaltWater(world.func_147439_a(flowX, flowY, flowZ)))
        {
          world.func_147468_f(flowX, flowY, flowZ);
          if (player.field_71075_bZ.field_75098_d)
            return is;
          return new ItemStack(TFCItems.woodenBucketSaltWater);
        }

      } else {

        return new ItemStack(TFCItems.woodenBucketEmpty);
      }

    } else if (this.bucketContents == Blocks.field_150350_a && mop.field_72308_g instanceof EntityCowTFC && ((EntityCowTFC)mop.field_72308_g).getGender() == IAnimal.GenderEnum.FEMALE) {

      return new ItemStack(TFCItems.woodenBucketMilk);
    }
    return is;
  }




  public boolean func_77648_a(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    boolean isEmpty = (this.bucketContents == Blocks.field_150350_a);
    int[][] map = { { 0, -1, 0 }, { 0, 1, 0 }, { 0, 0, -1 }, { 0, 0, 1 }, { -1, 0, 0 }, { 1, 0, 0 } };

    if (!isEmpty && world.func_147439_a(x, y, z) != Blocks.field_150383_bp && world.func_147437_c(x + map[side][0], y + map[side][1], z + map[side][2])) {

      world.func_147465_d(x + map[side][0], y + map[side][1], z + map[side][2], TFCBlocks.freshWater, 2, 1);
      player.func_70062_b(0, new ItemStack(TFCItems.woodenBucketEmpty));
      player.field_71071_by.func_70441_a(new ItemStack(TFCItems.woodenBucketEmpty));
      return true;
    }

    if (!isEmpty && world.func_147439_a(x, y, z) == Blocks.field_150383_bp) {

      int meta = world.func_72805_g(x, y, z);
      if (meta < 3) {

        if (!player.field_71075_bZ.field_75098_d)
        {
          player.func_70062_b(0, new ItemStack(TFCItems.woodenBucketEmpty));
        }
        world.func_72921_c(x, y, z, MathHelper.func_76125_a(3, 0, 3), 2);
        world.func_147453_f(x, y, z, (Block)Blocks.field_150383_bp);

        return true;
      }
    }

    return false;
  }



  public EnumItemReach getReach(ItemStack is) {
    return EnumItemReach.SHORT;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Tools\ItemCustomBucket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */