package com.bloom.backend.model;

import com.bloom.backend.user.User;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "discharge_logs")
public class DischargeLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDate logDate;
    private String colour;
    private String consistency;
    private String odour;
    private String category;
    private String notes;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public LocalDate getLogDate() { return logDate; }
    public void setLogDate(LocalDate logDate) { this.logDate = logDate; }

    public String getColour() { return colour; }
    public void setColour(String colour) { this.colour = colour; }

    public String getConsistency() { return consistency; }
    public void setConsistency(String consistency) { this.consistency = consistency; }

    public String getOdour() { return odour; }
    public void setOdour(String odour) { this.odour = odour; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}