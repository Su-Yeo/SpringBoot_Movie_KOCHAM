package com.project.order.controller;

import com.project.order.dto.GifticonDTO;
import com.project.order.dto.OrderDTO;
import com.project.order.repository.GifticonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
public class GifticonAPIController {
    @Autowired
    private GifticonRepository gifticonRepository;

    @PostMapping(value="/gifticon") //기프티콘 리스트 more Ajax
    public ResponseEntity<?> more(@PageableDefault(size=2, sort = "giftNum", direction = Sort.Direction.DESC) Pageable pageable){
        Page<GifticonDTO> gifticons = gifticonRepository.findAll(pageable);
        int startPage = Math.max(1,gifticons.getPageable().getPageNumber() - 2);
        int endPage = Math.min(gifticons.getTotalPages(),gifticons.getPageable().getPageNumber() + 2);
        return ResponseEntity.ok(gifticons);

    }
}
