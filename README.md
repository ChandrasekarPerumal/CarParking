
<b> Instructions to followed: </b><br>
 <i>  1.First create the space using 1st end-point<br>
   2.Add car's data for ticketing data<br>
   3.Then check the other endpoints<br></i>

<b>REST API Endpoints:</b>

1. POST : http://localhost:8082/api/v1/parking/create
   {
    "totalCapacity":3
   }

2. POST : http://localhost:8082/api/v1/parking/park-new
   {
    "regNo":"KA-32-WA-3204",
    "color":"Black"
   }
   <br>
3. GET : http://localhost:8082/api/v1/parking/status
   Know the parking status
   <br>  
4. GET : http://localhost:8082/api/v1/parking/slotno-color/Black
   Get slot numbers where "White" color car's are parked
   <br>
5. GET : http://localhost:8082/api/v1/parking/slotno-regno/KA-33-EA-0923
   Get slot number of a car by its registration number "KA-33-EA-0923"<br>
  
6. GET : http://localhost:8082/api/v1/parking/regno-color/White
   Get Registration numbers list by car color "White"<br>
  
7. DELETE : http://localhost:8082/api/v1/parking/leave/2
   When leaving the parking are remove the car from the slot<br>


