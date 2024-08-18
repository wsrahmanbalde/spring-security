package spring_security.Controllers;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;
import spring_security.Models.AppRole;
import spring_security.Models.AppUser;
import spring_security.Models.RoleUserForm;
import spring_security.Services.User.AccountService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("all")
    List<AppUser> All(){
        return accountService.findAll();
    }

    @PostMapping("add")
    AppUser add(@RequestBody AppUser user){
        return accountService.addNewUser(user);
    }

    @PostMapping("update")
    AppUser update(@RequestBody AppUser user){
        return accountService.updateUser(user);
    }

    @GetMapping("getUser")
    AppUser getUser(@PathVariable Long id){
        return accountService.findById(id);
    }

    @GetMapping("getUsername")
    AppUser getUsername(String username){
        return accountService.findByUsername(username);
    }

    @GetMapping("getRole")
    AppRole getRole(String rolename){
        return accountService.findByRolename(rolename);
    }

    @PostMapping("addRole")
    AppRole addRole(@RequestBody AppRole role){
        return accountService.addRole(role);
    }

    @PostMapping("addRoleToUser")
    void addRoleToUser(@RequestBody @NotNull RoleUserForm roleUserForm){
        accountService.addRoleToUser(roleUserForm.getUsername(),roleUserForm.getRolename());
    }
}
