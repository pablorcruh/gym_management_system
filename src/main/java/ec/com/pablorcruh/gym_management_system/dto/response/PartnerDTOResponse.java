package ec.com.pablorcruh.gym_management_system.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartnerDTOResponse {

    private UUID id;

    private String cedula;

    private String fatherLastName;

    private String motherLastName;

    private String firstName;

    private String phoneNumber;

    private String email;
}
