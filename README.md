## 자바와 JUnit을 활용한 실용주의 단위 테스트 - 제프 랭어, 앤디 헌트, 데이브 토마스

**단위 테스트를 작성하는 이유**

- 실행 시간을 단축
- 회귀 테스팅(regression testing)를 통해 잠재적인 문제 방지
- 테스트 코드를 통해 실제 서비스의 동작을 빠른 시간 내에 분석

**JUnit에서 각 단위 테스트는 고유의 맥락을 갖는다.**

- 즉, JUnit은 결정된 순서로 테스트를 실행하지 않으며, 모든 테스트는 다른 테스트 결과에 영향을 받지 않는다.