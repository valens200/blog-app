package valens.qt.v1.services.serviceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import valens.qt.v1.exceptions.NotFoundException;
import valens.qt.v1.models.Role;
import valens.qt.v1.repositories.IRoleRepository;
import valens.qt.v1.services.IRoleService;
import valens.qt.v1.utils.ExceptionsUtils;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {
    private final IRoleRepository roleRepository;
    @Override
    public Role getByRoleName(String roleName) {
        try {
            return this.roleRepository.findByRoleName(roleName).orElseThrow(()-> new NotFoundException("The role with the provided name is not found"));
        }catch (Exception exception){
            ExceptionsUtils.handleServiceExceptions(exception);
            return null;
        }
    }
}
