/*      */ package com.bioxx.tfc;
/*      */ 
/*      */ import com.bioxx.tfc.Core.Metal.Alloy;
/*      */ import com.bioxx.tfc.Core.Metal.AlloyManager;
/*      */ import com.bioxx.tfc.Core.Metal.MetalRegistry;
/*      */ import com.bioxx.tfc.Core.Player.FoodStatsTFC;
/*      */ import com.bioxx.tfc.Core.Recipes;
/*      */ import com.bioxx.tfc.Core.TFCTabs;
/*      */ import com.bioxx.tfc.Food.ItemEgg;
/*      */ import com.bioxx.tfc.Food.ItemFoodMeat;
/*      */ import com.bioxx.tfc.Food.ItemFoodTFC;
/*      */ import com.bioxx.tfc.Food.ItemSalad;
/*      */ import com.bioxx.tfc.Food.ItemSandwich;
/*      */ import com.bioxx.tfc.Handlers.TFCFuelHandler;
/*      */ import com.bioxx.tfc.Items.ItemAlcohol;
/*      */ import com.bioxx.tfc.Items.ItemArrow;
/*      */ import com.bioxx.tfc.Items.ItemBlocks.ItemWoodDoor;
/*      */ import com.bioxx.tfc.Items.ItemBloom;
/*      */ import com.bioxx.tfc.Items.ItemBlueprint;
/*      */ import com.bioxx.tfc.Items.ItemClay;
/*      */ import com.bioxx.tfc.Items.ItemCoal;
/*      */ import com.bioxx.tfc.Items.ItemCustomLeash;
/*      */ import com.bioxx.tfc.Items.ItemCustomMinecart;
/*      */ import com.bioxx.tfc.Items.ItemCustomNameTag;
/*      */ import com.bioxx.tfc.Items.ItemCustomPotion;
/*      */ import com.bioxx.tfc.Items.ItemCustomSeeds;
/*      */ import com.bioxx.tfc.Items.ItemDyeCustom;
/*      */ import com.bioxx.tfc.Items.ItemFertilizer;
/*      */ import com.bioxx.tfc.Items.ItemFlatGeneric;
/*      */ import com.bioxx.tfc.Items.ItemFruitTreeSapling;
/*      */ import com.bioxx.tfc.Items.ItemGem;
/*      */ import com.bioxx.tfc.Items.ItemGlassBottle;
/*      */ import com.bioxx.tfc.Items.ItemIngot;
/*      */ import com.bioxx.tfc.Items.ItemLeather;
/*      */ import com.bioxx.tfc.Items.ItemLogs;
/*      */ import com.bioxx.tfc.Items.ItemLooseRock;
/*      */ import com.bioxx.tfc.Items.ItemMeltedMetal;
/*      */ import com.bioxx.tfc.Items.ItemMetalSheet;
/*      */ import com.bioxx.tfc.Items.ItemMetalSheet2x;
/*      */ import com.bioxx.tfc.Items.ItemOre;
/*      */ import com.bioxx.tfc.Items.ItemOreSmall;
/*      */ import com.bioxx.tfc.Items.ItemPlank;
/*      */ import com.bioxx.tfc.Items.ItemQuiver;
/*      */ import com.bioxx.tfc.Items.ItemRawHide;
/*      */ import com.bioxx.tfc.Items.ItemReeds;
/*      */ import com.bioxx.tfc.Items.ItemSluice;
/*      */ import com.bioxx.tfc.Items.ItemStick;
/*      */ import com.bioxx.tfc.Items.ItemStoneBrick;
/*      */ import com.bioxx.tfc.Items.ItemTFCArmor;
/*      */ import com.bioxx.tfc.Items.ItemTerra;
/*      */ import com.bioxx.tfc.Items.ItemTuyere;
/*      */ import com.bioxx.tfc.Items.ItemUnfinishedArmor;
/*      */ import com.bioxx.tfc.Items.ItemYarn;
/*      */ import com.bioxx.tfc.Items.Pottery.ItemPotteryBase;
/*      */ import com.bioxx.tfc.Items.Pottery.ItemPotteryJug;
/*      */ import com.bioxx.tfc.Items.Pottery.ItemPotteryMold;
/*      */ import com.bioxx.tfc.Items.Pottery.ItemPotterySmallVessel;
/*      */ import com.bioxx.tfc.Items.Tools.ItemChisel;
/*      */ import com.bioxx.tfc.Items.Tools.ItemCustomAxe;
/*      */ import com.bioxx.tfc.Items.Tools.ItemCustomBow;
/*      */ import com.bioxx.tfc.Items.Tools.ItemCustomBucket;
/*      */ import com.bioxx.tfc.Items.Tools.ItemCustomBucketMilk;
/*      */ import com.bioxx.tfc.Items.Tools.ItemCustomFishingRod;
/*      */ import com.bioxx.tfc.Items.Tools.ItemCustomHoe;
/*      */ import com.bioxx.tfc.Items.Tools.ItemCustomPickaxe;
/*      */ import com.bioxx.tfc.Items.Tools.ItemCustomSaw;
/*      */ import com.bioxx.tfc.Items.Tools.ItemCustomScythe;
/*      */ import com.bioxx.tfc.Items.Tools.ItemCustomShovel;
/*      */ import com.bioxx.tfc.Items.Tools.ItemCustomSword;
/*      */ import com.bioxx.tfc.Items.Tools.ItemFirestarter;
/*      */ import com.bioxx.tfc.Items.Tools.ItemFlintSteel;
/*      */ import com.bioxx.tfc.Items.Tools.ItemGoldPan;
/*      */ import com.bioxx.tfc.Items.Tools.ItemHammer;
/*      */ import com.bioxx.tfc.Items.Tools.ItemJavelin;
/*      */ import com.bioxx.tfc.Items.Tools.ItemKnife;
/*      */ import com.bioxx.tfc.Items.Tools.ItemMiscToolHead;
/*      */ import com.bioxx.tfc.Items.Tools.ItemProPick;
/*      */ import com.bioxx.tfc.Items.Tools.ItemShears;
/*      */ import com.bioxx.tfc.Items.Tools.ItemSpindle;
/*      */ import com.bioxx.tfc.Items.Tools.ItemSteelBucketBlue;
/*      */ import com.bioxx.tfc.Items.Tools.ItemSteelBucketRed;
/*      */ import com.bioxx.tfc.api.Armor;
/*      */ import com.bioxx.tfc.api.Constant.Global;
/*      */ import com.bioxx.tfc.api.Enums.EnumDamageType;
/*      */ import com.bioxx.tfc.api.Enums.EnumFoodGroup;
/*      */ import com.bioxx.tfc.api.Enums.EnumSize;
/*      */ import com.bioxx.tfc.api.Enums.EnumWeight;
/*      */ import com.bioxx.tfc.api.Metal;
/*      */ import com.bioxx.tfc.api.TFCBlocks;
/*      */ import com.bioxx.tfc.api.TFCItems;
/*      */ import com.bioxx.tfc.api.TFCOptions;
/*      */ import java.util.ArrayList;
/*      */ import net.minecraft.client.renderer.texture.IIconRegister;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemArmor;
/*      */ import net.minecraft.item.ItemBow;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.util.IIcon;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraftforge.common.util.EnumHelper;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ItemSetup
/*      */   extends TFCItems
/*      */ {
/*      */   public static void setup() {
/*  113 */     igInToolMaterial = EnumHelper.addToolMaterial("IgIn", 1, igInStoneUses, igInStoneEff, 40.0F, 5);
/*  114 */     sedToolMaterial = EnumHelper.addToolMaterial("Sed", 1, sedStoneUses, sedStoneEff, 40.0F, 5);
/*  115 */     igExToolMaterial = EnumHelper.addToolMaterial("IgEx", 1, igExStoneUses, igExStoneEff, 40.0F, 5);
/*  116 */     mMToolMaterial = EnumHelper.addToolMaterial("MM", 1, mMStoneUses, mMStoneEff, 40.0F, 5);
/*      */     
/*  118 */     copperToolMaterial = EnumHelper.addToolMaterial("Copper", 2, copperUses, copperEff, 65.0F, 8);
/*      */     
/*  120 */     bronzeToolMaterial = EnumHelper.addToolMaterial("Bronze", 2, bronzeUses, bronzeEff, 100.0F, 13);
/*  121 */     bismuthBronzeToolMaterial = EnumHelper.addToolMaterial("BismuthBronze", 2, bismuthBronzeUses, bismuthBronzeEff, 90.0F, 10);
/*  122 */     blackBronzeToolMaterial = EnumHelper.addToolMaterial("BlackBronze", 2, blackBronzeUses, blackBronzeEff, 95.0F, 10);
/*      */     
/*  124 */     ironToolMaterial = EnumHelper.addToolMaterial("Iron", 2, wroughtIronUses, wroughtIronEff, 135.0F, 10);
/*      */     
/*  126 */     steelToolMaterial = EnumHelper.addToolMaterial("Steel", 2, steelUses, steelEff, 170.0F, 10);
/*      */     
/*  128 */     blackSteelToolMaterial = EnumHelper.addToolMaterial("BlackSteel", 2, blackSteelUses, blackSteelEff, 205.0F, 12);
/*      */     
/*  130 */     blueSteelToolMaterial = EnumHelper.addToolMaterial("BlueSteel", 3, blueSteelUses, blueSteelEff, 240.0F, 22);
/*  131 */     redSteelToolMaterial = EnumHelper.addToolMaterial("RedSteel", 3, redSteelUses, redSteelEff, 240.0F, 22);
/*      */     
/*  133 */     TerraFirmaCraft.LOG.info("Loading Items");
/*      */     
/*  135 */     fishingRod = (new ItemCustomFishingRod()).func_77655_b("fishingRod").func_111206_d("tools/fishing_rod");
/*  136 */     coal = (new ItemCoal()).func_77655_b("coal");
/*  137 */     stick = (new ItemStick()).func_77664_n().func_77655_b("stick");
/*  138 */     bow = (new ItemCustomBow()).func_77655_b("bow").func_111206_d("tools/bow");
/*  139 */     Items.field_151031_f = (ItemBow)bow;
/*  140 */     arrow = (new ItemArrow()).func_77655_b("arrow").func_77637_a(TFCTabs.TFC_WEAPONS);
/*  141 */     dye = (new ItemDyeCustom()).func_77655_b("dyePowder").func_111206_d("dye_powder").func_77637_a(TFCTabs.TFC_MATERIALS);
/*  142 */     glassBottle = (new ItemGlassBottle()).func_77655_b("Glass Bottle");
/*  143 */     potion = (new ItemCustomPotion()).func_77655_b("potion").func_111206_d("potion");
/*  144 */     rope = (new ItemCustomLeash()).func_77655_b("Rope").func_77637_a(TFCTabs.TFC_TOOLS);
/*  145 */     Items.field_151058_ca = rope;
/*      */     
/*  147 */     minecartCrate = (new ItemCustomMinecart(1)).func_77655_b("minecartChest").func_111206_d("minecart_chest");
/*  148 */     goldPan = (new ItemGoldPan()).func_77655_b("GoldPan");
/*  149 */     sluiceItem = (new ItemSluice()).setFolder("devices/").func_77655_b("SluiceItem").func_77637_a(TFCTabs.TFC_DEVICES);
/*      */     
/*  151 */     shears = (new ItemShears(0.0F, ironToolMaterial)).func_77655_b("shears").func_111206_d("shears");
/*      */     
/*  153 */     proPickBismuthBronze = (new ItemProPick()).func_77655_b("Bismuth Bronze ProPick").func_77656_e(bismuthBronzeUses / 3);
/*  154 */     proPickBlackBronze = (new ItemProPick()).func_77655_b("Black Bronze ProPick").func_77656_e(blackBronzeUses / 3);
/*  155 */     proPickBlackSteel = (new ItemProPick()).func_77655_b("Black Steel ProPick").func_77656_e(blackSteelUses / 3);
/*  156 */     proPickBlueSteel = (new ItemProPick()).func_77655_b("Blue Steel ProPick").func_77656_e(blueSteelUses / 3);
/*  157 */     proPickBronze = (new ItemProPick()).func_77655_b("Bronze ProPick").func_77656_e(bronzeUses / 3);
/*  158 */     proPickCopper = (new ItemProPick()).func_77655_b("Copper ProPick").func_77656_e(copperUses / 3);
/*  159 */     proPickIron = (new ItemProPick()).func_77655_b("Wrought Iron ProPick").func_77656_e(wroughtIronUses / 3);
/*  160 */     proPickRedSteel = (new ItemProPick()).func_77655_b("Red Steel ProPick").func_77656_e(redSteelUses / 3);
/*  161 */     proPickSteel = (new ItemProPick()).func_77655_b("Steel ProPick").func_77656_e(steelUses / 3);
/*      */     
/*  163 */     bismuthIngot = (new ItemIngot()).func_77655_b("Bismuth Ingot");
/*  164 */     bismuthBronzeIngot = (new ItemIngot()).func_77655_b("Bismuth Bronze Ingot");
/*  165 */     blackBronzeIngot = (new ItemIngot()).func_77655_b("Black Bronze Ingot");
/*  166 */     blackSteelIngot = (new ItemIngot()).func_77655_b("Black Steel Ingot");
/*  167 */     blueSteelIngot = (new ItemIngot()).func_77655_b("Blue Steel Ingot");
/*  168 */     brassIngot = (new ItemIngot()).func_77655_b("Brass Ingot");
/*  169 */     bronzeIngot = (new ItemIngot()).func_77655_b("Bronze Ingot");
/*  170 */     copperIngot = (new ItemIngot()).func_77655_b("Copper Ingot");
/*  171 */     goldIngot = (new ItemIngot()).func_77655_b("Gold Ingot");
/*  172 */     wroughtIronIngot = (new ItemIngot()).func_77655_b("Wrought Iron Ingot");
/*  173 */     leadIngot = (new ItemIngot()).func_77655_b("Lead Ingot");
/*  174 */     nickelIngot = (new ItemIngot()).func_77655_b("Nickel Ingot");
/*  175 */     pigIronIngot = (new ItemIngot()).func_77655_b("Pig Iron Ingot");
/*  176 */     platinumIngot = (new ItemIngot()).func_77655_b("Platinum Ingot");
/*  177 */     redSteelIngot = (new ItemIngot()).func_77655_b("Red Steel Ingot");
/*  178 */     roseGoldIngot = (new ItemIngot()).func_77655_b("Rose Gold Ingot");
/*  179 */     silverIngot = (new ItemIngot()).func_77655_b("Silver Ingot");
/*  180 */     steelIngot = (new ItemIngot()).func_77655_b("Steel Ingot");
/*  181 */     sterlingSilverIngot = (new ItemIngot()).func_77655_b("Sterling Silver Ingot");
/*  182 */     tinIngot = (new ItemIngot()).func_77655_b("Tin Ingot");
/*  183 */     zincIngot = (new ItemIngot()).func_77655_b("Zinc Ingot");
/*      */     
/*  185 */     bismuthIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Bismuth Double Ingot")).setSize(EnumSize.LARGE).setMetal("Bismuth", 200);
/*  186 */     bismuthBronzeIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Bismuth Bronze Double Ingot")).setSize(EnumSize.LARGE).setMetal("Bismuth Bronze", 200);
/*  187 */     blackBronzeIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Black Bronze Double Ingot")).setSize(EnumSize.LARGE).setMetal("Black Bronze", 200);
/*  188 */     blackSteelIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Black Steel Double Ingot")).setSize(EnumSize.LARGE).setMetal("Black Steel", 200);
/*  189 */     blueSteelIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Blue Steel Double Ingot")).setSize(EnumSize.LARGE).setMetal("Blue Steel", 200);
/*  190 */     brassIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Brass Double Ingot")).setSize(EnumSize.LARGE).setMetal("Brass", 200);
/*  191 */     bronzeIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Bronze Double Ingot")).setSize(EnumSize.LARGE).setMetal("Bronze", 200);
/*  192 */     copperIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Copper Double Ingot")).setSize(EnumSize.LARGE).setMetal("Copper", 200);
/*  193 */     goldIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Gold Double Ingot")).setSize(EnumSize.LARGE).setMetal("Gold", 200);
/*  194 */     wroughtIronIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Wrought Iron Double Ingot")).setSize(EnumSize.LARGE).setMetal("Wrought Iron", 200);
/*  195 */     leadIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Lead Double Ingot")).setSize(EnumSize.LARGE).setMetal("Lead", 200);
/*  196 */     nickelIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Nickel Double Ingot")).setSize(EnumSize.LARGE).setMetal("Nickel", 200);
/*  197 */     pigIronIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Pig Iron Double Ingot")).setSize(EnumSize.LARGE).setMetal("Pig Iron", 200);
/*  198 */     platinumIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Platinum Double Ingot")).setSize(EnumSize.LARGE).setMetal("Platinum", 200);
/*  199 */     redSteelIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Red Steel Double Ingot")).setSize(EnumSize.LARGE).setMetal("Red Steel", 200);
/*  200 */     roseGoldIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Rose Gold Double Ingot")).setSize(EnumSize.LARGE).setMetal("Rose Gold", 200);
/*  201 */     silverIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Silver Double Ingot")).setSize(EnumSize.LARGE).setMetal("Silver", 200);
/*  202 */     steelIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Steel Double Ingot")).setSize(EnumSize.LARGE).setMetal("Steel", 200);
/*  203 */     sterlingSilverIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Sterling Silver Double Ingot")).setSize(EnumSize.LARGE).setMetal("Sterling Silver", 200);
/*  204 */     tinIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Tin Double Ingot")).setSize(EnumSize.LARGE).setMetal("Tin", 200);
/*  205 */     zincIngot2x = (Item)((ItemIngot)(new ItemIngot()).func_77655_b("Zinc Double Ingot")).setSize(EnumSize.LARGE).setMetal("Zinc", 200);
/*      */     
/*  207 */     gemRuby = (new ItemGem()).func_77655_b("Ruby");
/*  208 */     gemSapphire = (new ItemGem()).func_77655_b("Sapphire");
/*  209 */     gemEmerald = (new ItemGem()).func_77655_b("Emerald");
/*  210 */     gemTopaz = (new ItemGem()).func_77655_b("Topaz");
/*  211 */     gemTourmaline = (new ItemGem()).func_77655_b("Tourmaline");
/*  212 */     gemJade = (new ItemGem()).func_77655_b("Jade");
/*  213 */     gemBeryl = (new ItemGem()).func_77655_b("Beryl");
/*  214 */     gemAgate = (new ItemGem()).func_77655_b("Agate");
/*  215 */     gemOpal = (new ItemGem()).func_77655_b("Opal");
/*  216 */     gemGarnet = (new ItemGem()).func_77655_b("Garnet");
/*  217 */     gemJasper = (new ItemGem()).func_77655_b("Jasper");
/*  218 */     gemAmethyst = (new ItemGem()).func_77655_b("Amethyst");
/*  219 */     gemDiamond = (new ItemGem()).func_77655_b("Diamond");
/*      */ 
/*      */     
/*  222 */     igInShovel = (new ItemCustomShovel(igInToolMaterial)).func_77655_b("IgIn Stone Shovel").func_77656_e(igInStoneUses);
/*  223 */     igInAxe = (new ItemCustomAxe(igInToolMaterial, 60.0F)).func_77655_b("IgIn Stone Axe").func_77656_e(igInStoneUses);
/*  224 */     igInHoe = (new ItemCustomHoe(igInToolMaterial)).func_77655_b("IgIn Stone Hoe").func_77656_e(igInStoneUses);
/*      */     
/*  226 */     sedShovel = (new ItemCustomShovel(sedToolMaterial)).func_77655_b("Sed Stone Shovel").func_77656_e(sedStoneUses);
/*  227 */     sedAxe = (new ItemCustomAxe(sedToolMaterial, 60.0F)).func_77655_b("Sed Stone Axe").func_77656_e(sedStoneUses);
/*  228 */     sedHoe = (new ItemCustomHoe(sedToolMaterial)).func_77655_b("Sed Stone Hoe").func_77656_e(sedStoneUses);
/*      */     
/*  230 */     igExShovel = (new ItemCustomShovel(igExToolMaterial)).func_77655_b("IgEx Stone Shovel").func_77656_e(igExStoneUses);
/*  231 */     igExAxe = (new ItemCustomAxe(igExToolMaterial, 60.0F)).func_77655_b("IgEx Stone Axe").func_77656_e(igExStoneUses);
/*  232 */     igExHoe = (new ItemCustomHoe(igExToolMaterial)).func_77655_b("IgEx Stone Hoe").func_77656_e(igExStoneUses);
/*      */     
/*  234 */     mMShovel = (new ItemCustomShovel(mMToolMaterial)).func_77655_b("MM Stone Shovel").func_77656_e(mMStoneUses);
/*  235 */     mMAxe = (new ItemCustomAxe(mMToolMaterial, 60.0F)).func_77655_b("MM Stone Axe").func_77656_e(mMStoneUses);
/*  236 */     mMHoe = (new ItemCustomHoe(mMToolMaterial)).func_77655_b("MM Stone Hoe").func_77656_e(mMStoneUses);
/*      */     
/*  238 */     bismuthBronzePick = (new ItemCustomPickaxe(bismuthBronzeToolMaterial)).func_77655_b("Bismuth Bronze Pick").func_77656_e(bismuthBronzeUses);
/*  239 */     bismuthBronzeShovel = (new ItemCustomShovel(bismuthBronzeToolMaterial)).func_77655_b("Bismuth Bronze Shovel").func_77656_e(bismuthBronzeUses);
/*  240 */     bismuthBronzeAxe = (new ItemCustomAxe(bismuthBronzeToolMaterial, 150.0F)).func_77655_b("Bismuth Bronze Axe").func_77656_e(bismuthBronzeUses);
/*  241 */     bismuthBronzeHoe = (new ItemCustomHoe(bismuthBronzeToolMaterial)).func_77655_b("Bismuth Bronze Hoe").func_77656_e(bismuthBronzeUses);
/*      */     
/*  243 */     blackBronzePick = (new ItemCustomPickaxe(blackBronzeToolMaterial)).func_77655_b("Black Bronze Pick").func_77656_e(blackBronzeUses);
/*  244 */     blackBronzeShovel = (new ItemCustomShovel(blackBronzeToolMaterial)).func_77655_b("Black Bronze Shovel").func_77656_e(blackBronzeUses);
/*  245 */     blackBronzeAxe = (new ItemCustomAxe(blackBronzeToolMaterial, 170.0F)).func_77655_b("Black Bronze Axe").func_77656_e(blackBronzeUses);
/*  246 */     blackBronzeHoe = (new ItemCustomHoe(blackBronzeToolMaterial)).func_77655_b("Black Bronze Hoe").func_77656_e(blackBronzeUses);
/*      */     
/*  248 */     blackSteelPick = (new ItemCustomPickaxe(blackSteelToolMaterial)).func_77655_b("Black Steel Pick").func_77656_e(blackSteelUses);
/*  249 */     blackSteelShovel = (new ItemCustomShovel(blackSteelToolMaterial)).func_77655_b("Black Steel Shovel").func_77656_e(blackSteelUses);
/*  250 */     blackSteelAxe = (new ItemCustomAxe(blackSteelToolMaterial, 245.0F)).func_77655_b("Black Steel Axe").func_77656_e(blackSteelUses);
/*  251 */     blackSteelHoe = (new ItemCustomHoe(blackSteelToolMaterial)).func_77655_b("Black Steel Hoe").func_77656_e(blackSteelUses);
/*      */     
/*  253 */     blueSteelPick = (new ItemCustomPickaxe(blueSteelToolMaterial)).func_77655_b("Blue Steel Pick").func_77656_e(blueSteelUses);
/*  254 */     blueSteelShovel = (new ItemCustomShovel(blueSteelToolMaterial)).func_77655_b("Blue Steel Shovel").func_77656_e(blueSteelUses);
/*  255 */     blueSteelAxe = (new ItemCustomAxe(blueSteelToolMaterial, 270.0F)).func_77655_b("Blue Steel Axe").func_77656_e(blueSteelUses);
/*  256 */     blueSteelHoe = (new ItemCustomHoe(blueSteelToolMaterial)).func_77655_b("Blue Steel Hoe").func_77656_e(blueSteelUses);
/*      */     
/*  258 */     bronzePick = (new ItemCustomPickaxe(bronzeToolMaterial)).func_77655_b("Bronze Pick").func_77656_e(bronzeUses);
/*  259 */     bronzeShovel = (new ItemCustomShovel(bronzeToolMaterial)).func_77655_b("Bronze Shovel").func_77656_e(bronzeUses);
/*  260 */     bronzeAxe = (new ItemCustomAxe(bronzeToolMaterial, 160.0F)).func_77655_b("Bronze Axe").func_77656_e(bronzeUses);
/*  261 */     bronzeHoe = (new ItemCustomHoe(bronzeToolMaterial)).func_77655_b("Bronze Hoe").func_77656_e(bronzeUses);
/*      */     
/*  263 */     copperPick = (new ItemCustomPickaxe(copperToolMaterial)).func_77655_b("Copper Pick").func_77656_e(copperUses);
/*  264 */     copperShovel = (new ItemCustomShovel(copperToolMaterial)).func_77655_b("Copper Shovel").func_77656_e(copperUses);
/*  265 */     copperAxe = (new ItemCustomAxe(copperToolMaterial, 115.0F)).func_77655_b("Copper Axe").func_77656_e(copperUses);
/*  266 */     copperHoe = (new ItemCustomHoe(copperToolMaterial)).func_77655_b("Copper Hoe").func_77656_e(copperUses);
/*      */     
/*  268 */     wroughtIronPick = (new ItemCustomPickaxe(ironToolMaterial)).func_77655_b("Wrought Iron Pick").func_77656_e(wroughtIronUses);
/*  269 */     wroughtIronShovel = (new ItemCustomShovel(ironToolMaterial)).func_77655_b("Wrought Iron Shovel").func_77656_e(wroughtIronUses);
/*  270 */     wroughtIronAxe = (new ItemCustomAxe(ironToolMaterial, 185.0F)).func_77655_b("Wrought Iron Axe").func_77656_e(wroughtIronUses);
/*  271 */     wroughtIronHoe = (new ItemCustomHoe(ironToolMaterial)).func_77655_b("Wrought Iron Hoe").func_77656_e(wroughtIronUses);
/*      */     
/*  273 */     redSteelPick = (new ItemCustomPickaxe(redSteelToolMaterial)).func_77655_b("Red Steel Pick").func_77656_e(redSteelUses);
/*  274 */     redSteelShovel = (new ItemCustomShovel(redSteelToolMaterial)).func_77655_b("Red Steel Shovel").func_77656_e(redSteelUses);
/*  275 */     redSteelAxe = (new ItemCustomAxe(redSteelToolMaterial, 270.0F)).func_77655_b("Red Steel Axe").func_77656_e(redSteelUses);
/*  276 */     redSteelHoe = (new ItemCustomHoe(redSteelToolMaterial)).func_77655_b("Red Steel Hoe").func_77656_e(redSteelUses);
/*      */     
/*  278 */     steelPick = (new ItemCustomPickaxe(steelToolMaterial)).func_77655_b("Steel Pick").func_77656_e(steelUses);
/*  279 */     steelShovel = (new ItemCustomShovel(steelToolMaterial)).func_77655_b("Steel Shovel").func_77656_e(steelUses);
/*  280 */     steelAxe = (new ItemCustomAxe(steelToolMaterial, 210.0F)).func_77655_b("Steel Axe").func_77656_e(steelUses);
/*  281 */     steelHoe = (new ItemCustomHoe(steelToolMaterial)).func_77655_b("Steel Hoe").func_77656_e(steelUses);
/*      */ 
/*      */     
/*  284 */     bismuthBronzeChisel = (new ItemChisel(bismuthBronzeToolMaterial)).func_77655_b("Bismuth Bronze Chisel").func_77656_e(bismuthBronzeUses);
/*  285 */     blackBronzeChisel = (new ItemChisel(blackBronzeToolMaterial)).func_77655_b("Black Bronze Chisel").func_77656_e(blackBronzeUses);
/*  286 */     blackSteelChisel = (new ItemChisel(blackSteelToolMaterial)).func_77655_b("Black Steel Chisel").func_77656_e(blackSteelUses);
/*  287 */     blueSteelChisel = (new ItemChisel(blueSteelToolMaterial)).func_77655_b("Blue Steel Chisel").func_77656_e(blueSteelUses);
/*  288 */     bronzeChisel = (new ItemChisel(bronzeToolMaterial)).func_77655_b("Bronze Chisel").func_77656_e(bronzeUses);
/*  289 */     copperChisel = (new ItemChisel(copperToolMaterial)).func_77655_b("Copper Chisel").func_77656_e(copperUses);
/*  290 */     wroughtIronChisel = (new ItemChisel(ironToolMaterial)).func_77655_b("Wrought Iron Chisel").func_77656_e(wroughtIronUses);
/*  291 */     redSteelChisel = (new ItemChisel(redSteelToolMaterial)).func_77655_b("Red Steel Chisel").func_77656_e(redSteelUses);
/*  292 */     steelChisel = (new ItemChisel(steelToolMaterial)).func_77655_b("Steel Chisel").func_77656_e(steelUses);
/*      */     
/*  294 */     bismuthBronzeSword = (new ItemCustomSword(bismuthBronzeToolMaterial, 210.0F)).func_77655_b("Bismuth Bronze Sword").func_77656_e(bismuthBronzeUses);
/*  295 */     blackBronzeSword = (new ItemCustomSword(blackBronzeToolMaterial, 230.0F)).func_77655_b("Black Bronze Sword").func_77656_e(blackBronzeUses);
/*  296 */     blackSteelSword = (new ItemCustomSword(blackSteelToolMaterial, 270.0F)).func_77655_b("Black Steel Sword").func_77656_e(blackSteelUses);
/*  297 */     blueSteelSword = (new ItemCustomSword(blueSteelToolMaterial, 315.0F)).func_77655_b("Blue Steel Sword").func_77656_e(blueSteelUses);
/*  298 */     bronzeSword = (new ItemCustomSword(bronzeToolMaterial, 220.0F)).func_77655_b("Bronze Sword").func_77656_e(bronzeUses);
/*  299 */     copperSword = (new ItemCustomSword(copperToolMaterial, 165.0F)).func_77655_b("Copper Sword").func_77656_e(copperUses);
/*  300 */     wroughtIronSword = (new ItemCustomSword(ironToolMaterial, 240.0F)).func_77655_b("Wrought Iron Sword").func_77656_e(wroughtIronUses);
/*  301 */     redSteelSword = (new ItemCustomSword(redSteelToolMaterial, 315.0F)).func_77655_b("Red Steel Sword").func_77656_e(redSteelUses);
/*  302 */     steelSword = (new ItemCustomSword(steelToolMaterial, 265.0F)).func_77655_b("Steel Sword").func_77656_e(steelUses);
/*      */     
/*  304 */     bismuthBronzeMace = (new ItemCustomSword(bismuthBronzeToolMaterial, 210.0F, EnumDamageType.CRUSHING)).func_77655_b("Bismuth Bronze Mace").func_77656_e(bismuthBronzeUses);
/*  305 */     blackBronzeMace = (new ItemCustomSword(blackBronzeToolMaterial, 230.0F, EnumDamageType.CRUSHING)).func_77655_b("Black Bronze Mace").func_77656_e(blackBronzeUses);
/*  306 */     blackSteelMace = (new ItemCustomSword(blackSteelToolMaterial, 270.0F, EnumDamageType.CRUSHING)).func_77655_b("Black Steel Mace").func_77656_e(blackSteelUses);
/*  307 */     blueSteelMace = (new ItemCustomSword(blueSteelToolMaterial, 315.0F, EnumDamageType.CRUSHING)).func_77655_b("Blue Steel Mace").func_77656_e(blueSteelUses);
/*  308 */     bronzeMace = (new ItemCustomSword(bronzeToolMaterial, 220.0F, EnumDamageType.CRUSHING)).func_77655_b("Bronze Mace").func_77656_e(bronzeUses);
/*  309 */     copperMace = (new ItemCustomSword(copperToolMaterial, 165.0F, EnumDamageType.CRUSHING)).func_77655_b("Copper Mace").func_77656_e(copperUses);
/*  310 */     wroughtIronMace = (new ItemCustomSword(ironToolMaterial, 240.0F, EnumDamageType.CRUSHING)).func_77655_b("Wrought Iron Mace").func_77656_e(wroughtIronUses);
/*  311 */     redSteelMace = (new ItemCustomSword(redSteelToolMaterial, 315.0F, EnumDamageType.CRUSHING)).func_77655_b("Red Steel Mace").func_77656_e(redSteelUses);
/*  312 */     steelMace = (new ItemCustomSword(steelToolMaterial, 265.0F, EnumDamageType.CRUSHING)).func_77655_b("Steel Mace").func_77656_e(steelUses);
/*      */     
/*  314 */     bismuthBronzeSaw = (new ItemCustomSaw(bismuthBronzeToolMaterial)).func_77655_b("Bismuth Bronze Saw").func_77656_e(bismuthBronzeUses);
/*  315 */     blackBronzeSaw = (new ItemCustomSaw(blackBronzeToolMaterial)).func_77655_b("Black Bronze Saw").func_77656_e(blackBronzeUses);
/*  316 */     blackSteelSaw = (new ItemCustomSaw(blackSteelToolMaterial)).func_77655_b("Black Steel Saw").func_77656_e(blackSteelUses);
/*  317 */     blueSteelSaw = (new ItemCustomSaw(blueSteelToolMaterial)).func_77655_b("Blue Steel Saw").func_77656_e(blueSteelUses);
/*  318 */     bronzeSaw = (new ItemCustomSaw(bronzeToolMaterial)).func_77655_b("Bronze Saw").func_77656_e(bronzeUses);
/*  319 */     copperSaw = (new ItemCustomSaw(copperToolMaterial)).func_77655_b("Copper Saw").func_77656_e(copperUses);
/*  320 */     wroughtIronSaw = (new ItemCustomSaw(ironToolMaterial)).func_77655_b("Wrought Iron Saw").func_77656_e(wroughtIronUses);
/*  321 */     redSteelSaw = (new ItemCustomSaw(redSteelToolMaterial)).func_77655_b("Red Steel Saw").func_77656_e(redSteelUses);
/*  322 */     steelSaw = (new ItemCustomSaw(steelToolMaterial)).func_77655_b("Steel Saw").func_77656_e(steelUses);
/*      */     
/*  324 */     highCarbonBlackSteelIngot = (new ItemIngot(false)).func_77655_b("HC Black Steel Ingot");
/*  325 */     weakBlueSteelIngot = (new ItemIngot(false)).func_77655_b("Weak Blue Steel Ingot");
/*  326 */     weakRedSteelIngot = (new ItemIngot(false)).func_77655_b("Weak Red Steel Ingot");
/*  327 */     weakSteelIngot = (new ItemIngot(false)).func_77655_b("Weak Steel Ingot");
/*  328 */     highCarbonBlueSteelIngot = (new ItemIngot(false)).func_77655_b("HC Blue Steel Ingot");
/*  329 */     highCarbonRedSteelIngot = (new ItemIngot(false)).func_77655_b("HC Red Steel Ingot");
/*  330 */     highCarbonSteelIngot = (new ItemIngot(false)).func_77655_b("HC Steel Ingot");
/*      */     
/*  332 */     oreChunk = (new ItemOre()).setFolder("ore/").func_77655_b("Ore");
/*  333 */     smallOreChunk = (new ItemOreSmall()).func_77655_b("Small Ore");
/*  334 */     powder = (new ItemTerra()).setMetaNames(Global.POWDER).func_77655_b("Powder").func_77637_a(TFCTabs.TFC_MATERIALS);
/*  335 */     logs = (new ItemLogs()).func_77655_b("Log");
/*      */ 
/*      */ 
/*      */     
/*  339 */     igInStoneJavelin = (new ItemJavelin(igInToolMaterial, 60.0F)).func_77655_b("IgIn Stone Javelin");
/*  340 */     sedStoneJavelin = (new ItemJavelin(sedToolMaterial, 60.0F)).func_77655_b("Sed Stone Javelin");
/*  341 */     igExStoneJavelin = (new ItemJavelin(igExToolMaterial, 60.0F)).func_77655_b("IgEx Stone Javelin");
/*  342 */     mMStoneJavelin = (new ItemJavelin(mMToolMaterial, 60.0F)).func_77655_b("MM Stone Javelin");
/*  343 */     copperJavelin = (new ItemJavelin(copperToolMaterial, 80.0F)).func_77655_b("Copper Javelin");
/*  344 */     bismuthBronzeJavelin = (new ItemJavelin(bismuthBronzeToolMaterial, 90.0F)).func_77655_b("Bismuth Bronze Javelin");
/*  345 */     bronzeJavelin = (new ItemJavelin(bronzeToolMaterial, 100.0F)).func_77655_b("Bronze Javelin");
/*  346 */     blackBronzeJavelin = (new ItemJavelin(blackBronzeToolMaterial, 95.0F)).func_77655_b("Black Bronze Javelin");
/*  347 */     wroughtIronJavelin = (new ItemJavelin(ironToolMaterial, 135.0F)).func_77655_b("Wrought Iron Javelin");
/*  348 */     steelJavelin = (new ItemJavelin(steelToolMaterial, 170.0F)).func_77655_b("Steel Javelin");
/*  349 */     blackSteelJavelin = (new ItemJavelin(blackSteelToolMaterial, 205.0F)).func_77655_b("Black Steel Javelin");
/*  350 */     blueSteelJavelin = (new ItemJavelin(blueSteelToolMaterial, 240.0F)).func_77655_b("Blue Steel Javelin");
/*  351 */     redSteelJavelin = (new ItemJavelin(redSteelToolMaterial, 240.0F)).func_77655_b("Red Steel Javelin");
/*      */ 
/*      */     
/*  354 */     igInStoneJavelinHead = (new ItemMiscToolHead(igInToolMaterial)).func_77655_b("IgIn Stone Javelin Head");
/*  355 */     sedStoneJavelinHead = (new ItemMiscToolHead(sedToolMaterial)).func_77655_b("Sed Stone Javelin Head");
/*  356 */     igExStoneJavelinHead = (new ItemMiscToolHead(igExToolMaterial)).func_77655_b("IgEx Stone Javelin Head");
/*  357 */     mMStoneJavelinHead = (new ItemMiscToolHead(mMToolMaterial)).func_77655_b("MM Stone Javelin Head");
/*  358 */     copperJavelinHead = (new ItemMiscToolHead()).func_77655_b("Copper Javelin Head");
/*  359 */     bismuthBronzeJavelinHead = (new ItemMiscToolHead()).func_77655_b("Bismuth Bronze Javelin Head");
/*  360 */     bronzeJavelinHead = (new ItemMiscToolHead()).func_77655_b("Bronze Javelin Head");
/*  361 */     blackBronzeJavelinHead = (new ItemMiscToolHead()).func_77655_b("Black Bronze Javelin Head");
/*  362 */     wroughtIronJavelinHead = (new ItemMiscToolHead()).func_77655_b("Wrought Iron Javelin Head");
/*  363 */     steelJavelinHead = (new ItemMiscToolHead()).func_77655_b("Steel Javelin Head");
/*  364 */     blackSteelJavelinHead = (new ItemMiscToolHead()).func_77655_b("Black Steel Javelin Head");
/*  365 */     blueSteelJavelinHead = (new ItemMiscToolHead()).func_77655_b("Blue Steel Javelin Head");
/*  366 */     redSteelJavelinHead = (new ItemMiscToolHead()).func_77655_b("Red Steel Javelin Head");
/*      */     
/*  368 */     bismuthUnshaped = (new ItemMeltedMetal()).func_77655_b("Bismuth Unshaped");
/*  369 */     bismuthBronzeUnshaped = (new ItemMeltedMetal()).func_77655_b("Bismuth Bronze Unshaped");
/*  370 */     blackBronzeUnshaped = (new ItemMeltedMetal()).func_77655_b("Black Bronze Unshaped");
/*  371 */     blackSteelUnshaped = (new ItemMeltedMetal()).func_77655_b("Black Steel Unshaped");
/*  372 */     blueSteelUnshaped = (new ItemMeltedMetal()).func_77655_b("Blue Steel Unshaped");
/*  373 */     brassUnshaped = (new ItemMeltedMetal()).func_77655_b("Brass Unshaped");
/*  374 */     bronzeUnshaped = (new ItemMeltedMetal()).func_77655_b("Bronze Unshaped");
/*  375 */     copperUnshaped = (new ItemMeltedMetal()).func_77655_b("Copper Unshaped");
/*  376 */     goldUnshaped = (new ItemMeltedMetal()).func_77655_b("Gold Unshaped");
/*  377 */     wroughtIronUnshaped = (new ItemMeltedMetal()).func_77655_b("Wrought Iron Unshaped");
/*  378 */     leadUnshaped = (new ItemMeltedMetal()).func_77655_b("Lead Unshaped");
/*  379 */     nickelUnshaped = (new ItemMeltedMetal()).func_77655_b("Nickel Unshaped");
/*  380 */     pigIronUnshaped = (new ItemMeltedMetal()).func_77655_b("Pig Iron Unshaped");
/*  381 */     platinumUnshaped = (new ItemMeltedMetal()).func_77655_b("Platinum Unshaped");
/*  382 */     redSteelUnshaped = (new ItemMeltedMetal()).func_77655_b("Red Steel Unshaped");
/*  383 */     roseGoldUnshaped = (new ItemMeltedMetal()).func_77655_b("Rose Gold Unshaped");
/*  384 */     silverUnshaped = (new ItemMeltedMetal()).func_77655_b("Silver Unshaped");
/*  385 */     steelUnshaped = (new ItemMeltedMetal()).func_77655_b("Steel Unshaped");
/*  386 */     sterlingSilverUnshaped = (new ItemMeltedMetal()).func_77655_b("Sterling Silver Unshaped");
/*  387 */     tinUnshaped = (new ItemMeltedMetal()).func_77655_b("Tin Unshaped");
/*  388 */     zincUnshaped = (new ItemMeltedMetal()).func_77655_b("Zinc Unshaped");
/*      */ 
/*      */     
/*  391 */     stoneHammer = (new ItemHammer(igInToolMaterial, 60.0F)).func_77655_b("Stone Hammer").func_77656_e(igInStoneUses);
/*  392 */     bismuthBronzeHammer = (new ItemHammer(bismuthBronzeToolMaterial, 90.0F)).func_77655_b("Bismuth Bronze Hammer").func_77656_e(bismuthBronzeUses);
/*  393 */     blackBronzeHammer = (new ItemHammer(blackBronzeToolMaterial, 95.0F)).func_77655_b("Black Bronze Hammer").func_77656_e(blackBronzeUses);
/*  394 */     blackSteelHammer = (new ItemHammer(blackSteelToolMaterial, 205.0F)).func_77655_b("Black Steel Hammer").func_77656_e(blackSteelUses);
/*  395 */     blueSteelHammer = (new ItemHammer(blueSteelToolMaterial, 240.0F)).func_77655_b("Blue Steel Hammer").func_77656_e(blueSteelUses);
/*  396 */     bronzeHammer = (new ItemHammer(bronzeToolMaterial, 100.0F)).func_77655_b("Bronze Hammer").func_77656_e(bronzeUses);
/*  397 */     copperHammer = (new ItemHammer(copperToolMaterial, 80.0F)).func_77655_b("Copper Hammer").func_77656_e(copperUses);
/*  398 */     wroughtIronHammer = (new ItemHammer(ironToolMaterial, 135.0F)).func_77655_b("Wrought Iron Hammer").func_77656_e(wroughtIronUses);
/*  399 */     redSteelHammer = (new ItemHammer(redSteelToolMaterial, 240.0F)).func_77655_b("Red Steel Hammer").func_77656_e(redSteelUses);
/*  400 */     steelHammer = (new ItemHammer(steelToolMaterial, 170.0F)).func_77655_b("Steel Hammer").func_77656_e(steelUses);
/*      */     
/*  402 */     ink = (new ItemTerra()).func_77655_b("Ink").func_77637_a(TFCTabs.TFC_MATERIALS);
/*  403 */     fireStarter = (new ItemFirestarter()).setFolder("tools/").func_77655_b("Firestarter");
/*      */ 
/*      */     
/*  406 */     bismuthBronzePickaxeHead = (new ItemMiscToolHead()).func_77655_b("Bismuth Bronze Pick Head");
/*  407 */     blackBronzePickaxeHead = (new ItemMiscToolHead()).func_77655_b("Black Bronze Pick Head");
/*  408 */     blackSteelPickaxeHead = (new ItemMiscToolHead()).func_77655_b("Black Steel Pick Head");
/*  409 */     blueSteelPickaxeHead = (new ItemMiscToolHead()).func_77655_b("Blue Steel Pick Head");
/*  410 */     bronzePickaxeHead = (new ItemMiscToolHead()).func_77655_b("Bronze Pick Head");
/*  411 */     copperPickaxeHead = (new ItemMiscToolHead()).func_77655_b("Copper Pick Head");
/*  412 */     wroughtIronPickaxeHead = (new ItemMiscToolHead()).func_77655_b("Wrought Iron Pick Head");
/*  413 */     redSteelPickaxeHead = (new ItemMiscToolHead()).func_77655_b("Red Steel Pick Head");
/*  414 */     steelPickaxeHead = (new ItemMiscToolHead()).func_77655_b("Steel Pick Head");
/*      */     
/*  416 */     bismuthBronzeShovelHead = (new ItemMiscToolHead()).func_77655_b("Bismuth Bronze Shovel Head");
/*  417 */     blackBronzeShovelHead = (new ItemMiscToolHead()).func_77655_b("Black Bronze Shovel Head");
/*  418 */     blackSteelShovelHead = (new ItemMiscToolHead()).func_77655_b("Black Steel Shovel Head");
/*  419 */     blueSteelShovelHead = (new ItemMiscToolHead()).func_77655_b("Blue Steel Shovel Head");
/*  420 */     bronzeShovelHead = (new ItemMiscToolHead()).func_77655_b("Bronze Shovel Head");
/*  421 */     copperShovelHead = (new ItemMiscToolHead()).func_77655_b("Copper Shovel Head");
/*  422 */     wroughtIronShovelHead = (new ItemMiscToolHead()).func_77655_b("Wrought Iron Shovel Head");
/*  423 */     redSteelShovelHead = (new ItemMiscToolHead()).func_77655_b("Red Steel Shovel Head");
/*  424 */     steelShovelHead = (new ItemMiscToolHead()).func_77655_b("Steel Shovel Head");
/*      */     
/*  426 */     bismuthBronzeHoeHead = (new ItemMiscToolHead()).func_77655_b("Bismuth Bronze Hoe Head");
/*  427 */     blackBronzeHoeHead = (new ItemMiscToolHead()).func_77655_b("Black Bronze Hoe Head");
/*  428 */     blackSteelHoeHead = (new ItemMiscToolHead()).func_77655_b("Black Steel Hoe Head");
/*  429 */     blueSteelHoeHead = (new ItemMiscToolHead()).func_77655_b("Blue Steel Hoe Head");
/*  430 */     bronzeHoeHead = (new ItemMiscToolHead()).func_77655_b("Bronze Hoe Head");
/*  431 */     copperHoeHead = (new ItemMiscToolHead()).func_77655_b("Copper Hoe Head");
/*  432 */     wroughtIronHoeHead = (new ItemMiscToolHead()).func_77655_b("Wrought Iron Hoe Head");
/*  433 */     redSteelHoeHead = (new ItemMiscToolHead()).func_77655_b("Red Steel Hoe Head");
/*  434 */     steelHoeHead = (new ItemMiscToolHead()).func_77655_b("Steel Hoe Head");
/*      */     
/*  436 */     bismuthBronzeAxeHead = (new ItemMiscToolHead()).func_77655_b("Bismuth Bronze Axe Head");
/*  437 */     blackBronzeAxeHead = (new ItemMiscToolHead()).func_77655_b("Black Bronze Axe Head");
/*  438 */     blackSteelAxeHead = (new ItemMiscToolHead()).func_77655_b("Black Steel Axe Head");
/*  439 */     blueSteelAxeHead = (new ItemMiscToolHead()).func_77655_b("Blue Steel Axe Head");
/*  440 */     bronzeAxeHead = (new ItemMiscToolHead()).func_77655_b("Bronze Axe Head");
/*  441 */     copperAxeHead = (new ItemMiscToolHead()).func_77655_b("Copper Axe Head");
/*  442 */     wroughtIronAxeHead = (new ItemMiscToolHead()).func_77655_b("Wrought Iron Axe Head");
/*  443 */     redSteelAxeHead = (new ItemMiscToolHead()).func_77655_b("Red Steel Axe Head");
/*  444 */     steelAxeHead = (new ItemMiscToolHead()).func_77655_b("Steel Axe Head");
/*      */     
/*  446 */     bismuthBronzeHammerHead = (new ItemMiscToolHead()).func_77655_b("Bismuth Bronze Hammer Head");
/*  447 */     blackBronzeHammerHead = (new ItemMiscToolHead()).func_77655_b("Black Bronze Hammer Head");
/*  448 */     blackSteelHammerHead = (new ItemMiscToolHead()).func_77655_b("Black Steel Hammer Head");
/*  449 */     blueSteelHammerHead = (new ItemMiscToolHead()).func_77655_b("Blue Steel Hammer Head");
/*  450 */     bronzeHammerHead = (new ItemMiscToolHead()).func_77655_b("Bronze Hammer Head");
/*  451 */     copperHammerHead = (new ItemMiscToolHead()).func_77655_b("Copper Hammer Head");
/*  452 */     wroughtIronHammerHead = (new ItemMiscToolHead()).func_77655_b("Wrought Iron Hammer Head");
/*  453 */     redSteelHammerHead = (new ItemMiscToolHead()).func_77655_b("Red Steel Hammer Head");
/*  454 */     steelHammerHead = (new ItemMiscToolHead()).func_77655_b("Steel Hammer Head");
/*      */ 
/*      */     
/*  457 */     bismuthBronzeChiselHead = (new ItemMiscToolHead()).func_77655_b("Bismuth Bronze Chisel Head");
/*  458 */     blackBronzeChiselHead = (new ItemMiscToolHead()).func_77655_b("Black Bronze Chisel Head");
/*  459 */     blackSteelChiselHead = (new ItemMiscToolHead()).func_77655_b("Black Steel Chisel Head");
/*  460 */     blueSteelChiselHead = (new ItemMiscToolHead()).func_77655_b("Blue Steel Chisel Head");
/*  461 */     bronzeChiselHead = (new ItemMiscToolHead()).func_77655_b("Bronze Chisel Head");
/*  462 */     copperChiselHead = (new ItemMiscToolHead()).func_77655_b("Copper Chisel Head");
/*  463 */     wroughtIronChiselHead = (new ItemMiscToolHead()).func_77655_b("Wrought Iron Chisel Head");
/*  464 */     redSteelChiselHead = (new ItemMiscToolHead()).func_77655_b("Red Steel Chisel Head");
/*  465 */     steelChiselHead = (new ItemMiscToolHead()).func_77655_b("Steel Chisel Head");
/*      */     
/*  467 */     bismuthBronzeSwordHead = (new ItemMiscToolHead()).func_77655_b("Bismuth Bronze Sword Blade");
/*  468 */     blackBronzeSwordHead = (new ItemMiscToolHead()).func_77655_b("Black Bronze Sword Blade");
/*  469 */     blackSteelSwordHead = (new ItemMiscToolHead()).func_77655_b("Black Steel Sword Blade");
/*  470 */     blueSteelSwordHead = (new ItemMiscToolHead()).func_77655_b("Blue Steel Sword Blade");
/*  471 */     bronzeSwordHead = (new ItemMiscToolHead()).func_77655_b("Bronze Sword Blade");
/*  472 */     copperSwordHead = (new ItemMiscToolHead()).func_77655_b("Copper Sword Blade");
/*  473 */     wroughtIronSwordHead = (new ItemMiscToolHead()).func_77655_b("Wrought Iron Sword Blade");
/*  474 */     redSteelSwordHead = (new ItemMiscToolHead()).func_77655_b("Red Steel Sword Blade");
/*  475 */     steelSwordHead = (new ItemMiscToolHead()).func_77655_b("Steel Sword Blade");
/*      */     
/*  477 */     bismuthBronzeMaceHead = (new ItemMiscToolHead()).func_77655_b("Bismuth Bronze Mace Head");
/*  478 */     blackBronzeMaceHead = (new ItemMiscToolHead()).func_77655_b("Black Bronze Mace Head");
/*  479 */     blackSteelMaceHead = (new ItemMiscToolHead()).func_77655_b("Black Steel Mace Head");
/*  480 */     blueSteelMaceHead = (new ItemMiscToolHead()).func_77655_b("Blue Steel Mace Head");
/*  481 */     bronzeMaceHead = (new ItemMiscToolHead()).func_77655_b("Bronze Mace Head");
/*  482 */     copperMaceHead = (new ItemMiscToolHead()).func_77655_b("Copper Mace Head");
/*  483 */     wroughtIronMaceHead = (new ItemMiscToolHead()).func_77655_b("Wrought Iron Mace Head");
/*  484 */     redSteelMaceHead = (new ItemMiscToolHead()).func_77655_b("Red Steel Mace Head");
/*  485 */     steelMaceHead = (new ItemMiscToolHead()).func_77655_b("Steel Mace Head");
/*      */     
/*  487 */     bismuthBronzeSawHead = (new ItemMiscToolHead()).func_77655_b("Bismuth Bronze Saw Blade");
/*  488 */     blackBronzeSawHead = (new ItemMiscToolHead()).func_77655_b("Black Bronze Saw Blade");
/*  489 */     blackSteelSawHead = (new ItemMiscToolHead()).func_77655_b("Black Steel Saw Blade");
/*  490 */     blueSteelSawHead = (new ItemMiscToolHead()).func_77655_b("Blue Steel Saw Blade");
/*  491 */     bronzeSawHead = (new ItemMiscToolHead()).func_77655_b("Bronze Saw Blade");
/*  492 */     copperSawHead = (new ItemMiscToolHead()).func_77655_b("Copper Saw Blade");
/*  493 */     wroughtIronSawHead = (new ItemMiscToolHead()).func_77655_b("Wrought Iron Saw Blade");
/*  494 */     redSteelSawHead = (new ItemMiscToolHead()).func_77655_b("Red Steel Saw Blade");
/*  495 */     steelSawHead = (new ItemMiscToolHead()).func_77655_b("Steel Saw Blade");
/*      */     
/*  497 */     highCarbonBlackSteelUnshaped = (new ItemMeltedMetal()).func_77655_b("HC Black Steel Unshaped");
/*  498 */     weakBlueSteelUnshaped = (new ItemMeltedMetal()).func_77655_b("Weak Blue Steel Unshaped");
/*  499 */     highCarbonBlueSteelUnshaped = (new ItemMeltedMetal()).func_77655_b("HC Blue Steel Unshaped");
/*  500 */     weakRedSteelUnshaped = (new ItemMeltedMetal()).func_77655_b("Weak Red Steel Unshaped");
/*  501 */     highCarbonRedSteelUnshaped = (new ItemMeltedMetal()).func_77655_b("HC Red Steel Unshaped");
/*  502 */     weakSteelUnshaped = (new ItemMeltedMetal()).func_77655_b("Weak Steel Unshaped");
/*  503 */     highCarbonSteelUnshaped = (new ItemMeltedMetal()).func_77655_b("HC Steel Unshaped");
/*      */ 
/*      */     
/*  506 */     bismuthBronzeProPickHead = (new ItemMiscToolHead()).func_77655_b("Bismuth Bronze ProPick Head");
/*  507 */     blackBronzeProPickHead = (new ItemMiscToolHead()).func_77655_b("Black Bronze ProPick Head");
/*  508 */     blackSteelProPickHead = (new ItemMiscToolHead()).func_77655_b("Black Steel ProPick Head");
/*  509 */     blueSteelProPickHead = (new ItemMiscToolHead()).func_77655_b("Blue Steel ProPick Head");
/*  510 */     bronzeProPickHead = (new ItemMiscToolHead()).func_77655_b("Bronze ProPick Head");
/*  511 */     copperProPickHead = (new ItemMiscToolHead()).func_77655_b("Copper ProPick Head");
/*  512 */     wroughtIronProPickHead = (new ItemMiscToolHead()).func_77655_b("Wrought Iron ProPick Head");
/*  513 */     redSteelProPickHead = (new ItemMiscToolHead()).func_77655_b("Red Steel ProPick Head");
/*  514 */     steelProPickHead = (new ItemMiscToolHead()).func_77655_b("Steel ProPick Head");
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  519 */     bismuthBronzeScythe = (new ItemCustomScythe(bismuthBronzeToolMaterial)).func_77655_b("Bismuth Bronze Scythe").func_77656_e(bismuthBronzeUses);
/*  520 */     blackBronzeScythe = (new ItemCustomScythe(blackBronzeToolMaterial)).func_77655_b("Black Bronze Scythe").func_77656_e(blackBronzeUses);
/*  521 */     blackSteelScythe = (new ItemCustomScythe(blackSteelToolMaterial)).func_77655_b("Black Steel Scythe").func_77656_e(blackSteelUses);
/*  522 */     blueSteelScythe = (new ItemCustomScythe(blueSteelToolMaterial)).func_77655_b("Blue Steel Scythe").func_77656_e(blueSteelUses);
/*  523 */     bronzeScythe = (new ItemCustomScythe(bronzeToolMaterial)).func_77655_b("Bronze Scythe").func_77656_e(bronzeUses);
/*  524 */     copperScythe = (new ItemCustomScythe(copperToolMaterial)).func_77655_b("Copper Scythe").func_77656_e(copperUses);
/*  525 */     wroughtIronScythe = (new ItemCustomScythe(ironToolMaterial)).func_77655_b("Wrought Iron Scythe").func_77656_e(wroughtIronUses);
/*  526 */     redSteelScythe = (new ItemCustomScythe(redSteelToolMaterial)).func_77655_b("Red Steel Scythe").func_77656_e(redSteelUses);
/*  527 */     steelScythe = (new ItemCustomScythe(steelToolMaterial)).func_77655_b("Steel Scythe").func_77656_e(steelUses);
/*      */     
/*  529 */     bismuthBronzeScytheHead = (new ItemMiscToolHead()).func_77655_b("Bismuth Bronze Scythe Blade");
/*  530 */     blackBronzeScytheHead = (new ItemMiscToolHead()).func_77655_b("Black Bronze Scythe Blade");
/*  531 */     blackSteelScytheHead = (new ItemMiscToolHead()).func_77655_b("Black Steel Scythe Blade");
/*  532 */     blueSteelScytheHead = (new ItemMiscToolHead()).func_77655_b("Blue Steel Scythe Blade");
/*  533 */     bronzeScytheHead = (new ItemMiscToolHead()).func_77655_b("Bronze Scythe Blade");
/*  534 */     copperScytheHead = (new ItemMiscToolHead()).func_77655_b("Copper Scythe Blade");
/*  535 */     wroughtIronScytheHead = (new ItemMiscToolHead()).func_77655_b("Wrought Iron Scythe Blade");
/*  536 */     redSteelScytheHead = (new ItemMiscToolHead()).func_77655_b("Red Steel Scythe Blade");
/*  537 */     steelScytheHead = (new ItemMiscToolHead()).func_77655_b("Steel Scythe Blade");
/*      */     
/*  539 */     woodenBucketEmpty = (new ItemCustomBucket(Blocks.field_150350_a)).func_77655_b("Wooden Bucket Empty");
/*  540 */     woodenBucketWater = (new ItemCustomBucket(TFCBlocks.freshWater, woodenBucketEmpty)).func_77655_b("Wooden Bucket Water");
/*  541 */     woodenBucketSaltWater = (new ItemCustomBucket(TFCBlocks.saltWater, woodenBucketEmpty)).func_77655_b("Wooden Bucket Salt Water");
/*  542 */     woodenBucketMilk = (new ItemCustomBucketMilk()).func_77655_b("Wooden Bucket Milk").func_77642_a(woodenBucketEmpty).func_77637_a(TFCTabs.TFC_FOODS);
/*      */     
/*  544 */     bismuthBronzeKnifeHead = (new ItemMiscToolHead()).func_77655_b("Bismuth Bronze Knife Blade");
/*  545 */     blackBronzeKnifeHead = (new ItemMiscToolHead()).func_77655_b("Black Bronze Knife Blade");
/*  546 */     blackSteelKnifeHead = (new ItemMiscToolHead()).func_77655_b("Black Steel Knife Blade");
/*  547 */     blueSteelKnifeHead = (new ItemMiscToolHead()).func_77655_b("Blue Steel Knife Blade");
/*  548 */     bronzeKnifeHead = (new ItemMiscToolHead()).func_77655_b("Bronze Knife Blade");
/*  549 */     copperKnifeHead = (new ItemMiscToolHead()).func_77655_b("Copper Knife Blade");
/*  550 */     wroughtIronKnifeHead = (new ItemMiscToolHead()).func_77655_b("Wrought Iron Knife Blade");
/*  551 */     redSteelKnifeHead = (new ItemMiscToolHead()).func_77655_b("Red Steel Knife Blade");
/*  552 */     steelKnifeHead = (new ItemMiscToolHead()).func_77655_b("Steel Knife Blade");
/*      */     
/*  554 */     bismuthBronzeKnife = (new ItemKnife(bismuthBronzeToolMaterial, 155.0F)).func_77655_b("Bismuth Bronze Knife").func_77656_e(bismuthBronzeUses);
/*  555 */     blackBronzeKnife = (new ItemKnife(blackBronzeToolMaterial, 165.0F)).func_77655_b("Black Bronze Knife").func_77656_e(blackBronzeUses);
/*  556 */     blackSteelKnife = (new ItemKnife(blackSteelToolMaterial, 205.0F)).func_77655_b("Black Steel Knife").func_77656_e(blackSteelUses);
/*  557 */     blueSteelKnife = (new ItemKnife(blueSteelToolMaterial, 250.0F)).func_77655_b("Blue Steel Knife").func_77656_e(blueSteelUses);
/*  558 */     bronzeKnife = (new ItemKnife(bronzeToolMaterial, 150.0F)).func_77655_b("Bronze Knife").func_77656_e(bronzeUses);
/*  559 */     copperKnife = (new ItemKnife(copperToolMaterial, 100.0F)).func_77655_b("Copper Knife").func_77656_e(copperUses);
/*  560 */     wroughtIronKnife = (new ItemKnife(ironToolMaterial, 175.0F)).func_77655_b("Wrought Iron Knife").func_77656_e(wroughtIronUses);
/*  561 */     redSteelKnife = (new ItemKnife(redSteelToolMaterial, 250.0F)).func_77655_b("Red Steel Knife").func_77656_e(redSteelUses);
/*  562 */     steelKnife = (new ItemKnife(steelToolMaterial, 200.0F)).func_77655_b("Steel Knife").func_77656_e(steelUses);
/*      */     
/*  564 */     flatRock = (new ItemFlatGeneric()).setFolder("rocks/flatrocks/").setMetaNames(Global.STONE_ALL).func_77655_b("FlatRock");
/*  565 */     looseRock = (new ItemLooseRock()).setSpecialCraftingType(flatRock).setFolder("rocks/").setMetaNames(Global.STONE_ALL).func_77655_b("LooseRock");
/*      */     
/*  567 */     igInStoneShovelHead = (new ItemMiscToolHead(igInToolMaterial)).func_77655_b("IgIn Stone Shovel Head");
/*  568 */     sedStoneShovelHead = (new ItemMiscToolHead(sedToolMaterial)).func_77655_b("Sed Stone Shovel Head");
/*  569 */     igExStoneShovelHead = (new ItemMiscToolHead(igExToolMaterial)).func_77655_b("IgEx Stone Shovel Head");
/*  570 */     mMStoneShovelHead = (new ItemMiscToolHead(mMToolMaterial)).func_77655_b("MM Stone Shovel Head");
/*      */     
/*  572 */     igInStoneAxeHead = (new ItemMiscToolHead(igInToolMaterial)).func_77655_b("IgIn Stone Axe Head");
/*  573 */     sedStoneAxeHead = (new ItemMiscToolHead(sedToolMaterial)).func_77655_b("Sed Stone Axe Head");
/*  574 */     igExStoneAxeHead = (new ItemMiscToolHead(igExToolMaterial)).func_77655_b("IgEx Stone Axe Head");
/*  575 */     mMStoneAxeHead = (new ItemMiscToolHead(mMToolMaterial)).func_77655_b("MM Stone Axe Head");
/*      */     
/*  577 */     igInStoneHoeHead = (new ItemMiscToolHead(igInToolMaterial)).func_77655_b("IgIn Stone Hoe Head");
/*  578 */     sedStoneHoeHead = (new ItemMiscToolHead(sedToolMaterial)).func_77655_b("Sed Stone Hoe Head");
/*  579 */     igExStoneHoeHead = (new ItemMiscToolHead(igExToolMaterial)).func_77655_b("IgEx Stone Hoe Head");
/*  580 */     mMStoneHoeHead = (new ItemMiscToolHead(mMToolMaterial)).func_77655_b("MM Stone Hoe Head");
/*      */     
/*  582 */     stoneKnifeHead = (new ItemMiscToolHead(igInToolMaterial)).func_77655_b("Stone Knife Blade");
/*  583 */     stoneHammerHead = (new ItemMiscToolHead(igInToolMaterial)).func_77655_b("Stone Hammer Head");
/*      */     
/*  585 */     stoneKnife = (new ItemKnife(igExToolMaterial, 40.0F)).func_77655_b("Stone Knife").func_77656_e(igExStoneUses);
/*  586 */     singlePlank = (new ItemPlank()).func_77655_b("SinglePlank");
/*      */     
/*  588 */     redSteelBucketEmpty = (new ItemSteelBucketRed(Blocks.field_150350_a)).func_77655_b("Red Steel Bucket Empty");
/*  589 */     redSteelBucketWater = (new ItemSteelBucketRed(TFCBlocks.freshWater)).func_77655_b("Red Steel Bucket Water").func_77642_a(redSteelBucketEmpty);
/*  590 */     redSteelBucketSaltWater = (new ItemSteelBucketRed(TFCBlocks.saltWater)).func_77655_b("Red Steel Bucket Salt Water").func_77642_a(redSteelBucketEmpty);
/*      */     
/*  592 */     blueSteelBucketEmpty = (new ItemSteelBucketBlue(Blocks.field_150350_a)).func_77655_b("Blue Steel Bucket Empty");
/*  593 */     blueSteelBucketLava = (new ItemSteelBucketBlue(TFCBlocks.lava)).func_77655_b("Blue Steel Bucket Lava").func_77642_a(blueSteelBucketEmpty);
/*      */     
/*  595 */     quern = (Item)((ItemTerra)(new ItemTerra()).func_77655_b("Quern").func_77656_e(250)).setSize(EnumSize.MEDIUM).setWeight(EnumWeight.HEAVY);
/*  596 */     flintSteel = (new ItemFlintSteel()).func_77655_b("flintAndSteel").func_77656_e(200).func_111206_d("flint_and_steel");
/*      */     
/*  598 */     doorOak = (new ItemWoodDoor(0)).func_77655_b("Oak Door");
/*  599 */     doorAspen = (new ItemWoodDoor(1)).func_77655_b("Aspen Door");
/*  600 */     doorBirch = (new ItemWoodDoor(2)).func_77655_b("Birch Door");
/*  601 */     doorChestnut = (new ItemWoodDoor(3)).func_77655_b("Chestnut Door");
/*  602 */     doorDouglasFir = (new ItemWoodDoor(4)).func_77655_b("Douglas Fir Door");
/*  603 */     doorHickory = (new ItemWoodDoor(5)).func_77655_b("Hickory Door");
/*  604 */     doorMaple = (new ItemWoodDoor(6)).func_77655_b("Maple Door");
/*  605 */     doorAsh = (new ItemWoodDoor(7)).func_77655_b("Ash Door");
/*  606 */     doorPine = (new ItemWoodDoor(8)).func_77655_b("Pine Door");
/*  607 */     doorSequoia = (new ItemWoodDoor(9)).func_77655_b("Sequoia Door");
/*  608 */     doorSpruce = (new ItemWoodDoor(10)).func_77655_b("Spruce Door");
/*  609 */     doorSycamore = (new ItemWoodDoor(11)).func_77655_b("Sycamore Door");
/*  610 */     doorWhiteCedar = (new ItemWoodDoor(12)).func_77655_b("White Cedar Door");
/*  611 */     doorWhiteElm = (new ItemWoodDoor(13)).func_77655_b("White Elm Door");
/*  612 */     doorWillow = (new ItemWoodDoor(14)).func_77655_b("Willow Door");
/*  613 */     doorKapok = (new ItemWoodDoor(15)).func_77655_b("Kapok Door");
/*  614 */     doorAcacia = (new ItemWoodDoor(16)).func_77655_b("Acacia Door");
/*      */ 
/*      */     
/*  617 */     doorWattle = (new ItemWoodDoor(-1)).func_77655_b("Wattle Door");
/*      */     
/*  619 */     beer = (new ItemAlcohol()).func_77655_b("Beer").func_77637_a(TFCTabs.TFC_FOODS);
/*  620 */     cider = (new ItemAlcohol()).func_77655_b("Cider").func_77637_a(TFCTabs.TFC_FOODS);
/*  621 */     rum = (new ItemAlcohol()).func_77655_b("Rum").func_77637_a(TFCTabs.TFC_FOODS);
/*  622 */     ryeWhiskey = (new ItemAlcohol()).func_77655_b("RyeWhiskey").func_77637_a(TFCTabs.TFC_FOODS);
/*  623 */     sake = (new ItemAlcohol()).func_77655_b("Sake").func_77637_a(TFCTabs.TFC_FOODS);
/*  624 */     vodka = (new ItemAlcohol()).func_77655_b("Vodka").func_77637_a(TFCTabs.TFC_FOODS);
/*  625 */     whiskey = (new ItemAlcohol()).func_77655_b("Whiskey").func_77637_a(TFCTabs.TFC_FOODS);
/*  626 */     cornWhiskey = (new ItemAlcohol()).func_77655_b("CornWhiskey").func_77637_a(TFCTabs.TFC_FOODS);
/*      */     
/*  628 */     blueprint = (Item)new ItemBlueprint();
/*  629 */     nametag = (Item)new ItemCustomNameTag();
/*      */     
/*  631 */     woolYarn = (new ItemYarn()).func_77655_b("WoolYarn").func_77637_a(TFCTabs.TFC_MATERIALS);
/*  632 */     wool = (new ItemTerra()).func_77655_b("Wool").func_77637_a(TFCTabs.TFC_MATERIALS);
/*  633 */     woolCloth = (new ItemTerra()).func_77655_b("WoolCloth").func_77637_a(TFCTabs.TFC_MATERIALS);
/*  634 */     silkCloth = (new ItemTerra()).func_77655_b("SilkCloth").func_77637_a(TFCTabs.TFC_MATERIALS);
/*  635 */     burlapCloth = (new ItemTerra()).func_77655_b("BurlapCloth").func_77637_a(TFCTabs.TFC_MATERIALS);
/*  636 */     spindle = (new ItemSpindle()).func_77655_b("Spindle").func_77637_a(TFCTabs.TFC_POTTERY);
/*      */ 
/*      */     
/*  639 */     spindleHead = (new ItemPotteryBase()).setMetaNames(new String[] { "Clay Spindle", "Spindle Head" }).func_77655_b("Spindle Head").func_77637_a(TFCTabs.TFC_POTTERY);
/*  640 */     stoneBrick = (new ItemStoneBrick()).setFolder("tools/").func_77655_b("ItemStoneBrick");
/*  641 */     mortar = (new ItemTerra()).setFolder("tools/").func_77655_b("Mortar").func_77637_a(TFCTabs.TFC_MATERIALS);
/*  642 */     vinegar = (new ItemCustomBucket(Blocks.field_150350_a)).setFolder("food/").func_77655_b("Vinegar").func_77642_a(woodenBucketEmpty).func_77637_a(TFCTabs.TFC_FOODS);
/*  643 */     hide = (new ItemRawHide()).setFolder("tools/").func_77655_b("Hide").func_77637_a(TFCTabs.TFC_MATERIALS);
/*  644 */     soakedHide = (new ItemRawHide()).setFolder("tools/").func_77655_b("Soaked Hide").func_77637_a(TFCTabs.TFC_MATERIALS);
/*  645 */     scrapedHide = (new ItemRawHide()).setFolder("tools/").func_77655_b("Scraped Hide").func_77637_a(TFCTabs.TFC_MATERIALS);
/*  646 */     prepHide = (new ItemRawHide()).setFolder("tools/").setFolder("tools/").func_77655_b("Prep Hide").func_77637_a(TFCTabs.TFC_MATERIALS);
/*      */     
/*  648 */     sheepSkin = (new ItemRawHide()).setFolder("tools/").func_77655_b("Sheep Skin").func_77637_a(TFCTabs.TFC_MATERIALS);
/*  649 */     flatLeather = (new ItemFlatGeneric()).setFolder("tools/").func_77655_b("Flat Leather");
/*  650 */     leather = (new ItemLeather()).setSpecialCraftingType(flatLeather).setFolder("tools/").func_77655_b("TFC Leather");
/*      */     
/*  652 */     straw = (new ItemTerra()).setFolder("plants/").func_77655_b("Straw").func_77637_a(TFCTabs.TFC_MATERIALS);
/*      */     
/*  654 */     flatClay = (new ItemFlatGeneric()).setFolder("pottery/").setMetaNames(new String[] { "clay flat light", "clay flat dark", "clay flat fire", "clay flat dark fire" }).func_77655_b("Flat Clay");
/*      */     
/*  656 */     potteryJug = (new ItemPotteryJug()).func_77655_b("Jug");
/*  657 */     potterySmallVessel = (new ItemPotterySmallVessel()).func_77655_b("Small Vessel");
/*      */     
/*  659 */     ceramicMold = (new ItemPotteryBase()).setMetaNames(new String[] { "Clay Mold", "Ceramic Mold" }).func_77655_b("Mold");
/*  660 */     potteryBowl = (new ItemPotteryBase()).setMetaNames(new String[] { "Clay Bowl", "Ceramic Bowl" }).func_77655_b("ClayBowl");
/*  661 */     clayBall = (new ItemClay()).setSpecialCraftingType(flatClay, new ItemStack(flatClay, 1, 1)).setMetaNames(new String[] { "Clay", "Fire Clay" }).func_77655_b("Clay");
/*  662 */     fireBrick = (new ItemPotteryBase()).setMetaNames(new String[] { "Clay Fire Brick", "Fire Brick" }).func_77655_b("Fire Brick");
/*      */     
/*  664 */     paraffin = (Item)((ItemTerra)(new ItemTerra()).func_77655_b("Paraffin").func_77637_a(TFCTabs.TFC_MATERIALS)).setSize(EnumSize.SMALL);
/*      */ 
/*      */     
/*  667 */     clayMoldAxe = (new ItemPotteryMold()).setMetaNames(new String[] { "Clay Mold Axe", "Ceramic Mold Axe", "Ceramic Mold Axe Copper", "Ceramic Mold Axe Bronze", "Ceramic Mold Axe Bismuth Bronze", "Ceramic Mold Axe Black Bronze" }).func_77655_b("Axe Mold");
/*      */     
/*  669 */     clayMoldChisel = (new ItemPotteryMold()).setMetaNames(new String[] { "Clay Mold Chisel", "Ceramic Mold Chisel", "Ceramic Mold Chisel Copper", "Ceramic Mold Chisel Bronze", "Ceramic Mold Chisel Bismuth Bronze", "Ceramic Mold Chisel Black Bronze" }).func_77655_b("Chisel Mold");
/*      */     
/*  671 */     clayMoldHammer = (new ItemPotteryMold()).setMetaNames(new String[] { "Clay Mold Hammer", "Ceramic Mold Hammer", "Ceramic Mold Hammer Copper", "Ceramic Mold Hammer Bronze", "Ceramic Mold Hammer Bismuth Bronze", "Ceramic Mold Hammer Black Bronze" }).func_77655_b("Hammer Mold");
/*      */     
/*  673 */     clayMoldHoe = (new ItemPotteryMold()).setMetaNames(new String[] { "Clay Mold Hoe", "Ceramic Mold Hoe", "Ceramic Mold Hoe Copper", "Ceramic Mold Hoe Bronze", "Ceramic Mold Hoe Bismuth Bronze", "Ceramic Mold Hoe Black Bronze" }).func_77655_b("Hoe Mold");
/*      */     
/*  675 */     clayMoldKnife = (new ItemPotteryMold()).setMetaNames(new String[] { "Clay Mold Knife", "Ceramic Mold Knife", "Ceramic Mold Knife Copper", "Ceramic Mold Knife Bronze", "Ceramic Mold Knife Bismuth Bronze", "Ceramic Mold Knife Black Bronze" }).func_77655_b("Knife Mold");
/*      */     
/*  677 */     clayMoldMace = (new ItemPotteryMold()).setMetaNames(new String[] { "Clay Mold Mace", "Ceramic Mold Mace", "Ceramic Mold Mace Copper", "Ceramic Mold Mace Bronze", "Ceramic Mold Mace Bismuth Bronze", "Ceramic Mold Mace Black Bronze" }).func_77655_b("Mace Mold");
/*      */     
/*  679 */     clayMoldPick = (new ItemPotteryMold()).setMetaNames(new String[] { "Clay Mold Pick", "Ceramic Mold Pick", "Ceramic Mold Pick Copper", "Ceramic Mold Pick Bronze", "Ceramic Mold Pick Bismuth Bronze", "Ceramic Mold Pick Black Bronze" }).func_77655_b("Pick Mold");
/*      */     
/*  681 */     clayMoldProPick = (new ItemPotteryMold()).setMetaNames(new String[] { "Clay Mold ProPick", "Ceramic Mold ProPick", "Ceramic Mold ProPick Copper", "Ceramic Mold ProPick Bronze", "Ceramic Mold ProPick Bismuth Bronze", "Ceramic Mold ProPick Black Bronze" }).func_77655_b("ProPick Mold");
/*      */     
/*  683 */     clayMoldSaw = (new ItemPotteryMold()).setMetaNames(new String[] { "Clay Mold Saw", "Ceramic Mold Saw", "Ceramic Mold Saw Copper", "Ceramic Mold Saw Bronze", "Ceramic Mold Saw Bismuth Bronze", "Ceramic Mold Saw Black Bronze" }).func_77655_b("Saw Mold");
/*      */     
/*  685 */     clayMoldScythe = (new ItemPotteryMold()).setMetaNames(new String[] { "Clay Mold Scythe", "Ceramic Mold Scythe", "Ceramic Mold Scythe Copper", "Ceramic Mold Scythe Bronze", "Ceramic Mold Scythe Bismuth Bronze", "Ceramic Mold Scythe Black Bronze" }).func_77655_b("Scythe Mold");
/*      */     
/*  687 */     clayMoldShovel = (new ItemPotteryMold()).setMetaNames(new String[] { "Clay Mold Shovel", "Ceramic Mold Shovel", "Ceramic Mold Shovel Copper", "Ceramic Mold Shovel Bronze", "Ceramic Mold Shovel Bismuth Bronze", "Ceramic Mold Shovel Black Bronze" }).func_77655_b("Shovel Mold");
/*      */     
/*  689 */     clayMoldSword = (new ItemPotteryMold()).setMetaNames(new String[] { "Clay Mold Sword", "Ceramic Mold Sword", "Ceramic Mold Sword Copper", "Ceramic Mold Sword Bronze", "Ceramic Mold Sword Bismuth Bronze", "Ceramic Mold Sword Black Bronze" }).func_77655_b("Sword Mold");
/*      */     
/*  691 */     clayMoldJavelin = (new ItemPotteryMold()).setMetaNames(new String[] { "Clay Mold Javelin", "Ceramic Mold Javelin", "Ceramic Mold Javelin Copper", "Ceramic Mold Javelin Bronze", "Ceramic Mold Javelin Bismuth Bronze", "Ceramic Mold Javelin Black Bronze" }).func_77655_b("Javelin Mold");
/*      */     
/*  693 */     tuyereCopper = (new ItemTuyere(40, 0)).func_77655_b("Copper Tuyere");
/*  694 */     tuyereBronze = (new ItemTuyere(80, 1)).func_77655_b("Bronze Tuyere");
/*  695 */     tuyereBlackBronze = (new ItemTuyere(80, 1)).func_77655_b("Black Bronze Tuyere");
/*  696 */     tuyereBismuthBronze = (new ItemTuyere(80, 1)).func_77655_b("Bismuth Bronze Tuyere");
/*  697 */     tuyereWroughtIron = (new ItemTuyere(120, 2)).func_77655_b("Wrought Iron Tuyere");
/*  698 */     tuyereSteel = (new ItemTuyere(180, 3)).func_77655_b("Steel Tuyere");
/*  699 */     tuyereBlackSteel = (new ItemTuyere(260, 4)).func_77655_b("Black Steel Tuyere");
/*  700 */     tuyereRedSteel = (new ItemTuyere(400, 5)).func_77655_b("Red Steel Tuyere");
/*  701 */     tuyereBlueSteel = (new ItemTuyere(500, 6)).func_77655_b("Blue Steel Tuyere");
/*      */     
/*  703 */     bloom = (new ItemBloom()).setFolder("ingots/").func_77655_b("Iron Bloom");
/*  704 */     rawBloom = (new ItemBloom()).setFolder("ingots/").func_77655_b("Raw Iron Bloom");
/*      */     
/*  706 */     unknownIngot = (new ItemIngot()).func_77655_b("Unknown Ingot");
/*  707 */     unknownUnshaped = (new ItemMeltedMetal()).func_77655_b("Unknown Unshaped");
/*      */     
/*  709 */     jute = (new ItemTerra()).setFolder("plants/").func_77655_b("Jute").func_77637_a(TFCTabs.TFC_MATERIALS);
/*  710 */     juteFiber = (new ItemTerra()).setFolder("plants/").func_77655_b("Jute Fibre").func_77637_a(TFCTabs.TFC_MATERIALS);
/*      */     
/*  712 */     Items.field_151120_aE.func_77637_a(null);
/*  713 */     reeds = (new ItemReeds()).func_77655_b("Reeds").func_77637_a(TFCTabs.TFC_MATERIALS).func_111206_d("reeds");
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  718 */     setupFood();
/*      */     
/*  720 */     fertilizer = (new ItemFertilizer()).func_77655_b("Fertilizer").func_77637_a(TFCTabs.TFC_MATERIALS);
/*      */ 
/*      */     
/*  723 */     setupArmor();
/*      */     
/*  725 */     Recipes.doors = new Item[] { doorOak, doorAspen, doorBirch, doorChestnut, doorDouglasFir, doorHickory, doorMaple, doorAsh, doorPine, doorSequoia, doorSpruce, doorSycamore, doorWhiteCedar, doorWhiteElm, doorWillow, doorKapok, doorAcacia };
/*      */ 
/*      */ 
/*      */     
/*  729 */     Recipes.axes = new Item[] { sedAxe, igInAxe, igExAxe, mMAxe, bismuthBronzeAxe, blackBronzeAxe, blackSteelAxe, blueSteelAxe, bronzeAxe, copperAxe, wroughtIronAxe, redSteelAxe, steelAxe };
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  734 */     Recipes.chisels = new Item[] { bismuthBronzeChisel, blackBronzeChisel, blackSteelChisel, blueSteelChisel, bronzeChisel, copperChisel, wroughtIronChisel, redSteelChisel, steelChisel };
/*      */ 
/*      */ 
/*      */     
/*  738 */     Recipes.saws = new Item[] { bismuthBronzeSaw, blackBronzeSaw, blackSteelSaw, blueSteelSaw, bronzeSaw, copperSaw, wroughtIronSaw, redSteelSaw, steelSaw };
/*      */ 
/*      */ 
/*      */     
/*  742 */     Recipes.knives = new Item[] { stoneKnife, bismuthBronzeKnife, blackBronzeKnife, blackSteelKnife, blueSteelKnife, bronzeKnife, copperKnife, wroughtIronKnife, redSteelKnife, steelKnife };
/*      */ 
/*      */ 
/*      */     
/*  746 */     Recipes.meltedMetal = new Item[] { bismuthUnshaped, bismuthBronzeUnshaped, blackBronzeUnshaped, blackSteelUnshaped, blueSteelUnshaped, brassUnshaped, bronzeUnshaped, copperUnshaped, goldUnshaped, wroughtIronUnshaped, leadUnshaped, nickelUnshaped, pigIronUnshaped, platinumUnshaped, redSteelUnshaped, roseGoldUnshaped, silverUnshaped, steelUnshaped, sterlingSilverUnshaped, tinUnshaped, zincUnshaped, highCarbonSteelUnshaped, weakSteelUnshaped, highCarbonBlackSteelUnshaped, highCarbonBlueSteelUnshaped, highCarbonRedSteelUnshaped, weakBlueSteelUnshaped, weakRedSteelUnshaped };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  754 */     Recipes.hammers = new Item[] { stoneHammer, bismuthBronzeHammer, blackBronzeHammer, blackSteelHammer, blueSteelHammer, bronzeHammer, copperHammer, wroughtIronHammer, redSteelHammer, steelHammer };
/*      */ 
/*      */ 
/*      */     
/*  758 */     Recipes.scythes = new Item[] { bismuthBronzeScythe, blackBronzeScythe, blackSteelScythe, blueSteelScythe, bronzeScythe, copperScythe, wroughtIronScythe, redSteelScythe, steelScythe };
/*      */ 
/*      */ 
/*      */     
/*  762 */     Recipes.picks = new Item[] { bismuthBronzePick, blackBronzePick, blackSteelPick, blueSteelPick, bronzePick, copperPick, wroughtIronPick, redSteelPick, steelPick };
/*      */ 
/*      */ 
/*      */     
/*  766 */     Recipes.proPicks = new Item[] { proPickBismuthBronze, proPickBlackBronze, proPickBlackSteel, proPickBlueSteel, proPickBronze, proPickCopper, proPickIron, proPickRedSteel, proPickSteel };
/*      */ 
/*      */ 
/*      */     
/*  770 */     Recipes.shovels = new Item[] { sedShovel, igInShovel, igExShovel, mMShovel, bismuthBronzeShovel, blackBronzeShovel, blackSteelShovel, blueSteelShovel, bronzeShovel, copperShovel, wroughtIronShovel, redSteelShovel, steelShovel };
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  775 */     Recipes.hoes = new Item[] { sedHoe, igInHoe, igExHoe, mMHoe, bismuthBronzeHoe, blackBronzeHoe, blackSteelHoe, blueSteelHoe, bronzeHoe, copperHoe, wroughtIronHoe, redSteelHoe, steelHoe };
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  780 */     Recipes.swords = new Item[] { bismuthBronzeSword, blackBronzeSword, blackSteelSword, blueSteelSword, bronzeSword, copperSword, wroughtIronSword, redSteelSword, steelSword };
/*      */ 
/*      */ 
/*      */     
/*  784 */     Recipes.maces = new Item[] { bismuthBronzeMace, blackBronzeMace, blackSteelMace, blueSteelMace, bronzeMace, copperMace, wroughtIronMace, redSteelMace, steelMace };
/*      */ 
/*      */ 
/*      */     
/*  788 */     Recipes.javelins = new Item[] { sedStoneJavelin, igInStoneJavelin, igExStoneJavelin, mMStoneJavelin, bismuthBronzeJavelin, blackBronzeJavelin, blackSteelJavelin, blueSteelJavelin, bronzeJavelin, copperJavelin, wroughtIronJavelin, redSteelJavelin, steelJavelin };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  794 */     Recipes.spindle = new Item[] { spindle };
/*      */     
/*  796 */     Recipes.gems = new Item[] { gemAgate, gemAmethyst, gemBeryl, gemDiamond, gemEmerald, gemGarnet, gemJade, gemJasper, gemOpal, gemRuby, gemSapphire, gemTopaz, gemTourmaline };
/*      */ 
/*      */     
/*  799 */     Recipes.seeds = new Item[] { seedsBarley, seedsCabbage, seedsCarrot, seedsGarlic, seedsGreenbean, seedsJute, seedsMaize, seedsOat, seedsOnion, seedsPotato, seedsRedBellPepper, seedsRice, seedsRye, seedsSoybean, seedsSquash, seedsSugarcane, seedsTomato, seedsWheat, seedsYellowBellPepper };
/*      */ 
/*      */ 
/*      */     
/*  803 */     ((TFCTabs)TFCTabs.TFC_BUILDING).setTabIconItemStack(new ItemStack(TFCBlocks.stoneSedBrick));
/*  804 */     ((TFCTabs)TFCTabs.TFC_DECORATION).setTabIconItemStack(new ItemStack(TFCBlocks.flora));
/*  805 */     ((TFCTabs)TFCTabs.TFC_DEVICES).setTabIconItem(sluiceItem);
/*  806 */     ((TFCTabs)TFCTabs.TFC_POTTERY).setTabIconItemStack(new ItemStack(potteryJug, 1, 1));
/*  807 */     ((TFCTabs)TFCTabs.TFC_MISC).setTabIconItem(blueSteelBucketLava);
/*  808 */     ((TFCTabs)TFCTabs.TFC_FOODS).setTabIconItem(redApple);
/*  809 */     ((TFCTabs)TFCTabs.TFC_TOOLS).setTabIconItem(redSteelAxe);
/*  810 */     ((TFCTabs)TFCTabs.TFC_WEAPONS).setTabIconItem(bismuthBronzeSword);
/*  811 */     ((TFCTabs)TFCTabs.TFC_ARMOR).setTabIconItem(bronzeHelmet);
/*  812 */     ((TFCTabs)TFCTabs.TFC_MATERIALS).setTabIconItem(blueSteelIngot);
/*      */     
/*  814 */     registerItems();
/*  815 */     registerMetals();
/*      */     
/*  817 */     TerraFirmaCraft.LOG.info("Finished Loading Items");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void setupFood() {
/*  825 */     foodList = new ArrayList();
/*      */     
/*  827 */     egg = (new ItemEgg()).setSize(EnumSize.SMALL).func_77655_b("egg").func_111206_d("egg").func_77637_a(TFCTabs.TFC_FOODS);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  832 */     porkchopRaw = (new ItemFoodMeat(EnumFoodGroup.Protein, 0, 0, 0, 0, 40, false, false)).setDecayRate(2.5F).setCanSmoke().setHasCookedIcon().setSmokeAbsorbMultiplier(1.0F).func_77655_b("Porkchop");
/*  833 */     fishRaw = (new ItemFoodMeat(EnumFoodGroup.Protein, 0, 0, 0, 0, 40, false, true)).setDecayRate(2.5F).setCanSmoke().setHasCookedIcon().setSmokeAbsorbMultiplier(1.0F).func_77655_b("Fish");
/*  834 */     beefRaw = (new ItemFoodMeat(EnumFoodGroup.Protein, 0, 0, 0, 0, 50, false, false)).setDecayRate(2.5F).setCanSmoke().setHasCookedIcon().setSmokeAbsorbMultiplier(1.0F).func_77655_b("Beef");
/*  835 */     chickenRaw = (new ItemFoodMeat(EnumFoodGroup.Protein, 0, 0, 0, 0, 40, false, false)).setDecayRate(2.5F).setCanSmoke().setHasCookedIcon().setSmokeAbsorbMultiplier(1.0F).func_77655_b("Chicken");
/*  836 */     soybean = (new ItemFoodTFC(EnumFoodGroup.Protein, 10, 0, 0, 0, 40, true)).func_77655_b("Soybeans");
/*  837 */     eggCooked = (new ItemFoodTFC(EnumFoodGroup.Protein, 0, 0, 0, 0, 25)).setDecayRate(2.5F).func_77655_b("Egg Cooked");
/*  838 */     calamariRaw = (new ItemFoodMeat(EnumFoodGroup.Protein, 0, 0, 20, 0, 35, false, false)).setCanSmoke().setHasCookedIcon().setSmokeAbsorbMultiplier(1.0F).setDecayRate(4.0F).func_77655_b("Calamari");
/*  839 */     muttonRaw = (new ItemFoodMeat(EnumFoodGroup.Protein, 0, 0, 0, 0, 40, false, false)).setDecayRate(2.5F).setCanSmoke().setHasCookedIcon().setSmokeAbsorbMultiplier(1.0F).func_77655_b("Mutton");
/*  840 */     venisonRaw = (new ItemFoodMeat(EnumFoodGroup.Protein, 5, 0, 0, 0, 50, false, false)).setDecayRate(2.5F).setCanSmoke().setHasCookedIcon().setSmokeAbsorbMultiplier(1.0F).func_77655_b("Venison");
/*  841 */     horseMeatRaw = (new ItemFoodMeat(EnumFoodGroup.Protein, 0, 0, 0, 0, 40, false, false)).setDecayRate(2.5F).setCanSmoke().setHasCookedIcon().setSmokeAbsorbMultiplier(1.0F).func_77655_b("HorseMeat");
/*      */ 
/*      */     
/*  844 */     cheese = (new ItemFoodTFC(EnumFoodGroup.Dairy, 0, 10, 20, 0, 35)).setDecayRate(0.5F).setCanSmoke().setSmokeAbsorbMultiplier(1.0F).func_77655_b("Cheese");
/*      */ 
/*      */     
/*  847 */     wheatGrain = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 5, 20)).setDecayRate(0.5F).func_77655_b("Wheat Grain");
/*  848 */     barleyGrain = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 10, 20)).setDecayRate(0.5F).func_77655_b("Barley Grain");
/*  849 */     oatGrain = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 5, 20)).setDecayRate(0.5F).func_77655_b("Oat Grain");
/*  850 */     ryeGrain = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 15, 0, 5, 20)).setDecayRate(0.5F).func_77655_b("Rye Grain");
/*  851 */     riceGrain = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 5, 20)).setDecayRate(0.5F).func_77655_b("Rice Grain");
/*  852 */     maizeEar = (new ItemFoodTFC(EnumFoodGroup.Grain, 25, 0, 0, 5, 20, true)).func_77655_b("Maize Ear");
/*      */     
/*  854 */     wheatWhole = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 5, 20, false, false)).setFolder("food/").func_77655_b("Wheat Whole");
/*  855 */     barleyWhole = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 10, 20, false, false)).setFolder("food/").func_77655_b("Barley Whole");
/*  856 */     oatWhole = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 5, 20, false, false)).setFolder("food/").func_77655_b("Oat Whole");
/*  857 */     ryeWhole = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 15, 0, 5, 20, false, false)).setFolder("food/").func_77655_b("Rye Whole");
/*  858 */     riceWhole = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 5, 20, false, false)).setFolder("food/").func_77655_b("Rice Whole");
/*      */     
/*  860 */     wheatGround = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 0, 20, false, false)).setFolder("food/").func_77655_b("Wheat Ground");
/*  861 */     barleyGround = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 5, 20, false, false)).setFolder("food/").func_77655_b("Barley Ground");
/*  862 */     oatGround = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 0, 20, false, false)).setFolder("food/").func_77655_b("Oat Ground");
/*  863 */     ryeGround = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 15, 0, 0, 20, false, false)).setFolder("food/").func_77655_b("Rye Ground");
/*  864 */     riceGround = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 0, 20, false, false)).setFolder("food/").func_77655_b("Rice Ground");
/*  865 */     cornmealGround = (new ItemFoodTFC(EnumFoodGroup.Grain, 25, 0, 0, 0, 20, false, false)).setFolder("food/").func_77655_b("Cornmeal Ground");
/*      */     
/*  867 */     wheatDough = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 0, 20, false, false)).func_77655_b("Wheat Dough");
/*  868 */     barleyDough = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 5, 20, false, false)).func_77655_b("Barley Dough");
/*  869 */     oatDough = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 0, 20, false, false)).func_77655_b("Oat Dough");
/*  870 */     ryeDough = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 15, 0, 0, 20, false, false)).func_77655_b("Rye Dough");
/*  871 */     riceDough = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 0, 20, false, false)).func_77655_b("Rice Dough");
/*  872 */     cornmealDough = (new ItemFoodTFC(EnumFoodGroup.Grain, 25, 0, 0, 0, 20, false, false)).func_77655_b("Cornmeal Dough");
/*      */     
/*  874 */     wheatBread = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 0, 20)).func_77655_b("Wheat Bread");
/*  875 */     barleyBread = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 5, 20)).func_77655_b("Barley Bread");
/*  876 */     oatBread = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 0, 20)).func_77655_b("Oat Bread");
/*  877 */     ryeBread = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 15, 0, 0, 20)).func_77655_b("Rye Bread");
/*  878 */     riceBread = (new ItemFoodTFC(EnumFoodGroup.Grain, 10, 0, 0, 0, 20)).func_77655_b("Rice Bread");
/*  879 */     cornBread = (new ItemFoodTFC(EnumFoodGroup.Grain, 25, 0, 0, 0, 20)).func_77655_b("Corn Bread");
/*      */ 
/*      */     
/*  882 */     tomato = (new ItemFoodTFC(EnumFoodGroup.Vegetable, 30, 5, 0, 0, 50, true)).func_77655_b("Tomato");
/*  883 */     potato = (new ItemFoodTFC(EnumFoodGroup.Vegetable, 0, 0, 10, 15, 20, true)).func_77655_b("Potato");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  903 */     onion = (new ItemFoodTFC(EnumFoodGroup.Vegetable, 10, 25, 0, 0, 20, true) { public void func_94581_a(IIconRegister registerer) { super.func_94581_a(registerer); this.field_77787_bX = true; this.metaIcons = new IIcon[2]; this.metaIcons[0] = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + func_77658_a().replace("item.", "")); this.metaIcons[1] = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + "Rutabaga"); } public IIcon func_77617_a(int i) { if (i == 1) return this.metaIcons[1];  return super.func_77617_a(i); } }).func_77655_b(TFCOptions.onionsAreGross ? "Rutabaga" : "Onion");
/*  904 */     cabbage = (new ItemFoodTFC(EnumFoodGroup.Vegetable, 10, 0, 0, 0, 30, true)).func_77655_b("Cabbage");
/*  905 */     garlic = (new ItemFoodTFC(EnumFoodGroup.Vegetable, 0, 0, 0, 10, 20, true)).func_77655_b("Garlic");
/*  906 */     carrot = (new ItemFoodTFC(EnumFoodGroup.Vegetable, 20, 0, 0, 0, 20, true)).func_77655_b("Carrot");
/*  907 */     greenbeans = (new ItemFoodTFC(EnumFoodGroup.Vegetable, 20, 0, 0, 0, 20, true)).func_77655_b("Greenbeans");
/*  908 */     greenBellPepper = (new ItemFoodTFC(EnumFoodGroup.Vegetable, 10, 0, 0, 0, 20, true)).func_77655_b("Green Bell Pepper");
/*  909 */     yellowBellPepper = (new ItemFoodTFC(EnumFoodGroup.Vegetable, 15, 0, 0, 0, 20, true)).func_77655_b("Yellow Bell Pepper");
/*  910 */     redBellPepper = (new ItemFoodTFC(EnumFoodGroup.Vegetable, 20, 0, 0, 0, 20, true)).func_77655_b("Red Bell Pepper");
/*  911 */     squash = (new ItemFoodTFC(EnumFoodGroup.Vegetable, 20, 0, 0, 0, 20, true)).func_77655_b("Squash");
/*  912 */     seaWeed = (new ItemFoodTFC(EnumFoodGroup.Vegetable, 0, 0, 10, 10, 10, true)).func_77655_b("Sea Weed");
/*  913 */     sugar = (new ItemFoodTFC(EnumFoodGroup.None, 30, 0, 0, 0, 0, true)).setDecayRate(0.01F).func_77655_b("Sugar");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  934 */     honeycomb = (new ItemFoodTFC(EnumFoodGroup.None, 50, 0, 0, 0, 0, true, true) { public ItemStack func_77654_b(ItemStack is, World world, EntityPlayer player) { super.func_77654_b(is, world, player); if (!world.field_72995_K && isEdible(is)) if (is.func_77942_o()) { float eatAmount = getFoodMaxWeight(is) / 3.0F; if (FoodStatsTFC.reduceFood(is, eatAmount)) is.field_77994_a = 0;  }   return is; } }).setDecayRate(0.01F).setSize(EnumSize.MEDIUM).func_77655_b("Honeycomb");
/*      */ 
/*      */     
/*  937 */     redApple = (new ItemFoodTFC(EnumFoodGroup.Fruit, 25, 5, 0, 10, 0, true)).setDecayRate(2.0F).func_77655_b(Global.FRUIT_META_NAMES[0]);
/*  938 */     banana = (new ItemFoodTFC(EnumFoodGroup.Fruit, 30, 5, 0, 0, 0, true)).setDecayRate(2.0F).func_77655_b(Global.FRUIT_META_NAMES[1]);
/*  939 */     orange = (new ItemFoodTFC(EnumFoodGroup.Fruit, 50, 30, 0, 10, 0, true)).setDecayRate(2.0F).func_77655_b(Global.FRUIT_META_NAMES[2]);
/*  940 */     greenApple = (new ItemFoodTFC(EnumFoodGroup.Fruit, 30, 15, 0, 10, 0, true)).setDecayRate(2.0F).func_77655_b(Global.FRUIT_META_NAMES[3]);
/*  941 */     lemon = (new ItemFoodTFC(EnumFoodGroup.Fruit, 30, 50, 0, 10, 0, true)).setDecayRate(2.0F).func_77655_b(Global.FRUIT_META_NAMES[4]);
/*  942 */     olive = (new ItemFoodTFC(EnumFoodGroup.Fruit, 10, 0, 3, 10, 0, true)).setDecayRate(2.0F).func_77655_b(Global.FRUIT_META_NAMES[5]);
/*  943 */     cherry = (new ItemFoodTFC(EnumFoodGroup.Fruit, 30, 5, 0, 0, 0, true)).setDecayRate(2.0F).func_77655_b(Global.FRUIT_META_NAMES[6]);
/*  944 */     peach = (new ItemFoodTFC(EnumFoodGroup.Fruit, 25, 10, 0, 0, 0, true)).setDecayRate(2.0F).func_77655_b(Global.FRUIT_META_NAMES[7]);
/*  945 */     plum = (new ItemFoodTFC(EnumFoodGroup.Fruit, 20, 15, 0, 0, 0, true)).setDecayRate(2.0F).func_77655_b(Global.FRUIT_META_NAMES[8]);
/*      */     
/*  947 */     wintergreenBerry = (new ItemFoodTFC(EnumFoodGroup.Fruit, 30, 0, 0, 20, 0)).setDecayRate(2.0F).func_77655_b("Wintergreen Berry");
/*  948 */     blueberry = (new ItemFoodTFC(EnumFoodGroup.Fruit, 30, 20, 0, 0, 0)).setDecayRate(2.0F).func_77655_b("Blueberry");
/*  949 */     raspberry = (new ItemFoodTFC(EnumFoodGroup.Fruit, 35, 15, 0, 5, 0)).setDecayRate(2.0F).func_77655_b("Raspberry");
/*  950 */     strawberry = (new ItemFoodTFC(EnumFoodGroup.Fruit, 30, 5, 0, 5, 0)).setDecayRate(2.0F).func_77655_b("Strawberry");
/*  951 */     blackberry = (new ItemFoodTFC(EnumFoodGroup.Fruit, 30, 30, 0, 0, 0)).setDecayRate(2.0F).func_77655_b("Blackberry");
/*  952 */     bunchberry = (new ItemFoodTFC(EnumFoodGroup.Fruit, 20, 5, 0, 0, 0)).setDecayRate(2.0F).func_77655_b("Bunchberry");
/*  953 */     cranberry = (new ItemFoodTFC(EnumFoodGroup.Fruit, 30, 5, 0, 45, 0)).setDecayRate(2.0F).func_77655_b("Cranberry");
/*  954 */     snowberry = (new ItemFoodTFC(EnumFoodGroup.Fruit, 10, 0, 0, 90, 0)).setDecayRate(2.0F).func_77655_b("Snowberry");
/*  955 */     elderberry = (new ItemFoodTFC(EnumFoodGroup.Fruit, 20, 40, 0, 10, 0)).setDecayRate(2.0F).func_77655_b("Elderberry");
/*  956 */     gooseberry = (new ItemFoodTFC(EnumFoodGroup.Fruit, 20, 40, 0, 0, 0)).setDecayRate(2.0F).func_77655_b("Gooseberry");
/*  957 */     cloudberry = (new ItemFoodTFC(EnumFoodGroup.Fruit, 40, 40, 0, 30, 0)).setDecayRate(2.0F).func_77655_b("Cloudberry");
/*      */     
/*  959 */     sandwich = (new ItemSandwich()).func_77655_b("Sandwich");
/*  960 */     salad = (new ItemSalad()).func_77655_b("Salad");
/*      */ 
/*      */     
/*  963 */     sugarcane = (new ItemFoodTFC(EnumFoodGroup.None, 30, 0, 0, 0, 0, false, false)).setDecayRate(0.75F).setFolder("plants/").func_77655_b("Sugarcane");
/*      */ 
/*      */     
/*  966 */     seedsWheat = (new ItemCustomSeeds(0)).func_77655_b("Seeds Wheat");
/*  967 */     seedsMaize = (new ItemCustomSeeds(1)).func_77655_b("Seeds Maize");
/*  968 */     seedsTomato = (new ItemCustomSeeds(2)).func_77655_b("Seeds Tomato");
/*  969 */     seedsBarley = (new ItemCustomSeeds(3)).func_77655_b("Seeds Barley");
/*  970 */     seedsRye = (new ItemCustomSeeds(4)).func_77655_b("Seeds Rye");
/*  971 */     seedsOat = (new ItemCustomSeeds(5)).func_77655_b("Seeds Oat");
/*  972 */     seedsRice = (new ItemCustomSeeds(6)).func_77655_b("Seeds Rice");
/*  973 */     seedsPotato = (new ItemCustomSeeds(7)).func_77655_b("Seeds Potato");
/*  974 */     seedsOnion = (new ItemCustomSeeds(8)).func_77655_b(TFCOptions.onionsAreGross ? "Seeds Rutabaga" : "Seeds Onion");
/*  975 */     seedsCabbage = (new ItemCustomSeeds(9)).func_77655_b("Seeds Cabbage");
/*  976 */     seedsGarlic = (new ItemCustomSeeds(10)).func_77655_b("Seeds Garlic");
/*  977 */     seedsCarrot = (new ItemCustomSeeds(11)).func_77655_b("Seeds Carrot");
/*  978 */     seedsYellowBellPepper = (new ItemCustomSeeds(12)).func_77655_b("Seeds Yellow Bell Pepper");
/*  979 */     seedsRedBellPepper = (new ItemCustomSeeds(13)).func_77655_b("Seeds Red Bell Pepper");
/*  980 */     seedsSoybean = (new ItemCustomSeeds(14)).func_77655_b("Seeds Soybean");
/*  981 */     seedsGreenbean = (new ItemCustomSeeds(15)).func_77655_b("Seeds Greenbean");
/*  982 */     seedsSquash = (new ItemCustomSeeds(16)).func_77655_b("Seeds Squash");
/*  983 */     seedsJute = (new ItemCustomSeeds(17)).func_77655_b("Seeds Jute");
/*  984 */     seedsSugarcane = (new ItemCustomSeeds(18)).func_77655_b("Seeds Sugarcane");
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  989 */     fruitTreeSapling = (new ItemFruitTreeSapling()).func_77655_b("FruitSapling");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void registerMetals() {
/* 1009 */     Global.BISMUTH = new Metal("Bismuth", TFCItems.bismuthUnshaped, TFCItems.bismuthIngot);
/* 1010 */     Global.BISMUTHBRONZE = new Metal("Bismuth Bronze", TFCItems.bismuthBronzeUnshaped, TFCItems.bismuthBronzeIngot);
/* 1011 */     Global.BLACKBRONZE = new Metal("Black Bronze", TFCItems.blackBronzeUnshaped, TFCItems.blackBronzeIngot);
/* 1012 */     Global.BLACKSTEEL = new Metal("Black Steel", TFCItems.blackSteelUnshaped, TFCItems.blackSteelIngot);
/* 1013 */     Global.BLUESTEEL = new Metal("Blue Steel", TFCItems.blueSteelUnshaped, TFCItems.blueSteelIngot);
/* 1014 */     Global.BRASS = new Metal("Brass", TFCItems.brassUnshaped, TFCItems.brassIngot);
/* 1015 */     Global.BRONZE = new Metal("Bronze", TFCItems.bronzeUnshaped, TFCItems.bronzeIngot);
/* 1016 */     Global.COPPER = new Metal("Copper", TFCItems.copperUnshaped, TFCItems.copperIngot);
/* 1017 */     Global.GOLD = new Metal("Gold", TFCItems.goldUnshaped, TFCItems.goldIngot);
/* 1018 */     Global.WROUGHTIRON = new Metal("Wrought Iron", TFCItems.wroughtIronUnshaped, TFCItems.wroughtIronIngot);
/* 1019 */     Global.LEAD = new Metal("Lead", TFCItems.leadUnshaped, TFCItems.leadIngot);
/* 1020 */     Global.NICKEL = new Metal("Nickel", TFCItems.nickelUnshaped, TFCItems.nickelIngot);
/* 1021 */     Global.PIGIRON = new Metal("Pig Iron", TFCItems.pigIronUnshaped, TFCItems.pigIronIngot);
/* 1022 */     Global.PLATINUM = new Metal("Platinum", TFCItems.platinumUnshaped, TFCItems.platinumIngot);
/* 1023 */     Global.REDSTEEL = new Metal("Red Steel", TFCItems.redSteelUnshaped, TFCItems.redSteelIngot);
/* 1024 */     Global.ROSEGOLD = new Metal("Rose Gold", TFCItems.roseGoldUnshaped, TFCItems.roseGoldIngot);
/* 1025 */     Global.SILVER = new Metal("Silver", TFCItems.silverUnshaped, TFCItems.silverIngot);
/* 1026 */     Global.STEEL = new Metal("Steel", TFCItems.steelUnshaped, TFCItems.steelIngot);
/* 1027 */     Global.STERLINGSILVER = new Metal("Sterling Silver", TFCItems.sterlingSilverUnshaped, TFCItems.sterlingSilverIngot);
/* 1028 */     Global.TIN = new Metal("Tin", TFCItems.tinUnshaped, TFCItems.tinIngot);
/* 1029 */     Global.ZINC = new Metal("Zinc", TFCItems.zincUnshaped, TFCItems.zincIngot);
/* 1030 */     Global.WEAKSTEEL = new Metal("Weak Steel", TFCItems.weakSteelUnshaped, TFCItems.weakSteelIngot);
/* 1031 */     Global.HCBLACKSTEEL = new Metal("HC Black Steel", TFCItems.highCarbonBlackSteelUnshaped, TFCItems.highCarbonBlackSteelIngot);
/* 1032 */     Global.WEAKREDSTEEL = new Metal("Weak Red Steel", TFCItems.weakRedSteelUnshaped, TFCItems.weakRedSteelIngot);
/* 1033 */     Global.HCREDSTEEL = new Metal("HC Red Steel", TFCItems.highCarbonRedSteelUnshaped, TFCItems.highCarbonRedSteelIngot);
/* 1034 */     Global.WEAKBLUESTEEL = new Metal("Weak Blue Steel", TFCItems.weakBlueSteelUnshaped, TFCItems.weakBlueSteelIngot);
/* 1035 */     Global.HCBLUESTEEL = new Metal("HC Blue Steel", TFCItems.highCarbonBlueSteelUnshaped, TFCItems.highCarbonBlueSteelIngot);
/* 1036 */     Global.UNKNOWN = new Metal("Unknown", TFCItems.unknownUnshaped, TFCItems.unknownIngot, false);
/*      */     
/* 1038 */     MetalRegistry.instance.addMetal(Global.BISMUTH, Alloy.EnumTier.TierI);
/* 1039 */     MetalRegistry.instance.addMetal(Global.BISMUTHBRONZE, Alloy.EnumTier.TierI);
/* 1040 */     MetalRegistry.instance.addMetal(Global.BLACKBRONZE, Alloy.EnumTier.TierI);
/* 1041 */     MetalRegistry.instance.addMetal(Global.BLACKSTEEL, Alloy.EnumTier.TierV);
/* 1042 */     MetalRegistry.instance.addMetal(Global.BLUESTEEL, Alloy.EnumTier.TierV);
/* 1043 */     MetalRegistry.instance.addMetal(Global.BRASS, Alloy.EnumTier.TierI);
/* 1044 */     MetalRegistry.instance.addMetal(Global.BRONZE, Alloy.EnumTier.TierI);
/* 1045 */     MetalRegistry.instance.addMetal(Global.COPPER, Alloy.EnumTier.TierI);
/* 1046 */     MetalRegistry.instance.addMetal(Global.GOLD, Alloy.EnumTier.TierI);
/* 1047 */     MetalRegistry.instance.addMetal(Global.WROUGHTIRON, Alloy.EnumTier.TierIII);
/* 1048 */     MetalRegistry.instance.addMetal(Global.LEAD, Alloy.EnumTier.TierI);
/* 1049 */     MetalRegistry.instance.addMetal(Global.NICKEL, Alloy.EnumTier.TierI);
/* 1050 */     MetalRegistry.instance.addMetal(Global.PIGIRON, Alloy.EnumTier.TierIV);
/* 1051 */     MetalRegistry.instance.addMetal(Global.PLATINUM, Alloy.EnumTier.TierV);
/* 1052 */     MetalRegistry.instance.addMetal(Global.REDSTEEL, Alloy.EnumTier.TierV);
/* 1053 */     MetalRegistry.instance.addMetal(Global.ROSEGOLD, Alloy.EnumTier.TierI);
/* 1054 */     MetalRegistry.instance.addMetal(Global.SILVER, Alloy.EnumTier.TierI);
/* 1055 */     MetalRegistry.instance.addMetal(Global.STEEL, Alloy.EnumTier.TierIV);
/* 1056 */     MetalRegistry.instance.addMetal(Global.STERLINGSILVER, Alloy.EnumTier.TierI);
/* 1057 */     MetalRegistry.instance.addMetal(Global.TIN, Alloy.EnumTier.TierI);
/* 1058 */     MetalRegistry.instance.addMetal(Global.ZINC, Alloy.EnumTier.TierI);
/* 1059 */     MetalRegistry.instance.addMetal(Global.WEAKSTEEL, Alloy.EnumTier.TierV);
/* 1060 */     MetalRegistry.instance.addMetal(Global.HCBLACKSTEEL, Alloy.EnumTier.TierV);
/* 1061 */     MetalRegistry.instance.addMetal(Global.WEAKREDSTEEL, Alloy.EnumTier.TierV);
/* 1062 */     MetalRegistry.instance.addMetal(Global.HCREDSTEEL, Alloy.EnumTier.TierV);
/* 1063 */     MetalRegistry.instance.addMetal(Global.WEAKBLUESTEEL, Alloy.EnumTier.TierV);
/* 1064 */     MetalRegistry.instance.addMetal(Global.HCBLUESTEEL, Alloy.EnumTier.TierV);
/* 1065 */     MetalRegistry.instance.addMetal(Global.UNKNOWN, Alloy.EnumTier.TierI);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1070 */     Alloy bronze = new Alloy(Global.BRONZE, Alloy.EnumTier.TierI);
/* 1071 */     bronze.addIngred(Global.COPPER, 87.99F, 92.01F);
/* 1072 */     bronze.addIngred(Global.TIN, 7.99F, 12.01F);
/* 1073 */     AlloyManager.INSTANCE.addAlloy(bronze);
/*      */     
/* 1075 */     Alloy brass = new Alloy(Global.BRASS, Alloy.EnumTier.TierI);
/* 1076 */     brass.addIngred(Global.COPPER, 87.99F, 92.01F);
/* 1077 */     brass.addIngred(Global.ZINC, 7.99F, 12.01F);
/* 1078 */     AlloyManager.INSTANCE.addAlloy(brass);
/*      */     
/* 1080 */     Alloy roseGold = new Alloy(Global.ROSEGOLD, Alloy.EnumTier.TierI);
/* 1081 */     roseGold.addIngred(Global.GOLD, 69.99F, 85.01F);
/* 1082 */     roseGold.addIngred(Global.COPPER, 14.99F, 30.01F);
/* 1083 */     AlloyManager.INSTANCE.addAlloy(roseGold);
/*      */     
/* 1085 */     Alloy blackBronze = new Alloy(Global.BLACKBRONZE, Alloy.EnumTier.TierI);
/* 1086 */     blackBronze.addIngred(Global.GOLD, 9.99F, 25.01F);
/* 1087 */     blackBronze.addIngred(Global.COPPER, 49.99F, 70.01F);
/* 1088 */     blackBronze.addIngred(Global.SILVER, 9.99F, 25.01F);
/* 1089 */     AlloyManager.INSTANCE.addAlloy(blackBronze);
/*      */     
/* 1091 */     Alloy bismuthBronze = new Alloy(Global.BISMUTHBRONZE, Alloy.EnumTier.TierI);
/* 1092 */     bismuthBronze.addIngred(Global.ZINC, 19.99F, 30.01F);
/* 1093 */     bismuthBronze.addIngred(Global.COPPER, 49.99F, 65.01F);
/* 1094 */     bismuthBronze.addIngred(Global.BISMUTH, 9.99F, 20.01F);
/* 1095 */     AlloyManager.INSTANCE.addAlloy(bismuthBronze);
/*      */     
/* 1097 */     Alloy sterlingSilver = new Alloy(Global.STERLINGSILVER, Alloy.EnumTier.TierI);
/* 1098 */     sterlingSilver.addIngred(Global.SILVER, 59.99F, 80.01F);
/* 1099 */     sterlingSilver.addIngred(Global.COPPER, 19.99F, 40.01F);
/* 1100 */     AlloyManager.INSTANCE.addAlloy(sterlingSilver);
/*      */     
/* 1102 */     Alloy weakSteel = new Alloy(Global.WEAKSTEEL, Alloy.EnumTier.TierIII);
/* 1103 */     weakSteel.addIngred(Global.STEEL, 49.99F, 70.01F);
/* 1104 */     weakSteel.addIngred(Global.NICKEL, 14.99F, 25.01F);
/* 1105 */     weakSteel.addIngred(Global.BLACKBRONZE, 14.99F, 25.01F);
/* 1106 */     AlloyManager.INSTANCE.addAlloy(weakSteel);
/*      */     
/* 1108 */     Alloy weakRedSteel = new Alloy(Global.WEAKREDSTEEL, Alloy.EnumTier.TierIII);
/* 1109 */     weakRedSteel.addIngred(Global.BLACKSTEEL, 49.99F, 55.01F);
/* 1110 */     weakRedSteel.addIngred(Global.ROSEGOLD, 9.99F, 15.01F);
/* 1111 */     weakRedSteel.addIngred(Global.BRASS, 9.99F, 15.01F);
/* 1112 */     weakRedSteel.addIngred(Global.STEEL, 19.99F, 25.01F);
/* 1113 */     AlloyManager.INSTANCE.addAlloy(weakRedSteel);
/*      */     
/* 1115 */     Alloy weakBlueSteel = new Alloy(Global.WEAKBLUESTEEL, Alloy.EnumTier.TierIII);
/* 1116 */     weakBlueSteel.addIngred(Global.BLACKSTEEL, 49.99F, 55.01F);
/* 1117 */     weakBlueSteel.addIngred(Global.BISMUTHBRONZE, 9.99F, 15.01F);
/* 1118 */     weakBlueSteel.addIngred(Global.STERLINGSILVER, 9.99F, 15.01F);
/* 1119 */     weakBlueSteel.addIngred(Global.STEEL, 19.99F, 25.01F);
/* 1120 */     AlloyManager.INSTANCE.addAlloy(weakBlueSteel);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setupArmor() {
/* 1125 */     String[] names = { "Bismuth Bronze", "Black Bronze", "Black Steel", "Blue Steel", "Bronze", "Copper", "Wrought Iron", "Red Steel", "Steel" };
/* 1126 */     String[] namesNSO = { "Brass", "Gold", "Lead", "Nickel", "Pig Iron", "Platinum", "Silver", "Sterling Silver" };
/* 1127 */     CommonProxy proxy = TerraFirmaCraft.proxy;
/* 1128 */     int i = 0;
/*      */     
/* 1130 */     bismuthSheet = (Item)((ItemMetalSheet)(new ItemMetalSheet(0)).func_77655_b("Bismuth Sheet")).setMetal("Bismuth", 200);
/* 1131 */     bismuthBronzeSheet = (Item)((ItemMetalSheet)(new ItemMetalSheet(1)).func_77655_b("Bismuth Bronze Sheet")).setMetal("Bismuth Bronze", 200);
/* 1132 */     blackBronzeSheet = (Item)((ItemMetalSheet)(new ItemMetalSheet(2)).func_77655_b("Black Bronze Sheet")).setMetal("Black Bronze", 200);
/* 1133 */     blackSteelSheet = (Item)((ItemMetalSheet)(new ItemMetalSheet(3)).func_77655_b("Black Steel Sheet")).setMetal("Black Steel", 200);
/* 1134 */     blueSteelSheet = (Item)((ItemMetalSheet)(new ItemMetalSheet(4)).func_77655_b("Blue Steel Sheet")).setMetal("Blue Steel", 200);
/* 1135 */     bronzeSheet = (Item)((ItemMetalSheet)(new ItemMetalSheet(6)).func_77655_b("Bronze Sheet")).setMetal("Bronze", 200);
/* 1136 */     copperSheet = (Item)((ItemMetalSheet)(new ItemMetalSheet(7)).func_77655_b("Copper Sheet")).setMetal("Copper", 200);
/* 1137 */     wroughtIronSheet = (Item)((ItemMetalSheet)(new ItemMetalSheet(9)).func_77655_b("Wrought Iron Sheet")).setMetal("Wrought Iron", 200);
/* 1138 */     redSteelSheet = (Item)((ItemMetalSheet)(new ItemMetalSheet(14)).func_77655_b("Red Steel Sheet")).setMetal("Red Steel", 200);
/* 1139 */     roseGoldSheet = (Item)((ItemMetalSheet)(new ItemMetalSheet(15)).func_77655_b("Rose Gold Sheet")).setMetal("Rose Gold", 200);
/* 1140 */     steelSheet = (Item)((ItemMetalSheet)(new ItemMetalSheet(17)).func_77655_b("Steel Sheet")).setMetal("Steel", 200);
/* 1141 */     tinSheet = (Item)((ItemMetalSheet)(new ItemMetalSheet(19)).func_77655_b("Tin Sheet")).setMetal("Tin", 200);
/* 1142 */     zincSheet = (Item)((ItemMetalSheet)(new ItemMetalSheet(20)).func_77655_b("Zinc Sheet")).setMetal("Zinc", 200);
/*      */     
/* 1144 */     bismuthSheet2x = (Item)((ItemMetalSheet2x)(new ItemMetalSheet2x(0)).func_77655_b("Bismuth Double Sheet")).setMetal("Bismuth", 400);
/* 1145 */     bismuthBronzeSheet2x = (Item)((ItemMetalSheet2x)(new ItemMetalSheet2x(1)).func_77655_b("Bismuth Bronze Double Sheet")).setMetal("Bismuth Bronze", 400);
/* 1146 */     blackBronzeSheet2x = (Item)((ItemMetalSheet2x)(new ItemMetalSheet2x(2)).func_77655_b("Black Bronze Double Sheet")).setMetal("Black Bronze", 400);
/* 1147 */     blackSteelSheet2x = (Item)((ItemMetalSheet2x)(new ItemMetalSheet2x(3)).func_77655_b("Black Steel Double Sheet")).setMetal("Black Steel", 400);
/* 1148 */     blueSteelSheet2x = (Item)((ItemMetalSheet2x)(new ItemMetalSheet2x(4)).func_77655_b("Blue Steel Double Sheet")).setMetal("Blue Steel", 400);
/* 1149 */     bronzeSheet2x = (Item)((ItemMetalSheet2x)(new ItemMetalSheet2x(6)).func_77655_b("Bronze Double Sheet")).setMetal("Bronze", 400);
/* 1150 */     copperSheet2x = (Item)((ItemMetalSheet2x)(new ItemMetalSheet2x(7)).func_77655_b("Copper Double Sheet")).setMetal("Copper", 400);
/* 1151 */     wroughtIronSheet2x = (Item)((ItemMetalSheet2x)(new ItemMetalSheet2x(9)).func_77655_b("Wrought Iron Double Sheet")).setMetal("Wrought Iron", 400);
/* 1152 */     redSteelSheet2x = (Item)((ItemMetalSheet2x)(new ItemMetalSheet2x(14)).func_77655_b("Red Steel Double Sheet")).setMetal("Red Steel", 400);
/* 1153 */     roseGoldSheet2x = (Item)((ItemMetalSheet2x)(new ItemMetalSheet2x(15)).func_77655_b("Rose Gold Double Sheet")).setMetal("Rose Gold", 400);
/* 1154 */     steelSheet2x = (Item)((ItemMetalSheet2x)(new ItemMetalSheet2x(17)).func_77655_b("Steel Double Sheet")).setMetal("Steel", 400);
/* 1155 */     tinSheet2x = (Item)((ItemMetalSheet2x)(new ItemMetalSheet2x(19)).func_77655_b("Tin Double Sheet")).setMetal("Tin", 400);
/* 1156 */     zincSheet2x = (Item)((ItemMetalSheet2x)(new ItemMetalSheet2x(20)).func_77655_b("Zinc Double Sheet")).setMetal("Zinc", 400);
/*      */     
/* 1158 */     i = 0;
/* 1159 */     brassSheet = (new ItemMetalSheet(5)).setMetal("Brass", 200).func_77655_b(namesNSO[i++] + " Sheet");
/* 1160 */     goldSheet = (new ItemMetalSheet(8)).setMetal("Gold", 200).func_77655_b(namesNSO[i++] + " Sheet");
/* 1161 */     leadSheet = (new ItemMetalSheet(10)).setMetal("Lead", 200).func_77655_b(namesNSO[i++] + " Sheet");
/* 1162 */     nickelSheet = (new ItemMetalSheet(11)).setMetal("Nickel", 200).func_77655_b(namesNSO[i++] + " Sheet");
/* 1163 */     pigIronSheet = (new ItemMetalSheet(12)).setMetal("Pig Iron", 200).func_77655_b(namesNSO[i++] + " Sheet");
/* 1164 */     platinumSheet = (new ItemMetalSheet(13)).setMetal("Platinum", 200).func_77655_b(namesNSO[i++] + " Sheet");
/* 1165 */     silverSheet = (new ItemMetalSheet(16)).setMetal("Silver", 200).func_77655_b(namesNSO[i++] + " Sheet");
/* 1166 */     sterlingSilverSheet = (new ItemMetalSheet(18)).setMetal("Sterling Silver", 200).func_77655_b(namesNSO[i++] + " Sheet");
/*      */     
/* 1168 */     i = 0;
/* 1169 */     brassSheet2x = (new ItemMetalSheet2x(5)).setMetal("Brass", 400).func_77655_b(namesNSO[i++] + " Double Sheet");
/* 1170 */     goldSheet2x = (new ItemMetalSheet2x(8)).setMetal("Gold", 400).func_77655_b(namesNSO[i++] + " Double Sheet");
/* 1171 */     leadSheet2x = (new ItemMetalSheet2x(10)).setMetal("Lead", 400).func_77655_b(namesNSO[i++] + " Double Sheet");
/* 1172 */     nickelSheet2x = (new ItemMetalSheet2x(1)).setMetal("Nickel", 400).func_77655_b(namesNSO[i++] + " Double Sheet");
/* 1173 */     pigIronSheet2x = (new ItemMetalSheet2x(12)).setMetal("Pig Iron", 400).func_77655_b(namesNSO[i++] + " Double Sheet");
/* 1174 */     platinumSheet2x = (new ItemMetalSheet2x(13)).setMetal("Platinum", 400).func_77655_b(namesNSO[i++] + " Double Sheet");
/* 1175 */     silverSheet2x = (new ItemMetalSheet2x(16)).setMetal("Silver", 400).func_77655_b(namesNSO[i++] + " Double Sheet");
/* 1176 */     sterlingSilverSheet2x = (new ItemMetalSheet2x(18)).setMetal("Sterling Silver", 400).func_77655_b(namesNSO[i++] + " Double Sheet");
/*      */     
/* 1178 */     i = 0;
/* 1179 */     bismuthBronzeUnfinishedBoots = (new ItemUnfinishedArmor()).setMetal("Bismuth Bronze", 3).func_77655_b(names[i] + " Unfinished Boots"); i++;
/* 1180 */     blackBronzeUnfinishedBoots = (new ItemUnfinishedArmor()).setMetal("Black Bronze", 3).func_77655_b(names[i] + " Unfinished Boots"); i++;
/* 1181 */     blackSteelUnfinishedBoots = (new ItemUnfinishedArmor()).setMetal("Black Steel", 3).func_77655_b(names[i] + " Unfinished Boots"); i++;
/* 1182 */     blueSteelUnfinishedBoots = (new ItemUnfinishedArmor()).setMetal("Blue Steel", 3).func_77655_b(names[i] + " Unfinished Boots"); i++;
/* 1183 */     bronzeUnfinishedBoots = (new ItemUnfinishedArmor()).setMetal("Bronze", 3).func_77655_b(names[i] + " Unfinished Boots"); i++;
/* 1184 */     copperUnfinishedBoots = (new ItemUnfinishedArmor()).setMetal("Copper", 3).func_77655_b(names[i] + " Unfinished Boots"); i++;
/* 1185 */     wroughtIronUnfinishedBoots = (new ItemUnfinishedArmor()).setMetal("Wrought Iron", 3).func_77655_b(names[i] + " Unfinished Boots"); i++;
/* 1186 */     redSteelUnfinishedBoots = (new ItemUnfinishedArmor()).setMetal("Red Steel", 3).func_77655_b(names[i] + " Unfinished Boots"); i++;
/* 1187 */     steelUnfinishedBoots = (new ItemUnfinishedArmor()).setMetal("Steel", 3).func_77655_b(names[i] + " Unfinished Boots");
/*      */     
/* 1189 */     i = 0;
/* 1190 */     bismuthBronzeBoots = (new ItemTFCArmor(Armor.bismuthBronzePlate, proxy.getArmorRenderID("bismuthbronze"), 3, 50, 0)).func_77655_b(names[i] + " Boots"); i++;
/* 1191 */     blackBronzeBoots = (new ItemTFCArmor(Armor.blackBronzePlate, proxy.getArmorRenderID("blackbronze"), 3, 50, 0)).func_77655_b(names[i] + " Boots"); i++;
/* 1192 */     blackSteelBoots = (new ItemTFCArmor(Armor.blackSteelPlate, proxy.getArmorRenderID("blacksteel"), 3, 50, 0)).func_77655_b(names[i] + " Boots"); i++;
/* 1193 */     blueSteelBoots = (new ItemTFCArmor(Armor.blueSteelPlate, proxy.getArmorRenderID("bluesteel"), 3, 50, 0)).func_77655_b(names[i] + " Boots"); i++;
/* 1194 */     bronzeBoots = (new ItemTFCArmor(Armor.bronzePlate, proxy.getArmorRenderID("bronze"), 3, 50, 0)).func_77655_b(names[i] + " Boots"); i++;
/* 1195 */     copperBoots = (new ItemTFCArmor(Armor.copperPlate, proxy.getArmorRenderID("copper"), 3, 50, 0)).func_77655_b(names[i] + " Boots"); i++;
/* 1196 */     wroughtIronBoots = (new ItemTFCArmor(Armor.wroughtIronPlate, proxy.getArmorRenderID("wroughtiron"), 3, 50, 0)).func_77655_b(names[i] + " Boots"); i++;
/* 1197 */     redSteelBoots = (new ItemTFCArmor(Armor.redSteelPlate, proxy.getArmorRenderID("redsteel"), 3, 50, 0)).func_77655_b(names[i] + " Boots"); i++;
/* 1198 */     steelBoots = (new ItemTFCArmor(Armor.steelPlate, proxy.getArmorRenderID("steel"), 3, 50, 0)).func_77655_b(names[i] + " Boots");
/*      */     
/* 1200 */     i = 0;
/* 1201 */     bismuthBronzeUnfinishedGreaves = (new ItemUnfinishedArmor()).setMetal("Bismuth Bronze", 2).func_77655_b(names[i] + " Unfinished Greaves"); i++;
/* 1202 */     blackBronzeUnfinishedGreaves = (new ItemUnfinishedArmor()).setMetal("Black Bronze", 2).func_77655_b(names[i] + " Unfinished Greaves"); i++;
/* 1203 */     blackSteelUnfinishedGreaves = (new ItemUnfinishedArmor()).setMetal("Black Steel", 2).func_77655_b(names[i] + " Unfinished Greaves"); i++;
/* 1204 */     blueSteelUnfinishedGreaves = (new ItemUnfinishedArmor()).setMetal("Blue Steel", 2).func_77655_b(names[i] + " Unfinished Greaves"); i++;
/* 1205 */     bronzeUnfinishedGreaves = (new ItemUnfinishedArmor()).setMetal("Bronze", 2).func_77655_b(names[i] + " Unfinished Greaves"); i++;
/* 1206 */     copperUnfinishedGreaves = (new ItemUnfinishedArmor()).setMetal("Copper", 2).func_77655_b(names[i] + " Unfinished Greaves"); i++;
/* 1207 */     wroughtIronUnfinishedGreaves = (new ItemUnfinishedArmor()).setMetal("Wrought Iron", 2).func_77655_b(names[i] + " Unfinished Greaves"); i++;
/* 1208 */     redSteelUnfinishedGreaves = (new ItemUnfinishedArmor()).setMetal("Red Steel", 2).func_77655_b(names[i] + " Unfinished Greaves"); i++;
/* 1209 */     steelUnfinishedGreaves = (new ItemUnfinishedArmor()).setMetal("Steel", 2).func_77655_b(names[i] + " Unfinished Greaves");
/*      */     
/* 1211 */     i = 0;
/* 1212 */     bismuthBronzeGreaves = (new ItemTFCArmor(Armor.bismuthBronzePlate, proxy.getArmorRenderID("bismuthbronze"), 2, 50, 1)).func_77655_b(names[i] + " Greaves"); i++;
/* 1213 */     blackBronzeGreaves = (new ItemTFCArmor(Armor.blackBronzePlate, proxy.getArmorRenderID("blackbronze"), 2, 50, 1)).func_77655_b(names[i] + " Greaves"); i++;
/* 1214 */     blackSteelGreaves = (new ItemTFCArmor(Armor.blackSteelPlate, proxy.getArmorRenderID("blacksteel"), 2, 50, 1)).func_77655_b(names[i] + " Greaves"); i++;
/* 1215 */     blueSteelGreaves = (new ItemTFCArmor(Armor.blueSteelPlate, proxy.getArmorRenderID("bluesteel"), 2, 50, 1)).func_77655_b(names[i] + " Greaves"); i++;
/* 1216 */     bronzeGreaves = (new ItemTFCArmor(Armor.bronzePlate, proxy.getArmorRenderID("bronze"), 2, 50, 1)).func_77655_b(names[i] + " Greaves"); i++;
/* 1217 */     copperGreaves = (new ItemTFCArmor(Armor.copperPlate, proxy.getArmorRenderID("copper"), 2, 50, 1)).func_77655_b(names[i] + " Greaves"); i++;
/* 1218 */     wroughtIronGreaves = (new ItemTFCArmor(Armor.wroughtIronPlate, proxy.getArmorRenderID("wroughtiron"), 2, 50, 1)).func_77655_b(names[i] + " Greaves"); i++;
/* 1219 */     redSteelGreaves = (new ItemTFCArmor(Armor.redSteelPlate, proxy.getArmorRenderID("redsteel"), 2, 50, 1)).func_77655_b(names[i] + " Greaves"); i++;
/* 1220 */     steelGreaves = (new ItemTFCArmor(Armor.steelPlate, proxy.getArmorRenderID("steel"), 2, 50, 1)).func_77655_b(names[i] + " Greaves");
/*      */     
/* 1222 */     i = 0;
/* 1223 */     bismuthBronzeUnfinishedChestplate = (new ItemUnfinishedArmor()).setMetal("Bismuth Bronze", 1).func_77655_b(names[i] + " Unfinished Chestplate"); i++;
/* 1224 */     blackBronzeUnfinishedChestplate = (new ItemUnfinishedArmor()).setMetal("Black Bronze", 1).func_77655_b(names[i] + " Unfinished Chestplate"); i++;
/* 1225 */     blackSteelUnfinishedChestplate = (new ItemUnfinishedArmor()).setMetal("Black Steel", 1).func_77655_b(names[i] + " Unfinished Chestplate"); i++;
/* 1226 */     blueSteelUnfinishedChestplate = (new ItemUnfinishedArmor()).setMetal("Blue Steel", 1).func_77655_b(names[i] + " Unfinished Chestplate"); i++;
/* 1227 */     bronzeUnfinishedChestplate = (new ItemUnfinishedArmor()).setMetal("Bronze", 1).func_77655_b(names[i] + " Unfinished Chestplate"); i++;
/* 1228 */     copperUnfinishedChestplate = (new ItemUnfinishedArmor()).setMetal("Copper", 1).func_77655_b(names[i] + " Unfinished Chestplate"); i++;
/* 1229 */     wroughtIronUnfinishedChestplate = (new ItemUnfinishedArmor()).setMetal("Wrought Iron", 1).func_77655_b(names[i] + " Unfinished Chestplate"); i++;
/* 1230 */     redSteelUnfinishedChestplate = (new ItemUnfinishedArmor()).setMetal("Red Steel", 1).func_77655_b(names[i] + " Unfinished Chestplate"); i++;
/* 1231 */     steelUnfinishedChestplate = (new ItemUnfinishedArmor()).setMetal("Steel", 1).func_77655_b(names[i] + " Unfinished Chestplate");
/*      */     
/* 1233 */     i = 0;
/* 1234 */     bismuthBronzeChestplate = (new ItemTFCArmor(Armor.bismuthBronzePlate, proxy.getArmorRenderID("bismuthbronze"), 1, 50, 2)).func_77655_b(names[i] + " Chestplate"); i++;
/* 1235 */     blackBronzeChestplate = (new ItemTFCArmor(Armor.blackBronzePlate, proxy.getArmorRenderID("blackbronze"), 1, 50, 2)).func_77655_b(names[i] + " Chestplate"); i++;
/* 1236 */     blackSteelChestplate = (new ItemTFCArmor(Armor.blackSteelPlate, proxy.getArmorRenderID("blacksteel"), 1, 50, 2)).func_77655_b(names[i] + " Chestplate"); i++;
/* 1237 */     blueSteelChestplate = (new ItemTFCArmor(Armor.blueSteelPlate, proxy.getArmorRenderID("bluesteel"), 1, 50, 2)).func_77655_b(names[i] + " Chestplate"); i++;
/* 1238 */     bronzeChestplate = (new ItemTFCArmor(Armor.bronzePlate, proxy.getArmorRenderID("bronze"), 1, 50, 2)).func_77655_b(names[i] + " Chestplate"); i++;
/* 1239 */     copperChestplate = (new ItemTFCArmor(Armor.copperPlate, proxy.getArmorRenderID("copper"), 1, 50, 2)).func_77655_b(names[i] + " Chestplate"); i++;
/* 1240 */     wroughtIronChestplate = (new ItemTFCArmor(Armor.wroughtIronPlate, proxy.getArmorRenderID("wroughtiron"), 1, 50, 2)).func_77655_b(names[i] + " Chestplate"); i++;
/* 1241 */     redSteelChestplate = (new ItemTFCArmor(Armor.redSteelPlate, proxy.getArmorRenderID("redsteel"), 1, 50, 2)).func_77655_b(names[i] + " Chestplate"); i++;
/* 1242 */     steelChestplate = (new ItemTFCArmor(Armor.steelPlate, proxy.getArmorRenderID("steel"), 1, 50, 2)).func_77655_b(names[i] + " Chestplate");
/*      */     
/* 1244 */     i = 0;
/* 1245 */     bismuthBronzeUnfinishedHelmet = (new ItemUnfinishedArmor()).setMetal("Bismuth Bronze", 0).func_77655_b(names[i] + " Unfinished Helmet"); i++;
/* 1246 */     blackBronzeUnfinishedHelmet = (new ItemUnfinishedArmor()).setMetal("Black Bronze", 0).func_77655_b(names[i] + " Unfinished Helmet"); i++;
/* 1247 */     blackSteelUnfinishedHelmet = (new ItemUnfinishedArmor()).setMetal("Black Steel", 0).func_77655_b(names[i] + " Unfinished Helmet"); i++;
/* 1248 */     blueSteelUnfinishedHelmet = (new ItemUnfinishedArmor()).setMetal("Blue Steel", 0).func_77655_b(names[i] + " Unfinished Helmet"); i++;
/* 1249 */     bronzeUnfinishedHelmet = (new ItemUnfinishedArmor()).setMetal("Bronze", 0).func_77655_b(names[i] + " Unfinished Helmet"); i++;
/* 1250 */     copperUnfinishedHelmet = (new ItemUnfinishedArmor()).setMetal("Copper", 0).func_77655_b(names[i] + " Unfinished Helmet"); i++;
/* 1251 */     wroughtIronUnfinishedHelmet = (new ItemUnfinishedArmor()).setMetal("Wrought Iron", 0).func_77655_b(names[i] + " Unfinished Helmet"); i++;
/* 1252 */     redSteelUnfinishedHelmet = (new ItemUnfinishedArmor()).setMetal("Red Steel", 0).func_77655_b(names[i] + " Unfinished Helmet"); i++;
/* 1253 */     steelUnfinishedHelmet = (new ItemUnfinishedArmor()).setMetal("Steel", 0).func_77655_b(names[i] + " Unfinished Helmet");
/*      */     
/* 1255 */     i = 0;
/* 1256 */     bismuthBronzeHelmet = (new ItemTFCArmor(Armor.bismuthBronzePlate, proxy.getArmorRenderID("bismuthbronze"), 0, 50, 3)).func_77655_b(names[i] + " Helmet"); i++;
/* 1257 */     blackBronzeHelmet = (new ItemTFCArmor(Armor.blackBronzePlate, proxy.getArmorRenderID("blackbronze"), 0, 50, 3)).func_77655_b(names[i] + " Helmet"); i++;
/* 1258 */     blackSteelHelmet = (new ItemTFCArmor(Armor.blackSteelPlate, proxy.getArmorRenderID("blacksteel"), 0, 50, 3)).func_77655_b(names[i] + " Helmet"); i++;
/* 1259 */     blueSteelHelmet = (new ItemTFCArmor(Armor.blueSteelPlate, proxy.getArmorRenderID("bluesteel"), 0, 50, 3)).func_77655_b(names[i] + " Helmet"); i++;
/* 1260 */     bronzeHelmet = (new ItemTFCArmor(Armor.bronzePlate, proxy.getArmorRenderID("bronze"), 0, 50, 3)).func_77655_b(names[i] + " Helmet"); i++;
/* 1261 */     copperHelmet = (new ItemTFCArmor(Armor.copperPlate, proxy.getArmorRenderID("copper"), 0, 50, 3)).func_77655_b(names[i] + " Helmet"); i++;
/* 1262 */     wroughtIronHelmet = (new ItemTFCArmor(Armor.wroughtIronPlate, proxy.getArmorRenderID("wroughtiron"), 0, 50, 3)).func_77655_b(names[i] + " Helmet"); i++;
/* 1263 */     redSteelHelmet = (new ItemTFCArmor(Armor.redSteelPlate, proxy.getArmorRenderID("redsteel"), 0, 50, 3)).func_77655_b(names[i] + " Helmet"); i++;
/* 1264 */     steelHelmet = (new ItemTFCArmor(Armor.steelPlate, proxy.getArmorRenderID("steel"), 0, 50, 3)).func_77655_b(names[i] + " Helmet");
/*      */     
/* 1266 */     leatherHelmet = (new ItemTFCArmor(Armor.leather, proxy.getArmorRenderID("leather"), 0, ItemArmor.ArmorMaterial.CLOTH, 100, 3)).func_77655_b("helmetCloth").func_111206_d("leather_helmet");
/* 1267 */     leatherChestplate = (new ItemTFCArmor(Armor.leather, proxy.getArmorRenderID("leather"), 1, ItemArmor.ArmorMaterial.CLOTH, 100, 2)).func_77655_b("chestplateCloth").func_111206_d("leather_chestplate");
/* 1268 */     leatherLeggings = (new ItemTFCArmor(Armor.leather, proxy.getArmorRenderID("leather"), 2, ItemArmor.ArmorMaterial.CLOTH, 100, 1)).func_77655_b("leggingsCloth").func_111206_d("leather_leggings");
/* 1269 */     leatherBoots = (new ItemTFCArmor(Armor.leather, proxy.getArmorRenderID("leather"), 3, ItemArmor.ArmorMaterial.CLOTH, 100, 0)).func_77655_b("bootsCloth").func_111206_d("leather_boots");
/*      */     
/* 1271 */     quiver = (new ItemQuiver()).func_77655_b("Quiver");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void registerFurnaceFuel() {
/* 1277 */     TFCFuelHandler.registerFuel(blueSteelBucketLava, 20000);
/* 1278 */     TFCFuelHandler.registerFuel(singlePlank, 400);
/* 1279 */     TFCFuelHandler.registerFuel(woodenBucketEmpty, 300);
/* 1280 */     TFCFuelHandler.registerFuel(fireStarter, 100);
/* 1281 */     TFCFuelHandler.registerFuel(logs, 800);
/* 1282 */     TFCFuelHandler.registerFuel(sluiceItem, 300);
/* 1283 */     TFCFuelHandler.registerFuel(rope, 50);
/* 1284 */     TFCFuelHandler.registerFuel(arrow, 20);
/* 1285 */     TFCFuelHandler.registerFuel(bow, 100);
/* 1286 */     TFCFuelHandler.registerFuel(fishingRod, 100);
/* 1287 */     TFCFuelHandler.registerFuel(stick, 100);
/* 1288 */     TFCFuelHandler.registerFuel(coal, 1600);
/* 1289 */     TFCFuelHandler.registerFuel(woolCloth, 20);
/* 1290 */     TFCFuelHandler.registerFuel(silkCloth, 20);
/* 1291 */     TFCFuelHandler.registerFuel(burlapCloth, 20);
/* 1292 */     TFCFuelHandler.registerFuel(straw, 20);
/*      */     
/* 1294 */     for (int l = 0; l < Recipes.doors.length; l++) {
/* 1295 */       TFCFuelHandler.registerFuel(Recipes.doors[l], 300);
/*      */     }
/* 1297 */     TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.woodSupportV), 300);
/* 1298 */     TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.woodSupportV2), 300);
/* 1299 */     TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.woodSupportH), 300);
/* 1300 */     TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.woodSupportH2), 300);
/* 1301 */     TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.fence), 300);
/* 1302 */     TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.fence2), 300);
/* 1303 */     TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.fenceGate), 300);
/* 1304 */     TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.fenceGate2), 300);
/* 1305 */     TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.chest), 300);
/* 1306 */     TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.strawHideBed), 200);
/* 1307 */     TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.thatch), 200);
/* 1308 */     TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.planks), 300);
/* 1309 */     TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.planks2), 300);
/* 1310 */     TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.barrel), 300);
/* 1311 */     TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.torch), 100);
/* 1312 */     TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.sapling), 100);
/* 1313 */     TFCFuelHandler.registerFuel(Item.func_150898_a(TFCBlocks.sapling2), 100);
/*      */   }
/*      */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\ItemSetup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */