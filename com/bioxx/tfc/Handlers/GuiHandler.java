/*     */ package com.bioxx.tfc.Handlers;
/*     */ 
/*     */ import com.bioxx.tfc.Containers.ContainerAnvil;
/*     */ import com.bioxx.tfc.Containers.ContainerBarrel;
/*     */ import com.bioxx.tfc.Containers.ContainerBlastFurnace;
/*     */ import com.bioxx.tfc.Containers.ContainerChestTFC;
/*     */ import com.bioxx.tfc.Containers.ContainerCrucible;
/*     */ import com.bioxx.tfc.Containers.ContainerFirepit;
/*     */ import com.bioxx.tfc.Containers.ContainerFoodPrep;
/*     */ import com.bioxx.tfc.Containers.ContainerForge;
/*     */ import com.bioxx.tfc.Containers.ContainerGrill;
/*     */ import com.bioxx.tfc.Containers.ContainerHopper;
/*     */ import com.bioxx.tfc.Containers.ContainerHorseInventoryTFC;
/*     */ import com.bioxx.tfc.Containers.ContainerLargeVessel;
/*     */ import com.bioxx.tfc.Containers.ContainerLiquidVessel;
/*     */ import com.bioxx.tfc.Containers.ContainerLogPile;
/*     */ import com.bioxx.tfc.Containers.ContainerMold;
/*     */ import com.bioxx.tfc.Containers.ContainerNestBox;
/*     */ import com.bioxx.tfc.Containers.ContainerPlanSelection;
/*     */ import com.bioxx.tfc.Containers.ContainerPlayerTFC;
/*     */ import com.bioxx.tfc.Containers.ContainerQuern;
/*     */ import com.bioxx.tfc.Containers.ContainerQuiver;
/*     */ import com.bioxx.tfc.Containers.ContainerSluice;
/*     */ import com.bioxx.tfc.Containers.ContainerSpecialCrafting;
/*     */ import com.bioxx.tfc.Containers.ContainerVessel;
/*     */ import com.bioxx.tfc.Containers.ContainerWorkbench;
/*     */ import com.bioxx.tfc.Core.Player.PlayerInfo;
/*     */ import com.bioxx.tfc.Core.Player.PlayerManagerTFC;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntityHorseTFC;
/*     */ import com.bioxx.tfc.TileEntities.TEAnvil;
/*     */ import com.bioxx.tfc.TileEntities.TEBarrel;
/*     */ import com.bioxx.tfc.TileEntities.TEBlastFurnace;
/*     */ import com.bioxx.tfc.TileEntities.TECrucible;
/*     */ import com.bioxx.tfc.TileEntities.TEFirepit;
/*     */ import com.bioxx.tfc.TileEntities.TEFoodPrep;
/*     */ import com.bioxx.tfc.TileEntities.TEForge;
/*     */ import com.bioxx.tfc.TileEntities.TEGrill;
/*     */ import com.bioxx.tfc.TileEntities.TELogPile;
/*     */ import com.bioxx.tfc.TileEntities.TENestBox;
/*     */ import com.bioxx.tfc.TileEntities.TEQuern;
/*     */ import com.bioxx.tfc.TileEntities.TESluice;
/*     */ import com.bioxx.tfc.TileEntities.TEVessel;
/*     */ import com.bioxx.tfc.TileEntities.TEWorkbench;
/*     */ import cpw.mods.fml.common.network.IGuiHandler;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GuiHandler
/*     */   implements IGuiHandler
/*     */ {
/*     */   public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
/*     */     PlayerInfo pi;
/*  58 */     TileEntity te = world.func_147438_o(x, y, z);
/*     */     
/*  60 */     switch (id) {
/*     */ 
/*     */       
/*     */       case 0:
/*  64 */         return new ContainerLogPile(player.field_71071_by, (TELogPile)te, world, x, y, z);
/*     */ 
/*     */       
/*     */       case 1:
/*  68 */         return new ContainerWorkbench(player.field_71071_by, (TEWorkbench)te, world, x, y, z);
/*     */ 
/*     */       
/*     */       case 19:
/*  72 */         return new ContainerLiquidVessel(player.field_71071_by, world, x, y, z);
/*     */ 
/*     */       
/*     */       case 20:
/*  76 */         return new ContainerFirepit(player.field_71071_by, (TEFirepit)te, world, x, y, z);
/*     */ 
/*     */       
/*     */       case 21:
/*  80 */         return new ContainerAnvil(player.field_71071_by, (TEAnvil)te, world, x, y, z);
/*     */ 
/*     */       
/*     */       case 22:
/*  84 */         return null;
/*     */ 
/*     */       
/*     */       case 23:
/*  88 */         return new ContainerForge(player.field_71071_by, (TEForge)te, world, x, y, z);
/*     */ 
/*     */       
/*     */       case 24:
/*  92 */         return new ContainerPlanSelection(player, (TEAnvil)te, world, x, y, z);
/*     */ 
/*     */       
/*     */       case 25:
/*  96 */         return new ContainerSluice(player.field_71071_by, (TESluice)te, world, x, y, z);
/*     */ 
/*     */       
/*     */       case 26:
/* 100 */         return new ContainerBlastFurnace(player.field_71071_by, (TEBlastFurnace)te, world, x, y, z);
/*     */ 
/*     */       
/*     */       case 28:
/* 104 */         pi = PlayerManagerTFC.getInstance().getPlayerInfoFromPlayer(player);
/* 105 */         return new ContainerSpecialCrafting(player.field_71071_by, (pi.specialCraftingTypeAlternate == null) ? pi.specialCraftingType : null, world, x, y, z);
/*     */ 
/*     */       
/*     */       case 29:
/* 109 */         return new ContainerChestTFC((IInventory)player.field_71071_by, (IInventory)te, world, x, y, z);
/*     */ 
/*     */       
/*     */       case 31:
/* 113 */         return new ContainerPlayerTFC(player.field_71071_by, false, player);
/*     */ 
/*     */       
/*     */       case 33:
/* 117 */         return new ContainerQuern(player.field_71071_by, (TEQuern)te, world, x, y, z);
/*     */ 
/*     */       
/*     */       case 34:
/* 121 */         return null;
/*     */ 
/*     */       
/*     */       case 35:
/* 125 */         return new ContainerBarrel(player.field_71071_by, (TEBarrel)te, world, x, y, z, 0);
/*     */ 
/*     */       
/*     */       case 36:
/* 129 */         return new ContainerBarrel(player.field_71071_by, (TEBarrel)te, world, x, y, z, 1);
/*     */ 
/*     */       
/*     */       case 37:
/* 133 */         return new ContainerCrucible(player.field_71071_by, (TECrucible)te, world, x, y, z);
/*     */ 
/*     */       
/*     */       case 38:
/* 137 */         return new ContainerMold(player.field_71071_by, world, x, y, z);
/*     */ 
/*     */       
/*     */       case 39:
/* 141 */         return new ContainerVessel(player.field_71071_by, world, x, y, z);
/*     */ 
/*     */       
/*     */       case 40:
/* 145 */         return new ContainerQuiver(player.field_71071_by, world, x, y, z);
/*     */ 
/*     */       
/*     */       case 41:
/* 149 */         return new ContainerNestBox(player.field_71071_by, (TENestBox)te, world, x, y, z);
/*     */ 
/*     */       
/*     */       case 42:
/* 153 */         if (player.field_70154_o instanceof EntityHorseTFC) {
/*     */           
/* 155 */           EntityHorseTFC horse = (EntityHorseTFC)player.field_70154_o;
/* 156 */           return new ContainerHorseInventoryTFC(player.field_71071_by, (IInventory)horse.getHorseChest(), horse);
/*     */         } 
/*     */         
/* 159 */         return null;
/*     */ 
/*     */       
/*     */       case 43:
/* 163 */         return new ContainerGrill(player.field_71071_by, (TEGrill)te, world, x, y, z);
/*     */       
/*     */       case 44:
/* 166 */         return new ContainerFoodPrep(player.field_71071_by, (TEFoodPrep)te, world, x, y, z, 0);
/*     */       case 45:
/* 168 */         return new ContainerFoodPrep(player.field_71071_by, (TEFoodPrep)te, world, x, y, z, 1);
/*     */       case 46:
/* 170 */         return new ContainerLargeVessel(player.field_71071_by, (TEVessel)te, world, x, y, z, 0);
/*     */       case 47:
/* 172 */         return new ContainerLargeVessel(player.field_71071_by, (TEVessel)te, world, x, y, z, 1);
/*     */       
/*     */       case 48:
/* 175 */         return null;
/*     */ 
/*     */       
/*     */       case 49:
/* 179 */         return new ContainerHopper(player.field_71071_by, (IInventory)te);
/*     */     } 
/*     */ 
/*     */     
/* 183 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
/* 191 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\GuiHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */