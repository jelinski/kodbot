package pl.jellysoft.kodbot.resolver.evaluator;

public interface ActionTypeVisitor<T> {

    T visitMove();

    T visitJump();

    T visitTurnLeft();

    T visitTurnRight();

}
