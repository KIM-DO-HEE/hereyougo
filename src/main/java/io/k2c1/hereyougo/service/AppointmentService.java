package io.k2c1.hereyougo.service;

import io.k2c1.hereyougo.constant.Progress;
import io.k2c1.hereyougo.domain.Appointment;
import io.k2c1.hereyougo.domain.Member;
import io.k2c1.hereyougo.domain.Post;
import io.k2c1.hereyougo.dto.AppointmentForm;
import io.k2c1.hereyougo.repository.AppointmentRepository;
import io.k2c1.hereyougo.repository.MemberRepository;
import io.k2c1.hereyougo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private MemberRepository memberRepository;

    /**
     * 약속 등록
     */
    public void makeAppointment(AppointmentForm appointmentForm){
        Appointment appointment= new Appointment();

        Long postId = appointmentForm.getPostId();
        Post post =  postRepository.findById(postId).get();

        int reservationQuantity = post.getReservationQuantity(); // 현재 post 예약 수량
        int appointmentQuantity = appointmentForm.getRequireQuantity(); // 구매자가 원하는 수량
        int remainQuantity = reservationQuantity - appointmentQuantity;

//      구매자가 원하는 수량(=약속 수량)과 Post 예약 수량 비교
        if(0 < remainQuantity ){
            Member member = memberRepository.findById(appointmentForm.getMemberId()).get();
            appointment.setPost(post);
            appointment.setWanted(member);
            appointment.setProgress(Progress.RESERVING);

            appointmentRepository.save(appointment);

        }else if( remainQuantity == 0){
            throw new IllegalStateException("현재 예약을 하실 수 없습니다");
        }else{
            throw new IllegalStateException(remainQuantity + "개 예약이 가능 합니다");
        }
    }
}
