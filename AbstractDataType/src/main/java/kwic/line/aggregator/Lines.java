package kwic.line.aggregator;

import java.util.ArrayList;

import kwic.line.model.Line;


public class Lines {
    private final ArrayList<Line> lines = new ArrayList<>();

    private Lines concordances = null;

    public Lines() {
    }

    public void addLine(Line line) {
        lines.add(line);
    }


    public static Lines fromStrings(ArrayList<String> lineStrings) {
        Lines lines = new Lines();
        for (String l : lineStrings) {
            lines.addLine(new Line(l));
        }
        return lines;
    }

    public static Lines fromLines(ArrayList<Line> lines) {
        Lines _lines = new Lines();
        for (Line l : lines) {
            _lines.addLine(l);
        }
        return _lines;
    }


    public void concat(Lines lines) {
        for (Line _line : lines.lines) {
            this.addLine(_line);
        }
    }

    public Lines concordize() {
        if (concordances == null) {
            this.concordances = new Lines();
            for (Line _line : lines) {
                concordances.concat(_line.concordize());
            }
        }
        return concordances;
    }

    public Lines getSorted() {

        ArrayList<Line> _lines = new ArrayList<>();

        for (Line l : lines) {
            _lines.add(l);
        }
        _lines.sort(Line::compare__);
        return fromLines(_lines);
    }

    public void print() {
        for (Line line : lines) {
            line.print();
        }
    }

    public String toString() {
        ArrayList<String> strings = new ArrayList<>();
        for (Line l : lines) {
            strings.add(l.origin);
        }
        return String.join(System.lineSeparator(), strings) + System.lineSeparator();
    }
}
