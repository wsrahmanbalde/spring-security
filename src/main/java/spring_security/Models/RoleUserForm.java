package spring_security.Models;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleUserForm {
    private String username;
    private String rolename;
}
