package me.NickUltracraft.Protect.Cache;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import me.NickUltracraft.Protect.Console;
import me.NickUltracraft.Protect.Main;
import me.NickUltracraft.Protect.Console.ConsoleLevel;

/**
 * A class Messages.java do projeto (PLUGIN - nProtect Rebuilt) pertence ao NickUltracraft
 * Discord: NickUltracraft#4550
 * Mais informa��es: https://nickuc.tk 
 *
 * Rebuild, do not copy
*/

public class Messages {
	
	public static HashMap<String, String> messagesMap = new HashMap<>(); 
 	
	public static Messages getInstance() {
		return new Messages();
	}
	public void loadMessages() {
		messagesMap.clear();
		try {
			for(String messageToAdd : Main.m.getConfig().getConfigurationSection("Mensagens").getKeys(false)) {
				if(Main.m.getConfig().isSet("Mensagens." + messageToAdd)) {
					add(messageToAdd, loadFromConfig(messageToAdd));
				}
			}
			addMissingMessages();
		} catch (Exception e) {
			new Console("Falha ao carregar as mensagens. Usando valores default.", ConsoleLevel.ERRO).sendMessage();
			addMissingMessages();
		}
	}
	private void addMissingMessages() {
		add("argumentos_invalidos", "&cVoc� deve usar: /loginstaff <sua senha>");
		add("argumentos_invalidos2", "&cVoc� deve usar: /mudarsenhastaff <nova senha>");
		add("nao_staffer", "&cVoc� deve ser um staffer para executar este comando.");
		add("ja_autenticado", "&cVoc� j� est� autenticado como staffer.");
		add("senha_incorreta", "&cA senha inserida est� incorreta.");
		add("autenticou_sucesso", "&aVoc� se autenticou com sucesso como staffer.");
		add("demorou_logar", "&cVoc� demorou muito para se autenticar como staffer");
		add("mudousenha_sucesso", "&aVoc� alterou a sua senha com sucesso.");
		add("mensagem_logar", "&ePara se autenticar como staffer, utilize /loginstaff <sua senha>");
		add("loginstaff_title", "&e&lLOGIN STAFF");
		add("logou_subtitle", "&eVoc� logou com sucesso!");
		add("mudousenha_subtitle", "&eVoc� alterou sua senha.");
		add("logar_subtitle", "Se autentique usando /loginstaff <senha>");
	}
	private void add(String id, String valor) {
		if(!messagesMap.containsKey(id)) messagesMap.put(id, valor);
	}
	public String loadFromConfig(String path) {
		FileConfiguration config = Main.m.getConfig(); return config.getString("Mensagens." + path);
	}
	public String getCachedMessage(String selectValue) {
		return ChatColor.translateAlternateColorCodes('&', messagesMap.get(selectValue));
	}

}
