package net.datenwerke.rs.googledrive.service.googledrive.definitions.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.NullPointerException;
import java.lang.RuntimeException;
import java.lang.reflect.Field;
import java.util.Collection;
import javax.persistence.EntityManager;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.validator.DtoPropertyValidator;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.googledrive.client.googledrive.dto.GoogleDriveDatasinkDto;
import net.datenwerke.rs.googledrive.service.googledrive.definitions.GoogleDriveDatasink;
import net.datenwerke.rs.googledrive.service.googledrive.definitions.dtogen.Dto2GoogleDriveDatasinkGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for GoogleDriveDatasink
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2GoogleDriveDatasinkGenerator implements Dto2PosoGenerator<GoogleDriveDatasinkDto,GoogleDriveDatasink> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2GoogleDriveDatasinkGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		Provider<EntityManager> entityManagerProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.entityManagerProvider = entityManagerProvider;
		this.reflectionService = reflectionService;
	}

	public GoogleDriveDatasink loadPoso(GoogleDriveDatasinkDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		GoogleDriveDatasink poso = entityManager.find(GoogleDriveDatasink.class, id);
		return poso;
	}

	public GoogleDriveDatasink instantiatePoso()  {
		GoogleDriveDatasink poso = new GoogleDriveDatasink();
		return poso;
	}

	public GoogleDriveDatasink createPoso(GoogleDriveDatasinkDto dto)  throws ExpectedException {
		GoogleDriveDatasink poso = new GoogleDriveDatasink();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public GoogleDriveDatasink createUnmanagedPoso(GoogleDriveDatasinkDto dto)  throws ExpectedException {
		GoogleDriveDatasink poso = new GoogleDriveDatasink();

		/* store old id */
		if(null != dto.getId()){
			Field transientIdField = reflectionService.getFieldByAnnotation(poso, TransientID.class);
			if(null != transientIdField){
				transientIdField.setAccessible(true);
				try{
					transientIdField.set(poso, dto.getId());
				} catch(Exception e){
				}
			}
		}

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(GoogleDriveDatasinkDto dto, final GoogleDriveDatasink poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(GoogleDriveDatasinkDto dto, final GoogleDriveDatasink poso)  throws ExpectedException {
		/*  set appKey */
		poso.setAppKey(dto.getAppKey() );

		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set folder */
		poso.setFolder(dto.getFolder() );

		/*  set name */
		poso.setName(dto.getName() );

		/*  set refreshToken */
		poso.setRefreshToken(dto.getRefreshToken() );

		/*  set secretKey */
		poso.setSecretKey(dto.getSecretKey() );

	}

	protected void mergeProxy2Poso(GoogleDriveDatasinkDto dto, final GoogleDriveDatasink poso)  throws ExpectedException {
		/*  set appKey */
		if(dto.isAppKeyModified()){
			poso.setAppKey(dto.getAppKey() );
		}

		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set flags */
		if(dto.isFlagsModified()){
			try{
				poso.setFlags(dto.getFlags() );
			} catch(NullPointerException e){
			}
		}

		/*  set folder */
		if(dto.isFolderModified()){
			poso.setFolder(dto.getFolder() );
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set refreshToken */
		if(dto.isRefreshTokenModified()){
			poso.setRefreshToken(dto.getRefreshToken() );
		}

		/*  set secretKey */
		if(dto.isSecretKeyModified()){
			poso.setSecretKey(dto.getSecretKey() );
		}

	}

	public void mergeUnmanagedPoso(GoogleDriveDatasinkDto dto, final GoogleDriveDatasink poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(GoogleDriveDatasinkDto dto, final GoogleDriveDatasink poso)  throws ExpectedException {
		/*  set appKey */
		poso.setAppKey(dto.getAppKey() );

		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set folder */
		poso.setFolder(dto.getFolder() );

		/*  set name */
		poso.setName(dto.getName() );

		/*  set refreshToken */
		poso.setRefreshToken(dto.getRefreshToken() );

		/*  set secretKey */
		poso.setSecretKey(dto.getSecretKey() );

	}

	protected void mergeProxy2UnmanagedPoso(GoogleDriveDatasinkDto dto, final GoogleDriveDatasink poso)  throws ExpectedException {
		/*  set appKey */
		if(dto.isAppKeyModified()){
			poso.setAppKey(dto.getAppKey() );
		}

		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set flags */
		if(dto.isFlagsModified()){
			try{
				poso.setFlags(dto.getFlags() );
			} catch(NullPointerException e){
			}
		}

		/*  set folder */
		if(dto.isFolderModified()){
			poso.setFolder(dto.getFolder() );
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set refreshToken */
		if(dto.isRefreshTokenModified()){
			poso.setRefreshToken(dto.getRefreshToken() );
		}

		/*  set secretKey */
		if(dto.isSecretKeyModified()){
			poso.setSecretKey(dto.getSecretKey() );
		}

	}

	public GoogleDriveDatasink loadAndMergePoso(GoogleDriveDatasinkDto dto)  throws ExpectedException {
		GoogleDriveDatasink poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(GoogleDriveDatasinkDto dto, GoogleDriveDatasink poso)  {
	}


	public void postProcessCreateUnmanaged(GoogleDriveDatasinkDto dto, GoogleDriveDatasink poso)  {
	}


	public void postProcessLoad(GoogleDriveDatasinkDto dto, GoogleDriveDatasink poso)  {
	}


	public void postProcessMerge(GoogleDriveDatasinkDto dto, GoogleDriveDatasink poso)  {
	}


	public void postProcessInstantiate(GoogleDriveDatasink poso)  {
	}



}
