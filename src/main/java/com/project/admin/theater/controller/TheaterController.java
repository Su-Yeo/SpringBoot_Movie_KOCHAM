
package com.project.admin.theater.controller;



import com.project.admin.theater.dto.TheaterDTO;
import com.project.admin.theater.repository.TheaterRepository;
import com.project.admin.theater.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping(value="/adminTheater")
public class TheaterController {

    @Inject

    private TheaterService service;
    @Autowired
    private TheaterRepository theaterRepository;



    /* 상영정보 등록페이지 */

    //상영정보 등록 get
    @GetMapping(value = "/Register")
    public String getTheaterRegister() throws Exception {

        return "admin/Theater/register";

    }

    /*//상영정보 등록 post
    @PostMapping(value ="/Register")
    public String postScheduleRegister(ScheduleDTO scheduleDTO, @RequestParam(name="uploadFile", required=false) MultipartFile multipartFile) throws Exception {

        String filePath = "d:\\Repository\\file\\";
        File targetFile = new File(filePath);
        if(!multipartFile.isEmpty()) {

            String originalFileName = multipartFile.getOriginalFilename();
            Long filesize = multipartFile.getSize();

            scheduleDTO.setFile_name(originalFileName);
            scheduleDTO.setFile_size(filesize);

            targetFile = new File(filePath + originalFileName);
            multipartFile.transferTo(targetFile);
        }

        service.save(scheduleDTO);
        return "redirect:admin/Schedule/list";
    }*/
    @PostMapping(value ="/Register")
    public String postScheduleRegister(TheaterDTO theaterDTO) throws Exception {
      /*  System.out.println(scheduleDTO.toString());
        Schedule schedule = scheduleDTO.toEntity();
        System.out.println(schedule.toString());

        Schedule saved = scheduleRepository.save(scheduleDTO.toEntity());


        System.out.println(saved.toString());
*/
        service.save(theaterDTO);


        return "redirect:../adminTheater/list";
    }

    //상영정보 게시판 보기
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getList(Model model) throws Exception {

        List<TheaterDTO> list = service.findAll();
        System.out.println(list.toString()+"###################################");
        /*List<ScheduleDTO> list = scheduleRepository.findAll(scheduleDTO.toEntity());*/

        model.addAttribute("theaterList", list);


        return "admin/Theater/list";

    }
    /*//상영정보 게시판 보기
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String postList(Model model) throws Exception {

        List<ScheduleDTO> list = service.findAll();
        System.out.println(list.toString()+"###################################");
        *//*List<ScheduleDTO> list = scheduleRepository.findAll(scheduleDTO.toEntity());*//*

        model.addAttribute("scheduleList", list);


        return "admin/Schedule/list";

    }*/

    //상영정보 게시판 내용 보기
    @RequestMapping(value = "/detailView", method = RequestMethod.GET)
    public String getMView(Model model, @RequestParam(name="theater_area_num", required=false) Long theater_area_num, RedirectAttributes rttr) throws Exception {

        TheaterDTO list = service.getView(theater_area_num);
        model.addAttribute("list", list);
        return "admin/Theater/detailView";
    }

    //미니 게시판 내용 수정
    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String getMModify(Model model, @RequestParam(name="theater_area_num", required=false) Long theater_area_num) throws Exception {

        TheaterDTO list = service.getView(theater_area_num);

        model.addAttribute("list", list);
        return "admin/Theater/modify";

    }

    //미니 게시판 내용 수정
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String postMModify(TheaterDTO dto, @RequestParam(name="theater_area_num", required=false) Long theater_area_num) throws Exception {

        service.update(theater_area_num, dto);

        return "redirect:/adminTheater/detailView?theater_area_num=" + Long.toString(theater_area_num);

    }

    //미니 게시판 내용 삭제
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String postDelete(Model model, @RequestParam(name="theater_area_num", required=false) Long theater_area_num) throws Exception {

        service.delete(theater_area_num);
        return "redirect:/adminTheater/list";

    }

}
