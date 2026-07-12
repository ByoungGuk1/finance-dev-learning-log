package com.shinhan.bananaapp.controller;


import com.shinhan.bananaapp.dto.EmpDTO;
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
        return "employee/emplist";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("empDTO", new EmpDTO());
        return "employee/insert";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable int id, Model model) {
        model.addAttribute("empDTO", empService.selectById(id));
        return "employee/update";
    }

    @GetMapping("/{id}")
    public String selectOne(@PathVariable int id, Model model) {
        EmpDTO emp = empService.selectById(id);
        model.addAttribute("empDTO", emp);
        return "employee/detail";
    }

    @PostMapping
    public String create(@ModelAttribute EmpDTO empDTO, RedirectAttributes attr) {
        Integer result = empService.createEmp(empDTO);
        if (result >= 1) {
            attr.addFlashAttribute("msg", "입력 되었습니다.");
        } else {
            attr.addFlashAttribute("msg", "신규 생성 실패");
        }
        return "redirect:/employee";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute EmpDTO empDTO, RedirectAttributes attr) {
        Integer result = empService.updateEmp(empDTO);
        if (result >= 1) {
            attr.addFlashAttribute("msg", "수정 되었습니다.");
        } else {
            attr.addFlashAttribute("msg", "수정 실패");
        }
        return "redirect:/employee";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes attr) {
        Integer result = empService.deleteEmp(id);
        if (result >= 1) {
            attr.addFlashAttribute("msg", "삭제 되었습니다.");
        } else {
            attr.addFlashAttribute("msg", "삭제 실패");
        }
        return "redirect:/employee";
    }
}

