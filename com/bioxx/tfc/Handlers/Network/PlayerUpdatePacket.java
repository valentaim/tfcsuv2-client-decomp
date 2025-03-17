/*     */ package com.bioxx.tfc.Handlers.Network;
/*     */ 
/*     */ import com.bioxx.tfc.Core.Player.Anvil_Skills;
/*     */ import com.bioxx.tfc.Core.Player.FoodStatsTFC;
/*     */ import com.bioxx.tfc.Core.Player.PlayerInventory;
/*     */ import com.bioxx.tfc.Core.Player.SkillStats;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import cpw.mods.fml.common.network.ByteBufUtils;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import io.netty.channel.ChannelHandlerContext;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlayerUpdatePacket
/*     */   extends AbstractPacket
/*     */ {
/*     */   private byte flag;
/*     */   private float stomachLevel;
/*     */   private float waterLevel;
/*     */   private long soberTime;
/*     */   private float nutrFruit;
/*     */   private float nutrVeg;
/*     */   private float nutrGrain;
/*     */   private float nutrProtein;
/*     */   private float nutrDairy;
/*     */   private SkillStats playerSkills;
/*     */   private String skillName;
/*     */   private int skillLevel;
/*     */   private boolean craftingTable;
/*  35 */   private Map<String, Integer> skillMap = new HashMap<>();
/*     */   
/*  37 */   private Anvil_Skills adv_skills = new Anvil_Skills();
/*  38 */   private Anvil_Skills.Anvil_Skill adv_skill = null;
/*     */ 
/*     */   
/*     */   public PlayerUpdatePacket() {}
/*     */   
/*     */   public PlayerUpdatePacket(EntityPlayer p, int f) {
/*  44 */     this.flag = (byte)f;
/*  45 */     if (this.flag == 0) {
/*     */       
/*  47 */       FoodStatsTFC fs = TFC_Core.getPlayerFoodStats(p);
/*  48 */       this.stomachLevel = fs.stomachLevel;
/*  49 */       this.waterLevel = fs.waterLevel;
/*  50 */       this.soberTime = fs.soberTime;
/*  51 */       this.nutrFruit = fs.nutrFruit;
/*  52 */       this.nutrVeg = fs.nutrVeg;
/*  53 */       this.nutrGrain = fs.nutrGrain;
/*  54 */       this.nutrProtein = fs.nutrProtein;
/*  55 */       this.nutrDairy = fs.nutrDairy;
/*     */     }
/*  57 */     else if (this.flag == 2) {
/*     */       
/*  59 */       this.craftingTable = p.getEntityData().func_74767_n("craftingTable");
/*     */     }
/*  61 */     else if (this.flag == 3) {
/*     */       
/*  63 */       this.playerSkills = TFC_Core.getSkillStats(p);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PlayerUpdatePacket(int f, String name, int lvl, EntityPlayer p) {
/*  73 */     this.flag = (byte)f;
/*  74 */     if (this.flag == 1) {
/*     */       
/*  76 */       this.skillName = name;
/*  77 */       this.skillLevel = lvl;
/*     */ 
/*     */       
/*  80 */       this.playerSkills = TFC_Core.getSkillStats(p);
/*  81 */       this.adv_skill = this.playerSkills.adv_skills.getSkill(name);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
/*  88 */     buffer.writeByte(this.flag);
/*  89 */     if (this.flag == 0) {
/*     */       
/*  91 */       buffer.writeFloat(this.stomachLevel);
/*  92 */       buffer.writeFloat(this.waterLevel);
/*  93 */       buffer.writeLong(this.soberTime);
/*  94 */       buffer.writeFloat(this.nutrFruit);
/*  95 */       buffer.writeFloat(this.nutrVeg);
/*  96 */       buffer.writeFloat(this.nutrGrain);
/*  97 */       buffer.writeFloat(this.nutrProtein);
/*  98 */       buffer.writeFloat(this.nutrDairy);
/*     */     }
/* 100 */     else if (this.flag == 1) {
/*     */       
/* 102 */       ByteBufUtils.writeUTF8String(buffer, this.skillName);
/* 103 */       buffer.writeInt(this.skillLevel);
/* 104 */       if (this.adv_skill != null) for (byte i = 0; i < this.adv_skill.size(); ) { buffer.writeDouble(this.adv_skill.getTierData(i)); i = (byte)(i + 1); }
/*     */       
/*     */     
/* 107 */     } else if (this.flag == 2) {
/*     */       
/* 109 */       buffer.writeBoolean(this.craftingTable);
/*     */     }
/* 111 */     else if (this.flag == 3) {
/*     */       
/* 113 */       this.playerSkills.toOutBuffer(buffer);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
/* 124 */     this.flag = buffer.readByte();
/* 125 */     if (this.flag == 0) {
/*     */       
/* 127 */       this.stomachLevel = buffer.readFloat();
/* 128 */       this.waterLevel = buffer.readFloat();
/* 129 */       this.soberTime = buffer.readLong();
/* 130 */       this.nutrFruit = buffer.readFloat();
/* 131 */       this.nutrVeg = buffer.readFloat();
/* 132 */       this.nutrGrain = buffer.readFloat();
/* 133 */       this.nutrProtein = buffer.readFloat();
/* 134 */       this.nutrDairy = buffer.readFloat();
/*     */     }
/* 136 */     else if (this.flag == 1) {
/*     */       
/* 138 */       this.skillName = ByteBufUtils.readUTF8String(buffer);
/* 139 */       this.skillLevel = buffer.readInt();
/* 140 */       this.adv_skill = this.adv_skills.getSkill(this.skillName);
/* 141 */       if (this.adv_skill != null) for (byte i = 0; i < this.adv_skill.size(); ) { this.adv_skill.setTierData(i, buffer.readDouble()); i = (byte)(i + 1); }
/*     */       
/*     */     
/* 144 */     } else if (this.flag == 2) {
/*     */       
/* 146 */       this.craftingTable = buffer.readBoolean();
/*     */     }
/* 148 */     else if (this.flag == 3) {
/*     */       
/* 150 */       this.skillMap.clear();
/*     */ 
/*     */       
/* 153 */       int size = buffer.readInt();
/* 154 */       for (int l = 0; l < size; l++) {
/*     */         
/* 156 */         String name = ByteBufUtils.readUTF8String(buffer);
/* 157 */         int lvl = buffer.readInt();
/* 158 */         this.skillMap.put(name, Integer.valueOf(lvl));
/* 159 */         Anvil_Skills.Anvil_Skill as = this.adv_skills.getSkill(name);
/* 160 */         if (as != null) { byte i; for (i = 0; i < as.size(); ) { as.setTierData(i, buffer.readDouble()); i = (byte)(i + 1); }
/*     */            }
/*     */       
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleClientSide(EntityPlayer player) {
/* 172 */     if (this.flag == 0) {
/*     */       
/* 174 */       FoodStatsTFC fs = TFC_Core.getPlayerFoodStats(player);
/* 175 */       fs.stomachLevel = this.stomachLevel;
/* 176 */       fs.waterLevel = this.waterLevel;
/* 177 */       fs.soberTime = this.soberTime;
/* 178 */       fs.nutrFruit = this.nutrFruit;
/* 179 */       fs.nutrVeg = this.nutrVeg;
/* 180 */       fs.nutrGrain = this.nutrGrain;
/* 181 */       fs.nutrProtein = this.nutrProtein;
/* 182 */       fs.nutrDairy = this.nutrDairy;
/* 183 */       TFC_Core.setPlayerFoodStats(player, fs);
/*     */     }
/* 185 */     else if (this.flag == 1) {
/*     */       
/* 187 */       this.playerSkills = TFC_Core.getSkillStats(player);
/* 188 */       if (this.adv_skill != null) this.playerSkills.adv_skills.setSkill(this.skillName, this.adv_skill); 
/* 189 */       this.playerSkills.setSkillSave(this.skillName, this.skillLevel);
/*     */     }
/* 191 */     else if (this.flag == 2) {
/*     */       
/* 193 */       if (this.craftingTable && !player.getEntityData().func_74764_b("craftingTable"))
/*     */       {
/* 195 */         player.getEntityData().func_74757_a("craftingTable", this.craftingTable);
/* 196 */         PlayerInventory.upgradePlayerCrafting(player);
/*     */       }
/*     */     
/* 199 */     } else if (this.flag == 3) {
/*     */       
/* 201 */       this.playerSkills = TFC_Core.getSkillStats(player);
/* 202 */       this.playerSkills.adv_skills = this.adv_skills;
/* 203 */       for (String skill : this.skillMap.keySet())
/*     */       {
/* 205 */         this.playerSkills.setSkillSave(skill, ((Integer)this.skillMap.get(skill)).intValue());
/*     */       }
/* 207 */       this.skillMap.clear();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleServerSide(EntityPlayer player) {
/* 218 */     if (this.flag == 4) {
/*     */       
/* 220 */       AbstractPacket pkt = new PlayerUpdatePacket(player, 3);
/* 221 */       TerraFirmaCraft.PACKET_PIPELINE.sendTo(pkt, (EntityPlayerMP)player);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\Network\PlayerUpdatePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */