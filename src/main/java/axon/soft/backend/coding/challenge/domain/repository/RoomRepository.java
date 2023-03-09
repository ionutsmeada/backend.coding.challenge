package axon.soft.backend.coding.challenge.domain.repository;

import axon.soft.backend.coding.challenge.domain.entity.RoomEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomRepository extends CrudRepository<RoomEntity, Integer> {
    List<RoomEntity> findByRoomNumber(String roomNumber);
}
