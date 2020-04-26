package pl.jellysoft.kodbot.resolver.evaluator;

import io.vavr.collection.List;
import org.junit.Test;
import pl.jellysoft.kodbot.resolver.evaluator.command.Command;
import pl.jellysoft.kodbot.resolver.evaluator.command.MainBlock;
import pl.jellysoft.kodbot.resolver.evaluator.command.MoveCommand;

import static org.assertj.core.api.Assertions.assertThat;

public class EvaluatorTest {

    @Test
    public void shouldRunSimpleCommand() throws Exception {
        // given
        Evaluator evaluator = new Evaluator();


        // when
        MainBlock mainBlock = new MainBlock();
        mainBlock.setCommands(List.<Command>of(new MoveCommand()).asJava());

        EvaluatorResult evaluatorResult = evaluator.evaluate(List.<Command>of(mainBlock).asJava());

        // then
        assertThat(evaluatorResult).isNotNull();
        assertThat(evaluatorResult.getActions()).hasSize(1);
        assertThat(evaluatorResult.getActions().get(0)).isEqualTo(ActionType.MOVE);
    }

}