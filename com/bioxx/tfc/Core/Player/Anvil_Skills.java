/*     */ package com.bioxx.tfc.Core.Player;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Anvil_Skills
/*     */ {
/*   7 */   private static final double[][] WEAPON_mod = new double[][] { { 2182574.0D, -0.01311471D, 160.9209D, 0.7587267D, 1.752043E-5D }, { 8707224.0D, -0.1095062D, 568.7289D, 0.6730252D, 5.483342E-6D }, { 2772504.0D, -0.02210149D, 545.6473D, 0.7277708D, 1.352218E-5D }, { 4785152.0D, -0.08510828D, 1152.857D, 0.6289507D, 1.13967E-5D }, { 95.81505D, -0.01520951D, 4.852472E9D, 0.6205839D, 6601.871D }, { 87.96664D, 0.05674759D, 3.719587E9D, 0.6660817D, 13310.69D } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  16 */   private static final double[][] ARMOR_mod = new double[][] { { 136.4265D, -0.05507596D, 700.4815D, 0.7406342D, 1.333634D }, { 150.4811D, -0.0279769D, 2334.296D, 0.7656195D, 1.295762D }, { 159.3962D, -0.02377229D, 3697.671D, 0.7696301D, 1.171888D }, { 182.1076D, -0.04226746D, 2117.977D, 0.7572831D, 0.7563843D }, { 121.4766D, 0.04466283D, 3.03582E9D, 0.6916768D, 18395.55D }, { 126.3737D, -0.08595737D, 4695.196D, 0.7230726D, 2.358883D } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  25 */   private static final double[][] TOOLS_mod = new double[][] { { 1215769.0D, -0.0743595D, 122.2653D, 0.8337736D, 3.683509E-5D }, { 174.1547D, -0.05116831D, 3564.642D, 0.7147664D, 1.071231D }, { 163.3978D, -0.04814403D, 9409.0D, 0.7020651D, 1.500853D }, { 137.7674D, -0.09101151D, 94211.44D, 0.6705424D, 7.825762D }, { 145.8189D, -0.1078157D, 15911.25D, 0.6981541D, 2.835813D }, { 166.6608D, -0.07461839D, 4420.886D, 0.7233578D, 1.262362D } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int MAX_WEAP_PERCENT = 85;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  36 */   public Anvil_Skill WEAPON = new Anvil_Skill(WEAPON_mod);
/*  37 */   public Anvil_Skill ARMOR = new Anvil_Skill(ARMOR_mod);
/*  38 */   public Anvil_Skill TOOLS = new Anvil_Skill(TOOLS_mod);
/*     */ 
/*     */ 
/*     */   
/*     */   public Anvil_Skill getSkill(String skill) {
/*  43 */     switch (skill) { case "skill.weaponsmith":
/*  44 */         return this.WEAPON;
/*  45 */       case "skill.armorsmith": return this.ARMOR;
/*  46 */       case "skill.toolsmith": return this.TOOLS; }
/*     */     
/*  48 */     return null;
/*     */   }
/*     */   
/*     */   public void setSkill(String skill, Anvil_Skill sk) {
/*  52 */     switch (skill) { case "skill.weaponsmith":
/*  53 */         this.WEAPON = sk; break;
/*  54 */       case "skill.armorsmith": this.ARMOR = sk; break;
/*  55 */       case "skill.toolsmith": this.TOOLS = sk;
/*     */         break; }
/*     */   
/*     */   }
/*     */   
/*     */   public class Anvil_Skill
/*     */   {
/*  62 */     private double[] Tiers = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D };
/*     */     private double[][] mod;
/*     */     
/*     */     public Anvil_Skill(double[][] in) {
/*  66 */       this.mod = in;
/*     */     }
/*     */ 
/*     */     
/*     */     public void increase_exp(byte tier) {
/*  71 */       double booster = 1.0D;
/*  72 */       double bonus = math_bonus(tier);
/*  73 */       if (bonus < 0.4D) booster = 1.0D;
/*     */       
/*  75 */       this.Tiers[tier] = this.Tiers[tier] + booster;
/*     */       
/*  77 */       switch (tier) {
/*     */         case 0:
/*  79 */           this.Tiers[1] = this.Tiers[1] + 0.1D * booster;
/*  80 */           this.Tiers[2] = this.Tiers[2] + 0.02D * booster;
/*     */           break;
/*     */         case 1:
/*  83 */           this.Tiers[0] = this.Tiers[0] + 0.5D * booster;
/*  84 */           this.Tiers[1] = this.Tiers[1] + 0.1D * booster;
/*  85 */           this.Tiers[3] = this.Tiers[3] + 0.02D * booster;
/*     */           break;
/*     */         case 2:
/*  88 */           this.Tiers[0] = this.Tiers[0] + 0.33D * booster;
/*  89 */           this.Tiers[1] = this.Tiers[1] + 0.5D * booster;
/*  90 */           this.Tiers[3] = this.Tiers[3] + 0.1D * booster;
/*  91 */           this.Tiers[4] = this.Tiers[4] + 0.02D * booster;
/*     */           break;
/*     */         case 3:
/*  94 */           this.Tiers[0] = this.Tiers[0] + 0.13D * booster;
/*  95 */           this.Tiers[1] = this.Tiers[1] + 0.33D * booster;
/*  96 */           this.Tiers[2] = this.Tiers[2] + 0.5D * booster;
/*  97 */           this.Tiers[4] = this.Tiers[4] + 0.1D * booster;
/*  98 */           this.Tiers[5] = this.Tiers[5] + 0.02D * booster;
/*     */           break;
/*     */         case 4:
/* 101 */           this.Tiers[1] = this.Tiers[1] + 0.13D * booster;
/* 102 */           this.Tiers[2] = this.Tiers[2] + 0.33D * booster;
/* 103 */           this.Tiers[3] = this.Tiers[3] + 0.5D * booster;
/* 104 */           this.Tiers[5] = this.Tiers[5] + 0.1D * booster;
/*     */           break;
/*     */         case 5:
/* 107 */           this.Tiers[2] = this.Tiers[2] + 0.13D * booster;
/* 108 */           this.Tiers[3] = this.Tiers[3] + 0.33D * booster;
/* 109 */           this.Tiers[4] = this.Tiers[4] + 0.5D * booster;
/*     */           break;
/*     */       } 
/*     */     }
/*     */     
/*     */     public double math_bonus(byte tier) {
/* 115 */       double x = this.Tiers[tier];
/* 116 */       double bonus = this.mod[tier][0] + (this.mod[tier][1] - this.mod[tier][0]) / Math.pow(1.0D + Math.pow(x / this.mod[tier][2], this.mod[tier][3]), this.mod[tier][4]);
/* 117 */       if (this.mod.equals(Anvil_Skills.WEAPON_mod)) {
/* 118 */         double max_bonus_percent = (85 - tier * 5);
/* 119 */         double max_bonus_fake = Math.pow(Math.pow((this.mod[tier][1] - this.mod[tier][0]) / (max_bonus_percent + 1.0D - this.mod[tier][0]), 1.0D / this.mod[tier][4]) - 1.0D, 1.0D / this.mod[tier][3]) * this.mod[tier][2];
/* 120 */         double max_bonus_real = Math.pow(Math.pow((this.mod[tier][1] - this.mod[tier][0]) / (max_bonus_percent - this.mod[tier][0]), 1.0D / this.mod[tier][4]) - 1.0D, 1.0D / this.mod[tier][3]) * this.mod[tier][2];
/* 121 */         if (x > max_bonus_fake) {
/* 122 */           bonus = Math.sqrt(x / max_bonus_real);
/* 123 */           bonus += max_bonus_percent;
/*     */         } 
/*     */       } 
/* 126 */       return finalize_bonus(bonus);
/*     */     }
/*     */     
/*     */     private double finalize_bonus(double bonus) {
/* 130 */       if (bonus < 0.0D) { bonus = 0.0D; }
/* 131 */       else if (bonus > 100.0D) { bonus = 100.0D; }
/* 132 */        return bonus / 100.0D;
/*     */     }
/*     */     
/*     */     public double getTierData(byte tier) {
/* 136 */       return this.Tiers[tier];
/*     */     }
/*     */     
/*     */     public void setTierData(byte i, double t) {
/* 140 */       this.Tiers[i] = t;
/*     */     }
/*     */     
/*     */     public byte size() {
/* 144 */       return (byte)this.Tiers.length;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\Player\Anvil_Skills.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */