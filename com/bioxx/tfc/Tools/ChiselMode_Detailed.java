package com.bioxx.tfc.Tools;

import com.bioxx.tfc.Core.Player.PlayerInfo;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.TileEntities.TEDetailed;
import com.bioxx.tfc.TileEntities.TEPartial;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.Tools.ChiselMode;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;






public class ChiselMode_Detailed
  extends ChiselMode
{
  private static ResourceLocation resourcelocation = new ResourceLocation("terrafirmacraft", "textures/gui/icons.png");
  private static int textureU;
  private static Random random = new Random();
  private static int textureV;

  public ChiselMode_Detailed(String n) {
    textureU = 60;
    textureV = 58;
    div = 8;
  }

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
    return div;
  }



  public int getDivY(Block block) {
    return div;
  }



  public int getDivZ(Block block) {
    return div;
  }



  public boolean onUsedHandler(World world, EntityPlayer player, int x, int y, int z, Block id, int meta, int side, float hitX, float hitY, float hitZ) {
    if ((TFC_Core.isNaturalStone(id) && TFC_Core.isNaturalStone(world.func_147439_a(x, y + 1, z)) &&
      TFC_Core.isNaturalStone(world.func_147439_a(x, y + 2, z))) || id == TFCBlocks.stoneStairs) {
      return false;
    }

    int hasChisel = hasChisel(player);
    PlayerInfo pi = playerInfo(world, player);


    if (hasChisel >= 0 && pi.lockMatches(x, y, z)) {


      if (id == TFCBlocks.stoneSlabs) {
        TEPartial tep = (TEPartial)world.func_147438_o(x, y, z);
        int extraX = (int)(tep.extraData & 0xFL);
        int extraY = (int)(tep.extraData >> 4L & 0xFL);
        int extraZ = (int)(tep.extraData >> 8L & 0xFL);
        int extraX2 = 8 - (int)(tep.extraData >> 12L & 0xFL);
        int extraY2 = 8 - (int)(tep.extraData >> 16L & 0xFL);
        int extraZ2 = 8 - (int)(tep.extraData >> 20L & 0xFL);
        world.func_147449_b(x, y, z, TFCBlocks.detailed);
        TEDetailed tEDetailed = (TEDetailed)world.func_147438_o(x, y, z);
        tEDetailed.typeID = tep.typeID;
        tEDetailed.metaID = tep.metaID;

        for (int i = 0; i < 8; i++) {
          for (int subZ = 0; subZ < 8; subZ++) {
            for (int subY = 0; subY < 8; subY++) {
              if (i >= extraX && i < extraX2 && subY >= extraY && subY < extraY2 && subZ >= extraZ && subZ < extraZ2) {
                tEDetailed.setBlock(i, subY, subZ);
                tEDetailed.setQuad(i, subY, subZ);
              }
            }
          }
        }
        return true;
      }

      world.func_147449_b(x, y, z, TFCBlocks.detailed);

      TEDetailed te = (TEDetailed)world.func_147438_o(x, y, z);
      te.typeID = (short)Block.func_149682_b(id);
      te.metaID = (byte)meta;

      for (int subX = 0; subX < 8; subX++) {
        for (int subZ = 0; subZ < 8; subZ++) {
          for (int subY = 0; subY < 8; subY++) {
            te.setBlock(subX, subY, subZ);
            te.setQuad(subX, subY, subZ);
          }
        }
      }


      world.func_147459_d(x, y, z, world.func_147439_a(x, y, z));

      if (random.nextInt(4) == 0)
      {
        player.field_71071_by.field_70462_a[hasChisel].func_77972_a(1, (EntityLivingBase)player);
      }
    }

    return true;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Tools\ChiselMode_Detailed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */