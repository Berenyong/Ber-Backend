package bssm.ber.domain.posts.comment.repository;

import bssm.ber.domain.posts.comment.MajorPostsComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MajorPostsCommentRepository extends JpaRepository<MajorPostsComment, Long> {

    @Query("SELECT M FROM MajorPostsComment M ORDER BY M.id DESC")
    List<MajorPostsComment> findAllDesc();
}
