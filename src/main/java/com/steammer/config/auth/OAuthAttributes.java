package com.steammer.config.auth;

import com.steammer.domain.user.Role;
import com.steammer.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

// 사용자 정보를 담는 클래스 정의
@Getter
public class OAuthAttributes {
    private Map<String,Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    //사용자 속성들을 담아주는 빌더
    @Builder
    public OAuthAttributes(Map<String,Object> attributes, String nameAttributeKey, String name, String email, String picture){
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    //사용자 정보는 Map이기 때문에 변경해야함
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String,Object> attributes){

        //네이버 로그인 인지 판단, 네이버는 고유 키가 없기 때문에 추가 해줘야함.
        if("naver".equals(registrationId)){
            return ofNaver("id",attributes);
        }

        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {

        // 응답 받은 사용자의 정보를 Map형태로 변경.
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        // 미리 정의한 속성으로 빌드.
        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String,Object> attributes){
        // 미리 정의한 속성으로 빌드.
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    // UserRepository를 통해 저장해야 하기 때문에 형태 변환.
    public User toEntity(){
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.USER)
                .build();
    }
}
