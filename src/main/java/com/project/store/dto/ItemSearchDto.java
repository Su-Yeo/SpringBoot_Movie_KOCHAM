package com.project.store.dto;

import com.project.store.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemSearchDto {

    private String searchDateType; // 267p 현제 시간과 상품 등록일을 비교해서 상품 데이터를 조회
    // all 등록일 전체 1d 최근 하루 동안 등록된 상품 1w 일주일 1m 한달 6m 6개월

    private ItemSellStatus searchSellStatus; // 2. 판매 상태를 기준으로 상품 데이터 조회

    private String searchBy; // 3. 조회시 어떤 유형으로 조회할지 선택

    private String searchQuery = ""; // 조회할 검색어 저장할 변수, searchBy가 itemNm일 경우 상품명을 기준으로, createBy는 등록자 아이디기준.

}