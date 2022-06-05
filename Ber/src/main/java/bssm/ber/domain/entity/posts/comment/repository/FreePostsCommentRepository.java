package bssm.ber.domain.entity.posts.comment.repository;

import bssm.ber.domain.entity.posts.comment.FreePostsComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FreePostsCommentRepository extends JpaRepository<FreePostsComment, Long> {

    @Query("SELECT f FROM FreePostsComment f ORDER BY f.id DESC")
    List<FreePostsComment> findAllDesc();


}
