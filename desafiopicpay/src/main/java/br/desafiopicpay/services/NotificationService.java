package br.desafiopicpay.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.desafiopicpay.dtos.NotificationDTO;
import br.desafiopicpay.models.User;

@Service
public class NotificationService {

	@Autowired
	private RestTemplate restTemplate;
	
	public void sendNotification(User user, String message) throws Exception {
		String email = user.getEmail();
		NotificationDTO notificationRequest = new NotificationDTO(email, message);
		/*ResponseEntity<String> notification = restTemplate.postForEntity("http://o4d9z.mocklab.io/notify",notificationRequest, String.class);
		if(!(notification.getStatusCode()== HttpStatus.OK)) {
			System.out.println("Erro ao enviar notificação");
			throw new Exception("Erro no serviço de notificação");
		}*/
		System.out.println("Notificacao enviada para o usuario");
	}
	
}
