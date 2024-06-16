package ec.com.pablorcruh.gym_management_system.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CampusDTORequest {

    private String name;

    private String ruc;

    private String email;

    private String phoneNumber;
}
