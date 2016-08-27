package com.mfed.dto;

import java.util.List;

/**
 * Created by Maciej Fedorowiat
 * on 21/08/2016 19:26.
 * mfedorowiat@gmail.com
 */
public class PathRequestDTO {
    public List<String> path;
    public Point startPoint;

    public PathRequestDTO() {
    }

    public PathRequestDTO(List<String> path) {
        this.path = path;
    }


}
