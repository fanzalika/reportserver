package net.datenwerke.rs.box.client.box.hookers;

import java.util.Map;
import java.util.Optional;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.rs.box.client.box.BoxUiModule;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerUiService;
import net.datenwerke.rs.fileserver.client.fileserver.hooks.DatasinkSendToFormConfiguratorHook;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class BoxSendToFormConfiguratorHooker implements DatasinkSendToFormConfiguratorHook {

   @Inject
   public BoxSendToFormConfiguratorHooker(Provider<LoginService> loginServiceProvider) {
   }

   @Override
   public boolean consumes(Class<? extends DatasinkDefinitionDto> datasinkType) {
      return BoxUiModule.TYPE.equals(datasinkType);
   }

   @Override
   public void installAdditionalFields(SimpleForm form) {
   }

   @Override
   public Optional<Map<String, Object>> getAdditionalFieldsValues(SimpleForm form) {
      return Optional.empty();
   }

   @Override
   public String getWindowTitle() {
      return BoxUiModule.NAME;
   }

   @Override
   public BaseIcon getIcon() {
      return BoxUiModule.ICON;
   }

   @Override
   public int getWindowHeight() {
      return FileServerUiService.DEFAULT_FILE_SEND_TO_WINDOW_HEIGHT;
   }

   @Override
   public boolean isFolderedDatasink() {
      return true;
   }
   
   @Override
   public boolean isCanCompress() {
      return true;
   }

}