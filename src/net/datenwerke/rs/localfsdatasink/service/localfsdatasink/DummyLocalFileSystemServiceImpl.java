package net.datenwerke.rs.localfsdatasink.service.localfsdatasink;

import java.util.Optional;

import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkConfiguration;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.core.service.datasinkmanager.exceptions.DatasinkExportException;
import net.datenwerke.rs.localfsdatasink.service.localfsdatasink.definitions.LocalFileSystemDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class DummyLocalFileSystemServiceImpl implements LocalFileSystemService {


   @Override
   public Optional<LocalFileSystemDatasink> getDefaultDatasink() {
      return null;
   }

   @Override
   public String getDatasinkPropertyName() {
      return null;
   }

   @Override
   public StorageType getStorageType() {
      return null;
   }

   @Override
   public StorageType getSchedulingStorageType() {
      return null;
   }

   @Override
   public void doExportIntoDatasink(Object report, DatasinkDefinition datasinkDefinition, DatasinkConfiguration config)
         throws DatasinkExportException {
   }

}