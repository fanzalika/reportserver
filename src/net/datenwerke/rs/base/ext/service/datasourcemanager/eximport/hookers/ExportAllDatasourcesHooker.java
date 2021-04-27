package net.datenwerke.rs.base.ext.service.datasourcemanager.eximport.hookers;

import net.datenwerke.eximport.ex.ExportConfig;
import net.datenwerke.rs.core.service.datasourcemanager.DatasourceService;
import net.datenwerke.rs.eximport.service.eximport.hooks.ExportAllHook;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeExImportOptions;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeExportItemConfig;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeExporterConfig;
import net.datenwerke.treedb.service.treedb.AbstractNode;

import com.google.inject.Inject;

public class ExportAllDatasourcesHooker implements ExportAllHook {

	private final DatasourceService dsService;
	
	@Inject
	public ExportAllDatasourcesHooker(DatasourceService dsService) {
		this.dsService = dsService;
	}

	@Override
	public void configure(ExportConfig config) {
		TreeNodeExporterConfig specConfig = new TreeNodeExporterConfig();
		specConfig.addExImporterOptions(TreeNodeExImportOptions.INCLUDE_OWNER, TreeNodeExImportOptions.INCLUDE_SECURITY);
		config.addSpecificExporterConfigs(specConfig);
		
		for(AbstractNode<?> node: dsService.getAllNodes())
			config.addItemConfig(new TreeNodeExportItemConfig(node));
	}

}