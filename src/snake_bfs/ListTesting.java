package snake_bfs;

import java.util.ArrayList;

/**
 *
 */
public class ListTesting {

    public static void main(String[] args) {
        // create an empty array list with an initial capacity
        ArrayList<Integer> arrlist = new ArrayList<>(4);

        // use add() method to add elements in the list
        arrlist.add(15);
        arrlist.add(22);
        arrlist.add(30);
        arrlist.add(15);

        // insert(not updating 3rd element) element with value 25 at third position
        arrlist.add(2, 25);

        // let us print all the elements available in list
        for (Integer number : arrlist) {
            System.out.println("Number = " + number);
        }
    }

}
