# Job Scheduling

- 작업 스케줄링(Job Scheduling) 문제
  - n개의 작업, 각 작업의 수행 시간 t<sub>i</sub>, 1 =1, 2, 3, ⋯, n, 그리고 m개의 동일한 기계가 주어질 때, 모든 작업이 가장 빨리 종료되도록 작업을 기계에 배정하는 문제이다.
  - 한 작업은 배정된 기계에서 연속적으로 수행되어야 한다.
  - 기계는 1번에 하나의 작업만을 수행한다.



- 근사해 구하기 
  - greedy 알고리즘을 이용하여 현재까지 배정된 작업에 대해 가장 빨리 끝나는 기계에 새 작업을 배정한다.
- 최적해 구하기
  - brute force 알고리즘을 이용하여 작업이 기계에 배정될 수 있는 모든 경우의 수를 구한다.



------



# 코드 구현

### 수도 코드

> ```html
> 입력: n개의 작업, 각 작업의 수행 시간 t<sub>i</sub>, 1 = 1, 2, 3, ⋯, n, 기계 M<sub>j</sub>,
> j = 1, 2, 3, ⋯, m
> 출력: 모든 작업이 종료된 시간
> for j = 1 to m
> 	L[j] = 0		// L[j] = 기계 M<sub>j</sub>에 배정된 마지막 작업의 종료 시간
> for i = 1 to n {
> 	min = 1
> 	for j = 2 to m	// 가장 일찍 끝나는 기계를 찾는다.
> 		if (L[j] < L[min]) 
>         {
>             min = j
>         }
>     	작업 i를 기계 M<sub>j</sub>에 배정한다.
>         L[min] = L[min] + t<sub>i</sub>
> 	}
> return 가장 늦은 작업 종료 시간
> ```



### 코드 구현

> ```java
> import java.util.Random;
> 
> public class JobSchedulling {
>     public static void main(String[] args) {
>         int n = 4;      // n = 작업의 갯수
>         int m = 2;     // m = 기계의 갯수
>         int[] t = new int[n];       // t = 각 작업의 수행 시간
>         Random random = new Random();
>         System.out.print("작업 시간 : ");
>         for (int i = 0; i < n; i++) {
>             t[i] = random.nextInt(10) + 1;
>             System.out.printf("%d ", t[i]);
>         }
>         System.out.println();
>         System.out.println(Approx_schedule(n, m, t));
>     }
> 
>     public static int Approx_schedule(int n, int m, int[] t) {
>         int[] L = new int[m];       // L = 각 기계의 마지막 작업 종료 시간
>         for (int j = 0; j < m; j++) {
>             L[j] = 0;
>         }
>         // 제일 먼저 끝나는 기계에 작업 배정
>         for (int i = 0; i < n; i++) {
>             int min = 0;
>             for (int j = 1; j < m; j++) {
>                 if (L[j] < L[min]) {
>                     min = j;
>                 }
>             }
>             L[min] = L[min] + t[i];
>         }
>         // 가장 마미가으로 종료되는 시간 찾기
>         int max = L[0];
>         for (int i = 1; i < m; i++) {
>             if (L[i] > max) {
>                 max = L[i];
>             }
>         }
>         return max;
>     }
> }
> ```



### 수행 결과

> - n = {4, 8, 16}
> - m = 2
> - t = 1 ~ 10
>
> `n = 4`
>
>![1](https://user-images.githubusercontent.com/80325051/118653877-ef698180-b822-11eb-8407-06f7db35aa2c.PNG)
>
> `n = 8`Cancel changes
>
>![2](https://user-images.githubusercontent.com/80325051/118654115-2d66a580-b823-11eb-8757-38d2dc1908fa.PNG)
>
> `n = 16`
>
> ![](C:\Users\PC\Desktop\3.PNG)





------



# 시간복잡도

### **greedy**

```
- n개의 작업을 정렬하는데 O(nlogn) 시간
- while-루프에서 작업을 L에서 가져다가 수행 가능한 기계를 찾아 배정하므로 O(m)이 걸린다.
(m은 사용된 기계의 수)
- while-루프가 수행된 총 횟수는 n번이므로, line 5~12까지는 O(m)*n = O(mn) 시간이 걸린다.

따라서 O(nlogn) + O(nm)이다.
```



### **brute force**

```
- Approx_JobScheduling 알고리즘은 n개의 작업을 하나씩 가장 빨리 끝나는 기계에 배정한다.
- 이러한 기계를 찾기 위해 for-루프가 (m-1)번 수행된다.
- 따라서 O(m) 시간 동안 기계의 작업 종료 시간을 살핀다.
- 또한, n개의 작업을 배정하고, 마지막으로 배열을 탐색한다.

따라서 n*O(m) + O(m) = O(nm)
```



------



# 근사 비율

- Approx_Job-scheduling 알고리즘의 근사해를 OPT’, 최적해를 OPT라고 한다. 

  - OPT’ <= 2*OPT이다.

  - 근사해는 최적해의 2배를 넘지 않는다.



> 그 이유는 다음과 같다.

![](https://dudri63.github.io/image/algo38-4.png)

> 가장 마지막으로 배정된 작업 i가 T부터 수행, OPT’ = T + ti이다.
> 또한, T’는 작업 i를 제외한 평균 종료 시간이다.
> 따라서 T <= T’이다.

![](https://dudri63.github.io/image/algo38-5.png)

