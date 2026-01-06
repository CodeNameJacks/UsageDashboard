import {Line} from 'react-chartjs-2';
import type { ChartData, ChartOptions } from "chart.js";
import "../lib/chartSetup"; 

type LineChartProps = {
  data: ChartData<"line">;
  options?: ChartOptions<"line">;
};


export function LineChart({data, options}: LineChartProps) {
  return <Line data={data} options = {options} />;
}