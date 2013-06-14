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

import org.spout.api.Spout;
import org.spout.api.chat.ChatArguments;
import org.spout.api.chat.style.ChatStyle;
import org.spout.api.command.CommandContext;
import org.spout.api.command.CommandSource;
import org.spout.api.entity.Player;
import org.spout.api.geo.discrete.Point;

import org.spout.vanilla.component.entity.misc.Burn;
import org.spout.vanilla.component.entity.misc.Effects;
import org.spout.vanilla.component.entity.misc.Health;
import org.spout.vanilla.component.entity.misc.Hunger;
import org.spout.vanilla.data.effect.ExplosionEffect;
import org.spout.vanilla.data.effect.StatusEffect;
import org.spout.vanilla.data.effect.StatusEffectContainer;
import org.spout.vanilla.event.cause.HealthChangeCause;

public class CommandMethods {
	/**
	 * Used for starving the player.
	 * @param args
	 * @param source
	 */
	public void starve(CommandContext args, CommandSource source) {
		if (args.length() == 0) {
			try {
				Spout.getEngine().getPlayer(source.getName(), false).get(Hunger.class).setHunger(0);
				source.sendMessage(new ChatArguments("Succesfully set your hunger to zero"));
			} catch (Exception e) {
				source.sendMessage("You are not a player!");
			}
		} else if (args.length() == 1) {
			Player player = null;

			try {
				player = Spout.getEngine().getPlayer(args.get(0).getPlainString(), false);
				player.get(Hunger.class).setHunger(0);
				source.sendMessage(new ChatArguments("Succesfully set ", player.getName(), "'s hunger to zero"));
			} catch (NullPointerException e) {
				source.sendMessage(args.get(0).toString(), " is not a player!");
			}
		} else if (args.length() == 2) {
			Player player = null;
			int hungerLevel;

			try {
				player = Spout.getEngine().getPlayer(args.get(0).getPlainString(), false);
			} catch (NullPointerException e) {
				source.sendMessage(args.get(0).toString(), " is not a player!");
			}

			try {
				hungerLevel = args.getInteger(1);
				player.get(Hunger.class).setHunger(hungerLevel);
				source.sendMessage(new ChatArguments("Succesfully set ", player.getName(), "'s hunger to ", hungerLevel));
			} catch (Exception e) {
				source.sendMessage(args.get(1), " is not a valid hunger level!");
			}
		} else {
			source.sendMessage("Syntax: /c starve <player> <hunger level>");
		}
	}

	/**
	 * Used for setting the health of the player.
	 * @param args
	 * @param source
	 */
	public void hurt(CommandContext args, CommandSource source) {
		if (args.length() == 0) {
			try {
				Spout.getEngine().getPlayer(source.getName(), false).get(Health.class).setHealth(1, HealthChangeCause.COMMAND);
				source.sendMessage(new ChatArguments("Succesfully set your health to one"));
			} catch (Exception e) {
				source.sendMessage("You are not a player!");
			}
		} else if (args.length() == 1) {
			Player player = null;

			try {
				player = Spout.getEngine().getPlayer(args.get(0).getPlainString(), false);
				player.get(Health.class).setHealth(1, HealthChangeCause.COMMAND);
				source.sendMessage(new ChatArguments("Succesfully set ", player.getName(), "'s health to one"));
			} catch (NullPointerException e) {
				source.sendMessage(args.get(0).toString(), " is not a player!");
			}
		} else if (args.length() == 2) {
			Player player = null;
			int healthLevel;

			try {
				player = Spout.getEngine().getPlayer(args.get(0).getPlainString(), false);
			} catch (NullPointerException e) {
				source.sendMessage(args.get(0).toString(), " is not a player!");
			}

			try {
				healthLevel = args.getInteger(1);
				player.get(Health.class).setHealth(healthLevel, HealthChangeCause.COMMAND);
				source.sendMessage(new ChatArguments("Succesfully set ", player.getName(), "'s health to ", healthLevel));
			} catch (Exception e) {
				source.sendMessage(args.get(1), " is not a valid health level!");
			}
		} else {
			source.sendMessage("Syntax: /c hurt <player> <health level>");
		}
	}

