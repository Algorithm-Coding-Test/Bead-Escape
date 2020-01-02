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
}

class Point {
	int x = 0;
	int y = 0;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
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
		
		State stat;
		Point tempR;
		Point tempB;
		while (!next.isEmpty()) {

			stat = next.get(0);

			if (stat.num > 10)
				break;

			if (stat.red.x == result.x && stat.red.y == result.y
					&& !(stat.blue.x == result.x && stat.blue.y == result.y)) {
				if (resultNum > stat.num)
					resultNum = stat.num;

			} else if (stat.blue.x != result.x || stat.blue.y != result.y) {

				tempR = new Point(stat.red.x, stat.red.y);
				tempB = new Point(stat.blue.x, stat.blue.y);

				if (tempR.x > tempB.x && tempR.y == tempB.y) {
					while (tempB.x != 0 && board[tempB.x - 1][tempB.y]
							&& !(tempB.x == result.x && tempB.y == result.y)) {
						tempB.x--;
					}
					while (tempR.x != 0 && board[tempR.x - 1][tempR.y] && !(tempR.x == result.x && tempR.y == result.y)
							&& !(tempR.x - 1 == tempB.x && tempR.y == tempB.y)) {
						tempR.x--;
					}
				} else {
					while (tempR.x != 0 && board[tempR.x - 1][tempR.y]
							&& !(tempR.x == result.x && tempR.y == result.y)) {
						tempR.x--;
					}
					while (tempB.x != 0 && board[tempB.x - 1][tempB.y] && !(tempB.x - 1 == tempR.x && tempB.y == tempR.y)
							&& !(tempB.x == result.x && tempB.y == result.y)) {
						tempB.x--;
					}
					if (tempR.x == result.x && tempR.y == result.y
							&& tempB.x - 1 == tempR.x && tempB.y == tempR.y) {
						tempB.x--;
					}
				}
				if(tempR.x != stat.red.x || tempR.y != stat.red.y || tempB.x != stat.blue.x || tempB.y != stat.blue.y) next.add(new State(tempR, tempB, stat.num + 1));

				tempR = new Point(stat.red.x, stat.red.y);
				tempB = new Point(stat.blue.x, stat.blue.y);

				if (tempR.x < tempB.x && tempR.y == tempB.y) {
					while (tempB.x != n - 1 && board[tempB.x + 1][tempB.y]
							&& !(tempB.x == result.x && tempB.y == result.y)) {
						tempB.x++;
					}
					while (tempR.x != n - 1 && board[tempR.x + 1][tempR.y]
							&& !(tempR.x == result.x && tempR.y == result.y)
							&& !(tempR.x + 1 == tempB.x && tempR.y == tempB.y)) {
						tempR.x++;
					}
				} else {
					while (tempR.x != n - 1 && board[tempR.x + 1][tempR.y]
							&& !(tempR.x == result.x && tempR.y == result.y)) {
						tempR.x++;
					}
					while (tempB.x != n - 1 && board[tempB.x + 1][tempB.y]
							&& !(tempB.x + 1 == tempR.x && tempB.y == tempR.y)
							&& !(tempB.x == result.x && tempB.y == result.y)) {
						tempB.x++;
					}
					if (tempR.x == result.x && tempR.y == result.y
							&& tempB.x + 1 == tempR.x && tempB.y == tempR.y) {
						tempB.x++;
					}
				}
				if(tempR.x != stat.red.x || tempR.y != stat.red.y || tempB.x != stat.blue.x || tempB.y != stat.blue.y) next.add(new State(tempR, tempB, stat.num + 1));

				tempR = new Point(stat.red.x, stat.red.y);
				tempB = new Point(stat.blue.x, stat.blue.y);

				if (tempR.x == tempB.x && tempR.y > tempB.y) {
					while (tempB.y != 0 && board[tempB.x][tempB.y - 1]
							&& !(tempB.x == result.x && tempB.y == result.y)) {
						tempB.y--;
					}
					while (tempR.y != 0 && board[tempR.x][tempR.y - 1] && !(tempR.x == result.x && tempR.y == result.y)
							&& !(tempR.x == tempB.x && tempR.y - 1 == tempB.y)) {
						tempR.y--;
					}
				} else {
					while (tempR.y != 0 && board[tempR.x][tempR.y - 1]
							&& !(tempR.x == result.x && tempR.y == result.y)) {
						tempR.y--;
					}
					while (tempB.y != 0 && board[tempB.x][tempB.y - 1]
							&& !(tempB.x == tempR.x && tempB.y - 1 == tempR.y)
							&& !(tempB.x == result.x && tempB.y == result.y)) {
						tempB.y--;
					}
					if (tempR.x == result.x && tempR.y == result.y
							&& tempB.x == tempR.x && tempB.y - 1 == tempR.y) {
						tempB.y--;
					}
				}
				if(tempR.x != stat.red.x || tempR.y != stat.red.y || tempB.x != stat.blue.x || tempB.y != stat.blue.y) next.add(new State(tempR, tempB, stat.num + 1));

				tempR = new Point(stat.red.x, stat.red.y);
				tempB = new Point(stat.blue.x, stat.blue.y);

				if (tempR.x == tempB.x && tempR.y < tempB.y) {
					while (tempB.y != m - 1 && board[tempB.x][tempB.y + 1]
							&& !(tempB.x == result.x && tempB.y == result.y)) {
						tempB.y++;
					}
					while (tempR.y != m - 1 && board[tempR.x][tempR.y + 1]
							&& !(tempR.x == result.x && tempR.y == result.y)
							&& !(tempR.x == tempB.x && tempR.y + 1 == tempB.y)) {
						tempR.y++;
					}
				} else {
					while (tempR.y != m - 1 && board[tempR.x][tempR.y + 1]
							&& !(tempR.x == result.x && tempR.y == result.y)) {
						tempR.y++;
					}
					while (tempB.y != m - 1 && board[tempB.x][tempB.y + 1]
							&& !(tempB.x == tempR.x && tempB.y + 1 == tempR.y)
							&& !(tempB.x == result.x && tempB.y == result.y)) {
						tempB.y++;
					}
					if (tempR.x == result.x && tempR.y == result.y
							&& tempB.x == tempR.x && tempB.y + 1 == tempR.y) {
						tempB.y++;
					}
				}
				if(tempR.x != stat.red.x || tempR.y != stat.red.y || tempB.x != stat.blue.x || tempB.y != stat.blue.y) next.add(new State(tempR, tempB, stat.num + 1));

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
