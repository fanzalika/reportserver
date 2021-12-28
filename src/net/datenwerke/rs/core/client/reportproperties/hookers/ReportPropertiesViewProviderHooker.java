package net.datenwerke.rs.core.client.reportproperties.hookers;

import java.util.Arrays;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewProviderHook;
import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportproperties.ui.ReportPropertiesView;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

/**
 * 
 *
 */
public class ReportPropertiesViewProviderHooker implements MainPanelViewProviderHook {

   private final Provider<ReportPropertiesView> propertiesViewProvider;

   @Inject
   public ReportPropertiesViewProviderHooker(Provider<ReportPropertiesView> propertiesViewProvider) {

      /* store objects */
      this.propertiesViewProvider = propertiesViewProvider;
   }

   public List<MainPanelView> mainPanelViewProviderHook_getView(AbstractNodeDto node) {
      if (node instanceof ReportDto)
         return getViewForReport();

      return null;
   }

   private List<MainPanelView> getViewForReport() {
      return Arrays.asList(new MainPanelView[] { propertiesViewProvider.get() });
   }

}
