package com.bioxx.tfc.Handlers.Client;

import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import org.lwjgl.opengl.GL11;





public class PlankHighlightHandler
{
  @SubscribeEvent
  public void drawBlockHighlightEvent(DrawBlockHighlightEvent evt) {
    World world = evt.player.field_70170_p;
    double var8 = evt.player.field_70142_S + (evt.player.field_70165_t - evt.player.field_70142_S) * evt.partialTicks;
    double var10 = evt.player.field_70137_T + (evt.player.field_70163_u - evt.player.field_70137_T) * evt.partialTicks;
    double var12 = evt.player.field_70136_U + (evt.player.field_70161_v - evt.player.field_70136_U) * evt.partialTicks;

    if (evt.currentItem != null && evt.currentItem.func_77973_b() instanceof com.bioxx.tfc.Items.ItemPlank) {

























      GL11.glDisable(3553);

      boolean isConstruct = (world.func_147439_a(evt.target.field_72311_b, evt.target.field_72312_c, evt.target.field_72309_d) == TFCBlocks.woodConstruct);


      double hitX = Math.round((evt.target.field_72307_f.field_72450_a - evt.target.field_72311_b) * 100.0D) / 100.0D;
      double hitY = Math.round((evt.target.field_72307_f.field_72448_b - evt.target.field_72312_c) * 100.0D) / 100.0D;
      double hitZ = Math.round((evt.target.field_72307_f.field_72449_c - evt.target.field_72309_d) * 100.0D) / 100.0D;


      double subX = (int)(hitX * 8.0D) / 8.0D;
      double subY = (int)(hitY * 8.0D) / 8.0D;
      double subZ = (int)(hitZ * 8.0D) / 8.0D;


      double minX = evt.target.field_72311_b + subX;
      double minY = evt.target.field_72312_c + subY;
      double minZ = evt.target.field_72309_d + subZ;
      double maxX = minX + 0.125D;
      double maxY = minY + 0.125D;
      double maxZ = minZ + 0.125D;

      if (isConstruct && hitY != 0.0D && hitY != 1.0D && hitZ != 0.0D && hitZ != 1.0D && hitX != 0.0D && hitX != 1.0D) {

        if (evt.target.field_72310_e == 0)
        {
          minY = evt.target.field_72312_c;
          maxY = (evt.target.field_72312_c + 1);
        }
        else if (evt.target.field_72310_e == 1)
        {
          minY = evt.target.field_72312_c;
          maxY = (evt.target.field_72312_c + 1);
        }
        else if (evt.target.field_72310_e == 2)
        {
          minZ = evt.target.field_72309_d;
          maxZ = (evt.target.field_72309_d + 1);
        }
        else if (evt.target.field_72310_e == 3)
        {
          minZ = evt.target.field_72309_d;
          maxZ = (evt.target.field_72309_d + 1);
        }
        else if (evt.target.field_72310_e == 4)
        {
          minX = evt.target.field_72311_b;
          maxX = (evt.target.field_72311_b + 1);
        }
        else if (evt.target.field_72310_e == 5)
        {
          minX = evt.target.field_72311_b;
          maxX = (evt.target.field_72311_b + 1);

        }

      }
      else if (evt.target.field_72310_e == 0) {

        maxY = minY;
        minY--;
      }
      else if (evt.target.field_72310_e == 1) {

        maxY = minY + 1.0D;
      }
      else if (evt.target.field_72310_e == 2) {

        maxZ = minZ;
        minZ--;
      }
      else if (evt.target.field_72310_e == 3) {

        maxZ = minZ + 1.0D;
      }
      else if (evt.target.field_72310_e == 4) {

        maxX = minX;
        minX--;
      }
      else if (evt.target.field_72310_e == 5) {

        maxX = minX + 1.0D;
      }



      GL11.glEnable(3042);

      GL11.glBlendFunc(770, 771);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.4F);
      GL11.glDisable(2884);
      GL11.glDepthMask(false);

      drawBox(AxisAlignedBB.func_72330_a(minX, minY, minZ, maxX, maxY, maxZ).func_72314_b(0.0020000000949949026D, 0.0020000000949949026D, 0.0020000000949949026D).func_72325_c(-var8, -var10, -var12));

      GL11.glDepthMask(true);
      GL11.glEnable(3553);
      GL11.glDisable(3042);
    }
  }


  public void drawFaceUV(AxisAlignedBB par1AxisAlignedBB, int side) {
    Tessellator var2 = Tessellator.field_78398_a;

    var2.func_78369_a(1.0F, 1.0F, 1.0F, 1.0F);

    var2.func_78371_b(7);

    if (side == 0) {

      var2.func_78374_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c, 0.0D, 0.0D);
      var2.func_78374_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c, 1.0D, 0.0D);
      var2.func_78374_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f, 1.0D, 1.0D);
      var2.func_78374_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f, 0.0D, 1.0D);
    }
    else if (side == 1) {

      var2.func_78374_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c, 0.0D, 0.0D);
      var2.func_78374_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c, 1.0D, 0.0D);
      var2.func_78374_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f, 1.0D, 1.0D);
      var2.func_78374_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f, 0.0D, 1.0D);
    }
    else if (side == 2) {

      var2.func_78374_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c, 0.0D, 0.0D);
      var2.func_78374_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c, 1.0D, 0.0D);
      var2.func_78374_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c, 1.0D, 1.0D);
      var2.func_78374_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c, 0.0D, 1.0D);
    }
    else if (side == 3) {

      var2.func_78374_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f, 0.0D, 0.0D);
      var2.func_78374_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f, 1.0D, 0.0D);
      var2.func_78374_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f, 1.0D, 1.0D);
      var2.func_78374_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f, 0.0D, 1.0D);
    }
    else if (side == 4) {

      var2.func_78374_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c, 0.0D, 0.0D);
      var2.func_78374_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f, 1.0D, 0.0D);
      var2.func_78374_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f, 1.0D, 1.0D);
      var2.func_78374_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c, 0.0D, 1.0D);
    }
    else if (side == 5) {

      var2.func_78374_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c, 0.0D, 0.0D);
      var2.func_78374_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f, 1.0D, 0.0D);
      var2.func_78374_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f, 1.0D, 1.0D);
      var2.func_78374_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c, 0.0D, 1.0D);
    }
    var2.func_78381_a();
  }


  public void drawFace(AxisAlignedBB par1AxisAlignedBB) {
    Tessellator var2 = Tessellator.field_78398_a;


    var2.func_78371_b(7);
    var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
    var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
    var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
    var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
    var2.func_78381_a();
  }


  public void drawBox(AxisAlignedBB par1AxisAlignedBB) {
    Tessellator var2 = Tessellator.field_78398_a;


    var2.func_78371_b(7);
    var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
    var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
    var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
    var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
    var2.func_78381_a();


    var2.func_78371_b(7);
    var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
    var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
    var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
    var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
    var2.func_78381_a();


    var2.func_78371_b(7);
    var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
    var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
    var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
    var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
    var2.func_78381_a();


    var2.func_78371_b(7);
    var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
    var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
    var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
    var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
    var2.func_78381_a();


    var2.func_78371_b(7);
    var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
    var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
    var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
    var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
    var2.func_78381_a();


    var2.func_78371_b(7);
    var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
    var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
    var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
    var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
    var2.func_78381_a();
  }


  public void drawOutlinedBoundingBox(AxisAlignedBB par1AxisAlignedBB) {
    Tessellator var2 = Tessellator.field_78398_a;
    var2.func_78371_b(3);
    var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
    var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
    var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
    var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
    var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
    var2.func_78381_a();
    var2.func_78371_b(3);
    var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
    var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
    var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
    var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
    var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
    var2.func_78381_a();
    var2.func_78371_b(1);
    var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
    var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
    var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
    var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
    var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
    var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
    var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
    var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
    var2.func_78381_a();
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\Client\PlankHighlightHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */