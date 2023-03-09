package axon.soft.backend.coding.challenge.domain.entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "room", schema = "backend_coding_challenge")
@Builder
public class RoomEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_room")

    private Integer id;

    @Column(name = "room_number")
    private String roomNumber;

    @OneToMany(mappedBy = "roomEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PersonEntity> personEntities;

    public RoomEntity(Integer id, String roomNumber, List<PersonEntity> personEntities) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.personEntities = personEntities;
    }
}
