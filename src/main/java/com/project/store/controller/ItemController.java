package com.project.store.controller;

import com.project.store.dto.ItemFormDto;
import com.project.store.dto.ItemSearchDto;
import com.project.store.entity.Item;
import com.project.store.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping(value ="/admin/item/new")
    public String productForm(Model model){
        model.addAttribute("itemFormDto", new ItemFormDto());
        return "admin/adminItemNew";
    }

    @PostMapping(value = "/admin/item/new")
    public String itemNew(@Valid ItemFormDto itemFormDto, BindingResult bindingResult, Model model, @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList){

        if(bindingResult.hasErrors()){ // 250p 1.  상품 등록 시 필수 값이 없다면 다시 상품 들록 페이지로 전환합니다.
            return "admin/adminItemNew";
        }
        if(itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == null){ // 2. 상품 들록시ㅣ 첫 번째 이미지가 없다면
            // 에러 메시지와 함께 상품 등록 페이지로 전환 합니다. 상품의 첫번째 이미지는 메인 페이지에서 버여줄 상품 이미지로 사용하기 위해서
            // 필수 값으로 지정함
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
            return "admin/adminItemNew";
        }

        try{
            itemService.saveItem(itemFormDto, itemImgFileList); // 3. 상품 저장 로직을 호출, 매개 변수로 상품 정보와 상품 이미지 정보를 담고 있는
            // itemImgFileList를 넘겨줍니다,
        } catch (Exception e){
            model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
            return "admin/adminItemNew";
        }

        return "redirect:/admin/item/new"; // 4. 상품 등록이 정삭적으로 작동 되었다면 메인 페이지로 이동합니다.


    }

    @GetMapping(value = "/admin/item/{itemId}")
    public String itemDtl(@PathVariable("itemId") Long itemId, Model model){

        try {
            ItemFormDto itemFormDto = itemService.getItemDtl(itemId); // 258p 1. 조회한 상품 데이터를 모델에 담아서 뷰로 전달
            model.addAttribute("itemFormDto", itemFormDto);
        } catch(EntityNotFoundException e){ // 2. 상품 엔티티가 존재하지 않을 경우 에러 메시지를 담아서 상품 등록 페이지로 이동.
            model.addAttribute("errorMessage", "존재하지 않는 상품 입니다.");
            model.addAttribute("itemFormDto", new ItemFormDto());
            return "admin/adminItemNew";
        }

        return "admin/adminItemNew";
    }

    @PostMapping(value = "/admin/item/{itemId}")
    public String itemUpdate(@Valid ItemFormDto itemFormDto, BindingResult bindingResult,
                             @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList, Model model){
        if(bindingResult.hasErrors()){
            return "admin/adminItemNew";
        }

        if(itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == null){
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
            return "admin/adminItemNew";
        }

        try {
            itemService.updateItem(itemFormDto, itemImgFileList); // 263p 1. 상품 수정 로직을 호출 합니다.
        } catch (Exception e){
            model.addAttribute("errorMessage", "상품 수정 중 에러가 발생하였습니다.");
            return "admin/adminItemNew";
        }

        return "redirect:/admin/items";
    }
    @GetMapping(value = {"/admin/items", "/admin/items/{page}"}) //272p 1. value에 상품관리 화면 진입시 URL에 페이지 번호가 없는 경우와 페이지 번호가 있는 경우 2가지를 매핑
    public String itemManage(ItemSearchDto itemSearchDto, @PathVariable("page") Optional<Integer> page, Model model){

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 3);
        // 2. 페이징을 위해서 PageRequest.of 메소드를 통해 Pageable 객체를 생성.
        // 첫 번째 파라미터로는 조회할 페이지 번호, 두 번째는 한 번에 가지고 올 데이터 수를 넣어준다.
        // URL 경로에 페이지 번호가 있으면 해당 페이지를 조회하도록 세팅하고, 페이지 번호가 없으면 0페이지를 조회.
        Page<Item> items = itemService.getAdminItemPage(itemSearchDto, pageable);
        // 3. 조회 조건과 페이징 정보를 파라미터로 넘겨서 Page<Item>객체를 반환 받습니다.

        model.addAttribute("items", items);
        // 4. 조회한 상품 데이터 및 페이징 정보를 뷰에 전달
        model.addAttribute("itemSearchDto", itemSearchDto);
        // 5. 페이지 전환 시 기존 검색 조건을 유지한 채 이동할 수 있도록 뷰에 다시 전달.
        model.addAttribute("maxPage", 5);
        // 6. 상풍 관리 메뉴 하단에 보여줄 페이지 번호의 최대 개수. 5로 설정되어있음.

        return "admin/adminItemMng";
    }
    @GetMapping(value = "/item/{itemId}")// 상품 상세페이지로 이동
    public String itemDtl(Model model, @PathVariable("itemId") Long itemId){
        ItemFormDto itemFormDto = itemService.getItemDtl(itemId);
        model.addAttribute("item", itemFormDto);
        return "store/itemDtl";
    }
}
