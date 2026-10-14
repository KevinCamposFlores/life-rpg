package com.liferpg.mission.missionlog.entity;


import com.liferpg.mission.weeklyMission.entity.WeeklyMission;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mission_log")
public class MissionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "weekly_middion_id", nullable = false )
    private WeeklyMission weeklyMission;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @Column(name = "value")
    private Double value;

    @Column(name = "notes")
    private String notes;

    @Column(name = "xp_reward_snapshot")
    private Integer xpRewardSnapshot;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Version
    private Long version;

    @PrePersist
    public void prePersist(){
        this.createdAt = LocalDateTime.now();
    }

}
