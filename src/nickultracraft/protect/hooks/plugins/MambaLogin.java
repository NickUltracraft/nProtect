package nickultracraft.protect.hooks.plugins;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import nickultracraft.protect.hooks.LoginAbstract;
import rush.login.events.PlayerAuthLoginEvent;
import rush.login.events.PlayerAuthRegisterEvent;

/**
 * A class MambaLogin.java da package (nickultracraft.protect.hooks.plugins) pertence ao NickUltracraft
 * Discord: NickUltracraft#4550
 * Mais informações: https://nickuc.tk 
 *
 * É expressamente proibído alterar o nome do proprietário do código, sem
 * expressar e deixar claramente o link do download/source original.
*/

public class MambaLogin extends LoginAbstract implements Listener {
	
	public MambaLogin() {
		super("MambaLogin", "Unknow");
	}
	@EventHandler
	public void onLogar(PlayerAuthLoginEvent e) {
		callLogin(e.getPlayer());
	}
	@EventHandler
	public void onRegistrar(PlayerAuthRegisterEvent e) {
		callLogin(e.getPlayer());
	}
}
