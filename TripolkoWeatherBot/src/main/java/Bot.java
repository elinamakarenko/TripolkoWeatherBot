import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

  public void onUpdateReceived(Update update) {
	  String text="";
	  // �������� ��������� �� �������� � �������� �� ���
    if(update.hasMessage()&&update.getMessage().hasText()) {
    	text = update.getMessage().getText();
    	long chat_id = update.getMessage().getChatId();
    	System.out.println(text); 
    	SendMessage mes = new SendMessage().setChatId(chat_id).setText(text);
    	
    	
    	if(text.equals("/help")) {
    	text="This bot shows current weather conditions";
    	mes.setChatId(chat_id).setText(text);
    	try {
    		
			execute(mes);
			System.out.println(text); 
		} catch (TelegramApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	}else {
    		
    		String weather="";
    		
    		try {
				weather=Weather.getWeather(text);
				System.out.println(weather);
				//execute(weather);
//				ByteBuffer buffer = StandardCharsets.UTF_8.encode("weather"); 
//				 
//				String utf8EncodedString = StandardCharsets.UTF_8.decode(buffer).toString();
//				System.out.println(utf8EncodedString);
    		} catch (IOException e) {
				// TODO Auto-generated catch block
		    	System.out.println("Wrong city name!"); 
				e.printStackTrace();
			}
    		mes.setChatId(chat_id).setText(weather);
        	try {
    			execute(mes);
    			//System.out.println(text); 
    		} catch (TelegramApiException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    }
    
   
  }

  public String getBotUsername() {
    // TODO Auto-generated method stub
    return "TripolkoWeatherBot";
  }

  @Override
  public String getBotToken() {
    // TODO Auto-generated method stub
    return "1256760118:AAG6VYhucBv_fhmsWH4d5kEH-8OrKDkma64";
  }
}

