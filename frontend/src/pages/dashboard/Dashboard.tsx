import React from "react";
import {BarChart} from "../../components/BarChart"

const Dashboard = () =>{
    const expenses = [
        {id: 1, name: "water bill", amount: 200.00, date: new Date().toDateString()},
        {id: 2, name: "electricity bill", amount: 500.00, date: new Date().toDateString()},
        {id:3, name: "wifi bill", amount: 700.00, date: new Date().toDateString()},
    ]
    return (
        
        <div className='some-page-wrapper'>
  <div className='row'>
    <div className='column'>
      <div className='apiCalls'>
        <div className="apiText">
            <h1>Total API Calls</h1>
            <p>455</p>
        </div>
      </div>
    </div>
    <div className='column'>
      <div className='tokens'>
       <div className="tokensText">
            <h1>Tokens Consumed</h1>
            <p>455</p>
        </div>
      </div>
    </div>
    <div className='column'>
      <div className='cost'>
        <div className="costText">
            <h1>Total Cost</h1>
            <p>$455</p>
        </div>
      </div>
    </div>
  </div>
  <div className='row 2'>
    <div className='column'>
      <div className='chart'>
        <div className='chartContainer' style={{ width: 600 }}>
      <BarChart />
    </div>
      </div>
    </div>
    
  </div>
</div>
        
    )
}

export default Dashboard