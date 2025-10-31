package mindTrace.local.Controllers;

import lombok.RequiredArgsConstructor;
import mindTrace.local.Dtos.LoginRequest;
import mindTrace.local.Dtos.SessionDto;
import mindTrace.local.Dtos.UserRegistrationDTO;
import mindTrace.local.Services.AdminService;
import mindTrace.local.Services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final AdminService adminService;

    @PostMapping("/user/register")
    public void registerUser(@RequestBody UserRegistrationDTO dto) {
        userService.registerUser(dto);
    }

    @PostMapping("/user/login")
    public SessionDto loginUser(@RequestBody LoginRequest request) {
        return userService.loginUser(request);
    }

    @PostMapping("/admin/register")
    public void registerAdmin(@RequestBody UserRegistrationDTO dto) {
        adminService.registerAdmin(dto);
    }

    @PostMapping("/admin/login")
    public SessionDto loginAdmin(@RequestBody LoginRequest request) {
        return adminService.loginAdmin(request);
    }


}
