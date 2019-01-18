package me.NickUltracraft.Protect.Cache;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import me.NickUltracraft.Protect.Main;

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
		messagesMap.put("argumentos_invalidos", "&cVoc� deve usar: /loginstaff <sua senha>");
		messagesMap.put("nao_staffer", "&cVoc� deve ser um staffer para executar este comando.");
		messagesMap.put("ja_autenticado", "&cVoc� j� est� autenticado como staffer.");
		messagesMap.put("senha_incorreta", "&cA senha inserida est� incorreta.");
		messagesMap.put("autenticou_sucesso", "&aVoc� se autenticou com sucesso como staffer.");
		messagesMap.put("demorou_logar", "&cVoc� demorou muito para se autenticar como staffer");
		messagesMap.put("mudousenha_sucesso", "&aVoc� alterou a sua senha com sucesso.");
	}
	public String loadFromConfig(String path) {
		FileConfiguration config = Main.m.getConfig(); return config.getString("Mensagens." + path);
	}
	public String getCachedMessage(String selectValue) {
		return ChatColor.translateAlternateColorCodes('&', messagesMap.get(selectValue));
	}

}
