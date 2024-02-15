

REST API Endpoints:

1. POST : http://localhost:8082/api/v1/parking/create
   {
    "totalCapacity":3
   }

2. POST : http://localhost:8082/api/v1/parking/park-new
   {
    "regNo":"KA-32-WA-3204",
    "color":"Black"
   }
   Know the parking status
3. GET : http://localhost:8082/api/v1/parking/status

  Get slot numbers where "White" color car's are parked 
4. GET : http://localhost:8082/api/v1/parking/slotno-color/Black

  Get slot number of a car by its registration number "KA-33-EA-0923"
5. GET : http://localhost:8082/api/v1/parking/slotno-regno/KA-33-EA-0923

  Get Registration numbers list by car color "White"
6. GET : http://localhost:8082/api/v1/parking/regno-color/White

  When leaving the parking are remove the car from the slot
7. DELETE : http://localhost:8082/api/v1/parking/leave/2


