package com.mfed.dto;

import java.util.List;

/**
 * Created by Maciej Fedorowiat
 * on 21/08/2016 20:20.
 * mfedorowiat@gmail.com
 */
public class PathResponseDTO {
    public boolean isValid;
    public List<String> path;

    public PathResponseDTO() {
    }

    public PathResponseDTO(boolean isValid, List<String> path) {

        this.isValid = isValid;
        this.path = path;
    }
}
