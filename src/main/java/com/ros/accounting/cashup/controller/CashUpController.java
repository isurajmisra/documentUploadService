package com.ros.accounting.cashup.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.security.RolesAllowed;

import com.ros.accounting.cashup.service.DocumentUploadService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ros.accounting.cashup.controller.dto.BankingInfoDto;
import com.ros.accounting.cashup.controller.dto.CashUpDto;
import com.ros.accounting.cashup.exceptions.cashUpNotFoundException;
import com.ros.accounting.cashup.model.CashUpStatus;
import com.ros.accounting.cashup.service.CashUpService;
import com.ros.accounting.log.RosLogDebug;
import com.ros.accounting.util.ExceptionHandler;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/cashup")
@CrossOrigin("*")
@Slf4j
public class CashUpController {

	@Autowired
	private CashUpService cashUpService;

	@Autowired
	DocumentUploadService documentUploadService;

    private static final Logger log = LogManager.getLogger(CashUpController.class);
	/**
	 * Add the
	 *
	 * @param cashup
	 * @return
	 */
	@PostMapping
	@ResponseBody
	@RosLogDebug
	@Operation(summary = "Add new cashup sheet")
	public ResponseEntity<?> addNewCashUp(@RequestBody CashUpDto dto) {
		ResponseEntity<?> response;
		try {
			log.info("Adding new cashup: {}", dto);
			response = new ResponseEntity<CashUpDto>(cashUpService.addNewCashUp(dto), HttpStatus.OK);
		} catch (cashUpNotFoundException e) {
			log.error(e.getMessage());
			response = new ResponseEntity<ExceptionHandler>(new ExceptionHandler(e.getMessage()), HttpStatus.OK);
		}
		return response;
	}

	@PutMapping
	@RosLogDebug
	@Operation(summary = "Editing the cash up sheet")
	public ResponseEntity<?> editCashUp(@RequestBody CashUpDto dto) {
		ResponseEntity<?> response;
		try {
			log.info("Updating cashup: {}", dto);
			response = new ResponseEntity(cashUpService.editCashUp(dto), HttpStatus.OK);
		} catch (cashUpNotFoundException e) {
			log.error(e.getMessage());
			response = new ResponseEntity(e.getMessage(), HttpStatus.OK);
		}
		return response;
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	@ResponseBody
	@RosLogDebug
	@Operation(summary = "Get cashup sheet based on id")
	public ResponseEntity<?> getCashUpById(@PathVariable(value = "id") UUID id) {
		ResponseEntity<?> response;
		try {
			log.info("Getting cashup by id : {}", id);
			response = new ResponseEntity<CashUpDto>(cashUpService.getCashUpById(id), HttpStatus.OK);
		} catch (cashUpNotFoundException e) {
			log.error(e.getMessage());
			response = new ResponseEntity<ExceptionHandler>(new ExceptionHandler(e.getMessage()), HttpStatus.OK);
		}
		return response;
	}

	@GetMapping("/summary")
	@RosLogDebug
	@Operation(summary = "Get cashup sheets based on the status and date(yyyy-MM-dd)")
	public ResponseEntity<?> getCashUpSheetsByStatus(@RequestParam(defaultValue = "DRAFT") CashUpStatus status,
			@RequestParam(required = false) String fromDate, @RequestParam(required = false) String toDate)
			throws ParseException {
		ResponseEntity<?> response = null;
		Date from = null, to = null;
		if (fromDate != null && toDate != null) {
			from = new SimpleDateFormat("yyyy-MM-dd").parse(fromDate);
			to = new SimpleDateFormat("yyyy-MM-dd").parse(toDate);
		}
		log.info("cashup sheets: {}", status);
		try {
			response = new ResponseEntity<List<?>>(cashUpService.getCashUpSheets(status, from, to), HttpStatus.OK);
		} catch (cashUpNotFoundException e) {
			e.printStackTrace();
		}
		return response;
	}

//    @GetMapping("/summary")
//@RosLogDebug
//@Operation(summary = "Get cashup sheets based on the status and date(yyyy-MM-dd)")
//public ResponseEntity<?> getCashUpSheetsByStatus(@RequestParam(defaultValue = "DRAFT") CashUpStatus status,
//@RequestParam(required = false) String fromDate, @RequestParam(required = false) String toDate)
//throws ParseException {​​​
//ResponseEntity<?> response = null;
//Date from = null, to = null;
//if (fromDate != null && toDate != null) {​​​
//from = new SimpleDateFormat("yyyy-MM-dd").parse(fromDate);
//to = new SimpleDateFormat("yyyy-MM-dd").parse(toDate);
//}​​​
//log.info("cashup sheets: {​​​}​​​", status);
//try {​​​
//response = new ResponseEntity<List<?>>(cashUpService.getCashUpSheets(status, from, to), HttpStatus.OK);
//}​​​ catch (cashUpNotFoundException e) {​​​
//e.printStackTrace();
//}​​​
//return response;
//}​​​

	@GetMapping
	@RosLogDebug
	@ResponseBody
	@RolesAllowed({ "ROLE_USER" })
	@Operation(summary = "Find all cashup sheets available in the database")
	public ResponseEntity<?> getAllCashUpSheets() {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<List<CashUpDto>>(cashUpService.getAllCashUpSheets(), HttpStatus.OK);
		} catch (cashUpNotFoundException e) {
			response = new ResponseEntity<ExceptionHandler>(new ExceptionHandler(e.getMessage()), HttpStatus.OK);
		}
		return response;
	}

