package com.beecub.glizer;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;

public class glizerBlockListener extends BlockListener {
	
	@SuppressWarnings("unused")
	private final glizer plugin;

	public glizerBlockListener(glizer instance) {
		plugin = instance;
	}
	
	public void onBlockPlace(BlockPlaceEvent event) {
	    
	}
	public void onBlockBreak(BlockBreakEvent event) {
	    
	}
}
