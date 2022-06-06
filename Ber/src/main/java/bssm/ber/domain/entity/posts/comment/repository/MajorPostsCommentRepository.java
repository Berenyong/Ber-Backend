package bssm.ber.domain.entity.posts.comment.repository;

import bssm.ber.domain.entity.posts.comment.FreePostsComment;
import bssm.ber.domain.entity.posts.comment.MajorPostsComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MajorPostsCommentRepository extends JpaRepository<MajorPostsComment, Long> {

    @Query("SELECT M FROM MajorPostsComment M ORDER BY M.id DESC")
    List<MajorPostsComment> findAllDesc();
}
