package com.Url.Deeplink.Converter.Services;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.Url.Deeplink.Converter.Dto.ResponseDTO;
import com.Url.Deeplink.Converter.Enums.ErrorCodes;
import com.Url.Deeplink.Converter.Repository.TransactionRepository;

@Component("deeplinkstrategy")
public class DeeplinkStrategy implements ConvertStrategy {
	@Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
	private TransactionRepository tranRepository;

	@Override
	public ResponseDTO Convert(String deeplink) {
ResponseDTO result = new ResponseDTO();
		
		try {
			
			String resultUrl = "xyz.com/";
			Pattern p = Pattern.compile("ty://?(.+)&(.+)&(.+)");
			Matcher m = p.matcher(deeplink);
			int contentId = 0;
			if(m.find()) {
				contentId = Integer.parseInt(m.group(2).split("=")[1]);
				var tran = tranRepository.findTransactionByContentId(contentId);
				if(tran == null || !String.valueOf(tran.getFlowId()).equals(m.group(3).split("=")[1])) {
					result.getHeader().setSuccess(false);
					result.getHeader().setCode(ErrorCodes.RecordNotFound.getValue());
					result.getHeader().setMessage(ErrorCodes.RecordNotFound.name());
					return result;
				}
				resultUrl += tran.getCustNo() + "/" + tran.getTransactionName() + "-t-" + contentId + "?flowName=" + tran.getFlowName();
				result.setResult(resultUrl);
			}
			else {
				 p = Pattern.compile("ty://?(.+)&(.+)");
				 m = p.matcher(deeplink);
				 if(m.find()) {
					 contentId = Integer.parseInt(m.group(2).split("=")[1]);
						var tran = tranRepository.findTransactionByContentId(contentId);
						if(tran == null) {
							result.getHeader().setSuccess(false);
							result.getHeader().setCode(ErrorCodes.RecordNotFound.getValue());
							result.getHeader().setMessage(ErrorCodes.RecordNotFound.name());
							return result;
						}
						resultUrl += tran.getCustNo() + "/" + tran.getTransactionName() + "-t-" + contentId;
						result.setResult(resultUrl);
				 }
				 else {
					 result.getHeader().setSuccess(false);
					 result.getHeader().setCode(ErrorCodes.UrlNotMatched.getValue());
					 result.getHeader().setMessage(ErrorCodes.UrlNotMatched.name());
				 }
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.getHeader().setSuccess(false);
			result.getHeader().setCode(ErrorCodes.GeneralError.getValue());
			result.getHeader().setMessage(e.getMessage());
		}
		
		return result;
	}

}
