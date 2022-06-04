package bssm.ber.domain.entity.posts.comment.repository;

import bssm.ber.domain.entity.posts.comment.FreePostsComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreePostsCommentRepository extends JpaRepository<FreePostsComment, Long> {
}
