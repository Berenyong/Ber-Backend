package bssm.ber.domain.entity.posts.posts.repository;

import bssm.ber.domain.entity.posts.posts.ManagerPosts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ManagerPostsRepository extends JpaRepository<ManagerPosts, Long> {

    @Query("select m from ManagerPosts m where m.title = :title")
    List<ManagerPosts> findByTitle(@Param("title") String title);
}
