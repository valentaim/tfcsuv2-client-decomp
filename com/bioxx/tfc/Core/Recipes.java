package com.bioxx.tfc.Core;

import com.bioxx.tfc.Core.Config.TFC_ConfigFiles;
import com.bioxx.tfc.Food.ItemFoodTFC;
import com.bioxx.tfc.TileEntities.TEBarrel;
import com.bioxx.tfc.TileEntities.TELoom;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.Crafting.AnvilManager;
import com.bioxx.tfc.api.Crafting.AnvilRecipe;
import com.bioxx.tfc.api.Crafting.AnvilReq;
import com.bioxx.tfc.api.Crafting.CraftingManagerTFC;
import com.bioxx.tfc.api.Crafting.KilnCraftingManager;
import com.bioxx.tfc.api.Crafting.KilnRecipe;
import com.bioxx.tfc.api.Crafting.PlanRecipe;
import com.bioxx.tfc.api.Crafting.QuernManager;
import com.bioxx.tfc.api.Crafting.QuernRecipe;
import com.bioxx.tfc.api.Enums.RuleEnum;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import cpw.mods.fml.common.registry.GameRegistry;
import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import terramisc.core.TFCMBlocks;







public class Recipes
{
  public static Item[] axes;
  public static Item[] chisels;
  public static Item[] saws;
  public static Item[] scythes;
  public static Item[] knives;
  public static Item[] meltedMetal;
  public static Item[] hammers;
  public static Item[] picks;
  public static Item[] proPicks;
  public static Item[] shovels;
  public static Item[] hoes;
  public static Item[] swords;
  public static Item[] maces;
  public static Item[] javelins;
  public static Item[] spindle;
  public static Item[] gems;
  public static Item[] seeds;
  public static Item[] doors;
  public static final int WILD = 32767;
  
