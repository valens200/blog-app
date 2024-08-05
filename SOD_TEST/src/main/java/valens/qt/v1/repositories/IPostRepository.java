package valens.qt.v1.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import valens.qt.v1.models.Post;
import valens.qt.v1.models.Profile;

import java.util.TreeSet;
import java.util.UUID;
@Repository
public interface IPostRepository extends JpaRepository<Post, UUID> {
    public boolean existsByAuthorAndContentAndTitle(Profile author, String content, String title);
    public boolean existsById(UUID id);
    TreeSet<Post> findByAuthor(Profile author, Sort sort);
    Page<Post> findAllByAuthor(Profile profile, Pageable pageable);

}
