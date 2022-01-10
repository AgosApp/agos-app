package UFC.Agos.services.imp;

import UFC.Agos.models.Department;
import UFC.Agos.models.Room;
import UFC.Agos.repositories.RoomRepository;
import UFC.Agos.repositories.ThesisRepository;
import UFC.Agos.services.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
@Service
public class RoomService implements IRoomService {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    ThesisRepository thesisRepository;
    @Override
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }
    @Override
    public Room getRoom(Long roomId) {
        return roomRepository.findById(roomId).orElseThrow(
                ()-> new IllegalStateException("The room with id "+ roomId + " does not exist")
        );
    }
    @Override
    public void addRoom(Room room) {
        roomRepository.save(room);
    }

    @Override
    public void deleteRoom(Long roomId) throws Exception {
        boolean exists = roomRepository.existsById(roomId);
        boolean thesisExist = thesisRepository.getThesesByRoom(roomId).isEmpty();
        if(!exists){
            throw new IllegalStateException("The room with id "+ roomId + " does not exist");
        }
        if(!thesisExist){
            throw new IllegalStateException("the room with id "+ roomId +" can't be removed because it contains thesis");
        }
        //should add something here !!!
        roomRepository.deleteById(roomId);

    }

    @Override
    @Transactional
    public void updateRoom(Long roomId, String name, String description) {
        Room room = roomRepository.findById(roomId).orElseThrow(
                () -> new IllegalStateException("The room with id "+ roomId + " does not exist")
        );
        if(name != null && name.length()>0 && !Objects.equals(name, room.getName())){
            room.setName(name);
        }
        if(description != null && description.length()>0 && !Objects.equals(description, room.getDescription())){
            room.setDescription(description);
        }
    }
}
