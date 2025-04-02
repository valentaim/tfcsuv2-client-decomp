package com.bioxx.tfc.Commands;

import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.WorldGen.DataLayer;
import com.bioxx.tfc.WorldGen.TFCBiome;
import com.bioxx.tfc.api.TFCOptions;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;



public class PrintImageMapCommand
  extends CommandBase
{
  public String func_71517_b() {
    return "printimage";
  }



  public void func_71515_b(ICommandSender sender, String[] params) {
    EntityPlayerMP player = func_71521_c(sender);

    if (!TFCOptions.enableDebugMode) {

      TFC_Core.sendInfoMessage((EntityPlayer)player, (IChatComponent)new ChatComponentText("Debug Mode Required"));

      return;
    }
    MinecraftServer server = MinecraftServer.func_71276_C();
    WorldServer world = server.func_71218_a((player.func_130014_f_()).field_73011_w.field_76574_g);
    if (params.length >= 2) {

      String name = params[1];
      if (params[0].equals("biome")) {

        int size = (params.length >= 3) ? Integer.parseInt(params[2]) : 512;
        int skipSize = (params.length >= 4) ? Integer.parseInt(params[3]) : 1;
        drawBiomeImage((int)Math.floor(player.field_70165_t), (int)Math.floor(player.field_70161_v), size, (World)world, name, skipSize);
      }
      else if (params[0].equals("temp")) {

        int size = (params.length >= 3) ? Integer.parseInt(params[2]) : 512;
        int skipSize = (params.length >= 4) ? Integer.parseInt(params[3]) : 1;
        drawTempImage((int)Math.floor(player.field_70165_t), (int)Math.floor(player.field_70161_v), size, (World)world, name, skipSize);
      }
      else if (params[0].equals("drainage")) {

        int size = (params.length >= 3) ? Integer.parseInt(params[2]) : 512;
        int skipSize = (params.length >= 4) ? Integer.parseInt(params[3]) : 1;
        drawDrainageImage((int)Math.floor(player.field_70165_t), (int)Math.floor(player.field_70161_v), size, (World)world, name, skipSize);
      }
      else if (params[0].equals("ph")) {

        int size = (params.length >= 3) ? Integer.parseInt(params[2]) : 512;
        int skipSize = (params.length >= 4) ? Integer.parseInt(params[3]) : 1;
        drawPhImage((int)Math.floor(player.field_70165_t), (int)Math.floor(player.field_70161_v), size, (World)world, name, skipSize);
      }
    }
  }


  public static void drawPhImage(int xCoord, int zCoord, int size, World world, String name, int skipSize) {
    try {
      File outFile = new File(name + ".bmp");
      BufferedImage outBitmap = new BufferedImage(size, size, 1);
      Graphics2D graphics = (Graphics2D)outBitmap.getGraphics();
      graphics.clearRect(0, 0, size, size);
      TerraFirmaCraft.LOG.info(name + ".bmp");
      float perc = 0.1F;
      int sizeHalf = size / 2;
      float count = 0.0F;
      for (int x = -sizeHalf; x < sizeHalf; x++) {

        for (int z = -sizeHalf; z < sizeHalf; z++) {

          count++;
          int ph = (TFC_Climate.getCacheManager(world).getDrainageLayerAt(xCoord + x * skipSize, zCoord + z * skipSize)).data1;
          int g = ph * 50;
          graphics.setColor(Color.getColor("", g << 8));
          graphics.drawRect(x + sizeHalf, z + sizeHalf, 1, 1);
          if (count / (size * size) > perc) {

            TerraFirmaCraft.LOG.info((int)(perc * 100.0F) + "%");
            perc += 0.1F;
          }
        }
      }
      TerraFirmaCraft.LOG.info(name + ".bmp Done!");
      ImageIO.write(outBitmap, "BMP", outFile);
    }
    catch (Exception e) {

      TerraFirmaCraft.LOG.catching(e);
    }
  }


  public static void drawDrainageImage(int xCoord, int zCoord, int size, World world, String name, int skipSize) {
    try {
      File outFile = new File(name + ".bmp");
      BufferedImage outBitmap = new BufferedImage(size, size, 1);
      Graphics2D graphics = (Graphics2D)outBitmap.getGraphics();
      graphics.clearRect(0, 0, size, size);
      TerraFirmaCraft.LOG.info(name + ".bmp");
      float perc = 0.1F;
      int sizeHalf = size / 2;
      float count = 0.0F;
      for (int x = -sizeHalf; x < sizeHalf; x++) {

        for (int z = -sizeHalf; z < sizeHalf; z++) {

          count++;
          DataLayer dl = TFC_Climate.getCacheManager(world).getDrainageLayerAt(xCoord + x * skipSize, zCoord + z * skipSize);
          int drainage = dl.data1;
          int r = drainage * 42 / 2;
          int g = drainage * 42 / 4;
          graphics.setColor(Color.getColor("", (r << 16) + (g << 8)));
          graphics.drawRect(x + sizeHalf, z + sizeHalf, 1, 1);
          if (count / (size * size) > perc) {

            TerraFirmaCraft.LOG.info((int)(perc * 100.0F) + "%");
            perc += 0.1F;
          }
        }
      }
      TerraFirmaCraft.LOG.info(name + ".bmp Done!");
      ImageIO.write(outBitmap, "BMP", outFile);
    }
    catch (Exception e) {

      TerraFirmaCraft.LOG.catching(e);
    }
  }


  public static void drawTempImage(int xCoord, int zCoord, int size, World world, String name, int skipSize) {
    try {
      File outFile = new File(name + ".bmp");
      BufferedImage outBitmap = new BufferedImage(size, size, 1);
      Graphics2D graphics = (Graphics2D)outBitmap.getGraphics();
      graphics.clearRect(0, 0, size, size);
      TerraFirmaCraft.LOG.info(name + ".bmp");
      float perc = 0.1F;
      int sizeHalf = size / 2;
      float count = 0.0F;
      for (int x = -sizeHalf; x < sizeHalf; x++) {

        for (int z = -sizeHalf; z < sizeHalf; z++) {

          count++;
          int temp = (int)(255.0F * TFC_Climate.getBioTemperature(world, xCoord + x * skipSize, zCoord + z * skipSize) / 50.0F);
          graphics.setColor(Color.getColor("", (temp << 16) + (temp << 8) + temp));
          graphics.drawRect(x + sizeHalf, z + sizeHalf, 1, 1);
          if (count / (size * size) > perc) {

            TerraFirmaCraft.LOG.info((int)(perc * 100.0F) + "%");
            perc += 0.1F;
          }
        }
      }
      TerraFirmaCraft.LOG.info(name + ".bmp Done!");
      ImageIO.write(outBitmap, "BMP", outFile);
    }
    catch (Exception e) {

      TerraFirmaCraft.LOG.catching(e);
    }
  }


  public static void drawBiomeImage(int xCoord, int zCoord, int size, World world, String name, int skipSize) {
    try {
      File outFile = new File(name + ".bmp");
      BufferedImage outBitmap = new BufferedImage(size, size, 1);
      Graphics2D graphics = (Graphics2D)outBitmap.getGraphics();
      graphics.clearRect(0, 0, size, size);
      TerraFirmaCraft.LOG.info(name + ".bmp");
      float perc = 0.1F;
      float count = 0.0F;
      for (int x = -size / 2; x < size / 2; x++) {

        for (int z = -size / 2; z < size / 2; z++) {

          count++;
          graphics.setColor(Color.getColor("", ((TFCBiome)world.func_72959_q().func_76935_a(x * skipSize + xCoord, z * skipSize + zCoord)).getBiomeColor()));
          graphics.drawRect(x + size / 2, z + size / 2, 1, 1);
          if (count / (size * size) > perc) {

            TerraFirmaCraft.LOG.info((int)(perc * 100.0F) + "%");
            perc += 0.1F;
          }
        }
      }
      TerraFirmaCraft.LOG.info(name + ".bmp Done!");
      ImageIO.write(outBitmap, "BMP", outFile);
    }
    catch (Exception e) {

      TerraFirmaCraft.LOG.catching(e);
    }
  }



  public String func_71518_a(ICommandSender icommandsender) {
    return "";
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Commands\PrintImageMapCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */