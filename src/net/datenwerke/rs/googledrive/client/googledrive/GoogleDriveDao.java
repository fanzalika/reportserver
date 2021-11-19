package net.datenwerke.rs.googledrive.client.googledrive;

import java.util.List;
import java.util.Map;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.core.client.datasinkmanager.HasDefaultDatasink;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.googledrive.client.googledrive.dto.GoogleDriveDatasinkDto;
import net.datenwerke.rs.googledrive.client.googledrive.rpc.GoogleDriveRpcServiceAsync;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class GoogleDriveDao extends Dao implements HasDefaultDatasink {

   private final GoogleDriveRpcServiceAsync rpcService;

   @Inject
   public GoogleDriveDao(GoogleDriveRpcServiceAsync rpcService) {
      this.rpcService = rpcService;
   }

   public void exportIntoGoogleDrive(ReportDto reportDto, String executorToken,
         GoogleDriveDatasinkDto googleDriveDatasinkDto, String format, List<ReportExecutionConfigDto> configs,
         String name, String folder, boolean compressed, AsyncCallback<Void> callback) {
      rpcService.exportIntoGoogleDrive(reportDto, executorToken, googleDriveDatasinkDto, format, configs, name, folder, compressed,
            transformAndKeepCallback(callback));
   }
   
   public void exportFileIntoDatasink(FileServerFileDto file, DatasinkDefinitionDto datasinkDto, String name,
         String folder, AsyncCallback<Void> callback) {
      rpcService.exportFileIntoDatasink(file, datasinkDto, name, folder, callback);
   }

   public void getStorageEnabledConfigs(AsyncCallback<Map<StorageType, Boolean>> callback) {
      rpcService.getStorageEnabledConfigs(transformAndKeepCallback(callback));
   }

   public Request testGoogleDriveDatasink(GoogleDriveDatasinkDto googleDriveDatasinkDto,
         AsyncCallback<Boolean> callback) {
      return rpcService.testGoogleDriveDatasink(googleDriveDatasinkDto, transformAndKeepCallback(callback));
   }

   @Override
   public void getDefaultDatasink(AsyncCallback<DatasinkDefinitionDto> callback) {
      rpcService.getDefaultDatasink(transformAndKeepCallback(callback));
   }

}
