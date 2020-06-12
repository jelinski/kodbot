package pl.jellysoft.kodbot.resolver.simulator.element;

public class SpikedBox implements Element {

    @Override
    public boolean isPickable() {
        return false;
    }

    @Override
    public boolean isStandable() {
        return false;
    }

}
