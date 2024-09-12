package cl.praxis.ProyectoHospitalPostPandemia.service;

import cl.praxis.ProyectoHospitalPostPandemia.model.dto.Paciente;

import java.util.List;

public interface IPaciente {
    List<Paciente> findAll();
    Paciente findOne(int id);
    boolean create(Paciente patient);
    boolean update(Paciente patient);
    boolean delete(int id);
    int newId();
}
