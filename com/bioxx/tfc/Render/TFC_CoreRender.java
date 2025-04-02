package com.bioxx.tfc.Render;

import com.bioxx.tfc.Blocks.Devices.BlockSluice;
import com.bioxx.tfc.Blocks.Flora.BlockFruitLeaves;
import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.Food.FloraIndex;
import com.bioxx.tfc.Food.FloraManager;
import com.bioxx.tfc.Render.Blocks.RenderFlora;
import com.bioxx.tfc.TileEntities.TEFruitTreeWood;
import com.bioxx.tfc.TileEntities.TEPartial;
import com.bioxx.tfc.TileEntities.TEWaterPlant;
import com.bioxx.tfc.WorldGen.DataLayer;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCOptions;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TFC_CoreRender
{
  private static RenderBlocksLightCache renderer;

  public static boolean renderBlockSlab(Block block, int x, int y, int z, RenderBlocks renderblocks) {
    TEPartial te = (TEPartial)renderblocks.field_147845_a.func_147438_o(x, y, z);



    if (te.typeID <= 0) {
      return false;
    }
    int type = te.typeID;
    int meta = te.metaID;
    Block b = Block.func_149729_e(type);
    IIcon tex = b.func_149691_a(0, meta);




    long extraX = te.extraData & 0xFL;
    long extraY = te.extraData >> 4L & 0xFL;
    long extraZ = te.extraData >> 8L & 0xFL;
    long extraX2 = te.extraData >> 12L & 0xFL;
    long extraY2 = te.extraData >> 16L & 0xFL;
    long extraZ2 = te.extraData >> 20L & 0xFL;

    float div = 0.125F;

    renderblocks.func_147782_a((0.0F + div * (float)extraX), (0.0F + div * (float)extraY), (0.0F + div * (float)extraZ), (1.0F - div * (float)extraX2), (1.0F - div * (float)extraY2), (1.0F - div * (float)extraZ2));


    boolean breaking = (renderblocks.field_147840_d != null);
    IIcon over = renderblocks.field_147840_d;
    if (!breaking && (b == TFCBlocks.ore || b == TFCBlocks.ore2 || b == TFCBlocks.ore3)) {


      renderblocks.field_147840_d = getRockTexture((World)(Minecraft.func_71410_x()).field_71441_e, x, y, z);
      renderblocks.func_147784_q(block, x, y, z);
      renderblocks.field_147840_d = over;
    }

    if (!breaking) {
      renderblocks.field_147840_d = tex;
    }
    renderblocks.func_147784_q(block, x, y, z);
    renderblocks.field_147840_d = over;

    return true;
  }



  public static boolean renderBlockStairs(Block block, int x, int y, int z, RenderBlocks renderblocks) {
    if (renderer == null) {
      renderer = new RenderBlocksLightCache(renderblocks);
    } else {
      renderer.update(renderblocks);
    }

    renderer.disableRender();
    renderer.func_147753_b(true);
    renderer.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    renderer.func_147784_q(block, x, y, z);
    renderer.func_147753_b(false);
    renderer.enableRender();
















    TEPartial te = (TEPartial)renderblocks.field_147845_a.func_147438_o(x, y, z);
    if (te.typeID <= 0) {
      return false;
    }
    long rvmeta = te.extraData;
    int type = te.typeID;
    int temeta = te.metaID;
    IIcon myTexture = (renderblocks.field_147840_d == null) ? Block.func_149729_e(type).func_149691_a(0, temeta) : renderblocks.field_147840_d;

    if ((rvmeta & 0x1L) == 0L) {

      renderer.func_147782_a(0.0D, 0.5D, 0.5D, 0.5D, 1.0D, 1.0D);
      renderer.renderCachedBlock(block, x, y, z, myTexture);
    }
    if ((rvmeta & 0x2L) == 0L) {

      renderer.func_147782_a(0.5D, 0.5D, 0.0D, 1.0D, 1.0D, 0.5D);
      renderer.renderCachedBlock(block, x, y, z, myTexture);
    }
    if ((rvmeta & 0x4L) == 0L) {

      renderer.func_147782_a(0.0D, 0.5D, 0.0D, 0.5D, 1.0D, 0.5D);
      renderer.renderCachedBlock(block, x, y, z, myTexture);
    }
    if ((rvmeta & 0x8L) == 0L) {

      renderer.func_147782_a(0.5D, 0.5D, 0.5D, 1.0D, 1.0D, 1.0D);
      renderer.renderCachedBlock(block, x, y, z, myTexture);
    }

    if ((rvmeta & 0x10L) == 0L) {

      renderer.func_147782_a(0.0D, 0.0D, 0.5D, 0.5D, 0.5D, 1.0D);
      renderer.renderCachedBlock(block, x, y, z, myTexture);
    }
    if ((rvmeta & 0x20L) == 0L) {

      renderer.func_147782_a(0.5D, 0.0D, 0.0D, 1.0D, 0.5D, 0.5D);
      renderer.renderCachedBlock(block, x, y, z, myTexture);
    }
    if ((rvmeta & 0x40L) == 0L) {

      renderer.func_147782_a(0.0D, 0.0D, 0.0D, 0.5D, 0.5D, 0.5D);
      renderer.renderCachedBlock(block, x, y, z, myTexture);
    }
    if ((rvmeta & 0x80L) == 0L) {

      renderer.func_147782_a(0.5D, 0.0D, 0.5D, 1.0D, 0.5D, 1.0D);
      renderer.renderCachedBlock(block, x, y, z, myTexture);
    }

    renderer.func_147771_a();
    renderer.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    return true;
  }


  public static boolean renderSulfur(Block block, int x, int y, int z, RenderBlocks renderblocks) {
    IBlockAccess world = renderblocks.field_147845_a;
    if (world.func_147439_a(x, y, z + 1).isSideSolid(world, x, y, z + 1, ForgeDirection.NORTH)) {

      renderblocks.func_147782_a(0.0D, 0.0D, 0.9900000095367432D, 1.0D, 1.0D, 1.0D);
      renderblocks.func_147784_q(block, x, y, z);
    }
    if (world.func_147439_a(x, y, z - 1).isSideSolid(world, x, y, z - 1, ForgeDirection.SOUTH)) {

      renderblocks.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.009999999776482582D);
      renderblocks.func_147784_q(block, x, y, z);
    }
    if (world.func_147439_a(x + 1, y, z).isSideSolid(world, x + 1, y, z, ForgeDirection.EAST)) {

      renderblocks.func_147782_a(0.9900000095367432D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
      renderblocks.func_147784_q(block, x, y, z);
    }
    if (world.func_147439_a(x - 1, y, z).isSideSolid(world, x - 1, y, z, ForgeDirection.WEST)) {

      renderblocks.func_147782_a(0.0D, 0.0D, 0.0D, 0.009999999776482582D, 1.0D, 1.0D);
      renderblocks.func_147784_q(block, x, y, z);
    }
    if (world.func_147439_a(x, y + 1, z).isSideSolid(world, x, y + 1, z, ForgeDirection.DOWN)) {

      renderblocks.func_147782_a(0.0D, 0.9900000095367432D, 0.0D, 1.0D, 1.0D, 1.0D);
      renderblocks.func_147784_q(block, x, y, z);
    }
    if (world.func_147439_a(x, y - 1, z).isSideSolid(world, x, y - 1, z, ForgeDirection.UP)) {

      renderblocks.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 0.009999999776482582D, 1.0D);
      renderblocks.func_147784_q(block, x, y, z);
    }

    return true;
  }


  public static boolean renderSnow(Block block, int i, int j, int k, RenderBlocks renderblocks) {
    int meta = renderblocks.field_147845_a.func_72805_g(i, j, k);
    float drift = 0.04F + meta * 0.06F;
    renderblocks.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, drift, 1.0D);
    renderblocks.func_147784_q(block, i, j, k);
    return true;
  }


  public static boolean renderWoodTrunk(Block block, int i, int j, int k, RenderBlocks renderblocks) {
    IBlockAccess blockAccess = renderblocks.field_147845_a;



    if (blockAccess.func_147438_o(i, j, k) != null && (blockAccess.func_147439_a(i, j - 1, k) == TFCBlocks.fruitTreeWood || blockAccess.func_147439_a(i, j - 1, k).func_149662_c())) {

      renderblocks.func_147782_a(0.30000001192092896D, 0.0D, 0.30000001192092896D, 0.699999988079071D, 1.0D, 0.699999988079071D);
      renderblocks.func_147784_q(block, i, j, k);
    }
    if (blockAccess.func_147439_a(i - 1, j, k).func_149688_o() == Material.field_151584_j || blockAccess.func_147439_a(i - 1, j, k) == TFCBlocks.fruitTreeWood) {

      renderblocks.func_147782_a(0.0D, 0.4000000059604645D, 0.4000000059604645D, 0.5D, 0.6000000238418579D, 0.6000000238418579D);
      renderblocks.func_147784_q(block, i, j, k);
    }
    if (blockAccess.func_147439_a(i + 1, j, k).func_149688_o() == Material.field_151584_j || blockAccess.func_147439_a(i + 1, j, k) == TFCBlocks.fruitTreeWood) {

      renderblocks.func_147782_a(0.5D, 0.4000000059604645D, 0.4000000059604645D, 1.0D, 0.6000000238418579D, 0.6000000238418579D);
      renderblocks.func_147784_q(block, i, j, k);
    }
    if (blockAccess.func_147439_a(i, j, k - 1).func_149688_o() == Material.field_151584_j || blockAccess.func_147439_a(i, j, k - 1) == TFCBlocks.fruitTreeWood) {

      renderblocks.func_147782_a(0.4000000059604645D, 0.4000000059604645D, 0.0D, 0.6000000238418579D, 0.6000000238418579D, 0.5D);
      renderblocks.func_147784_q(block, i, j, k);
    }
    if (blockAccess.func_147439_a(i, j, k + 1).func_149688_o() == Material.field_151584_j || blockAccess.func_147439_a(i, j, k + 1) == TFCBlocks.fruitTreeWood) {

      renderblocks.func_147782_a(0.4000000059604645D, 0.4000000059604645D, 0.5D, 0.6000000238418579D, 0.6000000238418579D, 1.0D);
      renderblocks.func_147784_q(block, i, j, k);
    }

    if (!((TEFruitTreeWood)blockAccess.func_147438_o(i, j, k)).isTrunk && blockAccess.func_147439_a(i, j - 1, k) != TFCBlocks.fruitTreeWood && !blockAccess.func_147439_a(i, j - 1, k).func_149662_c()) {

      renderblocks.func_147782_a(0.0D, 0.4000000059604645D, 0.4000000059604645D, 0.5D, 0.6000000238418579D, 0.6000000238418579D);
      renderblocks.func_147784_q(block, i, j, k);

      renderblocks.func_147782_a(0.5D, 0.4000000059604645D, 0.4000000059604645D, 1.0D, 0.6000000238418579D, 0.6000000238418579D);
      renderblocks.func_147784_q(block, i, j, k);

      renderblocks.func_147782_a(0.4000000059604645D, 0.4000000059604645D, 0.0D, 0.6000000238418579D, 0.6000000238418579D, 0.5D);
      renderblocks.func_147784_q(block, i, j, k);

      renderblocks.func_147782_a(0.4000000059604645D, 0.4000000059604645D, 0.5D, 0.6000000238418579D, 0.6000000238418579D, 1.0D);
      renderblocks.func_147784_q(block, i, j, k);
    }


    return true;
  }

  public static Random renderRandom = new Random();


  public static boolean renderLooseRock(Block block, int i, int j, int k, RenderBlocks renderblocks) {
    boolean breaking = false;






    WorldClient worldClient = (Minecraft.func_71410_x()).field_71441_e;

    renderblocks.field_147837_f = true;

    DataLayer rockLayer1 = TFC_Climate.getCacheManager((World)worldClient).getRockLayerAt(i, k, 0);
    if (rockLayer1 != null && rockLayer1.block != null && !breaking) {
      renderblocks.field_147840_d = rockLayer1.block.func_149691_a(0, rockLayer1.data2);
    }
    int seed = i * k + j;
    renderRandom.setSeed(seed);

    float xOffset = (renderRandom.nextInt(5) - 2) * 0.05F;
    float zOffset = (renderRandom.nextInt(5) - 2) * 0.05F;

    float xOffset2 = (renderRandom.nextInt(5) - 2) * 0.05F;
    float yOffset2 = (renderRandom.nextInt(5) - 2) * 0.05F;
    float zOffset2 = (renderRandom.nextInt(5) - 2) * 0.05F;

    renderblocks.func_147782_a((0.35F + xOffset), 0.0D, (0.35F + zOffset), (0.65F + xOffset2), (0.15F + yOffset2), (0.65F + zOffset2));
    renderblocks.func_147784_q(block, i, j, k);

    renderblocks.func_147771_a();

    return true;
  }





















  public static boolean renderOre(Block block, int xCoord, int yCoord, int zCoord, float par5, float par6, float par7, RenderBlocks renderblocks, IBlockAccess iblockaccess) {
    return true;
  }


  public static IIcon getRockTexture(World world, int xCoord, int yCoord, int zCoord) {
    IIcon var27;
    DataLayer rockLayer1 = TFC_Climate.getCacheManager(world).getRockLayerAt(xCoord, zCoord, 0);
    DataLayer rockLayer2 = TFC_Climate.getCacheManager(world).getRockLayerAt(xCoord, zCoord, 1);
    DataLayer rockLayer3 = TFC_Climate.getCacheManager(world).getRockLayerAt(xCoord, zCoord, 2);

    if (yCoord <= TFCOptions.rockLayer3Height) {
      var27 = rockLayer3.block.func_149691_a(5, rockLayer3.data2);
    } else if (yCoord <= TFCOptions.rockLayer2Height) {
      var27 = rockLayer2.block.func_149691_a(5, rockLayer2.data2);
    } else {
      var27 = rockLayer1.block.func_149691_a(5, rockLayer1.data2);
    }  return var27;
  }


  public static boolean renderMolten(Block block, int i, int j, int k, RenderBlocks renderblocks) {
    renderblocks.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    renderblocks.func_147784_q(block, i, j, k);

    return true;
  }



  public static boolean renderFirepit(Block block, int i, int j, int k, RenderBlocks renderblocks) {
    renderblocks.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 0.019999999552965164D, 1.0D);
    renderblocks.func_147784_q(block, i, j, k);
    renderblocks.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 0.019999999552965164D, 1.0D);
    return true;
  }



  public static boolean renderForge(Block block, int i, int j, int k, RenderBlocks renderblocks) {
    renderblocks.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 0.8999999761581421D, 1.0D);
    renderblocks.func_147784_q(block, i, j, k);
    renderblocks.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 0.8999999761581421D, 1.0D);
    return true;
  }


  public static boolean renderSluice(Block block, int i, int j, int k, RenderBlocks renderblocks) {
    IBlockAccess blockAccess = renderblocks.field_147845_a;

    int meta = blockAccess.func_72805_g(i, j, k);
    int dir = BlockSluice.getDirectionFromMetadata(meta);


    if (!BlockSluice.isBlockFootOfBed(meta)) {

      if (dir == 0) {

        for (int count = 0; count < 4; count++)
        {

          renderblocks.func_147782_a(0.0D, 0.0D, 0.0D + 0.25D * count, 1.0D, 1.0D - 0.125D * count, 0.05000000074505806D + 0.25D * count);
          renderblocks.func_147784_q(block, i, j, k);

          renderblocks.func_147782_a(0.0D, 0.0D, 0.05000000074505806D + 0.25D * count, 1.0D, 0.8125D - 0.125D * count, 0.25D + 0.25D * count);
          renderblocks.func_147784_q(block, i, j, k);
        }

      } else if (dir == 1) {

        if ((meta & 0x4) != 0)
          renderblocks.field_147867_u = 1;
        for (int count = 0; count < 4; count++)
        {

          renderblocks.func_147782_a(0.949999988079071D - 0.25D * count, 0.0D, 0.0D, 1.0D - 0.25D * count, 1.0D - 0.125D * count, 1.0D);
          renderblocks.func_147784_q(block, i, j, k);

          renderblocks.func_147782_a(0.75D - 0.25D * count, 0.0D, 0.0D, 0.949999988079071D - 0.25D * count, 0.8125D - 0.125D * count, 1.0D);
          renderblocks.func_147784_q(block, i, j, k);
        }

      } else if (dir == 2) {

        if ((meta & 0x4) != 0)
          renderblocks.field_147867_u = 3;
        for (int count = 0; count < 4; count++)
        {

          renderblocks.func_147782_a(0.0D, 0.0D, 0.949999988079071D - 0.25D * count, 1.0D, 1.0D - 0.125D * count, 1.0D - 0.25D * count);
          renderblocks.func_147784_q(block, i, j, k);

          renderblocks.func_147782_a(0.0D, 0.0D, 0.75D - 0.25D * count, 1.0D, 0.8125D - 0.125D * count, 0.949999988079071D - 0.25D * count);
          renderblocks.func_147784_q(block, i, j, k);
        }

      } else if (dir == 3) {

        if ((meta & 0x4) != 0)
          renderblocks.field_147867_u = 2;
        for (int count = 0; count < 4; count++)
        {

          renderblocks.func_147782_a(0.0D + 0.25D * count, 0.0D, 0.0D, 0.05000000074505806D + 0.25D * count, 1.0D - 0.125D * count, 1.0D);
          renderblocks.func_147784_q(block, i, j, k);

          renderblocks.func_147782_a(0.05000000074505806D + 0.25D * count, 0.0D, 0.0D, 0.25D + 0.25D * count, 0.8125D - 0.125D * count, 1.0D);
          renderblocks.func_147784_q(block, i, j, k);
        }

      }
    } else {

      if (dir == 0)
      {
        for (int count = 0; count < 4; count++) {


          renderblocks.func_147782_a(0.0D, 0.0D, 0.0D + 0.25D * count, 1.0D, 0.5D - 0.125D * count, 0.05000000074505806D + 0.25D * count);
          renderblocks.func_147784_q(block, i, j, k);

          renderblocks.func_147782_a(0.0D, 0.0D, 0.05000000074505806D + 0.25D * count, 1.0D, Math.max(0.3125D - 0.125D * count, 0.01D), 0.25D + 0.25D * count);
          renderblocks.func_147784_q(block, i, j, k);
        }
      }
      if (dir == 1) {

        if ((meta & 0x4) != 0)
          renderblocks.field_147867_u = 1;
        for (int count = 0; count < 4; count++) {


          renderblocks.func_147782_a(0.949999988079071D - 0.25D * count, 0.0D, 0.0D, 1.0D - 0.25D * count, 0.5D - 0.125D * count, 1.0D);
          renderblocks.func_147784_q(block, i, j, k);

          renderblocks.func_147782_a(0.75D - 0.25D * count, 0.0D, 0.0D, 0.949999988079071D - 0.25D * count, Math.max(0.3125D - 0.125D * count, 0.01D), 1.0D);
          renderblocks.func_147784_q(block, i, j, k);
        }
      }
      if (dir == 2) {

        if ((meta & 0x4) != 0)
          renderblocks.field_147867_u = 3;
        for (int count = 0; count < 4; count++) {


          renderblocks.func_147782_a(0.0D, 0.0D, 0.949999988079071D - 0.25D * count, 1.0D, 0.5D - 0.125D * count, 1.0D - 0.25D * count);
          renderblocks.func_147784_q(block, i, j, k);

          renderblocks.func_147782_a(0.0D, 0.0D, 0.75D - 0.25D * count, 1.0D, Math.max(0.3125D - 0.125D * count, 0.01D), 0.949999988079071D - 0.25D * count);
          renderblocks.func_147784_q(block, i, j, k);
        }
      }

      if (dir == 3) {

        if ((meta & 0x4) != 0)
          renderblocks.field_147867_u = 2;
        for (int count = 0; count < 4; count++) {


          renderblocks.func_147782_a(0.0D + 0.25D * count, 0.0D, 0.0D, 0.05000000074505806D + 0.25D * count, 0.5D - 0.125D * count, 1.0D);
          renderblocks.func_147784_q(block, i, j, k);

          renderblocks.func_147782_a(0.05000000074505806D + 0.25D * count, 0.0D, 0.0D, 0.25D + 0.25D * count, Math.max(0.3125D - 0.125D * count, 0.01D), 1.0D);
          renderblocks.func_147784_q(block, i, j, k);
        }
      }
    }
    renderblocks.field_147867_u = 0;

    renderblocks.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);

    return true;
  }


  public static boolean renderBlockWithCustomColorMultiplier(Block block, RenderBlocks renderBlocks, int xCoord, int yCoord, int zCoord, int colorMultiplier) {
    int l = colorMultiplier;
    float f = (l >> 16 & 0xFF) / 255.0F;
    float f1 = (l >> 8 & 0xFF) / 255.0F;
    float f2 = (l & 0xFF) / 255.0F;

    if (EntityRenderer.field_78517_a) {

      float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
      float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
      float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
      f = f3;
      f1 = f4;
      f2 = f5;
    }

    return (Minecraft.func_71379_u() && block.func_149750_m() == 0) ? (renderBlocks.field_147849_o ? renderBlocks

      .func_147751_a(block, xCoord, yCoord, zCoord, f, f1, f2) : renderBlocks
      .func_147751_a(block, xCoord, yCoord, zCoord, f, f1, f2)) : renderBlocks
      .func_147736_d(block, xCoord, yCoord, zCoord, f, f1, f2);
  }


  public static boolean renderFruitLeaves(Block block, int xCoord, int yCoord, int zCoord, RenderBlocks renderblocks) {
    int meta = renderblocks.field_147845_a.func_72805_g(xCoord, yCoord, zCoord);
    if (meta >= 8) {
      meta -= 8;
    }
    FloraManager manager = FloraManager.getInstance();
    FloraIndex index = manager.findMatchingIndex(BlockFruitLeaves.getType(block, meta));

    renderblocks.func_147784_q(block, xCoord, yCoord, zCoord);
    if (index != null && (index.inBloom(TFC_Time.getSeasonAdjustedMonth(zCoord)) || index.inHarvest(TFC_Time.getSeasonAdjustedMonth(zCoord)))) {

      renderblocks.field_147840_d = getFruitTreeOverlay(renderblocks.field_147845_a, xCoord, yCoord, zCoord);
      if (renderblocks.field_147840_d != null)
        renderBlockWithCustomColorMultiplier(block, renderblocks, xCoord, yCoord, zCoord, 16777215);
      renderblocks.func_147771_a();
    }
    return true;
  }


  public static boolean renderSeaPlant(Block par1Block, int par2, int par3, int par4, RenderBlocks renderblocks) {
    boolean substrateRender = false;
    boolean plantRender = false;
    TileEntity te = renderblocks.field_147845_a.func_147438_o(par2, par3, par4);
    if (te instanceof TEWaterPlant) {
      TEWaterPlant wp = (TEWaterPlant)te;
      if (wp.getBlockFromType() != null) {
        substrateRender = renderblocks.func_147736_d(wp.getBlockFromType(), par2, par3, par4, 1.0F, 1.0F, 1.0F);
        plantRender = RenderFlora.render(par1Block, par2, par3, par4, renderblocks);
      }
    }
    return (substrateRender && plantRender);
  }


  public static IIcon getFruitTreeOverlay(IBlockAccess world, int x, int y, int z) {
    IIcon out = null;
    int meta = world.func_72805_g(x, y, z);
    Block id = world.func_147439_a(x, y, z);
    int offset = (id == TFCBlocks.fruitTreeLeaves) ? 0 : 8;

    FloraManager manager = FloraManager.getInstance();
    FloraIndex index = manager.findMatchingIndex(BlockFruitLeaves.getType(id, meta & 0x7));
    if (index != null)
    {
      if (index.inBloom(TFC_Time.getSeasonAdjustedMonth(z))) {
        out = BlockFruitLeaves.iconsFlowers[(meta & 0x7) + offset];
      } else if (meta >= 8) {
        out = BlockFruitLeaves.iconsFruit[(meta & 0x7) + offset];
      }  }
    return out;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\TFC_CoreRender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */