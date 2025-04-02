package com.bioxx.tfc.Tools;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.Tools.ChiselMode;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;





public class ChiselMode_Smooth
  extends ChiselMode
{
  private static ResourceLocation resourcelocation = new ResourceLocation("terrafirmacraft", "textures/gui/icons.png");

  private static int textureU;

  public ChiselMode_Smooth(String n) {
    textureU = 0;
    textureV = 58;
    div = 1;
  }
  private static int textureV; private static int div;

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
    if (block instanceof com.bioxx.tfc.Blocks.Terrain.BlockStone) {
      return div;
    }

    return 0;
  }



  public int getDivY(Block block) {
    if (block instanceof com.bioxx.tfc.Blocks.Terrain.BlockStone) {
      return div;
    }

    return 0;
  }



  public int getDivZ(Block block) {
    if (block instanceof com.bioxx.tfc.Blocks.Terrain.BlockStone) {
      return div;
    }

    return 0;
  }




  public boolean onUsedHandler(World world, EntityPlayer player, int x, int y, int z, Block id, int meta, int side, float hitX, float hitY, float hitZ) {
    if (TFC_Core.isNaturalStone(world.func_147439_a(x, y + 1, z)) && TFC_Core.isNaturalStone(world.func_147439_a(x, y + 2, z))) {
      return false;
    }

    if (TFC_Core.isRawStone(id)) {

      int hasChisel = hasChisel(player);
      if (hasChisel >= 0) {
        if (id == TFCBlocks.stoneIgIn) {
          world.func_147465_d(x, y, z, TFCBlocks.stoneIgInSmooth, meta, 2);
        } else if (id == TFCBlocks.stoneIgEx) {
          world.func_147465_d(x, y, z, TFCBlocks.stoneIgExSmooth, meta, 2);
        } else if (id == TFCBlocks.stoneSed) {
          world.func_147465_d(x, y, z, TFCBlocks.stoneSedSmooth, meta, 2);
        } else if (id == TFCBlocks.stoneMM) {
          world.func_147465_d(x, y, z, TFCBlocks.stoneMMSmooth, meta, 2);
        }

        player.field_71071_by.field_70462_a[hasChisel].func_77972_a(1, (EntityLivingBase)player);
        return true;
      }
    }

    return false;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Tools\ChiselMode_Smooth.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */