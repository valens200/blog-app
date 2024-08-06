package valens.qt.v1.utils;
import org.modelmapper.ModelMapper;
import valens.qt.v1.models.Comment;
import valens.qt.v1.models.Post;
import valens.qt.v1.models.Profile;
import valens.qt.v1.models.User;

public class Mapper {
    public static ModelMapper modelMapper = new ModelMapper();

    public static Profile getProfileFromDTO(Object object){
        return modelMapper.map(object, Profile.class);
    }
    public static User getUserFromDTO(Object o){
        return modelMapper.map(o, User.class);
    }
    public static Post getPostFromDTO(Object object){
        return modelMapper.map(object, Post.class);
    }

    public static Comment getCommentFromDTO(Object object){
        return modelMapper.map(object,Comment.class);
    }

}
