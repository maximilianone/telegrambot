package control;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
public class HoneyProBot extends TelegramLongPollingBot {

    private BotController botController;

    public HoneyProBot(BotController botController) {
        super();
        this.botController = botController;
    }

    /**
     * Main method of the button triggered on update received from user.
     *
     * @param update update received from user
     */
    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = null;
        SendMediaGroup sendPhotos = null;
        if (update.hasMessage() && update.getMessage().hasText()) {
            sendMessage = botController.getMessageToSend(update.getMessage());
        }
        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            sendPhotos = botController.getPhotoFromCallback(callbackQuery);
            sendMessage = botController.getMessageFromCallback(callbackQuery);
        }
        executeMethodSend(sendPhotos);
        executeMethodSend(sendMessage);
    }

    private void executeMethodSend(SendMessage sendMessage) {
        if (sendMessage != null) {
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                log.error("Error! Failed to send message", e);
            }
        }
    }

    private void executeMethodSend(SendMediaGroup sendPhoto) {
        if (sendPhoto != null) {
            try {
                execute(sendPhoto);
            } catch (TelegramApiException e) {
                log.error("Error! Failed to send message", e);
            }
        }
    }

    /**
     * Return bot username registered in BotFather.
     *
     * @return bot user name
     */
    @Override
    public String getBotUsername() {
        return "testMaxTestBot";
    }

    /**
     * Return bot token received from BotFather.
     *
     * @return bot token
     */
    @Override
    public String getBotToken() {
        return "1156579374:AAFsymO_zZ2_ifb9eU2AgTpunQxvhMwDGdU";
    }
}