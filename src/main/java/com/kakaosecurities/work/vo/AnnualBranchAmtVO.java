package com.kakaosecurities.work.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 연도별 지점 거래금액 합계 데이터 리턴을 위한 VO 클래스
 */

@Setter
@Getter
@Builder
@AllArgsConstructor
public class AnnualBranchAmtVO implements Serializable {


    private String year;
    private List<BranchAmtVO> brList;

    public  AnnualBranchAmtVO(){
        this.year = "";
        this.brList = new ArrayList<>();
    }
}
