package io.k2c1.hereyougo;

import io.k2c1.hereyougo.controller.PostController;
import io.k2c1.hereyougo.domain.Address;
import io.k2c1.hereyougo.domain.Category;
import io.k2c1.hereyougo.domain.Member;
import io.k2c1.hereyougo.domain.Post;
import io.k2c1.hereyougo.repository.CategoryRepository;
import io.k2c1.hereyougo.repository.MemberRepository;
import io.k2c1.hereyougo.repository.PostRepository;
import io.k2c1.hereyougo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 개발단계에 쓰일 테스트용 샘플 리소스들을 만들어봤습니다! (잘했죠?)
 */

@Slf4j
@RequiredArgsConstructor // 리포지토리 생성자
@Component
public class SampleDataInit {

    @Autowired
    public final MemberRepository memberRepository;
    @Autowired
    public final PostRepository postRepository;

    @Autowired
    public final CategoryRepository categoryRepository;

    @Autowired
    private DataSource dataSource;

    Address address = new Address("경기도", "의정부시", "경기도 의정부시 상금로 36, 103동 1601호(금오동, 거성아파트)", "경기도 의정부시 금오동 67-1 거성아파트", "11764");
    Member member = new Member("test@naver.com", "1234", "testNickname", "요식업", address);

    private static long MEMBER_LID = 0L;
    private static long POST_LID = 0L;

    @PostConstruct
    public void init() throws SQLException {
        ClassPathResource resource = new ClassPathResource("data.sql");

        // Execute the script using the DataSource
        ScriptUtils.executeSqlScript(dataSource.getConnection(), resource);

        memberRepository.save(member);
        log.info("SAMPLE MEMBER SAVED IN REPO");

        postRepository.save(createNewPost1());
        postRepository.save(createNewPost2());
        postRepository.save(createNewPost3());
        postRepository.save(createNewPost4());
        log.info("SAMPLE POST SAVED IN REPO");
    }

    public Post createNewPost1()
    {
        return new Post(
                member,
                "테스트 제목 1",
                "테스트 내용 1",
                "10 * 20 * 30",
                0,
                3,
                11,
                address,
                categoryRepository.findById(1L).get(),
                LocalDateTime.now()
        );
    }

    public Post createNewPost2()
    {
        return new Post(
                member,
                "테스트 제목 2",
                "테스트 내용 2",
                "10 * 20 * 30",
                0,
                5,
                12,
                address,
                categoryRepository.findById(3L).get(),
                LocalDateTime.now()
        );
    }

    public Post createNewPost3()
    {
        return new Post(
                member,
                "테스트 제목 3",
                "테스트 내용 3",
                "10 * 20 * 30",
                0,
                10,
                13,
                address,
                categoryRepository.findById(4L).get(),
                LocalDateTime.now()
        );
    }

    public Post createNewPost4()
    {
        return new Post(
                member,
                "테스트 제목 4",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It h",
                "10 * 20 * 30",
                0,
                20,
                14,
                address,
                categoryRepository.findById(37L).get(),
                LocalDateTime.now()
        );
    }
}
