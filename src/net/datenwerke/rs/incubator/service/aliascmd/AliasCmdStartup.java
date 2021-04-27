package net.datenwerke.rs.incubator.service.aliascmd;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.configservice.service.configservice.hooks.ReloadConfigNotificationHook;
import net.datenwerke.rs.incubator.service.aliascmd.terminal.commands.AliasCommand;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;

import com.google.inject.Inject;

public class AliasCmdStartup {

	@Inject
	public AliasCmdStartup(
		HookHandlerService hookHandler,
		
		AliasCommand aliasCommandProvider
		) {
		
		hookHandler.attachHooker(TerminalCommandHook.class, aliasCommandProvider, HookHandlerService.PRIORITY_LOWER);
		hookHandler.attachHooker(ReloadConfigNotificationHook.class, aliasCommandProvider, HookHandlerService.PRIORITY_LOWER);
	}
}