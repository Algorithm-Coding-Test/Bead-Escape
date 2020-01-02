import java.util.ArrayList;
import java.util.Scanner;

class State {
	Point red;
	Point blue;
	int num;

	public State(Point red, Point blue, int num) {
		this.red = red;
		this.blue = blue;
		this.num = num;
	}

	public String toString() {
		return "State ) RED : " + red + " BLUE : " + blue + " num : " + num;
	}
}

class Point {
	int x = 0;
	int y = 0;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return "( " + x + " , " + y + " )";
	}
}

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt();
		int m = scan.nextInt();

		scan.nextLine();

		boolean[][] board = new boolean[n][m];

		Point blue = null;
		Point red = null;
		Point result = null;

		for (int i = 0; i < n; i++) {
			String ln = scan.nextLine().toString();
			for (int j = 0; j < m; j++) {
				switch (ln.charAt(j)) {
				case '#':
					board[i][j] = false;
					break;
				case 'B':
					board[i][j] = true;
					blue = new Point(i, j);
					break;
				case 'R':
					board[i][j] = true;
					red = new Point(i, j);
					break;
				case '.':
					board[i][j] = true;
					break;
				case 'O':
					board[i][j] = true;
					result = new Point(i, j);
					break;
				}
			}
		}
		scan.close();

		int resultNum = Integer.MAX_VALUE;
		ArrayList<State> next = new ArrayList<>();

		if (red != null && blue != null)
			next.add(new State(red, blue, 0));
		while (!next.isEmpty()) {

			State stat = next.get(0);

			/* 10번 실행 후 빠져나감 */
			if (stat.num > 9)
				break;

			/* 빨간공이 구멍에 들어갔고, 파란공이 구멍에 안들어감 */
			if (stat.red.x == result.x && stat.red.y == result.y
					&& !(stat.blue.x == result.x && stat.blue.y == result.y)) {
				/* 가장 적은 횟수 */
				if (resultNum > stat.num)
					resultNum = stat.num;

				/* 파란공이 구멍에 안들어감 */
			} else if (stat.blue.x != result.x || stat.blue.y != result.y) {

				Point tempR = new Point(stat.red.x, stat.red.y);
				Point tempB = new Point(stat.blue.x, stat.blue.y);

				/* 빨간공이 파란공 아래 있는 경우 */
				if (tempR.x > tempB.x && tempR.y == tempB.y) {
					/* 파란공이 위쪽으로 이동 가능하고, 파란공 위치가 구멍이 아닐 경우에 계속 위로 이동 */
					while (tempB.x != 0 && board[tempB.x - 1][tempB.y]
							&& (tempB.x != result.x || tempB.y != result.y)) {
						tempB.x--;
					}
					/* 빨간공이 위쪽으로 이동 가능하고, 빨간공 위치가 구멍이 아니면 파란공 직전까지 계속 위로 이동 */
					while (tempR.x != 0 && board[tempR.x - 1][tempR.y] && (tempR.x != result.x || tempR.y != result.y)
							&& (tempR.x - 1 != tempB.x || tempR.y != tempB.y)) {
						tempR.x--;
					}
				} else {
					/* 빨간공이 위쪽으로 이동 가능하고, 빨간공 위치가 구멍이 아니면 계속 위로 이동 */
					while (tempR.x != 0 && board[tempR.x - 1][tempR.y]
							&& (tempR.x != result.x || tempR.y != result.y)) {
						tempR.x--;
					}
					/* 파란공이 위쪽으로 이동 가능하고, 파란공 위치가 구멍이 아니면 빨간공 직전까지 계속 위로 이동 */
					while (tempB.x != 0 && board[tempB.x - 1][tempB.y] && (tempB.x - 1 != tempR.x || tempB.y != tempR.y)
							&& (tempB.x != result.x || tempB.y != result.y)) {
						tempB.x--;
					}
					/* 빨간공이 구멍에 빠졌고, 파란공이 구멍 아래에 있으면 파란공도 구멍에 들어감 */
					if ((tempR.x == result.x && tempR.y == result.y)
							&& (tempB.x - 1 == tempR.x && tempB.y == tempR.y)) {
						tempB.x--;
					}
				}
				next.add(new State(tempR, tempB, stat.num + 1));

				tempR = new Point(stat.red.x, stat.red.y);
				tempB = new Point(stat.blue.x, stat.blue.y);

				/* 빨간공이 파란공 위에 있는 경우 */
				if (tempR.x < tempB.x && tempR.y == tempB.y) {
					/* 파란공이 아래로 이동 가능하고, 파란공이 구멍에 빠지지 않은 경우 계속 아래로 이동 */
					while (tempB.x != n - 1 && board[tempB.x + 1][tempB.y]
							&& (tempB.x != result.x || tempB.y != result.y)) {
						tempB.x++;
					}
					/* 빨간공이 아래로 이동 가능하고, 빨간공이 구멍에 빠지지 않는 경우, 파란공 바로 위까지 계속 아래로 이동 */
					while (tempR.x != n - 1 && board[tempR.x + 1][tempR.y]
							&& (tempR.x != result.x || tempR.y != result.y)
							&& !(tempR.x + 1 == tempB.x && tempR.y == tempB.y)) {
						tempR.x++;
					}
				} else {
					/* 빨간공이 아래로 이동 가능하고, 빨간공이 구멍에 빠지지 않은 경우 계속 아래로 이동 */
					while (tempR.x != n - 1 && board[tempR.x + 1][tempR.y]
							&& (tempR.x != result.x || tempR.y != result.y)) {
						tempR.x++;
					}
					/* 파란공이 아래로 이동 가능하고, 파란공이 구멍에 빠지지 않고, 빨간공 바로 위가 아닐 경우 계속 아래로 이동 */
					while (tempB.x != n - 1 && board[tempB.x + 1][tempB.y]
							&& !(tempB.x + 1 == tempR.x && tempB.y == tempR.y)
							&& (tempB.x != result.x || tempB.y != result.y)) {
						tempB.x++;
					}
					/* 빨간공이 구멍에 빠지고, 파란공 바로 아래가 구멍인 경우 파란공도 구멍에 빠짐 */
					if ((tempR.x == result.x && tempR.y == result.y)
							&& (tempB.x + 1 == tempR.x && tempB.y == tempR.y)) {
						tempB.x++;
					}
				}
				next.add(new State(tempR, tempB, stat.num + 1));

				tempR = new Point(stat.red.x, stat.red.y);
				tempB = new Point(stat.blue.x, stat.blue.y);

				/* 빨간공이 파란공 오른쪽에 있는 경우 */
				if (tempR.x == tempB.x && tempR.y > tempB.y) {
					/* 파란공이 왼쪽으로 이동 가능하고, 파란공이 구멍에 빠지지 않은 경우에 계속 왼쪽으로 이동 */
					while (tempB.y != 0 && board[tempB.x][tempB.y - 1]
							&& (tempB.x != result.x || tempB.y != result.y)) {
						tempB.y--;
					}
					/* 빨간공이 왼쪽으로 이동 가능하고, 빨간공이 구멍에 빠지지 않고, 파란공 바로 옆이 아닌 경우에 계속 왼쪽으로 이동 */
					while (tempR.y != 0 && board[tempR.x][tempR.y - 1] && (tempR.x != result.x || tempR.y != result.y)
							&& !(tempR.x == tempB.x && tempR.y - 1 == tempB.y)) {
						tempR.y--;
					}
				} else {
					/* 빨간공이 왼쪽으로 이동 가능하고, 빨간공이 구멍에 빠지지 않은 경우에 계속 왼쪽으로 이동 */
					while (tempR.y != 0 && board[tempR.x][tempR.y - 1]
							&& (tempR.x != result.x || tempR.y != result.y)) {
						tempR.y--;
					}
					/* 파란공이 왼쪽으로 이동 가능하고, 파란공이 구멍에 빠지지 않으면서 빨간공 바로 옆이 아닌 경우 계속 왼쪽으로 이동 */
					while (tempB.y != 0 && board[tempB.x][tempB.y - 1]
							&& !(tempB.x == tempR.x && tempB.y - 1 == tempR.y)
							&& (tempB.x != result.x || tempB.y != result.y)) {
						tempB.y--;
					}
					/* 빨간공이 구멍에 빠졌고, 구멍옆에 바로 파란공이 있는 경우 파란공 역시 구멍에 빠짐 */
					if ((tempR.x == result.x && tempR.y == result.y)
							&& (tempB.x == tempR.x && tempB.y - 1 == tempR.y)) {
						tempB.y--;
					}
				}
				next.add(new State(tempR, tempB, stat.num + 1));

				tempR = new Point(stat.red.x, stat.red.y);
				tempB = new Point(stat.blue.x, stat.blue.y);

				/* 빨간공 오른쪽에 파란공이 있는 경우 */
				if (tempR.x == tempB.x && tempR.y < tempB.y) {
					/* 파란공이 오른쪽으로 이동가능하고, 파란공이 구멍에 빠지지 않은 경우 계속 오른쪽으로 이동 */
					while (tempB.y != m - 1 && board[tempB.x][tempB.y + 1]
							&& (tempB.x != result.x || tempB.y != result.y)) {
						tempB.y++;
					}
					/* 빨간공이 오른쪽으로 이동가능하고, 빨간공이 구멍에 빠지지 않으면서 바로 오른쪽에 파란공이 아닌 경우, 계속 오른쪽으로 이동 */
					while (tempR.y != m - 1 && board[tempR.x][tempR.y + 1]
							&& (tempR.x != result.x || tempR.y != result.y)
							&& !(tempR.x == tempB.x && tempR.y + 1 == tempB.y)) {
						tempR.y++;
					}
				} else {
					/* 빨간공이 오른쪽으로 이동가능하고, 빨간공이 구멍에 빠지지 않은 경우 계속 오른쪽으로 이동 */
					while (tempR.y != m - 1 && board[tempR.x][tempR.y + 1]
							&& (tempR.x != result.x || tempR.y != result.y)) {
						tempR.y++;
					}
					/* 파란공이 오른쪽으로 이동 가능하고, 구멍에 빠지지 않으면서, 바로 오른쪽에 빨간공이 아닌 경우 계속 오른쪽으로 이동 */
					while (tempB.y != m - 1 && board[tempB.x][tempB.y + 1]
							&& !(tempB.x == tempR.x && tempB.y + 1 == tempR.y)
							&& (tempB.x != result.x || tempB.y != result.y)) {
						tempB.y++;
					}
					/* 빨간공이 구멍에 빠졌고, 파란공 바로 오른쪽이 구멍인 경우 파란공도 구멍에 빠짐 */
					if ((tempR.x == result.x && tempR.y == result.y)
							&& (tempB.x == tempR.x && tempB.y + 1 == tempR.y)) {
						tempB.y++;
					}
				}
				next.add(new State(tempR, tempB, stat.num + 1));

			}
			next.remove(0);

		}
		if (resultNum == Integer.MAX_VALUE) {
			System.out.print(-1);
		} else {
			System.out.print(resultNum);
		}

	}

}
