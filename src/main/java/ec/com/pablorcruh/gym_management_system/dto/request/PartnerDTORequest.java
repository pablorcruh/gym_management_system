package ec.com.pablorcruh.gym_management_system.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartnerDTORequest {

    private String cedula;

    private String fatherLastName;

    private String motherLastName;

    private String firstName;

    private String phoneNumber;

    private String email;
}
