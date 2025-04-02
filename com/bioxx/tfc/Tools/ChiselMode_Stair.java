package com.bioxx.tfc.Tools;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.TileEntities.TEPartial;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.Tools.ChiselMode;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;





public class ChiselMode_Stair
  extends ChiselMode
{
  private static ResourceLocation resourcelocation = new ResourceLocation("terrafirmacraft", "textures/gui/icons.png");

  private static int textureU;

  public ChiselMode_Stair(String n) {
    textureU = 20;
    textureV = 58;
    div = 2;
  }
  private static int textureV;
  private static int div;

  public ResourceLocation getResourceLocation() {
    return resourcelocation;
  }



  public int getTextureU() {
    return textureU;
  }



  public int getTextureV() {
    return textureV;
  }



  public int getDivX(Block block) {
    if (block == TFCBlocks.stoneStairs || isChiselable(block)) {
      return div;
    }

    return 0;
  }




  public int getDivY(Block block) {
    if (block == TFCBlocks.stoneStairs || isChiselable(block)) {
      return div;
    }

    return 0;
  }




  public int getDivZ(Block block) {
    if (block == TFCBlocks.stoneStairs || isChiselable(block)) {
      return div;
    }

    return 0;
  }




  public boolean onUsedHandler(World world, EntityPlayer player, int x, int y, int z, Block id, int meta, int side, float hitX, float hitY, float hitZ) {
    if ((TFC_Core.isNaturalStone(id) && TFC_Core.isNaturalStone(world.func_147439_a(x, y + 1, z)) &&
      TFC_Core.isNaturalStone(world.func_147439_a(x, y + 2, z))) || id == TFCBlocks.stoneSlabs) {
      return false;
    }

    int hasChisel = hasChisel(player);

    if (hasChisel >= 0) {
      int hit = 0;
      TEPartial te = null;
      if (id != TFCBlocks.stoneStairs) {

        world.func_147465_d(x, y, z, TFCBlocks.stoneStairs, 0, 3);
        te = (TEPartial)world.func_147438_o(x, y, z);
        te.typeID = (short)Block.func_149682_b(id);
        te.metaID = (byte)meta;
        te.extraData = hit;
        te.setMaterial(world.func_147439_a(x, y, z).func_149688_o());
        te.func_145829_t();
      }
      else {

        te = (TEPartial)world.func_147438_o(x, y, z);
        world.func_147444_c(x, y, z, id);
      }
      if (hitY > 0.5F) {

        if (hitX <= 0.5F && hitZ >= 0.5F && (te.extraData & 0x1L) == 0L)
          hit = 1;
        if (hitX >= 0.5F && hitZ <= 0.5F && (te.extraData & 0x2L) == 0L)
          hit = 2;
        if (hitX <= 0.5F && hitZ <= 0.5F && (te.extraData & 0x4L) == 0L)
          hit = 4;
        if (hitX >= 0.5F && hitZ >= 0.5F && (te.extraData & 0x8L) == 0L) {
          hit = 8;
        }
      } else {

        if (hitX <= 0.5F && hitZ >= 0.5F && (te.extraData & 0x10L) == 0L)
          hit = 16;
        if (hitX >= 0.5F && hitZ <= 0.5F && (te.extraData & 0x20L) == 0L)
          hit = 32;
        if (hitX <= 0.5F && hitZ <= 0.5F && (te.extraData & 0x40L) == 0L)
          hit = 64;
        if (hitX >= 0.5F && hitZ >= 0.5F && (te.extraData & 0x80L) == 0L) {
          hit = 128;
        }
      }
      te.extraData |= hit;
      if (te.extraData == 255L) {
        world.func_147449_b(x, y, z, Blocks.field_150350_a);
      } else {
        te.broadcastPacketInRange();
      }
      player.field_71071_by.field_70462_a[hasChisel].func_77972_a(1, (EntityLivingBase)player);
    }

    return true;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Tools\ChiselMode_Stair.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */