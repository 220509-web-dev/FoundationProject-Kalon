package dev.ranieri.app;

import com.github.lalyos.jfiglet.FigletFont;

import java.io.IOException;

public class app {
    public static void main(String[] args) {
        String greeting = "Hello Kalon!";
        try {
            String asciArt = FigletFont.convertOneLine(greeting);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
