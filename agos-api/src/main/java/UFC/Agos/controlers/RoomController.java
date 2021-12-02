package UFC.Agos.controlers;

import UFC.Agos.models.Department;
import UFC.Agos.models.Room;
import UFC.Agos.services.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/rooms")
public class RoomController {

    @Autowired(required = true)
    IRoomService roomService;

    @GetMapping
    public List<Room> getRooms(){return roomService.getRooms();}

    @GetMapping(path="/{roomId}")
    public Room getRoom(@PathVariable Long roomId){
        return roomService.getRoom(roomId);
    }

    @PostMapping
    public void saveRoom(@RequestBody Room room){
        roomService.addRoom(room);
    }

    @DeleteMapping(path = "/{roomId}")
    public  void deleteRoom(@PathVariable("roomId") Long roomId) throws Exception {
        roomService.deleteRoom(roomId);
    }

    @PutMapping(path = "/{roomId}")
    public void updateRoom(@PathVariable("roomId") Long roomId,
                                 @RequestParam(required = false) String name,
                                 @RequestParam(required = false) String description){
        roomService.updateRoom(roomId, name, description);
    }
}
