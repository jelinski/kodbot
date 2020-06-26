package pl.jellysoft.kodbot.resolver.statistic;

import lombok.RequiredArgsConstructor;
import pl.jellysoft.kodbot.resolver.evaluator.command.AssignCommand;
import pl.jellysoft.kodbot.resolver.evaluator.command.AssignWithAdditionCommand;
import pl.jellysoft.kodbot.resolver.evaluator.command.AssignWithSubtractionCommand;
import pl.jellysoft.kodbot.resolver.evaluator.command.Command;
import pl.jellysoft.kodbot.resolver.evaluator.command.CommandVisitor;
import pl.jellysoft.kodbot.resolver.evaluator.command.DecrementCommand;
import pl.jellysoft.kodbot.resolver.evaluator.command.FunctionBlock;
import pl.jellysoft.kodbot.resolver.evaluator.command.FunctionCallCommand;
import pl.jellysoft.kodbot.resolver.evaluator.command.IncrementCommand;
import pl.jellysoft.kodbot.resolver.evaluator.command.JumpCommand;
import pl.jellysoft.kodbot.resolver.evaluator.command.MainBlock;
import pl.jellysoft.kodbot.resolver.evaluator.command.MoveCommand;
import pl.jellysoft.kodbot.resolver.evaluator.command.RepeatBlock;
import pl.jellysoft.kodbot.resolver.evaluator.command.TurnLeftCommand;
import pl.jellysoft.kodbot.resolver.evaluator.command.TurnRightCommand;

import java.util.Collection;
import java.util.List;

import static io.vavr.collection.List.ofAll;

public class StatisticCounter {

    public static StatisticDTO countStatistics(List<Command> commands) {
        return StatisticCounterCommandVisitor.countStatistics(commands, StatisticDTO.builder().build());
    }

    @RequiredArgsConstructor
    private static class StatisticCounterCommandVisitor implements CommandVisitor<StatisticDTO> {

        private final StatisticDTO previous;

        private static StatisticDTO countStatistics(Command command, StatisticDTO previous) {
            return command.accept(new StatisticCounterCommandVisitor(previous));
        }

        private static StatisticDTO countStatistics(Collection<Command> commands, StatisticDTO previous) {
            return ofAll(commands).foldLeft(previous, (a, c) -> countStatistics(c, a));
        }

        @Override
        public StatisticDTO visit(AssignCommand assignCommand) {
            return previous.withAssignCount(previous.getAssignCount() + 1);
        }

        @Override
        public StatisticDTO visit(AssignWithAdditionCommand assignWithAdditionCommand) {
            return previous.withAssignWithAdditionCount(previous.getAssignWithAdditionCount() + 1);
        }

        @Override
        public StatisticDTO visit(AssignWithSubtractionCommand assignWithSubtractionCommand) {
            return previous.withAssignWithSubtractionCount(previous.getAssignWithSubtractionCount() + 1);
        }

        @Override
        public StatisticDTO visit(DecrementCommand decrementCommand) {
            return previous.withDecrementCount(previous.getDecrementCount() + 1);
        }

        @Override
        public StatisticDTO visit(FunctionBlock functionBlock) {
            StatisticDTO statisticDTO = previous.withDefinedFunctionCount(previous.getDefinedFunctionCount() + 1);
            return countStatistics(functionBlock.getCommands(), statisticDTO);
        }

        @Override
        public StatisticDTO visit(FunctionCallCommand functionCallCommand) {
            return previous.withCalledFunctionCount(previous.getCalledFunctionCount() + 1);
        }

        @Override
        public StatisticDTO visit(IncrementCommand incrementCommand) {
            return previous.withIncrementCount(previous.getIncrementCount() + 1);
        }

        @Override
        public StatisticDTO visit(JumpCommand jumpCommand) {
            return previous.withJumpCount(previous.getJumpCount() + 1);
        }

        @Override
        public StatisticDTO visit(MainBlock mainBlock) {
            return countStatistics(mainBlock.getCommands(), previous);
        }

        @Override
        public StatisticDTO visit(MoveCommand moveCommand) {
            return previous.withMoveCount(previous.getMoveCount() + 1);
        }

        @Override
        public StatisticDTO visit(RepeatBlock repeatBlock) {
            StatisticDTO statisticDTO = previous.withRepeatCount(previous.getRepeatCount() + 1);
            return countStatistics(repeatBlock.getCommands(), statisticDTO);
        }

        @Override
        public StatisticDTO visit(TurnLeftCommand turnLeftCommand) {
            return previous.withTurnLeftCount(previous.getTurnLeftCount() + 1);
        }

        @Override
        public StatisticDTO visit(TurnRightCommand turnRightCommand) {
            return previous.withTurnRightCount(previous.getTurnRightCount() + 1);
        }

    }

}
