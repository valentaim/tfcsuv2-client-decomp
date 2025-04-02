package com.bioxx.tfc.api.Crafting;

import com.bioxx.tfc.Core.Player.Anvil_Skills;
import com.bioxx.tfc.Core.Player.SkillStats;
import com.bioxx.tfc.Core.TFC_Core;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;



public class AnvilRecipe
{
  public ItemStack result;
  public String plan = "";
  public ItemStack input1;
  public ItemStack input2;
  public boolean flux;
  public int craftingValue;
  public int anvilreq;
  public boolean inheritsDamage;
  public int craftingXP = 1;
  public List<String> skillsList = new ArrayList<>();
  public static int craftingBoundDefault = 50;


  public AnvilRecipe(ItemStack in, ItemStack in2, String p, boolean flux, AnvilReq req, ItemStack result) {
    this(in, in2, p.toLowerCase(), 0, flux, req.Tier, result);
    this.craftingValue = 70 + (new Random(TFC_Core.getSuperSeed(AnvilManager.world) + ((in != null) ? Item.func_150891_b(in.func_77973_b()) : 0L) + ((result != null) ? Item.func_150891_b(result.func_77973_b()) : 0L))).nextInt(craftingBoundDefault);
  }


  public AnvilRecipe(ItemStack in, ItemStack in2, String p, AnvilReq req, ItemStack result) {
    this(in, in2, p.toLowerCase(), 0, false, req.Tier, result);
    this.craftingValue = 70 + (new Random(TFC_Core.getSuperSeed(AnvilManager.world) + ((in != null) ? Item.func_150891_b(in.func_77973_b()) : 0L) + ((result != null) ? Item.func_150891_b(result.func_77973_b()) : 0L))).nextInt(craftingBoundDefault);
  }


  public AnvilRecipe setCraftingBound(int max) {
    this.craftingValue = 70 + (new Random(TFC_Core.getSuperSeed(AnvilManager.world) + ((this.input1 != null) ? Item.func_150891_b(this.input1.func_77973_b()) : 0L) + ((this.result != null) ? Item.func_150891_b(this.result.func_77973_b()) : 0L))).nextInt(max);
    return this;
  }


  public AnvilRecipe(ItemStack in, ItemStack in2, String p, int cv, boolean flux, int req, ItemStack result) {
    this.input1 = in;
    this.input2 = in2;
    this.flux = flux;
    this.craftingValue = cv;
    this.anvilreq = req;
    this.result = result;
    this.inheritsDamage = false;
    this.plan = p;
  }



  public AnvilRecipe(ItemStack in, ItemStack p, boolean flux, AnvilReq req) {
    this(in, p, flux, req.Tier);
  }


  public AnvilRecipe(ItemStack in, ItemStack p, boolean flux, int req) {
    this.input1 = in;
    this.input2 = p;
    this.flux = flux;
    this.anvilreq = req;
    this.inheritsDamage = false;
  }


  public AnvilRecipe(ItemStack in, ItemStack p, String s, boolean flux, int req) {
    this(in, p, flux, req);
    this.plan = s;
  }


  public AnvilRecipe(ItemStack in, ItemStack p, boolean flux, AnvilReq req, ItemStack res) {
    this(in, p, req, res);
    this.flux = flux;
  }


  public AnvilRecipe(ItemStack in, ItemStack p, AnvilReq req, ItemStack res) {
    this.input1 = in;
    this.input2 = p;
    this.anvilreq = req.Tier;
    this.result = res;
    this.inheritsDamage = false;
  }


  public AnvilRecipe clearRecipeSkills() {
    this.skillsList.clear();
    return this;
  }


  public AnvilRecipe setCraftingXP(int xp) {
    this.craftingXP = xp;
    return this;
  }


  public AnvilRecipe setInheritsDamage() {
    this.inheritsDamage = true;
    return this;
  }


  public AnvilRecipe addRecipeSkill(String s) {
    this.skillsList.add(s);
    return this;
  }





  public boolean matches(AnvilRecipe recipe) {
    if (areItemStacksEqual(this.input1, recipe.input1) &&
      areItemStacksEqual(this.input2, recipe.input2) && this.plan
      .equals(recipe.plan) &&
      AnvilReq.matches(this.anvilreq, recipe.anvilreq))
    {
      return (!this.flux || recipe.flux);
    }
    return false;
  }


  public boolean isComplete(AnvilManager am, AnvilRecipe recipe, int[] rules) {
    PlanRecipe pr = am.getPlan(recipe.plan);
    if (areItemStacksEqual(this.input1, recipe.input1) &&
      areItemStacksEqual(this.input2, recipe.input2) && this.plan
      .equals(recipe.plan) && pr.rules[0]
      .matches(rules, 0) && pr.rules[1].matches(rules, 1) && pr.rules[2].matches(rules, 2) && this.craftingValue == recipe.craftingValue &&
      AnvilReq.matches(this.anvilreq, recipe.anvilreq)) {
      if (this.flux && recipe.flux)
        return true;
      if (!this.flux)
        return true;
    }  return false;
  }


  public boolean isComplete(AnvilRecipe recipe) {
    if (recipe.input1 == this.input1 && recipe.input2 == this.input2 && this.craftingValue == recipe.craftingValue && this.plan
      .equals(recipe.plan) && AnvilReq.matches(this.anvilreq, recipe.anvilreq)) {
      if (this.flux && recipe.flux)
        return true;
      if (!this.flux)
        return true;
    }  return false;
  }


  private boolean areItemStacksEqual(ItemStack is1, ItemStack is2) {
    if (is1 != null && is2 != null) {

      if (is1.func_77973_b() != is2.func_77973_b()) {
        return false;
      }
      if (is1.func_77960_j() != 32767 && is1.func_77960_j() != is2.func_77960_j()) {
        return false;
      }
    } else if ((is1 == null && is2 != null) || (is1 != null && is2 == null)) {
      return false;
    }
    return true;
  }





  public ItemStack getCraftingResult() {
    return this.result;
  }





  public ItemStack getCraftingResult(ItemStack input) {
    ItemStack is = this.result.func_77946_l();
    if (this.inheritsDamage)
      is.func_77964_b(input.func_77960_j());
    return is;
  }




  public int getCraftingValue() {
    return this.craftingValue;
  }


  public float getSkillMult(EntityPlayer p) {
    float skill = 0.0F;
    float total = 0.0F;
    SkillStats ss = TFC_Core.getSkillStats(p);
    for (String s : this.skillsList) {

      total++;
      Anvil_Skills.Anvil_Skill as = ss.adv_skills.getSkill(s);
      if (as != null && this.anvilreq > 0) { skill += (float)as.math_bonus((byte)(this.anvilreq - 1)); continue; }
       skill += TFC_Core.getSkillStats(p).getSkillMultiplier(s);
    }
    if (total > 0.0F) return skill / total;

    return 0.0F;
  }


  public String getPlan() {
    return this.plan;
  }


  public ItemStack getInput1() {
    return this.input1;
  }


  public ItemStack getInput2() {
    return this.input2;
  }


  public boolean isFlux() {
    return this.flux;
  }


  public int getAnvilreq() {
    return this.anvilreq;
  }


  public boolean isInheritsDamage() {
    return this.inheritsDamage;
  }


  public int getCraftingXP() {
    return this.craftingXP;
  }


  public List<String> getSkillsList() {
    return this.skillsList;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Crafting\AnvilRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */