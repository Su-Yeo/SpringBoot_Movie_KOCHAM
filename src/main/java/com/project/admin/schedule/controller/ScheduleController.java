
package com.project.admin.schedule.controller;


import com.project.admin.schedule.dto.ScheduleDTO;
import com.project.admin.schedule.entity.Schedule;
import com.project.admin.schedule.repository.ScheduleRepository;
import com.project.admin.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping(value="/adminSchedule")
public class ScheduleController {

    @Inject

    private ScheduleService service;
    @Autowired
    private ScheduleRepository scheduleRepository;



/* 상영정보 등록페이지 */

    //상영정보 등록 get
    @GetMapping(value = "/Register")
    public String getScheduleRegister() throws Exception {

        return "admin/Schedule/register";

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
    public String postScheduleRegister(ScheduleDTO scheduleDTO) throws Exception {
      /*  System.out.println(scheduleDTO.toString());
        Schedule schedule = scheduleDTO.toEntity();
        System.out.println(schedule.toString());

        Schedule saved = scheduleRepository.save(scheduleDTO.toEntity());


        System.out.println(saved.toString());
*/
        service.save(scheduleDTO);


        return "redirect:../adminSchedule/list";
    }

    //상영정보 게시판 보기
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getList(Model model) throws Exception {

        List<ScheduleDTO> list = service.findAll();
        System.out.println(list.toString()+"###################################");
        /*List<ScheduleDTO> list = scheduleRepository.findAll(scheduleDTO.toEntity());*/

        model.addAttribute("scheduleList", list);


        return "admin/Schedule/list";

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
    public String getMView(Model model, @RequestParam(name="schedule_num", required=false) Long schedule_num, RedirectAttributes rttr) throws Exception {

        ScheduleDTO list = service.getView(schedule_num);
        model.addAttribute("list", list);
        return "admin/Schedule/detailView";
    }

    //미니 게시판 내용 수정
    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String getMModify(Model model, @RequestParam(name="schedule_num", required=false) Long schedule_num) throws Exception {

        ScheduleDTO list = service.getView(schedule_num);

        model.addAttribute("list", list);
        return "admin/Schedule/modify";

    }

    //미니 게시판 내용 수정
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String postMModify(ScheduleDTO dto, @RequestParam(name="schedule_num", required=false) Long schedule_num) throws Exception {

        service.update(schedule_num, dto);

        return "redirect:/adminSchedule/detailView?schedule_num=" + Long.toString(schedule_num);

    }

    //미니 게시판 내용 삭제
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String postDelete(Model model, @RequestParam(name="schedule_num", required=false) Long schedule_num) throws Exception {

        service.delete(schedule_num);
        return "redirect:/adminSchedule/list";

    }

}
