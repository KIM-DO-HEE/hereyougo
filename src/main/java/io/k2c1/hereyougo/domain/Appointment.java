package io.k2c1.hereyougo.domain;

import io.k2c1.hereyougo.constant.Progress;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appointment")
public class Appointment
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Long id;

    /**
     * TODO JoinColumn의 name에 관해서 자세한 구글링을 해봐야 할듯해요! 😉
     */
    @ManyToOne
    @JoinColumn(name = "wanted_id")
    private Member wanted;

    @OneToOne
    private Post post;

    private int appointmentQuantity; // 예약 수량

    private LocalDateTime timestamp; // 약속한 일/시

    private Progress progress;
}
