package com.project.reservation.movie.controller;

import com.project.admin.schedule.dto.ScheduleDTO;
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
@RequestMapping(value="/reservation")
public class ReservationMovieController {

    @Inject

    private ScheduleService service;
    @Autowired
    private ScheduleRepository scheduleRepository;


    @GetMapping(value = "/choiceMovie") //예매 페이지 진입
    public String reservationMovie( Model model){
        return "reservation/movieTicket";
    }
/*
    @PostMapping("/districts")
    public @ResponseBody List<String> doDistricts(BookingDTO dto,
                                                  @RequestParam(value = "cinema", required = false) String cinema,
                                                  @RequestParam(value = "movie_num", required = false) int movie_num, Model model) throws Exception {

        model.addAttribute("movieRead", s2.movieRead(movie_num));

        dto.setMovieNum(movie_num);
        dto.setInfoCity(cinema);

        List<String> districts = service.getDistricts(dto);

        // 무비넘 가져오고 dto에 담기2
        log.info("cinema: " + cinema);
        return districts;
    }// doDistricts

    @PostMapping("/dates")
    public @ResponseBody List<String> doDates(BookingDTO dto,
                                              @RequestParam(value = "cinema", required = false) String cinema,
                                              @RequestParam(value = "movie_num", required = false) int movie_num,
                                              @RequestParam(value = "dist", required = false) String dist, Model model) throws Exception {

        model.addAttribute("movieRead", s2.movieRead(movie_num));

        dto.setMovieNum(movie_num);
        dto.setInfoCity(cinema);
        dto.setInfoDist(dist);
        *//* dto.setInfoDist(dist); ajax로 변수 가져ㅗ기 *//*

        List<String> dates = service.getDates(dto);

        log.info(dates);

        return dates;

    }// doDates

    @PostMapping("/times")
    public @ResponseBody List<String> doTimes(BookingDTO dto,
                                              @RequestParam(value = "cinema", required = false) String cinema,
                                              @RequestParam(value = "movie_num", required = false) int movie_num,
                                              @RequestParam(value = "dist", required = false) String dist,
                                              @RequestParam(value = "dates", required = false) String dates, Model model) throws Exception {

        model.addAttribute("movieRead", s2.movieRead(movie_num));

        dto.setMovieNum(movie_num);
        dto.setInfoCity(cinema);
        dto.setInfoDist(dist);
        dto.setStartTime("\'" + dates + "\'");

        List<String> times = service.getTimes(dto);

        return times;
    }// doTimes

    @PostMapping("/seats")
    public @ResponseBody Object doSeats(BookingDTO dto, SeatVO2 vo2, SeatInfo info,
                                        @RequestParam(value = "cinema", required = false) String cinema,
                                        @RequestParam(value = "movie_num", required = false) int movie_num,
                                        @RequestParam(value = "dist", required = false) String dist,
                                        @RequestParam(value = "dates", required = false) String dates,
                                        @RequestParam(value = "times", required = false) String times, Model model) throws Exception {
        model.addAttribute("movieRead", s2.movieRead(movie_num));

        dto.setMovieNum(movie_num);
        dto.setInfoCity(cinema);
        dto.setInfoDist(dist);
        String startTime = "\'" + dates + " " + times + "\'";
        log.info("\t\tstartTime: " + startTime);
        dto.setStartTime(startTime);
        // 남아있는 좌석 show
        List<String> seats = service.getSeats(dto);
        // 남아있는 좌석의 수 show
        List<String> totalseats = service.getTotalSeats(dto);
        // --------------------------------------------------------------------//
        List<SeatInfo> list = new ArrayList<>();
        SeatInfo s = new SeatInfo();
        SeatInfo[] s1 = new SeatInfo[totalseats.size()];

        model.addAttribute("remainseats", service.getSeats(dto).size());
        model.addAttribute("totalseats", service.getTotalSeats(dto).size());

        char[] ch1;
        char ch = 'a';

        // 자리의 row 알파벳 개수를 카운트하기 위한 반복문
        Set<Character> rowSet = new HashSet<>();
        for (int i = 0; i < totalseats.size(); i++) {
            rowSet.add(totalseats.get(i).charAt(0));
        }
        int rowCount = rowSet.size();

        for (int i = 0; i < rowCount; i++) {

            s1[i] = new SeatInfo();
            for (int j = 0; j < totalseats.size(); j++) {
                ch1 = totalseats.get(j).toCharArray();
                if (ch1[0] == ch) {// 만약 첫글자가 ch와 같으면, 1)a 2)b됨..
                    String status = "Y";
                    for (int k = 0; k < seats.size(); k++) {
                        if (seats.get(k).equals(totalseats.get(j))) { // seats에 있는 자리와 같으면 status는 N
                            status = "N";
                            break;
                        }
                    }
                    s1[i].addSeatVO2(new SeatVO2(totalseats.get(j), status));
                } // if
                ch1 = null;// 초기화해줌
            } // for(j)
            list.add(s1[i]);//
            ch++;
        } // for(i)

        return list;
    }// doSeats

    @PostMapping("/showSeat")
    public @ResponseBody String doShowSeat(BookingDTO dto,
                                           @RequestParam(value = "cinema", required = false) String cinema,
                                           @RequestParam(value = "movie_num", required = false) int movie_num,
                                           @RequestParam(value = "dist", required = false) String dist,
                                           @RequestParam(value = "dates", required = false) String dates,
                                           @RequestParam(value = "times", required = false) String times, Model model) throws Exception {

        dto.setMovieNum(movie_num);
        dto.setInfoCity(cinema);
        dto.setInfoDist(dist);
        String startTime = "\'" + dates + " " + times + "\'";
        log.info("\t\t startTime: " + startTime);
        dto.setStartTime(startTime);

        String state = service.getSeats(dto).size() + "/" + service.getTotalSeats(dto).size();

        return state;
    }// doShowSeat

    @PostMapping("/reservation")
    public @ResponseBody String doReservation(ReservationVO vo, BookingDTO dto,
                                              @RequestParam(value = "cinema", required = false) String cinema,
                                              @RequestParam(value = "movie_num", required = false) int movie_num,
                                              @RequestParam(value = "dist", required = false) String dist,
                                              @RequestParam(value = "dates", required = false) String dates,
                                              @RequestParam(value = "times", required = false) String times,
                                              @RequestParam(value = "seatSum", required = false) String seatSum, // 프론트에서 받기(구분자로 비교해서 넣어주기)
                                              Model model, HttpSession session) throws Exception {

        dto.setMovieNum(movie_num);
        dto.setInfoCity(cinema);
        dto.setInfoDist(dist);
        String startTime = "\'" + dates + " " + times + "\'";
        dto.setStartTime(startTime);

        String scnum = service.getScreeningNum(dto);

        MemberVo memberInfo1 = (MemberVo) session.getAttribute(loginkey);

        String id = memberInfo1.getId();

        log.info("좌석선태개ㅐ" + seatSum);
        log.info("로그인 된 아이디ㅣㅣ" + id);

        vo.setMovie_num(movie_num);
        vo.setId(id);
        vo.setScreening_num(scnum);
        int year = Calendar.getInstance().get(Calendar.YEAR);

        String data[] = seatSum.split(",");
        int x = (int) (Math.random() * 3000);
        for (int i = 0; i < data.length; i++) {
            log.info("\t*********dtodto*****************\" : " + dto);
            vo.setReservation_num("A" + year + Integer.toString(i + 100) + x); // hash값? 중복 없는 random값? 넣기(생각)
            vo.setSeat_location(data[i]);
            service.InsertReservation(vo);
        }

        return "member/mypage";

    }// doReservation*/


}