	/**
	 * Used for setting the player on fire
	 * @param args
	 * @param source
	 */
	public void ignite(CommandContext args, CommandSource source) {
		if (args.length() == 0) {
			try {
				Player player = Spout.getEngine().getPlayer(source.getName(), false);
				player.get(Burn.class).setOnFire(20, true);
				source.sendMessage(new ChatArguments("Succesfully set you on fire!"));
			} catch (Exception e) {
				source.sendMessage("You are not a player!");
			}
		} else if (args.length() == 1) {
			Player player = null;

			try {
				player = Spout.getEngine().getPlayer(args.get(0).getPlainString(), false);
				player.get(Burn.class).setOnFire(20, true);
				source.sendMessage(new ChatArguments("Succesfully set ", player.getName(), " on fire!"));
			} catch (NullPointerException e) {
				source.sendMessage(args.get(0).toString(), " is not a player!");
			}
		} else {
			source.sendMessage("Syntax: /c ignite <player>");
		}
	}

	/**
	 * Used for teleporting the player high up
	 * @param args
	 * @param source
	 */
	public void fall(CommandContext args, CommandSource source) {
		if (args.length() == 0) {
			try {
				Player player = Spout.getEngine().getPlayer(source.getName(), false);
				Point point = player.getScene().getPosition();
				Point newPoint = new Point(point.getWorld(), point.getBlockX(), (point.getBlockY() + 60), point.getBlockZ());
				player.teleport(newPoint);
				source.sendMessage(new ChatArguments("Succesfully fell!"));
			} catch (Exception e) {
				source.sendMessage(new ChatArguments("You must be a player!"));
				source.sendMessage(new ChatArguments(ChatStyle.RED, "Or use /c fall <player>"));
			}
		} else if (args.length() == 1) {
			Player player = null;

			try {
				player = Spout.getEngine().getPlayer(args.get(0).getPlainString(), false);
				Point point = player.getScene().getPosition();
				Point newPoint = new Point(point.getWorld(), point.getBlockX(), (point.getBlockY() + 60), point.getBlockZ());
				player.teleport(newPoint);
				source.sendMessage(new ChatArguments("Succesfully made ", player.getName(), " fall!"));
			} catch (NullPointerException e) {
				source.sendMessage(args.get(0).toString(), " is not a player!");
			}
		} else if (args.length() == 2) {
			Player player = null;
			int distance = 0;

			try {
				player = Spout.getEngine().getPlayer(args.get(0).getPlainString(), false);
				Point point = player.getScene().getPosition();
				distance = args.getInteger(1);
				Point newPoint = new Point(point.getWorld(), point.getBlockX(), (point.getBlockY() + distance), point.getBlockZ());
				player.teleport(newPoint);
				source.sendMessage(new ChatArguments("Succesfully made ", player.getName(), " fall " + distance + " blocks!"));
			} catch (NullPointerException e) {
				source.sendMessage(args.get(0).toString(), " is not a player!");
			} catch (Exception e) {
				source.sendMessage(args.get(1).toString(), " is not a valid distance!");
			}
		} else {
			source.sendMessage("Syntax: /c fall <player> <distance>");
		}
	}

	/**
	 * Used for creating an explosion around the player
	 * @param args
	 * @param source
	 */
	public void explode(CommandContext args, CommandSource source) {
		if (args.length() == 0) {
			try {
				Player player = Spout.getEngine().getPlayer(source.getName(), false);
				Point point = player.getScene().getPosition();
				ExplosionEffect explosion = new ExplosionEffect(10, 4);
				explosion.play(player, point);
				player.get(Health.class).setHealth(0, HealthChangeCause.COMMAND);
				source.sendMessage(new ChatArguments("Succesfully exploded!"));
			} catch (Exception e) {
				source.sendMessage(new ChatArguments("You must be a player!"));
				source.sendMessage(new ChatArguments(ChatStyle.RED, "Or use /c explode <player>"));
			}
		} else if (args.length() == 1) {
			try {
				Player player = Spout.getEngine().getPlayer(args.get(0).getPlainString(), false);
				Point point = player.getScene().getPosition();
				ExplosionEffect explosion = new ExplosionEffect(10, 4);
				explosion.play(player, point);
				player.get(Health.class).setHealth(0, HealthChangeCause.COMMAND);
				source.sendMessage(new ChatArguments("Succesfully exploded ", player.getName(), "!"));
			} catch (NullPointerException e) {
				source.sendMessage(args.get(0).toString(), " is not a player!");
			}
		} else {
			source.sendMessage("Syntax: /c explode <player>");
		}
	}

