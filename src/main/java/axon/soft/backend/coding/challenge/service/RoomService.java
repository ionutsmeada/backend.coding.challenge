package axon.soft.backend.coding.challenge.service;

import axon.soft.backend.coding.challenge.domain.entity.PersonEntity;
import axon.soft.backend.coding.challenge.domain.entity.RoomEntity;
import axon.soft.backend.coding.challenge.domain.repository.RoomRepository;
import axon.soft.backend.coding.challenge.exceptions.ErrorObject;
import axon.soft.backend.coding.challenge.exceptions.NotFoundException;
import axon.soft.backend.coding.challenge.helper.AppMapper;
import axon.soft.backend.coding.challenge.helper.ErrorType;
import axon.soft.backend.coding.challenge.model.PersonDTO;
import axon.soft.backend.coding.challenge.model.RoomDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private AppMapper appMapper;

    public List<RoomDTO> getRoomList(String roomNumber){
        List<RoomEntity> roomEntities = roomRepository.findByRoomNumber(roomNumber);
        if(roomEntities.isEmpty()){
            throw new NotFoundException(ErrorObject.builder()
                .errorCode(ErrorType.ERROR_CODE_5.getCode())
                .message(ErrorType.ERROR_CODE_5.getMessage())
                .build());
        }
        return responseRoomObject(roomEntities);
    }

    public List<RoomDTO> getAllRoomsList(){
        List<RoomEntity> roomEntities = (List<RoomEntity>) roomRepository.findAll();
        return responseRoomObject(roomEntities);
    }

    private List<RoomDTO> responseRoomObject(List<RoomEntity> roomEntities){
        List<RoomDTO> roomDTOs = new ArrayList<>();
        for(RoomEntity roomEntity : roomEntities){
            RoomDTO roomDTO = appMapper.mapToRoomDTO(roomEntity);
            List<PersonDTO> personDTO =new ArrayList<>();
            for(PersonEntity personEntity : roomEntity.getPersonEntities()){
                personDTO.add(appMapper.mapToPersonDTO(personEntity));
            }
            roomDTO.setPeople(personDTO);
            roomDTOs.add(appMapper.mapToRoomDTO(roomEntity));
        }
        return roomDTOs;
    }
}

