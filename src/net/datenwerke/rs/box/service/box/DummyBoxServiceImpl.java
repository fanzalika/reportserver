package net.datenwerke.rs.box.service.box;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import net.datenwerke.rs.box.service.box.definitions.BoxDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class DummyBoxServiceImpl implements BoxService {

   @Override
   public void exportIntoDatasink(Object report, BoxDatasink boxDatasink, String filename, String folder)
         throws IOException {

   }

   @Override
   public Map<StorageType, Boolean> getEnabledConfigs() {
      return Collections.emptyMap();
   }

   @Override
   public boolean isEnabled() {
      return false;
   }

   @Override
   public boolean isSchedulingEnabled() {
      return false;
   }

   @Override
   public void testDatasink(BoxDatasink boxDatasink) throws IOException {
   }

   @Override
   public Optional<BoxDatasink> getDefaultDatasink() {
      return null;
   }

}