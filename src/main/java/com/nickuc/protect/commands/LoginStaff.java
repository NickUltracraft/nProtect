/**
 * Copyright NickUC
 * -
 * Esta class pertence ao projeto de NickUC
 * Mais informações: https://nickuc.com
 * -
 * É expressamente proibido alterar o nome do proprietário do código, sem
 * expressar e deixar claramente o link para acesso da source original.
 * -
 * Este aviso não pode ser removido ou alterado de qualquer distribuição de origem.
 */

package com.nickuc.protect.commands;

import com.nickuc.ncore.api.logger.*;
import com.nickuc.ncore.api.plugin.shared.command.*;
import com.nickuc.ncore.api.plugin.shared.sender.*;
import com.nickuc.ncore.api.settings.*;
import com.nickuc.protect.events.*;
import com.nickuc.protect.management.*;
import com.nickuc.protect.*;
import com.nickuc.protect.model.*;
import org.bukkit.entity.*;

public final class LoginStaff extends SharedCommand<nProtect> {

	public LoginStaff() {
		super("loginstaff");
		setDescription("Comando para se autenticar como staffer");
	}

	@Override
	public void execute(SharedSender sender, String lb, String[] args) throws Exception {
		if (!(sender instanceof SharedPlayer)) {
			sender.sendMessage("§cDesculpe, mas este comando está indisponível para o console.");
			return;
		}
		Player player = sender.getSender();
		if(args.length != 1) {
			player.sendMessage(Messages.getMessage(MessagesEnum.INVALID_ARGS));
			return;
		}
		Account account = new Account(plugin, player);
		if(!account.isStaffer()) {
			player.sendMessage(Messages.getMessage(MessagesEnum.NAO_STAFFER));
			return;
		}
		if(PlayerCache.isAuthenticated(player)) {
			player.sendMessage(Messages.getMessage(MessagesEnum.JA_AUTENTICADO));
			return;
		}
		String password = args[0];
		if(!account.getGrupo().getPassword().equals(password)) {
			if (new PlayerWrongLoginStaffEvent(player.getName(), password).callEvt()) {
				ConsoleLogger.warning("O jogador " + player.getName() + " " + player.getAddress().getHostString() + " inseriu uma senha incorreta para o loginstaff.");
				player.kickPlayer(Messages.getMessage(MessagesEnum.INCORRECT_PASS));
			}
			return;
		}
		ConsoleLogger.debug("Login efetuado para " + player.getName() + " para " + account.toString() + ".");
		account.forceLogin(player);
	}

}