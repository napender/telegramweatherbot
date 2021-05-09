package weatherbot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
 * 
 * @author napendra 
 *
 */
public class MainClass {
	public static void main(String args[]) {
		 try {
             TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
             telegramBotsApi.registerBot(new NapendraBot());
         } catch (TelegramApiException e) {
             e.printStackTrace();
         }
	}
}
