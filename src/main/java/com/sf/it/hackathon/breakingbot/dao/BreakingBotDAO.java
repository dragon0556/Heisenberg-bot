package com.sf.it.hackathon.breakingbot.dao;

import org.springframework.stereotype.Component;

import com.sf.it.hackathon.breakingbot.dbmodel.BreakingBotEntity;

@Component
public class BreakingBotDAO {

	public BreakingBotEntity getBotDetails(BreakingBotEntity bbEntity){
		BreakingBotEntity bbEntityResult = null;
		bbEntityResult = new BreakingBotEntity(1, "Avinash Jha");
		return bbEntityResult;
	}
}
