package pl.jellysoft.kodbot.resolver.simulator.element;

public class HeavyBox implements Element {

    @Override
    public boolean isPickable() {
        return false;
    }

    @Override
    public boolean isStandable() {
        return true;
    }

}
