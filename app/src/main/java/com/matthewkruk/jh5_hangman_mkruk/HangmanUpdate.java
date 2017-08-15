package com.matthewkruk.jh5_hangman_mkruk;

public interface HangmanUpdate {
    public void updateMessage(String s);
    public void gameIsDone(boolean winner);
}