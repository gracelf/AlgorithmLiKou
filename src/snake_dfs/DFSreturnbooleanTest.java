package snake_dfs;

/**
 *
 */
public class DFSreturnbooleanTest {

    public boolean test() {
        return dfs(0, 0);
    }

    private boolean dfs(int i, int j) {
        System.out.println("====i : " + i + ", j: " + j);
        //base case
        if (i == 2) {
            return j == 4;
        } else {
            while (j <= 4) {
                if (dfs(i + 1, j + 1)) {
                    return true;
                }
                j++;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        DFSreturnbooleanTest test = new DFSreturnbooleanTest();
        System.out.println(test.test());
    }

}