	/**
	 * Used for blinding a player
	 * @param args
	 * @param source
	 */
	public void blind(CommandContext args, CommandSource source) {
		if (args.length() == 0) {
			try {
				Player player = Spout.getEngine().getPlayer(source.getName(), false);
				StatusEffectContainer blindness = new StatusEffectContainer(StatusEffect.BLINDNESS, 20);
				player.add(Effects.class).addEffect(blindness);
				source.sendMessage(new ChatArguments("Succesfully made blind!"));
			} catch (Exception e) {
				source.sendMessage(new ChatArguments("You must be a player!"));
				source.sendMessage(new ChatArguments(ChatStyle.RED, "Or use /c blind <player>"));
			}
		} else if (args.length() == 1) {
			try {
				Player player = Spout.getEngine().getPlayer(args.get(0).getPlainString(), false);
				StatusEffectContainer blindness = new StatusEffectContainer(StatusEffect.BLINDNESS, 20);
				player.add(Effects.class).addEffect(blindness);
				source.sendMessage(new ChatArguments("Succesfully made ", player.getName(), " blind!"));
			} catch (NullPointerException e) {
				source.sendMessage(args.get(0).toString(), " is not a player!");
			}
		} else {
			source.sendMessage("Syntax: /c blind <player>");
		}
	}

	/**
	 * Used for giving the player the Nausea effect
	 * @param args
	 * @param source
	 */
	public void ill(CommandContext args, CommandSource source) {
		if (args.length() == 0) {
			try {
				Player player = Spout.getEngine().getPlayer(source.getName(), false);
				StatusEffectContainer nausea = new StatusEffectContainer(StatusEffect.NAUSEA, 20);
				player.add(Effects.class).addEffect(nausea);
				source.sendMessage(new ChatArguments("Succesfully made ill!"));
			} catch (Exception e) {
				source.sendMessage(new ChatArguments("You must be a player!"));
				source.sendMessage(new ChatArguments(ChatStyle.RED, "Or use /c ill <player>"));
			}
		} else if (args.length() == 1) {
			try {
				Player player = Spout.getEngine().getPlayer(args.get(0).getPlainString(), false);
				StatusEffectContainer nausea = new StatusEffectContainer(StatusEffect.NAUSEA, 20);
				player.add(Effects.class).addEffect(nausea);
				source.sendMessage(new ChatArguments("Succesfully made ", player.getName(), " ill!"));
			} catch (NullPointerException e) {
				source.sendMessage(args.get(0).toString(), " is not a player!");
			}
		} else {
			source.sendMessage("Syntax: /c ill <player>");
		}
	}

	/**
	 * Used for giving the player the hunger effect
	 * @param args
	 * @param source
	 */
	public void hungry(CommandContext args, CommandSource source) {
		if (args.length() == 0) {
			try {
				Player player = Spout.getEngine().getPlayer(source.getName(), false);
				StatusEffectContainer hunger = new StatusEffectContainer(StatusEffect.HUNGER, 20);
				player.add(Effects.class).addEffect(hunger);
				source.sendMessage(new ChatArguments("Succesfully made hungry!"));
			} catch (Exception e) {
				source.sendMessage(new ChatArguments("You must be a player!"));
				source.sendMessage(new ChatArguments(ChatStyle.RED, "Or use /c hungry <player>"));
			}
		} else if (args.length() == 1) {
			try {
				Player player = Spout.getEngine().getPlayer(args.get(0).getPlainString(), false);
				StatusEffectContainer nausea = new StatusEffectContainer(StatusEffect.HUNGER, 20);
				player.add(Effects.class).addEffect(nausea);
				source.sendMessage(new ChatArguments("Succesfully made ", player.getName(), " hungry!"));
			} catch (NullPointerException e) {
				source.sendMessage(args.get(0).toString(), " is not a player!");
			}
		} else if (args.length() == 2) {
			try {
				Player player = Spout.getEngine().getPlayer(args.get(0).getPlainString(), false);
				StatusEffectContainer nausea = new StatusEffectContainer(StatusEffect.HUNGER, 20, args.getInteger(1));
				player.add(Effects.class).addEffect(nausea);
				source.sendMessage(new ChatArguments("Succesfully made ", player.getName(), " hungry at level ", args.getInteger(1)));
			} catch (NullPointerException e) {
				source.sendMessage(args.get(0).toString(), " is not a player!");
			}
		} else {
			source.sendMessage("Syntax: /c hungry <player>");
		}
	}
}
