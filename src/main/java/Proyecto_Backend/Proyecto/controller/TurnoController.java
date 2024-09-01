package Proyecto_Backend.Proyecto.controller;


import Proyecto_Backend.Proyecto.entity.Turno;
import Proyecto_Backend.Proyecto.service.ITurnoService;
import Proyecto_Backend.Proyecto.service.impl.TurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private ITurnoService turnoService;

    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }
    //POST
    @PostMapping("/guardar")
    public ResponseEntity<Turno> guardarTurno(@RequestBody Turno turno){
        return ResponseEntity.ok(turnoService.guardarTurno(turno));
    }

    //GET TODOS
    @GetMapping("/buscartodos")
    public ResponseEntity<List<Turno>> buscarTodos(){

        return ResponseEntity.ok(turnoService.buscarTodos());
    }

    //PUT

    @PutMapping("/modificar")
    public ResponseEntity<String>  modificar(@RequestBody Turno turno){
        Optional<Turno> turnoEncontrado = turnoService.buscarPorId(turno.getId());
        if(turnoEncontrado.isPresent()){
            turnoService.modificarTurno(turno);
            return ResponseEntity.ok("el turno fue modificado");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //DELETE
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id){
        Optional<Turno> turnoEncontrado = turnoService.buscarPorId(id);
        if(turnoEncontrado.isPresent()) {
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("el turno fue eliminado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //GET
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Turno>  buscarPorId(@PathVariable Integer id){
        Optional<Turno> turnoEncontrado = turnoService.buscarPorId(id);
        if(turnoEncontrado.isPresent()) {
            return ResponseEntity.ok(turnoEncontrado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
