package com.bioxx.tfc.Core;

import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;


public class TFC_OreDictionary
{
  public static void registerOreDict() {
    int WILD = 32767;

    
    OreDictionary.registerOre("logWood", new ItemStack(TFCItems.logs, 1, 32767));
    OreDictionary.registerOre("logWood", new ItemStack(TFCBlocks.logNatural, 1, 32767));
    OreDictionary.registerOre("logWood", new ItemStack(TFCBlocks.logNatural2, 1, 32767));
    OreDictionary.registerOre("logWood", new ItemStack(TFCBlocks.woodHoriz, 1, 32767));
    OreDictionary.registerOre("logWood", new ItemStack(TFCBlocks.woodHoriz2, 1, 32767));
    OreDictionary.registerOre("logWood", new ItemStack(TFCBlocks.woodHoriz3, 1, 32767));
    OreDictionary.registerOre("logWood", new ItemStack(TFCBlocks.woodHoriz4, 1, 32767));
    OreDictionary.registerOre("logWood", new ItemStack(TFCBlocks.woodVert, 1, 32767));
    OreDictionary.registerOre("logWood", new ItemStack(TFCBlocks.woodVert2, 1, 32767));
    
    OreDictionary.registerOre("plankWood", new ItemStack(TFCBlocks.planks, 1, 32767));
    OreDictionary.registerOre("plankWood", new ItemStack(TFCBlocks.planks2, 1, 32767));
    
    OreDictionary.registerOre("woodLumber", new ItemStack(TFCItems.singlePlank, 1, 32767));
    
    OreDictionary.registerOre("stickWood", new ItemStack(TFCItems.stick));
    
    OreDictionary.registerOre("treeSapling", new ItemStack(TFCBlocks.sapling, 1, 32767));
    OreDictionary.registerOre("treeSapling", new ItemStack(TFCBlocks.sapling2, 1, 32767));
    
    OreDictionary.registerOre("treeLeaves", new ItemStack(TFCBlocks.leaves, 1, 32767));
    OreDictionary.registerOre("treeLeaves", new ItemStack(TFCBlocks.leaves2, 1, 32767));

    
    OreDictionary.registerOre("chestWood", new ItemStack(TFCBlocks.chest, 1, 32767));
    OreDictionary.registerOre("barrelWood", new ItemStack(TFCBlocks.barrel, 1, 32767));

    
    OreDictionary.registerOre("oreNormalCopper", new ItemStack(TFCItems.oreChunk, 1, 0));
    OreDictionary.registerOre("oreNormalCopper", new ItemStack(TFCItems.oreChunk, 1, 9));
    OreDictionary.registerOre("oreNormalCopper", new ItemStack(TFCItems.oreChunk, 1, 13));
    OreDictionary.registerOre("oreSmallCopper", new ItemStack(TFCItems.smallOreChunk, 1, 0));
    OreDictionary.registerOre("oreSmallCopper", new ItemStack(TFCItems.smallOreChunk, 1, 9));
    OreDictionary.registerOre("oreSmallCopper", new ItemStack(TFCItems.smallOreChunk, 1, 13));
    OreDictionary.registerOre("oreRichCopper", new ItemStack(TFCItems.oreChunk, 1, 35));
    OreDictionary.registerOre("oreRichCopper", new ItemStack(TFCItems.oreChunk, 1, 44));
    OreDictionary.registerOre("oreRichCopper", new ItemStack(TFCItems.oreChunk, 1, 48));
    OreDictionary.registerOre("orePoorCopper", new ItemStack(TFCItems.oreChunk, 1, 49));
    OreDictionary.registerOre("orePoorCopper", new ItemStack(TFCItems.oreChunk, 1, 58));
    OreDictionary.registerOre("orePoorCopper", new ItemStack(TFCItems.oreChunk, 1, 62));
    
    OreDictionary.registerOre("oreNormalGold", new ItemStack(TFCItems.oreChunk, 1, 1));
    OreDictionary.registerOre("oreSmallGold", new ItemStack(TFCItems.smallOreChunk, 1, 1));
    OreDictionary.registerOre("oreRichGold", new ItemStack(TFCItems.oreChunk, 1, 36));
    OreDictionary.registerOre("orePoorGold", new ItemStack(TFCItems.oreChunk, 1, 50));
    
    OreDictionary.registerOre("oreNormalPlatinum", new ItemStack(TFCItems.oreChunk, 1, 2));
    OreDictionary.registerOre("oreSmallPlatinum", new ItemStack(TFCItems.smallOreChunk, 1, 2));
    OreDictionary.registerOre("oreRichPlatinum", new ItemStack(TFCItems.oreChunk, 1, 37));
    OreDictionary.registerOre("orePoorPlatinum", new ItemStack(TFCItems.oreChunk, 1, 51));
    
    OreDictionary.registerOre("oreNormalIron", new ItemStack(TFCItems.oreChunk, 1, 3));
    OreDictionary.registerOre("oreNormalIron", new ItemStack(TFCItems.oreChunk, 1, 10));
    OreDictionary.registerOre("oreNormalIron", new ItemStack(TFCItems.oreChunk, 1, 11));
    OreDictionary.registerOre("oreSmallIron", new ItemStack(TFCItems.smallOreChunk, 1, 3));
    OreDictionary.registerOre("oreSmallIron", new ItemStack(TFCItems.smallOreChunk, 1, 10));
    OreDictionary.registerOre("oreSmallIron", new ItemStack(TFCItems.smallOreChunk, 1, 11));
    OreDictionary.registerOre("oreRichIron", new ItemStack(TFCItems.oreChunk, 1, 38));
    OreDictionary.registerOre("oreRichIron", new ItemStack(TFCItems.oreChunk, 1, 45));
    OreDictionary.registerOre("oreRichIron", new ItemStack(TFCItems.oreChunk, 1, 46));
    OreDictionary.registerOre("orePoorIron", new ItemStack(TFCItems.oreChunk, 1, 52));
    OreDictionary.registerOre("orePoorIron", new ItemStack(TFCItems.oreChunk, 1, 59));
    OreDictionary.registerOre("orePoorIron", new ItemStack(TFCItems.oreChunk, 1, 60));
    
    OreDictionary.registerOre("oreNormalSilver", new ItemStack(TFCItems.oreChunk, 1, 4));
    OreDictionary.registerOre("oreSmallSilver", new ItemStack(TFCItems.smallOreChunk, 1, 4));
    OreDictionary.registerOre("oreRichSilver", new ItemStack(TFCItems.oreChunk, 1, 39));
    OreDictionary.registerOre("orePoorSilver", new ItemStack(TFCItems.oreChunk, 1, 53));
    
    OreDictionary.registerOre("oreNormalTin", new ItemStack(TFCItems.oreChunk, 1, 5));
    OreDictionary.registerOre("oreSmallTin", new ItemStack(TFCItems.smallOreChunk, 1, 5));
    OreDictionary.registerOre("oreRichTin", new ItemStack(TFCItems.oreChunk, 1, 40));
    OreDictionary.registerOre("orePoorTin", new ItemStack(TFCItems.oreChunk, 1, 54));
    
    OreDictionary.registerOre("oreNormalLead", new ItemStack(TFCItems.oreChunk, 1, 6));
    OreDictionary.registerOre("oreSmallLead", new ItemStack(TFCItems.smallOreChunk, 1, 6));
    OreDictionary.registerOre("oreRichLead", new ItemStack(TFCItems.oreChunk, 1, 41));
    OreDictionary.registerOre("orePoorLead", new ItemStack(TFCItems.oreChunk, 1, 55));
    
    OreDictionary.registerOre("oreNormalBismuth", new ItemStack(TFCItems.oreChunk, 1, 7));
    OreDictionary.registerOre("oreSmallBismuth", new ItemStack(TFCItems.smallOreChunk, 1, 7));
    OreDictionary.registerOre("oreRichBismuth", new ItemStack(TFCItems.oreChunk, 1, 42));
    OreDictionary.registerOre("orePoorBismuth", new ItemStack(TFCItems.oreChunk, 1, 56));
    
    OreDictionary.registerOre("oreNormalNickel", new ItemStack(TFCItems.oreChunk, 1, 8));
    OreDictionary.registerOre("oreSmallNickel", new ItemStack(TFCItems.smallOreChunk, 1, 8));
    OreDictionary.registerOre("oreRichNickel", new ItemStack(TFCItems.oreChunk, 1, 43));
    OreDictionary.registerOre("orePoorNickel", new ItemStack(TFCItems.oreChunk, 1, 57));
    
    OreDictionary.registerOre("oreNormalZinc", new ItemStack(TFCItems.oreChunk, 1, 12));
    OreDictionary.registerOre("oreSmallZinc", new ItemStack(TFCItems.smallOreChunk, 1, 12));
    OreDictionary.registerOre("oreRichZinc", new ItemStack(TFCItems.oreChunk, 1, 47));
    OreDictionary.registerOre("orePoorZinc", new ItemStack(TFCItems.oreChunk, 1, 61));
    
    OreDictionary.registerOre("oreCoal", new ItemStack(TFCItems.oreChunk, 1, 14));
    OreDictionary.registerOre("oreCoal", new ItemStack(TFCItems.oreChunk, 1, 15));
    
    OreDictionary.registerOre("oreKaolinite", new ItemStack(TFCItems.oreChunk, 1, 16));
    OreDictionary.registerOre("oreGypsum", new ItemStack(TFCItems.oreChunk, 1, 17));
    OreDictionary.registerOre("oreSatinspar", new ItemStack(TFCItems.oreChunk, 1, 18));
    OreDictionary.registerOre("oreSelenite", new ItemStack(TFCItems.oreChunk, 1, 19));
    OreDictionary.registerOre("oreGraphite", new ItemStack(TFCItems.oreChunk, 1, 20));
    OreDictionary.registerOre("oreDiamond", new ItemStack(TFCItems.oreChunk, 1, 21));
    OreDictionary.registerOre("orePetrifiedWood", new ItemStack(TFCItems.oreChunk, 1, 22));
    OreDictionary.registerOre("oreSulfur", new ItemStack(TFCItems.oreChunk, 1, 23));
    OreDictionary.registerOre("oreJet", new ItemStack(TFCItems.oreChunk, 1, 24));
    OreDictionary.registerOre("oreMicrocline", new ItemStack(TFCItems.oreChunk, 1, 25));
    OreDictionary.registerOre("oreUranium", new ItemStack(TFCItems.oreChunk, 1, 26));
    
    OreDictionary.registerOre("oreRedstone", new ItemStack(TFCItems.oreChunk, 1, 27));
    OreDictionary.registerOre("oreRedstone", new ItemStack(TFCItems.oreChunk, 1, 28));
    
    OreDictionary.registerOre("oreSaltpeter", new ItemStack(TFCItems.oreChunk, 1, 29));
    OreDictionary.registerOre("oreSerpentine", new ItemStack(TFCItems.oreChunk, 1, 30));
    OreDictionary.registerOre("oreSylvite", new ItemStack(TFCItems.oreChunk, 1, 31));
    OreDictionary.registerOre("oreBorax", new ItemStack(TFCItems.oreChunk, 1, 32));
    OreDictionary.registerOre("oreOlivine", new ItemStack(TFCItems.oreChunk, 1, 33));
    OreDictionary.registerOre("oreLapis", new ItemStack(TFCItems.oreChunk, 1, 34));

    
    OreDictionary.registerOre("dustFlux", new ItemStack(TFCItems.powder, 1, 0));
    OreDictionary.registerOre("dustKaolinite", new ItemStack(TFCItems.powder, 1, 1));
    OreDictionary.registerOre("dustGraphite", new ItemStack(TFCItems.powder, 1, 2));
    OreDictionary.registerOre("dustSulfur", new ItemStack(TFCItems.powder, 1, 3));
    OreDictionary.registerOre("dustSaltpeter", new ItemStack(TFCItems.powder, 1, 4));
    OreDictionary.registerOre("dustLapis", new ItemStack(TFCItems.powder, 1, 6));
    OreDictionary.registerOre("dustSalt", new ItemStack(TFCItems.powder, 1, 9));

    
    OreDictionary.registerOre("ingotBismuth", new ItemStack(TFCItems.bismuthIngot));
    OreDictionary.registerOre("ingotTin", new ItemStack(TFCItems.tinIngot));
    OreDictionary.registerOre("ingotZinc", new ItemStack(TFCItems.zincIngot));
    OreDictionary.registerOre("ingotCopper", new ItemStack(TFCItems.copperIngot));
    OreDictionary.registerOre("ingotBronze", new ItemStack(TFCItems.bronzeIngot));
    OreDictionary.registerOre("ingotBismuthBronze", new ItemStack(TFCItems.bismuthBronzeIngot));
    OreDictionary.registerOre("ingotBlackBronze", new ItemStack(TFCItems.blackBronzeIngot));
    OreDictionary.registerOre("ingotBrass", new ItemStack(TFCItems.brassIngot));
    OreDictionary.registerOre("ingotLead", new ItemStack(TFCItems.leadIngot));
    OreDictionary.registerOre("ingotGold", new ItemStack(TFCItems.goldIngot));
    OreDictionary.registerOre("ingotRoseGold", new ItemStack(TFCItems.roseGoldIngot));
    OreDictionary.registerOre("ingotSilver", new ItemStack(TFCItems.silverIngot));
    OreDictionary.registerOre("ingotSterlingSilver", new ItemStack(TFCItems.sterlingSilverIngot));
    OreDictionary.registerOre("ingotPlatinum", new ItemStack(TFCItems.platinumIngot));
    OreDictionary.registerOre("ingotWroughtIron", new ItemStack(TFCItems.wroughtIronIngot));
    OreDictionary.registerOre("ingotIron", new ItemStack(TFCItems.wroughtIronIngot));
    OreDictionary.registerOre("ingotNickel", new ItemStack(TFCItems.nickelIngot));
    OreDictionary.registerOre("ingotPigIron", new ItemStack(TFCItems.pigIronIngot));
    OreDictionary.registerOre("ingotSteel", new ItemStack(TFCItems.steelIngot));
    OreDictionary.registerOre("ingotBlackSteel", new ItemStack(TFCItems.blackSteelIngot));
    OreDictionary.registerOre("ingotRedSteel", new ItemStack(TFCItems.redSteelIngot));
    OreDictionary.registerOre("ingotBlueSteel", new ItemStack(TFCItems.blueSteelIngot));
    OreDictionary.registerOre("ingotUnknown", new ItemStack(TFCItems.unknownIngot));
    
    OreDictionary.registerOre("ingotAnyBronze", new ItemStack(TFCItems.bronzeIngot));
    OreDictionary.registerOre("ingotAnyBronze", new ItemStack(TFCItems.bismuthBronzeIngot));
    OreDictionary.registerOre("ingotAnyBronze", new ItemStack(TFCItems.blackBronzeIngot));

    
    OreDictionary.registerOre("ingotDoubleBismuth", new ItemStack(TFCItems.bismuthIngot2x));
    OreDictionary.registerOre("ingotDoubleTin", new ItemStack(TFCItems.tinIngot2x));
    OreDictionary.registerOre("ingotDoubleZinc", new ItemStack(TFCItems.zincIngot2x));
    OreDictionary.registerOre("ingotDoubleCopper", new ItemStack(TFCItems.copperIngot2x));
    OreDictionary.registerOre("ingotDoubleBronze", new ItemStack(TFCItems.bronzeIngot2x));
    OreDictionary.registerOre("ingotDoubleBismuthBronze", new ItemStack(TFCItems.bismuthBronzeIngot2x));
    OreDictionary.registerOre("ingotDoubleBlackBronze", new ItemStack(TFCItems.blackBronzeIngot2x));
    OreDictionary.registerOre("ingotDoubleBrass", new ItemStack(TFCItems.brassIngot2x));
    OreDictionary.registerOre("ingotDoubleLead", new ItemStack(TFCItems.leadIngot2x));
    OreDictionary.registerOre("ingotDoubleGold", new ItemStack(TFCItems.goldIngot2x));
    OreDictionary.registerOre("ingotDoubleRoseGold", new ItemStack(TFCItems.roseGoldIngot2x));
    OreDictionary.registerOre("ingotDoubleSilver", new ItemStack(TFCItems.silverIngot2x));
    OreDictionary.registerOre("ingotDoubleSterlingSilver", new ItemStack(TFCItems.sterlingSilverIngot2x));
    OreDictionary.registerOre("ingotDoublePlatinum", new ItemStack(TFCItems.platinumIngot2x));
    OreDictionary.registerOre("ingotDoubleWroughtIron", new ItemStack(TFCItems.wroughtIronIngot2x));
    OreDictionary.registerOre("ingotDoubleNickel", new ItemStack(TFCItems.nickelIngot2x));
    OreDictionary.registerOre("ingotDoublePigIron", new ItemStack(TFCItems.pigIronIngot2x));
    OreDictionary.registerOre("ingotDoubleSteel", new ItemStack(TFCItems.steelIngot2x));
    OreDictionary.registerOre("ingotDoubleBlackSteel", new ItemStack(TFCItems.blackSteelIngot2x));
    OreDictionary.registerOre("ingotDoubleRedSteel", new ItemStack(TFCItems.redSteelIngot2x));
    OreDictionary.registerOre("ingotDoubleBlueSteel", new ItemStack(TFCItems.blueSteelIngot2x));
    
    OreDictionary.registerOre("ingotDoubleAnyBronze", new ItemStack(TFCItems.bronzeIngot2x));
    OreDictionary.registerOre("ingotDoubleAnyBronze", new ItemStack(TFCItems.bismuthBronzeIngot2x));
    OreDictionary.registerOre("ingotDoubleAnyBronze", new ItemStack(TFCItems.blackBronzeIngot2x));

    
    OreDictionary.registerOre("plateBismuth", new ItemStack(TFCItems.bismuthSheet));
    OreDictionary.registerOre("plateTin", new ItemStack(TFCItems.tinSheet));
    OreDictionary.registerOre("plateZinc", new ItemStack(TFCItems.zincSheet));
    OreDictionary.registerOre("plateCopper", new ItemStack(TFCItems.copperSheet));
    OreDictionary.registerOre("plateBronze", new ItemStack(TFCItems.bronzeSheet));
    OreDictionary.registerOre("plateBismuthBronze", new ItemStack(TFCItems.bismuthBronzeSheet));
    OreDictionary.registerOre("plateBlackBronze", new ItemStack(TFCItems.blackBronzeSheet));
    OreDictionary.registerOre("plateBrass", new ItemStack(TFCItems.brassSheet));
    OreDictionary.registerOre("plateLead", new ItemStack(TFCItems.leadSheet));
    OreDictionary.registerOre("plateGold", new ItemStack(TFCItems.goldSheet));
    OreDictionary.registerOre("plateRoseGold", new ItemStack(TFCItems.roseGoldSheet));
    OreDictionary.registerOre("plateSilver", new ItemStack(TFCItems.silverSheet));
    OreDictionary.registerOre("plateSterlingSilver", new ItemStack(TFCItems.sterlingSilverSheet));
    OreDictionary.registerOre("platePlatinum", new ItemStack(TFCItems.platinumSheet));
    OreDictionary.registerOre("plateWroughtIron", new ItemStack(TFCItems.wroughtIronSheet));
    OreDictionary.registerOre("plateIron", new ItemStack(TFCItems.wroughtIronSheet));
    OreDictionary.registerOre("plateNickel", new ItemStack(TFCItems.nickelSheet));
    OreDictionary.registerOre("platePigIron", new ItemStack(TFCItems.pigIronSheet));
    OreDictionary.registerOre("plateSteel", new ItemStack(TFCItems.steelSheet));
    OreDictionary.registerOre("plateBlackSteel", new ItemStack(TFCItems.blackSteelSheet));
    OreDictionary.registerOre("plateRedSteel", new ItemStack(TFCItems.redSteelSheet));
    OreDictionary.registerOre("plateBlueSteel", new ItemStack(TFCItems.blueSteelSheet));
    
    OreDictionary.registerOre("plateAnyBronze", new ItemStack(TFCItems.bronzeSheet));
    OreDictionary.registerOre("plateAnyBronze", new ItemStack(TFCItems.bismuthBronzeSheet));
    OreDictionary.registerOre("plateAnyBronze", new ItemStack(TFCItems.blackBronzeSheet));

    
    OreDictionary.registerOre("plateDoubleBismuth", new ItemStack(TFCItems.bismuthSheet2x));
    OreDictionary.registerOre("plateDoubleTin", new ItemStack(TFCItems.tinSheet2x));
    OreDictionary.registerOre("plateDoubleZinc", new ItemStack(TFCItems.zincSheet2x));
    OreDictionary.registerOre("plateDoubleCopper", new ItemStack(TFCItems.copperSheet2x));
    OreDictionary.registerOre("plateDoubleBronze", new ItemStack(TFCItems.bronzeSheet2x));
    OreDictionary.registerOre("plateDoubleBismuthBronze", new ItemStack(TFCItems.bismuthBronzeSheet2x));
    OreDictionary.registerOre("plateDoubleBlackBronze", new ItemStack(TFCItems.blackBronzeSheet2x));
    OreDictionary.registerOre("plateDoubleBrass", new ItemStack(TFCItems.brassSheet2x));
    OreDictionary.registerOre("plateDoubleLead", new ItemStack(TFCItems.leadSheet2x));
    OreDictionary.registerOre("plateDoubleGold", new ItemStack(TFCItems.goldSheet2x));
    OreDictionary.registerOre("plateDoubleRoseGold", new ItemStack(TFCItems.roseGoldSheet2x));
    OreDictionary.registerOre("plateDoubleSilver", new ItemStack(TFCItems.silverSheet2x));
    OreDictionary.registerOre("plateDoubleSterlingSilver", new ItemStack(TFCItems.sterlingSilverSheet2x));
    OreDictionary.registerOre("plateDoublePlatinum", new ItemStack(TFCItems.platinumSheet2x));
    OreDictionary.registerOre("plateDoubleWroughtIron", new ItemStack(TFCItems.wroughtIronSheet2x));
    OreDictionary.registerOre("plateDoubleNickel", new ItemStack(TFCItems.nickelSheet2x));
    OreDictionary.registerOre("plateDoublePigIron", new ItemStack(TFCItems.pigIronSheet2x));
    OreDictionary.registerOre("plateDoubleSteel", new ItemStack(TFCItems.steelSheet2x));
    OreDictionary.registerOre("plateDoubleBlackSteel", new ItemStack(TFCItems.blackSteelSheet2x));
    OreDictionary.registerOre("plateDoubleRedSteel", new ItemStack(TFCItems.redSteelSheet2x));
    OreDictionary.registerOre("plateDoubleBlueSteel", new ItemStack(TFCItems.blueSteelSheet2x));
    
    OreDictionary.registerOre("plateDoubleAnyBronze", new ItemStack(TFCItems.bronzeSheet2x));
    OreDictionary.registerOre("plateDoubleAnyBronze", new ItemStack(TFCItems.bismuthBronzeSheet2x));
    OreDictionary.registerOre("plateDoubleAnyBronze", new ItemStack(TFCItems.blackBronzeSheet2x));

    
    OreDictionary.registerOre("gemChippedAgate", new ItemStack(TFCItems.gemAgate));
    OreDictionary.registerOre("gemChippedAmethyst", new ItemStack(TFCItems.gemAmethyst));
    OreDictionary.registerOre("gemChippedBeryl", new ItemStack(TFCItems.gemBeryl));
    OreDictionary.registerOre("gemChippedDiamond", new ItemStack(TFCItems.gemDiamond));
    OreDictionary.registerOre("gemChippedEmerald", new ItemStack(TFCItems.gemEmerald));
    OreDictionary.registerOre("gemChippedGarnet", new ItemStack(TFCItems.gemGarnet));
    OreDictionary.registerOre("gemChippedJade", new ItemStack(TFCItems.gemJade));
    OreDictionary.registerOre("gemChippedJasper", new ItemStack(TFCItems.gemJasper));
    OreDictionary.registerOre("gemChippedOpal", new ItemStack(TFCItems.gemOpal));
    OreDictionary.registerOre("gemChippedRuby", new ItemStack(TFCItems.gemRuby));
    OreDictionary.registerOre("gemChippedSapphire", new ItemStack(TFCItems.gemSapphire));
    OreDictionary.registerOre("gemChippedTopaz", new ItemStack(TFCItems.gemTopaz));
    OreDictionary.registerOre("gemChippedTourmaline", new ItemStack(TFCItems.gemTourmaline));
    
    OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.gemAgate));
    OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.gemAmethyst));
    OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.gemBeryl));
    OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.gemDiamond));
    OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.gemEmerald));
    OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.gemGarnet));
    OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.gemJade));
    OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.gemJasper));
    OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.gemOpal));
    OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.gemRuby));
    OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.gemSapphire));
    OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.gemTopaz));
    OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.gemTourmaline));
    
    OreDictionary.registerOre("gemFlawedAgate", new ItemStack(TFCItems.gemAgate, 1, 1));
    OreDictionary.registerOre("gemFlawedAmethyst", new ItemStack(TFCItems.gemAmethyst, 1, 1));
    OreDictionary.registerOre("gemFlawedBeryl", new ItemStack(TFCItems.gemBeryl, 1, 1));
    OreDictionary.registerOre("gemFlawedDiamond", new ItemStack(TFCItems.gemDiamond, 1, 1));
    OreDictionary.registerOre("gemFlawedEmerald", new ItemStack(TFCItems.gemEmerald, 1, 1));
    OreDictionary.registerOre("gemFlawedGarnet", new ItemStack(TFCItems.gemGarnet, 1, 1));
    OreDictionary.registerOre("gemFlawedJade", new ItemStack(TFCItems.gemJade, 1, 1));
    OreDictionary.registerOre("gemFlawedJasper", new ItemStack(TFCItems.gemJasper, 1, 1));
    OreDictionary.registerOre("gemFlawedOpal", new ItemStack(TFCItems.gemOpal, 1, 1));
    OreDictionary.registerOre("gemFlawedRuby", new ItemStack(TFCItems.gemRuby, 1, 1));
    OreDictionary.registerOre("gemFlawedSapphire", new ItemStack(TFCItems.gemSapphire, 1, 1));
    OreDictionary.registerOre("gemFlawedTopaz", new ItemStack(TFCItems.gemTopaz, 1, 1));
    OreDictionary.registerOre("gemFlawedTourmaline", new ItemStack(TFCItems.gemTourmaline, 1, 1));
    
    OreDictionary.registerOre("gemAgate", new ItemStack(TFCItems.gemAgate, 1, 2));
    OreDictionary.registerOre("gemAmethyst", new ItemStack(TFCItems.gemAmethyst, 1, 2));
    OreDictionary.registerOre("gemBeryl", new ItemStack(TFCItems.gemBeryl, 1, 2));
    OreDictionary.registerOre("gemDiamond", new ItemStack(TFCItems.gemDiamond, 1, 2));
    OreDictionary.registerOre("gemEmerald", new ItemStack(TFCItems.gemEmerald, 1, 2));
    OreDictionary.registerOre("gemGarnet", new ItemStack(TFCItems.gemGarnet, 1, 2));
    OreDictionary.registerOre("gemJade", new ItemStack(TFCItems.gemJade, 1, 2));
    OreDictionary.registerOre("gemJasper", new ItemStack(TFCItems.gemJasper, 1, 2));
    OreDictionary.registerOre("gemOpal", new ItemStack(TFCItems.gemOpal, 1, 2));
    OreDictionary.registerOre("gemRuby", new ItemStack(TFCItems.gemRuby, 1, 2));
    OreDictionary.registerOre("gemSapphire", new ItemStack(TFCItems.gemSapphire, 1, 2));
    OreDictionary.registerOre("gemTopaz", new ItemStack(TFCItems.gemTopaz, 1, 2));
    OreDictionary.registerOre("gemTourmaline", new ItemStack(TFCItems.gemTourmaline, 1, 2));
    
    OreDictionary.registerOre("gemFlawlessAgate", new ItemStack(TFCItems.gemAgate, 1, 3));
    OreDictionary.registerOre("gemFlawlessAmethyst", new ItemStack(TFCItems.gemAmethyst, 1, 3));
    OreDictionary.registerOre("gemFlawlessBeryl", new ItemStack(TFCItems.gemBeryl, 1, 3));
    OreDictionary.registerOre("gemFlawlessDiamond", new ItemStack(TFCItems.gemDiamond, 1, 3));
    OreDictionary.registerOre("gemFlawlessEmerald", new ItemStack(TFCItems.gemEmerald, 1, 3));
    OreDictionary.registerOre("gemFlawlessGarnet", new ItemStack(TFCItems.gemGarnet, 1, 3));
    OreDictionary.registerOre("gemFlawlessJade", new ItemStack(TFCItems.gemJade, 1, 3));
    OreDictionary.registerOre("gemFlawlessJasper", new ItemStack(TFCItems.gemJasper, 1, 3));
    OreDictionary.registerOre("gemFlawlessOpal", new ItemStack(TFCItems.gemOpal, 1, 3));
    OreDictionary.registerOre("gemFlawlessRuby", new ItemStack(TFCItems.gemRuby, 1, 3));
    OreDictionary.registerOre("gemFlawlessSapphire", new ItemStack(TFCItems.gemSapphire, 1, 3));
    OreDictionary.registerOre("gemFlawlessTopaz", new ItemStack(TFCItems.gemTopaz, 1, 3));
    OreDictionary.registerOre("gemFlawlessTourmaline", new ItemStack(TFCItems.gemTourmaline, 1, 3));
    
    OreDictionary.registerOre("gemExquisiteAgate", new ItemStack(TFCItems.gemAgate, 1, 4));
    OreDictionary.registerOre("gemExquisiteAmethyst", new ItemStack(TFCItems.gemAmethyst, 1, 4));
    OreDictionary.registerOre("gemExquisiteBeryl", new ItemStack(TFCItems.gemBeryl, 1, 4));
    OreDictionary.registerOre("gemExquisiteDiamond", new ItemStack(TFCItems.gemDiamond, 1, 4));
    OreDictionary.registerOre("gemExquisiteEmerald", new ItemStack(TFCItems.gemEmerald, 1, 4));
    OreDictionary.registerOre("gemExquisiteGarnet", new ItemStack(TFCItems.gemGarnet, 1, 4));
    OreDictionary.registerOre("gemExquisiteJade", new ItemStack(TFCItems.gemJade, 1, 4));
    OreDictionary.registerOre("gemExquisiteJasper", new ItemStack(TFCItems.gemJasper, 1, 4));
    OreDictionary.registerOre("gemExquisiteOpal", new ItemStack(TFCItems.gemOpal, 1, 4));
    OreDictionary.registerOre("gemExquisiteRuby", new ItemStack(TFCItems.gemRuby, 1, 4));
    OreDictionary.registerOre("gemExquisiteSapphire", new ItemStack(TFCItems.gemSapphire, 1, 4));
    OreDictionary.registerOre("gemExquisiteTopaz", new ItemStack(TFCItems.gemTopaz, 1, 4));
    OreDictionary.registerOre("gemExquisiteTourmaline", new ItemStack(TFCItems.gemTourmaline, 1, 4));

    
    OreDictionary.registerOre("gemCoal", new ItemStack(Items.field_151044_h, 1, 0));
    OreDictionary.registerOre("gemCoal", new ItemStack(TFCItems.coal, 1, 0));
    OreDictionary.registerOre("gemCharcoal", new ItemStack(Items.field_151044_h, 1, 1));
    OreDictionary.registerOre("gemCharcoal", new ItemStack(TFCItems.coal, 1, 1));

    
    OreDictionary.registerOre("stone", new ItemStack(TFCBlocks.stoneIgEx, 1, 32767));
    OreDictionary.registerOre("stone", new ItemStack(TFCBlocks.stoneIgIn, 1, 32767));
    OreDictionary.registerOre("stone", new ItemStack(TFCBlocks.stoneMM, 1, 32767));
    OreDictionary.registerOre("stone", new ItemStack(TFCBlocks.stoneSed, 1, 32767));

    
    OreDictionary.registerOre("cobblestone", new ItemStack(TFCBlocks.stoneIgExCobble, 1, 32767));
    OreDictionary.registerOre("cobblestone", new ItemStack(TFCBlocks.stoneIgInCobble, 1, 32767));
    OreDictionary.registerOre("cobblestone", new ItemStack(TFCBlocks.stoneMMCobble, 1, 32767));
    OreDictionary.registerOre("cobblestone", new ItemStack(TFCBlocks.stoneSedCobble, 1, 32767));
    
    OreDictionary.registerOre("stoneCobble", new ItemStack(TFCBlocks.stoneIgExCobble, 1, 32767));
    OreDictionary.registerOre("stoneCobble", new ItemStack(TFCBlocks.stoneIgInCobble, 1, 32767));
    OreDictionary.registerOre("stoneCobble", new ItemStack(TFCBlocks.stoneMMCobble, 1, 32767));
    OreDictionary.registerOre("stoneCobble", new ItemStack(TFCBlocks.stoneSedCobble, 1, 32767));

    
    OreDictionary.registerOre("stoneBricks", new ItemStack(Blocks.field_150417_aV));
    OreDictionary.registerOre("stoneBricks", new ItemStack(TFCBlocks.stoneIgExBrick, 1, 32767));
    OreDictionary.registerOre("stoneBricks", new ItemStack(TFCBlocks.stoneIgInBrick, 1, 32767));
    OreDictionary.registerOre("stoneBricks", new ItemStack(TFCBlocks.stoneMMBrick, 1, 32767));
    OreDictionary.registerOre("stoneBricks", new ItemStack(TFCBlocks.stoneSedBrick, 1, 32767));

    
    OreDictionary.registerOre("stoneSmooth", new ItemStack(TFCBlocks.stoneIgExSmooth, 1, 32767));
    OreDictionary.registerOre("stoneSmooth", new ItemStack(TFCBlocks.stoneIgInSmooth, 1, 32767));
    OreDictionary.registerOre("stoneSmooth", new ItemStack(TFCBlocks.stoneMMSmooth, 1, 32767));
    OreDictionary.registerOre("stoneSmooth", new ItemStack(TFCBlocks.stoneSedSmooth, 1, 32767));

    
    OreDictionary.registerOre("craftingTableWood", new ItemStack(TFCBlocks.workbench));
    OreDictionary.registerOre("craftingTableWood", new ItemStack(Blocks.field_150462_ai));

    
    OreDictionary.registerOre("dyeBlack", new ItemStack(TFCItems.dye, 1, 0));
    OreDictionary.registerOre("dyeRed", new ItemStack(TFCItems.powder, 1, 5));
    OreDictionary.registerOre("dyeRed", new ItemStack(TFCItems.dye, 1, 1));
    OreDictionary.registerOre("dyeGreen", new ItemStack(TFCItems.powder, 1, 8));
    OreDictionary.registerOre("dyeGreen", new ItemStack(TFCItems.dye, 1, 2));
    OreDictionary.registerOre("dyeBrown", new ItemStack(TFCItems.dye, 1, 3));
    OreDictionary.registerOre("dyeBlue", new ItemStack(TFCItems.powder, 1, 6));
    OreDictionary.registerOre("dyeBlue", new ItemStack(TFCItems.dye, 1, 4));
    OreDictionary.registerOre("dyePurple", new ItemStack(TFCItems.dye, 1, 5));
    OreDictionary.registerOre("dyeCyan", new ItemStack(TFCItems.dye, 1, 6));
    OreDictionary.registerOre("dyeLightGray", new ItemStack(TFCItems.dye, 1, 7));
    OreDictionary.registerOre("dyeGray", new ItemStack(TFCItems.dye, 1, 8));
    OreDictionary.registerOre("dyePink", new ItemStack(TFCItems.dye, 1, 9));
    OreDictionary.registerOre("dyeLime", new ItemStack(TFCItems.dye, 1, 10));
    OreDictionary.registerOre("dyeYellow", new ItemStack(TFCItems.powder, 1, 7));
    OreDictionary.registerOre("dyeYellow", new ItemStack(TFCItems.dye, 1, 11));
    OreDictionary.registerOre("dyeLightBlue", new ItemStack(TFCItems.dye, 1, 12));
    OreDictionary.registerOre("dyeMagenta", new ItemStack(TFCItems.dye, 1, 13));
    OreDictionary.registerOre("dyeOrange", new ItemStack(TFCItems.dye, 1, 14));
    OreDictionary.registerOre("dyeWhite", new ItemStack(TFCItems.dye, 1, 15));

    
    OreDictionary.registerOre("materialLeather", new ItemStack(Items.field_151116_aA));
    OreDictionary.registerOre("materialLeather", new ItemStack(TFCItems.leather));
    
    OreDictionary.registerOre("materialString", new ItemStack(Items.field_151007_F));
    OreDictionary.registerOre("materialString", new ItemStack(TFCItems.woolYarn));
    
    OreDictionary.registerOre("materialCloth", new ItemStack(TFCItems.woolCloth));
    OreDictionary.registerOre("materialCloth", new ItemStack(TFCItems.silkCloth));
    OreDictionary.registerOre("materialWool", new ItemStack(TFCItems.wool, 1, 32767));

    
    OreDictionary.registerOre("itemAxeStoneHead", new ItemStack(TFCItems.igExStoneAxeHead, 1, 32767));
    OreDictionary.registerOre("itemAxeStoneHead", new ItemStack(TFCItems.igInStoneAxeHead, 1, 32767));
    OreDictionary.registerOre("itemAxeStoneHead", new ItemStack(TFCItems.mMStoneAxeHead, 1, 32767));
    OreDictionary.registerOre("itemAxeStoneHead", new ItemStack(TFCItems.sedStoneAxeHead, 1, 32767));
    
    OreDictionary.registerOre("itemHoeStoneHead", new ItemStack(TFCItems.igExStoneHoeHead, 1, 32767));
    OreDictionary.registerOre("itemHoeStoneHead", new ItemStack(TFCItems.igInStoneHoeHead, 1, 32767));
    OreDictionary.registerOre("itemHoeStoneHead", new ItemStack(TFCItems.mMStoneHoeHead, 1, 32767));
    OreDictionary.registerOre("itemHoeStoneHead", new ItemStack(TFCItems.sedStoneHoeHead, 1, 32767));
    
    OreDictionary.registerOre("itemShovelStoneHead", new ItemStack(TFCItems.igExStoneShovelHead, 1, 32767));
    OreDictionary.registerOre("itemShovelStoneHead", new ItemStack(TFCItems.igInStoneShovelHead, 1, 32767));
    OreDictionary.registerOre("itemShovelStoneHead", new ItemStack(TFCItems.mMStoneShovelHead, 1, 32767));
    OreDictionary.registerOre("itemShovelStoneHead", new ItemStack(TFCItems.sedStoneShovelHead, 1, 32767));
    
    OreDictionary.registerOre("itemJavelinStoneHead", new ItemStack(TFCItems.igExStoneJavelinHead, 1, 32767));
    OreDictionary.registerOre("itemJavelinStoneHead", new ItemStack(TFCItems.igInStoneJavelinHead, 1, 32767));
    OreDictionary.registerOre("itemJavelinStoneHead", new ItemStack(TFCItems.mMStoneJavelinHead, 1, 32767));
    OreDictionary.registerOre("itemJavelinStoneHead", new ItemStack(TFCItems.sedStoneJavelinHead, 1, 32767));

    
    for (Item axe : Recipes.axes) {
      OreDictionary.registerOre("itemAxe", new ItemStack(axe, 1, 32767));
    }
    OreDictionary.registerOre("itemAxeStone", new ItemStack(TFCItems.sedAxe, 1, 32767));
    OreDictionary.registerOre("itemAxeStone", new ItemStack(TFCItems.igInAxe, 1, 32767));
    OreDictionary.registerOre("itemAxeStone", new ItemStack(TFCItems.igExAxe, 1, 32767));
    OreDictionary.registerOre("itemAxeStone", new ItemStack(TFCItems.mMAxe, 1, 32767));
    OreDictionary.registerOre("itemAxeBismuthBronze", new ItemStack(TFCItems.bismuthBronzeAxe, 1, 32767));
    OreDictionary.registerOre("itemAxeBlackBronze", new ItemStack(TFCItems.blackBronzeAxe, 1, 32767));
    OreDictionary.registerOre("itemAxeBlackSteel", new ItemStack(TFCItems.blackSteelAxe, 1, 32767));
    OreDictionary.registerOre("itemAxeBlueSteel", new ItemStack(TFCItems.blueSteelAxe, 1, 32767));
    OreDictionary.registerOre("itemAxeBronze", new ItemStack(TFCItems.bronzeAxe, 1, 32767));
    OreDictionary.registerOre("itemAxeCopper", new ItemStack(TFCItems.copperAxe, 1, 32767));
    OreDictionary.registerOre("itemAxeWroughtIron", new ItemStack(TFCItems.wroughtIronAxe, 1, 32767));
    OreDictionary.registerOre("itemAxeRedSteel", new ItemStack(TFCItems.redSteelAxe, 1, 32767));
    OreDictionary.registerOre("itemAxeSteel", new ItemStack(TFCItems.steelAxe, 1, 32767));
    
    for (Item chisel : Recipes.chisels) {
      OreDictionary.registerOre("itemChisel", new ItemStack(chisel, 1, 32767));
    }
    OreDictionary.registerOre("itemChiselBismuthBronze", new ItemStack(TFCItems.bismuthBronzeChisel, 1, 32767));
    OreDictionary.registerOre("itemChiselBlackBronze", new ItemStack(TFCItems.blackBronzeChisel, 1, 32767));
    OreDictionary.registerOre("itemChiselBlackSteel", new ItemStack(TFCItems.blackSteelChisel, 1, 32767));
    OreDictionary.registerOre("itemChiselBlueSteel", new ItemStack(TFCItems.blueSteelChisel, 1, 32767));
    OreDictionary.registerOre("itemChiselBronze", new ItemStack(TFCItems.bronzeChisel, 1, 32767));
    OreDictionary.registerOre("itemChiselCopper", new ItemStack(TFCItems.copperChisel, 1, 32767));
    OreDictionary.registerOre("itemChiselWroughtIron", new ItemStack(TFCItems.wroughtIronChisel, 1, 32767));
    OreDictionary.registerOre("itemChiselRedSteel", new ItemStack(TFCItems.redSteelChisel, 1, 32767));
    OreDictionary.registerOre("itemChiselSteel", new ItemStack(TFCItems.steelChisel, 1, 32767));
    
    for (Item hammer : Recipes.hammers) {
      OreDictionary.registerOre("itemHammer", new ItemStack(hammer, 1, 32767));
    }
    OreDictionary.registerOre("itemHammerStone", new ItemStack(TFCItems.stoneHammer, 1, 32767));
    OreDictionary.registerOre("itemHammerBismuthBronze", new ItemStack(TFCItems.bismuthBronzeHammer, 1, 32767));
    OreDictionary.registerOre("itemHammerBlackBronze", new ItemStack(TFCItems.blackBronzeHammer, 1, 32767));
    OreDictionary.registerOre("itemHammerBlackSteel", new ItemStack(TFCItems.blackSteelHammer, 1, 32767));
    OreDictionary.registerOre("itemHammerBlueSteel", new ItemStack(TFCItems.blueSteelHammer, 1, 32767));
    OreDictionary.registerOre("itemHammerBronze", new ItemStack(TFCItems.bronzeHammer, 1, 32767));
    OreDictionary.registerOre("itemHammerCopper", new ItemStack(TFCItems.copperHammer, 1, 32767));
    OreDictionary.registerOre("itemHammerWroughtIron", new ItemStack(TFCItems.wroughtIronHammer, 1, 32767));
    OreDictionary.registerOre("itemHammerRedSteel", new ItemStack(TFCItems.redSteelHammer, 1, 32767));
    OreDictionary.registerOre("itemHammerSteel", new ItemStack(TFCItems.steelHammer, 1, 32767));
    
    for (Item knife : Recipes.knives) {
      OreDictionary.registerOre("itemKnife", new ItemStack(knife, 1, 32767));
    }
    OreDictionary.registerOre("itemKnifeStone", new ItemStack(TFCItems.stoneKnife, 1, 32767));
    OreDictionary.registerOre("itemKnifeBismuthBronze", new ItemStack(TFCItems.bismuthBronzeKnife, 1, 32767));
    OreDictionary.registerOre("itemKnifeBlackBronze", new ItemStack(TFCItems.blackBronzeKnife, 1, 32767));
    OreDictionary.registerOre("itemKnifeBlackSteel", new ItemStack(TFCItems.blackSteelKnife, 1, 32767));
    OreDictionary.registerOre("itemKnifeBlueSteel", new ItemStack(TFCItems.blueSteelKnife, 1, 32767));
    OreDictionary.registerOre("itemKnifeBronze", new ItemStack(TFCItems.bronzeKnife, 1, 32767));
    OreDictionary.registerOre("itemKnifeCopper", new ItemStack(TFCItems.copperKnife, 1, 32767));
    OreDictionary.registerOre("itemKnifeWroughtIron", new ItemStack(TFCItems.wroughtIronKnife, 1, 32767));
    OreDictionary.registerOre("itemKnifeRedSteel", new ItemStack(TFCItems.redSteelKnife, 1, 32767));
    OreDictionary.registerOre("itemKnifeSteel", new ItemStack(TFCItems.steelKnife, 1, 32767));
    
    for (Item saw : Recipes.saws) {
      OreDictionary.registerOre("itemSaw", new ItemStack(saw, 1, 32767));
    }
    OreDictionary.registerOre("itemSawBismuthBronze", new ItemStack(TFCItems.bismuthBronzeSaw, 1, 32767));
    OreDictionary.registerOre("itemSawBlackBronze", new ItemStack(TFCItems.blackBronzeSaw, 1, 32767));
    OreDictionary.registerOre("itemSawBlackSteel", new ItemStack(TFCItems.blackSteelSaw, 1, 32767));
    OreDictionary.registerOre("itemSawBlueSteel", new ItemStack(TFCItems.blueSteelSaw, 1, 32767));
    OreDictionary.registerOre("itemSawBronze", new ItemStack(TFCItems.bronzeSaw, 1, 32767));
    OreDictionary.registerOre("itemSawCopper", new ItemStack(TFCItems.copperSaw, 1, 32767));
    OreDictionary.registerOre("itemSawWroughtIron", new ItemStack(TFCItems.wroughtIronSaw, 1, 32767));
    OreDictionary.registerOre("itemSawRedSteel", new ItemStack(TFCItems.redSteelSaw, 1, 32767));
    OreDictionary.registerOre("itemSawSteel", new ItemStack(TFCItems.steelSaw, 1, 32767));
    
    for (Item scythe : Recipes.scythes) {
      OreDictionary.registerOre("itemScythe", new ItemStack(scythe, 1, 32767));
    }
    OreDictionary.registerOre("itemScytheBismuthBronze", new ItemStack(TFCItems.bismuthBronzeScythe, 1, 32767));
    OreDictionary.registerOre("itemScytheBlackBronze", new ItemStack(TFCItems.blackBronzeScythe, 1, 32767));
    OreDictionary.registerOre("itemScytheBlackSteel", new ItemStack(TFCItems.blackSteelScythe, 1, 32767));
    OreDictionary.registerOre("itemScytheBlueSteel", new ItemStack(TFCItems.blueSteelScythe, 1, 32767));
    OreDictionary.registerOre("itemScytheBronze", new ItemStack(TFCItems.bronzeScythe, 1, 32767));
    OreDictionary.registerOre("itemScytheCopper", new ItemStack(TFCItems.copperScythe, 1, 32767));
    OreDictionary.registerOre("itemScytheWroughtIron", new ItemStack(TFCItems.wroughtIronScythe, 1, 32767));
    OreDictionary.registerOre("itemScytheRedSteel", new ItemStack(TFCItems.redSteelScythe, 1, 32767));
    OreDictionary.registerOre("itemScytheSteel", new ItemStack(TFCItems.steelScythe, 1, 32767));
    
    for (Item pick : Recipes.picks) {
      OreDictionary.registerOre("itemPick", new ItemStack(pick, 1, 32767));
    }
    OreDictionary.registerOre("itemPickBismuthBronze", new ItemStack(TFCItems.bismuthBronzePick, 1, 32767));
    OreDictionary.registerOre("itemPickBlackBronze", new ItemStack(TFCItems.blackBronzePick, 1, 32767));
    OreDictionary.registerOre("itemPickBlackSteel", new ItemStack(TFCItems.blackSteelPick, 1, 32767));
    OreDictionary.registerOre("itemPickBlueSteel", new ItemStack(TFCItems.blueSteelPick, 1, 32767));
    OreDictionary.registerOre("itemPickBronze", new ItemStack(TFCItems.bronzePick, 1, 32767));
    OreDictionary.registerOre("itemPickCopper", new ItemStack(TFCItems.copperPick, 1, 32767));
    OreDictionary.registerOre("itemPickWroughtIron", new ItemStack(TFCItems.wroughtIronPick, 1, 32767));
    OreDictionary.registerOre("itemPickRedSteel", new ItemStack(TFCItems.redSteelPick, 1, 32767));
    OreDictionary.registerOre("itemPickSteel", new ItemStack(TFCItems.steelPick, 1, 32767));
    
    for (Item proPick : Recipes.proPicks) {
      OreDictionary.registerOre("itemProPick", new ItemStack(proPick, 1, 32767));
    }
    OreDictionary.registerOre("itemProPickBismuthBronze", new ItemStack(TFCItems.proPickBismuthBronze, 1, 32767));
    OreDictionary.registerOre("itemProPickBlackBronze", new ItemStack(TFCItems.proPickBlackBronze, 1, 32767));
    OreDictionary.registerOre("itemProPickBlackSteel", new ItemStack(TFCItems.proPickBlackSteel, 1, 32767));
    OreDictionary.registerOre("itemProPickBlueSteel", new ItemStack(TFCItems.proPickBlueSteel, 1, 32767));
    OreDictionary.registerOre("itemProPickBronze", new ItemStack(TFCItems.proPickBronze, 1, 32767));
    OreDictionary.registerOre("itemProPickCopper", new ItemStack(TFCItems.proPickCopper, 1, 32767));
    OreDictionary.registerOre("itemProPickWroughtIron", new ItemStack(TFCItems.proPickIron, 1, 32767));
    OreDictionary.registerOre("itemProPickRedSteel", new ItemStack(TFCItems.proPickRedSteel, 1, 32767));
    OreDictionary.registerOre("itemProPickSteel", new ItemStack(TFCItems.proPickSteel, 1, 32767));
    
    for (Item shovel : Recipes.shovels) {
      OreDictionary.registerOre("itemShovel", new ItemStack(shovel, 1, 32767));
    }
    OreDictionary.registerOre("itemShovelStone", new ItemStack(TFCItems.sedShovel, 1, 32767));
    OreDictionary.registerOre("itemShovelStone", new ItemStack(TFCItems.igInShovel, 1, 32767));
    OreDictionary.registerOre("itemShovelStone", new ItemStack(TFCItems.igExShovel, 1, 32767));
    OreDictionary.registerOre("itemShovelStone", new ItemStack(TFCItems.mMShovel, 1, 32767));
    OreDictionary.registerOre("itemShovelBismuthBronze", new ItemStack(TFCItems.bismuthBronzeShovel, 1, 32767));
    OreDictionary.registerOre("itemShovelBlackBronze", new ItemStack(TFCItems.blackBronzeShovel, 1, 32767));
    OreDictionary.registerOre("itemShovelBlackSteel", new ItemStack(TFCItems.blackSteelShovel, 1, 32767));
    OreDictionary.registerOre("itemShovelBlueSteel", new ItemStack(TFCItems.blueSteelShovel, 1, 32767));
    OreDictionary.registerOre("itemShovelBronze", new ItemStack(TFCItems.bronzeShovel, 1, 32767));
    OreDictionary.registerOre("itemShovelCopper", new ItemStack(TFCItems.copperShovel, 1, 32767));
    OreDictionary.registerOre("itemShovelWroughtIron", new ItemStack(TFCItems.wroughtIronShovel, 1, 32767));
    OreDictionary.registerOre("itemShovelRedSteel", new ItemStack(TFCItems.redSteelShovel, 1, 32767));
    OreDictionary.registerOre("itemShovelSteel", new ItemStack(TFCItems.steelShovel, 1, 32767));
    
    for (Item hoe : Recipes.hoes) {
      OreDictionary.registerOre("itemHoe", new ItemStack(hoe, 1, 32767));
    }
    OreDictionary.registerOre("itemHoeStone", new ItemStack(TFCItems.sedHoe, 1, 32767));
    OreDictionary.registerOre("itemHoeStone", new ItemStack(TFCItems.igInHoe, 1, 32767));
    OreDictionary.registerOre("itemHoeStone", new ItemStack(TFCItems.igExHoe, 1, 32767));
    OreDictionary.registerOre("itemHoeStone", new ItemStack(TFCItems.mMHoe, 1, 32767));
    OreDictionary.registerOre("itemHoeBismuthBronze", new ItemStack(TFCItems.bismuthBronzeHoe, 1, 32767));
    OreDictionary.registerOre("itemHoeBlackBronze", new ItemStack(TFCItems.blackBronzeHoe, 1, 32767));
    OreDictionary.registerOre("itemHoeBlackSteel", new ItemStack(TFCItems.blackSteelHoe, 1, 32767));
    OreDictionary.registerOre("itemHoeBlueSteel", new ItemStack(TFCItems.blueSteelHoe, 1, 32767));
    OreDictionary.registerOre("itemHoeBronze", new ItemStack(TFCItems.bronzeHoe, 1, 32767));
    OreDictionary.registerOre("itemHoeCopper", new ItemStack(TFCItems.copperHoe, 1, 32767));
    OreDictionary.registerOre("itemHoeWroughtIron", new ItemStack(TFCItems.wroughtIronHoe, 1, 32767));
    OreDictionary.registerOre("itemHoeRedSteel", new ItemStack(TFCItems.redSteelHoe, 1, 32767));
    OreDictionary.registerOre("itemHoeSteel", new ItemStack(TFCItems.steelHoe, 1, 32767));

    
    for (Item sword : Recipes.swords) {
      OreDictionary.registerOre("itemSword", new ItemStack(sword, 1, 32767));
    }
    OreDictionary.registerOre("itemSwordBismuthBronze", new ItemStack(TFCItems.bismuthBronzeSword, 1, 32767));
    OreDictionary.registerOre("itemSwordBlackBronze", new ItemStack(TFCItems.blackBronzeSword, 1, 32767));
    OreDictionary.registerOre("itemSwordBlackSteel", new ItemStack(TFCItems.blackSteelSword, 1, 32767));
    OreDictionary.registerOre("itemSwordBlueSteel", new ItemStack(TFCItems.blueSteelSword, 1, 32767));
    OreDictionary.registerOre("itemSwordBronze", new ItemStack(TFCItems.bronzeSword, 1, 32767));
    OreDictionary.registerOre("itemSwordCopper", new ItemStack(TFCItems.copperSword, 1, 32767));
    OreDictionary.registerOre("itemSwordWroughtIron", new ItemStack(TFCItems.wroughtIronSword, 1, 32767));
    OreDictionary.registerOre("itemSwordRedSteel", new ItemStack(TFCItems.redSteelSword, 1, 32767));
    OreDictionary.registerOre("itemSwordSteel", new ItemStack(TFCItems.steelSword, 1, 32767));
    
    for (Item mace : Recipes.maces) {
      OreDictionary.registerOre("itemMace", new ItemStack(mace, 1, 32767));
    }
    OreDictionary.registerOre("itemMaceBismuthBronze", new ItemStack(TFCItems.bismuthBronzeMace, 1, 32767));
    OreDictionary.registerOre("itemMaceBlackBronze", new ItemStack(TFCItems.blackBronzeMace, 1, 32767));
    OreDictionary.registerOre("itemMaceBlackSteel", new ItemStack(TFCItems.blackSteelMace, 1, 32767));
    OreDictionary.registerOre("itemMaceBlueSteel", new ItemStack(TFCItems.blueSteelMace, 1, 32767));
    OreDictionary.registerOre("itemMaceBronze", new ItemStack(TFCItems.bronzeMace, 1, 32767));
    OreDictionary.registerOre("itemMaceCopper", new ItemStack(TFCItems.copperMace, 1, 32767));
    OreDictionary.registerOre("itemMaceWroughtIron", new ItemStack(TFCItems.wroughtIronMace, 1, 32767));
    OreDictionary.registerOre("itemMaceRedSteel", new ItemStack(TFCItems.redSteelMace, 1, 32767));
    OreDictionary.registerOre("itemMaceSteel", new ItemStack(TFCItems.steelMace, 1, 32767));
    
    for (Item javelin : Recipes.javelins) {
      OreDictionary.registerOre("itemJavelin", new ItemStack(javelin, 1, 32767));
    }
    OreDictionary.registerOre("itemJavelinStone", new ItemStack(TFCItems.sedStoneJavelin, 1, 32767));
    OreDictionary.registerOre("itemJavelinStone", new ItemStack(TFCItems.igInStoneJavelin, 1, 32767));
    OreDictionary.registerOre("itemJavelinStone", new ItemStack(TFCItems.igExStoneJavelin, 1, 32767));
    OreDictionary.registerOre("itemJavelinStone", new ItemStack(TFCItems.mMStoneJavelin, 1, 32767));
    OreDictionary.registerOre("itemJavelinBismuthBronze", new ItemStack(TFCItems.bismuthBronzeJavelin, 1, 32767));
    OreDictionary.registerOre("itemJavelinBlackBronze", new ItemStack(TFCItems.blackBronzeJavelin, 1, 32767));
    OreDictionary.registerOre("itemJavelinBlackSteel", new ItemStack(TFCItems.blackSteelJavelin, 1, 32767));
    OreDictionary.registerOre("itemJavelinBlueSteel", new ItemStack(TFCItems.blueSteelJavelin, 1, 32767));
    OreDictionary.registerOre("itemJavelinBronze", new ItemStack(TFCItems.bronzeJavelin, 1, 32767));
    OreDictionary.registerOre("itemJavelinCopper", new ItemStack(TFCItems.copperJavelin, 1, 32767));
    OreDictionary.registerOre("itemJavelinWroughtIron", new ItemStack(TFCItems.wroughtIronJavelin, 1, 32767));
    OreDictionary.registerOre("itemJavelinRedSteel", new ItemStack(TFCItems.redSteelJavelin, 1, 32767));
    OreDictionary.registerOre("itemJavelinSteel", new ItemStack(TFCItems.steelJavelin, 1, 32767));

    
    OreDictionary.registerOre("lumpClay", new ItemStack(Items.field_151119_aD));
    OreDictionary.registerOre("lumpClay", new ItemStack(TFCItems.clayBall, 1, 0));
    
    OreDictionary.registerOre("itemArrow", new ItemStack(Items.field_151032_g));
    OreDictionary.registerOre("itemArrow", new ItemStack(TFCItems.arrow));
    
    OreDictionary.registerOre("itemReed", new ItemStack(Items.field_151120_aE));
    OreDictionary.registerOre("itemReed", new ItemStack(TFCItems.reeds));
    
    OreDictionary.registerOre("itemRock", new ItemStack(TFCItems.looseRock, 1, 32767));
    
    OreDictionary.registerOre("bucketEmpty", new ItemStack(Items.field_151133_ar));
    OreDictionary.registerOre("bucketEmpty", new ItemStack(TFCItems.woodenBucketEmpty));
    OreDictionary.registerOre("bucketEmpty", new ItemStack(TFCItems.redSteelBucketEmpty));
    OreDictionary.registerOre("bucketEmpty", new ItemStack(TFCItems.blueSteelBucketEmpty));
    
    OreDictionary.registerOre("bucketWater", new ItemStack(Items.field_151131_as));
    OreDictionary.registerOre("bucketWater", new ItemStack(TFCItems.woodenBucketWater, 1, 32767));
    OreDictionary.registerOre("bucketWater", new ItemStack(TFCItems.redSteelBucketWater, 1, 32767));
    OreDictionary.registerOre("bucketWater", new ItemStack(TFCItems.woodenBucketSaltWater, 1, 32767));
    OreDictionary.registerOre("bucketWater", new ItemStack(TFCItems.redSteelBucketSaltWater, 1, 32767));
    
    OreDictionary.registerOre("bucketFreshWater", new ItemStack(TFCItems.woodenBucketWater, 1, 32767));
    OreDictionary.registerOre("bucketFreshWater", new ItemStack(TFCItems.redSteelBucketWater, 1, 32767));
    
    OreDictionary.registerOre("bucketSaltWater", new ItemStack(TFCItems.woodenBucketSaltWater, 1, 32767));
    OreDictionary.registerOre("bucketSaltWater", new ItemStack(TFCItems.redSteelBucketSaltWater, 1, 32767));
    
    OreDictionary.registerOre("bucketLava", new ItemStack(Items.field_151129_at));
    OreDictionary.registerOre("bucketLava", new ItemStack(TFCItems.blueSteelBucketLava));
    
    OreDictionary.registerOre("bucketMilk", new ItemStack(Items.field_151117_aB));
    OreDictionary.registerOre("bucketMilk", new ItemStack(TFCItems.woodenBucketMilk));
    
    OreDictionary.registerOre("toolFlintSteel", new ItemStack(Items.field_151033_d, 1, 32767));
    OreDictionary.registerOre("toolFlintSteel", new ItemStack(TFCItems.flintSteel, 1, 32767));

    
    OreDictionary.registerOre("blockSand", new ItemStack((Block)Blocks.field_150354_m));
    OreDictionary.registerOre("blockSand", new ItemStack(TFCBlocks.sand, 1, 32767));
    OreDictionary.registerOre("blockSand", new ItemStack(TFCBlocks.sand2, 1, 32767));
    
    OreDictionary.registerOre("blockGravel", new ItemStack(Blocks.field_150351_n));
    OreDictionary.registerOre("blockGravel", new ItemStack(TFCBlocks.gravel, 1, 32767));
    OreDictionary.registerOre("blockGravel", new ItemStack(TFCBlocks.gravel2, 1, 32767));
    
    OreDictionary.registerOre("blockDirt", new ItemStack(Blocks.field_150346_d));
    OreDictionary.registerOre("blockDirt", new ItemStack(TFCBlocks.dirt, 1, 32767));
    OreDictionary.registerOre("blockDirt", new ItemStack(TFCBlocks.dirt2, 1, 32767));
    
    OreDictionary.registerOre("blockTorch", new ItemStack(Blocks.field_150478_aa));
    OreDictionary.registerOre("blockTorch", new ItemStack(TFCBlocks.torch));
    
    OreDictionary.registerOre("blockPumpkin", new ItemStack(Blocks.field_150423_aK));
    OreDictionary.registerOre("blockPumpkin", new ItemStack(TFCBlocks.pumpkin));
    OreDictionary.registerOre("blockLitPumpkin", new ItemStack(Blocks.field_150428_aP));
    OreDictionary.registerOre("blockLitPumpkin", new ItemStack(TFCBlocks.litPumpkin));

    
    for (Item seed : Recipes.seeds)
      OreDictionary.registerOre("seedBag", new ItemStack(seed, 1, 32767)); 
    OreDictionary.registerOre("seedWheat", new ItemStack(TFCItems.seedsWheat, 1, 32767));
    OreDictionary.registerOre("seedMaize", new ItemStack(TFCItems.seedsMaize, 1, 32767));
    OreDictionary.registerOre("seedTomato", new ItemStack(TFCItems.seedsTomato, 1, 32767));
    OreDictionary.registerOre("seedBarley", new ItemStack(TFCItems.seedsBarley, 1, 32767));
    OreDictionary.registerOre("seedRye", new ItemStack(TFCItems.seedsRye, 1, 32767));
    OreDictionary.registerOre("seedOat", new ItemStack(TFCItems.seedsOat, 1, 32767));
    OreDictionary.registerOre("seedRice", new ItemStack(TFCItems.seedsRice, 1, 32767));
    OreDictionary.registerOre("seedPotato", new ItemStack(TFCItems.seedsPotato, 1, 32767));
    OreDictionary.registerOre("seedOnion", new ItemStack(TFCItems.seedsOnion, 1, 32767));
    OreDictionary.registerOre("seedCabbage", new ItemStack(TFCItems.seedsCabbage, 1, 32767));
    OreDictionary.registerOre("seedGarlic", new ItemStack(TFCItems.seedsGarlic, 1, 32767));
    OreDictionary.registerOre("seedCarrot", new ItemStack(TFCItems.seedsCarrot, 1, 32767));
    OreDictionary.registerOre("seedSugarcane", new ItemStack(TFCItems.seedsSugarcane, 1, 32767));
    OreDictionary.registerOre("seedYelloBellPepper", new ItemStack(TFCItems.seedsYellowBellPepper, 1, 32767));
    OreDictionary.registerOre("seedRedBellPepper", new ItemStack(TFCItems.seedsRedBellPepper, 1, 32767));
    OreDictionary.registerOre("seedSoybean", new ItemStack(TFCItems.seedsSoybean, 1, 32767));
    OreDictionary.registerOre("seedGreenbean", new ItemStack(TFCItems.seedsGreenbean, 1, 32767));
    OreDictionary.registerOre("seedSquash", new ItemStack(TFCItems.seedsSquash, 1, 32767));
    OreDictionary.registerOre("seedJute", new ItemStack(TFCItems.seedsJute, 1, 32767));
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\TFC_OreDictionary.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */