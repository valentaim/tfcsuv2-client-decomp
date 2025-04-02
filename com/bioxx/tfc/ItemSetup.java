package com.bioxx.tfc;

import com.bioxx.tfc.Core.Metal.Alloy;
import com.bioxx.tfc.Core.Metal.AlloyManager;
import com.bioxx.tfc.Core.Metal.MetalRegistry;
import com.bioxx.tfc.Core.Player.FoodStatsTFC;
import com.bioxx.tfc.Core.Recipes;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Food.ItemEgg;
import com.bioxx.tfc.Food.ItemFoodMeat;
import com.bioxx.tfc.Food.ItemFoodTFC;
import com.bioxx.tfc.Food.ItemSalad;
import com.bioxx.tfc.Food.ItemSandwich;
import com.bioxx.tfc.Handlers.TFCFuelHandler;
import com.bioxx.tfc.Items.ItemAlcohol;
import com.bioxx.tfc.Items.ItemArrow;
import com.bioxx.tfc.Items.ItemBlocks.ItemWoodDoor;
import com.bioxx.tfc.Items.ItemBloom;
import com.bioxx.tfc.Items.ItemBlueprint;
import com.bioxx.tfc.Items.ItemClay;
import com.bioxx.tfc.Items.ItemCoal;
import com.bioxx.tfc.Items.ItemCustomLeash;
import com.bioxx.tfc.Items.ItemCustomMinecart;
import com.bioxx.tfc.Items.ItemCustomNameTag;
import com.bioxx.tfc.Items.ItemCustomPotion;
import com.bioxx.tfc.Items.ItemCustomSeeds;
import com.bioxx.tfc.Items.ItemDyeCustom;
import com.bioxx.tfc.Items.ItemFertilizer;
import com.bioxx.tfc.Items.ItemFlatGeneric;
import com.bioxx.tfc.Items.ItemFruitTreeSapling;
import com.bioxx.tfc.Items.ItemGem;
import com.bioxx.tfc.Items.ItemGlassBottle;
import com.bioxx.tfc.Items.ItemIngot;
import com.bioxx.tfc.Items.ItemLeather;
import com.bioxx.tfc.Items.ItemLogs;
import com.bioxx.tfc.Items.ItemLooseRock;
import com.bioxx.tfc.Items.ItemMeltedMetal;
import com.bioxx.tfc.Items.ItemMetalSheet;
import com.bioxx.tfc.Items.ItemMetalSheet2x;
import com.bioxx.tfc.Items.ItemOre;
import com.bioxx.tfc.Items.ItemOreSmall;
import com.bioxx.tfc.Items.ItemPlank;
import com.bioxx.tfc.Items.ItemQuiver;
import com.bioxx.tfc.Items.ItemRawHide;
import com.bioxx.tfc.Items.ItemReeds;
import com.bioxx.tfc.Items.ItemSluice;
import com.bioxx.tfc.Items.ItemStick;
import com.bioxx.tfc.Items.ItemStoneBrick;
import com.bioxx.tfc.Items.ItemTFCArmor;
import com.bioxx.tfc.Items.ItemTerra;
import com.bioxx.tfc.Items.ItemTuyere;
import com.bioxx.tfc.Items.ItemUnfinishedArmor;
import com.bioxx.tfc.Items.ItemYarn;
import com.bioxx.tfc.Items.Pottery.ItemPotteryBase;
import com.bioxx.tfc.Items.Pottery.ItemPotteryJug;
import com.bioxx.tfc.Items.Pottery.ItemPotteryMold;
import com.bioxx.tfc.Items.Pottery.ItemPotterySmallVessel;
import com.bioxx.tfc.Items.Tools.ItemChisel;
import com.bioxx.tfc.Items.Tools.ItemCustomAxe;
import com.bioxx.tfc.Items.Tools.ItemCustomBow;
import com.bioxx.tfc.Items.Tools.ItemCustomBucket;
import com.bioxx.tfc.Items.Tools.ItemCustomBucketMilk;
import com.bioxx.tfc.Items.Tools.ItemCustomFishingRod;
import com.bioxx.tfc.Items.Tools.ItemCustomHoe;
import com.bioxx.tfc.Items.Tools.ItemCustomPickaxe;
import com.bioxx.tfc.Items.Tools.ItemCustomSaw;
import com.bioxx.tfc.Items.Tools.ItemCustomScythe;
import com.bioxx.tfc.Items.Tools.ItemCustomShovel;
import com.bioxx.tfc.Items.Tools.ItemCustomSword;
import com.bioxx.tfc.Items.Tools.ItemFirestarter;
import com.bioxx.tfc.Items.Tools.ItemFlintSteel;
import com.bioxx.tfc.Items.Tools.ItemGoldPan;
import com.bioxx.tfc.Items.Tools.ItemHammer;
import com.bioxx.tfc.Items.Tools.ItemJavelin;
import com.bioxx.tfc.Items.Tools.ItemKnife;
import com.bioxx.tfc.Items.Tools.ItemMiscToolHead;
import com.bioxx.tfc.Items.Tools.ItemProPick;
import com.bioxx.tfc.Items.Tools.ItemShears;
import com.bioxx.tfc.Items.Tools.ItemSpindle;
import com.bioxx.tfc.Items.Tools.ItemSteelBucketBlue;
import com.bioxx.tfc.Items.Tools.ItemSteelBucketRed;
import com.bioxx.tfc.api.Armor;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.Enums.EnumDamageType;
import com.bioxx.tfc.api.Enums.EnumFoodGroup;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import com.bioxx.tfc.api.Metal;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.TFCOptions;
import java.util.ArrayList;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;





public class ItemSetup
  extends TFCItems
{
  public static void setup() {
    igInToolMaterial = EnumHelper.addToolMaterial("IgIn", 1, igInStoneUses, igInStoneEff, 40.0F, 5);
    sedToolMaterial = EnumHelper.addToolMaterial("Sed", 1, sedStoneUses, sedStoneEff, 40.0F, 5);
    igExToolMaterial = EnumHelper.addToolMaterial("IgEx", 1, igExStoneUses, igExStoneEff, 40.0F, 5);
    mMToolMaterial = EnumHelper.addToolMaterial("MM", 1, mMStoneUses, mMStoneEff, 40.0F, 5);

    copperToolMaterial = EnumHelper.addToolMaterial("Copper", 2, copperUses, copperEff, 65.0F, 8);

    bronzeToolMaterial = EnumHelper.addToolMaterial("Bronze", 2, bronzeUses, bronzeEff, 100.0F, 13);
    bismuthBronzeToolMaterial = EnumHelper.addToolMaterial("BismuthBronze", 2, bismuthBronzeUses, bismuthBronzeEff, 90.0F, 10);
    blackBronzeToolMaterial = EnumHelper.addToolMaterial("BlackBronze", 2, blackBronzeUses, blackBronzeEff, 95.0F, 10);

    ironToolMaterial = EnumHelper.addToolMaterial("Iron", 2, wroughtIronUses, wroughtIronEff, 135.0F, 10);

    steelToolMaterial = EnumHelper.addToolMaterial("Steel", 2, steelUses, steelEff, 170.0F, 10);

    blackSteelToolMaterial = EnumHelper.addToolMaterial("BlackSteel", 2, blackSteelUses, blackSteelEff, 205.0F, 12);

    blueSteelToolMaterial = EnumHelper.addToolMaterial("BlueSteel", 3, blueSteelUses, blueSteelEff, 240.0F, 22);
    redSteelToolMaterial = EnumHelper.addToolMaterial("RedSteel", 3, redSteelUses, redSteelEff, 240.0F, 22);

    TerraFirmaCraft.LOG.info("Loading Items");

    fishingRod = (new ItemCustomFishingRod()).func_77655_b("fishingRod").func_111206_d("tools/fishing_rod");
    coal = (new ItemCoal()).func_77655_b("coal");
    stick = (new ItemStick()).func_77664_n().func_77655_b("stick");
    bow = (new ItemCustomBow()).func_77655_b("bow").func_111206_d("tools/bow");
    Items.field_151031_f = (ItemBow)bow;
    arrow = (new ItemArrow()).func_77655_b("arrow").func_77637_a(TFCTabs.TFC_WEAPONS);
    dye = (new ItemDyeCustom()).func_77655_b("dyePowder").func_111206_d("dye_powder").func_77637_a(TFCTabs.TFC_MATERIALS);
    glassBottle = (new ItemGlassBottle()).func_77655_b("Glass Bottle");
    potion = (new ItemCustomPotion()).func_77655_b("potion").func_111206_d("potion");
    rope = (new ItemCustomLeash()).func_77655_b("Rope").func_77637_a(TFCTabs.TFC_TOOLS);
    Items.field_151058_ca = rope;

    minecartCrate = (new ItemCustomMinecart(1)).func_77655_b("minecartChest").func_111206_d("minecart_chest");
    goldPan = (new ItemGoldPan()).func_77655_b("GoldPan");
    sluiceItem = (new ItemSluice()).setFolder("devices/").func_77655_b("SluiceItem").func_77637_a(TFCTabs.TFC_DEVICES);

    shears = (new ItemShears(0.0F, ironToolMaterial)).func_77655_b("shears").func_111206_d("shears");

    proPickBismuthBronze = (new ItemProPick()).func_77655_b("Bismuth Bronze ProPick").func_77656_e(bismuthBronzeUses / 3);
    proPickBlackBronze = (new ItemProPick()).func_77655_b("Black Bronze ProPick").func_77656_e(blackBronzeUses / 3);
    proPickBlackSteel = (new ItemProPick()).func_77655_b("Black Steel ProPick").func_77656_e(blackSteelUses / 3);
    proPickBlueSteel = (new ItemProPick()).func_77655_b("Blue Steel ProPick").func_77656_e(blueSteelUses / 3);
    proPickBronze = (new ItemProPick()).func_77655_b("Bronze ProPick").func_77656_e(bronzeUses / 3);
    proPickCopper = (new ItemProPick()).func_77655_b("Copper ProPick").func_77656_e(copperUses / 3);
    proPickIron = (new ItemProPick()).func_77655_b("Wrought Iron ProPick").func_77656_e(wroughtIronUses / 3);
    proPickRedSteel = (new ItemProPick()).func_77655_b("Red Steel ProPick").func_77656_e(redSteelUses / 3);
    proPickSteel = (new ItemProPick()).func_77655_b("Steel ProPick").func_77656_e(steelUses / 3);

    bismuthIngot = (new ItemIngot()).func_77655_b("Bismuth Ingot");
    bismuthBronzeIngot = (new ItemIngot()).func_77655_b("Bismuth Bronze Ingot");
    blackBronzeIngot = (new ItemIngot()).func_77655_b("Black Bronze Ingot");
    blackSteelIngot = (new ItemIngot()).func_77655_b("Black Steel Ingot");
    blueSteelIngot = (new ItemIngot()).func_77655_b("Blue Steel Ingot");
    brassIngot = (new ItemIngot()).func_77655_b("Brass Ingot");
    bronzeIngot = (new ItemIngot()).func_77655_b("Bronze Ingot");
    copperIngot = (new ItemIngot()).func_77655_b("Copper Ingot");
    goldIngot = (new ItemIngot()).func_77655_b("Gold Ingot");
    wroughtIronIngot = (new ItemIngot()).func_77655_b("Wrought Iron Ingot");
    leadIngot = (new ItemIngot()).func_77655_b("Lead Ingot");
    nickelIngot = (new ItemIngot()).func_77655_b("Nickel Ingot");
    pigIronIngot = (new ItemIngot()).func_77655_b("Pig Iron Ingot");
    platinumIngot = (new ItemIngot()).func_77655_b("Platinum Ingot");
    redSteelIngot = (new ItemIngot()).func_77655_b("Red Steel Ingot");
    roseGoldIngot = (new ItemIngot()).func_77655_b("Rose Gold Ingot");
    silverIngot = (new ItemIngot()).func_77655_b("Silver Ingot");
    steelIngot = (new ItemIngot()).func_77655_b("Steel Ingot");
    sterlingSilverIngot = (new ItemIngot()).func_77655_b("Sterling Silver Ingot");
    tinIngot = (new ItemIngot()).func_77655_b("Tin Ingot");
    zincIngot = (new ItemIngot()).func_77655_b("Zinc Ingot");

    bismuthIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Bismuth Double Ingot")).setSize(EnumSize.LARGE).setMetal("Bismuth", 200);
    bismuthBronzeIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Bismuth Bronze Double Ingot")).setSize(EnumSize.LARGE).setMetal("Bismuth Bronze", 200);
    blackBronzeIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Black Bronze Double Ingot")).setSize(EnumSize.LARGE).setMetal("Black Bronze", 200);
    blackSteelIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Black Steel Double Ingot")).setSize(EnumSize.LARGE).setMetal("Black Steel", 200);
    blueSteelIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Blue Steel Double Ingot")).setSize(EnumSize.LARGE).setMetal("Blue Steel", 200);
    brassIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Brass Double Ingot")).setSize(EnumSize.LARGE).setMetal("Brass", 200);
    bronzeIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Bronze Double Ingot")).setSize(EnumSize.LARGE).setMetal("Bronze", 200);
    copperIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Copper Double Ingot")).setSize(EnumSize.LARGE).setMetal("Copper", 200);
    goldIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Gold Double Ingot")).setSize(EnumSize.LARGE).setMetal("Gold", 200);
    wroughtIronIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Wrought Iron Double Ingot")).setSize(EnumSize.LARGE).setMetal("Wrought Iron", 200);
    leadIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Lead Double Ingot")).setSize(EnumSize.LARGE).setMetal("Lead", 200);
    nickelIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Nickel Double Ingot")).setSize(EnumSize.LARGE).setMetal("Nickel", 200);
    pigIronIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Pig Iron Double Ingot")).setSize(EnumSize.LARGE).setMetal("Pig Iron", 200);
    platinumIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Platinum Double Ingot")).setSize(EnumSize.LARGE).setMetal("Platinum", 200);
    redSteelIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Red Steel Double Ingot")).setSize(EnumSize.LARGE).setMetal("Red Steel", 200);
    roseGoldIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Rose Gold Double Ingot")).setSize(EnumSize.LARGE).setMetal("Rose Gold", 200);
    silverIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Silver Double Ingot")).setSize(EnumSize.LARGE).setMetal("Silver", 200);
    steelIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Steel Double Ingot")).setSize(EnumSize.LARGE).setMetal("Steel", 200);
    sterlingSilverIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Sterling Silver Double Ingot")).setSize(EnumSize.LARGE).setMetal("Sterling Silver", 200);
    tinIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Tin Double Ingot")).setSize(EnumSize.LARGE).setMetal("Tin", 200);
    zincIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Zinc Double Ingot")).setSize(EnumSize.LARGE).setMetal("Zinc", 200);

    gemRuby = (new ItemGem()).func_77655_b("Ruby");
    gemSapphire = (new ItemGem()).func_77655_b("Sapphire");
    gemEmerald = (new ItemGem()).func_77655_b("Emerald");
    gemTopaz = (new ItemGem()).func_77655_b("Topaz");
    gemTourmaline = (new ItemGem()).func_77655_b("Tourmaline");
    gemJade = (new ItemGem()).func_77655_b("Jade");
    gemBeryl = (new ItemGem()).func_77655_b("Beryl");
    gemAgate = (new ItemGem()).func_77655_b("Agate");
    gemOpal = (new ItemGem()).func_77655_b("Opal");
    gemGarnet = (new ItemGem()).func_77655_b("Garnet");
    gemJasper = (new ItemGem()).func_77655_b("Jasper");
    gemAmethyst = (new ItemGem()).func_77655_b("Amethyst");
    gemDiamond = (new ItemGem()).func_77655_b("Diamond");


    igInShovel = (new ItemCustomShovel(igInToolMaterial)).func_77655_b("IgIn Stone Shovel").func_77656_e(igInStoneUses);
    igInAxe = (new ItemCustomAxe(igInToolMaterial, 60.0F)).func_77655_b("IgIn Stone Axe").func_77656_e(igInStoneUses);
    igInHoe = (new ItemCustomHoe(igInToolMaterial)).func_77655_b("IgIn Stone Hoe").func_77656_e(igInStoneUses);

    sedShovel = (new ItemCustomShovel(sedToolMaterial)).func_77655_b("Sed Stone Shovel").func_77656_e(sedStoneUses);
    sedAxe = (new ItemCustomAxe(sedToolMaterial, 60.0F)).func_77655_b("Sed Stone Axe").func_77656_e(sedStoneUses);
    sedHoe = (new ItemCustomHoe(sedToolMaterial)).func_77655_b("Sed Stone Hoe").func_77656_e(sedStoneUses);

    igExShovel = (new ItemCustomShovel(igExToolMaterial)).func_77655_b("IgEx Stone Shovel").func_77656_e(igExStoneUses);
    igExAxe = (new ItemCustomAxe(igExToolMaterial, 60.0F)).func_77655_b("IgEx Stone Axe").func_77656_e(igExStoneUses);
    igExHoe = (new ItemCustomHoe(igExToolMaterial)).func_77655_b("IgEx Stone Hoe").func_77656_e(igExStoneUses);

    mMShovel = (new ItemCustomShovel(mMToolMaterial)).func_77655_b("MM Stone Shovel").func_77656_e(mMStoneUses);
    mMAxe = (new ItemCustomAxe(mMToolMaterial, 60.0F)).func_77655_b("MM Stone Axe").func_77656_e(mMStoneUses);
    mMHoe = (new ItemCustomHoe(mMToolMaterial)).func_77655_b("MM Stone Hoe").func_77656_e(mMStoneUses);

    bismuthBronzePick = (new ItemCustomPickaxe(bismuthBronzeToolMaterial)).func_77655_b("Bismuth Bronze Pick").func_77656_e(bismuthBronzeUses);
    bismuthBronzeShovel = (new ItemCustomShovel(bismuthBronzeToolMaterial)).func_77655_b("Bismuth Bronze Shovel").func_77656_e(bismuthBronzeUses);
    bismuthBronzeAxe = (new ItemCustomAxe(bismuthBronzeToolMaterial, 150.0F)).func_77655_b("Bismuth Bronze Axe").func_77656_e(bismuthBronzeUses);
    bismuthBronzeHoe = (new ItemCustomHoe(bismuthBronzeToolMaterial)).func_77655_b("Bismuth Bronze Hoe").func_77656_e(bismuthBronzeUses);

    blackBronzePick = (new ItemCustomPickaxe(blackBronzeToolMaterial)).func_77655_b("Black Bronze Pick").func_77656_e(blackBronzeUses);
    blackBronzeShovel = (new ItemCustomShovel(blackBronzeToolMaterial)).func_77655_b("Black Bronze Shovel").func_77656_e(blackBronzeUses);
    blackBronzeAxe = (new ItemCustomAxe(blackBronzeToolMaterial, 170.0F)).func_77655_b("Black Bronze Axe").func_77656_e(blackBronzeUses);
    blackBronzeHoe = (new ItemCustomHoe(blackBronzeToolMaterial)).func_77655_b("Black Bronze Hoe").func_77656_e(blackBronzeUses);

    blackSteelPick = (new ItemCustomPickaxe(blackSteelToolMaterial)).func_77655_b("Black Steel Pick").func_77656_e(blackSteelUses);
    blackSteelShovel = (new ItemCustomShovel(blackSteelToolMaterial)).func_77655_b("Black Steel Shovel").func_77656_e(blackSteelUses);
    blackSteelAxe = (new ItemCustomAxe(blackSteelToolMaterial, 245.0F)).func_77655_b("Black Steel Axe").func_77656_e(blackSteelUses);
    blackSteelHoe = (new ItemCustomHoe(blackSteelToolMaterial)).func_77655_b("Black Steel Hoe").func_77656_e(blackSteelUses);

    blueSteelPick = (new ItemCustomPickaxe(blueSteelToolMaterial)).func_77655_b("Blue Steel Pick").func_77656_e(blueSteelUses);
    blueSteelShovel = (new ItemCustomShovel(blueSteelToolMaterial)).func_77655_b("Blue Steel Shovel").func_77656_e(blueSteelUses);
    blueSteelAxe = (new ItemCustomAxe(blueSteelToolMaterial, 270.0F)).func_77655_b("Blue Steel Axe").func_77656_e(blueSteelUses);
    blueSteelHoe = (new ItemCustomHoe(blueSteelToolMaterial)).func_77655_b("Blue Steel Hoe").func_77656_e(blueSteelUses);

    bronzePick = (new ItemCustomPickaxe(bronzeToolMaterial)).func_77655_b("Bronze Pick").func_77656_e(bronzeUses);
    bronzeShovel = (new ItemCustomShovel(bronzeToolMaterial)).func_77655_b("Bronze Shovel").func_77656_e(bronzeUses);
    bronzeAxe = (new ItemCustomAxe(bronzeToolMaterial, 160.0F)).func_77655_b("Bronze Axe").func_77656_e(bronzeUses);
    bronzeHoe = (new ItemCustomHoe(bronzeToolMaterial)).func_77655_b("Bronze Hoe").func_77656_e(bronzeUses);

    copperPick = (new ItemCustomPickaxe(copperToolMaterial)).func_77655_b("Copper Pick").func_77656_e(copperUses);
    copperShovel = (new ItemCustomShovel(copperToolMaterial)).func_77655_b("Copper Shovel").func_77656_e(copperUses);
    copperAxe = (new ItemCustomAxe(copperToolMaterial, 115.0F)).func_77655_b("Copper Axe").func_77656_e(copperUses);
    copperHoe = (new ItemCustomHoe(copperToolMaterial)).func_77655_b("Copper Hoe").func_77656_e(copperUses);

    wroughtIronPick = (new ItemCustomPickaxe(ironToolMaterial)).func_77655_b("Wrought Iron Pick").func_77656_e(wroughtIronUses);
    wroughtIronShovel = (new ItemCustomShovel(ironToolMaterial)).func_77655_b("Wrought Iron Shovel").func_77656_e(wroughtIronUses);
    wroughtIronAxe = (new ItemCustomAxe(ironToolMaterial, 185.0F)).func_77655_b("Wrought Iron Axe").func_77656_e(wroughtIronUses);
    wroughtIronHoe = (new ItemCustomHoe(ironToolMaterial)).func_77655_b("Wrought Iron Hoe").func_77656_e(wroughtIronUses);

    redSteelPick = (new ItemCustomPickaxe(redSteelToolMaterial)).func_77655_b("Red Steel Pick").func_77656_e(redSteelUses);
    redSteelShovel = (new ItemCustomShovel(redSteelToolMaterial)).func_77655_b("Red Steel Shovel").func_77656_e(redSteelUses);
    redSteelAxe = (new ItemCustomAxe(redSteelToolMaterial, 270.0F)).func_77655_b("Red Steel Axe").func_77656_e(redSteelUses);
    redSteelHoe = (new ItemCustomHoe(redSteelToolMaterial)).func_77655_b("Red Steel Hoe").func_77656_e(redSteelUses);

    steelPick = (new ItemCustomPickaxe(steelToolMaterial)).func_77655_b("Steel Pick").func_77656_e(steelUses);
    steelShovel = (new ItemCustomShovel(steelToolMaterial)).func_77655_b("Steel Shovel").func_77656_e(steelUses);
    steelAxe = (new ItemCustomAxe(steelToolMaterial, 210.0F)).func_77655_b("Steel Axe").func_77656_e(steelUses);
    steelHoe = (new ItemCustomHoe(steelToolMaterial)).func_77655_b("Steel Hoe").func_77656_e(steelUses);


    bismuthBronzeChisel = (new ItemChisel(bismuthBronzeToolMaterial)).func_77655_b("Bismuth Bronze Chisel").func_77656_e(bismuthBronzeUses);
    blackBronzeChisel = (new ItemChisel(blackBronzeToolMaterial)).func_77655_b("Black Bronze Chisel").func_77656_e(blackBronzeUses);
    blackSteelChisel = (new ItemChisel(blackSteelToolMaterial)).func_77655_b("Black Steel Chisel").func_77656_e(blackSteelUses);
    blueSteelChisel = (new ItemChisel(blueSteelToolMaterial)).func_77655_b("Blue Steel Chisel").func_77656_e(blueSteelUses);
    bronzeChisel = (new ItemChisel(bronzeToolMaterial)).func_77655_b("Bronze Chisel").func_77656_e(bronzeUses);
    copperChisel = (new ItemChisel(copperToolMaterial)).func_77655_b("Copper Chisel").func_77656_e(copperUses);
    wroughtIronChisel = (new ItemChisel(ironToolMaterial)).func_77655_b("Wrought Iron Chisel").func_77656_e(wroughtIronUses);
    redSteelChisel = (new ItemChisel(redSteelToolMaterial)).func_77655_b("Red Steel Chisel").func_77656_e(redSteelUses);
    steelChisel = (new ItemChisel(steelToolMaterial)).func_77655_b("Steel Chisel").func_77656_e(steelUses);

    bismuthBronzeSword = (new ItemCustomSword(bismuthBronzeToolMaterial, 210.0F)).func_77655_b("Bismuth Bronze Sword").func_77656_e(bismuthBronzeUses);
    blackBronzeSword = (new ItemCustomSword(blackBronzeToolMaterial, 230.0F)).func_77655_b("Black Bronze Sword").func_77656_e(blackBronzeUses);
    blackSteelSword = (new ItemCustomSword(blackSteelToolMaterial, 270.0F)).func_77655_b("Black Steel Sword").func_77656_e(blackSteelUses);
    blueSteelSword = (new ItemCustomSword(blueSteelToolMaterial, 315.0F)).func_77655_b("Blue Steel Sword").func_77656_e(blueSteelUses);
    bronzeSword = (new ItemCustomSword(bronzeToolMaterial, 220.0F)).func_77655_b("Bronze Sword").func_77656_e(bronzeUses);
    copperSword = (new ItemCustomSword(copperToolMaterial, 165.0F)).func_77655_b("Copper Sword").func_77656_e(copperUses);
    wroughtIronSword = (new ItemCustomSword(ironToolMaterial, 240.0F)).func_77655_b("Wrought Iron Sword").func_77656_e(wroughtIronUses);
    redSteelSword = (new ItemCustomSword(redSteelToolMaterial, 315.0F)).func_77655_b("Red Steel Sword").func_77656_e(redSteelUses);
    steelSword = (new ItemCustomSword(steelToolMaterial, 265.0F)).func_77655_b("Steel Sword").func_77656_e(steelUses);

    bismuthBronzeMace = (new ItemCustomSword(bismuthBronzeToolMaterial, 210.0F, EnumDamageType.CRUSHING)).func_77655_b("Bismuth Bronze Mace").func_77656_e(bismuthBronzeUses);
    blackBronzeMace = (new ItemCustomSword(blackBronzeToolMaterial, 230.0F, EnumDamageType.CRUSHING)).func_77655_b("Black Bronze Mace").func_77656_e(blackBronzeUses);
    blackSteelMace = (new ItemCustomSword(blackSteelToolMaterial, 270.0F, EnumDamageType.CRUSHING)).func_77655_b("Black Steel Mace").func_77656_e(blackSteelUses);
    blueSteelMace = (new ItemCustomSword(blueSteelToolMaterial, 315.0F, EnumDamageType.CRUSHING)).func_77655_b("Blue Steel Mace").func_77656_e(blueSteelUses);
    bronzeMace = (new ItemCustomSword(bronzeToolMaterial, 220.0F, EnumDamageType.CRUSHING)).func_77655_b("Bronze Mace").func_77656_e(bronzeUses);
    copperMace = (new ItemCustomSword(copperToolMaterial, 165.0F, EnumDamageType.CRUSHING)).func_77655_b("Copper Mace").func_77656_e(copperUses);
    wroughtIronMace = (new ItemCustomSword(ironToolMaterial, 240.0F, EnumDamageType.CRUSHING)).func_77655_b("Wrought Iron Mace").func_77656_e(wroughtIronUses);
    redSteelMace = (new ItemCustomSword(redSteelToolMaterial, 315.0F, EnumDamageType.CRUSHING)).func_77655_b("Red Steel Mace").func_77656_e(redSteelUses);
    steelMace = (new ItemCustomSword(steelToolMaterial, 265.0F, EnumDamageType.CRUSHING)).func_77655_b("Steel Mace").func_77656_e(steelUses);

    bismuthBronzeSaw = (new ItemCustomSaw(bismuthBronzeToolMaterial)).func_77655_b("Bismuth Bronze Saw").func_77656_e(bismuthBronzeUses);
    blackBronzeSaw = (new ItemCustomSaw(blackBronzeToolMaterial)).func_77655_b("Black Bronze Saw").func_77656_e(blackBronzeUses);
    blackSteelSaw = (new ItemCustomSaw(blackSteelToolMaterial)).func_77655_b("Black Steel Saw").func_77656_e(blackSteelUses);
    blueSteelSaw = (new ItemCustomSaw(blueSteelToolMaterial)).func_77655_b("Blue Steel Saw").func_77656_e(blueSteelUses);
    bronzeSaw = (new ItemCustomSaw(bronzeToolMaterial)).func_77655_b("Bronze Saw").func_77656_e(bronzeUses);
    copperSaw = (new ItemCustomSaw(copperToolMaterial)).func_77655_b("Copper Saw").func_77656_e(copperUses);
    wroughtIronSaw = (new ItemCustomSaw(ironToolMaterial)).func_77655_b("Wrought Iron Saw").func_77656_e(wroughtIronUses);
    redSteelSaw = (new ItemCustomSaw(redSteelToolMaterial)).func_77655_b("Red Steel Saw").func_77656_e(redSteelUses);
    steelSaw = (new ItemCustomSaw(steelToolMaterial)).func_77655_b("Steel Saw").func_77656_e(steelUses);

    highCarbonBlackSteelIngot = (new ItemIngot(false)).func_77655_b("HC Black Steel Ingot");
    weakBlueSteelIngot = (new ItemIngot(false)).func_77655_b("Weak Blue Steel Ingot");
    weakRedSteelIngot = (new ItemIngot(false)).func_77655_b("Weak Red Steel Ingot");
    weakSteelIngot = (new ItemIngot(false)).func_77655_b("Weak Steel Ingot");
    highCarbonBlueSteelIngot = (new ItemIngot(false)).func_77655_b("HC Blue Steel Ingot");
    highCarbonRedSteelIngot = (new ItemIngot(false)).func_77655_b("HC Red Steel Ingot");
    highCarbonSteelIngot = (new ItemIngot(false)).func_77655_b("HC Steel Ingot");

    oreChunk = (new ItemOre()).setFolder("ore/").func_77655_b("Ore");
    smallOreChunk = (new ItemOreSmall()).func_77655_b("Small Ore");
    powder = (new ItemTerra()).setMetaNames(Global.POWDER).func_77655_b("Powder").func_77637_a(TFCTabs.TFC_MATERIALS);
    logs = (new ItemLogs()).func_77655_b("Log");



    igInStoneJavelin = (new ItemJavelin(igInToolMaterial, 60.0F)).func_77655_b("IgIn Stone Javelin");
    sedStoneJavelin = (new ItemJavelin(sedToolMaterial, 60.0F)).func_77655_b("Sed Stone Javelin");
    igExStoneJavelin = (new ItemJavelin(igExToolMaterial, 60.0F)).func_77655_b("IgEx Stone Javelin");
    mMStoneJavelin = (new ItemJavelin(mMToolMaterial, 60.0F)).func_77655_b("MM Stone Javelin");
    copperJavelin = (new ItemJavelin(copperToolMaterial, 80.0F)).func_77655_b("Copper Javelin");
    bismuthBronzeJavelin = (new ItemJavelin(bismuthBronzeToolMaterial, 90.0F)).func_77655_b("Bismuth Bronze Javelin");
    bronzeJavelin = (new ItemJavelin(bronzeToolMaterial, 100.0F)).func_77655_b("Bronze Javelin");
    blackBronzeJavelin = (new ItemJavelin(blackBronzeToolMaterial, 95.0F)).func_77655_b("Black Bronze Javelin");
    wroughtIronJavelin = (new ItemJavelin(ironToolMaterial, 135.0F)).func_77655_b("Wrought Iron Javelin");
    steelJavelin = (new ItemJavelin(steelToolMaterial, 170.0F)).func_77655_b("Steel Javelin");
    blackSteelJavelin = (new ItemJavelin(blackSteelToolMaterial, 205.0F)).func_77655_b("Black Steel Javelin");
    blueSteelJavelin = (new ItemJavelin(blueSteelToolMaterial, 240.0F)).func_77655_b("Blue Steel Javelin");
    redSteelJavelin = (new ItemJavelin(redSteelToolMaterial, 240.0F)).func_77655_b("Red Steel Javelin");


    igInStoneJavelinHead = (new ItemMiscToolHead(igInToolMaterial)).func_77655_b("IgIn Stone Javelin Head");
    sedStoneJavelinHead = (new ItemMiscToolHead(sedToolMaterial)).func_77655_b("Sed Stone Javelin Head");
    igExStoneJavelinHead = (new ItemMiscToolHead(igExToolMaterial)).func_77655_b("IgEx Stone Javelin Head");
    mMStoneJavelinHead = (new ItemMiscToolHead(mMToolMaterial)).func_77655_b("MM Stone Javelin Head");
    copperJavelinHead = (new ItemMiscToolHead()).func_77655_b("Copper Javelin Head");
    bismuthBronzeJavelinHead = (new ItemMiscToolHead()).func_77655_b("Bismuth Bronze Javelin Head");
    bronzeJavelinHead = (new ItemMiscToolHead()).func_77655_b("Bronze Javelin Head");
    blackBronzeJavelinHead = (new ItemMiscToolHead()).func_77655_b("Black Bronze Javelin Head");
    wroughtIronJavelinHead = (new ItemMiscToolHead()).func_77655_b("Wrought Iron Javelin Head");
    steelJavelinHead = (new ItemMiscToolHead()).func_77655_b("Steel Javelin Head");
    blackSteelJavelinHead = (new ItemMiscToolHead()).func_77655_b("Black Steel Javelin Head");
    blueSteelJavelinHead = (new ItemMiscToolHead()).func_77655_b("Blue Steel Javelin Head");
    redSteelJavelinHead = (new ItemMiscToolHead()).func_77655_b("Red Steel Javelin Head");

    bismuthUnshaped = (new ItemMeltedMetal()).func_77655_b("Bismuth Unshaped");
    bismuthBronzeUnshaped = (new ItemMeltedMetal()).func_77655_b("Bismuth Bronze Unshaped");
    blackBronzeUnshaped = (new ItemMeltedMetal()).func_77655_b("Black Bronze Unshaped");
    blackSteelUnshaped = (new ItemMeltedMetal()).func_77655_b("Black Steel Unshaped");
    blueSteelUnshaped = (new ItemMeltedMetal()).func_77655_b("Blue Steel Unshaped");
    brassUnshaped = (new ItemMeltedMetal()).func_77655_b("Brass Unshaped");
    bronzeUnshaped = (new ItemMeltedMetal()).func_77655_b("Bronze Unshaped");
    copperUnshaped = (new ItemMeltedMetal()).func_77655_b("Copper Unshaped");
    goldUnshaped = (new ItemMeltedMetal()).func_77655_b("Gold Unshaped");
    wroughtIronUnshaped = (new ItemMeltedMetal()).func_77655_b("Wrought Iron Unshaped");
    leadUnshaped = (new ItemMeltedMetal()).func_77655_b("Lead Unshaped");
    nickelUnshaped = (new ItemMeltedMetal()).func_77655_b("Nickel Unshaped");
    pigIronUnshaped = (new ItemMeltedMetal()).func_77655_b("Pig Iron Unshaped");
    platinumUnshaped = (new ItemMeltedMetal()).func_77655_b("Platinum Unshaped");
    redSteelUnshaped = (new ItemMeltedMetal()).func_77655_b("Red Steel Unshaped");
    roseGoldUnshaped = (new ItemMeltedMetal()).func_77655_b("Rose Gold Unshaped");
    silverUnshaped = (new ItemMeltedMetal()).func_77655_b("Silver Unshaped");
    steelUnshaped = (new ItemMeltedMetal()).func_77655_b("Steel Unshaped");
    sterlingSilverUnshaped = (new ItemMeltedMetal()).func_77655_b("Sterling Silver Unshaped");
    tinUnshaped = (new ItemMeltedMetal()).func_77655_b("Tin Unshaped");
    zincUnshaped = (new ItemMeltedMetal()).func_77655_b("Zinc Unshaped");


    stoneHammer = (new ItemHammer(igInToolMaterial, 60.0F)).func_77655_b("Stone Hammer").func_77656_e(igInStoneUses);
    bismuthBronzeHammer = (new ItemHammer(bismuthBronzeToolMaterial, 90.0F)).func_77655_b("Bismuth Bronze Hammer").func_77656_e(bismuthBronzeUses);
    blackBronzeHammer = (new ItemHammer(blackBronzeToolMaterial, 95.0F)).func_77655_b("Black Bronze Hammer").func_77656_e(blackBronzeUses);
    blackSteelHammer = (new ItemHammer(blackSteelToolMaterial, 205.0F)).func_77655_b("Black Steel Hammer").func_77656_e(blackSteelUses);
    blueSteelHammer = (new ItemHammer(blueSteelToolMaterial, 240.0F)).func_77655_b("Blue Steel Hammer").func_77656_e(blueSteelUses);
    bronzeHammer = (new ItemHammer(bronzeToolMaterial, 100.0F)).func_77655_b("Bronze Hammer").func_77656_e(bronzeUses);
    copperHammer = (new ItemHammer(copperToolMaterial, 80.0F)).func_77655_b("Copper Hammer").func_77656_e(copperUses);
    wroughtIronHammer = (new ItemHammer(ironToolMaterial, 135.0F)).func_77655_b("Wrought Iron Hammer").func_77656_e(wroughtIronUses);
    redSteelHammer = (new ItemHammer(redSteelToolMaterial, 240.0F)).func_77655_b("Red Steel Hammer").func_77656_e(redSteelUses);
    steelHammer = (new ItemHammer(steelToolMaterial, 170.0F)).func_77655_b("Steel Hammer").func_77656_e(steelUses);

    ink = (new ItemTerra()).func_77655_b("Ink").func_77637_a(TFCTabs.TFC_MATERIALS);
    fireStarter = (new ItemFirestarter()).setFolder("tools/").func_77655_b("Firestarter");


    bismuthBronzePickaxeHead = (new ItemMiscToolHead()).func_77655_b("Bismuth Bronze Pick Head");
    blackBronzePickaxeHead = (new ItemMiscToolHead()).func_77655_b("Black Bronze Pick Head");
    blackSteelPickaxeHead = (new ItemMiscToolHead()).func_77655_b("Black Steel Pick Head");
    blueSteelPickaxeHead = (new ItemMiscToolHead()).func_77655_b("Blue Steel Pick Head");
    bronzePickaxeHead = (new ItemMiscToolHead()).func_77655_b("Bronze Pick Head");
    copperPickaxeHead = (new ItemMiscToolHead()).func_77655_b("Copper Pick Head");
    wroughtIronPickaxeHead = (new ItemMiscToolHead()).func_77655_b("Wrought Iron Pick Head");
    redSteelPickaxeHead = (new ItemMiscToolHead()).func_77655_b("Red Steel Pick Head");
    steelPickaxeHead = (new ItemMiscToolHead()).func_77655_b("Steel Pick Head");

    bismuthBronzeShovelHead = (new ItemMiscToolHead()).func_77655_b("Bismuth Bronze Shovel Head");
    blackBronzeShovelHead = (new ItemMiscToolHead()).func_77655_b("Black Bronze Shovel Head");
    blackSteelShovelHead = (new ItemMiscToolHead()).func_77655_b("Black Steel Shovel Head");
    blueSteelShovelHead = (new ItemMiscToolHead()).func_77655_b("Blue Steel Shovel Head");
    bronzeShovelHead = (new ItemMiscToolHead()).func_77655_b("Bronze Shovel Head");
    copperShovelHead = (new ItemMiscToolHead()).func_77655_b("Copper Shovel Head");
    wroughtIronShovelHead = (new ItemMiscToolHead()).func_77655_b("Wrought Iron Shovel Head");
    redSteelShovelHead = (new ItemMiscToolHead()).func_77655_b("Red Steel Shovel Head");
    steelShovelHead = (new ItemMiscToolHead()).func_77655_b("Steel Shovel Head");

    bismuthBronzeHoeHead = (new ItemMiscToolHead()).func_77655_b("Bismuth Bronze Hoe Head");
    blackBronzeHoeHead = (new ItemMiscToolHead()).func_77655_b("Black Bronze Hoe Head");
    blackSteelHoeHead = (new ItemMiscToolHead()).func_77655_b("Black Steel Hoe Head");
    blueSteelHoeHead = (new ItemMiscToolHead()).func_77655_b("Blue Steel Hoe Head");
    bronzeHoeHead = (new ItemMiscToolHead()).func_77655_b("Bronze Hoe Head");
    copperHoeHead = (new ItemMiscToolHead()).func_77655_b("Copper Hoe Head");
    wroughtIronHoeHead = (new ItemMiscToolHead()).func_77655_b("Wrought Iron Hoe Head");
    redSteelHoeHead = (new ItemMiscToolHead()).func_77655_b("Red Steel Hoe Head");
    steelHoeHead = (new ItemMiscToolHead()).func_77655_b("Steel Hoe Head");

    bismuthBronzeAxeHead = (new ItemMiscToolHead()).func_77655_b("Bismuth Bronze Axe Head");
    blackBronzeAxeHead = (new ItemMiscToolHead()).func_77655_b("Black Bronze Axe Head");
    blackSteelAxeHead = (new ItemMiscToolHead()).func_77655_b("Black Steel Axe Head");
    blueSteelAxeHead = (new ItemMiscToolHead()).func_77655_b("Blue Steel Axe Head");
    bronzeAxeHead = (new ItemMiscToolHead()).func_77655_b("Bronze Axe Head");
    copperAxeHead = (new ItemMiscToolHead()).func_77655_b("Copper Axe Head");
    wroughtIronAxeHead = (new ItemMiscToolHead()).func_77655_b("Wrought Iron Axe Head");
    redSteelAxeHead = (new ItemMiscToolHead()).func_77655_b("Red Steel Axe Head");
    steelAxeHead = (new ItemMiscToolHead()).func_77655_b("Steel Axe Head");

    bismuthBronzeHammerHead = (new ItemMiscToolHead()).func_77655_b("Bismuth Bronze Hammer Head");
    blackBronzeHammerHead = (new ItemMiscToolHead()).func_77655_b("Black Bronze Hammer Head");
    blackSteelHammerHead = (new ItemMiscToolHead()).func_77655_b("Black Steel Hammer Head");
    blueSteelHammerHead = (new ItemMiscToolHead()).func_77655_b("Blue Steel Hammer Head");
    bronzeHammerHead = (new ItemMiscToolHead()).func_77655_b("Bronze Hammer Head");
    copperHammerHead = (new ItemMiscToolHead()).func_77655_b("Copper Hammer Head");
    wroughtIronHammerHead = (new ItemMiscToolHead()).func_77655_b("Wrought Iron Hammer Head");
    redSteelHammerHead = (new ItemMiscToolHead()).func_77655_b("Red Steel Hammer Head");
    steelHammerHead = (new ItemMiscToolHead()).func_77655_b("Steel Hammer Head");


    bismuthBronzeChiselHead = (new ItemMiscToolHead()).func_77655_b("Bismuth Bronze Chisel Head");
    blackBronzeChiselHead = (new ItemMiscToolHead()).func_77655_b("Black Bronze Chisel Head");
    blackSteelChiselHead = (new ItemMiscToolHead()).func_77655_b("Black Steel Chisel Head");
    blueSteelChiselHead = (new ItemMiscToolHead()).func_77655_b("Blue Steel Chisel Head");
    bronzeChiselHead = (new ItemMiscToolHead()).func_77655_b("Bronze Chisel Head");
    copperChiselHead = (new ItemMiscToolHead()).func_77655_b("Copper Chisel Head");
    wroughtIronChiselHead = (new ItemMiscToolHead()).func_77655_b("Wrought Iron Chisel Head");
    redSteelChiselHead = (new ItemMiscToolHead()).func_77655_b("Red Steel Chisel Head");
    steelChiselHead = (new ItemMiscToolHead()).func_77655_b("Steel Chisel Head");

    bismuthBronzeSwordHead = (new ItemMiscToolHead()).func_77655_b("Bismuth Bronze Sword Blade");
    blackBronzeSwordHead = (new ItemMiscToolHead()).func_77655_b("Black Bronze Sword Blade");
    blackSteelSwordHead = (new ItemMiscToolHead()).func_77655_b("Black Steel Sword Blade");
    blueSteelSwordHead = (new ItemMiscToolHead()).func_77655_b("Blue Steel Sword Blade");
    bronzeSwordHead = (new ItemMiscToolHead()).func_77655_b("Bronze Sword Blade");
    copperSwordHead = (new ItemMiscToolHead()).func_77655_b("Copper Sword Blade");
    wroughtIronSwordHead = (new ItemMiscToolHead()).func_77655_b("Wrought Iron Sword Blade");
    redSteelSwordHead = (new ItemMiscToolHead()).func_77655_b("Red Steel Sword Blade");
    steelSwordHead = (new ItemMiscToolHead()).func_77655_b("Steel Sword Blade");

    bismuthBronzeMaceHead = (new ItemMiscToolHead()).func_77655_b("Bismuth Bronze Mace Head");
    blackBronzeMaceHead = (new ItemMiscToolHead()).func_77655_b("Black Bronze Mace Head");
    blackSteelMaceHead = (new ItemMiscToolHead()).func_77655_b("Black Steel Mace Head");
    blueSteelMaceHead = (new ItemMiscToolHead()).func_77655_b("Blue Steel Mace Head");
    bronzeMaceHead = (new ItemMiscToolHead()).func_77655_b("Bronze Mace Head");
    copperMaceHead = (new ItemMiscToolHead()).func_77655_b("Copper Mace Head");
    wroughtIronMaceHead = (new ItemMiscToolHead()).func_77655_b("Wrought Iron Mace Head");
    redSteelMaceHead = (new ItemMiscToolHead()).func_77655_b("Red Steel Mace Head");
    steelMaceHead = (new ItemMiscToolHead()).func_77655_b("Steel Mace Head");

    bismuthBronzeSawHead = (new ItemMiscToolHead()).func_77655_b("Bismuth Bronze Saw Blade");
    blackBronzeSawHead = (new ItemMiscToolHead()).func_77655_b("Black Bronze Saw Blade");
    blackSteelSawHead = (new ItemMiscToolHead()).func_77655_b("Black Steel Saw Blade");
    blueSteelSawHead = (new ItemMiscToolHead()).func_77655_b("Blue Steel Saw Blade");
    bronzeSawHead = (new ItemMiscToolHead()).func_77655_b("Bronze Saw Blade");
    copperSawHead = (new ItemMiscToolHead()).func_77655_b("Copper Saw Blade");
    wroughtIronSawHead = (new ItemMiscToolHead()).func_77655_b("Wrought Iron Saw Blade");
    redSteelSawHead = (new ItemMiscToolHead()).func_77655_b("Red Steel Saw Blade");
    steelSawHead = (new ItemMiscToolHead()).func_77655_b("Steel Saw Blade");

    highCarbonBlackSteelUnshaped = (new ItemMeltedMetal()).func_77655_b("HC Black Steel Unshaped");
    weakBlueSteelUnshaped = (new ItemMeltedMetal()).func_77655_b("Weak Blue Steel Unshaped");
    highCarbonBlueSteelUnshaped = (new ItemMeltedMetal()).func_77655_b("HC Blue Steel Unshaped");
    weakRedSteelUnshaped = (new ItemMeltedMetal()).func_77655_b("Weak Red Steel Unshaped");
    highCarbonRedSteelUnshaped = (new ItemMeltedMetal()).func_77655_b("HC Red Steel Unshaped");
    weakSteelUnshaped = (new ItemMeltedMetal()).func_77655_b("Weak Steel Unshaped");
    highCarbonSteelUnshaped = (new ItemMeltedMetal()).func_77655_b("HC Steel Unshaped");


    bismuthBronzeProPickHead = (new ItemMiscToolHead()).func_77655_b("Bismuth Bronze ProPick Head");
    blackBronzeProPickHead = (new ItemMiscToolHead()).func_77655_b("Black Bronze ProPick Head");
    blackSteelProPickHead = (new ItemMiscToolHead()).func_77655_b("Black Steel ProPick Head");
    blueSteelProPickHead = (new ItemMiscToolHead()).func_77655_b("Blue Steel ProPick Head");
    bronzeProPickHead = (new ItemMiscToolHead()).func_77655_b("Bronze ProPick Head");
    copperProPickHead = (new ItemMiscToolHead()).func_77655_b("Copper ProPick Head");
    wroughtIronProPickHead = (new ItemMiscToolHead()).func_77655_b("Wrought Iron ProPick Head");
    redSteelProPickHead = (new ItemMiscToolHead()).func_77655_b("Red Steel ProPick Head");
    steelProPickHead = (new ItemMiscToolHead()).func_77655_b("Steel ProPick Head");




    bismuthBronzeScythe = (new ItemCustomScythe(bismuthBronzeToolMaterial)).func_77655_b("Bismuth Bronze Scythe").func_77656_e(bismuthBronzeUses);
    blackBronzeScythe = (new ItemCustomScythe(blackBronzeToolMaterial)).func_77655_b("Black Bronze Scythe").func_77656_e(blackBronzeUses);
    blackSteelScythe = (new ItemCustomScythe(blackSteelToolMaterial)).func_77655_b("Black Steel Scythe").func_77656_e(blackSteelUses);
    blueSteelScythe = (new ItemCustomScythe(blueSteelToolMaterial)).func_77655_b("Blue Steel Scythe").func_77656_e(blueSteelUses);
    bronzeScythe = (new ItemCustomScythe(bronzeToolMaterial)).func_77655_b("Bronze Scythe").func_77656_e(bronzeUses);
    copperScythe = (new ItemCustomScythe(copperToolMaterial)).func_77655_b("Copper Scythe").func_77656_e(copperUses);
    wroughtIronScythe = (new ItemCustomScythe(ironToolMaterial)).func_77655_b("Wrought Iron Scythe").func_77656_e(wroughtIronUses);
    redSteelScythe = (new ItemCustomScythe(redSteelToolMaterial)).func_77655_b("Red Steel Scythe").func_77656_e(redSteelUses);
    steelScythe = (new ItemCustomScythe(steelToolMaterial)).func_77655_b("Steel Scythe").func_77656_e(steelUses);

    bismuthBronzeScytheHead = (new ItemMiscToolHead()).func_77655_b("Bismuth Bronze Scythe Blade");
    blackBronzeScytheHead = (new ItemMiscToolHead()).func_77655_b("Black Bronze Scythe Blade");
    blackSteelScytheHead = (new ItemMiscToolHead()).func_77655_b("Black Steel Scythe Blade");
    blueSteelScytheHead = (new ItemMiscToolHead()).func_77655_b("Blue Steel Scythe Blade");
    bronzeScytheHead = (new ItemMiscToolHead()).func_77655_b("Bronze Scythe Blade");
    copperScytheHead = (new ItemMiscToolHead()).func_77655_b("Copper Scythe Blade");
    wroughtIronScytheHead = (new ItemMiscToolHead()).func_77655_b("Wrought Iron Scythe Blade");
    redSteelScytheHead = (new ItemMiscToolHead()).func_77655_b("Red Steel Scythe Blade");
    steelScytheHead = (new ItemMiscToolHead()).func_77655_b("Steel Scythe Blade");

    woodenBucketEmpty = (new ItemCustomBucket(Blocks.field_150350_a)).func_77655_b("Wooden Bucket Empty");
    woodenBucketWater = (new ItemCustomBucket(TFCBlocks.freshWater, woodenBucketEmpty)).func_77655_b("Wooden Bucket Water");
    woodenBucketSaltWater = (new ItemCustomBucket(TFCBlocks.saltWater, woodenBucketEmpty)).func_77655_b("Wooden Bucket Salt Water");
    woodenBucketMilk = (new ItemCustomBucketMilk()).func_77655_b("Wooden Bucket Milk").func_77642_a(woodenBucketEmpty).func_77637_a(TFCTabs.TFC_FOODS);

    bismuthBronzeKnifeHead = (new ItemMiscToolHead()).func_77655_b("Bismuth Bronze Knife Blade");
    blackBronzeKnifeHead = (new ItemMiscToolHead()).func_77655_b("Black Bronze Knife Blade");
    blackSteelKnifeHead = (new ItemMiscToolHead()).func_77655_b("Black Steel Knife Blade");
    blueSteelKnifeHead = (new ItemMiscToolHead()).func_77655_b("Blue Steel Knife Blade");
    bronzeKnifeHead = (new ItemMiscToolHead()).func_77655_b("Bronze Knife Blade");
    copperKnifeHead = (new ItemMiscToolHead()).func_77655_b("Copper Knife Blade");
    wroughtIronKnifeHead = (new ItemMiscToolHead()).func_77655_b("Wrought Iron Knife Blade");
    redSteelKnifeHead = (new ItemMiscToolHead()).func_77655_b("Red Steel Knife Blade");
    steelKnifeHead = (new ItemMiscToolHead()).func_77655_b("Steel Knife Blade");

    bismuthBronzeKnife = (new ItemKnife(bismuthBronzeToolMaterial, 155.0F)).func_77655_b("Bismuth Bronze Knife").func_77656_e(bismuthBronzeUses);
    blackBronzeKnife = (new ItemKnife(blackBronzeToolMaterial, 165.0F)).func_77655_b("Black Bronze Knife").func_77656_e(blackBronzeUses);
    blackSteelKnife = (new ItemKnife(blackSteelToolMaterial, 205.0F)).func_77655_b("Black Steel Knife").func_77656_e(blackSteelUses);
    blueSteelKnife = (new ItemKnife(blueSteelToolMaterial, 250.0F)).func_77655_b("Blue Steel Knife").func_77656_e(blueSteelUses);
    bronzeKnife = (new ItemKnife(bronzeToolMaterial, 150.0F)).func_77655_b("Bronze Knife").func_77656_e(bronzeUses);
    copperKnife = (new ItemKnife(copperToolMaterial, 100.0F)).func_77655_b("Copper Knife").func_77656_e(copperUses);
    wroughtIronKnife = (new ItemKnife(ironToolMaterial, 175.0F)).func_77655_b("Wrought Iron Knife").func_77656_e(wroughtIronUses);
    redSteelKnife = (new ItemKnife(redSteelToolMaterial, 250.0F)).func_77655_b("Red Steel Knife").func_77656_e(redSteelUses);
    steelKnife = (new ItemKnife(steelToolMaterial, 200.0F)).func_77655_b("Steel Knife").func_77656_e(steelUses);

    flatRock = (new ItemFlatGeneric()).setFolder("rocks/flatrocks/").setMetaNames(Global.STONE_ALL).func_77655_b("FlatRock");
    looseRock = (new ItemLooseRock()).setSpecialCraftingType(flatRock).setFolder("rocks/").setMetaNames(Global.STONE_ALL).func_77655_b("LooseRock");

    igInStoneShovelHead = (new ItemMiscToolHead(igInToolMaterial)).func_77655_b("IgIn Stone Shovel Head");
    sedStoneShovelHead = (new ItemMiscToolHead(sedToolMaterial)).func_77655_b("Sed Stone Shovel Head");
    igExStoneShovelHead = (new ItemMiscToolHead(igExToolMaterial)).func_77655_b("IgEx Stone Shovel Head");
    mMStoneShovelHead = (new ItemMiscToolHead(mMToolMaterial)).func_77655_b("MM Stone Shovel Head");

    igInStoneAxeHead = (new ItemMiscToolHead(igInToolMaterial)).func_77655_b("IgIn Stone Axe Head");
    sedStoneAxeHead = (new ItemMiscToolHead(sedToolMaterial)).func_77655_b("Sed Stone Axe Head");
    igExStoneAxeHead = (new ItemMiscToolHead(igExToolMaterial)).func_77655_b("IgEx Stone Axe Head");
    mMStoneAxeHead = (new ItemMiscToolHead(mMToolMaterial)).func_77655_b("MM Stone Axe Head");

    igInStoneHoeHead = (new ItemMiscToolHead(igInToolMaterial)).func_77655_b("IgIn Stone Hoe Head");
    sedStoneHoeHead = (new ItemMiscToolHead(sedToolMaterial)).func_77655_b("Sed Stone Hoe Head");
    igExStoneHoeHead = (new ItemMiscToolHead(igExToolMaterial)).func_77655_b("IgEx Stone Hoe Head");
    mMStoneHoeHead = (new ItemMiscToolHead(mMToolMaterial)).func_77655_b("MM Stone Hoe Head");

    stoneKnifeHead = (new ItemMiscToolHead(igInToolMaterial)).func_77655_b("Stone Knife Blade");
    stoneHammerHead = (new ItemMiscToolHead(igInToolMaterial)).func_77655_b("Stone Hammer Head");

    stoneKnife = (new ItemKnife(igExToolMaterial, 40.0F)).func_77655_b("Stone Knife").func_77656_e(igExStoneUses);
    singlePlank = (new ItemPlank()).func_77655_b("SinglePlank");

    redSteelBucketEmpty = (new ItemSteelBucketRed(Blocks.field_150350_a)).func_77655_b("Red Steel Bucket Empty");
    redSteelBucketWater = (new ItemSteelBucketRed(TFCBlocks.freshWater)).func_77655_b("Red Steel Bucket Water").func_77642_a(redSteelBucketEmpty);
    redSteelBucketSaltWater = (new ItemSteelBucketRed(TFCBlocks.saltWater)).func_77655_b("Red Steel Bucket Salt Water").func_77642_a(redSteelBucketEmpty);

    blueSteelBucketEmpty = (new ItemSteelBucketBlue(Blocks.field_150350_a)).func_77655_b("Blue Steel Bucket Empty");
    blueSteelBucketLava = (new ItemSteelBucketBlue(TFCBlocks.lava)).func_77655_b("Blue Steel Bucket Lava").func_77642_a(blueSteelBucketEmpty);

    quern = (Item)((ItemTerra)(new ItemTerra()).func_77655_b("Quern").func_77656_e(250)).setSize(EnumSize.MEDIUM).setWeight(EnumWeight.HEAVY);
    flintSteel = (new ItemFlintSteel()).func_77655_b("flintAndSteel").func_77656_e(200).func_111206_d("flint_and_steel");

    doorOak = (new ItemWoodDoor(0)).func_77655_b("Oak Door");
    doorAspen = (new ItemWoodDoor(1)).func_77655_b("Aspen Door");
    doorBirch = (new ItemWoodDoor(2)).func_77655_b("Birch Door");
    doorChestnut = (new ItemWoodDoor(3)).func_77655_b("Chestnut Door");
    doorDouglasFir = (new ItemWoodDoor(4)).func_77655_b("Douglas Fir Door");
    doorHickory = (new ItemWoodDoor(5)).func_77655_b("Hickory Door");
    doorMaple = (new ItemWoodDoor(6)).func_77655_b("Maple Door");
    doorAsh = (new ItemWoodDoor(7)).func_77655_b("Ash Door");
    doorPine = (new ItemWoodDoor(8)).func_77655_b("Pine Door");
    doorSequoia = (new ItemWoodDoor(9)).func_77655_b("Sequoia Door");
    doorSpruce = (new ItemWoodDoor(10)).func_77655_b("Spruce Door");
    doorSycamore = (new ItemWoodDoor(11)).func_77655_b("Sycamore Door");
    doorWhiteCedar = (new ItemWoodDoor(12)).func_77655_b("White Cedar Door");
    doorWhiteElm = (new ItemWoodDoor(13)).func_77655_b("White Elm Door");
    doorWillow = (new ItemWoodDoor(14)).func_77655_b("Willow Door");
    doorKapok = (new ItemWoodDoor(15)).func_77655_b("Kapok Door");
    doorAcacia = (new ItemWoodDoor(16)).func_77655_b("Acacia Door");


    doorWattle = (new ItemWoodDoor(-1)).func_77655_b("Wattle Door");

    beer = (new ItemAlcohol()).func_77655_b("Beer").func_77637_a(TFCTabs.TFC_FOODS);
    cider = (new ItemAlcohol()).func_77655_b("Cider").func_77637_a(TFCTabs.TFC_FOODS);
    rum = (new ItemAlcohol()).func_77655_b("Rum").func_77637_a(TFCTabs.TFC_FOODS);
    ryeWhiskey = (new ItemAlcohol()).func_77655_b("RyeWhiskey").func_77637_a(TFCTabs.TFC_FOODS);
    sake = (new ItemAlcohol()).func_77655_b("Sake").func_77637_a(TFCTabs.TFC_FOODS);
    vodka = (new ItemAlcohol()).func_77655_b("Vodka").func_77637_a(TFCTabs.TFC_FOODS);
    whiskey = (new ItemAlcohol()).func_77655_b("Whiskey").func_77637_a(TFCTabs.TFC_FOODS);
    cornWhiskey = (new ItemAlcohol()).func_77655_b("CornWhiskey").func_77637_a(TFCTabs.TFC_FOODS);

    blueprint = (Item)new ItemBlueprint();
    nametag = (Item)new ItemCustomNameTag();

    woolYarn = (new ItemYarn()).func_77655_b("WoolYarn").func_77637_a(TFCTabs.TFC_MATERIALS);
    wool = (new ItemTerra()).func_77655_b("Wool").func_77637_a(TFCTabs.TFC_MATERIALS);
    woolCloth = (new ItemTerra()).func_77655_b("WoolCloth").func_77637_a(TFCTabs.TFC_MATERIALS);
    silkCloth = (new ItemTerra()).func_77655_b("SilkCloth").func_77637_a(TFCTabs.TFC_MATERIALS);
    burlapCloth = (new ItemTerra()).func_77655_b("BurlapCloth").func_77637_a(TFCTabs.TFC_MATERIALS);
    spindle = (new ItemSpindle()).func_77655_b("Spindle").func_77637_a(TFCTabs.TFC_POTTERY);


    spindleHead = (new ItemPotteryBase()).setMetaNames(new String[] { "Clay Spindle", "Spindle Head" }).func_77655_b("Spindle Head").func_77637_a(TFCTabs.TFC_POTTERY);
    stoneBrick = (new ItemStoneBrick()).setFolder("tools/").func_77655_b("ItemStoneBrick");
    mortar = (new ItemTerra()).setFolder("tools/").func_77655_b("Mortar").func_77637_a(TFCTabs.TFC_MATERIALS);
    vinegar = (new ItemCustomBucket(Blocks.field_150350_a)).setFolder("food/").func_77655_b("Vinegar").func_77642_a(woodenBucketEmpty).func_77637_a(TFCTabs.TFC_FOODS);
    hide = (new ItemRawHide()).setFolder("tools/").func_77655_b("Hide").func_77637_a(TFCTabs.TFC_MATERIALS);
    soakedHide = (new ItemRawHide()).setFolder("tools/").func_77655_b("Soaked Hide").func_77637_a(TFCTabs.TFC_MATERIALS);
    scrapedHide = (new ItemRawHide()).setFolder("tools/").func_77655_b("Scraped Hide").func_77637_a(TFCTabs.TFC_MATERIALS);
    prepHide = (new ItemRawHide()).setFolder("tools/").setFolder("tools/").func_77655_b("Prep Hide").func_77637_a(TFCTabs.TFC_MATERIALS);

    sheepSkin = (new ItemRawHide()).setFolder("tools/").func_77655_b("Sheep Skin").func_77637_a(TFCTabs.TFC_MATERIALS);
    flatLeather = (new ItemFlatGeneric()).setFolder("tools/").func_77655_b("Flat Leather");
    leather = (new ItemLeather()).setSpecialCraftingType(flatLeather).setFolder("tools/").func_77655_b("TFC Leather");

    straw = (new ItemTerra()).setFolder("plants/").func_77655_b("Straw").func_77637_a(TFCTabs.TFC_MATERIALS);

    flatClay = (new ItemFlatGeneric()).setFolder("pottery/").setMetaNames(new String[] { "clay flat light", "clay flat dark", "clay flat fire", "clay flat dark fire" }).func_77655_b("Flat Clay");

    potteryJug = (new ItemPotteryJug()).func_77655_b("Jug");
    potterySmallVessel = (new ItemPotterySmallVessel()).func_77655_b("Small Vessel");

    ceramicMold = (new ItemPotteryBase()).setMetaNames(new String[] { "Clay Mold", "Ceramic Mold" }).func_77655_b("Mold");
    potteryBowl = (new ItemPotteryBase()).setMetaNames(new String[] { "Clay Bowl", "Ceramic Bowl" }).func_77655_b("ClayBowl");
    clayBall = (new ItemClay()).setSpecialCraftingType(flatClay, new ItemStack(flatClay, 1, 1)).setMetaNames(new String[] { "Clay", "Fire Clay" }).func_77655_b("Clay");
    fireBrick = (new ItemPotteryBase()).setMetaNames(new String[] { "Clay Fire Brick", "Fire Brick" }).func_77655_b("Fire Brick");

    paraffin = (Item)((ItemTerra)(new ItemTerra()).func_77655_b("Paraffin").func_77637_a(TFCTabs.TFC_MATERIALS)).setSize(EnumSize.SMALL);


    clayMoldAxe = (new ItemPotteryMold()).setMetaNames(new String[] { "Clay Mold Axe", "Ceramic Mold Axe", "Ceramic Mold Axe Copper", "Ceramic Mold Axe Bronze", "Ceramic Mold Axe Bismuth Bronze", "Ceramic Mold Axe Black Bronze" }).func_77655_b("Axe Mold");

    clayMoldChisel = (new ItemPotteryMold()).setMetaNames(new String[] { "Clay Mold Chisel", "Ceramic Mold Chisel", "Ceramic Mold Chisel Copper", "Ceramic Mold Chisel Bronze", "Ceramic Mold Chisel Bismuth Bronze", "Ceramic Mold Chisel Black Bronze" }).func_77655_b("Chisel Mold");

    clayMoldHammer = (new ItemPotteryMold()).setMetaNames(new String[] { "Clay Mold Hammer", "Ceramic Mold Hammer", "Ceramic Mold Hammer Copper", "Ceramic Mold Hammer Bronze", "Ceramic Mold Hammer Bismuth Bronze", "Ceramic Mold Hammer Black Bronze" }).func_77655_b("Hammer Mold");

    clayMoldHoe = (new ItemPotteryMold()).setMetaNames(new String[] { "Clay Mold Hoe", "Ceramic Mold Hoe", "Ceramic Mold Hoe Copper", "Ceramic Mold Hoe Bronze", "Ceramic Mold Hoe Bismuth Bronze", "Ceramic Mold Hoe Black Bronze" }).func_77655_b("Hoe Mold");

    clayMoldKnife = (new ItemPotteryMold()).setMetaNames(new String[] { "Clay Mold Knife", "Ceramic Mold Knife", "Ceramic Mold Knife Copper", "Ceramic Mold Knife Bronze", "Ceramic Mold Knife Bismuth Bronze", "Ceramic Mold Knife Black Bronze" }).func_77655_b("Knife Mold");

    clayMoldMace = (new ItemPotteryMold()).setMetaNames(new String[] { "Clay Mold Mace", "Ceramic Mold Mace", "Ceramic Mold Mace Copper", "Ceramic Mold Mace Bronze", "Ceramic Mold Mace Bismuth Bronze", "Ceramic Mold Mace Black Bronze" }).func_77655_b("Mace Mold");

    clayMoldPick = (new ItemPotteryMold()).setMetaNames(new String[] { "Clay Mold Pick", "Ceramic Mold Pick", "Ceramic Mold Pick Copper", "Ceramic Mold Pick Bronze", "Ceramic Mold Pick Bismuth Bronze", "Ceramic Mold Pick Black Bronze" }).func_77655_b("Pick Mold");

    clayMoldProPick = (new ItemPotteryMold()).setMetaNames(new String[] { "Clay Mold ProPick", "Ceramic Mold ProPick", "Ceramic Mold ProPick Copper", "Ceramic Mold ProPick Bronze", "Ceramic Mold ProPick Bismuth Bronze", "Ceramic Mold ProPick Black Bronze" }).func_77655_b("ProPick Mold");

    clayMoldSaw = (new ItemPotteryMold()).setMetaNames(new String[] { "Clay Mold Saw", "Ceramic Mold Saw", "Ceramic Mold Saw Copper", "Ceramic Mold Saw Bronze", "Ceramic Mold Saw Bismuth Bronze", "Ceramic Mold Saw Black Bronze" }).func_77655_b("Saw Mold");

    clayMoldScythe = (new ItemPotteryMold()).setMetaNames(new String[] { "Clay Mold Scythe", "Ceramic Mold Scythe", "Ceramic Mold Scythe Copper", "Ceramic Mold Scythe Bronze", "Ceramic Mold Scythe Bismuth Bronze", "Ceramic Mold Scythe Black Bronze" }).func_77655_b("Scythe Mold");

    clayMoldShovel = (new ItemPotteryMold()).setMetaNames(new String[] { "Clay Mold Shovel", "Ceramic Mold Shovel", "Ceramic Mold Shovel Copper", "Ceramic Mold Shovel Bronze", "Ceramic Mold Shovel Bismuth Bronze", "Ceramic Mold Shovel Black Bronze" }).func_77655_b("Shovel Mold");

    clayMoldSword = (new ItemPotteryMold()).setMetaNames(new String[] { "Clay Mold Sword", "Ceramic Mold Sword", "Ceramic Mold Sword Copper", "Ceramic Mold Sword Bronze", "Ceramic Mold Sword Bismuth Bronze", "Ceramic Mold Sword Black Bronze" }).func_77655_b("Sword Mold");

    clayMoldJavelin = (new ItemPotteryMold()).setMetaNames(new String[] { "Clay Mold Javelin", "Ceramic Mold Javelin", "Ceramic Mold Javelin Copper", "Ceramic Mold Javelin Bronze", "Ceramic Mold Javelin Bismuth Bronze", "Ceramic Mold Javelin Black Bronze" }).func_77655_b("Javelin Mold");

    tuyereCopper = (new ItemTuyere(40, 0)).func_77655_b("Copper Tuyere");
    tuyereBronze = (new ItemTuyere(80, 1)).func_77655_b("Bronze Tuyere");
    tuyereBlackBronze = (new ItemTuyere(80, 1)).func_77655_b("Black Bronze Tuyere");
    tuyereBismuthBronze = (new ItemTuyere(80, 1)).func_77655_b("Bismuth Bronze Tuyere");
    tuyereWroughtIron = (new ItemTuyere(120, 2)).func_77655_b("Wrought Iron Tuyere");
    tuyereSteel = (new ItemTuyere(180, 3)).func_77655_b("Steel Tuyere");
    tuyereBlackSteel = (new ItemTuyere(260, 4)).func_77655_b("Black Steel Tuyere");
    tuyereRedSteel = (new ItemTuyere(400, 5)).func_77655_b("Red Steel Tuyere");
    tuyereBlueSteel = (new ItemTuyere(500, 6)).func_77655_b("Blue Steel Tuyere");

    bloom = (new ItemBloom()).setFolder("ingots/").func_77655_b("Iron Bloom");
    rawBloom = (new ItemBloom()).setFolder("ingots/").func_77655_b("Raw Iron Bloom");

    unknownIngot = (new ItemIngot()).func_77655_b("Unknown Ingot");
    unknownUnshaped = (new ItemMeltedMetal()).func_77655_b("Unknown Unshaped");

    jute = (new ItemTerra()).setFolder("plants/").func_77655_b("Jute").func_77637_a(TFCTabs.TFC_MATERIALS);
    juteFiber = (new ItemTerra()).setFolder("plants/").func_77655_b("Jute Fibre").func_77637_a(TFCTabs.TFC_MATERIALS);

    Items.field_151120_aE.func_77637_a(null);
    reeds = (new ItemReeds()).func_77655_b("Reeds").func_77637_a(TFCTabs.TFC_MATERIALS).func_111206_d("reeds");




    setupFood();

    fertilizer = (new ItemFertilizer()).func_77655_b("Fertilizer").func_77637_a(TFCTabs.TFC_MATERIALS);


    setupArmor();

    Recipes.doors = new Item[] { doorOak, doorAspen, doorBirch, doorChestnut, doorDouglasFir, doorHickory, doorMaple, doorAsh, doorPine, doorSequoia, doorSpruce, doorSycamore, doorWhiteCedar, doorWhiteElm, doorWillow, doorKapok, doorAcacia };



    Recipes.axes = new Item[] { sedAxe, igInAxe, igExAxe, mMAxe, bismuthBronzeAxe, blackBronzeAxe, blackSteelAxe, blueSteelAxe, bronzeAxe, copperAxe, wroughtIronAxe, redSteelAxe, steelAxe };




    Recipes.chisels = new Item[] { bismuthBronzeChisel, blackBronzeChisel, blackSteelChisel, blueSteelChisel, bronzeChisel, copperChisel, wroughtIronChisel, redSteelChisel, steelChisel };



    Recipes.saws = new Item[] { bismuthBronzeSaw, blackBronzeSaw, blackSteelSaw, blueSteelSaw, bronzeSaw, copperSaw, wroughtIronSaw, redSteelSaw, steelSaw };



    Recipes.knives = new Item[] { stoneKnife, bismuthBronzeKnife, blackBronzeKnife, blackSteelKnife, blueSteelKnife, bronzeKnife, copperKnife, wroughtIronKnife, redSteelKnife, steelKnife };



    Recipes.meltedMetal = new Item[] { bismuthUnshaped, bismuthBronzeUnshaped, blackBronzeUnshaped, blackSteelUnshaped, blueSteelUnshaped, brassUnshaped, bronzeUnshaped, copperUnshaped, goldUnshaped, wroughtIronUnshaped, leadUnshaped, nickelUnshaped, pigIronUnshaped, platinumUnshaped, redSteelUnshaped, roseGoldUnshaped, silverUnshaped, steelUnshaped, sterlingSilverUnshaped, tinUnshaped, zincUnshaped, highCarbonSteelUnshaped, weakSteelUnshaped, highCarbonBlackSteelUnshaped, highCarbonBlueSteelUnshaped, highCarbonRedSteelUnshaped, weakBlueSteelUnshaped, weakRedSteelUnshaped };







    Recipes.hammers = new Item[] { stoneHammer, bismuthBronzeHammer, blackBronzeHammer, blackSteelHammer, blueSteelHammer, bronzeHammer, copperHammer, wroughtIronHammer, redSteelHammer, steelHammer };



    Recipes.scythes = new Item[] { bismuthBronzeScythe, blackBronzeScythe, blackSteelScythe, blueSteelScythe, bronzeScythe, copperScythe, wroughtIronScythe, redSteelScythe, steelScythe };



    Recipes.picks = new Item[] { bismuthBronzePick, blackBronzePick, blackSteelPick, blueSteelPick, bronzePick, copperPick, wroughtIronPick, redSteelPick, steelPick };



    Recipes.proPicks = new Item[] { proPickBismuthBronze, proPickBlackBronze, proPickBlackSteel, proPickBlueSteel, proPickBronze, proPickCopper, proPickIron, proPickRedSteel, proPickSteel };



    Recipes.shovels = new Item[] { sedShovel, igInShovel, igExShovel, mMShovel, bismuthBronzeShovel, blackBronzeShovel, blackSteelShovel, blueSteelShovel, bronzeShovel, copperShovel, wroughtIronShovel, redSteelShovel, steelShovel };




    Recipes.hoes = new Item[] { sedHoe, igInHoe, igExHoe, mMHoe, bismuthBronzeHoe, blackBronzeHoe, blackSteelHoe, blueSteelHoe, bronzeHoe, copperHoe, wroughtIronHoe, redSteelHoe, steelHoe };




    Recipes.swords = new Item[] { bismuthBronzeSword, blackBronzeSword, blackSteelSword, blueSteelSword, bronzeSword, copperSword, wroughtIronSword, redSteelSword, steelSword };



    Recipes.maces = new Item[] { bismuthBronzeMace, blackBronzeMace, blackSteelMace, blueSteelMace, bronzeMace, copperMace, wroughtIronMace, redSteelMace, steelMace };



    Recipes.javelins = new Item[] { sedStoneJavelin, igInStoneJavelin, igExStoneJavelin, mMStoneJavelin, bismuthBronzeJavelin, blackBronzeJavelin, blackSteelJavelin, blueSteelJavelin, bronzeJavelin, copperJavelin, wroughtIronJavelin, redSteelJavelin, steelJavelin };





    Recipes.spindle = new Item[] { spindle };

    Recipes.gems = new Item[] { gemAgate, gemAmethyst, gemBeryl, gemDiamond, gemEmerald, gemGarnet, gemJade, gemJasper, gemOpal, gemRuby, gemSapphire, gemTopaz, gemTourmaline };


    Recipes.seeds = new Item[] { seedsBarley, seedsCabbage, seedsCarrot, seedsGarlic, seedsGreenbean, seedsJute, seedsMaize, seedsOat, seedsOnion, seedsPotato, seedsRedBellPepper, seedsRice, seedsRye, seedsSoybean, seedsSquash, seedsSugarcane, seedsTomato, seedsWheat, seedsYellowBellPepper };



    ((TFCTabs)TFCTabs.TFC_BUILDING).setTabIconItemStack(new ItemStack(TFCBlocks.stoneSedBrick));
    ((TFCTabs)TFCTabs.TFC_DECORATION).setTabIconItemStack(new ItemStack(TFCBlocks.flora));
    ((TFCTabs)TFCTabs.TFC_DEVICES).setTabIconItem(sluiceItem);
    ((TFCTabs)TFCTabs.TFC_POTTERY).setTabIconItemStack(new ItemStack(potteryJug, 1, 1));
    ((TFCTabs)TFCTabs.TFC_MISC).setTabIconItem(blueSteelBucketLava);
    ((TFCTabs)TFCTabs.TFC_FOODS).setTabIconItem(redApple);
    ((TFCTabs)TFCTabs.TFC_TOOLS).setTabIconItem(redSteelAxe);
    ((TFCTabs)TFCTabs.TFC_WEAPONS).setTabIconItem(bismuthBronzeSword);
    ((TFCTabs)TFCTabs.TFC_ARMOR).setTabIconItem(bronzeHelmet);
    ((TFCTabs)TFCTabs.TFC_MATERIALS).setTabIconItem(blueSteelIngot);

    registerItems();
    registerMetals();

    TerraFirmaCraft.LOG.info("Finished Loading Items");
  }





  private static void setupFood() {
    foodList = new ArrayList();

    egg = (new ItemEgg()).setSize(EnumSize.SMALL).func_77655_b("egg").func_111206_d("egg").func_77637_a(TFCTabs.TFC_FOODS);




    porkchopRaw = (new ItemFoodMeat(EnumFoodGroup.Protein, 0, 0, 0, 0, 40, false, false)).setDecayRate(2.5F).setCanSmoke().setHasCookedIcon().setSmokeAbsorbMultiplier(1.0F).func_77655_b("Porkchop");
    fishRaw = (new ItemFoodMeat(EnumFoodGroup.Protein, 0, 0, 0, 0, 40, false, true)).setDecayRate(2.5F).setCanSmoke().setHasCookedIcon().setSmokeAbsorbMultiplier(1.0F).func_77655_b("Fish");
    beefRaw = (new ItemFoodMeat(EnumFoodGroup.Protein, 0, 0, 0, 0, 50, false, false)).setDecayRate(2.5F).setCanSmoke().setHasCookedIcon().setSmokeAbsorbMultiplier(1.0F).func_77655_b("Beef");
    chickenRaw = (new ItemFoodMeat(EnumFoodGroup.Protein, 0, 0, 0, 0, 40, false, false)).setDecayRate(2.5F).setCanSmoke().setHasCookedIcon().setSmokeAbsorbMultiplier(1.0F).func_77655_b("Chicken");
    soybean = (new ItemFoodTFC(EnumFoodGroup.Protein, 10, 0, 0, 0, 40, true)).func_77655_b("Soybeans");
    eggCooked = (new ItemFoodTFC(EnumFoodGroup.Protein, 0, 0, 0, 0, 25)).setDecayRate(2.5F).func_77655_b("Egg Cooked");
    calamariRaw = (new ItemFoodMeat(EnumFoodGroup.Protein, 0, 0, 20, 0, 35, false, false)).setCanSmoke().setHasCookedIcon().setSmokeAbsorbMultiplier(1.0F).setDecayRate(4.0F).func_77655_b("Calamari");
    muttonRaw = (new ItemFoodMeat(EnumFoodGroup.Protein, 0, 0, 0, 0, 40, false, false)).setDecayRate(2.5F).setCanSmoke().setHasCookedIcon().setSmokeAbsorbMultiplier(1.0F).func_77655_b("Mutton");
    venisonRaw = (new ItemFoodMeat(EnumFoodGroup.Protein, 5, 0, 0, 0, 50, false, false)).setDecayRate(2.5F).setCanSmoke().setHasCookedIcon().setSmokeAbsorbMultiplier(1.0F).func_77655_b("Venison");
    horseMeatRaw = (new ItemFoodMeat(EnumFoodGroup.Protein, 0, 0, 0, 0, 40, false, false)).setDecayRate(2.5F).setCanSmoke().setHasCookedIcon().setSmokeAbsorbMultiplier(1.0F).func_77655_b("HorseMeat");


    cheese = (new ItemFoodTFC(EnumFoodGroup.Dairy, 0, 10, 20, 0, 35)).setDecayRate(0.5F).setCanSmoke().setSmokeAbsorbMultiplier(1.0F).func_77655_b("Cheese");


    wheatGrain = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 5, 20)).setDecayRate(0.5F).func_77655_b("Wheat Grain");
    barleyGrain = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 10, 20)).setDecayRate(0.5F).func_77655_b("Barley Grain");
    oatGrain = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 5, 20)).setDecayRate(0.5F).func_77655_b("Oat Grain");
    ryeGrain = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 15, 0, 5, 20)).setDecayRate(0.5F).func_77655_b("Rye Grain");
    riceGrain = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 5, 20)).setDecayRate(0.5F).func_77655_b("Rice Grain");
    maizeEar = (new ItemFoodTFC(EnumFoodGroup.Grain, 25, 0, 0, 5, 20, true)).func_77655_b("Maize Ear");

    wheatWhole = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 5, 20, false, false)).setFolder("food/").func_77655_b("Wheat Whole");
    barleyWhole = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 10, 20, false, false)).setFolder("food/").func_77655_b("Barley Whole");
    oatWhole = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 5, 20, false, false)).setFolder("food/").func_77655_b("Oat Whole");
    ryeWhole = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 15, 0, 5, 20, false, false)).setFolder("food/").func_77655_b("Rye Whole");
    riceWhole = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 5, 20, false, false)).setFolder("food/").func_77655_b("Rice Whole");

    wheatGround = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 0, 20, false, false)).setFolder("food/").func_77655_b("Wheat Ground");
    barleyGround = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 5, 20, false, false)).setFolder("food/").func_77655_b("Barley Ground");
    oatGround = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 0, 20, false, false)).setFolder("food/").func_77655_b("Oat Ground");
    ryeGround = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 15, 0, 0, 20, false, false)).setFolder("food/").func_77655_b("Rye Ground");
    riceGround = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 0, 20, false, false)).setFolder("food/").func_77655_b("Rice Ground");
    cornmealGround = (new ItemFoodTFC(EnumFoodGroup.Grain, 25, 0, 0, 0, 20, false, false)).setFolder("food/").func_77655_b("Cornmeal Ground");

    wheatDough = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 0, 20, false, false)).func_77655_b("Wheat Dough");
    barleyDough = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 5, 20, false, false)).func_77655_b("Barley Dough");
    oatDough = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 0, 20, false, false)).func_77655_b("Oat Dough");
    ryeDough = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 15, 0, 0, 20, false, false)).func_77655_b("Rye Dough");
    riceDough = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 0, 20, false, false)).func_77655_b("Rice Dough");
    cornmealDough = (new ItemFoodTFC(EnumFoodGroup.Grain, 25, 0, 0, 0, 20, false, false)).func_77655_b("Cornmeal Dough");

    wheatBread = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 0, 20)).func_77655_b("Wheat Bread");
    barleyBread = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 5, 20)).func_77655_b("Barley Bread");
    oatBread = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 0, 20)).func_77655_b("Oat Bread");
    ryeBread = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 15, 0, 0, 20)).func_77655_b("Rye Bread");
    riceBread = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 0, 20)).func_77655_b("Rice Bread");
    cornBread = (new ItemFoodTFC(EnumFoodGroup.Grain, 25, 0, 0, 0, 20)).func_77655_b("Corn Bread");


    tomato = (new ItemFoodTFC(EnumFoodGroup.Vegetable, 30, 5, 0, 0, 50, true)).func_77655_b("Tomato");
    potato = (new ItemFoodTFC(EnumFoodGroup.Vegetable, 0, 0, 10, 15, 20, true)).func_77655_b("Potato");



















    onion = (new ItemFoodTFC(EnumFoodGroup.Vegetable, 10, 25, 0, 0, 20, true) { public void func_94581_a(IIconRegister registerer) { super.func_94581_a(registerer); this.field_77787_bX = true; this.metaIcons = new IIcon[2]; this.metaIcons[0] = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + func_77658_a().replace("item.", "")); this.metaIcons[1] = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + "Rutabaga"); } public IIcon func_77617_a(int i) { if (i == 1) return this.metaIcons[1];  return super.func_77617_a(i); } }).func_77655_b(TFCOptions.onionsAreGross ? "Rutabaga" : "Onion");
    cabbage = (new ItemFoodTFC(EnumFoodGroup.Vegetable, 10, 0, 0, 0, 30, true)).func_77655_b("Cabbage");
    garlic = (new ItemFoodTFC(EnumFoodGroup.Vegetable, 0, 0, 0, 10, 20, true)).func_77655_b("Garlic");
    carrot = (new ItemFoodTFC(EnumFoodGroup.Vegetable, 20, 0, 0, 0, 20, true)).func_77655_b("Carrot");
    greenbeans = (new ItemFoodTFC(EnumFoodGroup.Vegetable, 20, 0, 0, 0, 20, true)).func_77655_b("Greenbeans");
    greenBellPepper = (new ItemFoodTFC(EnumFoodGroup.Vegetable, 10, 0, 0, 0, 20, true)).func_77655_b("Green Bell Pepper");
    yellowBellPepper = (new ItemFoodTFC(EnumFoodGroup.Vegetable, 15, 0, 0, 0, 20, true)).func_77655_b("Yellow Bell Pepper");
    redBellPepper = (new ItemFoodTFC(EnumFoodGroup.Vegetable, 20, 0, 0, 0, 20, true)).func_77655_b("Red Bell Pepper");
    squash = (new ItemFoodTFC(EnumFoodGroup.Vegetable, 20, 0, 0, 0, 20, true)).func_77655_b("Squash");
    seaWeed = (new ItemFoodTFC(EnumFoodGroup.Vegetable, 0, 0, 10, 10, 10, true)).func_77655_b("Sea Weed");
    sugar = (new ItemFoodTFC(EnumFoodGroup.None, 30, 0, 0, 0, 0, true)).setDecayRate(0.01F).func_77655_b("Sugar");




















    honeycomb = (new ItemFoodTFC(EnumFoodGroup.None, 50, 0, 0, 0, 0, true, true) { public ItemStack func_77654_b(ItemStack is, World world, EntityPlayer player) { super.func_77654_b(is, world, player); if (!world.field_72995_K && isEdible(is)) if (is.func_77942_o()) { float eatAmount = getFoodMaxWeight(is) / 3.0F; if (FoodStatsTFC.reduceFood(is, eatAmount)) is.field_77994_a = 0;  }   return is; } }).setDecayRate(0.01F).setSize(EnumSize.MEDIUM).func_77655_b("Honeycomb");


    redApple = (new ItemFoodTFC(EnumFoodGroup.Fruit, 25, 5, 0, 10, 0, true)).setDecayRate(2.0F).func_77655_b(Global.FRUIT_META_NAMES[0]);
    banana = (new ItemFoodTFC(EnumFoodGroup.Fruit, 30, 5, 0, 0, 0, true)).setDecayRate(2.0F).func_77655_b(Global.FRUIT_META_NAMES[1]);
    orange = (new ItemFoodTFC(EnumFoodGroup.Fruit, 50, 30, 0, 10, 0, true)).setDecayRate(2.0F).func_77655_b(Global.FRUIT_META_NAMES[2]);
    greenApple = (new ItemFoodTFC(EnumFoodGroup.Fruit, 30, 15, 0, 10, 0, true)).setDecayRate(2.0F).func_77655_b(Global.FRUIT_META_NAMES[3]);
    lemon = (new ItemFoodTFC(EnumFoodGroup.Fruit, 30, 50, 0, 10, 0, true)).setDecayRate(2.0F).func_77655_b(Global.FRUIT_META_NAMES[4]);
    olive = (new ItemFoodTFC(EnumFoodGroup.Fruit, 10, 0, 3, 10, 0, true)).setDecayRate(2.0F).func_77655_b(Global.FRUIT_META_NAMES[5]);
    cherry = (new ItemFoodTFC(EnumFoodGroup.Fruit, 30, 5, 0, 0, 0, true)).setDecayRate(2.0F).func_77655_b(Global.FRUIT_META_NAMES[6]);
    peach = (new ItemFoodTFC(EnumFoodGroup.Fruit, 25, 10, 0, 0, 0, true)).setDecayRate(2.0F).func_77655_b(Global.FRUIT_META_NAMES[7]);
    plum = (new ItemFoodTFC(EnumFoodGroup.Fruit, 20, 15, 0, 0, 0, true)).setDecayRate(2.0F).func_77655_b(Global.FRUIT_META_NAMES[8]);

    wintergreenBerry = (new ItemFoodTFC(EnumFoodGroup.Fruit, 30, 0, 0, 20, 0)).setDecayRate(2.0F).func_77655_b("Wintergreen Berry");
    blueberry = (new ItemFoodTFC(EnumFoodGroup.Fruit, 30, 20, 0, 0, 0)).setDecayRate(2.0F).func_77655_b("Blueberry");
    raspberry = (new ItemFoodTFC(EnumFoodGroup.Fruit, 35, 15, 0, 5, 0)).setDecayRate(2.0F).func_77655_b("Raspberry");
    strawberry = (new ItemFoodTFC(EnumFoodGroup.Fruit, 30, 5, 0, 5, 0)).setDecayRate(2.0F).func_77655_b("Strawberry");
    blackberry = (new ItemFoodTFC(EnumFoodGroup.Fruit, 30, 30, 0, 0, 0)).setDecayRate(2.0F).func_77655_b("Blackberry");
    bunchberry = (new ItemFoodTFC(EnumFoodGroup.Fruit, 20, 5, 0, 0, 0)).setDecayRate(2.0F).func_77655_b("Bunchberry");
    cranberry = (new ItemFoodTFC(EnumFoodGroup.Fruit, 30, 5, 0, 45, 0)).setDecayRate(2.0F).func_77655_b("Cranberry");
    snowberry = (new ItemFoodTFC(EnumFoodGroup.Fruit, 10, 0, 0, 90, 0)).setDecayRate(2.0F).func_77655_b("Snowberry");
    elderberry = (new ItemFoodTFC(EnumFoodGroup.Fruit, 20, 40, 0, 10, 0)).setDecayRate(2.0F).func_77655_b("Elderberry");
    gooseberry = (new ItemFoodTFC(EnumFoodGroup.Fruit, 20, 40, 0, 0, 0)).setDecayRate(2.0F).func_77655_b("Gooseberry");
    cloudberry = (new ItemFoodTFC(EnumFoodGroup.Fruit, 40, 40, 0, 30, 0)).setDecayRate(2.0F).func_77655_b("Cloudberry");

    sandwich = (new ItemSandwich()).func_77655_b("Sandwich");
    salad = (new ItemSalad()).func_77655_b("Salad");


    sugarcane = (new ItemFoodTFC(EnumFoodGroup.None, 30, 0, 0, 0, 0, false, false)).setDecayRate(0.75F).setFolder("plants/").func_77655_b("Sugarcane");


    seedsWheat = (new ItemCustomSeeds(0)).func_77655_b("Seeds Wheat");
    seedsMaize = (new ItemCustomSeeds(1)).func_77655_b("Seeds Maize");
    seedsTomato = (new ItemCustomSeeds(2)).func_77655_b("Seeds Tomato");
    seedsBarley = (new ItemCustomSeeds(3)).func_77655_b("Seeds Barley");
    seedsRye = (new ItemCustomSeeds(4)).func_77655_b("Seeds Rye");
    seedsOat = (new ItemCustomSeeds(5)).func_77655_b("Seeds Oat");
    seedsRice = (new ItemCustomSeeds(6)).func_77655_b("Seeds Rice");
    seedsPotato = (new ItemCustomSeeds(7)).func_77655_b("Seeds Potato");
    seedsOnion = (new ItemCustomSeeds(8)).func_77655_b(TFCOptions.onionsAreGross ? "Seeds Rutabaga" : "Seeds Onion");
    seedsCabbage = (new ItemCustomSeeds(9)).func_77655_b("Seeds Cabbage");
    seedsGarlic = (new ItemCustomSeeds(10)).func_77655_b("Seeds Garlic");
    seedsCarrot = (new ItemCustomSeeds(11)).func_77655_b("Seeds Carrot");
    seedsYellowBellPepper = (new ItemCustomSeeds(12)).func_77655_b("Seeds Yellow Bell Pepper");
    seedsRedBellPepper = (new ItemCustomSeeds(13)).func_77655_b("Seeds Red Bell Pepper");
    seedsSoybean = (new ItemCustomSeeds(14)).func_77655_b("Seeds Soybean");
    seedsGreenbean = (new ItemCustomSeeds(15)).func_77655_b("Seeds Greenbean");
    seedsSquash = (new ItemCustomSeeds(16)).func_77655_b("Seeds Squash");
    seedsJute = (new ItemCustomSeeds(17)).func_77655_b("Seeds Jute");
    seedsSugarcane = (new ItemCustomSeeds(18)).func_77655_b("Seeds Sugarcane");




    fruitTreeSapling = (new ItemFruitTreeSapling()).func_77655_b("FruitSapling");
  }

















  private static void registerMetals() {
    Global.BISMUTH = new Metal("Bismuth", TFCItems.bismuthUnshaped, TFCItems.bismuthIngot);
    Global.BISMUTHBRONZE = new Metal("Bismuth Bronze", TFCItems.bismuthBronzeUnshaped, TFCItems.bismuthBronzeIngot);
    Global.BLACKBRONZE = new Metal("Black Bronze", TFCItems.blackBronzeUnshaped, TFCItems.blackBronzeIngot);
    Global.BLACKSTEEL = new Metal("Black Steel", TFCItems.blackSteelUnshaped, TFCItems.blackSteelIngot);
    Global.BLUESTEEL = new Metal("Blue Steel", TFCItems.blueSteelUnshaped, TFCItems.blueSteelIngot);
    Global.BRASS = new Metal("Brass", TFCItems.brassUnshaped, TFCItems.brassIngot);
    Global.BRONZE = new Metal("Bronze", TFCItems.bronzeUnshaped, TFCItems.bronzeIngot);
    Global.COPPER = new Metal("Copper", TFCItems.copperUnshaped, TFCItems.copperIngot);
    Global.GOLD = new Metal("Gold", TFCItems.goldUnshaped, TFCItems.goldIngot);
    Global.WROUGHTIRON = new Metal("Wrought Iron", TFCItems.wroughtIronUnshaped, TFCItems.wroughtIronIngot);
    Global.LEAD = new Metal("Lead", TFCItems.leadUnshaped, TFCItems.leadIngot);
    Global.NICKEL = new Metal("Nickel", TFCItems.nickelUnshaped, TFCItems.nickelIngot);
    Global.PIGIRON = new Metal("Pig Iron", TFCItems.pigIronUnshaped, TFCItems.pigIronIngot);
    Global.PLATINUM = new Metal("Platinum", TFCItems.platinumUnshaped, TFCItems.platinumIngot);
    Global.REDSTEEL = new Metal("Red Steel", TFCItems.redSteelUnshaped, TFCItems.redSteelIngot);
    Global.ROSEGOLD = new Metal("Rose Gold", TFCItems.roseGoldUnshaped, TFCItems.roseGoldIngot);
    Global.SILVER = new Metal("Silver", TFCItems.silverUnshaped, TFCItems.silverIngot);
    Global.STEEL = new Metal("Steel", TFCItems.steelUnshaped, TFCItems.steelIngot);
    Global.STERLINGSILVER = new Metal("Sterling Silver", TFCItems.sterlingSilverUnshaped, TFCItems.sterlingSilverIngot);
    Global.TIN = new Metal("Tin", TFCItems.tinUnshaped, TFCItems.tinIngot);
    Global.ZINC = new Metal("Zinc", TFCItems.zincUnshaped, TFCItems.zincIngot);
    Global.WEAKSTEEL = new Metal("Weak Steel", TFCItems.weakSteelUnshaped, TFCItems.weakSteelIngot);
    Global.HCBLACKSTEEL = new Metal("HC Black Steel", TFCItems.highCarbonBlackSteelUnshaped, TFCItems.highCarbonBlackSteelIngot);
    Global.WEAKREDSTEEL = new Metal("Weak Red Steel", TFCItems.weakRedSteelUnshaped, TFCItems.weakRedSteelIngot);
    Global.HCREDSTEEL = new Metal("HC Red Steel", TFCItems.highCarbonRedSteelUnshaped, TFCItems.highCarbonRedSteelIngot);
    Global.WEAKBLUESTEEL = new Metal("Weak Blue Steel", TFCItems.weakBlueSteelUnshaped, TFCItems.weakBlueSteelIngot);
    Global.HCBLUESTEEL = new Metal("HC Blue Steel", TFCItems.highCarbonBlueSteelUnshaped, TFCItems.highCarbonBlueSteelIngot);
    Global.UNKNOWN = new Metal("Unknown", TFCItems.unknownUnshaped, TFCItems.unknownIngot, false);

    MetalRegistry.instance.addMetal(Global.BISMUTH, Alloy.EnumTier.TierI);
    MetalRegistry.instance.addMetal(Global.BISMUTHBRONZE, Alloy.EnumTier.TierI);
    MetalRegistry.instance.addMetal(Global.BLACKBRONZE, Alloy.EnumTier.TierI);
    MetalRegistry.instance.addMetal(Global.BLACKSTEEL, Alloy.EnumTier.TierV);
    MetalRegistry.instance.addMetal(Global.BLUESTEEL, Alloy.EnumTier.TierV);
    MetalRegistry.instance.addMetal(Global.BRASS, Alloy.EnumTier.TierI);
    MetalRegistry.instance.addMetal(Global.BRONZE, Alloy.EnumTier.TierI);
    MetalRegistry.instance.addMetal(Global.COPPER, Alloy.EnumTier.TierI);
    MetalRegistry.instance.addMetal(Global.GOLD, Alloy.EnumTier.TierI);
    MetalRegistry.instance.addMetal(Global.WROUGHTIRON, Alloy.EnumTier.TierIII);
    MetalRegistry.instance.addMetal(Global.LEAD, Alloy.EnumTier.TierI);
    MetalRegistry.instance.addMetal(Global.NICKEL, Alloy.EnumTier.TierI);
    MetalRegistry.instance.addMetal(Global.PIGIRON, Alloy.EnumTier.TierIV);
    MetalRegistry.instance.addMetal(Global.PLATINUM, Alloy.EnumTier.TierV);
    MetalRegistry.instance.addMetal(Global.REDSTEEL, Alloy.EnumTier.TierV);
    MetalRegistry.instance.addMetal(Global.ROSEGOLD, Alloy.EnumTier.TierI);
    MetalRegistry.instance.addMetal(Global.SILVER, Alloy.EnumTier.TierI);
    MetalRegistry.instance.addMetal(Global.STEEL, Alloy.EnumTier.TierIV);
    MetalRegistry.instance.addMetal(Global.STERLINGSILVER, Alloy.EnumTier.TierI);
    MetalRegistry.instance.addMetal(Global.TIN, Alloy.EnumTier.TierI);
    MetalRegistry.instance.addMetal(Global.ZINC, Alloy.EnumTier.TierI);
    MetalRegistry.instance.addMetal(Global.WEAKSTEEL, Alloy.EnumTier.TierV);
    MetalRegistry.instance.addMetal(Global.HCBLACKSTEEL, Alloy.EnumTier.TierV);
    MetalRegistry.instance.addMetal(Global.WEAKREDSTEEL, Alloy.EnumTier.TierV);
    MetalRegistry.instance.addMetal(Global.HCREDSTEEL, Alloy.EnumTier.TierV);
    MetalRegistry.instance.addMetal(Global.WEAKBLUESTEEL, Alloy.EnumTier.TierV);
    MetalRegistry.instance.addMetal(Global.HCBLUESTEEL, Alloy.EnumTier.TierV);
    MetalRegistry.instance.addMetal(Global.UNKNOWN, Alloy.EnumTier.TierI);




    Alloy bronze = new Alloy(Global.BRONZE, Alloy.EnumTier.TierI);
    bronze.addIngred(Global.COPPER, 87.99F, 92.01F);
    bronze.addIngred(Global.TIN, 7.99F, 12.01F);
    AlloyManager.INSTANCE.addAlloy(bronze);

    Alloy brass = new Alloy(Global.BRASS, Alloy.EnumTier.TierI);
    brass.addIngred(Global.COPPER, 87.99F, 92.01F);
    brass.addIngred(Global.ZINC, 7.99F, 12.01F);
    AlloyManager.INSTANCE.addAlloy(brass);

    Alloy roseGold = new Alloy(Global.ROSEGOLD, Alloy.EnumTier.TierI);
    roseGold.addIngred(Global.GOLD, 69.99F, 85.01F);
    roseGold.addIngred(Global.COPPER, 14.99F, 30.01F);
    AlloyManager.INSTANCE.addAlloy(roseGold);

    Alloy blackBronze = new Alloy(Global.BLACKBRONZE, Alloy.EnumTier.TierI);
    blackBronze.addIngred(Global.GOLD, 9.99F, 25.01F);
    blackBronze.addIngred(Global.COPPER, 49.99F, 70.01F);
    blackBronze.addIngred(Global.SILVER, 9.99F, 25.01F);
    AlloyManager.INSTANCE.addAlloy(blackBronze);

    Alloy bismuthBronze = new Alloy(Global.BISMUTHBRONZE, Alloy.EnumTier.TierI);
    bismuthBronze.addIngred(Global.ZINC, 19.99F, 30.01F);
    bismuthBronze.addIngred(Global.COPPER, 49.99F, 65.01F);
    bismuthBronze.addIngred(Global.BISMUTH, 9.99F, 20.01F);
    AlloyManager.INSTANCE.addAlloy(bismuthBronze);

    Alloy sterlingSilver = new Alloy(Global.STERLINGSILVER, Alloy.EnumTier.TierI);
    sterlingSilver.addIngred(Global.SILVER, 59.99F, 80.01F);
    sterlingSilver.addIngred(Global.COPPER, 19.99F, 40.01F);
    AlloyManager.INSTANCE.addAlloy(sterlingSilver);

    Alloy weakSteel = new Alloy(Global.WEAKSTEEL, Alloy.EnumTier.TierIII);
    weakSteel.addIngred(Global.STEEL, 49.99F, 70.01F);
    weakSteel.addIngred(Global.NICKEL, 14.99F, 25.01F);
    weakSteel.addIngred(Global.BLACKBRONZE, 14.99F, 25.01F);
    AlloyManager.INSTANCE.addAlloy(weakSteel);

    Alloy weakRedSteel = new Alloy(Global.WEAKREDSTEEL, Alloy.EnumTier.TierIII);
    weakRedSteel.addIngred(Global.BLACKSTEEL, 49.99F, 55.01F);
    weakRedSteel.addIngred(Global.ROSEGOLD, 9.99F, 15.01F);
    weakRedSteel.addIngred(Global.BRASS, 9.99F, 15.01F);
    weakRedSteel.addIngred(Global.STEEL, 19.99F, 25.01F);
    AlloyManager.INSTANCE.addAlloy(weakRedSteel);

    Alloy weakBlueSteel = new Alloy(Global.WEAKBLUESTEEL, Alloy.EnumTier.TierIII);
    weakBlueSteel.addIngred(Global.BLACKSTEEL, 49.99F, 55.01F);
    weakBlueSteel.addIngred(Global.BISMUTHBRONZE, 9.99F, 15.01F);
    weakBlueSteel.addIngred(Global.STERLINGSILVER, 9.99F, 15.01F);
    weakBlueSteel.addIngred(Global.STEEL, 19.99F, 25.01F);
    AlloyManager.INSTANCE.addAlloy(weakBlueSteel);
  }


  public static void setupArmor() {
    String[] names = { "Bismuth Bronze", "Black Bronze", "Black Steel", "Blue Steel", "Bronze", "Copper", "Wrought Iron", "Red Steel", "Steel" };
    String[] namesNSO = { "Brass", "Gold", "Lead", "Nickel", "Pig Iron", "Platinum", "Silver", "Sterling Silver" };
    CommonProxy proxy = TerraFirmaCraft.proxy;
    int i = 0;

    bismuthSheet = (Item)((ItemMetalSheet)(new ItemMetalSheet(0)).func_77655_b("Bismuth Sheet")).setMetal("Bismuth", 200);
    bismuthBronzeSheet = (Item)((ItemMetalSheet)(new ItemMetalSheet(1)).func_77655_b("Bismuth Bronze Sheet")).setMetal("Bismuth Bronze", 200);
    blackBronzeSheet = (Item)((ItemMetalSheet)(new ItemMetalSheet(2)).func_77655_b("Black Bronze Sheet")).setMetal("Black Bronze", 200);
    blackSteelSheet = (Item)((ItemMetalSheet)(new ItemMetalSheet(3)).func_77655_b("Black Steel Sheet")).setMetal("Black Steel", 200);
    blueSteelSheet = (Item)((ItemMetalSheet)(new ItemMetalSheet(4)).func_77655_b("Blue Steel Sheet")).setMetal("Blue Steel", 200);
    bronzeSheet = (Item)((ItemMetalSheet)(new ItemMetalSheet(6)).func_77655_b("Bronze Sheet")).setMetal("Bronze", 200);
    copperSheet = (Item)((ItemMetalSheet)(new ItemMetalSheet(7)).func_77655_b("Copper Sheet")).setMetal("Copper", 200);
    wroughtIronSheet = (Item)((ItemMetalSheet)(new ItemMetalSheet(9)).func_77655_b("Wrought Iron Sheet")).setMetal("Wrought Iron", 200);
    redSteelSheet = (Item)((ItemMetalSheet)(new ItemMetalSheet(14)).func_77655_b("Red Steel Sheet")).setMetal("Red Steel", 200);
    roseGoldSheet = (Item)((ItemMetalSheet)(new ItemMetalSheet(15)).func_77655_b("Rose Gold Sheet")).setMetal("Rose Gold", 200);
    steelSheet = (Item)((ItemMetalSheet)(new ItemMetalSheet(17)).func_77655_b("Steel Sheet")).setMetal("Steel", 200);
    tinSheet = (Item)((ItemMetalSheet)(new ItemMetalSheet(19)).func_77655_b("Tin Sheet")).setMetal("Tin", 200);
    zincSheet = (Item)((ItemMetalSheet)(new ItemMetalSheet(20)).func_77655_b("Zinc Sheet")).setMetal("Zinc", 200);

    bismuthSheet2x = (Item)((ItemMetalSheet2x)(new ItemMetalSheet2x(0)).func_77655_b("Bismuth Double Sheet")).setMetal("Bismuth", 400);
    bismuthBronzeSheet2x = (Item)((ItemMetalSheet2x)(new ItemMetalSheet2x(1)).func_77655_b("Bismuth Bronze Double Sheet")).setMetal("Bismuth Bronze", 400);
    blackBronzeSheet2x = (Item)((ItemMetalSheet2x)(new ItemMetalSheet2x(2)).func_77655_b("Black Bronze Double Sheet")).setMetal("Black Bronze", 400);
    blackSteelSheet2x = (Item)((ItemMetalSheet2x)(new ItemMetalSheet2x(3)).func_77655_b("Black Steel Double Sheet")).setMetal("Black Steel", 400);
    blueSteelSheet2x = (Item)((ItemMetalSheet2x)(new ItemMetalSheet2x(4)).func_77655_b("Blue Steel Double Sheet")).setMetal("Blue Steel", 400);
    bronzeSheet2x = (Item)((ItemMetalSheet2x)(new ItemMetalSheet2x(6)).func_77655_b("Bronze Double Sheet")).setMetal("Bronze", 400);
    copperSheet2x = (Item)((ItemMetalSheet2x)(new ItemMetalSheet2x(7)).func_77655_b("Copper Double Sheet")).setMetal("Copper", 400);
    wroughtIronSheet2x = (Item)((ItemMetalSheet2x)(new ItemMetalSheet2x(9)).func_77655_b("Wrought Iron Double Sheet")).setMetal("Wrought Iron", 400);
    redSteelSheet2x = (Item)((ItemMetalSheet2x)(new ItemMetalSheet2x(14)).func_77655_b("Red Steel Double Sheet")).setMetal("Red Steel", 400);
    roseGoldSheet2x = (Item)((ItemMetalSheet2x)(new ItemMetalSheet2x(15)).func_77655_b("Rose Gold Double Sheet")).setMetal("Rose Gold", 400);
    steelSheet2x = (Item)((ItemMetalSheet2x)(new ItemMetalSheet2x(17)).func_77655_b("Steel Double Sheet")).setMetal("Steel", 400);
    tinSheet2x = (Item)((ItemMetalSheet2x)(new ItemMetalSheet2x(19)).func_77655_b("Tin Double Sheet")).setMetal("Tin", 400);
    zincSheet2x = (Item)((ItemMetalSheet2x)(new ItemMetalSheet2x(20)).func_77655_b("Zinc Double Sheet")).setMetal("Zinc", 400);

    i = 0;
    brassSheet = (new ItemMetalSheet(5)).setMetal("Brass", 200).func_77655_b(namesNSO[i++] + " Sheet");
    goldSheet = (new ItemMetalSheet(8)).setMetal("Gold", 200).func_77655_b(namesNSO[i++] + " Sheet");
    leadSheet = (new ItemMetalSheet(10)).setMetal("Lead", 200).func_77655_b(namesNSO[i++] + " Sheet");
    nickelSheet = (new ItemMetalSheet(11)).setMetal("Nickel", 200).func_77655_b(namesNSO[i++] + " Sheet");
    pigIronSheet = (new ItemMetalSheet(12)).setMetal("Pig Iron", 200).func_77655_b(namesNSO[i++] + " Sheet");
    platinumSheet = (new ItemMetalSheet(13)).setMetal("Platinum", 200).func_77655_b(namesNSO[i++] + " Sheet");
    silverSheet = (new ItemMetalSheet(16)).setMetal("Silver", 200).func_77655_b(namesNSO[i++] + " Sheet");
    sterlingSilverSheet = (new ItemMetalSheet(18)).setMetal("Sterling Silver", 200).func_77655_b(namesNSO[i++] + " Sheet");

    i = 0;
    brassSheet2x = (new ItemMetalSheet2x(5)).setMetal("Brass", 400).func_77655_b(namesNSO[i++] + " Double Sheet");
    goldSheet2x = (new ItemMetalSheet2x(8)).setMetal("Gold", 400).func_77655_b(namesNSO[i++] + " Double Sheet");
    leadSheet2x = (new ItemMetalSheet2x(10)).setMetal("Lead", 400).func_77655_b(namesNSO[i++] + " Double Sheet");
    nickelSheet2x = (new ItemMetalSheet2x(1)).setMetal("Nickel", 400).func_77655_b(namesNSO[i++] + " Double Sheet");
    pigIronSheet2x = (new ItemMetalSheet2x(12)).setMetal("Pig Iron", 400).func_77655_b(namesNSO[i++] + " Double Sheet");
    platinumSheet2x = (new ItemMetalSheet2x(13)).setMetal("Platinum", 400).func_77655_b(namesNSO[i++] + " Double Sheet");
    silverSheet2x = (new ItemMetalSheet2x(16)).setMetal("Silver", 400).func_77655_b(namesNSO[i++] + " Double Sheet");
    sterlingSilverSheet2x = (new ItemMetalSheet2x(18)).setMetal("Sterling Silver", 400).func_77655_b(namesNSO[i++] + " Double Sheet");

    i = 0;
    bismuthBronzeUnfinishedBoots = (new ItemUnfinishedArmor()).setMetal("Bismuth Bronze", 3).func_77655_b(names[i] + " Unfinished Boots"); i++;
    blackBronzeUnfinishedBoots = (new ItemUnfinishedArmor()).setMetal("Black Bronze", 3).func_77655_b(names[i] + " Unfinished Boots"); i++;
    blackSteelUnfinishedBoots = (new ItemUnfinishedArmor()).setMetal("Black Steel", 3).func_77655_b(names[i] + " Unfinished Boots"); i++;
    blueSteelUnfinishedBoots = (new ItemUnfinishedArmor()).setMetal("Blue Steel", 3).func_77655_b(names[i] + " Unfinished Boots"); i++;
    bronzeUnfinishedBoots = (new ItemUnfinishedArmor()).setMetal("Bronze", 3).func_77655_b(names[i] + " Unfinished Boots"); i++;
    copperUnfinishedBoots = (new ItemUnfinishedArmor()).setMetal("Copper", 3).func_77655_b(names[i] + " Unfinished Boots"); i++;
    wroughtIronUnfinishedBoots = (new ItemUnfinishedArmor()).setMetal("Wrought Iron", 3).func_77655_b(names[i] + " Unfinished Boots"); i++;
    redSteelUnfinishedBoots = (new ItemUnfinishedArmor()).setMetal("Red Steel", 3).func_77655_b(names[i] + " Unfinished Boots"); i++;
    steelUnfinishedBoots = (new ItemUnfinishedArmor()).setMetal("Steel", 3).func_77655_b(names[i] + " Unfinished Boots");

    i = 0;
    bismuthBronzeBoots = (new ItemTFCArmor(Armor.bismuthBronzePlate, proxy.getArmorRenderID("bismuthbronze"), 3, 50, 0)).func_77655_b(names[i] + " Boots"); i++;
    blackBronzeBoots = (new ItemTFCArmor(Armor.blackBronzePlate, proxy.getArmorRenderID("blackbronze"), 3, 50, 0)).func_77655_b(names[i] + " Boots"); i++;
    blackSteelBoots = (new ItemTFCArmor(Armor.blackSteelPlate, proxy.getArmorRenderID("blacksteel"), 3, 50, 0)).func_77655_b(names[i] + " Boots"); i++;
    blueSteelBoots = (new ItemTFCArmor(Armor.blueSteelPlate, proxy.getArmorRenderID("bluesteel"), 3, 50, 0)).func_77655_b(names[i] + " Boots"); i++;
    bronzeBoots = (new ItemTFCArmor(Armor.bronzePlate, proxy.getArmorRenderID("bronze"), 3, 50, 0)).func_77655_b(names[i] + " Boots"); i++;
    copperBoots = (new ItemTFCArmor(Armor.copperPlate, proxy.getArmorRenderID("copper"), 3, 50, 0)).func_77655_b(names[i] + " Boots"); i++;
    wroughtIronBoots = (new ItemTFCArmor(Armor.wroughtIronPlate, proxy.getArmorRenderID("wroughtiron"), 3, 50, 0)).func_77655_b(names[i] + " Boots"); i++;
    redSteelBoots = (new ItemTFCArmor(Armor.redSteelPlate, proxy.getArmorRenderID("redsteel"), 3, 50, 0)).func_77655_b(names[i] + " Boots"); i++;
    steelBoots = (new ItemTFCArmor(Armor.steelPlate, proxy.getArmorRenderID("steel"), 3, 50, 0)).func_77655_b(names[i] + " Boots");

    i = 0;
    bismuthBronzeUnfinishedGreaves = (new ItemUnfinishedArmor()).setMetal("Bismuth Bronze", 2).func_77655_b(names[i] + " Unfinished Greaves"); i++;
    blackBronzeUnfinishedGreaves = (new ItemUnfinishedArmor()).setMetal("Black Bronze", 2).func_77655_b(names[i] + " Unfinished Greaves"); i++;
    blackSteelUnfinishedGreaves = (new ItemUnfinishedArmor()).setMetal("Black Steel", 2).func_77655_b(names[i] + " Unfinished Greaves"); i++;
    blueSteelUnfinishedGreaves = (new ItemUnfinishedArmor()).setMetal("Blue Steel", 2).func_77655_b(names[i] + " Unfinished Greaves"); i++;
    bronzeUnfinishedGreaves = (new ItemUnfinishedArmor()).setMetal("Bronze", 2).func_77655_b(names[i] + " Unfinished Greaves"); i++;
    copperUnfinishedGreaves = (new ItemUnfinishedArmor()).setMetal("Copper", 2).func_77655_b(names[i] + " Unfinished Greaves"); i++;
    wroughtIronUnfinishedGreaves = (new ItemUnfinishedArmor()).setMetal("Wrought Iron", 2).func_77655_b(names[i] + " Unfinished Greaves"); i++;
    redSteelUnfinishedGreaves = (new ItemUnfinishedArmor()).setMetal("Red Steel", 2).func_77655_b(names[i] + " Unfinished Greaves"); i++;
    steelUnfinishedGreaves = (new ItemUnfinishedArmor()).setMetal("Steel", 2).func_77655_b(names[i] + " Unfinished Greaves");

    i = 0;
    bismuthBronzeGreaves = (new ItemTFCArmor(Armor.bismuthBronzePlate, proxy.getArmorRenderID("bismuthbronze"), 2, 50, 1)).func_77655_b(names[i] + " Greaves"); i++;
    blackBronzeGreaves = (new ItemTFCArmor(Armor.blackBronzePlate, proxy.getArmorRenderID("blackbronze"), 2, 50, 1)).func_77655_b(names[i] + " Greaves"); i++;
    blackSteelGreaves = (new ItemTFCArmor(Armor.blackSteelPlate, proxy.getArmorRenderID("blacksteel"), 2, 50, 1)).func_77655_b(names[i] + " Greaves"); i++;
    blueSteelGreaves = (new ItemTFCArmor(Armor.blueSteelPlate, proxy.getArmorRenderID("bluesteel"), 2, 50, 1)).func_77655_b(names[i] + " Greaves"); i++;
    bronzeGreaves = (new ItemTFCArmor(Armor.bronzePlate, proxy.getArmorRenderID("bronze"), 2, 50, 1)).func_77655_b(names[i] + " Greaves"); i++;
    copperGreaves = (new ItemTFCArmor(Armor.copperPlate, proxy.getArmorRenderID("copper"), 2, 50, 1)).func_77655_b(names[i] + " Greaves"); i++;
    wroughtIronGreaves = (new ItemTFCArmor(Armor.wroughtIronPlate, proxy.getArmorRenderID("wroughtiron"), 2, 50, 1)).func_77655_b(names[i] + " Greaves"); i++;
    redSteelGreaves = (new ItemTFCArmor(Armor.redSteelPlate, proxy.getArmorRenderID("redsteel"), 2, 50, 1)).func_77655_b(names[i] + " Greaves"); i++;
    steelGreaves = (new ItemTFCArmor(Armor.steelPlate, proxy.getArmorRenderID("steel"), 2, 50, 1)).func_77655_b(names[i] + " Greaves");

    i = 0;
    bismuthBronzeUnfinishedChestplate = (new ItemUnfinishedArmor()).setMetal("Bismuth Bronze", 1).func_77655_b(names[i] + " Unfinished Chestplate"); i++;
    blackBronzeUnfinishedChestplate = (new ItemUnfinishedArmor()).setMetal("Black Bronze", 1).func_77655_b(names[i] + " Unfinished Chestplate"); i++;
    blackSteelUnfinishedChestplate = (new ItemUnfinishedArmor()).setMetal("Black Steel", 1).func_77655_b(names[i] + " Unfinished Chestplate"); i++;
    blueSteelUnfinishedChestplate = (new ItemUnfinishedArmor()).setMetal("Blue Steel", 1).func_77655_b(names[i] + " Unfinished Chestplate"); i++;
    bronzeUnfinishedChestplate = (new ItemUnfinishedArmor()).setMetal("Bronze", 1).func_77655_b(names[i] + " Unfinished Chestplate"); i++;
    copperUnfinishedChestplate = (new ItemUnfinishedArmor()).setMetal("Copper", 1).func_77655_b(names[i] + " Unfinished Chestplate"); i++;
    wroughtIronUnfinishedChestplate = (new ItemUnfinishedArmor()).setMetal("Wrought Iron", 1).func_77655_b(names[i] + " Unfinished Chestplate"); i++;
    redSteelUnfinishedChestplate = (new ItemUnfinishedArmor()).setMetal("Red Steel", 1).func_77655_b(names[i] + " Unfinished Chestplate"); i++;
    steelUnfinishedChestplate = (new ItemUnfinishedArmor()).setMetal("Steel", 1).func_77655_b(names[i] + " Unfinished Chestplate");

    i = 0;
    bismuthBronzeChestplate = (new ItemTFCArmor(Armor.bismuthBronzePlate, proxy.getArmorRenderID("bismuthbronze"), 1, 50, 2)).func_77655_b(names[i] + " Chestplate"); i++;
    blackBronzeChestplate = (new ItemTFCArmor(Armor.blackBronzePlate, proxy.getArmorRenderID("blackbronze"), 1, 50, 2)).func_77655_b(names[i] + " Chestplate"); i++;
    blackSteelChestplate = (new ItemTFCArmor(Armor.blackSteelPlate, proxy.getArmorRenderID("blacksteel"), 1, 50, 2)).func_77655_b(names[i] + " Chestplate"); i++;
    blueSteelChestplate = (new ItemTFCArmor(Armor.blueSteelPlate, proxy.getArmorRenderID("bluesteel"), 1, 50, 2)).func_77655_b(names[i] + " Chestplate"); i++;
    bronzeChestplate = (new ItemTFCArmor(Armor.bronzePlate, proxy.getArmorRenderID("bronze"), 1, 50, 2)).func_77655_b(names[i] + " Chestplate"); i++;
    copperChestplate = (new ItemTFCArmor(Armor.copperPlate, proxy.getArmorRenderID("copper"), 1, 50, 2)).func_77655_b(names[i] + " Chestplate"); i++;
    wroughtIronChestplate = (new ItemTFCArmor(Armor.wroughtIronPlate, proxy.getArmorRenderID("wroughtiron"), 1, 50, 2)).func_77655_b(names[i] + " Chestplate"); i++;
    redSteelChestplate = (new ItemTFCArmor(Armor.redSteelPlate, proxy.getArmorRenderID("redsteel"), 1, 50, 2)).func_77655_b(names[i] + " Chestplate"); i++;
    steelChestplate = (new ItemTFCArmor(Armor.steelPlate, proxy.getArmorRenderID("steel"), 1, 50, 2)).func_77655_b(names[i] + " Chestplate");

    i = 0;
    bismuthBronzeUnfinishedHelmet = (new ItemUnfinishedArmor()).setMetal("Bismuth Bronze", 0).func_77655_b(names[i] + " Unfinished Helmet"); i++;
    blackBronzeUnfinishedHelmet = (new ItemUnfinishedArmor()).setMetal("Black Bronze", 0).func_77655_b(names[i] + " Unfinished Helmet"); i++;
    blackSteelUnfinishedHelmet = (new ItemUnfinishedArmor()).setMetal("Black Steel", 0).func_77655_b(names[i] + " Unfinished Helmet"); i++;
    blueSteelUnfinishedHelmet = (new ItemUnfinishedArmor()).setMetal("Blue Steel", 0).func_77655_b(names[i] + " Unfinished Helmet"); i++;
    bronzeUnfinishedHelmet = (new ItemUnfinishedArmor()).setMetal("Bronze", 0).func_77655_b(names[i] + " Unfinished Helmet"); i++;
    copperUnfinishedHelmet = (new ItemUnfinishedArmor()).setMetal("Copper", 0).func_77655_b(names[i] + " Unfinished Helmet"); i++;
    wroughtIronUnfinishedHelmet = (new ItemUnfinishedArmor()).setMetal("Wrought Iron", 0).func_77655_b(names[i] + " Unfinished Helmet"); i++;
    redSteelUnfinishedHelmet = (new ItemUnfinishedArmor()).setMetal("Red Steel", 0).func_77655_b(names[i] + " Unfinished Helmet"); i++;
    steelUnfinishedHelmet = (new ItemUnfinishedArmor()).setMetal("Steel", 0).func_77655_b(names[i] + " Unfinished Helmet");

    i = 0;
    bismuthBronzeHelmet = (new ItemTFCArmor(Armor.bismuthBronzePlate, proxy.getArmorRenderID("bismuthbronze"), 0, 50, 3)).func_77655_b(names[i] + " Helmet"); i++;
    blackBronzeHelmet = (new ItemTFCArmor(Armor.blackBronzePlate, proxy.getArmorRenderID("blackbronze"), 0, 50, 3)).func_77655_b(names[i] + " Helmet"); i++;
    blackSteelHelmet = (new ItemTFCArmor(Armor.blackSteelPlate, proxy.getArmorRenderID("blacksteel"), 0, 50, 3)).func_77655_b(names[i] + " Helmet"); i++;
    blueSteelHelmet = (new ItemTFCArmor(Armor.blueSteelPlate, proxy.getArmorRenderID("bluesteel"), 0, 50, 3)).func_77655_b(names[i] + " Helmet"); i++;
    bronzeHelmet = (new ItemTFCArmor(Armor.bronzePlate, proxy.getArmorRenderID("bronze"), 0, 50, 3)).func_77655_b(names[i] + " Helmet"); i++;
    copperHelmet = (new ItemTFCArmor(Armor.copperPlate, proxy.getArmorRenderID("copper"), 0, 50, 3)).func_77655_b(names[i] + " Helmet"); i++;
    wroughtIronHelmet = (new ItemTFCArmor(Armor.wroughtIronPlate, proxy.getArmorRenderID("wroughtiron"), 0, 50, 3)).func_77655_b(names[i] + " Helmet"); i++;
    redSteelHelmet = (new ItemTFCArmor(Armor.redSteelPlate, proxy.getArmorRenderID("redsteel"), 0, 50, 3)).func_77655_b(names[i] + " Helmet"); i++;
    steelHelmet = (new ItemTFCArmor(Armor.steelPlate, proxy.getArmorRenderID("steel"), 0, 50, 3)).func_77655_b(names[i] + " Helmet");

    leatherHelmet = (new ItemTFCArmor(Armor.leather, proxy.getArmorRenderID("leather"), 0, ItemArmor.ArmorMaterial.CLOTH, 100, 3)).func_77655_b("helmetCloth").func_111206_d("leather_helmet");
    leatherChestplate = (new ItemTFCArmor(Armor.leather, proxy.getArmorRenderID("leather"), 1, ItemArmor.ArmorMaterial.CLOTH, 100, 2)).func_77655_b("chestplateCloth").func_111206_d("leather_chestplate");
    leatherLeggings = (new ItemTFCArmor(Armor.leather, proxy.getArmorRenderID("leather"), 2, ItemArmor.ArmorMaterial.CLOTH, 100, 1)).func_77655_b("leggingsCloth").func_111206_d("leather_leggings");
    leatherBoots = (new ItemTFCArmor(Armor.leather, proxy.getArmorRenderID("leather"), 3, ItemArmor.ArmorMaterial.CLOTH, 100, 0)).func_77655_b("bootsCloth").func_111206_d("leather_boots");

    quiver = (new ItemQuiver()).func_77655_b("Quiver");
  }



  public static void registerFurnaceFuel() {
    TFCFuelHandler.registerFuel(blueSteelBucketLava, 20000);
    TFCFuelHandler.registerFuel(singlePlank, 400);
    TFCFuelHandler.registerFuel(woodenBucketEmpty, 300);
    TFCFuelHandler.registerFuel(fireStarter, 100);
    TFCFuelHandler.registerFuel(logs, 800);
    TFCFuelHandler.registerFuel(sluiceItem, 300);
    TFCFuelHandler.registerFuel(rope, 50);
    TFCFuelHandler.registerFuel(arrow, 20);
    TFCFuelHandler.registerFuel(bow, 100);
    TFCFuelHandler.registerFuel(fishingRod, 100);
    TFCFuelHandler.registerFuel(stick, 100);
    TFCFuelHandler.registerFuel(coal, 1600);
    TFCFuelHandler.registerFuel(woolCloth, 20);
    TFCFuelHandler.registerFuel(silkCloth, 20);
    TFCFuelHandler.registerFuel(burlapCloth, 20);
    TFCFuelHandler.registerFuel(straw, 20);

    for (int l = 0; l < Recipes.doors.length; l++) {
      TFCFuelHandler.registerFuel(Recipes.doors[l], 300);
    }
    TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.woodSupportV), 300);
    TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.woodSupportV2), 300);
    TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.woodSupportH), 300);
    TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.woodSupportH2), 300);
    TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.fence), 300);
    TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.fence2), 300);
    TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.fenceGate), 300);
    TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.fenceGate2), 300);
    TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.chest), 300);
    TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.strawHideBed), 200);
    TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.thatch), 200);
    TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.planks), 300);
    TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.planks2), 300);
    TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.barrel), 300);
    TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.torch), 100);
    TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.sapling), 100);
    TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.sapling2), 100);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\ItemSetup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */