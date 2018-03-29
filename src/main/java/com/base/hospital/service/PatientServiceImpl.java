package com.base.hospital.service;

import com.base.entity.response.BaseResponse;
import com.base.entity.response.ErrorResponse;
import com.base.entity.response.SuccessResponse;
import com.base.hospital.bean.CreatePatientRequest;
import com.base.hospital.bean.Patient;
import com.base.hospital.bean.PatientEntity;
import com.base.hospital.bean.UpdatePatientRequest;
import com.base.hospital.dao.PatientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class PatientServiceImpl implements PatientService {


    @Autowired
    PatientDao patientDao;

    public List<Patient> getAllPatient(String patientId) {
        List<Patient> patientList = new ArrayList<>();
        //Patient  patient = Patient.builder().id(1).address("abc").age("34").email("abc@gmail.com").build();
        List<PatientEntity> patientEntityList = patientDao.findAll();
        for (PatientEntity pe : patientEntityList) {
            patientList.add(getPatientFromEntity(pe));
        }
        return patientList;
    }

    public Patient getPatient(Long patientId) {

        return getPatientFromEntity(patientDao.getOne(patientId));
    }

    public void deletePatient(Long patientId) {
         patientDao.delete(patientId);
    }


    public BaseResponse createPatient(CreatePatientRequest CreatePatientRequest){
        PatientEntity patientEntity = getPatientEntityFromPatientRequest(CreatePatientRequest);
        return new SuccessResponse(getPatientFromEntity(patientDao.save(patientEntity)));
    }

   public  BaseResponse updatePatient(UpdatePatientRequest updatePatientRequest){
       PatientEntity patientEntity = patientDao.getOne(updatePatientRequest.getId());
       if(patientEntity == null){
           return new ErrorResponse("No user exist with id"+updatePatientRequest.getId());
       }

       getPatientEntityFromUpdatePatientRequest(patientEntity,updatePatientRequest);
        patientDao.save(patientEntity);
        return new SuccessResponse(getPatientFromEntity(patientDao.save(patientEntity)));
    }

    private void getPatientEntityFromUpdatePatientRequest(PatientEntity patientEntity, UpdatePatientRequest upr) {
        if(upr.getName() == null){
            patientEntity.setName(upr.getName());
        }
        if(upr.getAddress() == null){
            patientEntity.setName(upr.getAddress());
        }
        if(upr.getEmail() == null){
            patientEntity.setName(upr.getName());
        }
        if(upr.getName() == null){
            patientEntity.setName(upr.getName());
        }
        if(upr.getName() == null){
            patientEntity.setName(upr.getName());
        }

    }

    private PatientEntity getPatientEntityFromPatientRequest(CreatePatientRequest cpr) {
        PatientEntity patientEntity = PatientEntity.builder().
                name(cpr.getName()).
                mobile(cpr.getMobile()).
                email(cpr.getEmail()).
                address(cpr.getAddress()).
                gender(cpr.getGender()).
                createdOn(Timestamp.valueOf(LocalDateTime.now())).build();

        return patientEntity;

    }


    private Patient getPatientFromEntity(PatientEntity pe) {
        if(pe == null){
            return null;
        }
        return Patient.builder().id(pe.getId()).
                name(pe.getName()).
                mobile(pe.getMobile()).
                gender(pe.getGender()).
                address(pe.getAddress()).age(pe.getAge()).
                email(pe.getEmail()).build();
    }


}
