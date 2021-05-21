import control.BotController;
import control.HoneyProBot;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
public class Main {
    public static void main(String[] args) {
        // Initialize Api Context
        ApiContextInitializer.init();

        // Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        // Register bot
        try {
            botsApi.registerBot(new HoneyProBot(new BotController()));
        } catch (TelegramApiException e) {
            log.error("Error! Failed to register bot", e);
        }
    }
}