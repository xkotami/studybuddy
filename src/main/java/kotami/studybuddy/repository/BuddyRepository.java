package kotami.studybuddy.repository;

import kotami.studybuddy.entity.Buddy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuddyRepository extends JpaRepository<Buddy, Long> {
}
