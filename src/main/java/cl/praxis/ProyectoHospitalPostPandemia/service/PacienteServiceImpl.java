package cl.praxis.ProyectoHospitalPostPandemia.service;

import cl.praxis.ProyectoHospitalPostPandemia.ProyectoHospitalPostPandemiaApplication;
import cl.praxis.ProyectoHospitalPostPandemia.model.dto.Paciente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PacienteServiceImpl implements IPaciente {

    private static final Logger logger = LoggerFactory.getLogger(ProyectoHospitalPostPandemiaApplication.class);
    private final List<Paciente> patientList;

        public PacienteServiceImpl() {
        patientList = new ArrayList<>();
        patientList.add(new Paciente(1, "Ronald", "Garcia", "Efermedad1"));
        patientList.add(new Paciente(2, "Maribel", "Rojas", "Efermedad2"));
        patientList.add(new Paciente(3, "Marcos", "Vidal", "Efermedad3"));
        patientList.add(new Paciente(4, "Kamala", "Harris", "Efermedad4"));
    }

    public PacienteServiceImpl(List<Paciente> patientList) {
        this.patientList = patientList;
    }

    @Override
    public List<Paciente> findAll() {
        logger.warn("Ejecutando findAll de PatientServiceImpl");
        return patientList;
    }

    @Override
    public Paciente findOne(int id) {
        logger.warn("Ejecutando findOne de PatientServiceImpl");
        return patientList.stream()
                .filter(patient -> patient.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean create(Paciente patient) {
        logger.warn("Ejecutando create de PatientServiceImpl");
        patient.setId(newId());
        logger.warn("EjecutandovvvvvvceImpl");
        patientList.add(patient);
        return true;
    }

    @Override
    public boolean update(Paciente p) {
        logger.warn("Ejecutando update de PatientServiceImpl");
        Paciente patient = findOne(p.getId());
        patient.setName(p.getName());
        patient.setLastName(p.getLastName());
        patient.setConcurrence(p.getConcurrence());

        return true;
    }

    @Override
    public boolean delete(int id) {
        logger.warn("Ejecutando delete de PatientServiceImpl");
        Paciente patient = findOne(id);
        if (patient != null){
            patientList.remove(patient);
            return true;
        }

        return false;
    }

    @Override
    public int newId() {
        logger.warn("Ejecutando newID de PatientServiceImpl");

        if (!patientList.isEmpty()){
            Paciente p= patientList.stream().max(Comparator.comparing(Paciente::getId)).orElseThrow(NoSuchElementException::new);
            logger.warn("verificar2");
             return p.getId()+1;
        }
        return 1;
    }


}
