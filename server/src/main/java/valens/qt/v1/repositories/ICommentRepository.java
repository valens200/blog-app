package valens.qt.v1.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import valens.qt.v1.models.Comment;
import valens.qt.v1.models.Post;
import valens.qt.v1.models.Profile;

import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
@Repository
public interface ICommentRepository extends JpaRepository<Comment, UUID> {
    public boolean existsByAuthorAndContentAndTitleAndPost(Profile author, String content, String title, Post post);
    public TreeSet<Comment> findAllByAuthorAndPost(Profile author, Post post);
    public Page<Comment> findAllByAuthorAndPost(Profile author, Post post, Pageable pageable);
    public Set<Comment> findAllByPost(Post post);
    public Page<Comment> findAllByPost(Post post, Pageable pageable);
}
