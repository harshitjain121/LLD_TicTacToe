package factories;

import enums.BotDifficultyLevel;
import strategies.botplayingstrategy.BotPlayingStrategy;
import strategies.botplayingstrategy.EasyBotPlayingStrategy;

public class BotPlayingStrategyFactory {

    public static BotPlayingStrategy getBotPlayingStrategyForDifficultyLevel(BotDifficultyLevel difficultyLevel) {
        if (difficultyLevel.equals(BotDifficultyLevel.EASY)) {
            return new EasyBotPlayingStrategy();
        }
//        else if (difficultyLevel.equals(BotDifficultyLevel.MEDIUM)) {
//            //return new MediumBotPlayingStrategy();
//            return
//        }
        return null;
    }
}