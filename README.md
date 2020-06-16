## 자바와 JUnit을 활용한 실용주의 단위 테스트 - 제프 랭어, 앤디 헌트, 데이브 토마스

**단위 테스트를 작성하는 이유**

- 실행 시간을 단축
- 회귀 테스팅(regression testing)를 통해 잠재적인 문제 방지
- 테스트 코드를 통해 실제 서비스의 동작을 빠른 시간 내에 분석

**JUnit에서 각 단위 테스트는 고유의 맥락을 갖는다.**

- 즉, JUnit은 결정된 순서로 테스트를 실행하지 않으며, 모든 테스트는 다른 테스트 결과에 영향을 받지 않는다.

**JUnit hamcrest 을 이용하여 가능한 검사**

- 객체 타입 검사
- 두 객체의 참조가 같은 인스턴스 인지 확인
- 다수의 matcher를 결합하여 둘 다 혹은 둘 중 어떤 것이든 성공하는지 검사
- 어떤 컬렉션이 요소를 포함하거나 조건에 부합하는지 검사
- 어떤 컬렉션이 아이템 몇 개를 모두 포함하는지 검사
- 어떤 컬렉션에 있는 모든 요소가 matcher를 준수하는지 검사
- 등...

**Assert는 테스트가 실패할 경우 유용한 정보를 빠르게 알려주지만 군더더기 없는 코드를 만드는 것과는 상충 관계(trade-off)이다.**

**JUnit는 3가지 방식으로 예외를 처리할 수 있다.**

- annotation - 단순한 방식
    - 예외가 발생하지 않으면 error 발생

```
@Test(expected=IssufficientFundsException.class)
public void throwsWhenWithdrawingTooMuch() {
	account.withdraw(100);
}
```

- try/catch 와 final - 옛날 방식
    - 예외가 발생한 후 상태를 검사할 때 유용

```
try {
	account.withdraw(100);
	fail();
} catch(IssufficientFundsException expected) {
	assertThat(expected.getMessage(), equalTo("balance only 0"));
}
```

- ExpectedException - 새로운 방식
    - 위 두가지 방법의 장점을 모았다.

```
@Rule
public ExpectedException thrown = ExpectedException.none();

@Test
public void exceptionRule() {
	thrown.expect(IssufficientFundsException.class);
	thrown.expectMessage("baleance only 0");

	account.withdraw(100);
}
```

**AAA(Arrange - Act - Assert), After**

- Arrange
    - 테스트 코드를 실행하기 전에 시스템이 적절한 상태에 있는지 확인(객체를 생성하거나 다른 API를 호출하는 것 등)
- Act
    - 테스트 코드를 실행(보통은 단일 메서드를 호출)
- Assert
    - 실행한 코드가 기대한 대로 동작하는지 확인(실행한 코드의 반환값, 그 외 필요한 객체들의 새로운 상태를 검사, 테스트한 코드와 다른 객체들 사이의 의사소통을 검사, 등)
- After (때에 따라 필요)
    - 테스트를 실행할 때 어떤 자원을 할당했다면 잘 정리(clean up) 되었는지 확인

**단일 목적 테스트의 가치**

- Assert가 실패했을때 실패한 테스트의 메서드명을 통해 문제를 빠르게 파악할 수 있다.
- 실패한 테스트를 분석하는데 시간을 줄일 수 있다.
- 모든 케이스가 실행되었음을 보장할 수 있다.

**BDD(Behavior-Driven Development) - 행위 주도 개발**

- given - when - then

**테스트 코드는 어떤 형식이든 일관성을 유지하는 것이 중요하다. 주요 목표는 테스트 코드를 다른 사람에게 의미 있게 만드는 것이다.**
