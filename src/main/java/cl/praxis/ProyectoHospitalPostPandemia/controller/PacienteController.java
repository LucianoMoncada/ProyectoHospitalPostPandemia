package cl.praxis.ProyectoHospitalPostPandemia.controller;

import cl.praxis.ProyectoHospitalPostPandemia.ProyectoHospitalPostPandemiaApplication;
import cl.praxis.ProyectoHospitalPostPandemia.model.dto.Paciente;
import cl.praxis.ProyectoHospitalPostPandemia.service.IPaciente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {
    private static final Logger logger = LoggerFactory.getLogger(ProyectoHospitalPostPandemiaApplication.class);

    //@Autowired
    private IPaciente service;

        public PacienteController(IPaciente service) {
        this.service = service;
    }

    @GetMapping
    public String findAll(Model model){
        logger.info("Ejecutando findAll de PacienteController");
        model.addAttribute("patients", service.findAll());
        return "patientList";
    }

    @GetMapping("/{id}")
    public String findOne(@PathVariable("id") int id, Model model){
        logger.info("Ejecutando findOne de PacienteController");
        model.addAttribute("patient", service.findOne(id));

        return "patientDetail";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id,Model model){
            model.addAttribute("patient", service.findOne(id));
            return "patientEdit";
    }

    @PostMapping
    public String update(@ModelAttribute Paciente patient){
        logger.info("Ejecutando update de PacienteController");
        boolean result = service.update(patient);

        return "redirect:/pacientes";
    }

    @GetMapping("/new")
    public String newPatient(){
        logger.info("Ejecutando newPatient de PatientController");
        return "newPatient";
    }

    @PostMapping("/new")
    public String createPatient(@ModelAttribute Paciente patient){
        logger.info("Ejecutando createPatient de PatientController");
        boolean result = service.create(patient);

        return "redirect:/pacientes";
    }
    @GetMapping("/del/{id}")
    public String delete(@PathVariable("id") int id){
        logger.info("Ejecutando delete de PatientController");
        boolean result = service.delete(id);

        if (result){
            logger.info("paciente eliminado correctamente");
        } else {
            logger.error("Error al eliminar el paciente");
        }
        return "redirect:/pacientes";
    }


}
