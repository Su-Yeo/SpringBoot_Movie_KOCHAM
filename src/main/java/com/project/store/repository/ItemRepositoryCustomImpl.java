package com.project.store.repository;

import com.project.store.constant.ItemSellStatus;
import com.project.store.dto.ItemSearchDto;
import com.project.store.dto.MainItemDto;
import com.project.store.dto.QMainItemDto;
import com.project.store.entity.Item;
import com.project.store.entity.QItem;
import com.project.store.entity.QItemImg;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

public class ItemRepositoryCustomImpl implements ItemRepositoryCustom{ // 268p 1. ItemRepositoryCustom 상속

    private JPAQueryFactory queryFactory; // 2. 동적으로 쿼리를 생성하기 위해서 JPAQueryFactory 클래스 사용

    public ItemRepositoryCustomImpl(EntityManager em){ // 3. JPAQueryFactory의 생성자로 EntityManager를 넣음
        this.queryFactory = new JPAQueryFactory(em);
    }

    private BooleanExpression searchSellStatusEq(ItemSellStatus searchSellStatus){ // 4. 판매상태 조건이 전체(null)일 경우는 null을 리턴.
        // 결과값이 null이면 whewe절에서 해당 조건은 무시됩니다. 상품
        // 판매 샅애 조건이 null이 아니라 판매중 or 품절 상태라면 해당 조건의 상품만 조회
        return searchSellStatus ==
                null ? null : QItem.item.itemSellStatus.eq(searchSellStatus);
    }

    private BooleanExpression regDtsAfter(String searchDateType){ // 5. searchDateType의 값에 따라서 dateTime의 값을 이전 시간의 값으로 세팅 후 해당 시간 이후로 등록된 상품만 조회.
        // 예) searchDateType 값이  "1m" 인 경우 dateTime의 시간을 한 달 전으로 세팅 후 최근 한 달 동안 등록된 상품만 조회하도록 조건값을 반환.

        LocalDateTime dateTime = LocalDateTime.now();

        if(StringUtils.equals("all", searchDateType) || searchDateType == null){
            return null;
        } else if(StringUtils.equals("1d", searchDateType)){
            dateTime = dateTime.minusDays(1);
        } else if(StringUtils.equals("1w", searchDateType)){
            dateTime = dateTime.minusWeeks(1);
        } else if(StringUtils.equals("1m", searchDateType)){
            dateTime = dateTime.minusMonths(1);
        } else if(StringUtils.equals("6m", searchDateType)){
            dateTime = dateTime.minusMonths(6);
        }

        return QItem.item.regTime.after(dateTime);
    }

    private BooleanExpression searchByLike(String searchBy, String searchQuery){
        // 6. searchBy의 값에 따라서 상품명에 검색어를 포함하고 있는 상품또는 상품 생성자의
        // 아이디에 검색어를 포함하고 있는 상품을 조회하도록 조건값을 반환합니다.
        if(StringUtils.equals("itemNm", searchBy)){
            return QItem.item.itemNm.like("%" + searchQuery + "%");
        } else if(StringUtils.equals("createdBy", searchBy)){
            return QItem.item.createdBy.like("%" + searchQuery + "%");
        }

        return null;
    }

    @Override
    public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {

        QueryResults<Item> results = queryFactory  // 270p. 7.  queryFactory를 이용해서 쿼리를 생성
                .selectFrom(QItem.item)
                .where(regDtsAfter(itemSearchDto.getSearchDateType()),
                        searchSellStatusEq(itemSearchDto.getSearchSellStatus()),
                        searchByLike(itemSearchDto.getSearchBy(),
                                itemSearchDto.getSearchQuery()))
                .orderBy(QItem.item.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Item> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total); // 8. 조회한 데이터를 Page 클래스의 구현체인 PageImpl객체로 반환.
    }

    private BooleanExpression itemNmLike(String searchQuery){// 283p 1. 검색어가 null이 아니면 상품명에 해당 검색어가 포하되는 상품을 조회하는 조건을 반환
        return StringUtils.isEmpty(searchQuery) ? null : QItem.item.itemNm.like("%" + searchQuery + "%");
    }

    @Override
    public Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
        QItem item = QItem.item;
        QItemImg itemImg = QItemImg.itemImg;

        QueryResults<MainItemDto> results = queryFactory
                .select(
                        new QMainItemDto( // 2. QMainItemDto의 생성자에 반환할 값들을 넣어줍니다. @QueryProjection을 사용하면 DTO로 바로 조회가 가능. 엔티티 조회후 DTO로 변환하는 과정을 줄일수 있다.
                                item.id,
                                item.itemNm,
                                item.itemDetail,
                                itemImg.imgUrl,
                                item.price)
                )
                .from(itemImg)
                .join(itemImg.item, item) // 3. itemImg 와 item을 내부 조인한다.
                .where(itemImg.repimgYn.eq("Y")) // 4. 상품 ㅇㅁ지의 경우 대표 상품 이미지만 불러온다.
                .where(itemNmLike(itemSearchDto.getSearchQuery()))
                .orderBy(item.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<MainItemDto> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }

}