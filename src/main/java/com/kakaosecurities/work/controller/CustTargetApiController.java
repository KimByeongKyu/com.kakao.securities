package com.kakaosecurities.work.controller;

import com.kakaosecurities.work.exception.CustTargetException;
import com.kakaosecurities.work.service.CustTargetService;
import com.kakaosecurities.work.vo.AnnualBranchAmtVO;
import com.kakaosecurities.work.vo.AnnualCustAmtVO;
import com.kakaosecurities.work.vo.AnnualCustVO;
import com.kakaosecurities.work.vo.BranchAmtVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 고객 마케팅을 위한 Api Controller
 */
@Controller
@Api(value="고객 마케팅을 위한 Api")
public class CustTargetApiController {

    @Autowired
    private CustTargetService service;

    @ApiOperation(value = "Swagger UI(Api 테스트 페이지) - Redirect")
    @GetMapping(value="/")
    public String home(){

        return "targetCust";
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "정상적으로 조회되었습니다."),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Server Error")
            }
     )
    @ApiOperation(value = "2018년, 2019년 각 연도별 합계금액이 가장 많은 고객을 조회합니다. 거래금액은 수수료를 제외한 금액입니다.")
    @GetMapping(value="/targetAnnualMaxCustomers")
    @ResponseBody
    public List<AnnualCustAmtVO> targetAnnualMaxCustomers (){

        return service.targetAnnualMaxCustomers();
    }


    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "정상적으로 조회되었습니다."),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Server Error")
    })
    @ApiOperation(value = "2018년 또는 2019년에 거래가 없는 고객을 조회합니다.")
    @GetMapping(value="/targetAnnualNoTrdCustomers")
    @ResponseBody
    public List<AnnualCustVO> targetAnnualNoTrdCustomers (){


        return service.targetAnnualNoTrdCustomers();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "정상적으로 조회되었습니다."),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Server Error")
    })
    @ApiOperation(value = "연도별 관리점 별 거래금액의 합계를 조회합니다. 합계금액이 큰 순서대로 출력됩니다.")
    @GetMapping(value="/targetAnnualAmtBranch")
    @ResponseBody
    public List<AnnualBranchAmtVO> targetAnnualAmtBranch (){


        return service.targetAnnualAmtBranch();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "정상적으로 조회되었습니다."),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Server Error")
    })
    @ApiOperation(value = "입력받은 관리점의 거래금액 합계를 출력합니다. 입력 타입은 JSON 입니다.")
    @PostMapping(value="/trdSumBranch")
    @ResponseBody
    public BranchAmtVO trdSumBranch (@RequestBody Map<String, Object> param) throws CustTargetException {

        BranchAmtVO vo = service.trdSumBranch((String) param.get("brName"));

        return vo;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "정상적으로 지점이 병합되었습니다(분당점 to 판교점)."),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Server Error")
    })
    @ApiOperation(value = "분당점을 판교점으로 지점 통폐합하는 요청입니다. 해당 Api 호출 후에는 관리점이 분당점인 계좌의 관리점이 판교점으로 변경됩니다. 변경된 계좌의 갯수가 리턴됩니다.")
    @GetMapping(value="/mergeDept")
    @ResponseBody
    public int mergeDept() {
        return service.mergeDept();
    }
}
