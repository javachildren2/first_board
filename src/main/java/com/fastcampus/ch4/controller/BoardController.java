package com.fastcampus.ch4.controller;

import com.fastcampus.ch4.domain.*;
import com.fastcampus.ch4.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.*;

import javax.servlet.http.*;
import java.time.*;
import java.util.*;



@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired BoardService boardService;
    @PostMapping("/modify")
    public String modify(BoardDto boardDto,HttpSession session,Model m,RedirectAttributes rattr) {
        String writer = (String) session.getAttribute("id");
        boardDto.setWriter(writer);
        try {
            int rowCnt = boardService.modify(boardDto);
            if (rowCnt != 1)
                throw new Exception("Modify failed");
            rattr.addFlashAttribute("msg", "MOD_OK");
            return "redirect:/board/list";       //글을 쓰면 최신글이니까 맨앞으로 와서 이렇게만 적어도 됨
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute(boardDto);       //글쓰기 실패시 입력했던 내용을 다시 돌려주기위함
            m.addAttribute("msg", "MOD_ERR");
            return "board";
        }
    }


        @PostMapping("/write")

        public String write(BoardDto boardDto,HttpSession session,Model m,RedirectAttributes rattr){
             String writer = (String)session.getAttribute("id");
             boardDto.setWriter(writer);
        try {
            int rowCnt =boardService.write(boardDto);
            if(rowCnt!=1)
                throw new Exception("Write failed");
            rattr.addFlashAttribute("msg","WRT_OK");
            return"redirect:/board/list";    //글을 쓰면 최신글이니까 맨앞으로 와서 이렇게만 적어도 됨
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute(boardDto);       //글쓰기 실패시 입력했던 내용을 다시 돌려주기위함
            m.addAttribute("msg","WRT_ERR");
            return "board";
        }

        }

         @GetMapping("/write")
             public String write(Model m){
             m.addAttribute("mode","new");    //읽기와 쓰기에 사용됨 ,mode가 new일때는 쓰기에 알맞게 세팅
                                                                    //new가 아니면 그냥 보여만 줌
            return "board";
        }
    @PostMapping("/remove")
    public String remove(Integer bno,Integer page,Integer pageSize,Model m, HttpSession session) {
        String writer = (String) session.getAttribute("id");


        try {
            boardService.remove(bno, writer);

        } catch (Exception e) {
            e.printStackTrace();

        }
        m.addAttribute("page", page);
        m.addAttribute("pageSize", pageSize);

        return "redirect:/board/list";
    }

    @GetMapping("/read")
    public String read(Integer bno,Integer page,Integer pageSize,Model m) {
        try {
            BoardDto boardDto=boardService.read(bno);
            m.addAttribute(boardDto);        //읽어온것을 board.jsp에 전달하기 위해 Model이 필요함
                                             //이름을 생략하면 타입의 첫글자를 소문자로 한것으로 자동설정
            m.addAttribute("page",page);
            m.addAttribute("pageSize",pageSize);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "board";
    }


    @GetMapping("/list")
    public String list(Model m, SearchCondition sc, HttpServletRequest request) {
        if(!loginCheck(request))
            return "redirect:/login/login?toURL="+request.getRequestURL();  // 로그인을 안했으면 로그인 화면으로 이동

        try {
            int totalCnt = boardService.getSearchResultCnt(sc);
            m.addAttribute("totalCnt", totalCnt);

            PageHandler pageHandler = new PageHandler(totalCnt, sc);

            List<BoardDto> list = boardService.getSearchResultPage(sc);
            m.addAttribute("list", list);
            m.addAttribute("ph", pageHandler);

            Instant startOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
            m.addAttribute("startOfToday", startOfToday.toEpochMilli());
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg", "LIST_ERR");
            m.addAttribute("totalCnt", 0);
        }

        return "boardList"; // 로그인을 한 상태이면, 게시판 화면으로 이동
    }

    private boolean loginCheck(HttpServletRequest request) {
        // 1. 세션을 얻어서
        HttpSession session = request.getSession();
        // 2. 세션에 id가 있는지 확인, 있으면 true를 반환
        return session.getAttribute("id")!=null;
    }
}