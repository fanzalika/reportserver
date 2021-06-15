package net.datenwerke.rs.ftp.client.ftp;

import java.util.List;
import java.util.Map;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.core.client.datasinkmanager.DatasinkDao;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.ftp.client.ftp.dto.SftpDatasinkDto;
import net.datenwerke.rs.ftp.client.ftp.rpc.SftpRpcServiceAsync;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class SftpDao extends Dao implements DatasinkDao {

   private final SftpRpcServiceAsync rpcService;

   @Inject
   public SftpDao(SftpRpcServiceAsync rpcService) {
      this.rpcService = rpcService;
   }

   public void exportIntoSftp(ReportDto reportDto, String executorToken, SftpDatasinkDto sftpDatasinkDto, String format,
         List<ReportExecutionConfigDto> configs, String name, String folder, AsyncCallback<Void> callback) {
      rpcService.exportIntoSftp(reportDto, executorToken, sftpDatasinkDto, format, configs, name, folder,
            transformAndKeepCallback(callback));
   }

   public void getStorageEnabledConfigs(AsyncCallback<Map<StorageType, Boolean>> callback) {
      rpcService.getStorageEnabledConfigs(transformAndKeepCallback(callback));
   }

   public Request testSftpDataSink(SftpDatasinkDto sftpDatasinkDto, AsyncCallback<Boolean> callback) {
      return rpcService.testSftpDatasink(sftpDatasinkDto, transformAndKeepCallback(callback));
   }

   @Override
   public void getDefaultDatasink(AsyncCallback<DatasinkDefinitionDto> callback) {
      rpcService.getDefaultDatasink(transformAndKeepCallback(callback));
   }

}