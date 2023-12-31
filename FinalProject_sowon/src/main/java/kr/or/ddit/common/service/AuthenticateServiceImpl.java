package kr.or.ddit.common.service;

import java.lang.reflect.InvocationTargetException;

import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import kr.or.ddit.common.ServiceResult;
import kr.or.ddit.common.dao.MemberDAO;
import kr.or.ddit.common.vo.MemberVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthenticateServiceImpl implements AuthenticateService  {
	 
	private final MemberDAO memberDAO;

	@Override
	public ServiceResult authenticate(MemberVO inputData) {
		MemberVO saved = memberDAO.selectUsersForAuth(inputData);
		ServiceResult result =  null;
		if(saved!=null) {
			//회원이 있는 경우
			String inputPass = inputData.getMemPass();
			String savedPass = saved.getMemPass();
			if(savedPass.equals(inputPass)) {
				try {
					BeanUtils.copyProperties(inputData, saved);
					result = ServiceResult.OK;
					
				} catch (IllegalAccessException | InvocationTargetException e) {
					//membervo 잘못만들어짐(로그인 불가)
					throw new RuntimeException(e);
				}
				
				
			}else {
				result = ServiceResult.INVALIDPASSWORD;
			}
		}else {
			//회원이 없는 경우
			result = ServiceResult.NOTEXIST;
			
		}
			return result;
		
	}
	
	

}
