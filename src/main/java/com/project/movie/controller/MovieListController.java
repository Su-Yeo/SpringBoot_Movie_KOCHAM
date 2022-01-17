package com.project.movie.controller;


import com.project.movie.dto.MovieDTO;
import com.project.movie.repository.MovieRepository;
import com.project.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping(value="/admin/movie")
public class MovieListController {
    @Inject

    private MovieService service;
    @Autowired
    private MovieRepository scheduleRepository;



    /* 상영정보 등록페이지 */

    //상영정보 등록 get
    @GetMapping(value = "/Register")
    public String getScheduleRegister() throws Exception {

        return "admin/Movie/register";

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
    public String postScheduleRegister(MovieDTO dto) throws Exception {
      /*  System.out.println(scheduleDTO.toString());
        Schedule schedule = scheduleDTO.toEntity();
        System.out.println(schedule.toString());

        Schedule saved = scheduleRepository.save(scheduleDTO.toEntity());


        System.out.println(saved.toString());
*/
        service.save(dto);


        return "redirect:../movie/list";
    }

    //상영정보 게시판 보기
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getList(Model model) throws Exception {

        List<MovieDTO> list = service.findAll();
        System.out.println(list.toString()+"###################################");
        /*List<ScheduleDTO> list = scheduleRepository.findAll(scheduleDTO.toEntity());*/

        model.addAttribute("MovieList", list);


        return "admin/Movie/list";

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
    public String getMView(Model model, @RequestParam(name="movie_num", required=false) Long movie_num, RedirectAttributes rttr) throws Exception {

        MovieDTO list = service.getView(movie_num);
        model.addAttribute("list", list);
        return "admin/movie/detailView";
    }

    //미니 게시판 내용 수정
    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String getMModify(Model model, @RequestParam(name="movie_num", required=false) Long movie_num) throws Exception {

        MovieDTO list = service.getView(movie_num);

        model.addAttribute("list", list);
        return "admin/movie/modify";

    }

    //미니 게시판 내용 수정
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String postMModify(MovieDTO dto, @RequestParam(name="movie_num", required=false) Long movie_num) throws Exception {

        service.update(movie_num, dto);

        return "redirect:/admin/movie/detailView?movie_num=" + Long.toString(movie_num);

    }

    //미니 게시판 내용 삭제
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String postMDelete(Model model, @RequestParam(name="movie_num", required=false) Long movie_num) throws Exception {

        service.delete(movie_num);
        return "redirect:/admin/movie/list";

    }

}