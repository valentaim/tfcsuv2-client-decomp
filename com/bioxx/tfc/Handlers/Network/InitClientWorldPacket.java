package com.bioxx.tfc.Handlers.Network;

import com.bioxx.tfc.Core.Player.Anvil_Skills;
import com.bioxx.tfc.Core.Player.FoodStatsTFC;
import com.bioxx.tfc.Core.Player.PlayerInfo;
import com.bioxx.tfc.Core.Player.PlayerInventory;
import com.bioxx.tfc.Core.Player.PlayerManagerTFC;
import com.bioxx.tfc.Core.Player.SkillStats;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.api.TFCOptions;
import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;



public class InitClientWorldPacket
  extends AbstractPacket
{
  private long seed;
  private long soberTime;
  private float stomachLevel;
  private float waterLevel;
  private float nutrFruit;
  private float nutrVeg;
  private float nutrGrain;
  private float nutrProtein;
  private float nutrDairy;
  private boolean craftingTable;
  private SkillStats playerSkills;
  private Map<String, Integer> skillMap = new HashMap<>();

  private byte chiselMode;
  private Anvil_Skills adv_skills = new Anvil_Skills(); private boolean debugMode; private int daysInYear; private int healthGainRate;
  private int healthGainCap;
  private int maxProtectionMonths;
  private int protectionGain;
  private int protectionBuffer;
  private int smallOreUnits;
  private int poorOreUnits;
  private int normalOreUnits;
  private int richOreUnits;
  private int torchBurnTime;

  public InitClientWorldPacket(EntityPlayer p) {
    this.seed = p.field_70170_p.func_72905_C();

    TFC_Time.updateTime(p.field_70170_p);
    FoodStatsTFC fs = TFC_Core.getPlayerFoodStats(p);
    fs.resetTimers();
    fs.writeNBT(p.getEntityData());
    this.stomachLevel = fs.stomachLevel;
    this.waterLevel = fs.waterLevel;
    this.soberTime = fs.soberTime;
    this.nutrFruit = fs.nutrFruit;
    this.nutrVeg = fs.nutrVeg;
    this.nutrGrain = fs.nutrGrain;
    this.nutrProtein = fs.nutrProtein;
    this.nutrDairy = fs.nutrDairy;


    this.debugMode = TFCOptions.enableDebugMode;
    this.daysInYear = TFCOptions.yearLength;
    this.healthGainRate = TFCOptions.healthGainRate;
    this.healthGainCap = TFCOptions.healthGainCap;
    this.maxProtectionMonths = TFCOptions.maxProtectionMonths;
    this.protectionGain = TFCOptions.protectionGain;
    this.protectionBuffer = TFCOptions.protectionBuffer;
    this.smallOreUnits = TFCOptions.smallOreUnits;
    this.poorOreUnits = TFCOptions.poorOreUnits;
    this.normalOreUnits = TFCOptions.normalOreUnits;
    this.richOreUnits = TFCOptions.richOreUnits;
    this.pitKilnBurnTime = TFCOptions.pitKilnBurnTime;
    this.bloomeryBurnTime = TFCOptions.bloomeryBurnTime;
    this.charcoalPitBurnTime = TFCOptions.charcoalPitBurnTime;
    this.torchBurnTime = TFCOptions.torchBurnTime;
    this.saplingTimerMultiplier = TFCOptions.saplingTimerMultiplier;
    this.oilLampFuelMult = TFCOptions.oilLampFuelMult;
    this.animalTimeMultiplier = TFCOptions.animalTimeMultiplier;


    if (p.getEntityData().func_74764_b("craftingTable"))
      this.craftingTable = true;
    this.playerSkills = TFC_Core.getSkillStats(p);
    this.chiselMode = (PlayerManagerTFC.getInstance().getPlayerInfoFromPlayer(p)).chiselMode;
  }
  private int oilLampFuelMult;
  private float pitKilnBurnTime;

  public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
    buffer.writeLong(this.seed);
    buffer.writeFloat(this.stomachLevel);
    buffer.writeFloat(this.waterLevel);
    buffer.writeLong(this.soberTime);
    buffer.writeFloat(this.nutrFruit);
    buffer.writeFloat(this.nutrVeg);
    buffer.writeFloat(this.nutrGrain);
    buffer.writeFloat(this.nutrProtein);
    buffer.writeFloat(this.nutrDairy);
    buffer.writeBoolean(this.craftingTable);
    this.playerSkills.toOutBuffer(buffer);
    buffer.writeByte(this.chiselMode);


    buffer.writeBoolean(this.debugMode);
    buffer.writeInt(this.daysInYear);
    buffer.writeInt(this.healthGainRate);
    buffer.writeInt(this.healthGainCap);
    buffer.writeInt(this.maxProtectionMonths);
    buffer.writeInt(this.protectionGain);
    buffer.writeInt(this.protectionBuffer);
    buffer.writeInt(this.smallOreUnits);
    buffer.writeInt(this.poorOreUnits);
    buffer.writeInt(this.normalOreUnits);
    buffer.writeInt(this.richOreUnits);
    buffer.writeInt(this.torchBurnTime);
    buffer.writeInt(this.oilLampFuelMult);
    buffer.writeFloat(this.pitKilnBurnTime);
    buffer.writeFloat(this.bloomeryBurnTime);
    buffer.writeFloat(this.charcoalPitBurnTime);
    buffer.writeFloat(this.saplingTimerMultiplier);
    buffer.writeFloat(this.animalTimeMultiplier);
  }
  private float bloomeryBurnTime;
  private float charcoalPitBurnTime;

  public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
    this.seed = buffer.readLong();
    this.stomachLevel = buffer.readFloat();
    this.waterLevel = buffer.readFloat();
    this.soberTime = buffer.readLong();
    this.nutrFruit = buffer.readFloat();
    this.nutrVeg = buffer.readFloat();
    this.nutrGrain = buffer.readFloat();
    this.nutrProtein = buffer.readFloat();
    this.nutrDairy = buffer.readFloat();
    this.craftingTable = buffer.readBoolean();

    this.skillMap.clear();


    int size = buffer.readInt();
    for (int l = 0; l < size; l++) {

      String name = ByteBufUtils.readUTF8String(buffer);
      int lvl = buffer.readInt();
      this.skillMap.put(name, Integer.valueOf(lvl));
      Anvil_Skills.Anvil_Skill as = this.adv_skills.getSkill(name);
      if (as != null) { byte i; for (i = 0; i < as.size(); ) { as.setTierData(i, buffer.readDouble()); i = (byte)(i + 1); }
         }

    }  this.chiselMode = buffer.readByte();


    this.debugMode = buffer.readBoolean();
    this.daysInYear = buffer.readInt();
    this.healthGainRate = buffer.readInt();
    this.healthGainCap = buffer.readInt();
    this.maxProtectionMonths = buffer.readInt();
    this.protectionGain = buffer.readInt();
    this.protectionBuffer = buffer.readInt();
    this.smallOreUnits = buffer.readInt();
    this.poorOreUnits = buffer.readInt();
    this.normalOreUnits = buffer.readInt();
    this.richOreUnits = buffer.readInt();
    this.torchBurnTime = buffer.readInt();
    this.oilLampFuelMult = buffer.readInt();
    this.pitKilnBurnTime = buffer.readFloat();
    this.bloomeryBurnTime = buffer.readFloat();
    this.charcoalPitBurnTime = buffer.readFloat();
    this.saplingTimerMultiplier = buffer.readFloat();
    this.animalTimeMultiplier = buffer.readFloat();
  }
  private float saplingTimerMultiplier; private float animalTimeMultiplier;
  public InitClientWorldPacket() {}

  public void handleClientSide(EntityPlayer player) {
    FoodStatsTFC fs = TFC_Core.getPlayerFoodStats(player);
    fs.stomachLevel = this.stomachLevel;
    fs.waterLevel = this.waterLevel;
    fs.soberTime = this.soberTime;
    fs.nutrFruit = this.nutrFruit;
    fs.nutrVeg = this.nutrVeg;
    fs.nutrProtein = this.nutrProtein;
    fs.nutrDairy = this.nutrDairy;
    TFC_Core.setPlayerFoodStats(player, fs);

    if (this.craftingTable) {

      player.getEntityData().func_74757_a("craftingTable", this.craftingTable);
      PlayerInventory.upgradePlayerCrafting(player);
    }
    TFC_Core.setupWorld(player.field_70170_p, this.seed);

    this.playerSkills = TFC_Core.getSkillStats(player);
    this.playerSkills.adv_skills = this.adv_skills;
    for (String skill : this.skillMap.keySet())
    {
      this.playerSkills.setSkillSave(skill, ((Integer)this.skillMap.get(skill)).intValue());
    }
    this.skillMap.clear();

    (PlayerManagerTFC.getInstance()).players.add(new PlayerInfo(player
          .func_70005_c_(), player
          .func_110124_au()));

    PlayerManagerTFC.getInstance().getClientPlayer().setChiselMode(this.chiselMode);


    TFCOptions.enableDebugMode = this.debugMode;
    TFC_Time.setYearLength(this.daysInYear);
    TFCOptions.healthGainRate = this.healthGainRate;
    TFCOptions.healthGainCap = this.healthGainCap;
    TFCOptions.maxProtectionMonths = this.maxProtectionMonths;
    TFCOptions.protectionGain = this.protectionGain;
    TFCOptions.protectionBuffer = this.protectionBuffer;
    TFCOptions.smallOreUnits = this.smallOreUnits;
    TFCOptions.poorOreUnits = this.poorOreUnits;
    TFCOptions.normalOreUnits = this.normalOreUnits;
    TFCOptions.richOreUnits = this.richOreUnits;
    TFCOptions.torchBurnTime = this.torchBurnTime;
    TFCOptions.oilLampFuelMult = this.oilLampFuelMult;
    TFCOptions.pitKilnBurnTime = this.pitKilnBurnTime;
    TFCOptions.bloomeryBurnTime = this.bloomeryBurnTime;
    TFCOptions.charcoalPitBurnTime = this.charcoalPitBurnTime;
    TFCOptions.saplingTimerMultiplier = this.saplingTimerMultiplier;
    TFCOptions.animalTimeMultiplier = this.animalTimeMultiplier;
  }

  public void handleServerSide(EntityPlayer player) {}
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\Network\InitClientWorldPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */