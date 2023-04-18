package net.datenwerke.rs.remoteserver.client.remoteservermanager.eximport.im.dto;

import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.AbstractRemoteServerManagerNodeDto;
import net.datenwerke.treedb.ext.client.eximport.im.dto.TreeImportConfigDto;

public class RemoteServerManagerImportConfigDto extends TreeImportConfigDto<AbstractRemoteServerManagerNodeDto> {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   
   private boolean removeKey;

   public boolean isRemoveKey() {
      return removeKey;
   }

   public void setRemoveKey(boolean removekey) {
      this.removeKey = removekey;
   }

}