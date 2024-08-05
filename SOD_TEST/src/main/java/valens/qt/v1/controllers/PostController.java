package valens.qt.v1.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import valens.qt.v1.dtos.requests.CreatePostDTO;
import valens.qt.v1.models.Post;
import valens.qt.v1.payload.ApiResponse;
import valens.qt.v1.services.IPostService;
import valens.qt.v1.utils.Constants;
import valens.qt.v1.utils.ExceptionsUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController  extends Controller{
    private final IPostService postService;

    @PostMapping("create")
    public ResponseEntity<ApiResponse> createPost(@RequestBody CreatePostDTO postDTO) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true,"The post was created successfully",this.postService.createPost(postDTO)));
        }catch (Exception exception){
            return ExceptionsUtils.handleControllerExceptions(exception);
        }
    }
    @PutMapping("update/{postId}")
    public ResponseEntity<ApiResponse> updatePost(@RequestBody CreatePostDTO postDTO, @PathVariable("postId") UUID postId) {
        try {
            return ResponseEntity.ok(new ApiResponse(true,"The post was updated successfully", this.postService.updatePost(postDTO,postId)));
        }catch (Exception exception){
            return ExceptionsUtils.handleControllerExceptions(exception);
        }
    }
    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable("postId") UUID postId) {
        try {
            this.postService.deletePost(postId);
            return ResponseEntity.ok(new ApiResponse(true,"The post was deleted successfully"));
        }catch (Exception exception){
           return ExceptionsUtils.handleControllerExceptions(exception);
        }
    }
    @GetMapping("all/mine")
    @ApiOperation("Retrieves all posts for loggedIn user")
    public ResponseEntity<ApiResponse> getMyPosts() {
        try{
            return ResponseEntity.ok(new ApiResponse(true,"Get all posts for loggedIn user", this.postService.getAll()));
        }catch (Exception exception){
           return ExceptionsUtils.handleControllerExceptions(exception);
        }
    }
    @GetMapping("all/by-author/{authorId}")
    public ResponseEntity<ApiResponse> getAllByAuthor(@PathVariable("authorId") UUID authorId) {
        try {
            return ResponseEntity.ok(new ApiResponse(true,"All posts was retrieved successfully", postService.getAllByAuthor(authorId)));
        }catch (Exception exception){
           return ExceptionsUtils.handleControllerExceptions(exception);
        }
    }
    @GetMapping("all")
    public ResponseEntity<ApiResponse> getAll(){
        try {
            return ResponseEntity.ok(new ApiResponse(true,"All posts were retrieved successfully", postService.getAll()));
        }catch (Exception exception){
            return ExceptionsUtils.handleControllerExceptions(exception);
        }
    }
    @GetMapping("all/mine-paginated")
    public ResponseEntity<ApiResponse> gePostsForLoggedInUser(@RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page, @RequestParam(value = "limit" , defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit) {
        try {
            pageable = PageRequest.of(page,limit, Sort.Direction.DESC,"created_at");
            return ResponseEntity.ok(new ApiResponse(true,"Your posts were retrieved successfully", this.postService.getMyPosts()));
        }catch (Exception exception){
            return ExceptionsUtils.handleControllerExceptions(exception);
        }
    }
    @GetMapping("/all/by-author/paginated/{authorId}")
    public ResponseEntity<ApiResponse> getAllByAuthorPaginated(@PathVariable("authorId") UUID authorId, @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page, @RequestParam(value = "limit" , defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit) {
        try{
            pageable = PageRequest.of(page,limit, Sort.Direction.DESC,"createdAt");
            return ResponseEntity.ok(new ApiResponse(true,"Posts were retrieved successfully", this.postService.getAllByAuthor(authorId,pageable)));
        }catch (Exception exception){
            return ExceptionsUtils.handleControllerExceptions(exception);
        }
    }
    @GetMapping("all/paginated")
    public ResponseEntity<ApiResponse> getAll(@RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page, @RequestParam(value = "limit" , defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit) {
        try {
            pageable = PageRequest.of(page,limit, Sort.Direction.DESC,"createdAt");
            return ResponseEntity.ok(new ApiResponse(true,"All posts were retrieved successfully",this.postService.getAll(pageable)));
        }catch (Exception exception){
            return ExceptionsUtils.handleControllerExceptions(exception);
        }
    }
    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse> getById(@PathVariable("postId") UUID postId) {
        try {
            Post post =  this.postService.getById(postId);
            Collections.sort(post.getComments());
            return ResponseEntity.ok(new ApiResponse(true,"The post was retrieved successfully",post));
        }catch (Exception exception){
            ExceptionsUtils.handleServiceExceptions(exception);
            return null;
        }
    }
}
