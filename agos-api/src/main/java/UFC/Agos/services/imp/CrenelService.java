package UFC.Agos.services.imp;

import UFC.Agos.models.*;
import UFC.Agos.repositories.CrenelRepository;
import UFC.Agos.repositories.RoomRepository;
import UFC.Agos.repositories.SessionRepository;
import UFC.Agos.services.ICrenelService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class CrenelService implements ICrenelService {

    @Autowired
    CrenelRepository crenelRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    RoomRepository roomRepository;

    @Override
    public List<Crenel> getCreneauxBySession(Long sessionId) {
        return crenelRepository.findAll();
    }

    @Override
    public Crenel getCrenelBySession(Long crenelId, Long sessionIf) {
        return crenelRepository.findById(crenelId).orElseThrow(
                ()-> new IllegalStateException("The crenel with id "+ crenelId + " does not exist")
        );
    }

    @Override
    public void addCrenel(Crenel crenel, Long sessionId, List<Long> roomsIds) {
        Session session = sessionRepository.getById(sessionId);
        crenel.setSession(session);

        List<Room> rooms = new ArrayList<>();
        if(roomsIds != null) {
            roomsIds.forEach(roomId -> {
                rooms.add(roomRepository.getById(roomId));
            });
            crenel.setRooms(rooms);
        }
        crenelRepository.save(crenel);
    }

    @Override
    @Transactional
    public void deleteCrenel(Long crenelId) throws Exception {

            crenelRepository.removeSessionfromCrenel(crenelId);
            //remove the link between creneaux and rooms
            crenelRepository.deleteByCrenel(crenelId);

            crenelRepository.deleteById(crenelId);
    }

    @Override
    @Transactional
    public void updateCrenel(Long crenelId, Map<String, Object> request, Long sessionId, List<Long> roomsIds) {

        Crenel crenel = crenelRepository.findById(crenelId).orElseThrow(
                () -> new IllegalStateException("The crenel with id " + crenelId + " does not exist")
        );

        request.forEach((key, value) -> {
            if(key =="day"){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse((CharSequence) value, formatter);
                value = date;
            }

            if(key =="startTime" || key =="endTime"){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                LocalTime time = LocalTime.parse((CharSequence) value, formatter);
                value = time;
            }

            Field field = ReflectionUtils.findField(Crenel.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, crenel, value);
        });

        if(sessionId != null){
            Session session = sessionRepository.getById(sessionId);
            crenel.setSession(session);
        }

        List<Room> rooms = new ArrayList<>();
       // Collection<Room> oldRooms = crenel.getRooms();
        //oldRooms.forEach( room -> {
          //  rooms.add(room);
        //});

        if(roomsIds != null) {
            roomsIds.forEach(roomId -> {
                //Room room = roomRepository.getById(roomId);
                //if(!rooms.contains(room)){
                    rooms.add(roomRepository.getById(roomId));
                //}
            });
        crenel.setRooms(rooms);
        }
    }
}
