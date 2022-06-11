package bssm.ber.domain.posts.comment.repository;

import bssm.ber.domain.posts.comment.FreePostsComment;
import bssm.ber.domain.posts.comment.MajorPostsComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MajorPostsCommentRepository extends JpaRepository<MajorPostsComment, Long> {
    @Query("select mpc from MajorPostsComment mpc where mpc.MajorPosts.id = :id")
    List<MajorPostsComment> findAllDesc(@Param("id") Long id);
}
