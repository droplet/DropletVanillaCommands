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

import org.spout.api.command.CommandContext;
import org.spout.api.command.CommandSource;
import org.spout.api.command.annotated.Command;
import org.spout.api.command.annotated.CommandPermissions;

import org.spout.droplet.vanillacommands.DropletVanillaCommands;

public class VanillaCommands {
	DropletVanillaCommands plugin;
	CommandMethods CM = new CommandMethods();

	public VanillaCommands(DropletVanillaCommands instance) {
		plugin = instance;
	}

	@Command(aliases = {"starve"}, usage = "<player> <hunger level>", desc = "Sets the hunger level of the player.")
	@CommandPermissions("vanillacommands.starve")
	public void starve(CommandContext args, CommandSource source) {
		CM.starve(args, source);
	}

	@Command(aliases = {"hurt", "damage"}, usage = "<player> <health level>", desc = "Sets the health level of the player.")
	@CommandPermissions("vanillacommands.hurt")
	public void hurt(CommandContext args, CommandSource source) {
		CM.hurt(args, source);
	}

	@Command(aliases = {"ignite"}, usage = "<player>", desc = "Sets the player on fire.")
	@CommandPermissions("vanillacommands.ignite")
	public void ignite(CommandContext args, CommandSource source) {
		CM.ignite(args, source);
	}

	@Command(aliases = {"fall"}, usage = "<player>", desc = "Makes the player fall.")
	@CommandPermissions("vanillacommands.fall")
	public void fall(CommandContext args, CommandSource source) {
		CM.fall(args, source);
	}

	@Command(aliases = {"explode"}, usage = "<player>", desc = "Makes the player explode.")
	@CommandPermissions("vanillacommands.explode")
	public void explode(CommandContext args, CommandSource source) {
		CM.explode(args, source);
	}

	@Command(aliases = {"blind"}, usage = "<player>", desc = "Makes the player blind.")
	@CommandPermissions("vanillacommands.blind")
	public void blind(CommandContext args, CommandSource source) {
		CM.blind(args, source);
	}

	@Command(aliases = {"ill"}, usage = "<player>", desc = "Makes the player ill.")
	@CommandPermissions("vanillacommands.ill")
	public void ill(CommandContext args, CommandSource source) {
		CM.ill(args, source);
	}

	@Command(aliases = {"hungry"}, usage = "<player>", desc = "Makes the player hungry.")
	@CommandPermissions("vanillacommands.hungry")
	public void hungry(CommandContext args, CommandSource source) {
		CM.hungry(args, source);
	}
}
