package com.bioxx.tfc.Core.Player;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Handlers.Network.AbstractPacket;
import com.bioxx.tfc.Handlers.Network.PlayerUpdatePacket;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.api.Crafting.AnvilRecipe;
import com.bioxx.tfc.api.Events.GetSkillMultiplierEvent;
import com.bioxx.tfc.api.Events.PlayerSkillEvent;
import com.bioxx.tfc.api.SkillsManager;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.MinecraftForge;




public class SkillStats
{
  private Map<SkillsManager.Skill, Integer> skillsMap;
  private EntityPlayer player;
  public Anvil_Skills adv_skills = new Anvil_Skills();


  public SkillStats(EntityPlayer p) {
    this.player = p;
    this.skillsMap = new HashMap<>();
    for (SkillsManager.Skill s : SkillsManager.instance.getSkillsArray())
    {
      setSkill(s.skillName, 0);
    }
  }


  public Object[] getSkillsKeysArray() {
    return this.skillsMap.keySet().toArray();
  }


  public void setSkill(String skillName, int amount) {
    SkillsManager.Skill sk = SkillsManager.instance.getSkill(skillName);
    if (sk != null) {
      this.skillsMap.put(sk, Integer.valueOf(amount));
    }
  }

  public void setSkillSave(String skillName, int amount) {
    SkillsManager.Skill sk = SkillsManager.instance.getSkill(skillName);
    if (sk != null)
      this.skillsMap.put(sk, Integer.valueOf(amount));
    writeNBT(this.player.getEntityData());
  }


  public void increaseSkill(String skillName, int amount) {
    SkillsManager.Skill sk = SkillsManager.instance.getSkill(skillName);
    PlayerSkillEvent.Increase event = new PlayerSkillEvent.Increase(this.player, skillName, amount);
    if (!MinecraftForge.EVENT_BUS.post((Event)event))
    {
      if (this.skillsMap.containsKey(sk)) {


        int j = ((Integer)this.skillsMap.get(sk)).intValue();

        this.skillsMap.put(sk, Integer.valueOf(j + amount));
      }
      else {

        this.skillsMap.put(sk, Integer.valueOf(amount));
      }
    }

    int i = ((Integer)this.skillsMap.get(sk)).intValue();
    if (this.player instanceof EntityPlayerMP) {

      PlayerUpdatePacket playerUpdatePacket = new PlayerUpdatePacket(1, skillName, i, this.player);
      TerraFirmaCraft.PACKET_PIPELINE.sendTo((AbstractPacket)playerUpdatePacket, (EntityPlayerMP)this.player);
    }
    writeNBT(this.player.getEntityData());
  }


  public void increaseAnvillSkill(String skillName, AnvilRecipe recipe) {
    SkillsManager.Skill sk = SkillsManager.instance.getSkill(skillName);
    int amount = recipe.craftingXP;
    PlayerSkillEvent.Increase event = new PlayerSkillEvent.Increase(this.player, skillName, amount);
    if (!MinecraftForge.EVENT_BUS.post((Event)event))
    {
      if (this.skillsMap.containsKey(sk)) {


        int j = ((Integer)this.skillsMap.get(sk)).intValue();

        this.skillsMap.put(sk, Integer.valueOf(j + amount));
      }
      else {

        this.skillsMap.put(sk, Integer.valueOf(amount));
      }
    }

    int i = ((Integer)this.skillsMap.get(sk)).intValue();
    Anvil_Skills.Anvil_Skill as = this.adv_skills.getSkill(skillName);
    if (as != null) as.increase_exp((byte)(recipe.getAnvilreq() - 1));
    writeNBT(this.player.getEntityData());
    if (this.player instanceof EntityPlayerMP) {

      PlayerUpdatePacket playerUpdatePacket = new PlayerUpdatePacket(1, skillName, i, this.player);
      TerraFirmaCraft.PACKET_PIPELINE.sendTo((AbstractPacket)playerUpdatePacket, (EntityPlayerMP)this.player);
    }
  }



  public int getSkillRaw(String skillName) {
    SkillsManager.Skill sk = SkillsManager.instance.getSkill(skillName);
    if (this.skillsMap.containsKey(sk)) {
      return ((Integer)this.skillsMap.get(sk)).intValue();
    }
    return 0;
  }


  public SkillRank getSkillRank(String skillName) {
    float raw = getSkillMultiplier(skillName);
    if (raw < 0.25D)
    {
      return SkillRank.Novice;
    }
    if (raw < 0.5D)
    {
      return SkillRank.Adept;
    }
    if (raw < 0.75D)
    {
      return SkillRank.Expert;
    }


    return SkillRank.Master;
  }



