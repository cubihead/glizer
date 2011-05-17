package com.beecub.glizer;

import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.SignChangeEvent;

public class glizerBlockListener extends BlockListener {
	
	@SuppressWarnings("unused")
	private final glizer plugin;

	public glizerBlockListener(glizer instance) {
		plugin = instance;
	}
	
	public void onSignChange(SignChangeEvent event) {
		Player player = event.getPlayer();
		String[] text = event.getLines();
	    int i = 0;
		if (glizer.permissions) {
			if(glizer.Permissions.permission(player, "glizer.signColor")) {
			    for (String line : text)
			    {
			      line = line.replaceAll("&", "§");
			      line = line.replaceAll("§§", "&");
			      event.setLine(i, line);
			      i++;
			    }
			}
		}
		else {
		    for (String line : text)
		    {
		      line = line.replaceAll("&", "§");
		      line = line.replaceAll("§§", "&");
		      event.setLine(i, line);
		      i++;
		    }
		}
	}
}
