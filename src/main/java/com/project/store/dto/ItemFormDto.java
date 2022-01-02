package com.project.store.dto;


import com.project.store.constant.ItemSellStatus;
import com.project.store.constant.ItemType;
import com.project.store.entity.Item;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ItemFormDto {

    private Long id;


    @NotBlank(message = "상품명은 필수 입력 값입니다.")
    private String itemNm;

    @NotNull(message = "가격은 필수 입력 값입니다.")
    private Integer price;

    @NotBlank(message = "상품 상세는 필수 입력 값입니다.")
    private String itemDetail;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer stockNumber;

    private ItemSellStatus itemSellStatus;

    private ItemType itemType;

    private List<ItemImgDto> itemImgDtoList = new ArrayList<>(); // 234p 1. 상품 저장 후 수정할 때 상품 이미지 정보를 저장하는 리스트 입니다.

    private List<Long> itemImgIds = new ArrayList<>(); // 2. 상품의 미미지 아이디를 저장하는 리스트입니다. 상품 등록 시에는 아직 상품의 이미지를 저장하지 않았기 때문에
    // 아무 값도 들어가 있지 않고 수정 시에 이미지 아이디를 담아둘 용도로 사용합니다.

    private static ModelMapper modelMapper = new ModelMapper();

    public Item createItem(){
        return modelMapper.map(this, Item.class); // 3. modelMapper를 이용하여 엔티티 객체와 DTO 객체 간의 데이터를 복사하여 복사한 객체를 반환해주는 메소드입니다.
    }

    public static ItemFormDto of(Item item){
        return modelMapper.map(item,ItemFormDto.class); // 4. modelMapper를 이용하여 엔티티 객체와 DTO 객체 간의 데이터를 복사하여 복사한 객체를 반환해주는 메소드입니다.
    }
}
