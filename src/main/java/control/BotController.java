package control;

import model.ActionType;
import model.Command;
import model.KeyBoardType;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import utils.StringUtil;
import view.InlineKeyboardBuilder;

import java.util.List;
import java.util.stream.Collectors;

public class BotController {
    public SendMessage getMessageToSend(Message message) {
        SendMessage sendMessage = null;
        Command command = Command.fromString(message.getText());

        if (Command.START == command) {
            long chatId = message.getChatId();
            String messageText = ActionType.MENU.getMessageText();
            InlineKeyboardMarkup replyMarkup = InlineKeyboardBuilder.buildKeyboardMarkup(KeyBoardType.ENTRY);

            sendMessage = new SendMessage()
                    .setChatId(chatId)
                    .setText(messageText)
                    .enableMarkdown(true)
                    .setReplyMarkup(replyMarkup);
        }
        return sendMessage;
    }

    public SendMessage getMessageFromCallback(CallbackQuery callbackQuery) {
        String data = callbackQuery.getData();
        ActionType actionType = ActionType.valueOf(data);
        String messageText = actionType.getMessageText();

        long chatId = callbackQuery.getFrom().getId();
        InlineKeyboardMarkup replyMarkup = InlineKeyboardBuilder.buildKeyboardMarkup(actionType);

        return new SendMessage()
                .setChatId(chatId)
                .setText(messageText)
                .enableHtml(true)
                .setReplyMarkup(replyMarkup);
    }

    public SendMediaGroup getPhotoFromCallback(CallbackQuery callbackQuery) {
        String data = callbackQuery.getData();
        ActionType actionType = ActionType.valueOf(data);

        List<InputMedia> photoList = actionType.getPhotoUrls()
                .stream()
                .map(photoName -> new InputMediaPhoto(photoName, StringUtil.EMPTY_STRING))
                .collect(Collectors.toList());

        if (photoList.isEmpty()) {
            return null;
        }

        long chatId = callbackQuery.getFrom().getId();
        SendMediaGroup mediaGroup = new SendMediaGroup().setChatId(chatId);
        mediaGroup.setMedia(photoList);
        return mediaGroup;
    }
}
