package com.kakaosecurities.work.vo;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 지점별 거래금액 출력을 위한 VO 클래스
 */
@Setter
@Getter
@AllArgsConstructor
@Builder
public class BranchAmtVO implements Serializable {

    private String brName;
    private String brCode;
    private BigDecimal sumAmt;
}
