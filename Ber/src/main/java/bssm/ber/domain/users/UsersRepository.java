package bssm.ber.domain.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query("select u from Users u where u.nickname = :nickname")
    List<Users> findByNickname(@Param("nickname") String nickname);

    Optional<Users> findByEmail(String email);
}
