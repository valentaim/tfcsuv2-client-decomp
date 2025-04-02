package com.bioxx.tfc.Handlers.Client;

import com.bioxx.tfc.TileEntities.TEStand;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import org.lwjgl.opengl.GL11;



public class ArmourStandHighlightHandler
{
  private AxisAlignedBB boxToRender;

  @SubscribeEvent
  public void drawBlockHighlightEvent(DrawBlockHighlightEvent evt) {
    World world = evt.player.field_70170_p;
    if (evt.currentItem == null && TFCBlocks.isArmourStand(world.func_147439_a(evt.target.field_72311_b, evt.target.field_72312_c, evt.target.field_72309_d)) &&
      Math.sqrt(evt.target.field_72307_f.func_72445_d(evt.player.field_70165_t, evt.player.field_70163_u, evt.player.field_70161_v)) < 2.5D) {


      EntityPlayer player = evt.player;
      Vec3 vectorTerm = player.func_70040_Z();
      vectorTerm.field_72450_a *= 0.5D;
      vectorTerm.field_72448_b *= 0.5D;
      vectorTerm.field_72449_c *= 0.5D;
      vectorTerm = vectorTerm.func_72441_c(evt.target.field_72307_f.field_72450_a, evt.target.field_72307_f.field_72448_b, evt.target.field_72307_f.field_72449_c);
      TEStand stand = (TEStand)world.func_147438_o(evt.target.field_72311_b, evt.target.field_72312_c, evt.target.field_72309_d);
      boolean isTop = stand.isTop;

      if (isTop)
        stand = (TEStand)world.func_147438_o(evt.target.field_72311_b, evt.target.field_72312_c - 1, evt.target.field_72309_d);
      boolean ns = (stand.yaw % 360.0F == 0.0F || stand.yaw % 360.0F == 180.0F);
      double var8 = player.field_70142_S + (player.field_70165_t - player.field_70142_S) * evt.partialTicks;
      double var10 = player.field_70137_T + (player.field_70163_u - player.field_70137_T) * evt.partialTicks;
      double var12 = player.field_70136_U + (player.field_70161_v - player.field_70136_U) * evt.partialTicks;


      AxisAlignedBB head = AxisAlignedBB.func_72330_a(evt.target.field_72311_b + 0.5D - 0.3D, evt.target.field_72312_c + 1.35D + (isTop ? -1 : false), evt.target.field_72309_d + 0.5D - 0.3D, evt.target.field_72311_b + 0.5D + 0.3D, evt.target.field_72312_c + 1.95D + (isTop ? -1 : false), evt.target.field_72309_d + 0.5D + 0.3D);



      AxisAlignedBB body = AxisAlignedBB.func_72330_a(evt.target.field_72311_b + 0.5D - 0.55D, evt.target.field_72312_c + 0.75D + (isTop ? -1 : false), evt.target.field_72309_d + 0.5D - 0.17D, evt.target.field_72311_b + 0.5D + 0.55D, evt.target.field_72312_c + 1.5D + (isTop ? -1 : false), evt.target.field_72309_d + 0.5D + 0.17D);


      if (!ns) {
        body = AxisAlignedBB.func_72330_a(evt.target.field_72311_b + 0.5D - 0.17D, evt.target.field_72312_c + 0.75D + (isTop ? -1 : false), evt.target.field_72309_d + 0.5D - 0.55D, evt.target.field_72311_b + 0.5D + 0.17D, evt.target.field_72312_c + 1.5D + (isTop ? -1 : false), evt.target.field_72309_d + 0.5D + 0.55D);
      }


      AxisAlignedBB legs = AxisAlignedBB.func_72330_a(evt.target.field_72311_b + 0.5D - 0.315D, evt.target.field_72312_c + 0.19999999999999996D + (isTop ? -1 : false), evt.target.field_72309_d + 0.5D - 0.1395D, evt.target.field_72311_b + 0.5D + 0.315D, evt.target.field_72312_c + 0.9D + (isTop ? -1 : false), evt.target.field_72309_d + 0.5D + 0.1395D);


      if (!ns) {
        legs = AxisAlignedBB.func_72330_a(evt.target.field_72311_b + 0.5D - 0.1395D, evt.target.field_72312_c + 0.19999999999999996D + (isTop ? -1 : false), evt.target.field_72309_d + 0.5D - 0.315D, evt.target.field_72311_b + 0.5D + 0.1395D, evt.target.field_72312_c + 0.9D + (isTop ? -1 : false), evt.target.field_72309_d + 0.5D + 0.315D);
      }


      AxisAlignedBB feet = AxisAlignedBB.func_72330_a(evt.target.field_72311_b + 0.5D - 0.35D, evt.target.field_72312_c + 0.15D + (isTop ? -1 : false), evt.target.field_72309_d + 0.5D - 0.17D, evt.target.field_72311_b + 0.5D + 0.35D, evt.target.field_72312_c + 0.40000000000000013D + (isTop ? -1 : false), evt.target.field_72309_d + 0.5D + 0.17D);


      if (!ns) {
        feet = AxisAlignedBB.func_72330_a(evt.target.field_72311_b + 0.5D - 0.17D, evt.target.field_72312_c + 0.15D + (isTop ? -1 : false), evt.target.field_72309_d + 0.5D - 0.35D, evt.target.field_72311_b + 0.5D + 0.17D, evt.target.field_72312_c + 0.40000000000000013D + (isTop ? -1 : false), evt.target.field_72309_d + 0.5D + 0.35D);
      }


      Vec3 unit = vectorTerm.func_72432_b();
      unit = player.func_70040_Z();
      if (isVecInsideBox(head, player, unit, var8, var10, var12) && stand.items[4] != null) {

        this.boxToRender = head;

      }
      else if (isVecInsideBox(body, player, unit, var8, var10, var12) && stand.items[3] != null) {

        this.boxToRender = body;

      }
      else if (isVecInsideBox(legs, player, unit, var8, var10, var12) && stand.items[2] != null) {

        this.boxToRender = legs;

      }
      else if (isVecInsideBox(feet, player, unit, var8, var10, var12) && stand.items[1] != null) {

        this.boxToRender = feet;
      }






      if (this.boxToRender != null) {


        GL11.glEnable(3042);
        GL11.glBlendFunc(1, 1);
        GL11.glDisable(3553);
        GL11.glDepthMask(false);


        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(1.0F, 0.0F, 0.0F, 0.4F);
        GL11.glLineWidth(4.0F);
        GL11.glDepthMask(true);

        drawOutlinedBoundingBox(this.boxToRender.func_72314_b(0.019999999552965164D, 0.019999999552965164D, 0.019999999552965164D).func_72325_c(-var8, -var10, -var12));
        GL11.glDepthMask(true);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
      }
      this.boxToRender = null;
    }
  }


