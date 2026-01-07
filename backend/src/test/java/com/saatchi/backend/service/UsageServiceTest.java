package com.saatchi.backend.service;

import com.saatchi.backend.repository.TeamDataRepository;
import com.saatchi.backend.projection.TeamDataSummary;
import com.saatchi.backend.projection.ModelUsageSummary;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeamDataServiceTest {

    @Mock
    private TeamDataRepository teamDataRepository;

    @InjectMocks
    private TeamDataService teamDataService;

    @Test
    void getTeamDataByTeamId_success() {
        int teamId = 1;

        // mock daily totals
        TeamDataSummary summary = mock(TeamDataSummary.class);
        when(summary.getDate()).thenReturn(LocalDate.of(2026, 1, 1));
        when(summary.getTotalCalls()).thenReturn(100L);
        when(summary.getTotalTokens()).thenReturn(100L);
        when(summary.getTotalEstimatedCost()).thenReturn(12.34);

        List<TeamDataSummary> summaries = List.of(summary);

        // mock top models
        ModelUsageSummary modelSummary = mock(ModelUsageSummary.class);
        when(modelSummary.getModel()).thenReturn("Luna");
        when(modelSummary.getCalls()).thenReturn(50L);

        List<ModelUsageSummary> topModels = List.of(modelSummary);

        when(teamDataRepository.findTeamTotals(teamId))
            .thenReturn(summaries);

        when(teamDataRepository.findTopModelsByTeamId(
                eq(teamId),
                any(PageRequest.class))
        ).thenReturn(topModels);

        // execute 
        Map<String, Object> result =
            teamDataService.getTeamDataByTeamId(teamId);
        
        // verify 
        assertNotNull(result);
        assertTrue(result.containsKey("dailyTotals"));
        assertTrue(result.containsKey("topModels"));

        @SuppressWarnings("unchecked")
        Map<String, Object> dailyTotals =
            (Map<String, Object>) result.get("dailyTotals");

        assertEquals(
            List.of(LocalDate.of(2026, 1, 1)),
            dailyTotals.get("date")
        );

        assertEquals(
            List.of(100L),
            dailyTotals.get("totalCalls")
        );

        assertEquals(
            List.of(100L),
            dailyTotals.get("totalTokens")
        );

        assertEquals(
            List.of(12.34),
            dailyTotals.get("totalEstimatedCost")
        );

        @SuppressWarnings("unchecked")
        Map<String, Object> topModelsResult =
            (Map<String, Object>) result.get("topModels");

    
        assertEquals(List.of("Luna"), topModelsResult.get("models"));
        assertEquals(List.of(50L), topModelsResult.get("calls"));
    }  
    
    @Test
    void getTeamDataByTeamId_invalidTeamId_throwsException() {
        IllegalArgumentException ex =
            assertThrows(
                IllegalArgumentException.class,
                () -> teamDataService.getTeamDataByTeamId(0)
            );

        assertEquals("A valid team id is required", ex.getMessage());
    }

    @Test
    void getTeamDataByTeamId_noDataFound_throwsException() {
        int teamId = 99;

        when(teamDataRepository.findTeamTotals(teamId))
            .thenReturn(Collections.emptyList());

        NoSuchElementException ex =
            assertThrows(
                NoSuchElementException.class,
                () -> teamDataService.getTeamDataByTeamId(teamId)
            );

        assertEquals("Team not found", ex.getMessage());
    }
}