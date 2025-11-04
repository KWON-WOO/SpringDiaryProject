# ERD

<img width="968" height="199" alt="Image" src="https://github.com/user-attachments/assets/3ea5b7ff-854f-48f1-901c-618cee5f2abb" />

# 📘 Diary API Documentation

## 📌 개요

Spring Boot 기반의 다이어리 프로젝트 API 명세서입니다. 일정 등록, 조회, 수정, 삭제 및 댓글 등록 기능을 제공합니다.

---

## 📂 API 목록

- [일정 등록](#일정-등록)
- [일정 조회](#일정-조회)
- [일정 수정](#일정-수정)
- [일정 삭제](#일정-삭제)
- [댓글 등록](#댓글-등록)

---

## 🗓️ 일정 등록

- **URL**: `POST /schedules`
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
  "title",
  "title": "노래방",
  "content": "마시고 먹고 지르고 흔들고",
  "name": "KWONWOO",
  "createdAt": "2025-11-04T15:12:00",
  "ModifiedAt": "2025-11-04T15:12:00"
}
```

- **Status Code**:
- `201 Created`: 등록 성공
- `400 Bad Request`: 필수값 누락

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
- Status Code:
- 200 OK
- Null

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
- Status Code:
- 200 OK
- 404 Not Found: 해당 일정 없음

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

- Status Code:
- 201 Created
- 404 Not Found: 일정 없음