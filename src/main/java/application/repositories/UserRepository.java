package application.repositories;

import application.models.NumberTranslationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<NumberTranslationUser, Integer>
{
    @Query("SELECT u FROM NumberTranslationUser u WHERE u.login = :login")
    NumberTranslationUser findByLogin(@Param("login") String login);
}
