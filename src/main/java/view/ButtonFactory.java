package view;

import lombok.experimental.UtilityClass;
import model.ActionType;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class ButtonFactory {
    public final Map<ActionType, InlineKeyboardButton> BUTTON_CACHE = new HashMap<>();

    public InlineKeyboardButton buildInlineButton(ActionType actionType) {
        if (BUTTON_CACHE.containsKey(actionType)) {
            return BUTTON_CACHE.get(actionType);
        }
        InlineKeyboardButton button = new InlineKeyboardButton().setText(actionType.getButtonText()).setCallbackData(actionType.name());
        BUTTON_CACHE.put(actionType, button);
        return button;
    }
}
