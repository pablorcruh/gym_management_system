package ec.com.pablorcruh.gym_management_system.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MainCompanyDTOResponse {

    private UUID id;

    private String name;

    private String ruc;

    private String email;

    private String phoneNumber;

    private List<CampusDTOResponse> campuses;
}