	@DeleteMapping("{id}")
	@ResponseBody
	@RosLogDebug
	@Operation(summary = "Deleting the cashup sheet")
	public ResponseEntity<?> deleteCashUp(@PathVariable UUID id) {
		ResponseEntity<?> response = null;
		try {
			log.info("Deleting cashup: {}", id);
			response = new ResponseEntity<String>(cashUpService.deleteCashUp(id), HttpStatus.OK);
		} catch (cashUpNotFoundException e) {
			log.error(e.getMessage());
			response = new ResponseEntity<ExceptionHandler>(new ExceptionHandler(e.getMessage()), HttpStatus.NOT_FOUND);
		}
		return response;
	}

	/**
	 * Create the
	 *
	 * @param Banking
	 * @return
	 * @throws SheetNotFoundException
	 */

	@PostMapping(value = "/banking")
	@ResponseBody
	@RosLogDebug
	@Operation(summary = "Create new banking sheet")
	public ResponseEntity<?> createBanking(@RequestBody BankingInfoDto dto) {
		ResponseEntity<?> response;
		try {
			log.info("Create new Banking: {}", dto);
			response = new ResponseEntity<BankingInfoDto>(cashUpService.CreateBanking(dto), HttpStatus.OK);
		} catch (cashUpNotFoundException e) {
			log.error(e.getMessage());
			response = new ResponseEntity<ExceptionHandler>(new ExceptionHandler(e.getMessage()), HttpStatus.OK);
		}
		return response;
	}

	@PutMapping("/banking")
	@ResponseBody
	@RosLogDebug
	@Operation(summary = "Editing the Basking sheet")
	public ResponseEntity<?> editBanking(@RequestBody BankingInfoDto dto) {
		ResponseEntity<?> response;
		try {
			log.info("Update Banking: {}", dto);
			response = new ResponseEntity(cashUpService.updateBanking(dto), HttpStatus.OK);
		} catch (cashUpNotFoundException e) {
			log.error(e.getMessage());
			response = new ResponseEntity(new ExceptionHandler(e.getMessage()), HttpStatus.OK);
		}
		return response;
	}

	@ResponseBody
	@GetMapping("/banking")
	@RosLogDebug
	@Operation(summary = "Get all the Banking")
	public ResponseEntity<?> getAllbankig() throws cashUpNotFoundException {
		ResponseEntity<?> response = null;
		try {
			log.info("GetAll the Banking: {}");
			response = new ResponseEntity<List<BankingInfoDto>>(cashUpService.getAllBanks(), HttpStatus.OK);
		} catch (cashUpNotFoundException e) {
			log.error(e.getMessage());
			response = new ResponseEntity<ExceptionHandler>(new ExceptionHandler(e.getMessage()), HttpStatus.OK);
		}
		return response;
	}

	@GetMapping("/banking/{id}")
	@ResponseBody
	@RosLogDebug
	@Operation(summary = "Get Banking by ID")
	public ResponseEntity<?> getbankigById(@PathVariable(value = "id") UUID id) throws cashUpNotFoundException {
		ResponseEntity<?> response = null;
		try {
			log.info("Get Bank By ID: {}");
			response = new ResponseEntity<BankingInfoDto>(cashUpService.getBankById(id), HttpStatus.OK);

		} catch (cashUpNotFoundException e) {
			log.error(e.getMessage());
			response = new ResponseEntity<ExceptionHandler>(new ExceptionHandler(e.getMessage()), HttpStatus.NOT_FOUND);
		}
		return response;
	}

	@DeleteMapping("/banking/{id}")
	@RosLogDebug
	@ResponseBody
	@Operation(summary = "Delete Select banking")
	public ResponseEntity<?> deleteBankingById(@PathVariable("id") UUID id) {
		ResponseEntity<?> response = null;
		try {
			log.info("Deleting cashup: {}", id);
			response = new ResponseEntity<String>(cashUpService.DeleteBankingById(id), HttpStatus.OK);
		} catch (cashUpNotFoundException e) {
			log.error(e.getMessage());
			response = new ResponseEntity<ExceptionHandler>(new ExceptionHandler(e.getMessage()), HttpStatus.NOT_FOUND);
		}
		return response;
	}


	@PostMapping(path = "/document", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public String upload(@RequestPart("file") MultipartFile[] multipartFiles) {
		log.info("Start file upload...");
		if (multipartFiles == null || multipartFiles.length == 0) {
			return "Upload failed, please select file";
		}

		return documentUploadService.uploadFile("PICTURE", multipartFiles);
	}

//	@GetMapping(path = "/download")
//	public ResponseEntity<ByteArrayResource> uploadFile(@RequestParam(value = "file") String file) throws IOException {
//		byte[] data = azureAdapter.getFile(file);
//		ByteArrayResource resource = new ByteArrayResource(data);
//
//		return ResponseEntity
//				.ok()
//				.contentLength(data.length)
//				.header("Content-type", "application/octet-stream")
//				.header("Content-disposition", "attachment; filename=\"" + file + "\"")
//				.body(resource);
//
//	}


}
