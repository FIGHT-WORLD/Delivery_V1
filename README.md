![header](https://capsule-render.vercel.app/api?type=venom&text=Fight%20World!%20Delivery)

O2O 배달 플랫폼을 스프링 부트 기반의 모놀리식 아키텍처로개발한 팀 프로젝트 입니다.
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

## DDD
<img width="855" alt="스크린샷 2024-09-02 오후 9 22 16" src="https://github.com/user-attachments/assets/e7b9b970-c1fe-49c6-ab5a-f8a2fba8ed03">

## AOP
<img width="509" alt="스크린샷 2024-09-02 오후 9 19 05" src="https://github.com/user-attachments/assets/9dd248b9-79ab-4679-93bc-a6ab2864af47">

## PREAUTHORIZE
<img width="599" alt="스크린샷 2024-09-02 오후 9 20 02" src="https://github.com/user-attachments/assets/54d2e48e-00d0-45a7-bce4-b0cc9eeeaf25">

## 주요 기능
#### 🏢 배달 가능 가게
<img width="740" alt="스크린샷 2024-09-02 오후 9 26 52" src="https://github.com/user-attachments/assets/080a9415-48b8-421b-99f3-e191cf2ae6ed">

#### ✅ 문의 게시판
<img width="1884" alt="스크린샷 2024-09-02 오후 9 40 14" src="https://github.com/user-attachments/assets/20d43f14-a1df-4e47-8d5f-89f3c57c6836">
<img width="1679" alt="스크린샷 2024-09-02 오후 9 45 08" src="https://github.com/user-attachments/assets/5612549c-3736-4b3f-ae2d-cc297b4606f7">

고객이 작성한 신고 내용을 확인하고 답변을 작성할 수 있는 문의 게시판입니다.
고객은 자신이 신고한 내역만 볼 수 있고 관리자는 모든 신고 내역을 확인할 수 있습니다.

#### 🤖 상품 설명 자동 생성 AI
<img width="740" alt="스크린샷 2024-09-02 오후 9 26 52" src="https://github.com/user-attachments/assets/2224eae4-1348-4eda-94ba-6cc56fc08620">
AI API를 연동하여 가게 사장님이 상품 설명을 쉽게 작성할 수 있도록 지원합니다.

#### 🏠 스토어
<img width="1603" alt="스크린샷 2024-09-02 오후 10 03 43" src="https://github.com/user-attachments/assets/a4c98cea-b35a-4602-bee0-9fdb91df69b0">
<img width="1374" alt="스크린샷 2024-09-02 오후 10 04 37" src="https://github.com/user-attachments/assets/8f6f5c1c-0bf1-426c-a676-734cd7cb3eae">
카테고리로 분류하여 스토어를 추가하고 스토어 주인은 주문내역, 스토어 정보, 주문 처리 및 메뉴 수정이 가능합니다.

#### 👥 유저
<img width="1613" alt="스크린샷 2024-09-02 오후 10 13 28" src="https://github.com/user-attachments/assets/f05de06a-fc64-46bf-9e42-734566e6d524">
<img width="1286" alt="스크린샷 2024-09-02 오후 10 14 18" src="https://github.com/user-attachments/assets/f541ec4f-4c57-4f8f-9276-ed62d87bd626">
사용자 권한을 CUSTOMER, OWNER, MANAGER, MASTER로 분류하고 Spring Security와 JWT를 이용해 권한 관리가 가능하도록 했습니다.

## API 명세서
<img width="633" alt="스크린샷 2024-09-02 오후 9 07 32" src="https://github.com/user-attachments/assets/8be1f7ea-1c62-4cec-96f6-dae0a276c81a">
<img width="668" alt="스크린샷 2024-09-02 오후 9 09 22" src="https://github.com/user-attachments/assets/89f633c3-0f2c-47e3-b930-20b6538591ee">
<img width="590" alt="스크린샷 2024-09-02 오후 9 10 56" src="https://github.com/user-attachments/assets/a761d543-36c3-4096-97c9-6dd53b116fb1">
<img width="668" alt="스크린샷 2024-09-02 오후 9 12 13" src="https://github.com/user-attachments/assets/7a8e78d1-9dc2-41e4-88be-88afceaa6ccb">
<img width="670" alt="스크린샷 2024-09-02 오후 9 15 23" src="https://github.com/user-attachments/assets/e4cfeb39-8518-48bc-8e99-921cb4f49fec">
<img width="686" alt="스크린샷 2024-09-02 오후 9 16 29" src="https://github.com/user-attachments/assets/3abff06c-72a0-4024-bfe5-4a6d92aeb14c">

