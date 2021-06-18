package com.ros.accounting.cashup.controller;

import com.ros.accounting.cashup.controller.dto.ReconciliationLogDto;
import com.ros.accounting.cashup.exceptions.cashUpNotFoundException;
import com.ros.accounting.cashup.service.ReconciliationService;
import com.ros.accounting.log.RosLogDebug;
import com.ros.accounting.util.ExceptionHandler;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reconciliation")
@CrossOrigin("*")
@Slf4j
public class ReconciliationController {

    @Autowired
    private ReconciliationService reconciliationService;

    /**
     * Add the
     *
     * @param cashup
     * @return
     */
    @PostMapping
    @ResponseBody
    @RosLogDebug
    @Operation(summary = "Reconcile CashUp Sheets")
    public ResponseEntity<?> reconcileCashUps(@RequestBody List<ReconciliationLogDto> reconciliationLogDto) {
        ResponseEntity<?> response;
        try {
            log.info("Reconciling Cash Up Sheets: {}", reconciliationLogDto);
            response = new ResponseEntity<List<ReconciliationLogDto>>(reconciliationService.reconcileCashUps(reconciliationLogDto), HttpStatus.OK);
        } catch (cashUpNotFoundException e) {
            log.error(e.getMessage());
            response = new ResponseEntity<ExceptionHandler>(new ExceptionHandler(e.getMessage()), HttpStatus.OK);
        }
        return response;
    }

    /**
     * Add the
     *
     * @param cashup
     * @return
     */
//	@PostMapping
//	@ResponseBody
//	@RosLogDebug
//	@Operation(summary = "Reconcile a CashUp Sheet")
//	public ResponseEntity<?> reconcileCashUp(@RequestBody ReconciliationLogDto reconciliationLogDto) {
//		ResponseEntity<?> response;
//		try {
//			log.info("Reconciling a Cash Up Sheet: {}", reconciliationLogDto);
//			response = new ResponseEntity<ReconciliationLogDto>(reconciliationService.reconcileCashUp(reconciliationLogDto), HttpStatus.OK);
//		} catch (cashUpNotFoundException e) {
//			log.error(e.getMessage());
//			response = new ResponseEntity<ExceptionHandler>(new ExceptionHandler(e.getMessage()), HttpStatus.OK);
//		}
//		return response;
//	}
    @GetMapping("/summary")
    @RosLogDebug
    @Operation(summary = "Get cashup sheets for reconciliation for a given duration")
    public ResponseEntity<?> getCashUpSheetsForReconciliation(@RequestParam(required = false) String fromDate, @RequestParam(required = false) String toDate)
            throws ParseException {
        ResponseEntity<?> response = null;
        Date from = null, to = null;
        if (fromDate != null && toDate != null) {
            from = new SimpleDateFormat("yyyy-MM-dd").parse(fromDate);
            to = new SimpleDateFormat("yyyy-MM-dd").parse(toDate);
        }
        log.info("cashup sheets: {}");
        try {
            response = new ResponseEntity<>(reconciliationService.getPendingReconciliationSummary(from, to), HttpStatus.OK);
        } catch (cashUpNotFoundException e) {
            e.printStackTrace();
        }
        return response;
    }

    @CrossOrigin
    @GetMapping("/reconciledsummary")
    @RosLogDebug
    @Operation(summary = "Get reconciled cash up sheet total for a given duration")
    public ResponseEntity<?> getReconciledCashUpSheets(@RequestParam(required = false) String fromDate, @RequestParam(required = false) String toDate)
            throws ParseException {
        ResponseEntity<?> response = null;
        Date from = null, to = null;
        if (fromDate != null && toDate != null) {
            from = new SimpleDateFormat("yyyy-MM-dd").parse(fromDate);
            to = new SimpleDateFormat("yyyy-MM-dd").parse(toDate);
        }
        log.info("cashup sheets: {}");
        try {
            response = new ResponseEntity<>(reconciliationService.getReconciledSummary(from, to), HttpStatus.OK);
        } catch (cashUpNotFoundException e) {
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping("/reconciledcalendarsummary")
    @RosLogDebug
    @Operation(summary = "Get reconciled cash up sheet total for a given duration")
    public ResponseEntity<?> getReconciledCalendarSummary(@RequestParam(required = false) String fromDate, @RequestParam(required = false) String toDate, String rType)
            throws ParseException {
        ResponseEntity<?> response = null;
        Date from = null, to = null;
        if (fromDate != null && toDate != null) {
            from = new SimpleDateFormat("yyyy-MM-dd").parse(fromDate);
            to = new SimpleDateFormat("yyyy-MM-dd").parse(toDate);
        }
        log.info("cashup sheets: {}");
        try {
            response = new ResponseEntity<>(reconciliationService.getReconciliationLog(from, to, rType), HttpStatus.OK);
        } catch (cashUpNotFoundException e) {
            e.printStackTrace();
        }
        return response;
    }
}
