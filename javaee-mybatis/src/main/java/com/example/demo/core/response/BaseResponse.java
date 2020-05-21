package com.example.demo.core.response;

import lombok.Data;

/**
 * BaseResonse
 *
 * @author wangkm
 * @date 2020-05-07
 * @since 0.0.1
 */
@Data
public abstract class BaseResponse {
    private int code;
    private String msg = "success";
}
