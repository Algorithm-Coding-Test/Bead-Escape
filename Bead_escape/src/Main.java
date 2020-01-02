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

			/* 10�� ���� �� �������� */
			if (stat.num > 9)
				break;

			/* �������� ���ۿ� ����, �Ķ����� ���ۿ� �ȵ� */
			if (stat.red.x == result.x && stat.red.y == result.y
					&& !(stat.blue.x == result.x && stat.blue.y == result.y)) {
				/* ���� ���� Ƚ�� */
				if (resultNum > stat.num)
					resultNum = stat.num;

				/* �Ķ����� ���ۿ� �ȵ� */
			} else if (stat.blue.x != result.x || stat.blue.y != result.y) {

				Point tempR = new Point(stat.red.x, stat.red.y);
				Point tempB = new Point(stat.blue.x, stat.blue.y);

				/* �������� �Ķ��� �Ʒ� �ִ� ��� */
				if (tempR.x > tempB.x && tempR.y == tempB.y) {
					/* �Ķ����� �������� �̵� �����ϰ�, �Ķ��� ��ġ�� ������ �ƴ� ��쿡 ��� ���� �̵� */
					while (tempB.x != 0 && board[tempB.x - 1][tempB.y]
							&& (tempB.x != result.x || tempB.y != result.y)) {
						tempB.x--;
					}
					/* �������� �������� �̵� �����ϰ�, ������ ��ġ�� ������ �ƴϸ� �Ķ��� �������� ��� ���� �̵� */
					while (tempR.x != 0 && board[tempR.x - 1][tempR.y] && (tempR.x != result.x || tempR.y != result.y)
							&& (tempR.x - 1 != tempB.x || tempR.y != tempB.y)) {
						tempR.x--;
					}
				} else {
					/* �������� �������� �̵� �����ϰ�, ������ ��ġ�� ������ �ƴϸ� ��� ���� �̵� */
					while (tempR.x != 0 && board[tempR.x - 1][tempR.y]
							&& (tempR.x != result.x || tempR.y != result.y)) {
						tempR.x--;
					}
					/* �Ķ����� �������� �̵� �����ϰ�, �Ķ��� ��ġ�� ������ �ƴϸ� ������ �������� ��� ���� �̵� */
					while (tempB.x != 0 && board[tempB.x - 1][tempB.y] && (tempB.x - 1 != tempR.x || tempB.y != tempR.y)
							&& (tempB.x != result.x || tempB.y != result.y)) {
						tempB.x--;
					}
					/* �������� ���ۿ� ������, �Ķ����� ���� �Ʒ��� ������ �Ķ����� ���ۿ� �� */
					if ((tempR.x == result.x && tempR.y == result.y)
							&& (tempB.x - 1 == tempR.x && tempB.y == tempR.y)) {
						tempB.x--;
					}
				}
				next.add(new State(tempR, tempB, stat.num + 1));

				tempR = new Point(stat.red.x, stat.red.y);
				tempB = new Point(stat.blue.x, stat.blue.y);

				/* �������� �Ķ��� ���� �ִ� ��� */
				if (tempR.x < tempB.x && tempR.y == tempB.y) {
					/* �Ķ����� �Ʒ��� �̵� �����ϰ�, �Ķ����� ���ۿ� ������ ���� ��� ��� �Ʒ��� �̵� */
					while (tempB.x != n - 1 && board[tempB.x + 1][tempB.y]
							&& (tempB.x != result.x || tempB.y != result.y)) {
						tempB.x++;
					}
					/* �������� �Ʒ��� �̵� �����ϰ�, �������� ���ۿ� ������ �ʴ� ���, �Ķ��� �ٷ� ������ ��� �Ʒ��� �̵� */
					while (tempR.x != n - 1 && board[tempR.x + 1][tempR.y]
							&& (tempR.x != result.x || tempR.y != result.y)
							&& !(tempR.x + 1 == tempB.x && tempR.y == tempB.y)) {
						tempR.x++;
					}
				} else {
					/* �������� �Ʒ��� �̵� �����ϰ�, �������� ���ۿ� ������ ���� ��� ��� �Ʒ��� �̵� */
					while (tempR.x != n - 1 && board[tempR.x + 1][tempR.y]
							&& (tempR.x != result.x || tempR.y != result.y)) {
						tempR.x++;
					}
					/* �Ķ����� �Ʒ��� �̵� �����ϰ�, �Ķ����� ���ۿ� ������ �ʰ�, ������ �ٷ� ���� �ƴ� ��� ��� �Ʒ��� �̵� */
					while (tempB.x != n - 1 && board[tempB.x + 1][tempB.y]
							&& !(tempB.x + 1 == tempR.x && tempB.y == tempR.y)
							&& (tempB.x != result.x || tempB.y != result.y)) {
						tempB.x++;
					}
					/* �������� ���ۿ� ������, �Ķ��� �ٷ� �Ʒ��� ������ ��� �Ķ����� ���ۿ� ���� */
					if ((tempR.x == result.x && tempR.y == result.y)
							&& (tempB.x + 1 == tempR.x && tempB.y == tempR.y)) {
						tempB.x++;
					}
				}
				next.add(new State(tempR, tempB, stat.num + 1));

				tempR = new Point(stat.red.x, stat.red.y);
				tempB = new Point(stat.blue.x, stat.blue.y);

				/* �������� �Ķ��� �����ʿ� �ִ� ��� */
				if (tempR.x == tempB.x && tempR.y > tempB.y) {
					/* �Ķ����� �������� �̵� �����ϰ�, �Ķ����� ���ۿ� ������ ���� ��쿡 ��� �������� �̵� */
					while (tempB.y != 0 && board[tempB.x][tempB.y - 1]
							&& (tempB.x != result.x || tempB.y != result.y)) {
						tempB.y--;
					}
					/* �������� �������� �̵� �����ϰ�, �������� ���ۿ� ������ �ʰ�, �Ķ��� �ٷ� ���� �ƴ� ��쿡 ��� �������� �̵� */
					while (tempR.y != 0 && board[tempR.x][tempR.y - 1] && (tempR.x != result.x || tempR.y != result.y)
							&& !(tempR.x == tempB.x && tempR.y - 1 == tempB.y)) {
						tempR.y--;
					}
				} else {
					/* �������� �������� �̵� �����ϰ�, �������� ���ۿ� ������ ���� ��쿡 ��� �������� �̵� */
					while (tempR.y != 0 && board[tempR.x][tempR.y - 1]
							&& (tempR.x != result.x || tempR.y != result.y)) {
						tempR.y--;
					}
					/* �Ķ����� �������� �̵� �����ϰ�, �Ķ����� ���ۿ� ������ �����鼭 ������ �ٷ� ���� �ƴ� ��� ��� �������� �̵� */
					while (tempB.y != 0 && board[tempB.x][tempB.y - 1]
							&& !(tempB.x == tempR.x && tempB.y - 1 == tempR.y)
							&& (tempB.x != result.x || tempB.y != result.y)) {
						tempB.y--;
					}
					/* �������� ���ۿ� ������, ���ۿ��� �ٷ� �Ķ����� �ִ� ��� �Ķ��� ���� ���ۿ� ���� */
					if ((tempR.x == result.x && tempR.y == result.y)
							&& (tempB.x == tempR.x && tempB.y - 1 == tempR.y)) {
						tempB.y--;
					}
				}
				next.add(new State(tempR, tempB, stat.num + 1));

				tempR = new Point(stat.red.x, stat.red.y);
				tempB = new Point(stat.blue.x, stat.blue.y);

				/* ������ �����ʿ� �Ķ����� �ִ� ��� */
				if (tempR.x == tempB.x && tempR.y < tempB.y) {
					/* �Ķ����� ���������� �̵������ϰ�, �Ķ����� ���ۿ� ������ ���� ��� ��� ���������� �̵� */
					while (tempB.y != m - 1 && board[tempB.x][tempB.y + 1]
							&& (tempB.x != result.x || tempB.y != result.y)) {
						tempB.y++;
					}
					/* �������� ���������� �̵������ϰ�, �������� ���ۿ� ������ �����鼭 �ٷ� �����ʿ� �Ķ����� �ƴ� ���, ��� ���������� �̵� */
					while (tempR.y != m - 1 && board[tempR.x][tempR.y + 1]
							&& (tempR.x != result.x || tempR.y != result.y)
							&& !(tempR.x == tempB.x && tempR.y + 1 == tempB.y)) {
						tempR.y++;
					}
				} else {
					/* �������� ���������� �̵������ϰ�, �������� ���ۿ� ������ ���� ��� ��� ���������� �̵� */
					while (tempR.y != m - 1 && board[tempR.x][tempR.y + 1]
							&& (tempR.x != result.x || tempR.y != result.y)) {
						tempR.y++;
					}
					/* �Ķ����� ���������� �̵� �����ϰ�, ���ۿ� ������ �����鼭, �ٷ� �����ʿ� �������� �ƴ� ��� ��� ���������� �̵� */
					while (tempB.y != m - 1 && board[tempB.x][tempB.y + 1]
							&& !(tempB.x == tempR.x && tempB.y + 1 == tempR.y)
							&& (tempB.x != result.x || tempB.y != result.y)) {
						tempB.y++;
					}
					/* �������� ���ۿ� ������, �Ķ��� �ٷ� �������� ������ ��� �Ķ����� ���ۿ� ���� */
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
