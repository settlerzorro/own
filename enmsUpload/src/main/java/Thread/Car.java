package Thread;

import java.util.ArrayList;
import java.util.List;

public class Car {
    private String key;
    private List<String> list;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setList(ArrayList<Object> objects) {
    }
}
