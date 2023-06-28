package vn.tripi.message.telegram;
import org.springframework.boot.SpringApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import vn.tripi.telegram.MyBot;


public class TelegramApplication {

	public static void main(String[] args) {
		try {
			TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
			botsApi.registerBot(new MyBot());
			System.out.println("Bot started successfully!");
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

}
