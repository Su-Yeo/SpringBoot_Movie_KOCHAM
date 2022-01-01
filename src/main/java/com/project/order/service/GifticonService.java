package com.project.order.service;

import com.project.order.dto.GifticonDTO;
import com.project.order.repository.GifticonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GifticonService {
    @Autowired
    private GifticonRepository gifticonRepository;

    public List<GifticonDTO> getGifticonList(long member_id){
        List<GifticonDTO> gifticons = gifticonRepository.findAllByMemberId(member_id);
        return gifticons;
    }
}
