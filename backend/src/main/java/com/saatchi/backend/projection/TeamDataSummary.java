package com.saatchi.backend.projection;

import java.time.LocalDate;

public interface TeamDataSummary {
    LocalDate getDate();
    Long getTotalCalls();
    Long getTotalTokens();
    Double getTotalEstimatedCost();
}
