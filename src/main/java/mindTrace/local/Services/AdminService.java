package mindTrace.local.Services;

import mindTrace.local.Dtos.LoginRequest;
import mindTrace.local.Dtos.SessionDto;
import mindTrace.local.Dtos.UserRegistrationDTO;

public interface AdminService {
    void registerAdmin(UserRegistrationDTO dto);
    SessionDto loginAdmin(LoginRequest request);
}
