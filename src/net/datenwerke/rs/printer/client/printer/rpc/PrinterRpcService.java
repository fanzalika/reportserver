package net.datenwerke.rs.printer.client.printer.rpc;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.printer.client.printer.dto.PrinterDatasinkDto;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

@RemoteServiceRelativePath("printer")
public interface PrinterRpcService extends RemoteService {

   void exportReportIntoDatasink(ReportDto reportDto, String executorToken, DatasinkDefinitionDto datasinkDto,
         String format, List<ReportExecutionConfigDto> configs, String name, boolean compressed)
         throws ServerCallFailedException;

   Map<StorageType, Boolean> getStorageEnabledConfigs() throws ServerCallFailedException;

   boolean testPrinterDatasink(PrinterDatasinkDto amazonS3DatasinkDto) throws ServerCallFailedException;

   DatasinkDefinitionDto getDefaultDatasink() throws ServerCallFailedException;

   void exportFileIntoDatasink(AbstractFileServerNodeDto abstractNodeDto, DatasinkDefinitionDto datasinkDto,
         String filename, boolean compressed) throws ServerCallFailedException;
   
   List<String> getAvailablePrinters() throws ServerCallFailedException;
}