package com.base.hospital.controller;

import com.base.entity.response.BaseResponse;
import com.base.entity.response.ErrorResponse;
import com.base.entity.response.SuccessResponse;
import com.base.hospital.bean.CreatePatientRequest;
import com.base.hospital.bean.UpdatePatientRequest;
import com.base.hospital.service.PatientService;
import com.base.utils.Utility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestController
@Slf4j
@RequestMapping("/v1/patient")
public class PatientController {

    private static final int ERROR_CODE = 100;
    private static final String ERROR_MSG = "Some error on server for patient controller";


    @Autowired
    PatientService patientService;

    @RequestMapping(
            value = "",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BaseResponse getAllPatient(HttpServletRequest request, @RequestParam(name = "patient_id", required = false) String patientId
    ,@RequestParam(name = "limit", required = false) String limit) {
        try {
            log.info("Patient id {}", patientId);
            return new SuccessResponse(patientService.getAllPatient(patientId));
        } catch (Exception e) {
            return new ErrorResponse(ERROR_CODE, ERROR_MSG);
        }
    }

    @RequestMapping(
            value = "/{patient_id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BaseResponse getPatient(HttpServletRequest request, @PathVariable("patient_id") Long patientId) {
        try {
            log.info("Patient id {}", patientId);
            return new SuccessResponse(patientService.getPatient(patientId));
        } catch (Exception e) {
            log.error( "error ",e);
            return new ErrorResponse(ERROR_CODE, ERROR_MSG);
        }
    }


    @RequestMapping(
            value = {""},
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BaseResponse createPatient(@RequestBody CreatePatientRequest CreatePatientRequest){
        try{
            log.info("Create Transaction Request: {}" , Utility.jsonEncode(CreatePatientRequest));
            return  patientService.createPatient(CreatePatientRequest);
        }
        catch (Exception e) {
            return new ErrorResponse(ERROR_CODE, ERROR_MSG);
        }
    }

    @RequestMapping(
            value = {"/{patient_id}"},
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BaseResponse updatePatient( @PathVariable("patient_id") Long id ,@RequestBody UpdatePatientRequest updatePatientRequest){
        try{
            updatePatientRequest.setId(id);
            log.info("Create Transaction Request: {}" , Utility.jsonEncode(updatePatientRequest));
            return  patientService.updatePatient(updatePatientRequest);
        }
        catch (Exception e) {
            return new ErrorResponse(ERROR_CODE, ERROR_MSG);
        }
    }

    @RequestMapping(
            value = "/{patient_id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BaseResponse deletePatient(HttpServletRequest request, @PathVariable("patient_id") Long patientId) {
        try {
            log.info("Patient id {}", patientId);
            patientService.deletePatient(patientId);
            return new SuccessResponse("Patient deleted with id "+patientId);
        } catch (Exception e) {
            log.error( "error ",e);
            return new ErrorResponse(ERROR_CODE, ERROR_MSG);
        }
    }

}
