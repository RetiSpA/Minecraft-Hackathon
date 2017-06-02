package it.reti.minecraft.plugin.sample.commands;

import it.reti.minecraft.plugin.sample.helpers.GenericCommand;
import it.reti.minecraft.plugin.sample.helpers.MinecraftEvent;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

/**
 * Classe per il comando sky che porta 50 blocchi nel cielo tutte le entit� viventi e da l� le lascia cadere.
 * 
 * La documentazione sulla libreria Forge � navigabile qui:
 * http://mcforge.readthedocs.io/en/latest/
 * 
 * @author Andrea Biancini <andrea.biancini@gmail.com>
 */
@MinecraftEvent(aliases = { "sky" },
	description = "Fa volare tutte le creature viventi del tuo mondo!",
	registerInEventBus = true)
public class Sky extends GenericCommand implements ICommand {
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		EntityPlayer me = (EntityPlayer) sender;
		World w = me.getEntityWorld();
		
		for (Entity entity : w.loadedEntityList) {
			if (entity instanceof EntityLiving) {
				double x = entity.getPosition().getX();
				double z = entity.getPosition().getZ();
				
				// Aggiungi 50 alla coordinata y, porta in cielo l'essere vivente di 50 blocchi.
				double y = entity.getPosition().getY() + 50;
				
				entity.setPositionAndUpdate(x, y, z);
			}
		}
	}
	
}
