package bssm.ber.domain.posts.posts.repository;

import bssm.ber.domain.posts.posts.FreePosts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FreePostsRepository extends JpaRepository<FreePosts, Long> {

    @Query("select f from FreePosts f where f.title = :title")
    List<FreePosts> findByTitle(@Param("title") String title);

}
