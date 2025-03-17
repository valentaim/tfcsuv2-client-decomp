/*     */ package com.bioxx.tfc.Handlers.Client;
/*     */ import com.bioxx.tfc.Core.Player.PlayerInfo;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntityHorseTFC;
/*     */ import com.bioxx.tfc.GUI.GuiAnvil;
/*     */ import com.bioxx.tfc.GUI.GuiBarrel;
/*     */ import com.bioxx.tfc.GUI.GuiChestTFC;
/*     */ import com.bioxx.tfc.GUI.GuiFoodPrep;
/*     */ import com.bioxx.tfc.GUI.GuiInventoryTFC;
/*     */ import com.bioxx.tfc.GUI.GuiLargeVessel;
/*     */ import com.bioxx.tfc.GUI.GuiWorkbench;
/*     */ import com.bioxx.tfc.TileEntities.TEAnvil;
/*     */ import com.bioxx.tfc.TileEntities.TEBarrel;
/*     */ import com.bioxx.tfc.TileEntities.TEFirepit;
/*     */ import com.bioxx.tfc.TileEntities.TEFoodPrep;
/*     */ import com.bioxx.tfc.TileEntities.TEForge;
/*     */ import com.bioxx.tfc.TileEntities.TEVessel;
/*     */ import cuchaz.ships.EntityShip;
/*     */ import cuchaz.ships.ShipWorld;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.client.event.GuiOpenEvent;
/*     */ 
/*     */ public class GuiHandler extends GuiHandler {
/*     */   public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
/*     */     ShipWorld shipWorld;
/*     */     TileEntity te;
/*     */     PlayerInfo pi;
/*     */     try {
/*  31 */       if (TerraFirmaCraft.instance.ShipsExist) {
/*     */         
/*  33 */         EntityShip ship = ShipLocator.getFromPlayerLook(player);
/*  34 */         if (ship != null) shipWorld = ship.getShipWorld(); 
/*     */       } 
/*  36 */       te = shipWorld.func_147438_o(x, y, z);
/*     */     }
/*  38 */     catch (Exception e) {
/*     */       
/*  40 */       te = null;
/*     */     } 
/*     */     
/*  43 */     switch (id) {
/*     */       
/*     */       case 0:
/*  46 */         return new GuiLogPile(player.field_71071_by, (TELogPile)te, (World)shipWorld, x, y, z);
/*     */       case 1:
/*  48 */         return new GuiWorkbench(player.field_71071_by, (TEWorkbench)te, (World)shipWorld, x, y, z);
/*     */       case 19:
/*  50 */         return new GuiVesselLiquid(player.field_71071_by, (World)shipWorld, x, y, z);
/*     */       case 20:
/*  52 */         return new GuiFirepit(player.field_71071_by, (TEFirepit)te, (World)shipWorld, x, y, z);
/*     */       case 21:
/*  54 */         return new GuiAnvil(player.field_71071_by, (TEAnvil)te, (World)shipWorld, x, y, z);
/*     */       case 22:
/*  56 */         return null;
/*     */       case 23:
/*  58 */         return new GuiForge(player.field_71071_by, (TEForge)te, (World)shipWorld, x, y, z);
/*     */       case 24:
/*  60 */         return new GuiPlanSelection(player, (TEAnvil)te, (World)shipWorld, x, y, z);
/*     */       case 25:
/*  62 */         return new GuiSluice(player.field_71071_by, (TESluice)te, (World)shipWorld, x, y, z);
/*     */       case 26:
/*  64 */         return new GuiBlastFurnace(player.field_71071_by, (TEBlastFurnace)te, (World)shipWorld, x, y, z);
/*     */       case 27:
/*  66 */         return new GuiCalendar(player);
/*     */       case 28:
/*  68 */         pi = PlayerManagerTFC.getInstance().getPlayerInfoFromPlayer(player);
/*  69 */         return new GuiKnapping(player.field_71071_by, (pi.specialCraftingTypeAlternate == null) ? pi.specialCraftingType : null, (World)shipWorld, x, y, z);
/*     */       case 29:
/*  71 */         return new GuiChestTFC((IInventory)player.field_71071_by, (IInventory)te, (World)shipWorld, x, y, z);
/*     */       case 31:
/*  73 */         return new GuiInventoryTFC(player);
/*     */       
/*     */       case 32:
/*     */       case 33:
/*  77 */         return new GuiQuern(player.field_71071_by, (TEQuern)te, (World)shipWorld, x, y, z);
/*     */       case 34:
/*  79 */         return new GuiBlueprint(player, (World)shipWorld, x, y, z);
/*     */       case 35:
/*  81 */         return new GuiBarrel(player.field_71071_by, (TEBarrel)te, (World)shipWorld, x, y, z, 0);
/*     */       case 36:
/*  83 */         return new GuiBarrel(player.field_71071_by, (TEBarrel)te, (World)shipWorld, x, y, z, 1);
/*     */       case 37:
/*  85 */         return new GuiCrucible(player.field_71071_by, (TECrucible)te, (World)shipWorld, x, y, z);
/*     */       case 38:
/*  87 */         return new GuiMold(player.field_71071_by, (World)shipWorld, x, y, z);
/*     */       case 39:
/*  89 */         return new GuiVessel(player.field_71071_by, (World)shipWorld, x, y, z);
/*     */       case 40:
/*  91 */         return new GuiQuiver(player.field_71071_by, (World)shipWorld, x, y, z);
/*     */       case 41:
/*  93 */         return new GuiNestBox(player.field_71071_by, (TENestBox)te, (World)shipWorld, x, y, z);
/*     */       
/*     */       case 42:
/*  96 */         if (player.field_70154_o instanceof EntityHorseTFC) {
/*     */           
/*  98 */           EntityHorseTFC horse = (EntityHorseTFC)player.field_70154_o;
/*  99 */           horse.updateChestSaddle();
/* 100 */           return new GuiScreenHorseInventoryTFC(player.field_71071_by, (IInventory)horse.getHorseChest(), horse);
/*     */         } 
/*     */         
/* 103 */         return null;
/*     */       
/*     */       case 43:
/* 106 */         return new GuiGrill(player.field_71071_by, (TEGrill)te, (World)shipWorld, x, y, z);
/*     */       case 44:
/* 108 */         return new GuiFoodPrep(player.field_71071_by, (TEFoodPrep)te, (World)shipWorld, x, y, z, 0);
/*     */       case 45:
/* 110 */         return new GuiFoodPrep(player.field_71071_by, (TEFoodPrep)te, (World)shipWorld, x, y, z, 1);
/*     */       case 46:
/* 112 */         return new GuiLargeVessel(player.field_71071_by, (TEVessel)te, (World)shipWorld, x, y, z, 0);
/*     */       case 47:
/* 114 */         return new GuiLargeVessel(player.field_71071_by, (TEVessel)te, (World)shipWorld, x, y, z, 1);
/*     */       case 48:
/* 116 */         return new GuiCustomNametag(player, (World)shipWorld, x, y, z);
/*     */       
/*     */       case 49:
/* 119 */         return new GuiHopper(player.field_71071_by, (TEHopper)te, (World)shipWorld, x, y, z);
/*     */     } 
/*     */     
/* 122 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void openGuiHandler(GuiOpenEvent event) {
/* 129 */     if (event.gui instanceof net.minecraft.client.gui.inventory.GuiInventory && !(event.gui instanceof GuiInventoryTFC))
/* 130 */       event.gui = (GuiScreen)new GuiInventoryTFC((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g); 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\Client\GuiHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */