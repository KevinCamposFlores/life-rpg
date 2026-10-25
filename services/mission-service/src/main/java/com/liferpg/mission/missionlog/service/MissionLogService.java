package com.liferpg.mission.missionlog.service;

import com.liferpg.mission.mission.entity.Mission;
import com.liferpg.mission.mission.entity.MissionType;
import com.liferpg.mission.mission.mapper.MissionMapper;
import com.liferpg.mission.missionlog.dto.CreateMissionLogRequest;
import com.liferpg.mission.missionlog.dto.MissionLogResponse;
import com.liferpg.mission.missionlog.entity.MissionLog;
import com.liferpg.mission.missionlog.exception.MissionWasCompletedTodayException;
import com.liferpg.mission.missionlog.exception.ValueIsLessThanTragetValueException;
import com.liferpg.mission.missionlog.mapper.MissionLogMapper;
import com.liferpg.mission.missionlog.repository.MissionLogRepository;
import com.liferpg.mission.weeklyMission.entity.WeeklyMission;
import com.liferpg.mission.weeklyMission.entity.WeeklyMissionStatus;
import com.liferpg.mission.weeklyMission.exception.WeeklyMissionStatusNotInProgresException;
import com.liferpg.mission.weeklyMission.service.WeeklyMissionsService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class MissionLogService {

    private final WeeklyMissionsService weeklyMissionsService;
    private final MissionLogRepository missionLogRepository;

    public MissionLogService (WeeklyMissionsService weeklyMissionsService, MissionLogRepository missionLogRepository){
        this.weeklyMissionsService  = weeklyMissionsService;
        this.missionLogRepository = missionLogRepository;
    }

    @Transactional
    public MissionLogResponse completeMission(Long weeklyMissionId,CreateMissionLogRequest createMissionLogRequest){

        WeeklyMission weeklyMission = weeklyMissionsService.findById(weeklyMissionId);

        if(weeklyMission.getStatus() != (WeeklyMissionStatus.IN_PROGRESS)){ // CUANOD USAMOS ENUM ES MEJOR !=
            throw  new WeeklyMissionStatusNotInProgresException("Mission is not status IN_PROGRESS: " + weeklyMissionId);
        }

        BigDecimal targetValue =
                weeklyMission.getMission().getTargetValue();

        if(targetValue != null){

        if(createMissionLogRequest.value().compareTo(weeklyMission.getMission().getTargetValue()) < 0){
            throw  new ValueIsLessThanTragetValueException("Value is less than targetValue");
        }
        }

      MissionLog missionLog=  MissionLogMapper.toEntity(createMissionLogRequest, weeklyMission);

        if( weeklyMission.getMission().getMissionType() == MissionType.DAILY) {

            LocalDate today = LocalDate.now();
            boolean missionLogCompletedToday = existsByWeeklyMission_IdAndCompletedAtGreaterThanEqualAndCompletedAtLessThan(weeklyMissionId,
                    today.atStartOfDay(),
                    today.plusDays(1).atStartOfDay());

            if(missionLogCompletedToday){
            throw new MissionWasCompletedTodayException("Mission already completed Today ");
            }
        }

        MissionLog missionLogCreated = missionLogRepository.save(missionLog);

        Long completedCount = findCountMissionLogForWeeklyMission(weeklyMissionId);

        if( completedCount >=  weeklyMission.getTargetCount()){
            weeklyMission.setStatus(WeeklyMissionStatus.COMPLETED);
        }

        return  MissionLogMapper.toResponse( missionLogCreated,completedCount, weeklyMission.getTargetCount(), weeklyMission.getStatus()  );

    }

    public Long findCountMissionLogForWeeklyMission( Long weeklyMissionId){
      return missionLogRepository.findCountMissionLogForWeeklyMission(weeklyMissionId);
    }

    public boolean existsByWeeklyMission_IdAndCompletedAtGreaterThanEqualAndCompletedAtLessThan(Long weeklyMissionId, LocalDateTime begin, LocalDateTime end){
        return missionLogRepository.existsByWeeklyMission_IdAndCompletedAtGreaterThanEqualAndCompletedAtLessThan(weeklyMissionId, begin, end);
    }


}
