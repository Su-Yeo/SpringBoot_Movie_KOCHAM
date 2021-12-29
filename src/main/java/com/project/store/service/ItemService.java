package com.project.store.service;

import com.project.store.dto.ItemFormDto;
import com.project.store.dto.ItemImgDto;
import com.project.store.dto.ItemSearchDto;
import com.project.store.dto.MainItemDto;
import com.project.store.entity.Item;
import com.project.store.entity.ItemImg;
import com.project.store.repository.ItemImgRepository;
import com.project.store.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    private final ItemImgService itemImgService;

    private final ItemImgRepository itemImgRepository;

    public Long saveItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception{

        //상품 등록
        Item item = itemFormDto.createItem();  // 248p 1. 상품 등록 폼으로부터 입력 받은 데이터를 이용하여 item 객체를 생성합니다.
        itemRepository.save(item); // 2. 상품데이터를 저장합니다.

        //이미지 등록
        for(int i=0;i<itemImgFileList.size();i++){
            ItemImg itemImg = new ItemImg();
            itemImg.setItem(item);

            if(i == 0) // 3. 첫번째 이미지일 경우 대표 상품 이미지 여부 값을 "Y"로 세팅합니다. 나머지 상품 이미지는 "N"로 설정합니다.
                itemImg.setRepimgYn("Y");
            else
                itemImg.setRepimgYn("N");

            itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));  // 4. 상품의 이미지 정보를 저장
        }

        return item.getId();
    }
    //등록된 상품을 불러오는 메소드
    @Transactional(readOnly = true) //257p 1. 상품 데이터를 읽어오는 트랜잭션을 읽기 전용을 설정한다. 이경우 JPA가 더티체킹(변경감지)을 하지 않아서 성능향상을 꽤할수 있다.
    public ItemFormDto getItemDtl(Long itemId){

        List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);
        // 2. 해당 상품의 이미지를 조회한다. 등록순으로 가져오기 위해 상품 이미지 아이디 오름차순으로 가지고 온다.
        List<ItemImgDto> itemImgDtoList = new ArrayList<>();
        for (ItemImg itemImg : itemImgList) { // 3. 조회한 ItemImgDto 객체로 만들어서 리스트에 추가
            ItemImgDto itemImgDto = ItemImgDto.of(itemImg);
            itemImgDtoList.add(itemImgDto);
        }

        Item item = itemRepository.findById(itemId) // 4. 상품의 아이디를 통해 상품 엔티티를 조회, 존재하지 안을 때는
                .orElseThrow(EntityNotFoundException::new); // EntityNotFoundException을 발생시킨다.
        ItemFormDto itemFormDto = ItemFormDto.of(item);
        itemFormDto.setItemImgDtoList(itemImgDtoList);
        return itemFormDto;
    }

    public Long updateItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception{

        //상품 수정
        Item item = itemRepository.findById(itemFormDto.getId()).orElseThrow(EntityNotFoundException::new);
        // 262p 1. 상품 등록 화면으로부터 전달 받은 상품 아이디를 이용하여 상품 인티티를 조회
        item.updateItem(itemFormDto); // 2. 상품 등록화면으로부터 전달 받은 ItemFormDto를 통해 상품 엔티티를 업데이트

        List<Long> itemImgIds = itemFormDto.getItemImgIds(); // 3. 상품 이미지 아이디 리스트를 조회

        //이미지 등록
        for(int i=0;i<itemImgFileList.size();i++){
            itemImgService.updateItemImg(itemImgIds.get(i), itemImgFileList.get(i));
            // 4. 상품 이미지를 업데이트 하기위해서 updateItemImg() 메소드에 상품 이미지 아이디와, 상품 이미지 파일 정보를 파라미터로 전달.
        }
        return item.getId();
    }

    @Transactional(readOnly = true)
    public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable){
        // 상품 조회 조건과 페이지 정보를 파라미터로 받아서 상품 데이터를 조회하는 메소드
        return itemRepository.getAdminItemPage(itemSearchDto, pageable);
    }
    @Transactional(readOnly = true) // 메인페이지에 보여줄 상품 데이터를 조회하는 메소드
    public Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable){
        return itemRepository.getMainItemPage(itemSearchDto, pageable);
    }

}
