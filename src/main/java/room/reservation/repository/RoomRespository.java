package room.reservation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import room.reservation.entities.*;

@Repository
public interface RoomRespository extends JpaRepository<Room, Long> {
	List<Room> findRoomsByEquipmentsId(Long equipmentId);
	
}
