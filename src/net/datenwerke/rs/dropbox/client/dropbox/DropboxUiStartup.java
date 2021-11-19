package net.datenwerke.rs.dropbox.client.dropbox;

import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewToolbarConfiguratorHook;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.datasinkmanager.hooks.DatasinkDefinitionConfigProviderHook;
import net.datenwerke.rs.core.client.reportexporter.hooks.ExportExternalEntryProviderHook;
import net.datenwerke.rs.dropbox.client.dropbox.hookers.DropboxDatasinkConfigProviderHooker;
import net.datenwerke.rs.dropbox.client.dropbox.hookers.DropboxDatasinkOAuthToolbarConfigurator;
import net.datenwerke.rs.dropbox.client.dropbox.hookers.DropboxDatasinkTesterToolbarConfigurator;
import net.datenwerke.rs.dropbox.client.dropbox.hookers.DropboxExportSnippetProvider;
import net.datenwerke.rs.dropbox.client.dropbox.hookers.ExportToDropboxHooker;
import net.datenwerke.rs.dropbox.client.dropbox.hookers.FileExportToDropboxHooker;
import net.datenwerke.rs.fileserver.client.fileserver.provider.treehooks.FileExportExternalEntryProviderHook;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.scheduler.client.scheduler.hooks.ScheduleExportSnippetProviderHook;

public class DropboxUiStartup {
   @Inject
   public DropboxUiStartup(
         final Provider<ExportToDropboxHooker> exportToDropboxHooker,
         final Provider<FileExportToDropboxHooker> fileExportToDatasinkHooker,
         final HookHandlerService hookHandler, 
         final WaitOnEventUIService waitOnEventService, 
         final DropboxDao dao,
         final Provider<DropboxDatasinkConfigProviderHooker> dropboxTreeConfiguratorProvider,
         final DropboxDatasinkTesterToolbarConfigurator dropboxTestToolbarConfigurator,
         final DropboxDatasinkOAuthToolbarConfigurator dropboxOauthToolbarConfigurator,
         final Provider<DropboxExportSnippetProvider> dropboxExportSnippetProvider
         ) {
      /* config tree */
      hookHandler.attachHooker(DatasinkDefinitionConfigProviderHook.class, dropboxTreeConfiguratorProvider.get(),
            HookHandlerService.PRIORITY_MEDIUM);

      /* Send-to hookers */
      hookHandler.attachHooker(ExportExternalEntryProviderHook.class, exportToDropboxHooker,
            HookHandlerService.PRIORITY_LOW);
      hookHandler.attachHooker(FileExportExternalEntryProviderHook.class, fileExportToDatasinkHooker,
            HookHandlerService.PRIORITY_LOW);

      /* test datasinks */
      hookHandler.attachHooker(MainPanelViewToolbarConfiguratorHook.class, dropboxTestToolbarConfigurator,
            HookHandlerService.PRIORITY_HIGH);

      /* Oauth */
      hookHandler.attachHooker(MainPanelViewToolbarConfiguratorHook.class, dropboxOauthToolbarConfigurator);

      waitOnEventService.callbackOnEvent(LoginService.REPORTSERVER_EVENT_AFTER_ANY_LOGIN, ticket -> {
         waitOnEventService.signalProcessingDone(ticket);

         dao.getStorageEnabledConfigs(new RsAsyncCallback<Map<StorageType, Boolean>>() {
            @Override
            public void onSuccess(final Map<StorageType, Boolean> result) {
               if (result.get(StorageType.DROPBOX) && result.get(StorageType.DROPBOX_SCHEDULING))
                  hookHandler.attachHooker(ScheduleExportSnippetProviderHook.class, dropboxExportSnippetProvider,
                        HookHandlerService.PRIORITY_LOWER + 20);
               else
                  hookHandler.detachHooker(ScheduleExportSnippetProviderHook.class, dropboxExportSnippetProvider);
            }

            @Override
            public void onFailure(Throwable caught) {
               super.onFailure(caught);
            }
         });

      });

   }

}
