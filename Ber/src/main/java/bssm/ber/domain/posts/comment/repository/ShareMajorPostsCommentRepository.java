package bssm.ber.domain.posts.comment.repository;

import bssm.ber.domain.posts.comment.ShareMajorPostsComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShareMajorPostsCommentRepository extends JpaRepository<ShareMajorPostsComment, Long> {

    @Query("select smpc from ShareMajorPostsComment smpc where ShareMajorPosts.id = :id")
    List<ShareMajorPostsComment> findAllDesc(@Param("id") Long id);
}
