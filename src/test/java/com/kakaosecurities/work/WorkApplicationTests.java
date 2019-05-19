package com.kakaosecurities.work;

import com.kakaosecurities.work.controller.CustTargetApiController;
import com.kakaosecurities.work.service.CustTargetService;
import com.kakaosecurities.work.vo.AnnualBranchAmtVO;
import com.kakaosecurities.work.vo.AnnualCustAmtVO;
import com.kakaosecurities.work.vo.AnnualCustVO;
import com.kakaosecurities.work.vo.BranchAmtVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(CustTargetApiController.class)
public class WorkApplicationTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private CustTargetService targetService;

	@Test
	public void targetAnnualMaxCustomers_Test() throws Exception{


		// 기대되는 객체 생성
		List<AnnualCustAmtVO> list =  new ArrayList<>();
		list.add(new AnnualCustAmtVO("2018", "테드", "11111114", new BigDecimal("28992000")));
		list.add(new AnnualCustAmtVO("2019", "에이스", "11111112", new BigDecimal("40998400")));

		// 해당 서비스 호출 시, 리턴될 기대값 설정
		given(targetService.targetAnnualMaxCustomers()).willReturn(list);

		// 확인
		mvc.perform(
				get("/targetAnnualMaxCustomers")
						.contentType(MediaType.APPLICATION_JSON_UTF8)
		)
				.andExpect(status().isOk())
				.andExpect(handler().methodName("targetAnnualMaxCustomers"))		// 호출 서비스 메서드 네임 검증
				.andExpect(handler().handlerType(CustTargetApiController.class))	// 컨트롤러 클래스 검증
				.andExpect(jsonPath("$[0].year", is(equalTo("2018"))))	// 값 검증
				.andExpect(jsonPath("$[0].name", is(equalTo("테드"))))
				.andExpect(jsonPath("$[0].acctNo", is(equalTo("11111114"))))
				.andExpect(jsonPath("$[1].year", is(equalTo("2019"))))
				.andExpect(jsonPath("$[1].name", is(equalTo("에이스"))))
				.andExpect(jsonPath("$[1].acctNo", is(equalTo("11111112"))))
				.andDo(print());
	}

	@Test
	public void targetAnnualNoTrdCustomers_Test() throws Exception{

		// 기대되는 객체 생성
		List<AnnualCustVO> list =  new ArrayList<>();
		list.add(new AnnualCustVO("2018", "테드", "11111114"));
		list.add(new AnnualCustVO("2019", "에이스", "11111112"));

		// 해당 서비스 호출 시, 리턴될 기대값 설정
		given(targetService.targetAnnualNoTrdCustomers()).willReturn(list);

		mvc.perform(
				get("/targetAnnualNoTrdCustomers")
						.contentType(MediaType.APPLICATION_JSON_UTF8)
		)
				.andExpect(status().isOk())
				.andExpect(handler().methodName("targetAnnualNoTrdCustomers"))		// 호출 서비스 메서드 네임 검증
				.andExpect(handler().handlerType(CustTargetApiController.class))	// 컨트롤러 클래스 검증
				.andExpect(jsonPath("$[0].year", is(equalTo("2018"))))	// 값 검증
				.andExpect(jsonPath("$[0].name", is(equalTo("테드"))))
				.andExpect(jsonPath("$[0].acctNo", is(equalTo("11111114"))))
				.andExpect(jsonPath("$[1].year", is(equalTo("2019"))))
				.andExpect(jsonPath("$[1].name", is(equalTo("에이스"))))
				.andExpect(jsonPath("$[1].acctNo", is(equalTo("11111112"))))
				.andDo(print());
	}


	@Test
	public void targetAnnualAmtBranch_Test() throws Exception{

		// 기대되는 객체 생성
		List<AnnualBranchAmtVO> list =  new ArrayList<>();
		AnnualBranchAmtVO mockAnnualBranchVo = new AnnualBranchAmtVO();
		BranchAmtVO mockBranchVo = new BranchAmtVO("분당점", "A", new BigDecimal("9900000"));
		List<BranchAmtVO> mockBranchList = new ArrayList<>();
		mockBranchList.add(mockBranchVo);

		mockAnnualBranchVo.setYear("2018");
		mockAnnualBranchVo.setBrList(mockBranchList);
		list.add(mockAnnualBranchVo);

		// 해당 서비스 호출 시, 리턴될 기대값 설정
		given(targetService.targetAnnualAmtBranch()).willReturn(list);

		// 확인
		mvc.perform(
				get("/targetAnnualAmtBranch")
						.contentType(MediaType.APPLICATION_JSON_UTF8)
		)
				.andExpect(status().isOk())
				.andExpect(handler().methodName("targetAnnualAmtBranch"))			// 호출 서비스 메서드 네임 검증
				.andExpect(handler().handlerType(CustTargetApiController.class))	// 컨트롤러 클래스 검증
				.andExpect(jsonPath("$[0].year", is(equalTo("2018"))))	// 값 검증
				.andExpect(jsonPath("$[0].brList[0].brName", is(equalTo("분당점"))))
				.andDo(print());
	}


	@Test
	public void trdSumBranch_Test() throws Exception{

		// RequestParam 생성
		String userJson = "{\"brName\":\"판교점\"}";

		// 기대되는 값 설정
		given(targetService.trdSumBranch("판교점"))
				.willReturn(new BranchAmtVO("판교점", "A", new BigDecimal(0)));


		// 확인
		mvc.perform(
				get("/trdSumBranch")
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.accept(MediaType.APPLICATION_JSON_UTF8)
						.content(userJson))	// JSON contents
				.andExpect(status().isOk())
				.andExpect(handler().methodName("trdSumBranch"))			// 호출 서비스 메서드 네임 검증
				.andExpect(handler().handlerType(CustTargetApiController.class))	// 컨트롤러 클래스 검증
				.andExpect(jsonPath("$.brName", is(equalTo("판교점"))))	 // 값 검증
				.andExpect(jsonPath("$.brCode", is(equalTo("A"))))
				.andDo(print());
	}
}
