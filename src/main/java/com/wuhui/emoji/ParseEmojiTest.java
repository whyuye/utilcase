package com.wuhui.emoji;

import com.vdurmont.emoji.EmojiLoader;
import com.vdurmont.emoji.EmojiManager;
import com.vdurmont.emoji.EmojiParser;
import com.vdurmont.emoji.EmojiTrie;

public class ParseEmojiTest {

    public static void main(String[] args) {
        String str1 = "😭";
        System.out.println();
        String str3 = "好";
        System.out.println(str3.length());
        String str2 = "\\xF0\\x9F\\xA5\\x88";
        System.out.println(EmojiParser.parseToAliases(str2));
        System.out.println(str2);
        System.out.println(str1.length()); // emoji表情占2个字符
        System.out.println(EmojiParser.parseToHtmlDecimal(str1).length());
        String str4 = "🀄️哈哈哈";
        String str = "中文An emoji表情😀awesome 😃string with a few 😉emojis!";
        System.out.println(EmojiParser.parseToUnicode(str));

        System.out.println(EmojiParser.parseFromUnicode(str2, unicodeCandidate -> unicodeCandidate.getEmoji().getHtmlHexadecimal()));
        String resultDecimal = EmojiParser.parseToHtmlDecimal(str);
        System.out.println(resultDecimal);
// Prints:
// "An &#128512;awesome &#128515;string with a few &#128521;emojis!"

        String resultHexadecimal = EmojiParser.parseToHtmlHexadecimal(str);
        System.out.println(resultHexadecimal);
// Prints:
// "An &#x1f600;awesome &#x1f603;string with a few &#x1f609;emojis!"

        System.out.println(EmojiParser.parseToAliases(str));
        System.out.println(EmojiParser.parseToAliases(resultHexadecimal));

        System.out.println(EmojiManager.containsEmoji(str1));
        System.out.println(EmojiManager.containsEmoji(str3));
        System.out.println(EmojiManager.containsEmoji(str));
        System.out.println(EmojiManager.containsEmoji(str4));
    }
}
