package com.kakaosecurities.work.mapper;

import com.kakaosecurities.work.dto.BranchInfoDTO;
import com.kakaosecurities.work.dto.CustInfoDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * MyBatis 연동 Mapper
 */
public interface CustTargetMapper {

    public List<CustInfoDTO>  targetAnnualMaxCustomers();
    public List<CustInfoDTO> targetAnnualNoTrdCustomers();
    public List<BranchInfoDTO> targetAnnualAmtBranch();
    public BranchInfoDTO trdSumBranch(@Param("brName") String brName);
    public int mergeBranch();
}
