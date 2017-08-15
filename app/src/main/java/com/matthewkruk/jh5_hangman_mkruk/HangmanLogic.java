package com.matthewkruk.jh5_hangman_mkruk;

import android.util.Log;

import java.util.ArrayList;

class HangmanLogic {
    private String wordToGuess;
    private boolean[] letterGuess;
    private int missesAllowed;
    private int missesSoFar;
    private boolean wordIsGuessed = false;

    private HangmanUpdate hangmanUpdate = null;

    public HangmanLogic(HangmanUpdate hangmanUpdate) {
        this.hangmanUpdate = hangmanUpdate;
    }

    private String gameStatus() {
        StringBuilder sb = new StringBuilder();
        wordIsGuessed = true;
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (letterGuess[i])
                sb.append(wordToGuess.charAt(i) + " ");
            else {
                sb.append("_ ");
                wordIsGuessed = false;
            }
        }
        return sb.toString();
    }

    public void newGame(int missesAllowed, String[] words) {
        this.missesAllowed = missesAllowed;

        int len = words.length;
        int index = (int) (len * Math.random());
        wordToGuess = words[index];
        newGameCommon();

    }

    public void newGame(int missesAllowed, ArrayList<String> words) {
        this.missesAllowed = missesAllowed;

        int len = words.size();
        int index = (int) (len * Math.random());
        wordToGuess = words.get(index);
        newGameCommon();
    }

    private void newGameCommon() {
        missesSoFar = 0;

        Log.d("Mine", "Word to guess=" + wordToGuess);

        letterGuess = new boolean[wordToGuess.length()];
        for (int i = 0; i < wordToGuess.length(); i++)
            letterGuess[i] = false;

        hangmanUpdate.updateMessage(gameStatus());
    }

    public boolean buttonClicked(char c) {
        boolean letterMatched = false;
        for (int i = 0; i < wordToGuess.length(); i++) {
            char c2 = wordToGuess.charAt(i);
            if (Character.isLetter(c2)) {
                if (c == Character.toLowerCase(c2)) {
                    letterGuess[i] = true;
                    letterMatched = true;
                }
            } else
                letterGuess[i] = true;
        }
        String message = gameStatus();

        if (wordIsGuessed) {
            hangmanUpdate.updateMessage(message + " and you win!!!!");
            hangmanUpdate.gameIsDone(true);
        } else {
            if (!letterMatched)
                missesSoFar += 1;
            if (missesSoFar >= missesAllowed) {
                hangmanUpdate.updateMessage(message + " and you Lose!!!" +
                        "\n" + wordToGuess);
                hangmanUpdate.gameIsDone(false);
            } else
                hangmanUpdate.updateMessage(message);
        }
        return letterMatched;
    }
}