package io.k2c1.hereyougo.controller;

import io.k2c1.hereyougo.constant.SessionConst;
import io.k2c1.hereyougo.domain.Address;
import io.k2c1.hereyougo.domain.Member;
import io.k2c1.hereyougo.domain.Post;
import io.k2c1.hereyougo.domain.Image;
import io.k2c1.hereyougo.dto.PostSaveForm;
import io.k2c1.hereyougo.config.FileUploader;
import io.k2c1.hereyougo.repository.MemberRepository;
import io.k2c1.hereyougo.repository.PostRepository;
import io.k2c1.hereyougo.service.PostService;
import io.k2c1.hereyougo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/posts")
public class PostController
{
    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private final PostRepository postRepository;
    private final CategoryService categoryService;
    private final FileUploader fileUploader;

    @GetMapping("/{postId}")
    public String getPost(
            @PathVariable("postId") Long postId,
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
            Model model)
    {
        if (loginMember != null) model.addAttribute("member", loginMember);

        Post getPost = postRepository.findById(postId).get();
        log.info("Getting Post - ID: {}, TITLE : {}", getPost.getId(), getPost.getTitle());
        model.addAttribute("post", getPost);
        getPost.plusViews();
        return "posts/post";
    }

    @GetMapping("/add")
    public String addForm(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
            Model model)
    {
        if (loginMember != null) model.addAttribute("member", loginMember);
        model.addAttribute("form", new PostSaveForm());
        model.addAttribute("secondCategories", categoryService.getAllChildCategories());
        return "posts/addPost";
    }

    @PostMapping("/add")
    public String addPost(
            @Validated @ModelAttribute("form") PostSaveForm form,
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) throws IOException {

        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "posts/addPost";
        }

        Address address = Address.builder()
                .sido(form.getSiNm())
                .sgg(form.getSggNm())
                .doro(form.getRoadFullAddr())
                .jibun(form.getJibunAddr())
                .zipNo(form.getZipNo())
                .build();

        Post post = Post.builder()
                .writer(loginMember)
                .title(form.getTitle())
                .content(form.getContent())
                .views(0)
                .quantity(form.getQuantity())
                .address(address)
                .category(categoryService.getCategory(form.getCategoryId()))
                .build();

        List<MultipartFile> images = form.getImages();
        List<Image> uploadFiles = fileUploader.uploadFiles(images, post);
        for (Image uploadFile : uploadFiles) {
            log.info("{} : ", uploadFile);
        }
        post.setImages(uploadFiles);

        Post savedPost = postRepository.save(post);
        log.info("id : {}", savedPost.getId());
        redirectAttributes.addAttribute("postId", savedPost.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/posts/{postId}";
    }

    @GetMapping("/{postId}/edit")
    public String editForm(
            @PathVariable Long postId,
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
            Model model)
    {
        if (loginMember != null) model.addAttribute("member", loginMember);

        Post post = postRepository.findById(postId).get();
        model.addAttribute("post", post);
        return "posts/editPost";
    }

    @PostMapping("/{postId}/edit")
    public String editPost(@PathVariable Long postId, @ModelAttribute Post post)
    {
        return "redirect:/posts/{postId}";
    }

    @PostMapping("/{postId}/delete")
    public String deletePost(@PathVariable Long postId)
    {
        postRepository.deleteById(postId);
        return "redirect:/";
    }

    @GetMapping("/filtered")
    public String getFilteredPosts(
            @RequestParam("sido") String sido,
            @RequestParam("sgg") String sgg,
            @RequestParam("categoryId") Long categoryId,
            Model model
    ) {
        log.info("categoryId = {}", categoryId);
        List<Post> posts = postRepository.findAll().stream()
                .filter(post -> {
                    if(sido.equals("시/도 전체")) return true;
                    else return post.getAddress().getSido().equals(sido);
                })
                .filter(post -> {
                    if(sgg.equals("0")) return true;
                    else return post.getAddress().getSgg().equals(sgg);
                })
                .filter(post -> {
                    if(categoryId == 0L) return true;
                    else {
                        return categoryService.getParentAndChildCategories(categoryId)
                                .contains(post.getCategory());
                    }
                })
                .sorted(Comparator.comparing((Post p) -> p.getId()).reversed())
                .collect(Collectors.toList());

        model.addAttribute("posts", posts);

        return "fragments/filtered";
    }

    @GetMapping("/list")
    public String getPostsByMember(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
            Model model)
    {
        if (loginMember != null) model.addAttribute("member", loginMember);

        List<Post> posts = postRepository.findByWriter_Id(loginMember.getId());

        model.addAttribute("posts", posts);

        return "posts/postList";
    }
}
