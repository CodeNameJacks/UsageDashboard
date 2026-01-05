import { Bar } from 'react-chartjs-2';
//import { Chart, BarController, BarElement } from 'chart.js/auto';
import type { ChartData, ChartOptions } from "chart.js";
import "../lib/chartSetup";


//Chart.register(BarController, BarElement);
const data: ChartData<"bar"> = {
  labels: ["Jan", "Feb", "Mar", "Apr"],
  datasets: [
    {
      label: "Sales",
      data: [120, 190, 300, 250],
      backgroundColor: "rgba(75, 192, 192, 0.6)",
    },
  ],
};

const options: ChartOptions<"bar"> = {
  responsive: true,
  plugins: {
    legend: {
      position: "top",
    },
    title: {
      display: true,
      text: "Monthly Sales",
    },
  },
};

export function BarChart() {
  return <Bar data={data} options={options} />;
}