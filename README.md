# steammer-webservice

스팀 웹페이지에서 크롤링한 데이터를 심플한 웹페이지 형태로 보여주는 웹 서비스.

## 2. 사용 기술
#### `Back-end`
- Java 8
- Spring Boot 2.1.9
- Gradle
- Spring Data JPA
- MySql
- Spring Security
- AWS EC2, RDS, Code Deploy
- Travis

#### `Front-end`
- Mustache
- jQuery
- bootstrap

# features
 - steam 웹사이트 크롤링한 데이터 사용
 - 구글,네이버 로그인 연동
 - JPA를 통한 ORM 프로젝트

# 소개

- ### DB ERD

![img_3.png](readmeimg/img_3.png)

- ### Entity ERD

![img_4.png](readmeimg/img_4.png)

- ### 동작 구조

![img_5.png](readmeimg/img_5.png)

## 핵심 트러블 슈팅
###  태그와 페이징 처리 문제
- 저는 이 서비스가 페이스북이나 인스타그램 처럼 가볍고 심플하게 사용되길 바라는 마음으로 개발했습니다.  
  때문에 페이징 처리도 무한 스크롤을 적용했습니다.

- 무한 스크롤이 동작하면, 게임 각각의 태그는  많게는 열가지 이상의 태그를 가지고 있어서

- 웹페이지의 성능이 떨어지기 때문에 개선이 필요했다.

~~~java
//DTO 개선 전
public LimitTagGameResponseDto(Game entity) {
        this.gameId = entity.getGameId();
        this.gameName = entity.getGameName();
        this.gameInfo = entity.getGameInfo();
        this.launchDate = entity.getLaunchDate();
        this.evaluation = entity.getEvaluation();
        this.imgUrl = entity.getImgUrl();
        this.videoUrl = entity.getVideoUrl();
        this.devCompany = entity.getDevCompany();
        this.distributor = entity.getDistributor();
        this.gameTags = entity.gametags;
}
        
//DTO 개선 후
public LimitTagGameResponseDto(Game entity) {
        this.gameId = entity.getGameId();
        this.gameName = entity.getGameName();
        this.gameInfo = entity.getGameInfo();
        this.launchDate = entity.getLaunchDate();
        this.evaluation = entity.getEvaluation();
        this.imgUrl = entity.getImgUrl();
        this.videoUrl = entity.getVideoUrl();
        this.devCompany = entity.getDevCompany();
        this.distributor = entity.getDistributor();
        this.gameTags = entity.getGameTags().stream().limit(5L).map(GameTag::getTag).collect(Collectors.toList());
}
~~~

## 6. 그 외 트러블 슈팅
<details>
<summary>무한 참조 오류</summary>
<div markdown="1">

- Game Entity 에서 `List<GameTag>`를 가지고 있는데 GameTag에서 Game을 참조하고 Game에서
GameTag를 참조 함으로 무한 참조가 일어나면서 오류가 발생
- 중복된 아이디는 생성하지 않게 스프링 부트에서 지원하는 어노테이션을 사용하여 개선.
- `@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)`

</div>
</details>

<details>
<summary>application.properties 인식 문제</summary>
<div markdown="1">

- 쉘 스크립트를 통해서 travis로 배포된 jar파일에는 Database root 설정된 파일이 없기 때문에 따로 경로를 지정을 해줘야 했다. 
- `Dspring.config.location=classpath:/application.properties,/home/ec2-user/app/application.properties,/home/ec2-user/app/properties/application-oauth.properties`
</div>
</details>

<details>
<summary>무한 스크롤 동적 이벤트</summary>
<div markdown="1">

- Mustache는 논리적 데이터만 처리하는 템플릿으로 jsp처럼 조건을 걸 수 없는 페이지 였다.
- 제이쿼리를 사용하여 동적 이벤트를 추가.

</div>
</details>

### 서비스 페이지
![img_6.png](readmeimg/img_6.png)

![img_7.png](readmeimg/img_7.png)

![img_8.png](readmeimg/img_8.png)


