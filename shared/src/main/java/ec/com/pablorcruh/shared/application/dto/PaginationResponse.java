package ec.com.pablorcruh.shared.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationResponse {
    private Integer page;
    private Integer pageSize;
    private Integer total;

    public PaginationResponse(Integer page, Integer pageSize, Integer total) {
        this.page = page;
        this.pageSize = pageSize;
        this.total = total;
    }
}
