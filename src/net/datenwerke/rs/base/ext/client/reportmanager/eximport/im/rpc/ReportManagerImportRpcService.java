package net.datenwerke.rs.base.ext.client.reportmanager.eximport.im.rpc;

import java.util.List;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("reportmanager_import")
public interface ReportManagerImportRpcService extends RemoteService {

	public List<ImportTreeModel> loadTree() throws ServerCallFailedException;
	
}