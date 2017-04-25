import java.util.Scanner;
public class Main {
    public static char[][] map;
    public static boolean[][] visited;
    public static int n;
    public static int min = Integer.MAX_VALUE;
    public static int ai = 0, aj = 0, bi = 0, bj = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        map = new char[n][n];
        visited = new boolean[n][n];
        for (int i = 0; i  n; i++) {
            for (int j = 0; j  n; j++) {
                map[i][j] = scanner.next().charAt(0);
                if (map[i][j] == 'A') {
                    ai = i;
                    aj = j;
                    visited[i][j] = true;
                } else if (map[i][j] == 'B') {
                    bi = i;
                    bj = j;
                }
            }
        }
        dfs(ai, aj, 0);
        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
    }

    public static void dfs(int x, int y, int step) {
        if (x == bi && y == bj) {
            if (step  min) {
                min = step;
            }
            return;
        }
        int min = Integer.MAX_VALUE;
        if (x  0 && !visited[x - 1][y] && map[x][y] != map[x - 1][y]) {
            visited[x - 1][y] = true;
            dfs(x - 1, y, step + 1);
            visited[x - 1][y] = false;
        }
        if (x  n - 1 && !visited[x + 1][y] && map[x][y] != map[x + 1][y]) {
            visited[x + 1][y] = true;
            dfs(x + 1, y, step + 1);
            visited[x + 1][y] = false;
        }
        if (y  0 && !visited[x][y - 1] && map[x][y] != map[x][y - 1]) {
            visited[x][y - 1] = true;
            dfs(x, y - 1, step + 1);
            visited[x][y - 1] = false;
        }
        if (y  n - 1 && !visited[x][y + 1] && map[x][y] != map[x][y + 1]) {
            visited[x][y + 1] = true;
            dfs(x, y + 1, step + 1);
            visited[x][y + 1] = false;
        }
    }

}

