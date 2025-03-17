/*     */ package com.bioxx.tfc.Core;
/*     */ 
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ 
/*     */ 
/*     */ public class TFC_OreDictionary
/*     */ {
/*     */   public static void registerOreDict() {
/*  16 */     int WILD = 32767;
/*     */ 
/*     */     
/*  19 */     OreDictionary.registerOre("logWood", new ItemStack(TFCItems.logs, 1, 32767));
/*  20 */     OreDictionary.registerOre("logWood", new ItemStack(TFCBlocks.logNatural, 1, 32767));
/*  21 */     OreDictionary.registerOre("logWood", new ItemStack(TFCBlocks.logNatural2, 1, 32767));
/*  22 */     OreDictionary.registerOre("logWood", new ItemStack(TFCBlocks.woodHoriz, 1, 32767));
/*  23 */     OreDictionary.registerOre("logWood", new ItemStack(TFCBlocks.woodHoriz2, 1, 32767));
/*  24 */     OreDictionary.registerOre("logWood", new ItemStack(TFCBlocks.woodHoriz3, 1, 32767));
/*  25 */     OreDictionary.registerOre("logWood", new ItemStack(TFCBlocks.woodHoriz4, 1, 32767));
/*  26 */     OreDictionary.registerOre("logWood", new ItemStack(TFCBlocks.woodVert, 1, 32767));
/*  27 */     OreDictionary.registerOre("logWood", new ItemStack(TFCBlocks.woodVert2, 1, 32767));
/*     */     
/*  29 */     OreDictionary.registerOre("plankWood", new ItemStack(TFCBlocks.planks, 1, 32767));
/*  30 */     OreDictionary.registerOre("plankWood", new ItemStack(TFCBlocks.planks2, 1, 32767));
/*     */     
/*  32 */     OreDictionary.registerOre("woodLumber", new ItemStack(TFCItems.singlePlank, 1, 32767));
/*     */     
/*  34 */     OreDictionary.registerOre("stickWood", new ItemStack(TFCItems.stick));
/*     */     
/*  36 */     OreDictionary.registerOre("treeSapling", new ItemStack(TFCBlocks.sapling, 1, 32767));
/*  37 */     OreDictionary.registerOre("treeSapling", new ItemStack(TFCBlocks.sapling2, 1, 32767));
/*     */     
/*  39 */     OreDictionary.registerOre("treeLeaves", new ItemStack(TFCBlocks.leaves, 1, 32767));
/*  40 */     OreDictionary.registerOre("treeLeaves", new ItemStack(TFCBlocks.leaves2, 1, 32767));
/*     */ 
/*     */     
/*  43 */     OreDictionary.registerOre("chestWood", new ItemStack(TFCBlocks.chest, 1, 32767));
/*  44 */     OreDictionary.registerOre("barrelWood", new ItemStack(TFCBlocks.barrel, 1, 32767));
/*     */ 
/*     */     
/*  47 */     OreDictionary.registerOre("oreNormalCopper", new ItemStack(TFCItems.oreChunk, 1, 0));
/*  48 */     OreDictionary.registerOre("oreNormalCopper", new ItemStack(TFCItems.oreChunk, 1, 9));
/*  49 */     OreDictionary.registerOre("oreNormalCopper", new ItemStack(TFCItems.oreChunk, 1, 13));
/*  50 */     OreDictionary.registerOre("oreSmallCopper", new ItemStack(TFCItems.smallOreChunk, 1, 0));
/*  51 */     OreDictionary.registerOre("oreSmallCopper", new ItemStack(TFCItems.smallOreChunk, 1, 9));
/*  52 */     OreDictionary.registerOre("oreSmallCopper", new ItemStack(TFCItems.smallOreChunk, 1, 13));
/*  53 */     OreDictionary.registerOre("oreRichCopper", new ItemStack(TFCItems.oreChunk, 1, 35));
/*  54 */     OreDictionary.registerOre("oreRichCopper", new ItemStack(TFCItems.oreChunk, 1, 44));
/*  55 */     OreDictionary.registerOre("oreRichCopper", new ItemStack(TFCItems.oreChunk, 1, 48));
/*  56 */     OreDictionary.registerOre("orePoorCopper", new ItemStack(TFCItems.oreChunk, 1, 49));
/*  57 */     OreDictionary.registerOre("orePoorCopper", new ItemStack(TFCItems.oreChunk, 1, 58));
/*  58 */     OreDictionary.registerOre("orePoorCopper", new ItemStack(TFCItems.oreChunk, 1, 62));
/*     */     
/*  60 */     OreDictionary.registerOre("oreNormalGold", new ItemStack(TFCItems.oreChunk, 1, 1));
/*  61 */     OreDictionary.registerOre("oreSmallGold", new ItemStack(TFCItems.smallOreChunk, 1, 1));
/*  62 */     OreDictionary.registerOre("oreRichGold", new ItemStack(TFCItems.oreChunk, 1, 36));
/*  63 */     OreDictionary.registerOre("orePoorGold", new ItemStack(TFCItems.oreChunk, 1, 50));
/*     */     
/*  65 */     OreDictionary.registerOre("oreNormalPlatinum", new ItemStack(TFCItems.oreChunk, 1, 2));
/*  66 */     OreDictionary.registerOre("oreSmallPlatinum", new ItemStack(TFCItems.smallOreChunk, 1, 2));
/*  67 */     OreDictionary.registerOre("oreRichPlatinum", new ItemStack(TFCItems.oreChunk, 1, 37));
/*  68 */     OreDictionary.registerOre("orePoorPlatinum", new ItemStack(TFCItems.oreChunk, 1, 51));
/*     */     
/*  70 */     OreDictionary.registerOre("oreNormalIron", new ItemStack(TFCItems.oreChunk, 1, 3));
/*  71 */     OreDictionary.registerOre("oreNormalIron", new ItemStack(TFCItems.oreChunk, 1, 10));
/*  72 */     OreDictionary.registerOre("oreNormalIron", new ItemStack(TFCItems.oreChunk, 1, 11));
/*  73 */     OreDictionary.registerOre("oreSmallIron", new ItemStack(TFCItems.smallOreChunk, 1, 3));
/*  74 */     OreDictionary.registerOre("oreSmallIron", new ItemStack(TFCItems.smallOreChunk, 1, 10));
/*  75 */     OreDictionary.registerOre("oreSmallIron", new ItemStack(TFCItems.smallOreChunk, 1, 11));
/*  76 */     OreDictionary.registerOre("oreRichIron", new ItemStack(TFCItems.oreChunk, 1, 38));
/*  77 */     OreDictionary.registerOre("oreRichIron", new ItemStack(TFCItems.oreChunk, 1, 45));
/*  78 */     OreDictionary.registerOre("oreRichIron", new ItemStack(TFCItems.oreChunk, 1, 46));
/*  79 */     OreDictionary.registerOre("orePoorIron", new ItemStack(TFCItems.oreChunk, 1, 52));
/*  80 */     OreDictionary.registerOre("orePoorIron", new ItemStack(TFCItems.oreChunk, 1, 59));
/*  81 */     OreDictionary.registerOre("orePoorIron", new ItemStack(TFCItems.oreChunk, 1, 60));
/*     */     
/*  83 */     OreDictionary.registerOre("oreNormalSilver", new ItemStack(TFCItems.oreChunk, 1, 4));
/*  84 */     OreDictionary.registerOre("oreSmallSilver", new ItemStack(TFCItems.smallOreChunk, 1, 4));
/*  85 */     OreDictionary.registerOre("oreRichSilver", new ItemStack(TFCItems.oreChunk, 1, 39));
/*  86 */     OreDictionary.registerOre("orePoorSilver", new ItemStack(TFCItems.oreChunk, 1, 53));
/*     */     
/*  88 */     OreDictionary.registerOre("oreNormalTin", new ItemStack(TFCItems.oreChunk, 1, 5));
/*  89 */     OreDictionary.registerOre("oreSmallTin", new ItemStack(TFCItems.smallOreChunk, 1, 5));
/*  90 */     OreDictionary.registerOre("oreRichTin", new ItemStack(TFCItems.oreChunk, 1, 40));
/*  91 */     OreDictionary.registerOre("orePoorTin", new ItemStack(TFCItems.oreChunk, 1, 54));
/*     */     
/*  93 */     OreDictionary.registerOre("oreNormalLead", new ItemStack(TFCItems.oreChunk, 1, 6));
/*  94 */     OreDictionary.registerOre("oreSmallLead", new ItemStack(TFCItems.smallOreChunk, 1, 6));
/*  95 */     OreDictionary.registerOre("oreRichLead", new ItemStack(TFCItems.oreChunk, 1, 41));
/*  96 */     OreDictionary.registerOre("orePoorLead", new ItemStack(TFCItems.oreChunk, 1, 55));
/*     */     
/*  98 */     OreDictionary.registerOre("oreNormalBismuth", new ItemStack(TFCItems.oreChunk, 1, 7));
/*  99 */     OreDictionary.registerOre("oreSmallBismuth", new ItemStack(TFCItems.smallOreChunk, 1, 7));
/* 100 */     OreDictionary.registerOre("oreRichBismuth", new ItemStack(TFCItems.oreChunk, 1, 42));
/* 101 */     OreDictionary.registerOre("orePoorBismuth", new ItemStack(TFCItems.oreChunk, 1, 56));
/*     */     
/* 103 */     OreDictionary.registerOre("oreNormalNickel", new ItemStack(TFCItems.oreChunk, 1, 8));
/* 104 */     OreDictionary.registerOre("oreSmallNickel", new ItemStack(TFCItems.smallOreChunk, 1, 8));
/* 105 */     OreDictionary.registerOre("oreRichNickel", new ItemStack(TFCItems.oreChunk, 1, 43));
/* 106 */     OreDictionary.registerOre("orePoorNickel", new ItemStack(TFCItems.oreChunk, 1, 57));
/*     */     
/* 108 */     OreDictionary.registerOre("oreNormalZinc", new ItemStack(TFCItems.oreChunk, 1, 12));
/* 109 */     OreDictionary.registerOre("oreSmallZinc", new ItemStack(TFCItems.smallOreChunk, 1, 12));
/* 110 */     OreDictionary.registerOre("oreRichZinc", new ItemStack(TFCItems.oreChunk, 1, 47));
/* 111 */     OreDictionary.registerOre("orePoorZinc", new ItemStack(TFCItems.oreChunk, 1, 61));
/*     */     
/* 113 */     OreDictionary.registerOre("oreCoal", new ItemStack(TFCItems.oreChunk, 1, 14));
/* 114 */     OreDictionary.registerOre("oreCoal", new ItemStack(TFCItems.oreChunk, 1, 15));
/*     */     
/* 116 */     OreDictionary.registerOre("oreKaolinite", new ItemStack(TFCItems.oreChunk, 1, 16));
/* 117 */     OreDictionary.registerOre("oreGypsum", new ItemStack(TFCItems.oreChunk, 1, 17));
/* 118 */     OreDictionary.registerOre("oreSatinspar", new ItemStack(TFCItems.oreChunk, 1, 18));
/* 119 */     OreDictionary.registerOre("oreSelenite", new ItemStack(TFCItems.oreChunk, 1, 19));
/* 120 */     OreDictionary.registerOre("oreGraphite", new ItemStack(TFCItems.oreChunk, 1, 20));
/* 121 */     OreDictionary.registerOre("oreDiamond", new ItemStack(TFCItems.oreChunk, 1, 21));
/* 122 */     OreDictionary.registerOre("orePetrifiedWood", new ItemStack(TFCItems.oreChunk, 1, 22));
/* 123 */     OreDictionary.registerOre("oreSulfur", new ItemStack(TFCItems.oreChunk, 1, 23));
/* 124 */     OreDictionary.registerOre("oreJet", new ItemStack(TFCItems.oreChunk, 1, 24));
/* 125 */     OreDictionary.registerOre("oreMicrocline", new ItemStack(TFCItems.oreChunk, 1, 25));
/* 126 */     OreDictionary.registerOre("oreUranium", new ItemStack(TFCItems.oreChunk, 1, 26));
/*     */     
/* 128 */     OreDictionary.registerOre("oreRedstone", new ItemStack(TFCItems.oreChunk, 1, 27));
/* 129 */     OreDictionary.registerOre("oreRedstone", new ItemStack(TFCItems.oreChunk, 1, 28));
/*     */     
/* 131 */     OreDictionary.registerOre("oreSaltpeter", new ItemStack(TFCItems.oreChunk, 1, 29));
/* 132 */     OreDictionary.registerOre("oreSerpentine", new ItemStack(TFCItems.oreChunk, 1, 30));
/* 133 */     OreDictionary.registerOre("oreSylvite", new ItemStack(TFCItems.oreChunk, 1, 31));
/* 134 */     OreDictionary.registerOre("oreBorax", new ItemStack(TFCItems.oreChunk, 1, 32));
/* 135 */     OreDictionary.registerOre("oreOlivine", new ItemStack(TFCItems.oreChunk, 1, 33));
/* 136 */     OreDictionary.registerOre("oreLapis", new ItemStack(TFCItems.oreChunk, 1, 34));
/*     */ 
/*     */     
/* 139 */     OreDictionary.registerOre("dustFlux", new ItemStack(TFCItems.powder, 1, 0));
/* 140 */     OreDictionary.registerOre("dustKaolinite", new ItemStack(TFCItems.powder, 1, 1));
/* 141 */     OreDictionary.registerOre("dustGraphite", new ItemStack(TFCItems.powder, 1, 2));
/* 142 */     OreDictionary.registerOre("dustSulfur", new ItemStack(TFCItems.powder, 1, 3));
/* 143 */     OreDictionary.registerOre("dustSaltpeter", new ItemStack(TFCItems.powder, 1, 4));
/* 144 */     OreDictionary.registerOre("dustLapis", new ItemStack(TFCItems.powder, 1, 6));
/* 145 */     OreDictionary.registerOre("dustSalt", new ItemStack(TFCItems.powder, 1, 9));
/*     */ 
/*     */     
/* 148 */     OreDictionary.registerOre("ingotBismuth", new ItemStack(TFCItems.bismuthIngot));
/* 149 */     OreDictionary.registerOre("ingotTin", new ItemStack(TFCItems.tinIngot));
/* 150 */     OreDictionary.registerOre("ingotZinc", new ItemStack(TFCItems.zincIngot));
/* 151 */     OreDictionary.registerOre("ingotCopper", new ItemStack(TFCItems.copperIngot));
/* 152 */     OreDictionary.registerOre("ingotBronze", new ItemStack(TFCItems.bronzeIngot));
/* 153 */     OreDictionary.registerOre("ingotBismuthBronze", new ItemStack(TFCItems.bismuthBronzeIngot));
/* 154 */     OreDictionary.registerOre("ingotBlackBronze", new ItemStack(TFCItems.blackBronzeIngot));
/* 155 */     OreDictionary.registerOre("ingotBrass", new ItemStack(TFCItems.brassIngot));
/* 156 */     OreDictionary.registerOre("ingotLead", new ItemStack(TFCItems.leadIngot));
/* 157 */     OreDictionary.registerOre("ingotGold", new ItemStack(TFCItems.goldIngot));
/* 158 */     OreDictionary.registerOre("ingotRoseGold", new ItemStack(TFCItems.roseGoldIngot));
/* 159 */     OreDictionary.registerOre("ingotSilver", new ItemStack(TFCItems.silverIngot));
/* 160 */     OreDictionary.registerOre("ingotSterlingSilver", new ItemStack(TFCItems.sterlingSilverIngot));
/* 161 */     OreDictionary.registerOre("ingotPlatinum", new ItemStack(TFCItems.platinumIngot));
/* 162 */     OreDictionary.registerOre("ingotWroughtIron", new ItemStack(TFCItems.wroughtIronIngot));
/* 163 */     OreDictionary.registerOre("ingotIron", new ItemStack(TFCItems.wroughtIronIngot));
/* 164 */     OreDictionary.registerOre("ingotNickel", new ItemStack(TFCItems.nickelIngot));
/* 165 */     OreDictionary.registerOre("ingotPigIron", new ItemStack(TFCItems.pigIronIngot));
/* 166 */     OreDictionary.registerOre("ingotSteel", new ItemStack(TFCItems.steelIngot));
/* 167 */     OreDictionary.registerOre("ingotBlackSteel", new ItemStack(TFCItems.blackSteelIngot));
/* 168 */     OreDictionary.registerOre("ingotRedSteel", new ItemStack(TFCItems.redSteelIngot));
/* 169 */     OreDictionary.registerOre("ingotBlueSteel", new ItemStack(TFCItems.blueSteelIngot));
/* 170 */     OreDictionary.registerOre("ingotUnknown", new ItemStack(TFCItems.unknownIngot));
/*     */     
/* 172 */     OreDictionary.registerOre("ingotAnyBronze", new ItemStack(TFCItems.bronzeIngot));
/* 173 */     OreDictionary.registerOre("ingotAnyBronze", new ItemStack(TFCItems.bismuthBronzeIngot));
/* 174 */     OreDictionary.registerOre("ingotAnyBronze", new ItemStack(TFCItems.blackBronzeIngot));
/*     */ 
/*     */     
/* 177 */     OreDictionary.registerOre("ingotDoubleBismuth", new ItemStack(TFCItems.bismuthIngot2x));
/* 178 */     OreDictionary.registerOre("ingotDoubleTin", new ItemStack(TFCItems.tinIngot2x));
/* 179 */     OreDictionary.registerOre("ingotDoubleZinc", new ItemStack(TFCItems.zincIngot2x));
/* 180 */     OreDictionary.registerOre("ingotDoubleCopper", new ItemStack(TFCItems.copperIngot2x));
/* 181 */     OreDictionary.registerOre("ingotDoubleBronze", new ItemStack(TFCItems.bronzeIngot2x));
/* 182 */     OreDictionary.registerOre("ingotDoubleBismuthBronze", new ItemStack(TFCItems.bismuthBronzeIngot2x));
/* 183 */     OreDictionary.registerOre("ingotDoubleBlackBronze", new ItemStack(TFCItems.blackBronzeIngot2x));
/* 184 */     OreDictionary.registerOre("ingotDoubleBrass", new ItemStack(TFCItems.brassIngot2x));
/* 185 */     OreDictionary.registerOre("ingotDoubleLead", new ItemStack(TFCItems.leadIngot2x));
/* 186 */     OreDictionary.registerOre("ingotDoubleGold", new ItemStack(TFCItems.goldIngot2x));
/* 187 */     OreDictionary.registerOre("ingotDoubleRoseGold", new ItemStack(TFCItems.roseGoldIngot2x));
/* 188 */     OreDictionary.registerOre("ingotDoubleSilver", new ItemStack(TFCItems.silverIngot2x));
/* 189 */     OreDictionary.registerOre("ingotDoubleSterlingSilver", new ItemStack(TFCItems.sterlingSilverIngot2x));
/* 190 */     OreDictionary.registerOre("ingotDoublePlatinum", new ItemStack(TFCItems.platinumIngot2x));
/* 191 */     OreDictionary.registerOre("ingotDoubleWroughtIron", new ItemStack(TFCItems.wroughtIronIngot2x));
/* 192 */     OreDictionary.registerOre("ingotDoubleNickel", new ItemStack(TFCItems.nickelIngot2x));
/* 193 */     OreDictionary.registerOre("ingotDoublePigIron", new ItemStack(TFCItems.pigIronIngot2x));
/* 194 */     OreDictionary.registerOre("ingotDoubleSteel", new ItemStack(TFCItems.steelIngot2x));
/* 195 */     OreDictionary.registerOre("ingotDoubleBlackSteel", new ItemStack(TFCItems.blackSteelIngot2x));
/* 196 */     OreDictionary.registerOre("ingotDoubleRedSteel", new ItemStack(TFCItems.redSteelIngot2x));
/* 197 */     OreDictionary.registerOre("ingotDoubleBlueSteel", new ItemStack(TFCItems.blueSteelIngot2x));
/*     */     
/* 199 */     OreDictionary.registerOre("ingotDoubleAnyBronze", new ItemStack(TFCItems.bronzeIngot2x));
/* 200 */     OreDictionary.registerOre("ingotDoubleAnyBronze", new ItemStack(TFCItems.bismuthBronzeIngot2x));
/* 201 */     OreDictionary.registerOre("ingotDoubleAnyBronze", new ItemStack(TFCItems.blackBronzeIngot2x));
/*     */ 
/*     */     
/* 204 */     OreDictionary.registerOre("plateBismuth", new ItemStack(TFCItems.bismuthSheet));
/* 205 */     OreDictionary.registerOre("plateTin", new ItemStack(TFCItems.tinSheet));
/* 206 */     OreDictionary.registerOre("plateZinc", new ItemStack(TFCItems.zincSheet));
/* 207 */     OreDictionary.registerOre("plateCopper", new ItemStack(TFCItems.copperSheet));
/* 208 */     OreDictionary.registerOre("plateBronze", new ItemStack(TFCItems.bronzeSheet));
/* 209 */     OreDictionary.registerOre("plateBismuthBronze", new ItemStack(TFCItems.bismuthBronzeSheet));
/* 210 */     OreDictionary.registerOre("plateBlackBronze", new ItemStack(TFCItems.blackBronzeSheet));
/* 211 */     OreDictionary.registerOre("plateBrass", new ItemStack(TFCItems.brassSheet));
/* 212 */     OreDictionary.registerOre("plateLead", new ItemStack(TFCItems.leadSheet));
/* 213 */     OreDictionary.registerOre("plateGold", new ItemStack(TFCItems.goldSheet));
/* 214 */     OreDictionary.registerOre("plateRoseGold", new ItemStack(TFCItems.roseGoldSheet));
/* 215 */     OreDictionary.registerOre("plateSilver", new ItemStack(TFCItems.silverSheet));
/* 216 */     OreDictionary.registerOre("plateSterlingSilver", new ItemStack(TFCItems.sterlingSilverSheet));
/* 217 */     OreDictionary.registerOre("platePlatinum", new ItemStack(TFCItems.platinumSheet));
/* 218 */     OreDictionary.registerOre("plateWroughtIron", new ItemStack(TFCItems.wroughtIronSheet));
/* 219 */     OreDictionary.registerOre("plateIron", new ItemStack(TFCItems.wroughtIronSheet));
/* 220 */     OreDictionary.registerOre("plateNickel", new ItemStack(TFCItems.nickelSheet));
/* 221 */     OreDictionary.registerOre("platePigIron", new ItemStack(TFCItems.pigIronSheet));
/* 222 */     OreDictionary.registerOre("plateSteel", new ItemStack(TFCItems.steelSheet));
/* 223 */     OreDictionary.registerOre("plateBlackSteel", new ItemStack(TFCItems.blackSteelSheet));
/* 224 */     OreDictionary.registerOre("plateRedSteel", new ItemStack(TFCItems.redSteelSheet));
/* 225 */     OreDictionary.registerOre("plateBlueSteel", new ItemStack(TFCItems.blueSteelSheet));
/*     */     
/* 227 */     OreDictionary.registerOre("plateAnyBronze", new ItemStack(TFCItems.bronzeSheet));
/* 228 */     OreDictionary.registerOre("plateAnyBronze", new ItemStack(TFCItems.bismuthBronzeSheet));
/* 229 */     OreDictionary.registerOre("plateAnyBronze", new ItemStack(TFCItems.blackBronzeSheet));
/*     */ 
/*     */     
/* 232 */     OreDictionary.registerOre("plateDoubleBismuth", new ItemStack(TFCItems.bismuthSheet2x));
/* 233 */     OreDictionary.registerOre("plateDoubleTin", new ItemStack(TFCItems.tinSheet2x));
/* 234 */     OreDictionary.registerOre("plateDoubleZinc", new ItemStack(TFCItems.zincSheet2x));
/* 235 */     OreDictionary.registerOre("plateDoubleCopper", new ItemStack(TFCItems.copperSheet2x));
/* 236 */     OreDictionary.registerOre("plateDoubleBronze", new ItemStack(TFCItems.bronzeSheet2x));
/* 237 */     OreDictionary.registerOre("plateDoubleBismuthBronze", new ItemStack(TFCItems.bismuthBronzeSheet2x));
/* 238 */     OreDictionary.registerOre("plateDoubleBlackBronze", new ItemStack(TFCItems.blackBronzeSheet2x));
/* 239 */     OreDictionary.registerOre("plateDoubleBrass", new ItemStack(TFCItems.brassSheet2x));
/* 240 */     OreDictionary.registerOre("plateDoubleLead", new ItemStack(TFCItems.leadSheet2x));
/* 241 */     OreDictionary.registerOre("plateDoubleGold", new ItemStack(TFCItems.goldSheet2x));
/* 242 */     OreDictionary.registerOre("plateDoubleRoseGold", new ItemStack(TFCItems.roseGoldSheet2x));
/* 243 */     OreDictionary.registerOre("plateDoubleSilver", new ItemStack(TFCItems.silverSheet2x));
/* 244 */     OreDictionary.registerOre("plateDoubleSterlingSilver", new ItemStack(TFCItems.sterlingSilverSheet2x));
/* 245 */     OreDictionary.registerOre("plateDoublePlatinum", new ItemStack(TFCItems.platinumSheet2x));
/* 246 */     OreDictionary.registerOre("plateDoubleWroughtIron", new ItemStack(TFCItems.wroughtIronSheet2x));
/* 247 */     OreDictionary.registerOre("plateDoubleNickel", new ItemStack(TFCItems.nickelSheet2x));
/* 248 */     OreDictionary.registerOre("plateDoublePigIron", new ItemStack(TFCItems.pigIronSheet2x));
/* 249 */     OreDictionary.registerOre("plateDoubleSteel", new ItemStack(TFCItems.steelSheet2x));
/* 250 */     OreDictionary.registerOre("plateDoubleBlackSteel", new ItemStack(TFCItems.blackSteelSheet2x));
/* 251 */     OreDictionary.registerOre("plateDoubleRedSteel", new ItemStack(TFCItems.redSteelSheet2x));
/* 252 */     OreDictionary.registerOre("plateDoubleBlueSteel", new ItemStack(TFCItems.blueSteelSheet2x));
/*     */     
/* 254 */     OreDictionary.registerOre("plateDoubleAnyBronze", new ItemStack(TFCItems.bronzeSheet2x));
/* 255 */     OreDictionary.registerOre("plateDoubleAnyBronze", new ItemStack(TFCItems.bismuthBronzeSheet2x));
/* 256 */     OreDictionary.registerOre("plateDoubleAnyBronze", new ItemStack(TFCItems.blackBronzeSheet2x));
/*     */ 
/*     */     
/* 259 */     OreDictionary.registerOre("gemChippedAgate", new ItemStack(TFCItems.gemAgate));
/* 260 */     OreDictionary.registerOre("gemChippedAmethyst", new ItemStack(TFCItems.gemAmethyst));
/* 261 */     OreDictionary.registerOre("gemChippedBeryl", new ItemStack(TFCItems.gemBeryl));
/* 262 */     OreDictionary.registerOre("gemChippedDiamond", new ItemStack(TFCItems.gemDiamond));
/* 263 */     OreDictionary.registerOre("gemChippedEmerald", new ItemStack(TFCItems.gemEmerald));
/* 264 */     OreDictionary.registerOre("gemChippedGarnet", new ItemStack(TFCItems.gemGarnet));
/* 265 */     OreDictionary.registerOre("gemChippedJade", new ItemStack(TFCItems.gemJade));
/* 266 */     OreDictionary.registerOre("gemChippedJasper", new ItemStack(TFCItems.gemJasper));
/* 267 */     OreDictionary.registerOre("gemChippedOpal", new ItemStack(TFCItems.gemOpal));
/* 268 */     OreDictionary.registerOre("gemChippedRuby", new ItemStack(TFCItems.gemRuby));
/* 269 */     OreDictionary.registerOre("gemChippedSapphire", new ItemStack(TFCItems.gemSapphire));
/* 270 */     OreDictionary.registerOre("gemChippedTopaz", new ItemStack(TFCItems.gemTopaz));
/* 271 */     OreDictionary.registerOre("gemChippedTourmaline", new ItemStack(TFCItems.gemTourmaline));
/*     */     
/* 273 */     OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.gemAgate));
/* 274 */     OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.gemAmethyst));
/* 275 */     OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.gemBeryl));
/* 276 */     OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.gemDiamond));
/* 277 */     OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.gemEmerald));
/* 278 */     OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.gemGarnet));
/* 279 */     OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.gemJade));
/* 280 */     OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.gemJasper));
/* 281 */     OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.gemOpal));
/* 282 */     OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.gemRuby));
/* 283 */     OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.gemSapphire));
/* 284 */     OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.gemTopaz));
/* 285 */     OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.gemTourmaline));
/*     */     
/* 287 */     OreDictionary.registerOre("gemFlawedAgate", new ItemStack(TFCItems.gemAgate, 1, 1));
/* 288 */     OreDictionary.registerOre("gemFlawedAmethyst", new ItemStack(TFCItems.gemAmethyst, 1, 1));
/* 289 */     OreDictionary.registerOre("gemFlawedBeryl", new ItemStack(TFCItems.gemBeryl, 1, 1));
/* 290 */     OreDictionary.registerOre("gemFlawedDiamond", new ItemStack(TFCItems.gemDiamond, 1, 1));
/* 291 */     OreDictionary.registerOre("gemFlawedEmerald", new ItemStack(TFCItems.gemEmerald, 1, 1));
/* 292 */     OreDictionary.registerOre("gemFlawedGarnet", new ItemStack(TFCItems.gemGarnet, 1, 1));
/* 293 */     OreDictionary.registerOre("gemFlawedJade", new ItemStack(TFCItems.gemJade, 1, 1));
/* 294 */     OreDictionary.registerOre("gemFlawedJasper", new ItemStack(TFCItems.gemJasper, 1, 1));
/* 295 */     OreDictionary.registerOre("gemFlawedOpal", new ItemStack(TFCItems.gemOpal, 1, 1));
/* 296 */     OreDictionary.registerOre("gemFlawedRuby", new ItemStack(TFCItems.gemRuby, 1, 1));
/* 297 */     OreDictionary.registerOre("gemFlawedSapphire", new ItemStack(TFCItems.gemSapphire, 1, 1));
/* 298 */     OreDictionary.registerOre("gemFlawedTopaz", new ItemStack(TFCItems.gemTopaz, 1, 1));
/* 299 */     OreDictionary.registerOre("gemFlawedTourmaline", new ItemStack(TFCItems.gemTourmaline, 1, 1));
/*     */     
/* 301 */     OreDictionary.registerOre("gemAgate", new ItemStack(TFCItems.gemAgate, 1, 2));
/* 302 */     OreDictionary.registerOre("gemAmethyst", new ItemStack(TFCItems.gemAmethyst, 1, 2));
/* 303 */     OreDictionary.registerOre("gemBeryl", new ItemStack(TFCItems.gemBeryl, 1, 2));
/* 304 */     OreDictionary.registerOre("gemDiamond", new ItemStack(TFCItems.gemDiamond, 1, 2));
/* 305 */     OreDictionary.registerOre("gemEmerald", new ItemStack(TFCItems.gemEmerald, 1, 2));
/* 306 */     OreDictionary.registerOre("gemGarnet", new ItemStack(TFCItems.gemGarnet, 1, 2));
/* 307 */     OreDictionary.registerOre("gemJade", new ItemStack(TFCItems.gemJade, 1, 2));
/* 308 */     OreDictionary.registerOre("gemJasper", new ItemStack(TFCItems.gemJasper, 1, 2));
/* 309 */     OreDictionary.registerOre("gemOpal", new ItemStack(TFCItems.gemOpal, 1, 2));
/* 310 */     OreDictionary.registerOre("gemRuby", new ItemStack(TFCItems.gemRuby, 1, 2));
/* 311 */     OreDictionary.registerOre("gemSapphire", new ItemStack(TFCItems.gemSapphire, 1, 2));
/* 312 */     OreDictionary.registerOre("gemTopaz", new ItemStack(TFCItems.gemTopaz, 1, 2));
/* 313 */     OreDictionary.registerOre("gemTourmaline", new ItemStack(TFCItems.gemTourmaline, 1, 2));
/*     */     
/* 315 */     OreDictionary.registerOre("gemFlawlessAgate", new ItemStack(TFCItems.gemAgate, 1, 3));
/* 316 */     OreDictionary.registerOre("gemFlawlessAmethyst", new ItemStack(TFCItems.gemAmethyst, 1, 3));
/* 317 */     OreDictionary.registerOre("gemFlawlessBeryl", new ItemStack(TFCItems.gemBeryl, 1, 3));
/* 318 */     OreDictionary.registerOre("gemFlawlessDiamond", new ItemStack(TFCItems.gemDiamond, 1, 3));
/* 319 */     OreDictionary.registerOre("gemFlawlessEmerald", new ItemStack(TFCItems.gemEmerald, 1, 3));
/* 320 */     OreDictionary.registerOre("gemFlawlessGarnet", new ItemStack(TFCItems.gemGarnet, 1, 3));
/* 321 */     OreDictionary.registerOre("gemFlawlessJade", new ItemStack(TFCItems.gemJade, 1, 3));
/* 322 */     OreDictionary.registerOre("gemFlawlessJasper", new ItemStack(TFCItems.gemJasper, 1, 3));
/* 323 */     OreDictionary.registerOre("gemFlawlessOpal", new ItemStack(TFCItems.gemOpal, 1, 3));
/* 324 */     OreDictionary.registerOre("gemFlawlessRuby", new ItemStack(TFCItems.gemRuby, 1, 3));
/* 325 */     OreDictionary.registerOre("gemFlawlessSapphire", new ItemStack(TFCItems.gemSapphire, 1, 3));
/* 326 */     OreDictionary.registerOre("gemFlawlessTopaz", new ItemStack(TFCItems.gemTopaz, 1, 3));
/* 327 */     OreDictionary.registerOre("gemFlawlessTourmaline", new ItemStack(TFCItems.gemTourmaline, 1, 3));
/*     */     
/* 329 */     OreDictionary.registerOre("gemExquisiteAgate", new ItemStack(TFCItems.gemAgate, 1, 4));
/* 330 */     OreDictionary.registerOre("gemExquisiteAmethyst", new ItemStack(TFCItems.gemAmethyst, 1, 4));
/* 331 */     OreDictionary.registerOre("gemExquisiteBeryl", new ItemStack(TFCItems.gemBeryl, 1, 4));
/* 332 */     OreDictionary.registerOre("gemExquisiteDiamond", new ItemStack(TFCItems.gemDiamond, 1, 4));
/* 333 */     OreDictionary.registerOre("gemExquisiteEmerald", new ItemStack(TFCItems.gemEmerald, 1, 4));
/* 334 */     OreDictionary.registerOre("gemExquisiteGarnet", new ItemStack(TFCItems.gemGarnet, 1, 4));
/* 335 */     OreDictionary.registerOre("gemExquisiteJade", new ItemStack(TFCItems.gemJade, 1, 4));
/* 336 */     OreDictionary.registerOre("gemExquisiteJasper", new ItemStack(TFCItems.gemJasper, 1, 4));
/* 337 */     OreDictionary.registerOre("gemExquisiteOpal", new ItemStack(TFCItems.gemOpal, 1, 4));
/* 338 */     OreDictionary.registerOre("gemExquisiteRuby", new ItemStack(TFCItems.gemRuby, 1, 4));
/* 339 */     OreDictionary.registerOre("gemExquisiteSapphire", new ItemStack(TFCItems.gemSapphire, 1, 4));
/* 340 */     OreDictionary.registerOre("gemExquisiteTopaz", new ItemStack(TFCItems.gemTopaz, 1, 4));
/* 341 */     OreDictionary.registerOre("gemExquisiteTourmaline", new ItemStack(TFCItems.gemTourmaline, 1, 4));
/*     */ 
/*     */     
/* 344 */     OreDictionary.registerOre("gemCoal", new ItemStack(Items.field_151044_h, 1, 0));
/* 345 */     OreDictionary.registerOre("gemCoal", new ItemStack(TFCItems.coal, 1, 0));
/* 346 */     OreDictionary.registerOre("gemCharcoal", new ItemStack(Items.field_151044_h, 1, 1));
/* 347 */     OreDictionary.registerOre("gemCharcoal", new ItemStack(TFCItems.coal, 1, 1));
/*     */ 
/*     */     
/* 350 */     OreDictionary.registerOre("stone", new ItemStack(TFCBlocks.stoneIgEx, 1, 32767));
/* 351 */     OreDictionary.registerOre("stone", new ItemStack(TFCBlocks.stoneIgIn, 1, 32767));
/* 352 */     OreDictionary.registerOre("stone", new ItemStack(TFCBlocks.stoneMM, 1, 32767));
/* 353 */     OreDictionary.registerOre("stone", new ItemStack(TFCBlocks.stoneSed, 1, 32767));
/*     */ 
/*     */     
/* 356 */     OreDictionary.registerOre("cobblestone", new ItemStack(TFCBlocks.stoneIgExCobble, 1, 32767));
/* 357 */     OreDictionary.registerOre("cobblestone", new ItemStack(TFCBlocks.stoneIgInCobble, 1, 32767));
/* 358 */     OreDictionary.registerOre("cobblestone", new ItemStack(TFCBlocks.stoneMMCobble, 1, 32767));
/* 359 */     OreDictionary.registerOre("cobblestone", new ItemStack(TFCBlocks.stoneSedCobble, 1, 32767));
/*     */     
/* 361 */     OreDictionary.registerOre("stoneCobble", new ItemStack(TFCBlocks.stoneIgExCobble, 1, 32767));
/* 362 */     OreDictionary.registerOre("stoneCobble", new ItemStack(TFCBlocks.stoneIgInCobble, 1, 32767));
/* 363 */     OreDictionary.registerOre("stoneCobble", new ItemStack(TFCBlocks.stoneMMCobble, 1, 32767));
/* 364 */     OreDictionary.registerOre("stoneCobble", new ItemStack(TFCBlocks.stoneSedCobble, 1, 32767));
/*     */ 
/*     */     
/* 367 */     OreDictionary.registerOre("stoneBricks", new ItemStack(Blocks.field_150417_aV));
/* 368 */     OreDictionary.registerOre("stoneBricks", new ItemStack(TFCBlocks.stoneIgExBrick, 1, 32767));
/* 369 */     OreDictionary.registerOre("stoneBricks", new ItemStack(TFCBlocks.stoneIgInBrick, 1, 32767));
/* 370 */     OreDictionary.registerOre("stoneBricks", new ItemStack(TFCBlocks.stoneMMBrick, 1, 32767));
/* 371 */     OreDictionary.registerOre("stoneBricks", new ItemStack(TFCBlocks.stoneSedBrick, 1, 32767));
/*     */ 
/*     */     
/* 374 */     OreDictionary.registerOre("stoneSmooth", new ItemStack(TFCBlocks.stoneIgExSmooth, 1, 32767));
/* 375 */     OreDictionary.registerOre("stoneSmooth", new ItemStack(TFCBlocks.stoneIgInSmooth, 1, 32767));
/* 376 */     OreDictionary.registerOre("stoneSmooth", new ItemStack(TFCBlocks.stoneMMSmooth, 1, 32767));
/* 377 */     OreDictionary.registerOre("stoneSmooth", new ItemStack(TFCBlocks.stoneSedSmooth, 1, 32767));
/*     */ 
/*     */     
/* 380 */     OreDictionary.registerOre("craftingTableWood", new ItemStack(TFCBlocks.workbench));
/* 381 */     OreDictionary.registerOre("craftingTableWood", new ItemStack(Blocks.field_150462_ai));
/*     */ 
/*     */     
/* 384 */     OreDictionary.registerOre("dyeBlack", new ItemStack(TFCItems.dye, 1, 0));
/* 385 */     OreDictionary.registerOre("dyeRed", new ItemStack(TFCItems.powder, 1, 5));
/* 386 */     OreDictionary.registerOre("dyeRed", new ItemStack(TFCItems.dye, 1, 1));
/* 387 */     OreDictionary.registerOre("dyeGreen", new ItemStack(TFCItems.powder, 1, 8));
/* 388 */     OreDictionary.registerOre("dyeGreen", new ItemStack(TFCItems.dye, 1, 2));
/* 389 */     OreDictionary.registerOre("dyeBrown", new ItemStack(TFCItems.dye, 1, 3));
/* 390 */     OreDictionary.registerOre("dyeBlue", new ItemStack(TFCItems.powder, 1, 6));
/* 391 */     OreDictionary.registerOre("dyeBlue", new ItemStack(TFCItems.dye, 1, 4));
/* 392 */     OreDictionary.registerOre("dyePurple", new ItemStack(TFCItems.dye, 1, 5));
/* 393 */     OreDictionary.registerOre("dyeCyan", new ItemStack(TFCItems.dye, 1, 6));
/* 394 */     OreDictionary.registerOre("dyeLightGray", new ItemStack(TFCItems.dye, 1, 7));
/* 395 */     OreDictionary.registerOre("dyeGray", new ItemStack(TFCItems.dye, 1, 8));
/* 396 */     OreDictionary.registerOre("dyePink", new ItemStack(TFCItems.dye, 1, 9));
/* 397 */     OreDictionary.registerOre("dyeLime", new ItemStack(TFCItems.dye, 1, 10));
/* 398 */     OreDictionary.registerOre("dyeYellow", new ItemStack(TFCItems.powder, 1, 7));
/* 399 */     OreDictionary.registerOre("dyeYellow", new ItemStack(TFCItems.dye, 1, 11));
/* 400 */     OreDictionary.registerOre("dyeLightBlue", new ItemStack(TFCItems.dye, 1, 12));
/* 401 */     OreDictionary.registerOre("dyeMagenta", new ItemStack(TFCItems.dye, 1, 13));
/* 402 */     OreDictionary.registerOre("dyeOrange", new ItemStack(TFCItems.dye, 1, 14));
/* 403 */     OreDictionary.registerOre("dyeWhite", new ItemStack(TFCItems.dye, 1, 15));
/*     */ 
/*     */     
/* 406 */     OreDictionary.registerOre("materialLeather", new ItemStack(Items.field_151116_aA));
/* 407 */     OreDictionary.registerOre("materialLeather", new ItemStack(TFCItems.leather));
/*     */     
/* 409 */     OreDictionary.registerOre("materialString", new ItemStack(Items.field_151007_F));
/* 410 */     OreDictionary.registerOre("materialString", new ItemStack(TFCItems.woolYarn));
/*     */     
/* 412 */     OreDictionary.registerOre("materialCloth", new ItemStack(TFCItems.woolCloth));
/* 413 */     OreDictionary.registerOre("materialCloth", new ItemStack(TFCItems.silkCloth));
/* 414 */     OreDictionary.registerOre("materialWool", new ItemStack(TFCItems.wool, 1, 32767));
/*     */ 
/*     */     
/* 417 */     OreDictionary.registerOre("itemAxeStoneHead", new ItemStack(TFCItems.igExStoneAxeHead, 1, 32767));
/* 418 */     OreDictionary.registerOre("itemAxeStoneHead", new ItemStack(TFCItems.igInStoneAxeHead, 1, 32767));
/* 419 */     OreDictionary.registerOre("itemAxeStoneHead", new ItemStack(TFCItems.mMStoneAxeHead, 1, 32767));
/* 420 */     OreDictionary.registerOre("itemAxeStoneHead", new ItemStack(TFCItems.sedStoneAxeHead, 1, 32767));
/*     */     
/* 422 */     OreDictionary.registerOre("itemHoeStoneHead", new ItemStack(TFCItems.igExStoneHoeHead, 1, 32767));
/* 423 */     OreDictionary.registerOre("itemHoeStoneHead", new ItemStack(TFCItems.igInStoneHoeHead, 1, 32767));
/* 424 */     OreDictionary.registerOre("itemHoeStoneHead", new ItemStack(TFCItems.mMStoneHoeHead, 1, 32767));
/* 425 */     OreDictionary.registerOre("itemHoeStoneHead", new ItemStack(TFCItems.sedStoneHoeHead, 1, 32767));
/*     */     
/* 427 */     OreDictionary.registerOre("itemShovelStoneHead", new ItemStack(TFCItems.igExStoneShovelHead, 1, 32767));
/* 428 */     OreDictionary.registerOre("itemShovelStoneHead", new ItemStack(TFCItems.igInStoneShovelHead, 1, 32767));
/* 429 */     OreDictionary.registerOre("itemShovelStoneHead", new ItemStack(TFCItems.mMStoneShovelHead, 1, 32767));
/* 430 */     OreDictionary.registerOre("itemShovelStoneHead", new ItemStack(TFCItems.sedStoneShovelHead, 1, 32767));
/*     */     
/* 432 */     OreDictionary.registerOre("itemJavelinStoneHead", new ItemStack(TFCItems.igExStoneJavelinHead, 1, 32767));
/* 433 */     OreDictionary.registerOre("itemJavelinStoneHead", new ItemStack(TFCItems.igInStoneJavelinHead, 1, 32767));
/* 434 */     OreDictionary.registerOre("itemJavelinStoneHead", new ItemStack(TFCItems.mMStoneJavelinHead, 1, 32767));
/* 435 */     OreDictionary.registerOre("itemJavelinStoneHead", new ItemStack(TFCItems.sedStoneJavelinHead, 1, 32767));
/*     */ 
/*     */     
/* 438 */     for (Item axe : Recipes.axes) {
/* 439 */       OreDictionary.registerOre("itemAxe", new ItemStack(axe, 1, 32767));
/*     */     }
/* 441 */     OreDictionary.registerOre("itemAxeStone", new ItemStack(TFCItems.sedAxe, 1, 32767));
/* 442 */     OreDictionary.registerOre("itemAxeStone", new ItemStack(TFCItems.igInAxe, 1, 32767));
/* 443 */     OreDictionary.registerOre("itemAxeStone", new ItemStack(TFCItems.igExAxe, 1, 32767));
/* 444 */     OreDictionary.registerOre("itemAxeStone", new ItemStack(TFCItems.mMAxe, 1, 32767));
/* 445 */     OreDictionary.registerOre("itemAxeBismuthBronze", new ItemStack(TFCItems.bismuthBronzeAxe, 1, 32767));
/* 446 */     OreDictionary.registerOre("itemAxeBlackBronze", new ItemStack(TFCItems.blackBronzeAxe, 1, 32767));
/* 447 */     OreDictionary.registerOre("itemAxeBlackSteel", new ItemStack(TFCItems.blackSteelAxe, 1, 32767));
/* 448 */     OreDictionary.registerOre("itemAxeBlueSteel", new ItemStack(TFCItems.blueSteelAxe, 1, 32767));
/* 449 */     OreDictionary.registerOre("itemAxeBronze", new ItemStack(TFCItems.bronzeAxe, 1, 32767));
/* 450 */     OreDictionary.registerOre("itemAxeCopper", new ItemStack(TFCItems.copperAxe, 1, 32767));
/* 451 */     OreDictionary.registerOre("itemAxeWroughtIron", new ItemStack(TFCItems.wroughtIronAxe, 1, 32767));
/* 452 */     OreDictionary.registerOre("itemAxeRedSteel", new ItemStack(TFCItems.redSteelAxe, 1, 32767));
/* 453 */     OreDictionary.registerOre("itemAxeSteel", new ItemStack(TFCItems.steelAxe, 1, 32767));
/*     */     
/* 455 */     for (Item chisel : Recipes.chisels) {
/* 456 */       OreDictionary.registerOre("itemChisel", new ItemStack(chisel, 1, 32767));
/*     */     }
/* 458 */     OreDictionary.registerOre("itemChiselBismuthBronze", new ItemStack(TFCItems.bismuthBronzeChisel, 1, 32767));
/* 459 */     OreDictionary.registerOre("itemChiselBlackBronze", new ItemStack(TFCItems.blackBronzeChisel, 1, 32767));
/* 460 */     OreDictionary.registerOre("itemChiselBlackSteel", new ItemStack(TFCItems.blackSteelChisel, 1, 32767));
/* 461 */     OreDictionary.registerOre("itemChiselBlueSteel", new ItemStack(TFCItems.blueSteelChisel, 1, 32767));
/* 462 */     OreDictionary.registerOre("itemChiselBronze", new ItemStack(TFCItems.bronzeChisel, 1, 32767));
/* 463 */     OreDictionary.registerOre("itemChiselCopper", new ItemStack(TFCItems.copperChisel, 1, 32767));
/* 464 */     OreDictionary.registerOre("itemChiselWroughtIron", new ItemStack(TFCItems.wroughtIronChisel, 1, 32767));
/* 465 */     OreDictionary.registerOre("itemChiselRedSteel", new ItemStack(TFCItems.redSteelChisel, 1, 32767));
/* 466 */     OreDictionary.registerOre("itemChiselSteel", new ItemStack(TFCItems.steelChisel, 1, 32767));
/*     */     
/* 468 */     for (Item hammer : Recipes.hammers) {
/* 469 */       OreDictionary.registerOre("itemHammer", new ItemStack(hammer, 1, 32767));
/*     */     }
/* 471 */     OreDictionary.registerOre("itemHammerStone", new ItemStack(TFCItems.stoneHammer, 1, 32767));
/* 472 */     OreDictionary.registerOre("itemHammerBismuthBronze", new ItemStack(TFCItems.bismuthBronzeHammer, 1, 32767));
/* 473 */     OreDictionary.registerOre("itemHammerBlackBronze", new ItemStack(TFCItems.blackBronzeHammer, 1, 32767));
/* 474 */     OreDictionary.registerOre("itemHammerBlackSteel", new ItemStack(TFCItems.blackSteelHammer, 1, 32767));
/* 475 */     OreDictionary.registerOre("itemHammerBlueSteel", new ItemStack(TFCItems.blueSteelHammer, 1, 32767));
/* 476 */     OreDictionary.registerOre("itemHammerBronze", new ItemStack(TFCItems.bronzeHammer, 1, 32767));
/* 477 */     OreDictionary.registerOre("itemHammerCopper", new ItemStack(TFCItems.copperHammer, 1, 32767));
/* 478 */     OreDictionary.registerOre("itemHammerWroughtIron", new ItemStack(TFCItems.wroughtIronHammer, 1, 32767));
/* 479 */     OreDictionary.registerOre("itemHammerRedSteel", new ItemStack(TFCItems.redSteelHammer, 1, 32767));
/* 480 */     OreDictionary.registerOre("itemHammerSteel", new ItemStack(TFCItems.steelHammer, 1, 32767));
/*     */     
/* 482 */     for (Item knife : Recipes.knives) {
/* 483 */       OreDictionary.registerOre("itemKnife", new ItemStack(knife, 1, 32767));
/*     */     }
/* 485 */     OreDictionary.registerOre("itemKnifeStone", new ItemStack(TFCItems.stoneKnife, 1, 32767));
/* 486 */     OreDictionary.registerOre("itemKnifeBismuthBronze", new ItemStack(TFCItems.bismuthBronzeKnife, 1, 32767));
/* 487 */     OreDictionary.registerOre("itemKnifeBlackBronze", new ItemStack(TFCItems.blackBronzeKnife, 1, 32767));
/* 488 */     OreDictionary.registerOre("itemKnifeBlackSteel", new ItemStack(TFCItems.blackSteelKnife, 1, 32767));
/* 489 */     OreDictionary.registerOre("itemKnifeBlueSteel", new ItemStack(TFCItems.blueSteelKnife, 1, 32767));
/* 490 */     OreDictionary.registerOre("itemKnifeBronze", new ItemStack(TFCItems.bronzeKnife, 1, 32767));
/* 491 */     OreDictionary.registerOre("itemKnifeCopper", new ItemStack(TFCItems.copperKnife, 1, 32767));
/* 492 */     OreDictionary.registerOre("itemKnifeWroughtIron", new ItemStack(TFCItems.wroughtIronKnife, 1, 32767));
/* 493 */     OreDictionary.registerOre("itemKnifeRedSteel", new ItemStack(TFCItems.redSteelKnife, 1, 32767));
/* 494 */     OreDictionary.registerOre("itemKnifeSteel", new ItemStack(TFCItems.steelKnife, 1, 32767));
/*     */     
/* 496 */     for (Item saw : Recipes.saws) {
/* 497 */       OreDictionary.registerOre("itemSaw", new ItemStack(saw, 1, 32767));
/*     */     }
/* 499 */     OreDictionary.registerOre("itemSawBismuthBronze", new ItemStack(TFCItems.bismuthBronzeSaw, 1, 32767));
/* 500 */     OreDictionary.registerOre("itemSawBlackBronze", new ItemStack(TFCItems.blackBronzeSaw, 1, 32767));
/* 501 */     OreDictionary.registerOre("itemSawBlackSteel", new ItemStack(TFCItems.blackSteelSaw, 1, 32767));
/* 502 */     OreDictionary.registerOre("itemSawBlueSteel", new ItemStack(TFCItems.blueSteelSaw, 1, 32767));
/* 503 */     OreDictionary.registerOre("itemSawBronze", new ItemStack(TFCItems.bronzeSaw, 1, 32767));
/* 504 */     OreDictionary.registerOre("itemSawCopper", new ItemStack(TFCItems.copperSaw, 1, 32767));
/* 505 */     OreDictionary.registerOre("itemSawWroughtIron", new ItemStack(TFCItems.wroughtIronSaw, 1, 32767));
/* 506 */     OreDictionary.registerOre("itemSawRedSteel", new ItemStack(TFCItems.redSteelSaw, 1, 32767));
/* 507 */     OreDictionary.registerOre("itemSawSteel", new ItemStack(TFCItems.steelSaw, 1, 32767));
/*     */     
/* 509 */     for (Item scythe : Recipes.scythes) {
/* 510 */       OreDictionary.registerOre("itemScythe", new ItemStack(scythe, 1, 32767));
/*     */     }
/* 512 */     OreDictionary.registerOre("itemScytheBismuthBronze", new ItemStack(TFCItems.bismuthBronzeScythe, 1, 32767));
/* 513 */     OreDictionary.registerOre("itemScytheBlackBronze", new ItemStack(TFCItems.blackBronzeScythe, 1, 32767));
/* 514 */     OreDictionary.registerOre("itemScytheBlackSteel", new ItemStack(TFCItems.blackSteelScythe, 1, 32767));
/* 515 */     OreDictionary.registerOre("itemScytheBlueSteel", new ItemStack(TFCItems.blueSteelScythe, 1, 32767));
/* 516 */     OreDictionary.registerOre("itemScytheBronze", new ItemStack(TFCItems.bronzeScythe, 1, 32767));
/* 517 */     OreDictionary.registerOre("itemScytheCopper", new ItemStack(TFCItems.copperScythe, 1, 32767));
/* 518 */     OreDictionary.registerOre("itemScytheWroughtIron", new ItemStack(TFCItems.wroughtIronScythe, 1, 32767));
/* 519 */     OreDictionary.registerOre("itemScytheRedSteel", new ItemStack(TFCItems.redSteelScythe, 1, 32767));
/* 520 */     OreDictionary.registerOre("itemScytheSteel", new ItemStack(TFCItems.steelScythe, 1, 32767));
/*     */     
/* 522 */     for (Item pick : Recipes.picks) {
/* 523 */       OreDictionary.registerOre("itemPick", new ItemStack(pick, 1, 32767));
/*     */     }
/* 525 */     OreDictionary.registerOre("itemPickBismuthBronze", new ItemStack(TFCItems.bismuthBronzePick, 1, 32767));
/* 526 */     OreDictionary.registerOre("itemPickBlackBronze", new ItemStack(TFCItems.blackBronzePick, 1, 32767));
/* 527 */     OreDictionary.registerOre("itemPickBlackSteel", new ItemStack(TFCItems.blackSteelPick, 1, 32767));
/* 528 */     OreDictionary.registerOre("itemPickBlueSteel", new ItemStack(TFCItems.blueSteelPick, 1, 32767));
/* 529 */     OreDictionary.registerOre("itemPickBronze", new ItemStack(TFCItems.bronzePick, 1, 32767));
/* 530 */     OreDictionary.registerOre("itemPickCopper", new ItemStack(TFCItems.copperPick, 1, 32767));
/* 531 */     OreDictionary.registerOre("itemPickWroughtIron", new ItemStack(TFCItems.wroughtIronPick, 1, 32767));
/* 532 */     OreDictionary.registerOre("itemPickRedSteel", new ItemStack(TFCItems.redSteelPick, 1, 32767));
/* 533 */     OreDictionary.registerOre("itemPickSteel", new ItemStack(TFCItems.steelPick, 1, 32767));
/*     */     
/* 535 */     for (Item proPick : Recipes.proPicks) {
/* 536 */       OreDictionary.registerOre("itemProPick", new ItemStack(proPick, 1, 32767));
/*     */     }
/* 538 */     OreDictionary.registerOre("itemProPickBismuthBronze", new ItemStack(TFCItems.proPickBismuthBronze, 1, 32767));
/* 539 */     OreDictionary.registerOre("itemProPickBlackBronze", new ItemStack(TFCItems.proPickBlackBronze, 1, 32767));
/* 540 */     OreDictionary.registerOre("itemProPickBlackSteel", new ItemStack(TFCItems.proPickBlackSteel, 1, 32767));
/* 541 */     OreDictionary.registerOre("itemProPickBlueSteel", new ItemStack(TFCItems.proPickBlueSteel, 1, 32767));
/* 542 */     OreDictionary.registerOre("itemProPickBronze", new ItemStack(TFCItems.proPickBronze, 1, 32767));
/* 543 */     OreDictionary.registerOre("itemProPickCopper", new ItemStack(TFCItems.proPickCopper, 1, 32767));
/* 544 */     OreDictionary.registerOre("itemProPickWroughtIron", new ItemStack(TFCItems.proPickIron, 1, 32767));
/* 545 */     OreDictionary.registerOre("itemProPickRedSteel", new ItemStack(TFCItems.proPickRedSteel, 1, 32767));
/* 546 */     OreDictionary.registerOre("itemProPickSteel", new ItemStack(TFCItems.proPickSteel, 1, 32767));
/*     */     
/* 548 */     for (Item shovel : Recipes.shovels) {
/* 549 */       OreDictionary.registerOre("itemShovel", new ItemStack(shovel, 1, 32767));
/*     */     }
/* 551 */     OreDictionary.registerOre("itemShovelStone", new ItemStack(TFCItems.sedShovel, 1, 32767));
/* 552 */     OreDictionary.registerOre("itemShovelStone", new ItemStack(TFCItems.igInShovel, 1, 32767));
/* 553 */     OreDictionary.registerOre("itemShovelStone", new ItemStack(TFCItems.igExShovel, 1, 32767));
/* 554 */     OreDictionary.registerOre("itemShovelStone", new ItemStack(TFCItems.mMShovel, 1, 32767));
/* 555 */     OreDictionary.registerOre("itemShovelBismuthBronze", new ItemStack(TFCItems.bismuthBronzeShovel, 1, 32767));
/* 556 */     OreDictionary.registerOre("itemShovelBlackBronze", new ItemStack(TFCItems.blackBronzeShovel, 1, 32767));
/* 557 */     OreDictionary.registerOre("itemShovelBlackSteel", new ItemStack(TFCItems.blackSteelShovel, 1, 32767));
/* 558 */     OreDictionary.registerOre("itemShovelBlueSteel", new ItemStack(TFCItems.blueSteelShovel, 1, 32767));
/* 559 */     OreDictionary.registerOre("itemShovelBronze", new ItemStack(TFCItems.bronzeShovel, 1, 32767));
/* 560 */     OreDictionary.registerOre("itemShovelCopper", new ItemStack(TFCItems.copperShovel, 1, 32767));
/* 561 */     OreDictionary.registerOre("itemShovelWroughtIron", new ItemStack(TFCItems.wroughtIronShovel, 1, 32767));
/* 562 */     OreDictionary.registerOre("itemShovelRedSteel", new ItemStack(TFCItems.redSteelShovel, 1, 32767));
/* 563 */     OreDictionary.registerOre("itemShovelSteel", new ItemStack(TFCItems.steelShovel, 1, 32767));
/*     */     
/* 565 */     for (Item hoe : Recipes.hoes) {
/* 566 */       OreDictionary.registerOre("itemHoe", new ItemStack(hoe, 1, 32767));
/*     */     }
/* 568 */     OreDictionary.registerOre("itemHoeStone", new ItemStack(TFCItems.sedHoe, 1, 32767));
/* 569 */     OreDictionary.registerOre("itemHoeStone", new ItemStack(TFCItems.igInHoe, 1, 32767));
/* 570 */     OreDictionary.registerOre("itemHoeStone", new ItemStack(TFCItems.igExHoe, 1, 32767));
/* 571 */     OreDictionary.registerOre("itemHoeStone", new ItemStack(TFCItems.mMHoe, 1, 32767));
/* 572 */     OreDictionary.registerOre("itemHoeBismuthBronze", new ItemStack(TFCItems.bismuthBronzeHoe, 1, 32767));
/* 573 */     OreDictionary.registerOre("itemHoeBlackBronze", new ItemStack(TFCItems.blackBronzeHoe, 1, 32767));
/* 574 */     OreDictionary.registerOre("itemHoeBlackSteel", new ItemStack(TFCItems.blackSteelHoe, 1, 32767));
/* 575 */     OreDictionary.registerOre("itemHoeBlueSteel", new ItemStack(TFCItems.blueSteelHoe, 1, 32767));
/* 576 */     OreDictionary.registerOre("itemHoeBronze", new ItemStack(TFCItems.bronzeHoe, 1, 32767));
/* 577 */     OreDictionary.registerOre("itemHoeCopper", new ItemStack(TFCItems.copperHoe, 1, 32767));
/* 578 */     OreDictionary.registerOre("itemHoeWroughtIron", new ItemStack(TFCItems.wroughtIronHoe, 1, 32767));
/* 579 */     OreDictionary.registerOre("itemHoeRedSteel", new ItemStack(TFCItems.redSteelHoe, 1, 32767));
/* 580 */     OreDictionary.registerOre("itemHoeSteel", new ItemStack(TFCItems.steelHoe, 1, 32767));
/*     */ 
/*     */     
/* 583 */     for (Item sword : Recipes.swords) {
/* 584 */       OreDictionary.registerOre("itemSword", new ItemStack(sword, 1, 32767));
/*     */     }
/* 586 */     OreDictionary.registerOre("itemSwordBismuthBronze", new ItemStack(TFCItems.bismuthBronzeSword, 1, 32767));
/* 587 */     OreDictionary.registerOre("itemSwordBlackBronze", new ItemStack(TFCItems.blackBronzeSword, 1, 32767));
/* 588 */     OreDictionary.registerOre("itemSwordBlackSteel", new ItemStack(TFCItems.blackSteelSword, 1, 32767));
/* 589 */     OreDictionary.registerOre("itemSwordBlueSteel", new ItemStack(TFCItems.blueSteelSword, 1, 32767));
/* 590 */     OreDictionary.registerOre("itemSwordBronze", new ItemStack(TFCItems.bronzeSword, 1, 32767));
/* 591 */     OreDictionary.registerOre("itemSwordCopper", new ItemStack(TFCItems.copperSword, 1, 32767));
/* 592 */     OreDictionary.registerOre("itemSwordWroughtIron", new ItemStack(TFCItems.wroughtIronSword, 1, 32767));
/* 593 */     OreDictionary.registerOre("itemSwordRedSteel", new ItemStack(TFCItems.redSteelSword, 1, 32767));
/* 594 */     OreDictionary.registerOre("itemSwordSteel", new ItemStack(TFCItems.steelSword, 1, 32767));
/*     */     
/* 596 */     for (Item mace : Recipes.maces) {
/* 597 */       OreDictionary.registerOre("itemMace", new ItemStack(mace, 1, 32767));
/*     */     }
/* 599 */     OreDictionary.registerOre("itemMaceBismuthBronze", new ItemStack(TFCItems.bismuthBronzeMace, 1, 32767));
/* 600 */     OreDictionary.registerOre("itemMaceBlackBronze", new ItemStack(TFCItems.blackBronzeMace, 1, 32767));
/* 601 */     OreDictionary.registerOre("itemMaceBlackSteel", new ItemStack(TFCItems.blackSteelMace, 1, 32767));
/* 602 */     OreDictionary.registerOre("itemMaceBlueSteel", new ItemStack(TFCItems.blueSteelMace, 1, 32767));
/* 603 */     OreDictionary.registerOre("itemMaceBronze", new ItemStack(TFCItems.bronzeMace, 1, 32767));
/* 604 */     OreDictionary.registerOre("itemMaceCopper", new ItemStack(TFCItems.copperMace, 1, 32767));
/* 605 */     OreDictionary.registerOre("itemMaceWroughtIron", new ItemStack(TFCItems.wroughtIronMace, 1, 32767));
/* 606 */     OreDictionary.registerOre("itemMaceRedSteel", new ItemStack(TFCItems.redSteelMace, 1, 32767));
/* 607 */     OreDictionary.registerOre("itemMaceSteel", new ItemStack(TFCItems.steelMace, 1, 32767));
/*     */     
/* 609 */     for (Item javelin : Recipes.javelins) {
/* 610 */       OreDictionary.registerOre("itemJavelin", new ItemStack(javelin, 1, 32767));
/*     */     }
/* 612 */     OreDictionary.registerOre("itemJavelinStone", new ItemStack(TFCItems.sedStoneJavelin, 1, 32767));
/* 613 */     OreDictionary.registerOre("itemJavelinStone", new ItemStack(TFCItems.igInStoneJavelin, 1, 32767));
/* 614 */     OreDictionary.registerOre("itemJavelinStone", new ItemStack(TFCItems.igExStoneJavelin, 1, 32767));
/* 615 */     OreDictionary.registerOre("itemJavelinStone", new ItemStack(TFCItems.mMStoneJavelin, 1, 32767));
/* 616 */     OreDictionary.registerOre("itemJavelinBismuthBronze", new ItemStack(TFCItems.bismuthBronzeJavelin, 1, 32767));
/* 617 */     OreDictionary.registerOre("itemJavelinBlackBronze", new ItemStack(TFCItems.blackBronzeJavelin, 1, 32767));
/* 618 */     OreDictionary.registerOre("itemJavelinBlackSteel", new ItemStack(TFCItems.blackSteelJavelin, 1, 32767));
/* 619 */     OreDictionary.registerOre("itemJavelinBlueSteel", new ItemStack(TFCItems.blueSteelJavelin, 1, 32767));
/* 620 */     OreDictionary.registerOre("itemJavelinBronze", new ItemStack(TFCItems.bronzeJavelin, 1, 32767));
/* 621 */     OreDictionary.registerOre("itemJavelinCopper", new ItemStack(TFCItems.copperJavelin, 1, 32767));
/* 622 */     OreDictionary.registerOre("itemJavelinWroughtIron", new ItemStack(TFCItems.wroughtIronJavelin, 1, 32767));
/* 623 */     OreDictionary.registerOre("itemJavelinRedSteel", new ItemStack(TFCItems.redSteelJavelin, 1, 32767));
/* 624 */     OreDictionary.registerOre("itemJavelinSteel", new ItemStack(TFCItems.steelJavelin, 1, 32767));
/*     */ 
/*     */     
/* 627 */     OreDictionary.registerOre("lumpClay", new ItemStack(Items.field_151119_aD));
/* 628 */     OreDictionary.registerOre("lumpClay", new ItemStack(TFCItems.clayBall, 1, 0));
/*     */     
/* 630 */     OreDictionary.registerOre("itemArrow", new ItemStack(Items.field_151032_g));
/* 631 */     OreDictionary.registerOre("itemArrow", new ItemStack(TFCItems.arrow));
/*     */     
/* 633 */     OreDictionary.registerOre("itemReed", new ItemStack(Items.field_151120_aE));
/* 634 */     OreDictionary.registerOre("itemReed", new ItemStack(TFCItems.reeds));
/*     */     
/* 636 */     OreDictionary.registerOre("itemRock", new ItemStack(TFCItems.looseRock, 1, 32767));
/*     */     
/* 638 */     OreDictionary.registerOre("bucketEmpty", new ItemStack(Items.field_151133_ar));
/* 639 */     OreDictionary.registerOre("bucketEmpty", new ItemStack(TFCItems.woodenBucketEmpty));
/* 640 */     OreDictionary.registerOre("bucketEmpty", new ItemStack(TFCItems.redSteelBucketEmpty));
/* 641 */     OreDictionary.registerOre("bucketEmpty", new ItemStack(TFCItems.blueSteelBucketEmpty));
/*     */     
/* 643 */     OreDictionary.registerOre("bucketWater", new ItemStack(Items.field_151131_as));
/* 644 */     OreDictionary.registerOre("bucketWater", new ItemStack(TFCItems.woodenBucketWater, 1, 32767));
/* 645 */     OreDictionary.registerOre("bucketWater", new ItemStack(TFCItems.redSteelBucketWater, 1, 32767));
/* 646 */     OreDictionary.registerOre("bucketWater", new ItemStack(TFCItems.woodenBucketSaltWater, 1, 32767));
/* 647 */     OreDictionary.registerOre("bucketWater", new ItemStack(TFCItems.redSteelBucketSaltWater, 1, 32767));
/*     */     
/* 649 */     OreDictionary.registerOre("bucketFreshWater", new ItemStack(TFCItems.woodenBucketWater, 1, 32767));
/* 650 */     OreDictionary.registerOre("bucketFreshWater", new ItemStack(TFCItems.redSteelBucketWater, 1, 32767));
/*     */     
/* 652 */     OreDictionary.registerOre("bucketSaltWater", new ItemStack(TFCItems.woodenBucketSaltWater, 1, 32767));
/* 653 */     OreDictionary.registerOre("bucketSaltWater", new ItemStack(TFCItems.redSteelBucketSaltWater, 1, 32767));
/*     */     
/* 655 */     OreDictionary.registerOre("bucketLava", new ItemStack(Items.field_151129_at));
/* 656 */     OreDictionary.registerOre("bucketLava", new ItemStack(TFCItems.blueSteelBucketLava));
/*     */     
/* 658 */     OreDictionary.registerOre("bucketMilk", new ItemStack(Items.field_151117_aB));
/* 659 */     OreDictionary.registerOre("bucketMilk", new ItemStack(TFCItems.woodenBucketMilk));
/*     */     
/* 661 */     OreDictionary.registerOre("toolFlintSteel", new ItemStack(Items.field_151033_d, 1, 32767));
/* 662 */     OreDictionary.registerOre("toolFlintSteel", new ItemStack(TFCItems.flintSteel, 1, 32767));
/*     */ 
/*     */     
/* 665 */     OreDictionary.registerOre("blockSand", new ItemStack((Block)Blocks.field_150354_m));
/* 666 */     OreDictionary.registerOre("blockSand", new ItemStack(TFCBlocks.sand, 1, 32767));
/* 667 */     OreDictionary.registerOre("blockSand", new ItemStack(TFCBlocks.sand2, 1, 32767));
/*     */     
/* 669 */     OreDictionary.registerOre("blockGravel", new ItemStack(Blocks.field_150351_n));
/* 670 */     OreDictionary.registerOre("blockGravel", new ItemStack(TFCBlocks.gravel, 1, 32767));
/* 671 */     OreDictionary.registerOre("blockGravel", new ItemStack(TFCBlocks.gravel2, 1, 32767));
/*     */     
/* 673 */     OreDictionary.registerOre("blockDirt", new ItemStack(Blocks.field_150346_d));
/* 674 */     OreDictionary.registerOre("blockDirt", new ItemStack(TFCBlocks.dirt, 1, 32767));
/* 675 */     OreDictionary.registerOre("blockDirt", new ItemStack(TFCBlocks.dirt2, 1, 32767));
/*     */     
/* 677 */     OreDictionary.registerOre("blockTorch", new ItemStack(Blocks.field_150478_aa));
/* 678 */     OreDictionary.registerOre("blockTorch", new ItemStack(TFCBlocks.torch));
/*     */     
/* 680 */     OreDictionary.registerOre("blockPumpkin", new ItemStack(Blocks.field_150423_aK));
/* 681 */     OreDictionary.registerOre("blockPumpkin", new ItemStack(TFCBlocks.pumpkin));
/* 682 */     OreDictionary.registerOre("blockLitPumpkin", new ItemStack(Blocks.field_150428_aP));
/* 683 */     OreDictionary.registerOre("blockLitPumpkin", new ItemStack(TFCBlocks.litPumpkin));
/*     */ 
/*     */     
/* 686 */     for (Item seed : Recipes.seeds)
/* 687 */       OreDictionary.registerOre("seedBag", new ItemStack(seed, 1, 32767)); 
/* 688 */     OreDictionary.registerOre("seedWheat", new ItemStack(TFCItems.seedsWheat, 1, 32767));
/* 689 */     OreDictionary.registerOre("seedMaize", new ItemStack(TFCItems.seedsMaize, 1, 32767));
/* 690 */     OreDictionary.registerOre("seedTomato", new ItemStack(TFCItems.seedsTomato, 1, 32767));
/* 691 */     OreDictionary.registerOre("seedBarley", new ItemStack(TFCItems.seedsBarley, 1, 32767));
/* 692 */     OreDictionary.registerOre("seedRye", new ItemStack(TFCItems.seedsRye, 1, 32767));
/* 693 */     OreDictionary.registerOre("seedOat", new ItemStack(TFCItems.seedsOat, 1, 32767));
/* 694 */     OreDictionary.registerOre("seedRice", new ItemStack(TFCItems.seedsRice, 1, 32767));
/* 695 */     OreDictionary.registerOre("seedPotato", new ItemStack(TFCItems.seedsPotato, 1, 32767));
/* 696 */     OreDictionary.registerOre("seedOnion", new ItemStack(TFCItems.seedsOnion, 1, 32767));
/* 697 */     OreDictionary.registerOre("seedCabbage", new ItemStack(TFCItems.seedsCabbage, 1, 32767));
/* 698 */     OreDictionary.registerOre("seedGarlic", new ItemStack(TFCItems.seedsGarlic, 1, 32767));
/* 699 */     OreDictionary.registerOre("seedCarrot", new ItemStack(TFCItems.seedsCarrot, 1, 32767));
/* 700 */     OreDictionary.registerOre("seedSugarcane", new ItemStack(TFCItems.seedsSugarcane, 1, 32767));
/* 701 */     OreDictionary.registerOre("seedYelloBellPepper", new ItemStack(TFCItems.seedsYellowBellPepper, 1, 32767));
/* 702 */     OreDictionary.registerOre("seedRedBellPepper", new ItemStack(TFCItems.seedsRedBellPepper, 1, 32767));
/* 703 */     OreDictionary.registerOre("seedSoybean", new ItemStack(TFCItems.seedsSoybean, 1, 32767));
/* 704 */     OreDictionary.registerOre("seedGreenbean", new ItemStack(TFCItems.seedsGreenbean, 1, 32767));
/* 705 */     OreDictionary.registerOre("seedSquash", new ItemStack(TFCItems.seedsSquash, 1, 32767));
/* 706 */     OreDictionary.registerOre("seedJute", new ItemStack(TFCItems.seedsJute, 1, 32767));
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\TFC_OreDictionary.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */