export interface TopModels {
  models: string[];
  calls: number[];
}

export interface TeamDashboardResponse {
  dailyTotals: any;
  totalCalls: number;
  totalEstimatedCost: number;
  topModels: TopModels;
}