package Healthlog.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller			// 해당 클래스가 컨트롤러임을 알리고 bean으로 등록하기 위함
@RequiredArgsConstructor	// 나중에 의존관계 관련하여 필요한 어노테이션
public class UserController {

    @GetMapping("/")
    public String Home() {
        return "home";
    }
    }