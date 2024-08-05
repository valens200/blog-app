package valens.qt.v1.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import valens.qt.v1.dtos.requests.CreateCommentDTO;
import valens.qt.v1.models.Comment;

import java.util.Set;
import java.util.UUID;

public interface ICommentService {
    public Comment create(CreateCommentDTO commentDTO);
    public Comment update(CreateCommentDTO commentDTO, UUID commentId);
    public void delete(UUID commentId);
    public Set<Comment> getAllByAuthorAndPost(UUID authorId, UUID postId);
    public Set<Comment> getMyCommentsByPost(UUID postId);
    public Set<Comment> getAllByPost(UUID postId);
    public Page<Comment> getAllByAuthorAndPostPaginated(UUID authorId, UUID postId, Pageable pageable);
    public Page<Comment> getMyCommentsByPostPaginated(UUID postId, Pageable pageable);
    public Page<Comment> getAllByPostPaginated(UUID postId, Pageable pageable);

    Comment getById(UUID commentId);
}
