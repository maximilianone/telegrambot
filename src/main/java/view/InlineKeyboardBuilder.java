package view;

import lombok.experimental.UtilityClass;
import model.ActionType;
import model.KeyBoardType;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@UtilityClass
public class InlineKeyboardBuilder {
    public InlineKeyboardMarkup buildKeyboardMarkup(ActionType actionType) {
        switch (actionType) {
            case CONTACT_US:
                return buildKeyboardMarkup(KeyBoardType.RETURN);
            case MENU:
                return buildKeyboardMarkup(KeyBoardType.ENTRY);
            default:
                return buildKeyboardMarkup(KeyBoardType.SERVICE);
        }
    }

    public InlineKeyboardMarkup buildKeyboardMarkup(KeyBoardType type) {
        List<List<InlineKeyboardButton>> keyboard;
        switch (type) {
            case SERVICE:
                keyboard = buildServiceKeyBoard();
                break;
            case RETURN:
                keyboard = buildReturnKeyBoard();
                break;
            case ENTRY:
            default:
                keyboard = buildEntryKeyboard();
        }
        return new InlineKeyboardMarkup().setKeyboard(keyboard);
    }

    private List<List<InlineKeyboardButton>> buildEntryKeyboard() {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(Collections.singletonList(ButtonFactory.buildInlineButton(ActionType.SLOWMOTION)));
        buttons.add(Collections.singletonList(ButtonFactory.buildInlineButton(ActionType.PHOTO_PRINTING)));
        buttons.add(Collections.singletonList(ButtonFactory.buildInlineButton(ActionType.VINYL_MAGNETS)));
        buttons.add(Collections.singletonList(ButtonFactory.buildInlineButton(ActionType.PHOTO_BOOTH)));
        buttons.add(Collections.singletonList(ButtonFactory.buildInlineButton(ActionType.PHOTO_BOOTH_360)));
        buttons.add(Collections.singletonList(ButtonFactory.buildInlineButton(ActionType.CONTACT_US)));
        return buttons;
    }

    private List<List<InlineKeyboardButton>> buildServiceKeyBoard() {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(Arrays.asList(
                ButtonFactory.buildInlineButton(ActionType.CONTACT_US),
                ButtonFactory.buildInlineButton(ActionType.MENU)
        ));
        return buttons;
    }

    private List<List<InlineKeyboardButton>> buildReturnKeyBoard() {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(Collections.singletonList(ButtonFactory.buildInlineButton(ActionType.MENU)));
        return buttons;
    }
}
