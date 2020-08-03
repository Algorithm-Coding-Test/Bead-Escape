# Bead-Escape
## 구슬 탈출 2
BAEKJOON ONLINE JUDGE
13460번 문제


#### 문제
[https://www.acmicpc.net/problem/13460](https://www.acmicpc.net/problem/13460)
#### 풀이
모든 경우의 수에 대하여 *BFS* 알고리즘을 통해 문제를 해결했다.

보드의 상태를 나타내는 `State`,  각 구슬의 위치를 나타내는 `Point`를 통해 보드 위 구슬들을 표현한다.

빨간 구슬과 파란 구슬의 초기값으로 초기 `State`를 구성하여 `ArrayList<State> next`에 삽입한다.

`next`가 빌 때까지 아래를 반복한다.
- `next`에서 `State`를 꺼내, 해당 `State`에서 발생할 수 있는 모든 경우에 따른 새로운 `State`를 `next`에 삽입한다.
- 파란 구슬이 빨간 구슬보다 먼저 탈출한 경우의 `State`는 `next`에 삽입하지 않는다.
- `State`에서 파생된 새로운 `State`는 보드를 움직인 횟수 `num`이 1 추가된다.
- 빨간 구슬이 탈출한 경우 `State`의 `num`이 10 이하인 경우 `resultNum`과 비교하여 더 작은 경우 `resultNum`을 갱신한다.
