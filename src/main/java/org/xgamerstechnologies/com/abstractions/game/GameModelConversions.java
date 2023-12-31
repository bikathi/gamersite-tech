package org.xgamerstechnologies.com.abstractions.game;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.xgamerstechnologies.com.payload.GamePayload;
import org.xgamerstechnologies.com.type.Game;

public abstract class GameModelConversions<T extends Game> {
    @Autowired
    private ModelMapper modelMapper;

    public final GamePayload convertToPayload(Game game) {
        GamePayload gamePayload = modelMapper.map(game, GamePayload.class);
        gamePayload.setReleaseDate(new GamePayload().getReleaseDateAsString(game.getDateReleased()));

        return gamePayload;
    }
    public final T convertToEntity(GamePayload gamePayload, Class<T> clazz) {
        T game = modelMapper.map(gamePayload, clazz);
        game.setDateReleased(gamePayload.getReleaseDateAsDate(gamePayload.getReleaseDate()));

        return game;
    }
}
