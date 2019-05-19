package com.kakaosecurities.work.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 지점정보 데이터 교환을 위한 DTO
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BranchInfoDTO implements Serializable {
    private String year;
    private String brCode;
    private String brName;
    private String sumAmt;
}
