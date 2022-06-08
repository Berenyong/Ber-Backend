package bssm.ber.domain.entity.posts.comment.repository;

import bssm.ber.domain.entity.posts.comment.ManagerPostsComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ManagerPostsCommentRepository extends JpaRepository<ManagerPostsComment, Long> {

    @Query("SELECT M FROM ManagerPostsComment M ORDER BY M.id DESC")
    List<ManagerPostsComment> findAllDesc();
}
