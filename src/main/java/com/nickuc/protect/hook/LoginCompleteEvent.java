/**
 * Copyright NickUC
 * -
 * Esta class pertence ao projeto de NickUC
 * Discord: NickUltracraft#4550
 * Mais informações: https://nickuc.com
 * -
 * É expressamente proibido alterar o nome do proprietário do código, sem
 * expressar e deixar claramente o link para acesso da source original.
 * -
 * Este aviso não pode ser removido ou alterado de qualquer distribuição de origem.
 */

package com.nickuc.protect.hook;

import com.nickuc.ncore.api.plugin.bukkit.events.*;
import lombok.*;
import org.bukkit.entity.*;

@AllArgsConstructor @Getter
public final class LoginCompleteEvent extends PluginEvent {

	private final Player player;

}
