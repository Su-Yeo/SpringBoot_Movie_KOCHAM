package com.project.order.service;

import com.project.order.dto.GifticonDTO;
import com.project.order.dto.OrderDTO;
import com.project.order.repository.GifticonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
public class GifticonService {
    @Autowired
    private GifticonRepository gifticonRepository;

    public GifticonDTO saveGifticon(OrderDTO orderPay, int price){
        GifticonDTO gifticon = new GifticonDTO(orderPay.getMemberId(),
                orderPay.getProduct_num(), orderPay.getProduct_name(), orderPay.getOrder_to_id(),
                LocalDateTime.now().plusYears(1), LocalDateTime.now(),price);
        return gifticonRepository.save(gifticon);
    }

    public void deleteGifticon(Long giftNum){
        gifticonRepository.deleteById(giftNum);
    }

    public List<GifticonDTO> getGifticonList(Long member_id){
        List<GifticonDTO> gifticons = gifticonRepository.findAllByMemberId(member_id);
        return gifticons;
    }

    public Page<GifticonDTO> getGifticonListPage(Long member_id, Pageable pageable){
        Page<GifticonDTO> gifticons = gifticonRepository.findByMemberId(member_id,pageable);
        return gifticons;
    }
}
