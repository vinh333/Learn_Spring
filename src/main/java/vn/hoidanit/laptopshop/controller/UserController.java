package vn.hoidanit.laptopshop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;
import vn.hoidanit.laptopshop.service.UploadService;
import vn.hoidanit.laptopshop.service.UserService;

@Controller
public class UserController {

    private final UserService userService;
    private final UploadService uploadService;

    public UserController(UserService userService, UploadService uploadService) {
        this.userService = userService;
        this.uploadService = uploadService;
    }

    // Home Page
    @RequestMapping("/")
    public String getHomePage(Model model) {
        List<User> arrUsers = this.userService.getAllUsersByEmail("admin@gmail.com");
        System.out.println(arrUsers);

        model.addAttribute("eric", "test");
        return "hello";
    }

    // Page list user
    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> users = this.userService.getAllUsers();
        // System.out.println(">>> Check list users:" + users);
        model.addAttribute("users1", users);
        return "admin/user/table-user";
    }

    // Page info user
    @RequestMapping("/admin/user/user-detail/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id) {

        // System.out.println(">>> Check id:" + id);
        // post id to view
        model.addAttribute("id", id);
        // post detail user by id -> view
        User users_detail = this.userService.getUsersByID(id);
        System.out.println(">>>>>>check users_detail:" + users_detail);
        model.addAttribute("users_detail", users_detail);

        return "admin/user/user-detail";
    }

    @GetMapping("/admin/user/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @RequestMapping(value = "/admin/user/create")
    public String createUserPage(Model model,
            @ModelAttribute("newUser") User hoidanit,
            @RequestParam("hoidanitFile") MultipartFile file) {
        String avatar = this.uploadService.handleSaveUploadFile(file, "avatar");
        // System.out.println("run here" + hoidanit);
        // this.userService.handleSaveUser(hoidanit);
        return "redirect:/admin/user";
    }

    // Page update user
    @RequestMapping("/admin/user/update-user/{id}")
    public String getUserUpdatePage(Model model, @PathVariable long id) {
        // post id to view
        model.addAttribute("id", id);
        // post detail user by id -> view
        User currentUser = this.userService.getUsersByID(id);
        model.addAttribute("users_detail", currentUser);
        return "admin/user/update-user";
    }

    @PostMapping(value = "/admin/user/update-user")
    public String postUpdateUser(Model model, @ModelAttribute("users_detail") User user) {
        User currentUser = this.userService.getUsersByID(user.getId());
        if (currentUser != null) {
            currentUser.setAddress(user.getAddress());
            currentUser.setFullName(user.getFullName());
            currentUser.setPhone(user.getPhone());
            this.userService.handleSaveUser(currentUser);
        }
        return "redirect:/admin/user";
    }

    // Page delete user
    @RequestMapping("/admin/user/delete-user/{id}")
    public String getUserDeletePage(Model model, @PathVariable long id) {
        // post id to view
        model.addAttribute("id", id);
        // post detail user by id -> view
        User currentUser = this.userService.getUsersByID(id);
        model.addAttribute("users_detail", currentUser);
        return "admin/user/delete-user";
    }

    @PostMapping(value = "/admin/user/delete-user")
    public String postDeleteUser(Model model, @ModelAttribute("users_detail") User user) {
        // User currentUser = this.userService.getUsersByID(user.getId());
        // this.userService.handleSaveUser(hoidanit);
        if (user != null) {
            // currentUser.setAddress(user.getAddress());
            // currentUser.setFullName(user.getFullName());
            // currentUser.setPhone(user.getPhone());
            this.userService.deleteUsersByID(user.getId());
        }
        System.out.println("check here" + user);
        return "redirect:/admin/user";
    }
}

// @RestController
// public class UserController {
// private UserService userService;

// public UserController(UserService userService) {
// this.userService = userService;
// }

// @GetMapping("/")
// public String getHomePage() {
// return this.userService.handleHello();
// }
// }
