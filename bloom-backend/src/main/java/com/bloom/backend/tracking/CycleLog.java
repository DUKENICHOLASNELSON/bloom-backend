package com.bloom.backend.tracking;

import com.bloom.backend.user.User;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "cycle_logs")
public class CycleLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate startDate;
    private LocalDate endDate;
    private Integer cycleLength;
    private Integer periodLength;

    @ElementCollection
    @CollectionTable(name = "cycle_symptoms")
    private List<String> symptoms;

    private String notes;

    private LocalDateTime loggedAt = LocalDateTime.now();
}
