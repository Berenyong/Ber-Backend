package bssm.ber.domain.entity.posts.repository;

import bssm.ber.domain.entity.posts.FreePosts;
import bssm.ber.domain.entity.posts.MajorPosts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MajorPostsRepository extends JpaRepository<MajorPosts, Long> {

    @Query("select m from MajorPosts m where m.title = :title")
    List<MajorPosts> findByTitle(@Param("title") String title);
}
