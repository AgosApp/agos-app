package UFC.Agos.services;

import UFC.Agos.models.Department;
import UFC.Agos.models.Room;

import java.util.List;

public interface IRoomService {
    public List<Room> getRooms();
    public Room getRoom(Long roomId);
    public void addRoom(Room room);
    public void deleteRoom(Long roomId) throws Exception;
    public void updateRoom(Long roomId,
                           String name,
                           String description);


}
