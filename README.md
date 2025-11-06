# 🗓️ Private Schedule & Comment API

일정 공유 및 댓글 기능을 제공하는 웹 서비스입니다.  
비밀번호 기반 인증을 통해 일정 삭제 및 댓글 작성이 가능하며, 커스텀 예외 처리와 유효성 검사를 시행하는 API를 제공합니다.

---

## 🚀 주요 기능

- 일정 등록, 조회, 삭제
- 댓글 등록, 조회
- 비밀번호 기반 인증
- 댓글 수 상한치 제한
- 입력값 유효성 검사 (`@Valid`, `@NotBlank`, `@Size`)
- 커스텀 예외 처리 (`PasswordMismatchException`, `CommentOverflowException` 등)
- 전역 예외 처리 (`ServiceExceptionHandler`)

---

## 🛠️ 기술 스택

- Java 17+
- Spring Boot
- Spring Web MVC
- Spring Validation
- Lombok
- Gradle

---
# 📬 API 예시
- POST /schedules → 일정 등록
- DELETE /schedules/{id} → 일정 삭제 (비밀번호 필요)
- POST /schedules/{id}/comments → 댓글 작성
- GET /schedules/{id}/comments → 댓글 조회

# 🧠 설계 철학
- 비즈니스 로직은 서비스에서 처리
- 예외는 상황별로 명확하게 정의
- 예외 처리는 핸들러에서 분리하여 관리
- 클라이언트에게는 일관된 에러 응답 제공

# 📌 기타
- 테스트 코드 작성 예정
- Swagger 연동 가능 (추후 추가)


# ERD

<img width="1237" height="244" alt="Image" src="https://github.com/user-attachments/assets/ab7a98e1-8eb4-444a-9638-6cd71fc36faf" />

# 📘 Diary API 명세서

## 📌 개요

Spring Boot 기반의 다이어리 프로젝트 API 명세서입니다. 일정 등록, 조회, 수정, 삭제 및 댓글 등록 기능을 제공합니다.

---

## 📂 API 목록

- [일정 등록](#%EF%B8%8F-일정-등록)
- [일정 조회](#-일정-조회)
- [일정 수정](#%EF%B8%8F-일정-수정)
- [일정 삭제](#%EF%B8%8F-일정-삭제)
- [댓글 등록](#-댓글-등록)

---

## 🗓️ 일정 등록

- **URL**: `/schedules`
- **Method**:`Post`
- **설명**: 새로운 일정을 등록합니다.
- **Request Body**:

```json
{
  "title": "노래방",
  "content": "마시고 먹고 지르고 흔들고",
  "name": "KWONWOO",
  "password": "123422"
}
```

- **Response**:

```json
{
  "id": 1,
  "title": "노래방",
  "content": "마시고 먹고 지르고 흔들고",
  "name": "KWONWOO",
  "createdAt": "2025-11-04T15:12:00",
  "ModifiedAt": "2025-11-04T15:12:00"
}
```

- **Status Code**:
- `201 Created`: 등록 성공
- `400 Bad Request`: 잘못된 리퀘스트. 요청값이 잘못됨.
- `404 Not Found`: 요청 주소가 잘못됨.

## 🔍 일정 조회


- **설명**:  전체 일정 혹은 특정 작성자의 일정을 조회합니다.


- ### 전체일정
    - **Response**:

        ```json
        { 
        "names": {
              "id": 1, 
            "title": "노래방",
            "content": "먹고 마시고 지르고 흔들고",
            "name": "KWONWOO",
            "createDate": "2025-11-04T15:12:00",
            "updateDate": "2025-11-04T15:12:00"
            }
        }
        ```
  - **Status Code**:
  - `200 OK`: 조회완료
  - `404 NotFound`: 잘못된 주소 입력값

- ### 특정일정
  - **URL**: `GET /schedules/{name}`

  - **Request Body**:
    ```json
    {
    "name": "KWONWOO"
    }
    ```
  - **Response**:

    ```json
    { 
    "names": {
          "id": 1, 
        "title": "노래방",
        "content": "먹고 마시고 지르고 흔들고",
        "name": "KWONWOO",
        "createDate": "2025-11-04T15:12:00",
        "updateDate": "2025-11-04T15:12:00"
        }
    }
    ```
  - **Status Code**:
  - `200 OK`: 조회 완료.
  - `400 Bad Request`: 잘못된 리퀘스트. 요청값이 잘못됨.
  - `404 Not Found`: 요청 주소가 잘못됨.

## ✏️ 일정 수정

- **URL**: `PUT /schedules/{id}`
- **설명**: 특정 일정의 제목과 작성자명만 수정 가능.
- **Request Body**:

```json
{
  "title": "당구장",
  "name": "그누",
  "password": "123422"
}
```
- **Response**:

```json
{
  "id": 1,
  "title": "당구장",
  "content": "먹고 마시고 지르고 흔들고",
  "name": "그누",
  "createDate": "2025-11-04T15:12:00",
  "updateDate": "2025-11-04T17:00:00"
}
```
- **State Code**:
- `200 OK`: 수정완료
- `400 Bad Request`: 잘못된 입력값.
  `404 Not Found`: 요청 주소가 잘못됨.
## 🗑️ 일정 삭제

- **url**:`DELETE /schedules/{id}`
- 설명: 특정 일정을 삭제합니다.
- **Request Body**:
```json
{
  "id": 1,
  "password": "1234233"
}
```

- **Response**:

```json
{
"id": 101,
"createDate": "2025-11-04 4:40:00"
}
```
- **State Code**:
- `204 No Content`: 삭제 완료
- `400 Bad Request`: 잘못된 입력값.
- `404 Not Found`: 요청 주소가 잘못됨.
## 💬 댓글 등록

- **URL**: `POST /schedules/{scheduleId}/comments`
- **설명**: 특정 일정에 댓글을 등록합니다.
- **Request Body**:

```json
{
  "comment": "재미있겠다...",
  "name": "용현철호",
  "password": "abcd"
}
```

- **Response**:

```json
{
  "id": 101,
  "createDate": "2025-11-04 4:40:00"
}
```

- **Status Code**:
- `201 Created`: 등록 성공
- `400 Bad Request`: 잘못된 리퀘스트. 요청값이 잘못됨.
- `404 Not Found`: 요청 주소가 잘못됨.
