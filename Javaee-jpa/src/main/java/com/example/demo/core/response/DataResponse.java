package com.example.demo.core.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DataResponse
 *
 * @author wangkm
 * @date 2020-05-07
 * @since 0.0.1
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DataResponse<T> extends BaseResponse {
    private T data;
}
