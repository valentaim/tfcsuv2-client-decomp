package com.bioxx.tfc;

import com.bioxx.tfc.Core.WeatherManager;
import com.bioxx.tfc.api.Util.Helper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.nio.IntBuffer;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityRainFX;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class ClientOverrides {
  private static int rainSoundCounter;

  public static void loadRenderers() {
    RenderGlobal renderG = (Minecraft.func_71410_x()).field_71438_f;
    Object obj = Helper.getObject(renderG, "t", "field_72767_j", "worldRenderersToUpdate", TFCASMLoadingPlugin.runtimeDeobf);
    if (!(obj instanceof List))
      return;
    int k = 0, j = 0;
    int renderChunksWide = Helper.getInteger(renderG, "w", "field_72766_m", "renderChunksWide", TFCASMLoadingPlugin.runtimeDeobf);
    int renderChunksTall = Helper.getInteger(renderG, "x", "field_72763_n", "renderChunksTall", TFCASMLoadingPlugin.runtimeDeobf);
    int renderChunksDeep = Helper.getInteger(renderG, "y", "field_72764_o", "renderChunksDeep", TFCASMLoadingPlugin.runtimeDeobf);
    int glRenderListBase = Helper.getInteger(renderG, "z", "field_72778_p", "glRenderListBase", TFCASMLoadingPlugin.runtimeDeobf);
    WorldRenderer[] worldRenderers = (WorldRenderer[])Helper.getObject(renderG, "v", "field_72765_l", "worldRenderers", TFCASMLoadingPlugin.runtimeDeobf);
    WorldRenderer[] sortedWorldRenderers = (WorldRenderer[])Helper.getObject(renderG, "u", "field_72768_k", "sortedWorldRenderers", TFCASMLoadingPlugin.runtimeDeobf);

    List<WorldRenderer> worldRenderersToUpdate = (List<WorldRenderer>)Helper.getObject(renderG, "t", "field_72767_j", "worldRenderersToUpdate", TFCASMLoadingPlugin.runtimeDeobf);
    World world = (World)Helper.getObject(renderG, "r", "field_72769_h", "theWorld", TFCASMLoadingPlugin.runtimeDeobf);

    worldRenderersToUpdate.clear();
    renderG.field_147598_a.clear();

    for (int l = 0; l < renderChunksWide; l++) {

      for (int i1 = renderChunksTall - 1; i1 >= 0; i1--) {

        for (int j1 = 0; j1 < renderChunksDeep; j1++) {

          worldRenderers[(j1 * renderChunksTall + i1) * renderChunksWide + l] = new WorldRenderer(world, renderG.field_147598_a, l * 16, i1 * 16, j1 * 16, glRenderListBase + j);

          if (Helper.getBoolean(renderG, "D", "field_72774_t", "occlusionEnabled", TFCASMLoadingPlugin.runtimeDeobf))
          {
            (worldRenderers[(j1 * renderChunksTall + i1) * renderChunksWide + l]).field_78934_v = ((IntBuffer)Helper.getObject(renderG, "C", "field_72775_s", "glOcclusionQueryBase", TFCASMLoadingPlugin.runtimeDeobf)).get(k);
          }

          (worldRenderers[(j1 * renderChunksTall + i1) * renderChunksWide + l]).field_78935_u = false;
          (worldRenderers[(j1 * renderChunksTall + i1) * renderChunksWide + l]).field_78936_t = true;
          (worldRenderers[(j1 * renderChunksTall + i1) * renderChunksWide + l]).field_78927_l = true;
          (worldRenderers[(j1 * renderChunksTall + i1) * renderChunksWide + l]).field_78937_s = k++;
          worldRenderers[(j1 * renderChunksTall + i1) * renderChunksWide + l].func_78914_f();
          sortedWorldRenderers[(j1 * renderChunksTall + i1) * renderChunksWide + l] = worldRenderers[(j1 * renderChunksTall + i1) * renderChunksWide + l];
          worldRenderersToUpdate.add(worldRenderers[(j1 * renderChunksTall + i1) * renderChunksWide + l]);
          j += 3;
        }
      }
    }
  }




  public static void doRainClient(Random random, int rendererUpdateCount) {
    float f = (Minecraft.func_71410_x()).field_71441_e.func_72867_j(1.0F);

    if (!(Minecraft.func_71410_x()).field_71474_y.field_74347_j)
    {
      f /= 2.0F;
    }

    if (f != 0.0F) {

      random.setSeed(rendererUpdateCount * 312987231L);
      EntityLivingBase entitylivingbase = (Minecraft.func_71410_x()).field_71451_h;
      WorldClient worldclient = (Minecraft.func_71410_x()).field_71441_e;
      int i = MathHelper.func_76128_c(entitylivingbase.field_70165_t);
      int j = MathHelper.func_76128_c(entitylivingbase.field_70163_u);
      int k = MathHelper.func_76128_c(entitylivingbase.field_70161_v);
      byte b0 = 10;
      double d0 = 0.0D;
      double d1 = 0.0D;
      double d2 = 0.0D;
      int l = 0;
      int i1 = (int)(100.0F * f * f);

      if ((Minecraft.func_71410_x()).field_71474_y.field_74362_aa == 1) {

        i1 >>= 1;
      }
      else if ((Minecraft.func_71410_x()).field_71474_y.field_74362_aa == 2) {

        i1 = 0;
      }

      for (int j1 = 0; j1 < i1; j1++) {

        int x = i + random.nextInt(b0) - random.nextInt(b0);
        int z = k + random.nextInt(b0) - random.nextInt(b0);
        int y = worldclient.func_72874_g(x, z);
        Block b = worldclient.func_147439_a(x, y - 1, z);
        if (!WeatherManager.canSnow((World)(Minecraft.func_71410_x()).field_71441_e, x, y, z))
        {
          if (y <= j + b0 && y >= j - b0) {

            float f1 = random.nextFloat();
            float f2 = random.nextFloat();

            if (!b.isAir((IBlockAccess)worldclient, x, y - 1, z))
            {
              if (b.func_149688_o() == Material.field_151587_i) {

                (Minecraft.func_71410_x()).field_71452_i.func_78873_a((EntityFX)new EntitySmokeFX((World)worldclient, (x + f1), (y + 0.1F) - b.func_149665_z(), (z + f2), 0.0D, 0.0D, 0.0D));
              }
              else {

                l++;

                if (random.nextInt(l) == 0) {

                  d0 = (x + f1);
                  d1 = (y + 0.1F) - b.func_149665_z();
                  d2 = (z + f2);
                }

                (Minecraft.func_71410_x()).field_71452_i.func_78873_a((EntityFX)new EntityRainFX((World)worldclient, (x + f1), (y + 0.1F) - b.func_149665_z(), (z + f2)));
              }
            }
          }
        }
      }

      if (l > 0 && random.nextInt(3) < rainSoundCounter++) {

        rainSoundCounter = 0;

        if (d1 > entitylivingbase.field_70163_u + 1.0D && worldclient.func_72874_g(MathHelper.func_76128_c(entitylivingbase.field_70165_t), MathHelper.func_76128_c(entitylivingbase.field_70161_v)) > MathHelper.func_76128_c(entitylivingbase.field_70163_u)) {

          (Minecraft.func_71410_x()).field_71441_e.func_72980_b(d0, d1, d2, "ambient.weather.rain", 0.1F, 0.5F, false);
        }
        else {

          (Minecraft.func_71410_x()).field_71441_e.func_72980_b(d0, d1, d2, "ambient.weather.rain", 0.2F, 1.0F, false);
        }
      }
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\ClientOverrides.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */