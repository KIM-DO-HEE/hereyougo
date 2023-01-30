package io.k2c1.hereyougo.service;

import io.k2c1.hereyougo.domain.Appointment;
import io.k2c1.hereyougo.domain.Member;
import io.k2c1.hereyougo.domain.Post;
import io.k2c1.hereyougo.dto.JoinForm;
import io.k2c1.hereyougo.dto.MemberUpdateForm;
import io.k2c1.hereyougo.dto.MyPageForm;
import io.k2c1.hereyougo.repository.AppointmentRepository;
import io.k2c1.hereyougo.repository.MemberRepository;
import io.k2c1.hereyougo.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Transactional
@Service
public class MemberService {
    private MemberRepository memberRepository;
    private AppointmentRepository appointmentRepository;
    private PostRepository postRepository;

    public MemberService(MemberRepository memberRepository, PostRepository postRepository){
        this.memberRepository = memberRepository;
        this.postRepository = postRepository;
//        this.appointmentRepository = appointmentRepository;
    }

    /***
     * 회원가입
     */
    public Long join(JoinForm joinForm){

        Member member = new Member();
        member.setEmail(joinForm.getEmail());
        member.setPassword(joinForm.getPassword());
        member.setNickname(joinForm.getNickname());
        member.setBusinessType(joinForm.getBusinessType());

        isDuplicateMember(member); // 회원 이메일 중복 검사
        memberRepository.save(member);

        return member.getId();
    }

    /***
     * 회원정보 수정
     */
    public void updateMemberInfo(MemberUpdateForm updateForm){
        Member result = memberRepository.findById(updateForm.getId()).get();

        result.updateMemberInfo(
                updateForm.getPassword(),
                updateForm.getNickname(),
                updateForm.getBusinessType()
        );
    }

    /***
     * 회원정보 상세 조회
     */
    public Member findMember(Long memberId){
        return memberRepository.findById(memberId).get();
    }

    /***
     * 회원 마이페이지 조회
     * TODO
     * 키워드, 약속 목록
     * 현재 임의로 memberId를 지정하여 멤버 상세정보를 조회
     * 세션 기반으로 변경
     */
    public MyPageForm mypage(Long memberId){
        MyPageForm myPageForm = new MyPageForm();

        Member member = findMember(memberId);
        myPageForm.setNickname(member.getNickname());

        List<Post> posts = postRepository.findByWriter_Id(memberId);
        myPageForm.setPosts(posts);

        log.info("======post List======="+ posts);


//        키워드 목록 Set하기
//        약속 목록 Set하기
//        myPageForm.setAppointments();

        return myPageForm;
    }

    /***
     * 회원 탈퇴
     */
    public void deleteMember(Member member){
        memberRepository.delete(member);
    }


    /***
     * 회원가입 시 이메일 중복 검사
     */
    public void isDuplicateMember(Member member){
        Optional<Member> result = memberRepository.findByEmail(member.getEmail());
        if(result.isPresent()){
            throw new IllegalStateException("이미 존재하는 이메일입니다");
        }
    }
}