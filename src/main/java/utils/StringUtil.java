package utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtil {
    public final String EMPTY_STRING = "";

    public boolean emptyString(String string) {
        return string == null || "".equals(string);
    }
}
