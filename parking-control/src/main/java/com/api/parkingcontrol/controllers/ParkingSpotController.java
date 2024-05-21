package com.api.parkingcontrol.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.parkingcontrol.dtos.ParkingSpotDto;
import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.services.ParkingSpotService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;



@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {

    final ParkingSpotService parkingSpotService;

    public ParkingSpotController(ParkingSpotService parkingSpotService) {
        this.parkingSpotService=parkingSpotService;
    }

    @PostMapping
    public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpotDto){
        if(parkingSpotService.existsByLicensePlateCar(parkingSpotDto.getLicensePlateCar())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: License Plate Car is Already in use");
        }
        if(parkingSpotService.existsByParkingSpotNumber(parkingSpotDto.getParkingSpotNumber())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot is Already in use!");
        }
        if(parkingSpotService.existsByApartamentAndBlock(parkingSpotDto.getApartament(), parkingSpotDto.getBlock())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot is Already registered for this apartment/block!");
        }
 
        var parkingSpotModel = new ParkingSpotModel(); // Corrigido para letra minúscula
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel); // o beanutils converse como recebe, dto, para model, que é como é guardado
        parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel)); // Corrigido para letra minúscula
    }
    
    //implementação do método get pra mostrar todas as vagas registradas
    @GetMapping
    public ResponseEntity<List<ParkingSpotModel>> getAllParkingSpots() {
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAll());
    }
     //implementação do método get pra mostrar uma das vagas registradas pelo id
     @GetMapping("/{id}")
     public ResponseEntity<Object> getOneParkingSpot(@PathVariable(value="id") UUID id){
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
        if(!parkingSpotModelOptional.isPresent())/*se o id não existe iremos mostrar*/{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
        }
        //se não iremos mostraar a vaga pesquisada pelo id
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModelOptional.get());
     }

     //implementação do método pra deletrar um registo especifico pelo id
     @DeleteMapping("/{id}")
     public ResponseEntity<Object> deleteOneParkingSpot(@PathVariable(value="id") UUID id){
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
        if(!parkingSpotModelOptional.isPresent())/*se o id não existe iremos mostrar*/{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
        }
        //se ele existir a vaga será deletada com sucesso
        parkingSpotService.delete(parkingSpotModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Parking Spot deleted successfully");
     }

     //método put para atualizar os campos
     @PutMapping("/{id}")
     public ResponseEntity<Object> updateParkingSpot(@PathVariable(value="id") UUID id, @RequestBody @Valid ParkingSpotDto parkingSpotDto){
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
        if(!parkingSpotModelOptional.isPresent()){/*se o id não existe iremos mostrar*/
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");  
     }
     var parkingSpotModel = new ParkingSpotModel();//realizar das duas maneiras
     return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.save(parkingSpotModel));   
     

    

}
}
