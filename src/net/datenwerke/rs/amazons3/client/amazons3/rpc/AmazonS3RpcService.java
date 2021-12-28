package net.datenwerke.rs.amazons3.client.amazons3.rpc;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.amazons3.client.amazons3.dto.AmazonS3DatasinkDto;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

@RemoteServiceRelativePath("amazons3")
public interface AmazonS3RpcService extends RemoteService {

   void exportReportIntoDatasink(ReportDto reportDto, String executorToken, DatasinkDefinitionDto datasinkDto,
         String format, List<ReportExecutionConfigDto> configs, String name, String folder, boolean compressed)
         throws ServerCallFailedException;

   Map<StorageType, Boolean> getStorageEnabledConfigs() throws ServerCallFailedException;

   boolean testAmazonS3Datasink(AmazonS3DatasinkDto amazonS3DatasinkDto) throws ServerCallFailedException;

   DatasinkDefinitionDto getDefaultDatasink() throws ServerCallFailedException;

   void exportFileIntoDatasink(AbstractFileServerNodeDto abstractNodeDto, DatasinkDefinitionDto datasinkDto,
         String filename, String folder, boolean compressed) throws ServerCallFailedException;
}
