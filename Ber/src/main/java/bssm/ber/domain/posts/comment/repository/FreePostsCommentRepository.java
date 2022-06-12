package bssm.ber.domain.posts.comment.repository;

import bssm.ber.domain.posts.comment.FreePostsComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FreePostsCommentRepository extends JpaRepository<FreePostsComment, Long> {

//    @Query("select fpc from FreePostsComment fpc where fpc.freePosts.id = :id")
    @Query("select fpc from FreePostsComment fpc where fpc.freePosts.id = :id order by fpc.createdDate")
    List<FreePostsComment> findAllDesc(@Param("id") Long id);

}
