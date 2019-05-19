package com.kakaosecurities.work.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


/**
 * 고객정보 교환을 위한 DTO
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustInfoDTO implements Serializable {
    
    private String year;
    private String name;
    private String acctNo;
    private String sumAmt;
}
