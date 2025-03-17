/*     */ package com.bioxx.tfc.Core.Player;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Handlers.Network.AbstractPacket;
/*     */ import com.bioxx.tfc.Handlers.Network.PlayerUpdatePacket;
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import com.bioxx.tfc.api.Crafting.AnvilRecipe;
/*     */ import com.bioxx.tfc.api.Events.GetSkillMultiplierEvent;
/*     */ import com.bioxx.tfc.api.Events.PlayerSkillEvent;
/*     */ import com.bioxx.tfc.api.SkillsManager;
/*     */ import cpw.mods.fml.common.eventhandler.Event;
/*     */ import cpw.mods.fml.common.network.ByteBufUtils;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SkillStats
/*     */ {
/*     */   private Map<SkillsManager.Skill, Integer> skillsMap;
/*     */   private EntityPlayer player;
/*  30 */   public Anvil_Skills adv_skills = new Anvil_Skills();
/*     */ 
/*     */   
/*     */   public SkillStats(EntityPlayer p) {
/*  34 */     this.player = p;
/*  35 */     this.skillsMap = new HashMap<>();
/*  36 */     for (SkillsManager.Skill s : SkillsManager.instance.getSkillsArray())
/*     */     {
/*  38 */       setSkill(s.skillName, 0);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Object[] getSkillsKeysArray() {
/*  44 */     return this.skillsMap.keySet().toArray();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSkill(String skillName, int amount) {
/*  49 */     SkillsManager.Skill sk = SkillsManager.instance.getSkill(skillName);
/*  50 */     if (sk != null) {
/*  51 */       this.skillsMap.put(sk, Integer.valueOf(amount));
/*     */     }
/*     */   }
/*     */   
/*     */   public void setSkillSave(String skillName, int amount) {
/*  56 */     SkillsManager.Skill sk = SkillsManager.instance.getSkill(skillName);
/*  57 */     if (sk != null)
/*  58 */       this.skillsMap.put(sk, Integer.valueOf(amount)); 
/*  59 */     writeNBT(this.player.getEntityData());
/*     */   }
/*     */ 
/*     */   
/*     */   public void increaseSkill(String skillName, int amount) {
/*  64 */     SkillsManager.Skill sk = SkillsManager.instance.getSkill(skillName);
/*  65 */     PlayerSkillEvent.Increase event = new PlayerSkillEvent.Increase(this.player, skillName, amount);
/*  66 */     if (!MinecraftForge.EVENT_BUS.post((Event)event))
/*     */     {
/*  68 */       if (this.skillsMap.containsKey(sk)) {
/*     */ 
/*     */         
/*  71 */         int j = ((Integer)this.skillsMap.get(sk)).intValue();
/*     */         
/*  73 */         this.skillsMap.put(sk, Integer.valueOf(j + amount));
/*     */       }
/*     */       else {
/*     */         
/*  77 */         this.skillsMap.put(sk, Integer.valueOf(amount));
/*     */       } 
/*     */     }
/*     */     
/*  81 */     int i = ((Integer)this.skillsMap.get(sk)).intValue();
/*  82 */     if (this.player instanceof EntityPlayerMP) {
/*     */       
/*  84 */       PlayerUpdatePacket playerUpdatePacket = new PlayerUpdatePacket(1, skillName, i, this.player);
/*  85 */       TerraFirmaCraft.PACKET_PIPELINE.sendTo((AbstractPacket)playerUpdatePacket, (EntityPlayerMP)this.player);
/*     */     } 
/*  87 */     writeNBT(this.player.getEntityData());
/*     */   }
/*     */ 
/*     */   
/*     */   public void increaseAnvillSkill(String skillName, AnvilRecipe recipe) {
/*  92 */     SkillsManager.Skill sk = SkillsManager.instance.getSkill(skillName);
/*  93 */     int amount = recipe.craftingXP;
/*  94 */     PlayerSkillEvent.Increase event = new PlayerSkillEvent.Increase(this.player, skillName, amount);
/*  95 */     if (!MinecraftForge.EVENT_BUS.post((Event)event))
/*     */     {
/*  97 */       if (this.skillsMap.containsKey(sk)) {
/*     */ 
/*     */         
/* 100 */         int j = ((Integer)this.skillsMap.get(sk)).intValue();
/*     */         
/* 102 */         this.skillsMap.put(sk, Integer.valueOf(j + amount));
/*     */       }
/*     */       else {
/*     */         
/* 106 */         this.skillsMap.put(sk, Integer.valueOf(amount));
/*     */       } 
/*     */     }
/*     */     
/* 110 */     int i = ((Integer)this.skillsMap.get(sk)).intValue();
/* 111 */     Anvil_Skills.Anvil_Skill as = this.adv_skills.getSkill(skillName);
/* 112 */     if (as != null) as.increase_exp((byte)(recipe.getAnvilreq() - 1)); 
/* 113 */     writeNBT(this.player.getEntityData());
/* 114 */     if (this.player instanceof EntityPlayerMP) {
/*     */       
/* 116 */       PlayerUpdatePacket playerUpdatePacket = new PlayerUpdatePacket(1, skillName, i, this.player);
/* 117 */       TerraFirmaCraft.PACKET_PIPELINE.sendTo((AbstractPacket)playerUpdatePacket, (EntityPlayerMP)this.player);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSkillRaw(String skillName) {
/* 124 */     SkillsManager.Skill sk = SkillsManager.instance.getSkill(skillName);
/* 125 */     if (this.skillsMap.containsKey(sk)) {
/* 126 */       return ((Integer)this.skillsMap.get(sk)).intValue();
/*     */     }
/* 128 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public SkillRank getSkillRank(String skillName) {
/* 133 */     float raw = getSkillMultiplier(skillName);
/* 134 */     if (raw < 0.25D)
/*     */     {
/* 136 */       return SkillRank.Novice;
/*     */     }
/* 138 */     if (raw < 0.5D)
/*     */     {
/* 140 */       return SkillRank.Adept;
/*     */     }
/* 142 */     if (raw < 0.75D)
/*     */     {
/* 144 */       return SkillRank.Expert;
/*     */     }
/*     */ 
/*     */     
/* 148 */     return SkillRank.Master;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getPercToNextRank(String skillName) {
/* 154 */     float raw = getSkillMultiplier(skillName);
/* 155 */     if (raw < 0.25D)
/*     */     {
/* 157 */       return raw / 0.25F;
/*     */     }
/* 159 */     if (raw < 0.5D)
/*     */     {
/* 161 */       return (raw - 0.25F) / 0.25F;
/*     */     }
/* 163 */     if (raw < 0.75D)
/*     */     {
/* 165 */       return (raw - 0.5F) / 0.25F;
/*     */     }
/*     */ 
/*     */     
/* 169 */     return (raw - 0.75F) / 0.25F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSkillMultiplier(String skillName) {
/* 175 */     int skill = getSkillRaw(skillName);
/* 176 */     SkillsManager.Skill sk = SkillsManager.instance.getSkill(skillName);
/* 177 */     float mult = getSkillMult(skill, sk.skillRate);
/* 178 */     GetSkillMultiplierEvent event = new GetSkillMultiplierEvent(this.player, skillName, mult);
/* 179 */     MinecraftForge.EVENT_BUS.post((Event)event);
/* 180 */     return event.skillResult;
/*     */   }
/*     */ 
/*     */   
/*     */   private float getSkillMult(int skill, float rate) {
/* 185 */     return 1.0F - rate / (rate + skill);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readNBT(NBTTagCompound nbt) {
/* 190 */     if (nbt.func_74764_b("skillCompound")) {
/*     */       
/* 192 */       NBTTagCompound skillCompound = nbt.func_74775_l("skillCompound");
/* 193 */       for (Object n : skillCompound.func_150296_c()) {
/*     */         
/* 195 */         String skill = (String)n;
/* 196 */         setSkill(skill, skillCompound.func_74762_e(skill));
/*     */       } 
/*     */     } 
/* 199 */     if (nbt.func_74764_b("adv_skillCompound")) readAnvilSkills(nbt.func_74775_l("adv_skillCompound")); 
/*     */   }
/*     */   
/*     */   public void readOneAnvilSkill(NBTTagList nbttaglist, Anvil_Skills.Anvil_Skill skill) {
/* 203 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/* 204 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 205 */       byte tier_num = (byte)(nbttagcompound1.func_74771_c("tier") & 0xFF);
/* 206 */       skill.setTierData(tier_num, nbttagcompound1.func_74769_h("exp"));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void readAnvilSkills(NBTTagCompound nbt) {
/* 212 */     readOneAnvilSkill(nbt.func_150295_c("weapon", 10), this.adv_skills.WEAPON);
/* 213 */     readOneAnvilSkill(nbt.func_150295_c("armor", 10), this.adv_skills.ARMOR);
/* 214 */     readOneAnvilSkill(nbt.func_150295_c("tools", 10), this.adv_skills.TOOLS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeNBT(NBTTagCompound nbt) {
/* 222 */     NBTTagCompound skillCompound = new NBTTagCompound();
/* 223 */     Object[] keys = this.skillsMap.keySet().toArray();
/* 224 */     for (Object o : keys) {
/*     */       
/* 226 */       SkillsManager.Skill k = (SkillsManager.Skill)o;
/* 227 */       int f = ((Integer)this.skillsMap.get(k)).intValue();
/* 228 */       skillCompound.func_74768_a(k.skillName, f);
/*     */     } 
/* 230 */     nbt.func_74782_a("skillCompound", (NBTBase)skillCompound);
/* 231 */     writeAnvilSkills(nbt);
/*     */   }
/*     */   
/*     */   public void writeOneAnvilSkill(NBTTagCompound nbt, Anvil_Skills.Anvil_Skill skill, String nbt_name) {
/* 235 */     NBTTagList nbttaglist = new NBTTagList(); byte i;
/* 236 */     for (i = 0; i < skill.size(); i = (byte)(i + 1)) {
/* 237 */       NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 238 */       nbttagcompound1.func_74774_a("tier", i);
/* 239 */       nbttagcompound1.func_74780_a("exp", skill.getTierData(i));
/* 240 */       nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
/*     */     } 
/* 242 */     nbt.func_74782_a(nbt_name, (NBTBase)nbttaglist);
/*     */   }
/*     */   
/*     */   public void writeAnvilSkills(NBTTagCompound nbt) {
/* 246 */     NBTTagCompound skillCompound = new NBTTagCompound();
/* 247 */     writeOneAnvilSkill(skillCompound, this.adv_skills.WEAPON, "weapon");
/* 248 */     writeOneAnvilSkill(skillCompound, this.adv_skills.ARMOR, "armor");
/* 249 */     writeOneAnvilSkill(skillCompound, this.adv_skills.TOOLS, "tools");
/* 250 */     nbt.func_74782_a("adv_skillCompound", (NBTBase)skillCompound);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void toOutBuffer(ByteBuf buffer) {
/* 256 */     Object[] keys = this.skillsMap.keySet().toArray();
/* 257 */     buffer.writeInt(keys.length);
/* 258 */     for (Object o : keys) {
/*     */       
/* 260 */       SkillsManager.Skill k = (SkillsManager.Skill)o;
/* 261 */       int f = ((Integer)this.skillsMap.get(k)).intValue();
/* 262 */       ByteBufUtils.writeUTF8String(buffer, k.skillName);
/* 263 */       buffer.writeInt(f);
/* 264 */       Anvil_Skills.Anvil_Skill as = this.adv_skills.getSkill(k.skillName);
/* 265 */       if (as != null) { byte i; for (i = 0; i < as.size(); ) { buffer.writeDouble(as.getTierData(i)); i = (byte)(i + 1); }
/*     */          }
/*     */     
/*     */     } 
/*     */   }
/*     */   
/* 271 */   public enum SkillRank { Novice("gui.skill.novice"), Adept("gui.skill.adept"), Expert("gui.skill.expert"), Master("gui.skill.master");
/*     */     
/*     */     String name;
/*     */     
/*     */     SkillRank(String local) {
/* 276 */       this.name = local;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getUnlocalizedName() {
/* 281 */       return this.name;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getLocalizedName() {
/* 286 */       return TFC_Core.translate(this.name);
/*     */     } }
/*     */ 
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\Player\SkillStats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */