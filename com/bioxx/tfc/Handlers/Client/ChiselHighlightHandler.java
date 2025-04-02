package com.bioxx.tfc.Handlers.Client;

import com.bioxx.tfc.Core.Player.PlayerManagerTFC;
import com.bioxx.tfc.api.Tools.ChiselManager;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import org.lwjgl.opengl.GL11;






public class ChiselHighlightHandler
{
  @SubscribeEvent
  public void drawBlockHighlightEvent(DrawBlockHighlightEvent evt) {
    EntityPlayer player = evt.player;
    World world = player.field_70170_p;

    if (evt.currentItem != null && evt.currentItem.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemChisel) {

      boolean hasHammer = false;
      for (int i = 0; i < 9; i++) {

        if (player.field_71071_by.field_70462_a[i] != null && player.field_71071_by.field_70462_a[i].func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemHammer) {

          hasHammer = true;

          break;
        }
      }
      if (hasHammer) {

        MovingObjectPosition target = evt.target;
        Block id = world.func_147439_a(target.field_72311_b, target.field_72312_c, target.field_72309_d);
        int mode = (PlayerManagerTFC.getInstance().getClientPlayer()).chiselMode;


        if (mode > -1) {


          double hitX = Math.round((target.field_72307_f.field_72450_a - target.field_72311_b) * 100.0D) / 100.0D;
          double hitY = Math.round((target.field_72307_f.field_72448_b - target.field_72312_c) * 100.0D) / 100.0D;
          double hitZ = Math.round((target.field_72307_f.field_72449_c - target.field_72309_d) * 100.0D) / 100.0D;

          ChiselManager.getInstance().setDivision(mode, target.field_72310_e);
          int divX = ChiselManager.getInstance().getDivX(mode, id);
          int divY = ChiselManager.getInstance().getDivY(mode, id);
          int divZ = ChiselManager.getInstance().getDivZ(mode, id);

          if (divX > 0) {



            double subX = (int)(hitX * divX) / divX;
            double subY = (int)(hitY * divY) / divY;
            double subZ = (int)(hitZ * divZ) / divZ;

            switch (target.field_72310_e) {

              case 1:
                subY -= 1.0D / divY;
                if (hitX == 1.0D)
                  subX -= 1.0D / divX;
                if (hitZ == 1.0D)
                  subZ -= 1.0D / divZ;
                break;
              case 3:
                subZ -= 1.0D / divZ;
                if (hitX == 1.0D)
                  subX -= 1.0D / divX;
                if (hitY == 1.0D)
                  subY -= 1.0D / divY;
                break;
              case 5:
                subX -= 1.0D / divX;
                if (hitY == 1.0D)
                  subY -= 1.0D / divY;
                if (hitZ == 1.0D) {
                  subZ -= 1.0D / divZ;
                }
                break;
            }

            double minX = target.field_72311_b + subX;
            double minY = target.field_72312_c + subY;
            double minZ = target.field_72309_d + subZ;
            double maxX = minX + 1.0D / divX;
            double maxY = minY + 1.0D / divY;
            double maxZ = minZ + 1.0D / divZ;

            double var8 = player.field_70142_S + (player.field_70165_t - player.field_70142_S) * evt.partialTicks;
            double var10 = player.field_70137_T + (player.field_70163_u - player.field_70137_T) * evt.partialTicks;
            double var12 = player.field_70136_U + (player.field_70161_v - player.field_70136_U) * evt.partialTicks;


            GL11.glEnable(3042);
            GL11.glBlendFunc(1, 1);
            GL11.glDisable(3553);
            GL11.glDepthMask(false);


            GL11.glBlendFunc(770, 771);
            GL11.glColor4f(1.0F, 0.0F, 0.0F, 0.4F);
            GL11.glLineWidth(4.0F);
            GL11.glDepthMask(true);

            drawOutlinedBoundingBox(AxisAlignedBB.func_72330_a(minX, minY, minZ, maxX, maxY, maxZ).func_72314_b(0.0020000000949949026D, 0.0020000000949949026D, 0.0020000000949949026D).func_72325_c(-var8, -var10, -var12));

            GL11.glDepthMask(true);
            GL11.glEnable(3553);
            GL11.glDisable(3042);
          }
        }
      }
    }
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


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\Client\ChiselHighlightHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */