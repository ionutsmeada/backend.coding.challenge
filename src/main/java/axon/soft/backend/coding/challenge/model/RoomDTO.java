package axon.soft.backend.coding.challenge.model;

import jakarta.validation.constraints.Pattern;
import lombok.*;



import java.util.List;
@Data
@Builder
@NoArgsConstructor
public class RoomDTO {
    private String roomNumber;

    private List<PersonDTO> people;

    public RoomDTO(String roomNumber, List<PersonDTO> people) {
        this.roomNumber = roomNumber;
        this.people = people;
    }

    @Override
    public String toString() {
        return "RoomDTO{" +
                "roomNumber='" + roomNumber + '\'' +
                ", people=" + people +
                '}';
    }
}
