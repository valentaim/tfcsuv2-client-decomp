package com.bioxx.tfc.Food;

import com.bioxx.tfc.Core.Player.FoodStatsTFC;
import com.bioxx.tfc.Core.Player.SkillStats;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.Items.ItemTerra;
import com.bioxx.tfc.Render.Item.FoodItemRenderer;
import com.bioxx.tfc.api.Enums.EnumFoodGroup;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import com.bioxx.tfc.api.Food;
import com.bioxx.tfc.api.FoodRegistry;
import com.bioxx.tfc.api.Interfaces.ICookableFood;
import com.bioxx.tfc.api.Interfaces.IFood;
import com.bioxx.tfc.api.Interfaces.IMergeableFood;
import com.bioxx.tfc.api.Interfaces.ISize;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.TFCOptions;
import com.bioxx.tfc.api.TFC_ItemHeat;
import com.bioxx.tfc.api.Util.Helper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;










public class ItemFoodTFC
  extends ItemTerra
  implements ISize, ICookableFood, IMergeableFood
{
  private EnumFoodGroup foodgroup;
  public int foodID;
  public float decayRate = 1.0F;
  
  public boolean edible = true;
  
  public boolean canBeUsedRaw = true;
  
  protected int tasteSweet;
  protected int tasteSour;
  protected int tasteSalty;
  protected int tasteBitter;
  protected int tasteUmami;
  protected boolean canBeSmoked;
  protected float smokeAbsorb;
  public IIcon cookedIcon;
  protected boolean hasCookedIcon;
  
  public ItemFoodTFC(EnumFoodGroup fg, int sw, int so, int sa, int bi, int um) {
    func_77637_a(TFCTabs.TFC_FOODS);
    setFolder("food/");
    this.foodgroup = fg;
    TFCItems.foodList.add(this);
    func_77656_e(100);
    this.field_77787_bX = false;
    this.smokeAbsorb = 0.5F;
    this.tasteSweet = sw;
    this.tasteSour = so;
    this.tasteSalty = sa;
    this.tasteBitter = bi;
    this.tasteUmami = um;
    this.foodID = FoodRegistry.getInstance().registerFood(fg, (Item)this);
  }

  
  public ItemFoodTFC(EnumFoodGroup fg, int sw, int so, int sa, int bi, int um, boolean edible) {
    this(fg, sw, so, sa, bi, um);
    this.edible = edible;
  }

  
  public ItemFoodTFC(EnumFoodGroup fg, int sw, int so, int sa, int bi, int um, boolean edible, boolean usable) {
    this(fg, sw, so, sa, bi, um, edible);
    this.canBeUsedRaw = usable;
  }

  
  public ItemFoodTFC setDecayRate(float f) {
    this.decayRate = f;
    return this;
  }

  
  public ItemFoodTFC setCanSmoke() {
    this.canBeSmoked = true;
    return this;
  }

  
  public ItemFoodTFC setHasCookedIcon() {
    this.hasCookedIcon = true;
    return this;
  }


  
  public void func_94581_a(IIconRegister registerer) {
    super.func_94581_a(registerer);
    if (this.hasCookedIcon) {
      
      String name = func_77658_a();
      this.cookedIcon = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + name.replace("item.", "") + " Cooked");
    } 
    MinecraftForgeClient.registerItemRenderer((Item)this, (IItemRenderer)new FoodItemRenderer());
  }


  
  public IIcon getIcon(ItemStack stack, int pass) {
    if (Food.isCooked(stack) && this.cookedIcon != null)
      return this.cookedIcon; 
    return this.field_77791_bV;
  }


  
  @SideOnly(Side.CLIENT)
  public boolean func_77623_v() {
    return true;
  }






  
  public int getRenderPasses(int metadata) {
    return 1;
  }














  
  public float getDecayRate(ItemStack is) {
    float mult = 1.0F;
    if (Food.isCooked(is)) {
      
      mult *= 0.75F;
      if (Food.isPickled(is) || Food.isSalted(is))
        mult *= 0.75F; 
      if (Food.isSmoked(is)) {
        mult *= 1.0F - 0.25F * getSmokeAbsorbMultiplier();
      }
    } else {
      
      if (Food.isPickled(is) || Food.isSalted(is))
        mult *= 0.5F; 
      if (Food.isSmoked(is))
        mult *= 1.0F - 0.25F * getSmokeAbsorbMultiplier(); 
      if (Food.isDried(is))
        mult *= 0.25F; 
    } 
    return this.decayRate * TFC_Time.getYearRatio(96.0F) * mult;
  }


  
  public void func_150895_a(Item item, CreativeTabs tabs, List<ItemStack> list) {
    list.add(createTag(new ItemStack((Item)this, 1), 160.0F));
  }


  
  public String func_77653_i(ItemStack is) {
    StringBuilder name = new StringBuilder();
    if (is.func_77942_o()) {
      
      if (Food.isPickled(is)) {
        name.append(TFC_Core.translate("word.pickled")).append(' ');
      } else if (Food.isBrined(is) && !Food.isDried(is)) {
        name.append(TFC_Core.translate("word.brined")).append(' ');
      } 
      if (Food.isSalted(is))
        name.append(TFC_Core.translate("word.salted")).append(' '); 
      if (Food.isCooked(is)) {
        name.append(TFC_Core.translate("word.cooked")).append(' ');
      } else if (Food.isSmoked(is)) {
        name.append(TFC_Core.translate("word.smoked")).append(' ');
      } 
      if (Food.isDried(is) && !Food.isCooked(is))
        name.append(TFC_Core.translate("word.dried")).append(' '); 
      if (Food.isInfused(is)) {
        name.append(TFC_Core.translate(Food.getInfusion(is) + ".name")).append(' ');
      }
    } 
    return name.append(TFC_Core.translate(func_77667_c(is) + ".name")).append(getCookedLevelString(is)).toString();
  }

  
  protected String getCookedLevelString(ItemStack is) {
    String s = "";
    if (Food.isCooked(is)) {
      
      s = s + " (";
      int cookedLevel = ((int)Food.getCooked(is) - 600) / 120;
      switch (cookedLevel) {
        case 0:
          s = s + TFC_Core.translate("food.cooked.0"); break;
        case 1: s = s + TFC_Core.translate("food.cooked.1"); break;
        case 2: s = s + TFC_Core.translate("food.cooked.2"); break;
        case 3: s = s + TFC_Core.translate("food.cooked.3"); break;
        default: s = s + TFC_Core.translate("food.cooked.4"); break;
      } 
      s = s + ")";
    } 
    return s;
  }

  
  public static void addFoodHeatInformation(ItemStack is, List<String> arraylist) {
    if (TFC_ItemHeat.hasTemp(is)) {
      
      float meltTemp = TFC_ItemHeat.isCookable(is);
      if (meltTemp != -1.0F) {
        arraylist.add(TFC_ItemHeat.getHeatColorFood(TFC_ItemHeat.getTemp(is), meltTemp));
      }
    } 
  }

  
  public void func_77624_a(ItemStack is, EntityPlayer player, List<String> arraylist, boolean flag) {
    ItemTerra.addSizeInformation(is, arraylist);
    arraylist.add(getFoodGroupName(getFoodGroup()));
    
    if (is.func_77942_o()) {
      
      addFoodHeatInformation(is, arraylist);
      addFoodInformation(is, player, arraylist);
    }
    else {
      
      arraylist.add(TFC_Core.translate("gui.badnbt"));
    } 
  }




  
  public void addFoodInformation(ItemStack is, EntityPlayer player, List<String> arraylist) {
    float ounces = Helper.roundNumber(Food.getWeight(is), 100.0F);
    if (ounces > 0.0F && ounces <= 160.0F) {
      arraylist.add(TFC_Core.translate("gui.food.amount") + " " + ounces + " oz / " + 160.0F + " oz");
    }
    float decay = Food.getDecay(is);
    if (decay > 0.0F)
      arraylist.add(EnumChatFormatting.DARK_GRAY + TFC_Core.translate("gui.food.decay") + " " + Helper.roundNumber(decay / ounces * 100.0F, 10.0F) + "%"); 
    if (TFCOptions.enableDebugMode) {
      
      arraylist.add(EnumChatFormatting.DARK_GRAY + TFC_Core.translate("gui.food.decay") + ": " + decay);
      arraylist.add(EnumChatFormatting.DARK_GRAY + "Decay Rate: " + Helper.roundNumber(getDecayRate(is), 100.0F));
    } 
    
    if (TFC_Core.showCtrlInformation()) {
      addTasteInformation(is, player, arraylist);
    } else {
      arraylist.add(TFC_Core.translate("gui.showtaste"));
    } 
  }
  
  public static void addTasteInformation(ItemStack is, EntityPlayer player, List<String> arraylist) {
    IFood food = (IFood)is.func_77973_b();
    int sweet = food.getTasteSweet(is);
    int sour = food.getTasteSour(is);
    int salty = food.getTasteSalty(is);
    int bitter = food.getTasteBitter(is);
    int savory = food.getTasteSavory(is);
    SkillStats.SkillRank rank = TFC_Core.getSkillStats(player).getSkillRank("skill.cooking");
    if (Food.hasMealSkill(is)) {
      rank = SkillStats.SkillRank.values()[Food.getMealSkill(is)];
    }
    int[] prefs = TFC_Core.getPlayerFoodStats(player).getPrefTaste();
    
    String sSweet = EnumChatFormatting.DARK_GRAY + TFC_Core.translate("gui.taste.sweet") + ": " + EnumChatFormatting.WHITE;
    String sSour = EnumChatFormatting.DARK_GRAY + TFC_Core.translate("gui.taste.sour") + ": " + EnumChatFormatting.WHITE;
    String sSalty = EnumChatFormatting.DARK_GRAY + TFC_Core.translate("gui.taste.salty") + ": " + EnumChatFormatting.WHITE;
    String sBitter = EnumChatFormatting.DARK_GRAY + TFC_Core.translate("gui.taste.bitter") + ": " + EnumChatFormatting.WHITE;
    String sSavory = EnumChatFormatting.DARK_GRAY + TFC_Core.translate("gui.taste.savory") + ": " + EnumChatFormatting.WHITE;
    
    if (rank == SkillStats.SkillRank.Novice) {
      
      sSweet = sSweet + ((sweet > prefs[0]) ? TFC_Core.translate("gui.taste.novice.sweet1") : TFC_Core.translate("gui.taste.novice.sweet0"));
      sSour = sSour + ((sour > prefs[1]) ? TFC_Core.translate("gui.taste.novice.sour1") : TFC_Core.translate("gui.taste.novice.sour0"));
      sSalty = sSalty + ((salty > prefs[2]) ? TFC_Core.translate("gui.taste.novice.salty1") : TFC_Core.translate("gui.taste.novice.salty0"));
      sBitter = sBitter + ((bitter > prefs[3]) ? TFC_Core.translate("gui.taste.novice.bitter1") : TFC_Core.translate("gui.taste.novice.bitter0"));
      sSavory = sSavory + ((savory > prefs[4]) ? TFC_Core.translate("gui.taste.novice.savory1") : TFC_Core.translate("gui.taste.novice.savory0"));
    }
    else if (rank == SkillStats.SkillRank.Adept) {
      
      sSweet = sSweet + createBasicString(sweet, prefs[0], "sweet");
      sSour = sSour + createBasicString(sour, prefs[1], "sour");
      sSalty = sSalty + createBasicString(salty, prefs[2], "salty");
      sBitter = sBitter + createBasicString(bitter, prefs[3], "bitter");
      sSavory = sSavory + createBasicString(savory, prefs[4], "savory");
    }
    else if (rank == SkillStats.SkillRank.Expert) {
      
      sSweet = sSweet + createExpertString(sweet, prefs[0], "sweet");
      sSour = sSour + createExpertString(sour, prefs[1], "sour");
      sSalty = sSalty + createExpertString(salty, prefs[2], "salty");
      sBitter = sBitter + createExpertString(bitter, prefs[3], "bitter");
      sSavory = sSavory + createExpertString(savory, prefs[4], "savory");
    }
    else if (rank == SkillStats.SkillRank.Master) {
      
      sSweet = sSweet + createBasicString(sweet, prefs[0], "sweet") + " (" + (sweet - prefs[0]) + ")";
      sSour = sSour + createBasicString(sour, prefs[1], "sour") + " (" + (sour - prefs[1]) + ")";
      sSalty = sSalty + createBasicString(salty, prefs[2], "salty") + " (" + (salty - prefs[2]) + ")";
      sBitter = sBitter + createBasicString(bitter, prefs[3], "bitter") + " (" + (bitter - prefs[3]) + ")";
      sSavory = sSavory + createBasicString(savory, prefs[4], "savory") + " (" + (savory - prefs[4]) + ")";
    } 
    
    arraylist.add(sSweet);
    arraylist.add(sSour);
    arraylist.add(sSalty);
    arraylist.add(sBitter);
    arraylist.add(sSavory);
  }

  
  private static String createExpertString(int val, int pref, String name) {
    int abs = Math.abs(val - pref);
    
    String out = createBasicString(val, pref, name);
    
    if (abs <= 5) {
      out = out + " (+-5)";
    } else if (abs <= 10) {
      out = out + " (+-10)";
    } else if (abs <= 15) {
      out = out + " (+-15)";
    } else if (abs <= 20) {
      out = out + " (+-20)";
    } 
    return out;
  }

  
  private static String createBasicString(int val, int pref, String name) {
    int dif = val - pref;
    
    if (dif >= -5 && dif <= 5)
      return TFC_Core.translate("gui.taste.4") + " " + TFC_Core.translate("gui.taste." + name); 
    if (dif >= -10 && dif < -5)
      return TFC_Core.translate("gui.taste.3") + " " + TFC_Core.translate("gui.taste." + name); 
    if (dif >= -15 && dif < -10)
      return TFC_Core.translate("gui.taste.2") + " " + TFC_Core.translate("gui.taste." + name); 
    if (dif >= -20 && dif < -15)
      return TFC_Core.translate("gui.taste.1") + " " + TFC_Core.translate("gui.taste." + name); 
    if (dif < -20)
      return TFC_Core.translate("gui.taste.0") + " " + TFC_Core.translate("gui.taste." + name); 
    if (dif > 5 && dif <= 10)
      return TFC_Core.translate("gui.taste.5") + " " + TFC_Core.translate("gui.taste." + name); 
    if (dif > 10 && dif <= 15)
      return TFC_Core.translate("gui.taste.6") + " " + TFC_Core.translate("gui.taste." + name); 
    if (dif > 15 && dif <= 20)
      return TFC_Core.translate("gui.taste.7") + " " + TFC_Core.translate("gui.taste." + name); 
    if (dif > 20) {
      return TFC_Core.translate("gui.taste.8") + " " + TFC_Core.translate("gui.taste." + name);
    }
    return "";
  }

  
  public static String getFoodGroupName(EnumFoodGroup fg) {
    if (fg == EnumFoodGroup.Dairy)
      return EnumChatFormatting.WHITE + TFC_Core.translate("gui.food.dairy"); 
    if (fg == EnumFoodGroup.Fruit)
      return EnumChatFormatting.DARK_PURPLE + TFC_Core.translate("gui.food.fruit"); 
    if (fg == EnumFoodGroup.Vegetable)
      return EnumChatFormatting.DARK_GREEN + TFC_Core.translate("gui.food.vegetable"); 
    if (fg == EnumFoodGroup.Protein)
      return EnumChatFormatting.DARK_RED + TFC_Core.translate("gui.food.protein"); 
    if (fg == EnumFoodGroup.Grain) {
      return EnumChatFormatting.YELLOW + TFC_Core.translate("gui.food.grain");
    }
    return "N/A";
  }

  
  public static String getFoodGroupColor(EnumFoodGroup fg) {
    if (fg == EnumFoodGroup.Dairy)
      return EnumChatFormatting.WHITE.toString(); 
    if (fg == EnumFoodGroup.Fruit)
      return EnumChatFormatting.DARK_PURPLE.toString(); 
    if (fg == EnumFoodGroup.Vegetable)
      return EnumChatFormatting.DARK_GREEN.toString(); 
    if (fg == EnumFoodGroup.Protein)
      return EnumChatFormatting.DARK_RED.toString(); 
    if (fg == EnumFoodGroup.Grain) {
      return EnumChatFormatting.YELLOW.toString();
    }
    return "N/A";
  }


  
  public ItemStack func_77659_a(ItemStack is, World world, EntityPlayer player) {
    FoodStatsTFC foodstats = TFC_Core.getPlayerFoodStats(player);
    if (foodstats.needFood() && isEdible(is)) {
      player.func_71008_a(is, 32);
    }
    return is;
  }


  
  public ItemStack func_77654_b(ItemStack is, World world, EntityPlayer player) {
    FoodStatsTFC foodstats = TFC_Core.getPlayerFoodStats(player);
    if (!world.field_72995_K && isEdible(is))
    {
      if (is.func_77942_o()) {

        
        float weight = Food.getWeight(is);
        float decay = Math.max(Food.getDecay(is), 0.0F);
        
        float eatAmount = Math.min(weight - decay, 5.0F);
        float stomachDiff = foodstats.stomachLevel + eatAmount - foodstats.getMaxStomach(foodstats.player);
        if (stomachDiff > 0.0F) {
          eatAmount -= stomachDiff;
        }
        float tasteFactor = foodstats.getTasteFactor(is);
        foodstats.addNutrition(((IFood)is.func_77973_b()).getFoodGroup(), eatAmount * tasteFactor);
        foodstats.stomachLevel += eatAmount * tasteFactor;
        if (FoodStatsTFC.reduceFood(is, eatAmount)) {
          is.field_77994_a = 0;
        }
      } else {
        
        foodstats.addNutrition(((IFood)is.func_77973_b()).getFoodGroup(), 1.0F);

        
        String error = TFC_Core.translate("error.error") + " " + is.func_77977_a() + " " + TFC_Core.translate("error.NBT") + " " + TFC_Core.translate("error.Contact");

        
        TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentText(error));
      } 
    }
    
    world.func_72956_a((Entity)player, "random.burp", 0.5F, world.field_73012_v.nextFloat() * 0.1F + 0.9F);
    TFC_Core.setPlayerFoodStats(player, foodstats);
    return is;
  }

  
  public ItemStack onConsumedByEntity(ItemStack is, World world, EntityLivingBase entity) {
    if (entity instanceof com.bioxx.tfc.api.Entities.IAnimal) {
      
      if (!world.field_72995_K) {
        
        float weight = Food.getWeight(is);
        float decay = Math.max(Food.getDecay(is), 0.0F);
        float eatAmount = Math.min(weight - decay, 5.0F);
        if (FoodStatsTFC.reduceFood(is, eatAmount))
          is.field_77994_a = 0; 
      } 
      world.func_72956_a((Entity)entity, "random.burp", 0.5F, world.field_73012_v.nextFloat() * 0.1F + 0.9F);
    } 
    return is;
  }

  
  public boolean isHot(ItemStack is) {
    return (TFC_ItemHeat.getTemp(is) > TFC_ItemHeat.isCookable(is) * 0.8D);
  }

  
  public static ItemStack createTag(ItemStack is) {
    return createTag(is, 999.0F);
  }

  
  public static ItemStack createTag(ItemStack is, float weight) {
    if (!is.func_77942_o()) {
      is.func_77982_d(new NBTTagCompound());
    }
    Food.setWeight(is, weight);
    Food.setDecay(is, -24.0F);
    Food.setDecayTimer(is, (int)TFC_Time.getTotalHours() + 1);
    
    return is;
  }

  
  public static ItemStack createTag(ItemStack is, float weight, float decay) {
    is = createTag(is, weight);
    Food.setDecay(is, decay);
    return is;
  }


  
  public int func_77626_a(ItemStack is) {
    return 32;
  }


  
  public EnumAction func_77661_b(ItemStack is) {
    return EnumAction.eat;
  }


  
  public int getDisplayDamage(ItemStack is) {
    float decay = Food.getDecay(is);
    float weight = Food.getWeight(is);
    int percent = (int)(decay / weight * 100.0F);
    percent = (percent > 0) ? ((percent < 100) ? percent : 100) : 0;
    return percent;
  }





  
  public boolean isDamaged(ItemStack is) {
    return false;
  }


  
  public int getMaxDamage(ItemStack is) {
    return 100;
  }


  
  public EnumSize getSize(ItemStack is) {
    float weight = Food.getWeight(is);
    if (weight <= 20.0F)
      return EnumSize.TINY; 
    if (weight <= 40.0F)
      return EnumSize.VERYSMALL; 
    if (weight <= 80.0F) {
      return EnumSize.SMALL;
    }
    return EnumSize.MEDIUM;
  }


  
  public EnumWeight getWeight(ItemStack is) {
    float weight = Food.getWeight(is);
    if (weight < 80.0F)
      return EnumWeight.LIGHT; 
    if (weight < 160.0F) {
      return EnumWeight.MEDIUM;
    }
    return EnumWeight.HEAVY;
  }

  
  public boolean canStack() {
    return false;
  }


  
  public EnumFoodGroup getFoodGroup() {
    return this.foodgroup;
  }


  
  public int getFoodID() {
    return this.foodID;
  }


  
  public ItemStack onDecayed(ItemStack is, World world, int x, int y, int z) {
    return null;
  }


  
  public boolean isEdible(ItemStack is) {
    return (this.edible || Food.isCooked(is));
  }


  
  public boolean isUsable(ItemStack is) {
    return (this.canBeUsedRaw || Food.isCooked(is));
  }

  
  public int getTasteSweet(ItemStack is) {
    int base = this.tasteSweet;
    if (is != null && is.func_77942_o()) {
      
      if (is.func_77978_p().func_74764_b("tasteSweet"))
        base = is.func_77978_p().func_74762_e("tasteSweet"); 
      base += Food.getCookedProfile(is)[0];
      base = (int)(base + Food.getFuelProfile(is)[0] * getSmokeAbsorbMultiplier());
    } 
    return Math.max(base + Food.getSweetMod(is), 0);
  }

  
  public int getTasteSour(ItemStack is) {
    int base = this.tasteSour;
    if (is != null) {
      
      if (is.func_77978_p().func_74764_b("tasteSour"))
        base = is.func_77978_p().func_74762_e("tasteSour"); 
      base += Food.getCookedProfile(is)[1];
      base = (int)(base + Food.getFuelProfile(is)[1] * getSmokeAbsorbMultiplier());
    } 
    if (Food.isBrined(is))
      base += 5; 
    if (Food.isPickled(is))
      base += 30; 
    return Math.max(base + Food.getSourMod(is), 0);
  }

  
  public int getTasteSalty(ItemStack is) {
    int base = this.tasteSalty;
    if (is != null) {
      
      if (is.func_77978_p().func_74764_b("tasteSalty"))
        base = is.func_77978_p().func_74762_e("tasteSalty"); 
      base += Food.getCookedProfile(is)[2];
      base = (int)(base + Food.getFuelProfile(is)[2] * getSmokeAbsorbMultiplier());
    } 
    if (Food.isSalted(is))
      base += 40; 
    if (Food.isBrined(is)) {
      base += 10;
    }
    return Math.max(base + Food.getSaltyMod(is), 0);
  }

  
  public int getTasteBitter(ItemStack is) {
    int base = this.tasteBitter;
    if (is != null) {
      
      if (is.func_77978_p().func_74764_b("tasteBitter"))
        base = is.func_77978_p().func_74762_e("tasteBitter"); 
      base += Food.getCookedProfile(is)[3];
      base = (int)(base + Food.getFuelProfile(is)[3] * getSmokeAbsorbMultiplier());
    } 
    return Math.max(base + Food.getBitterMod(is), 0);
  }

  
  public int getTasteSavory(ItemStack is) {
    int base = this.tasteUmami;
    if (is != null) {
      
      if (is.func_77978_p().func_74764_b("tasteUmami"))
        base = is.func_77978_p().func_74762_e("tasteUmami"); 
      base += Food.getCookedProfile(is)[4];
      base = (int)(base + Food.getFuelProfile(is)[4] * getSmokeAbsorbMultiplier());
    } 
    return Math.max(base + Food.getSavoryMod(is), 0);
  }

  
  public float getFoodMaxWeight(ItemStack is) {
    return 160.0F;
  }

  
  public boolean renderDecay() {
    return true;
  }

  
  public boolean renderWeight() {
    return true;
  }

  
  public boolean canSmoke() {
    return this.canBeSmoked;
  }


  
  public float getSmokeAbsorbMultiplier() {
    return this.smokeAbsorb;
  }
  
  public ItemFoodTFC setSmokeAbsorbMultiplier(float s) {
    this.smokeAbsorb = s;
    return this;
  }


  
  public float getFoodWeight(ItemStack is) {
    return Food.getWeight(is);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Food\ItemFoodTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */