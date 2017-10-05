package com.sf.it.hackathon.breakingbot;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.sf.it.hackathon.breakingbot.model.BreakingBotDTO;
import com.sf.it.hackathon.breakingbot.service.BreakingBotService;

@Path("/api")
@Component
public class BreakingBotComponent {
	
	@Inject
	private BreakingBotService breakingBotService;
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get_details")
	public BreakingBotDTO getDetails(){
		
		return breakingBotService.getBotDetail(new BreakingBotDTO(1, "Avinash"));
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get-query-response")
	public Map<String, String> getQueryResponse(@QueryParam("query") String query, @QueryParam("intent") String intent){
		Map<String, String> queryRespMap = new HashMap<>();
		String queryResp = breakingBotService.getQueryResponse(query, intent);
		System.out.println("Contrller: "+queryResp);
		queryRespMap.put("query_result", queryResp);
		return queryRespMap;
	}

}
