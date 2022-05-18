package dat.startcode.model.services;

public class SVG {
    StringBuilder svg = new StringBuilder();

    private int x;
    private int y;
    private String viewBox;
    private int width;
    private int height;

    private final String headerTemplate = "<svg height=\"%d\" " +
            "width=\"%d\" " +
            "viewBox=\"%s\" " +
            "x=\"%d\"   " +
            "y=\"%d\"   " +
            " preserveAspectRatio=\"xMinYMin meet\">";

    private final String rectTemplate = "<rect x=\"%d\" y=\"%d\" height=\"%d\" width=\"%d\" style=\"stroke:#000000; fill: #ffffff\"/>";

    private final String lineTemplate = "<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" stroke=\"black\" stroke-dasharray=\"5,5\"/>";

    private final String arrowTemplate = "    <defs>\n" +
            "\n" +
            "        <marker\n" +
            "                id=\"arrow\"\n" +
            "                markerWidth=\"12\"\n" +
            "                markerHeight=\"12\"\n" +
            "                refX=\"12\"\n" +
            "                refY=\"6\"\n" +
            "                orient=\"auto\">\n" +
            "            <path d=\"M0,0 L12,6 L0,12 L0,0 \" style=\"fill: #000000;\"/>\n" +
            "        </marker>\n" +
            "    </defs>\n" +
            " <line x1=\"50\" y1=\"%d\" x2=\"50\" y2=\"50\"\n" +
            "          style=\"stroke: #000000;\n" +
            "\tmarker-end: url(#arrow);\"/>\n" +
            "\n" +
            "    <line x1=\"50\" y1=\"%d\" x2=\"%d\" y2=\"%d\"\n" +
            "          style=\"stroke: #000000;\n" +
            "\tmarker-end: url(#arrow);\"/>" +

            "    <text style=\"text-anchor: middle\" transform=\"translate(30, %d) rotate(-90)\" font-size=\"20\">%d cm</text>\n" +
            "    <text style=\"text-anchor: middle\" transform=\"translate(%d, %d)\" font-size=\"20\">%d cm</text>\n";

    public SVG(int x, int y, String viewBox, int width, int height) {
        this.x = x;
        this.y = y;
        this.viewBox = viewBox;
        this.width = width;
        this.height = height;
        svg.append(String.format(headerTemplate, height, width, viewBox, x, y));
    }

    public void addRect(int x, int y, int height, int width) {
        svg.append(String.format(rectTemplate, x, y, height, width));
    }

    public void addLine(int x1, int y1, int x2, int y2) {
        svg.append(String.format(lineTemplate, x1, y1, x2, y2));

    }

    public void addArrow(int x, int y) {
        svg.append(String.format(arrowTemplate, y, y, x, y, y / 2, y, x / 2, y + 30, x));

    }

    public void addSvg(SVG innerSVG) {
        svg.append(innerSVG.toString());
    }

    @Override
    public String toString() {
        return svg.toString() + "</svg>";
    }
}

