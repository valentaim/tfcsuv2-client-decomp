/*     */ package com.bioxx.tfc.Items.Tools;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.Terrain.BlockOre;
/*     */ import com.bioxx.tfc.Core.Player.SkillStats;
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Core.TFC_Textures;
/*     */ import com.bioxx.tfc.Items.ItemTerra;
/*     */ import com.bioxx.tfc.TileEntities.TEOre;
/*     */ import com.bioxx.tfc.WorldGen.Generators.OreSpawnData;
/*     */ import com.bioxx.tfc.WorldGen.Generators.WorldGenOre;
/*     */ import com.bioxx.tfc.api.Constant.Global;
/*     */ import com.bioxx.tfc.api.Crafting.AnvilManager;
/*     */ import com.bioxx.tfc.api.Enums.EnumItemReach;
/*     */ import com.bioxx.tfc.api.Enums.EnumSize;
/*     */ import com.bioxx.tfc.api.Enums.EnumWeight;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.ForgeHooks;
/*     */ 
/*     */ public class ItemProPick
/*     */   extends ItemTerra
/*     */ {
/*  39 */   private Map<String, ProspectResult> results = new HashMap<>();
/*     */   
/*     */   private Random random;
/*     */ 
/*     */   
/*     */   public ItemProPick() {
/*  45 */     this.field_77777_bU = 1;
/*  46 */     func_77637_a(TFCTabs.TFC_TOOLS);
/*  47 */     setWeight(EnumWeight.LIGHT);
/*  48 */     setSize(EnumSize.SMALL);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_94581_a(IIconRegister registerer) {
/*  54 */     this.field_77791_bV = registerer.func_94245_a("terrafirmacraft:tools/" + func_77658_a().replace("item.", ""));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IIcon getIcon(ItemStack stack, int pass) {
/*  60 */     NBTTagCompound nbt = stack.func_77978_p();
/*  61 */     if (pass == 1 && nbt != null && nbt.func_74764_b("broken")) {
/*  62 */       return TFC_Textures.brokenItem;
/*     */     }
/*  64 */     return func_77618_c(stack.func_77960_j(), pass);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_77648_a(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
/*  70 */     Block block = world.func_147439_a(x, y, z);
/*  71 */     if (!world.field_72995_K) {
/*     */ 
/*     */       
/*  74 */       if (block == TFCBlocks.toolRack) {
/*  75 */         return true;
/*     */       }
/*     */       
/*  78 */       int meta = world.func_72805_g(x, y, z);
/*     */       
/*  80 */       SkillStats.SkillRank rank = TFC_Core.getSkillStats(player).getSkillRank("skill.prospecting");
/*     */ 
/*     */       
/*  83 */       if ((block == TFCBlocks.ore || block == TFCBlocks.ore2 || block == TFCBlocks.ore3) && world
/*  84 */         .func_147438_o(x, y, z) instanceof TEOre) {
/*     */         
/*  86 */         TEOre te = (TEOre)world.func_147438_o(x, y, z);
/*  87 */         if (block == TFCBlocks.ore && rank == SkillStats.SkillRank.Master)
/*  88 */           meta = ((BlockOre)block).getOreGrade(te, meta); 
/*  89 */         if (block == TFCBlocks.ore2) meta += Global.ORE_METAL.length; 
/*  90 */         if (block == TFCBlocks.ore3) meta = meta + Global.ORE_METAL.length + Global.ORE_MINERAL.length; 
/*  91 */         tellResult(player, new ItemStack(TFCItems.oreChunk, 1, meta));
/*  92 */         return true;
/*     */       } 
/*  94 */       if (!TFC_Core.isGround(block)) {
/*     */         
/*  96 */         Iterator<OreSpawnData> iter = WorldGenOre.oreList.values().iterator();
/*  97 */         while (iter.hasNext()) {
/*     */           
/*  99 */           OreSpawnData osd = iter.next();
/* 100 */           if (osd != null && block == osd.block) {
/*     */             
/* 102 */             tellResult(player, new ItemStack(block));
/* 103 */             return true;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 108 */       this.random = new Random((x * z + y));
/* 109 */       int chance = 60 + (rank.ordinal() + 1) * 10;
/*     */       
/* 111 */       this.results.clear();
/*     */ 
/*     */       
/* 114 */       if (this.random.nextInt(100) >= chance && rank != SkillStats.SkillRank.Master) {
/*     */         
/* 116 */         tellNothingFound(player);
/* 117 */         return true;
/*     */       } 
/*     */       
/* 120 */       this.results.clear();
/*     */ 
/*     */       
/* 123 */       for (int i = -12; i < 12; i++) {
/*     */         
/* 125 */         for (int j = -12; j < 12; j++) {
/*     */           
/* 127 */           for (int k = -12; k < 12; k++) {
/*     */             
/* 129 */             int blockX = x + i;
/* 130 */             int blockY = y + j;
/* 131 */             int blockZ = z + k;
/*     */             
/* 133 */             block = world.func_147439_a(blockX, blockY, blockZ);
/* 134 */             meta = world.func_72805_g(blockX, blockY, blockZ);
/* 135 */             ItemStack ore = null;
/* 136 */             if (block == TFCBlocks.ore && world.func_147438_o(blockX, blockY, blockZ) instanceof TEOre) {
/*     */               
/* 138 */               TEOre te = (TEOre)world.func_147438_o(blockX, blockY, blockZ);
/* 139 */               if (rank == SkillStats.SkillRank.Master) {
/* 140 */                 ore = new ItemStack(TFCItems.oreChunk, 1, ((BlockOre)block).getOreGrade(te, meta));
/*     */               } else {
/* 142 */                 ore = new ItemStack(TFCItems.oreChunk, 1, meta);
/*     */               } 
/* 144 */             } else if (block == TFCBlocks.ore2) {
/* 145 */               ore = new ItemStack(TFCItems.oreChunk, 1, meta + Global.ORE_METAL.length);
/* 146 */             } else if (block == TFCBlocks.ore3) {
/* 147 */               ore = new ItemStack(TFCItems.oreChunk, 1, meta + Global.ORE_METAL.length + Global.ORE_MINERAL.length);
/* 148 */             } else if (!TFC_Core.isGround(block)) {
/*     */               
/* 150 */               Iterator<OreSpawnData> iter = WorldGenOre.oreList.values().iterator();
/* 151 */               while (iter.hasNext()) {
/*     */                 
/* 153 */                 OreSpawnData osd = iter.next();
/* 154 */                 if (osd != null && block == osd.block) {
/*     */                   
/* 156 */                   ore = new ItemStack(block);
/*     */                   
/*     */                   break;
/*     */                 } 
/*     */               } 
/*     */             } else {
/*     */               continue;
/*     */             } 
/* 164 */             if (ore != null) {
/*     */               
/* 166 */               String oreName = ore.func_82833_r();
/*     */               
/* 168 */               if (this.results.containsKey(oreName)) {
/* 169 */                 ((ProspectResult)this.results.get(oreName)).count++;
/*     */               } else {
/* 171 */                 this.results.put(oreName, new ProspectResult(ore, 1));
/*     */               } 
/* 173 */               ore = null;
/* 174 */               oreName = null;
/*     */             } 
/*     */             
/*     */             continue;
/*     */           } 
/*     */         } 
/*     */       } 
/* 181 */       if (this.results.isEmpty()) {
/* 182 */         tellNothingFound(player);
/*     */       } else {
/* 184 */         tellResult(player);
/*     */       } 
/*     */       
/* 187 */       this.results.clear();
/* 188 */       this.random = null;
/*     */ 
/*     */       
/* 191 */       itemStack.func_77972_a(1, (EntityLivingBase)player);
/* 192 */       if (itemStack.func_77960_j() >= itemStack.func_77958_k()) {
/* 193 */         player.func_71028_bD();
/*     */       }
/*     */     } 
/* 196 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void tellNothingFound(EntityPlayer player) {
/* 204 */     TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("gui.ProPick.FoundNothing", new Object[0]));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void tellResult(EntityPlayer player, ItemStack ore) {
/* 212 */     String oreName = ore.func_77977_a() + ".name";
/* 213 */     TFC_Core.sendInfoMessage(player, (new ChatComponentTranslation("gui.ProPick.Found", new Object[0]))
/*     */         
/* 215 */         .func_150258_a(" ")
/* 216 */         .func_150257_a((IChatComponent)new ChatComponentTranslation(oreName, new Object[0])));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void tellResult(EntityPlayer player) {
/*     */     String quantityMsg;
/* 225 */     TFC_Core.getSkillStats(player).increaseSkill("skill.prospecting", 1);
/* 226 */     int index = this.random.nextInt(this.results.size());
/* 227 */     ProspectResult result = ((ProspectResult[])this.results.values().toArray((T[])new ProspectResult[0]))[index];
/* 228 */     String oreName = result.itemStack.func_77977_a() + ".name";
/*     */ 
/*     */     
/* 231 */     if (result.count < 10) {
/* 232 */       quantityMsg = "gui.ProPick.FoundTraces";
/* 233 */     } else if (result.count < 20) {
/* 234 */       quantityMsg = "gui.ProPick.FoundSmall";
/* 235 */     } else if (result.count < 40) {
/* 236 */       quantityMsg = "gui.ProPick.FoundMedium";
/* 237 */     } else if (result.count < 80) {
/* 238 */       quantityMsg = "gui.ProPick.FoundLarge";
/*     */     } else {
/* 240 */       quantityMsg = "gui.ProPick.FoundVeryLarge";
/*     */     } 
/* 242 */     TFC_Core.sendInfoMessage(player, (new ChatComponentTranslation(quantityMsg, new Object[0]))
/*     */         
/* 244 */         .func_150258_a(" ")
/* 245 */         .func_150257_a((IChatComponent)new ChatComponentTranslation(oreName, new Object[0])));
/*     */     
/* 247 */     oreName = null;
/* 248 */     result = null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canStack() {
/* 254 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private class ProspectResult
/*     */   {
/*     */     public ItemStack itemStack;
/*     */     public int count;
/*     */     
/*     */     public ProspectResult(ItemStack itemStack, int count) {
/* 264 */       this.itemStack = itemStack;
/* 265 */       this.count = count;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumItemReach getReach(ItemStack is) {
/* 272 */     return EnumItemReach.SHORT;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxDamage(ItemStack stack) {
/* 278 */     return (int)(func_77612_l() + func_77612_l() * AnvilManager.getDurabilityBuff(stack));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getDigSpeed(ItemStack stack, Block block, int meta) {
/* 284 */     float digSpeed = super.getDigSpeed(stack, block, meta);
/*     */     
/* 286 */     if (ForgeHooks.isToolEffective(stack, block, meta))
/*     */     {
/* 288 */       return digSpeed + digSpeed * AnvilManager.getDurabilityBuff(stack);
/*     */     }
/* 290 */     return digSpeed;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_77624_a(ItemStack is, EntityPlayer player, List<String> arraylist, boolean flag) {
/* 296 */     ItemTerra.addSizeInformation(is, arraylist);
/* 297 */     ItemTerraTool.addSmithingBonusInformation(is, arraylist);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Tools\ItemProPick.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */