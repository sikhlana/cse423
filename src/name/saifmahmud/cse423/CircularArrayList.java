package name.saifmahmud.cse423;

import java.util.ArrayList;

public class CircularArrayList<E> extends ArrayList<E> {
    @Override
    public E get(int index) {
        if (index < 0) {
            index = size() + index;
        } else if (index >= size()) {
            index %= size();
        }

        return super.get(index);
    }
}
