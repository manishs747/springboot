package com.base.hospital.service;

import com.base.entity.response.BaseResponse;
import com.base.hospital.bean.CreatePatientRequest;
import com.base.hospital.bean.Patient;
import com.base.hospital.bean.UpdatePatientRequest;

import java.util.List;

public interface PatientService {

    List<Patient> getAllPatient(String patientId);
    BaseResponse createPatient(CreatePatientRequest CreatePatientRequest);
    Patient getPatient(Long patientId);
    void deletePatient(Long patientId);
    BaseResponse updatePatient(UpdatePatientRequest updatePatientRequest);

}
