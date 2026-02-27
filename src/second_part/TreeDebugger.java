package second_part;

import java.util.ArrayList;
import java.util.List;

public class TreeDebugger {

    private final List<TracePoint> crumbs = new ArrayList<>();

    public void add(TracePoint point) {
        crumbs.add(point);
    }

    public List<TracePoint> get() {
        return crumbs;
    }

    public void clear() {
        crumbs.clear();
    }
}
