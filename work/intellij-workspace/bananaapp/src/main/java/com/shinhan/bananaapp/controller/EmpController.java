package com.shinhan.bananaapp.controller;


import com.shinhan.bananaapp.di2.EmpDTO;
import com.shinhan.bananaapp.service.EmpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller("empHR")
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmpController {

    private final EmpService empService;

    @GetMapping
    public String selectAll(Model model) {
        model.addAttribute("empList", empService.selectAllService());
        return "employee/list";
    }

    @GetMapping("/{id}")
    public String selectOne(@PathVariable int id, Model model) {
        EmpDTO emp = empService.selectById(id);
        model.addAttribute("empDTO", emp);
        return "employee/detail";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("empDTO", new EmpDTO());
        return "employee/insert";
    }

    @PostMapping
    public String create(@ModelAttribute EmpDTO empDTO, RedirectAttributes attr) {
        boolean result = empService.createEmp(empDTO);
        if (result) {
            attr.addFlashAttribute("msg", "입력 되었습니다.");
        } else {
            attr.addFlashAttribute("msg", "신규 생성 실패");
        }
        return "redirect:/employee";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable int id, Model model) {
        model.addAttribute("empDTO", empService.selectById(id));
        return "employee/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute EmpDTO empDTO, RedirectAttributes attr) {
        boolean result = empService.updateEmp(empDTO);
        if (result) {
            attr.addFlashAttribute("msg", "수정 되었습니다.");
        } else {
            attr.addFlashAttribute("msg", "수정 실패");
        }
        return "redirect:/employee";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes attr) {
        boolean result = empService.deleteEmp(id);
        if (result) {
            attr.addFlashAttribute("msg", "삭제 되었습니다.");
        } else {
            attr.addFlashAttribute("msg", "삭제 실패");
        }
        return "redirect:/employee";
    }
}

