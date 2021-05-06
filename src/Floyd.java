public class Floyd {
    public static int[][] path;

    public static void main(String[] args) {
        int vertex = 7; //7个顶点(改为用户输入可以再加一个edge表示边数)
        int[][] matrix = new int[vertex][vertex]; //从a点到b点的边的权重
        path = new int[vertex][vertex]; //从a点到b点的路径
        for (int i = 0; i < vertex; i++) { //初始化
            for (int j = 0; j < vertex; j++) {
                path[i][j] = -1;//无路径
                matrix[i][j] = 99999; //无法通过
            }
        }
        matrix[0][1] = 6;//0顶点到1顶点的边的权值为6(可改为用户输入)
        matrix[1][2] = 5;
        matrix[0][3] = 2;
        matrix[3][1] = 7;
        matrix[3][4] = 5;
        matrix[4][1] = 1;
        matrix[1][5] = 3;
        matrix[5][2] = 3;
        matrix[5][4] = 2;
        matrix[4][6] = 1;
        matrix[1][0] = 6;//反方向变成无向图
        matrix[2][1] = 5;
        matrix[3][0] = 2;
        matrix[1][3] = 7;
        matrix[4][3] = 5;
        matrix[1][4] = 1;
        matrix[5][1] = 3;
        matrix[2][5] = 3;
        matrix[4][5] = 2;
        matrix[6][4] = 1;
        floyd(matrix, path);
    }

    static void floyd(int[][] matrix, int[][] path) {
        for (int m = 0; m < matrix.length; m++) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    if (matrix[i][m] + matrix[m][j] < matrix[i][j]) {  //如果经过m比直达更短
                        matrix[i][j] = matrix[i][m] + matrix[m][j];
                        path[i][j] = m; //从i到j经过m
                    }
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) { //输出结果
            for (int j = 0; j < matrix.length; j++) {
                if (i != j) {
                    if (matrix[i][j] == 99999) {
                        System.out.println(i + "无法到达" + j);
                    } else {
                        System.out.print(i + "到" + j + "的最短距离为: " + matrix[i][j]);
                        System.out.print("  最短路径为:" + i + "->");
                        findPath(i, j);
                        System.out.println(j);
                    }
                }
            }
        }
    }

    static void findPath(int i, int j) { //找路径
        int m = path[i][j];
        if (m == -1) {
            return;
        }
        findPath(i, m);
        System.out.print(m + "->");
        findPath(m, j);
    }
}