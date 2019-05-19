package com.kakaosecurities.work.vo;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 연도별 고객의 거래금액을 출력하기 위한 VO 클래스
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnnualCustAmtVO implements Serializable {
    private String year;
    private String name;
    private String acctNo;
    private BigDecimal sumAmt;
}
