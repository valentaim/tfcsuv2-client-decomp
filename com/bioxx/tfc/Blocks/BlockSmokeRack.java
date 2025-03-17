/*     */ package com.bioxx.tfc.Blocks;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.TileEntities.TESmokeRack;
/*     */ import com.bioxx.tfc.api.Food;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockSmokeRack
/*     */   extends BlockTerraContainer
/*     */ {
/*     */   public BlockSmokeRack() {
/*  33 */     super(Material.field_151594_q);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149719_a(IBlockAccess world, int x, int y, int z) {
/*  39 */     if ((world.func_72805_g(x, y, z) & 0x1) == 0) {
/*     */       
/*  41 */       func_149676_a(0.45F, 0.45F, 0.0F, 0.55F, 0.55F, 1.0F);
/*     */     }
/*     */     else {
/*     */       
/*  45 */       func_149676_a(0.0F, 0.45F, 0.45F, 1.0F, 0.55F, 0.55F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReplaceable(IBlockAccess world, int x, int y, int z) {
/*  52 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149655_b(IBlockAccess bAccess, int x, int y, int z) {
/*  58 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
/*  64 */     return new ItemStack(TFCItems.woolYarn);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float hitX, float hitY, float hitZ) {
/*  70 */     boolean flag = false;
/*  71 */     if (!world.field_72995_K) {
/*     */       
/*  73 */       int meta = world.func_72805_g(x, y, z);
/*  74 */       TESmokeRack te = (TESmokeRack)world.func_147438_o(x, y, z);
/*  75 */       ItemStack yarn = TFC_Core.getItemInInventory(TFCItems.woolYarn, (IInventory)entityplayer.field_71071_by);
/*  76 */       if ((meta & 0x1) == 0 && hitZ < 0.5D) {
/*     */         
/*  78 */         if (te.func_70301_a(0) == null && yarn != null && isItemValid(entityplayer.field_71071_by.func_70448_g()))
/*     */         {
/*  80 */           te.func_70299_a(0, entityplayer.field_71071_by.func_70448_g().func_77946_l());
/*  81 */           (entityplayer.field_71071_by.func_70448_g()).field_77994_a--;
/*  82 */           entityplayer.field_71071_by.func_146026_a(TFCItems.woolYarn);
/*  83 */           flag = true;
/*     */         }
/*  85 */         else if (te.func_70301_a(0) != null)
/*     */         {
/*  87 */           TFC_Core.giveItemToPlayer(te.removeStackInSlot(0), entityplayer);
/*  88 */           flag = true;
/*     */         }
/*     */       
/*  91 */       } else if ((meta & 0x1) == 0 && hitZ >= 0.5D) {
/*     */         
/*  93 */         if (te.func_70301_a(1) == null && yarn != null && isItemValid(entityplayer.field_71071_by.func_70448_g()))
/*     */         {
/*  95 */           te.func_70299_a(1, entityplayer.field_71071_by.func_70448_g().func_77946_l());
/*  96 */           (entityplayer.field_71071_by.func_70448_g()).field_77994_a--;
/*  97 */           entityplayer.field_71071_by.func_146026_a(TFCItems.woolYarn);
/*  98 */           flag = true;
/*     */         }
/* 100 */         else if (te.func_70301_a(1) != null)
/*     */         {
/* 102 */           TFC_Core.giveItemToPlayer(te.removeStackInSlot(1), entityplayer);
/* 103 */           flag = true;
/*     */         }
/*     */       
/* 106 */       } else if ((meta & 0x1) == 1 && hitX < 0.5D) {
/*     */         
/* 108 */         if (te.func_70301_a(0) == null && yarn != null && isItemValid(entityplayer.field_71071_by.func_70448_g()))
/*     */         {
/* 110 */           te.func_70299_a(0, entityplayer.field_71071_by.func_70448_g().func_77946_l());
/* 111 */           (entityplayer.field_71071_by.func_70448_g()).field_77994_a--;
/* 112 */           entityplayer.field_71071_by.func_146026_a(TFCItems.woolYarn);
/* 113 */           flag = true;
/*     */         }
/* 115 */         else if (te.func_70301_a(0) != null)
/*     */         {
/* 117 */           TFC_Core.giveItemToPlayer(te.removeStackInSlot(0), entityplayer);
/* 118 */           flag = true;
/*     */         }
/*     */       
/* 121 */       } else if ((meta & 0x1) == 1 && hitX >= 0.5D) {
/*     */         
/* 123 */         if (te.func_70301_a(1) == null && yarn != null && isItemValid(entityplayer.field_71071_by.func_70448_g())) {
/*     */           
/* 125 */           te.func_70299_a(1, entityplayer.field_71071_by.func_70448_g().func_77946_l());
/* 126 */           (entityplayer.field_71071_by.func_70448_g()).field_77994_a--;
/* 127 */           entityplayer.field_71071_by.func_146026_a(TFCItems.woolYarn);
/* 128 */           return true;
/*     */         } 
/* 130 */         if (te.func_70301_a(1) != null) {
/*     */           
/* 132 */           TFC_Core.giveItemToPlayer(te.removeStackInSlot(1), entityplayer);
/* 133 */           flag = true;
/*     */         } 
/*     */       } 
/*     */     } 
/* 137 */     return flag;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isItemValid(ItemStack is) {
/* 142 */     if (is == null)
/* 143 */       return false; 
/* 144 */     if (is.func_77973_b() instanceof com.bioxx.tfc.Food.ItemFoodMeat) {
/*     */       
/* 146 */       if (!Food.isCooked(is) && Food.isBrined(is)) {
/* 147 */         return true;
/*     */       }
/* 149 */     } else if (is.func_77973_b().func_77658_a().toLowerCase().contains("cheese")) {
/*     */       
/* 151 */       if (!Food.isCooked(is)) {
/* 152 */         return true;
/*     */       }
/*     */     } 
/* 155 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149662_c() {
/* 161 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149645_b() {
/* 167 */     return TFCBlocks.smokeRackRenderId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149695_a(World world, int x, int y, int z, Block block) {
/* 173 */     int meta = world.func_72805_g(x, y, z);
/* 174 */     if (!world.field_72995_K) {
/*     */       
/* 176 */       if ((meta & 0x1) == 0) {
/*     */         
/* 178 */         if (!isValidNeighbor(world, x, y, z - 1, ForgeDirection.NORTH) || !isValidNeighbor(world, x, y, z + 1, ForgeDirection.SOUTH))
/*     */         {
/* 180 */           TFC_Core.destroyBlock(world, x, y, z);
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 185 */       else if (!isValidNeighbor(world, x - 1, y, z, ForgeDirection.WEST) || !isValidNeighbor(world, x + 1, y, z, ForgeDirection.EAST)) {
/*     */         
/* 187 */         TFC_Core.destroyBlock(world, x, y, z);
/*     */       } 
/*     */ 
/*     */       
/* 191 */       if (world.func_147439_a(x, y + 1, z) instanceof com.bioxx.tfc.Blocks.Terrain.BlockCollapsible)
/*     */       {
/* 193 */         TFC_Core.destroyBlock(world, x, y, z);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isValidNeighbor(World world, int x, int y, int z, ForgeDirection dir) {
/* 200 */     Block b = world.func_147439_a(x, y, z);
/* 201 */     return (b == this || b.isSideSolid((IBlockAccess)world, x, y, z, dir.getOpposite()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149686_d() {
/* 207 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
/* 213 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_149633_g(World world, int x, int y, int z) {
/* 219 */     if ((world.func_72805_g(x, y, z) & 0x1) == 0)
/*     */     {
/* 221 */       return AxisAlignedBB.func_72330_a(x + 0.45D, y + 0.45D, z, x + 0.55D, y + 0.55D, (z + 1));
/*     */     }
/*     */ 
/*     */     
/* 225 */     return AxisAlignedBB.func_72330_a(x, y + 0.45D, z + 0.45D, (x + 1), y + 0.55D, z + 0.55D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item func_149650_a(int i, Random rand, int j) {
/* 232 */     return TFCItems.woolYarn;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister reg) {
/* 239 */     this.field_149761_L = reg.func_94245_a("terrafirmacraft:String");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createTileEntity(World world, int meta) {
/* 245 */     return (TileEntity)new TESmokeRack();
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockSmokeRack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */