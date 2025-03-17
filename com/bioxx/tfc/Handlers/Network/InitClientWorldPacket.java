/*     */ package com.bioxx.tfc.Handlers.Network;
/*     */ 
/*     */ import com.bioxx.tfc.Core.Player.Anvil_Skills;
/*     */ import com.bioxx.tfc.Core.Player.FoodStatsTFC;
/*     */ import com.bioxx.tfc.Core.Player.PlayerInfo;
/*     */ import com.bioxx.tfc.Core.Player.PlayerInventory;
/*     */ import com.bioxx.tfc.Core.Player.PlayerManagerTFC;
/*     */ import com.bioxx.tfc.Core.Player.SkillStats;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import cpw.mods.fml.common.network.ByteBufUtils;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import io.netty.channel.ChannelHandlerContext;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InitClientWorldPacket
/*     */   extends AbstractPacket
/*     */ {
/*     */   private long seed;
/*     */   private long soberTime;
/*     */   private float stomachLevel;
/*     */   private float waterLevel;
/*     */   private float nutrFruit;
/*     */   private float nutrVeg;
/*     */   private float nutrGrain;
/*     */   private float nutrProtein;
/*     */   private float nutrDairy;
/*     */   private boolean craftingTable;
/*     */   private SkillStats playerSkills;
/*  35 */   private Map<String, Integer> skillMap = new HashMap<>();
/*     */   
/*     */   private byte chiselMode;
/*  38 */   private Anvil_Skills adv_skills = new Anvil_Skills(); private boolean debugMode; private int daysInYear; private int healthGainRate;
/*     */   private int healthGainCap;
/*     */   private int maxProtectionMonths;
/*     */   private int protectionGain;
/*     */   private int protectionBuffer;
/*     */   private int smallOreUnits;
/*     */   private int poorOreUnits;
/*     */   private int normalOreUnits;
/*     */   private int richOreUnits;
/*     */   private int torchBurnTime;
/*     */   
/*     */   public InitClientWorldPacket(EntityPlayer p) {
/*  50 */     this.seed = p.field_70170_p.func_72905_C();
/*     */     
/*  52 */     TFC_Time.updateTime(p.field_70170_p);
/*  53 */     FoodStatsTFC fs = TFC_Core.getPlayerFoodStats(p);
/*  54 */     fs.resetTimers();
/*  55 */     fs.writeNBT(p.getEntityData());
/*  56 */     this.stomachLevel = fs.stomachLevel;
/*  57 */     this.waterLevel = fs.waterLevel;
/*  58 */     this.soberTime = fs.soberTime;
/*  59 */     this.nutrFruit = fs.nutrFruit;
/*  60 */     this.nutrVeg = fs.nutrVeg;
/*  61 */     this.nutrGrain = fs.nutrGrain;
/*  62 */     this.nutrProtein = fs.nutrProtein;
/*  63 */     this.nutrDairy = fs.nutrDairy;
/*     */ 
/*     */     
/*  66 */     this.debugMode = TFCOptions.enableDebugMode;
/*  67 */     this.daysInYear = TFCOptions.yearLength;
/*  68 */     this.healthGainRate = TFCOptions.healthGainRate;
/*  69 */     this.healthGainCap = TFCOptions.healthGainCap;
/*  70 */     this.maxProtectionMonths = TFCOptions.maxProtectionMonths;
/*  71 */     this.protectionGain = TFCOptions.protectionGain;
/*  72 */     this.protectionBuffer = TFCOptions.protectionBuffer;
/*  73 */     this.smallOreUnits = TFCOptions.smallOreUnits;
/*  74 */     this.poorOreUnits = TFCOptions.poorOreUnits;
/*  75 */     this.normalOreUnits = TFCOptions.normalOreUnits;
/*  76 */     this.richOreUnits = TFCOptions.richOreUnits;
/*  77 */     this.pitKilnBurnTime = TFCOptions.pitKilnBurnTime;
/*  78 */     this.bloomeryBurnTime = TFCOptions.bloomeryBurnTime;
/*  79 */     this.charcoalPitBurnTime = TFCOptions.charcoalPitBurnTime;
/*  80 */     this.torchBurnTime = TFCOptions.torchBurnTime;
/*  81 */     this.saplingTimerMultiplier = TFCOptions.saplingTimerMultiplier;
/*  82 */     this.oilLampFuelMult = TFCOptions.oilLampFuelMult;
/*  83 */     this.animalTimeMultiplier = TFCOptions.animalTimeMultiplier;
/*     */ 
/*     */     
/*  86 */     if (p.getEntityData().func_74764_b("craftingTable"))
/*  87 */       this.craftingTable = true; 
/*  88 */     this.playerSkills = TFC_Core.getSkillStats(p);
/*  89 */     this.chiselMode = (PlayerManagerTFC.getInstance().getPlayerInfoFromPlayer(p)).chiselMode;
/*     */   }
/*     */   private int oilLampFuelMult;
/*     */   private float pitKilnBurnTime;
/*     */   
/*     */   public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
/*  95 */     buffer.writeLong(this.seed);
/*  96 */     buffer.writeFloat(this.stomachLevel);
/*  97 */     buffer.writeFloat(this.waterLevel);
/*  98 */     buffer.writeLong(this.soberTime);
/*  99 */     buffer.writeFloat(this.nutrFruit);
/* 100 */     buffer.writeFloat(this.nutrVeg);
/* 101 */     buffer.writeFloat(this.nutrGrain);
/* 102 */     buffer.writeFloat(this.nutrProtein);
/* 103 */     buffer.writeFloat(this.nutrDairy);
/* 104 */     buffer.writeBoolean(this.craftingTable);
/* 105 */     this.playerSkills.toOutBuffer(buffer);
/* 106 */     buffer.writeByte(this.chiselMode);
/*     */ 
/*     */     
/* 109 */     buffer.writeBoolean(this.debugMode);
/* 110 */     buffer.writeInt(this.daysInYear);
/* 111 */     buffer.writeInt(this.healthGainRate);
/* 112 */     buffer.writeInt(this.healthGainCap);
/* 113 */     buffer.writeInt(this.maxProtectionMonths);
/* 114 */     buffer.writeInt(this.protectionGain);
/* 115 */     buffer.writeInt(this.protectionBuffer);
/* 116 */     buffer.writeInt(this.smallOreUnits);
/* 117 */     buffer.writeInt(this.poorOreUnits);
/* 118 */     buffer.writeInt(this.normalOreUnits);
/* 119 */     buffer.writeInt(this.richOreUnits);
/* 120 */     buffer.writeInt(this.torchBurnTime);
/* 121 */     buffer.writeInt(this.oilLampFuelMult);
/* 122 */     buffer.writeFloat(this.pitKilnBurnTime);
/* 123 */     buffer.writeFloat(this.bloomeryBurnTime);
/* 124 */     buffer.writeFloat(this.charcoalPitBurnTime);
/* 125 */     buffer.writeFloat(this.saplingTimerMultiplier);
/* 126 */     buffer.writeFloat(this.animalTimeMultiplier);
/*     */   }
/*     */   private float bloomeryBurnTime;
/*     */   private float charcoalPitBurnTime;
/*     */   
/*     */   public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
/* 132 */     this.seed = buffer.readLong();
/* 133 */     this.stomachLevel = buffer.readFloat();
/* 134 */     this.waterLevel = buffer.readFloat();
/* 135 */     this.soberTime = buffer.readLong();
/* 136 */     this.nutrFruit = buffer.readFloat();
/* 137 */     this.nutrVeg = buffer.readFloat();
/* 138 */     this.nutrGrain = buffer.readFloat();
/* 139 */     this.nutrProtein = buffer.readFloat();
/* 140 */     this.nutrDairy = buffer.readFloat();
/* 141 */     this.craftingTable = buffer.readBoolean();
/*     */     
/* 143 */     this.skillMap.clear();
/*     */ 
/*     */     
/* 146 */     int size = buffer.readInt();
/* 147 */     for (int l = 0; l < size; l++) {
/*     */       
/* 149 */       String name = ByteBufUtils.readUTF8String(buffer);
/* 150 */       int lvl = buffer.readInt();
/* 151 */       this.skillMap.put(name, Integer.valueOf(lvl));
/* 152 */       Anvil_Skills.Anvil_Skill as = this.adv_skills.getSkill(name);
/* 153 */       if (as != null) { byte i; for (i = 0; i < as.size(); ) { as.setTierData(i, buffer.readDouble()); i = (byte)(i + 1); }
/*     */          }
/*     */     
/* 156 */     }  this.chiselMode = buffer.readByte();
/*     */ 
/*     */     
/* 159 */     this.debugMode = buffer.readBoolean();
/* 160 */     this.daysInYear = buffer.readInt();
/* 161 */     this.healthGainRate = buffer.readInt();
/* 162 */     this.healthGainCap = buffer.readInt();
/* 163 */     this.maxProtectionMonths = buffer.readInt();
/* 164 */     this.protectionGain = buffer.readInt();
/* 165 */     this.protectionBuffer = buffer.readInt();
/* 166 */     this.smallOreUnits = buffer.readInt();
/* 167 */     this.poorOreUnits = buffer.readInt();
/* 168 */     this.normalOreUnits = buffer.readInt();
/* 169 */     this.richOreUnits = buffer.readInt();
/* 170 */     this.torchBurnTime = buffer.readInt();
/* 171 */     this.oilLampFuelMult = buffer.readInt();
/* 172 */     this.pitKilnBurnTime = buffer.readFloat();
/* 173 */     this.bloomeryBurnTime = buffer.readFloat();
/* 174 */     this.charcoalPitBurnTime = buffer.readFloat();
/* 175 */     this.saplingTimerMultiplier = buffer.readFloat();
/* 176 */     this.animalTimeMultiplier = buffer.readFloat();
/*     */   }
/*     */   private float saplingTimerMultiplier; private float animalTimeMultiplier;
/*     */   public InitClientWorldPacket() {}
/*     */   
/*     */   public void handleClientSide(EntityPlayer player) {
/* 182 */     FoodStatsTFC fs = TFC_Core.getPlayerFoodStats(player);
/* 183 */     fs.stomachLevel = this.stomachLevel;
/* 184 */     fs.waterLevel = this.waterLevel;
/* 185 */     fs.soberTime = this.soberTime;
/* 186 */     fs.nutrFruit = this.nutrFruit;
/* 187 */     fs.nutrVeg = this.nutrVeg;
/* 188 */     fs.nutrProtein = this.nutrProtein;
/* 189 */     fs.nutrDairy = this.nutrDairy;
/* 190 */     TFC_Core.setPlayerFoodStats(player, fs);
/*     */     
/* 192 */     if (this.craftingTable) {
/*     */       
/* 194 */       player.getEntityData().func_74757_a("craftingTable", this.craftingTable);
/* 195 */       PlayerInventory.upgradePlayerCrafting(player);
/*     */     } 
/* 197 */     TFC_Core.setupWorld(player.field_70170_p, this.seed);
/*     */     
/* 199 */     this.playerSkills = TFC_Core.getSkillStats(player);
/* 200 */     this.playerSkills.adv_skills = this.adv_skills;
/* 201 */     for (String skill : this.skillMap.keySet())
/*     */     {
/* 203 */       this.playerSkills.setSkillSave(skill, ((Integer)this.skillMap.get(skill)).intValue());
/*     */     }
/* 205 */     this.skillMap.clear();
/*     */     
/* 207 */     (PlayerManagerTFC.getInstance()).players.add(new PlayerInfo(player
/* 208 */           .func_70005_c_(), player
/* 209 */           .func_110124_au()));
/*     */     
/* 211 */     PlayerManagerTFC.getInstance().getClientPlayer().setChiselMode(this.chiselMode);
/*     */ 
/*     */     
/* 214 */     TFCOptions.enableDebugMode = this.debugMode;
/* 215 */     TFC_Time.setYearLength(this.daysInYear);
/* 216 */     TFCOptions.healthGainRate = this.healthGainRate;
/* 217 */     TFCOptions.healthGainCap = this.healthGainCap;
/* 218 */     TFCOptions.maxProtectionMonths = this.maxProtectionMonths;
/* 219 */     TFCOptions.protectionGain = this.protectionGain;
/* 220 */     TFCOptions.protectionBuffer = this.protectionBuffer;
/* 221 */     TFCOptions.smallOreUnits = this.smallOreUnits;
/* 222 */     TFCOptions.poorOreUnits = this.poorOreUnits;
/* 223 */     TFCOptions.normalOreUnits = this.normalOreUnits;
/* 224 */     TFCOptions.richOreUnits = this.richOreUnits;
/* 225 */     TFCOptions.torchBurnTime = this.torchBurnTime;
/* 226 */     TFCOptions.oilLampFuelMult = this.oilLampFuelMult;
/* 227 */     TFCOptions.pitKilnBurnTime = this.pitKilnBurnTime;
/* 228 */     TFCOptions.bloomeryBurnTime = this.bloomeryBurnTime;
/* 229 */     TFCOptions.charcoalPitBurnTime = this.charcoalPitBurnTime;
/* 230 */     TFCOptions.saplingTimerMultiplier = this.saplingTimerMultiplier;
/* 231 */     TFCOptions.animalTimeMultiplier = this.animalTimeMultiplier;
/*     */   }
/*     */   
/*     */   public void handleServerSide(EntityPlayer player) {}
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\Network\InitClientWorldPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */