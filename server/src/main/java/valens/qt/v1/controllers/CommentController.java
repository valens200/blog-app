package valens.qt.v1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import valens.qt.v1.dtos.requests.CreateCommentDTO;
import valens.qt.v1.payload.ApiResponse;
import valens.qt.v1.services.ICommentService;
import valens.qt.v1.utils.Constants;
import valens.qt.v1.utils.ExceptionsUtils;

import javax.validation.Valid;
import java.util.UUID;

/**
 * Controller class handling comment-related endpoints.
 */
@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController extends Controller {

    private final ICommentService commentService;

    /**
     * Endpoint for creating a new comment.
     * @param commentDTO The DTO containing comment creation data.
     * @return ResponseEntity containing ApiResponse with the result of the comment creation.
     */
    @PostMapping("create")
    public ResponseEntity<ApiResponse> create(@RequestBody @Valid CreateCommentDTO commentDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "The comment was created successfully", this.commentService.create(commentDTO)));
        } catch (Exception exception) {
            return ExceptionsUtils.handleControllerExceptions(exception);
        }
    }

    /**
     * Endpoint for updating an existing comment.
     * @param commentDTO The DTO containing updated comment data.
     * @param commentId The ID of the comment to be updated.
     * @return ResponseEntity containing ApiResponse with the result of the comment update.
     */
    @PutMapping("update/{commentId}")
    public ResponseEntity<ApiResponse> update(@RequestBody CreateCommentDTO commentDTO, @PathVariable("commentId") UUID commentId) {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "The comment was updated successfully", this.commentService.update(commentDTO, commentId)));
        } catch (Exception exception) {
            return ExceptionsUtils.handleControllerExceptions(exception);
        }
    }

    /**
     * Endpoint for deleting an existing comment.
     * @param commentId The ID of the comment to be deleted.
     * @return ResponseEntity containing ApiResponse with the result of the comment deletion.
     */
    @DeleteMapping("delete/{commentId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("commentId") UUID commentId) {
        try {
            this.commentService.delete(commentId);
            return ResponseEntity.ok(new ApiResponse(true, "The comment was deleted successfully"));
        } catch (Exception exception) {
            return ExceptionsUtils.handleControllerExceptions(exception);
        }
    }

    /**
     * Endpoint for retrieving all comments by a specific author and post.
     * @param authorId The ID of the author.
     * @param postId The ID of the post.
     * @return ResponseEntity containing ApiResponse with the list of comments.
     */
    @GetMapping("all/author/post")
    public ResponseEntity<ApiResponse> getAllByAuthorAndPost(@RequestParam("authorId") UUID authorId, @RequestParam("postId") UUID postId) {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "The comments were retrieved successfully", this.commentService.getAllByAuthorAndPost(authorId, postId)));
        } catch (Exception exception) {
            return ExceptionsUtils.handleControllerExceptions(exception);
        }
    }

    /**
     * Endpoint for retrieving all comments by the logged-in user for a specific post.
     * @param postId The ID of the post.
     * @return ResponseEntity containing ApiResponse with the list of comments.
     */
    @GetMapping("all/mine/by-post")
    public ResponseEntity<ApiResponse> getMyCommentsByPost(@RequestParam("postId") UUID postId) {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Comments were retrieved successfully", this.commentService.getMyCommentsByPost(postId)));
        } catch (Exception exception) {
            return ExceptionsUtils.handleControllerExceptions(exception);
        }
    }

    /**
     * Endpoint for retrieving all comments by post ID.
     * @param postId The ID of the post.
     * @return ResponseEntity containing ApiResponse with the list of comments.
     */
    @GetMapping("/by-post/{postId}")
    public ResponseEntity<ApiResponse> getAllByPost(@PathVariable("postId") UUID postId) {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "The comments were retrieved successfully", this.commentService.getAllByPost(postId)));
        } catch (Exception exception) {
            return ExceptionsUtils.handleControllerExceptions(exception);
        }
    }

    /**
     * Endpoint for retrieving paginated comments by a specific author and post.
     * @param authorId The ID of the author.
     * @param postId The ID of the post.
     * @param page The page number to retrieve.
     * @param limit The number of comments per page.
     * @return ResponseEntity containing ApiResponse with the paginated list of comments.
     */
    @GetMapping("all/by-author-and-post")
    public ResponseEntity<ApiResponse> getAllByAuthorAndPostPaginated(@RequestParam("authorId") UUID authorId, @RequestParam("postId") UUID postId, @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page, @RequestParam(value = "limit", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit) {
        try {
            pageable = PageRequest.of(page, limit, Sort.Direction.DESC, "createdAt");
            return ResponseEntity.ok(new ApiResponse(true, "Comments were retrieved successfully", this.commentService.getAllByAuthorAndPostPaginated(authorId, postId, pageable)));
        } catch (Exception exception) {
            return ExceptionsUtils.handleControllerExceptions(exception);
        }
    }

    /**
     * Endpoint for retrieving paginated comments by the logged-in user for a specific post.
     * @param postId The ID of the post.
     * @param page The page number to retrieve.
     * @param limit The number of comments per page.
     * @return ResponseEntity containing ApiResponse with the paginated list of comments.
     */
    @GetMapping("all/mine")
    public ResponseEntity<ApiResponse> getMyCommentsByPostPaginated(@RequestParam("postId") UUID postId, @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page, @RequestParam(value = "limit", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit) {
        try {
            pageable = PageRequest.of(page, limit, Sort.Direction.DESC, "createdAt");
            return ResponseEntity.ok(new ApiResponse(true, "Your comments were retrieved successfully", this.commentService.getMyCommentsByPostPaginated(postId, pageable)));
        } catch (Exception exception) {
            return ExceptionsUtils.handleControllerExceptions(exception);
        }
    }

    /**
     * Endpoint for retrieving paginated comments by post ID.
     * @param postId The ID of the post.
     * @param page The page number to retrieve.
     * @param limit The number of comments per page.
     * @return ResponseEntity containing ApiResponse with the paginated list of comments.
     */
    @GetMapping("all/by-post")
    public ResponseEntity<ApiResponse> getAllByPostPaginated(@RequestParam("postId") UUID postId, @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page, @RequestParam(value = "limit", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit) {
        try {
            pageable = PageRequest.of(page, limit, Sort.Direction.DESC, "createdAt");
            return ResponseEntity.ok(new ApiResponse(true, "Paginated comments retrieved successfully", this.commentService.getAllByPostPaginated(postId, pageable)));
        } catch (Exception exception) {
            return ExceptionsUtils.handleControllerExceptions(exception);
        }
    }

    /**
     * Endpoint for retrieving a comment by its ID.
     * @param commentId The ID of the comment.
     * @return ResponseEntity containing ApiResponse with the comment data.
     */
    @GetMapping("/{commentId}")
    public ResponseEntity<ApiResponse> getById(@PathVariable("commentId") UUID commentId) {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Comment retrieved successfully", this.commentService.getById(commentId)));
        } catch (Exception exception) {
            return ExceptionsUtils.handleControllerExceptions(exception);
        }
    }
}
