package com.sjy.controller;

import com.sjy.dao.PersonDao;

import com.sjy.entity.Person;
import com.sjy.entity.User;
import com.sjy.service.PersonService;
import com.sjy.util.EmailType;
import com.sjy.util.EmailUtils;
import com.sjy.util.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


/**
 * @Author 傻傻的远
 * @Date 2021 11 05 22:16
 * @Description: 项目名称 test 路径 com.sjy.controller
 * @Function:
 */
@Controller
public class EmailController {
    @Autowired
    PersonService personService;
//    public static  Long EmailSum=5L;
    @GetMapping("/tologin")
    public String tologin(Model model)
    {
        model.addAttribute("person",new Person());
        return "login";
    }

    @PostMapping("/login")
    public String login(Person person, Model model) throws Exception {  // redirect最好用这个参数 model.addAttribute也可以实现
        Person user = personService.getPersonByName(person.getEmail());
        if (user == null) {
            String s= RandomUtils.getRandomString(8);
            person.setCode(s);
//            person.setId(EmailSum++);
            personService.addUser(person);
            person.setId(personService.GetIdByEmail(person.getEmail()));
//            System.out.println(person);
            model.addAttribute("person",person);
            User user1 = new User();
            EmailUtils.createMail(person.getName(),s,person.getEmail(), EmailType.GUANZHU,null);
//            attributes.addAttribute("person",person);
            return "login";
        } else {
            if(user.getStatus()==1) {
                model.addAttribute("message1", "你已经关注过了呢");
                return "about";
            } else {
                model.addAttribute("person",user);
                model.addAttribute("message", "请去邮箱看看你的验证码呢");
                return "login";
            }
        }
    }

    @GetMapping("/queren/{email}")
    public String querenByemail(@PathVariable String email){
        personService.changeState(email);
        return "about";
    }


    @PostMapping("/login/{id}")
    public String queren(@PathVariable Long id,@RequestParam String code ,Person person, Model model)
    {
        String yanzhengma=personService.getCode(id);
        model.addAttribute("person",person);
        if(yanzhengma==null)
        {
            model.addAttribute("message", "你咋不输验证码");
            return "login";
        }
        else if(!yanzhengma.equals(code))
        {
            model.addAttribute("message", "验证码错了呢");
            return "login";  //model return "/admin/login""
        }
        else
        {
            model.addAttribute("message1", "感谢你的关注");
            personService.changeState(id);
            return "about";
        }
    }
//    @PostMapping("/queren")
//    public String queren( @RequestParam String code, @RequestParam String email , Model model)
//    {
//        System.out.println(email+"    "+ "name"+"       "+code);
//
//        String yanzhengma=personDao.getCode(email);
//        System.out.println();
//        if(yanzhengma==null)
//        {
//            model.addAttribute("message", "你咋不输验证码");
//            return "queren";  //model return "/admin/login""
//        }
//        else if(!yanzhengma.equals(code))
//        {
//            model.addAttribute("message", "数错了呢");
//            model.addAttribute("person",personDao.getPersonById(email));
//            return "queren";  //model return "/admin/login""
//        }
//        else
//        {
//            model.addAttribute("message1", "感谢你的关注");
//            personDao.changeState(email);
//            return "index";
//        }
//    }

    @GetMapping("/toquxiao")
    public  String toquxiao()
    {
        return "quxiao";
    }

    @PostMapping("/quxiao")
    public  String quxiao(@RequestParam String email,RedirectAttributes attributes) throws Exception {
        Person person=personService.getPersonByName(email);
        if(person==null||person.getStatus()==0)
        {
            attributes.addFlashAttribute("message","你还没关注呢");
            return "redirect:/toquxiao";
        }
        else
        {
            EmailUtils.createMail(person.getName(),"",email,EmailType.CANCELGUANZHU,null);
            personService.Byebye(email);
            attributes.addFlashAttribute("message","感谢你曾经关注过我");
            return  "redirect:/about";
        }
    }
//    @PostMapping("/quxiao")
//    public  String quxiao(@RequestParam String email,@RequestParam String name,Model model) throws Exception {
//        Person person=personDao.getPersonById(email);
//        if(person==null)
//        {
//            model.addAttribute("message","你还没关注呢");
//            return "quxiao";
//        }
//        else
//        {
//            EmailUtils.createMail(name,"",email,false);
//            personDao.Byebye(email);
//            model.addAttribute("message","感谢你曾经关注过我");
//            return  "index";
//        }
//    }

}
