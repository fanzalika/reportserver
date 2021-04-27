package net.datenwerke.rs.localfsdatasink.client.localfsdatasink.hookers;

import static net.datenwerke.rs.core.client.datasinkmanager.helper.forms.simpleform.DatasinkSimpleFormProvider.extractSingleTreeSelectionField;

import java.util.Collection;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.selection.SingleTreeSelectionField;
import net.datenwerke.gf.client.treedb.simpleform.SFFCGenericTreeNode;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.actions.ShowHideFieldAction;
import net.datenwerke.gxtdto.client.forms.simpleform.conditions.FieldEquals;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCAllowBlank;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCBoolean;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.datasinkmanager.DatasinkTreeManagerDao;
import net.datenwerke.rs.core.client.datasinkmanager.helper.forms.DatasinkSelectionField;
import net.datenwerke.rs.core.client.datasinkmanager.locale.DatasinksMessages;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewConfiguration;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.dto.LocalFileSystemDatasinkDto;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.dto.ScheduleAsLocalFileSystemInformation;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.provider.annotations.DatasinkTreeLocalFileSystem;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.locale.ScheduleAsFileMessages;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.client.scheduler.hooks.ScheduleExportSnippetProviderHook;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereport.pages.JobMetadataConfigurationForm;

public class LocalFileSystemExportSnippetProvider implements
ScheduleExportSnippetProviderHook {
   
   private String isExportAsFileKey;
   private String folderKey;
   private String nameKey;
   private String localFileSystemKey;
   
   private final Provider<UITree> treeProvider;
   private final DatasinkTreeManagerDao datasinkTreeManager;
   
   @Inject
   public LocalFileSystemExportSnippetProvider(
         @DatasinkTreeLocalFileSystem Provider<UITree> treeProvider,
         DatasinkTreeManagerDao datasinkTreeManager
         ) {
      this.treeProvider = treeProvider;
      this.datasinkTreeManager = datasinkTreeManager;
   }

   @Override
   public void configureSimpleForm(SimpleForm xform, ReportDto report, Collection<ReportViewConfiguration> configs) {
      xform.setLabelAlign(LabelAlign.LEFT);
      isExportAsFileKey = xform.addField(Boolean.class, "", new SFFCBoolean() {
         @Override
         public String getBoxLabel() {
            return DatasinksMessages.INSTANCE.localFileSystem();
         }
      });
      xform.setLabelAlign(LabelAlign.TOP);
      
      xform.setFieldWidth(260);
      xform.beginFloatRow();
      
      localFileSystemKey = xform.addField(DatasinkSelectionField.class, DatasinksMessages.INSTANCE.localFileSystem(), new SFFCGenericTreeNode() {
         @Override
         public UITree getTreeForPopup() {
            return treeProvider.get();
         }
      }, new SFFCAllowBlank() {
         @Override
         public boolean allowBlank() {
            return false;
         }
      });
      
      folderKey = xform.addField(String.class, ScheduleAsFileMessages.INSTANCE.folder(), new SFFCAllowBlank() {
         @Override
         public boolean allowBlank() {
            return false;
         }
      });
      
      xform.endRow();
      xform.setFieldWidth(530);
      
      nameKey = xform.addField(String.class, BaseMessages.INSTANCE.propertyName(), new SFFCAllowBlank() {
         @Override
         public boolean allowBlank() {
            return false;
         }
      });
      
      xform.addCondition(isExportAsFileKey, new FieldEquals(true), new ShowHideFieldAction(folderKey));
      xform.addCondition(isExportAsFileKey, new FieldEquals(true), new ShowHideFieldAction(nameKey));
      xform.addCondition(isExportAsFileKey, new FieldEquals(true), new ShowHideFieldAction(localFileSystemKey));
      
   }

   @Override
   public boolean isValid(SimpleForm simpleForm) {
      simpleForm.clearInvalid();
      return simpleForm.isValid();
   }

   @Override
   public void configureConfig(ReportScheduleDefinition configDto, SimpleForm simpleForm) {
      if(! isActive(simpleForm))
         return;

      ScheduleAsLocalFileSystemInformation info = new ScheduleAsLocalFileSystemInformation();
      info.setName((String) simpleForm.getValue(nameKey));
      info.setFolder((String) simpleForm.getValue(folderKey));
      info.setLocalFileSystemDatasinkDto((LocalFileSystemDatasinkDto) simpleForm.getValue(localFileSystemKey));
      
      configDto.addAdditionalInfo(info);
      
   }

   @Override
   public boolean isActive(SimpleForm simpleForm) {
      return (Boolean) simpleForm.getValue(isExportAsFileKey); 
   }

   @Override
   public void loadFields(SimpleForm form, ReportScheduleDefinition definition, ReportDto report) {
      form.loadFields();
      
      final SingleTreeSelectionField localFileSystemField = extractSingleTreeSelectionField(form.getField(localFileSystemKey));
      
      if (null != definition) {
         form.setValue(nameKey, "${now} - " + definition.getTitle());
         ScheduleAsLocalFileSystemInformation info = definition.getAdditionalInfo(ScheduleAsLocalFileSystemInformation.class);
         if(null != info){
            form.setValue(isExportAsFileKey, true);
            form.setValue(nameKey, info.getName());
            form.setValue(folderKey, info.getFolder());
            localFileSystemField.setValue(info.getLocalFileSystemDatasinkDto());
         } 
      }
      
      localFileSystemField.addValueChangeHandler(event -> {
         if (null == event.getValue()) 
            return;
         
         datasinkTreeManager.loadFullViewNode((LocalFileSystemDatasinkDto)event.getValue(), new RsAsyncCallback<LocalFileSystemDatasinkDto>() {
            @Override
            public void onSuccess(LocalFileSystemDatasinkDto result) {
               form.setValue(folderKey, result.getFolder());
            }
            
            @Override
            public void onFailure(Throwable caught) {
               super.onFailure(caught);
            }
         });
         
      });
      
   }

   @Override
   public void onWizardPageChange(int pageNr, Widget page, SimpleForm form, ReportScheduleDefinition definition,
         ReportDto report) {
      if (! (page instanceof JobMetadataConfigurationForm))
         return;
      
      JobMetadataConfigurationForm metadataForm = (JobMetadataConfigurationForm) page;
      
      String jobTitle = metadataForm.getTitleValue();
      
      form.setValue(nameKey, "${now} - " + jobTitle);
      if (null != definition) {
         ScheduleAsLocalFileSystemInformation info = definition.getAdditionalInfo(ScheduleAsLocalFileSystemInformation.class);
         if(null != info)
            form.setValue(nameKey, info.getName());
      }
      
   }

   @Override
   public boolean appliesFor(ReportDto report, Collection<ReportViewConfiguration> configs) {
      return true;
   }

}