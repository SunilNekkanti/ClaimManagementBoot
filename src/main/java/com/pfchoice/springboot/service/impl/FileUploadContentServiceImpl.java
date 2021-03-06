package com.pfchoice.springboot.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;

import com.pfchoice.springboot.configuration.ConfigProperties;
import com.pfchoice.springboot.controller.FileUploadContentController;
import com.pfchoice.springboot.model.FileUploadContent;
import com.pfchoice.springboot.repositories.FileUploadContentRepository;
import com.pfchoice.springboot.service.FileService;
import com.pfchoice.springboot.service.FileTypeService;
import com.pfchoice.springboot.service.FileUploadContentService;
import com.pfchoice.springboot.util.PrasUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("fileUploadContentService")
@Transactional
public class FileUploadContentServiceImpl implements FileUploadContentService {

	public static final Logger logger = LoggerFactory.getLogger(FileUploadContentController.class);

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	ConfigProperties configProperties;

	@Autowired
	PrasUtil prasUtil;

	@Autowired
	private FileUploadContentRepository fileUploadContentRepository;

	@Autowired
	FileService fileService;

	@Autowired
	FileTypeService fileTypeService;

	public FileUploadContent findById(Integer id) {
		return fileUploadContentRepository.findOne(id);
	}

	public FileUploadContent findByFileName(String fileName) {
		return fileUploadContentRepository.findByFileName(fileName);
	}

	public void saveFileUploadContent(FileUploadContent fileUploadContent) {
		fileUploadContentRepository.save(fileUploadContent);
	}

	public void updateFileUploadContent(FileUploadContent fileUploadContent) {
		saveFileUploadContent(fileUploadContent);
	}

	public void deleteFileUploadContentById(Integer id) {
		fileUploadContentRepository.delete(id);
	}

	public void deleteAllFileUploadContents() {
		fileUploadContentRepository.deleteAll();
	}

	public List<FileUploadContent> findAllFileUploadContents() {
		return fileUploadContentRepository.findAll();
	}

	public boolean isFileUploadContentExists(FileUploadContent fileUploadContent) {
		return findByFileName(fileUploadContent.getFileName()) != null;
	}
	
	@Async
	public Future<?> asyncMbrRosterOrCapFileUploadProcessing(final String username, final Integer insId, final Integer fileTypeId,
			final Integer activityMonth, final Integer reportMonth, final String fileName) throws IOException, InterruptedException {
	/*	Hashtable<String, Object> params = new Hashtable<>();

		final FileType fileType = fileTypeService.findById(fileTypeId);
		final String tableName = fileType.getTablesName();
		final String fileTypeDescription = fileType.getDescription();
		final String entityClassName = fileType.getEntityClassName();
		final String insuranceCode = (fileType.getInsuranceCode() != null)?fileType.getInsuranceCode():"";
		final String queryTypeLoad = configProperties.getQueryTypeLoad();
		final String queryTypeInsert = configProperties.getQueryTypeInsert();

		logger.info("tableName " + tableName);
		String sqlQuery = "select BigToInt(count(*)> 0) from " + tableName;
		logger.info("sqlQuery " + sqlQuery);
		Integer dataExists = prasUtil.executeSqlScript(sqlQuery, params, true);
		logger.info("dataExists " + dataExists);

		if (dataExists > 0) {
			logger.info("Previous file processing is incomplete ");
			CustomErrorType errorMessage = new CustomErrorType("Previous file processing is incomplete");

			return new AsyncResult<ResponseEntity<CustomErrorType>>(
					new ResponseEntity<CustomErrorType>(errorMessage, HttpStatus.NO_CONTENT));
		} else {
			Integer fileId = 0;
			try {
				File fileRecord = new File();
				fileRecord.setFileName(fileName);
				fileRecord.setFileType(fileType);
				fileRecord.setCreatedBy(username);
				fileRecord.setUpdatedBy(username);
				fileService.saveFile(fileRecord);

				fileId = fileRecord.getId();

			} catch (Exception e) {
				logger.warn(e.getCause().getMessage());
				logger.info("Similar file already processed in past");
				CustomErrorType errorMessage = new CustomErrorType("Similar file already processed in past");
				return new AsyncResult<ResponseEntity<CustomErrorType>>(
						new ResponseEntity<CustomErrorType>(errorMessage, HttpStatus.NO_CONTENT));
			}
			
			Resource loadFromCSVTable = getResource(
					"classpath:static/sql/" + entityClassName  +insuranceCode+ queryTypeLoad + configProperties.getSqlQueryExtn());
			sqlQuery = StreamUtils.copyToString(loadFromCSVTable.getInputStream(), StandardCharsets.UTF_8);

			params.clear();
			params.put("file", fileName);
			logger.info("sqlQuery script: "+sqlQuery);
			Integer loadedData = prasUtil.executeSqlScript(sqlQuery, params, false);
			if (loadedData < 0) {
				CustomErrorType errorMessage = new CustomErrorType("ZERO records to process");
				return new AsyncResult<ResponseEntity<CustomErrorType>>(
						new ResponseEntity<CustomErrorType>(errorMessage, HttpStatus.NO_CONTENT));
			}

				logger.info("loaded  " + loadedData + "  records into table " + tableName);

			logger.info("processing " + fileTypeDescription + " data" + new Date());

			params.clear();
			params.put("insId", insId);
			params.put("fileId", fileId);
			params.put("activityMonth", activityMonth);
			params.put("username", username);
			Resource insertIntoTable = getResource(
					"classpath:static/sql/" + entityClassName +insuranceCode+ queryTypeInsert + configProperties.getSqlQueryExtn());
			sqlQuery = StreamUtils.copyToString(insertIntoTable.getInputStream(), StandardCharsets.UTF_8);

			Integer noOfRecordsLoaded = prasUtil.executeSqlScript(sqlQuery, params, false);
			logger.info("insertedData " + noOfRecordsLoaded + " records into " + entityClassName);

			params.clear();
			sqlQuery = String.format("truncate table %s ", tableName);
			prasUtil.executeSqlScript(sqlQuery, params, false);
			logger.info("cleared CSV Table " + tableName); 

			logger.info("processed " + fileTypeDescription + " data " + new Date());*/
			return new AsyncResult<ResponseEntity<Object>>(new ResponseEntity<Object>(HttpStatus.OK));
		//}
	}
 
	
	
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	public Resource getResource(String location) {
		return resourceLoader.getResource(location);
	}

	
}
