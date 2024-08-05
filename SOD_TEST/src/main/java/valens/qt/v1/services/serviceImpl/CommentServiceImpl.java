package valens.qt.v1.services.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import valens.qt.v1.dtos.requests.CreateCommentDTO;
import valens.qt.v1.exceptions.ForbiddenException;
import valens.qt.v1.exceptions.NotFoundException;
import valens.qt.v1.models.Comment;
import valens.qt.v1.repositories.ICommentRepository;
import valens.qt.v1.services.ICommentService;
import valens.qt.v1.services.IPostService;
import valens.qt.v1.services.IUserService;
import valens.qt.v1.utils.ExceptionsUtils;
import valens.qt.v1.utils.Mapper;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl extends ServiceImpl implements ICommentService {
    private final ICommentRepository commentRepository;
    private final IUserService userService;
    private final IPostService postService;
    @Override
    public Comment create(CreateCommentDTO commentDTO) {
        try {
            user = this.userService.getLoggedInUser();
            post = postService.getById(commentDTO.getPostId());
            if(commentRepository.existsByAuthorAndContentAndTitleAndPost(user,commentDTO.getContent(),commentDTO.getTitle(),post)){
                throw new ForbiddenException("The same comment is already registered, you can't create duplicates");
            }
            comment = new Comment(commentDTO.getTitle(), commentDTO.getContent());
            comment.setAuthor(user);
            comment.setPost(post);
            return commentRepository.save(comment);
        }catch (Exception exception){
            ExceptionsUtils.handleServiceExceptions(exception);
            return null;
        }
    }

    @Override
    public Comment update(CreateCommentDTO commentDTO, UUID commentId) {
        try {
            comment = this.getById(commentId);
            comment.setTitle(commentDTO.getTitle());
            comment.setContent(commentDTO.getContent());
            return commentRepository.save(comment);
        }catch (Exception exception){
            ExceptionsUtils.handleServiceExceptions(exception);
            return null;
        }
    }

    @Override
    public void delete(UUID commentId) {
        try {
            comment = this.getById(commentId);
            this.commentRepository.delete(comment);
        }catch (Exception exception){
            ExceptionsUtils.handleServiceExceptions(exception);
        }

    }

    @Override
    public Set<Comment> getAllByAuthorAndPost(UUID authorId, UUID postId) {
        try {
            post = postService.getById(postId);
            profile = userService.getProfileById(authorId);
            return this.commentRepository.findAllByAuthorAndPost(profile,post);
        }catch (Exception exception){
            ExceptionsUtils.handleServiceExceptions(exception);
            return null;
        }
    }

    @Override
    public Set<Comment> getMyCommentsByPost(UUID postId) {
        try {
            post = postService.getById(postId);
            user = userService.getLoggedInUser();
            return this.commentRepository.findAllByAuthorAndPost(user,post);
        }catch (Exception exception){
            ExceptionsUtils.handleServiceExceptions(exception);
            return null;
        }
    }

    @Override
    public Set<Comment> getAllByPost(UUID postId) {
        try {
            post = postService.getById(postId);
            return this.commentRepository.findAllByPost(post);
        }catch (Exception exception){
            return null;
        }
    }

    @Override
    public Page<Comment> getAllByAuthorAndPostPaginated(UUID authorId, UUID postId, Pageable pageable) {
        try {
            post = postService.getById(postId);
            profile = userService.getProfileById(authorId);
            return this.commentRepository.findAllByAuthorAndPost(profile,post,pageable);
        }catch (Exception exception){
            return null;
        }
    }

    @Override
    public Page<Comment> getMyCommentsByPostPaginated(UUID postId, Pageable pageable) {
        try {
            post = postService.getById(postId);
            user = userService.getLoggedInUser();
            return this.commentRepository.findAllByAuthorAndPost(user,post,pageable);
        }catch (Exception exception){
            return null;
        }
    }

    @Override
    public Page<Comment> getAllByPostPaginated(UUID postId, Pageable pageable) {
        try {
            post = postService.getById(postId);
            return this.commentRepository.findAllByPost(post,pageable);
        }catch (Exception exception){
            return null;
        }
    }
    @Override
    public Comment getById(UUID commentId){
        try{
            return this.commentRepository.findById(commentId).orElseThrow(() -> new NotFoundException("The comment with the provided Id is not found"));
        }catch (Exception exception){
            ExceptionsUtils.handleServiceExceptions(exception);
            return null;
        }
    }
}
