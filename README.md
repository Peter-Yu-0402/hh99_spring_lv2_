# 📚스파르타 도서관 서버 만들기
---
항해 99 페어 과제
민가람 | 유재성

## 🤔요구사항
---
- [X]  도서 등록 기능
    - `제목`, `저자`, `언어`, `출판사`,`등록일`을 저장할 수 있습니다.
    - 저장된 도서의 정보를 반환 받아 확인할 수 있습니다.
- [X]  선택한 도서 정보 조회 기능
    - 선택한 도서의 정보를 조회할 수 있습니다.
- [X]  도서 목록 조회 기능
    - 등록된 도서 전체를 조회할 수 있습니다.
    - 조회된 도서 목록은 `등록일` 기준 오름차순으로 정렬 되어있습니다.
- [X]  도서관 회원 등록 기능
    - `이름`, `성별`, `주민번호`,`전화번호`, `주소`를 저장할 수 있습니다.
        - `주민번호` 와 `전화번호` 는 중복될 수 없습니다.
    - 저장된 회원의 정보를 반환 받아 확인할 수 있습니다.
        - 반환 받은 회원의 정보에 `주민번호`는 제외 되어있습니다.
- [X]  선택한 도서 대출 기능
    - 회원은 선택한 도서를 대출받을 수 있습니다.
        - `도서의 식별값`과 `회원의 식별값`이 함께 요청됩니다.
        - 반납하지 않은 책이 있다면 대출이 불가능합니다.
        - 선택한 도서가 현재 대출 상태라면 대출이 불가능합니다.
    - 회원의 대출 내역을 기록할 수 있습니다.
        - 대출 내역 기록에는 `도서의 식별값`, `회원의 식별값`, `반납상태`, `대출일`, `반납일`을 저장할 수 있습니다.
        - JPA의 연관관계 매핑 애너테이션을 사용하지 않고 구현합니다.
    - 대출 성공을 확인할 수 있는 값을 반환합니다.
        - ex) HTTP Status Code, Error Message …
- [X]  선택한 도서 반납 기능
    - 선택한 도서를 반납할 수 있습니다.
        - 대출 내역 기록의 `반납상태`와 `반납일`이 변경됩니다.
- [X]  대출 내역 조회 기능
    - 회원의 대출 내역 기록을 조회할 수 있습니다.
        - 대출 내역 기록에는 회원의 `이름`과 `전화번호`, 도서의 `제목`과 `저자`가 포함 되어있어야 합니다.
    - 조회된 대출 내역 기록은 `대출일` 기준 오름차순으로 정렬 되어있습니다.

## 🔧스택
---
- java 17
- Spring Gradle(8.5)
- Spring Boot (3.1.9)
- Spring JPA
- Swagger UI
- MySql



## Use Case Diagram
---
![](https://opaque-tail-d2e.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F875d8bef-1ed4-4b82-a5be-63dce4f8d27e%2Fe87cb94c-41ca-4480-a943-fe4872e47ba8%2FUse-case_Spring_lvl02_v2.png?table=block&id=8506b885-350f-44d9-9d67-7c24b1cbbb10&spaceId=875d8bef-1ed4-4b82-a5be-63dce4f8d27e&width=2000&userId=&cache=v2)




## ERD
---
![](https://opaque-tail-d2e.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F875d8bef-1ed4-4b82-a5be-63dce4f8d27e%2F9b8f34ff-be59-4471-8e30-91872efd04fa%2FUntitled.png?table=block&id=6ef0e63e-17e6-4953-8d3d-4001c5f885b7&spaceId=875d8bef-1ed4-4b82-a5be-63dce4f8d27e&width=2000&userId=&cache=v2)





## API 명세서
---
[📝스파르타 도서관 API 명세서](https://github.com/ramizzang/hh99_spring_lv2/blob/main/%EB%8F%84%EC%84%9C%EA%B4%80%20API%20%EB%AA%85%EC%84%B8%EC%84%9C.pdf)
