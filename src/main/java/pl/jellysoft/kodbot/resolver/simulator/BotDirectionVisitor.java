package pl.jellysoft.kodbot.resolver.simulator;

public interface BotDirectionVisitor<T> {

    T visitBottomRight();

    T visitBottomLeft();

    T visitTopLeft();

    T visitTopRight();

}
