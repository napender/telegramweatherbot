package weatherbot;

import java.util.ArrayList;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * 
 * @author napendra
 *
 */
public class NapendraBot extends TelegramLongPollingBot{
	   WeatherApi weatherApi = new WeatherApi();
		String city = null;
		ArrayList<String> weatherData;

	public void onUpdateReceived(Update update) {
	
		String command=update.getMessage().getText();
		
		if(command.equals("/start")) {
			sendMsg(update.getMessage().getChatId().toString(), "Please enter the city name, for which you want to check the temprature.");
		}
		
		if(!command.equals("/start") && update.getMessage().hasText()) {
			city = update.getMessage().getText().toString();
			weatherData = weatherApi.getWeatherByCityName(city);
			if(weatherData.size()>1) {
			sendMsg(update.getMessage().getChatId().toString(), "Current temprature in " + city + " is : " + weatherData.get(0).toString() +"°C");
			sendMsg(update.getMessage().getChatId().toString(), "Temprature feels like " + weatherData.get(1).toString()+"°C");
			sendMsg(update.getMessage().getChatId().toString(), "Your current city is " + weatherData.get(2).toString());
			sendMsg(update.getMessage().getChatId().toString(), "Please hit command /start to search temprature for another city");
			}else {
				sendMsg(update.getMessage().getChatId().toString(), "Sorry " + weatherData.get(0).toString());
			}
		}
		
    }
	
	public synchronized void sendMsg(String chatId, String s) {
		
		SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
		
        try {
			execute(sendMessage);
		} catch (TelegramApiException e) {
			sendMessage.setText("Sorry, the commands is invalid. Please use /start to chat with me.");
		}

		
	}

	public String getBotUsername() {
		// TODO Auto-generated method stub
		return "napendra_bot";
	}

	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		return "1871169166:AAErx6QqqEc0MAofs3ZGo01Mnu9kfqlKHHM";
	}

}
