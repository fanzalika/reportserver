package net.datenwerke.rs.amazons3.client.amazons3.ui;

import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCPasswordField;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.datasinkmanager.locale.DatasinksMessages;
import net.datenwerke.rs.amazons3.client.amazons3.dto.AmazonS3DatasinkDto;
import net.datenwerke.rs.amazons3.client.amazons3.dto.pa.AmazonS3DatasinkDtoPA;

public class AmazonS3DatasinkForm extends SimpleFormView {

   @Override
   protected void configureSimpleForm(SimpleForm form) {
      /* configure form */
      form.setHeading(DatasinksMessages.INSTANCE.editDatasink()
            + (getSelectedNode() == null ? "" : " (" + getSelectedNode().getId() + ")"));

      /* name */
      form.addField(String.class, AmazonS3DatasinkDtoPA.INSTANCE.name(), BaseMessages.INSTANCE.name());

      /* description */
      form.addField(String.class, AmazonS3DatasinkDtoPA.INSTANCE.description(), BaseMessages.INSTANCE.description(),
            new SFFCTextAreaImpl());

      form.setFieldWidth(750);

      /* folder */
      form.addField(String.class, AmazonS3DatasinkDtoPA.INSTANCE.folder(), BaseMessages.INSTANCE.folder());

      /* app key */
      form.addField(String.class, AmazonS3DatasinkDtoPA.INSTANCE.appKey(), BaseMessages.INSTANCE.appKey());

      /* secret key */
      String secretKey = form.addField(String.class, AmazonS3DatasinkDtoPA.INSTANCE.secretKey(), BaseMessages.INSTANCE.secretKey(),
            new SFFCPasswordField() {
               @Override
               public Boolean isPasswordSet() {
                  return ((AmazonS3DatasinkDto) getSelectedNode()).isHasSecretKey();
               }
            }); // $NON-NLS-1$
      Menu clearPwMenu = new DwMenu();
      MenuItem clearPwItem = new DwMenuItem(BaseMessages.INSTANCE.clearPassword());
      clearPwMenu.add(clearPwItem);
      clearPwItem.addSelectionHandler(event -> ((AmazonS3DatasinkDto) getSelectedNode()).setAppKey(null));
      form.addFieldMenu(secretKey, clearPwMenu);
      
      /* bucket name */
      form.addField(String.class, AmazonS3DatasinkDtoPA.INSTANCE.bucketName(), BaseMessages.INSTANCE.amazonS3BucketName());
      
      /* region */
      form.addField(String.class, AmazonS3DatasinkDtoPA.INSTANCE.regionName(), BaseMessages.INSTANCE.amazonS3Region());

   }
   
   

}