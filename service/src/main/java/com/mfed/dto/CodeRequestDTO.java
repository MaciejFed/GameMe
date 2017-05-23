package com.mfed.dto;

import java.util.List;

/**
 * Created by Maciej Fedorowiat
 * on 21/08/2016 19:26.
 * mfedorowiat@gmail.com
 */
public class CodeRequestDTO {
    public String code;

    public CodeRequestDTO() {
    }

    public CodeRequestDTO(String code) {
        this.code = code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
