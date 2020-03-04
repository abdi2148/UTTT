/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTTT.bll.game;

import UTTT.bll.field.IField;

/**
 *
 * @author PC
 */
public interface IGameState {

    IField getField();

    int getMoveNumber();

    void setMoveNumber(int moveNumber);

    int getRoundNumber();

    void setRoundNumber(int roundNumber);

    int getTimePerMove();

    void setTimePerMove(int milliSeconds);

}