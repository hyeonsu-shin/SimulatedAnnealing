# SimulatedAnnealing
## 모의 담금질 구현
### 3~4차 함수의 전역 최적점 찾기
초기온도(t) : 1000, 냉각률(c) : 0.95, 자유롭게 탐색할 확률(p) : e^(-d/t)    
종료조건(반복 횟수) : t가 0.01이 될 때까지    
f(x) = 3x^4 - 8x^3 - 6x^2 + 24x + 9 함수의 전역 최적해를 찾을 수 있었다.

---
+ 모델 선정은 찾다가 힘들어서 주변에 사시는 분들의 자녀 나이와 부모 나이를 분포로 선정하였습니다.

## Data
---

|자녀 나이|부모 나이|
|:------:|:---:|
|1|32|
|2|28|
|3|36|
|6|43|
|9|40|
|12|39|
|15|45|
|17|37|
|20|55|

## Model Setting
---
![모델1](https://user-images.githubusercontent.com/80369791/121343949-2a606000-c95e-11eb-96ff-cec1d26042f5.PNG)

* 각 점은 자녀 나이에 따른 부모나이를 분포로 나타낸것이다.
* 직선은 선형으로 데이터들의 회귀식이다.
* 회귀식: y=x+28

## Code Review
---
'''
public static void main(String[] args) {

        SimulatedAnnealing sa = new SimulatedAnnealing(1, 0.97, 100);
        sa.solve(new Problem() {
            @Override
            public double fit(double a,double b) {

                return 10000*a*a + 2000*a*b + b*b - 14000*a - 1400*b + 400 ;
            }

            @Override
            public boolean isNeighborBetter(double f0, double f1) {
                return f1 > f0;
            }
        }, 1, 20);

        System.out.println(sa.hist);
        System.out.println(sa.hista);
        System.out.println(sa.histb);

    }
'''
* 코드는 수업에서 사용한 코드에 추가적으로 조금만 수정하였습니다.
* 에러가 가장 작아지는 파라미터 ( a, b) 값을 비교

## Parameter Astimate
---
![모델](https://user-images.githubusercontent.com/80369791/121346336-f3d81480-c960-11eb-811d-57daeb528910.PNG)

* 위 데이터는 결과 값중 일부분입니다.
* 첫번째 값을 보게 되면 a=19.8 b=46.05 으로 오차를 고려 하면, 실제값 a=20 b=45과 비슷한 것을 알 수 있었습니다.
 
 ## 결론
 * 자녀의 나이와 부모의 나이는 y=x+28의 관계식으로 설명할 수 있었다.
 *  담금질 알고리즘에서는 kt값을 다르게 설정하거나 초기 온도, 냉각률 등을 바꾸어서 반복 횟수를 늘린다면 성능은 안 좋아질 수 있지만 최적해를 찾을 가능성이 더 높아질 수 있다.
