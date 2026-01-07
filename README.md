# Usage Dashboard

This dashboard displays AI platform usage metrics for a team. It’s built in Java Spring Boot on the back end and React Typescript on the front end.
Mock data has been generated using Mockaroo and is stored in a remote Mysql database. The raw sql data can be found in the sql folder that is in the project’s root directory.

Assumptions:
-	There are teams (teams 1 through 10). You to toggle between teams to update the charts with the team’s metrics accordingly. 
-	There are 10 models: Luna, Phoenix, Stella, Nova, Aurora, Jasmine, Sapphire, Ruby, Amber, and Diamond.
-	Mock data has been generated for a seven-day period only. 
-	The data range selection drop down is there for viewing purposes and has not been implemented.

To run the project:
-	Download the project as a zip file and unzip the file.
-	In your terminal navigate to the `backend` folder and enter `mvn spring-boot: run` in the command line.
-	Open another terminal session and navigate to the `frontend` folder. Enter `npm install` on the command line to install the dependencies. Then enter `npm start` to run the application.
-	In your web browser enter `https://localhost:5173`. You should be able to see the running application.
-	To shut down the backend and frontend servers, go to the respective folders in the terminal and press `control + c`.

To run unit test:
-	Navigate to the `backend` folder in your terminal. 
-	Enter `mnv test` in the command line.
