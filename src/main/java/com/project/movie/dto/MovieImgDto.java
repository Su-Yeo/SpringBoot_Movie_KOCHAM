package com.project.movie.dto;

import com.project.store.entity.ItemImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class MovieImgDto {

    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private String repImgYn;

    private static ModelMapper modelMapper = new ModelMapper(); // 232p 1. 맴버 변수로 ModelMapper 객체를 추가

    public static MovieImgDto of(ItemImg itemImg) {
        return modelMapper.map(itemImg, MovieImgDto.class); // 2. ItemImg 엔티티 객체를 파라미터로 받아서 ItemIm 객체의 자료형과
        //멤버변수의 이름이 같을때 ItemImgDto로 값을 복사해서 반환합니다. static 메소드로 선헌해 ItemImgDto 객체를 생성하지 않아도
        //호출할 수 있도록 하겠습니다.
    }

}