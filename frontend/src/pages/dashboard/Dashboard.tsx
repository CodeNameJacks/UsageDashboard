import { useEffect, useState, type JSX } from "react";
import { type TeamDashboardResponse } from "../../types/teamDashboard";
import { LineChart } from "../../components/LineChart";
import { BarChart } from "../../components/BarChart";
import { HorizontalChart } from "../../components/HorizontalChart";
import type { ChartOptions } from "chart.js";



export default function TeamDashboard(): JSX.Element {
  const [teamId, setTeamId] = useState<number>(1); // default team
  const [data, setData] = useState<TeamDashboardResponse | null>(null);
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<string | null>(null);
  const teams = Array.from({ length: 10 }, (_, i) => i + 1); // 1 to 10

  useEffect(() => {
    async function fetchData() {
      setLoading(true);
      setError(null);

      try {
        const res = await fetch(`http://localhost:8080/team/${teamId}`);

        if (!res.ok) {
          const errorData = await res.json();
          setError(errorData.error ?? "Unknown error");
          setData(null);
          setLoading(false);
          return;
        }

        const json: TeamDashboardResponse = await res.json();
        setData(json);
      } catch (err) {
        setError("Failed to fetch data");
        setData(null);
        console.error(err);
      } finally {
        setLoading(false);
      }
    }

    fetchData();
  }, [teamId]);

  //handle dropdown selction change
  const handleTeamChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setTeamId(Number(e.target.value));
  };

  // Chart.js data configurations
  const top3Data = data
    ? {
        labels: data.topModels.models,
        datasets: [
          {
            label: "API Calls per Model",
            data: data.topModels.calls,
            backgroundColor: "rgba(75, 192, 192, 0.7)",
          },
        ],
      }
    : { labels: [], datasets: [] };

    const callData = data
    ? {
        labels: data.dailyTotals.date,
        datasets: [
          {
            label: "Number of API Calls",
            data: data.dailyTotals.totalCalls,
            backgroundColor: "rgba(75, 192, 192, 0.7)",
          },
        ],
      }
    : { labels: [], datasets: [] };

    const costData = data
    ? {
        labels: data.dailyTotals.date,
        datasets: [
          {
            label: "Cost ($)",
            data: data.dailyTotals.totalEstimatedCost,
            backgroundColor: "rgba(75, 192, 192, 0.7)",
          },
        ],
      }
    : { labels: [], datasets: [] };

     const tokenData = data
    ? {
        labels: data.dailyTotals.date,
        datasets: [
          {
            label: "Number of Tokens",
            data: data.dailyTotals.totalTokens,
            backgroundColor: "rgba(75, 192, 192, 0.7)",
          },
        ],
      }
    : { labels: [], datasets: [] }; 

    //chart options
    const tokenOptions: ChartOptions<"bar"> = {
      indexAxis: "y", 
      responsive: true,
      plugins: {
        legend: {
          position: "bottom",
        },
        title: {
          display: true,
          text: "Daily Tokens Consumed",
          font: {
            size: 22, 
            family: "Arial",
            weight: "bold", 
          },
          padding: {
            bottom: 30, 
          },
        },
      },
    };

    const callOptions: ChartOptions<"line"> = {
      responsive: true,
      plugins: {
        legend: {
          position: "bottom",
        },
        title: {
          display: true,
          text: "Daily API Calls",
          font: {
            size: 22, 
            family: "Arial",
            weight: "bold", 
          },
          padding: {
            bottom: 30, 
          },
        },
      },
    };
    
    const costOptions: ChartOptions<"line"> = {
      responsive: true,
      plugins: {
        legend: {
          position: "bottom",
        },
        title: {
          display: true,
          text: "Daily Total Costs",
          font: {
            size: 22, 
            family: "Arial",
            weight: "bold", 
          },
          padding: {
            bottom: 30, 
          },
        },
      },
    };

    const top3Options: ChartOptions<"bar"> = {
      responsive: true,
      plugins: {
        legend: {
          position: "bottom",
        },
        title: {
          display: true,
          text: "Top 3 Models",
          font: {
            size: 22, 
            family: "Arial",
            weight: "bold", 
          },
          padding: {
            bottom: 30, 
          },
        },
      },
    };
    
  return (
        <>
          {console.log(data)}
          <div className='page-wrapper'>
            <h2>Usage Dashboard</h2>
            <div className='selectTeam'>
              <label htmlFor="team-select">
                Select Team:{" "}
              </label>
              <select id="teamSelect" value={teamId} tabIndex={0} onChange={handleTeamChange}>
              {teams.map((team) => (
                <option key={team} value={team} tabIndex={0}>
                  Team {team}
                </option>
              ))}
            </select>
          </div>
            {loading ? (
              <p className='loading'>Loading...</p>
            ) : error ? (
              <p style={{ color: "red" }}>Error: {error}</p>
            ) : data ? (
          <>
            <div className='row'>
              <div className='column'>
                <div className='chart'>
                  <div className='chartContainer' style={{ width: 450 }}>
                   {data && !loading ? <LineChart data={callData} options={callOptions} /> : null}
                  </div>
                </div>
              </div>
              <div className='column'>
                <div className='tokens' id='tokens'>
                  <div className='chart'>
                    <div className='chartContainer' style={{ width: 450 }}>
                    {data && !loading ? <HorizontalChart data={tokenData} options={tokenOptions}/> : null}
                    </div>
                  </div>
                </div>
              </div>
            </div>
            </>): null}
            <div className='row 2'>
              <div className='column'>
                <div className='chart' id='top3'>
                  <div className='chartContainer' style={{ width: 450 }}>
                   {data && !loading ? <BarChart data={top3Data} options={top3Options}/> : null}
                  </div>
                </div>
              </div>
              <div className='column'>
                <div className='chart'>
                  <div className='chartContainer' style={{ width: 450 }}>
                   {data && !loading ? <LineChart data={costData} options={costOptions} /> : null}
                  </div>
                </div>
              </div>
              
            </div>
          </div>
        </>
        
    )
}