  public static void registerRecipes() {
    TEBarrel.registerRecipes();
    TELoom.registerRecipes();







    
    vanillaRecipes();

    
    for (int m = 0; m < Global.WOOD_ALL.length; m++) {
      
      GameRegistry.addRecipe(new ItemStack(doors[m]), new Object[] { "WW", "WW", "WW", Character.valueOf('W'), new ItemStack(TFCItems.singlePlank, 1, m) });
      GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCBlocks.chest, 1, m), new Object[] { "###", "# #", "###", Character.valueOf('#'), new ItemStack(TFCItems.singlePlank, 1, m) }));
      GameRegistry.addRecipe(new ItemStack(TFCBlocks.toolRack, 1, m), new Object[] { "###", "   ", "###", Character.valueOf('#'), new ItemStack(TFCItems.singlePlank, 1, m) });
      
      int l = m % 16;
      if (m == l) {
        
        GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack(TFCItems.singlePlank, 8, m), new Object[] { new ItemStack(TFCItems.logs, 1, m), "itemSaw" }));
        GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack(TFCItems.singlePlank, 4, m), new Object[] { new ItemStack(TFCBlocks.planks, 1, m), "itemSaw" }));
        GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCBlocks.woodSupportV, 8, m), new Object[] { "A2", " 2", Character.valueOf('2'), new ItemStack(TFCItems.logs, 1, m), Character.valueOf('A'), "itemSaw" }));
        
        GameRegistry.addRecipe(new ItemStack(TFCBlocks.planks, 1, m), new Object[] { "11", "11", Character.valueOf('1'), new ItemStack(TFCItems.singlePlank, 1, m) });
        
        GameRegistry.addRecipe(new ItemStack(TFCBlocks.barrel, 1, m), new Object[] { "# #", "# #", "###", Character.valueOf('#'), new ItemStack(TFCItems.singlePlank, 1, m) });
        
        GameRegistry.addRecipe(new ItemStack(TFCBlocks.fence, 6, m), new Object[] { "LPL", "LPL", Character.valueOf('L'), new ItemStack(TFCItems.logs, 1, m), Character.valueOf('P'), new ItemStack(TFCItems.singlePlank, 1, m) });
        GameRegistry.addRecipe(new ItemStack(TFCBlocks.fenceGate, 2, m), new Object[] { "LPL", "LPL", Character.valueOf('L'), new ItemStack(TFCItems.singlePlank, 1, m), Character.valueOf('P'), new ItemStack(TFCBlocks.planks, 1, m) });
        GameRegistry.addRecipe(new ItemStack(TFCBlocks.armorStand, 1, m), new Object[] { "###", " # ", "%%%", Character.valueOf('#'), new ItemStack(TFCItems.singlePlank, 1, m), Character.valueOf('%'), new ItemStack(TFCBlocks.planks, 1, m) });
        
        GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCBlocks.loom, 1, m), new Object[] { "LLL", "LSL", "L L", Character.valueOf('L'), new ItemStack(TFCItems.singlePlank, 1, m), Character.valueOf('S'), "stickWood" }));
      }
      else if (m / 16 == 1) {
        
        GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack(TFCItems.singlePlank, 8, m), new Object[] { new ItemStack(TFCItems.logs, 1, m), "itemSaw" }));
        GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack(TFCItems.singlePlank, 4, m), new Object[] { new ItemStack(TFCBlocks.planks2, 1, l), "itemSaw" }));
        GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCBlocks.woodSupportV2, 8, l), new Object[] { "A2", " 2", Character.valueOf('2'), new ItemStack(TFCItems.logs, 1, m), Character.valueOf('A'), "itemSaw" }));
        
        GameRegistry.addRecipe(new ItemStack(TFCBlocks.planks2, 1, l), new Object[] { "11", "11", Character.valueOf('1'), new ItemStack(TFCItems.singlePlank, 1, m) });
        
        GameRegistry.addRecipe(new ItemStack(TFCBlocks.fence2, 6, l), new Object[] { "LPL", "LPL", Character.valueOf('L'), new ItemStack(TFCItems.logs, 1, m), Character.valueOf('P'), new ItemStack(TFCItems.singlePlank, 1, m) });
        GameRegistry.addRecipe(new ItemStack(TFCBlocks.fenceGate2, 2, l), new Object[] { "LPL", "LPL", Character.valueOf('L'), new ItemStack(TFCItems.singlePlank, 1, m), Character.valueOf('P'), new ItemStack(TFCBlocks.planks2, 1, l) });
        
        GameRegistry.addRecipe(new ItemStack(TFCBlocks.armorStand2, 1, l), new Object[] { "###", " # ", "%%%", Character.valueOf('#'), new ItemStack(TFCItems.singlePlank, 1, m), Character.valueOf('%'), new ItemStack(TFCBlocks.planks2, 1, l) });
        
        GameRegistry.addRecipe(new ItemStack(TFCBlocks.barrel, 1, m), new Object[] { "# #", "# #", "###", Character.valueOf('#'), new ItemStack(TFCItems.singlePlank, 1, m) });
        GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCBlocks.loom, 1, m), new Object[] { "LLL", "LSL", "L L", Character.valueOf('L'), new ItemStack(TFCItems.singlePlank, 1, m), Character.valueOf('S'), "stickWood" }));
      } 
    } 

    
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCBlocks.wattle, 1, 0), new Object[] { "SS", "SS", Character.valueOf('S'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.doorWattle), new Object[] { "PW", "PW", Character.valueOf('P'), "stickWood", Character.valueOf('W'), TFCBlocks.wattle }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.doorWattle), new Object[] { "WP", "WP", Character.valueOf('P'), "stickWood", Character.valueOf('W'), TFCBlocks.wattle }));


    
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.sluiceItem, 1), new Object[] { "  1", " 12", "122", Character.valueOf('1'), "stickWood", Character.valueOf('2'), "woodLumber" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCBlocks.nestBox, 1), new Object[] { "S S", "PSP", "PPP", Character.valueOf('S'), new ItemStack(TFCItems.straw, 1), Character.valueOf('P'), "woodLumber" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.woodenBucketEmpty, 1), new Object[] { "w w", "w w", " w ", Character.valueOf('w'), "woodLumber" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(Blocks.field_150415_aT, 1, 0), new Object[] { "###", "###", Character.valueOf('#'), "woodLumber" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(Items.field_151155_ap, 1, 0), new Object[] { "###", "###", " 1 ", Character.valueOf('#'), "woodLumber", Character.valueOf('1'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCBlocks.buttonWood, 1), new Object[] { "#", Character.valueOf('#'), "plankWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(Items.field_151104_aV, 1), new Object[] { "PPP", "QQQ", Character.valueOf('P'), "materialCloth", Character.valueOf('Q'), "woodLumber" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCBlocks.workbench, 1), new Object[] { "PP", "PP", Character.valueOf('P'), "plankWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCBlocks.bellows, 1, 0), new Object[] { "###", "???", "###", Character.valueOf('#'), "woodLumber", Character.valueOf('?'), "materialLeather" }));

    
    for (int k = 0; k < 3; k++) {
      
      GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack(TFCItems.wool, 1 + k, 0), new Object[] { new ItemStack(TFCItems.sheepSkin, 1, k), "itemKnife" }));
    } 

    
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.dye, 1, 4), new Object[] { new ItemStack(TFCItems.powder, 1, 6) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.dye, 1, 2), new Object[] { new ItemStack(TFCItems.powder, 1, 8) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.dye, 1, 1), new Object[] { new ItemStack(TFCItems.powder, 1, 5) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.dye, 1, 11), new Object[] { new ItemStack(TFCItems.powder, 1, 7) });
    GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack(TFCItems.dye, 1, 12), new Object[] { new ItemStack(TFCItems.powder, 1, 8), new ItemStack(TFCItems.powder, 1, 0), "blockSand" }));
    
    int i;
    for (i = 0; i < Global.STONE_FLUXINDEX.length; i++) {
      GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack(TFCItems.powder, 2, 0), new Object[] { new ItemStack(TFCItems.looseRock, 1, Global.STONE_FLUXINDEX[i]), "itemHammer" }));
    }  GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack(TFCItems.powder, 6, 0), new Object[] { new ItemStack(TFCItems.oreChunk, 1, 32), "itemHammer" }));

    
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCBlocks.anvil, 1, 1), new Object[] { "###", " # ", "###", Character.valueOf('#'), "ingotDoubleCopper" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCBlocks.anvil, 1, 2), new Object[] { "###", " # ", "###", Character.valueOf('#'), "ingotDoubleBronze" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCBlocks.anvil, 1, 3), new Object[] { "###", " # ", "###", Character.valueOf('#'), "ingotDoubleWroughtIron" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCBlocks.anvil, 1, 4), new Object[] { "###", " # ", "###", Character.valueOf('#'), "ingotDoubleSteel" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCBlocks.anvil, 1, 5), new Object[] { "###", " # ", "###", Character.valueOf('#'), "ingotDoubleBlackSteel" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCBlocks.anvil, 1, 6), new Object[] { "###", " # ", "###", Character.valueOf('#'), "ingotDoubleBlueSteel" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCBlocks.anvil, 1, 7), new Object[] { "###", " # ", "###", Character.valueOf('#'), "ingotDoubleRedSteel" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCBlocks.anvil2, 1, 0), new Object[] { "###", " # ", "###", Character.valueOf('#'), "ingotDoubleRoseGold" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCBlocks.anvil2, 1, 1), new Object[] { "###", " # ", "###", Character.valueOf('#'), "ingotDoubleBismuthBronze" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCBlocks.anvil2, 1, 2), new Object[] { "###", " # ", "###", Character.valueOf('#'), "ingotDoubleBlackBronze" }));
    
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCBlocks.bloomery, 1), new Object[] { "PPP", "P P", "PPP", Character.valueOf('P'), "plateDoubleAnyBronze" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCBlocks.blastFurnace, 1), new Object[] { "PPP", "PCP", "PPP", Character.valueOf('P'), "plateDoubleWroughtIron", Character.valueOf('C'), new ItemStack(TFCBlocks.crucible, 1) }));
    
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCBlocks.spawnMeter, 1), new Object[] { "PPP", "GKG", "PPP", Character.valueOf('P'), "stoneSmooth", Character.valueOf('K'), "gemChipped", Character.valueOf('G'), new ItemStack(Blocks.field_150359_w, 1) }));
    
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCBlocks.quern, 1), new Object[] { "PPP", Character.valueOf('P'), "stoneSmooth" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.quern, 1), new Object[] { "  W", "PPP", Character.valueOf('P'), "stone", Character.valueOf('W'), "stickWood" }));

    
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.clayBall, 1, 1), new Object[] { "PXP", "XCX", "PXP", Character.valueOf('P'), new ItemStack(TFCItems.powder, 1, 1), Character.valueOf('X'), new ItemStack(TFCItems.powder, 1, 2), Character.valueOf('C'), "lumpClay" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.clayBall, 1, 1), new Object[] { "PXP", "XCX", "PXP", Character.valueOf('P'), new ItemStack(TFCItems.powder, 1, 2), Character.valueOf('X'), new ItemStack(TFCItems.powder, 1, 1), Character.valueOf('C'), "lumpClay" }));
    
    GameRegistry.addRecipe(new ItemStack(TFCItems.fireBrick, 2, 0), new Object[] { "PP", "PP", Character.valueOf('P'), new ItemStack(TFCItems.clayBall, 1, 1) });
    
    GameRegistry.addRecipe(new ItemStack(TFCBlocks.fireBrick, 2, 0), new Object[] { "PXP", "XPX", "PXP", Character.valueOf('P'), new ItemStack(TFCItems.fireBrick, 1, 1), Character.valueOf('X'), new ItemStack(TFCItems.mortar, 1) });

    
    GameRegistry.addRecipe(new ItemStack(TFCBlocks.thatch, 1), new Object[] { "PP", "PP", Character.valueOf('P'), new ItemStack(TFCItems.straw, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.straw, 4), new Object[] { new ItemStack(TFCBlocks.thatch, 1) });

    
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.coal, 9), new Object[] { new ItemStack(Blocks.field_150402_ci) });
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(Blocks.field_150402_ci, 1), new Object[] { "###", "###", "###", Character.valueOf('#'), "gemCoal" }));

    
    GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack(TFCItems.arrow, 8), new Object[] { "itemRock", "stickWood", new ItemStack(Items.field_151008_G) }));
    
    GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack(Items.field_151016_H, 2, 0), new Object[] { "gemCharcoal", "dustSulfur", "dustSaltpeter" }));
    
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(Items.field_151160_bD, 1), new Object[] { "###", "#$#", "###", Character.valueOf('#'), "stickWood", Character.valueOf('$'), "materialLeather" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(Items.field_151159_an, 1), new Object[] { "###", "#$#", "###", Character.valueOf('#'), "stickWood", Character.valueOf('$'), "materialCloth" }));
    
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(Blocks.field_150404_cg, 2, 0), new Object[] { "$$", Character.valueOf('$'), "materialCloth" }));
    
    GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack(TFCBlocks.litPumpkin, 1), new Object[] { "blockTorch", "blockPumpkin" }));
    
    GameRegistry.addRecipe(new ItemStack(TFCItems.glassBottle, 3), new Object[] { "# #", " # ", Character.valueOf('#'), new ItemStack(Blocks.field_150359_w) });
    
    for (i = 0; i < Global.DYE_NAMES.length; i++) {
      
      GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack(Blocks.field_150404_cg, 1, i), new Object[] { Global.DYE_NAMES[i], new ItemStack(Blocks.field_150404_cg, 1, 32767) }));
      GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack(TFCItems.potterySmallVessel, 1, 0), new Object[] { new ItemStack(TFCItems.potterySmallVessel, 1, 0), Global.DYE_NAMES[i] }));
    } 
    
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(Blocks.field_150448_aq, 64), new Object[] { "PsP", "PsP", Character.valueOf('P'), "ingotIron", Character.valueOf('s'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(Blocks.field_150318_D, 64), new Object[] { " r ", "PsP", "PsP", Character.valueOf('P'), "ingotGold", Character.valueOf('s'), "stickWood", Character.valueOf('r'), Items.field_151137_ax }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(Items.field_151143_au, 1), new Object[] { "P P", "PPP", Character.valueOf('P'), "plateWroughtIron" }));

    
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(Blocks.field_150442_at, 1), new Object[] { "P", "H", Character.valueOf('P'), "stickWood", Character.valueOf('H'), "itemRock" }));
    
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(Items.field_151121_aF, 3), new Object[] { "###", Character.valueOf('#'), "itemReed" }));
    GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack(Items.field_151122_aG, 1), new Object[] { new ItemStack(Items.field_151121_aF), new ItemStack(Items.field_151121_aF), new ItemStack(Items.field_151121_aF), "materialLeather" }));

    
    GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack(TFCItems.woolYarn, 8), new Object[] { "materialWool", new ItemStack(TFCItems.spindle, 1, 32767) }));

    
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.ink, 16, 0), new Object[] { "2", Character.valueOf('2'), "dyeBlack" }));
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.blueprint, 1), new Object[] { new ItemStack(TFCItems.ink), new ItemStack(Items.field_151121_aF, 1) });
    GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack(TFCItems.nametag, 1), new Object[] { new ItemStack(TFCItems.ink), new ItemStack(Items.field_151121_aF, 1), "materialString" }));
    
    int j;
    for (j = 0; j < Global.STONE_IGIN.length; j++) {

      
      GameRegistry.addRecipe(new ItemStack(TFCBlocks.stoneIgInBrick, 4, j), new Object[] { "PXP", "XPX", "PXP", Character.valueOf('P'), new ItemStack(TFCItems.stoneBrick, 1, j + 0), Character.valueOf('X'), new ItemStack(TFCItems.mortar, 1) });
      GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack(TFCItems.stoneBrick, 1, j + 0), new Object[] { new ItemStack(TFCItems.looseRock, 1, j + 0), "itemChisel" }));

      
      GameRegistry.addRecipe(new ItemStack(TFCBlocks.stoneIgInCobble, 1, j), new Object[] { "PP", "PP", 
            Character.valueOf('P'), new ItemStack(TFCItems.looseRock, 1, j + 0) });
      GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.looseRock, 4, j + 0), new Object[] { new ItemStack(TFCBlocks.stoneIgInCobble, 1, j) });

      
      GameRegistry.addRecipe(new ItemStack(TFCBlocks.wallCobbleIgIn, 4, j), new Object[] { "RRR", "RRR", 
            Character.valueOf('R'), new ItemStack(TFCItems.looseRock, 1, j + 0) });
      GameRegistry.addRecipe(new ItemStack(TFCBlocks.wallRawIgIn, 3, j), new Object[] { "RRR", "RRR", 
            Character.valueOf('R'), new ItemStack(TFCBlocks.stoneIgIn, 1, j) });
      GameRegistry.addRecipe(new ItemStack(TFCBlocks.wallBrickIgIn, 3, j), new Object[] { "BMB", "MBM", 
            Character.valueOf('B'), new ItemStack(TFCItems.stoneBrick, 1, j + 0), Character.valueOf('M'), new ItemStack(TFCItems.mortar, 1) });
      GameRegistry.addRecipe(new ItemStack(TFCBlocks.wallSmoothIgIn, 3, j), new Object[] { "RRR", "RRR", 
            Character.valueOf('R'), new ItemStack(TFCBlocks.stoneIgInSmooth, 1, j) });
    } 
    
    for (j = 0; j < Global.STONE_SED.length; j++) {

      
      GameRegistry.addRecipe(new ItemStack(TFCBlocks.stoneSedBrick, 4, j), new Object[] { "PXP", "XPX", "PXP", Character.valueOf('P'), new ItemStack(TFCItems.stoneBrick, 1, j + Global.STONE_SED_START), Character.valueOf('X'), new ItemStack(TFCItems.mortar, 1) });
      GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack(TFCItems.stoneBrick, 1, j + Global.STONE_SED_START), new Object[] { new ItemStack(TFCItems.looseRock, 1, j + Global.STONE_SED_START), "itemChisel" }));

      
      GameRegistry.addRecipe(new ItemStack(TFCBlocks.stoneSedCobble, 1, j), new Object[] { "PP", "PP", 
            Character.valueOf('P'), new ItemStack(TFCItems.looseRock, 1, j + Global.STONE_SED_START) });
      GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.looseRock, 4, j + Global.STONE_SED_START), new Object[] { new ItemStack(TFCBlocks.stoneSedCobble, 1, j) });

      
      GameRegistry.addRecipe(new ItemStack(TFCBlocks.wallCobbleSed, 4, j), new Object[] { "RRR", "RRR", 
            Character.valueOf('R'), new ItemStack(TFCItems.looseRock, 1, j + Global.STONE_SED_START) });
      GameRegistry.addRecipe(new ItemStack(TFCBlocks.wallRawSed, 3, j), new Object[] { "RRR", "RRR", 
            Character.valueOf('R'), new ItemStack(TFCBlocks.stoneSed, 1, j) });
      GameRegistry.addRecipe(new ItemStack(TFCBlocks.wallBrickSed, 3, j), new Object[] { "BMB", "MBM", 
            Character.valueOf('B'), new ItemStack(TFCItems.stoneBrick, 1, j + Global.STONE_SED_START), Character.valueOf('M'), new ItemStack(TFCItems.mortar, 1) });
      GameRegistry.addRecipe(new ItemStack(TFCBlocks.wallSmoothSed, 3, j), new Object[] { "RRR", "RRR", 
            Character.valueOf('R'), new ItemStack(TFCBlocks.stoneSedSmooth, 1, j) });
    } 
    
    for (j = 0; j < Global.STONE_IGEX.length; j++) {

      
      GameRegistry.addRecipe(new ItemStack(TFCBlocks.stoneIgExBrick, 4, j), new Object[] { "PXP", "XPX", "PXP", Character.valueOf('P'), new ItemStack(TFCItems.stoneBrick, 1, j + Global.STONE_IGEX_START), Character.valueOf('X'), new ItemStack(TFCItems.mortar, 1) });
      GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack(TFCItems.stoneBrick, 1, j + Global.STONE_IGEX_START), new Object[] { new ItemStack(TFCItems.looseRock, 1, j + Global.STONE_IGEX_START), "itemChisel" }));

      
      GameRegistry.addRecipe(new ItemStack(TFCBlocks.stoneIgExCobble, 1, j), new Object[] { "PP", "PP", 
            Character.valueOf('P'), new ItemStack(TFCItems.looseRock, 1, j + Global.STONE_IGEX_START) });
      GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.looseRock, 4, j + Global.STONE_IGEX_START), new Object[] { new ItemStack(TFCBlocks.stoneIgExCobble, 1, j) });

      
      GameRegistry.addRecipe(new ItemStack(TFCBlocks.wallCobbleIgEx, 4, j), new Object[] { "RRR", "RRR", 
            Character.valueOf('R'), new ItemStack(TFCItems.looseRock, 1, j + Global.STONE_IGEX_START) });
      GameRegistry.addRecipe(new ItemStack(TFCBlocks.wallRawIgEx, 3, j), new Object[] { "RRR", "RRR", 
            Character.valueOf('R'), new ItemStack(TFCBlocks.stoneIgEx, 1, j) });
      GameRegistry.addRecipe(new ItemStack(TFCBlocks.wallBrickIgEx, 3, j), new Object[] { "BMB", "MBM", 
            Character.valueOf('B'), new ItemStack(TFCItems.stoneBrick, 1, j + Global.STONE_IGEX_START), Character.valueOf('M'), new ItemStack(TFCItems.mortar, 1) });
      GameRegistry.addRecipe(new ItemStack(TFCBlocks.wallSmoothIgEx, 3, j), new Object[] { "RRR", "RRR", 
            Character.valueOf('R'), new ItemStack(TFCBlocks.stoneIgExSmooth, 1, j) });
    } 
    
    for (j = 0; j < Global.STONE_MM.length; j++) {
      
      GameRegistry.addRecipe(new ItemStack(TFCBlocks.stoneMMBrick, 4, j), new Object[] { "PXP", "XPX", "PXP", Character.valueOf('P'), new ItemStack(TFCItems.stoneBrick, 1, j + Global.STONE_MM_START), Character.valueOf('X'), new ItemStack(TFCItems.mortar, 1) });
      GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack(TFCItems.stoneBrick, 1, j + Global.STONE_MM_START), new Object[] { new ItemStack(TFCItems.looseRock, 1, j + Global.STONE_MM_START), "itemChisel" }));

      
      GameRegistry.addRecipe(new ItemStack(TFCBlocks.stoneMMCobble, 1, j), new Object[] { "PP", "PP", 
            Character.valueOf('P'), new ItemStack(TFCItems.looseRock, 1, j + Global.STONE_MM_START) });
      GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.looseRock, 4, j + Global.STONE_MM_START), new Object[] { new ItemStack(TFCBlocks.stoneMMCobble, 1, j) });

      
      GameRegistry.addRecipe(new ItemStack(TFCBlocks.wallCobbleMM, 4, j), new Object[] { "RRR", "RRR", 
            Character.valueOf('R'), new ItemStack(TFCItems.looseRock, 1, j + Global.STONE_MM_START) });
      GameRegistry.addRecipe(new ItemStack(TFCBlocks.wallRawMM, 3, j), new Object[] { "RRR", "RRR", 
            Character.valueOf('R'), new ItemStack(TFCBlocks.stoneMM, 1, j) });
      GameRegistry.addRecipe(new ItemStack(TFCBlocks.wallBrickMM, 3, j), new Object[] { "BMB", "MBM", 
            Character.valueOf('B'), new ItemStack(TFCItems.stoneBrick, 1, j + Global.STONE_MM_START), Character.valueOf('M'), new ItemStack(TFCItems.mortar, 1) });
      GameRegistry.addRecipe(new ItemStack(TFCBlocks.wallSmoothMM, 3, j), new Object[] { "RRR", "RRR", 
            Character.valueOf('R'), new ItemStack(TFCBlocks.stoneMMSmooth, 1, j) });
    } 

    
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.bismuthUnshaped, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.bismuthIngot, 1)), new ItemStack(TFCItems.ceramicMold, 1, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.bismuthBronzeUnshaped, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.bismuthBronzeIngot, 1)), new ItemStack(TFCItems.ceramicMold, 1, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.blackBronzeUnshaped, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.blackBronzeIngot, 1)), new ItemStack(TFCItems.ceramicMold, 1, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.blackSteelUnshaped, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.blackSteelIngot, 1)), new ItemStack(TFCItems.ceramicMold, 1, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.blueSteelUnshaped, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.blueSteelIngot, 1)), new ItemStack(TFCItems.ceramicMold, 1, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.brassUnshaped, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.brassIngot, 1)), new ItemStack(TFCItems.ceramicMold, 1, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.bronzeUnshaped, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.bronzeIngot, 1)), new ItemStack(TFCItems.ceramicMold, 1, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.copperUnshaped, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.copperIngot, 1)), new ItemStack(TFCItems.ceramicMold, 1, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.goldUnshaped, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.goldIngot, 1)), new ItemStack(TFCItems.ceramicMold, 1, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.highCarbonSteelUnshaped, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.highCarbonSteelIngot, 1)), new ItemStack(TFCItems.ceramicMold, 1, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.highCarbonBlackSteelUnshaped, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.highCarbonBlackSteelIngot, 1)), new ItemStack(TFCItems.ceramicMold, 1, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.highCarbonBlueSteelUnshaped, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.highCarbonBlueSteelIngot, 1)), new ItemStack(TFCItems.ceramicMold, 1, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.highCarbonRedSteelUnshaped, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.highCarbonRedSteelIngot, 1)), new ItemStack(TFCItems.ceramicMold, 1, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.wroughtIronUnshaped, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.wroughtIronIngot, 1)), new ItemStack(TFCItems.ceramicMold, 1, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.leadUnshaped, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.leadIngot, 1)), new ItemStack(TFCItems.ceramicMold, 1, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.nickelUnshaped, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.nickelIngot, 1)), new ItemStack(TFCItems.ceramicMold, 1, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.pigIronUnshaped, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.pigIronIngot, 1)), new ItemStack(TFCItems.ceramicMold, 1, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.platinumUnshaped, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.platinumIngot, 1)), new ItemStack(TFCItems.ceramicMold, 1, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.redSteelUnshaped, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.redSteelIngot, 1)), new ItemStack(TFCItems.ceramicMold, 1, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.roseGoldUnshaped, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.roseGoldIngot, 1)), new ItemStack(TFCItems.ceramicMold, 1, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.silverUnshaped, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.silverIngot, 1)), new ItemStack(TFCItems.ceramicMold, 1, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.steelUnshaped, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.steelIngot, 1)), new ItemStack(TFCItems.ceramicMold, 1, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.sterlingSilverUnshaped, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.sterlingSilverIngot, 1)), new ItemStack(TFCItems.ceramicMold, 1, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.tinUnshaped, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.tinIngot, 1)), new ItemStack(TFCItems.ceramicMold, 1, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.zincUnshaped, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.zincIngot, 1)), new ItemStack(TFCItems.ceramicMold, 1, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.weakSteelUnshaped, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.weakSteelIngot, 1)), new ItemStack(TFCItems.ceramicMold, 1, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.weakBlueSteelUnshaped, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.weakBlueSteelIngot, 1)), new ItemStack(TFCItems.ceramicMold, 1, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.weakRedSteelUnshaped, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.weakRedSteelIngot, 1)), new ItemStack(TFCItems.ceramicMold, 1, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.unknownUnshaped, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.unknownIngot, 1)), new ItemStack(TFCItems.ceramicMold, 1, 1)
        });
    
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.bismuthIngot, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.bismuthUnshaped, 1)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.bismuthBronzeIngot, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.bismuthBronzeUnshaped, 1)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.blackBronzeIngot, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.blackBronzeUnshaped, 1)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.blackSteelIngot, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.blackSteelUnshaped, 1)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.blueSteelIngot, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.blueSteelUnshaped, 1)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.brassIngot, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.brassUnshaped, 1)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.bronzeIngot, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.bronzeUnshaped, 1)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.copperIngot, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.copperUnshaped, 1)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.goldIngot, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.goldUnshaped, 1)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.highCarbonSteelIngot, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.highCarbonSteelUnshaped, 1)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.highCarbonBlackSteelIngot, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.highCarbonBlackSteelUnshaped, 1)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.highCarbonBlueSteelIngot, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.highCarbonBlueSteelUnshaped, 1)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.highCarbonRedSteelIngot, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.highCarbonRedSteelUnshaped, 1)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.wroughtIronIngot, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.wroughtIronUnshaped, 1)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.leadIngot, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.leadUnshaped, 1)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.nickelIngot, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.nickelUnshaped, 1)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.pigIronIngot, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.pigIronUnshaped, 1)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.platinumIngot, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.platinumUnshaped, 1)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.redSteelIngot, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.redSteelUnshaped, 1)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.roseGoldIngot, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.roseGoldUnshaped, 1)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.silverIngot, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.silverUnshaped, 1)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.steelIngot, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.steelUnshaped, 1)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.sterlingSilverIngot, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.sterlingSilverUnshaped, 1)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.tinIngot, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.tinUnshaped, 1)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.zincIngot, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.zincUnshaped, 1)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.weakSteelIngot, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.weakSteelUnshaped, 1)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.weakBlueSteelIngot, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.weakBlueSteelUnshaped, 1)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.weakRedSteelIngot, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.weakRedSteelUnshaped, 1)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.unknownIngot, 1, 0), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.unknownUnshaped, 1))
        });
    registerToolRecipes();
    registerFoodRecipes();
    registerKilnRecipes();
    registerToolMolds();
    registerQuernRecipes();
  }











  
  private static void vanillaRecipes() {
    removeRecipe(new ItemStack(Blocks.field_150462_ai));
    
    removeRecipe(new ItemStack((Item)Items.field_151112_aM));
    removeRecipe(new ItemStack(Blocks.field_150471_bO));
    removeRecipe(new ItemStack(Items.field_151033_d));
    removeRecipe(new ItemStack(Items.field_151044_h, 9));
    removeRecipe(new ItemStack(Items.field_151102_aT));
    removeRecipe(new ItemStack(Items.field_151069_bo, 3));
    removeRecipe(new ItemStack(Items.field_151121_aF, 3));

    
    removeRecipe(ItemBow.class);

    
    TFC_ConfigFiles.firstLoadCrafting();
  }


  
  private static void registerToolRecipes() {
    GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack(TFCItems.flintSteel, 1), new Object[] { Items.field_151145_ak, "ingotIron" }));
    GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack(TFCItems.flintSteel, 1), new Object[] { Items.field_151145_ak, "ingotSteel" }));
    
    GameRegistry.addRecipe(new ItemStack(TFCItems.rope, 1), new Object[] { "RR ", "RR ", "  R", Character.valueOf('R'), new ItemStack(TFCItems.juteFiber, 1) });
    
    GameRegistry.addRecipe(new ItemStack(TFCItems.goldPan, 1, 0), new Object[] { "1", Character.valueOf('1'), new ItemStack(TFCItems.potteryBowl, 1, 1) });
    
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.fireStarter, 1, 0), new Object[] { "2 ", " 2", Character.valueOf('2'), "stickWood" }));
    
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.bow, 1), new Object[] { " #$", "# $", " #$", Character.valueOf('#'), "stickWood", Character.valueOf('$'), "materialString" }));
    
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.fishingRod, 1), new Object[] { "  #", " #$", "# $", Character.valueOf('#'), "stickWood", Character.valueOf('$'), "materialString" }));
    
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.spindle, 1), new Object[] { "P", "#", Character.valueOf('P'), new ItemStack(TFCItems.spindleHead, 1, 1), Character.valueOf('#'), "stickWood" }));
    
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.spindleHead, 1, 0), new Object[] { "P", "#", Character.valueOf('P'), "lumpClay", Character.valueOf('#'), "stickWood" }));

    
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.igInStoneJavelin, 1, 0), new Object[] { "1", "2", Character.valueOf('1'), TFCItems.igInStoneJavelinHead, Character.valueOf('2'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.sedStoneJavelin, 1, 0), new Object[] { "1", "2", Character.valueOf('1'), TFCItems.sedStoneJavelinHead, Character.valueOf('2'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.igExStoneJavelin, 1, 0), new Object[] { "1", "2", Character.valueOf('1'), TFCItems.igExStoneJavelinHead, Character.valueOf('2'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.mMStoneJavelin, 1, 0), new Object[] { "1", "2", Character.valueOf('1'), TFCItems.mMStoneJavelinHead, Character.valueOf('2'), "stickWood" }));

    
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.igInShovel, 1, 0), new Object[] { "1", "2", Character.valueOf('1'), TFCItems.igInStoneShovelHead, Character.valueOf('2'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.sedShovel, 1, 0), new Object[] { "1", "2", Character.valueOf('1'), TFCItems.sedStoneShovelHead, Character.valueOf('2'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.igExShovel, 1, 0), new Object[] { "1", "2", Character.valueOf('1'), TFCItems.igExStoneShovelHead, Character.valueOf('2'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.mMShovel, 1, 0), new Object[] { "1", "2", Character.valueOf('1'), TFCItems.mMStoneShovelHead, Character.valueOf('2'), "stickWood" }));
    
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.igInAxe, 1, 0), new Object[] { "1", "2", Character.valueOf('1'), TFCItems.igInStoneAxeHead, Character.valueOf('2'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.sedAxe, 1, 0), new Object[] { "1", "2", Character.valueOf('1'), TFCItems.sedStoneAxeHead, Character.valueOf('2'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.igExAxe, 1, 0), new Object[] { "1", "2", Character.valueOf('1'), TFCItems.igExStoneAxeHead, Character.valueOf('2'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.mMAxe, 1, 0), new Object[] { "1", "2", Character.valueOf('1'), TFCItems.mMStoneAxeHead, Character.valueOf('2'), "stickWood" }));
    
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.igInHoe, 1, 0), new Object[] { "1", "2", Character.valueOf('1'), TFCItems.igInStoneHoeHead, Character.valueOf('2'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.sedHoe, 1, 0), new Object[] { "1", "2", Character.valueOf('1'), TFCItems.sedStoneHoeHead, Character.valueOf('2'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.igExHoe, 1, 0), new Object[] { "1", "2", Character.valueOf('1'), TFCItems.igExStoneHoeHead, Character.valueOf('2'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.mMHoe, 1, 0), new Object[] { "1", "2", Character.valueOf('1'), TFCItems.mMStoneHoeHead, Character.valueOf('2'), "stickWood" }));

    
    GameRegistry.addRecipe(new ItemStack(TFCItems.igInShovel, 1, 0), new Object[] { "1", "2", Character.valueOf('1'), TFCItems.igInStoneShovelHead, Character.valueOf('2'), new ItemStack(Items.field_151103_aS) });
    GameRegistry.addRecipe(new ItemStack(TFCItems.sedShovel, 1, 0), new Object[] { "1", "2", Character.valueOf('1'), TFCItems.sedStoneShovelHead, Character.valueOf('2'), new ItemStack(Items.field_151103_aS) });
    GameRegistry.addRecipe(new ItemStack(TFCItems.igExShovel, 1, 0), new Object[] { "1", "2", Character.valueOf('1'), TFCItems.igExStoneShovelHead, Character.valueOf('2'), new ItemStack(Items.field_151103_aS) });
    GameRegistry.addRecipe(new ItemStack(TFCItems.mMShovel, 1, 0), new Object[] { "1", "2", Character.valueOf('1'), TFCItems.mMStoneShovelHead, Character.valueOf('2'), new ItemStack(Items.field_151103_aS) });
    
    GameRegistry.addRecipe(new ItemStack(TFCItems.igInAxe, 1, 0), new Object[] { "1", "2", Character.valueOf('1'), TFCItems.igInStoneAxeHead, Character.valueOf('2'), new ItemStack(Items.field_151103_aS) });
    GameRegistry.addRecipe(new ItemStack(TFCItems.sedAxe, 1, 0), new Object[] { "1", "2", Character.valueOf('1'), TFCItems.sedStoneAxeHead, Character.valueOf('2'), new ItemStack(Items.field_151103_aS) });
    GameRegistry.addRecipe(new ItemStack(TFCItems.igExAxe, 1, 0), new Object[] { "1", "2", Character.valueOf('1'), TFCItems.igExStoneAxeHead, Character.valueOf('2'), new ItemStack(Items.field_151103_aS) });
    GameRegistry.addRecipe(new ItemStack(TFCItems.mMAxe, 1, 0), new Object[] { "1", "2", Character.valueOf('1'), TFCItems.mMStoneAxeHead, Character.valueOf('2'), new ItemStack(Items.field_151103_aS) });
    
    GameRegistry.addRecipe(new ItemStack(TFCItems.igInHoe, 1, 0), new Object[] { "1", "2", Character.valueOf('1'), TFCItems.igInStoneHoeHead, Character.valueOf('2'), new ItemStack(Items.field_151103_aS) });
    GameRegistry.addRecipe(new ItemStack(TFCItems.sedHoe, 1, 0), new Object[] { "1", "2", Character.valueOf('1'), TFCItems.sedStoneHoeHead, Character.valueOf('2'), new ItemStack(Items.field_151103_aS) });
    GameRegistry.addRecipe(new ItemStack(TFCItems.igExHoe, 1, 0), new Object[] { "1", "2", Character.valueOf('1'), TFCItems.igExStoneHoeHead, Character.valueOf('2'), new ItemStack(Items.field_151103_aS) });
    GameRegistry.addRecipe(new ItemStack(TFCItems.mMHoe, 1, 0), new Object[] { "1", "2", Character.valueOf('1'), TFCItems.mMStoneHoeHead, Character.valueOf('2'), new ItemStack(Items.field_151103_aS) });
    
    GameRegistry.addRecipe(new ItemStack(TFCItems.igInStoneJavelin, 1, 0), new Object[] { "1", "2", Character.valueOf('1'), TFCItems.igInStoneJavelinHead, Character.valueOf('2'), new ItemStack(Items.field_151103_aS) });
    GameRegistry.addRecipe(new ItemStack(TFCItems.sedStoneJavelin, 1, 0), new Object[] { "1", "2", Character.valueOf('1'), TFCItems.sedStoneJavelinHead, Character.valueOf('2'), new ItemStack(Items.field_151103_aS) });
    GameRegistry.addRecipe(new ItemStack(TFCItems.igExStoneJavelin, 1, 0), new Object[] { "1", "2", Character.valueOf('1'), TFCItems.igExStoneJavelinHead, Character.valueOf('2'), new ItemStack(Items.field_151103_aS) });
    GameRegistry.addRecipe(new ItemStack(TFCItems.mMStoneJavelin, 1, 0), new Object[] { "1", "2", Character.valueOf('1'), TFCItems.mMStoneJavelinHead, Character.valueOf('2'), new ItemStack(Items.field_151103_aS) });

    
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.stoneHammer, 1, 0), new Object[] { "1", "2", Character.valueOf('1'), TFCItems.stoneHammerHead, Character.valueOf('2'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.stoneHammer, 1, 0), new Object[] { "1", "2", Character.valueOf('1'), TFCItems.stoneHammerHead, Character.valueOf('2'), new ItemStack(Items.field_151103_aS) }));
    
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.stoneKnife, 1, 0), new Object[] { "1", "2", Character.valueOf('1'), TFCItems.stoneKnifeHead, Character.valueOf('2'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.stoneKnife, 1, 0), new Object[] { "1", "2", Character.valueOf('1'), TFCItems.stoneKnifeHead, Character.valueOf('2'), new ItemStack(Items.field_151103_aS) }));

    
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.bismuthBronzePick, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.bismuthBronzePickaxeHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.blackBronzePick, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.blackBronzePickaxeHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.blackSteelPick, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.blackSteelPickaxeHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.blueSteelPick, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.blueSteelPickaxeHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.bronzePick, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.bronzePickaxeHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.copperPick, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.copperPickaxeHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.wroughtIronPick, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.wroughtIronPickaxeHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.redSteelPick, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.redSteelPickaxeHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.steelPick, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.steelPickaxeHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));

    
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.bismuthBronzeShovel, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.bismuthBronzeShovelHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.blackBronzeShovel, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.blackBronzeShovelHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.blackSteelShovel, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.blackSteelShovelHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.blueSteelShovel, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.blueSteelShovelHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.bronzeShovel, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.bronzeShovelHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.copperShovel, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.copperShovelHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.wroughtIronShovel, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.wroughtIronShovelHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.redSteelShovel, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.redSteelShovelHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.steelShovel, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.steelShovelHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));

    
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.bismuthBronzeHoe, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.bismuthBronzeHoeHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.blackBronzeHoe, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.blackBronzeHoeHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.blackSteelHoe, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.blackSteelHoeHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.blueSteelHoe, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.blueSteelHoeHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.bronzeHoe, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.bronzeHoeHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.copperHoe, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.copperHoeHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.wroughtIronHoe, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.wroughtIronHoeHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.redSteelHoe, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.redSteelHoeHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.steelHoe, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.steelHoeHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));

    
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.bismuthBronzeAxe, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.bismuthBronzeAxeHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.blackBronzeAxe, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.blackBronzeAxeHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.blackSteelAxe, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.blackSteelAxeHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.blueSteelAxe, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.blueSteelAxeHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.bronzeAxe, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.bronzeAxeHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.copperAxe, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.copperAxeHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.wroughtIronAxe, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.wroughtIronAxeHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.redSteelAxe, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.redSteelAxeHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.steelAxe, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.steelAxeHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));

    
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.bismuthBronzeSword, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.bismuthBronzeSwordHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.blackBronzeSword, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.blackBronzeSwordHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.blackSteelSword, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.blackSteelSwordHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.blueSteelSword, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.blueSteelSwordHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.bronzeSword, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.bronzeSwordHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.copperSword, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.copperSwordHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.wroughtIronSword, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.wroughtIronSwordHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.redSteelSword, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.redSteelSwordHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.steelSword, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.steelSwordHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));

    
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.bismuthBronzeMace, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.bismuthBronzeMaceHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.blackBronzeMace, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.blackBronzeMaceHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.blackSteelMace, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.blackSteelMaceHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.blueSteelMace, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.blueSteelMaceHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.bronzeMace, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.bronzeMaceHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.copperMace, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.copperMaceHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.wroughtIronMace, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.wroughtIronMaceHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.redSteelMace, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.redSteelMaceHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.steelMace, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.steelMaceHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));

    
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.bismuthBronzeHammer, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.bismuthBronzeHammerHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.blackBronzeHammer, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.blackBronzeHammerHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.blackSteelHammer, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.blackSteelHammerHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.blueSteelHammer, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.blueSteelHammerHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.bronzeHammer, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.bronzeHammerHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.copperHammer, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.copperHammerHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.wroughtIronHammer, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.wroughtIronHammerHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.redSteelHammer, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.redSteelHammerHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.steelHammer, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.steelHammerHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));

    
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.bismuthBronzeSaw, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.bismuthBronzeSawHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.blackBronzeSaw, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.blackBronzeSawHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.blackSteelSaw, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.blackSteelSawHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.blueSteelSaw, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.blueSteelSawHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.bronzeSaw, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.bronzeSawHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.copperSaw, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.copperSawHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.wroughtIronSaw, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.wroughtIronSawHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.redSteelSaw, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.redSteelSawHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.steelSaw, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.steelSawHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));

    
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.bismuthBronzeChisel, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.bismuthBronzeChiselHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.blackBronzeChisel, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.blackBronzeChiselHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.blackSteelChisel, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.blackSteelChiselHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.blueSteelChisel, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.blueSteelChiselHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.bronzeChisel, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.bronzeChiselHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.copperChisel, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.copperChiselHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.wroughtIronChisel, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.wroughtIronChiselHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.redSteelChisel, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.redSteelChiselHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.steelChisel, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.steelChiselHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));

    
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.proPickBismuthBronze, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.bismuthBronzeProPickHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.proPickBlackBronze, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.blackBronzeProPickHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.proPickBlackSteel, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.blackSteelProPickHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.proPickBlueSteel, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.blueSteelProPickHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.proPickBronze, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.bronzeProPickHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.proPickCopper, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.copperProPickHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.proPickIron, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.wroughtIronProPickHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.proPickRedSteel, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.redSteelProPickHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.proPickSteel, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.steelProPickHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.bismuthBronzeScythe, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.bismuthBronzeScytheHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.blackBronzeScythe, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.blackBronzeScytheHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.blackSteelScythe, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.blackSteelScytheHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.blueSteelScythe, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.blueSteelScytheHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.bronzeScythe, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.bronzeScytheHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.copperScythe, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.copperScytheHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.wroughtIronScythe, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.wroughtIronScytheHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.redSteelScythe, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.redSteelScytheHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.steelScythe, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.steelScytheHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.bismuthBronzeKnife, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.bismuthBronzeKnifeHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.blackBronzeKnife, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.blackBronzeKnifeHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.blackSteelKnife, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.blackSteelKnifeHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.blueSteelKnife, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.blueSteelKnifeHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.bronzeKnife, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.bronzeKnifeHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.copperKnife, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.copperKnifeHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.wroughtIronKnife, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.wroughtIronKnifeHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.redSteelKnife, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.redSteelKnifeHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.steelKnife, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.steelKnifeHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));

    
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.bismuthBronzeJavelin, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.bismuthBronzeJavelinHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.blackBronzeJavelin, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.blackBronzeJavelinHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.blackSteelJavelin, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.blackSteelJavelinHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.blueSteelJavelin, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.blueSteelJavelinHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.bronzeJavelin, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.bronzeJavelinHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.copperJavelin, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.copperJavelinHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.wroughtIronJavelin, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.wroughtIronJavelinHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.redSteelJavelin, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.redSteelJavelinHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(TFCItems.steelJavelin, 1), new Object[] { "#", "I", Character.valueOf('#'), new ItemStack(TFCItems.steelJavelinHead, 1, 0), 
            Character.valueOf('I'), "stickWood" }));

    
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldPick, 1), new Object[] { "     ", " ### ", "#   #", "     ", Character.valueOf('#'), new ItemStack(TFCItems.flatClay, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldShovel, 1), new Object[] { " ### ", " ### ", " ### ", " ### ", "  #  ", Character.valueOf('#'), new ItemStack(TFCItems.flatClay, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldHoe, 1), new Object[] { "     ", "#####", "   ##", "     ", Character.valueOf('#'), new ItemStack(TFCItems.flatClay, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldAxe, 1), new Object[] { " #   ", "#### ", "#####", "#### ", " #   ", Character.valueOf('#'), new ItemStack(TFCItems.flatClay, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldHammer, 1), new Object[] { "     ", "#####", "#####", "  #  ", "     ", Character.valueOf('#'), new ItemStack(TFCItems.flatClay, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldChisel, 1), new Object[] { "  #  ", "  #  ", "  #  ", "  #  ", "  #  ", Character.valueOf('#'), new ItemStack(TFCItems.flatClay, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldSword, 1), new Object[] { "   ##", "  ###", " ### ", " ##  ", "#    ", Character.valueOf('#'), new ItemStack(TFCItems.flatClay, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldMace, 1), new Object[] { "  #  ", " ### ", " ### ", " ### ", "  #  ", Character.valueOf('#'), new ItemStack(TFCItems.flatClay, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldSaw, 1), new Object[] { "##   ", "###  ", " ### ", " ####", "   ##", Character.valueOf('#'), new ItemStack(TFCItems.flatClay, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldProPick, 1), new Object[] { "     ", " ####", "#   #", "    #", "     ", Character.valueOf('#'), new ItemStack(TFCItems.flatClay, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldScythe, 1), new Object[] { "     ", "#### ", " ####", "   ##", "     ", Character.valueOf('#'), new ItemStack(TFCItems.flatClay, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldKnife, 1), new Object[] { "  # ", " ## ", " ## ", " ## ", " ## ", Character.valueOf('#'), new ItemStack(TFCItems.flatClay, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldJavelin, 1), new Object[] { "###  ", "#### ", "#####", " ### ", "  #  ", Character.valueOf('#'), new ItemStack(TFCItems.flatClay, 1, 1) });
    
    registerAlloys();
    
    registerKnapping();

    
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.leatherHelmet, 1), new Object[] { "#####", "#   #", "#   #", Character.valueOf('#'), TFCItems.flatLeather });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.leatherChestplate, 1), new Object[] { "#   #", "#####", "#####", "#####", "#####", Character.valueOf('#'), TFCItems.flatLeather });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.leatherLeggings, 1), new Object[] { "#####", "#####", "## ##", "## ##", "## ##", Character.valueOf('#'), TFCItems.flatLeather });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.leatherBoots, 1), new Object[] { "##   ", "##   ", "##   ", "#### ", "#####", Character.valueOf('#'), TFCItems.flatLeather });
    
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.quiver, 1), new Object[] { " ####", "# ###", "# ###", "# ###", " ####", Character.valueOf('#'), TFCItems.flatLeather });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(Items.field_151141_av, 1), new Object[] { "  #  ", "#####", "#####", "#####", "  #  ", Character.valueOf('#'), TFCItems.flatLeather });
  }


  
  private static void registerKnapping() {
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.stoneKnifeHead, 2), new Object[] { " #  #", "## ##", "## ##", "## ##", "## ##", 
          Character.valueOf('#'), new ItemStack(TFCItems.flatRock, 1, 32767) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.stoneKnifeHead, 2), new Object[] { "#  # ", "## ##", "## ##", "## ##", "## ##", 
          Character.valueOf('#'), new ItemStack(TFCItems.flatRock, 1, 32767) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.stoneKnifeHead, 2), new Object[] { "#   #", "## ##", "## ##", "## ##", "## ##", 
          Character.valueOf('#'), new ItemStack(TFCItems.flatRock, 1, 32767) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.stoneKnifeHead, 2), new Object[] { " # # ", "## ##", "## ##", "## ##", "## ##", 
          Character.valueOf('#'), new ItemStack(TFCItems.flatRock, 1, 32767) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.stoneKnifeHead, 1), new Object[] { " #", "##", "##", "##", "##", 
          Character.valueOf('#'), new ItemStack(TFCItems.flatRock, 1, 32767) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.stoneHammerHead, 1), new Object[] { "#####", "#####", "  #  ", 
          Character.valueOf('#'), new ItemStack(TFCItems.flatRock, 1, 32767) });
    int i;
    for (i = 0; i < Global.STONE_IGIN.length; i++) {
      
      CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.igInStoneShovelHead, 1), new Object[] { "###", "###", "###", "###", " # ", Character.valueOf('#'), new ItemStack(TFCItems.flatRock, 1, i + 0) });
      CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.igInStoneAxeHead, 1), new Object[] { " #   ", "#### ", "#####", "#### ", " #   ", Character.valueOf('#'), new ItemStack(TFCItems.flatRock, 1, i + 0) });
      CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.igInStoneHoeHead, 1), new Object[] { "#####", "   ##", Character.valueOf('#'), new ItemStack(TFCItems.flatRock, 1, i + 0) });
      CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.igInStoneJavelinHead, 1), new Object[] { "###  ", "#### ", "#####", " ### ", "  #  ", Character.valueOf('#'), new ItemStack(TFCItems.flatRock, 1, i + 0) });
    } 
    for (i = 0; i < Global.STONE_SED.length; i++) {
      
      CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.sedStoneShovelHead, 1), new Object[] { "###", "###", "###", "###", " # ", Character.valueOf('#'), new ItemStack(TFCItems.flatRock, 1, i + Global.STONE_SED_START) });
      CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.sedStoneAxeHead, 1), new Object[] { " #   ", "#### ", "#####", "#### ", " #   ", Character.valueOf('#'), new ItemStack(TFCItems.flatRock, 1, i + Global.STONE_SED_START) });
      CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.sedStoneHoeHead, 1), new Object[] { "#####", "   ##", Character.valueOf('#'), new ItemStack(TFCItems.flatRock, 1, i + Global.STONE_SED_START) });
      CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.sedStoneJavelinHead, 1), new Object[] { "###  ", "#### ", "#####", " ### ", "  #  ", Character.valueOf('#'), new ItemStack(TFCItems.flatRock, 1, i + Global.STONE_SED_START) });
    } 
    for (i = 0; i < Global.STONE_IGEX.length; i++) {
      
      CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.igExStoneShovelHead, 1), new Object[] { "###", "###", "###", "###", " # ", Character.valueOf('#'), new ItemStack(TFCItems.flatRock, 1, i + Global.STONE_IGEX_START) });
      CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.igExStoneAxeHead, 1), new Object[] { " #   ", "#### ", "#####", "#### ", " #   ", Character.valueOf('#'), new ItemStack(TFCItems.flatRock, 1, i + Global.STONE_IGEX_START) });
      CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.igExStoneHoeHead, 1), new Object[] { "#####", "   ##", Character.valueOf('#'), new ItemStack(TFCItems.flatRock, 1, i + Global.STONE_IGEX_START) });
      CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.igExStoneJavelinHead, 1), new Object[] { "###  ", "#### ", "#####", " ### ", "  #  ", Character.valueOf('#'), new ItemStack(TFCItems.flatRock, 1, i + Global.STONE_IGEX_START) });
    } 
    for (i = 0; i < Global.STONE_MM.length; i++) {
      
      CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.mMStoneShovelHead, 1), new Object[] { "###", "###", "###", "###", " # ", Character.valueOf('#'), new ItemStack(TFCItems.flatRock, 1, i + Global.STONE_MM_START) });
      CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.mMStoneAxeHead, 1), new Object[] { " #   ", "#### ", "#####", "#### ", " #   ", Character.valueOf('#'), new ItemStack(TFCItems.flatRock, 1, i + Global.STONE_MM_START) });
      CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.mMStoneHoeHead, 1), new Object[] { "#####", "   ##", Character.valueOf('#'), new ItemStack(TFCItems.flatRock, 1, i + Global.STONE_MM_START) });
      CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.mMStoneJavelinHead, 1), new Object[] { "###  ", "#### ", "#####", " ### ", "  #  ", Character.valueOf('#'), new ItemStack(TFCItems.flatRock, 1, i + Global.STONE_MM_START) });
    } 


    
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.ceramicMold, 2, 0), new Object[] { "    ", " ## ", " ## ", " ## ", "    ", 



          
          Character.valueOf('#'), new ItemStack(TFCItems.flatClay, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.potteryJug, 1, 0), new Object[] { "X XXX", "    X", "   X ", "    X", "   XX", 



          
          Character.valueOf('X'), new ItemStack(TFCItems.flatClay, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.potterySmallVessel, 1, 0), new Object[] { "#   #", "     ", "     ", "     ", "#   #", 



          
          Character.valueOf('#'), new ItemStack(TFCItems.flatClay, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCBlocks.flowerPot), new Object[] { "#   #", " ### ", " ### ", " ### ", "#   #", 



          
          Character.valueOf('#'), new ItemStack(TFCItems.flatClay, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCBlocks.crucible, 1), new Object[] { " ### ", " ### ", " ### ", " ### ", "     ", 



          
          Character.valueOf('#'), new ItemStack(TFCItems.flatClay, 1, 3) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCBlocks.vessel, 1), new Object[] { " ### ", " ### ", " ### ", " ### ", "     ", 



          
          Character.valueOf('#'), new ItemStack(TFCItems.flatClay, 1, 1) });
  }











  
  private static void registerAlloys() {
    CraftingManagerTFC.getInstance().addShapelessRecipe(new ItemStack(TFCItems.bismuthBronzeUnshaped, 4), new Object[] { new ItemStack(TFCItems.copperUnshaped), new ItemStack(TFCItems.copperUnshaped), new ItemStack(TFCItems.tinUnshaped), new ItemStack(TFCItems.bismuthUnshaped) });


    
    CraftingManagerTFC.getInstance().addShapelessRecipe(new ItemStack(TFCItems.bismuthBronzeUnshaped, 4), new Object[] { new ItemStack(TFCItems.copperUnshaped), new ItemStack(TFCItems.copperUnshaped), new ItemStack(TFCItems.zincUnshaped), new ItemStack(TFCItems.bismuthUnshaped) });


    
    CraftingManagerTFC.getInstance().addShapelessRecipe(new ItemStack(TFCItems.blackBronzeUnshaped, 4), new Object[] { new ItemStack(TFCItems.copperUnshaped), new ItemStack(TFCItems.copperUnshaped), new ItemStack(TFCItems.silverUnshaped), new ItemStack(TFCItems.goldUnshaped) });


    
    CraftingManagerTFC.getInstance().addShapelessRecipe(new ItemStack(TFCItems.weakSteelUnshaped, 4), new Object[] { new ItemStack(TFCItems.steelUnshaped), new ItemStack(TFCItems.steelUnshaped), new ItemStack(TFCItems.nickelUnshaped), new ItemStack(TFCItems.blackBronzeUnshaped) });


    
    CraftingManagerTFC.getInstance().addShapelessRecipe(new ItemStack(TFCItems.weakBlueSteelUnshaped, 4), new Object[] { new ItemStack(TFCItems.blackSteelUnshaped), new ItemStack(TFCItems.bismuthBronzeUnshaped), new ItemStack(TFCItems.sterlingSilverUnshaped), new ItemStack(TFCItems.steelUnshaped) });


    
    CraftingManagerTFC.getInstance().addShapelessRecipe(new ItemStack(TFCItems.brassUnshaped, 4), new Object[] { new ItemStack(TFCItems.copperUnshaped), new ItemStack(TFCItems.copperUnshaped), new ItemStack(TFCItems.copperUnshaped), new ItemStack(TFCItems.zincUnshaped) });


    
    CraftingManagerTFC.getInstance().addShapelessRecipe(new ItemStack(TFCItems.bronzeUnshaped, 4), new Object[] { new ItemStack(TFCItems.copperUnshaped), new ItemStack(TFCItems.copperUnshaped), new ItemStack(TFCItems.copperUnshaped), new ItemStack(TFCItems.tinUnshaped) });


    
    CraftingManagerTFC.getInstance().addShapelessRecipe(new ItemStack(TFCItems.weakRedSteelUnshaped, 4), new Object[] { new ItemStack(TFCItems.blackSteelUnshaped), new ItemStack(TFCItems.roseGoldUnshaped), new ItemStack(TFCItems.brassUnshaped), new ItemStack(TFCItems.steelUnshaped) });


    
    CraftingManagerTFC.getInstance().addShapelessRecipe(new ItemStack(TFCItems.roseGoldUnshaped, 4), new Object[] { new ItemStack(TFCItems.copperUnshaped), new ItemStack(TFCItems.goldUnshaped), new ItemStack(TFCItems.goldUnshaped), new ItemStack(TFCItems.goldUnshaped) });


    
    CraftingManagerTFC.getInstance().addShapelessRecipe(new ItemStack(TFCItems.highCarbonSteelUnshaped, 4), new Object[] { new ItemStack(TFCItems.pigIronUnshaped), new ItemStack(TFCItems.wroughtIronUnshaped), new ItemStack(TFCItems.wroughtIronUnshaped), new ItemStack(TFCItems.wroughtIronUnshaped) });


    
    CraftingManagerTFC.getInstance().addShapelessRecipe(new ItemStack(TFCItems.sterlingSilverUnshaped, 4), new Object[] { new ItemStack(TFCItems.copperUnshaped), new ItemStack(TFCItems.silverUnshaped), new ItemStack(TFCItems.silverUnshaped), new ItemStack(TFCItems.silverUnshaped) });
  }






  
  private static void registerToolMolds() {
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldAxe, 1, 2), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.copperUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldAxe, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldAxe, 1, 3), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.bronzeUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldAxe, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldAxe, 1, 4), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.bismuthBronzeUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldAxe, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldAxe, 1, 5), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.blackBronzeUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldAxe, 1, 1) });
    
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldChisel, 1, 2), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.copperUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldChisel, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldChisel, 1, 3), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.bronzeUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldChisel, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldChisel, 1, 4), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.bismuthBronzeUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldChisel, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldChisel, 1, 5), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.blackBronzeUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldChisel, 1, 1) });
    
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldHammer, 1, 2), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.copperUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldHammer, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldHammer, 1, 3), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.bronzeUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldHammer, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldHammer, 1, 4), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.bismuthBronzeUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldHammer, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldHammer, 1, 5), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.blackBronzeUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldHammer, 1, 1) });
    
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldHoe, 1, 2), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.copperUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldHoe, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldHoe, 1, 3), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.bronzeUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldHoe, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldHoe, 1, 4), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.bismuthBronzeUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldHoe, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldHoe, 1, 5), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.blackBronzeUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldHoe, 1, 1) });
    
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldKnife, 1, 2), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.copperUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldKnife, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldKnife, 1, 3), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.bronzeUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldKnife, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldKnife, 1, 4), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.bismuthBronzeUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldKnife, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldKnife, 1, 5), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.blackBronzeUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldKnife, 1, 1) });
    
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldJavelin, 1, 2), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.copperUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldJavelin, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldJavelin, 1, 3), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.bronzeUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldJavelin, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldJavelin, 1, 4), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.bismuthBronzeUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldJavelin, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldJavelin, 1, 5), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.blackBronzeUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldJavelin, 1, 1) });
    
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldMace, 1, 2), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.copperUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldMace, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldMace, 1, 3), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.bronzeUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldMace, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldMace, 1, 4), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.bismuthBronzeUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldMace, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldMace, 1, 5), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.blackBronzeUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldMace, 1, 1) });
    
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldPick, 1, 2), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.copperUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldPick, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldPick, 1, 3), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.bronzeUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldPick, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldPick, 1, 4), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.bismuthBronzeUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldPick, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldPick, 1, 5), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.blackBronzeUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldPick, 1, 1) });
    
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldProPick, 1, 2), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.copperUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldProPick, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldProPick, 1, 3), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.bronzeUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldProPick, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldProPick, 1, 4), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.bismuthBronzeUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldProPick, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldProPick, 1, 5), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.blackBronzeUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldProPick, 1, 1) });
    
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldSaw, 1, 2), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.copperUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldSaw, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldSaw, 1, 3), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.bronzeUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldSaw, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldSaw, 1, 4), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.bismuthBronzeUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldSaw, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldSaw, 1, 5), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.blackBronzeUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldSaw, 1, 1) });
    
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldScythe, 1, 2), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.copperUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldScythe, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldScythe, 1, 3), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.bronzeUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldScythe, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldScythe, 1, 4), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.bismuthBronzeUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldScythe, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldScythe, 1, 5), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.blackBronzeUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldScythe, 1, 1) });
    
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldShovel, 1, 2), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.copperUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldShovel, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldShovel, 1, 3), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.bronzeUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldShovel, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldShovel, 1, 4), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.bismuthBronzeUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldShovel, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldShovel, 1, 5), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.blackBronzeUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldShovel, 1, 1) });
    
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldSword, 1, 2), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.copperUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldSword, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldSword, 1, 3), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.bronzeUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldSword, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldSword, 1, 4), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.bismuthBronzeUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldSword, 1, 1) });
    CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.clayMoldSword, 1, 5), new Object[] { "12", 
          Character.valueOf('1'), getStackTemp(new ItemStack(TFCItems.blackBronzeUnshaped, 1, 1)), Character.valueOf('2'), new ItemStack(TFCItems.clayMoldSword, 1, 1) });


    
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.copperAxeHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldAxe, 1, 2)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.bronzeAxeHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldAxe, 1, 3)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.bismuthBronzeAxeHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldAxe, 1, 4)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.blackBronzeAxeHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldAxe, 1, 5))
        });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.copperChiselHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldChisel, 1, 2)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.bronzeChiselHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldChisel, 1, 3)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.bismuthBronzeChiselHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldChisel, 1, 4)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.blackBronzeChiselHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldChisel, 1, 5))
        });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.copperHammerHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldHammer, 1, 2)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.bronzeHammerHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldHammer, 1, 3)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.bismuthBronzeHammerHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldHammer, 1, 4)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.blackBronzeHammerHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldHammer, 1, 5))
        });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.copperHoeHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldHoe, 1, 2)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.bronzeHoeHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldHoe, 1, 3)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.bismuthBronzeHoeHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldHoe, 1, 4)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.blackBronzeHoeHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldHoe, 1, 5))
        });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.copperKnifeHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldKnife, 1, 2)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.bronzeKnifeHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldKnife, 1, 3)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.bismuthBronzeKnifeHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldKnife, 1, 4)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.blackBronzeKnifeHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldKnife, 1, 5))
        });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.copperJavelinHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldJavelin, 1, 2)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.bronzeJavelinHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldJavelin, 1, 3)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.bismuthBronzeJavelinHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldJavelin, 1, 4)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.blackBronzeJavelinHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldJavelin, 1, 5))
        });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.copperMaceHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldMace, 1, 2)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.bronzeMaceHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldMace, 1, 3)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.bismuthBronzeMaceHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldMace, 1, 4)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.blackBronzeMaceHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldMace, 1, 5))
        });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.copperPickaxeHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldPick, 1, 2)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.bronzePickaxeHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldPick, 1, 3)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.bismuthBronzePickaxeHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldPick, 1, 4)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.blackBronzePickaxeHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldPick, 1, 5))
        });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.copperProPickHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldProPick, 1, 2)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.bronzeProPickHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldProPick, 1, 3)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.bismuthBronzeProPickHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldProPick, 1, 4)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.blackBronzeProPickHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldProPick, 1, 5))
        });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.copperSawHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldSaw, 1, 2)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.bronzeSawHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldSaw, 1, 3)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.bismuthBronzeSawHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldSaw, 1, 4)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.blackBronzeSawHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldSaw, 1, 5))
        });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.copperScytheHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldScythe, 1, 2)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.bronzeScytheHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldScythe, 1, 3)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.bismuthBronzeScytheHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldScythe, 1, 4)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.blackBronzeScytheHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldScythe, 1, 5))
        });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.copperShovelHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldShovel, 1, 2)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.bronzeShovelHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldShovel, 1, 3)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.bismuthBronzeShovelHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldShovel, 1, 4)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.blackBronzeShovelHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldShovel, 1, 5))
        });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.copperSwordHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldSword, 1, 2)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.bronzeSwordHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldSword, 1, 3)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.bismuthBronzeSwordHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldSword, 1, 4)) });
    GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.blackBronzeSwordHead), new Object[] {
          getStackNoTemp(new ItemStack(TFCItems.clayMoldSword, 1, 5))
        });
  }
  
  public static ItemStack getStackTemp(ItemStack is) {
    NBTTagCompound nbt = new NBTTagCompound();
    nbt.func_74757_a("temp", true);
    is.func_77982_d(nbt);
    return is;
  }

  
  public static ItemStack getStackNoTemp(ItemStack is) {
    NBTTagCompound noTemp = new NBTTagCompound();
    noTemp.func_74757_a("noTemp", true);
    is.func_77982_d(noTemp);
    return is;
  }

  
  public static void registerAnvilRecipes(Random r, World world) {
    AnvilManager manager = AnvilManager.getInstance();
    
    AnvilManager.world = world;
    
    manager.addPlan("ingot", new PlanRecipe(new RuleEnum[] { RuleEnum.HITLAST, RuleEnum.HITSECONDFROMLAST, RuleEnum.HITTHIRDFROMLAST }));
    
    manager.addPlan("sheet", new PlanRecipe(new RuleEnum[] { RuleEnum.HITLAST, RuleEnum.HITSECONDFROMLAST, RuleEnum.HITTHIRDFROMLAST }));
    
    manager.addPlan("pickaxe", new PlanRecipe(new RuleEnum[] { RuleEnum.PUNCHLAST, RuleEnum.BENDNOTLAST, RuleEnum.DRAWNOTLAST }));
    
    manager.addPlan("shovel", new PlanRecipe(new RuleEnum[] { RuleEnum.PUNCHLAST, RuleEnum.HITNOTLAST, RuleEnum.ANY }));
    
    manager.addPlan("axe", new PlanRecipe(new RuleEnum[] { RuleEnum.PUNCHLAST, RuleEnum.HITSECONDFROMLAST, RuleEnum.UPSETTHIRDFROMLAST }));
    manager.addPlan("hoe", new PlanRecipe(new RuleEnum[] { RuleEnum.PUNCHLAST, RuleEnum.HITNOTLAST, RuleEnum.BENDNOTLAST }));
    manager.addPlan("hammer", new PlanRecipe(new RuleEnum[] { RuleEnum.PUNCHLAST, RuleEnum.ANY, RuleEnum.SHRINKNOTLAST }));
    manager.addPlan("chisel", new PlanRecipe(new RuleEnum[] { RuleEnum.HITLAST, RuleEnum.HITNOTLAST, RuleEnum.DRAWNOTLAST }));
    manager.addPlan("propick", new PlanRecipe(new RuleEnum[] { RuleEnum.PUNCHLAST, RuleEnum.DRAWNOTLAST, RuleEnum.BENDNOTLAST }));
    manager.addPlan("saw", new PlanRecipe(new RuleEnum[] { RuleEnum.HITLAST, RuleEnum.HITSECONDFROMLAST, RuleEnum.ANY }));
    manager.addPlan("sword", new PlanRecipe(new RuleEnum[] { RuleEnum.HITLAST, RuleEnum.BENDSECONDFROMLAST, RuleEnum.BENDTHIRDFROMLAST }));
    manager.addPlan("mace", new PlanRecipe(new RuleEnum[] { RuleEnum.HITLAST, RuleEnum.SHRINKNOTLAST, RuleEnum.BENDNOTLAST }));
    manager.addPlan("scythe", new PlanRecipe(new RuleEnum[] { RuleEnum.HITLAST, RuleEnum.DRAWSECONDFROMLAST, RuleEnum.BENDTHIRDFROMLAST }));
    manager.addPlan("knife", new PlanRecipe(new RuleEnum[] { RuleEnum.HITLAST, RuleEnum.DRAWSECONDFROMLAST, RuleEnum.DRAWTHIRDFROMLAST }));
    manager.addPlan("javelin", new PlanRecipe(new RuleEnum[] { RuleEnum.HITLAST, RuleEnum.HITSECONDFROMLAST, RuleEnum.DRAWTHIRDFROMLAST }));
    manager.addPlan("helmplate", new PlanRecipe(new RuleEnum[] { RuleEnum.HITLAST, RuleEnum.BENDSECONDFROMLAST, RuleEnum.BENDTHIRDFROMLAST }));
    manager.addPlan("chestplate", new PlanRecipe(new RuleEnum[] { RuleEnum.HITLAST, RuleEnum.HITSECONDFROMLAST, RuleEnum.UPSETTHIRDFROMLAST }));
    manager.addPlan("legsplate", new PlanRecipe(new RuleEnum[] { RuleEnum.BENDANY, RuleEnum.DRAWANY, RuleEnum.HITANY }));
    manager.addPlan("bootsplate", new PlanRecipe(new RuleEnum[] { RuleEnum.BENDLAST, RuleEnum.BENDSECONDFROMLAST, RuleEnum.SHRINKTHIRDFROMLAST }));
    manager.addPlan("bucket", new PlanRecipe(new RuleEnum[] { RuleEnum.BENDLAST, RuleEnum.BENDSECONDFROMLAST, RuleEnum.BENDTHIRDFROMLAST }));
    manager.addPlan("refinebloom", new PlanRecipe(new RuleEnum[] { RuleEnum.HITLAST, RuleEnum.HITSECONDFROMLAST, RuleEnum.HITTHIRDFROMLAST }));
    manager.addPlan("splitbloom", new PlanRecipe(new RuleEnum[] { RuleEnum.PUNCHLAST, RuleEnum.ANY, RuleEnum.ANY }));
    manager.addPlan("tuyere", new PlanRecipe(new RuleEnum[] { RuleEnum.BENDLAST, RuleEnum.BENDSECONDFROMLAST, RuleEnum.ANY }));
    manager.addPlan("trapdoor", new PlanRecipe(new RuleEnum[] { RuleEnum.HITLAST, RuleEnum.SHRINKNOTLAST, RuleEnum.UPSETNOTLAST }));
    manager.addPlan("grill", new PlanRecipe(new RuleEnum[] { RuleEnum.BENDLAST, RuleEnum.DRAWSECONDFROMLAST, RuleEnum.DRAWTHIRDFROMLAST }));
    manager.addPlan("shears", new PlanRecipe(new RuleEnum[] { RuleEnum.HITLAST, RuleEnum.HITSECONDFROMLAST, RuleEnum.HITTHIRDFROMLAST }));
    manager.addPlan("oillamp", new PlanRecipe(new RuleEnum[] { RuleEnum.BENDLAST, RuleEnum.BENDSECONDFROMLAST, RuleEnum.DRAWTHIRDFROMLAST }));
    manager.addPlan("hopper", new PlanRecipe(new RuleEnum[] { RuleEnum.UPSETLAST, RuleEnum.HITSECONDFROMLAST, RuleEnum.BENDTHIRDFROMLAST }));
    
    addWeldRecipes(manager);




    
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.pigIronIngot), null, "ingot", false, AnvilReq.WROUGHTIRON, new ItemStack(TFCItems.highCarbonSteelIngot))).clearRecipeSkills());
    
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.highCarbonBlackSteelIngot), null, "ingot", false, AnvilReq.STEEL, new ItemStack(TFCItems.blackSteelIngot))).clearRecipeSkills());
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.highCarbonBlueSteelIngot), null, "ingot", false, AnvilReq.BLACKSTEEL, new ItemStack(TFCItems.blueSteelIngot))).clearRecipeSkills());
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.highCarbonRedSteelIngot), null, "ingot", false, AnvilReq.BLACKSTEEL, new ItemStack(TFCItems.redSteelIngot))).clearRecipeSkills());
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.highCarbonSteelIngot), null, "ingot", false, AnvilReq.WROUGHTIRON, new ItemStack(TFCItems.steelIngot))).clearRecipeSkills());
    
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bismuthIngot2x), null, "sheet", false, AnvilReq.STONE, new ItemStack(TFCItems.bismuthSheet)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeIngot2x), null, "sheet", false, AnvilReq.BISMUTHBRONZE, new ItemStack(TFCItems.bismuthBronzeSheet)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackBronzeIngot2x), null, "sheet", false, AnvilReq.BLACKBRONZE, new ItemStack(TFCItems.blackBronzeSheet)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackSteelIngot2x), null, "sheet", false, AnvilReq.BLACKSTEEL, new ItemStack(TFCItems.blackSteelSheet)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blueSteelIngot2x), null, "sheet", false, AnvilReq.BLUESTEEL, new ItemStack(TFCItems.blueSteelSheet)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.brassIngot2x), null, "sheet", false, AnvilReq.COPPER, new ItemStack(TFCItems.brassSheet)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bronzeIngot2x), null, "sheet", false, AnvilReq.BRONZE, new ItemStack(TFCItems.bronzeSheet)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.copperIngot2x), null, "sheet", false, AnvilReq.COPPER, new ItemStack(TFCItems.copperSheet)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.goldIngot2x), null, "sheet", false, AnvilReq.COPPER, new ItemStack(TFCItems.goldSheet)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.wroughtIronIngot2x), null, "sheet", false, AnvilReq.WROUGHTIRON, new ItemStack(TFCItems.wroughtIronSheet)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.leadIngot2x), null, "sheet", false, AnvilReq.COPPER, new ItemStack(TFCItems.leadSheet)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.nickelIngot2x), null, "sheet", false, AnvilReq.WROUGHTIRON, new ItemStack(TFCItems.nickelSheet)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.pigIronIngot2x), null, "sheet", false, AnvilReq.BRONZE, new ItemStack(TFCItems.pigIronSheet)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.platinumIngot2x), null, "sheet", false, AnvilReq.BRONZE, new ItemStack(TFCItems.platinumSheet)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.redSteelIngot2x), null, "sheet", false, AnvilReq.REDSTEEL, new ItemStack(TFCItems.redSteelSheet)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.roseGoldIngot2x), null, "sheet", false, AnvilReq.BRONZE, new ItemStack(TFCItems.roseGoldSheet)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.silverIngot2x), null, "sheet", false, AnvilReq.COPPER, new ItemStack(TFCItems.silverSheet)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.steelIngot2x), null, "sheet", false, AnvilReq.STEEL, new ItemStack(TFCItems.steelSheet)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.sterlingSilverIngot2x), null, "sheet", false, AnvilReq.BRONZE, new ItemStack(TFCItems.sterlingSilverSheet)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.tinIngot2x), null, "sheet", false, AnvilReq.STONE, new ItemStack(TFCItems.tinSheet)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.zincIngot2x), null, "sheet", false, AnvilReq.STONE, new ItemStack(TFCItems.zincSheet)));

    
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeIngot), null, "pickaxe", AnvilReq.BISMUTHBRONZE, new ItemStack(TFCItems.bismuthBronzePickaxeHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackBronzeIngot), null, "pickaxe", AnvilReq.BLACKBRONZE, new ItemStack(TFCItems.blackBronzePickaxeHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackSteelIngot), null, "pickaxe", AnvilReq.BLACKSTEEL, new ItemStack(TFCItems.blackSteelPickaxeHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blueSteelIngot), null, "pickaxe", AnvilReq.BLUESTEEL, new ItemStack(TFCItems.blueSteelPickaxeHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bronzeIngot), null, "pickaxe", AnvilReq.BRONZE, new ItemStack(TFCItems.bronzePickaxeHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.copperIngot), null, "pickaxe", AnvilReq.COPPER, new ItemStack(TFCItems.copperPickaxeHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.wroughtIronIngot), null, "pickaxe", AnvilReq.WROUGHTIRON, new ItemStack(TFCItems.wroughtIronPickaxeHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.redSteelIngot), null, "pickaxe", AnvilReq.REDSTEEL, new ItemStack(TFCItems.redSteelPickaxeHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.steelIngot), null, "pickaxe", AnvilReq.STEEL, new ItemStack(TFCItems.steelPickaxeHead, 1))).addRecipeSkill("skill.toolsmith"));


    
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeIngot), null, "shovel", AnvilReq.BISMUTHBRONZE, new ItemStack(TFCItems.bismuthBronzeShovelHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackBronzeIngot), null, "shovel", AnvilReq.BLACKBRONZE, new ItemStack(TFCItems.blackBronzeShovelHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackSteelIngot), null, "shovel", AnvilReq.BLACKSTEEL, new ItemStack(TFCItems.blackSteelShovelHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blueSteelIngot), null, "shovel", AnvilReq.BLUESTEEL, new ItemStack(TFCItems.blueSteelShovelHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bronzeIngot), null, "shovel", AnvilReq.BRONZE, new ItemStack(TFCItems.bronzeShovelHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.copperIngot), null, "shovel", AnvilReq.COPPER, new ItemStack(TFCItems.copperShovelHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.wroughtIronIngot), null, "shovel", AnvilReq.WROUGHTIRON, new ItemStack(TFCItems.wroughtIronShovelHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.redSteelIngot), null, "shovel", AnvilReq.REDSTEEL, new ItemStack(TFCItems.redSteelShovelHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.steelIngot), null, "shovel", AnvilReq.STEEL, new ItemStack(TFCItems.steelShovelHead, 1))).addRecipeSkill("skill.toolsmith"));

    
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeIngot), null, "axe", AnvilReq.BISMUTHBRONZE, new ItemStack(TFCItems.bismuthBronzeAxeHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackBronzeIngot), null, "axe", AnvilReq.BLACKBRONZE, new ItemStack(TFCItems.blackBronzeAxeHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackSteelIngot), null, "axe", AnvilReq.BLACKSTEEL, new ItemStack(TFCItems.blackSteelAxeHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blueSteelIngot), null, "axe", AnvilReq.BLUESTEEL, new ItemStack(TFCItems.blueSteelAxeHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bronzeIngot), null, "axe", AnvilReq.BRONZE, new ItemStack(TFCItems.bronzeAxeHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.copperIngot), null, "axe", AnvilReq.COPPER, new ItemStack(TFCItems.copperAxeHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.wroughtIronIngot), null, "axe", AnvilReq.WROUGHTIRON, new ItemStack(TFCItems.wroughtIronAxeHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.redSteelIngot), null, "axe", AnvilReq.REDSTEEL, new ItemStack(TFCItems.redSteelAxeHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.steelIngot), null, "axe", AnvilReq.STEEL, new ItemStack(TFCItems.steelAxeHead, 1))).addRecipeSkill("skill.toolsmith"));

    
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeIngot), null, "hoe", AnvilReq.BISMUTHBRONZE, new ItemStack(TFCItems.bismuthBronzeHoeHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackBronzeIngot), null, "hoe", AnvilReq.BLACKBRONZE, new ItemStack(TFCItems.blackBronzeHoeHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackSteelIngot), null, "hoe", AnvilReq.BLACKSTEEL, new ItemStack(TFCItems.blackSteelHoeHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blueSteelIngot), null, "hoe", AnvilReq.BLUESTEEL, new ItemStack(TFCItems.blueSteelHoeHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bronzeIngot), null, "hoe", AnvilReq.BRONZE, new ItemStack(TFCItems.bronzeHoeHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.copperIngot), null, "hoe", AnvilReq.COPPER, new ItemStack(TFCItems.copperHoeHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.wroughtIronIngot), null, "hoe", AnvilReq.WROUGHTIRON, new ItemStack(TFCItems.wroughtIronHoeHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.redSteelIngot), null, "hoe", AnvilReq.REDSTEEL, new ItemStack(TFCItems.redSteelHoeHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.steelIngot), null, "hoe", AnvilReq.STEEL, new ItemStack(TFCItems.steelHoeHead, 1))).addRecipeSkill("skill.toolsmith"));

    
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeIngot), null, "hammer", AnvilReq.BISMUTHBRONZE, new ItemStack(TFCItems.bismuthBronzeHammerHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackBronzeIngot), null, "hammer", AnvilReq.BLACKBRONZE, new ItemStack(TFCItems.blackBronzeHammerHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackSteelIngot), null, "hammer", AnvilReq.BLACKSTEEL, new ItemStack(TFCItems.blackSteelHammerHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blueSteelIngot), null, "hammer", AnvilReq.BLUESTEEL, new ItemStack(TFCItems.blueSteelHammerHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bronzeIngot), null, "hammer", AnvilReq.BRONZE, new ItemStack(TFCItems.bronzeHammerHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.copperIngot), null, "hammer", AnvilReq.COPPER, new ItemStack(TFCItems.copperHammerHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.wroughtIronIngot), null, "hammer", AnvilReq.WROUGHTIRON, new ItemStack(TFCItems.wroughtIronHammerHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.redSteelIngot), null, "hammer", AnvilReq.REDSTEEL, new ItemStack(TFCItems.redSteelHammerHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.steelIngot), null, "hammer", AnvilReq.STEEL, new ItemStack(TFCItems.steelHammerHead, 1))).addRecipeSkill("skill.toolsmith"));

    
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeIngot), null, "chisel", AnvilReq.BISMUTHBRONZE, new ItemStack(TFCItems.bismuthBronzeChiselHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackBronzeIngot), null, "chisel", AnvilReq.BLACKBRONZE, new ItemStack(TFCItems.blackBronzeChiselHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackSteelIngot), null, "chisel", AnvilReq.BLACKSTEEL, new ItemStack(TFCItems.blackSteelChiselHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blueSteelIngot), null, "chisel", AnvilReq.BLUESTEEL, new ItemStack(TFCItems.blueSteelChiselHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bronzeIngot), null, "chisel", AnvilReq.BRONZE, new ItemStack(TFCItems.bronzeChiselHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.copperIngot), null, "chisel", AnvilReq.COPPER, new ItemStack(TFCItems.copperChiselHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.wroughtIronIngot), null, "chisel", AnvilReq.WROUGHTIRON, new ItemStack(TFCItems.wroughtIronChiselHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.redSteelIngot), null, "chisel", AnvilReq.REDSTEEL, new ItemStack(TFCItems.redSteelChiselHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.steelIngot), null, "chisel", AnvilReq.STEEL, new ItemStack(TFCItems.steelChiselHead, 1))).addRecipeSkill("skill.toolsmith"));

    
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeIngot), null, "propick", AnvilReq.BISMUTHBRONZE, new ItemStack(TFCItems.bismuthBronzeProPickHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackBronzeIngot), null, "propick", AnvilReq.BLACKBRONZE, new ItemStack(TFCItems.blackBronzeProPickHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackSteelIngot), null, "propick", AnvilReq.BLACKSTEEL, new ItemStack(TFCItems.blackSteelProPickHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blueSteelIngot), null, "propick", AnvilReq.BLUESTEEL, new ItemStack(TFCItems.blueSteelProPickHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bronzeIngot), null, "propick", AnvilReq.BRONZE, new ItemStack(TFCItems.bronzeProPickHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.copperIngot), null, "propick", AnvilReq.COPPER, new ItemStack(TFCItems.copperProPickHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.wroughtIronIngot), null, "propick", AnvilReq.WROUGHTIRON, new ItemStack(TFCItems.wroughtIronProPickHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.redSteelIngot), null, "propick", AnvilReq.REDSTEEL, new ItemStack(TFCItems.redSteelProPickHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.steelIngot), null, "propick", AnvilReq.STEEL, new ItemStack(TFCItems.steelProPickHead, 1))).addRecipeSkill("skill.toolsmith"));

    
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeIngot), null, "saw", AnvilReq.BISMUTHBRONZE, new ItemStack(TFCItems.bismuthBronzeSawHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackBronzeIngot), null, "saw", AnvilReq.BLACKBRONZE, new ItemStack(TFCItems.blackBronzeSawHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackSteelIngot), null, "saw", AnvilReq.BLACKSTEEL, new ItemStack(TFCItems.blackSteelSawHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blueSteelIngot), null, "saw", AnvilReq.BLUESTEEL, new ItemStack(TFCItems.blueSteelSawHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bronzeIngot), null, "saw", AnvilReq.BRONZE, new ItemStack(TFCItems.bronzeSawHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.copperIngot), null, "saw", AnvilReq.COPPER, new ItemStack(TFCItems.copperSawHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.wroughtIronIngot), null, "saw", AnvilReq.WROUGHTIRON, new ItemStack(TFCItems.wroughtIronSawHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.redSteelIngot), null, "saw", AnvilReq.REDSTEEL, new ItemStack(TFCItems.redSteelSawHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.steelIngot), null, "saw", AnvilReq.STEEL, new ItemStack(TFCItems.steelSawHead, 1))).addRecipeSkill("skill.toolsmith"));

    
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeIngot2x), null, "sword", AnvilReq.BISMUTHBRONZE, new ItemStack(TFCItems.bismuthBronzeSwordHead, 1))).addRecipeSkill("skill.weaponsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackBronzeIngot2x), null, "sword", AnvilReq.BLACKBRONZE, new ItemStack(TFCItems.blackBronzeSwordHead, 1))).addRecipeSkill("skill.weaponsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackSteelIngot2x), null, "sword", AnvilReq.BLACKSTEEL, new ItemStack(TFCItems.blackSteelSwordHead, 1))).addRecipeSkill("skill.weaponsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blueSteelIngot2x), null, "sword", AnvilReq.BLUESTEEL, new ItemStack(TFCItems.blueSteelSwordHead, 1))).addRecipeSkill("skill.weaponsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bronzeIngot2x), null, "sword", AnvilReq.BRONZE, new ItemStack(TFCItems.bronzeSwordHead, 1))).addRecipeSkill("skill.weaponsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.copperIngot2x), null, "sword", AnvilReq.COPPER, new ItemStack(TFCItems.copperSwordHead, 1))).addRecipeSkill("skill.weaponsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.wroughtIronIngot2x), null, "sword", AnvilReq.WROUGHTIRON, new ItemStack(TFCItems.wroughtIronSwordHead, 1))).addRecipeSkill("skill.weaponsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.redSteelIngot2x), null, "sword", AnvilReq.REDSTEEL, new ItemStack(TFCItems.redSteelSwordHead, 1))).addRecipeSkill("skill.weaponsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.steelIngot2x), null, "sword", AnvilReq.STEEL, new ItemStack(TFCItems.steelSwordHead, 1))).addRecipeSkill("skill.weaponsmith"));


    
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeIngot2x), null, "mace", AnvilReq.BISMUTHBRONZE, new ItemStack(TFCItems.bismuthBronzeMaceHead, 1))).addRecipeSkill("skill.weaponsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackBronzeIngot2x), null, "mace", AnvilReq.BLACKBRONZE, new ItemStack(TFCItems.blackBronzeMaceHead, 1))).addRecipeSkill("skill.weaponsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackSteelIngot2x), null, "mace", AnvilReq.BLACKSTEEL, new ItemStack(TFCItems.blackSteelMaceHead, 1))).addRecipeSkill("skill.weaponsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blueSteelIngot2x), null, "mace", AnvilReq.BLUESTEEL, new ItemStack(TFCItems.blueSteelMaceHead, 1))).addRecipeSkill("skill.weaponsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bronzeIngot2x), null, "mace", AnvilReq.BRONZE, new ItemStack(TFCItems.bronzeMaceHead, 1))).addRecipeSkill("skill.weaponsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.copperIngot2x), null, "mace", AnvilReq.COPPER, new ItemStack(TFCItems.copperMaceHead, 1))).addRecipeSkill("skill.weaponsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.wroughtIronIngot2x), null, "mace", AnvilReq.WROUGHTIRON, new ItemStack(TFCItems.wroughtIronMaceHead, 1))).addRecipeSkill("skill.weaponsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.redSteelIngot2x), null, "mace", AnvilReq.REDSTEEL, new ItemStack(TFCItems.redSteelMaceHead, 1))).addRecipeSkill("skill.weaponsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.steelIngot2x), null, "mace", AnvilReq.STEEL, new ItemStack(TFCItems.steelMaceHead, 1))).addRecipeSkill("skill.weaponsmith"));

    
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeIngot), null, "scythe", AnvilReq.BISMUTHBRONZE, new ItemStack(TFCItems.bismuthBronzeScytheHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackBronzeIngot), null, "scythe", AnvilReq.BLACKBRONZE, new ItemStack(TFCItems.blackBronzeScytheHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackSteelIngot), null, "scythe", false, AnvilReq.BLACKSTEEL, new ItemStack(TFCItems.blackSteelScytheHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blueSteelIngot), null, "scythe", AnvilReq.BLUESTEEL, new ItemStack(TFCItems.blueSteelScytheHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bronzeIngot), null, "scythe", AnvilReq.BRONZE, new ItemStack(TFCItems.bronzeScytheHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.copperIngot), null, "scythe", AnvilReq.COPPER, new ItemStack(TFCItems.copperScytheHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.wroughtIronIngot), null, "scythe", AnvilReq.WROUGHTIRON, new ItemStack(TFCItems.wroughtIronScytheHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.redSteelIngot), null, "scythe", AnvilReq.REDSTEEL, new ItemStack(TFCItems.redSteelScytheHead, 1))).addRecipeSkill("skill.toolsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.steelIngot), null, "scythe", AnvilReq.STEEL, new ItemStack(TFCItems.steelScytheHead, 1))).addRecipeSkill("skill.toolsmith"));

    
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeIngot), null, "knife", AnvilReq.BISMUTHBRONZE, new ItemStack(TFCItems.bismuthBronzeKnifeHead, 1))).addRecipeSkill("skill.weaponsmith").setCraftingBound(40));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackBronzeIngot), null, "knife", AnvilReq.BLACKBRONZE, new ItemStack(TFCItems.blackBronzeKnifeHead, 1))).addRecipeSkill("skill.weaponsmith").setCraftingBound(40));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackSteelIngot), null, "knife", AnvilReq.BLACKSTEEL, new ItemStack(TFCItems.blackSteelKnifeHead, 1))).addRecipeSkill("skill.weaponsmith").setCraftingBound(40));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blueSteelIngot), null, "knife", AnvilReq.BLUESTEEL, new ItemStack(TFCItems.blueSteelKnifeHead, 1))).addRecipeSkill("skill.weaponsmith").setCraftingBound(40));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bronzeIngot), null, "knife", AnvilReq.BRONZE, new ItemStack(TFCItems.bronzeKnifeHead, 1))).addRecipeSkill("skill.weaponsmith").setCraftingBound(40));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.copperIngot), null, "knife", AnvilReq.COPPER, new ItemStack(TFCItems.copperKnifeHead, 1))).addRecipeSkill("skill.weaponsmith").setCraftingBound(40));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.wroughtIronIngot), null, "knife", AnvilReq.WROUGHTIRON, new ItemStack(TFCItems.wroughtIronKnifeHead, 1))).addRecipeSkill("skill.weaponsmith").setCraftingBound(40));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.redSteelIngot), null, "knife", AnvilReq.REDSTEEL, new ItemStack(TFCItems.redSteelKnifeHead, 1))).addRecipeSkill("skill.weaponsmith").setCraftingBound(40));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.steelIngot), null, "knife", AnvilReq.STEEL, new ItemStack(TFCItems.steelKnifeHead, 1))).addRecipeSkill("skill.weaponsmith").setCraftingBound(40));


    
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeIngot), null, "javelin", AnvilReq.BISMUTHBRONZE, new ItemStack(TFCItems.bismuthBronzeJavelinHead, 1))).addRecipeSkill("skill.weaponsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackBronzeIngot), null, "javelin", AnvilReq.BLACKBRONZE, new ItemStack(TFCItems.blackBronzeJavelinHead, 1))).addRecipeSkill("skill.weaponsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bronzeIngot), null, "javelin", AnvilReq.BRONZE, new ItemStack(TFCItems.bronzeJavelinHead, 1))).addRecipeSkill("skill.weaponsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackSteelIngot), null, "javelin", AnvilReq.BLACKSTEEL, new ItemStack(TFCItems.blackSteelJavelinHead, 1))).addRecipeSkill("skill.weaponsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blueSteelIngot), null, "javelin", AnvilReq.BLUESTEEL, new ItemStack(TFCItems.blueSteelJavelinHead, 1))).addRecipeSkill("skill.weaponsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.redSteelIngot), null, "javelin", AnvilReq.REDSTEEL, new ItemStack(TFCItems.redSteelJavelinHead, 1))).addRecipeSkill("skill.weaponsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.steelIngot), null, "javelin", AnvilReq.STEEL, new ItemStack(TFCItems.steelJavelinHead, 1))).addRecipeSkill("skill.weaponsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.wroughtIronIngot), null, "javelin", AnvilReq.WROUGHTIRON, new ItemStack(TFCItems.wroughtIronJavelinHead, 1))).addRecipeSkill("skill.weaponsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.copperIngot), null, "javelin", AnvilReq.COPPER, new ItemStack(TFCItems.copperJavelinHead, 1))).addRecipeSkill("skill.weaponsmith"));
    
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeSheet), null, "helmPlate", AnvilReq.BISMUTHBRONZE, new ItemStack(TFCItems.bismuthBronzeUnfinishedHelmet, 1, 0))).addRecipeSkill("skill.armorsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackBronzeSheet), null, "helmPlate", AnvilReq.BLACKBRONZE, new ItemStack(TFCItems.blackBronzeUnfinishedHelmet, 1, 0))).addRecipeSkill("skill.armorsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackSteelSheet), null, "helmPlate", AnvilReq.BLACKSTEEL, new ItemStack(TFCItems.blackSteelUnfinishedHelmet, 1, 0))).addRecipeSkill("skill.armorsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blueSteelSheet), null, "helmPlate", AnvilReq.BLUESTEEL, new ItemStack(TFCItems.blueSteelUnfinishedHelmet, 1, 0))).addRecipeSkill("skill.armorsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bronzeSheet), null, "helmPlate", AnvilReq.BRONZE, new ItemStack(TFCItems.bronzeUnfinishedHelmet, 1, 0))).addRecipeSkill("skill.armorsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.copperSheet), null, "helmPlate", AnvilReq.COPPER, new ItemStack(TFCItems.copperUnfinishedHelmet, 1, 0))).addRecipeSkill("skill.armorsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.wroughtIronSheet), null, "helmPlate", AnvilReq.WROUGHTIRON, new ItemStack(TFCItems.wroughtIronUnfinishedHelmet, 1, 0))).addRecipeSkill("skill.armorsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.redSteelSheet), null, "helmPlate", AnvilReq.REDSTEEL, new ItemStack(TFCItems.redSteelUnfinishedHelmet, 1, 0))).addRecipeSkill("skill.armorsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.steelSheet), null, "helmPlate", AnvilReq.STEEL, new ItemStack(TFCItems.steelUnfinishedHelmet, 1, 0))).addRecipeSkill("skill.armorsmith"));
    
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeUnfinishedHelmet, 1, 1), null, "helmPlate", AnvilReq.BISMUTHBRONZE, new ItemStack(TFCItems.bismuthBronzeHelmet, 1))).addRecipeSkill("skill.armorsmith").setCraftingXP(2));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackBronzeUnfinishedHelmet, 1, 1), null, "helmPlate", AnvilReq.BLACKBRONZE, new ItemStack(TFCItems.blackBronzeHelmet, 1))).addRecipeSkill("skill.armorsmith").setCraftingXP(2));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackSteelUnfinishedHelmet, 1, 1), null, "helmPlate", AnvilReq.BLACKSTEEL, new ItemStack(TFCItems.blackSteelHelmet, 1))).addRecipeSkill("skill.armorsmith").setCraftingXP(2));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blueSteelUnfinishedHelmet, 1, 1), null, "helmPlate", AnvilReq.BLUESTEEL, new ItemStack(TFCItems.blueSteelHelmet, 1))).addRecipeSkill("skill.armorsmith").setCraftingXP(2));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bronzeUnfinishedHelmet, 1, 1), null, "helmPlate", AnvilReq.BRONZE, new ItemStack(TFCItems.bronzeHelmet, 1))).addRecipeSkill("skill.armorsmith").setCraftingXP(2));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.copperUnfinishedHelmet, 1, 1), null, "helmPlate", AnvilReq.COPPER, new ItemStack(TFCItems.copperHelmet, 1))).addRecipeSkill("skill.armorsmith").setCraftingXP(2));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.wroughtIronUnfinishedHelmet, 1, 1), null, "helmPlate", AnvilReq.WROUGHTIRON, new ItemStack(TFCItems.wroughtIronHelmet, 1))).addRecipeSkill("skill.armorsmith").setCraftingXP(2));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.redSteelUnfinishedHelmet, 1, 1), null, "helmPlate", AnvilReq.REDSTEEL, new ItemStack(TFCItems.redSteelHelmet, 1))).addRecipeSkill("skill.armorsmith").setCraftingXP(2));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.steelUnfinishedHelmet, 1, 1), null, "helmPlate", AnvilReq.STEEL, new ItemStack(TFCItems.steelHelmet, 1))).addRecipeSkill("skill.armorsmith").setCraftingXP(2));
    
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeSheet2x), null, "chestPlate", AnvilReq.BISMUTHBRONZE, new ItemStack(TFCItems.bismuthBronzeUnfinishedChestplate, 1, 0))).addRecipeSkill("skill.armorsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackBronzeSheet2x), null, "chestPlate", AnvilReq.BLACKBRONZE, new ItemStack(TFCItems.blackBronzeUnfinishedChestplate, 1, 0))).addRecipeSkill("skill.armorsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackSteelSheet2x), null, "chestPlate", AnvilReq.BLACKSTEEL, new ItemStack(TFCItems.blackSteelUnfinishedChestplate, 1, 0))).addRecipeSkill("skill.armorsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blueSteelSheet2x), null, "chestPlate", AnvilReq.BLUESTEEL, new ItemStack(TFCItems.blueSteelUnfinishedChestplate, 1, 0))).addRecipeSkill("skill.armorsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bronzeSheet2x), null, "chestPlate", AnvilReq.BRONZE, new ItemStack(TFCItems.bronzeUnfinishedChestplate, 1, 0))).addRecipeSkill("skill.armorsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.copperSheet2x), null, "chestPlate", AnvilReq.COPPER, new ItemStack(TFCItems.copperUnfinishedChestplate, 1, 0))).addRecipeSkill("skill.armorsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.wroughtIronSheet2x), null, "chestPlate", AnvilReq.WROUGHTIRON, new ItemStack(TFCItems.wroughtIronUnfinishedChestplate, 1, 0))).addRecipeSkill("skill.armorsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.redSteelSheet2x), null, "chestPlate", AnvilReq.REDSTEEL, new ItemStack(TFCItems.redSteelUnfinishedChestplate, 1, 0))).addRecipeSkill("skill.armorsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.steelSheet2x), null, "chestPlate", AnvilReq.STEEL, new ItemStack(TFCItems.steelUnfinishedChestplate, 1, 0))).addRecipeSkill("skill.armorsmith"));
    
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeUnfinishedChestplate, 1, 1), null, "chestPlate", AnvilReq.BISMUTHBRONZE, new ItemStack(TFCItems.bismuthBronzeChestplate, 1))).addRecipeSkill("skill.armorsmith").setCraftingXP(3));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackBronzeUnfinishedChestplate, 1, 1), null, "chestPlate", AnvilReq.BLACKBRONZE, new ItemStack(TFCItems.blackBronzeChestplate, 1))).addRecipeSkill("skill.armorsmith").setCraftingXP(3));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackSteelUnfinishedChestplate, 1, 1), null, "chestPlate", AnvilReq.BLACKSTEEL, new ItemStack(TFCItems.blackSteelChestplate, 1))).addRecipeSkill("skill.armorsmith").setCraftingXP(3));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blueSteelUnfinishedChestplate, 1, 1), null, "chestPlate", AnvilReq.BLUESTEEL, new ItemStack(TFCItems.blueSteelChestplate, 1))).addRecipeSkill("skill.armorsmith").setCraftingXP(3));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bronzeUnfinishedChestplate, 1, 1), null, "chestPlate", AnvilReq.BRONZE, new ItemStack(TFCItems.bronzeChestplate, 1))).addRecipeSkill("skill.armorsmith").setCraftingXP(3));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.copperUnfinishedChestplate, 1, 1), null, "chestPlate", AnvilReq.COPPER, new ItemStack(TFCItems.copperChestplate, 1))).addRecipeSkill("skill.armorsmith").setCraftingXP(3));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.wroughtIronUnfinishedChestplate, 1, 1), null, "chestPlate", AnvilReq.WROUGHTIRON, new ItemStack(TFCItems.wroughtIronChestplate, 1))).addRecipeSkill("skill.armorsmith").setCraftingXP(3));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.redSteelUnfinishedChestplate, 1, 1), null, "chestPlate", AnvilReq.REDSTEEL, new ItemStack(TFCItems.redSteelChestplate, 1))).addRecipeSkill("skill.armorsmith").setCraftingXP(3));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.steelUnfinishedChestplate, 1, 1), null, "chestPlate", AnvilReq.STEEL, new ItemStack(TFCItems.steelChestplate, 1))).addRecipeSkill("skill.armorsmith").setCraftingXP(3));
    
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeSheet2x), null, "legsPlate", AnvilReq.BISMUTHBRONZE, new ItemStack(TFCItems.bismuthBronzeUnfinishedGreaves, 1, 0))).addRecipeSkill("skill.armorsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackBronzeSheet2x), null, "legsPlate", AnvilReq.BLACKBRONZE, new ItemStack(TFCItems.blackBronzeUnfinishedGreaves, 1, 0))).addRecipeSkill("skill.armorsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackSteelSheet2x), null, "legsPlate", AnvilReq.BLACKSTEEL, new ItemStack(TFCItems.blackSteelUnfinishedGreaves, 1, 0))).addRecipeSkill("skill.armorsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blueSteelSheet2x), null, "legsPlate", AnvilReq.BLUESTEEL, new ItemStack(TFCItems.blueSteelUnfinishedGreaves, 1, 0))).addRecipeSkill("skill.armorsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bronzeSheet2x), null, "legsPlate", AnvilReq.BRONZE, new ItemStack(TFCItems.bronzeUnfinishedGreaves, 1, 0))).addRecipeSkill("skill.armorsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.copperSheet2x), null, "legsPlate", AnvilReq.COPPER, new ItemStack(TFCItems.copperUnfinishedGreaves, 1, 0))).addRecipeSkill("skill.armorsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.wroughtIronSheet2x), null, "legsPlate", AnvilReq.WROUGHTIRON, new ItemStack(TFCItems.wroughtIronUnfinishedGreaves, 1, 0))).addRecipeSkill("skill.armorsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.redSteelSheet2x), null, "legsPlate", AnvilReq.REDSTEEL, new ItemStack(TFCItems.redSteelUnfinishedGreaves, 1, 0))).addRecipeSkill("skill.armorsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.steelSheet2x), null, "legsPlate", AnvilReq.STEEL, new ItemStack(TFCItems.steelUnfinishedGreaves, 1, 0))).addRecipeSkill("skill.armorsmith"));
    
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeUnfinishedGreaves, 1, 1), null, "legsPlate", AnvilReq.BISMUTHBRONZE, new ItemStack(TFCItems.bismuthBronzeGreaves, 1))).addRecipeSkill("skill.armorsmith").setCraftingXP(3));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackBronzeUnfinishedGreaves, 1, 1), null, "legsPlate", AnvilReq.BLACKBRONZE, new ItemStack(TFCItems.blackBronzeGreaves, 1))).addRecipeSkill("skill.armorsmith").setCraftingXP(3));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackSteelUnfinishedGreaves, 1, 1), null, "legsPlate", AnvilReq.BLACKSTEEL, new ItemStack(TFCItems.blackSteelGreaves, 1))).addRecipeSkill("skill.armorsmith").setCraftingXP(3));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blueSteelUnfinishedGreaves, 1, 1), null, "legsPlate", AnvilReq.BLUESTEEL, new ItemStack(TFCItems.blueSteelGreaves, 1))).addRecipeSkill("skill.armorsmith").setCraftingXP(3));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bronzeUnfinishedGreaves, 1, 1), null, "legsPlate", AnvilReq.BRONZE, new ItemStack(TFCItems.bronzeGreaves, 1))).addRecipeSkill("skill.armorsmith").setCraftingXP(3));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.copperUnfinishedGreaves, 1, 1), null, "legsPlate", AnvilReq.COPPER, new ItemStack(TFCItems.copperGreaves, 1))).addRecipeSkill("skill.armorsmith").setCraftingXP(3));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.wroughtIronUnfinishedGreaves, 1, 1), null, "legsPlate", AnvilReq.WROUGHTIRON, new ItemStack(TFCItems.wroughtIronGreaves, 1))).addRecipeSkill("skill.armorsmith").setCraftingXP(3));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.redSteelUnfinishedGreaves, 1, 1), null, "legsPlate", AnvilReq.REDSTEEL, new ItemStack(TFCItems.redSteelGreaves, 1))).addRecipeSkill("skill.armorsmith").setCraftingXP(3));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.steelUnfinishedGreaves, 1, 1), null, "legsPlate", AnvilReq.STEEL, new ItemStack(TFCItems.steelGreaves, 1))).addRecipeSkill("skill.armorsmith").setCraftingXP(3));
    
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeSheet), null, "bootsplate", AnvilReq.BISMUTHBRONZE, new ItemStack(TFCItems.bismuthBronzeUnfinishedBoots, 1, 0))).addRecipeSkill("skill.armorsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackBronzeSheet), null, "bootsplate", AnvilReq.BLACKBRONZE, new ItemStack(TFCItems.blackBronzeUnfinishedBoots, 1, 0))).addRecipeSkill("skill.armorsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackSteelSheet), null, "bootsplate", AnvilReq.BLACKSTEEL, new ItemStack(TFCItems.blackSteelUnfinishedBoots, 1, 0))).addRecipeSkill("skill.armorsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blueSteelSheet), null, "bootsplate", AnvilReq.BLUESTEEL, new ItemStack(TFCItems.blueSteelUnfinishedBoots, 1, 0))).addRecipeSkill("skill.armorsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bronzeSheet), null, "bootsplate", AnvilReq.BRONZE, new ItemStack(TFCItems.bronzeUnfinishedBoots, 1, 0))).addRecipeSkill("skill.armorsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.copperSheet), null, "bootsplate", AnvilReq.COPPER, new ItemStack(TFCItems.copperUnfinishedBoots, 1, 0))).addRecipeSkill("skill.armorsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.wroughtIronSheet), null, "bootsplate", AnvilReq.WROUGHTIRON, new ItemStack(TFCItems.wroughtIronUnfinishedBoots, 1, 0))).addRecipeSkill("skill.armorsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.redSteelSheet), null, "bootsplate", AnvilReq.REDSTEEL, new ItemStack(TFCItems.redSteelUnfinishedBoots, 1, 0))).addRecipeSkill("skill.armorsmith"));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.steelSheet), null, "bootsplate", AnvilReq.STEEL, new ItemStack(TFCItems.steelUnfinishedBoots, 1, 0))).addRecipeSkill("skill.armorsmith"));
    
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeUnfinishedBoots, 1, 1), null, "bootsplate", AnvilReq.BISMUTHBRONZE, new ItemStack(TFCItems.bismuthBronzeBoots, 1))).addRecipeSkill("skill.armorsmith").setCraftingXP(2));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackBronzeUnfinishedBoots, 1, 1), null, "bootsplate", AnvilReq.BLACKBRONZE, new ItemStack(TFCItems.blackBronzeBoots, 1))).addRecipeSkill("skill.armorsmith").setCraftingXP(2));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blackSteelUnfinishedBoots, 1, 1), null, "bootsplate", AnvilReq.BLACKSTEEL, new ItemStack(TFCItems.blackSteelBoots, 1))).addRecipeSkill("skill.armorsmith").setCraftingXP(2));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.blueSteelUnfinishedBoots, 1, 1), null, "bootsplate", AnvilReq.BLUESTEEL, new ItemStack(TFCItems.blueSteelBoots, 1))).addRecipeSkill("skill.armorsmith").setCraftingXP(2));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bronzeUnfinishedBoots, 1, 1), null, "bootsplate", AnvilReq.BRONZE, new ItemStack(TFCItems.bronzeBoots, 1))).addRecipeSkill("skill.armorsmith").setCraftingXP(2));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.copperUnfinishedBoots, 1, 1), null, "bootsplate", AnvilReq.COPPER, new ItemStack(TFCItems.copperBoots, 1))).addRecipeSkill("skill.armorsmith").setCraftingXP(2));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.wroughtIronUnfinishedBoots, 1, 1), null, "bootsplate", AnvilReq.WROUGHTIRON, new ItemStack(TFCItems.wroughtIronBoots, 1))).addRecipeSkill("skill.armorsmith").setCraftingXP(2));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.redSteelUnfinishedBoots, 1, 1), null, "bootsplate", AnvilReq.REDSTEEL, new ItemStack(TFCItems.redSteelBoots, 1))).addRecipeSkill("skill.armorsmith").setCraftingXP(2));
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.steelUnfinishedBoots, 1, 1), null, "bootsplate", AnvilReq.STEEL, new ItemStack(TFCItems.steelBoots, 1))).addRecipeSkill("skill.armorsmith").setCraftingXP(2));



    
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.redSteelSheet), null, "bucket", AnvilReq.REDSTEEL, new ItemStack(TFCItems.redSteelBucketEmpty, 1)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blueSteelSheet), null, "bucket", AnvilReq.BLUESTEEL, new ItemStack(TFCItems.blueSteelBucketEmpty, 1)));
    
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.rawBloom, 1, 32767), null, "refinebloom", AnvilReq.BRONZE, new ItemStack(TFCItems.bloom, 1))).setInheritsDamage().clearRecipeSkills());
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bloom, 1, 100), null, "refinebloom", AnvilReq.BRONZE, new ItemStack(TFCItems.wroughtIronIngot, 1))).clearRecipeSkills());
    manager.addRecipe((new AnvilRecipe(new ItemStack(TFCItems.bloom, 1, 32767), null, "splitbloom", AnvilReq.BRONZE, new ItemStack(TFCItems.bloom, 1))).clearRecipeSkills());

    
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.copperSheet2x), null, "tuyere", AnvilReq.COPPER, new ItemStack(TFCItems.tuyereCopper, 1)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bronzeSheet2x), null, "tuyere", AnvilReq.BRONZE, new ItemStack(TFCItems.tuyereBronze, 1)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeSheet2x), null, "tuyere", AnvilReq.BRONZE, new ItemStack(TFCItems.tuyereBismuthBronze, 1)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackBronzeSheet2x), null, "tuyere", AnvilReq.BRONZE, new ItemStack(TFCItems.tuyereBlackBronze, 1)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.wroughtIronSheet2x), null, "tuyere", AnvilReq.WROUGHTIRON, new ItemStack(TFCItems.tuyereWroughtIron, 1)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.steelSheet2x), null, "tuyere", AnvilReq.STEEL, new ItemStack(TFCItems.tuyereSteel, 1)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackSteelSheet2x), null, "tuyere", AnvilReq.BLACKSTEEL, new ItemStack(TFCItems.tuyereBlackSteel, 1)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blueSteelSheet2x), null, "tuyere", AnvilReq.BLUESTEEL, new ItemStack(TFCItems.tuyereBlueSteel, 1)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.redSteelSheet2x), null, "tuyere", AnvilReq.REDSTEEL, new ItemStack(TFCItems.tuyereRedSteel, 1)));
    
    addTrapDoor(TFCItems.bismuthSheet, 0); addTrapDoor(TFCItems.bismuthBronzeSheet, 1); addTrapDoor(TFCItems.blackBronzeSheet, 2); addTrapDoor(TFCItems.blackSteelSheet, 3);
    addTrapDoor(TFCItems.blueSteelSheet, 4); addTrapDoor(TFCItems.brassSheet, 5); addTrapDoor(TFCItems.bronzeSheet, 6); addTrapDoor(TFCItems.copperSheet, 7);
    addTrapDoor(TFCItems.goldSheet, 8); addTrapDoor(TFCItems.wroughtIronSheet, 9); addTrapDoor(TFCItems.leadSheet, 10); addTrapDoor(TFCItems.nickelSheet, 11);
    addTrapDoor(TFCItems.nickelSheet, 12); addTrapDoor(TFCItems.platinumSheet, 13); addTrapDoor(TFCItems.redSteelSheet, 14); addTrapDoor(TFCItems.roseGoldSheet, 15);
    addTrapDoor(TFCItems.silverSheet, 16); addTrapDoor(TFCItems.steelSheet, 17); addTrapDoor(TFCItems.sterlingSilverSheet, 18); addTrapDoor(TFCItems.tinSheet, 19);
    addTrapDoor(TFCItems.zincSheet, 20);
    
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.wroughtIronIngot2x), new ItemStack(TFCItems.wroughtIronIngot2x), "grill", AnvilReq.WROUGHTIRON, new ItemStack(TFCBlocks.grill, 1, 0)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.wroughtIronKnifeHead), new ItemStack(TFCItems.wroughtIronKnifeHead), "shears", AnvilReq.WROUGHTIRON, new ItemStack(TFCItems.shears, 1, 0)));
    
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.goldIngot), null, "oillamp", AnvilReq.COPPER, new ItemStack(TFCBlocks.oilLamp, 1, 0)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.platinumIngot), null, "oillamp", AnvilReq.COPPER, new ItemStack(TFCBlocks.oilLamp, 1, 1)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.roseGoldIngot), null, "oillamp", AnvilReq.COPPER, new ItemStack(TFCBlocks.oilLamp, 1, 2)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.silverIngot), null, "oillamp", AnvilReq.COPPER, new ItemStack(TFCBlocks.oilLamp, 1, 3)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.sterlingSilverIngot), null, "oillamp", AnvilReq.COPPER, new ItemStack(TFCBlocks.oilLamp, 1, 4)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blueSteelIngot), null, "oillamp", AnvilReq.BLUESTEEL, new ItemStack(TFCBlocks.oilLamp, 1, 5)));

    
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.wroughtIronSheet2x), new ItemStack(TFCItems.wroughtIronSheet2x), "hopper", AnvilReq.WROUGHTIRON, new ItemStack(TFCMBlocks.blockFruitPress)));

    
    manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.wroughtIronIngot2x), null, "bucket", AnvilReq.WROUGHTIRON, new ItemStack(Items.field_151143_au)));
  }

  
  private static void addTrapDoor(Item sheet, int index) {
    AnvilManager manager = AnvilManager.getInstance();
    manager.addRecipe(new AnvilRecipe(new ItemStack(sheet), new ItemStack(TFCItems.bismuthIngot), "trapdoor", AnvilReq.COPPER, new ItemStack(TFCBlocks.metalTrapDoor, 1, index)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(sheet), new ItemStack(TFCItems.bismuthBronzeIngot), "trapdoor", AnvilReq.COPPER, new ItemStack(TFCBlocks.metalTrapDoor, 1, index + 32)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(sheet), new ItemStack(TFCItems.blackBronzeIngot), "trapdoor", AnvilReq.COPPER, new ItemStack(TFCBlocks.metalTrapDoor, 1, index + 64)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(sheet), new ItemStack(TFCItems.blackSteelIngot), "trapdoor", AnvilReq.COPPER, new ItemStack(TFCBlocks.metalTrapDoor, 1, index + 96)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(sheet), new ItemStack(TFCItems.blueSteelIngot), "trapdoor", AnvilReq.COPPER, new ItemStack(TFCBlocks.metalTrapDoor, 1, index + 128)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(sheet), new ItemStack(TFCItems.brassIngot), "trapdoor", AnvilReq.COPPER, new ItemStack(TFCBlocks.metalTrapDoor, 1, index + 160)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(sheet), new ItemStack(TFCItems.bronzeIngot), "trapdoor", AnvilReq.COPPER, new ItemStack(TFCBlocks.metalTrapDoor, 1, index + 192)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(sheet), new ItemStack(TFCItems.copperIngot), "trapdoor", AnvilReq.COPPER, new ItemStack(TFCBlocks.metalTrapDoor, 1, index + 224)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(sheet), new ItemStack(TFCItems.goldIngot), "trapdoor", AnvilReq.COPPER, new ItemStack(TFCBlocks.metalTrapDoor, 1, index + 256)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(sheet), new ItemStack(TFCItems.wroughtIronIngot), "trapdoor", AnvilReq.COPPER, new ItemStack(TFCBlocks.metalTrapDoor, 1, index + 288)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(sheet), new ItemStack(TFCItems.leadIngot), "trapdoor", AnvilReq.COPPER, new ItemStack(TFCBlocks.metalTrapDoor, 1, index + 320)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(sheet), new ItemStack(TFCItems.nickelIngot), "trapdoor", AnvilReq.COPPER, new ItemStack(TFCBlocks.metalTrapDoor, 1, index + 352)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(sheet), new ItemStack(TFCItems.platinumIngot), "trapdoor", AnvilReq.COPPER, new ItemStack(TFCBlocks.metalTrapDoor, 1, index + 416)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(sheet), new ItemStack(TFCItems.redSteelIngot), "trapdoor", AnvilReq.COPPER, new ItemStack(TFCBlocks.metalTrapDoor, 1, index + 448)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(sheet), new ItemStack(TFCItems.roseGoldIngot), "trapdoor", AnvilReq.COPPER, new ItemStack(TFCBlocks.metalTrapDoor, 1, index + 480)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(sheet), new ItemStack(TFCItems.silverIngot), "trapdoor", AnvilReq.COPPER, new ItemStack(TFCBlocks.metalTrapDoor, 1, index + 512)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(sheet), new ItemStack(TFCItems.steelIngot), "trapdoor", AnvilReq.COPPER, new ItemStack(TFCBlocks.metalTrapDoor, 1, index + 544)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(sheet), new ItemStack(TFCItems.sterlingSilverIngot), "trapdoor", AnvilReq.COPPER, new ItemStack(TFCBlocks.metalTrapDoor, 1, index + 576)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(sheet), new ItemStack(TFCItems.tinIngot), "trapdoor", AnvilReq.COPPER, new ItemStack(TFCBlocks.metalTrapDoor, 1, index + 608)));
    manager.addRecipe(new AnvilRecipe(new ItemStack(sheet), new ItemStack(TFCItems.zincIngot), "trapdoor", AnvilReq.COPPER, new ItemStack(TFCBlocks.metalTrapDoor, 1, index + 640)));
  }







  
  private static void addWeldRecipes(AnvilManager manager) {
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.bismuthIngot), new ItemStack(TFCItems.bismuthIngot), AnvilReq.STONE, new ItemStack(TFCItems.bismuthIngot2x, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeIngot), new ItemStack(TFCItems.bismuthBronzeIngot), AnvilReq.COPPER, new ItemStack(TFCItems.bismuthBronzeIngot2x, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackBronzeIngot), new ItemStack(TFCItems.blackBronzeIngot), AnvilReq.COPPER, new ItemStack(TFCItems.blackBronzeIngot2x, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackSteelIngot), new ItemStack(TFCItems.blackSteelIngot), AnvilReq.STEEL, new ItemStack(TFCItems.blackSteelIngot2x, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.blueSteelIngot), new ItemStack(TFCItems.blueSteelIngot), AnvilReq.BLACKSTEEL, new ItemStack(TFCItems.blueSteelIngot2x, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.brassIngot), new ItemStack(TFCItems.brassIngot), AnvilReq.COPPER, new ItemStack(TFCItems.brassIngot2x, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.bronzeIngot), new ItemStack(TFCItems.bronzeIngot), AnvilReq.COPPER, new ItemStack(TFCItems.bronzeIngot2x, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.copperIngot), new ItemStack(TFCItems.copperIngot), AnvilReq.STONE, new ItemStack(TFCItems.copperIngot2x, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.goldIngot), new ItemStack(TFCItems.goldIngot), AnvilReq.COPPER, new ItemStack(TFCItems.goldIngot2x, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.wroughtIronIngot), new ItemStack(TFCItems.wroughtIronIngot), AnvilReq.BRONZE, new ItemStack(TFCItems.wroughtIronIngot2x, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.leadIngot), new ItemStack(TFCItems.leadIngot), AnvilReq.COPPER, new ItemStack(TFCItems.leadIngot2x, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.nickelIngot), new ItemStack(TFCItems.nickelIngot), AnvilReq.WROUGHTIRON, new ItemStack(TFCItems.nickelIngot2x, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.pigIronIngot), new ItemStack(TFCItems.pigIronIngot), AnvilReq.BRONZE, new ItemStack(TFCItems.pigIronIngot2x, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.platinumIngot), new ItemStack(TFCItems.platinumIngot), AnvilReq.BRONZE, new ItemStack(TFCItems.platinumIngot2x, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.redSteelIngot), new ItemStack(TFCItems.redSteelIngot), AnvilReq.BLACKSTEEL, new ItemStack(TFCItems.redSteelIngot2x, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.roseGoldIngot), new ItemStack(TFCItems.roseGoldIngot), AnvilReq.COPPER, new ItemStack(TFCItems.roseGoldIngot2x, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.silverIngot), new ItemStack(TFCItems.silverIngot), AnvilReq.COPPER, new ItemStack(TFCItems.silverIngot2x, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.steelIngot), new ItemStack(TFCItems.steelIngot), AnvilReq.WROUGHTIRON, new ItemStack(TFCItems.steelIngot2x, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.sterlingSilverIngot), new ItemStack(TFCItems.sterlingSilverIngot), AnvilReq.BRONZE, new ItemStack(TFCItems.sterlingSilverIngot2x, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.tinIngot), new ItemStack(TFCItems.tinIngot), AnvilReq.STONE, new ItemStack(TFCItems.tinIngot2x, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.zincIngot), new ItemStack(TFCItems.zincIngot), AnvilReq.STONE, new ItemStack(TFCItems.zincIngot2x, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.weakSteelIngot), new ItemStack(TFCItems.pigIronIngot), AnvilReq.WROUGHTIRON, new ItemStack(TFCItems.highCarbonBlackSteelIngot, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.weakBlueSteelIngot), new ItemStack(TFCItems.blackSteelIngot), AnvilReq.BLACKSTEEL, new ItemStack(TFCItems.highCarbonBlueSteelIngot, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.weakRedSteelIngot), new ItemStack(TFCItems.blackSteelIngot), AnvilReq.BLACKSTEEL, new ItemStack(TFCItems.highCarbonRedSteelIngot, 1)));
    
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.bismuthSheet), new ItemStack(TFCItems.bismuthSheet), AnvilReq.STONE, new ItemStack(TFCItems.bismuthSheet2x, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeSheet), new ItemStack(TFCItems.bismuthBronzeSheet), AnvilReq.COPPER, new ItemStack(TFCItems.bismuthBronzeSheet2x, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackBronzeSheet), new ItemStack(TFCItems.blackBronzeSheet), AnvilReq.COPPER, new ItemStack(TFCItems.blackBronzeSheet2x, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackSteelSheet), new ItemStack(TFCItems.blackSteelSheet), AnvilReq.STEEL, new ItemStack(TFCItems.blackSteelSheet2x, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.blueSteelSheet), new ItemStack(TFCItems.blueSteelSheet), AnvilReq.BLACKSTEEL, new ItemStack(TFCItems.blueSteelSheet2x, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.brassSheet), new ItemStack(TFCItems.brassSheet), AnvilReq.COPPER, new ItemStack(TFCItems.brassSheet2x, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.bronzeSheet), new ItemStack(TFCItems.bronzeSheet), AnvilReq.COPPER, new ItemStack(TFCItems.bronzeSheet2x, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.copperSheet), new ItemStack(TFCItems.copperSheet), AnvilReq.STONE, new ItemStack(TFCItems.copperSheet2x, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.goldSheet), new ItemStack(TFCItems.goldSheet), AnvilReq.COPPER, new ItemStack(TFCItems.goldSheet2x, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.wroughtIronSheet), new ItemStack(TFCItems.wroughtIronSheet), AnvilReq.BRONZE, new ItemStack(TFCItems.wroughtIronSheet2x, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.leadSheet), new ItemStack(TFCItems.leadSheet), AnvilReq.COPPER, new ItemStack(TFCItems.leadSheet2x, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.nickelSheet), new ItemStack(TFCItems.nickelSheet), AnvilReq.WROUGHTIRON, new ItemStack(TFCItems.nickelSheet2x, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.pigIronSheet), new ItemStack(TFCItems.pigIronSheet), AnvilReq.BRONZE, new ItemStack(TFCItems.pigIronSheet2x, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.platinumSheet), new ItemStack(TFCItems.platinumSheet), AnvilReq.BRONZE, new ItemStack(TFCItems.platinumSheet2x, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.redSteelSheet), new ItemStack(TFCItems.redSteelSheet), AnvilReq.BLACKSTEEL, new ItemStack(TFCItems.redSteelSheet2x, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.roseGoldSheet), new ItemStack(TFCItems.roseGoldSheet), AnvilReq.COPPER, new ItemStack(TFCItems.roseGoldSheet2x, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.silverSheet), new ItemStack(TFCItems.silverSheet), AnvilReq.COPPER, new ItemStack(TFCItems.silverSheet2x, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.steelSheet), new ItemStack(TFCItems.steelSheet), AnvilReq.WROUGHTIRON, new ItemStack(TFCItems.steelSheet2x, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.sterlingSilverSheet), new ItemStack(TFCItems.sterlingSilverSheet), AnvilReq.BRONZE, new ItemStack(TFCItems.sterlingSilverSheet2x, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.tinSheet), new ItemStack(TFCItems.tinSheet), AnvilReq.STONE, new ItemStack(TFCItems.tinSheet2x, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.zincSheet), new ItemStack(TFCItems.zincSheet), AnvilReq.STONE, new ItemStack(TFCItems.zincSheet2x, 1)));

    
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeUnfinishedChestplate, 1, 0), new ItemStack(TFCItems.bismuthBronzeSheet2x), true, AnvilReq.COPPER, new ItemStack(TFCItems.bismuthBronzeUnfinishedChestplate, 1, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackBronzeUnfinishedChestplate, 1, 0), new ItemStack(TFCItems.blackBronzeSheet2x), true, AnvilReq.COPPER, new ItemStack(TFCItems.blackBronzeUnfinishedChestplate, 1, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackSteelUnfinishedChestplate, 1, 0), new ItemStack(TFCItems.blackSteelSheet2x), true, AnvilReq.STEEL, new ItemStack(TFCItems.blackSteelUnfinishedChestplate, 1, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.blueSteelUnfinishedChestplate, 1, 0), new ItemStack(TFCItems.blueSteelSheet2x), true, AnvilReq.BLACKSTEEL, new ItemStack(TFCItems.blueSteelUnfinishedChestplate, 1, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.bronzeUnfinishedChestplate, 1, 0), new ItemStack(TFCItems.bronzeSheet2x), true, AnvilReq.COPPER, new ItemStack(TFCItems.bronzeUnfinishedChestplate, 1, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.copperUnfinishedChestplate, 1, 0), new ItemStack(TFCItems.copperSheet2x), true, AnvilReq.STONE, new ItemStack(TFCItems.copperUnfinishedChestplate, 1, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.wroughtIronUnfinishedChestplate, 1, 0), new ItemStack(TFCItems.wroughtIronSheet2x), true, AnvilReq.BRONZE, new ItemStack(TFCItems.wroughtIronUnfinishedChestplate, 1, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.redSteelUnfinishedChestplate, 1, 0), new ItemStack(TFCItems.redSteelSheet2x), true, AnvilReq.BLACKSTEEL, new ItemStack(TFCItems.redSteelUnfinishedChestplate, 1, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.steelUnfinishedChestplate, 1, 0), new ItemStack(TFCItems.steelSheet2x), true, AnvilReq.WROUGHTIRON, new ItemStack(TFCItems.steelUnfinishedChestplate, 1, 1)));

    
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeUnfinishedGreaves, 1, 0), new ItemStack(TFCItems.bismuthBronzeSheet), true, AnvilReq.COPPER, new ItemStack(TFCItems.bismuthBronzeUnfinishedGreaves, 1, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackBronzeUnfinishedGreaves, 1, 0), new ItemStack(TFCItems.blackBronzeSheet), true, AnvilReq.COPPER, new ItemStack(TFCItems.blackBronzeUnfinishedGreaves, 1, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackSteelUnfinishedGreaves, 1, 0), new ItemStack(TFCItems.blackSteelSheet), true, AnvilReq.STEEL, new ItemStack(TFCItems.blackSteelUnfinishedGreaves, 1, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.blueSteelUnfinishedGreaves, 1, 0), new ItemStack(TFCItems.blueSteelSheet), true, AnvilReq.BLACKSTEEL, new ItemStack(TFCItems.blueSteelUnfinishedGreaves, 1, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.bronzeUnfinishedGreaves, 1, 0), new ItemStack(TFCItems.bronzeSheet), true, AnvilReq.COPPER, new ItemStack(TFCItems.bronzeUnfinishedGreaves, 1, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.copperUnfinishedGreaves, 1, 0), new ItemStack(TFCItems.copperSheet), true, AnvilReq.STONE, new ItemStack(TFCItems.copperUnfinishedGreaves, 1, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.wroughtIronUnfinishedGreaves, 1, 0), new ItemStack(TFCItems.wroughtIronSheet), true, AnvilReq.BRONZE, new ItemStack(TFCItems.wroughtIronUnfinishedGreaves, 1, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.redSteelUnfinishedGreaves, 1, 0), new ItemStack(TFCItems.redSteelSheet), true, AnvilReq.BLACKSTEEL, new ItemStack(TFCItems.redSteelUnfinishedGreaves, 1, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.steelUnfinishedGreaves, 1, 0), new ItemStack(TFCItems.steelSheet), true, AnvilReq.WROUGHTIRON, new ItemStack(TFCItems.steelUnfinishedGreaves, 1, 1)));

    
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeUnfinishedHelmet, 1, 0), new ItemStack(TFCItems.bismuthBronzeSheet), true, AnvilReq.COPPER, new ItemStack(TFCItems.bismuthBronzeUnfinishedHelmet, 1, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackBronzeUnfinishedHelmet, 1, 0), new ItemStack(TFCItems.blackBronzeSheet), true, AnvilReq.COPPER, new ItemStack(TFCItems.blackBronzeUnfinishedHelmet, 1, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackSteelUnfinishedHelmet, 1, 0), new ItemStack(TFCItems.blackSteelSheet), true, AnvilReq.STEEL, new ItemStack(TFCItems.blackSteelUnfinishedHelmet, 1, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.blueSteelUnfinishedHelmet, 1, 0), new ItemStack(TFCItems.blueSteelSheet), true, AnvilReq.BLACKSTEEL, new ItemStack(TFCItems.blueSteelUnfinishedHelmet, 1, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.bronzeUnfinishedHelmet, 1, 0), new ItemStack(TFCItems.bronzeSheet), true, AnvilReq.COPPER, new ItemStack(TFCItems.bronzeUnfinishedHelmet, 1, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.copperUnfinishedHelmet, 1, 0), new ItemStack(TFCItems.copperSheet), true, AnvilReq.STONE, new ItemStack(TFCItems.copperUnfinishedHelmet, 1, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.wroughtIronUnfinishedHelmet, 1, 0), new ItemStack(TFCItems.wroughtIronSheet), true, AnvilReq.BRONZE, new ItemStack(TFCItems.wroughtIronUnfinishedHelmet, 1, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.redSteelUnfinishedHelmet, 1, 0), new ItemStack(TFCItems.redSteelSheet), true, AnvilReq.BLACKSTEEL, new ItemStack(TFCItems.redSteelUnfinishedHelmet, 1, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.steelUnfinishedHelmet, 1, 0), new ItemStack(TFCItems.steelSheet), true, AnvilReq.WROUGHTIRON, new ItemStack(TFCItems.steelUnfinishedHelmet, 1, 1)));

    
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeUnfinishedBoots, 1, 0), new ItemStack(TFCItems.bismuthBronzeSheet), true, AnvilReq.COPPER, new ItemStack(TFCItems.bismuthBronzeUnfinishedBoots, 1, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackBronzeUnfinishedBoots, 1, 0), new ItemStack(TFCItems.blackBronzeSheet), true, AnvilReq.COPPER, new ItemStack(TFCItems.blackBronzeUnfinishedBoots, 1, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackSteelUnfinishedBoots, 1, 0), new ItemStack(TFCItems.blackSteelSheet), true, AnvilReq.STEEL, new ItemStack(TFCItems.blackSteelUnfinishedBoots, 1, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.blueSteelUnfinishedBoots, 1, 0), new ItemStack(TFCItems.blueSteelSheet), true, AnvilReq.BLACKSTEEL, new ItemStack(TFCItems.blueSteelUnfinishedBoots, 1, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.bronzeUnfinishedBoots, 1, 0), new ItemStack(TFCItems.bronzeSheet), true, AnvilReq.COPPER, new ItemStack(TFCItems.bronzeUnfinishedBoots, 1, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.copperUnfinishedBoots, 1, 0), new ItemStack(TFCItems.copperSheet), true, AnvilReq.STONE, new ItemStack(TFCItems.copperUnfinishedBoots, 1, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.wroughtIronUnfinishedBoots, 1, 0), new ItemStack(TFCItems.wroughtIronSheet), true, AnvilReq.BRONZE, new ItemStack(TFCItems.wroughtIronUnfinishedBoots, 1, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.redSteelUnfinishedBoots, 1, 0), new ItemStack(TFCItems.redSteelSheet), true, AnvilReq.BLACKSTEEL, new ItemStack(TFCItems.redSteelUnfinishedBoots, 1, 1)));
    manager.addWeldRecipe(new AnvilRecipe(new ItemStack(TFCItems.steelUnfinishedBoots, 1, 0), new ItemStack(TFCItems.steelSheet), true, AnvilReq.WROUGHTIRON, new ItemStack(TFCItems.steelUnfinishedBoots, 1, 1)));
  }

  
  public static void registerFoodRecipes() {
    addFoodRefineRecipe(TFCItems.wheatWhole, TFCItems.wheatGrain);
    addFoodRefineRecipe(TFCItems.ryeWhole, TFCItems.ryeGrain);
    addFoodRefineRecipe(TFCItems.barleyWhole, TFCItems.barleyGrain);
    addFoodRefineRecipe(TFCItems.oatWhole, TFCItems.oatGrain);
    addFoodRefineRecipe(TFCItems.riceWhole, TFCItems.riceGrain);
    
    addFoodDoughRecipe(TFCItems.wheatGround, TFCItems.wheatDough, TFCItems.woodenBucketWater);
    addFoodDoughRecipe(TFCItems.barleyGround, TFCItems.barleyDough, TFCItems.woodenBucketWater);
    addFoodDoughRecipe(TFCItems.ryeGround, TFCItems.ryeDough, TFCItems.woodenBucketWater);
    addFoodDoughRecipe(TFCItems.oatGround, TFCItems.oatDough, TFCItems.woodenBucketWater);
    addFoodDoughRecipe(TFCItems.riceGround, TFCItems.riceDough, TFCItems.woodenBucketWater);
    addFoodDoughRecipe(TFCItems.cornmealGround, TFCItems.cornmealDough, TFCItems.woodenBucketWater);
    addFoodDoughRecipe(TFCItems.wheatGround, TFCItems.wheatDough, TFCItems.redSteelBucketWater);
    addFoodDoughRecipe(TFCItems.barleyGround, TFCItems.barleyDough, TFCItems.redSteelBucketWater);
    addFoodDoughRecipe(TFCItems.ryeGround, TFCItems.ryeDough, TFCItems.redSteelBucketWater);
    addFoodDoughRecipe(TFCItems.oatGround, TFCItems.oatDough, TFCItems.redSteelBucketWater);
    addFoodDoughRecipe(TFCItems.riceGround, TFCItems.riceDough, TFCItems.redSteelBucketWater);
    addFoodDoughRecipe(TFCItems.cornmealGround, TFCItems.cornmealDough, TFCItems.redSteelBucketWater);
    
    addFoodSaltRecipe(TFCItems.venisonRaw);
    addFoodSaltRecipe(TFCItems.beefRaw);
    addFoodSaltRecipe(TFCItems.chickenRaw);
    addFoodSaltRecipe(TFCItems.porkchopRaw);
    addFoodSaltRecipe(TFCItems.fishRaw);
    addFoodSaltRecipe(TFCItems.calamariRaw);
    addFoodSaltRecipe(TFCItems.muttonRaw);
    addFoodSaltRecipe(TFCItems.horseMeatRaw);
    for (Item i : TFCItems.foodList) {
      
      if (!(i instanceof com.bioxx.tfc.Food.ItemEgg)) {
        addFoodMergeRecipe(i);
        GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(ItemFoodTFC.createTag(new ItemStack(i, 1)), new Object[] { ItemFoodTFC.createTag(new ItemStack(i, 1)), "itemKnife" }));
        GameRegistry.addShapelessRecipe(ItemFoodTFC.createTag(new ItemStack(i, 1)), new Object[] { ItemFoodTFC.createTag(new ItemStack(i, 1)) });
      } 
    } 
  }

  
  public static void addFoodSaltRecipe(Item food) {
    GameRegistry.addShapelessRecipe(ItemFoodTFC.createTag(new ItemStack(food, 1)), new Object[] { ItemFoodTFC.createTag(new ItemStack(food, 1)), new ItemStack(TFCItems.powder, 1, 9) });
  }

  
  public static void addFoodRefineRecipe(Item foodInput, Item foodOutput) {
    GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(ItemFoodTFC.createTag(new ItemStack(foodOutput, 1)), new Object[] { ItemFoodTFC.createTag(new ItemStack(foodInput, 1)), "itemKnife" }));
  }

  
  public static void addFoodDoughRecipe(Item foodInput, Item foodOutput, Item bucket) {
    GameRegistry.addShapelessRecipe(ItemFoodTFC.createTag(new ItemStack(foodOutput, 1)), new Object[] { ItemFoodTFC.createTag(new ItemStack(foodInput, 1)), bucket });
  }

  
  public static void addFoodMergeRecipe(Item food) {
    GameRegistry.addShapelessRecipe(ItemFoodTFC.createTag(new ItemStack(food, 1)), new Object[] { ItemFoodTFC.createTag(new ItemStack(food, 1)), ItemFoodTFC.createTag(new ItemStack(food, 1)) });
    GameRegistry.addShapelessRecipe(ItemFoodTFC.createTag(new ItemStack(food, 1)), new Object[] { ItemFoodTFC.createTag(new ItemStack(food, 1)), ItemFoodTFC.createTag(new ItemStack(food, 1)), ItemFoodTFC.createTag(new ItemStack(food, 1)) });
    GameRegistry.addShapelessRecipe(ItemFoodTFC.createTag(new ItemStack(food, 1)), new Object[] { ItemFoodTFC.createTag(new ItemStack(food, 1)), ItemFoodTFC.createTag(new ItemStack(food, 1)), ItemFoodTFC.createTag(new ItemStack(food, 1)), ItemFoodTFC.createTag(new ItemStack(food, 1)) });
    GameRegistry.addShapelessRecipe(ItemFoodTFC.createTag(new ItemStack(food, 1)), new Object[] { ItemFoodTFC.createTag(new ItemStack(food, 1)), ItemFoodTFC.createTag(new ItemStack(food, 1)), ItemFoodTFC.createTag(new ItemStack(food, 1)), ItemFoodTFC.createTag(new ItemStack(food, 1)), ItemFoodTFC.createTag(new ItemStack(food, 1)) });
    GameRegistry.addShapelessRecipe(ItemFoodTFC.createTag(new ItemStack(food, 1)), new Object[] { ItemFoodTFC.createTag(new ItemStack(food, 1)), ItemFoodTFC.createTag(new ItemStack(food, 1)), ItemFoodTFC.createTag(new ItemStack(food, 1)), ItemFoodTFC.createTag(new ItemStack(food, 1)), ItemFoodTFC.createTag(new ItemStack(food, 1)), ItemFoodTFC.createTag(new ItemStack(food, 1)) });
    GameRegistry.addShapelessRecipe(ItemFoodTFC.createTag(new ItemStack(food, 1)), new Object[] { ItemFoodTFC.createTag(new ItemStack(food, 1)), ItemFoodTFC.createTag(new ItemStack(food, 1)), ItemFoodTFC.createTag(new ItemStack(food, 1)), ItemFoodTFC.createTag(new ItemStack(food, 1)), ItemFoodTFC.createTag(new ItemStack(food, 1)), ItemFoodTFC.createTag(new ItemStack(food, 1)), ItemFoodTFC.createTag(new ItemStack(food, 1)) });
    GameRegistry.addShapelessRecipe(ItemFoodTFC.createTag(new ItemStack(food, 1)), new Object[] { ItemFoodTFC.createTag(new ItemStack(food, 1)), ItemFoodTFC.createTag(new ItemStack(food, 1)), ItemFoodTFC.createTag(new ItemStack(food, 1)), ItemFoodTFC.createTag(new ItemStack(food, 1)), ItemFoodTFC.createTag(new ItemStack(food, 1)), ItemFoodTFC.createTag(new ItemStack(food, 1)), ItemFoodTFC.createTag(new ItemStack(food, 1)), ItemFoodTFC.createTag(new ItemStack(food, 1)) });
    GameRegistry.addShapelessRecipe(ItemFoodTFC.createTag(new ItemStack(food, 1)), new Object[] { ItemFoodTFC.createTag(new ItemStack(food, 1)), ItemFoodTFC.createTag(new ItemStack(food, 1)), ItemFoodTFC.createTag(new ItemStack(food, 1)), ItemFoodTFC.createTag(new ItemStack(food, 1)), ItemFoodTFC.createTag(new ItemStack(food, 1)), ItemFoodTFC.createTag(new ItemStack(food, 1)), ItemFoodTFC.createTag(new ItemStack(food, 1)), ItemFoodTFC.createTag(new ItemStack(food, 1)), ItemFoodTFC.createTag(new ItemStack(food, 1)) });
  }

  
  public static void registerKilnRecipes() {
    KilnCraftingManager manager = KilnCraftingManager.getInstance();
    
    manager.addRecipe(new KilnRecipe(new ItemStack(TFCItems.ceramicMold, 1, 0), 0, new ItemStack(TFCItems.ceramicMold, 1, 1)));




    
    manager.addRecipe(new KilnRecipe(new ItemStack(TFCItems.spindleHead, 1, 0), 0, new ItemStack(TFCItems.spindleHead, 1, 1)));




    
    manager.addRecipe(new KilnRecipe(new ItemStack(TFCItems.potteryJug, 1, 0), 0, new ItemStack(TFCItems.potteryJug, 1, 1)));




    
    manager.addRecipe(new KilnRecipe(new ItemStack(TFCItems.potterySmallVessel, 1, 0), 0, new ItemStack(TFCItems.potterySmallVessel, 1, 1)));










    
    manager.addRecipe(new KilnRecipe(new ItemStack(TFCBlocks.vessel, 1, 0), 0, new ItemStack(TFCBlocks.vessel, 1, 1)));




    
    manager.addRecipe(new KilnRecipe(new ItemStack(TFCItems.clayMoldAxe, 1, 0), 0, new ItemStack(TFCItems.clayMoldAxe, 1, 1)));




    
    manager.addRecipe(new KilnRecipe(new ItemStack(TFCItems.clayMoldAxe, 1, 0), 0, new ItemStack(TFCItems.clayMoldAxe, 1, 1)));




    
    manager.addRecipe(new KilnRecipe(new ItemStack(TFCItems.clayMoldChisel, 1, 0), 0, new ItemStack(TFCItems.clayMoldChisel, 1, 1)));




    
    manager.addRecipe(new KilnRecipe(new ItemStack(TFCItems.clayMoldHammer, 1, 0), 0, new ItemStack(TFCItems.clayMoldHammer, 1, 1)));




    
    manager.addRecipe(new KilnRecipe(new ItemStack(TFCItems.clayMoldHoe, 1, 0), 0, new ItemStack(TFCItems.clayMoldHoe, 1, 1)));




    
    manager.addRecipe(new KilnRecipe(new ItemStack(TFCItems.clayMoldKnife, 1, 0), 0, new ItemStack(TFCItems.clayMoldKnife, 1, 1)));




    
    manager.addRecipe(new KilnRecipe(new ItemStack(TFCItems.clayMoldMace, 1, 0), 0, new ItemStack(TFCItems.clayMoldMace, 1, 1)));




    
    manager.addRecipe(new KilnRecipe(new ItemStack(TFCItems.clayMoldPick, 1, 0), 0, new ItemStack(TFCItems.clayMoldPick, 1, 1)));




    
    manager.addRecipe(new KilnRecipe(new ItemStack(TFCItems.clayMoldProPick, 1, 0), 0, new ItemStack(TFCItems.clayMoldProPick, 1, 1)));




    
    manager.addRecipe(new KilnRecipe(new ItemStack(TFCItems.clayMoldSaw, 1, 0), 0, new ItemStack(TFCItems.clayMoldSaw, 1, 1)));




    
    manager.addRecipe(new KilnRecipe(new ItemStack(TFCItems.clayMoldScythe, 1, 0), 0, new ItemStack(TFCItems.clayMoldScythe, 1, 1)));




    
    manager.addRecipe(new KilnRecipe(new ItemStack(TFCItems.clayMoldShovel, 1, 0), 0, new ItemStack(TFCItems.clayMoldShovel, 1, 1)));




    
    manager.addRecipe(new KilnRecipe(new ItemStack(TFCItems.clayMoldSword, 1, 0), 0, new ItemStack(TFCItems.clayMoldSword, 1, 1)));




    
    manager.addRecipe(new KilnRecipe(new ItemStack(TFCItems.clayMoldJavelin, 1, 0), 0, new ItemStack(TFCItems.clayMoldJavelin, 1, 1)));




    
    manager.addRecipe(new KilnRecipe(new ItemStack(TFCItems.fireBrick, 1, 0), 0, new ItemStack(TFCItems.fireBrick, 1, 1)));




    
    manager.addRecipe(new KilnRecipe(new ItemStack(TFCItems.potteryBowl, 1, 0), 0, new ItemStack(TFCItems.potteryBowl, 1, 1)));
  }





  
  private static void registerQuernRecipes() {
    QuernManager manager = QuernManager.getInstance();
    
    manager.addRecipe(new QuernRecipe(new ItemStack(TFCItems.wheatGrain, 1), new ItemStack(TFCItems.wheatGround, 1)));
    manager.addRecipe(new QuernRecipe(new ItemStack(TFCItems.ryeGrain, 1), new ItemStack(TFCItems.ryeGround, 1)));
    manager.addRecipe(new QuernRecipe(new ItemStack(TFCItems.oatGrain, 1), new ItemStack(TFCItems.oatGround, 1)));
    manager.addRecipe(new QuernRecipe(new ItemStack(TFCItems.barleyGrain, 1), new ItemStack(TFCItems.barleyGround, 1)));
    manager.addRecipe(new QuernRecipe(new ItemStack(TFCItems.riceGrain, 1), new ItemStack(TFCItems.riceGround, 1)));
    manager.addRecipe(new QuernRecipe(new ItemStack(TFCItems.maizeEar, 1), new ItemStack(TFCItems.cornmealGround, 1)));
    manager.addRecipe(new QuernRecipe(new ItemStack(TFCItems.oreChunk, 1, 16), new ItemStack(TFCItems.powder, 4, 1)));
    manager.addRecipe(new QuernRecipe(new ItemStack(TFCItems.oreChunk, 1, 20), new ItemStack(TFCItems.powder, 4, 2)));
    manager.addRecipe(new QuernRecipe(new ItemStack(TFCItems.oreChunk, 1, 27), new ItemStack(Items.field_151137_ax, 8)));
    manager.addRecipe(new QuernRecipe(new ItemStack(TFCItems.oreChunk, 1, 28), new ItemStack(Items.field_151137_ax, 8)));
    manager.addRecipe(new QuernRecipe(new ItemStack(Items.field_151103_aS, 1), new ItemStack(TFCItems.dye, 2, 15)));
    manager.addRecipe(new QuernRecipe(new ItemStack(TFCItems.oreChunk, 1, 34), new ItemStack(TFCItems.powder, 4, 6)));
    manager.addRecipe(new QuernRecipe(new ItemStack(TFCItems.smallOreChunk, 1, 9), new ItemStack(TFCItems.powder, 1, 8)));
    manager.addRecipe(new QuernRecipe(new ItemStack(TFCItems.oreChunk, 1, 58), new ItemStack(TFCItems.powder, 2, 8)));
    manager.addRecipe(new QuernRecipe(new ItemStack(TFCItems.oreChunk, 1, 9), new ItemStack(TFCItems.powder, 4, 8)));
    manager.addRecipe(new QuernRecipe(new ItemStack(TFCItems.oreChunk, 1, 44), new ItemStack(TFCItems.powder, 6, 8)));
    manager.addRecipe(new QuernRecipe(new ItemStack(TFCItems.smallOreChunk, 1, 3), new ItemStack(TFCItems.powder, 1, 5)));
    manager.addRecipe(new QuernRecipe(new ItemStack(TFCItems.oreChunk, 1, 52), new ItemStack(TFCItems.powder, 2, 5)));
    manager.addRecipe(new QuernRecipe(new ItemStack(TFCItems.oreChunk, 1, 3), new ItemStack(TFCItems.powder, 4, 5)));
    manager.addRecipe(new QuernRecipe(new ItemStack(TFCItems.oreChunk, 1, 38), new ItemStack(TFCItems.powder, 6, 5)));
    manager.addRecipe(new QuernRecipe(new ItemStack(TFCItems.smallOreChunk, 1, 11), new ItemStack(TFCItems.powder, 1, 7)));
    manager.addRecipe(new QuernRecipe(new ItemStack(TFCItems.oreChunk, 1, 60), new ItemStack(TFCItems.powder, 2, 7)));
    manager.addRecipe(new QuernRecipe(new ItemStack(TFCItems.oreChunk, 1, 11), new ItemStack(TFCItems.powder, 4, 7)));
    manager.addRecipe(new QuernRecipe(new ItemStack(TFCItems.oreChunk, 1, 46), new ItemStack(TFCItems.powder, 6, 7)));
    manager.addRecipe(new QuernRecipe(new ItemStack(TFCItems.oreChunk, 1, 31), new ItemStack(TFCItems.fertilizer, 4)));
    manager.addRecipe(new QuernRecipe(new ItemStack(TFCItems.looseRock, 1, 5), new ItemStack(TFCItems.powder, 4, 9)));
  }

  
  public static int valueOfString(String s) {
    if (s.length() > 0) {
      
      byte[] b = s.getBytes();
      int out = 0;
      for (int i = 0; i < b.length; i++)
        out += b[i]; 
      return out;
    } 
    return 0;
  }


  
  public static void removeRecipe(ItemStack resultItem) {
    List<IRecipe> recipes = CraftingManager.func_77594_a().func_77592_b();
    for (int i = 0; i < recipes.size(); i++) {
      
      if (recipes.get(i) != null) {
        
        ItemStack recipeResult = ((IRecipe)recipes.get(i)).func_77571_b();
        
        if (ItemStack.func_77989_b(resultItem, recipeResult)) {
          recipes.remove(i--);
        }
      } 
    } 
  }

  
  public static void removeRecipe(Class clazz) {
    List<IRecipe> recipes = CraftingManager.func_77594_a().func_77592_b();
    for (int i = 0; i < recipes.size(); i++) {
      
      IRecipe tmpRecipe = recipes.get(i);
      if (tmpRecipe != null) {
        
        ItemStack recipeResult = tmpRecipe.func_77571_b();
        
        if (recipeResult != null && clazz.isInstance(recipeResult.func_77973_b()))
          recipes.remove(i--); 
      } 
    } 
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\Recipes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */