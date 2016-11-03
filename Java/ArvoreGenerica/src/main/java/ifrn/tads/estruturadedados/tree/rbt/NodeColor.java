package ifrn.tads.estruturadedados.tree.rbt;

public enum NodeColor {
    RED("red"), BLACK("black");

    private final String color;

    NodeColor(String option) {
        color = option;
    }

    public String getColor() {
        return color;
    }
}