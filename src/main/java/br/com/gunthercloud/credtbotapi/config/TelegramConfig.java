package br.com.gunthercloud.credtbotapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import br.com.gunthercloud.credtbotapi.bot.TelegramBot;

@Configuration
public class TelegramConfig {
	@Bean
	public TelegramBot telegramBot(@Value("${bot.name}") String botName,
			@Value("${bot.token}") String botToken) {
		TelegramBot telegramBot = new TelegramBot(botName, botToken);
		try {
			var telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
			telegramBotsApi.registerBot(telegramBot);
		}
		catch(TelegramApiException e) {
			e.getStackTrace();
		}
		return telegramBot;
	}

}
