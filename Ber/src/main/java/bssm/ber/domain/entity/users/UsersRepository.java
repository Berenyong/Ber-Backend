package bssm.ber.domain.entity.users;

import bssm.ber.web.dto.users.UsersResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query("select u from Users u where u.nickname = :nickname")
    List<Users> findByNickname(@Param("nickname") String nickname);
}
