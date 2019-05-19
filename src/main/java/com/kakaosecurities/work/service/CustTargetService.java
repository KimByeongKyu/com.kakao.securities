package com.kakaosecurities.work.service;


import com.kakaosecurities.work.dto.BranchInfoDTO;
import com.kakaosecurities.work.dto.CustInfoDTO;
import com.kakaosecurities.work.exception.CustTargetException;
import com.kakaosecurities.work.mapper.CustTargetMapper;
import com.kakaosecurities.work.vo.AnnualBranchAmtVO;
import com.kakaosecurities.work.vo.AnnualCustAmtVO;
import com.kakaosecurities.work.vo.AnnualCustVO;
import com.kakaosecurities.work.vo.BranchAmtVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 실제 비즈니스 로직 구현을 위한 서비스 클래스
 */
@Service
public class CustTargetService {

    @Autowired
    private CustTargetMapper mapper;


    public List<AnnualCustAmtVO> targetAnnualMaxCustomers(){
        List<CustInfoDTO> targetList = mapper.targetAnnualMaxCustomers();
        List<AnnualCustAmtVO> paramList = new ArrayList<>();

        for(CustInfoDTO dto : targetList){
            AnnualCustAmtVO obj = AnnualCustAmtVO.builder()
                    .acctNo(dto.getAcctNo())
                    .name(dto.getName())
                    .year(dto.getYear())
                    .sumAmt(new BigDecimal(dto.getSumAmt()))
                    .build();
            paramList.add(obj);
        }
        return paramList;
    }

    public List<AnnualCustVO> targetAnnualNoTrdCustomers(){

        List<CustInfoDTO> targetList = mapper.targetAnnualNoTrdCustomers();
        List<AnnualCustVO> paramList = new ArrayList<>();

        for(CustInfoDTO dto : targetList){
            AnnualCustVO obj = AnnualCustVO.builder()
                    .acctNo(dto.getAcctNo())
                    .name(dto.getName())
                    .year(dto.getYear())
                    .build();
            paramList.add(obj);
        }
        return paramList;
    }

    public List<AnnualBranchAmtVO> targetAnnualAmtBranch(){
        List<BranchInfoDTO> targetList = mapper.targetAnnualAmtBranch();
        List<AnnualBranchAmtVO> paramList = new ArrayList<>();

        AnnualBranchAmtVO obj = null;
        String tmpYear = "";

        for(BranchInfoDTO dto : targetList){

            // 다른 year 파싱 시작하는 경우
            // 새로운 객체 사용
            if(!tmpYear.equals(dto.getYear())) {

                if(obj != null) paramList.add(obj);

                obj = new AnnualBranchAmtVO();
                obj.setYear(dto.getYear());
            }

            BranchAmtVO inObj = BranchAmtVO.builder()
                    .brCode(dto.getBrCode())
                    .brName(dto.getBrName())
                    .sumAmt(new BigDecimal(dto.getSumAmt()))
                    .build();
            obj.getBrList().add(inObj);

            tmpYear = dto.getYear();
        }

        if(obj != null) paramList.add(obj);

        return paramList;
    }

    public BranchAmtVO trdSumBranch(String brName) throws CustTargetException {

        BranchInfoDTO target = mapper.trdSumBranch(brName);
        if(target == null){

            // 해당 지점의 정보가 없다면
            // throw Custom Exception!
            throw new CustTargetException("404", "br code not found error");
        }

        BranchAmtVO retParam = BranchAmtVO.builder()
                .brCode(target.getBrCode())
                .brName(target.getBrName())
                .sumAmt(new BigDecimal(target.getSumAmt()))
                .build();

        return retParam;
    }

    public int mergeDept(){
        return mapper.mergeBranch();
    }
}
