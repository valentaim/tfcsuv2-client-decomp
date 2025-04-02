package com.bioxx.tfc.Core;

import com.bioxx.tfc.api.HeatIndex;
import com.bioxx.tfc.api.HeatRaw;
import com.bioxx.tfc.api.HeatRegistry;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;





public class ItemHeat
{
  public static void setupItemHeat() {
    HeatRegistry manager = HeatRegistry.getInstance();





    int WILDCARD_VALUE = 32767;

    HeatRaw bismuthRaw = new HeatRaw(0.14D, 270.0D);
    HeatRaw bismuthBronzeRaw = new HeatRaw(0.35D, 985.0D);
    HeatRaw blackBronzeRaw = new HeatRaw(0.35D, 1070.0D);
    HeatRaw blackSteelRaw = new HeatRaw(0.35D, 1485.0D);
    HeatRaw blueSteelRaw = new HeatRaw(0.35D, 1540.0D);
    HeatRaw brassRaw = new HeatRaw(0.35D, 930.0D);
    HeatRaw bronzeRaw = new HeatRaw(0.35D, 950.0D);
    HeatRaw copperRaw = new HeatRaw(0.35D, 1080.0D);
    HeatRaw goldRaw = new HeatRaw(0.6D, 1060.0D);
    HeatRaw ironRaw = new HeatRaw(0.35D, 1535.0D);
    HeatRaw leadRaw = new HeatRaw(0.22D, 328.0D);
    HeatRaw nickelRaw = new HeatRaw(0.48D, 1453.0D);
    HeatRaw pigIronRaw = new HeatRaw(0.35D, 1500.0D);
    HeatRaw platinumRaw = new HeatRaw(0.35D, 1730.0D);
    HeatRaw redSteelRaw = new HeatRaw(0.35D, 1540.0D);
    HeatRaw roseGoldRaw = new HeatRaw(0.35D, 960.0D);
    HeatRaw silverRaw = new HeatRaw(0.48D, 961.0D);
    HeatRaw steelRaw = new HeatRaw(0.35D, 1540.0D);
    HeatRaw sterlingSilverRaw = new HeatRaw(0.35D, 900.0D);
    HeatRaw tinRaw = new HeatRaw(0.14D, 230.0D);
    HeatRaw zincRaw = new HeatRaw(0.21D, 420.0D);

    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 0), copperRaw, new ItemStack(TFCItems.copperUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 1), goldRaw, new ItemStack(TFCItems.goldUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 2), platinumRaw, new ItemStack(TFCItems.platinumUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 3), ironRaw, new ItemStack(TFCItems.pigIronUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 4), silverRaw, new ItemStack(TFCItems.silverUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 5), tinRaw, new ItemStack(TFCItems.tinUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 6), leadRaw, new ItemStack(TFCItems.leadUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 7), bismuthRaw, new ItemStack(TFCItems.bismuthUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 8), nickelRaw, new ItemStack(TFCItems.nickelUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 9), copperRaw, new ItemStack(TFCItems.copperUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 10), ironRaw, new ItemStack(TFCItems.pigIronUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 11), ironRaw, new ItemStack(TFCItems.pigIronUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 12), zincRaw, new ItemStack(TFCItems.zincUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 13), copperRaw, new ItemStack(TFCItems.copperUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 35), copperRaw, new ItemStack(TFCItems.copperUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 36), goldRaw, new ItemStack(TFCItems.goldUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 37), platinumRaw, new ItemStack(TFCItems.platinumUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 38), ironRaw, new ItemStack(TFCItems.pigIronUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 39), silverRaw, new ItemStack(TFCItems.silverUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 40), tinRaw, new ItemStack(TFCItems.tinUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 41), leadRaw, new ItemStack(TFCItems.leadUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 42), bismuthRaw, new ItemStack(TFCItems.bismuthUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 43), nickelRaw, new ItemStack(TFCItems.nickelUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 44), copperRaw, new ItemStack(TFCItems.copperUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 45), ironRaw, new ItemStack(TFCItems.pigIronUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 46), ironRaw, new ItemStack(TFCItems.pigIronUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 47), zincRaw, new ItemStack(TFCItems.zincUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 48), copperRaw, new ItemStack(TFCItems.copperUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 49), copperRaw, new ItemStack(TFCItems.copperUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 50), goldRaw, new ItemStack(TFCItems.goldUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 51), platinumRaw, new ItemStack(TFCItems.platinumUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 52), ironRaw, new ItemStack(TFCItems.pigIronUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 53), silverRaw, new ItemStack(TFCItems.silverUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 54), tinRaw, new ItemStack(TFCItems.tinUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 55), leadRaw, new ItemStack(TFCItems.leadUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 56), bismuthRaw, new ItemStack(TFCItems.bismuthUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 57), nickelRaw, new ItemStack(TFCItems.nickelUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 58), copperRaw, new ItemStack(TFCItems.copperUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 59), ironRaw, new ItemStack(TFCItems.pigIronUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 60), ironRaw, new ItemStack(TFCItems.pigIronUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 61), zincRaw, new ItemStack(TFCItems.zincUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oreChunk, 1, 62), copperRaw, new ItemStack(TFCItems.copperUnshaped, 1)));

    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallOreChunk, 1, 0), copperRaw, new ItemStack(TFCItems.copperUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallOreChunk, 1, 1), goldRaw, new ItemStack(TFCItems.goldUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallOreChunk, 1, 2), platinumRaw, new ItemStack(TFCItems.platinumUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallOreChunk, 1, 3), ironRaw, new ItemStack(TFCItems.pigIronUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallOreChunk, 1, 4), silverRaw, new ItemStack(TFCItems.silverUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallOreChunk, 1, 5), tinRaw, new ItemStack(TFCItems.tinUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallOreChunk, 1, 6), leadRaw, new ItemStack(TFCItems.leadUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallOreChunk, 1, 7), bismuthRaw, new ItemStack(TFCItems.bismuthUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallOreChunk, 1, 8), nickelRaw, new ItemStack(TFCItems.nickelUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallOreChunk, 1, 9), copperRaw, new ItemStack(TFCItems.copperUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallOreChunk, 1, 10), ironRaw, new ItemStack(TFCItems.pigIronUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallOreChunk, 1, 11), ironRaw, new ItemStack(TFCItems.pigIronUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallOreChunk, 1, 12), zincRaw, new ItemStack(TFCItems.zincUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.smallOreChunk, 1, 13), copperRaw, new ItemStack(TFCItems.copperUnshaped, 1)));





    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.weakSteelUnshaped, 1), steelRaw, new ItemStack(TFCItems.weakSteelUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.weakRedSteelUnshaped, 1), redSteelRaw, new ItemStack(TFCItems.weakRedSteelUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.weakBlueSteelUnshaped, 1), blueSteelRaw, new ItemStack(TFCItems.weakBlueSteelUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.highCarbonBlackSteelUnshaped, 1), blackSteelRaw, new ItemStack(TFCItems.highCarbonBlackSteelUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.highCarbonBlueSteelUnshaped, 1), blueSteelRaw, new ItemStack(TFCItems.highCarbonBlueSteelUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.highCarbonRedSteelUnshaped, 1), redSteelRaw, new ItemStack(TFCItems.highCarbonRedSteelUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.highCarbonSteelUnshaped, 1), steelRaw, new ItemStack(TFCItems.highCarbonSteelUnshaped, 1)));

    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.weakSteelIngot, 1), steelRaw, new ItemStack(TFCItems.weakSteelUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.weakRedSteelIngot, 1), redSteelRaw, new ItemStack(TFCItems.weakRedSteelUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.weakBlueSteelIngot, 1), blueSteelRaw, new ItemStack(TFCItems.weakBlueSteelUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.highCarbonBlackSteelIngot, 1), blackSteelRaw, new ItemStack(TFCItems.highCarbonBlackSteelUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.highCarbonBlueSteelIngot, 1), blueSteelRaw, new ItemStack(TFCItems.highCarbonBlueSteelUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.highCarbonRedSteelIngot, 1), redSteelRaw, new ItemStack(TFCItems.highCarbonRedSteelUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.highCarbonSteelIngot, 1), steelRaw, new ItemStack(TFCItems.highCarbonSteelUnshaped, 1)));

    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.unknownIngot, 1), copperRaw, new ItemStack(TFCItems.unknownUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.unknownUnshaped, 1), copperRaw, new ItemStack(TFCItems.unknownUnshaped, 1)));


    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthIngot, 1), bismuthRaw, new ItemStack(TFCItems.bismuthUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthIngot2x, 1), bismuthRaw, new ItemStack(TFCItems.bismuthUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthUnshaped, 1), bismuthRaw, new ItemStack(TFCItems.bismuthUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthSheet, 1), bismuthRaw, new ItemStack(TFCItems.bismuthUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthSheet2x, 1), bismuthRaw, new ItemStack(TFCItems.bismuthUnshaped, 4, 0)));

    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthBronzeIngot, 1), bismuthBronzeRaw, new ItemStack(TFCItems.bismuthBronzeUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthBronzeIngot2x, 1), bismuthBronzeRaw, new ItemStack(TFCItems.bismuthBronzeUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthBronzeUnshaped, 1), bismuthBronzeRaw, new ItemStack(TFCItems.bismuthBronzeUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthBronzeSheet, 1), bismuthBronzeRaw, new ItemStack(TFCItems.bismuthBronzeUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthBronzeSheet2x, 1), bismuthBronzeRaw, new ItemStack(TFCItems.bismuthBronzeUnshaped, 4, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthBronzeUnfinishedHelmet, 1, 0), bismuthBronzeRaw, new ItemStack(TFCItems.bismuthBronzeUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthBronzeUnfinishedHelmet, 1, 1), bismuthBronzeRaw, new ItemStack(TFCItems.bismuthBronzeUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthBronzeUnfinishedChestplate, 1, 0), bismuthBronzeRaw, new ItemStack(TFCItems.bismuthBronzeUnshaped, 4, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthBronzeUnfinishedChestplate, 1, 1), bismuthBronzeRaw, new ItemStack(TFCItems.bismuthBronzeUnshaped, 4, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthBronzeUnfinishedGreaves, 1, 0), bismuthBronzeRaw, new ItemStack(TFCItems.bismuthBronzeUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthBronzeUnfinishedGreaves, 1, 1), bismuthBronzeRaw, new ItemStack(TFCItems.bismuthBronzeUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthBronzeUnfinishedBoots, 1, 0), bismuthBronzeRaw, new ItemStack(TFCItems.bismuthBronzeUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bismuthBronzeUnfinishedBoots, 1, 1), bismuthBronzeRaw, new ItemStack(TFCItems.bismuthBronzeUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.anvil2, 1, 1), bismuthBronzeRaw, null));

    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackBronzeIngot, 1), blackBronzeRaw, new ItemStack(TFCItems.blackBronzeUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackBronzeIngot2x, 1), blackBronzeRaw, new ItemStack(TFCItems.blackBronzeUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackBronzeUnshaped, 1), blackBronzeRaw, new ItemStack(TFCItems.blackBronzeUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackBronzeSheet, 1), blackBronzeRaw, new ItemStack(TFCItems.blackBronzeUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackBronzeSheet2x, 1), blackBronzeRaw, new ItemStack(TFCItems.blackBronzeUnshaped, 4, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackBronzeUnfinishedHelmet, 1, 0), blackBronzeRaw, new ItemStack(TFCItems.blackBronzeUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackBronzeUnfinishedHelmet, 1, 1), blackBronzeRaw, new ItemStack(TFCItems.blackBronzeUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackBronzeUnfinishedChestplate, 1, 0), blackBronzeRaw, new ItemStack(TFCItems.blackBronzeUnshaped, 4, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackBronzeUnfinishedChestplate, 1, 1), blackBronzeRaw, new ItemStack(TFCItems.blackBronzeUnshaped, 4, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackBronzeUnfinishedGreaves, 1, 0), blackBronzeRaw, new ItemStack(TFCItems.blackBronzeUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackBronzeUnfinishedGreaves, 1, 1), blackBronzeRaw, new ItemStack(TFCItems.blackBronzeUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackBronzeUnfinishedBoots, 1, 0), blackBronzeRaw, new ItemStack(TFCItems.blackBronzeUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackBronzeUnfinishedBoots, 1, 1), blackBronzeRaw, new ItemStack(TFCItems.blackBronzeUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.anvil2, 1, 2), blackBronzeRaw, null));

    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackSteelIngot, 1), blackSteelRaw, new ItemStack(TFCItems.blackSteelUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackSteelIngot2x, 1), blackSteelRaw, new ItemStack(TFCItems.blackSteelUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackSteelUnshaped, 1), blackSteelRaw, new ItemStack(TFCItems.blackSteelUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackSteelSheet, 1), blackSteelRaw, new ItemStack(TFCItems.blackSteelUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackSteelSheet2x, 1), blackSteelRaw, new ItemStack(TFCItems.blackSteelUnshaped, 4, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackSteelUnfinishedHelmet, 1, 0), blackSteelRaw, new ItemStack(TFCItems.blackSteelUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackSteelUnfinishedHelmet, 1, 1), blackSteelRaw, new ItemStack(TFCItems.blackSteelUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackSteelUnfinishedChestplate, 1, 0), blackSteelRaw, new ItemStack(TFCItems.blackSteelUnshaped, 4, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackSteelUnfinishedChestplate, 1, 1), blackSteelRaw, new ItemStack(TFCItems.blackSteelUnshaped, 4, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackSteelUnfinishedGreaves, 1, 0), blackSteelRaw, new ItemStack(TFCItems.blackSteelUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackSteelUnfinishedGreaves, 1, 1), blackSteelRaw, new ItemStack(TFCItems.blackSteelUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackSteelUnfinishedBoots, 1, 0), blackSteelRaw, new ItemStack(TFCItems.blackSteelUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackSteelUnfinishedBoots, 1, 1), blackSteelRaw, new ItemStack(TFCItems.blackSteelUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.anvil, 1, 5), blackSteelRaw, null));

    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blueSteelIngot, 1), blueSteelRaw, new ItemStack(TFCItems.blueSteelUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blueSteelIngot2x, 1), blueSteelRaw, new ItemStack(TFCItems.blueSteelUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blueSteelUnshaped, 1), blueSteelRaw, new ItemStack(TFCItems.blueSteelUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blueSteelSheet, 1), blueSteelRaw, new ItemStack(TFCItems.blueSteelUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blueSteelSheet2x, 1), blueSteelRaw, new ItemStack(TFCItems.blueSteelUnshaped, 4, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blueSteelUnfinishedHelmet, 1, 0), blueSteelRaw, new ItemStack(TFCItems.blueSteelUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blueSteelUnfinishedHelmet, 1, 1), blueSteelRaw, new ItemStack(TFCItems.blueSteelUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blueSteelUnfinishedChestplate, 1, 0), blueSteelRaw, new ItemStack(TFCItems.blueSteelUnshaped, 4, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blueSteelUnfinishedChestplate, 1, 1), blueSteelRaw, new ItemStack(TFCItems.blueSteelUnshaped, 4, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blueSteelUnfinishedGreaves, 1, 0), blueSteelRaw, new ItemStack(TFCItems.blueSteelUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blueSteelUnfinishedGreaves, 1, 1), blueSteelRaw, new ItemStack(TFCItems.blueSteelUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blueSteelUnfinishedBoots, 1, 0), blueSteelRaw, new ItemStack(TFCItems.blueSteelUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blueSteelUnfinishedBoots, 1, 1), blueSteelRaw, new ItemStack(TFCItems.blueSteelUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.anvil, 1, 6), blueSteelRaw, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.oilLamp, 1, 5), blueSteelRaw, null));

    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.brassIngot, 1), brassRaw, new ItemStack(TFCItems.brassUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.brassIngot2x, 1), brassRaw, new ItemStack(TFCItems.brassUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.brassUnshaped, 1), brassRaw, new ItemStack(TFCItems.brassUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.brassSheet, 1), brassRaw, new ItemStack(TFCItems.brassUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.brassSheet2x, 1), brassRaw, new ItemStack(TFCItems.brassUnshaped, 4, 0)));

    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bronzeIngot, 1), bronzeRaw, new ItemStack(TFCItems.bronzeUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bronzeIngot2x, 1), bronzeRaw, new ItemStack(TFCItems.bronzeUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bronzeUnshaped, 1), bronzeRaw, new ItemStack(TFCItems.bronzeUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bronzeSheet, 1), bronzeRaw, new ItemStack(TFCItems.bronzeUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bronzeSheet2x, 1), bronzeRaw, new ItemStack(TFCItems.bronzeUnshaped, 4, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bronzeUnfinishedHelmet, 1, 0), bronzeRaw, new ItemStack(TFCItems.bronzeUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bronzeUnfinishedHelmet, 1, 1), bronzeRaw, new ItemStack(TFCItems.bronzeUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bronzeUnfinishedChestplate, 1, 0), bronzeRaw, new ItemStack(TFCItems.bronzeUnshaped, 4, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bronzeUnfinishedChestplate, 1, 1), bronzeRaw, new ItemStack(TFCItems.bronzeUnshaped, 4, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bronzeUnfinishedGreaves, 1, 0), bronzeRaw, new ItemStack(TFCItems.bronzeUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bronzeUnfinishedGreaves, 1, 1), bronzeRaw, new ItemStack(TFCItems.bronzeUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bronzeUnfinishedBoots, 1, 0), bronzeRaw, new ItemStack(TFCItems.bronzeUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bronzeUnfinishedBoots, 1, 1), bronzeRaw, new ItemStack(TFCItems.bronzeUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.anvil, 1, 2), bronzeRaw, null));

    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.copperIngot, 1), copperRaw, new ItemStack(TFCItems.copperUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.copperIngot2x, 1), copperRaw, new ItemStack(TFCItems.copperUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.copperUnshaped, 1), copperRaw, new ItemStack(TFCItems.copperUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.copperSheet, 1), copperRaw, new ItemStack(TFCItems.copperUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.copperSheet2x, 1), copperRaw, new ItemStack(TFCItems.copperUnshaped, 4, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.copperUnfinishedHelmet, 1, 0), copperRaw, new ItemStack(TFCItems.copperUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.copperUnfinishedHelmet, 1, 1), copperRaw, new ItemStack(TFCItems.copperUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.copperUnfinishedChestplate, 1, 0), copperRaw, new ItemStack(TFCItems.copperUnshaped, 4, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.copperUnfinishedChestplate, 1, 1), copperRaw, new ItemStack(TFCItems.copperUnshaped, 4, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.copperUnfinishedGreaves, 1, 0), copperRaw, new ItemStack(TFCItems.copperUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.copperUnfinishedGreaves, 1, 1), copperRaw, new ItemStack(TFCItems.copperUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.copperUnfinishedBoots, 1, 0), copperRaw, new ItemStack(TFCItems.copperUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.copperUnfinishedBoots, 1, 1), copperRaw, new ItemStack(TFCItems.copperUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.anvil, 1, 1), copperRaw, null));

    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.goldIngot, 1), goldRaw, new ItemStack(TFCItems.goldUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.goldIngot2x, 1), goldRaw, new ItemStack(TFCItems.goldUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.goldUnshaped, 1), goldRaw, new ItemStack(TFCItems.goldUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.goldSheet, 1), goldRaw, new ItemStack(TFCItems.goldUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.goldSheet2x, 1), goldRaw, new ItemStack(TFCItems.goldUnshaped, 4, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.oilLamp, 1, 0), goldRaw, null));

    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronIngot, 1), ironRaw, new ItemStack(TFCItems.wroughtIronUnshaped, 1)));

    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bloom, 1, 32767), ironRaw, new ItemStack(TFCItems.wroughtIronUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.rawBloom, 1, 32767), ironRaw, new ItemStack(TFCItems.unknownUnshaped, 1)));

    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronIngot2x, 1), ironRaw, new ItemStack(TFCItems.wroughtIronUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronUnshaped, 1), ironRaw, new ItemStack(TFCItems.wroughtIronUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronSheet, 1), ironRaw, new ItemStack(TFCItems.wroughtIronUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronSheet2x, 1), ironRaw, new ItemStack(TFCItems.wroughtIronUnshaped, 4, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronUnfinishedHelmet, 1, 0), ironRaw, new ItemStack(TFCItems.wroughtIronUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronUnfinishedHelmet, 1, 1), ironRaw, new ItemStack(TFCItems.wroughtIronUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronUnfinishedChestplate, 1, 0), ironRaw, new ItemStack(TFCItems.wroughtIronUnshaped, 4, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronUnfinishedChestplate, 1, 1), ironRaw, new ItemStack(TFCItems.wroughtIronUnshaped, 4, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronUnfinishedGreaves, 1, 0), ironRaw, new ItemStack(TFCItems.wroughtIronUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronUnfinishedGreaves, 1, 1), ironRaw, new ItemStack(TFCItems.wroughtIronUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronUnfinishedBoots, 1, 0), ironRaw, new ItemStack(TFCItems.wroughtIronUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronUnfinishedBoots, 1, 1), ironRaw, new ItemStack(TFCItems.wroughtIronUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wroughtIronKnifeHead, 1), ironRaw, new ItemStack(TFCItems.wroughtIronUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.anvil, 1, 3), ironRaw, null));

    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.leadIngot, 1), leadRaw, new ItemStack(TFCItems.leadUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.leadIngot2x, 1), leadRaw, new ItemStack(TFCItems.leadUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.leadUnshaped, 1), leadRaw, new ItemStack(TFCItems.leadUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.leadSheet, 1), leadRaw, new ItemStack(TFCItems.leadUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.leadSheet2x, 1), leadRaw, new ItemStack(TFCItems.leadUnshaped, 4, 0)));

    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.nickelIngot, 1), nickelRaw, new ItemStack(TFCItems.nickelUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.nickelIngot2x, 1), nickelRaw, new ItemStack(TFCItems.nickelUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.nickelUnshaped, 1), nickelRaw, new ItemStack(TFCItems.nickelUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.nickelSheet, 1), nickelRaw, new ItemStack(TFCItems.nickelUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.nickelSheet2x, 1), nickelRaw, new ItemStack(TFCItems.nickelUnshaped, 4, 0)));

    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.pigIronIngot, 1), pigIronRaw, new ItemStack(TFCItems.pigIronUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.pigIronIngot2x, 1), pigIronRaw, new ItemStack(TFCItems.pigIronUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.pigIronUnshaped, 1), pigIronRaw, new ItemStack(TFCItems.pigIronUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.pigIronSheet, 1), pigIronRaw, new ItemStack(TFCItems.pigIronUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.pigIronSheet2x, 1), pigIronRaw, new ItemStack(TFCItems.pigIronUnshaped, 4, 0)));

    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.platinumIngot, 1), platinumRaw, new ItemStack(TFCItems.platinumUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.platinumIngot2x, 1), platinumRaw, new ItemStack(TFCItems.platinumUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.platinumUnshaped, 1), platinumRaw, new ItemStack(TFCItems.platinumUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.platinumSheet, 1), platinumRaw, new ItemStack(TFCItems.platinumUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.platinumSheet2x, 1), platinumRaw, new ItemStack(TFCItems.platinumUnshaped, 4, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.oilLamp, 1, 1), platinumRaw, null));

    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redSteelIngot, 1), redSteelRaw, new ItemStack(TFCItems.redSteelUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redSteelIngot2x, 1), redSteelRaw, new ItemStack(TFCItems.redSteelUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redSteelUnshaped, 1), redSteelRaw, new ItemStack(TFCItems.redSteelUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redSteelSheet, 1), redSteelRaw, new ItemStack(TFCItems.redSteelUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redSteelSheet2x, 1), redSteelRaw, new ItemStack(TFCItems.redSteelUnshaped, 4, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redSteelUnfinishedHelmet, 1, 0), redSteelRaw, new ItemStack(TFCItems.redSteelUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redSteelUnfinishedHelmet, 1, 1), redSteelRaw, new ItemStack(TFCItems.redSteelUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redSteelUnfinishedChestplate, 1, 0), redSteelRaw, new ItemStack(TFCItems.redSteelUnshaped, 4, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redSteelUnfinishedChestplate, 1, 1), redSteelRaw, new ItemStack(TFCItems.redSteelUnshaped, 4, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redSteelUnfinishedGreaves, 1, 0), redSteelRaw, new ItemStack(TFCItems.redSteelUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redSteelUnfinishedGreaves, 1, 1), redSteelRaw, new ItemStack(TFCItems.redSteelUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redSteelUnfinishedBoots, 1, 0), redSteelRaw, new ItemStack(TFCItems.redSteelUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redSteelUnfinishedBoots, 1, 1), redSteelRaw, new ItemStack(TFCItems.redSteelUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.anvil, 1, 7), redSteelRaw, null));

    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.roseGoldIngot, 1), roseGoldRaw, new ItemStack(TFCItems.roseGoldUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.roseGoldIngot2x, 1), roseGoldRaw, new ItemStack(TFCItems.roseGoldUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.roseGoldUnshaped, 1), roseGoldRaw, new ItemStack(TFCItems.roseGoldUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.roseGoldSheet, 1), roseGoldRaw, new ItemStack(TFCItems.roseGoldUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.roseGoldSheet2x, 1), roseGoldRaw, new ItemStack(TFCItems.roseGoldUnshaped, 4, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.oilLamp, 1, 2), roseGoldRaw, null));

    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.silverIngot, 1), silverRaw, new ItemStack(TFCItems.silverUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.silverIngot2x, 1), silverRaw, new ItemStack(TFCItems.silverUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.silverUnshaped, 1), silverRaw, new ItemStack(TFCItems.silverUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.silverSheet, 1), silverRaw, new ItemStack(TFCItems.silverUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.silverSheet2x, 1), silverRaw, new ItemStack(TFCItems.silverUnshaped, 4, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.oilLamp, 1, 3), silverRaw, null));

    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.steelIngot, 1), steelRaw, new ItemStack(TFCItems.steelUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.steelIngot2x, 1), steelRaw, new ItemStack(TFCItems.steelUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.steelUnshaped, 1), steelRaw, new ItemStack(TFCItems.steelUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.steelSheet, 1), steelRaw, new ItemStack(TFCItems.steelUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.steelSheet2x, 1), steelRaw, new ItemStack(TFCItems.steelUnshaped, 4, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.steelUnfinishedHelmet, 1, 0), steelRaw, new ItemStack(TFCItems.steelUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.steelUnfinishedHelmet, 1, 1), steelRaw, new ItemStack(TFCItems.steelUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.steelUnfinishedChestplate, 1, 0), steelRaw, new ItemStack(TFCItems.steelUnshaped, 4, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.steelUnfinishedChestplate, 1, 1), steelRaw, new ItemStack(TFCItems.steelUnshaped, 4, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.steelUnfinishedGreaves, 1, 0), steelRaw, new ItemStack(TFCItems.steelUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.steelUnfinishedGreaves, 1, 1), steelRaw, new ItemStack(TFCItems.steelUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.steelUnfinishedBoots, 1, 0), steelRaw, new ItemStack(TFCItems.steelUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.steelUnfinishedBoots, 1, 1), steelRaw, new ItemStack(TFCItems.steelUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.anvil, 1, 4), steelRaw, null));

    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.sterlingSilverIngot, 1), sterlingSilverRaw, new ItemStack(TFCItems.sterlingSilverUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.sterlingSilverIngot2x, 1), sterlingSilverRaw, new ItemStack(TFCItems.sterlingSilverUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.sterlingSilverUnshaped, 1), sterlingSilverRaw, new ItemStack(TFCItems.sterlingSilverUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.sterlingSilverSheet, 1), sterlingSilverRaw, new ItemStack(TFCItems.sterlingSilverUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.sterlingSilverSheet2x, 1), sterlingSilverRaw, new ItemStack(TFCItems.sterlingSilverUnshaped, 4, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.oilLamp, 1, 4), sterlingSilverRaw, null));

    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.tinIngot, 1), tinRaw, new ItemStack(TFCItems.tinUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.tinIngot2x, 1), tinRaw, new ItemStack(TFCItems.tinUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.tinUnshaped, 1), tinRaw, new ItemStack(TFCItems.tinUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.tinSheet, 1), tinRaw, new ItemStack(TFCItems.tinUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.tinSheet2x, 1), tinRaw, new ItemStack(TFCItems.tinUnshaped, 4, 0)));

    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.zincIngot, 1), zincRaw, new ItemStack(TFCItems.zincUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.zincIngot2x, 1), zincRaw, new ItemStack(TFCItems.zincUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.zincUnshaped, 1), zincRaw, new ItemStack(TFCItems.zincUnshaped, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.zincSheet, 1), zincRaw, new ItemStack(TFCItems.zincUnshaped, 2, 0)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.zincSheet2x, 1), zincRaw, new ItemStack(TFCItems.zincUnshaped, 4, 0)));

    manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.sand, 1, 32767), 1.0D, 600.0D, new ItemStack(Blocks.field_150359_w, 1)));
    manager.addIndex(new HeatIndex(new ItemStack(TFCBlocks.sand2, 1, 32767), 1.0D, 600.0D, new ItemStack(Blocks.field_150359_w, 1)));



    manager.addIndex((new HeatIndex(new ItemStack(TFCItems.egg, 1), 1.0D, 600.0D, new ItemStack(TFCItems.eggCooked, 1))).setKeepNBT(true));

    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.porkchopRaw, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.fishRaw, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.beefRaw, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.chickenRaw, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.soybean, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.eggCooked, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.calamariRaw, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.muttonRaw, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.venisonRaw, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.horseMeatRaw, 1), 1.0D, 1200.0D, null));


    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.cheese, 1), 1.0D, 1200.0D, null));


    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.maizeEar, 1), 1.0D, 1200.0D, null));

    manager.addIndex((new HeatIndex(new ItemStack(TFCItems.wheatDough, 1), 1.0D, 600.0D, new ItemStack(TFCItems.wheatBread, 1))).setKeepNBT(true));
    manager.addIndex((new HeatIndex(new ItemStack(TFCItems.barleyDough, 1), 1.0D, 600.0D, new ItemStack(TFCItems.barleyBread, 1))).setKeepNBT(true));
    manager.addIndex((new HeatIndex(new ItemStack(TFCItems.oatDough, 1), 1.0D, 600.0D, new ItemStack(TFCItems.oatBread, 1))).setKeepNBT(true));
    manager.addIndex((new HeatIndex(new ItemStack(TFCItems.ryeDough, 1), 1.0D, 600.0D, new ItemStack(TFCItems.ryeBread, 1))).setKeepNBT(true));
    manager.addIndex((new HeatIndex(new ItemStack(TFCItems.riceDough, 1), 1.0D, 600.0D, new ItemStack(TFCItems.riceBread, 1))).setKeepNBT(true));
    manager.addIndex((new HeatIndex(new ItemStack(TFCItems.cornmealDough, 1), 1.0D, 600.0D, new ItemStack(TFCItems.cornBread, 1))).setKeepNBT(true));

    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wheatBread, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.barleyBread, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.oatBread, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.ryeBread, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.riceBread, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.cornBread, 1), 1.0D, 1200.0D, null));


    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.tomato, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.potato, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.onion, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.cabbage, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.garlic, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.carrot, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.greenbeans, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.greenBellPepper, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.yellowBellPepper, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redBellPepper, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.squash, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.seaWeed, 1), 1.0D, 1200.0D, null));


    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.redApple, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.banana, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.orange, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.greenApple, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.lemon, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.olive, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.cherry, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.peach, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.plum, 1), 1.0D, 1200.0D, null));

    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.wintergreenBerry, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blueberry, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.raspberry, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.strawberry, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.blackberry, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.bunchberry, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.cranberry, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.snowberry, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.elderberry, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.gooseberry, 1), 1.0D, 1200.0D, null));
    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.cloudberry, 1), 1.0D, 1200.0D, null));




    manager.addIndex(new HeatIndex(new ItemStack(TFCItems.stick, 1, 32767), 1.0D, 40.0D, new ItemStack(TFCBlocks.torch, 2)));
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\ItemHeat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */