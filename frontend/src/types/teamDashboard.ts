export interface TopModels {
  models: string[];
  calls: number[];
}

export interface TeamDashboardResponse {
  totalCalls: number;
  totalEstimatedCost: number;
  topModels: TopModels;
}