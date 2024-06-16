package ec.com.pablorcruh.gym_management_system.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CampusDTORequest {

    private String name;

    private String ruc;

    private String email;

    private String phoneNumber;
}
