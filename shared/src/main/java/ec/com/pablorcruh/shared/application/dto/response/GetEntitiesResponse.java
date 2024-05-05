package ec.com.pablorcruh.shared.application.dto.response;

import ec.com.pablorcruh.shared.application.dto.PaginationResponse;
import lombok.Getter;

import java.util.List;

@Getter
public class GetEntitiesResponse {
    private final List entities;
    private PaginationResponse pagination;

    public GetEntitiesResponse(List entities, PaginationResponse pagination) {
        this.entities = entities;
        this.pagination = pagination;
    }
}
