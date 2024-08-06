package valens.qt.v1.services;

import valens.qt.v1.models.Role;

public interface IRoleService {
    public Role getByRoleName(String roleName);
}
