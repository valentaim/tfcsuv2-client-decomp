/*     */ package com.bioxx.tfc.Blocks.Devices;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.BlockTerraContainer;
/*     */ import com.bioxx.tfc.Core.Metal.MetalPair;
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import com.bioxx.tfc.TileEntities.TECrucible;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockCrucible
/*     */   extends BlockTerraContainer
/*     */ {
/*     */   private IIcon[] icons;
/*     */   
/*     */   public BlockCrucible() {
/*  37 */     func_149647_a(TFCTabs.TFC_DEVICES);
/*  38 */     func_149676_a(0.0625F, 0.25F, 0.0625F, 0.9375F, 0.9375F, 0.9375F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {
/*  44 */     if (!world.field_72995_K && (TECrucible)world.func_147438_o(i, j, k) != null)
/*     */     {
/*     */ 
/*     */ 
/*     */       
/*  49 */       entityplayer.openGui(TerraFirmaCraft.instance, 37, world, i, j, k);
/*     */     }
/*  51 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149749_a(World world, int i, int j, int k, Block block, int par6) {
/*  57 */     if (world.func_147438_o(i, j, k) instanceof TECrucible) {
/*     */       
/*  59 */       TECrucible te = (TECrucible)world.func_147438_o(i, j, k);
/*  60 */       if (te instanceof net.minecraft.inventory.IInventory)
/*     */       {
/*  62 */         for (int i1 = 0; i1 < te.func_70302_i_(); i1++) {
/*     */           
/*  64 */           if (te.func_70301_a(i1) != null) {
/*     */             
/*  66 */             EntityItem entityItem = new EntityItem(world, i + 0.5D, j + 0.5D, k + 0.5D, te.func_70301_a(i1));
/*  67 */             entityItem.field_70159_w = 0.0D;
/*  68 */             entityItem.field_70181_x = 0.0D;
/*  69 */             entityItem.field_70179_y = 0.0D;
/*  70 */             world.func_72838_d((Entity)entityItem);
/*  71 */             te.func_70299_a(i1, null);
/*     */           } 
/*     */         } 
/*     */       }
/*  75 */       ItemStack is = new ItemStack(Item.func_150898_a(block), 1);
/*  76 */       NBTTagCompound nbt = writeCrucibleToNBT(te);
/*  77 */       is.func_77982_d(nbt);
/*  78 */       EntityItem ei = new EntityItem(world, i, j, k, is);
/*  79 */       world.func_72838_d((Entity)ei);
/*     */     } 
/*  81 */     super.func_149749_a(world, i, j, k, block, par6);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_149642_a(World par1World, int par2, int par3, int par4, ItemStack par5ItemStack) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound writeCrucibleToNBT(TECrucible te) {
/* 108 */     NBTTagCompound nbt = new NBTTagCompound();
/*     */     
/* 110 */     nbt.func_74768_a("temp", te.temperature);
/*     */     
/* 112 */     NBTTagList nbttaglist = new NBTTagList();
/* 113 */     Iterator<MetalPair> iter = te.metals.values().iterator();
/* 114 */     while (iter.hasNext()) {
/*     */       
/* 116 */       MetalPair m = iter.next();
/* 117 */       if (m != null) {
/*     */         
/* 119 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 120 */         nbttagcompound1.func_74768_a("ID", Item.func_150891_b(m.type.ingot));
/* 121 */         nbttagcompound1.func_74776_a("AmountF", m.amount);
/* 122 */         nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
/*     */       } 
/*     */     } 
/* 125 */     nbt.func_74782_a("Metals", (NBTBase)nbttaglist);
/*     */     
/* 127 */     nbttaglist = new NBTTagList();
/* 128 */     for (int i = 0; i < te.storage.length; i++) {
/*     */       
/* 130 */       if (te.storage[i] != null) {
/*     */         
/* 132 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 133 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 134 */         te.storage[i].func_77955_b(nbttagcompound1);
/* 135 */         nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
/*     */       } 
/*     */     } 
/*     */     
/* 139 */     nbt.func_74782_a("Items", (NBTBase)nbttaglist);
/*     */     
/* 141 */     return nbt;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149689_a(World world, int i, int j, int k, EntityLivingBase player, ItemStack is) {
/* 147 */     super.func_149689_a(world, i, j, k, player, is);
/* 148 */     TECrucible te = (TECrucible)world.func_147438_o(i, j, k);
/*     */     
/* 150 */     if (te != null && is.func_77942_o())
/*     */     {
/* 152 */       te.readFromItemNBT(is.func_77978_p());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_149646_a(IBlockAccess par1iBlockAccess, int par2, int par3, int par4, int par5) {
/* 160 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149651_a(IIconRegister iconRegisterer) {
/* 166 */     this.icons = new IIcon[2];
/* 167 */     this.icons[0] = iconRegisterer.func_94245_a("terrafirmacraft:devices/Crucible Top");
/* 168 */     this.icons[1] = iconRegisterer.func_94245_a("terrafirmacraft:devices/Crucible Side");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IIcon func_149691_a(int i, int j) {
/* 174 */     if (i < 2)
/*     */     {
/* 176 */       return this.icons[0];
/*     */     }
/*     */     
/* 179 */     return this.icons[1];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149645_b() {
/* 185 */     return TFCBlocks.crucibleRenderId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149662_c() {
/* 191 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149686_d() {
/* 197 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity func_149915_a(World var1, int var2) {
/* 203 */     return (TileEntity)new TECrucible();
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Devices\BlockCrucible.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */