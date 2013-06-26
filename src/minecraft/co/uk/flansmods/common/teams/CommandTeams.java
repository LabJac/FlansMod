package co.uk.flansmods.common.teams;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import co.uk.flansmods.common.FlansMod;
import co.uk.flansmods.common.FlansModPlayerData;
import co.uk.flansmods.common.FlansModPlayerHandler;
import co.uk.flansmods.common.network.PacketTeamSelect;
import co.uk.flansmods.common.teams.TeamsManager.TeamsMap;
import cpw.mods.fml.common.network.PacketDispatcher;

public class CommandTeams extends CommandBase {
	
	public static TeamsManager teamsManager = TeamsManager.getInstance();

	@Override
	public String getCommandName() 
	{
		return "teams";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] split) 
	{
		if(teamsManager == null)
		{
			sender.sendChatToPlayer("Teams mod is broken. You will need to look at the server side logs to see what's wrong");
			return;
		}
		if(split == null || split.length == 0 || split[0].equals("help") || split[0].equals("?"))
		{
			sendHelpInformation(sender);
			return;
		}
		if(split[0].equals("listGametypes"))
		{
			sender.sendChatToPlayer("\u00a72Showing all avaliable gametypes");
			sender.sendChatToPlayer("\u00a72To pick a gametype, use \"/teams setGametype <gametype>\" with the name in brackets");
			for(Gametype gametype : Gametype.gametypes)
			{
				sender.sendChatToPlayer("\u00a7f" + gametype.name + " (" + gametype.shortName + ")");
			}
			return;
		}
		if(split[0].equals("setGametype"))
		{
			if(split.length != 2)
			{
				sender.sendChatToPlayer("\u00a74To set the gametype, use \"/teams setGametype <gametype>\" with a valid gametype.");
				return;
			}
			if(split[1].toLowerCase().equals("none"))
			{
				if(teamsManager.currentGametype != null)
					teamsManager.currentGametype.stopGametype();
				teamsManager.currentGametype = null;
				for(FlansModPlayerData data : FlansModPlayerHandler.serverSideData.values())
				{
					if(data != null)
						data.team = null;
				}
				return;
			}
			Gametype gametype = Gametype.getGametype(split[1]);
			if(gametype == null)
			{
				sender.sendChatToPlayer("\u00a74Invalid gametype. To see gametypes available type \"/teams listGametypes\"");
				return;
			}
			if(teamsManager.currentGametype != null)
			{
				teamsManager.currentGametype.stopGametype();
			}
			teamsManager.currentGametype = gametype;

			teamsManager.messageAll("\u00a72" + sender.getCommandSenderName() + "\u00a7f changed the gametype to \u00a72" + gametype.name);
			if(teamsManager.teams != null && gametype.numTeamsRequired == teamsManager.teams.length)
			{
				teamsManager.messageAll("\u00a7fTeams will remain the same unless altered by an op.");
			}
			else
			{
				teamsManager.teams = new Team[gametype.numTeamsRequired];
				teamsManager.messageAll("\u00a7fTeams must be reassigned for this gametype. Please wait for an op to do so.");
			}
			gametype.initGametype();
			return;
		}
		if(split[0].equals("listMaps"))
		{
			if(teamsManager.maps == null)
			{
				sender.sendChatToPlayer("The map list is null");
				return;
			}
			sender.sendChatToPlayer("\u00a72Listing maps and corresponding IDs");
			for(int i = 0; i < teamsManager.maps.size(); i++)
			{
				TeamsMap map = teamsManager.maps.get(i);
				sender.sendChatToPlayer((map == teamsManager.currentMap ? "\u00a74" : "") + i + ". " + map.name + " (" + map.shortName + ")");
			}
			return;
		}
		if(split[0].equals("addMap"))
		{
			if(split.length < 3)
			{
				sender.sendChatToPlayer("You need to specify a map name");
				return;
			}
			String shortName = split[1];
			String name = split[2];
			for(int i = 3; i < split.length; i++)
			{
				name += " " + split[i];
			}
			teamsManager.maps.add(new TeamsMap(shortName, name));
			sender.sendChatToPlayer("Added new map : " + name + " (" + shortName + ")");
			return;
		}
		if(split[0].equals("removeMap"))
		{
			if(split.length != 2)
			{
				sender.sendChatToPlayer("You need to specify a map's short name");
				return;
			}
			TeamsMap map = teamsManager.getTeamsMap(split[1]);
			if(map != null)
			{
				teamsManager.maps.remove(map);
			}
			sender.sendChatToPlayer("Removed map " + split[1]);
			return;
		}
		if(split[0].equals("setMap"))
		{
			if(split.length != 2)
			{
				sender.sendChatToPlayer("You need to specify a map's short name");
				return;
			}
			TeamsMap map = teamsManager.getTeamsMap(split[1]);
			if(map != null)
			{
				teamsManager.messageAll("\u00a72Map changed to " + map.name + ".");
				teamsManager.currentMap = map;
				if(teamsManager.currentGametype != null)
				{
					teamsManager.currentGametype.startNewRound();
				}
			}
			return;
		}
		if(split[0].equals("listTeams"))
		{
			if(teamsManager.currentGametype == null || teamsManager.teams == null)
			{
				sender.sendChatToPlayer("\u00a74The gametype is not yet set. Set it by \"/teams setGametype <gametype>\"");
				return;
			}
			sender.sendChatToPlayer("\u00a72Showing currently in use teams");
			for(int i = 0; i < teamsManager.teams.length; i++)
			{
				Team team = teamsManager.teams[i];
				if(team == null)
					sender.sendChatToPlayer("\u00a7f" + i + " : No team");
				else
					sender.sendChatToPlayer("\u00a7" + team.textColour + i + " : " + team.name + " (" + team.shortName + ")");
			}
			return;
		}
		if(split[0].equals("listAllTeams"))
		{
			if(Team.teams.size() == 0)
			{
				sender.sendChatToPlayer("\u00a74No teams available. You need a content pack that has some teams with it");
				return;
			}
			sender.sendChatToPlayer("\u00a72Showing all avaliable teams");
			sender.sendChatToPlayer("\u00a72To pick these teams, use /teams setTeams <team1> <team2> with the names in brackets");
			for(Team team : Team.teams)
			{
				sender.sendChatToPlayer("\u00a7" + team.textColour + team.name + " (" + team.shortName + ")");
			}
			return;
		}
		if(split[0].equals("setTeams"))
		{
			if(teamsManager.currentGametype == null || teamsManager.teams == null)
			{
				sender.sendChatToPlayer("\u00a74No gametype selected. Please select the gametype with the setGametype command");
				return;
			}
			if(split.length - 1 != teamsManager.teams.length)
			{
				sender.sendChatToPlayer("\u00a74Wrong number of teams given. This gametype requires " + teamsManager.teams.length + " teams to work");
				return;
			}
			Team[] teams = new Team[teamsManager.teams.length];
			String teamList = "";
			for(int i = 0; i < split.length - 1; i++)
			{
				Team team = Team.getTeam(split[i + 1]);
				if(team == null)
				{
					sender.sendChatToPlayer("\u00a74" + split[i + 1] + " is not a valid team");
					return;
				}
				for(int j = 0; j < i; j++)
				{
					if(team == teams[j])
					{
						sender.sendChatToPlayer("\u00a74You may not add " + split[i + 1] + " twice");
						return;
					}
				}
				teams[i] = team;
				teamList += (i == 0 ? "" : (i == split.length - 2 ? " and " : ", ")) + "\u00a7" + team.textColour + team.name + "\u00a7f";
			}
			teamsManager.teams = teams;
			teamsManager.currentGametype.teamsSet();
			teamsManager.messageAll("\u00a72" + sender.getCommandSenderName() + "\u00a7f changed the teams to be " + teamList);
			return;
		}
		if(split[0].equals("getSticks") || split[0].equals("getOpSticks") || split[0].equals("getOpKit"))
		{
			EntityPlayerMP player = getPlayer(sender.getCommandSenderName());
			if(player != null)
			{
				player.inventory.addItemStackToInventory(new ItemStack(FlansMod.opStick, 1, 0));
				player.inventory.addItemStackToInventory(new ItemStack(FlansMod.opStick, 1, 1));
				player.inventory.addItemStackToInventory(new ItemStack(FlansMod.opStick, 1, 2));
				player.inventory.addItemStackToInventory(new ItemStack(FlansMod.opStick, 1, 3));
				sender.sendChatToPlayer("\u00a72Enjoy your op sticks.");
				sender.sendChatToPlayer("\u00a77The Stick of Connecting connects objects (spawners, banners etc) to bases (flagpoles etc)");
				sender.sendChatToPlayer("\u00a77The Stick of Ownership sets the team that currently owns a base");
				sender.sendChatToPlayer("\u00a77The Stick of Mapping sets the map that a base is currently associated with");
				sender.sendChatToPlayer("\u00a77The Stick of Destruction deletes bases and team objects");
			}
			return;
		}
		if(split[0].equals("useRotation"))
		{
			if(split.length != 2)
			{
				sender.sendChatToPlayer("Incorrect Usage : Should be /teams " + split[0] + " <true/false>");	
				return;
			}
			FlansMod.useRotation = Boolean.parseBoolean(split[1]);
			sender.sendChatToPlayer("Map rotation is now " + (FlansMod.useRotation ? "enabled" : "disabled"));
			return;
		}
		if(split[0].equals("listRotation"))
		{
			sender.sendChatToPlayer("\u00a72Current Map Rotation");
			for(int i = 0; i < TeamsManager.getInstance().rotation.size(); i++)
			{
				TeamsManager.RotationEntry entry = TeamsManager.getInstance().rotation.get(i);
				String s = i + ". " + entry.map.shortName + ", " + entry.gametype.shortName;
				if(i == TeamsManager.getInstance().currentRotationEntry)
				{
					s = "\u00a74" + s;
				}
				for(int j = 0; j < entry.teams.length; j++)
				{
					s += ", " + entry.teams[j].shortName;
				}
				sender.sendChatToPlayer(s);
			}
			return;
		}
		if(split[0].equals("removeMapFromRotation") || split[0].equals("removeFromRotation") || split[0].equals("removeRotation"))
		{
			if(split.length != 2)
			{
				sender.sendChatToPlayer("Incorrect Usage : Should be /teams " + split[0] + " <ID>");	
				return;
			}
			int map = Integer.parseInt(split[1]);
			sender.sendChatToPlayer("Removed map " + map + " (" + TeamsManager.getInstance().rotation.get(map).map.shortName + ") from rotation");
			TeamsManager.getInstance().rotation.remove(map);
			return;
		}
		if(split[0].equals("addMapToRotation") || split[0].equals("addToRotation") || split[0].equals("addRotation"))
		{
			if(split.length < 5)
			{
				sender.sendChatToPlayer("Incorrect Usage : Should be /teams " + split[0] + " <Map> <Gametype> <Team1> <Team2> ...");	
				return;
			}
			TeamsMap map = TeamsManager.getInstance().getTeamsMap(split[1]);
			Gametype gametype = Gametype.getGametype(split[2]);
			if(split.length != 3 + gametype.numTeamsRequired)
			{
				sender.sendChatToPlayer("Incorrect Usage : Should be /teams " + split[0] + " <Map> <Gametype> <Team1> <Team2> ...");	
				return;
			}
			Team[] teams = new Team[gametype.numTeamsRequired];
			for(int i = 0; i < teams.length; i++)
			{
				teams[i] = Team.getTeam(split[3 + i]);
			}
			sender.sendChatToPlayer("Added map (" + map.shortName + ") to rotation");
			TeamsManager.getInstance().rotation.add(new TeamsManager.RotationEntry(map, gametype, teams));
			return;
		}
		if(split[0].equals("forceAdventure") || split[0].equals("forceAdventureMode"))
		{
			if(split.length != 2)
			{
				sender.sendChatToPlayer("Incorrect Usage : Should be /teams " + split[0] + " <true/false>");	
				return;
			}
			FlansMod.forceAdventureMode = Boolean.parseBoolean(split[1]);
			sender.sendChatToPlayer("Adventure mode will " + (FlansMod.forceAdventureMode ? "now" : "no longer") + " be forced");
			return;
		}
		if(split[0].equals("explosions"))
		{
			if(split.length != 2)
			{
				sender.sendChatToPlayer("Incorrect Usage : Should be /teams " + split[0] + " <true/false>");	
				return;
			}
			FlansMod.explosions = Boolean.parseBoolean(split[1]);
			sender.sendChatToPlayer("Expolsions are now " + (FlansMod.explosions ? "enabled" : "disabled"));
			return;
		}
		if(split[0].equals("bombs") || split[0].equals("allowBombs"))
		{
			if(split.length != 2)
			{
				sender.sendChatToPlayer("Incorrect Usage : Should be /teams " + split[0] + " <true/false>");	
				return;
			}
			FlansMod.bombsEnabled = Boolean.parseBoolean(split[1]);
			sender.sendChatToPlayer("Bombs are now " + (FlansMod.bombsEnabled ? "enabled" : "disabled"));
			return;
		}
		if(split[0].equals("bullets") || split[0].equals("bulletsEnabled"))
		{
			if(split.length != 2)
			{
				sender.sendChatToPlayer("Incorrect Usage : Should be /teams " + split[0] + " <true/false>");	
				return;
			}
			FlansMod.bulletsEnabled = Boolean.parseBoolean(split[1]);
			sender.sendChatToPlayer("Bullets are now " + (FlansMod.bulletsEnabled ? "enabled" : "disabled"));
			return;
		}
		if(split[0].equals("canBreakGuns"))
		{
			if(split.length != 2)
			{
				sender.sendChatToPlayer("Incorrect Usage : Should be /teams " + split[0] + " <true/false>");	
				return;
			}
			FlansMod.canBreakGuns = Boolean.parseBoolean(split[1]);
			sender.sendChatToPlayer("AAGuns and MGs can " + (FlansMod.canBreakGuns ? "now" : "no longer") + " be broken");
			return;
		}
		if(split[0].equals("canBreakGlass"))
		{
			if(split.length != 2)
			{
				sender.sendChatToPlayer("Incorrect Usage : Should be /teams " + split[0] + " <true/false>");	
				return;
			}
			FlansMod.canBreakGlass = Boolean.parseBoolean(split[1]);
			sender.sendChatToPlayer("Glass and glowstone can " + (FlansMod.canBreakGlass ? "now" : "no longer") + " be broken");
			return;
		}
		if(split[0].equals("armourDrops") || split[0].equals("armorDrops"))
		{
			if(split.length != 2)
			{
				sender.sendChatToPlayer("Incorrect Usage : Should be /teams " + split[0] + " <true/false>");	
				return;
			}
			FlansMod.armourDrops = Boolean.parseBoolean(split[1]);
			sender.sendChatToPlayer("Armour will " + (FlansMod.armourDrops ? "now" : "no longer") + " be dropped");
			return;
		}
		if(split[0].equals("weaponDrops"))
		{
			if(split.length != 2)
			{
				sender.sendChatToPlayer("Incorrect Usage : Should be /teams " + split[0] + " <on/off/smart>");	
				return;
			}
			if(split[1].toLowerCase().equals("on"))
			{
				FlansMod.weaponDrops = 1;
				sender.sendChatToPlayer("Weapons will be dropped normally");
			}
			else if(split[1].toLowerCase().equals("off"))
			{
				FlansMod.weaponDrops = 0;
				sender.sendChatToPlayer("Weapons will be not be dropped");
			}
			else if(split[1].toLowerCase().equals("smart"))
			{
				FlansMod.weaponDrops = 2;
				sender.sendChatToPlayer("Smart drops enabled");
			}
			return;
		}
		if(split[0].equals("fuelNeeded"))
		{
			if(split.length != 2)
			{
				sender.sendChatToPlayer("Incorrect Usage : Should be /teams " + split[0] + " <true/false>");	
				return;
			}
			FlansMod.vehiclesNeedFuel = Boolean.parseBoolean(split[1]);
			sender.sendChatToPlayer("Vehicles will " + (FlansMod.vehiclesNeedFuel ? "now" : "no longer") + " require fuel");
			return;
		}
		if(split[0].equals("mgLife"))
		{
			if(split.length != 2)
			{
				sender.sendChatToPlayer("Incorrect Usage : Should be /teams " + split[0] + " <time>");	
				return;
			}
			FlansMod.mgLife = Integer.parseInt(split[1]);
			if(FlansMod.mgLife > 0)
				sender.sendChatToPlayer("MGs will despawn after " + FlansMod.mgLife + " seconds");
			else sender.sendChatToPlayer("MGs will not despawn");
			return;
		}
		if(split[0].equals("planeLife"))
		{
			if(split.length != 2)
			{
				sender.sendChatToPlayer("Incorrect Usage : Should be /teams " + split[0] + " <time>");	
				return;
			}
			FlansMod.planeLife = Integer.parseInt(split[1]);
			if(FlansMod.planeLife > 0)
				sender.sendChatToPlayer("Planes will despawn after " + FlansMod.planeLife + " seconds");
			else sender.sendChatToPlayer("Planes will not despawn");
			return;
		}
		if(split[0].equals("vehicleLife"))
		{
			if(split.length != 2)
			{
				sender.sendChatToPlayer("Incorrect Usage : Should be /teams " + split[0] + " <time>");	
				return;
			}
			FlansMod.vehicleLife = Integer.parseInt(split[1]);
			if(FlansMod.vehicleLife > 0)
				sender.sendChatToPlayer("Vehicles will despawn after " + FlansMod.vehicleLife + " seconds");
			else sender.sendChatToPlayer("Vehicles will not despawn");
			return;
		}
		if(split[0].equals("aaLife"))
		{
			if(split.length != 2)
			{
				sender.sendChatToPlayer("Incorrect Usage : Should be /teams " + split[0] + " <time>");	
				return;
			}
			FlansMod.aaLife = Integer.parseInt(split[1]);
			if(FlansMod.aaLife > 0)
				sender.sendChatToPlayer("AA Guns will despawn after " + FlansMod.aaLife + " seconds");
			else sender.sendChatToPlayer("AA Guns will not despawn");
			return;
		}
		if(split[0].equals("setVariable"))
		{
			if(TeamsManager.getInstance().currentGametype == null)
			{
				sender.sendChatToPlayer("There is no gametype to set variables for");		
				return;
			}
			if(split.length != 3)
			{
				sender.sendChatToPlayer("Incorrect Usage : Should be /teams setVariable <variable> <value>");	
				return;
			}
			if(TeamsManager.getInstance().currentGametype.setVariable(split[1], split[2]))
				sender.sendChatToPlayer("Set variable " + split[1] + " in gametype " + TeamsManager.getInstance().currentGametype.shortName + " to " + split[2]);
			else sender.sendChatToPlayer("Variable " + split[1] + " did not exist in gametype " + TeamsManager.getInstance().currentGametype.shortName);
			return;
		}
		sender.sendChatToPlayer(split[0] + " is not a valid teams command. Try /teams help");
	}
	
	public void sendHelpInformation(ICommandSender sender)
	{
		sender.sendChatToPlayer("\u00a72Listing teams commands");
		sender.sendChatToPlayer("/teams listGametypes");
		sender.sendChatToPlayer("/teams setGametype <name>");
		sender.sendChatToPlayer("/teams listAllTeams");
		sender.sendChatToPlayer("/teams listTeams");
		sender.sendChatToPlayer("/teams setTeams <teamName1> <teamName2>");
		sender.sendChatToPlayer("/teams getSticks");
		sender.sendChatToPlayer("/teams setVariable <variable> <value>");
		sender.sendChatToPlayer("/teams forceAdventure <true / false>");
		sender.sendChatToPlayer("/teams explosions <true / false>");
		sender.sendChatToPlayer("/teams canBreakGuns <true / false>");
		sender.sendChatToPlayer("/teams canBreakGlass <true / false>");
		sender.sendChatToPlayer("/teams armourDrops <true / false>");
		sender.sendChatToPlayer("/teams weaponDrops <off / on / smart>");
		sender.sendChatToPlayer("/teams fuelNeeded <true / false>");
		sender.sendChatToPlayer("/teams mgLife <time>");
		/*
		sender.sendChatToPlayer("Listing teams commands");
		sender.sendChatToPlayer("/teams listGametypes - see the gametypes available on this server");
		sender.sendChatToPlayer("/teams setGametype <name> - set the gametype (to one listed by listGametypes)");
		sender.sendChatToPlayer("/teams listAllTeams - list the teams available on this server");
		sender.sendChatToPlayer("/teams listTeams - list the currently in use teams");
		sender.sendChatToPlayer("/teams setTeams <teamName1> <teamName2> - set the teams (to teams listed by listAllTeams)");
		sender.sendChatToPlayer("/teams getSticks - get the Op sticks (for map making and editing)");
		sender.sendChatToPlayer("/teams setVariable <variable> <value> - set a variable for the current gametype");
		sender.sendChatToPlayer("/teams forceAdventure <true / false> - set whether players should be in adventure mode while playing");
		sender.sendChatToPlayer("/teams explosions <true / false> - set whether explosions damage the world");
		sender.sendChatToPlayer("/teams canBreakGuns <true / false> - set whether MGs / AAGuns can be broken");
		*/
	}

	public EntityPlayerMP getPlayer(String name)
	{
		return MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(name);
	}
}
