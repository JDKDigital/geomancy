package net.codersdownunder.gemmod.client;

import net.codersdownunder.gemmod.GemMod;
import net.codersdownunder.gemmod.client.gui.*;
import net.codersdownunder.gemmod.client.renderer.DipperBlockEntityRenderer;
import net.codersdownunder.gemmod.init.BlockInit;
import net.codersdownunder.gemmod.init.MenuInit;
import net.codersdownunder.gemmod.init.TileEntityInit;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = GemMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
	
	  public static void doClientStuff(final FMLClientSetupEvent event) {
		  
	        MenuScreens.register(MenuInit.INFUSION_TABLE_CONTAINER.get(), InfusionTableScreen::new);
	        MenuScreens.register(MenuInit.DIPPER_CONTAINER.get(), DipperScreen::new);
	        MenuScreens.register(MenuInit.DREAM_CATCHER_MENU.get(), DreamCatcherScreen::new);
	        MenuScreens.register(MenuInit.TELEPAD_MENU.get(), TelepadScreen::new);
	        MenuScreens.register(MenuInit.TERRA_FIRMA_MENU.get(), TerraFirmaScreen::new);
	        MenuScreens.register(MenuInit.INFUSION_STAND_MENU.get(), InfusionStandScreen::new);
	        MenuScreens.register(MenuInit.SONG_FORGE_MENU.get(), SongForgeScreen::new);
	       
	        //RenderTypeLookup.setRenderLayer(BlockInit.CHASM_LEAVES.get(), RenderType.cutoutMipped());
 
	        
	        event.enqueueWork(() -> {
	         renderLayer();
	         
//	         ItemProperties.register(ItemInit.DIGGING_CLAW_WOOD.get(), new ResourceLocation(GemMod.MODID + ":flat"),(p_174625_, p_174626_, p_174627_, p_174628_) -> {
//	             return p_174627_.isHolding(p_174625_.getItem()) ? 0.0F : 1.0F; });
	        });
	        
	        BlockEntityRenderers.register(TileEntityInit.CUSTOM_SIGN.get(), SignRenderer::new);
	        event.enqueueWork(() -> {
	            Sheets.addWoodType(GemMod.CHASM);
	        });
	        
	        BlockEntityRenderers.register(TileEntityInit.DIPPER_BE.get(), DipperBlockEntityRenderer::new);
	    }
	  
	  private static void renderLayer() {
		  ItemBlockRenderTypes.setRenderLayer(BlockInit.END_LANTERN.get(), RenderType.cutoutMipped());
	      ItemBlockRenderTypes.setRenderLayer(BlockInit.INFUSION_TABLE.get(), RenderType.cutoutMipped());
	      ItemBlockRenderTypes.setRenderLayer(BlockInit.CHASM_LEAVES.get(), RenderType.cutoutMipped());
	      ItemBlockRenderTypes.setRenderLayer(BlockInit.CHASM_SAPLING.get(), RenderType.cutoutMipped());
	      ItemBlockRenderTypes.setRenderLayer(BlockInit.DIPPER.get(), RenderType.cutout());    
	      ItemBlockRenderTypes.setRenderLayer(BlockInit.TELEPAD.get(), RenderType.cutout());
	      ItemBlockRenderTypes.setRenderLayer(BlockInit.TELEPAD_SLAB.get(), RenderType.cutout());
	      ItemBlockRenderTypes.setRenderLayer(BlockInit.TRELLIS.get(), RenderType.cutout());
	      ItemBlockRenderTypes.setRenderLayer(BlockInit.TRELLIS_VINE.get(), RenderType.cutout());
	      ItemBlockRenderTypes.setRenderLayer(BlockInit.TRELLIS_CAVE_VINES.get(), RenderType.cutout());
	      ItemBlockRenderTypes.setRenderLayer(BlockInit.TRELLIS_CHORUS.get(), RenderType.cutout());
	      ItemBlockRenderTypes.setRenderLayer(BlockInit.TRELLIS_LICHEN.get(), RenderType.cutout());
	      ItemBlockRenderTypes.setRenderLayer(BlockInit.TRELLIS_MOSS.get(), RenderType.cutout());
	      ItemBlockRenderTypes.setRenderLayer(BlockInit.TRELLIS_CRIMSON.get(), RenderType.cutout());
	      ItemBlockRenderTypes.setRenderLayer(BlockInit.TRELLIS_WARP.get(), RenderType.cutout());
	      
	  }
}
