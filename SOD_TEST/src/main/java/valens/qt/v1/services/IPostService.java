package valens.qt.v1.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import valens.qt.v1.dtos.requests.CreatePostDTO;
import valens.qt.v1.models.Post;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface IPostService {
    public Post createPost(CreatePostDTO postDTO);

    public Post updatePost(CreatePostDTO postDTO, UUID postId);
    public void deletePost(UUID postId);

    public Set<Post> getMyPosts();
    public Set<Post> getAllByAuthor(UUID authorId);
    public List<Post> getAll();

    public Page<Post> getMyPosts(Pageable pageable);
    public Page<Post> getAllByAuthor(UUID authorId, Pageable pageable);
    public Page<Post> getAll(Pageable pageable);
    public Post getById(UUID postId);

}
