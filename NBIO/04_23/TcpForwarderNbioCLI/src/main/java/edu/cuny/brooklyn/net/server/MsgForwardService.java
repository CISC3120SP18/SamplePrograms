package edu.cuny.brooklyn.net.server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.cuny.brooklyn.net.Protocol;

public class MsgForwardService {
	private final static Logger LOGGER = LoggerFactory.getLogger(MsgForwardService.class);
	private Random rng = new Random();
	private Map<Integer, ClientHandler> clientMap;
	
	public MsgForwardService() {
		clientMap = new HashMap<Integer, ClientHandler>();
		this.rng = new Random();
	}
	

	public void addClient(ClientHandler clientRunnable) {
		clientMap.put(clientRunnable.getId(), clientRunnable);
	}
	
	public String forwardMsg(int requestingClient, String msg) {
		String[] msgParts = msg.split(",");
		if (msgParts.length < 3) {
			LOGGER.error("Message received at " + requestingClient + " is in wrong message format.");
			return null;
		}
		
		int dstClientId = Integer.parseInt(msgParts[1]);
		if (dstClientId == -1) {
			dstClientId = selectClient();			
		}
		if (dstClientId == -1) {
			return null;
		}
		
		msgParts[0] = Integer.toString(requestingClient);
		msgParts[1] = Integer.toString(dstClientId);
		msg = String.join(",", msgParts);
		try {
			clientMap.get(dstClientId).send(Protocol.buildMessage(msg));
			LOGGER.info("Forwarded to " + msgParts[1] + " the message: [" + Protocol.buildMessage(msg) + "]");
		} catch (IOException e) {
			LOGGER.error("Failed to sent message to " + dstClientId);
			LOGGER.debug("Before client removal: # of clients = " + clientMap.size());
			remove(dstClientId);
			msg = null;
			LOGGER.debug("After client removal: # of clients = " + clientMap.size());
			LOGGER.info("removed client " + dstClientId + " due to I/O error.");
		}
		return msg;
	}
	
	public void remove(int id) {
		ClientHandler client = clientMap.get(id);
		client.close();
		clientMap.remove(id);
	}
	
	private int selectClient() {
		int selectedClient = -1;
		
		if (clientMap.size() < 1) {
			LOGGER.debug("There are no clients at the server.");
			return selectedClient;
		}		
		Set<Integer> keySet = clientMap.keySet();
		Integer[] clientIds = new Integer[keySet.size()];
		clientIds = keySet.toArray(clientIds);

		selectedClient = clientIds[rng.nextInt(clientIds.length)]; 
		return selectedClient;
	}


	public void stop() {
		clientMap.forEach((id, client) -> client.close()); 
	}

}
