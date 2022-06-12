package bssm.ber.domain.posts.comment.repository;

import bssm.ber.domain.posts.comment.ManagerPostsComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ManagerPostsCommentRepository extends JpaRepository<ManagerPostsComment, Long> {

    @Query("select mpc from ManagerPostsComment mpc where mpc.managerPosts.id = :id order by mpc.createdDate")
    List<ManagerPostsComment> findAllDesc(@Param("id") Long id);
}
