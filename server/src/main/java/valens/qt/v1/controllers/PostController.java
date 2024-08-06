package valens.qt.v1.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import valens.qt.v1.dtos.requests.CreatePostDTO;
import valens.qt.v1.models.Post;
import valens.qt.v1.models.Profile;
import valens.qt.v1.payload.ApiResponse;
import valens.qt.v1.services.IPostService;
import valens.qt.v1.services.IUserService;
import valens.qt.v1.utils.Constants;
import valens.qt.v1.utils.ExceptionsUtils;

import java.util.Collections;
import java.util.UUID;

/**
 * Controller class handling post-related endpoints.
 */
@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController extends Controller{

    private final IPostService postService;
    private final IUserService userService;

    /**
     * Endpoint for creating a new post.
     * @param postDTO The DTO containing post creation data.
     * @return ResponseEntity containing ApiResponse with the result of the post creation.
     */
    @PostMapping("create")
    public ResponseEntity<ApiResponse> createPost(@RequestBody CreatePostDTO postDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "The post was created successfully", this.postService.createPost(postDTO)));
        } catch (Exception exception) {
            return ExceptionsUtils.handleControllerExceptions(exception);
        }
    }

    /**
     * Endpoint for updating an existing post.
     * @param postDTO The DTO containing updated post data.
     * @param postId The ID of the post to be updated.
     * @return ResponseEntity containing ApiResponse with the result of the post update.
     */
    @PutMapping("update/{postId}")
    public ResponseEntity<ApiResponse> updatePost(@RequestBody CreatePostDTO postDTO, @PathVariable("postId") UUID postId) {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "The post was updated successfully", this.postService.updatePost(postDTO, postId)));
        } catch (Exception exception) {
            return ExceptionsUtils.handleControllerExceptions(exception);
        }
    }

    /**
     * Endpoint for deleting an existing post.
     * @param postId The ID of the post to be deleted.
     * @return ResponseEntity containing ApiResponse with the result of the post deletion.
     */
    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable("postId") UUID postId) {
        try {
            this.postService.deletePost(postId);
            return ResponseEntity.ok(new ApiResponse(true, "The post was deleted successfully"));
        } catch (Exception exception) {
            return ExceptionsUtils.handleControllerExceptions(exception);
        }
    }

    /**
     * Endpoint for retrieving all posts of the logged-in user.
     * @return ResponseEntity containing ApiResponse with the list of posts.
     */
    @GetMapping("all/mine")
    @ApiOperation("Retrieves all posts for the logged-in user")
    public ResponseEntity<ApiResponse> getMyPosts() {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Get all posts for the logged-in user", this.postService.getAll()));
        } catch (Exception exception) {
            return ExceptionsUtils.handleControllerExceptions(exception);
        }
    }

    /**
     * Endpoint for retrieving all posts by a specific author.
     * @param authorId The ID of the author.
     * @return ResponseEntity containing ApiResponse with the list of posts.
     */
    @GetMapping("all/by-author/{authorId}")
    public ResponseEntity<ApiResponse> getAllByAuthor(@PathVariable("authorId") UUID authorId) {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "All posts were retrieved successfully", postService.getAllByAuthor(authorId)));
        } catch (Exception exception) {
            return ExceptionsUtils.handleControllerExceptions(exception);
        }
    }

    /**
     * Endpoint for retrieving all posts.
     * @return ResponseEntity containing ApiResponse with the list of all posts.
     */
    @GetMapping("all")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<ApiResponse> getAll() {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "All posts were retrieved successfully", postService.getAll()));
        } catch (Exception exception) {
            return ExceptionsUtils.handleControllerExceptions(exception);
        }
    }

    /**
     * Endpoint for retrieving paginated posts of the logged-in user.
     * @param page The page number to retrieve.
     * @param limit The number of posts per page.
     * @return ResponseEntity containing ApiResponse with the paginated list of posts.
     */
    @GetMapping("all/mine-paginated")
    public ResponseEntity<ApiResponse> gePostsForLoggedInUser(@RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page, @RequestParam(value = "limit", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit) {
        try {
            pageable = PageRequest.of(page, limit, Sort.Direction.DESC, "created_at");
            return ResponseEntity.ok(new ApiResponse(true, "Your posts were retrieved successfully", this.postService.getMyPosts()));
        } catch (Exception exception) {
            return ExceptionsUtils.handleControllerExceptions(exception);
        }
    }

    /**
     * Endpoint for retrieving paginated posts by a specific author.
     * @param authorId The ID of the author.
     * @param page The page number to retrieve.
     * @param limit The number of posts per page.
     * @return ResponseEntity containing ApiResponse with the paginated list of posts.
     */
    @GetMapping("/all/by-author/paginated/{authorId}")
    public ResponseEntity<ApiResponse> getAllByAuthorPaginated(@PathVariable("authorId") UUID authorId, @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page, @RequestParam(value = "limit", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit) {
        try {
            pageable = PageRequest.of(page, limit, Sort.Direction.DESC, "createdAt");
            return ResponseEntity.ok(new ApiResponse(true, "Posts were retrieved successfully", this.postService.getAllByAuthor(authorId, pageable)));
        } catch (Exception exception) {
            return ExceptionsUtils.handleControllerExceptions(exception);
        }
    }

    /**
     * Endpoint for retrieving all paginated posts.
     * @param page The page number to retrieve.
     * @param limit The number of posts per page.
     * @return ResponseEntity containing ApiResponse with the paginated list of all posts.
     */
    @GetMapping("all/paginated")
    public ResponseEntity<ApiResponse> getAll(@RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page, @RequestParam(value = "limit", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit) {
        try {
            pageable = PageRequest.of(page, limit, Sort.Direction.DESC, "createdAt");
            return ResponseEntity.ok(new ApiResponse(true, "All posts were retrieved successfully", this.postService.getAll(pageable)));
        } catch (Exception exception) {
            return ExceptionsUtils.handleControllerExceptions(exception);
        }
    }

    /**
     * Endpoint for retrieving a post by its ID.
     * @param postId The ID of the post.
     * @return ResponseEntity containing ApiResponse with the post data.
     */
    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse> getById(@PathVariable("postId") UUID postId) {
        try {
            Post post = this.postService.getById(postId);
            Profile user = userService.getLoggedInUser();
            if (post.getAuthor().equals(user)) {
                post.setIsOwner("T");
            }
            Collections.sort(post.getComments());
            return ResponseEntity.ok(new ApiResponse(true, "The post was retrieved successfully", post));
        } catch (Exception exception) {
            ExceptionsUtils.handleServiceExceptions(exception);
            return null;
        }
    }
}