  public boolean isVecInsideBox(AxisAlignedBB aabb, EntityPlayer player, Vec3 unit, double xOffset, double yOffset, double zOffset) {
    unit = player.func_70040_Z();
    aabb.field_72338_b += 0.1D;
    aabb.field_72337_e += 0.1D;
    Vec3 playerVec = Vec3.func_72443_a(player.field_70165_t, player.field_70163_u + player.eyeHeight, player.field_70161_v);


    Vec3 distBlockxyz = playerVec.func_72444_a(Vec3.func_72443_a(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72339_c)).func_72432_b();
    Vec3 distBlockXYZ = playerVec.func_72444_a(Vec3.func_72443_a(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72334_f)).func_72432_b();

    Vec3 distBlockxyZ = playerVec.func_72444_a(Vec3.func_72443_a(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72334_f)).func_72432_b();
    Vec3 distBlockXYz = playerVec.func_72444_a(Vec3.func_72443_a(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72339_c)).func_72432_b();

    Vec3 distBlockxYZ = playerVec.func_72444_a(Vec3.func_72443_a(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72334_f)).func_72432_b();
    Vec3 distBlockXyz = playerVec.func_72444_a(Vec3.func_72443_a(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72339_c)).func_72432_b();

    Vec3 distBlockxYz = playerVec.func_72444_a(Vec3.func_72443_a(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72339_c)).func_72432_b();
    Vec3 distBlockXyZ = playerVec.func_72444_a(Vec3.func_72443_a(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72334_f)).func_72432_b();

    double currentLongestProj = 0.0D;
    currentLongestProj = Math.max(currentLongestProj, unit.func_72430_b(distBlockxyz));
    currentLongestProj = Math.max(currentLongestProj, unit.func_72430_b(distBlockXYZ));
    currentLongestProj = Math.max(currentLongestProj, unit.func_72430_b(distBlockXyz));
    currentLongestProj = Math.max(currentLongestProj, unit.func_72430_b(distBlockxYZ));
    currentLongestProj = Math.max(currentLongestProj, unit.func_72430_b(distBlockxYz));
    currentLongestProj = Math.max(currentLongestProj, unit.func_72430_b(distBlockXyZ));
    currentLongestProj = Math.max(currentLongestProj, unit.func_72430_b(distBlockxyZ));
    currentLongestProj = Math.max(currentLongestProj, unit.func_72430_b(distBlockXYz));

    unit.field_72450_a *= currentLongestProj * 0.99D; unit.field_72448_b *= currentLongestProj * 0.99D; unit.field_72449_c *= currentLongestProj * 0.99D;









    boolean insideBoxX = ((unit.field_72450_a >= distBlockxyz.field_72450_a && unit.field_72450_a <= distBlockXYZ.field_72450_a) || (unit.field_72450_a >= distBlockxyZ.field_72450_a && unit.field_72450_a <= distBlockXYz.field_72450_a) || (unit.field_72450_a >= distBlockxYZ.field_72450_a && unit.field_72450_a <= distBlockXyz.field_72450_a) || (unit.field_72450_a >= distBlockxYz.field_72450_a && unit.field_72450_a <= distBlockXyZ.field_72450_a));




    boolean insideBoxY = ((unit.field_72448_b >= distBlockxyz.field_72448_b && unit.field_72448_b <= distBlockXYZ.field_72448_b) || (unit.field_72448_b >= distBlockxyZ.field_72448_b && unit.field_72448_b <= distBlockXYz.field_72448_b) || (unit.field_72448_b >= distBlockXyz.field_72448_b && unit.field_72448_b <= distBlockxYZ.field_72448_b) || (unit.field_72448_b >= distBlockXyZ.field_72448_b && unit.field_72448_b <= distBlockxYz.field_72448_b));




    boolean insideBoxZ = ((unit.field_72449_c >= distBlockxyz.field_72449_c && unit.field_72449_c <= distBlockXYZ.field_72449_c) || (unit.field_72449_c >= distBlockXYz.field_72449_c && unit.field_72449_c <= distBlockxyZ.field_72449_c) || (unit.field_72449_c >= distBlockXyz.field_72449_c && unit.field_72449_c <= distBlockxYZ.field_72449_c) || (unit.field_72449_c >= distBlockxYz.field_72449_c && unit.field_72449_c <= distBlockXyZ.field_72449_c));

































    aabb.field_72338_b -= 0.1D;
    aabb.field_72337_e -= 0.1D;
    return (insideBoxX && insideBoxY && insideBoxZ);
  }


  public void drawLine(Vec3 origin, Vec3 vector) {
    Tessellator var2 = Tessellator.field_78398_a;
    var2.func_78371_b(3);


    var2.func_78377_a(origin.field_72450_a, origin.field_72448_b, origin.field_72449_c);
    var2.func_78377_a(vector.field_72450_a, vector.field_72448_b, vector.field_72449_c);
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


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\Client\ArmourStandHighlightHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */