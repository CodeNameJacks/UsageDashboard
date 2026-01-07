import { Bar } from 'react-chartjs-2';
//import { Chart, BarController, BarElement } from 'chart.js/auto';
import type { ChartData, ChartOptions } from "chart.js";
import "../lib/chartSetup";


type BarChartProps = {
  data: ChartData<"bar">;
  options?: ChartOptions<"bar">;
};


export function BarChart({data, options}: BarChartProps) {
  return <Bar data={data} options={options} />;
}