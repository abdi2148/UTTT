/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTTT.bll.bot;

import UTTT.bll.game.IGameState;
import UTTT.bll.move.IMove;
import java.util.List;
import java.util.Random;

/**
 *
 * @author PC
 */
public class FungusFoot implements IBot{
    private static final String BOTNAME = "Fungus foot";

    @Override
    public IMove doMove(IGameState state) {
        Random r = new Random();
        List<IMove> validMoves = state.getField().getAvailableMoves();
        return validMoves.get(r.nextInt(validMoves.size()));
    }

    @Override
    public String getBotName() {
        return BOTNAME;
    }

}