package valens.qt.v1.services.serviceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import valens.qt.v1.dtos.requests.CreatePostDTO;
import valens.qt.v1.exceptions.ForbiddenException;
import valens.qt.v1.exceptions.NotFoundException;
import valens.qt.v1.models.Post;
import valens.qt.v1.repositories.IPostRepository;
import valens.qt.v1.services.IPostService;
import valens.qt.v1.services.IUserService;
import valens.qt.v1.utils.ExceptionsUtils;
import valens.qt.v1.utils.Mapper;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Service implementation class handling post-related operations.
 */
@Service
@RequiredArgsConstructor
public class PostServiceImpl extends ServiceImpl implements IPostService {

    @Autowired
    private final IPostRepository postRepository;
    private final IUserService userService;

    /**
     * Creates a new post.
     * @param postDTO The DTO containing post creation data.
     * @return The created Post object.
     */
    @Override
    @Transactional
    public Post createPost(CreatePostDTO postDTO) {
        try {
            post = Mapper.getPostFromDTO(postDTO);
            user = userService.getLoggedInUser();
            post.setImageUrl(null);
            post.setAuthor(user);
            if (postRepository.existsByAuthorAndContentAndTitle(post.getAuthor(), post.getContent(), post.getTitle())) {
                throw new ForbiddenException("The post already exists, you can't create duplicates");
            }
            return this.postRepository.save(post);
        } catch (Exception exception) {
            ExceptionsUtils.handleServiceExceptions(exception);
            return null;
        }
    }

    /**
     * Updates an existing post.
     * @param postDTO The DTO containing updated post data.
     * @param postId The ID of the post to be updated.
     * @return The updated Post object.
     */
    @Override
    @Transactional
    public Post updatePost(CreatePostDTO postDTO, UUID postId) {
        try {
            if (!postRepository.existsById(postId)) throw new NotFoundException("The post with the provided Id is not found");
            post = postRepository.findById(postId).get();
            post.setTitle(postDTO.getTitle());
            post.setContent(postDTO.getContent());
            post.setImageUrl(null);
            post.setImageUrl(postDTO.getImageUrl());
            return this.postRepository.save(post);
        } catch (Exception exception) {
            ExceptionsUtils.handleServiceExceptions(exception);
            return null;
        }
    }

    /**
     * Deletes an existing post.
     * @param postId The ID of the post to be deleted.
     */
    @Override
    public void deletePost(UUID postId) {
        try {
            if (!postRepository.existsById(postId)) throw new NotFoundException("The post with provided Id is not found");
            post = postRepository.findById(postId).get();
            this.postRepository.delete(post);
        } catch (Exception exception) {
            exception.printStackTrace();
            ExceptionsUtils.handleServiceExceptions(exception);
        }
    }

    /**
     * Retrieves all posts by the logged-in user.
     * @return A set of posts by the logged-in user.
     */
    @Override
    public Set<Post> getMyPosts() {
        try {
            user = this.userService.getLoggedInUser();
            return this.postRepository.findByAuthor(user, sort);
        } catch (Exception exception) {
            ExceptionsUtils.handleServiceExceptions(exception);
        }
        return null;
    }

    /**
     * Retrieves all posts by a specific author.
     * @param authorId The ID of the author.
     * @return A set of posts by the specified author.
     */
    @Override
    public Set<Post> getAllByAuthor(UUID authorId) {
        profile = this.userService.getProfileById(authorId);
        return this.postRepository.findByAuthor(profile, sort);
    }

    /**
     * Retrieves all posts.
     * @return A list of all posts.
     */
    @Override
    public List<Post> getAll() {
        try {
            posts = this.postRepository.findAll(sort);
            user = userService.getLoggedInUser();
            Collections.sort(posts);
            return posts;
        } catch (Exception exception) {
            ExceptionsUtils.handleServiceExceptions(exception);
            return null;
        }
    }

    /**
     * Retrieves paginated posts by the logged-in user.
     * @param pageable The pageable object specifying pagination details.
     * @return A page of posts by the logged-in user.
     */
    @Override
    public Page<Post> getMyPosts(Pageable pageable) {
        try {
            user = userService.getLoggedInUser();
            return this.postRepository.findAllByAuthor(user, pageable);
        } catch (Exception exception) {
            ExceptionsUtils.handleServiceExceptions(exception);
            return null;
        }
    }

    /**
     * Retrieves paginated posts by a specific author.
     * @param authorId The ID of the author.
     * @param pageable The pageable object specifying pagination details.
     * @return A page of posts by the specified author.
     */
    @Override
    public Page<Post> getAllByAuthor(UUID authorId, Pageable pageable) {
        try {
            profile = userService.getProfileById(authorId);
            return this.postRepository.findAllByAuthor(profile, pageable);
        } catch (Exception exception) {
            ExceptionsUtils.handleServiceExceptions(exception);
            return null;
        }
    }

    /**
     * Retrieves all paginated posts.
     * @param pageable The pageable object specifying pagination details.
     * @return A page of all posts.
     */
    @Override
    public Page<Post> getAll(Pageable pageable) {
        try {
            return this.postRepository.findAll(pageable);
        } catch (Exception exception) {
            ExceptionsUtils.handleServiceExceptions(exception);
            return null;
        }
    }

    /**
     * Retrieves a post by its ID.
     * @param postId The ID of the post.
     * @return The Post object with the specified ID.
     */
    @Override
    public Post getById(UUID postId) {
        try {
            return this.postRepository.findById(postId).orElseThrow(() -> new NotFoundException("The post with the provided Id is not found"));
        } catch (Exception exception) {
            exception.printStackTrace();
            ExceptionsUtils.handleServiceExceptions(exception);
            return null;
        }
    }
}
