package com.bioxx.tfc.Handlers.Client;

import com.bioxx.tfc.Blocks.BlockFarmland;
import com.bioxx.tfc.Core.Player.PlayerInfo;
import com.bioxx.tfc.Core.Player.PlayerManagerTFC;
import com.bioxx.tfc.Core.Player.SkillStats;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.Food.CropIndex;
import com.bioxx.tfc.Food.CropManager;
import com.bioxx.tfc.TileEntities.TECrop;
import com.bioxx.tfc.TileEntities.TEFarmland;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.TFCOptions;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import org.lwjgl.opengl.GL11;






public class FarmlandHighlightHandler
{
  @SubscribeEvent
  public void drawBlockHighlightEvent(DrawBlockHighlightEvent evt) {
    World world = evt.player.field_70170_p;
    double var8 = evt.player.field_70142_S + (evt.player.field_70165_t - evt.player.field_70142_S) * evt.partialTicks;
    double var10 = evt.player.field_70137_T + (evt.player.field_70163_u - evt.player.field_70137_T) * evt.partialTicks;
    double var12 = evt.player.field_70136_U + (evt.player.field_70161_v - evt.player.field_70136_U) * evt.partialTicks;

    boolean isMetalHoe = false;

    if (evt.currentItem != null && evt.currentItem
      .func_77973_b() != TFCItems.igInHoe && evt.currentItem
      .func_77973_b() != TFCItems.igExHoe && evt.currentItem
      .func_77973_b() != TFCItems.sedHoe && evt.currentItem
      .func_77973_b() != TFCItems.mMHoe)
    {
      isMetalHoe = true;
    }

    PlayerManagerTFC manager = PlayerManagerTFC.getInstance();
    PlayerInfo playerInfo = (manager != null) ? manager.getClientPlayer() : null;
    int hoeMode = (playerInfo != null) ? playerInfo.hoeMode : -1;

    if (evt.currentItem != null && evt.currentItem.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemCustomHoe && isMetalHoe && hoeMode == 1) {

      if (TFC_Core.getSkillStats(evt.player) != null) {

        SkillStats.SkillRank sr = TFC_Core.getSkillStats(evt.player).getSkillRank("skill.agriculture");
        if (sr != SkillStats.SkillRank.Expert && sr != SkillStats.SkillRank.Master) {
          return;
        }
      }
      Block b = world.func_147439_a(evt.target.field_72311_b, evt.target.field_72312_c, evt.target.field_72309_d);
      int crop = 0;
      if (b == TFCBlocks.crops && (world
        .func_147439_a(evt.target.field_72311_b, evt.target.field_72312_c - 1, evt.target.field_72309_d) == TFCBlocks.tilledSoil || world
        .func_147439_a(evt.target.field_72311_b, evt.target.field_72312_c - 1, evt.target.field_72309_d) == TFCBlocks.tilledSoil2)) {

        b = TFCBlocks.tilledSoil;
        crop = 1;
      }

      if (b == TFCBlocks.tilledSoil || b == TFCBlocks.tilledSoil2)
      {
        TEFarmland te = (TEFarmland)world.func_147438_o(evt.target.field_72311_b, evt.target.field_72312_c - crop, evt.target.field_72309_d);
        te.requestNutrientData();

        float timeMultiplier = TFC_Time.daysInYear / 360.0F;
        int soilMax = (int)(25000.0F * timeMultiplier);


        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);

        GL11.glDisable(2884);

        GL11.glDisable(3553);
        GL11.glDepthMask(false);

        double offset = 0.0D;
        double fertilizer = 1.02D + te.nutrients[3] / soilMax * 0.5D;
        GL11.glColor4ub(TFCOptions.cropFertilizerColor[0], TFCOptions.cropFertilizerColor[1], TFCOptions.cropFertilizerColor[2], TFCOptions.cropFertilizerColor[3]);
        drawBox(AxisAlignedBB.func_72330_a(evt.target.field_72311_b + offset, evt.target.field_72312_c + 1.01D - crop, evt.target.field_72309_d, (evt.target.field_72311_b + 1), evt.target.field_72312_c + fertilizer - crop, (evt.target.field_72309_d + 1))






            .func_72314_b(0.0020000000949949026D, 0.0020000000949949026D, 0.0020000000949949026D).func_72325_c(-var8, -var10, -var12));

        double nutrient = 1.02D + te.nutrients[0] / soilMax * 0.5D;
        GL11.glColor4ub(TFCOptions.cropNutrientAColor[0], TFCOptions.cropNutrientAColor[1], TFCOptions.cropNutrientAColor[2], TFCOptions.cropNutrientAColor[3]);
        drawBox(AxisAlignedBB.func_72330_a(evt.target.field_72311_b + offset, evt.target.field_72312_c + 1.01D - crop + fertilizer - 1.02D, evt.target.field_72309_d, evt.target.field_72311_b + offset + 0.3333D, evt.target.field_72312_c + nutrient - crop + fertilizer - 1.02D, (evt.target.field_72309_d + 1))






            .func_72314_b(0.0020000000949949026D, 0.0020000000949949026D, 0.0020000000949949026D).func_72325_c(-var8, -var10, -var12));

        offset = 0.3333D;
        nutrient = 1.02D + te.nutrients[1] / soilMax * 0.5D;
        GL11.glColor4ub(TFCOptions.cropNutrientBColor[0], TFCOptions.cropNutrientBColor[1], TFCOptions.cropNutrientBColor[2], TFCOptions.cropNutrientBColor[3]);
        drawBox(AxisAlignedBB.func_72330_a(evt.target.field_72311_b + offset, evt.target.field_72312_c + 1.01D - crop + fertilizer - 1.02D, evt.target.field_72309_d, evt.target.field_72311_b + offset + 0.3333D, evt.target.field_72312_c + nutrient - crop + fertilizer - 1.02D, (evt.target.field_72309_d + 1))






            .func_72314_b(0.0020000000949949026D, 0.0020000000949949026D, 0.0020000000949949026D).func_72325_c(-var8, -var10, -var12));

        offset = 0.6666D;
        nutrient = 1.02D + te.nutrients[2] / soilMax * 0.5D;
        GL11.glColor4ub(TFCOptions.cropNutrientCColor[0], TFCOptions.cropNutrientCColor[1], TFCOptions.cropNutrientCColor[2], TFCOptions.cropNutrientCColor[3]);
        drawBox(AxisAlignedBB.func_72330_a(evt.target.field_72311_b + offset, evt.target.field_72312_c + 1.01D - crop + fertilizer - 1.02D, evt.target.field_72309_d, evt.target.field_72311_b + offset + 0.3333D, evt.target.field_72312_c + nutrient - crop + fertilizer - 1.02D, (evt.target.field_72309_d + 1))






            .func_72314_b(0.0020000000949949026D, 0.0020000000949949026D, 0.0020000000949949026D).func_72325_c(-var8, -var10, -var12));

        GL11.glEnable(2884);






        GL11.glColor4f(0.1F, 0.1F, 0.1F, 1.0F);
        GL11.glLineWidth(3.0F);
        GL11.glDepthMask(false);

        offset = 0.0D;

        drawOutlinedBoundingBox(AxisAlignedBB.func_72330_a(evt.target.field_72311_b + offset, evt.target.field_72312_c + 1.01D - crop, evt.target.field_72309_d, (evt.target.field_72311_b + 1), evt.target.field_72312_c + fertilizer - crop, (evt.target.field_72309_d + 1))






            .func_72314_b(0.0020000000949949026D, 0.0020000000949949026D, 0.0020000000949949026D).func_72325_c(-var8, -var10, -var12));

        nutrient = 1.02D + te.nutrients[0] / soilMax * 0.5D;
        drawOutlinedBoundingBox(AxisAlignedBB.func_72330_a(evt.target.field_72311_b + offset, evt.target.field_72312_c + 1.01D - crop + fertilizer - 1.02D, evt.target.field_72309_d, evt.target.field_72311_b + offset + 0.3333D, evt.target.field_72312_c + nutrient - crop + fertilizer - 1.02D, (evt.target.field_72309_d + 1))






            .func_72314_b(0.0020000000949949026D, 0.0020000000949949026D, 0.0020000000949949026D).func_72325_c(-var8, -var10, -var12));

        offset = 0.3333D;
        nutrient = 1.02D + te.nutrients[1] / soilMax * 0.5D;
        drawOutlinedBoundingBox(AxisAlignedBB.func_72330_a(evt.target.field_72311_b + offset, evt.target.field_72312_c + 1.01D - crop + fertilizer - 1.02D, evt.target.field_72309_d, evt.target.field_72311_b + offset + 0.3333D, evt.target.field_72312_c + nutrient - crop + fertilizer - 1.02D, (evt.target.field_72309_d + 1))






            .func_72314_b(0.0020000000949949026D, 0.0020000000949949026D, 0.0020000000949949026D).func_72325_c(-var8, -var10, -var12));

        offset = 0.6666D;
        nutrient = 1.02D + te.nutrients[2] / soilMax * 0.5D;
        drawOutlinedBoundingBox(AxisAlignedBB.func_72330_a(evt.target.field_72311_b + offset, evt.target.field_72312_c + 1.01D - crop + fertilizer - 1.02D, evt.target.field_72309_d, evt.target.field_72311_b + offset + 0.3333D, evt.target.field_72312_c + nutrient - crop + fertilizer - 1.02D, (evt.target.field_72309_d + 1))






            .func_72314_b(0.0020000000949949026D, 0.0020000000949949026D, 0.0020000000949949026D).func_72325_c(-var8, -var10, -var12));
      }

    } else if (evt.currentItem != null && evt.currentItem.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemCustomHoe && hoeMode == 2) {

      Block b = world.func_147439_a(evt.target.field_72311_b, evt.target.field_72312_c, evt.target.field_72309_d);
      int crop = 0;
      if (b == TFCBlocks.crops && (world
        .func_147439_a(evt.target.field_72311_b, evt.target.field_72312_c - 1, evt.target.field_72309_d) == TFCBlocks.tilledSoil || world
        .func_147439_a(evt.target.field_72311_b, evt.target.field_72312_c - 1, evt.target.field_72309_d) == TFCBlocks.tilledSoil2)) {

        b = TFCBlocks.tilledSoil;
        crop = 1;
      }

      if (b == TFCBlocks.tilledSoil || b == TFCBlocks.tilledSoil2)
      {
        boolean water = BlockFarmland.isFreshWaterNearby(world, evt.target.field_72311_b, evt.target.field_72312_c - crop, evt.target.field_72309_d);

        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        if (water) {
          GL11.glColor4ub((byte)14, (byte)23, (byte)-44, (byte)-56);
        } else {
          GL11.glColor4ub((byte)0, (byte)0, (byte)0, (byte)-56);
        }  GL11.glDisable(2884);

        GL11.glDisable(3553);
        GL11.glDepthMask(false);

        drawFace(AxisAlignedBB.func_72330_a(evt.target.field_72311_b, evt.target.field_72312_c + 1.01D - crop, evt.target.field_72309_d, (evt.target.field_72311_b + 1), evt.target.field_72312_c + 1.02D - crop, (evt.target.field_72309_d + 1))






            .func_72314_b(0.0020000000949949026D, 0.0020000000949949026D, 0.0020000000949949026D).func_72325_c(-var8, -var10, -var12));

        GL11.glEnable(2884);
      }

    } else if (evt.currentItem != null && evt.currentItem.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemCustomHoe && hoeMode == 3) {

      Block b = world.func_147439_a(evt.target.field_72311_b, evt.target.field_72312_c, evt.target.field_72309_d);
      if (b == TFCBlocks.crops && (world
        .func_147439_a(evt.target.field_72311_b, evt.target.field_72312_c - 1, evt.target.field_72309_d) == TFCBlocks.tilledSoil || world
        .func_147439_a(evt.target.field_72311_b, evt.target.field_72312_c - 1, evt.target.field_72309_d) == TFCBlocks.tilledSoil2)) {

        TECrop te = (TECrop)world.func_147438_o(evt.target.field_72311_b, evt.target.field_72312_c, evt.target.field_72309_d);
        CropIndex index = CropManager.getInstance().getCropFromId(te.cropId);
        boolean fullyGrown = (index instanceof com.bioxx.tfc.Food.CropIndexPepper) ? ((te.growth >= (index.numGrowthStages - 1))) : ((te.growth >= index.numGrowthStages));

        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        if (fullyGrown) {
          GL11.glColor4ub((byte)64, (byte)-56, (byte)37, (byte)-56);
        } else {
          GL11.glColor4ub((byte)-56, (byte)37, (byte)37, (byte)-56);
        }  GL11.glDisable(2884);

        GL11.glDisable(3553);
        GL11.glDepthMask(false);

        drawFace(AxisAlignedBB.func_72330_a(evt.target.field_72311_b, evt.target.field_72312_c + 0.01D, evt.target.field_72309_d, (evt.target.field_72311_b + 1), evt.target.field_72312_c + 0.02D, (evt.target.field_72309_d + 1))






            .func_72314_b(0.0020000000949949026D, 0.0020000000949949026D, 0.0020000000949949026D).func_72325_c(-var8, -var10, -var12));

        GL11.glEnable(2884);
      }
    }
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


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\Client\FarmlandHighlightHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */