package com.springsession.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author sky
 * @date 2020/12/15 12:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginSuccessDto implements Serializable {
    private String name;
    private Integer age;
}
