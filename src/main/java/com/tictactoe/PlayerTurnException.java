package com.tictactoe;

/**
 * Created by Rafal on 2017-12-03.
 */
public class PlayerTurnException extends RuntimeException {

    public PlayerTurnException(String message) {
        super(message);
    }
}
