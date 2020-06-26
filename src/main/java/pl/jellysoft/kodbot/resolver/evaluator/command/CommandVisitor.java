package pl.jellysoft.kodbot.resolver.evaluator.command;

public interface CommandVisitor<R> {

    R visit(AssignCommand assignCommand);

    R visit(AssignWithAdditionCommand assignWithAdditionCommand);

    R visit(AssignWithSubtractionCommand assignWithSubtractionCommand);

    R visit(DecrementCommand decrementCommand);

    R visit(FunctionBlock functionBlock);

    R visit(FunctionCallCommand functionCallCommand);

    R visit(IncrementCommand incrementCommand);

    R visit(JumpCommand jumpCommand);

    R visit(MainBlock mainBlock);

    R visit(MoveCommand moveCommand);

    R visit(RepeatBlock repeatBlock);

    R visit(TurnLeftCommand turnLeftCommand);

    R visit(TurnRightCommand turnRightCommand);

}
