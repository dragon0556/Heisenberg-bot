package com.sf.it.hackathon.breakingbot.service;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.sf.it.hackathon.breakingbot.cdchatbot.ChatbotFacade;
import com.sf.it.hackathon.breakingbot.dao.BreakingBotDAO;
import com.sf.it.hackathon.breakingbot.dbmodel.BreakingBotEntity;
import com.sf.it.hackathon.breakingbot.model.BreakingBotDTO;

@Component
public class BreakingBotService {
	@Inject
	BreakingBotDAO breakingBotDAO;
	@Inject
	private ChatbotFacade chatbotFacade;

	public BreakingBotDTO getBotDetail( BreakingBotDTO bbDto){
		BreakingBotDTO bbDtoResult = null;
		BreakingBotEntity bbe = breakingBotDAO.getBotDetails(null);
		bbDtoResult = new BreakingBotDTO(bbe.getId(), bbe.getName());
		
		return bbDtoResult;
	}
	
	public String getQueryResponse(String input, String intent){
		String queryResp = chatbotFacade.start(input, intent);
		System.out.println("Service: "+queryResp);
		return queryResp;
	}
}
