package com.kakaosecurities.work.vo;

import lombok.*;

import java.io.Serializable;

/**
 * 연도별 고객 정보를 출력하기 위한 VO 클래스
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AnnualCustVO implements Serializable {

    private String year;
    private String name;
    private String acctNo;
}