  public float getPercToNextRank(String skillName) {
    float raw = getSkillMultiplier(skillName);
    if (raw < 0.25D)
    {
      return raw / 0.25F;
    }
    if (raw < 0.5D)
    {
      return (raw - 0.25F) / 0.25F;
    }
    if (raw < 0.75D)
    {
      return (raw - 0.5F) / 0.25F;
    }


    return (raw - 0.75F) / 0.25F;
  }



  public float getSkillMultiplier(String skillName) {
    int skill = getSkillRaw(skillName);
    SkillsManager.Skill sk = SkillsManager.instance.getSkill(skillName);
    float mult = getSkillMult(skill, sk.skillRate);
    GetSkillMultiplierEvent event = new GetSkillMultiplierEvent(this.player, skillName, mult);
    MinecraftForge.EVENT_BUS.post((Event)event);
    return event.skillResult;
  }


  private float getSkillMult(int skill, float rate) {
    return 1.0F - rate / (rate + skill);
  }


  public void readNBT(NBTTagCompound nbt) {
    if (nbt.func_74764_b("skillCompound")) {

      NBTTagCompound skillCompound = nbt.func_74775_l("skillCompound");
      for (Object n : skillCompound.func_150296_c()) {

        String skill = (String)n;
        setSkill(skill, skillCompound.func_74762_e(skill));
      }
    }
    if (nbt.func_74764_b("adv_skillCompound")) readAnvilSkills(nbt.func_74775_l("adv_skillCompound"));
  }

  public void readOneAnvilSkill(NBTTagList nbttaglist, Anvil_Skills.Anvil_Skill skill) {
    for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
      NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
      byte tier_num = (byte)(nbttagcompound1.func_74771_c("tier") & 0xFF);
      skill.setTierData(tier_num, nbttagcompound1.func_74769_h("exp"));
    }
  }


  public void readAnvilSkills(NBTTagCompound nbt) {
    readOneAnvilSkill(nbt.func_150295_c("weapon", 10), this.adv_skills.WEAPON);
    readOneAnvilSkill(nbt.func_150295_c("armor", 10), this.adv_skills.ARMOR);
    readOneAnvilSkill(nbt.func_150295_c("tools", 10), this.adv_skills.TOOLS);
  }





  public void writeNBT(NBTTagCompound nbt) {
    NBTTagCompound skillCompound = new NBTTagCompound();
    Object[] keys = this.skillsMap.keySet().toArray();
    for (Object o : keys) {

      SkillsManager.Skill k = (SkillsManager.Skill)o;
      int f = ((Integer)this.skillsMap.get(k)).intValue();
      skillCompound.func_74768_a(k.skillName, f);
    }
    nbt.func_74782_a("skillCompound", (NBTBase)skillCompound);
    writeAnvilSkills(nbt);
  }

  public void writeOneAnvilSkill(NBTTagCompound nbt, Anvil_Skills.Anvil_Skill skill, String nbt_name) {
    NBTTagList nbttaglist = new NBTTagList(); byte i;
    for (i = 0; i < skill.size(); i = (byte)(i + 1)) {
      NBTTagCompound nbttagcompound1 = new NBTTagCompound();
      nbttagcompound1.func_74774_a("tier", i);
      nbttagcompound1.func_74780_a("exp", skill.getTierData(i));
      nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
    }
    nbt.func_74782_a(nbt_name, (NBTBase)nbttaglist);
  }

  public void writeAnvilSkills(NBTTagCompound nbt) {
    NBTTagCompound skillCompound = new NBTTagCompound();
    writeOneAnvilSkill(skillCompound, this.adv_skills.WEAPON, "weapon");
    writeOneAnvilSkill(skillCompound, this.adv_skills.ARMOR, "armor");
    writeOneAnvilSkill(skillCompound, this.adv_skills.TOOLS, "tools");
    nbt.func_74782_a("adv_skillCompound", (NBTBase)skillCompound);
  }



  public void toOutBuffer(ByteBuf buffer) {
    Object[] keys = this.skillsMap.keySet().toArray();
    buffer.writeInt(keys.length);
    for (Object o : keys) {

      SkillsManager.Skill k = (SkillsManager.Skill)o;
      int f = ((Integer)this.skillsMap.get(k)).intValue();
      ByteBufUtils.writeUTF8String(buffer, k.skillName);
      buffer.writeInt(f);
      Anvil_Skills.Anvil_Skill as = this.adv_skills.getSkill(k.skillName);
      if (as != null) { byte i; for (i = 0; i < as.size(); ) { buffer.writeDouble(as.getTierData(i)); i = (byte)(i + 1); }
         }

    }
  }

  public enum SkillRank { Novice("gui.skill.novice"), Adept("gui.skill.adept"), Expert("gui.skill.expert"), Master("gui.skill.master");

    String name;

    SkillRank(String local) {
      this.name = local;
    }


    public String getUnlocalizedName() {
      return this.name;
    }


    public String getLocalizedName() {
      return TFC_Core.translate(this.name);
    } }

}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\Player\SkillStats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */