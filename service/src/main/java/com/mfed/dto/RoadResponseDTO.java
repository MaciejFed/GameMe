package com.mfed.dto;

import java.util.List;

/**
 * Created by Maciej Fedorowiat
 * on 21/08/2016 20:20.
 * mfedorowiat@gmail.com
 */
public class RoadResponseDTO {
    public boolean success;
    public List<MoveDTO> road;

    public RoadResponseDTO() {
    }

    public RoadResponseDTO(List<MoveDTO> road, boolean success) {
        this.success = success;
        this.road = road;
    }
}
