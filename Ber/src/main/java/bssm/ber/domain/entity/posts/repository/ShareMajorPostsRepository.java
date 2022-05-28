package bssm.ber.domain.entity.posts.repository;

import bssm.ber.domain.entity.posts.ShareMajorPosts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShareMajorPostsRepository extends JpaRepository<ShareMajorPosts, Long> {

    @Query("select s from ShareMajorPosts s where s.title = :title")
    List<ShareMajorPosts> findByTitle(@Param("title") String title);
}
