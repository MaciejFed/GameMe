package com.mfed.dto;

/**
 * Created by Maciej Fedorowiat
 * on 20/03/2017 15:52.
 * mfedorowiat@gmail.com
 */
public class BlockDTO {
    private BlockDTO blockDTO;
    private String data;
    public BlockDTO(){

    }

    public void setData(String data) {
        this.data = data;
    }

    public void setBlockDTO(BlockDTO blockDTO) {
        this.blockDTO = blockDTO;
    }

    public BlockDTO getBlockDTO() {
        return blockDTO;
    }

    public String getData() {
        return data;
    }
}
