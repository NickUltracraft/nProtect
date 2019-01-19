package me.NickUltracraft.Protect.Comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.NickUltracraft.Protect.API.PwManager;
import me.NickUltracraft.Protect.Cache.Arrays;
import me.NickUltracraft.Protect.Cache.Conta;
import me.NickUltracraft.Protect.Cache.Messages;
import me.NickUltracraft.Protect.Cache.Settings;
import me.NickUltracraft.Protect.Events.PlayerLoginStaffEvent;
import me.NickUltracraft.Protect.Events.PlayerWrongLoginStaffEvent;

/**
 * A class LoginStaff.java do projeto (PLUGIN - nProtect Rebuilt) pertence ao NickUltracraft
 * Discord: NickUltracraft#4550
 * Mais informa��es: https://nickuc.tk 
 *
 * Rebuild, do not copy
*/

public final class LoginStaff implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			int lenght = args.length;
			if(lenght != 1) {
				p.sendMessage(Messages.getInstance().getCachedMessage("argumentos_invalidos"));
				return true;
			} 
			Conta account = new Conta(p.getName());
			if(!account.isStaffer()) {
				p.sendMessage(Messages.getInstance().getCachedMessage("nao_staffer"));
				return true;
			}
			String password = args[0];
			if(Arrays.getInstance().estaLogado(p)) {
				p.sendMessage(Messages.getInstance().getCachedMessage("ja_autenticado"));
				return true;
			}
			if(!new PwManager(password).comparatePassword(account.getSenha(), account.getSalt())) {
				Bukkit.getPluginManager().callEvent(new PlayerWrongLoginStaffEvent(p.getName(), password));
				p.kickPlayer(Messages.getInstance().getCachedMessage("senha_incorreta"));
				return true;
			}
			Arrays.getInstance().adicionarLogados(p.getName());
			p.sendMessage(Messages.getInstance().getCachedMessage("autenticou_sucesso"));
			if(Settings.getInstance().getCachedSetting("usar_title")) {
				p.sendTitle("�e�lLOGIN STAFF", "�eVoc� logou com sucesso!");
			}
			p.setWalkSpeed((float)0.2);
			p.setFlySpeed((float)0.2);
			Bukkit.getPluginManager().callEvent(new PlayerLoginStaffEvent(p, password));
		}
		return false;
	}

}
