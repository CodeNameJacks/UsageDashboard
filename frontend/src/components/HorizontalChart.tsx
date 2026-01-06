import { Bar } from "react-chartjs-2";
import type { ChartData, ChartOptions } from "chart.js";
import "../lib/chartSetup"; // ensures Chart.js is registered

type HorizontalBarChartProps = {
  data: ChartData<"bar">;
  options?: ChartOptions<"bar">;
};


export function HorizontalChart({data, options}: HorizontalBarChartProps) {
  return <Bar data={data} options={options} />;
}