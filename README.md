# [여기있소](http://ec2-3-34-196-183.ap-northeast-2.compute.amazonaws.com)

## 소개
- 저희는 폐업으로 인한 자영업자들의 물품을 기부하는 플랫폼을 만들었습니다.
- [바로가기](https://ec2-3-34-196-183.ap-northeast-2.compute.amazonaws.com)
- 개발인원 : [조윤빈](https://github.com/yunbinni), [김도희](https://github.com/KIM-DO-HEE)
- 개발기간 :
- 맡은 역할
  - 공통 : ERD 설계
  - 조윤빈 : 게시글 관련, 카카오맵 API
  - 김도희 : 회원 관련, 채팅, 약속

## 기술스택

### Back End Skills
![SPRINGBOOT](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![JAVA](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=Java&logoColor=white)
![JPA](https://img.shields.io/badge/JPA-59666C?style=for-the-badge)
![QUERYDSL](https://img.shields.io/badge/Querydsl-0289CF?style=for-the-badge)
![MYSQL](https://img.shields.io/badge/MySQL-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)

### Front End Skills
![THYMELEAF](https://img.shields.io/badge/thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white)
![JAVASCRIPT](https://img.shields.io/badge/JavaScript-323330?style=for-the-badge&logo=javascript&logoColor=F7DF1E)
![BOOTSTRAP](https://img.shields.io/badge/Bootstrap_5-7952B3?style=for-the-badge&logo=Bootstrap&logoColor=white)
![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)

### Tools and Infra
![AMAZON EC2](https://img.shields.io/badge/Amazon_EC2-FF9900?style=for-the-badge&logo=amazon-ec2&logoColor=white)
![GIT](https://img.shields.io/badge/GIT-E44C30?style=for-the-badge&logo=git&logoColor=white)
![GITHUB](https://img.shields.io/badge/Github-181717?style=for-the-badge&logo=GitHub&logoColor=white)

### Etc
![KAKAOMAP](https://img.shields.io/badge/kakao_map-ffcd00?style=for-the-badge&logo=Kakao&logoColor=black)
![SOCKET](https://img.shields.io/badge/Socket-010101?style=for-the-badge&logo=socket.io)

## 프로젝트 주요 기능

## 1. 최근 인기글

* 홈 화면에서 최근 올라온 글 중 인기있는 글을 보여줍니다.
* 최근 생성된 글 중 추천수 10 이상을 받으면 보여집니다.

  <img src="https://user-images.githubusercontent.com/59231602/226596306-2ff6575f-4de7-49d5-bae8-a4dc38dd6db0.png" width="700" height="400"/>

## 2. 내 주변 보기
* 회원가입시 입력한 주소와 시/도, 시/구/군이 같은 지역의 글들을 마커로 보여줍니다.
* 마커를 클릭하면 해당 글로 리다이렉트됩니다.

  <img src="https://user-images.githubusercontent.com/59231602/226599352-205775a9-a1bf-4048-a118-e003bbbd7569.gif" width="700" height="400"/>

## 3. 회원가입
* 회원은 회원 정보를 입력하여 가입하면, 여기있소 서비스를 이용할 수 있습니다.
* 이메일 인증을 통해 본인 인증을 할 수 있으며, 무작위로 생성되는 계정을 방지합니다.  

  <img src="https://user-images.githubusercontent.com/47100801/226297770-1c8be501-96f4-4349-9f41-84fc511c2058.gif" width="700" height="300"/>

* 회원가입 시 입력한 주소는  메인 페이지에 내 주변 기부글 찾기의 위치로 반영됩니다.

## 4. 물품 올리기 (게시글 작성)
* 로그인 한 멤버는 자신의 기부물품을 올릴 수 있습니다.
* 가지고 있는 물품 수량을 입력할 수 있습니다.
* 사진을 첨부할 수 있습니다.
* 주소를 입력하면 자동으로 카카오맵에 표시됩니다. (기본주소는 회원가입시 주소)
  <img src="https://user-images.githubusercontent.com/59231602/226597407-266e5d62-2386-4fdb-bb67-81a75927abe9.gif" width="700" height="400"/>

## 5. 물품 검색
* 지역, 업종, 검색어로 원하는 조건을 충족하는 물품을 검색할 수 있습니다.
  <img src="https://user-images.githubusercontent.com/59231602/226597419-03c1d922-47ec-4558-8122-b2ad220399f5.gif" width="700" height="400"/>

## 6. 채팅
- Web Socket, STOMP을 활용하여 채팅 기능 구현

### 6-1. 채팅하기

* 회원은 글 내부에 문의하기 버튼을 클릭하여, 해당 글과 관련하여 문의를 할 수 있다

  <img src="https://user-images.githubusercontent.com/47100801/226149880-8242f2ae-3c97-44da-98c1-48a63bdce095.gif" width="700" height="300"/>


### 6-2. 채팅방 나가기

*  회원은 거래가 종료되었거나, 해당 채팅방이 필요 없을 경우 채팅방을 삭제할 수 있다

  <img src="https://user-images.githubusercontent.com/47100801/226199793-a1340a9a-bf1a-44f8-ab68-cd4bc3f4296b.gif" width="700" height="300"/>


## 7. 예약

* 약속잡기를 통해 물품 예약 및 거래 일정을 정할 수 있다
* 예약 상태는 예약중, 예약취소, 거래 완료로 구분할 수 있다

### 7-1. 예약하기

* 약속잡기를 통해 물품 예약 및 거래 일정을 정할 수 있다
* 약속 잡기 버튼 클릭 후, 날짜/시간 수량을 입력하여 예약을 한다
* 글에 작성한 물품 수량, 예약 수량에 따라 예약 가능 여부를 결정한다

<img src="https://user-images.githubusercontent.com/47100801/226260977-c13dbfd7-807f-4189-925e-590a2e5bb85d.gif" width="700" height="300"/>

###  7-2. 예약 취소

* 사용자는 예약을 취소할 수 있다
* 예약을 취소하면 예약한 수량만큼 예약 수량이 차감된다
* 예약 상태는 "예약 취소"로 변경된다

<img src="https://user-images.githubusercontent.com/47100801/226261979-b5d4e920-2531-43cd-9044-643e3fb58db3.gif" width="700" height="300"/>


### 7-3. 거래 완료

* 거래자가 물품을 전달해당 물품을 전달받았으면, 전달 완료 버튼을 클릭하여
* 거래가 완료되면 글에 업로드된 물품 수량이 예약 수량만큼 차감된다
* 예약 상태는 "거래 완료"로 변경된다

<img src="https://user-images.githubusercontent.com/47100801/226210436-0297de93-67eb-4514-8aab-5a9d78764296.gif" width="700" height="300"/>  

## 액티비티 다이어그램

1. 예약하기
   <!-- ![예약하기](https://user-images.githubusercontent.com/47100801/226205514-da0b0731-4f64-4155-b434-630775d5bd1b.png) -->
   <img src="https://user-images.githubusercontent.com/47100801/226349822-7191a608-dd20-4b26-89c9-ba1fac0f143d.png" width="700" height="700"/>   

2. 문의하기(채팅)
   <!-- ![채팅하기](https://user-images.githubusercontent.com/47100801/226205447-9b5de027-994f-49e9-8d89-c266d5b3f51e.png) -->
   <img src="https://user-images.githubusercontent.com/47100801/226205447-9b5de027-994f-49e9-8d89-c266d5b3f51e.png" width="700" height="600"/>
   
3. 글 검색  

   <img src="https://user-images.githubusercontent.com/59231602/226616188-11cf1999-eb85-49ab-b58d-b11666083375.png" width="700" height="600"/>

4. 내 근처 보기
  <img src="https://user-images.githubusercontent.com/59231602/226616328-dc66f108-c238-41d3-abc4-97a78253eb76.png" width="800" height="600"/>

