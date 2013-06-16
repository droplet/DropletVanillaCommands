/*
 * This file is part of DropletVanillaCommands.
 *
 * Copyright (c) 2012 Spout LLC <http://www.spout.org/>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.spout.droplet.vanillacommands.commands;

import org.spout.api.command.CommandArguments;
import org.spout.api.command.CommandSource;
import org.spout.api.command.annotated.Command;
import org.spout.api.command.annotated.Permissible;
import org.spout.api.entity.Player;
import org.spout.api.exception.CommandException;
import org.spout.api.geo.discrete.Point;

import org.spout.vanilla.component.entity.misc.Burn;
import org.spout.vanilla.component.entity.misc.Effects;
import org.spout.vanilla.component.entity.misc.Health;
import org.spout.vanilla.component.entity.misc.Hunger;
import org.spout.vanilla.data.effect.EntityEffect;
import org.spout.vanilla.data.effect.EntityEffectType;
import org.spout.vanilla.data.effect.ExplosionEffect;
import org.spout.vanilla.event.cause.HealthChangeCause;

public class VanillaCommands {
	/**
	 * Used for starving the player.
	 * @throws CommandException 
	 */
	@Command(aliases = {"starve"}, usage = "<player> <hunger level>", desc = "Sets the hunger level of the player.")
	@Permissible("vanillacommands.starve")
	public void starve(CommandSource source, CommandArguments args) throws CommandException {
		if (args.length() == 0) {
			if (source instanceof Player) {
				((Player)source).get(Hunger.class).setHunger(0);
				source.sendMessage("Succesfully set your hunger to zero");
			} else {
				source.sendMessage("You are not a player!");
			}
		} else {
			if (args.isPlayer(0)) {
				int hungerLevel = 0;
				if (args.length() == 2 && args.isInteger(1)) {
					hungerLevel = args.getInteger(1);
				}
				args.getPlayer(0).get(Hunger.class).setHunger(hungerLevel);
				source.sendMessage("Succesfully set " + args.getString(0) + "'s hunger to " + hungerLevel);
			} else {
				source.sendMessage(args.getString(0) + " is not a player!");
			}
		}
	}

	/**
	 * Used for setting the health of the player.
	 * @throws CommandException 
	 */
	@Command(aliases = {"hurt", "damage"}, usage = "<player> <health level>", desc = "Sets the health level of the player.")
	@Permissible("vanillacommands.hurt")
	public void hurt(CommandSource source, CommandArguments args) throws CommandException {
		if (args.length() == 0) {
			if (source instanceof Player) {
				((Player)source).get(Health.class).setHealth(1, HealthChangeCause.COMMAND);
				source.sendMessage("Succesfully set your health to one");
			} else {
				source.sendMessage("You are not a player!");
			}
		} else {
			if (args.isPlayer(0)) {
				int healthLevel = 0;
				if (args.length() == 2 && args.isInteger(1)) {
					healthLevel = args.getInteger(1);
				}
				args.getPlayer(0).get(Health.class).setHealth(healthLevel, HealthChangeCause.COMMAND);
				source.sendMessage("Succesfully set " + args.getString(0) + "'s health to " + healthLevel);
			} else {
				source.sendMessage(args.getString(0) + " is not a player!");
			}
		}
	}

	/**
	 * Used for setting the player on fire
	 * @throws CommandException 
	 */
	@Command(aliases = {"ignite"}, usage = "<player>", desc = "Sets the player on fire.")
	@Permissible("vanillacommands.ignite")
	public void ignite(CommandSource source, CommandArguments args) throws CommandException {
		if (args.length() == 0) {
			if (source instanceof Player) {
				((Player)source).get(Burn.class).setOnFire(20, true);
				source.sendMessage("Succesfully set you on fire!");
			} else {
				source.sendMessage("You are not a player!");
			}
		} else {
			if (args.isPlayer(0)) {
				args.getPlayer(0).get(Burn.class).setOnFire(20, true);
				source.sendMessage("Succesfully set " + args.getString(0) + " on fire!");
			} else {
				source.sendMessage(args.getString(0) + " is not a player!");
			}
		}
	}

	/**
	 * Used for teleporting the player high up
	 * @throws CommandException 
	 */
	@Command(aliases = {"fall"}, usage = "<player>", desc = "Makes the player fall.")
	@Permissible("vanillacommands.fall")
	public void fall(CommandSource source, CommandArguments args) throws CommandException {
		if (args.length() == 0) {
			if (source instanceof Player) {
				Player player = (Player)source;
				Point point = player.getScene().getPosition();
				Point newPoint = new Point(point.getWorld(), point.getBlockX(), (point.getBlockY() + 60), point.getBlockZ());
				player.teleport(newPoint);
				source.sendMessage("Succesfully fell!");
			} else {
				source.sendMessage("You must be a player!");
				source.sendMessage("Or use /c fall <player>");
			}
		} else {
			if (args.isPlayer(0)) {
				int distance = 60;
				if (args.length() == 2 && args.isInteger(1)) {
					distance = args.getInteger(1);
				}
				Player player = args.getPlayer(0);
				Point point = player.getScene().getPosition();
				Point newPoint = new Point(point.getWorld(), point.getBlockX(), (point.getBlockY() + distance), point.getBlockZ());
				source.sendMessage("Succesfully made " + args.getString(0) + " fall " + distance + " blocks!");
				player.teleport(newPoint);
			} else {
				source.sendMessage(args.getString(0) + " is not a player!");
			}
		}
	}

	/**
	 * Used for creating an explosion around the player
	 * @throws CommandException 
	 */
	@Command(aliases = {"explode"}, usage = "<player>", desc = "Makes the player explode.")
	@Permissible("vanillacommands.explode")
	public void explode(CommandSource source, CommandArguments args) throws CommandException {
		if (args.length() == 0) {
			if (source instanceof Player) {
				Player player = ((Player)source);
				Point point = player.getScene().getPosition();
				ExplosionEffect explosion = new ExplosionEffect(10, 4);
				explosion.play(player, point);
				player.get(Health.class).setHealth(0, HealthChangeCause.COMMAND);
				source.sendMessage("Succesfully exploded!");
			} else {
				source.sendMessage("You are not a player!");
				source.sendMessage("Or use /c explode <player>");
			}
		} else {
			if (args.isPlayer(0)) {
				Player player = args.getPlayer(0);
				Point point = player.getScene().getPosition();
				ExplosionEffect explosion = new ExplosionEffect(10, 4);
				explosion.play(player, point);
				player.get(Health.class).setHealth(0, HealthChangeCause.COMMAND);
				source.sendMessage("Succesfully exploded!");
			} else {
				source.sendMessage(args.getString(0) + " is not a player!");
			}
		}
	}

	/**
	 * Used for blinding a player
	 * @throws CommandException 
	 */
	@Command(aliases = {"blind"}, usage = "<player>", desc = "Makes the player blind.")
	@Permissible("vanillacommands.blind")
	public void blind(CommandSource source, CommandArguments args) throws CommandException {
		Player player = null;
		if (args.length() == 0) {
			player = (Player)source;
		} else if (args.length() == 1) {
			if (args.isPlayer(0)) {
				player = args.getPlayer(0);
			} else {
				source.sendMessage(args.getString(0) + " is not a player!");
			}
		}
		if (player != null) {
			player.add(Effects.class).add(new EntityEffect(EntityEffectType.BLINDNESS, 3F));
			source.sendMessage("Succesfully made " + player.getName() + " blind!");
		} else {
			source.sendMessage("Syntax: /c blind <player>");
		}
	}

	/**
	 * Used for giving the player the Nausea effect
	 * @throws CommandException 
	 */
	@Command(aliases = {"ill"}, usage = "<player>", desc = "Makes the player ill.")
	@Permissible("vanillacommands.ill")
	public void ill(CommandSource source, CommandArguments args) throws CommandException {
		Player player = null;
		if (args.length() == 0) {
			player = (Player)source;
		} else if (args.length() == 1) {
			if (args.isPlayer(0)) {
				player = args.getPlayer(0);
			} else {
				source.sendMessage(args.getString(0) + " is not a player!");
			}
		}
		if (player != null) {
			player.add(Effects.class).add(new EntityEffect(EntityEffectType.NAUSEA, 3F));
			source.sendMessage("Succesfully made " + player.getName() + " ill!");
		} else {
			source.sendMessage("Syntax: /c ill <player>");
		}
	}

	/**
	 * Used for giving the player the hunger effect
	 * @throws CommandException 
	 */
	@Command(aliases = {"hungry"}, usage = "<player>", desc = "Makes the player hungry.")
	@Permissible("vanillacommands.hungry")
	public void hungry(CommandSource source, CommandArguments args) throws CommandException {
		Player player = null;
		if (args.length() == 0) {
			player = (Player)source;
		} else if (args.length() == 1) {
			if (args.isPlayer(0)) {
				player = args.getPlayer(0);
			} else {
				source.sendMessage(args.getString(0) + " is not a player!");
			}
		}
		if (player != null) {
			player.add(Effects.class).add(new EntityEffect(EntityEffectType.HUNGER, 3F));
			source.sendMessage("Succesfully made " + player.getName() + " hungry!");
		} else {
			source.sendMessage("Syntax: /c hungry <player>");
		}
	}
}
