package valens.qt.v1.utils.helpers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import valens.qt.v1.exceptions.BadRequestException;
import valens.qt.v1.models.Profile;
import valens.qt.v1.models.enums.EGender;
import valens.qt.v1.services.IUserService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Service
@Slf4j
public class Helper {

    private static IUserService userService;

    @Autowired
    public Helper(
            IUserService userService){
        Helper.userService = userService;
    }

    public Profile getLoggedInUser(HttpServletRequest request){
        return  null;
    }

    public static String getActor(){
        return "Done at : " + LocalDateTime.now() +  " By Email : " +  userService.getLoggedInUser().getEmail();
    }
    public static void logAction(String message){
        log.info(message);
        log.info(getActor());
    }
    public static EGender getGender(String gender){
        System.out.println("Gender : " + gender);
        switch (gender.toUpperCase()){
            case "MALE", "M":
                return EGender.MALE;
            case "FEMALE", "F":
                return EGender.FEMALE;
            case "OTHER":
                return  EGender.OTHER;
            default:
                throw new BadRequestException("The provided gender is invalid");
        }
    }

}
