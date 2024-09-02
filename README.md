![header](https://capsule-render.vercel.app/api?type=venom&text=Fight%20World!%20Delivery🛵)

O2O 🍔주문 배달🛵 플랫폼을 스프링 부트 기반의 모놀리식 아키텍처로 개발한 팀 프로젝트 입니다.
<br>

## 🐕 Personal Role
<img width="810" alt="스크린샷 2024-09-02 오후 8 51 22" src="https://github.com/user-attachments/assets/90059bf5-1a41-410d-b101-29bb2f70b4d4">


| Name                                    | Role                                                        |
|-----------------------------------------|-------------------------------------------------------------|
| [조원호(팀장)](https://github.com/wonowonow) | Order, Payment, OrderHistory, Review, AI Prompt, AI History |
| [임수진](https://github.com/lsj104)     | Report, AI History, BackOffice Front                        |
| [김소이](https://github.com/soy9)  | Store, Category, Menu, Delivery Area                        |
| [신유섭](https://github.com/shin3766)       | User, Auth, Address                                         |


## ERD
<img width="2217" alt="스크린샷 2024-09-02 오후 8 28 07" src="https://github.com/user-attachments/assets/02d8c56d-c32a-4e0d-a8eb-2df96be9e663">

## Infra
<img width="1193" alt="스크린샷 2024-09-02 오후 8 30 47" src="https://github.com/user-attachments/assets/bd612a26-7fd4-41ca-a99e-23d3c35286eb">

<br>

## 📍 사용 기술 및 개념
### 1. DDD
<img width="855" alt="스크린샷 2024-09-02 오후 9 22 16" src="https://github.com/user-attachments/assets/e7b9b970-c1fe-49c6-ab5a-f8a2fba8ed03">

**도메인 주도 설계(DDD, Domain-Driven Design)** 의 원칙에 따라 도메인을 모델링하였으며, **값 객체(Value Object)** 를 사용하여 도메인에 대한 추상화를 구현하였습니다.</br>
값 객체를 적극 활용하여 코드의 가독성과 유지보수성을 높이고, 도메인 로직을 보다 명확하게 표현하였습니다.

### 2. AOP
<img width="509" alt="스크린샷 2024-09-02 오후 9 19 05" src="https://github.com/user-attachments/assets/9dd248b9-79ab-4679-93bc-a6ab2864af47">

기능 요구사항에 따라서 page의 size는 10, 30, 50 중 하나로 설정해야 하고, 이는 paging 처리하는 로직에서 page의 size 검증이 동일하게 진행됩니다. 따라서 모든 도메인에 중복되는 관심사가 발생하고, 이는 코드의 중복으로 이어집니다. 
이를 해결하기 위해 page size 검증 로직을 인터페이스 기반의 AOP를 통해 통합하여 관리하였습니다.

### 3. 메소드 레벨의 권한 검사( `@PreAuthorize` )
SecurityFilterChain에서 전역적인 보안 정책을 적용하여 공통적인 접근 제어 및 보안 정책을 설정하였습니다. 하지만 공통된 루트라도 권한이 다르게 부여되는 경우 SecurityFilterChain에서 설정이 복잡해집니다. 

따라서 `@PreAuthorize` 어노테이션을 사용하여 메소드 레벨의 권한 검사를 추가적으로 진행하여, 특정 비즈니스 로직에 맞추어 세밀한 권한 제어가 가능하도록 구현했습니다.

</br>

## 🔎 주요 기능
### 🏢 배달 가능 가게
<img width="740" alt="스크린샷 2024-09-02 오후 9 26 52" src="https://github.com/user-attachments/assets/080a9415-48b8-421b-99f3-e191cf2ae6ed">

- 가게 주인은 배달 가능한 지역을 등록할 수 있습니다. 
- 고객은 법정동 위치 기반으로 배달 가능한 가게 목록을 조회할 수 있습니다.

### ✅ 문의 게시판
<img width="1884" alt="스크린샷 2024-09-02 오후 9 40 14" src="https://github.com/user-attachments/assets/20d43f14-a1df-4e47-8d5f-89f3c57c6836">
<img width="1679" alt="스크린샷 2024-09-02 오후 9 45 08" src="https://github.com/user-attachments/assets/5612549c-3736-4b3f-ae2d-cc297b4606f7">

- 고객이 작성한 신고 내용을 확인하고 답변을 작성할 수 있는 문의 게시판입니다.
- 고객은 자신이 작성한 신고만 볼 수 있고 관리자는 모든 신고 내역을 확인할 수 있습니다.

### 🤖 상품 설명 자동 생성 AI
<img width="740" alt="스크린샷 2024-09-02 오후 9 26 52" src="https://github.com/user-attachments/assets/2224eae4-1348-4eda-94ba-6cc56fc08620">
<img width="1635" alt="스크린샷 2024-09-03 오전 12 46 52" src="https://github.com/user-attachments/assets/e8d163cf-8d45-442b-b2a0-167e073c96c3">

- AI API를 연동하여 가게 사장님이 상품 설명을 쉽게 작성할 수 있도록 지원합니다.

### 🏠 스토어
<img width="1603" alt="스크린샷 2024-09-02 오후 10 03 43" src="https://github.com/user-attachments/assets/a4c98cea-b35a-4602-bee0-9fdb91df69b0">
<img width="1286" alt="스크린샷 2024-09-03 오전 12 12 02" src="https://github.com/user-attachments/assets/eaad0f74-f1ac-4898-b5e6-6bee7dd31aed">

- 카테고리로 분류하여 스토어를 추가하고 스토어 주인은 주문내역, 스토어 정보, 주문 처리 및 메뉴 수정이 가능합니다.

### 👥 유저
<img width="1611" alt="스크린샷 2024-09-03 오전 12 14 27" src="https://github.com/user-attachments/assets/9a26109f-9626-4317-84c2-ff4c29b9720e">
<img width="1286" alt="스크린샷 2024-09-02 오후 10 14 18" src="https://github.com/user-attachments/assets/f541ec4f-4c57-4f8f-9276-ed62d87bd626">

- 사용자 권한을 CUSTOMER, OWNER, MANAGER, MASTER로 분류하고 Spring Security와 JWT를 이용해 권한 관리가 가능하도록 했습니다.

## 📋 API 명세서
![users](https://github.com/user-attachments/assets/5a8a4ecc-2d5d-4c4b-99ee-fb7fcdb563d1)

![auth](https://github.com/user-attachments/assets/10890259-8409-475e-952d-040953192549)

![address](https://github.com/user-attachments/assets/ff4d89f2-22bf-456b-8688-f8f9ff3c2153)

![store](https://github.com/user-attachments/assets/7adb1f68-ea86-4c72-b223-2c9e81ac2871)

![store-category](https://github.com/user-attachments/assets/b5449b56-9ef2-4d9a-b56a-9b0afbaed91b)

![delivery-area](https://github.com/user-attachments/assets/02b72bf8-c584-4931-977d-a76d20d32977)

![menu](https://github.com/user-attachments/assets/52cc4a9f-9f95-40c6-ba40-10c9e8a51cfa)

![order](https://github.com/user-attachments/assets/4fae3f25-f523-4130-a7d3-019a009fc3eb)

![payment](https://github.com/user-attachments/assets/4ac03367-e763-4020-888d-8f6320a98417)

![review](https://github.com/user-attachments/assets/5ee5cdc1-0d01-4651-ae11-f3cb2250a0f9)

![report2](https://github.com/user-attachments/assets/2ed0cbfd-ba63-44b2-97af-14ca6abf092c)

![ai](https://github.com/user-attachments/assets/f4fb5e25-e89f-4b66-8142-f18252a1ecd7)

