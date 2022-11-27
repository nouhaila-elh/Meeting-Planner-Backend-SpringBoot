package room.reservation.controller;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import room.reservation.repository.*;
import room.reservation.serviceInterface.RoomInterface;
import room.reservation.entities.*;
@RestController
public class RoomController {

	
    @Autowired
    RoomInterface roomService;
    
    @Autowired
    RoomRespository roomRespository;
    
    @Autowired
    EquipmentRepository equipmentRepository;
	

    
    @PostMapping("/addroom")
    public String addRoom(@RequestBody Room room){
    	roomService.addRoom(room);
        return "Added successfully";
    }
    
    
    @PostMapping("/save-equipment-to-room")
    public String SaveEquipmentToRoom(@RequestBody Map<String, Long> map){
    	
    	//Find the meeting already stored in database 
    	Optional < Room > roomToFind = roomRespository.findById(map.get("roomid"));
    	
    	//Find the equipment already stored in database 
    	Optional < Equipment > equipmentfToFind = equipmentRepository.findById(map.get("equipmentid"));
    	Equipment equipmentFound = new Equipment();
    	
    	// if meeting found
    	Room roomFound = new Room();
    	
    	if (roomToFind.isPresent()) {
    		roomFound= roomToFind.get();
    		// if equipment found :
    		
    		if(equipmentfToFind.isPresent()) {
    			equipmentFound= equipmentfToFind.get();
    			roomFound.getEquipments().add(equipmentFound);
    			roomRespository.save(roomFound);
    		}
    		else {
    			 System.out.printf("No equipment found");
    		}
    		
    		
			
        } else {
            System.out.printf("No meeting found");
        }
		
        return "Added successfully";
    }

}
