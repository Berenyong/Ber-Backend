package bssm.ber.domain.posts.comment.repository;

import bssm.ber.domain.posts.comment.ShareMajorPostsComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShareMajorPostsCommentRepository extends JpaRepository<ShareMajorPostsComment, Long> {

    @Query("SELECT S FROM ShareMajorPostsComment S ORDER BY S.id DESC")
    List<ShareMajorPostsComment> findAllDesc();
}
