package com.kakaosecurities.work.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Custom Exception 핸들링을 위한 Exception 상속 커스텀 클래스
 *
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class CustTargetException extends Exception {

    private String code;
    private String msg;
}
