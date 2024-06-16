package ec.com.pablorcruh.gym_management_system.dto.response;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CampusDTOResponse {

    private UUID id;

    private String name;

    private String ruc;

    private String email;

    private String phoneNumber;

    private Boolean active;

    private List<PartnerDTOResponse> partners;

}
