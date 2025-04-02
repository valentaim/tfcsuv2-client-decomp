package com.bioxx.tfc.Tools;

import com.bioxx.tfc.Blocks.BlockSlab;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.TileEntities.TEPartial;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCOptions;
import com.bioxx.tfc.api.Tools.ChiselMode;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;





public class ChiselMode_Slab
  extends ChiselMode
{
  private static ResourceLocation resourcelocation = new ResourceLocation("terrafirmacraft", "textures/gui/icons.png");
  private static int textureU;
  private static int textureV;

  public ChiselMode_Slab(String n) {
    textureU = 40;
    textureV = 58;
  }

  private static int divX;

  public ResourceLocation getResourceLocation() {
    return resourcelocation;
  }

  private static int divY;

  public int getTextureU() {
    return textureU;
  }

  private static int divZ;

  public int getTextureV() {
    return textureV;
  }



  public int getDivX(Block block) {
    if (block == TFCBlocks.stoneSlabs || isChiselable(block)) {
      return divX;
    }

    return 0;
  }




  public int getDivY(Block block) {
    if (block == TFCBlocks.stoneSlabs || isChiselable(block)) {
      return divY;
    }

    return 0;
  }




  public int getDivZ(Block block) {
    if (block == TFCBlocks.stoneSlabs || isChiselable(block)) {
      return divZ;
    }

    return 0;
  }




  public void setDivision(int sideHit) {
    if (sideHit == 5 || sideHit == 4) {

      divY = divZ = 1;
      divX = 8;
    }
    else if (sideHit == 1 || sideHit == 0) {

      divX = divZ = 1;
      divY = 8;
    }
    else if (sideHit == 3 || sideHit == 2) {

      divY = divX = 1;
      divZ = 8;
    }
  }



  public boolean onUsedHandler(World world, EntityPlayer player, int x, int y, int z, Block id, int meta, int side, float hitX, float hitY, float hitZ) {
    if ((TFC_Core.isNaturalStone(id) && TFC_Core.isNaturalStone(world.func_147439_a(x, y + 1, z)) &&
      TFC_Core.isNaturalStone(world.func_147439_a(x, y + 2, z))) || id == TFCBlocks.stoneStairs) {
      return false;
    }

    int hasChisel = hasChisel(player);

    if (hasChisel >= 0) {
      Block slab = TFCBlocks.stoneSlabs;


      if (world.func_147439_a(x, y, z) != slab) {
        world.func_147465_d(x, y, z, slab, side, 2);
        te = (TEPartial)world.func_147438_o(x, y, z);
        te.typeID = (short)Block.func_149682_b(id);
        te.metaID = (byte)meta;
        te.setMaterial(world.func_147439_a(x, y, z).func_149688_o());
      } else {
        te = (TEPartial)world.func_147438_o(x, y, z);
        world.func_147444_c(x, y, z, slab);
      }

      if (TFCOptions.enableDebugMode) {
        TerraFirmaCraft.LOG.info(Integer.valueOf(side));
      }
      long extraX = te.extraData & 0xFL;
      long extraY = te.extraData >> 4L & 0xFL;
      long extraZ = te.extraData >> 8L & 0xFL;
      long extraX2 = te.extraData >> 12L & 0xFL;
      long extraY2 = te.extraData >> 16L & 0xFL;
      long extraZ2 = te.extraData >> 20L & 0xFL;

      if (side == 0) {
        long e = extraY + 1L;
        long new1 = extraY << 4L;
        long new2 = e << 4L;
        long old2 = new2 | te.extraData - new1;

        if (e + BlockSlab.getTopChiselLevel(te.extraData) >= 8L) {
          world.func_147468_f(x, y, z);
        } else {
          te.extraData = old2;
        }
      } else if (side == 1) {
        long e = extraY2 + 1L;
        long new1 = extraY2 << 16L;
        long new2 = e << 16L;
        long old2 = new2 | te.extraData - new1;

        if (e + BlockSlab.getBottomChiselLevel(te.extraData) >= 8L) {
          world.func_147468_f(x, y, z);
        } else {
          te.extraData = old2;
        }
      } else if (side == 2) {
        long e = extraZ + 1L;
        long new1 = extraZ << 8L;
        long new2 = e << 8L;
        long old2 = new2 | te.extraData - new1;

        if (e + BlockSlab.getSouthChiselLevel(te.extraData) >= 8L) {
          world.func_147468_f(x, y, z);
        } else {
          te.extraData = old2;
        }
      } else if (side == 3) {
        long e = extraZ2 + 1L;
        long new1 = extraZ2 << 20L;
        long new2 = e << 20L;
        long old2 = new2 | te.extraData - new1;

        if (e + BlockSlab.getNorthChiselLevel(te.extraData) >= 8L) {
          world.func_147468_f(x, y, z);
        } else {
          te.extraData = old2;
        }
      } else if (side == 4) {
        long e = extraX + 1L;
        long new1 = extraX;
        long new2 = e;
        long old2 = new2 | te.extraData - new1;

        if (e + BlockSlab.getEastChiselLevel(te.extraData) >= 8L) {
          world.func_147468_f(x, y, z);
        } else {
          te.extraData = old2;
        }
      } else if (side == 5) {
        long e = extraX2 + 1L;
        long new1 = extraX2 << 12L;
        long new2 = e << 12L;
        long old2 = new2 | te.extraData - new1;

        if (e + BlockSlab.getWestChiselLevel(te.extraData) >= 8L) {
          world.func_147468_f(x, y, z);
        } else {
          te.extraData = old2;
        }
      }

      if (TFCOptions.enableDebugMode) {
        TerraFirmaCraft.LOG.info("Extra =" + te.extraData);
      }

      TEPartial te = (TEPartial)world.func_147438_o(x, y, z);
      if (te != null) {
        world.func_147471_g(te.field_145851_c, te.field_145848_d, te.field_145849_e);
      }


      world.func_147459_d(x, y, z, world.func_147439_a(x, y, z));

      player.field_71071_by.field_70462_a[hasChisel].func_77972_a(1, (EntityLivingBase)player);
    }

    return true;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Tools\ChiselMode_Slab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */