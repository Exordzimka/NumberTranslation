package application.repositories;

import application.models.NumberTranslationUser;
import application.models.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserHistoryRepository extends JpaRepository<UserHistory, Integer> {

    @Query("SELECT u FROM UserHistory u WHERE u.numberTranslationUser.username = :username")
    List<UserHistory> findByUsername(@Param("username") String username);
}
