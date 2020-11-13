package pl.jellysoft.kodbot.model;

public interface BotDirectionVisitor<T> {

    T visitBottomRight();

    T visitBottomLeft();

    T visitTopLeft();

    T visitTopRight();

}
