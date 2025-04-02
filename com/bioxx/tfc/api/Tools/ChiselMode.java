package com.bioxx.tfc.api.Tools;

import com.bioxx.tfc.Core.Player.PlayerInfo;
import com.bioxx.tfc.Core.Player.PlayerManagerTFC;
import com.bioxx.tfc.api.TFCBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;










public class ChiselMode
{
  private ResourceLocation resourcelocation;
  private int textureU;
  private int textureV;
  private int divX;
  private int divY;
  private int divZ;

  public ResourceLocation getResourceLocation() {
    return this.resourcelocation;
  }

  public int getTextureU() {
    return this.textureU;
  }

  public int getTextureV() {
    return this.textureV;
  }

  public int getDivX(Block block) {
    return this.divX;
  }

  public int getDivY(Block block) {
    return this.divY;
  }

  public int getDivZ(Block block) {
    return this.divZ;
  }


  public boolean onUsedHandler(World world, EntityPlayer player, int x, int y, int z, Block id, int meta, int side, float hitX, float hitY, float hitZ) {
    return false;
  }

  public void setDivision(int hitSide) {}

  public boolean isChiselable(Block block) {
    boolean isChiselable = (block == TFCBlocks.planks || block instanceof com.bioxx.tfc.Blocks.Terrain.BlockCobble || block instanceof com.bioxx.tfc.Blocks.Terrain.BlockStone || block.getClass().getName().toLowerCase().contains("blockbas") || block instanceof com.bioxx.tfc.Blocks.Terrain.BlockSmooth);

    return isChiselable;
  }

  public int hasChisel(EntityPlayer player) {
    int hasChisel = -1;
    if (player.field_71071_by.field_70462_a[player.field_71071_by.field_70461_c] != null && player.field_71071_by.field_70462_a[player.field_71071_by.field_70461_c].func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemChisel) {
      hasChisel = player.field_71071_by.field_70461_c;
    }
    return hasChisel;
  }

  public static PlayerInfo playerInfo(World world, EntityPlayer player) {
    PlayerInfo pi;
    if (!world.field_72995_K) {

      pi = PlayerManagerTFC.getInstance().getPlayerInfoFromPlayer(player);
    }
    else {

      pi = PlayerManagerTFC.getInstance().getClientPlayer();
    }
    return pi;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Tools\ChiselMode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */