package UFC.Agos.repositories;

import UFC.Agos.models.Crenel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CrenelRepository extends JpaRepository<Crenel, Long> {

    @Modifying
    @Query(value ="delete from crenel_rooms WHERE crenel_id =?" ,nativeQuery = true)
    void deleteByCrenel(Long crenelId);

    @Modifying
    @Query(value ="update crenel set session_id = null WHERE id =?" ,nativeQuery = true)
    void removeSessionfromCrenel(Long crenelId);
}
