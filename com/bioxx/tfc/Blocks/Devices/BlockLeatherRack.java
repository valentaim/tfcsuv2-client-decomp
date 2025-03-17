/*     */ package com.bioxx.tfc.Blocks.Devices;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.BlockTerraContainer;
/*     */ import com.bioxx.tfc.Handlers.Network.AbstractPacket;
/*     */ import com.bioxx.tfc.TileEntities.TELeatherRack;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ public class BlockLeatherRack
/*     */   extends BlockTerraContainer
/*     */ {
/*     */   public IIcon scrapedTex;
/*     */   
/*     */   public BlockLeatherRack() {
/*  33 */     super(Material.field_151575_d);
/*  34 */     func_149647_a(null);
/*  35 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.001F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float hitX, float hitY, float hitZ) {
/*  41 */     if (!world.field_72995_K && world.func_147438_o(x, y, z) instanceof TELeatherRack) {
/*     */       
/*  43 */       TELeatherRack te = (TELeatherRack)world.func_147438_o(x, y, z);
/*  44 */       ItemStack equipped = entityplayer.func_71045_bC();
/*     */       
/*  46 */       if (te.workedArea != -1 && equipped != null && equipped.func_77973_b() instanceof com.bioxx.tfc.api.Tools.IKnife) {
/*     */         
/*  48 */         int coord = (int)Math.floor((hitX / 0.25F)) + (int)Math.floor((hitZ / 0.25F)) * 4;
/*  49 */         if ((te.workedArea >> coord & 0x1) == 0)
/*     */         {
/*  51 */           te.workArea(coord);
/*  52 */           NBTTagCompound nbt = new NBTTagCompound();
/*  53 */           nbt.func_74777_a("workedArea", te.workedArea);
/*  54 */           te.broadcastPacketInRange((AbstractPacket)te.createDataPacket(nbt));
/*  55 */           equipped.func_77972_a(1, (EntityLivingBase)entityplayer);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/*  60 */         world.func_147468_f(x, y, z);
/*     */       } 
/*     */     } 
/*     */     
/*  64 */     if (!func_149718_j(world, x, y, z))
/*     */     {
/*  66 */       world.func_147468_f(x, y, z);
/*     */     }
/*     */     
/*  69 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149718_j(World world, int x, int y, int z) {
/*  75 */     if (world.func_147438_o(x, y, z) instanceof TELeatherRack) {
/*     */       
/*  77 */       TELeatherRack te = (TELeatherRack)world.func_147438_o(x, y, z);
/*  78 */       if (te.leatherItem == null)
/*     */       {
/*  80 */         return false;
/*     */       }
/*     */     } 
/*     */     
/*  84 */     return (world.func_147439_a(x, y - 1, z).func_149688_o() == Material.field_151575_d);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149695_a(World world, int i, int j, int k, Block par5) {
/*  90 */     super.func_149695_a(world, i, j, k, par5);
/*  91 */     if (!func_149718_j(world, i, j, k)) {
/*  92 */       world.func_147465_d(i, j, k, Blocks.field_150350_a, 0, 2);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_149725_f(World world, int i, int j, int k, int meta) {
/*  98 */     if (!world.field_72995_K && world.func_147438_o(i, j, k) instanceof TELeatherRack) {
/*     */       
/* 100 */       TELeatherRack te = (TELeatherRack)world.func_147438_o(i, j, k);
/* 101 */       if (te.leatherItem != null) {
/*     */         
/* 103 */         EntityItem ei = new EntityItem(world, i, j, k, te.leatherItem);
/* 104 */         ei.field_70159_w = 0.0D;
/* 105 */         ei.field_70179_y = 0.0D;
/* 106 */         world.func_72838_d((Entity)ei);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Item func_149650_a(int metadata, Random rand, int fortune) {
/* 114 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeReplacedByLeaves(IBlockAccess w, int x, int y, int z) {
/* 120 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149662_c() {
/* 126 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149686_d() {
/* 132 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149645_b() {
/* 138 */     return TFCBlocks.leatherRackRenderId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149651_a(IIconRegister iconRegisterer) {
/* 144 */     this.field_149761_L = iconRegisterer.func_94245_a("terrafirmacraft:Soaked Hide");
/* 145 */     this.scrapedTex = iconRegisterer.func_94245_a("terrafirmacraft:Scraped Hide");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_149646_a(IBlockAccess access, int x, int y, int z, int side) {
/* 152 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity func_149915_a(World world, int var2) {
/* 158 */     return (TileEntity)new TELeatherRack();
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Devices\BlockLeatherRack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */