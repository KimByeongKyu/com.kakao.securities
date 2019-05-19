package com.kakaosecurities.work.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


/**
 * Custom Exception 의 Response 처리를 위한 Exception 전용 VO 클래스
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorVO implements Serializable {
    private String code;
    private String msg;
}
