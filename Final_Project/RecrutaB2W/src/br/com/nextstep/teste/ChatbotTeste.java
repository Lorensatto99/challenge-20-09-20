package br.com.nextstep.teste;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.assistant.v2.Assistant;
import com.ibm.watson.assistant.v2.model.CreateSessionOptions;
import com.ibm.watson.assistant.v2.model.MessageInput;
import com.ibm.watson.assistant.v2.model.MessageOptions;
import com.ibm.watson.assistant.v2.model.MessageResponse;
import com.ibm.watson.assistant.v2.model.SessionResponse;

public class ChatbotTeste {

	public static void main(String[] args) {

		IamAuthenticator authenticator = new IamAuthenticator("QjJRIw8chC1WJ5Ae99A3neY0QzQeq_0L829-Mqaqc9ab");
		Assistant assistant = new Assistant("2020-11-31", authenticator);
		assistant.setServiceUrl("https://api.us-south.assistant.watson.cloud.ibm.com/instances/214ecc19-d036-4afb-a3d2-a0efeabca4bd");
		
		CreateSessionOptions sessionOptions = new CreateSessionOptions.Builder("ebfbc50b-c9f5-455f-8513-6fb54cba070a").build();

		SessionResponse sessionResponse = assistant.createSession(sessionOptions).execute().getResult();
			
		MessageInput input = new MessageInput.Builder()
				  .messageType("text")
				  .text("Sim")
				  .build();
		
		MessageOptions MsgOptions = new MessageOptions.Builder("ebfbc50b-c9f5-455f-8513-6fb54cba070a", sessionResponse.getSessionId())
				  .input(input)
				  .build();

				MessageResponse msgResponse = assistant.message(MsgOptions).execute().getResult();

				System.out.println(msgResponse);

	}
}