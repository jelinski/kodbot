package pl.jellysoft.kodbot.resolver.evaluator;

import org.junit.Test;
import pl.jellysoft.kodbot.resolver.evaluator.command.AssignCommand;
import pl.jellysoft.kodbot.resolver.evaluator.command.FunctionBlock;
import pl.jellysoft.kodbot.resolver.evaluator.command.FunctionCallCommand;
import pl.jellysoft.kodbot.resolver.evaluator.command.JumpCommand;
import pl.jellysoft.kodbot.resolver.evaluator.command.MainBlock;
import pl.jellysoft.kodbot.resolver.evaluator.command.MoveCommand;
import pl.jellysoft.kodbot.resolver.evaluator.command.RepeatBlock;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.jellysoft.kodbot.resolver.evaluator.ActionType.JUMP;
import static pl.jellysoft.kodbot.resolver.evaluator.ActionType.MOVE;

public class EvaluatorTest {

    @Test
    public void shouldEvaluateSimpleProgram() throws Exception {
        // given
        Evaluator evaluator = new Evaluator();
        MainBlock mainBlock = new MainBlock(List.of(new MoveCommand()));

        // when
        EvaluatorResult evaluatorResult = evaluator.evaluate(List.of(mainBlock));

        // then
        assertThat(evaluatorResult).isNotNull();
        assertThat(evaluatorResult.getActions()).hasSize(1);
        assertThat(evaluatorResult.getActions()).containsExactly(MOVE);
    }

    @Test
    public void shouldEvaluateAdvancedPrograms() throws Exception {
        // given
        Evaluator evaluator = new Evaluator();

        MainBlock mainBlock = new MainBlock(List.of(
                new JumpCommand(),
                new FunctionCallCommand("f1"),
                new JumpCommand()
        ));

        FunctionBlock functionBlock = new FunctionBlock("f1", List.of(
                new AssignCommand("x", "3"),
                new RepeatBlock("x", List.of(new MoveCommand()))
        ));

        // when
        EvaluatorResult evaluatorResult = evaluator.evaluate(List.of(mainBlock, functionBlock));

        // then
        assertThat(evaluatorResult).isNotNull();
        assertThat(evaluatorResult.getActions()).hasSize(5);
        assertThat(evaluatorResult.getActions()).containsExactly(JUMP, MOVE, MOVE, MOVE, JUMP);
    }

}